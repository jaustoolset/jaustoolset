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

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.transition;

/**
 * @author cmessmer
 *
 */
public class BaseState extends ConversionUtil{

	/**
	 * used to store secondary transitions that are attached to a pop transition
	 */
	public static Map<String, org.jts.eclipse.cjsidl.popTransition> secondaryTransitionMap;
	
	/**
	 * Called to post-process secondary transition references.  This is necessary because 
	 * the target for the secondary transition may not exist when the transition is defined.
	 * @param state - the current state that contains the transition
	 */
	public static void postProcessCJSIDLRefs(EObject state){
		// post processing for pop transition's secondary transition reference
		Set<String> keys = secondaryTransitionMap.keySet();
		for(String key: keys){
			org.jts.eclipse.cjsidl.popTransition target = secondaryTransitionMap.get(key);
			if(target instanceof org.jts.eclipse.cjsidl.popTransition){
				// find the transition with name matching the key
				EList<transition> translist = null;
				if(state instanceof org.jts.eclipse.cjsidl.defaultState){
					translist = ((org.jts.eclipse.cjsidl.defaultState)state).getTransition();
				} else if(state instanceof org.jts.eclipse.cjsidl.state){
					translist= ((org.jts.eclipse.cjsidl.state)state).getTransitions();
				}
				for(transition trans: translist){
					if(trans.getName().equals(key)){
						target.setSecondaryTransition(trans);
						break;
					}
				}
			} 
		}
	}

}
