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


