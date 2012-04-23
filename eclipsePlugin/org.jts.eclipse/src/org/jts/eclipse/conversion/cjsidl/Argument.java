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

import org.jts.eclipse.cjsidl.guardParam;

public class Argument extends ConversionUtil{

	/**
	 * Converts a string into a JSIDL argument
	 * @param ex - the string to convert
	 * @return - resulting JSIDL argument
	 */
	public static org.jts.jsidl.binding.Argument convert(String ex) {
		org.jts.jsidl.binding.Argument output = new org.jts.jsidl.binding.Argument();
		
		//output.setInterpretation(value)
		output.setValue(ex);
		
		return output;
	}
	

	/**
	 * converts a CJSIDL guardParam into a JSIDL argument
	 * @param param - input guard parameter
	 * @return - resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.Argument convertFromParam(guardParam param) {
		org.jts.jsidl.binding.Argument arg = new org.jts.jsidl.binding.Argument();
		
		arg.setInterpretation(CJSIDLCommentToJSIDLInterp(param.getParameter().getComment()));
		if(param.getParameter().getName() != null && !param.getParameter().getName().isEmpty() && 
				param.getParameter().getType() != null ){
			arg.setValue( param.getParameter().getName());
		}
		
		return arg;
	}

}
