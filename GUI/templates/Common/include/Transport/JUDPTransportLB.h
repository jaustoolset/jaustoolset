/*! 
 ***********************************************************************
 * @file      JUDPTransportLB.h
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
#ifndef __JAUS_UDP_TRANSPORTLB_H
#define __JAUS_UDP_TRANSPORTLB_H

#include "Transport.h"
#include "ConnectionList.h"
#include <sstream>
#include <map>

namespace DeVivo {
namespace Junior {


class JUDPTransportLB : public Transport
{
public:
    JUDPTransportLB();
   ~JUDPTransportLB();

    // All functions are abstract
    TransportError sendMsg(Message& msg);
    TransportError broadcastMsg(Message& msg);
    TransportError recvMsg(MessageList& msglist);
    TransportError initialize(ConfigData& config);

protected:

    IpAddressBook            _map;
    int                      _socket;
    IP_ADDRESS               _multicastAddr;
    std::list<unsigned int>  _interfaces;
	int                      _use_opc;
    IP_ADDRESS               _loopbackAddr;

};
}} // namespace DeVivo::Junior
#endif


