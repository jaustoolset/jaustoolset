/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of 
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

#ifndef JAUSROUTER_H
#define JAUSROUTER_H

#include <set> 
#include <vector>
#include <queue>
#include <map>

#include "JausUtils.h"
#include "InternalEvents/Receive_1_0.h"
#include "InternalEvents/Send_1_0.h"
#include "InternalEvents/Receive_1_1.h"
#include "InternalEvents/Send_1_1.h"
#include "InternalEvents/InternalEventHandler.h"
#include "JausAddress.h"
#include "SimpleThread.h"
#include "OS.h"

namespace JTS
{

/**
 * 
 */

/**
 * This class is responsible for routing messages between services within a component.
 */
class DllExport JausRouter : public SimpleThread
{
public:
	JausRouter(JausAddress jausAddress, InternalEventHandler* ieHandler);
	~JausRouter();

	enum TransportType {Version_1_0, Version_1_1};
	
	void sendMessage(Send_1_0* msg);
	void sendMessage(Send_1_1* msg);
	void routeMessage(JausAddress sender, unsigned int bufsize, const unsigned char* buffer);
	void setTransportType( TransportType type);
	
	void stop();
	
	JausAddress* const getJausAddress();
	
protected:
	
	virtual void run();	
	DeVivo::Junior::JrMutex runLock;
	bool isRunning;
	
	long jrHandle;
	JausAddress jausAddress;
	InternalEventHandler* ieHandler;
	TransportType transportType;
};

};

#endif // JAUSROUTER_H

