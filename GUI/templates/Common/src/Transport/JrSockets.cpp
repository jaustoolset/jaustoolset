/*! 
 ***********************************************************************
 * @file      JrSockets.cpp
 * @author    Dave Martin, DeVivo AST, Inc.  
 * @date      2008/03/03
 *
 *  Copyright (C) 2008. DeVivo AST, Inc
 *
 *  This file is part of Jr Middleware.
 *
 *  Jr Middleware is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Jr Middleware is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Jr Middleware.  If not, see <http://www.gnu.org/licenses/>.
 *
 ************************************************************************
 */
#include "Transport/JrSockets.h"
#include "Transport/ConfigData.h"
#include "Transport/JrLogger.h"
#include "Transport/JUDPArchive.h"
#include <fcntl.h>
#include <errno.h>
#include <sstream>

using namespace DeVivo::Junior;

#ifdef WINDOWS
#define SOCK_PATH "\\\\.\\mailslot\\"
#else
#define SOCK_PATH "/tmp/"
#endif

JrSocket::JrSocket(std::string name):
     sock(),
     _is_connected(false),
     _map(),
     _socket_name(name),
	 _type(POLL)
{
    my_name = "JSocket";
}


JrSocket::~JrSocket()
{
    if (sock) 
    {
#ifdef WINDOWS
        CloseHandle(sock);
#else
        std::stringstream s; s << SOCK_PATH; s << _socket_name;
        close(sock);
        unlink(s.str().c_str());
#endif
    }
}

unsigned char  JrSocket::messagesInQueue()
{
    // Returns the number of messages waiting in the queue
#ifdef WINDOWS
    DWORD count;
    GetMailslotInfo(sock, NULL, NULL, &count, NULL);
    return ((unsigned char) count);
#else
    // for Linux, we can't know the number of messages, just that
    // there is more than zero.
    struct timeval timeout;
    timeout.tv_sec=0; timeout.tv_usec=0;
    fd_set set; FD_ZERO(&set); FD_SET(sock, &set);
    if (select(sock+1, &set, NULL, NULL, &timeout) > 0) return 1;
    return 0;
#endif
}

void JrSocket::setWaitType( enum SocketWaitType type )
{
	_type = type;
#ifdef WINDOWS
	DWORD timeout = (_type == POLL) ? 0 : MAILSLOT_WAIT_FOREVER;
    SetMailslotInfo(sock, timeout);
#endif
}


#ifdef WINDOWS
SocketId JrSocket::OpenMailslot(std::string name)
{
    std::stringstream s; s << SOCK_PATH; s << name;
    return CreateFile(s.str().c_str(), 
         GENERIC_WRITE, FILE_SHARE_READ | FILE_SHARE_WRITE, 
         NULL, OPEN_EXISTING, 0, NULL);
}
#endif

void JrSocket::openResponseChannel(Message* msg)
{
#ifdef WINDOWS
    // For Windows, we need to open a mailslot back to the sender, if we
    // don't already have it.
    SocketId sockname;
    if (_map.getAddrFromId(msg->getSourceId(), sockname) == false)
    {
        std::stringstream s; s << msg->getSourceId().val;
        HANDLE source = OpenMailslot(s.str());
        if (source != INVALID_HANDLE_VALUE) 
            _map.addElement(msg->getSourceId(), source, AS5669);
    }
#else
    // For Unix, we just use the ID as the name of the socket.
    // The AddressMap class will prevent duplicates.
    std::stringstream s; s << SOCK_PATH; s << msg->getSourceId().val;
    _map.addElement(msg->getSourceId(), s.str(), AS5669);
#endif
}

