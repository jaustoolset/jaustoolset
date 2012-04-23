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

import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.jts.eclipse.cjsidl.defaultState;
import org.jts.eclipse.cjsidl.defaultTransition;
import org.jts.eclipse.cjsidl.serviceDef;
import org.jts.eclipse.cjsidl.transition;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class DefaultState extends BaseState{

	/**
	 * Converts a CJSIDL object to a JSIDL object
	 * @param defaultState - the input CJSIDL default state
	 * @return - resulting JSIDL object
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.DefaultState convert(defaultState defaultState) throws ConversionException {
		org.jts.jsidl.binding.DefaultState newstate = new org.jts.jsidl.binding.DefaultState();
		
		newstate.setInterpretation(CJSIDLCommentToJSIDLInterp(defaultState.getComment()));
		
		EList<defaultTransition> inputDefaultTransList = defaultState.getDefaultTransition();
		java.util.List<org.jts.jsidl.binding.DefaultTransition> newDefaultTransList = newstate.getDefaultTransition();
		for(defaultTransition dt: inputDefaultTransList){
			newDefaultTransList.add(DefaultTransition.convert(dt));
		}
		
		EList<transition> inputTransList = defaultState.getTransition();
		java.util.List<org.jts.jsidl.binding.Transition> newTransList = newstate.getTransition();
		for(transition trans: inputTransList){
			newTransList.add(Transition.convert(trans));
		}
		return newstate;
	}

	/**
	 * Converts a JSIDL object to a CJSIDL object
	 * @param defaultState - input default state
	 * @param svcDef - parent service def of the new default state
	 * @return - resulting CJSIDL object
	 * @throws ConversionException 
	 */
	public static defaultState convert(org.jts.jsidl.binding.DefaultState defaultState, serviceDef svcDef) throws ConversionException {
		secondaryTransitionMap = new HashMap<String, org.jts.eclipse.cjsidl.popTransition>();
		
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.defaultState output = factory.createdefaultState();
		
		output.setComment(JSIDLInterpToCJSIDLComment(defaultState.getInterpretation()));
		EList<defaultTransition> newDefaultTransList = output.getDefaultTransition();
		java.util.List<org.jts.jsidl.binding.DefaultTransition> inputDefaultTransList = defaultState.getDefaultTransition();
		for(org.jts.jsidl.binding.DefaultTransition dt: inputDefaultTransList){
			newDefaultTransList.add(DefaultTransition.convert(dt, output));
		}
		
		EList<transition> newTransList = output.getTransition();
		java.util.List<org.jts.jsidl.binding.Transition> inputTransList = defaultState.getTransition();
		for(org.jts.jsidl.binding.Transition trans: inputTransList){
			newTransList.add(Transition.convert(trans, output, svcDef));
		}
		
		postProcessCJSIDLRefs(output);
		return output;
	}

}
