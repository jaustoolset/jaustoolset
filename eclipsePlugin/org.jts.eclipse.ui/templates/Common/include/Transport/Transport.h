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

#ifndef  __TRANSPORT_H
#define __TRANSPORT_H

#include <string>
#include "JrMessage.h"
#include "ConfigData.h"

namespace DeVivo {
namespace Junior {

class Transport
{
public:
    Transport(){};
   ~Transport(){};

    //
    // Define the error codes
    //
    enum TransportError 
    {
        Ok, NoMessages, InvalidConfigFile, InitFailed, AddrUnknown, Failed, ConnectionClosed 
    };

    // All functions are abstract
    virtual TransportError sendMsg(Message& msg) = 0;
    virtual TransportError recvMsg(MessageList& msglist) = 0;
    virtual TransportError broadcastMsg(Message& msg) = 0;
    virtual TransportError initialize(ConfigData& config) = 0;

    // Debugging
    std::string enumToString( TransportError code );
    
    std::string getName(){ return my_name;}
    
protected:
    std::string     my_name;
    
};

inline std::string Transport::enumToString( TransportError code )
{
    switch (code)
    {
        case Transport::Ok:
            return std::string("Success");
        case Transport::NoMessages:
            return std::string("No messages in queue");
        case Transport::InvalidConfigFile:
            return std::string("Invalid config file");
        case Transport::InitFailed:
            return std::string("Initialization failed");
        case Transport::AddrUnknown:
            return std::string("Unknown destination address");
        case Transport::Failed:
            return std::string("Unknown failure");
        default:
            return std::string("Unkown error code");
    }
}
}} // namespace DeVivo::Junior    

#endif


