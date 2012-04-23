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

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.popTransition;
import org.jts.eclipse.cjsidl.pushTransition;
import org.jts.eclipse.cjsidl.refAttr;
import org.jts.eclipse.cjsidl.serviceDef;
import org.jts.eclipse.cjsidl.simpleTransition;
import org.jts.eclipse.cjsidl.transition;
import org.jts.eclipse.cjsidl.transParam;
import org.jts.eclipse.cjsidl.transParams;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class Transition extends ConversionUtil{

	/**
	 * Converts a CJSIDL transition to JSIDL
	 * @param dt - a CJSIDL transition
	 * @return - resulting JSIDL transition
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.Transition convert(transition dt) throws ConversionException {
		org.jts.jsidl.binding.Transition newdt = new org.jts.jsidl.binding.Transition();
		newdt.setInterpretation(CJSIDLCommentToJSIDLInterp(dt.getComment()));
		
		String name = "";
		EList<refAttr> scopes = dt.getScoped();
		for(refAttr ref: scopes){
			name += ref.getName() + ".";
		}
		name += dt.getName();
		newdt.setName(name);

		if(dt.getParams() != null && dt.getParams().getParams()!= null){
			EList<transParam> params = dt.getParams().getParams();
			java.util.List<org.jts.jsidl.binding.Parameter> newparams = newdt.getParameter();
			for(transParam tmpparam: params){
				newparams.add(Parameter.convert(tmpparam));
			}
		}
		
		if(dt.getTransGuard() != null){
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
	 * Converts a JSIDL transition to a CJSIDL transition
	 * @param dt - a JSIDL transition
	 * @param parent - the parent object - not currently used.
	 * @param svcDef - the CJSIDL service def that will contain the new transition
	 * @return - resulting CJSIDL transition
	 * @throws ConversionException 
	 */
	public static transition convert(org.jts.jsidl.binding.Transition dt, EObject parent, serviceDef svcDef) throws ConversionException {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		transition output = factory.createtransition();

		
		java.util.List<String> subnames = new ArrayList<String>(); 
		subnames.addAll(Arrays.asList(dt.getName().split("[.]")));

		EList<refAttr> scope = output.getScoped();
		output.setName(subnames.get(subnames.size()-1));
		
		EObject currentObj = svcDef;
		EObject nextObject = null;
		for(int i=0; i< subnames.size()-1; i++){
			nextObject = Conversion.referenceHelper.getEObjectByRef(subnames.get(i), currentObj);
			EObject refObj = Conversion.referenceHelper.findRefEObject(subnames.get(i), currentObj);
			scope.add((refAttr) refObj);
			currentObj = nextObject;
		}

		transParams params = factory.createtransParams();
		output.setParams(params);

		EList<transParam> outputparams = params.getParams();
		java.util.List<org.jts.jsidl.binding.Parameter> inputparams = dt.getParameter();
		
		for(org.jts.jsidl.binding.Parameter tmpparam: inputparams){
			outputparams.add(Parameter.convertToTransitionParam(tmpparam, svcDef));
		}
		
		
		output.setComment(JSIDLInterpToCJSIDLComment(dt.getInterpretation()));
		if(dt.getInternal() != null){
			output.setType("internal");
			output.setDestination(InternalTransition.convert(dt.getInternal()));
		} else if(dt.getSimple() != null){
			if(dt.getSimple().getEndState() == null){
				output.setType("internal");
				output.setDestination(InternalTransition.convert(dt.getSimple()));
			} else {
				output.setType("simple");
				output.setDestination(SimpleTransition.convert(dt.getSimple()));
			}
			
		} else if(dt.getPush() != null){
			output.setType("push");
			output.setDestination(PushTransition.convert(dt.getPush()));
			
		} else if (dt.getPop() != null){
			output.setType("pop");
			output.setDestination(PopTransition.convert(dt.getPop(), output));
			
		}
		
		if(dt.getGuard() != null){
			output.setTransGuard(Guard.convert(dt.getGuard(), output));
		}
		
		// set up the actions so they can be created and added to the proper list
		org.jts.eclipse.cjsidl.actionList newactionList = factory.createactionList();
		org.jts.eclipse.cjsidl.sendActionList newsendActionList = factory.createsendActionList();
		
		java.util.List<Object> actionList = dt.getActionOrSendAction();
		if(dt.getActionOrSendAction().size() > 0){
			EList<org.jts.eclipse.cjsidl.action> newactions = newactionList.getActions();
			EList<org.jts.eclipse.cjsidl.action> newsendactions = newsendActionList.getActions();
			
			for(Object tmpobj: actionList){
				if(tmpobj instanceof org.jts.jsidl.binding.Action){
					newactions.add(Action.convert((org.jts.jsidl.binding.Action)tmpobj, output));
				} else if(tmpobj instanceof org.jts.jsidl.binding.SendAction){
					newsendactions.add(SendAction.convert((org.jts.jsidl.binding.SendAction)tmpobj, output));
				}
			}
			if(newactionList.getActions().size() > 0){
				output.setActions(newactionList);
			}
			if(newsendActionList.getActions().size() > 0){
				output.setSendActions(newsendActionList);
			}

		}
		return output;
	}

}
