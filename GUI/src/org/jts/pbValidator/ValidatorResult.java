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

package org.jts.pbValidator;


public class ValidatorResult
{

	private String description;
	private String path;
	private String type;

	static final String TYPE_ERROR = new String("Error");
	static final String TYPE_WARNING = new String("Warning");

	ValidatorResult( String description, String path, String type )
	{
		this.description = description;
		this.path = path;
		this.type = type;
	}

	public String getDescription()
	{
		return description;
	}

	public String getPath()
	{
		return path;
	}

	public String getType()
	{
		return type;
	}
	
	public boolean isError()
	{
		return type.compareTo(TYPE_ERROR) == 0;
	}
	
	public boolean isWarning()
	{
		return type.compareTo(TYPE_WARNING) == 0;
	}

	public boolean same( ValidatorResult compare )
	{
		int sameType = compare.getType().compareTo( this.getType() );
		int sameDescription = compare.getDescription().compareTo( this.getDescription() );
		int samePath = compare.getPath().compareTo( this.getPath() );

		if( sameType == 0 && sameDescription == 0 && samePath == 0 )
		{
			return true;
		}

		return false;
	}

}
