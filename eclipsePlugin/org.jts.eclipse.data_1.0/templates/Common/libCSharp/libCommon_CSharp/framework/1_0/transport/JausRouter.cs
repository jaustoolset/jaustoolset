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
using System;
using System.Collections;
using System.Collections.Generic;

/**
 * <summary>The core of sending and recieving messages.
 * Communicates with the C++ framework and nodeManager to
 * process incoming and outgoing messages.</summary>
 * <author>Gina Nearing</author>
 * <date>4/1/11</date>
 */
namespace JTS
{
	public class JausRouter : SimpleThread
	{
		
		protected bool isRunning;
		protected int jrHandle;
		protected JausAddress jausAddress;
		protected object runLock = new object();
		protected InternalEventHandler ieHandler;
		
        /// <summary>
        /// Constructor. Connects with nodeManager and sets up variables.
        /// </summary>
        /// <param name="jausAddress">The JausAddress of the current component.</param>
		public JausRouter (JausAddress jausAddress, InternalEventHandler ieHandler)
		{
			this.jausAddress = jausAddress;
			this.ieHandler = ieHandler;
			jrHandle = 0;
			if (JuniorAPI.JrConnect((int)jausAddress.get(), "nm.cfg", ref jrHandle) != 0)
			{
				Console.WriteLine("UNABLE TO CONNECT TO THE NODE MANAGER.  IS IT RUNNING?");
			}
		}

        /// <summary>
        /// Destructor. Ensures that the component has properly disconnected from the C++ framework.
        /// </summary>
		~JausRouter()
		{
			JuniorAPI.JrDisconnect((int) jrHandle);
		}
		
        /// <summary>
        /// Determines if the outgoing message is local, or needs to be routed through
        /// the node manager.
        /// </summary>
        /// <param name="msg">the outgoing message wrapped in a Send wrapper.</param>
		public void sendMessage(ref Send msg)
		{
			// Pull the destination ID
			JausAddress destination = new JausAddress(msg.getBody().getSendRec().getDestSubsystemID(), 
			                                          msg.getBody().getSendRec().getDestNodeID(),
			                                          msg.getBody().getSendRec().getDestComponentID());
            // If the destination is local, loopback to the route message function
            if (destination.get() == jausAddress.get())
            {
                Receive message = new Receive();
                message.getBody().getReceiveRec().setSrcSubsystemID(jausAddress.getSubsystemID());
                message.getBody().getReceiveRec().setSrcNodeID(jausAddress.getNodeID());
                message.getBody().getReceiveRec().setSrcComponentID(jausAddress.getComponentID());
                message.getBody().getReceiveRec().getMessagePayload().set(msg.getBody().getSendRec().getMessagePayload().getLength(),
                                                                      msg.getBody().getSendRec().getMessagePayload().getData());

                routeMessage(message);
            }
            // Otherwise, forward the message to NodeManager
            else
			{
				JrErrorCode ret = JuniorAPI.JrSend((int) jrHandle, destination.get(),
				                                   msg.getBody().getSendRec().getMessagePayload().getLength(),
				                                   msg.getBody().getSendRec().getMessagePayload().getData());	
			}	
		}
		
        /// <summary>
        /// Takes the incoming message and if its ID is in the Handlers list,
        /// invoke the event.
        /// </summary>
        /// <param name="msg">The incoming message</param>
		public void routeMessage(Receive msg)
		{
			ieHandler.invoke((InternalEvent) msg);
			}
		
        /// <summary>
        /// Overrides the SimpleThread.stop() method.
        /// Wakes up any sleeping C++ threads.
        /// Joins JausRouter thread.
        /// </summary>
		public override void stop()
		{
			lock(runLock){
				isRunning = false;
			}
			
			// Send a message to ourselves to wake up the thread.
			byte[] msg_id = {0};
			JuniorAPI.JrSend(jrHandle, jausAddress.get(), (uint) 2, msg_id);
            thread.Join(THREAD_JOIN_TIME);
		}
		
        /// <summary>
        /// Get the component address.
        /// </summary>
        /// <returns>The component's JausAddress</returns>
		public JausAddress getJausAddress()
		{
			return jausAddress;
		}
		
        /// <summary>
        /// Checks the C++ framework for incoming messages, routing them as needed.
        /// </summary>
		public override void run()
		{
			int priority = 0;
			uint source = 0;
			int flags =0;
			uint bufsize = 0;
			byte[] buffer;
			ushort msg_id = 0;            
			//runLock._lock();
			isRunning = true;
			//runLock.unlock();

			while(isRunning)
			{           
                buffer = JuniorAPI.JrReceive((int) jrHandle, ref source, ref priority, ref flags, ref msg_id);
				if( buffer != null & buffer.Length > 0)
				{
					if(!isRunning)
					{
						break;
					}
                    bufsize = (uint) buffer.Length;
                    byte[] tmpBuff = new byte[buffer.Length];
                    for (int i = 0; i < buffer.Length; i++)
                    {
                        tmpBuff[i] = buffer[i];
                    }
                    buffer = null;
					Receive message = new Receive();
					JausAddress sender = new JausAddress(source);

					message.getBody().getReceiveRec().setSrcSubsystemID(sender.getSubsystemID());
					message.getBody().getReceiveRec().setSrcNodeID(sender.getNodeID());					                                               
					message.getBody().getReceiveRec().setSrcComponentID(sender.getComponentID());
					message.getBody().getReceiveRec().getMessagePayload().set(bufsize, tmpBuff);                    
					routeMessage(message);
				}
				else
				{
					//OS.JrSleep(1); //throttle
				}
			}
		}		
	}
}
