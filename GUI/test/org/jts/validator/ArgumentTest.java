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

package org.jts.validator;

import org.junit.*;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

public class ArgumentTest
{
	private JAXBContext jc;
	private Unmarshaller unmarshaller;

	@Before
	public void createUnmarshaller()
	{
		try
		{
			jc = JAXBContext.newInstance("org.jts.jsidl.binding");
			unmarshaller = jc.createUnmarshaller();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}

	@Test
	public void testValidArgument1() throws Exception
	{
		unmarshaller.setSchema(SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File("resources/schema/JSIDL_Plus/jsidl_plus.xsd")));
		org.jts.jsidl.binding.Argument argument;
		argument = (org.jts.jsidl.binding.Argument)unmarshaller.unmarshal(new File("test/org/jts/validator/JSIDL/Valid/Argument1.xml"));
		org.jts.validator.Argument.validate(argument);
	}

	@Test(expected = ValidatorException.class)
	public void testInvalidArgument1()
	{
		try
		{
			org.jts.jsidl.binding.Argument argument;
			argument = (org.jts.jsidl.binding.Argument)unmarshaller.unmarshal(new File("test/org/jts/validator/JSIDL/Invalid/Argument1.xml"));
			org.jts.validator.Argument.validate(argument);
		}
		catch(JAXBException e)
		{
			System.out.print(e);
		}
	}

	@Test(expected = ValidatorException.class)
	public void testInvalidArgument2()
	{
		try
		{
			org.jts.jsidl.binding.Argument argument;
			argument = (org.jts.jsidl.binding.Argument)unmarshaller.unmarshal(new File("test/org/jts/validator/JSIDL/Invalid/Argument2.xml"));
			org.jts.validator.Argument.validate(argument);
		}
		catch(JAXBException e)
		{
			System.out.print(e);
		}
	}
}
