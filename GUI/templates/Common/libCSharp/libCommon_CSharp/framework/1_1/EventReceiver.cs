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
using System.Collections.Generic;

/**
 * <summary>The parent class for all generated services.</summary>
 * <author>Gina Nearing</author>
 * <date>4/1/11</date>
 */
namespace JTS
{

public class EventReceiver : SimpleThread{

	protected bool running;
	protected object runLock = new object();
	protected OS.JrSignal recvSignal;	
	protected InternalEventHandler ieHandler;


	// Classes to be overridden.
	public virtual void processInternalEvent(InternalEvent ie){}

	public EventReceiver()
	{
		running = false;
		ieHandler = new InternalEventHandler();	
	}

    /// <summary>
    /// Overrides the SimpleThread.stop() method.
    /// Overridden by the child class. Stops the
    /// service by joining its thread.
    /// </summary>
    public override void stop()
	{
		lock(runLock)
        {
			running = false;
      	}
		//recvSignal.signal();
		thread.Join(THREAD_JOIN_TIME);
	}


    /// <summary>
    /// Overrides the SimpleThread.run() method.
    /// Overridden by child class. Contians the core
    /// of the service functionality.
    /// </summary>
	public override void run()
	{
		InternalEvent ie;

		lock(runLock)
        {
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
				/// Wait for message to be received.
				recvSignal.wait();
			}
		}
	}
}

}
