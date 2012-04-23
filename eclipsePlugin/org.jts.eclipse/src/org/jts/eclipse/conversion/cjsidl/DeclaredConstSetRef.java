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
import org.jts.eclipse.cjsidl.declaredConstSetRef;
import org.jts.eclipse.cjsidl.jaus;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class DeclaredConstSetRef {

	/**
	 * Converts a CJSIDL const set reference to a JSIDL object
	 * @param ref - CJSIDL constant set reference
	 * @return - the resulting JSIDL object
	 */
	public static org.jts.jsidl.binding.DeclaredConstSetRef convert(declaredConstSetRef ref) {
		org.jts.jsidl.binding.DeclaredConstSetRef newref = new org.jts.jsidl.binding.DeclaredConstSetRef();
		
		String id = ref.getImportedNamespace().getName();
		id = id.replace("'", "");
		id = id.replace("\"", "");
		id = id.replace(".", ":");
		newref.setId(id);		
		
		String version = ref.getImportedNamespace().getConstSetVersion();
		version = version.replace("'", "");
		version = version.replace("\"", "");
		newref.setVersion(version);

		newref.setName(ref.getName());
		
		return newref;
	}

	/**
	 * Converts a JSIDL data binding to a CJSIDL object
	 * @param ref - the input JSIDL data binding 
	 * @return - the resulting CJSIDL object
	 */
	public static declaredConstSetRef convert(org.jts.jsidl.binding.DeclaredConstSetRef ref) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();		
		org.jts.eclipse.cjsidl.declaredConstSetRef newref = factory.createdeclaredConstSetRef();

		//newref.setImportedNamespace((ref.getName()));	
		newref.setName( ref.getName());

		String id = ref.getId().replace(":", ".");		
		Resource resource = Conversion.referenceHelper.getResource(id);
		jaus tmpjaus = (jaus) resource.getContents().get(0);
		newref.setImportedNamespace((org.jts.eclipse.cjsidl.declaredConstSet) tmpjaus.getSet());

		return newref;
	}

}
