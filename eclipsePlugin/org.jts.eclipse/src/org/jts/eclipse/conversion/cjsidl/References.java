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
import org.jts.eclipse.cjsidl.references;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class References {

	/**
	 * Converts a CJSIDL references object to JSIDL
	 * @param refs - a CJSIDL references object
	 * @return - resulting JSIDL object
	 */
	public static org.jts.jsidl.binding.References convert(references refs) {
		org.jts.jsidl.binding.References newrefs = new org.jts.jsidl.binding.References();
		
		if(refs.getRefInherit() != null)
		{
			newrefs.setInheritsFrom(InheritsFrom.convert(refs.getRefInherit()));
		}
		EList<org.jts.eclipse.cjsidl.refAttr> clientrefs = refs.getRefClient();
		java.util.List<org.jts.jsidl.binding.ClientOf> newclientrefs =  newrefs.getClientOf();
		for(org.jts.eclipse.cjsidl.refAttr ref: clientrefs){
			newclientrefs.add(ClientOf.convert(ref));
		}
		
		return newrefs;
	}

	/**
	 * Converts a JSIDL references object to CJSIDL
	 * @param refs - a JSIDL references object
	 * @return - resulting CJSIDL objectd
	 */
	public static references convert(org.jts.jsidl.binding.References refs) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.references output = factory.createreferences();

		if(refs.getInheritsFrom() != null){
			output.setRefInherit(InheritsFrom.convert(refs.getInheritsFrom()));
		}
		
		EList<org.jts.eclipse.cjsidl.refAttr> outputrefs = output.getRefClient();
		java.util.List<org.jts.jsidl.binding.ClientOf> inputrefs = refs.getClientOf();
		for(org.jts.jsidl.binding.ClientOf ref: inputrefs){
			outputrefs.add(ClientOf.convert(ref));
		}
		
		return output;
	}

}
