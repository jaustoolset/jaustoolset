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

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Arfath Pasha
 *
 */
class Parameter
{
	List<ValidatorResult> results = null;

	Parameter()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.Parameter )
		{
			// cast object to parameter
			org.jts.jsidl.binding.Parameter parameter = (org.jts.jsidl.binding.Parameter)toValidate;

			// update path
			updatePath( parameter, path  );

			// validate parameter
			validateParameter( sdefs, parameter, path );
		}
		else
		{
			// no successors
		}

		return results;
	}

	/**
	 * Updates the error path to include the parameter
	 * @param parameter
	 * @param path
	 */
	private void updatePath( org.jts.jsidl.binding.Parameter parameter, StringBuffer path )
	{
		path.append(": "+parameter.getValue()+"[Parameter]");
	}

	/**
	 * Validates the Parameter by verifying
	 * -parameter's type
	 * -parameter's value
	 * Expects a non-null parameter
	 * @param sdefs
	 * @param parameter
	 * @param successorPath
	 */
	private void validateParameter( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.Parameter parameter, StringBuffer successorPath )
	{
		//---------- parameter type must be valid C++ parameter type----------//
		if( !verifyParameterType( sdefs, parameter.getType(), successorPath) )
			return;

		//---------- parameter value must be a valid C identifier----------//
		if( !verifyParameterValue( parameter.getValue(), successorPath) )
			return;
	}

	/**
	 * Verifies the parameter type name is part of a message or is a string
	 * Accepts a null parameterType
	 * @param parameterType
	 * @param successorPath
	 * @return
	 */
	private boolean verifyParameterType( List<org.jts.jsidl.binding.ServiceDef> sdefs, String parameterType, StringBuffer successorPath )
	{
		// Make sure the string is valid
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameType( parameterType ) )
		{
			results.add( new ValidatorResult( "Parameter type must be a valid identifier in the C++ programming language", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return true;
		}
		
		// check for 'unsigned byte' type
		if( parameterType.compareTo( "unsigned byte" ) == 0 )
		{
			return true;
		}
		// check for 'unsigned short' type
		else if( parameterType.compareTo( "unsigned short" ) == 0 )
		{
			return true;
		}
		// check for 'unsigned int' type
		else if( parameterType.compareTo( "unsigned int" ) == 0 )
		{
			return true;
		}
		// check for 'unsigned long' type
		else if( parameterType.compareTo( "unsigned long" ) == 0 )
		{
			return true;
		}
		// check for 'string' type
		else if( parameterType.compareTo( "string" ) == 0 )
		{
			return true;
		}

		// check for message/event part
		if( !org.jts.pbValidator.ValidatorUtils.isNameMatchingMessageOrEventPart( sdefs, parameterType ) )
		{
			// just display warning when no sdefs added to validate against
			if( sdefs.isEmpty() )
			{
				results.add( new ValidatorResult( "Parameter type must be a string literal or part of a message/internal event:"+parameterType, successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
			}
			else
			{
				results.add( new ValidatorResult( "Parameter type must be a string literal or part of a message/internal event:"+parameterType, successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			}
			return true;
		}

		return true;
	}

	/**
	 * Verifies that the parameter value string is a valid C identifier
	 * Accepts a null parameterValue
	 * @param parameterValue
	 * @param successorPath
	 * @return
	 */
	private boolean verifyParameterValue( String parameterValue, StringBuffer successorPath )
	{
		// Make sure the string exists
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameValue( parameterValue ))
		{
			results.add( new ValidatorResult( "Parameter value must be a valid identifier in the C programming language", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
		}

		return true;
	}
}
