%copyright%
package src.%service_namespace%;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
%statemachine_include_list%

public class %service_name% extends Service{

	%statemachine_variable_list%

	public %service_name%(JausRouter jausRouter %parent_service_list%)
	{

%statemachine_assignment_list%

	}
	
	public void run(){

		// Perform any entry actions specified by the start state.
%start_state_actions%
		

		// Kick off  receive loop...
		super.run();
	}

	//	This is the function that will process an event either generated
	//  by the service, sent to it by another service on the same component,
	//  or as a message sent by a different component. 
	public synchronized boolean processTransitions(InternalEvent ie)
	{
	    boolean done = false;

		// Invoke the FSM transition for this event.
%transition_calls%	

	    return done;
	}


	//	This is the function that will check for default transitions if
	//  no other transitions were satisfied. 
	//
	public synchronized boolean defaultTransitions(InternalEvent ie)
	{
	    boolean done = false;

		// Invoke the FSM transition for this event.
%default_transition_calls%	

	    return done;
			}

}



