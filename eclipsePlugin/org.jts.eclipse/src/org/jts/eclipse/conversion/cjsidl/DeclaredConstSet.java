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
import org.jts.eclipse.cjsidl.constDef;
import org.jts.eclipse.cjsidl.declaredConstSet;
import org.jts.eclipse.cjsidl.declaredConstSetRef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class DeclaredConstSet {

	/**
	 * Converts a constant set from CJSIDL to JSIDL
	 * @param objSet - the input CJSIDL object
	 * @return - the resulting JSIDL object
	 */
	public static org.jts.jsidl.binding.DeclaredConstSet convert(declaredConstSet objSet) {
		org.jts.jsidl.binding.DeclaredConstSet newconsts = new org.jts.jsidl.binding.DeclaredConstSet();
		
		String id = objSet.getName();
		// remove or replace any invalid values in the string
		if(id != null && !id.isEmpty()){
			id = id.replace("'", "");
			id = id.replace("\"", "");
			id = id.replace(".", ":");
			newconsts.setId(id);		
		}
		
		String version = objSet.getConstSetVersion();
		if(version != null && !version.isEmpty()){
			version = version.replace("'", "");
			version = version.replace("\"", "");
			newconsts.setVersion(version);
		}

		newconsts.setName(objSet.getConstName());

		// convert const defs
		java.util.List<org.jts.jsidl.binding.ConstDef> newdefs = newconsts.getConstDef();
		EList<constDef> defs = objSet.getConstDef();
		for(constDef def: defs){
			newdefs.add(ConstDef.convert(def));
		}
		
		java.util.List<org.jts.jsidl.binding.DeclaredConstSetRef> newrefs = newconsts.getDeclaredConstSetRef();
		EList<declaredConstSetRef> refs = objSet.getDeclaredConstSetRef();
		for(declaredConstSetRef ref: refs){
			newrefs.add(DeclaredConstSetRef.convert(ref));
		}
		
		return newconsts;
	}

	public static declaredConstSet convert(org.jts.jsidl.binding.DeclaredConstSet objSet){
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		
		org.jts.eclipse.cjsidl.declaredConstSet output = factory.createdeclaredConstSet();
		output.setConstName(objSet.getName());
		
		if(objSet.getId() != null){
			String tmpid = objSet.getId().replace(":", ".");
			tmpid = tmpid.replace("\"", "");
			tmpid = tmpid.replace("'", "");		
			output.setName( objSet.getId().replace(":", ".") );
		}
		if(objSet.getVersion() != null){
			output.setConstSetVersion( objSet.getVersion() );
		}

		
		java.util.List<org.jts.jsidl.binding.ConstDef> defs = objSet.getConstDef();
		EList<constDef> newdefs = output.getConstDef();
		for(org.jts.jsidl.binding.ConstDef def: defs){
			newdefs.add(ConstDef.convert(def));
		}
		java.util.List<org.jts.jsidl.binding.DeclaredConstSetRef> refs = objSet.getDeclaredConstSetRef();
		EList<declaredConstSetRef> newrefs = output.getDeclaredConstSetRef();
		for(org.jts.jsidl.binding.DeclaredConstSetRef ref: refs){
			newrefs.add(DeclaredConstSetRef.convert(ref));
		}
		return output;
	}
}
