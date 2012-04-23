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

/*
 * @(#)InternalEventsSetGenerator.java		0.1 2008/09/03
 * 
 * Copyright
 */

package org.jts.codegenerator;

import org.jts.jsidl.binding.*;

import java.util.Vector;

/**
 * This class will generate C++ code from a JSIDL Message Set 
 *
 * @version		0.1	3 Sept 2008
 * @author		Nicholas M. Johnson
 * @author		Gregory Garcia
 * @author		Jean-Francois Kamath
 * @author              Gina Nearing
 *
 */
public class InternalEventsSetGenerator
{
	private CodeLines.CodeType codeType;
	private InternalEventsSet intEventsSet;
	private Vector<String> eventDefIdList;
	private Vector<String> internalEventList;
	private ServiceDef sDef;
	private ServiceSet sSet;


    /*
     * @param codeType to determine language specific syntax
     * @param intEventsSet
     */
	public InternalEventsSetGenerator(CodeLines.CodeType codeType, ServiceDef sDef, ServiceSet sSet)
	{
		this.codeType = codeType;
		this.intEventsSet = sDef.getInternalEventsSet();
		this.sDef = sDef;
		this.sSet = sSet;
		
		eventDefIdList = new Vector<String>();
		internalEventList = new Vector<String>();
	}
	
	/**
	 * This method goes through the message set and calls the appropriate
	 * helper functions
	 * 
	 * Assumptions:	The object has been created.
	 * 				messageSet is non-NULL
     *
     * If ServiceDef inherits Transport it will ignore the Send and Receive events.
	 *  
	 */
	public void run(String namespace, String outDir)
	{		
        for (Object object:intEventsSet.getEventDefOrDeclaredEventDef())
        {
            if (object instanceof EventDef)
            {
                EventDef eventDef = (EventDef)object;
                
                boolean inheritsFromTransport = false;
                boolean isTransport = false;
                boolean isSendEvent = false;
                boolean isReceiveEvent = false;

				ServiceDef top = org.jts.codegenerator.support.InheritanceHelper.getTopParent(sDef, sSet);
		        if (top.getId().equals("urn:jaus:jss:core:Transport"))
                {
                	inheritsFromTransport = true;                	
                	}
                
                if( sDef.getName().equalsIgnoreCase( "transport" ) )
                {
                	isTransport = true;
                }
                
                if( eventDef.getName().equalsIgnoreCase( "send" ) )
                {
                	isSendEvent = true;
                }
                
                if( eventDef.getName().equalsIgnoreCase( "receive" ) )
                {
                	isReceiveEvent = true;
                }
                
                if( ( ( inheritsFromTransport ) || ( isTransport ) ) &&
                    ( isSendEvent || isReceiveEvent ) )
                {
					// Ignore Send and Receive since they are defined in the framework.
					// The generated code will include Transport_Aliases_1_x.h that will map
					// Send and Receive to either the 5710 or 5710A definition, depending on which
					// transport version was used.
				}
				else
				{
					// Generate internal event normally
                    EventDefGenerator eventDefGen = new EventDefGenerator(codeType, eventDef);
                    eventDefGen.run(namespace, outDir);
                    eventDefIdList.add(eventDefGen.getIdConstant());
                    
                    internalEventList.add(eventDef.getName());
                }
            }
            else if (object instanceof DeclaredEventDef)
            {
                DeclaredEventDef dEventDef = (DeclaredEventDef)object;
            }
        }
	}
	
	
	public Vector<String> getEventDefNameList()
	{
		return eventDefIdList;
	}
	
	public Vector<String> getInternalEventNameList()
	{
		return internalEventList;
	}
}
