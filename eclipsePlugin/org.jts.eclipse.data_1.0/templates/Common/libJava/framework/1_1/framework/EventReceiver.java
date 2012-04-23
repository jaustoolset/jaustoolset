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
* This is the parent class for the generated services.
*
* @version				04/01/2011
* @author				Gina Nearing
*
*/


import framework.transport.SimpleThread;
import framework.internalEvents.InternalEvent;
import framework.internalEvents.InternalEventHandler;
import framework.transport.OS.JrSignal;
import java.util.logging.Logger;
import java.util.logging.Level;


public class EventReceiver extends SimpleThread{

    protected JrSignal recvSignal = new JrSignal();
	protected static Logger logger;
	protected static final long THREAD_JOIN_TIME = 1000;
	protected static final long THREAD_SLEEP_TIME = 100;
	protected boolean running = false;
	protected InternalEventHandler ieHandler = new InternalEventHandler();


	// Classes to be overridden.
	// Cannot be abstract since they are part of runnable.
	protected void processInternalEvent(InternalEvent ie){}

	public void stop()
	{
		try
		{
			running = false;
			this.thread.join(THREAD_JOIN_TIME);
		}
		catch (java.lang.InterruptedException e)
		{
			logger.log(Level.SEVERE, null, e);
		}
	}

	public void run()
	{
		InternalEvent ie = new InternalEvent();
		
		synchronized(this){
			running = true;
		}

		/// Run receive loop...
		while(running)
		{
			ie = ieHandler.invoked();
			if (ie != null && ie.getName() != null)
			{
				// Let each inherited child handle the internal event differently...
				processInternalEvent(ie);

				// free the incoming message
				ie = null;
			}
			else
			{
				try
				{
					this.thread.sleep(THREAD_SLEEP_TIME);
				}
				catch (Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
			}
		}
	}
}
