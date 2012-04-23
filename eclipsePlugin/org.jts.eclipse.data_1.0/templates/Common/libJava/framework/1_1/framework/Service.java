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

import framework.EventReceiver;
import framework.internalEvents.InternalEvent;
import java.util.ArrayList;


public class Service extends EventReceiver{

    protected String m_URN;

	// Classes to be overridden.
	// Cannot be abstract since they are part of runnable.
	public synchronized boolean processTransitions(InternalEvent ie){return false;}
	public synchronized boolean defaultTransitions(InternalEvent ie){return false;}

	/*
	*
	* Default constructor.
	*
	*/
    public Service(){
        m_URN = "Unknown";
    }

	/*
	* 
	* @return m_URN the id of the service.
	*
	*/
    public String getUrn(){
        return m_URN;
    }

	public void processInternalEvent(InternalEvent ie)
	{
		// Invoke the FSM transition for this event.  If no explicit transition
		// is defined, try a default transition...
		if (processTransitions(ie) == false)
		{
			defaultTransitions(ie);
		}
    }

}
