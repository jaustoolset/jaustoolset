/*! 
 ***********************************************************************
 * @file      JUDPTransportLB.cpp
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
#include "Transport/JUDPTransportLB.h"
#include "Transport/JUDPArchive.h"
#include "Transport/ConfigData.h"
#include "Transport/OS.h"
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


JUDPTransportLB::JUDPTransportLB():
    _map(),
    _socket(0),
    _multicastAddr(),
    _interfaces(),
    _use_opc(0),
    _loopbackAddr()
{
    my_name = "JUDP-LB";
}

JUDPTransportLB::~JUDPTransportLB()
{
    if (_socket > 0) closesocket(_socket);
}

Transport::TransportError JUDPTransportLB::initialize( ConfigData& config )
{
    // Read the configuration file, and set-up defaults for anything
    // that isn't specified.
    std::string loopback_addr = "127.0.0.1";
    config.getValue(loopback_addr, "UDP_Address", "UDP_Loopback_Configuration" );
    unsigned short port = 55555;
    config.getValue(port, "UDP_Port", "UDP_Loopback_Configuration" );
    unsigned int multicast_TTL = 1;
    config.getValue(multicast_TTL, "MulticastTTL", "UDP_Loopback_Configuration");
    std::string multicast_addr = "239.255.0.100";
    config.getValue(multicast_addr, "MulticastAddr", "UDP_Loopback_Configuration");
    int buffer_size = 10000;
    config.getValue(buffer_size, "MaxBufferSize", "UDP_Loopback_Configuration");
    config.getValue(_use_opc, "UseOPC2.75_Header", "UDP_Loopback_Configuration");

    // Set-up the multicast address based on config data
    _multicastAddr.port = htons(port);
    _multicastAddr.addr = inet_addr(multicast_addr.c_str());
    
    // Set-up the loopback address based on config data
    _loopbackAddr.port = htons(port);
    _loopbackAddr.addr = inet_addr(loopback_addr.c_str());

#ifdef WINDOWS
    // Must initialize the windows socket library before using
    WSADATA temp;
    WSAStartup(0x22, &temp);
#endif

    // Create the socket
    _socket = socket(AF_INET, SOCK_DGRAM, 0);
    if (_socket < 0)
    {
        JrError << "Unable to create socket for UDP Loopback communication.  Error: " 
            << getSocketError << std::endl;
        return InitFailed;
    }

    // Bind the socket to the public JAUS port
    struct sockaddr_in sockAddr;
    sockAddr.sin_family = AF_INET;
    sockAddr.sin_addr.s_addr = htonl(INADDR_ANY);
    sockAddr.sin_port = htons(port);
    if (bind(_socket,(struct sockaddr*)&sockAddr,sizeof(sockAddr))<0)
    {
        JrError << "Unable to bind to UDP Loopback port " << port << std::endl;
        closesocket(_socket);
        _socket = 0;
        return InitFailed;
    }

    // Increase the size of the send/receive buffers
    int length = sizeof(buffer_size);
    setsockopt(_socket, SOL_SOCKET, SO_RCVBUF, (char*)&buffer_size, length);
    setsockopt(_socket, SOL_SOCKET, SO_SNDBUF, (char*)&buffer_size, length);

    // 
    // Set-up for multicast:
    //  1) No loopback
    //  2) TTL value set by configuration file
    /// 3) Send out our socket.
    //  4) Join the multicast group set by configuration file
    //
    char loop = 0; struct ip_mreq mreq;
    setsockopt(_socket, IPPROTO_IP, IP_MULTICAST_LOOP, &loop, sizeof(loop));
    setsockopt(_socket, IPPROTO_IP, IP_MULTICAST_TTL, (const char*) &multicast_TTL, sizeof(multicast_TTL));
    setsockopt(_socket, IPPROTO_IP, IP_MULTICAST_IF, (const char*) &sockAddr, sizeof(sockAddr));
    mreq.imr_multiaddr.s_addr = _multicastAddr.addr; 

    // Using INADDR_ANY causes us to join the multicast group, but only
    // on the default NIC.  When multiple NICs are present, we need to join
    // each manually.  Loop through all available addresses...
    // Get a list of IP addresses associated with this host.
    _interfaces = JrGetIPAddresses();
    std::list<unsigned int>::iterator addy;
    for (addy = _interfaces.begin(); addy != _interfaces.end(); ++addy)
    {
        mreq.imr_interface.s_addr = *addy;
        if (setsockopt (_socket, IPPROTO_IP, IP_ADD_MEMBERSHIP, 
            (const char*) &mreq, sizeof(mreq)) != 0)
            JrError << "Error joining multicast group : " << getSocketError << std::endl;

        JrInfo << "Found network interface: " << 
            inet_ntoa(*(in_addr*) &mreq.imr_interface.s_addr) << std::endl;
    }

    // UDP sockets support run-time discovery.  It's also possible, however,
    // to initialize the map statically through a config file.
    _map.Load(config);

    return Ok;
}

Transport::TransportError JUDPTransportLB::sendMsg(Message& msg)
{
    // Assume the worst...
    Transport::TransportError result = AddrUnknown;

    //
    // Creating a byte stream (payload) for the message
    //
    JUDPArchive archive;

    MsgVersion version = AS5669A;
    version = (_use_opc ? OPC : AS5669A);
    // pack for the selected version
    archive.pack(msg, version);

    // Create the destination address structure
    struct sockaddr_in dest;
    dest.sin_family = AF_INET;
    dest.sin_addr.s_addr = _loopbackAddr.addr;
    dest.sin_port = _loopbackAddr.port;
    
    // Lastly, send the message.  
    int val = sendto(_socket, archive.getArchive(), archive.getArchiveLength(),
               0, (struct sockaddr*) &dest, sizeof(dest));
    if (val < 0) 
    {
        JrError << "Unable to send UDP Loopback packet.  Error: " << getSocketError << std::endl;
        result = Failed;
    }
    else 
    {
        JrDebug << "Sent " << archive.getArchiveLength() << " bytes on UDP Loopback port\n";
        result = Ok;
    }
    
    return result;
}

Transport::TransportError JUDPTransportLB::recvMsg(MessageList& msglist)
{
    char buffer[5000];
    Transport::TransportError ret = NoMessages;
 
    // Check the recv port in a loop, exiting only when we have
    // no messages in the buffer (or we received 10 packets).
    for (int i=0; i<10; i++)
    {
        // See if we have anything waiting before we call recvfrom
        struct timeval timeout;
        timeout.tv_sec=0; timeout.tv_usec=0;
        fd_set set; FD_ZERO(&set); FD_SET(_socket, &set);
        if (select(_socket+1, &set, NULL, NULL, &timeout) == 0) break;

        // Check the socket for a message
        struct sockaddr_in source;
        int source_length = sizeof(source);
        int result = recvfrom(_socket, buffer, 5000, 0,
                              (struct sockaddr*) &source, 
                              (socklen_t*) &source_length);
        if (result <= 0) break;
        JrDebug << "Read " << result << " bytes on UDP Loopback port\n";
        ret = Ok;
    }

    // If we didn't find any message, return NoMessage.  Otherwise, Ok.
    return ret;
}

Transport::TransportError JUDPTransportLB::broadcastMsg(Message& msg)
{
    TransportError ret = Ok;

    //
    // Now pack the message for network transport.  If the message contains
    // a zero command code, use the AS5669A header.  Otherwise, use
    // the AS 5669 header UNLESS the UseOPC2.75_Header option is selected.
    //
    JUDPArchive archive;
    MsgVersion version = msg.getMessageCode() == 0 ? AS5669A : AS5669;
    if ((version == AS5669) && (_use_opc)) version = OPC;
    archive.pack( msg, version );

    // Create the destination address structure
    struct sockaddr_in dest;
    dest.sin_family = AF_INET;
    dest.sin_addr.s_addr = _multicastAddr.addr;
    dest.sin_port = _multicastAddr.port;

    // Send message on all available interfaces
    std::list<unsigned int>::iterator iter;
    // Just get the first interface
    iter = _interfaces.begin();
    
    // if it is not the end, setup and send
    struct in_addr sockAddr;
    sockAddr.s_addr = *iter;
    setsockopt (_socket, IPPROTO_IP, IP_MULTICAST_IF, 
        (const char*) &sockAddr, sizeof(sockAddr));

    // Lastly, send the message.
    if (sendto(_socket, archive.getArchive(), archive.getArchiveLength(),
               0, (struct sockaddr*) &dest, sizeof(dest)) < 0)
    {
        JrError << "Failed to broadcast UDP message on interface " <<
            inet_ntoa( *(struct in_addr*) &sockAddr.s_addr ) <<
            ".  Error: " << getSocketError << std::endl;
        ret = Failed;
    }
    else
    {
        JrDebug << "Broadcasted " << archive.getArchiveLength() <<
            " bytes on interface " << inet_ntoa( *(struct in_addr*) &sockAddr.s_addr ) 
            << std::endl;
    }

    return ret;
}
