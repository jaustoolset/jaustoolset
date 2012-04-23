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


/**
 * @author cmessmer
 *
 */
public class FixedLengthString extends ConversionUtil{

	/**
	 * Converts from JAXB data binding object to XText generated object
	 * @param tmpobj - input object
	 * @return the output Xtext object
	 */
	public static org.jts.jsidl.binding.FixedLengthString convert(org.jts.eclipse.cjsidl.fixedLenString tmpobj) {
		if(tmpobj == null || (tmpobj.getUpperLim() == null && 
				(tmpobj.getUpperLimRef() == null || tmpobj.getUpperLimRef().getConstVal().getConstType()==null)))
		{
			// log problem and throw exception
		}
		org.jts.jsidl.binding.FixedLengthString str = new org.jts.jsidl.binding.FixedLengthString();
		str.setName(tmpobj.getName());
		str.setInterpretation(CJSIDLCommentToJSIDLInterp(tmpobj.getComment()));
		

		// if one was specified then set it to whatever it was. default is "required"
		if(tmpobj.getOptional() != null){
			str.setOptional(tmpobj.getOptional().equals(OPTIONAL));
		}

		str.setOptional(false);
		String len = null;
		
		if(tmpobj.getUpperLim() != null){
			len = tmpobj.getUpperLim();
		} else if(tmpobj.getUpperLimRef() != null){
			len = tmpobj.getUpperLimRef().getConstVal().getConstValue();
		} else if(tmpobj.getUpperLimScoped() != null){
			len = tmpobj.getUpperLimScoped().getType().getConstValue();
		}

		str.setStringLength(len);		
		
		return str;
	}
	
	/**
	 * Converts from a Xtext generated object to a JAXB data binding
	 * @param tmpobj - input Xtext object
	 * @return - the output JAXB data binding
	 */
	public static org.jts.eclipse.cjsidl.fixedLenString convert(org.jts.jsidl.binding.FixedLengthString tmpobj){
		if(tmpobj == null || tmpobj.getStringLength().isEmpty())
		{
			// log problem and throw exception
		}
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		
		org.jts.eclipse.cjsidl.fixedLenString str = factory.createfixedLenString();

		
		str.setName(tmpobj.getName());
		str.setComment(JSIDLInterpToCJSIDLComment(tmpobj.getInterpretation()));
		if(tmpobj.isOptional()){
			str.setOptional(OPTIONAL);
		} 
		String len = tmpobj.getStringLength();
		str.setUpperLim(len);
		
		
		return str;
	}

}
