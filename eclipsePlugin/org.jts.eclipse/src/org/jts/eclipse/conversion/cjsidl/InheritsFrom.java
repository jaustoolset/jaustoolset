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
public class InheritsFrom extends ConversionUtil{

	/**
	 * converts an CJSIDL inheritance reference to a JSIDL object
	 * @param ref - CJSIDL object
	 * @return - resulting JSIDL object
	 */
	public static org.jts.jsidl.binding.InheritsFrom convert(refAttr ref) {
		org.jts.jsidl.binding.InheritsFrom newref = new org.jts.jsidl.binding.InheritsFrom();

		newref.setInterpretation(CJSIDLCommentToJSIDLInterp(ref.getComment()));
		newref.setName(ref.getName());

		// fix the id
		String id = ref.getImportedNamespace().getName().replace(".", ":");
		id = id.replace("'", "");
		id = id.replace("\"", "");
		newref.setId(id);
		
		// fix the version number
		String version = ref.getImportedNamespace().getServiceVersion();//ref.getRevVersion();
		version = version.replace("'", "");
		version = version.replace("\"", "");
		newref.setVersion(version);
		
		return newref;
	}

	/**
	 * Converts a JSIDL inheritance to a CJSIDL object
	 * @param inheritsFrom - JSIDL object
	 * @return - resulting CJSIDL object
	 */
	public static refAttr convert(org.jts.jsidl.binding.InheritsFrom inheritsFrom) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.refAttr output = factory.createrefAttr();
		
		output.setName(inheritsFrom.getName());
		// this was removed from our grammar, since it's available via a link
		// to the CJSIDL object
//		output.setRevVersion(inheritsFrom.getVersion());

		output.setComment(JSIDLInterpToCJSIDLComment(inheritsFrom.getInterpretation()));

		String id = inheritsFrom.getId().replace(":", ".");		
		Resource resource = Conversion.referenceHelper.getResource(id);
		jaus tmpjaus = (jaus) resource.getContents().get(0);
		output.setImportedNamespace((serviceDef) tmpjaus.getSet());

		return output;
	}

}
