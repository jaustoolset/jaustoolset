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
using System.Threading;
using System.Collections.Generic;

/**
 * <summary>The parent class for all generated services.</summary>
 * <author>Gina Nearing</author>
 * <date>4/1/11</date>
 */
namespace JTS
{

	public class Service : EventReceiver
	{
		protected String m_URN;
		protected Mutex mutex = new Mutex();

		public Service ()
		{
		}
		
		public virtual bool processTransitions(InternalEvent ie){return false;}
		public virtual bool defaultTransitions(InternalEvent ie){return false;}
		
        /// <summary>
        /// Retrieves the resource name of the service.
        /// </summary>
        /// <returns>m_URN - the resource ID stored as a String.</returns>
		public String getURN()
		{
			return m_URN;
		}
		
		public override void processInternalEvent(InternalEvent ie)
		{
			// Invoke the FSM transition for this event.  If no explicit transition
			// is defined, try a default transition...
			if (processTransitions(ie) == false)
		{
				defaultTransitions(ie);
			}
		}	
		}
}
