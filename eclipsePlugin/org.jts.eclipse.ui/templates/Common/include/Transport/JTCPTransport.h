/*! 
 ***********************************************************************
 * @file      JTCPTransport.h
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
#ifndef __JAUS_TCP_TRANSPORT_H
#define __JAUS_TCP_TRANSPORT_H

#include "Transport.h"
#include "ConnectionList.h"
#include "TCPConnection.h"
#include "Types.h"

namespace DeVivo {
namespace Junior {


class JTCPTransport : public Transport
{
public:
    JTCPTransport();
   ~JTCPTransport();

    // All functions are abstract
    TransportError sendMsg(Message& msg);
    TransportError broadcastMsg(Message& msg);
    TransportError recvMsg(MessageList& msglist);
    TransportError initialize(ConfigData& config);

    // These functions are specific to TCP implementation
    TransportError acceptConnections();

protected:

    IpAddressBook       _address_map;      // address book
    JTCPConnectionList  _connectionsList;  // active connections
    int                 _listen_socket;
    bool                _exit_flag;
};
}} // namespace DeVivo::Junior
#endif


