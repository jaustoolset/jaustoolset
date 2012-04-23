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

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jts.validator.ValidatorException;

/**
 *
 * @author Arfath Pasha
 *
 */
class Guard
{
	List<ValidatorResult> results = null;

	Guard()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.Guard )
		{
			// cast object to guard
			org.jts.jsidl.binding.Guard guard = (org.jts.jsidl.binding.Guard)toValidate;

			// update path
			updatePath( guard, path  );

			// validate guard
			validateGuard( sdefs, null, guard, path );
			//results.add( new ValidatorResult( "Validating a guard without its transition is not supported", path.toString(), ValidatorResult.TYPE_WARNING ) );
		}
		else
		{
			// no successors
		}

		return results;
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.Transition transition, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.Guard )
		{
			// cast object to guard
			org.jts.jsidl.binding.Guard guard = (org.jts.jsidl.binding.Guard)toValidate;

			// update path
			updatePath( guard, path  );

			// validate guard
			validateGuard( sdefs, transition, guard, path );
		}
		else
		{
			// no successors
		}

		return results;
	}

	/**
	 * Updates the error path to include the guard
	 * @param guard
	 * @param path
	 */
	private void updatePath( org.jts.jsidl.binding.Guard guard, StringBuffer path )
	{
		if( guard != null && guard.getCondition() != null )
		{
			path.append(": " + guard.getCondition() + "[Guard]");
		}
		else
		{
			path.append(": [Guard]");
		}
	}

	/**
	 * Validates the guard by verifying the guard condition
	 * Expects a non-null guard
	 * @param sdefs
	 * @param transition
	 * @param guard
	 * @param successorPath
	 */
	private void validateGuard( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.Transition transition, org.jts.jsidl.binding.Guard guard, StringBuffer successorPath )
	{
		ByteArrayInputStream guardInputStream = new ByteArrayInputStream(guard.getCondition().getBytes());
		
		org.jts.validator.parsers.GuardParser gp = new org.jts.validator.parsers.GuardParser(guardInputStream);

		try
		{
			gp.Condition();
		}
		catch(Exception e)
		{
			results.add( new ValidatorResult( "Guard function name must be a valid identifier in the C programming language", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			//System.out.println("Guard Parse Exception: " + e);
			return;
		}
		catch(Error err)
		{
			results.add( new ValidatorResult( "Guard function name must be a valid identifier in the C programming language", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			//System.out.println("Guard Parse Error: " + err);
			return;
		}
		
		//---------- condition must proper function defined within C language ----------//
		if( !verifyGuardCondition( transition, guard.getCondition(), successorPath) )
			return;
	}

	/**
	 * Verifies that the guard's condition string is a correctly formated string
	 * Expects the following
	 * -transition is non-null
	 * @param transition
	 * @param condition
	 * @param successorPath
	 * @return
	 */
	private boolean verifyGuardCondition( org.jts.jsidl.binding.Transition transition, String condition, StringBuffer successorPath )
	{
		// when no transition is avaliable, continue
		if( transition == null )
		{
			return true;
		}
		
		condition = preconditionCondition(condition);

		if( condition == null || condition.length() == 0 || condition.indexOf("(") == -1 || condition.indexOf(")") == -1 )
		{
			// error
			return false;
		}

		int startArg = condition.indexOf("(");
		int endArg = condition.indexOf(")");
		int first = 0;

		while( true )
		{
			// error if
			// ( not found
			// ) not found
			// argument start out of range
			// missing/incorrect bracket order
			if( startArg == -1 || endArg == -1 || condition.endsWith("(") || startArg > endArg )
			{
				// error
				return false;
			}

			String functionName = condition.substring(0, startArg);
			if( !verifyGuardFunctionName( first++, functionName, successorPath ) )
				return false;

			String arguments = condition.substring(startArg + 1, endArg);
			if( !verifyGuardArgumentNames( transition, arguments, successorPath ) )
				return false;

			condition = condition.substring(endArg + 1);

			// end condition
			if( condition.length() == 0)
				break;

			startArg = condition.indexOf("(");
			endArg = condition.indexOf(")");
		}

		return true;
	}

	/**
	 * Conditions a string by removing whitespaces
	 * also removes content within string literals to leave "''"
	 * ex: " abc( 'arg1' ) " -> "abc('')"
	 * @param condition
	 * @return
	 */
	private String preconditionCondition(String condition)
	{
		if(condition == null)
			return null;

		// remove all whitespace from condition
		// NOTE: we are worried about whitespaces within string literals
		condition = condition.replaceAll("\\s", "");

		// first we need to parse out all strings between ''
		if( condition.indexOf("'") != -1 )
		{
			// remove backslash which can mess up our parsing
			condition = condition.replace("\\", "");

			// remove single quote which can mess up our parsing
			condition = condition.replace("\\'", "");

			// using split so check for end case
			if(condition.endsWith("'"))
			{
				condition += " ";
			}

			// split condition and then reform leaving out string literals
			String[] fragments = condition.split("'");

			String compressedCondition = "";

			// keep odd interval fragments
			for( int i = 0; i < fragments.length; i++)
			{
				// EVEN
				if( i % 2 == 0 )
				{
					compressedCondition += fragments[i];
				}
				// ODD
				else
				{
					compressedCondition += "''";
				}
			}

			return compressedCondition;
		}

		return condition;
	}

	/**
	 * Verifies that a guard function name has
	 * -zero or one '!' boolean operators in front of the function name
	 * -that the function name is a valid C identifier
	 * Accepts null functionNames
	 * Expects the following
	 * -the functionName is flattened(whitespaces removed, string literals replaced with "''")
	 * @param functionName
	 * @param successorPath
	 * @return
	 */
	private boolean verifyGuardFunctionName( int first, String functionName, StringBuffer successorPath )
	{
		// Make sure the string exists
		if( functionName == null || functionName.length() == 0 )
		{
			results.add( new ValidatorResult( "Guard function name have a name", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return false;
		}
		// if first guard in condition, it can't have logical qualifiers besides negation
		else if( first == 0)
		{
			// prefix can be optional [!]
			// function name must be a valid c function name
			if(!Pattern.matches("[!]?[a-zA-Z_]+[a-zA-Z_0-9.]*" , functionName ))
			{
				results.add( new ValidatorResult( "Guard function name must be a valid identifier in the C programming language: " + functionName, successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
				return false;
			}
		}
		// prefix can be optional [==] or [!=] or [<=] or [>=] or [||] or [&&] or [>] or [<] and optional [!]
		// function name must be a valid c function name
		else if( !Pattern.matches("((==)|(!=)|(\\|\\|)|(&&))?[!]?[a-zA-Z_]+[a-zA-Z_0-9.]*" , functionName ))
		{
			results.add( new ValidatorResult( "Guard function name must be a valid identifier in the C programming language: " + functionName, successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return false;
		}

		return true;
	}

	/**
	 * Verifies each argument as a valid C identifier
	 * Accepts null guardNames
	 * Expects the following
	 * -the guardNames is flattened(whitespaces removed, string literals replaced with "''")
	 * -transition is non-null
	 * ex: "arg1,arg2,arg3"
	 * @param functionName
	 * @param successorPath
	 * @return
	 */
	private boolean verifyGuardArgumentNames( org.jts.jsidl.binding.Transition transition, String guardNames, StringBuffer successorPath )
	{
		// guard arguments are not required
		if( guardNames == null || guardNames.length() == 0 )
		{
			//results.add( new ValidatorResult( "Guard function arguments have a name", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
		}
		else
		{
			String[] guards = guardNames.split(",");

			for( String guard : guards )
			{
				if( guard.compareTo("''") != 0 )
				{
					if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameValue( guard ) )
					{
						results.add( new ValidatorResult( "Guard argument name must be a valid identifier in the C programming language", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
						// TODO:continue validation?
					}
	
					// check each guard was identified in the transition parameter list
					if( !org.jts.pbValidator.ValidatorUtils.isArgumentInParameters( transition, guard ) )
					{
						results.add( new ValidatorResult( "Guard argument must be defined within its transition parameter list", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
						// TODO:continue validation?
					}
				}
			}
		}

		return true;
	}

}
