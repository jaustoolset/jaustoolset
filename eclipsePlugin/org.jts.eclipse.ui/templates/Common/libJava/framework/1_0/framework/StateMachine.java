/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
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

package framework;

/*
* This is the parent class for all state machines. It stores data common to all state machines.
*
*/

import framework.messages.Message;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateChangeNotification;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class StateMachine{

    protected InternalEventHandler ieHandler;
    protected JausRouter jausRouter;
	protected ArrayList<StateChangeNotification> notifications = new ArrayList<StateChangeNotification>();

	// Load the library fro the Swigged wrappers
    static{
        System.loadLibrary("JausAddress");
    }

	/*
	* Default constructor
	*/
    public StateMachine(){}
		
	/*
	* @param ieHandler the Interal event Handler for the service
	* @param jausRouter the Jaus Router that belongs to the component.
	*
	* Stores the data from the ieHandler and the jausRouter.
	*/
    public void setHandler(InternalEventHandler ieHandler, JausRouter jausRouter){
        this.ieHandler = ieHandler;
        this.jausRouter = jausRouter;
    }

	public InternalEventHandler getHandler()
	{ 
		return ieHandler; 
	}

	/*
	* @param msg the Message object to be sent out.
	* @param dest the destination address for the message.
	*
	*  Encodes the message in a byte buffer to be sent out.
	*/
    public void sendJausMessage(Message msg, JausAddress dest){

        // Get the message size before we allocate the buffer
        long bufsize = msg.getSize();
        
        // Allocate & encode...
        ByteBuffer buffer = ByteBuffer.allocate((int) bufsize);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.clear();
		
		int pos = 0;
        msg.encode(buffer, pos);

        // And send...
        sendJausMessage(bufsize, buffer, dest);
    }

	/*
	* @param bufsize the number of bytes in the buffer.
	* @param buffer the byte buffer containing the bytes to be sent out.
	* @param dest the address the buffer is to be sent to.
	*
	* Wraps the buffer containing the message and sends it out through the JausRouter.
	*/
    public void sendJausMessage(long bufsize,
            ByteBuffer buffer,
            JausAddress dest){

        // Send the response. We enclose the response message
        // in a transport envelope that includes the
        // destination address.
        Send response = new Send();
        response.getBody().getSendRec().getMessagePayload().set(bufsize, buffer);
        response.getBody().getSendRec().setDestSubsystemID(dest.getSubsystemID());
        response.getBody().getSendRec().setDestNodeID(dest.getNodeID());
        response.getBody().getSendRec().setDestComponentID(dest.getComponentID());

        jausRouter.sendMessage(response);
    }

	// This function must be overridden by derived classes
	public void setupNotifications() {}

	/*
	* Registers a notification event handler with the FSM.
	*/
	public void registerNotification( String StateName, InternalEventHandler ieHandler, 
								String EventName, String SourceName )
	{
		StateChangeNotification n = new StateChangeNotification();
		n._state = StateName;
		n._handler = ieHandler;
		n._event = EventName;
		n._source = SourceName;

		notifications.add( n );
	}

	/*
	* Checks to see if an event triggers an notification
	*/
	public void processNotifications(String state, InternalEvent ie)
	{
		if (state.contains("."))
			state = state.substring(state.lastIndexOf(".")+1);
		for (int i=0; i < notifications.size(); i++)
		{
			if (notifications.get(i)._state.compareTo( state ) == 0)
			{
				// Note that we send a copy of the original internal event, 
				// since processing the event will delete it and we might
				// still need the original for future notifications...
				if (ie != null)
					notifications.get(i)._handler.invoke( new InternalEvent(notifications.get(i)._event, ie.getSource()) );
				else
					notifications.get(i)._handler.invoke( new InternalEvent(notifications.get(i)._event, notifications.get(i)._source) );
			}
		}
	}
}
