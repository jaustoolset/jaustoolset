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
public class TransitionTest
{
	private JAXBContext jc;
	private Unmarshaller unmarshaller;
	private List<org.jts.jsidl.binding.ServiceDef> sdefs1 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
	private List<org.jts.jsidl.binding.ServiceDef> sdefs2 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
	private List<org.jts.jsidl.binding.ServiceDef> sdefs3 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
	private List<org.jts.jsidl.binding.ServiceDef> sdefs4 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
	private List<org.jts.jsidl.binding.ServiceDef> sdefs5 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
	private List<org.jts.jsidl.binding.ServiceDef> sdefs6 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
	private List<org.jts.jsidl.binding.ServiceDef> sdefs7 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
	
	public static void main(String [ ] args)
	{
		TransitionTest t = new TransitionTest();
		t.createUnmarshaller();
		t.testValidate5();
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
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/TransitionTest1.xml"))
			);
			sdefs1.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/TransitionTest2.xml"))
			);

			sdefs2.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/TransitionTest3.xml"))
			);

			sdefs3.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/TransitionTest4.xml"))
			);

			sdefs4.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/TransitionTest5.xml"))
			);
			sdefs4.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/TransitionTest6.xml"))
			);

			sdefs5.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/TransitionTest7.xml"))
			);
			sdefs5.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/TransitionTest8.xml"))
			);

			sdefs6.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/TransitionTest9.xml"))
			);

			sdefs7.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/TransitionTest10.xml"))
			);
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}

	/* transition name must match a namespaced input message name or internal event name in the scope of the inheritance chain */
	@Test
	public void testValidate1()
	{
		Validator validator = new Validator();

		int errorSize = 0;

		try
		{
			validator.validate( sdefs1, sdefs1.get(1).getProtocolBehavior() );
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			errorSize = ValidatorUtils.getErrorCount( results );
			
			Assert.assertEquals( "\nTransitionTest.testValidate1() failed : description",
									results.get(0).getDescription(),
									"Transition name must match a message name or internal event name:TransitionTest1.Message2");
			Assert.assertEquals( "\nTransitionTest.testValidate1() failed : description",
									results.get(1).getDescription(),
									"Transition name must match a message name or internal event name:Message3");
			Assert.assertEquals( "\nTransitionTest.testValidate1() failed : description",
									results.get(2).getDescription(),
									"Transition name must match a message name or internal event name:Timeout2");
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}
	
		Assert.assertEquals( "\nTransitionTest.testValidate1() failed : size\n", 3, errorSize );
	}

	/* target state of transition must be in the same state machine as the source state */
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

			Assert.assertEquals( "\nTransitionTest.testValidate2() failed : description",
									results.get(0).getDescription(),
									"End state name must be a state within the same state machine");
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}

		Assert.assertEquals( "\nTransitionTest.testValidate2() failed : size\n", 1, errorSize );	
	}

	/* Transitions must be have unique triples {name, param list, guard conditions} */
	@Test
	public void testValidate3()
	{
		Validator validator = new Validator();

		int errorSize = 0;

		try
		{
			validator.validate( sdefs3, sdefs3.get(0).getProtocolBehavior() );
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			errorSize = ValidatorUtils.getErrorCount( results );

			Assert.assertEquals( "\nTransitionTest.testValidate3() failed : description",
									results.get(0).getDescription(),
									"Transition is not unique within its state, must have unique name:guards:parameters combination");
			Assert.assertEquals( "\nTransitionTest.testValidate3() failed : description",
									results.get(1).getDescription(),
									"Transition is not unique within its state, must have unique name:guards:parameters combination");
			Assert.assertEquals( "\nTransitionTest.testValidate3() failed : description",
									results.get(2).getDescription(),
									"Transition is not unique within its state, must have unique name:guards:parameters combination");
			Assert.assertEquals( "\nTransitionTest.testValidate3() failed : description",
									results.get(3).getDescription(),
									"Transition is not unique within its state, must have unique name:guards:parameters combination");
			Assert.assertEquals( "\nTransitionTest.testValidate3() failed : description",
									results.get(4).getDescription(),
									"Transition is not unique within its state, must have unique name:guards:parameters combination");
			Assert.assertEquals( "\nTransitionTest.testValidate3() failed : description",
									results.get(5).getDescription(),
									"Transition is not unique within its state, must have unique name:guards:parameters combination");
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}

		Assert.assertEquals( "\nTransitionTest.testValidate3() failed : size\n", 6, errorSize );
	}

	/* Add a warning to this effect � �If a transition is being overridden, ensure that all guarded versions of the transition are overridden� */
	@Test
	public void testValidate4()
	{
		Validator validator = new Validator();

		int warningSize = 0;

		try
		{
			validator.validate( sdefs4, sdefs4.get(1).getProtocolBehavior() );
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			warningSize = ValidatorUtils.getWarningCount( results );

			Assert.assertEquals( "\nTransitionTest.testValidate4() failed : description",
									results.get(0).getDescription(),
									"Trigger being overriden, make sure all instances are overriden");
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}

		Assert.assertEquals( "\nTransitionTest.testValidate4() failed : size\n", 1, warningSize );
	}

	/*
	Tests: Add a warning to this effect If the trigger of an added transition is an internal event that causes a state change
	in the base services, care must be taken to ensure that the internal event is first initialized by another transition that is
	triggered by a newly defined input message. That is, the new internal event must be causally and temporally related
	to a new input message such that the internal event executes only after a transition to the related input message has
	been executed at least once.
	*/
	@Test
	public void testValidate5()
	{
		Validator validator = new Validator();

		int errorSize = 0;

		try
		{
			validator.validate( sdefs5, sdefs5.get(1).getProtocolBehavior() );
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			errorSize = ValidatorUtils.getWarningCount( results );

			Assert.assertEquals( "\nTransitionTest.testValidate5() failed : description",
									results.get(0).getDescription(),
									"EndState causes a state change in the parent service");
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}

		Assert.assertEquals( "\nTransitionTest.testValidate5() failed : size\n", 1, errorSize );
	}

	/*
	Tests: The source of a push state must also define a corresponding pop transition. If a simple transition
	is defined within the push transition, the target state of the simple transition becomes the source of the push state.
	*/
	@Test
	public void testValidate6()
	{
		Validator validator = new Validator();

		int errorSize = 0;

		try
		{
			validator.validate( sdefs6, sdefs6.get(0).getProtocolBehavior() );
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			errorSize = ValidatorUtils.getErrorCount( results );

			Assert.assertEquals( "\nTransitionTest.testValidate6() failed : description",
									results.get(0).getDescription(),
									"Target end state from push must have corresponding pop:NotReady");
			Assert.assertEquals( "\nTransitionTest.testValidate6() failed : description",
									results.get(1).getDescription(),
									"Target end state from push must have corresponding pop:Ready");
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}

		Assert.assertEquals( "\nTransitionTest.testValidate6() failed : size\n", 2, errorSize );	
	}

	/*
	Tests: "If secondary transition takes a sequence of arguments, then the pop transition must specify the values of these arguments"
	*/
	@Test
	public void testValidate7()
	{
		Validator validator = new Validator();

		int errorSize = 0;

		try
		{
			validator.validate( sdefs7, sdefs7.get(0).getProtocolBehavior() );
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			errorSize = ValidatorUtils.getErrorCount( results );
			Assert.assertEquals( "\nTransitionTest.testValidate7() failed : description",
									results.get(0).getDescription(),
									"a d d  description  h e r e");
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}

		Assert.assertEquals( "\nTransitionTest.testValidate7() failed : size\n", 0, errorSize );
	}
}
