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

import org.jts.pbValidator.ProtocolBehavior;
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
public class ProtocolBehaviorTest
{
	private JAXBContext jc;
	private Unmarshaller unmarshaller;
	private List<org.jts.jsidl.binding.ServiceDef> sdefs1 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
	private List<org.jts.jsidl.binding.ServiceDef> sdefs2 = new ArrayList<org.jts.jsidl.binding.ServiceDef>();

	public static void main(String [ ] args)
	{
		ProtocolBehaviorTest pb = new ProtocolBehaviorTest();
		pb.createUnmarshaller();
		pb.testValidate2();
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
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/ProtocolBehaviorTest1.xml"))
			);

			sdefs2.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/ProtocolBehaviorTest2a.xml"))
			);
			sdefs2.add(
			(org.jts.jsidl.binding.ServiceDef)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/ProtocolBehaviorTest2b.xml"))
			);
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}

	/* At least one state machine must be defined */
	@Test
	public void testValidate1()
	{
		Validator validator = new Validator();
		sdefs1.get(0).getProtocolBehavior().getStateMachine().clear();   // remove all state machines
		
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

			Assert.assertEquals( "\nProtocolBehaviorTest.testValidate1() failed : description",
					results.get(0).getDescription(),
					"Protocol Behavior must have one or more state machines");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		Assert.assertEquals( "\nProtocolBehaviorTest.testValidate1() failed : size\n", 1, errorSize );
	}

	/* When inheritance is used, the definitions for the pseudo start states in the derived service must either be
		identical to the definitions of the pseudo start states in the base service definition, or they must point to nested
		states belonging to the start states defined in the base service definitions.
	*/
	@Test
	public void testValidate2()
	{
		Validator validator = new Validator();

		int errorSize = 0;

		try
		{
			validator.validate( sdefs2, sdefs2.get(1).getProtocolBehavior() );
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			errorSize = ValidatorUtils.getErrorCount( results );

			Assert.assertEquals( "\nProtocolBehaviorTest.testValidate2() failed : description",
									results.get(0).getDescription(),
									"State initial state must match inherited state properties: StateA");

			Assert.assertEquals( "\nProtocolBehaviorTest.testValidate2() failed : description",
									results.get(1).getDescription(),
									"Start state does not match inherited properties sm: FSM childStartStateName: Ready.StateA.StateAC parentStartStateName: Ready.StateA.StateAA");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		Assert.assertEquals( "\nProtocolBehaviorTest.testValidate2() failed : size\n", 2, errorSize );
	}
}
