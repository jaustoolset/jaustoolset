/***********           LICENSE HEADER   *******************************
JR Middleware
Copyright (c)  2008-2019, DeVivo AST, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the copyright holder nor the names of 
its contributors may be used to endorse or promote products derived from 
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/
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


