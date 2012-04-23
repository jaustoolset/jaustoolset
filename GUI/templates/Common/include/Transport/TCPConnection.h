/*! 
 ***********************************************************************
 * @file      TCPConnection.h
 * @author    Dave Martin, DeVivo AST, Inc.  
 * @date      2008/08/05
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
#ifndef __JAUS_TCP_CONNECTION_H
#define __JAUS_TCP_CONNECTION_H

#include "Transport.h"
#include "JTCPArchive.h"
#include "ConnectionList.h"
#include <map>

namespace DeVivo {
namespace Junior {


//
// A TCPConnection object manages a single TCP connection.
// Communication is by-directional (sending and receiving).
// We inherit from, but extend, the Connection class.  The basic
// Connection only supports data types (id, address, version)
// while a TCPConnection provides additional functionality
// for sending/receiving on a dedicated socket.
//
class JTCPConnection : public Connection<IP_ADDRESS>
{
public:
    JTCPConnection(int socket);
   ~JTCPConnection();

    // Public interface functions
    Transport::TransportError sendMsg(Message& msg);
    Transport::TransportError recvMsg(MessageList& msglist);

    // Data accessors
    int getSocket(){return _socket;}

protected:
    int               _socket;
    JTCPArchive       _incoming_stream;
    bool              _isStreamActive;
};

//
// Helper class to manage a list of connections
//
class JTCPConnectionList
{
public:
    JTCPConnectionList():_connections(){};
    ~JTCPConnectionList(){};

    // List management functions
    JTCPConnection* addConnection(int socket);
    void closeConnection(int socket);
	void closeAllConnections();
    JTCPConnection* getConnection(JAUS_ID id, MsgVersion version);

    // Public interface functions
    Transport::TransportError sendMsgToAll(Message& msg);
    Transport::TransportError recvMsgs(MessageList& msglist);

protected:

    std::map<int, JTCPConnection*> _connections;
};


}} // namespace DeVivo::Junior
#endif


