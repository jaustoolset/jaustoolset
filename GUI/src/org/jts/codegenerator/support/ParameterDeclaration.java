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

/**
 * Stores information for a parameter.
 * @author JFK
 *
 */
public class ParameterDeclaration
{
	String _name;
	String _type;
	String _defaultValue;
	
	public ParameterDeclaration()
	{
	}
	
	public ParameterDeclaration(String type, String name, String defaultValue)
	{
		_type = type;
		_name = name;
		_defaultValue = defaultValue;
	}
	
	public String get_name()
	{
		return _name;
	}
	
	public void set_name(String _name)
	{
		this._name = _name;
	}
	
	public String get_type()
	{
		return _type;
	}
	
	public void set_type(String _type)
	{
		this._type = _type;
	}
	
	public String get_defaultValue()
	{
		return _defaultValue;
	}
	
	public void set_defaultValue(String value)
	{
		_defaultValue = value;
	}

	@Override
	public String toString()
	{
		if(_type != null && _name != null)
			return _type + " " + _name + (_defaultValue != null ? " = " + _defaultValue : "") + ";";
		
		return "";
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		
		ParameterDeclaration param = (ParameterDeclaration)obj;
		if(param != null)
		{
			if(_type != null && _type.equals(param.get_type()) && _name != null && _name.equals(param.get_name()))
			{
				if(_defaultValue != null)
					return _defaultValue.equals(param.get_defaultValue());
				else
					return true;
			}
		}
		
		return false;
	}
}
