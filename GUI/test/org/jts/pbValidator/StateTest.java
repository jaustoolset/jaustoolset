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
public class StateTest
{
	private JAXBContext jc;
	private Unmarshaller unmarshaller;
	private List<org.jts.jsidl.binding.ServiceDef> sdefs1 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
	private List<org.jts.jsidl.binding.ServiceDef> sdefs2 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
	private List<org.jts.jsidl.binding.ServiceDef> sdefs3 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
	private List<org.jts.jsidl.binding.ServiceDef> sdefs4 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();

	public static void main(String [ ] args)
	{
		StateTest st = new StateTest();
		st.createUnmarshaller();
		st.testValidate4();
	}
	
	@Before
	public void createUnmarshaller()
	{
		try
		{
			jc = JAXBContext.newInstance("org.jts.jsidl.binding");
			unmarshaller = jc.createUnmarshaller();

			unmarshaller.setSchema(SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File("resources/schema/JSIDL_Plus/jsidl_plus.xsd")));

			sdefs1.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/StateTest1.xml"))
			);

			sdefs2.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/StateTest2.xml"))
			);

			sdefs3.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/StateTest3.xml"))
			);
			sdefs3.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/StateTest4.xml"))
			);

			sdefs4.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/StateTest5.xml"))
			);
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}

	/* The state names must be valid identifiers */
	@Test
	public void testValidate1()
	{
		Validator validator = new Validator();
		sdefs1.get(0).getProtocolBehavior().getStateMachine().get(0).getState().get(0).setName("Ready And Go");

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
			
			Assert.assertEquals( "\nStateTest.testValidate1() failed : description",
								results.get(0).getDescription(),
								"A start must have a valid state within the matching state machine");
			Assert.assertEquals( "\nStateTest.testValidate1() failed : description",
								results.get(1).getDescription(),
								"State name must be a valid identifier in the C programming language");
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}

		Assert.assertEquals( "\nStateTest.testValidate1() failed : size\n", 2, errorSize );
	}

	/* An initial state must be defined for every level of nesting */
	@Test
	public void testValidate2()
	{
		Validator validator = new Validator();
		
		int errorSize = 0;

		try
		{
			validator.validate( sdefs2, sdefs2.get(0).getProtocolBehavior() );
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			errorSize = ValidatorUtils.getErrorCount( results );
			
			Assert.assertEquals( "\nStateTest.testValidate2() failed : description",
									results.get(0).getDescription(),
									"A state that contains more than one nested state must have an initial state");
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}

		Assert.assertEquals( "\nStateTest.testValidate2() failed : size\n", 1, errorSize );		
	}

	/* when inheritance is used, base service states must be present in derived service in the same order as in the base service */
	@Test
	public void testValidate3()
	{
		Validator validator = new Validator();
		
		int errorSize = 0;

		try
		{
			validator.validate( sdefs3, sdefs3.get(1).getProtocolBehavior() );
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			errorSize = ValidatorUtils.getErrorCount( results );
			
			Assert.assertEquals( "\nStateTest.testValidate3() failed : description",
								results.get(0).getDescription(),
								"State must match inherited state properties: StateAB");
			Assert.assertEquals( "\nStateTest.testValidate3() failed : description",
								results.get(1).getDescription(),
								"State must match inherited state properties: B");
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}

		Assert.assertEquals( "\nStateTest.testValidate3() failed : size\n", 2, errorSize );		
	}

	/* Names of states must be unique in the set of all nested states within a parent state */
	@Test
	public void testValidate4()
	{		
		Validator validator = new Validator();
		
		int errorSize = 0;

		try
		{
			validator.validate( sdefs4, sdefs4.get(0).getProtocolBehavior() );
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			errorSize = ValidatorUtils.getErrorCount( results );
			
			Assert.assertEquals( "\nStateTest.testValidate4() failed : description",
								results.get(0).getDescription(),
								"State Machine must have substates unique within the context of their siblings");
			Assert.assertEquals( "\nStateTest.testValidate4() failed : description",
								results.get(1).getDescription(),
								"State must have substates unique within the context of their siblings");
			Assert.assertEquals( "\nStateTest.testValidate4() failed : description",
								results.get(2).getDescription(),
								"A state's initial state must point to one sub state within the state");
			Assert.assertEquals( "\nStateTest.testValidate4() failed : description",
								results.get(3).getDescription(),
								"State must have substates unique within the context of their siblings");
			Assert.assertEquals( "\nStateTest.testValidate4() failed : description",
								results.get(4).getDescription(),
								"A state's initial state must point to one sub state within the state");
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}

		Assert.assertEquals( "\nStateTest.testValidate4() failed : size\n", 5, errorSize );		
	}
}
