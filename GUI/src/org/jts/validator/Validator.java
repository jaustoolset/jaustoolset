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

import java.util.List;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * This class contains a set of methods to check JSIDL binding objects for correct semantics.
 * @author Tom Galluzzo
 *
 */
public class Validator 
{

	/**
	 * Validates that all the JAXB objects within a list have unique names.
	 * @param list	a list of objects that each have a "getName" method. The getName method is invoked
	 * 				to determine if each object in the list has a unique name.
	 * @throws ValidatorException	if any object does not have a getName method, there are repeated object
	 * 								names or if an exception occurs when calling the "getName" method. 
	 */
	public static void validateUniqueNameList(List<?> list) throws ValidatorException
	{
		for(int i=0; i<list.size(); i++)	// For each object in the list
		{
			Object obj = list.get(i);		
			Class<?> cls = obj.getClass(); 	// Determine the objects class
			Method getName;
			try
			{
				getName = cls.getMethod("getName");	// Attempt to get the getName method
			}
			catch(NoSuchMethodException e)
			{
				throw new ValidatorException("Attempted to get getName method from class " + cls.getName());
			}
			
			// Check for unique ID
			for(int j=0; j < list.size(); j++)	// For each other object in the list
			{
				if(i == j)
				{
					continue;
				}
				
				try	// Try to get the other object's name and test to see if it is the same as current object
				{
					Object obj2 = list.get(j);		
					Class<?> cls2 = obj2.getClass(); 	// Determine the objects class
					Method getName2;
					try
					{
						getName2 = cls2.getMethod("getName");	// Attempt to get the getName method
					}
					catch(NoSuchMethodException e)
					{
						throw new ValidatorException("Attempted to get getName method from class " + cls.getName());
					}

					if(getName.invoke(obj).equals(getName2.invoke(obj2)))
					{
						String objectName = (String)getName.invoke(obj);
						throw new ValidatorException(" has multiple definitions of " + objectName);
					}
				}
				catch(ValidatorException e)	// Pass ValidatorException up
				{
					throw e;
				}
				catch(Exception e)	// Catch any exceptions that may have occurred from the getName method object
				{
					throw new ValidatorException("Exception (" + e + ") when calling getName of class " + cls.getName());
				}
			}			
			
		}
	}
	
	/**
	 * Checks if all the JAXB objects within two lists have unique names.
	 * @param list	a list of objects that each have a "getName" method. The getName method is invoked
	 * 				to determine if each object in the list has a unique name.
	 * @throws ValidatorException	if any object does not have a getName method, there are repeated object
	 * 								names or if an exception occurs when calling the "getName" method. 
	 */
	public static boolean namesListsUnique(List<?> list1, List<?> list2) throws ValidatorException
	{
		for(int i=0; i<list1.size(); i++)	// For each object in the list
		{
			Object obj = list1.get(i);		
			Class<?> cls = obj.getClass(); 	// Determine the objects class
			Method getName;
			try
			{
				getName = cls.getMethod("getName");	// Attempt to get the getName method
			}
			catch(NoSuchMethodException e)
			{
				throw new ValidatorException("Attempted to get getName method from class " + cls.getName());
			}
			
			// Check for unique ID
			for(int j=0; j < list2.size(); j++)	// For each other object in the list
			{				
				try	// Try to get the other object's name and test to see if it is the same as current object
				{
					Object obj2 = list2.get(j);		
					Class<?> cls2 = obj2.getClass(); 	// Determine the objects class
					Method getName2;
					try
					{
						getName2 = cls2.getMethod("getName");	// Attempt to get the getName method
					}
					catch(NoSuchMethodException e)
					{
						throw new ValidatorException("Attempted to get getName method from class " + cls.getName());
					}

					if(getName.invoke(obj).equals(getName2.invoke(obj2)))
					{
						return false;
					}
				}
				catch(ValidatorException e)	// Pass ValidatorException up
				{
					throw e;
				}
				catch(Exception e)	// Catch any exceptions that may have occurred from the getName method object
				{
					throw new ValidatorException("Exception (" + e + ") when calling getName of class " + cls.getName());
				}
			}			
			
		}
		
		return true;
	}

