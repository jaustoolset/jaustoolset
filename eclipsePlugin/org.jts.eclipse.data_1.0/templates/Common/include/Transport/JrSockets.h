/*! 
 ***********************************************************************
 * @file      JrSockets.h
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
#ifndef __JR_SOCKETS_H
#define __JR_SOCKETS_H

#include "Transport.h"
#include "ConnectionList.h"
#include "OS.h"

namespace DeVivo {
namespace Junior {

// Since Windows and UNIX use different values for socket
// identifiers (strings versus handles), create an environment
// specific typedef.
#ifdef WINDOWS
typedef HANDLE SocketId;
#else
typedef std::string SocketId;
#endif

class JrSocket : public Transport
{
public:
    JrSocket(std::string name);
   ~JrSocket();

    // All functions are abstract
    TransportError sendMsg(Message& msg);
    TransportError recvMsg(MessageList& msglist);
    TransportError broadcastMsg(Message& msg);
    TransportError initialize(ConfigData& source);

	// A socket can be eithering POLLing or PENDing
	enum SocketWaitType {POLL, PEND};
	void setWaitType( enum SocketWaitType type );

	// speciality functions not forced by the parent class
    TransportError setDestination(std::string destination);
    TransportError removeDestination(JAUS_ID id);
    unsigned char  messagesInQueue();
   
protected:

    // Helper function to get around duplicating code in
    // sendMsg and broadcastMst
    TransportError sendMsg(Message& msg, SocketId dest);

    // Helper function to open a return channel
    void openResponseChannel(Message* msg);

    // Internal variables
    bool                     _is_connected;
	SocketId                 _connected_dest;
    ConnectionList<SocketId> _map;
    std::string              _socket_name;
	enum SocketWaitType      _type;
    
    // Unfortunately, implementations are different between UNIX
    // and Windows, since Windows does not support named sockets.
    // Instead we use Mailslots, managed by handles.
#ifdef WINDOWS
    SocketId OpenMailslot(std::string name);
    HANDLE sock;
#else
    int sock;
#endif
};
}} // namespace DeVivo::Junior
#endif


