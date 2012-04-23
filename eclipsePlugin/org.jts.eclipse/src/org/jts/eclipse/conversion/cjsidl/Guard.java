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

import org.eclipse.emf.common.util.EList;
import org.jts.eclipse.cjsidl.guard;
import org.jts.eclipse.cjsidl.guardAction;
import org.jts.eclipse.cjsidl.transition;

/**
 * @author cmessmer
 *
 */
public class Guard extends ConversionUtil{

	/**
	 * Converts a CJSIDL guard to a JSIDL guard
	 * @param inputguard - input CJSIDL guard
	 * @return - resulting JSIDL guard
	 */
	public static org.jts.jsidl.binding.Guard convert(guard inputguard) {
		org.jts.jsidl.binding.Guard newGuard = new org.jts.jsidl.binding.Guard();
		String expr = "";
		
		String equiv = inputguard.getEquiv();
		EList<guardAction> actions = inputguard.getGuardAction();
		EList<String> operators = inputguard.getLogicalOperator();

		// build the guard expression from the object data
		if( actions.size() == 1 ){
			expr = Action.convert(actions.get(0));
		} else if(equiv != null && actions != null && actions.size() == 2){
			expr = Action.convert(actions.get(0)) + " " + equiv + " " + Action.convert(actions.get(1));
		} else if(actions != null) {
			if(operators != null && operators.size() == actions.size()-1){
				for(int i=0; i< actions.size(); i++){
					if(i==0){
						expr = Action.convert(actions.get(0));
					} else {
						expr = expr + " " + operators.get(i-1) + " " + Action.convert(actions.get(i));
					}
					
				}
			} else {
	            Logger.getLogger("CJSIDL").log(Level.SEVERE,
	            "Unexpected guard structure for : " + inputguard);
			}
		}
		newGuard.setCondition(expr);
		
		if(inputguard.getComment() != null)
		{
		    newGuard.setInterpretation(CJSIDLCommentToJSIDLInterp(inputguard.getComment()));
		}
		
		return newGuard;
	}

	/**
	 * Converts a JSIDL guard to a CJSIDL guard.  This is more complex than normal so its
	 * done in the GuardExpressionParser class.
	 * @param guard - the input JSIDL guard
	 * @param parentTrans - the parent transition for the new CJSIDL guard
	 * @return- resulting CJSIDL guard
	 */
	public static guard convert(org.jts.jsidl.binding.Guard guard, transition parentTrans) {
		org.jts.eclipse.cjsidl.guard output = null;
		// this is done internal to the expression parser
//		output.setComment(JSIDLInterpToCJSIDLComment(guard.getInterpretation()));
		GuardExpressionParser parser = new GuardExpressionParser();
		output = parser.parse(guard, parentTrans);

		return output;
	}




}
