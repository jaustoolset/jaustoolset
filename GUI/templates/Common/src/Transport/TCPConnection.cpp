/*! 
 ***********************************************************************
 * @file      TCPConnection.cpp
 * @author    Dave Martin, DeVivo AST, Inc.  
 * @date      2008/08/03
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

#include "Transport/TCPConnection.h"
#include "Transport/JrLogger.h"
#include <fcntl.h>
#include <errno.h>
#include <sys/types.h>

using namespace DeVivo::Junior;

#ifdef WINDOWS
#define getSocketError WSAGetLastError()
#else
#define getSocketError errno
#define closesocket close
#endif

// TCP Connection class implementation
JTCPConnection::JTCPConnection(int socket):
    _socket(socket),
    _incoming_stream(),
    _isStreamActive(false)
{
}

JTCPConnection::~JTCPConnection()
{
    if (_socket > 0) closesocket(_socket);
}


// Helper function to send to a given socket
Transport::TransportError JTCPConnection::sendMsg(Message& msg)
{
    Transport::TransportError result = Transport::Ok;

    // Serialize the message to send.
    JTCPArchive payload;
	payload.pack(msg, _version);

    // By default, a JTCPArchive includes the version byte.
    // After we send the first message, however, the version byte is not needed.
    if (_isStreamActive) payload.removeVersion();
    else _isStreamActive = true;

    // Send the data
    int val = send(_socket, payload.getArchive(), payload.getArchiveLength(), 0);
    if (val < 0) 
    {
        JrError << "Unable to send TCP packet.  Error: " << getSocketError << std::endl;
        result = Transport::Failed;
    }
    else if (val != payload.getArchiveLength())
    {
        JrError << "Unable to send full TCP packet.  Sent  " << val << " of " <<
            payload.getArchiveLength() << " bytes.\n";
        result = Transport::Failed;
    }
    else
    {
        JrDebug << "Sent " << payload.getArchiveLength() << "bytes on TCP connection\n";
    }

    return result;
}


Transport::TransportError JTCPConnection::recvMsg(MessageList& msglist)
{
    char buffer[5000];
    Transport::TransportError ret = Transport::NoMessages;

    // We need to check the socket for waiting data.  If we
    // process data on the socket, we can 
    // subsequently check the archive to see if it contains a full 
    // message.  Any messages get added to the list returned
    // to the caller.
    struct timeval timeout;
    timeout.tv_sec=0; timeout.tv_usec=0;
    fd_set set; FD_ZERO(&set); FD_SET(_socket, &set);
    if (select(_socket+1, &set, NULL, NULL, &timeout) == 0) return ret;

    // getting here means we have data.  pull it.
    int len = recv(_socket, buffer, 5000, 0);
	if (len == 0) return Transport::ConnectionClosed;
    if (len < 0) return Transport::Failed;
    JrDebug << "Read " << len << " bytes on TCP port\n";

    // Since TCP data represents a stream, we can't assume the data
    // read represents a complete message.  Add it to the data previously
    // received.
    _incoming_stream.append(buffer, len);

    // If we've accrued a valid packet, shape it into a message
    while (_incoming_stream.isArchiveValid())
    {
        Message* msg = new Message();
		_incoming_stream.unpack(*msg);

        // Make sure we record the JAUS_ID of the sender
        if (_id.val == 0) _id = msg->getSourceId();
        if (_version == UnknownVersion) 
            _version = _incoming_stream.getVersion();

        // Add the message to the list and change the return value
        JrDebug << "Found valid TCP message (size " << msg->getDataLength() << ")\n";
        msglist.push_back(msg);
        ret = Transport::Ok;

        // Remove this message from the archive.
        _incoming_stream.removeHeadMsg();
    }

    return ret;
}


// Implementation for Connection List Manager
JTCPConnection* JTCPConnectionList::addConnection(int socket)
{
    // Make sure we don't already have a connection.
    if (_connections.count(socket) > 0) return _connections[socket];

    // Create the TCP Connection object for this socket
    JTCPConnection* ptr = new JTCPConnection(socket);
    if (ptr == NULL) return NULL;

    // Add it to the map
    _connections[socket] = ptr;
    return ptr;
}

void JTCPConnectionList::closeConnection(int socket)
{
    // Make sure we have a connection.
    if (_connections.count(socket) == 0) return;

    // Close it
    delete (_connections[socket]);

    // And remove from the map
    _connections.erase(socket);
}

void JTCPConnectionList::closeAllConnections()
{
    // Loop through the map entries, deleting the connection 
	// before clearing the entire map.
    std::map<int, JTCPConnection*>::iterator iter;
    for (iter = _connections.begin(); iter != _connections.end(); iter++)
		delete (iter->second);
	_connections.clear();

}

JTCPConnection* JTCPConnectionList::getConnection(JAUS_ID id, MsgVersion version)
{
    // check for null case. JAUS ids of zero are not permitted.
    if (id == 0) return NULL;

    // look-up connection by JAUS id.  This is a little harder since we have
    // to romp through the map manually.
    std::map<int, JTCPConnection*>::iterator iter;
    for (iter = _connections.begin(); iter != _connections.end(); iter++)
        if ((id == iter->second->getId()) && 
			(version == iter->second->getVersion()))
			return iter->second;
    return NULL;
}

// These functions act on all elements in the list
Transport::TransportError JTCPConnectionList::sendMsgToAll(Message& msg)
{
    std::map<int, JTCPConnection*>::iterator iter;
    for (iter = _connections.begin(); iter != _connections.end(); iter++)
        iter->second->sendMsg(msg);
    return Transport::Ok;
}

Transport::TransportError JTCPConnectionList::recvMsgs(MessageList& msglist)
{
    std::map<int, JTCPConnection*>::iterator iter, temp;
    for (iter = _connections.begin(); iter != _connections.end(); )
	{
        if (iter->second->recvMsg(msglist) == Transport::ConnectionClosed)
		{
			// remote entity has closed the connection
			JrDebug << "Detected shutdown of TCP connection.  Closing...\n";
			delete (iter->second);

			// be careful removing this element from the map.  we need to
			// update the iterator BEFORE erasing the element.
			temp = iter; iter++; _connections.erase(temp);
		}
		else
			++iter; // manually increment the iterator for the next loop
	}
    return Transport::Ok;
}


