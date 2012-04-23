/** 
 *  This Promela file was auto-generated using JTS on Fri May 13 14:02:55 EDT 2011.
 *  ID=urn:jaus:jss:core:Management  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

/**
*	Implementing Internal Events
*		Events are implmented as messages.  In order to fire an event, send a 
*		message with the event name as the message channel followed by _pid.
*		The _pid is just the processID of the process that sends the message.
*		The processID may not be necessary, but is sent as a default parameter
*		because Promela requires a payload for messages.
*		Example: You want to fire a Timeout event.
*			active proctype fireEvents{
*				Timeout ! _pid;
*			} 
*		More complex processing is possible for advanced users of Promela.
*
*	NOTE: The following type definitions are for reference purposes only and are not
*	being used in the implementation of the model.
*/
/**
*  The Send event is used by a derived service to hand over the payload data that it needs to send to a specified
*  destination endpoint via the transport layer. Upon receipt, this service prepares the message
*  for delivery (marshals the message) as specified by the transport layer specification and attempts
*  to deliver the encapsulated payload data to the destination endpoint.
*/
typedef urn_jaus_jss_core_Transport_Send{
	// data added to avoid empty type definitions.
	bit reserved;
};
/**
*  The Broadcast Local event is the same as the Send event except that this service sends the payload
*  to all endpoints within the subsystem.
*/
typedef urn_jaus_jss_core_Transport_BroadcastLocal{
	// data added to avoid empty type definitions.
	bit reserved;
};
/**
*  The Broadcast Global event is the same as the Send event except that this service sends the payload
*  to all endpoints on all subsystems.
*/
typedef urn_jaus_jss_core_Transport_BroadcastGlobal{
	// data added to avoid empty type definitions.
	bit reserved;
};
/**
*  The Receive event is used by derived services to hand over the data that was sent by another service
*  to an endpoint established by this service.
*/
typedef urn_jaus_jss_core_Transport_Receive{
	// data added to avoid empty type definitions.
	bit reserved;
};



/**
*	Implementing Internal Events
*		Events are implmented as messages.  In order to fire an event, send a 
*		message with the event name as the message channel followed by _pid.
*		The _pid is just the processID of the process that sends the message.
*		The processID may not be necessary, but is sent as a default parameter
*		because Promela requires a payload for messages.
*		Example: You want to fire a Timeout event.
*			active proctype fireEvents{
*				Timeout ! _pid;
*			} 
*		More complex processing is possible for advanced users of Promela.
*
*	NOTE: The following type definitions are for reference purposes only and are not
*	being used in the implementation of the model.
*/
/**
*  Received when an event occurs.
*/
typedef urn_jaus_jss_core_Events_EventOccurred{
	// data added to avoid empty type definitions.
	bit reserved;
};
/**
*  Received when an event error occurs.
*/
typedef urn_jaus_jss_core_Events_EventError{
	// data added to avoid empty type definitions.
	bit reserved;
};
/**
*  Received when a timeout occurs.
*/
typedef urn_jaus_jss_core_Events_Timeout{
	// data added to avoid empty type definitions.
	bit reserved;
};
/**
*  Must occur when a command event is completed.
*/
typedef urn_jaus_jss_core_Events_CommandCompleted{
	// data added to avoid empty type definitions.
	bit reserved;
};
/**
*  Must occur when a command event does not complete within its specified period of time.
*/
typedef urn_jaus_jss_core_Events_CommandExpired{
	// data added to avoid empty type definitions.
	bit reserved;
};



/**
*	Implementing Internal Events
*		Events are implmented as messages.  In order to fire an event, send a 
*		message with the event name as the message channel followed by _pid.
*		The _pid is just the processID of the process that sends the message.
*		The processID may not be necessary, but is sent as a default parameter
*		because Promela requires a payload for messages.
*		Example: You want to fire a Timeout event.
*			active proctype fireEvents{
*				Timeout ! _pid;
*			} 
*		More complex processing is possible for advanced users of Promela.
*
*	NOTE: The following type definitions are for reference purposes only and are not
*	being used in the implementation of the model.
*/
/**
*  Occurs when access is not re-acquired periodically
*/
typedef urn_jaus_jss_core_AccessControl_Timedout{
	// data added to avoid empty type definitions.
	bit reserved;
};



/**
*	Implementing Internal Events
*		Events are implmented as messages.  In order to fire an event, send a 
*		message with the event name as the message channel followed by _pid.
*		The _pid is just the processID of the process that sends the message.
*		The processID may not be necessary, but is sent as a default parameter
*		because Promela requires a payload for messages.
*		Example: You want to fire a Timeout event.
*			active proctype fireEvents{
*				Timeout ! _pid;
*			} 
*		More complex processing is possible for advanced users of Promela.
*
*	NOTE: The following type definitions are for reference purposes only and are not
*	being used in the implementation of the model.
*/
/**
*  An internally generated event that marks the completion of the initialization cycle.
*/
typedef urn_jaus_jss_core_Management_Initialized{
	// data added to avoid empty type definitions.
	bit reserved;
};
/**
*  An internally generated event that marks forces the component to a failure state.
*/
typedef urn_jaus_jss_core_Management_Failure{
	// data added to avoid empty type definitions.
	bit reserved;
};



