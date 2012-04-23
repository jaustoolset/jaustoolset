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
import org.jts.eclipse.cjsidl.defaultTransition;
import org.jts.eclipse.cjsidl.serviceDef;
import org.jts.eclipse.cjsidl.state;
import org.jts.eclipse.cjsidl.transition;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class State extends BaseState{

	/**
	 * Converts a CJSIDL state to JSIDL
	 * @param inputstate - a CJSIDL state object
	 * @return - resulting JSIDL state
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.State convert(state inputstate) throws ConversionException {
		org.jts.jsidl.binding.State newstate = new org.jts.jsidl.binding.State();
		
		newstate.setInterpretation(CJSIDLCommentToJSIDLInterp(inputstate.getComment()));
		newstate.setName(inputstate.getName());
		
		java.util.List<org.jts.jsidl.binding.State> newStateList = newstate.getState();
		EList<org.jts.eclipse.cjsidl.state> stateList = inputstate.getSubState();
		for(state st: stateList){
			newStateList.add(State.convert(st));
			if(st.getInitial() != null && st.getInitial().equals("initial")){
				newstate.setInitialState(st.getName());
			}
		}
		
		if(inputstate.getEntryAction() != null){
			newstate.setEntry(EntryAction.convert(inputstate.getEntryAction()));
		}
		if(inputstate.getExitAction() != null){
			newstate.setExit(ExitAction.convert(inputstate.getExitAction()));
		}
		if(inputstate.getDefaultState() != null){
			newstate.setDefaultState(DefaultState.convert(inputstate.getDefaultState()));
		}
		
		EList<defaultTransition> inputDefaultTransList = inputstate.getDefaultTransition();
		java.util.List<org.jts.jsidl.binding.DefaultTransition> newDefaultTransList = newstate.getDefaultTransition();
		for(defaultTransition dt: inputDefaultTransList){
			newDefaultTransList.add(DefaultTransition.convert(dt));
		}
		
		EList<transition> inputTransList = inputstate.getTransitions();
		java.util.List<org.jts.jsidl.binding.Transition> newTransList = newstate.getTransition();
		for(transition trans: inputTransList){
			newTransList.add(Transition.convert(trans));
		}
		
		return newstate;

	}
	
	/**
	 * Converts a JSIDL state to CJSIDL
	 * @param st - JSIDL state to convert
	 * @param svcDef - CJSIDL serviceDef that will contain the new state
	 * @return - resulting CJSIDL state
	 * @throws ConversionException 
	 */
	public static state convert(org.jts.jsidl.binding.State st, serviceDef svcDef) throws ConversionException {
		// each stat has transitions which may contain secondary transitions to transitions
		// that have not been created yet.  This is used for pop transitions.
		secondaryTransitionMap = new HashMap<String, org.jts.eclipse.cjsidl.popTransition>();
		
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.state output = factory.createstate();
		output.setName(st.getName());
		
		// convert the sub states
		java.util.List<org.jts.jsidl.binding.State> stateList = st.getState();
		EList<org.jts.eclipse.cjsidl.state> newStateList = output.getSubState();
		for(org.jts.jsidl.binding.State tmpstate: stateList){
			newStateList.add(State.convert(tmpstate, svcDef));
		}
		
		
		if(st.getEntry() != null){
			// convert the entry actions
			org.jts.eclipse.cjsidl.entry entryactions = EntryAction.convert(st.getEntry());
			output.setEntryAction(entryactions);
		}
		
		if(st.getExit() != null){
			// convert the exit actions
			org.jts.eclipse.cjsidl.exit exitactions = ExitAction.convert(st.getExit());
			output.setExitAction(exitactions);
		}
		
		if(st.getDefaultState() != null){
			output.setDefaultState(DefaultState.convert(st.getDefaultState(), svcDef));
		}
		
		output.setComment(JSIDLInterpToCJSIDLComment(st.getInterpretation()));
		EList<defaultTransition> newDefaultTransList = output.getDefaultTransition();
		java.util.List<org.jts.jsidl.binding.DefaultTransition> inputDefaultTransList = st.getDefaultTransition();
		for(org.jts.jsidl.binding.DefaultTransition dt: inputDefaultTransList){
			newDefaultTransList.add(DefaultTransition.convert(dt, output));
		}
		
		EList<transition> newTransList = output.getTransitions();
		java.util.List<org.jts.jsidl.binding.Transition> inputTransList = st.getTransition();
		for(org.jts.jsidl.binding.Transition trans: inputTransList){
			newTransList.add(Transition.convert(trans, output, svcDef));
		}
		
		if(st.getInitialState() != null){
			String init = st.getInitialState();
			for(state tmpstate: output.getSubState()){
				if(tmpstate.getName().equals(init)){
					tmpstate.setInitial("initial");
					break;
				}
			}
		}
		
		postProcessCJSIDLRefs(output);

		return output;
	}


}
