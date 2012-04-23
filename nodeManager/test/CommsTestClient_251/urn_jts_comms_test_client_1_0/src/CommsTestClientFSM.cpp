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



#include "CommsTestClientFSM.h"
#include "OS.h"




namespace urn_jts_comms_test_client_1_0
{

static JausAddress server;
static jUnsignedInteger numErrors = 0;
static jUnsignedInteger numSuccesses = 0;
static jUnsignedInteger lastSeqNum = 0;
static jUnsignedInteger lastDataSize = 0;
static jUnsignedInteger maxMsgTime = 0;
static double aveMsgSize = 0;
static double averageSuccessTime = 0;
static DeVivo::Junior::JrTimer* timer = NULL;


static void handle_timer( void* arg )
{
    ((InternalEventHandler*) arg)->invoke(new Timeout());
}

static void updateDisplay()
{
	fprintf(stdout, "Ave time: %.3f  Successes: %d   Failures: %d  Ave Size: %.3f\r", averageSuccessTime, numSuccesses, numErrors, aveMsgSize);
	fflush(stdout);
}

static JausAddress* findService(ReportServices msg, JausAddress sender, std::string id)
{
	// Loop through all services, searching for the input string
	for (int i = 0; i < msg.getBody()->getNodeListResponse()->getNumberOfElements(); i++)
	{
		ReportServices::Body::NodeListResponse::NodeSeqResponse* node =
			msg.getBody()->getNodeListResponse()->getElement(i);

		for (int j = 0; j < node->getComponentListResponse()->getNumberOfElements(); j++)
		{
			ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse* comp =
				node->getComponentListResponse()->getElement(j);

			for (int k = 0; k < comp->getServiceList()->getNumberOfElements(); k++)
			{
				if (comp->getServiceList()->getElement(k)->getURI() == id)
				{
					JausAddress* retval = new JausAddress(
						sender.getSubsystemID(),
						node->getNodeRecResponse()->getNodeID(),
						comp->getComponentRecResponse()->getComponentID());
					printf("Found service: %s at %d:%d:%d\n", id.c_str(),
						retval->getSubsystemID(), retval->getNodeID(), retval->getComponentID());
					return retval;
				}
			}
		}
	}

	printf("Failed to find service: %s\n", id.c_str());
	return NULL;
}


CommsTestClientFSM::CommsTestClientFSM(InternalEventHandler* ieHandler, JausHandler* jausHandler)
{
	this->ieHandler = ieHandler;
	this->jausHandler = jausHandler;

	// Create the timer.  We pass it the ieHandler, so that the
	// handler can send us a Timeout internal event.  
	timer = new DeVivo::Junior::JrTimer(handle_timer, (void*) ieHandler, 3000);

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new CommsTestClientFSMContext(*this);
	// entry actions for start state of state machine
	// entry actions for start state of state machine

	broadcastQueryServicesAction();

	// Seed the random number generator for repeatable performance.
	srand(1000);


}


CommsTestClientFSM::~CommsTestClientFSM() 
{
	if (timer) delete timer;
	delete context;
}

void CommsTestClientFSM::HandleInternalEvent(InternalEvent* ie)
{
	if (ie->getName() == "Timeout")
		context->timeoutTransition();
}

void CommsTestClientFSM::HandleComponentMessage(Receive* msg)
{
	ReportServices report_msg;
	CommsTestMsg comms_msg;

	// Get the message id as the first 2 bytes of the payload
	unsigned short id = *((unsigned short*) msg->getBody()->getReceiveRec()->getMessagePayload()->getData());

	// Extract the sender information
	JausAddress sender(msg->getBody()->getReceiveRec()->getSrcSubsystemID(),
					   msg->getBody()->getReceiveRec()->getSrcNodeID(),
					   msg->getBody()->getReceiveRec()->getSrcComponentID());


	if (id == ReportServices::ID)
	{
		report_msg.decode(msg->getBody()->getReceiveRec()->getMessagePayload()->getData());
		context->reportServicesTransition(report_msg, sender);
	}
	else if (id == CommsTestMsg::ID)
	{
		comms_msg.decode(msg->getBody()->getReceiveRec()->getMessagePayload()->getData());
		context->CommsTestMsgTransition(comms_msg, sender);
	}
	else
		printf("Unknown message received: 0x%x\n", id);
}



void CommsTestClientFSM::broadcastQueryServicesAction()
{
        printf("Searching for comms test server...\n");

	// Send a request to the Discovery component to find the Simulation controller...
	QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec comp_rec;
	comp_rec.setComponentID(255);
	QueryServices::Body::NodeList::NodeSeq seq;
	seq.getNodeRec()->setNodeID(255);
	seq.getComponentList()->addElement(comp_rec);
	QueryServices query_msg;
	query_msg.getBody()->getNodeList()->addElement(seq);

	// ... and broadcast using the Node Manager
	sendJausMessage(query_msg, JausAddress(65535, 255, 255));

	// Start the timer....
	timer->start();

}

void CommsTestClientFSM::processFailureAction()
{
	numErrors++;
	updateDisplay();
}

void CommsTestClientFSM::processSuccessAction(CommsTestMsg msg)
{
	// Make sure it's the messsage we're expecting.
	if ((msg.getCommsTestBody()->getCommsTestRec()->getSequenceNumber() != lastSeqNum) ||
		(msg.getCommsTestBody()->getCommsTestRec()->getPayload()->getLength() != lastDataSize))
	{
		numErrors++;
		updateDisplay();
		return;
	}

	// Make sure it's got the proper payload
	for (int i=0; i<lastDataSize; i++)
	{
		if (msg.getCommsTestBody()->getCommsTestRec()->getPayload()->getData()[i] != (unsigned char) i)
		{
			numErrors++;
			updateDisplay();
			return;
		}
	}
	
	// Getting here implies success.  Update statistics.
	double totalTime = averageSuccessTime * numSuccesses;
	double totalBytes = aveMsgSize * numSuccesses;
	numSuccesses++;
	jUnsignedInteger elapsedTime = DeVivo::Junior::JrGetTimestamp() - 
		msg.getCommsTestBody()->getCommsTestRec()->getTimeStamp();
	if (elapsedTime > maxMsgTime) maxMsgTime = elapsedTime;
	totalTime += elapsedTime;
	totalBytes += lastDataSize;
	averageSuccessTime = totalTime / numSuccesses;
	aveMsgSize = totalBytes / numSuccesses;
	updateDisplay();
}

void CommsTestClientFSM::sendCommsTestMsgAction()
{
	// Formulate the message to send
	CommsTestMsg comms_msg;
	comms_msg.getCommsTestBody()->getCommsTestRec()->setTimeStamp(DeVivo::Junior::JrGetTimestamp());
	comms_msg.getCommsTestBody()->getCommsTestRec()->setSequenceNumber(++lastSeqNum);

	// Set the random sized payload
    lastDataSize = (jUnsignedInteger) (((double)rand()) / ((double) RAND_MAX) * 10000 + 1);
	unsigned char* data = new unsigned char[lastDataSize];
	for (int i=0; i<lastDataSize; i++) data[i] = i;
	comms_msg.getCommsTestBody()->getCommsTestRec()->getPayload()->set(lastDataSize, data);

	// ... and broadcast using the Node Manager
	sendJausMessage(comms_msg, server);

	// Free the allocated buffers
	delete data;

	// Start the timer....
	timer->start();
}

void CommsTestClientFSM::setServerAddressAction(JausAddress sender)
{
	server = sender;
}



bool CommsTestClientFSM::isCommsTestServer(ReportServices msg, JausAddress sender)
{
	if (findService(msg, sender, "urn:jts:CommsTestServer") != NULL)
		return true;
	return false;
}



};
