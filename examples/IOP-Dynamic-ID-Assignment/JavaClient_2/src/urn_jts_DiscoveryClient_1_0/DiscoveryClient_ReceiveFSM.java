
package src.urn_jts_DiscoveryClient_1_0;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jts_DiscoveryClient_1_0.InternalEvents.*;
import src.urn_jts_DiscoveryClient_1_0.Messages.*;
import java.util.Timer;
import java.util.TimerTask;

import src.urn_jaus_jss_core_Transport_1_1.Transport_ReceiveFSM;


public class DiscoveryClient_ReceiveFSM extends StateMachine{
	protected boolean running;
	protected Timer register_timer = null;
	protected Timer node_timer = null;
	Transport_ReceiveFSM pTransport_ReceiveFSM;

    DiscoveryClient_ReceiveFSMContext context;

	final int AllocatorRate = 2000; // in milliseconds
	final int RegistrationRate = 5000; // in milliseconds
	int dynamic_iop_tries_so_far = 0;
	final int MaxNumberOfDynamicTries = 60; // increased from 15
	final String mac_string = "de:ad:be:ef:ca:fe:02";

    
	
public DiscoveryClient_ReceiveFSM(Transport_ReceiveFSM pTransport_ReceiveFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
		context = new DiscoveryClient_ReceiveFSMContext(this);

	this.pTransport_ReceiveFSM = pTransport_ReceiveFSM;
}

	
/// Handle notifications on parent state changes
public void setupNotifications()
{
	pTransport_ReceiveFSM.registerNotification("Receiving", ieHandler, "InternalStateChange_To_DiscoveryClient_ReceiveFSM_Receiving_Ready", "Transport_ReceiveFSM");
	registerNotification("Receiving_Ready", pTransport_ReceiveFSM.getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "DiscoveryClient_ReceiveFSM");
	registerNotification("Receiving", pTransport_ReceiveFSM.getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "DiscoveryClient_ReceiveFSM");

	register_timer = new Timer();
	register_timer.schedule( new QueryIDTask(), 0, RegistrationRate );

	register_timer = new Timer();
	register_timer.schedule( new AllocatorTask(), 0, AllocatorRate );
}

class QueryIDTask extends TimerTask {
	@Override
	public void run() {
		// If we know our ID fully, start the dynamic service discovery process
		if (!jausRouter.getJausAddress().containsWildcards())
		{
			QueryIdentification query_msg = new QueryIdentification();
			query_msg.getBody().getQueryIdentificationRec().setQueryType((short)2);
			JausAddress local = new JausAddress(jausRouter.getJausAddress().getSubsystemID(), (short) 0xFF, (short) 0xFF);
			sendJausMessage( query_msg, local);
		}
	}
}

class AllocatorTask extends TimerTask {
	@Override
	public void run() {
		// We first have to know the subsystem ID before we can start asking for a node ID
		// If that's not set, return prematurely.
		if ((jausRouter.getJausAddress().getSubsystemID() == 0xFFFF) ||
			(jausRouter.getJausAddress().getSubsystemID() == 0))
		{
			return;
		}

		// Check to see if we have an ID.  If we do, there's nothing to do
		if (!jausRouter.getJausAddress().containsWildcards())
		{
			// Nothing to do here... we just fall through the rest of the function

		}
		else if (dynamic_iop_tries_so_far++ > MaxNumberOfDynamicTries)
		{
			// If we've exceeded the max number of tries, use the fallback
			System.out.println("Failed to obtain Node ID dynamically.  Using fallback to assign node ID to default offset...\n");
			JausAddress id = new JausAddress( jausRouter.getJausAddress().getSubsystemID(),
							(short) 0x6F, // type + active offset
							jausRouter.getJausAddress().getComponentID() );            
			jausRouter.updateJausID( id );
		}
		else
		{
			// Broadcast a RequestNodeID message. Note that an actual implementation would have to get or know the MAC
			System.out.println("Sending RequestNodeID message since ID=" + jausRouter.getJausAddress().getNodeID());
			RequestNodeID request_id = new RequestNodeID();
			request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(6, (short)0x02);
			request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(0, (short)0xDE);
			request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(1, (short)0xAD);
			request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(2, (short)0xBE);
			request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(3, (short)0xEF);
			request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(4, (short)0xCA);
			request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(5, (short)0xFE);
			JausAddress localBroadcast = new JausAddress( 
				jausRouter.getJausAddress().getSubsystemID(), (short) 0xFF, (short) 0xFF);

			// Use the optional 'force' flag to make the transport layer send this message,
			// even if the local JAUS ID may not be fully known.
			sendJausMessage( request_id, localBroadcast, true);
		}
	}
}


/// Access for debug purposes
public String getStateName()
{
	return context.getState().getName();
}

public void handleMessageAction(ReportIdentification msg, Receive.Body.ReceiveRec transportData)
{
    // Since we found a discovery service, register our local services
	registerServicesAction(transportData);
}

public void handleTimeoutAction()
{
	/// Insert User Code HERE
}

public void parseServiceListAction(ReportServices msg, Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public void registerServicesAction(Receive.Body.ReceiveRec transportData)
{
   // Extract the sender information
   JausAddress sender = new JausAddress((int) transportData.getSourceID().getSubsystemID(),
										(short) transportData.getSourceID().getNodeID(),
										(short) transportData.getSourceID().getComponentID());

   // Found a discovery service.  Register our stuffs with it.
   RegisterServices register_msg = new RegisterServices();
   RegisterServices.RegisterServicesBody.ServiceList.ServiceRec service =
   		new RegisterServices.RegisterServicesBody.ServiceList.ServiceRec();
   service.setMinorVersionNumber((short)1);
   service.setMajorVersionNumber((short)1);

   // Register local services
	service.setURI("urn:jaus:jss:core:Transport");
	register_msg.getRegisterServicesBody().getServiceList().addElement(service);

   // Send the actual message
   sendJausMessage(register_msg, sender);   
}

public void updateNodeIDAction(GrantNodeID msg, Receive.Body.ReceiveRec transportData)
{
	// Convert incoming MAC to string
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<7; i++) 
    {
        sb.append(String.format("%02x", msg.getBody().getGrantNodeIDRec().getRequesterID().getRequesterIDArrayField(i)));
        if (i<6) sb.append(":");
    }
    System.out.println("Got GrantNodeID message for " + sb);

    // Check the MAC address to see if this is for us.
    if (sb.toString().equals(mac_string))
    {
        System.out.println("Updating NodeID to " + msg.getBody().getGrantNodeIDRec().getNodeID() + " for MAC: " + sb);

        JausAddress id = new JausAddress((int)  jausRouter.getJausAddress().getSubsystemID(),
                        				 (short)msg.getBody().getGrantNodeIDRec().getNodeID(),
                        				 (short)jausRouter.getJausAddress().getComponentID() );
        jausRouter.updateJausID( id );
    } else System.out.println("Ignoring grant message since " + sb + " != " + mac_string);
}

public void updateSubsystemIDAction(Receive.Body.ReceiveRec transportData)
{
	// Check to see if we already have a subsystem ID.  If so, nothing to do.
   if ((jausRouter.getJausAddress().getSubsystemID() != 0xFFFF) && (jausRouter.getJausAddress().getSubsystemID() != 0)) return;

   // We need to update the JAUS ID 
   System.out.println("DiscoveryClient:: update subsystem ID to " + transportData.getSourceID().getSubsystemID());
   JausAddress id = new JausAddress( (int)  transportData.getSourceID().getSubsystemID(),
									 (short)jausRouter.getJausAddress().getNodeID(),
									 (short)jausRouter.getJausAddress().getComponentID() );

   // We now know (and can set) the subsystem ID, but we might still not know the Node ID.
   // Force the lower layers to accept that the ID might still contain wildcards using the
   // optional boolean argument
   jausRouter.updateJausID( id, true );
}



public boolean fromMasterModule(Receive.Body.ReceiveRec transportData)
{
    // Typically, AEODRS systems use node 100 for master (discovery) while IOP use 1
    boolean fromMas = (transportData.getSourceID().getNodeID()==1) || (transportData.getSourceID().getNodeID()==100);
   return fromMas;
}


}

