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

import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;
import org.jts.jsidl.binding.Push;

/**
 * @author cmessmer
 *
 */
public class PushTransition extends ConversionUtil{

	/**
	 * Converts a CJSIDL push transition to JSIDL push
	 * @param destination - the destination for a push transition
	 * @return - resulting JSIDL object
	 */
	public static Push convert(org.jts.eclipse.cjsidl.pushTransition destination) {
		org.jts.jsidl.binding.Push tmptrans = new org.jts.jsidl.binding.Push();
		tmptrans.setInterpretation(CJSIDLCommentToJSIDLInterp(destination.getComment()));
		tmptrans.setEndState(EndState.convert(destination.getNextState()));
		if(destination.getSimpleTransition() != null){
			tmptrans.setSimple(SimpleTransition.convert(destination.getSimpleTransition()));
		}
		
		return tmptrans;
	}
	/**
	 * Converts a JSIDL push to a CJSIDL push transition
	 * @param push - a JSIDL push
	 * @return - resulting CJSIDL push transition
	 * @throws ConversionException 
	 */
	public static org.jts.eclipse.cjsidl.pushTransition convert(Push push) throws ConversionException {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.pushTransition output = factory.createpushTransition();
		output.setComment(JSIDLInterpToCJSIDLComment(push.getInterpretation()));
		
		if(push.getSimple() != null){
			output.setSimpleTransition(SimpleTransition.convert(push.getSimple()));
		}
		output.setNextState(EndState.convert(push.getEndState()));

		return output;
	}

}
