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

import org.jts.eclipse.cjsidl.simpleTransition;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;
import org.jts.jsidl.binding.Simple;

/**
 * @author cmessmer
 *
 */
public class SimpleTransition extends ConversionUtil{

	/**
	 * Converts CJSIDL simple transition to JSIDL simple
	 * @param destination - CJSIDL simple transition
	 * @return - resulting JSIDL object
	 */
	public static Simple convert(simpleTransition destination) {
		org.jts.jsidl.binding.Simple tmptrans = new org.jts.jsidl.binding.Simple();
		tmptrans.setInterpretation(CJSIDLCommentToJSIDLInterp(destination.getComment()));
		tmptrans.setEndState(EndState.convert(destination.getNextState()));
		
		return tmptrans;
	}

	/**
	 * Converts JSIDL simple to CJSIDL simple transition
	 * @param simple - JSIDL simple
	 * @return - resulting simple transtion
	 * @throws ConversionException 
	 */
	public static simpleTransition convert(Simple simple) throws ConversionException {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		simpleTransition output = factory.createsimpleTransition();
		
		output.setComment(JSIDLInterpToCJSIDLComment(simple.getInterpretation()));
		if(simple.getEndState() == null || simple.getEndState().getState() == null){
			throw new ConversionException("Missing end state found in simple transition.  " +
					"Ensure that the JSIDL is version 1.1 compliant and try again.");
		}
		output.setNextState(EndState.convert(simple.getEndState()));
		
		return output;
	}

}
