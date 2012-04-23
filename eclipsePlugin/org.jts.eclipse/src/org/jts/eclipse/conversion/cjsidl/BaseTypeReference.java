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

import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.arrayDef;
import org.jts.eclipse.cjsidl.bitfieldDef;
import org.jts.eclipse.cjsidl.bodyDef;
import org.jts.eclipse.cjsidl.bodyRef;
import org.jts.eclipse.cjsidl.bodyScopedRef;
import org.jts.eclipse.cjsidl.declaredConstSetRef;
import org.jts.eclipse.cjsidl.declaredTypeSetRef;
import org.jts.eclipse.cjsidl.eventDef;
import org.jts.eclipse.cjsidl.fixedFieldDef;
import org.jts.eclipse.cjsidl.fixedLenString;
import org.jts.eclipse.cjsidl.footerDef;
import org.jts.eclipse.cjsidl.footerRef;
import org.jts.eclipse.cjsidl.footerScopedRef;
import org.jts.eclipse.cjsidl.headerDef;
import org.jts.eclipse.cjsidl.headerRef;
import org.jts.eclipse.cjsidl.headerScopedRef;
import org.jts.eclipse.cjsidl.listDef;
import org.jts.eclipse.cjsidl.messageDef;
import org.jts.eclipse.cjsidl.messageScopedRef;
import org.jts.eclipse.cjsidl.recordDef;
import org.jts.eclipse.cjsidl.refAttr;
import org.jts.eclipse.cjsidl.scopedEventType;
import org.jts.eclipse.cjsidl.scopedType;
import org.jts.eclipse.cjsidl.sequenceDef;
import org.jts.eclipse.cjsidl.varField;
import org.jts.eclipse.cjsidl.varFormatField;
import org.jts.eclipse.cjsidl.varLenField;
import org.jts.eclipse.cjsidl.varLenString;
import org.jts.eclipse.cjsidl.variantDef;
public class BaseTypeReference extends ConversionUtil {

