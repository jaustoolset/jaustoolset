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

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class ValidatorTestMain
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Run one test
		//org.junit.runner.JUnitCore.main("org.jts.validator.ProtocolBehaviorTest");

		 JAXBContext jc;
		 Unmarshaller unmarshaller;
		
		 try
		 {
			 jc = JAXBContext.newInstance("org.jts.jsidl.binding");
			 unmarshaller = jc.createUnmarshaller();
			 org.jts.jsidl.binding.MessageDef msg;
			 msg =
			 (org.jts.jsidl.binding.MessageDef)unmarshaller.unmarshal(new
			 File(
			 "test/org/jts/validator/JSIDL/Valid/MessageDef1.xml"));
			 System.out.println("Msg Id: " + msg.getMessageId().length);
						
			 
			 org.jts.validator.MessageDef.validate(msg);
		 }
		 catch(Exception e)
		 {
			 System.out.print(e);
		 }

		/*
		 * Run all testsorg.junit.runner.JUnitCore.main(
		 * "org.jts.validator.ActionTest",
		 * "org.jts.validator.ArgumentTest",
		 * "org.jts.validator.ArrayTest",
		 * "org.jts.validator.AssumptionsTest",
		 * "org.jts.validator.BitFieldTest",
		 * "org.jts.validator.BitRangeTest",
		 * "org.jts.validator.BodyTest",
		 * "org.jts.validator.ClientOfTest",
		 * "org.jts.validator.ConstDefTest",
		 * "org.jts.validator.CountFieldTest",
		 * "org.jts.validator.DeclaredConstSetTest",
		 * "org.jts.validator.DeclaredTypeSetTest",
		 * "org.jts.validator.DescriptionTest",
		 * "org.jts.validator.DimensionTest",
		 * "org.jts.validator.EntryTest",
		 * "org.jts.validator.EventDefTest",
		 * "org.jts.validator.ExitTest",
		 * "org.jts.validator.FixedFieldTest",
		 * "org.jts.validator.FixedLengthStringTest",
		 * "org.jts.validator.FooterTest",
		 * "org.jts.validator.FormatEnumTest",
		 * "org.jts.validator.FormatFieldTest",
		 * "org.jts.validator.HeaderTest",
		 * "org.jts.validator.InheritsFromTest",
		 * "org.jts.validator.InputSetTest",
		 * "org.jts.validator.InternalEventsSetTest",
		 * "org.jts.validator.ListTest",
		 * "org.jts.validator.MessageDefTest",
		 * "org.jts.validator.MessageSetTest",
		 * "org.jts.validator.OutputSetTest",
		 * "org.jts.validator.PresenceVectorTest",
		 * "org.jts.validator.RecordTest",
		 * "org.jts.validator.ReferencesTest",
		 * "org.jts.validator.ScaleRangeTest",
		 * "org.jts.validator.SendActionTest",
		 * "org.jts.validator.SequenceTest",
		 * "org.jts.validator.ServiceDefTest",
		 * "org.jts.validator.ServiceSetTest",
		 * "org.jts.validator.SubFieldTest",
		 * "org.jts.validator.TypeAndUnitsEnumTest",
		 * "org.jts.validator.ValueEnumTest",
		 * "org.jts.validator.ValueRangeTest",
		 * "org.jts.validator.ValueSetTest",
		 * "org.jts.validator.VariableFieldTest",
		 * "org.jts.validator.VariableFormatFieldTest",
		 * "org.jts.validator.VariableLengthFieldTest",
		 * "org.jts.validator.VariableLengthStringTest",
		 * "org.jts.validator.VariantTest",
		 * "org.jts.validator.VtagFieldTest" ); /* All tests end
		 */
	}

}
