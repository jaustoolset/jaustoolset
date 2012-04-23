%copyright%
package src.%service_namespace%;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
%statemachine_include_list%
%parent_fsm_includes%

public class %statemachine_name% extends StateMachine{
	protected boolean running;
%parent_fsm_references%
    %statemachine_name%Context context;

    %user_definitions%
	
	public %statemachine_name%(%parent_fsm_arguments%)
%sm_constuctor%
	
	/// Handle notifications on parent state changes
	public void setupNotifications()
	{
%setup_notifications%
	}

	/// Access for debug purposes
	public String getStateName()
	{
		return context.getState().getName();
	}

	%action_method_definitions%

	%guard_method_definitions%
}

