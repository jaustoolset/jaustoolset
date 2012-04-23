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
 * <summary>The parent class for all generated Finite State Machines (FSM).</summary>
 * <author>Gina Nearing</author>
 * <date>4/1/11</date>
 */
namespace JTS
{
	public class StateMachine
	{
		protected InternalEventHandler ieHandler;
		protected JausRouter jausRouter;
		protected List<StateChangeNotification> notifications;


		public StateMachine ()
		{
			notifications = new List<StateChangeNotification>();
		}

        /// <summary>
        /// Uses the given InternalEventHandler and JausRouter as its own.
        /// </summary>
        /// <param name="ieHandler">The InternalEventHandler to reference.</param>
        /// <param name="jausRouter">The component's JausRouter to send and receive messages.</param>
		public void setHandlers (ref InternalEventHandler ieHandler, ref JausRouter jausRouter)
		{
			this.ieHandler = ieHandler;
			this.jausRouter = jausRouter;
		}
		
		public InternalEventHandler getHandler()
		{ 
			return ieHandler; 
		}


        /// <summary>
        /// Encodes the given message into a buffer and prepares to send it.
        /// </summary>
        /// <param name="msg">The message to be sent</param>
        /// <param name="dest">The address to which the message is going.</param>
		protected void sendJausMessage (Message msg, JausAddress dest)
		{
			// Encode the message
			uint bufsize = (uint) msg.getSize();
			byte[] buffer = new byte[bufsize];
			int pos = 0;
			msg.encode(buffer, pos);
			// and send...
			sendJausMessage (bufsize, buffer, dest);
						
		}

        /// <summary>
        /// Wraps the message buffer into a Send wrapper and sends it through
        /// the JausRouter.
        /// </summary>
        /// <param name="bufsize">the size of the given buffer in bytes</param>
        /// <param name="buffer">the bytes from the message to be sent</param>
        /// <param name="dest">the destination of the message</param>
		protected void sendJausMessage (uint bufsize, byte[] buffer, JausAddress dest)
		{
			Send response = new Send();
			response.getBody().getSendRec().getMessagePayload().set((int)bufsize, buffer);
			response.getBody().getSendRec().getDestinationID().setSubsystemID(dest.getSubsystemID());
            response.getBody().getSendRec().getDestinationID().setNodeID(dest.getNodeID());
            response.getBody().getSendRec().getDestinationID().setComponentID(dest.getComponentID());
            //Console.WriteLine("Sending response msg " + BitConverter.ToInt16(b, 0));
			jausRouter.sendMessage(ref response);
		}
		
		// This function must be overridden by derived classes
		public virtual void setupNotifications() {}

		/*
		* Registers a notification event handler with the FSM.
		*/
		public void registerNotification( string StateName, InternalEventHandler ieHandler, 
									string EventName, string SourceName )
		{
			StateChangeNotification n = new StateChangeNotification();
			n._state = StateName;
			n._handler = ieHandler;
			n._event = EventName;
			n._source = SourceName;

			notifications.Add( n );
		}

		/*
		* Checks to see if an event triggers an notification
		*/
		public void processNotifications(string state, InternalEvent ie)
		{
			if (state.IndexOf(".") != -1)
				state = state.Substring(state.LastIndexOf(".")+1);
			for (int i=0; i < notifications.Count; i++)
			{
				if (notifications[i]._state == state)
				{
					// Note that we send a copy of the original internal event, 
					// since processing the event will delete it and we might
					// still need the original for future notifications...
					if (ie != null)
						notifications[i]._handler.invoke( new InternalEvent(notifications[i]._event, ie.getSource()) );
					else
						notifications[i]._handler.invoke( new InternalEvent(notifications[i]._event, notifications[i]._source) );
				}
			}
		}		
		
		
	}
}
