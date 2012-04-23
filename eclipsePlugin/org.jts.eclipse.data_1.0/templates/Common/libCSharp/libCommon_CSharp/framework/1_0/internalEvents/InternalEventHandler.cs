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
 * <summary>Stores the internal events waiting to run, and controls adding and invoking them.</summary>
 * <author>Gina Nearing</author>
 * <date>4/1/11</date>
 */

namespace JTS
{
    
	public class InternalEventHandler
	{
        protected System.Collections.Generic.Queue<InternalEvent> internalEventQueue = new Queue<InternalEvent>();
		readonly object Lock = new object ();

		public InternalEventHandler ()
		{
		}

        /// <summary>
        /// Adds the given event to the Queue.
        /// </summary>
        /// <param name="ie">The event waiting to be called.</param>
		public void invoke (InternalEvent ie)
		{
			lock (Lock) {
				internalEventQueue.Enqueue (ie);
			}			
		}

        /// <summary>
        /// Pulls the next event off of the queue and gives it to the service.
        /// </summary>
        /// <returns>the event to be started - an empty event if no events waiting</returns>
		public InternalEvent invoked ()
		{
            InternalEvent ie = new InternalEvent();
			lock (Lock) {
				if (internalEventQueue.Count != 0) {
					ie = internalEventQueue.Dequeue ();
				}
			}
			return ie;
		}
		
		
	}
}
