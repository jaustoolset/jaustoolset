

using JTS;
using System;
	

	
namespace urn_jaus_jss_core_Transport_1_1
{
	public class Transport_ReceiveFSM : StateMachine{

		public Transport_ReceiveFSMContext context;
		
		

		public Transport_ReceiveFSM()
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new Transport_ReceiveFSMContext(this);

	}

		
		/// Handle notifications on parent state changes
		public override void setupNotifications()
		{

		}
		
		

		
	}
	
}

