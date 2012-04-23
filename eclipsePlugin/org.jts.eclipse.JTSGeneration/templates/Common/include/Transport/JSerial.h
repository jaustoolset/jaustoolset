/*! 
 ***********************************************************************
 * @file      JSerial.h
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
#ifndef __JAUS_SERIAL_TRANSPORT_H
#define __JAUS_SERIAL_TRANSPORT_H

#include "Transport.h"
#include "JSerialArchive.h"
#include "ConnectionList.h"
#include "ConfigData.h"

namespace DeVivo {
namespace Junior {

#ifndef WINDOWS
typedef int HANDLE;
#endif

class JSerial : public Transport
{
public:
    JSerial();
   ~JSerial();

    // All functions are abstract
    TransportError sendMsg(Message& msg);
    TransportError broadcastMsg(Message& msg);
    TransportError recvMsg(MessageList& msglist);
    TransportError initialize(ConfigData& config, int index);

	// We need to define the initialize() function as
	// required by the parent class.  However, this function
	// simply calls the class-specific form.
    TransportError initialize(ConfigData& config)
	{ 
		return initialize(config,0);
	};

protected:
    HANDLE                  hComm;
    JSerialArchive          unusedBytes;
    ConnectionList<HANDLE>  _map;
    bool                    previousByteWasDLE;

    // protected functions
    TransportError sendMsg(Message& msg, HANDLE handle);
    TransportError extractMsgsFromPacket(MessageList& msglist);
    TransportError configureLink(ConfigData& config, int index);
};
}} // namespace DeVivo::Junior
#endif


