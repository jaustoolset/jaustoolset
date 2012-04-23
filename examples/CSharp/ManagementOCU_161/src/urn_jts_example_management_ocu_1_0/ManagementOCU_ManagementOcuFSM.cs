

using JTS;
using System;
using System.Threading;

	
namespace urn_jts_example_management_ocu_1_0
{
	public class ManagementOCU_ManagementOcuFSM : StateMachine{

		public ManagementOCU_ManagementOcuFSMContext context;
        protected bool doOnce = false;

        public class Menu
        {
            InternalEventHandler ieHandler;

            public Menu(ref InternalEventHandler p)
            {
                ieHandler = p;
            }

            public void run()
            {
                displayMenuAndGrabInput();
                //join               
            }

            private void displayMenuAndGrabInput()
            {
                short selection = 255;
                bool run = true;
                string s = "";
                do
                {
					selection = 255;
                    Console.WriteLine("Menu");
                    Console.WriteLine("____________________________________");
                    Console.WriteLine(" 1) Send Resume");
                    Console.WriteLine(" 2) Send Standby");
                    Console.WriteLine(" 3) Send Set Emergency");
                    Console.WriteLine(" 4) Send Clear Emergency");
                    Console.WriteLine(" 5) Send Query Status");
                    Console.WriteLine(" 6) Send Shutdown");
					Console.WriteLine(" 7) Send Release Control");
                    Console.WriteLine(" 8) Quit and exit");
                    Console.WriteLine("____________________________________");
                    Console.WriteLine("[Input]: ");

                    try
                    {
                        s = Console.ReadLine();
                        selection = Convert.ToInt16(s);                        
                    }
                    catch (Exception e)
                    {
                        s = null;
                        selection = 255;
                    }

                    if (selection != 255)
                    {
                        MenuItemEntered item = new MenuItemEntered();
                        item.getMenuItemEnteredBody().getMenuItemEnteredRecord().setSelection((int)selection);

                        ieHandler.invoke(item);
                    }
                    if (selection == 8) run = false;
                }
                while (run);
            }
        }

		public ManagementOCU_ManagementOcuFSM()
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new ManagementOCU_ManagementOcuFSMContext(this);

	}

	
		/// Handle notifications on parent state changes
		public override void setupNotifications()
		{

		}
	
		public virtual void displayMenuAction()
{
    Menu m = new Menu(ref ieHandler);
    if (!doOnce)
    {
        ThreadStart menu = new ThreadStart(m.run);
        Thread t = new Thread(menu);
        t.Start();
    }
    doOnce = true;
}

public virtual void displayStatusAction(ReportStatus msg)
{
    String[] mgmt_states = { "Init", "Ready", "Standby", "Shutdown", "Failure", "Emergency" };
    Console.WriteLine("Management server status: " + mgmt_states[msg.getBody().getReportStatusRec().getStatus()]);
}

public virtual void printMessageAction(string arg0)
{
    Console.WriteLine(arg0);
}

public virtual void sendManagementMessageAction(MenuItemEntered msg)
{
    Resume resume_msg = new Resume();
    Standby standby_msg = new Standby();
    SetEmergency set_emergency_msg = new SetEmergency();
    ClearEmergency clear_emergency_msg = new ClearEmergency();
    QueryStatus query_status_msg = new QueryStatus();
    ReleaseControl release_control_msg = new ReleaseControl();
	Shutdown shutdown_msg = new Shutdown();

    JausAddress dest = new JausAddress(jausRouter.getJausAddress().getSubsystemID(),
                      jausRouter.getJausAddress().getNodeID(),
                      (byte)160);

    switch (msg.getMenuItemEnteredBody().getMenuItemEnteredRecord().getSelection())
    {
        case 1:
            Console.WriteLine("Sending Resume");
            sendJausMessage(resume_msg, dest);
            break;
        case 2:
            Console.WriteLine("Sending Standby");
            sendJausMessage(standby_msg, dest);
            break;
        case 3:
            Console.WriteLine("Sending SetEmergency");
            sendJausMessage(set_emergency_msg, dest);
            break;
        case 4:
            Console.WriteLine("Sending ClearEmergency");
            sendJausMessage(clear_emergency_msg, dest);
            break;
        case 5:
            Console.WriteLine("Sending QueryStatus");
            sendJausMessage(query_status_msg, dest);
            break;
        case 6:
            Console.WriteLine("Sending Shutdown");
            sendJausMessage(shutdown_msg, dest);
            break;
        case 7:
            Console.WriteLine("Sending ReleaseControl");
            sendJausMessage(release_control_msg, dest);
            break;
        case 8:
            Console.WriteLine("Sending Terminate");
            terminateServiceAction();
            break;
        default:
            Console.WriteLine("Unknown input: " + msg.getMenuItemEnteredBody().getMenuItemEnteredRecord().getSelection());
            break;
    }
}

public virtual void sendRequestControlAction()
{
    // what should really happen here is that an internal event is called, so that
    // the send happens inside of a sendAction(), rather than here. 

    // Now, we need to send a RequestComponentControl to the management service
    RequestControl request_control_msg = new RequestControl();
    request_control_msg.getBody().getRequestControlRec().setAuthorityCode(200);

    // send a message, to this subsystem, this node, component 11. 
    // Again, discovery should be used to find the JAUS ID of the service we want to 
    // address, rather than hardcoding the address
    JausAddress dest = new JausAddress(jausRouter.getJausAddress().getSubsystemID(),
                      jausRouter.getJausAddress().getNodeID(),
                      (byte)160);
    sendJausMessage(request_control_msg, dest);
}

public virtual void terminateServiceAction()
{
    Console.WriteLine("Terminating OCU...");
    Environment.Exit(0);
}



		public virtual bool isControlAccepted(ConfirmControl msg)
{
    return (msg.getBody().getConfirmControlRec().getResponseCode() == 0);
}

public virtual bool isSelectionToEnd(MenuItemEntered msg)
{
    return (msg.getMenuItemEnteredBody().getMenuItemEnteredRecord().getSelection() == '8');
}


	}
	
}

