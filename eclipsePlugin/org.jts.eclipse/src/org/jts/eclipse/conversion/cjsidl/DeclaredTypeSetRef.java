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

import org.eclipse.emf.ecore.resource.Resource;
import org.jts.eclipse.cjsidl.declaredTypeSet;
import org.jts.eclipse.cjsidl.declaredTypeSetRef;
import org.jts.eclipse.cjsidl.jaus;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class DeclaredTypeSetRef {

	/**
	 * Converts a JSIDL type set reference to a CJSIDL object
	 * @param tmpref - the type set reference to convert
	 * @return - the resulting CJSIDL object
	 */
	public static declaredTypeSetRef convert(
			org.jts.jsidl.binding.DeclaredTypeSetRef tmpref) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();		
		org.jts.eclipse.cjsidl.declaredTypeSetRef output = factory.createdeclaredTypeSetRef();
		
		// not supported in JSIDL
		//output.setComment(value)
		

		String id = tmpref.getId().replace(":", ".");		
		output.setName(tmpref.getName());
		Resource resource = Conversion.referenceHelper.getResource(id);
		jaus tmpjaus = (jaus) resource.getContents().get(0);
		output.setImportedNamespace((declaredTypeSet) tmpjaus.getSet());
		
		return output;
	}

	/**
	 * Converts a CJSIDL type set reference to a JSIDL type set reference
	 * @param tmpref - the input CJSIDL type set ref
	 * @return - the resulting JSIDL object
	 */
	public static org.jts.jsidl.binding.DeclaredTypeSetRef convert(
			declaredTypeSetRef tmpref) {
		org.jts.jsidl.binding.DeclaredTypeSetRef result = new org.jts.jsidl.binding.DeclaredTypeSetRef();
		
		if(tmpref.getImportedNamespace().getName() != null){
			String id = tmpref.getImportedNamespace().getName().replace(".", ":");
			
			result.setId(id);
		}
		
		result.setName(tmpref.getName());
		result.setVersion(tmpref.getImportedNamespace().getVersion());
		
		return result;
	}

}