Transport::TransportError JrSocket::sendMsg(Message& msg, SocketId sockname)
{
    // Serialize the message before sending it.  Note that the header
	// version depends on the setting of the message code.
    JUDPArchive archive;
	archive.pack(msg, msg.getMessageCode() == 0 ? AS5669A : AS5669);
	JrDebug << "Sending socket message to " << msg.getDestinationId().val << std::endl;

    // Send to the given socket
#ifdef WINDOWS
    DWORD cbWritten;
    bool fSuccess = WriteFile( sockname, archive.getArchive(), 
        archive.getArchiveLength(), &cbWritten, NULL);
    if (!fSuccess || (cbWritten != archive.getArchiveLength())) 
	{
		JrError << "Unable to write on local socket (" << cbWritten << " of "
			<< archive.getArchiveLength() << "written)\n";
		return Failed;
	}
#else
    struct sockaddr_un addr;
    memset(addr.sun_path, 0, sizeof(addr.sun_path));
    addr.sun_family = AF_UNIX;
    memcpy(addr.sun_path, sockname.c_str(), sockname.length());
    int ret = sendto(sock, archive.getArchive(), archive.getArchiveLength(), 0,
       (struct sockaddr *)&addr, sizeof(struct sockaddr_un));
    if (ret != archive.getArchiveLength()) 
	{
		JrError << "Unable to write on local socket (" << ret << " of "
			<< archive.getArchiveLength() << "written)\n";
		return Failed;
	}
#endif

    return Ok;
}

Transport::TransportError JrSocket::sendMsg(Message& msg)
{
    Transport::TransportError result = AddrUnknown;

    // If the socket is connected, the endpoint is pre-specified.
    // We can send the archive without much fuss.
    if (_is_connected)
    {
        // Send it to the connected socket
        result = sendMsg(msg, _connected_dest);
    }
    else
    {
        // Otherwise, send to the destination id specified.  Note that the destination
        // specified in the message may contain wildcard characters.  We need to loop
        // through all known destinations, sending to any that match (except the source). 
        JAUS_ID dest = msg.getDestinationId();
		for (int i = 0; i < _map.getList().size(); i++)
        {
            if (msg.getDestinationId() == _map.getList()[i]->getId())
            {
                msg.setDestinationId(_map.getList()[i]->getId());
                result = sendMsg(msg, _map.getList()[i]->getAddress());

                // If we failed to send to this destination, remove
                // it from our map.
                if (result == Failed)
                    removeDestination(_map.getList()[i]->getId());
            }
        }

        // Restore the initial destination identifier before we return.
        msg.setDestinationId( dest );
    }
    return result;
}

Transport::TransportError JrSocket::recvMsg(MessageList& msglist)
{
    // Assume we don't have any messages to return...
    Transport::TransportError ret = NoMessages;

    // Recv the message into a finite sized buffer
    char buffer[5000];
    int bytes = 0;

    // Check the recv port in a loop, exiting only when we have
    // no messages in the buffer (POLL) or we've received 1 messages (PEND)
    do
    {

#ifdef WINDOWS
 
        // Read the mailslot as if it's a file descriptor
        bool fSuccess; DWORD bytesread;
        fSuccess = ReadFile( sock, buffer, 5000, &bytesread, NULL);  
        if (!fSuccess) break;
        bytes = bytesread;

#else

        // See if we have anything waiting before we call recvfrom
		if (_type == POLL)
		{
			struct timeval timeout;
			timeout.tv_sec=0; timeout.tv_usec=0;
			fd_set set; FD_ZERO(&set); FD_SET(sock, &set);
			if (select(sock+1, &set, NULL, NULL, &timeout) == 0) break;
		}

        // Getting here means we have a message.  Read and process it.
        struct sockaddr_un addr;
        memset(addr.sun_path, 0, sizeof(addr.sun_path));
        addr.sun_family = AF_UNIX;
        int addr_len = sizeof(struct sockaddr_un);
        bytes = recvfrom(sock, buffer, 5000, 0, 
                         (struct sockaddr*)&addr, 
                         (socklen_t*) &addr_len);

#endif

        // If we didn't receive anything, break from the read loop.
        if (bytes <= 0) break;
 
        // Now that we have a datagram in our buffer, move it to an archive.
        JUDPArchive archive;
        archive.setData(buffer, bytes);

        // And unpack it...
        Message* msg = new Message();
        archive.unpack(*msg);

		JrDebug << "Received socket message from " << msg->getSourceId().val << std::endl;

        // If we're not a connected socket, open a response
        // channel to the sender so we can talk to it later.
        if (!_is_connected) openResponseChannel(msg);

        // Add the message to the MessageList and change the return value
        msglist.push_back(msg);
        ret = Ok;
    } while (_type == POLL);

    return ret;
}

