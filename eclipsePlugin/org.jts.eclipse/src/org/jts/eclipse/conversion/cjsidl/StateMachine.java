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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.nextState;
import org.jts.eclipse.cjsidl.refAttr;
import org.jts.eclipse.cjsidl.serviceDef;
import org.jts.eclipse.cjsidl.state;
import org.jts.eclipse.cjsidl.stateMachine;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class StateMachine extends ConversionUtil{
	// a mapping of startstates to names.  this is used because the start state
	// is processed before the state that it references.
	public static Map<String, org.jts.eclipse.cjsidl.startState> startStateMap;
	// a mapping of nextState transitions to states that don't exist when the 
	// transition is processed.
	public static Map<nextState, String> stateMap;

	/**
	 * Converts a CJSIDL state machine to JSIDL
	 * @param inputFSM - a CJSIDL state machine
	 * @return - resulting JSIDL state machine
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.StateMachine convert(stateMachine inputFSM) throws ConversionException {
		org.jts.jsidl.binding.StateMachine newFSM = new org.jts.jsidl.binding.StateMachine();
		
		if(inputFSM.getDefaultState() != null){
			newFSM.setDefaultState(DefaultState.convert(inputFSM.getDefaultState()));
		}
		newFSM.setInterpretation(CJSIDLCommentToJSIDLInterp(inputFSM.getComment()));
		String name = "";
		EList<refAttr> scopes = inputFSM.getScoped();
		for(refAttr ref: scopes){
			name += ref.getName() + ".";
		}
		name += inputFSM.getName();
		newFSM.setName(name);
		
		java.util.List<org.jts.jsidl.binding.State> newstates = newFSM.getState();
		EList<org.jts.eclipse.cjsidl.state> states = inputFSM.getStates();
		for(org.jts.eclipse.cjsidl.state state: states){
			newstates.add(State.convert(state));
		}

		return newFSM;
	}

	/**
	 * Converts a JSIDL state machine to CJSIDL
	 * @param inputFSM - a JSIDL state machine
	 * @param start - the JSIDL start 
	 * @param svcDef - the CJSIDL service def that will contain the resulting CJSIDL state machine
	 * @return - resulting CJSIDL state machine
	 * @throws ConversionException 
	 */
	public static stateMachine convert(org.jts.jsidl.binding.StateMachine inputFSM, org.jts.jsidl.binding.Start start, serviceDef svcDef) throws ConversionException {
		startStateMap = new HashMap<String, org.jts.eclipse.cjsidl.startState>();
		stateMap = new HashMap<nextState, String>();
		
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.stateMachine output = factory.createstateMachine();
		
		output.setStartState(StartState.convert(start));
		if(inputFSM.getDefaultState() != null){
			output.setDefaultState(DefaultState.convert(inputFSM.getDefaultState(), svcDef));
		}
		output.setComment(JSIDLInterpToCJSIDLComment(inputFSM.getInterpretation()));
		
		String name = inputFSM.getName();
		if(name.startsWith(svcDef.getServiceName())){
			name = name.replaceFirst(svcDef.getServiceName() + ".","");
		}
		java.util.List<String> subnames = new ArrayList<String>(); 
		subnames.addAll(Arrays.asList(name.split("[.]")));

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

		EList<org.jts.eclipse.cjsidl.state> outputstates = output.getStates();
		java.util.List<org.jts.jsidl.binding.State> inputstates = inputFSM.getState();
		for(org.jts.jsidl.binding.State st: inputstates){
			outputstates.add(State.convert(st, svcDef));
		}
		// some post processing for references
		output.setStartState(StartState.convert(start, output));
		
		addEndStateRefs(output);

		return output;
	}


	/**
	 * processes the stateMap to resolve missing references
	 * @param fsm - state machine to process
	 */
	private static void addEndStateRefs(stateMachine fsm) {
		
		Set<nextState> keys = stateMap.keySet();
		for(nextState key: keys){
			EList<state> states = fsm.getStates();
			String stateName = stateMap.get(key);

			EList<state> scoped = key.getScoped();
			
			java.util.List<String> subnames = new ArrayList<String>(); 
			subnames.addAll(Arrays.asList(stateName.split("[.]")));
			for(int i=0; i< subnames.size(); i++){
				String sub =  subnames.get(i);
				state tmpstate = getState(states, sub);
				if(tmpstate == null){
		            Logger.getLogger("CJSIDL").log(Level.SEVERE,
		    	            "Unable to locate state : " + stateName + " in stateMachine " + fsm.getName());
				}
				if(i == 0 && subnames.size() > 1){
					key.setFirstState(tmpstate);
				}else if(i == subnames.size()-1){
					key.setNextState(tmpstate);
				} else{
					scoped.add(tmpstate);
				}
				states = tmpstate.getSubState();
			}
		}
	}
	
	/**
	 * Retrieves a state based on name
	 * @param states - a list of states to search
	 * @param stateName - name to search for
	 * @return - state that was found
	 */
	private static state getState(EList<state> states, String stateName) {
		state result = null;
		
		for(state tmpst: states){
			if(tmpst.getName().equals(stateName)){
				result = tmpst;
				break;
			}
		}
		
		return result;
	}


}
