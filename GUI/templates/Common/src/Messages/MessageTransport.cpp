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

#include "Messages/MessageTransport.h"


//#define DEBUG


#ifdef DEBUG
#include <iostream>
#endif

namespace JTS
{

MessageHandler::MessageHandler(MessageRouter *r, DeVivo::Junior::JrSignal* s)
{	
	msgRouter = r;
	signal = s;
}

MessageHandler::~MessageHandler()
{
}


void MessageHandler::send(Message *msg)
{
#ifdef DEBUG
	std::cout << "[MessageHandler::send( 0x" << std::hex << msg->getID() << std::dec << " )]" << std::endl;
#endif

	msgRouter->routeMessage(msg);
}


int MessageHandler::receive(Message **msg)
{
	int size = 0;
	
	queueLock.lock();

	if (messageQueue.size() != 0)
	{
#ifdef DEBUG
	std::cout << "[MessageHandler::receive]" << std::endl;
#endif

		*msg = messageQueue.front();
		messageQueue.pop();
		size = 1;
	}
	
	queueLock.unlock();
	
	return size;
}
	

void MessageHandler::addToQueue(Message *msg)
{
#ifdef DEBUG
	std::cout << "[MessageHandler::addToQueue( 0x" << std::hex << msg->getID() << std::dec << " )]" << std::endl;
#endif
	
	queueLock.lock();
	messageQueue.push(msg);
	queueLock.unlock();
	
	signal->signal();
}



/**
 * MessageRouter Definitions
 */
MessageRouter::MessageRouter()
{
}


MessageRouter::~MessageRouter()
{
}



void MessageRouter::routeMessage(Message* msg)
{
	MessageHandler* msgHandler;
	
#ifdef DEBUG
	std::cout << "MessageRouter::routeMessage( 0x" << std::hex << msg->getID() << std::dec << " )]" << std::endl;
#endif

	/// Determine the Destination Address based on the MessageID
	msgHandler = msgIDToMsgHandlerTable[msg->getID()];
	msgHandler->addToQueue(msg);				
}


void MessageRouter::updateTable(MessageHandler* msgHandler, std::set<jUnsignedShortInteger> &inputMessageList)
{
	if (inputMessageList.size() > 0)
	{
		for (std::set<jUnsignedShortInteger>::iterator j = inputMessageList.begin(); j != inputMessageList.end(); j++)
		{
			msgIDToMsgHandlerTable[*j] = msgHandler;
		}
	}
}


};

