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

package org.jts.gui.JAXBtoJmatter;

import org.hibernate.Query;
import org.hibernate.Session;
import com.u2d.app.Context;
import com.u2d.type.atom.BooleanEO;
import com.u2d.type.atom.StringEO;
import org.jts.gui.importJSIDL.ImportException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import javax.xml.bind.JAXB;

// This class converts a JAXB ProtocolBehavior to a JMatter ProtocolBehavior
public class ProtocolBehavior
{
	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.ProtocolBehavior lookupOrCreate( org.jts.jsidl.binding.ServiceDef jxServiceDef ) 
	{
	  // Get and handle on a hibernate session
        com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism();
        
        // Initially, we create a unique protocol behavior element.  We'll decide
        // later whether this already exists in the database.
        com.u2d.generated.ProtocolBehavior jmProtocolBehavior = new com.u2d.generated.ProtocolBehavior();
        
        // switch loopbacks with internal transitions to be consistent with JSIDLv1.1 (loopbacks deprecated, replaced by internal transitions)
        convertLoopbacks( jxServiceDef.getProtocolBehavior() );
        
    	// check for inheritance
    	com.u2d.generated.ServiceDef jmBaseServiceDef = null;
    	org.jts.jsidl.binding.References refs = jxServiceDef.getReferences();

    	// initialize reference name to sd name in case there is not inheritance
    	String referenceName = jxServiceDef.getName();
    	if( refs != null )
    	{
    		org.jts.jsidl.binding.InheritsFrom iref = refs.getInheritsFrom();

    		if( iref != null )
    		{
    			referenceName = iref.getName();
    			jmBaseServiceDef = ServiceDef.lookupServiceDef( iref.getId(), iref.getVersion() );

    			if( jmBaseServiceDef == null )
    				throw new ImportException("ServiceDef with ID: (" + iref.getId() + ") and Version: (" + iref.getVersion() +") not found. " +
    				"Inherited by ServiceDef with ID: " + jxServiceDef.getId() + " and Version: " + jxServiceDef.getVersion());
    		}
    	}

		// in some cases (env sensing), the pb being imported may have the service def
		// name already prepended to the state machine names.  We need to strip it
		// off to be consistent with the stripped base.    	
    	org.jts.jsidl.binding.ProtocolBehavior pb = jxServiceDef.getProtocolBehavior();
		org.jts.gui.Inheritance.stripPrependedServiceName( jxServiceDef.getName(), pb );

    	// merge child pb and base pb if one exists
    	if( jmBaseServiceDef != null )
    	{
    		// create base behavior shell
    		// NOTE: this method will parse out the prepended service name stored with db objects
    		org.jts.jsidl.binding.ProtocolBehavior shell = 
    			org.jts.gui.Inheritance.getBaseBehaviorShellAsJaxb( jmBaseServiceDef.getProtocolBehavior() );
    		
     		// since the base was stored in the db, by convention the sdef name was
     		// prepended to the state machine names, remove them so we can resolve while merging
     		org.jts.gui.Inheritance.parsePrependedServiceName( shell );

    		// merge shell with derived behavior
    		pb = org.jts.gui.Inheritance.mergeBehaviors( shell, pb, referenceName );
    	}
    	
    	// JTS CONVENTION: prepend the current service name to the state machine name and
    	// start state names.  Note that this is done after any merging is complete
    	org.jts.gui.Inheritance.prependServiceName( jxServiceDef.getName(), pb );
    	
		// set merged pb as new value for db object
		OutputStream out = new ByteArrayOutputStream( );
		javax.xml.bind.JAXB.marshal( pb, out );
		jmProtocolBehavior.getProtocolBehavior().setValue( out.toString() );       	
	    
	    // Now that we've generated a string representation of the protocol, let's see if a
	    // match already exists in the database.		
		Session session = persistenceMechanism.getSession();
		String hql = "from ProtocolBehavior pb";
		Query hibernateQuery = session.createQuery(hql);
		
		// Get the results of the query
		java.util.List resultsList = hibernateQuery.list();

        // The protocol behavior being imported may have different mxCell and mxGeo properties
        // than those in the database.  We don't want to differentiate on those, however,
        // so we strip any elements from the strings.
        String simpleSource = CleanupProtocolString(jmProtocolBehavior.getProtocolBehavior().stringValue());
		System.out.println("Found " + resultsList.size() + " protocol behaviors");
		
		// loop through all protocols, checking for a match		
		for(int i = 0; i < resultsList.size(); i++)
		{
		    // The protocol behavior being imported may have different mxCell and mxGeo properties
            // than those in the database.  We don't want to differentiate on those, however,
            // so we strip any elements from the strings.
		    String testTarget = CleanupProtocolString(((com.u2d.generated.ProtocolBehavior) resultsList.get(i)).getProtocolBehavior().stringValue());
            	
            // compare the marhsalled strings (minus the mx-properties)	                                                  
		    if (simpleSource.compareTo(testTarget) == 0)
		    {
		        // The new protocol behavior matches an existing database entry.  Use that instead.
    		    System.out.println("Protocol match found: " + (i+1));
				jmProtocolBehavior = (com.u2d.generated.ProtocolBehavior) resultsList.get(i);
				break;
			}
		}
	    
	    
	    // return the new (or existing) protocol behavior.
	    persistenceMechanism.save( jmProtocolBehavior );
        jmProtocolBehavior.firePropertyChange("ProtocolBehavior", jmProtocolBehavior, jmProtocolBehavior);  
        return jmProtocolBehavior;
	}
	
