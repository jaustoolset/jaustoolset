/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
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
package org.jts.eclipse.conversion.cjsidl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.defaultTransition;
import org.jts.eclipse.cjsidl.popTransition;
import org.jts.eclipse.cjsidl.pushTransition;
import org.jts.eclipse.cjsidl.simpleTransition;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;
/**
 * @author cmessmer
 *
 */
public class DefaultTransition extends ConversionUtil{

	/**
	 * Converts a CJSIDL object to a JSIDL object
	 * @param dt - input default transition
	 * @return - the resulting JSIDL object
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.DefaultTransition convert(defaultTransition dt) throws ConversionException {
		org.jts.jsidl.binding.DefaultTransition newdt = new org.jts.jsidl.binding.DefaultTransition();
		newdt.setInterpretation(CJSIDLCommentToJSIDLInterp(dt.getComment()));

		if(dt.getTransGuard()!= null)
		{
		    newdt.setGuard(Guard.convert(dt.getTransGuard()));
		}
		if(dt.getType().equalsIgnoreCase("internal")){
			newdt.setInternal(InternalTransition.convert(dt.getComment()));
			
		} else if(dt.getType().equalsIgnoreCase("simple")){
			
			newdt.setSimple(SimpleTransition.convert((simpleTransition) dt.getDestination()));
 
		} else if(dt.getType().equalsIgnoreCase("push")){
			newdt.setPush(PushTransition.convert((pushTransition) dt.getDestination()));
		
		} else if(dt.getType().equalsIgnoreCase("pop")){
			newdt.setPop(PopTransition.convert((popTransition) dt.getDestination()));
		}
		java.util.List<Object> newactions = newdt.getActionOrSendAction();
		
		if(dt.getActions() != null){
			EList<org.jts.eclipse.cjsidl.action> actions = dt.getActions().getActions();
			for(org.jts.eclipse.cjsidl.action action: actions){
				newactions.add(Action.convert(action));
			}
		}
		if(dt.getSendActions() != null){
			EList<org.jts.eclipse.cjsidl.action> actions = dt.getSendActions().getActions();
			for(org.jts.eclipse.cjsidl.action action: actions){
				newactions.add(Action.convert(action));
			}
		}

		return newdt;
	}

	/**
	 * Converts a JSIDL object to a CJSIDL object
	 * @param dt - the input JSIDL default transition
	 * @param parent - the CJSIDL root object(serviceDef, typeSet)
	 * @return - resulting CJSIDL object
	 * @throws ConversionException 
	 */
	public static defaultTransition convert(org.jts.jsidl.binding.DefaultTransition dt, EObject parent) throws ConversionException {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		defaultTransition output = factory.createdefaultTransition();
		
		output.setComment(JSIDLInterpToCJSIDLComment(dt.getInterpretation()));
		if(dt.getInternal() != null){
			output.setType("internal");
			output.setDestination(InternalTransition.convert(dt.getInternal()));
		} else if(dt.getSimple() != null){
			output.setType("simple");
			output.setDestination(SimpleTransition.convert(dt.getSimple()));
			
		} else if(dt.getPush() != null){
			output.setType("push");
			output.setDestination(PushTransition.convert(dt.getPush()));
			
		} else if (dt.getPop() != null){
			output.setType("pop");
			// default transition has no parameters, so pass null to conversion
			output.setDestination(PopTransition.convert(dt.getPop(), null));
			
		}
		if(dt.getGuard() != null){
			output.setTransGuard(Guard.convert(dt.getGuard(), null));
		}
		
		// set up the actions so they can be created and added to the proper list
		org.jts.eclipse.cjsidl.actionList newactionList = factory.createactionList();
		output.setActions(newactionList);
		org.jts.eclipse.cjsidl.sendActionList newsendActionList = factory.createsendActionList();
		output.setSendActions(newsendActionList);
		
		java.util.List<Object> actionList = dt.getActionOrSendAction();
		EList<org.jts.eclipse.cjsidl.action> newactions = newactionList.getActions();
		EList<org.jts.eclipse.cjsidl.action> newsendactions = newsendActionList.getActions();
		
		for(Object tmpobj: actionList){
			if(tmpobj instanceof org.jts.jsidl.binding.Action){
				newactions.add(Action.convert((org.jts.jsidl.binding.Action)tmpobj));
			} else if(tmpobj instanceof org.jts.jsidl.binding.SendAction){
				newsendactions.add(SendAction.convert((org.jts.jsidl.binding.SendAction)tmpobj));
			}
		}

		return output;
	}


}
