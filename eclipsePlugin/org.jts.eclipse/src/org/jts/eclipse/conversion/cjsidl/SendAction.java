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
import org.jts.eclipse.cjsidl.action;
import org.jts.eclipse.cjsidl.guardParam;
import org.jts.eclipse.cjsidl.transition;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class SendAction extends ConversionUtil{

	/**
	 * Converts a CJSIDL action to a JSIDL action
	 * @param input - cjsidl object
	 * @return - resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.SendAction convert(org.jts.eclipse.cjsidl.action input)
	{
		org.jts.jsidl.binding.SendAction output = new org.jts.jsidl.binding.SendAction();
		output.setInterpretation(CJSIDLCommentToJSIDLInterp(input.getComment()));
		output.setName(input.getName());
		java.util.List<org.jts.jsidl.binding.Argument> args = output.getArgument();
		EList<org.jts.eclipse.cjsidl.guardParam> params = input.getParam();
		for(org.jts.eclipse.cjsidl.guardParam param: params){
			org.jts.jsidl.binding.Argument newarg = new org.jts.jsidl.binding.Argument();
			if(param.getGuardConst() != null){
				// we allow double or single quotes in CJSIDL but only single in JSIDL
				newarg.setValue(param.getGuardConst().replace("\"", "'"));
			} else if(param.getParameter() != null){
				newarg.setValue(param.getParameter().getName());
			}
			newarg.setInterpretation(CJSIDLCommentToJSIDLInterp(param.getParameter().getComment()));
			args.add(newarg);
		}
		return output;
	}
	/**
	 * Converts a JSIDL data binding to a CJSIDL object
	 * @param input - JSIDL binding
	 * @param parentTrans - a CJSIDL object representing the parent transition for this action
	 * @return - resulting CJSIDL action
	 */
	public static org.jts.eclipse.cjsidl.action convert(org.jts.jsidl.binding.SendAction input, transition parentTrans)
	{
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.action output = factory.createaction();

		output.setComment(JSIDLInterpToCJSIDLComment(input.getInterpretation()));
		output.setName(input.getName());
		
		EList<org.jts.eclipse.cjsidl.guardParam> params = output.getParam();
		
		java.util.List<org.jts.jsidl.binding.Argument> args = input.getArgument();
		for(org.jts.jsidl.binding.Argument arg: args){
			if(parentTrans.getParams() != null){
				EList<org.jts.eclipse.cjsidl.transParam> parentparams = parentTrans.getParams().getParams();
				guardParam tmpgp = factory.createguardParam();
				if(arg.getValue().contains("'")){
					// assume its a constant
					tmpgp.setGuardConst(arg.getValue().trim());
				} else{
					for(org.jts.eclipse.cjsidl.transParam tmpparam: parentparams){
						if(tmpparam.getName().equals(arg.getValue())){
							tmpgp.setParameter(tmpparam);
						} else  {
			                Logger.getLogger("CJSIDL").log(Level.SEVERE,
	                        "Unexpected parameter value found in action.");
						}
						
					}	
				}
				params.add(tmpgp);					
			}
		}		
		return output;
	}
	/**
	 * Converts entry and exit actions to a CJSIDL action. Don't need access to 
	 * transition parameters.
	 * @param tmpobj - input JSIDL action
	 * @return - resulting CJSIDL action
	 */
	public static action convert(org.jts.jsidl.binding.SendAction tmpobj) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.action output = factory.createaction();
		output.setComment(JSIDLInterpToCJSIDLComment(tmpobj.getInterpretation()));

		output.setName(tmpobj.getName());
		
		return output;	
	}


}
