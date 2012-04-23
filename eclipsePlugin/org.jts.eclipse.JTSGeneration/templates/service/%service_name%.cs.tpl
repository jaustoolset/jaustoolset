%copyright%

using JTS;
using System;
using System.Collections.Generic;

namespace %service_namespace%{

public class  %service_name% : Service{
	%statemachine_variable_list%

	public %service_name%(JausRouter jausRouter %parent_service_list%)
    {

%statemachine_assignment_list%

	}
	

	public override void run()
    {
		// Perform any entry actions specified by the start state.
%start_state_actions%
		
		// Kick off  receive loop...
		base.run();
		}
		
	//	This is the function that will process an event either generated
	//  by the service, sent to it by another service on the same component,
	//  or as a message sent by a different component. 
	public override bool processTransitions(InternalEvent ie)
	{
	    bool done = false;

	   // Since this function can be called from multiple threads,
	   // we use a mutex to ensure only one state transition is
	   // active at a time.
	   mutex.WaitOne();

		// Invoke the FSM transition for this event.
%transition_calls%	

		mutex.ReleaseMutex();
	    return done;
	}


	//	This is the function that will check for default transitions if
	//  no other transitions were satisfied. 
	//
	public override bool defaultTransitions(InternalEvent ie)
	{
	    bool done = false;

	   // Since this function can be called from multiple threads,
	   // we use a mutex to ensure only one state transition is
	   // active at a time.
	   mutex.WaitOne();

		// Invoke the FSM transition for this event.
%default_transition_calls%	

		mutex.ReleaseMutex();
	    return done;
	}	

}

}

