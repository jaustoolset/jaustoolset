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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jts.eclipse.cjsidl.nextState;
import org.jts.eclipse.cjsidl.state;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class EndState extends ConversionUtil{

	/**
	 * Creates a state name from the nextState CJSIDL object
	 * @param nextState - the input nextState object
	 * @return - the resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.EndState convert(nextState nextState) {
		org.jts.jsidl.binding.EndState es = new org.jts.jsidl.binding.EndState();
		es.setInterpretation(CJSIDLCommentToJSIDLInterp(nextState.getComment()));
		if(nextState.getNextState() != null){
			String name= "";
			if(nextState.getFirstState() != null){
				name = nextState.getFirstState().getName();
			}
			if(nextState.getScoped() != null){
				for(state st: nextState.getScoped()){
					name += "." + st.getName();
				}
			}
			if(!name.isEmpty() && !name.equals("")){
				name += ".";
			}
			name += nextState.getNextState().getName();
			if(name.equals("")){
                Logger.getLogger("CJSIDL").log(Level.SEVERE,
                        "Next state name was not set properly or was null.");
			}
			es.setState(name);
		}
		return es; 
	}

	/**
	 * Converts an end state target from JSIDL to CJSIDL
	 * @param endState - the JSIDL data binding
	 * @return - the resulting CJSIDL object, without the reference set.  the 
	 * reference is placed in a map set at the state machine level so that references
	 * can be resolved after all the states exist.
	 */
	public static nextState convert(org.jts.jsidl.binding.EndState endState) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		nextState output = factory.createnextState();
		
		output.setComment(JSIDLInterpToCJSIDLComment(endState.getInterpretation()));
		// since we don't have access to the object yet, we can't set the reference
		// put it in a map for post processing.
		StateMachine.stateMap.put(output, endState.getState());		
		//output.setNextState(value)
		
		return output;
	}

}
