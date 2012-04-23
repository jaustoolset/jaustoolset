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

public class DeclaredServiceSet 
{
	/**
	 * Validates a JSIDL Service Set.
	 * <pre>
		  element service_set {
		  		# TODO: 1. Must have at least one element in at least one declared list
		    attribute name { identifier },
				# 1. Must follow ANSI C syntax      
		    attribute id { xsd:anyURI },
		    attribute version { version_num },
		    	# 1. Must follow JSIDL version syntax
		    description,
		    declared_const_set*,
		    declared_type_set*,
		    declared_service_set_ref*,
		    declared_service_def*,
		    service_def*
		  }
	 * </pre>
	 * @param sRef JSIDL binding object of the DeclaredServiceSetRef to be checked
	 * @throws ValidatorException if the reference has an invalid name string
	 */
	/*public static void validate(org.jts.jsidl.binding.DeclaredServiceSetRef sRef) throws ValidatorException
	{
		Validator.validateName(sRef.getName());
		Validator.validateVersion(sRef.getVersion());
	}*/	
}
