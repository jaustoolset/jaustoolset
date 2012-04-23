/** 
 *  This Promela file was auto-generated using JTS on Thu Apr 14 11:49:18 EDT 2011.
 *  ID=urn.jts.DefaultTransTest  vsersion=1.1
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



