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



#include "CommsTestClientService.h"

namespace urn_jts_comms_test_client_1_0
{
	
CommsTestClientService::CommsTestClientService(JausRouter* jausRouter) : Service()
{
	running = false;
	
	ieHandler = new InternalEventHandler(&recvSignal);
	jausHandler = new JausHandler(jausRouter, &recvSignal);
	
	/// Input Messages
	m_InputMessageList.insert(CommsTestMsg::ID);
	m_InputMessageList.insert(ReportServices::ID);

	/// Output Messages
	m_OutputMessageList.insert(CommsTestMsg::ID);
	m_OutputMessageList.insert(QueryServices::ID);



	jausRouter->updateTable(jausHandler, m_InputMessageList);

	fsm0 = new CommsTestClientFSM(ieHandler, jausHandler);

}


CommsTestClientService::~CommsTestClientService()
{
	delete fsm0;

	if (ieHandler) delete ieHandler;
	if (jausHandler) delete jausHandler;
}


void CommsTestClientService::stop()
{
	runLock.lock();
	running = false;
	runLock.unlock();
	
	recvSignal.signal();
}


/**
 *	This is the function that will actually be run by the thread. 
 */
void CommsTestClientService::run()
{
	InternalEvent *ie;
	Receive *cmptMsg;

	
	runLock.lock();
	running = true;
	runLock.unlock();	

	
	/// Run Helper Threads

	
	
	while(running)
	{
		if (ieHandler->invoked(&ie))
		{
			/*
			 * Pass the received message to each statemachine within this service.
			 */
			fsm0->HandleInternalEvent(ie);

			delete ie;
		}
		else if (jausHandler->receive(&cmptMsg))
		{
			/*
			 * Pass the received component message to each statemachine within this service.
			 */
			fsm0->HandleComponentMessage(cmptMsg);

			delete cmptMsg;
		}
		/// Wait for message to be received
		else
		{
			recvSignal.wait();
		}
	}
}

};
