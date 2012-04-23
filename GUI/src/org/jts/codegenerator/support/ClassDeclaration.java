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

package org.jts.codegenerator.support;

import java.util.Vector;

public class ClassDeclaration
{
	Vector<String> _modifiers = new Vector<String>();
	String _name = "";
	Vector<FieldDeclaration> _fields = new Vector<FieldDeclaration>();
	Vector<MethodDeclaration> _constructors = new Vector<MethodDeclaration>();
	MethodDeclaration _destructor;
	Vector<MethodDeclaration> _methods = new Vector<MethodDeclaration>();
	
	public Vector<String> get_modifiers() {
		return _modifiers;
	}

	public void set_modifiers(Vector<String> _modifiers) {
		this._modifiers = _modifiers;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public Vector<FieldDeclaration> get_fields() {
		return _fields;
	}

	public void set_fields(Vector<FieldDeclaration> _fields) {
		this._fields = _fields;
	}

	public Vector<MethodDeclaration> get_constructors() {
		return _constructors;
	}

	public void set_constructors(Vector<MethodDeclaration> _constructors) {
		this._constructors = _constructors;
	}

	public MethodDeclaration get_destructor() {
		return _destructor;
	}

	public void set_destructor(MethodDeclaration _destructor) {
		this._destructor = _destructor;
	}

	public Vector<MethodDeclaration> get_methods() {
		return _methods;
	}

	public void set_methods(Vector<MethodDeclaration> _methods) {
		this._methods = _methods;
	}

	@Override
	public String toString()
	{
		StringBuffer strBuf = new StringBuffer();
		
		for(String mod : _modifiers)
			strBuf.append(mod).append(" ");
		
		strBuf.append("class ").append(_name).append("\n{\n");
		
		// separate into private/protected/public members
		Vector<IVisibility> vis = null;
		Vector<IVisibility> pubFields = new Vector<IVisibility>();
		Vector<IVisibility> priFields = new Vector<IVisibility>();
		Vector<IVisibility> proFields = new Vector<IVisibility>();
		Vector<IVisibility> pubMethods = new Vector<IVisibility>();
		Vector<IVisibility> priMethods = new Vector<IVisibility>();
		Vector<IVisibility> proMethods = new Vector<IVisibility>();
		Vector<IVisibility> pubConsts = new Vector<IVisibility>();
		Vector<IVisibility> priConsts = new Vector<IVisibility>();
		Vector<IVisibility> proConsts = new Vector<IVisibility>();
		
		vis = new Vector<IVisibility>();
		vis.addAll(_fields);
		ClassDeclaration.separateByVisibility(vis, pubFields, priFields, proFields);
		
		vis = new Vector<IVisibility>();
		vis.addAll(_constructors);
		ClassDeclaration.separateByVisibility(vis, pubFields, priFields, proFields);
		
		vis = new Vector<IVisibility>();
		vis.addAll(_methods);
		ClassDeclaration.separateByVisibility(vis, pubFields, priFields, proFields);
		
		strBuf.append("private:\n");
		strBuf.append("protected:\n");
		strBuf.append("public:\n");
		
		strBuf.append("\t// local fields\n");
		for(FieldDeclaration field : _fields)
		{
			strBuf.append("\t").append(field.toString()).append("\n");
		}
		
		strBuf.append("\n\t// constructors/destructors\n");
		for(MethodDeclaration con : _constructors)
		{
			strBuf.append("\t").append(con.toString()).append("\n");
		}
		if(_destructor != null)
			strBuf.append("\t").append(_destructor.toString()).append("\n");
		
		strBuf.append("\n\t// methods\n");
		for(MethodDeclaration method : _methods)
		{
			strBuf.append("\t").append(method.toString()).append("\n");
		}
		
		strBuf.append("}");
		
		return strBuf.toString();
	}

	public static void separateByVisibility(Vector<IVisibility> vec, Vector<IVisibility> pubVec, Vector<IVisibility> priVec, Vector<IVisibility> proVec)
	{
		for(IVisibility vis : vec)
		{
			switch(vis.get_visibility())
			{
			case Public:
				pubVec.add(vis);
				break;
			case Private:
				priVec.add(vis);
				break;
			case Protected:
				proVec.add(vis);
				break;
			}
		}
	}
}
