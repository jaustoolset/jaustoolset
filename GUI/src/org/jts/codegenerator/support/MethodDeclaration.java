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

import java.util.Iterator;
import java.util.Vector;

/**
 * Stores a method declaration.
 * @author JFK
 *
 */
public class MethodDeclaration implements IVisibility
{
	EMemberVisibility _visibility = EMemberVisibility.Public;
	Vector<String> _modifiers = new Vector<String>();
	String _returnType;
	String _name = "";
	Vector<ParameterDeclaration> _params = new Vector<ParameterDeclaration>();
	
	public EMemberVisibility get_visibility() {
		return _visibility;
	}

	public void set_visibility(EMemberVisibility _visibility) {
		this._visibility = _visibility;
	}

	public Vector<String> get_modifiers()
	{
		return _modifiers;
	}

	public void set_modifiers(Vector<String> _modifiers)
	{
		this._modifiers = _modifiers;
	}

	public void addModifier(String mod)
	{
		_modifiers.add(mod);
	}
	
	public String get_returnType()
	{
		return _returnType;
	}

	public void set_returnType(String type)
	{
		_returnType = type;
	}

	public String get_name()
	{
		return _name;
	}

	public void set_name(String _name)
	{
		this._name = _name;
	}

	public Vector<ParameterDeclaration> get_params()
	{
		return _params;
	}

	public void set_params(Vector<ParameterDeclaration> _params)
	{
		this._params = _params;
	}
	
	public void addParameter(ParameterDeclaration param)
	{
		_params.add(param);
	}
	
	@Override
	public String toString()
	{
		StringBuffer strBuf = new StringBuffer();

		for(String mod : _modifiers)
			strBuf.append(mod).append(" ");
		
		if(_returnType != null)
			strBuf.append(_returnType).append(" ");
		
		strBuf.append(_name).append("(");
		Iterator<ParameterDeclaration> iter = _params.iterator();
		while(iter.hasNext())
		{
			ParameterDeclaration param = iter.next();
			strBuf.append(param.toString());
			if(iter.hasNext())
				strBuf.append(", ");
		}
		
		strBuf.append(");");
		
		return strBuf.toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		
		MethodDeclaration dec = (MethodDeclaration)obj;
		if(dec != null)
		{
			// compare modifiers
			Vector<String> decMods = dec.get_modifiers();
			if(_modifiers != null && decMods != null)
			{
				for(String mod : decMods)
					if(!_modifiers.contains(mod))
						return false;
			}
			else if(_modifiers == null ^ decMods == null)
				return false;
			
			// compare return types
			String decRetType = dec.get_returnType();
			if((_returnType != null && decRetType != null && !_returnType.equals(decRetType)) || (_returnType == null ^ decRetType == null))
				return false;
			
			// compare names
			String decName = dec.get_name();
			if((_name != null && decName != null && !_name.equals(decName)) || (_name == null ^ decName == null))
				return false;
			
			// compare parameters
			Vector<ParameterDeclaration> decParams = dec.get_params();
			if(_params != null && decParams != null)
			{
				for(ParameterDeclaration param : decParams)
					if(!_params.contains(param))
						return false;
			}
			else if(_params == null ^ decParams == null)
				return false;
			
			return true;
		}
		
		return false;
	}

}
