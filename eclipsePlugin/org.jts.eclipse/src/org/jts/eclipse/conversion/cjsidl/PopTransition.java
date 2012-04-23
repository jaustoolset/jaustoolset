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
import org.jts.eclipse.cjsidl.guardParam;
import org.jts.eclipse.cjsidl.popTransition;
import org.jts.eclipse.cjsidl.transParam;
import org.jts.eclipse.cjsidl.transition;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;
import org.jts.jsidl.binding.Pop;

/**
 * @author cmessmer
 *
 */
public class PopTransition extends ConversionUtil{

	/**
	 * Converts a CJSIDL pop transition to a JSIDL pop transition
	 * @param popTransition - a CJSIDL pop transition
	 * @return - resulting JSIDL object
	 */
	public static org.jts.jsidl.binding.Pop convert(popTransition destination) {
		org.jts.jsidl.binding.Pop tmptrans = new org.jts.jsidl.binding.Pop();
		tmptrans.setInterpretation(CJSIDLCommentToJSIDLInterp(destination.getComment()));
		if(destination.getSecondaryTransition() != null){
			tmptrans.setTransition(destination.getSecondaryTransition().getName());
		}
		if(destination.getParam() != null){
			EList<org.jts.eclipse.cjsidl.guardParam> params = destination.getParam();
			java.util.List<org.jts.jsidl.binding.Argument> args = tmptrans.getArgument();
			for(org.jts.eclipse.cjsidl.guardParam param: params){
				args.add(Argument.convertFromParam(param));
			}
		}
		return tmptrans;
	}
	/**
	 * Converts a JSIDL pop to a CJSIDL pop transition
	 * @param pop - a JSIDL pop
	 * @param parentTrans - the parent transition
	 * @return - resulting CJSIDL object
	 */
	public static popTransition convert(Pop pop, transition parentTrans) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.popTransition output = factory.createpopTransition();
		output.setComment(JSIDLInterpToCJSIDLComment(pop.getInterpretation()));

		EList<org.jts.eclipse.cjsidl.guardParam> paramlist = output.getParam();
		if(pop.getArgument() != null){
			java.util.List<org.jts.jsidl.binding.Argument> args = pop.getArgument();
			for(org.jts.jsidl.binding.Argument arg: args){
				guardParam gp = factory.createguardParam();
				if(arg.getValue().contains("'")){
					gp.setGuardConst(arg.getValue());
				} else{
					if(parentTrans != null){
						gp.setParameter((transParam) Conversion.referenceHelper.getEObjectFromTransition(arg.getValue(), parentTrans));
					} else {
		                Logger.getLogger("CJSIDL").log(Level.SEVERE,
                        "Unexpected parameter found in guard.");
					}
				}
				
				paramlist.add(gp);
			}
		}

		// need to have the reference set, but the object may not exist yet, so post-process
		String transName = pop.getTransition();
		if(transName != null && !transName.isEmpty()){
			DefaultState.secondaryTransitionMap.put(transName, output);
		}
		
		return output;
	}

}
