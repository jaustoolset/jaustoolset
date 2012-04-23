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

import org.eclipse.emf.common.util.EList;
import org.jts.eclipse.cjsidl.bitfieldDef;
import org.jts.eclipse.cjsidl.subField;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

public class BitField extends ConversionUtil{

	/**
	 * Converts a CJSIDL object into a JSIDL data binding
	 * @param bitfieldDef - input CJSIDL object
	 * @return - resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.BitField convert(
			bitfieldDef bitfieldDef) {
		org.jts.jsidl.binding.BitField newfield = new org.jts.jsidl.binding.BitField();
		// set simple fields
		newfield.setFieldTypeUnsigned(CJSIDLTypeToJSIDLType(bitfieldDef.getType()));
		newfield.setInterpretation(CJSIDLCommentToJSIDLInterp(bitfieldDef.getComment()));
		newfield.setName(bitfieldDef.getName());
		
		EList<subField> subfields = bitfieldDef.getSubField();
		java.util.List<org.jts.jsidl.binding.SubField> newsubfields = newfield.getSubField();
		for(subField sub: subfields)
		{
			org.jts.jsidl.binding.SubField newsub = SubField.convert(sub);		
			newsubfields.add(newsub);
		}
		
		
		// if one was specified then set it to whatever it was. default is "required"
		if(bitfieldDef.getOptional() != null){
			newfield.setOptional(bitfieldDef.getOptional().equals(OPTIONAL));
		}

		
		return newfield;
	}

	/**
	 * Converts a JSIDL object into a CJSIDL data binding
	 * @param bitfield - input JSIDL binding
	 * @return - resulting CJSIDL data object
	 */
	public static bitfieldDef convert(
			org.jts.jsidl.binding.BitField bitfield) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.bitfieldDef bf = factory.createbitfieldDef();
		
		bf.setName(bitfield.getName());
		bf.setComment(JSIDLInterpToCJSIDLComment(bitfield.getInterpretation()));
		if(bitfield.isOptional()){
			bf.setOptional(OPTIONAL);
		}
		bf.setType(JSIDLTypeToCJSIDLType(bitfield.getFieldTypeUnsigned()));
		
		EList<subField> newfields = bf.getSubField();
		
		java.util.List<org.jts.jsidl.binding.SubField> fields = bitfield.getSubField();
		for(org.jts.jsidl.binding.SubField sub: fields){
			subField newsub = SubField.convert(sub);
			newfields.add(newsub);		
		}
		return bf;
	}

}
