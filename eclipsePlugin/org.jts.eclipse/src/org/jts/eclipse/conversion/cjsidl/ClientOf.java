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
import org.jts.eclipse.cjsidl.jaus;
import org.jts.eclipse.cjsidl.refAttr;
import org.jts.eclipse.cjsidl.serviceDef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class ClientOf extends ConversionUtil{

	/**
	 * Converts a reference attribute to a client_of JSIDL data binding
	 * @param ref - a scoped reference to another file in the project
	 * @return - resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.ClientOf convert(refAttr ref) {
		org.jts.jsidl.binding.ClientOf newref = new org.jts.jsidl.binding.ClientOf();

		newref.setInterpretation(CJSIDLCommentToJSIDLInterp(ref.getComment()));
		newref.setName(ref.getName());

		String id = ref.getImportedNamespace().getName().replace(".", ":");
		id = id.replace("'", "");
		id = id.replace("\"", "");
		newref.setId(id);
		
		String version = ref.getImportedNamespace().getServiceVersion();//ref.getRevVersion();
		version = version.replace("'", "");
		version = version.replace("\"", "");
		newref.setVersion(version);
		
		return newref;
	}

	/**
	 * Converts a JSIDL data binding to a reference attribute
	 * @param ref - input JSIDL reference
	 * @return - resulting CJSIDL reference
	 */
	public static refAttr convert(org.jts.jsidl.binding.ClientOf ref) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.refAttr output = factory.createrefAttr();
		
		output.setName(ref.getName());
		//output.setRevVersion("'" + ref.getVersion() + "'");

		output.setComment(JSIDLInterpToCJSIDLComment(ref.getInterpretation()));

		String id = ref.getId().replace(":", ".");		
		Resource resource = Conversion.referenceHelper.getResource(id);
		jaus tmpjaus = (jaus) resource.getContents().get(0);
		output.setImportedNamespace((serviceDef) tmpjaus.getSet());

		return output;
	}

}
