

using JTS;
using System;
using System.Timers;
using System.Text;
	
namespace urn_jts_DiscoveryClient_1_0
{
	public class DiscoveryClient_ReceiveFSM : StateMachine{
	urn_jaus_jss_core_Transport_1_1.Transport_ReceiveFSM pTransport_ReceiveFSM;

		public DiscoveryClient_ReceiveFSMContext context;
        protected Timer register_timer = null;
        protected Timer node_timer = null;
        const int AllocatorRate = 2000; // in milliseconds
        const int RegistrationRate = 5000; // in milliseconds
        int dynamic_iop_tries_so_far = 0;
        const int MaxNumberOfDynamicTries = 60; // increased from 15
        const String mac_string = "de:ad:be:ef:ca:fe:03";

        public DiscoveryClient_ReceiveFSM(urn_jaus_jss_core_Transport_1_1.Transport_ReceiveFSM pTransport_ReceiveFSM)
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
	    public override void setupNotifications()
	    {
		    pTransport_ReceiveFSM.registerNotification("Receiving", ieHandler, "InternalStateChange_To_DiscoveryClient_ReceiveFSM_Receiving_Ready", "Transport_ReceiveFSM");
		    registerNotification("Receiving_Ready", pTransport_ReceiveFSM.getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "DiscoveryClient_ReceiveFSM");
		    registerNotification("Receiving", pTransport_ReceiveFSM.getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "DiscoveryClient_ReceiveFSM");

            register_timer = new Timer
            {
                Interval = RegistrationRate
            };
            register_timer.Enabled = true;
            register_timer.AutoReset = true;
            register_timer.Elapsed += QueryIDTask;

            node_timer = new Timer
            {
                Interval = AllocatorRate
            };
            node_timer.Enabled = true;
            node_timer.AutoReset = true;
            node_timer.Elapsed += AllocatorTask;
        }

        private void QueryIDTask(Object source, ElapsedEventArgs e)
        {
            // If we know our ID fully, start the dynamic service discovery process
            if (!jausRouter.getJausAddress().containsWildcards())
            {
                QueryIdentification query_msg = new QueryIdentification();
                query_msg.getBody().getQueryIdentificationRec().setQueryType(2);
                JausAddress local = new JausAddress(jausRouter.getJausAddress().getSubsystemID(), 0xFF, 0xFF);
                sendJausMessage(query_msg, local);
            }
        }

        private void AllocatorTask(Object source, ElapsedEventArgs e)
        {
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
                Console.WriteLine("Failed to obtain Node ID dynamically.  Using fallback to assign node ID to default offset...\n");
                JausAddress id = new JausAddress(jausRouter.getJausAddress().getSubsystemID(),
                                0x6F, // type + active offset
                                jausRouter.getJausAddress().getComponentID());
                jausRouter.updateJausID(id);
            }
            else
            {
                // Broadcast a RequestNodeID message. Note that an actual implementation would have to get or know the MAC
                Console.WriteLine("Sending RequestNodeID message since ID=" + jausRouter.getJausAddress().getNodeID());
                RequestNodeID request_id = new RequestNodeID();
                request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(6, 0x03);
                request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(0, 0xDE);
                request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(1, 0xAD);
                request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(2, 0xBE);
                request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(3, 0xEF);
                request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(4, 0xCA);
                request_id.getBody().getReviewNodeIDRec().getRequesterID().setRequesterIDArrayField(5, 0xFE);
                JausAddress localBroadcast = new JausAddress(
                    jausRouter.getJausAddress().getSubsystemID(), 0xFF, 0xFF);

                // Use the optional 'force' flag to make the transport layer send this message,
                // even if the local JAUS ID may not be fully known.
                sendJausMessage(request_id, localBroadcast, true);
            }
        }

        public virtual void handleMessageAction(ReportIdentification msg, Receive.Body.ReceiveRec transportData)
        {
            // Since we found a discovery service, register our local services
            registerServicesAction(transportData);
        }

        public virtual void handleTimeoutAction()
        {
	        /// Insert User Code HERE
        }

        public virtual void parseServiceListAction(ReportServices msg, Receive.Body.ReceiveRec transportData)
        {
	        /// Insert User Code HERE
        }

        public virtual void registerServicesAction(Receive.Body.ReceiveRec transportData)
        {
            // Extract the sender information
            JausAddress sender = new JausAddress((ushort)transportData.getSourceID().getSubsystemID(),
                                                    (byte)transportData.getSourceID().getNodeID(),
                                                    (byte)transportData.getSourceID().getComponentID());

            // Found a discovery service.  Register our stuffs with it.
            RegisterServices register_msg = new RegisterServices();
            RegisterServices.RegisterServicesBody.ServiceList.ServiceRec service =
                    new RegisterServices.RegisterServicesBody.ServiceList.ServiceRec();
            service.setMinorVersionNumber(1);
            service.setMajorVersionNumber(1);

            // Register local services
            service.setURI("urn:jaus:jss:core:Transport");
            register_msg.getRegisterServicesBody().getServiceList().addElement(service);

            // Send the actual message
            sendJausMessage(register_msg, sender);
        }

        public virtual void updateNodeIDAction(GrantNodeID msg, Receive.Body.ReceiveRec transportData)
        {
            // Convert incoming MAC to string
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 7; i++)
            {
                sb.Append(((byte)msg.getBody().getGrantNodeIDRec().getRequesterID().getRequesterIDArrayField(i)).ToString("x2"));
                if (i < 6) sb.Append(":");
            }
            Console.WriteLine("Got GrantNodeID message for " + sb);

            // Check the MAC address to see if this is for us.
            if (String.Compare(sb.ToString(), mac_string) == 0)
            {
                Console.WriteLine("Updating NodeID to " + msg.getBody().getGrantNodeIDRec().getNodeID() + " for MAC: " + sb);

                JausAddress id = new JausAddress((ushort)jausRouter.getJausAddress().getSubsystemID(),
                                                 (byte)msg.getBody().getGrantNodeIDRec().getNodeID(),
                                                 (byte)jausRouter.getJausAddress().getComponentID());
                jausRouter.updateJausID(id);
            }
            else Console.WriteLine("Ignoring grant message since " + sb + " != " + mac_string);
        }

        public virtual void updateSubsystemIDAction(Receive.Body.ReceiveRec transportData)
        {
            // Check to see if we already have a subsystem ID.  If so, nothing to do.
            if ((jausRouter.getJausAddress().getSubsystemID() != 0xFFFF) && (jausRouter.getJausAddress().getSubsystemID() != 0)) return;

            // We need to update the JAUS ID 
            Console.WriteLine("DiscoveryClient:: update subsystem ID to " + transportData.getSourceID().getSubsystemID());
            JausAddress id = new JausAddress((ushort)transportData.getSourceID().getSubsystemID(),
                                                (byte)jausRouter.getJausAddress().getNodeID(),
                                                (byte)jausRouter.getJausAddress().getComponentID());

            // We now know (and can set) the subsystem ID, but we might still not know the Node ID.
            // Force the lower layers to accept that the ID might still contain wildcards using the
            // optional boolean argument
            jausRouter.updateJausID(id, true);
        }

        public virtual bool fromMasterModule(Receive.Body.ReceiveRec transportData)
        {
            // Typically, AEODRS systems use node 100 for master (discovery) while IOP use 1
            bool fromMas = (transportData.getSourceID().getNodeID() == 1) || (transportData.getSourceID().getNodeID() == 100);
            return fromMas;
        }
	}
}

