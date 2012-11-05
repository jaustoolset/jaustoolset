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

/*
 * @(#)ScaleRangeGenerator.java		0.1 2008/09/19
 * 
 * Copyright
 */
package org.jts.codegenerator;


import java.util.ArrayList;
import java.util.List;
import org.jts.jsidl.binding.ScaleRange;

public class ScaleRangeGenerator
{
	private CodeLines.CodeType codeType;
	private ScaleRange scaleRange;
	
	List<String> invalidValues;
	List<String> validValues;
	
	/**
	 * 
	 * @param scaleRange	the ScaleRange that is being generated for
	 * @param fieldType		the type of Integer that is being converted
	 * @param variableName	the name of the variable for the Integer
	 * @param strVector		the Vector<String> to populate with the code
	 * @param codeType		the type of Code being generated
	 */
	public ScaleRangeGenerator(CodeLines.CodeType codeType, ScaleRange scaleRange)
	{
            this.codeType = codeType;
            this.scaleRange = scaleRange;

            String min = scaleRange.getRealLowerLimit().replaceAll("\\,", "");
            String max = scaleRange.getRealUpperLimit().replaceAll("\\,", "");
		
			if ( !min.contains(".") ) min = min.concat( ".0" );
			if ( !max.contains(".") ) max = max.concat( ".0" );

            //double maxNum = Double.parseDouble(max);
            //double minNum = Double.parseDouble(min);
            //double midNum = (maxNum - minNum) / 2;
            String mid = "( " + max + " - " + min + " ) " + "/ 2";
        
            //String below = Double.toString(minNum - midNum);
            //String above = Double.toString(maxNum +  midNum);
            String below = min + " - " + mid;
            String above = min + " + " + mid;
        
            invalidValues = new ArrayList<String>();
            invalidValues.add(below);
            invalidValues.add(above);

            validValues = new ArrayList<String>();
            validValues.add(min);
            //validValues.add(Double.toString(midNum));
            validValues.add(mid);
            validValues.add(max);
	}
	
	
	public void getIntToDoubleConversion(String fieldType, String variableName, String doubleVar, List<String> strVector)
	{
            String min = scaleRange.getRealLowerLimit().replaceAll("\\,", "");
            String max = scaleRange.getRealUpperLimit().replaceAll("\\,", "");

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
				if ( !min.contains(".") ) min = min.concat( ".0" );
				if ( !max.contains(".") ) max = max.concat( ".0" );
	
	            String scaleFactor = "( " + max +  " - " + min + " ) / " + CppCode.getIntegerRange(fieldType);
	            String bias = min;
	
	            strVector.add("double scaleFactor = " + scaleFactor + ";" );
                    strVector.add("double bias = " + bias + ";");
                    strVector.add("");
                    strVector.add(doubleVar + " = " + variableName + " * scaleFactor + bias;");
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
	            String scaleFactor = "( " + max +  " - " + min + " ) / " + JavaCode.getIntegerRange(fieldType);
	            String bias = min;

	            strVector.add("double scaleFactor = " + scaleFactor + ";");
                    strVector.add("double bias = " + bias + ";");
                    strVector.add("");
                    strVector.add(doubleVar + " = " + variableName + " * scaleFactor + bias;");
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
	            String scaleFactor = "( " + max +  " - " + min + " ) / " + CSharpCode.getIntegerRange(fieldType);
	            String bias = min;

	            strVector.add("double scaleFactor = " + scaleFactor + ";");
                    strVector.add("double bias = " + bias + ";");
                    strVector.add("");
                    strVector.add(doubleVar + " = " + variableName + " * scaleFactor + bias;");
            }
	}
	
	
	public void getDoubleToIntConversion(String fieldType, String variableName, String doubleVar, List<String> strVector)
	{
            String min = scaleRange.getRealLowerLimit().replaceAll("\\,", "");
            String max = scaleRange.getRealUpperLimit().replaceAll("\\,", "");

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
					String minX = min;

					if ( !min.contains(".") ) min = min.concat( ".0" );
					if ( !max.contains(".") ) max = max.concat( ".0" );

                    //double scaleFactor = (Double.valueOf(max) - Double.valueOf(min)) / CppCode.getIntegerRange(fieldType);
                    //double bias = Double.valueOf(min);
                    String scaleFactor = "( " + max +  " - " + min + " ) / " + CppCode.getIntegerRange(fieldType);
                    String bias = min;
                    String integerFunction = scaleRange.getIntegerFunction();
		          
                    if (integerFunction.equalsIgnoreCase("round"))
                    {
                        integerFunction = "";
                    }
		          
                    strVector.add("double scaleFactor = " + scaleFactor + ";");
                    strVector.add("double bias = " + bias + ";");
                    strVector.add("");
                    strVector.add(variableName + "= (" + CppCode.getVariableType(fieldType) + ")" + integerFunction + "((" + doubleVar + " - bias) / scaleFactor);");
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                    //double scaleFactor = (Double.valueOf(max) - Double.valueOf(min)) / CppCode.getIntegerRange(fieldType);
                    //double bias = Double.valueOf(min);
                    String scaleFactor = "( " + max +  " - " + min + " ) / " + JavaCode.getIntegerRange(fieldType);
                    String bias = min;
                    String integerFunction = scaleRange.getIntegerFunction();

                    if (integerFunction.equalsIgnoreCase("round"))
                    {
                        integerFunction = "";
                    }

                    strVector.add("double scaleFactor = " + scaleFactor + ";");
                    strVector.add("double bias = " + bias + ";");
                    strVector.add("");
                    strVector.add(variableName + "= (" + JavaCode.getVariableType(fieldType) + ")" + integerFunction + "((" + doubleVar + " - bias) / scaleFactor);");
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                    //double scaleFactor = (Double.valueOf(max) - Double.valueOf(min)) / CppCode.getIntegerRange(fieldType);
                    //double bias = Double.valueOf(min);
                    String scaleFactor = "( " + max +  " - " + min + " ) / " + CSharpCode.getIntegerRange(fieldType);
                    String bias = min;
                    String integerFunction = scaleRange.getIntegerFunction();

                    if (integerFunction.equalsIgnoreCase("round"))
                    {
                        integerFunction = "";
                    }

                    strVector.add("double scaleFactor = " + scaleFactor + ";");
                    strVector.add("double bias = " + bias + ";");
                    strVector.add("");
                    strVector.add(variableName + "= (" + CSharpCode.getVariableType(fieldType) + ")" + integerFunction + "((" + doubleVar + " - bias) / scaleFactor);");
            }
	}
	
	
	public void addRangeCheck(String doubleVar, List<String> code)
	{
            String min = scaleRange.getRealLowerLimit().replaceAll("\\,", "");
            String max = scaleRange.getRealUpperLimit().replaceAll("\\,", "");

			if ( !min.contains(".") ) min = min.concat( ".0" );
			if ( !max.contains(".") ) max = max.concat( ".0" );

            Util.indent(1, code);
            code.add(0, "if ((" + doubleVar + " >= " + min + ") && (" + doubleVar + " <= " + max + "))");
            code.add(1, "{");
            code.add("}");
	}
	
	/**
	 * This method returns the double value of scaled integer that is initialized to 0 
	 * 
	 * @param fieldType
	 * @return
	 */
	public String getInitValue(String fieldType)
	{
            String min = scaleRange.getRealLowerLimit().replaceAll("\\,", "");
            String max = scaleRange.getRealUpperLimit().replaceAll("\\,", "");

			if ( !min.contains(".") ) min = min.concat( ".0" );
			if ( !max.contains(".") ) max = max.concat( ".0" );

            if (fieldType.startsWith("unsigned"))
            {
        	return min; 
            }
            else
            {
        	return Double.toString((Double.valueOf(max) + Double.valueOf(min)) / 2.0);
            }
	}
	
	/**
	 * Returns the Vector<String> that lists all the invalid values
	 * to be used for testing
	 * 
	 * @return
	 */
	public List<String> getInvalidRangeValues()
	{
            return invalidValues;
	}

	/**
	 * Returns the Vector<String> that lists all the valid values
	 * to be used for testing
	 * 
	 * @return
	 */
	public List<String> getValidRangeValues()
	{
            return  validValues;
	}
	
	/**
	 * This class generates the code necessary to run a CppUnit test on the generated code
	 */
	public void getUnitTestCode(String fieldType, String variableName, String instName, boolean isPointer, 
            List<String> strVector)
	{
            String min = scaleRange.getRealLowerLimit().replaceAll("\\,", "");
            String max = scaleRange.getRealUpperLimit().replaceAll("\\,", "");
            double maxNum = Double.parseDouble(max);
            double minNum = Double.parseDouble(min);
            double midNum = (maxNum - minNum) / 2;
            double initValue;
            String below = Double.toString(minNum - midNum);
            String above = Double.toString(maxNum +  midNum);
            String mid = Double.toString(midNum);

            if (codeType ==  CodeLines.CodeType.C_PLUS_PLUS)
            {
        	if (instName == null)
        	{
                    instName = "";
        	}
        	
        	if (!instName.equals(""))
        	{
                    if (isPointer)
                    {
                        instName += "->";
                    }
                    else
                    {
                        instName += ".";
                    }
        	}
        	
	        if (fieldType.startsWith("unsigned"))
	        {
	        	initValue = Double.valueOf(min); 
	        }
	        else
	        {
	        	initValue = (Double.valueOf(max) + Double.valueOf(min)) / 2.0;
	        }
	        
	        
			// if ScaleRange
			// if minRange = inclusive
			// if maxRange = inclusive
	        strVector.add("double " +  variableName + "_1 = " + below + ";	// Below Range = Min - Mid Range");
	        strVector.add("double " +  variableName + "_2 = " + min + ";	// Mininum");
	        strVector.add("double " +  variableName + "_3 = " + mid + ";	// Mid Range");
	        strVector.add("double " +  variableName + "_4 = " + max + ";	// Maximum");
	        strVector.add("double " +  variableName + "_5 = " + above + ";	// Above Range = Max + Mid Range");
	        strVector.add("");
	        strVector.add("CPPUNIT_ASSERT(" + instName + "get" + variableName + "() == " +  initValue +  ");");
	        strVector.add("");
			strVector.add("CPPUNIT_ASSERT(" + instName + "set" + variableName + "(" + variableName + "_1) == 1);");
			strVector.add("CPPUNIT_ASSERT(" + instName + "get" + variableName + "() != " + variableName + "_1);");
			strVector.add("CPPUNIT_ASSERT(" + instName + "get" + variableName + "() == " + initValue + ");");
			strVector.add("");
			strVector.add("CPPUNIT_ASSERT(" + instName + "set" + variableName + "(" + variableName + "_2) == 0);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() == " +  variableName + "_2);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_1);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_3);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_4);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_5);");
			strVector.add("");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "set" + variableName + "(" + variableName + "_3) == 0);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() == " + variableName + "_3);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_1);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_2);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_4);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_5);");
			strVector.add("");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "set" + variableName + "(" + variableName + "_4) == 0);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() == " +  variableName + "_4);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_1);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_2);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_3);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_5);");
			strVector.add("");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "set" + variableName + "(" + variableName + "_5) == 1);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() != " +  variableName + "_5);");
			strVector.add("CPPUNIT_ASSERT" + "(" + instName + "get" + variableName + "() == " +  variableName + "_4);");
        }
	}
}