	private static void convertLoopbacks( org.jts.jsidl.binding.ProtocolBehavior pb ) {
	
	    // for all statemachines
	    List <org.jts.jsidl.binding.StateMachine> smList = pb.getStateMachine();
	    for( int ii=0; ii<smList.size(); ii++ ) {
	       org.jts.jsidl.binding.StateMachine sm = smList.get(ii);
	       
	       List <org.jts.jsidl.binding.State> stateList = sm.getState();
	       convertStateLoopbacks( stateList );
	    }
	       // for all states and nested states
	              // for all transitions
	              // for all default transitions
	       // for all default states       
	              // for all transitions
	              // for all default transitions
	}
	
	private static void convertStateLoopbacks( List <org.jts.jsidl.binding.State> stateList ) {
	     if( stateList == null || stateList.size() == 0 )
	        return;
	     
	     for( int ii=0; ii<stateList.size(); ii++ ) {
	        org.jts.jsidl.binding.State state = stateList.get(ii);
	        convertStateLoopbacks( state.getState() );
	        
	        convertTransitionLoopbacks( state.getTransition() );
	        convertDefaultTransitionLoopbacks( state.getDefaultTransition() );
	        
	        convertDefaultStateLoopbacks( state.getDefaultState() );
	     }
	}
	
	private static void convertDefaultStateLoopbacks( org.jts.jsidl.binding.DefaultState ds ) {
	     if( ds == null )
	        return;
	     
	     convertTransitionLoopbacks( ds.getTransition() );
	     convertDefaultTransitionLoopbacks( ds.getDefaultTransition() );
	}
	
	private static void convertTransitionLoopbacks( List <org.jts.jsidl.binding.Transition> transitionList ) {
	     if( transitionList == null || transitionList.size() == 0 )
	        return;
	        
	     for( int ii=0; ii<transitionList.size(); ii++ ) {
	        org.jts.jsidl.binding.Transition tr = transitionList.get(ii);
	        
	        if( tr.getSimple() != null && tr.getSimple().getEndState() == null ) {
	           tr.setSimple( null );
	           tr.setInternal( new org.jts.jsidl.binding.Internal() );
	        }
	     }          
	}
	
	private static void convertDefaultTransitionLoopbacks( List <org.jts.jsidl.binding.DefaultTransition> defaultTransitionList ) {
	     if( defaultTransitionList == null || defaultTransitionList.size() == 0 )
	        return;
	        
	     for( int ii=0; ii<defaultTransitionList.size(); ii++ ) {
	        org.jts.jsidl.binding.DefaultTransition dtr = defaultTransitionList.get(ii);
	        
	        if( dtr.getSimple() != null && dtr.getSimple().getEndState() == null ) {
	           dtr.setSimple( null );
	           dtr.setInternal( new org.jts.jsidl.binding.Internal() );
	        }
	     }          
	}
	
	private static String CleanupProtocolString(String in)
	{
	    String out = in;
	    int leftIndex, rightIndex, safeCounter;
	    
	    // Strip out the mxCell stuff.  This might be in the form '<mxCell' or '<some_preface:mxCell'
	    // We therefore need to find the mxCell keyword, and look for the angle brackets before
	    // and after it.
	    safeCounter = 0;
        while (out.contains("mxCell") && (safeCounter < 10000))
	    {
	        safeCounter++;
	        leftIndex = out.lastIndexOf("<", out.indexOf("mxCell"));
	        rightIndex = out.indexOf(">", out.indexOf("mxCell"));
	        out = out.substring(0, leftIndex) + out.substring(rightIndex+1);
        }		
        
        // Strip out the mxGeometry stuff.  This might be in the form '<mxGeometry' 
        // or '<some_preface:mxGeometry'.  We therefore need to find the mxCell keyword, 
        // and look for the angle brackets before and after it.
	    safeCounter = 0;        
        while (out.contains("mxGeometry") && (safeCounter < 10000))
	    {
	        safeCounter++;	    
	        leftIndex = out.lastIndexOf("<", out.indexOf("mxGeometry"));
	        rightIndex = out.indexOf(">", out.indexOf("mxGeometry"));
	        out = out.substring(0, leftIndex) + out.substring(rightIndex+1);
        }		

        // Strip all interpretation fields
	    safeCounter = 0;        
        while (out.contains(" interpretation=\"") && (safeCounter < 10000))
	    {
	        safeCounter++;	    
	        leftIndex = out.indexOf(" interpretation=\"");
	        rightIndex = out.indexOf("\"", out.indexOf(" interpretation=\"")+17);
	        out = out.substring(0, leftIndex) + out.substring(rightIndex+1);
        }
        
        // remove all whitespaces, tabs, and line breaks
        out = out.replaceAll(" ", "").replaceAll("\t", "").replaceAll("\r", "").replaceAll("\n", "");
        return out;
    }

}
