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



#include "CommsTestServerFSM.h"




namespace urn_jts_comms_test_server_1_0
{



CommsTestServerFSM::CommsTestServerFSM(InternalEventHandler* ieHandler, JausHandler* jausHandler)
{
	this->ieHandler = ieHandler;
	this->jausHandler = jausHandler;

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new CommsTestServerFSMContext(*this);
	// entry actions for start state of state machine
	// entry actions for start state of state machine
}


CommsTestServerFSM::~CommsTestServerFSM() 
{
	delete context;
}

void CommsTestServerFSM::HandleInternalEvent(InternalEvent* ie)
{
	/// Insert User Code HERE
}

void CommsTestServerFSM::HandleComponentMessage(Receive* msg)
{
	CommsTestMsg comms_msg;
	QueryServices query_msg;

	// Get the message id as the first 2 bytes of the payload
	unsigned short id = *((unsigned short*) msg->getBody()->getReceiveRec()->getMessagePayload()->getData());

	// Extract the sender information
	JausAddress sender(msg->getBody()->getReceiveRec()->getSrcSubsystemID(),
					   msg->getBody()->getReceiveRec()->getSrcNodeID(),
					   msg->getBody()->getReceiveRec()->getSrcComponentID());
	
	// switch based off the command code
	if (id == QueryServices::ID)
	{
		query_msg.decode(msg->getBody()->getReceiveRec()->getMessagePayload()->getData());
		context->QueryServicesTransition(query_msg, sender);
	}
	else if (id == CommsTestMsg::ID)
	{
		comms_msg.decode(msg->getBody()->getReceiveRec()->getMessagePayload()->getData());
	    context->CommsTestMsgTransition(comms_msg, sender);
	}
	else
		printf("Unknowm message received.  ID=0x%x\n", id);
}



void CommsTestServerFSM::sendCommsTestMsgAction(CommsTestMsg msg, JausAddress sender)
{
	// Send the comms test message back to the sender
	sendJausMessage(msg, sender);
}

void CommsTestServerFSM::sendReportServicesAction(QueryServices msg, JausAddress sender)
{
	// We only have one service to report
	ReportServices report_msg;
	ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec rec;
	rec.setMinorVersionNumber(0);
	rec.setMajorVersionNumber(1);
	rec.setURI("urn:jts:CommsTestServer");
	ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse comp;
	comp.getComponentRecResponse()->setComponentID(jausHandler->getJausAddress()->getComponentID());
	comp.getServiceList()->addElement(rec);
	ReportServices::Body::NodeListResponse::NodeSeqResponse node;
	node.getNodeRecResponse()->setNodeID(jausHandler->getJausAddress()->getNodeID());
	node.getComponentListResponse()->addElement(comp);
	report_msg.getBody()->getNodeListResponse()->addElement(node);

	// Create and send as a component message back to the original requestor
	sendJausMessage(report_msg, sender);
}





};