Transport::TransportError JrSocket::broadcastMsg(Message& msg)
{
    Transport::TransportError result = AddrUnknown;

    // Connected sockets send to a single destination only.
    if (_is_connected)
    {
        sendMsg(msg);
    }
    else
    {
        // Loop through all known destinations, sending the message to
        // each socket that matches the destination (including wildcards).
        JAUS_ID dest = msg.getDestinationId();
		for (int i = 0; i < _map.getList().size(); i++)
        {
            if ((msg.getDestinationId() == _map.getList()[i]->getId()) &&
                (msg.getSourceId() != _map.getList()[i]->getId()))
                result = sendMsg(msg, _map.getList()[i]->getAddress());

            // If we failed to send to this destination, remove
            // it from our map.
            if (result == Failed)
                removeDestination(_map.getList()[i]->getId());
        }
    }
    return Ok;
}

Transport::TransportError JrSocket::initialize(ConfigData& config)
{
    // Set-up is considerably different for UNIX sockets and
    // Windows named pipes.
#ifdef WINDOWS
    std::stringstream s; s << SOCK_PATH; s << _socket_name;
	DWORD timeout = (_type == POLL) ? 0 : MAILSLOT_WAIT_FOREVER; 
    sock = CreateMailslot(s.str().c_str(), 0, timeout, NULL); 
    if (sock == INVALID_HANDLE_VALUE)
    {
        JrError << "Internal error.  Cannot initialize mailslot for IPC comms\n";
        return Failed;
    }
#else

    // Create the socket
    sock = socket(AF_UNIX, SOCK_DGRAM, 0);
    if (sock==-1) return InitFailed;

    // Bind to the given filename
    struct sockaddr_un addr;
    addr.sun_family = AF_UNIX;
    std::stringstream s; s << SOCK_PATH; s << _socket_name;
    memset(addr.sun_path, 0, sizeof(addr.sun_path));
    memcpy(addr.sun_path, s.str().c_str(), s.str().length());
    unlink(addr.sun_path);
    if (bind(sock, (struct sockaddr *)&addr, sizeof(struct sockaddr_un)) != 0)
    {

        JrError << "Bind failed for local socket (" << s.str() << ").  errno=" <<
            errno << std::endl;
        return InitFailed;
    }

    // Read the configuration file for buffer size info
    socklen_t buffer_size = 10000;
    config.getValue(buffer_size, "MaxBufferSize", "UDP_Configuration");

    // Increase the size of the send/receive buffers
    int length = sizeof(buffer_size);
    setsockopt(sock, SOL_SOCKET, SO_RCVBUF, (char*)&buffer_size, length);
    setsockopt(sock, SOL_SOCKET, SO_SNDBUF, (char*)&buffer_size, length);

#endif

    return Ok;
}

Transport::TransportError JrSocket::setDestination(std::string destination)
{
    // Connect to the given endpoint
    
#ifdef WINDOWS
    _connected_dest = OpenMailslot(destination);
    if (_connected_dest == INVALID_HANDLE_VALUE) return Failed;
#else
    std::stringstream name; name << SOCK_PATH; name << destination;
    _connected_dest = name.str();
#endif

    _is_connected = true;
    return Transport::Ok;
}

Transport::TransportError JrSocket::removeDestination(JAUS_ID id)
{
#ifdef WINDOWS
    // For Windows, we need to close the mailslot associated
    // with this destination
    SocketId sockname;
    if (_map.getAddrFromId(id, sockname) == false)
        CloseHandle(sockname);
#endif

    // Remove this destination from the map
    _map.removeElement(id);
    return Transport::Ok;
}
