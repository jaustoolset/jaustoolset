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
package framework.transport;

/* 
* This class connects with the C++ framework to send and receive messages
* 	throught the nodeManager.
*
* @version				04/01/2011
* @author				Gina Nearing
*
*/

import framework.internalEvents.InternalEventHandler;
import framework.internalEvents.Send;
import framework.internalEvents.Receive;
import framework.transport.OS;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class JausRouter extends SimpleThread{

	// Load the libraries containing the wrappers required by the Swigged interfaces.
    static{
        System.loadLibrary("JausAddress");
        System.loadLibrary("JuniorAPI");
    }

    protected boolean isRunning;
    protected long jrHandle;
    protected JausAddress jausAddress;
	protected InternalEventHandler ieHandler;

	private static final int THREAD_SLEEP = 100;

	protected static Logger logger = Logger.getLogger("framework.logger");

	/*
	* Constructor
	*
	* Initialized variables and connects to the node manager.
	* 
	*/
    public JausRouter(JausAddress jausAddress, InternalEventHandler ieHandler){
        this(jausAddress, ieHandler, false);
    }

    public JausRouter(JausAddress jausAddress, InternalEventHandler ieHandler, boolean allowWildcards){

        this.jausAddress = jausAddress;
		this.ieHandler = ieHandler;
        jrHandle = 0;
        int jAdd = (int) jausAddress.get();
        int jWildcards = allowWildcards ? 1 : 0;

		// array used to force pass by reference to C++ library
        long[] jrHandle_p = new long[1];
		String cfg = "nm.cfg\0";

        if(JuniorAPI.JrConnect(jAdd, cfg.getBytes(), jrHandle_p, jWildcards) != JrErrorCode.Ok)
		{
            System.out.println("UNABLE TO CONNECT TO THE NODE MANAGER. IS IT RUNNING?");
		}

		jrHandle = jrHandle_p[0];
    }

    public void updateJausID(JausAddress jausAddress) {
        this.updateJausID(jausAddress, false);
    }

    public synchronized void updateJausID(JausAddress jausAddress, boolean allowWildcards) {
        // Request to change our JAUS ID.  This means we have to close the current connection
        // to junior, and recreate it.  But first we need to stop the receive thread.
        stop();
        this.jausAddress = jausAddress;
        jrHandle = 0;
        int jAdd = (int) jausAddress.get();
        int jWildcards = allowWildcards ? 1 : 0;

		// array used to force pass by reference to C++ library
        long[] jrHandle_p = new long[1];
		String cfg = "nm.cfg\0";

        if(JuniorAPI.JrConnect(jAdd, cfg.getBytes(), jrHandle_p, jWildcards) != JrErrorCode.Ok)
		{
            System.out.println("UNABLE TO CONNECT TO THE NODE MANAGER. IS IT RUNNING?");
		}

		jrHandle = jrHandle_p[0];
        start();
    }

	/*
	* @param msg the message to be sent out.
	*
	* Routes the message through itself if message is local,
	* 	othewise routes it through the node manager.
	*/
    public synchronized void sendMessage(Send msg){

        // Pull the destination ID
		long destID = msg.getBody().getSendRec().getDestinationID().getSubFields();

        JausAddress destination = new JausAddress(destID);

        // If the destination is local, loopback through the routeMessage function
        if (destination.get() == jausAddress.get()){
            Receive message = new Receive();
            message.getBody().getReceiveRec().getSourceID().setSubsystemID(jausAddress.getSubsystemID());
            message.getBody().getReceiveRec().getSourceID().setNodeID(jausAddress.getNodeID());
            message.getBody().getReceiveRec().getSourceID().setComponentID(jausAddress.getComponentID());
            message.getBody().getReceiveRec().getMessagePayload().set(
                    msg.getBody().getSendRec().getMessagePayload().getLength(),
                    msg.getBody().getSendRec().getMessagePayload().getData());
			routeMessage(message);
        }

        // Otherwise, forward Message to NodeManager.
        else{
            JrErrorCode ret = JuniorAPI.JrSend(jrHandle, destination.get(),
                    msg.getBody().getSendRec().getMessagePayload().getLength(),
                    msg.getBody().getSendRec().getMessagePayload().getData().array());
			if (ret != JrErrorCode.Ok){
                logger.log(Level.SEVERE, "JausRouter failed to send message. JrErrorCode: " + ret.toString());
			}
        }
		
		notifyAll();
    }

	/*
	* @param msg the message being received. 
	*
	* Checks the ID of the message and invokes it as necessary.
	*
	*/
    public synchronized void routeMessage(Receive msg){
        ieHandler.invoke(msg);
        }


	/*
    * @Override
	*
	* Properly shuts down the thread.
	* Sends wake-up call to any sleeping threads in the C++ framework
	* Disconnects from Node Manager
	* Then joins the thread.
	*/
    public void stop(){
        isRunning = false;
    
        // Send a message to ourselves to wake-up the thread
        //byte[] msg_id = new byte[4];
        //long jAdd = jausAddress.get();
        
        //JuniorAPI.JrSend(jrHandle, (int) jAdd, 2, msg_id);
	
		// Inform the C++ framework to disconnect from NodeManager.
        //System.out.println("Stopping router...");
		JuniorAPI.JrDisconnect(jrHandle);
        //System.out.println("\tdisconnected from NM.");
		try
		{
			this.thread.join(THREAD_SLEEP);
            //System.out.println("\tJoining jausRouter thread.");
		}
		catch (java.lang.InterruptedException e)
		{
			logger.log(Level.SEVERE, null, e);
		}	
    }

	/*
	* @return jausAddress the jaus address of JausRouter.
	*
	*/
    public JausAddress getJausAddress(){
        return jausAddress;
    }

	/*
	* @Override
	*
	* While the thread is running, JausRouter continuously checks for received messages
	* through the node manager. 
	*/
    public void run(){


		// Arrays used to force pass by reference behavior in JrReceive
        long[] source_p = new long[1];
        int[] msg_id_p = new int[1];
        int[] flags_p = new int[1];
        int[] priority_p = new int[1];

        isRunning = true;

        while(isRunning){

            /*
             * Transport Service receives a TransportMessage
             */
            byte[] buffer_bytes = JuniorAPI.JrReceive(jrHandle, source_p, priority_p, flags_p, msg_id_p);
            if (buffer_bytes.length > 0)
            {
                // See if we got an exit signal while we were pending...
                if (!isRunning) break;
                
                // System.out.println("[JausRouter] Found message with size " + buffer_bytes.length);

				// wrap the received buffer in the ByteBuffer
				ByteBuffer buffer = ByteBuffer.wrap( buffer_bytes );
				buffer.order(ByteOrder.LITTLE_ENDIAN);
				buffer.rewind();

                // Create a component message wrapper to pass the servies...
                Receive message = new Receive();
                JausAddress sender = new JausAddress(source_p[0]);
                message.getBody().getReceiveRec().getSourceID().setSubsystemID(sender.getSubsystemID());
                message.getBody().getReceiveRec().getSourceID().setNodeID(sender.getNodeID());
                message.getBody().getReceiveRec().getSourceID().setComponentID(sender.getComponentID());
                message.getBody().getReceiveRec().getMessagePayload().set(buffer_bytes.length, buffer);
                
                // Route the message to the desired destination release the buffer
                routeMessage(message);
                if (buffer.array().length>0){
                    buffer = null; // won't the GC do this for us?
                }
            }
            else{
                try {
					this.thread.sleep(THREAD_SLEEP);
                } catch (InterruptedException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}
