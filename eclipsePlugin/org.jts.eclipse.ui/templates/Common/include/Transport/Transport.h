/*! 
 ***********************************************************************
 * @file      Transport.h
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


