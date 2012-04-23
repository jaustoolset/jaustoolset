%copyright%

using JTS;
using System;
	

	
namespace %service_namespace%
{
	public class %statemachine_name% : StateMachine{
%parent_fsm_references%
		public %statemachine_name%Context context;
		
		%user_definitions%

		public %statemachine_name%(%parent_fsm_arguments%)
%sm_constuctor%
	
		/// Handle notifications on parent state changes
		public override void setupNotifications()
		{
%setup_notifications%
		}
	
		%action_method_definitions%

		%guard_method_definitions%
	}
	
}

