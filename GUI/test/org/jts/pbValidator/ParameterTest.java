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

import org.jts.pbValidator.Validator;
import org.jts.pbValidator.ValidatorException;
import org.jts.pbValidator.ValidatorResult;
import org.jts.pbValidator.ValidatorUtils;
import org.junit.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

/**
 *
 * @author Arfath Pasha
 *
 */
public class ParameterTest
{
	private JAXBContext jc;
	private Unmarshaller unmarshaller;
	private List<org.jts.jsidl.binding.ServiceDef> sdefs1 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();

	@Before
	public void createUnmarshaller()
	{
		try
		{
			jc = JAXBContext.newInstance("org.jts.jsidl.binding");
			unmarshaller = jc.createUnmarshaller();

			unmarshaller.setSchema(SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File("resources/schema/JSIDL_Plus/jsidl_plus.xsd")));

			sdefs1.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/ParameterTest1.xml"))
			);
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}

	/* parameter type must be a string literal of type string or a namespaced name of a type defined inside the trigger message or internal event */
	@Test
	public void testValidate1()
	{
		Validator validator = new Validator();

		int errorSize = 0;

		try
		{
			validator.validate( sdefs1, sdefs1.get(0).getProtocolBehavior() );
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			errorSize = ValidatorUtils.getErrorCount( results );

			Assert.assertEquals( "\nParameterTest.testValidate1() failed : description",
									results.get(0).getDescription(),
									"Parameter type must be a string literal or part of a message/internal event:Message1.body.unknownRec");
			Assert.assertEquals( "\nParameterTest.testValidate1() failed : description",
									results.get(1).getDescription(),
									"Parameter type must be a string literal or part of a message/internal event:someObject");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		Assert.assertEquals( "\nParameterTest.testValidate1() failed : size\n", 2, errorSize );
	}
}
