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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.jts.eclipse.cjsidl.startState;
import org.jts.eclipse.cjsidl.state;
import org.jts.eclipse.cjsidl.stateMachine;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;
import org.jts.jsidl.binding.Start;

/**
 * @author cmessmer
 *
 */
public class StartState extends ConversionUtil {

	/**
	 * Converts a CJSIDL start tag to JSIDL
	 * @param FSMName - name of the containing FSM
	 * @param inputstate - the CJSIDL state referenced as the start point
	 * @return - resulting JSIDL start
	 */
	public static Start convert(String FSMName, startState inputstate) {
		Start start = new Start();
		
		start.setStateMachineName(FSMName);
		
		start.setInterpretation(CJSIDLCommentToJSIDLInterp(inputstate.getComment()));
		if(inputstate.getState() != null){
			String name = "";
			if(inputstate.getScoped() != null){
				EList<state> scoped = inputstate.getScoped();
				for(state st: scoped){
					name += st.getName() + ".";
				}
			}
			name += inputstate.getState().getName();
			start.setStateName(name);
		}
		return start;
	}

	/**
	 * Converts a JSIDL start to CJSIDL
	 * @param start - a JSIDL start 
	 * @return - resulting CJSIDL start 
	 */
	public static startState convert(Start start) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.startState output = factory.createstartState();
		
		output.setComment(JSIDLInterpToCJSIDLComment(start.getInterpretation()));
		StateMachine.startStateMap.put(start.getStateName(), output);
		
		return output;
	}


	/**
	 * Converts a JSIDL start to CJSIDL
	 * @param start - input JSIDL start
	 * @param fsm - containing CJSIDL FSM
	 * @return - resulting CJSIDL object
	 */
	public static startState convert(Start start, stateMachine fsm) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.startState output = factory.createstartState();
		
		output.setComment(JSIDLInterpToCJSIDLComment(start.getInterpretation()));

		String name = start.getStateName();
		java.util.List<String> subnames = new ArrayList<String>(); 
		subnames.addAll(Arrays.asList(name.split("[.]")));
		
		EList<state> states = output.getScoped();
		
		EList<org.jts.eclipse.cjsidl.state> stateList = fsm.getStates();

		// get rid of the last name from the list so it doesn't get added to the output list
		String laststate = subnames.get(subnames.size()-1);
		boolean success = subnames.remove(laststate);
		if(!success){
            Logger.getLogger("CJSIDL").log(Level.SEVERE,
            "Unable to remove last state name from list.");
		}
		
		for(String sub: subnames){
			state substate = getState(stateList, sub);
			states.add(substate);
			stateList = substate.getSubState();
		}
		output.setState(getState(stateList, laststate));
		
		return output;
	}
	/**
	 * Retrieves a state given a list of CJSIDL states
	 * @param states - list of CJSIDL states
	 * @param name - name of the state to find
	 * @return - state that was found with the correct name
	 */
	private static state getState(EList<state> states, String name){
		
		for(state st: states){
			if(st.getName().equals(name)){
				return st;
			}
		}
		// this should never be possible
        Logger.getLogger("CJSIDL").log(Level.SEVERE,
        "Unable to locate state with name=" + name);
		return null;
	}


}
