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

#ifndef JTS_STATEMACHINE_H
#define JTS_STATEMACHINE_H

#include "InternalEvents/InternalEventHandler.h"
#include "Messages/Message.h"
#include "Transport/JausTransport.h"

namespace JTS
{

typedef enum { OnEnter, OnExit } StateChangeType;

class StateChangeNotificationEvent
{
public:
	StateChangeNotificationEvent(){};
	~StateChangeNotificationEvent(){};

	std::string _state;
	InternalEventHandler* _handler;
	std::string _event;
	std::string _source;
};

class StateMachine
{
public:
	StateMachine(){};
	virtual ~StateMachine(){};

	// All state machines have an internal event queue and an external (jaus) queue.
	void setHandlers(InternalEventHandler *ieHandler, JausRouter* jausRouter);
	InternalEventHandler* getHandler(){ return ieHandler; };

	// Registration mechanism allows for internal event when this FSM enters or exits states
	void registerNotification( std::string StateName,
		                       InternalEventHandler *ieHandler, std::string EventName,
							   std::string SourceName );
	virtual void setupNotifications() {};
	void processNotifications(std::string state, InternalEvent* ie = NULL);

	// Send a message via the JAUS (external) queue
	void sendJausMessage(JTS::Message& msg, JausAddress dest);
	void sendJausMessage(const jUnsignedInteger length,
						 const unsigned char* buffer, 
						 JausAddress dest);
		
protected:

	InternalEventHandler* ieHandler;
	JausRouter* jausRouter;
	std::vector< StateChangeNotificationEvent* > notifications;
	std::string fsmname;
};

inline void StateMachine::setHandlers(InternalEventHandler *ieHandler,
									  JausRouter* jausRouter)
{
	this->ieHandler = ieHandler;
	this->jausRouter = jausRouter;
}

inline void StateMachine::sendJausMessage(JTS::Message& msg, 
										  JausAddress dest)
{
	// Encode the message
	unsigned int bufsize = msg.getSize();
	unsigned char* buffer = new unsigned char[bufsize];
	msg.encode(buffer);

	// and send...
	sendJausMessage(bufsize, buffer, dest);
	
	// Free the buffer we used to encode the message
	delete[] buffer;
}

inline void StateMachine::sendJausMessage(const jUnsignedInteger bufsize,
										  const unsigned char* buffer, 
										  JausAddress dest)
{
	// Send the response.  We enclose
	// the response message in a transport envelope that includes
	// the destination address.
	Send_1_0 response;
	response.getBody()->getSendRec()->getMessagePayload()->set(bufsize, buffer);
	response.getBody()->getSendRec()->setDestSubsystemID(dest.getSubsystemID());
	response.getBody()->getSendRec()->setDestNodeID(dest.getNodeID());
	response.getBody()->getSendRec()->setDestComponentID(dest.getComponentID());
	jausRouter->sendMessage( &response );
}

inline void StateMachine::registerNotification( std::string StateName,
								  InternalEventHandler *ieHandler, 
								  std::string EventName, std::string SourceName)
{
	StateChangeNotificationEvent* n = new StateChangeNotificationEvent();
	n->_state = StateName;
	n->_handler = ieHandler;
	n->_event = EventName;
	n->_source = SourceName;

	notifications.push_back( n );
}

inline void StateMachine::processNotifications(std::string state, InternalEvent* ie)
{
	if (state.find("::") != std::string::npos)
		state = state.substr(state.find_last_of("::")+1);
	for (int i=0; i < notifications.size(); i++)
	{
		if (notifications[i]->_state == state)
		{
			// Send an notification to anyone who signed up for this state change, unless...
			if (ie && (notifications[i]->_event.find( ie->getSource() ) != std::string::npos))
			{
				// ... this FSM was actually the CAUSE of the state change in the first place.
				// In other words, detect the loop and don't send notification back to the
				// original source of the transition.  This stops a parent from notifying 
				// a child, who in turn notifies the parent, who in turn notifies the child ad nauseum.
			}
			else
			{
				// Ok... state change not caused by the target FSM, so send a notification to it.
				notifications[i]->_handler->invoke( new InternalEvent(notifications[i]->_event, notifications[i]->_source) );
			}
		}
	}
}


}

#endif // JTS_STATEMACHINE_H
