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
import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.arrayDef;
import org.jts.eclipse.cjsidl.bitfieldDef;
import org.jts.eclipse.cjsidl.fixedFieldDef;
import org.jts.eclipse.cjsidl.fixedLenString;
import org.jts.eclipse.cjsidl.recordDef;
import org.jts.eclipse.cjsidl.scopedTypeId;
import org.jts.eclipse.cjsidl.typeReference;
import org.jts.eclipse.cjsidl.varField;
import org.jts.eclipse.cjsidl.varFormatField;
import org.jts.eclipse.cjsidl.varLenField;
import org.jts.eclipse.cjsidl.varLenString;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class Record extends ConversionUtil{

	/**
	 * Convert CJSIDL rec to a JSIDL rec
	 * @param recordDef - CJSIDL record
	 * @return - resulting JSIDL record
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.Record convert(recordDef recordDef) throws ConversionException {
		org.jts.jsidl.binding.Record newrec = new org.jts.jsidl.binding.Record();
		newrec.setInterpretation(CJSIDLCommentToJSIDLInterp(recordDef.getComment()));
		newrec.setName(recordDef.getName());

		// if one was specified then set it to whatever it was. default is "required"
		if(recordDef.getOptional() != null){
			newrec.setOptional(recordDef.getOptional().equals(OPTIONAL));
		}
		
		java.util.List<Object> recContent = newrec.getArrayOrFixedFieldOrVariableField();
		int elementcount = 0;
		boolean optional = false;
		
		EList<bitfieldDef> bfs = recordDef.getBitfieldDef();
		elementcount += bfs.size();
		for(bitfieldDef bf: bfs){
			if(bf.getOptional() != null)
			{
				optional = true;
			}
			recContent.add((Object)BitField.convert(bf));
		}
		
		EList<fixedFieldDef> fs = recordDef.getFieldDef();
		elementcount += fs.size();
		for(fixedFieldDef f: fs){
			if(f.getOptional() != null)
			{
				optional = true;
			}
			recContent.add((Object)FixedField.convert(f)); 
		}
		EList<varFormatField> vffs = recordDef.getVarFormatField();
		elementcount += vffs.size();
		for(varFormatField vff: vffs){
			if(vff.getOptional() != null)
			{
				optional = true;
			}
			recContent.add((Object)VariableFormatField.convert(vff));
		}
		EList<varField> vfs = recordDef.getVariableFieldDef();
		elementcount += vfs.size();
		for(varField vf: vfs){
			if(vf.getOptional() != null)
			{
				optional = true;
			}
			recContent.add((Object)VariableField.convert(vf));
		}
		
		EList<fixedLenString> flss = recordDef.getFixedLengthStringDef();
		elementcount += flss.size();
		for(fixedLenString fls: flss){
			if(fls.getOptional() != null)
			{
				optional = true;
			}
			recContent.add((Object)FixedLengthString.convert(fls));
		}
		EList<arrayDef> arrays = recordDef.getArrayDef();
		elementcount += arrays.size();
		for(arrayDef arr: arrays){
			if(arr.getOptional() != null)
			{
				optional = true;
			}
			recContent.add((Object)Array.convert(arr));
		}
		EList<varLenString> vlss = recordDef.getVariableLengthStringDef();
		elementcount += vlss.size();
		for(varLenString vls: vlss){
//			if(vlss. != null)
//			{
//				optional = true;
//			}
			recContent.add((Object)VariableLengthString.convert(vls));			
		}
		EList<varLenField> vs = recordDef.getVariableLengthFieldDef();
		elementcount += vs.size();
		for(varLenField v: vs){
			if(v.getOptional() != null)
			{
				optional = true;
			}
			recContent.add((Object)VariableLengthField.convert(v));
		}
		
		EList<typeReference> refs = recordDef.getTypeReference();
		elementcount += refs.size();
		for(typeReference ref: refs){
			if(ref.getType() instanceof bitfieldDef){
				recContent.add((Object)DeclaredBitField.convert(ref));
			} else if(ref.getType() instanceof fixedFieldDef){
				recContent.add((Object)DeclaredFixedField.convert(ref));
			} else if(ref.getType() instanceof varFormatField){
				recContent.add((Object)DeclaredVariableFormatField.convert(ref));
			} else if(ref.getType() instanceof varField){
				recContent.add((Object)DeclaredVariableField.convert(ref));				
			} else if(ref.getType() instanceof fixedLenString){
				recContent.add((Object)DeclaredFixedLengthString.convert(ref));
			} else if(ref.getType() instanceof arrayDef){
				recContent.add((Object)DeclaredArray.convert(ref));
			} else if(ref.getType() instanceof varLenString){
				recContent.add((Object)DeclaredVariableLengthString.convert(ref));
			} else if(ref.getType() instanceof varLenField){				
				recContent.add((Object)DeclaredVariableLengthField.convert(ref));
			}
		}
		EList<scopedTypeId> scopedRefs = recordDef.getScopedRef();
		elementcount += scopedRefs.size();
		for(scopedTypeId ref: scopedRefs){
			if(ref.getRef().getType() instanceof bitfieldDef){
				recContent.add((Object)DeclaredBitField.convert(ref));
			} else if(ref.getRef().getType() instanceof fixedFieldDef){
				recContent.add((Object)DeclaredFixedField.convert(ref));
			} else if(ref.getRef().getType() instanceof varFormatField){
				recContent.add((Object)DeclaredVariableFormatField.convert(ref));
			} else if(ref.getRef().getType() instanceof varField){
				recContent.add((Object)DeclaredVariableField.convert(ref));				
			} else if(ref.getRef().getType() instanceof fixedLenString){
				recContent.add((Object)DeclaredFixedLengthString.convert(ref));
			} else if(ref.getRef().getType() instanceof arrayDef){
				recContent.add((Object)DeclaredArray.convert(ref));
			} else if(ref.getRef().getType() instanceof varLenString){
				recContent.add((Object)DeclaredVariableLengthString.convert(ref));
			} else if(ref.getRef().getType() instanceof varLenField){				
				recContent.add((Object)DeclaredVariableLengthField.convert(ref));
			}
		}
		
		// set the presence vector type, if there are optional fields.
		if(optional)
		{
			org.jts.jsidl.binding.PresenceVector vec = new org.jts.jsidl.binding.PresenceVector();
			vec.setFieldTypeUnsigned(rangeToJSIDLType(0, elementcount));
			newrec.setPresenceVector(vec);
		}
		return newrec;
	}

	/**
	 * Converts a JSIDL record to a CJSIDL record
	 * @param tmpdef - JSIDL record
	 * @return - resulting CJSIDL record
	 */
	public static recordDef convert(org.jts.jsidl.binding.Record tmpdef, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.recordDef newrec = factory.createrecordDef();

		newrec.setName(tmpdef.getName());
		newrec.setComment(JSIDLInterpToCJSIDLComment(tmpdef.getInterpretation()));
		if(tmpdef.isOptional()){
			newrec.setOptional(OPTIONAL);
		}
		
		
		EList<arrayDef> arrays = newrec.getArrayDef();
		EList<bitfieldDef> bfs = newrec.getBitfieldDef();
		EList<fixedFieldDef> ffs = newrec.getFieldDef();
		EList<fixedLenString> flss = newrec.getFixedLengthStringDef();
		EList<varField> vfs = newrec.getVariableFieldDef();
		EList<varLenString> vlss = newrec.getVariableLengthStringDef();
		EList<varLenField> vs = newrec.getVariableLengthFieldDef();
		EList<varFormatField> vffs = newrec.getVarFormatField();
		EList<typeReference> typerefs = newrec.getTypeReference();
		EList<scopedTypeId> scopedrefs = newrec.getScopedRef();
		
		java.util.List<Object> fieldList = tmpdef.getArrayOrFixedFieldOrVariableField();
		for(Object tmpobj: fieldList){
			if(tmpobj instanceof org.jts.jsidl.binding.Array){
				arrays.add(Array.convert((org.jts.jsidl.binding.Array)tmpobj, root));
			} else if(tmpobj instanceof org.jts.jsidl.binding.FixedField){
				ffs.add(FixedField.convert((org.jts.jsidl.binding.FixedField)tmpobj));
			} else if(tmpobj instanceof org.jts.jsidl.binding.VariableField){
				vfs.add(VariableField.convert((org.jts.jsidl.binding.VariableField)tmpobj));
			} else if(tmpobj instanceof org.jts.jsidl.binding.BitField){
				bfs.add(BitField.convert((org.jts.jsidl.binding.BitField)tmpobj));
			} else if(tmpobj instanceof org.jts.jsidl.binding.FixedLengthString){
				flss.add(FixedLengthString.convert((org.jts.jsidl.binding.FixedLengthString)tmpobj));
			} else if(tmpobj instanceof org.jts.jsidl.binding.VariableLengthString){
				vlss.add(VariableLengthString.convert((org.jts.jsidl.binding.VariableLengthString)tmpobj));
			} else if(tmpobj instanceof org.jts.jsidl.binding.VariableLengthField){
				vs.add(VariableLengthField.convert((org.jts.jsidl.binding.VariableLengthField)tmpobj));
			} else if(tmpobj instanceof org.jts.jsidl.binding.VariableFormatField){
				vffs.add(VariableFormatField.convert((org.jts.jsidl.binding.VariableFormatField)tmpobj));
			
			} // references need a bit more processing and come in two flavors
			else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredArray){
				EObject tmpref =DeclaredArray.convertToRef((org.jts.jsidl.binding.DeclaredArray)tmpobj, root);
				if(tmpref instanceof typeReference){
					typerefs.add((typeReference) tmpref);
				} else{
					scopedrefs.add((scopedTypeId) tmpref);
				}
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredFixedField){
				EObject tmpref =DeclaredFixedField.convertToRef((org.jts.jsidl.binding.DeclaredFixedField)tmpobj, root);
				if(tmpref instanceof typeReference){
					typerefs.add((typeReference) tmpref);
				} else{
					scopedrefs.add((scopedTypeId) tmpref);
				}
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredVariableField){
				EObject tmpref =(DeclaredVariableField.convertToRef((org.jts.jsidl.binding.DeclaredVariableField)tmpobj, root));
				if(tmpref instanceof typeReference){
					typerefs.add((typeReference) tmpref);
				} else{
					scopedrefs.add((scopedTypeId) tmpref);
				}
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredFixedLengthString){
				EObject tmpref =(DeclaredFixedLengthString.convertToRef((org.jts.jsidl.binding.DeclaredFixedLengthString)tmpobj, root));
				if(tmpref instanceof typeReference){
					typerefs.add((typeReference) tmpref);
				} else{
					scopedrefs.add((scopedTypeId) tmpref);
				}
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredBitField){
				EObject tmpref =(DeclaredBitField.convertToRef((org.jts.jsidl.binding.DeclaredBitField)tmpobj, root));
				if(tmpref instanceof typeReference){
					typerefs.add((typeReference) tmpref);
				} else{
					scopedrefs.add((scopedTypeId) tmpref);
				}
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredVariableLengthString){
				EObject tmpref =(DeclaredVariableLengthString.convertToRef((org.jts.jsidl.binding.DeclaredVariableLengthString)tmpobj, root));
				if(tmpref instanceof typeReference){
					typerefs.add((typeReference) tmpref);
				} else{
					scopedrefs.add((scopedTypeId) tmpref);
				}
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredVariableLengthField){
				EObject tmpref =(DeclaredVariableLengthField.convertToRef((org.jts.jsidl.binding.DeclaredVariableLengthField)tmpobj, root));
				if(tmpref instanceof typeReference){
					typerefs.add((typeReference) tmpref);
				} else{
					scopedrefs.add((scopedTypeId) tmpref);
				}
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredVariableFormatField){
				EObject tmpref =(DeclaredVariableFormatField.convertToRef((org.jts.jsidl.binding.DeclaredVariableFormatField)tmpobj, root));
				if(tmpref instanceof typeReference){
					typerefs.add((typeReference) tmpref);
				} else{
					scopedrefs.add((scopedTypeId) tmpref);
				}
			}
		}
		
		return newrec;
	}

}