	/**
	 * Validates that a string contains a valid JSIDL name identifier string.
	 * @param name string containing the identifier to be checked.
	 * @throws ValidatorException if the name string is null, empty or invalid.
	 */
	public static void validateName(String name) throws ValidatorException
	{
        if( name == null || name.length() == 0 )	// Make sure the string exists
        {
        	throw new ValidatorException("Name is required....");
        }
        
        if( !Pattern.matches("[a-zA-Z_]+[a-zA-Z_0-9]*" , name ))	// Check the string pattern
        {
        	throw new ValidatorException("Name must be a valid identifier in the C programming language");
        }		
	}

	/**
	 * Validates that a string contains a valid URI.
	 * @param name string containing the identifier to be checked.
	 * @throws ValidatorException if the name string is null, empty or invalid.
	 */
	public static void validateURI(String uri) throws ValidatorException
	{
        if( uri == null || uri.length() == 0 )	// Make sure the string exists
        {
        	throw new ValidatorException("ID must be specified");
        }
        
        if( !Pattern.matches("[a-zA-Z_]+[a-zA-Z_0-9:.]*" , uri ))	// Check the string pattern
        {
        	throw new ValidatorException("ID must be a valid URI");
        }		
	}

	/**
	 * Validates that string contains a valid JSIDL version identifier string.
	 * @param vers string containing the version identifier to be checked.
	 * @throws ValidatorException if the version string is null, empty or invalid.
	 */
	public static void validateVersion(String vers) throws ValidatorException
	{
        if( vers == null || vers.length() == 0 )
        {
        	throw new ValidatorException("Version is required"); // Make sure the version string exists
        }
        
        if( !Pattern.matches("(0|[1-9]([0-9]*))\\.(0|[1-9]([0-9]*))"  , vers )) // Check the string pattern
        {
        	throw new ValidatorException("Version must be a valid identifier");
        }		
	}

	/**
	 * Validates that string contains a valid JSIDL version identifier string.
	 * @param vers string containing the version identifier to be checked.
	 * @throws ValidatorException if the version string is null, empty or invalid.
	 */
	public static boolean isConstantSting(String testString)
	{
        if( testString == null || testString.length() == 0 )
        {
        	return false;
        }
        
        if( !Pattern.matches("'.+'"  , testString )) // Check the string pattern
        {
        	return false;
        }
        
        return true;
	}
	
	/**
	 * Validates that string contains a valid JSIDL version identifier string.
	 * @param vers string containing the version identifier to be checked.
	 * @throws ValidatorException if the version string is null, empty or invalid.
	 */
	public static void validateFieldTypeValue(String fieldType, String value)
	{
		try
		{
			if(fieldType.equals("byte"))
			{
				Byte.parseByte(value);
			}
			else if(fieldType.equals("short integer"))
			{
				Short.parseShort(value);				
			}
			else if(fieldType.equals("integer"))
			{
				Integer.parseInt(value);				
			}
			else if(fieldType.equals("long integer"))
			{
				Long.parseLong(value);				
			}
			else if(fieldType.equals("unsigned byte"))
			{
				long lValue = Long.parseLong(value);
				if(lValue < 0 || lValue > 255)
				{
					throw new NumberFormatException();
				}
			}
			else if(fieldType.equals("unsigned short integer"))
			{
				long lValue = Long.parseLong(value);
				if(lValue < 0 || lValue > 65535)
				{
					throw new NumberFormatException();
				}
			}
			else if(fieldType.equals("unsigned integer"))
			{
				long lValue = Long.parseLong(value);
				if(lValue < 0 || lValue > 4294967295L)
				{
					throw new NumberFormatException();
				}
			}
			else if(fieldType.equals("unsigned long integer"))
			{
				long lValue = Long.parseLong(value);
				if(lValue < 0)
				{
					throw new NumberFormatException();
				}
			}
			else if(fieldType.equals("float"))
			{
				float fVal = Float.parseFloat(value);				
				if(fVal < Float.MIN_VALUE || fVal > Float.MAX_VALUE)
				{
					throw new NumberFormatException();
				}
			}
			else if(fieldType.equals("long float"))
			{
				double dVal = Double.parseDouble(value);				
				if(dVal < Double.MIN_VALUE || dVal > Double.MAX_VALUE)
				{
					throw new NumberFormatException();
				}
			}		
		}
		catch(NumberFormatException nfe)
		{
			throw new ValidatorException("Value: " + value + " is not in proper format for type: " + fieldType);
		}
	}
	
}
