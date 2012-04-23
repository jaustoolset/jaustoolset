/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010-2011, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of 
its contributors may be used to endorse or promote products derived from 
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/


package src.urn_jts_example_management_ocu_1_0;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jts_example_management_ocu_1_0.InternalEvents.*;
import src.urn_jts_example_management_ocu_1_0.Messages.*;

import java.io.InputStreamReader;
import java.io.BufferedReader;


public class ManagementOCU_ManagementOcuFSM extends StateMachine{
	protected boolean running;

    ManagementOCU_ManagementOcuFSMContext context;

    
    boolean doOnce = false;

	public static class Menu extends Thread
	{
		InternalEventHandler ieHandler;

		public Menu(InternalEventHandler p)
		{
			ieHandler = p;
		}
		public void run()
		{
			displayMenuAndGrabInput();
			try{
				this.join();
			}
			catch(Exception e)
			{
				System.out.println("Error joining Menu thread");
			}
		}

 	   void displayMenuAndGrabInput()
		{
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			int selection;
			boolean run = true;

			do {
				selection = 255;
				System.out.println("Menu" );
				System.out.println("____________________________________" );
				System.out.println(" 1) Send Resume" );
				System.out.println(" 2) Send Standby" );
				System.out.println(" 3) Send Set Emergency" );
				System.out.println(" 4) Send Clear Emergency" );
				System.out.println(" 5) Send Query Status" );
				System.out.println(" 6) Send Shutdown" );
				System.out.println(" 7) Send Release Control" );
				System.out.println(" 8) Quit and exit" );
				System.out.println("____________________________________" );
				System.out.println("[Input]: ");
				try
				{
					String s = read.readLine();
                	selection = Integer.parseInt(s);;
							
					MenuItemEntered item = new MenuItemEntered();
					item.getMenuItemEnteredBody().getMenuItemEnteredRecord().setSelection( selection );
					ieHandler.invoke(item);
	
				}
				catch(Exception e) 
				{
					// Silently discard.  Probably an invalid key entry...
				}

				if(selection == 8) run = false;

			} while (run);
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
	public void setupNotifications()
	{

	}

	/// Access for debug purposes
	public String getStateName()
	{
		return context.getState().getName();
	}

	public void displayMenuAction()
{
	// If we have not yet done it, spawn a thread to monitor the menu.
	if (!doOnce) (new Menu(ieHandler)).start();
	doOnce = true;
}

public void displayStatusAction(ReportStatus msg)
{
	String[] mgmt_states = {"Init", "Ready", "Standby", "Shutdown", "Failure", "Emergency"};
	System.out.println( "Management server status: " + mgmt_states[msg.getBody().getReportStatusRec().getStatus()]);
}

public void printMessageAction(String arg0)
{
	System.out.println(arg0);
}

public void sendManagementMessageAction(MenuItemEntered msg)
{
	Resume resume_msg = new Resume();
	Standby standby_msg = new Standby();
	SetEmergency set_emergency_msg = new SetEmergency();
	ClearEmergency clear_emergency_msg = new ClearEmergency();
	QueryStatus query_status_msg = new QueryStatus();
	ReleaseControl release_control_msg = new ReleaseControl();
	Shutdown shutdown_msg = new Shutdown();

	JausAddress dest = new JausAddress( jausRouter.getJausAddress().getSubsystemID(),
			          jausRouter.getJausAddress().getNodeID(),
			          (short)160);

	switch(msg.getMenuItemEnteredBody().getMenuItemEnteredRecord().getSelection()) {
		case 1: 
			System.out.println( "Sending Resume" );
            sendJausMessage( resume_msg, dest);
			break;
		case 2:
			System.out.println( "Sending Standby" );
            sendJausMessage( standby_msg, dest);
			break;
		case 3:
			System.out.println( "Sending SetEmergency" );
            sendJausMessage( set_emergency_msg, dest);
			break;
		case 4:
			System.out.println( "Sending ClearEmergency" );
            sendJausMessage( clear_emergency_msg, dest);
			break;
		case 5:
			System.out.println( "Sending QueryStatus" );
            sendJausMessage( query_status_msg, dest);
			break;
		case 6:
			System.out.println( "Sending Shutdown" );
            sendJausMessage( shutdown_msg, dest);
			break;
		case 7:
			System.out.println( "Sending ReleaseControl" );
            sendJausMessage( release_control_msg, dest);
			break;
		case 8:
			System.out.println("Sending Terminate");
			terminateServiceAction();
		default:
			System.out.println( "Unknown input: " + msg.getMenuItemEnteredBody().getMenuItemEnteredRecord().getSelection() );
			break;
	}
}

public void sendRequestControlAction()
{
	// what should really happen here is that an internal event is called, so that
	// the send happens inside of a sendAction(), rather than here. 

	// Now, we need to send a RequestComponentControl to the management service
	RequestControl request_control_msg = new RequestControl();
	request_control_msg.getBody().getRequestControlRec().setAuthorityCode((short)200);

	// send a message, to this subsystem, this node, component 11. 
	// Again, discovery should be used to find the JAUS ID of the service we want to 
	// address, rather than hardcoding the address
	JausAddress dest = new JausAddress( jausRouter.getJausAddress().getSubsystemID(), 
					  jausRouter.getJausAddress().getNodeID(),
					  (short)160);
    sendJausMessage( request_control_msg, dest);
}

public void terminateServiceAction()
{
	System.out.println("Terminating OCU...");
	System.exit(0);
}



	public boolean isControlAccepted(ConfirmControl msg)
{
	return (msg.getBody().getConfirmControlRec().getResponseCode() == 0);
}

public boolean isSelectionToEnd(MenuItemEntered msg)
{
	return (msg.getMenuItemEnteredBody().getMenuItemEnteredRecord().getSelection() == '8');
}


}

