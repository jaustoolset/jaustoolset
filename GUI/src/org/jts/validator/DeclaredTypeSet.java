/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
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

package org.jts.validator;

import java.util.List;

import org.jts.jsidl.binding.DeclaredConstSetRef;
import org.jts.jsidl.binding.DeclaredTypeSetRef;

public class DeclaredTypeSet
{
	/**
	 * Validates a JSIDL DeclaredTypeSet.
	 * <pre>
		declared_type_set = 
		  element declared_type_set {
		  		# 1. Must have at least one element in at least one declared list
		    attribute name { identifier },
		    	# 1. Must follow ANSI C syntax 
		    ( attribute id { xsd:anyURI },
		      attribute version { version_num } )?,
		      	# 1. Must follow JSIDL version syntax
		      declared_const_set_ref*,
		      	# 1. All names in list must be unique 
		      declared_type_set_ref*,
		      	# 1. All names in list must be unique 
		    ( message_def | header | body | footer | 
		      array | record | \list | variant| sequence | 
		      fixed_field | variable_field | bit_field |fixed_length_string | 
		      variable_length_string | variable_length_field |variable_format_field | 
		      declared_message_def | declared_event_def | declared_header | declared_body | declared_footer | 
		      declared_list | declared_sequence | declared_variant | declared_record | declared_array | 
		      declared_fixed_field | declared_variable_field | declared_bit_field | declared_fixed_length_string | 
		      declared_variable_length_string | declared_variable_length_field | declared_variable_format_field )*
		      	# 1. All names in list must be unique 
		  }
	 * </pre>
	 * @param constTypeRef JSIDL binding object of the DeclaredTypeSet to be checked. 
	 * @throws ValidatorException	if the object has an invalid name string, version string or repeated names in 
	 * 								any of its contained lists. It also checks to ensure that at least one of the
	 * 								lists has a non-zero size.
	 */
	public static void validate(org.jts.jsidl.binding.DeclaredTypeSet constTypeRef) throws ValidatorException
	{
		Validator.validateName(constTypeRef.getName());
		Validator.validateVersion(constTypeRef.getVersion());
		
		List<DeclaredConstSetRef> constRefList = constTypeRef.getDeclaredConstSetRef();
		Validator.validateUniqueNameList(constRefList);
		
		List<DeclaredTypeSetRef> typeRefList = constTypeRef.getDeclaredTypeSetRef();
		Validator.validateUniqueNameList(typeRefList);

		List<Object> objectList = constTypeRef.getMessageDefOrHeaderOrBody();
		Validator.validateUniqueNameList(objectList);
		
		if(constRefList.size() == 0 && typeRefList.size() == 0 && objectList.size() == 0)
		{
			throw new ValidatorException("Type set contains no definitions or references");
		}		
	}
}
