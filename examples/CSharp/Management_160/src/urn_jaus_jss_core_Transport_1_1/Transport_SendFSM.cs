

using JTS;
using System;
	

	
namespace urn_jaus_jss_core_Transport_1_1
{
	public class Transport_SendFSM : StateMachine{

		public Transport_SendFSMContext context;
		
		

		public Transport_SendFSM()
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new Transport_SendFSMContext(this);

	}

		
		/// Handle notifications on parent state changes
		public override void setupNotifications()
		{

		}
		
		public virtual void BroadcastGlobalEnqueueAction(BroadcastGlobal msg)
{
	sendJausMessage(
		(uint)msg.getBody().getBroadcastRec().getMessagePayload().getLength(),
		msg.getBody().getBroadcastRec().getMessagePayload().getData(),
		new JausAddress((ushort) 0xFFFF,(byte) 0xFF,(byte) 0xFF));
}

public virtual void BroadcastLocalEnqueueAction(BroadcastLocal msg)
{
	sendJausMessage(
		(uint)msg.getBody().getBroadcastRec().getMessagePayload().getLength(),
		msg.getBody().getBroadcastRec().getMessagePayload().getData(),
		new JausAddress(jausRouter.getJausAddress().getSubsystemID(), (byte) 0xFF, (byte) 0xFF));
}

public virtual void EnqueueAction(Send msg)
{
	sendJausMessage(
		(uint)msg.getBody().getSendRec().getMessagePayload().getLength(),
		msg.getBody().getSendRec().getMessagePayload().getData(),
		new JausAddress((ushort)msg.getBody().getSendRec().getDestinationID().getSubsystemID(),
			(byte)msg.getBody().getSendRec().getDestinationID().getNodeID(),
			(byte)msg.getBody().getSendRec().getDestinationID().getComponentID()));
}



		
	}
	
}