	/**
	 * Takes a scoped reference for a header and converts it to a JSIDL string
	 * @param ref - the input CJSIDL binding
	 * @return - a scoped reference as a string
	 */
	public static String ScopedTypeFromRef(headerScopedRef ref) {
		String type = "";
		
		if(ref.getName() instanceof declaredTypeSetRef){
			type = ((declaredTypeSetRef)ref.getName()).getName();			
		} else if(ref.getName() instanceof declaredConstSetRef){
			type = ((declaredConstSetRef)ref.getName()).getName();			
		} else if(ref.getName() instanceof refAttr){
			type = ((refAttr)ref.getName()).getName();			
		}
		for(EObject tmpobj: ref.getNames()){
			if(tmpobj instanceof declaredTypeSetRef){
				type += "." + ((declaredTypeSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof declaredConstSetRef){
				type += "." + ((declaredConstSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof refAttr){
				type += "." + ((refAttr)tmpobj).getName();			
			}
		}
		type += "." + getNameFromEObject(ref.getType());
		
		return type;
	}

	/**
	 * Takes a scoped reference for a footer and converts it to a JSIDL string
	 * @param ref - the input CJSIDL binding
	 * @return - a scoped reference as a string
	 */
	public static String ScopedTypeFromRef(footerScopedRef ref) {
		String type = "";
		
		if(ref.getName() instanceof declaredTypeSetRef){
			type = ((declaredTypeSetRef)ref.getName()).getName();			
		} else if(ref.getName() instanceof declaredConstSetRef){
			type = ((declaredConstSetRef)ref.getName()).getName();			
		} else if(ref.getName() instanceof refAttr){
			type = ((refAttr)ref.getName()).getName();			
		}
		for(EObject tmpobj: ref.getNames()){
			if(tmpobj instanceof declaredTypeSetRef){
				type += "." + ((declaredTypeSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof declaredConstSetRef){
				type += "." + ((declaredConstSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof refAttr){
				type += "." + ((refAttr)tmpobj).getName();			
			}
		}
		type += "." + getNameFromEObject(ref.getType());
		
		return type;
	}
	/**
	 * Takes a scoped reference for a body and converts it to a JSIDL string
	 * @param ref - the input CJSIDL binding
	 * @return - a scoped reference as a string
	 */
	public static String ScopedTypeFromRef(bodyScopedRef ref) {
		String type = "";
		
		if(ref.getName() instanceof declaredTypeSetRef){
			type = ((declaredTypeSetRef)ref.getName()).getName();			
		} else if(ref.getName() instanceof declaredConstSetRef){
			type = ((declaredConstSetRef)ref.getName()).getName();			
		} else if(ref.getName() instanceof refAttr){
			type = ((refAttr)ref.getName()).getName();			
		}
		for(EObject tmpobj: ref.getNames()){
			if(tmpobj instanceof declaredTypeSetRef){
				type += "." + ((declaredTypeSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof declaredConstSetRef){
				type += "." + ((declaredConstSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof refAttr){
				type += "." + ((refAttr)tmpobj).getName();			
			}
		}
		type += "." + getNameFromEObject(ref.getType());
		
		return type;
	}

	/**
	 * Takes a scoped reference for a generic scopedType and converts it to a JSIDL string
	 * @param ref - the input CJSIDL binding
	 * @return - a scoped reference as a string
	 */
	public static String ScopedTypeFromRef(scopedType ref) {
		String type = "";
		
		if(ref.getName() instanceof declaredTypeSetRef){
			type = ((declaredTypeSetRef)ref.getName()).getName();			
		} else if(ref.getName() instanceof declaredConstSetRef){
			type = ((declaredConstSetRef)ref.getName()).getName();			
		} else if(ref.getName() instanceof refAttr){
			type = ((refAttr)ref.getName()).getName();			
		}
		for(EObject tmpobj: ref.getNames()){
			if(tmpobj instanceof declaredTypeSetRef){
				type += "." + ((declaredTypeSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof declaredConstSetRef){
				type += "." + ((declaredConstSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof refAttr){
				type += "." + ((refAttr)tmpobj).getName();			
			}
		}
		type += "." + getNameFromEObject(ref.getType());
		
		return type;
	}
	/**
	 * Takes a scoped reference for an event and converts it to a JSIDL string
	 * @param ref - the input CJSIDL binding
	 * @return - a scoped reference as a string
	 * @throws ConversionException 
	 */
	public static String ScopedTypeFromRef(scopedEventType ref) throws ConversionException {
		String type = "";
		if(ref.getName() instanceof declaredTypeSetRef){
			type = ((declaredTypeSetRef)ref.getName()).getName();			
		} else if(ref.getName() instanceof declaredConstSetRef){
			type = ((declaredConstSetRef)ref.getName()).getName();			
		} else if(ref.getName() instanceof refAttr){
			type = ((refAttr)ref.getName()).getName();			
		} else if(ref.getName() instanceof eventDef){
		    type = ((eventDef)ref.getName()).getName();
        } else {
            Logger.getLogger("CJSIDL").log(Level.SEVERE,
    	            "Unexpected type \"" + ref.getName() + "\" used for scoped type.");
            throw new ConversionException("Unexpected type \"" + ref.getName() + "\" used for scoped type.");
        }
		for(EObject tmpobj: ref.getNames()){
			if(tmpobj instanceof declaredTypeSetRef){
				type += "." + ((declaredTypeSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof declaredConstSetRef){
				type += "." + ((declaredConstSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof refAttr){
				type += "." + ((refAttr)tmpobj).getName();			
			} else if(tmpobj instanceof eventDef){
                type +="." + ((eventDef)tmpobj).getName();
            } else if(tmpobj instanceof headerRef){
                type +="." + ((headerRef)tmpobj).getName();
            }else if(tmpobj instanceof headerDef){
                type +="." +  ((headerDef)tmpobj).getName();
            }else if(tmpobj instanceof bodyRef){
                type +="." + ((bodyRef)tmpobj).getName();
            }else if(tmpobj instanceof bodyDef){
                type +="." + ((bodyDef)tmpobj).getName();
            }else if(tmpobj instanceof footerRef){
                type +="." + ((footerRef)tmpobj).getName();
            }else if(tmpobj instanceof footerDef){
                type +="." + ((footerDef)tmpobj).getName();
            }else if(tmpobj instanceof recordDef){
                type +="." + ((recordDef)tmpobj).getName();
            }else if(tmpobj instanceof listDef){
                type +="." + ((recordDef)tmpobj).getName();
            }else if(tmpobj instanceof sequenceDef){
                type +="." + ((recordDef)tmpobj).getName();
            }else if(tmpobj instanceof variantDef){
                type +="." + ((recordDef)tmpobj).getName();
            }else {
	            Logger.getLogger("CJSIDL").log(Level.SEVERE,
	    	            "Unexpected type \"" + tmpobj + "\" used for scoped type.");
	            throw new ConversionException("Unexpected type \"" + tmpobj + "\" used for scoped type.");
            }
		}
		type += "." + getNameFromEObject(ref.getType());
		
		return type;
	}
	/**
	 * Takes a scoped reference for an event and converts it to a JSIDL string
	 * @param ref - the input CJSIDL binding
	 * @return - a scoped reference as a string
	 */
	public static String ScopedTypeFromRef(messageScopedRef ref) {
		String type = "";
		
		if(ref.getScope() instanceof declaredTypeSetRef){
			type = ((declaredTypeSetRef)ref.getScope()).getName();			
		} else if(ref.getScope() instanceof declaredConstSetRef){
			type = ((declaredConstSetRef)ref.getScope()).getName();			
		} else if(ref.getScope() instanceof refAttr){
			type = ((refAttr)ref.getScope()).getName();			
		}
		for(EObject tmpobj: ref.getScopes()){
			if(tmpobj instanceof declaredTypeSetRef){
				type += "." + ((declaredTypeSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof declaredConstSetRef){
				type += "." + ((declaredConstSetRef)tmpobj).getName();			
			} else if(tmpobj instanceof refAttr){
				type += "." + ((refAttr)tmpobj).getName();			
			}
			
		}
		type += "." + getNameFromEObject(ref.getRef());
		
		return type;
	}
	/**
	 * Takes a generic EObject and returns the name as a string
	 * @param obj - input generic object
	 * @return - name of the object
	 */
	public static String getNameFromEObject(EObject obj){
		String result = "";
		
		if(obj instanceof arrayDef){
			result = ((arrayDef)obj).getName();
		} else if(obj instanceof bitfieldDef){
			result = ((bitfieldDef)obj).getName();
		} else if(obj instanceof fixedFieldDef){
			result = ((fixedFieldDef)obj).getName();
		} else if(obj instanceof varField){
			result = ((varField)obj).getName();
		} else if(obj instanceof fixedLenString){
			result = ((fixedLenString)obj).getName();
		}else if(obj instanceof varLenString){
			result = ((varLenString)obj).getName();
		} else if(obj instanceof varLenField){
			result = ((varLenField)obj).getName();
		} else if(obj instanceof varFormatField){
			result = ((varFormatField)obj).getName();
		} else if(obj instanceof bodyDef){
			result = ((bodyDef)obj).getName();
		} else if(obj instanceof headerDef){
			result = ((headerDef)obj).getName();
		} else if(obj instanceof footerDef){
			result = ((footerDef)obj).getName();
		} else if(obj instanceof eventDef){
			result = ((eventDef)obj).getName();
		} else if(obj instanceof listDef){
			result = ((listDef)obj).getName();
		} else if(obj instanceof messageDef){
			result = ((messageDef)obj).getName();
		} else if(obj instanceof recordDef){
			result = ((recordDef)obj).getName();
		} else if(obj instanceof sequenceDef){
			result = ((sequenceDef)obj).getName();
		} else if(obj instanceof variantDef){
			result = ((variantDef)obj).getName();
		} 
		
		return result;
	}

}
