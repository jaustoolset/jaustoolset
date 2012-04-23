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

import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.internalTransition;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;
import org.jts.jsidl.binding.Internal;
import org.jts.jsidl.binding.Simple;

/**
 * @author cmessmer
 *
 */
public class InternalTransition extends ConversionUtil{

	/**
	 * Converts a CJSIDL internal transition to a JSIDL internal transition
	 * @param comment - the only data is a comment
	 * @return - resulting JSIDL object
	 */
	public static org.jts.jsidl.binding.Internal convert(String comment){
		org.jts.jsidl.binding.Internal tmptrans = new org.jts.jsidl.binding.Internal();
		tmptrans.setInterpretation(CJSIDLCommentToJSIDLInterp(comment));
		return tmptrans;
	}

	/**
	 * Converts a JSIDL internal to a CJSIDL object
	 * @param internal - JSIDL transition 
	 * @return - resulting CJSIDL internal transition
	 */
	public static internalTransition convert(Internal internal) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		internalTransition output = factory.createinternalTransition();
		
		output.setComment(JSIDLInterpToCJSIDLComment(internal.getInterpretation()));

		return output;
	}

	public static EObject convert(Simple simple) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		internalTransition output = factory.createinternalTransition();
		
		output.setComment(JSIDLInterpToCJSIDLComment(simple.getInterpretation()));

		return output;
	}
}
