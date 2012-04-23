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
import java.util.regex.Pattern;

/**
 *
 * @author Arfath Pasha
 *
 */
class Action
{
	List<ValidatorResult> results = null;

	Action()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.Action )
		{
			// cast object to action
			org.jts.jsidl.binding.Action action = (org.jts.jsidl.binding.Action)toValidate;

			// update path
			updatePath( action, path  );

			// validate action
			validateAction( sdefs, null, action, path );
		}
		else
		{
			// no successors
		}

		return results;
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.Transition transition, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.Action )
		{
			// cast object to action
			org.jts.jsidl.binding.Action action = (org.jts.jsidl.binding.Action)toValidate;

			// update path
			updatePath( action, path  );

			// validate action
			validateAction( sdefs, transition, action, path );
		}
		else
		{
			// no successors
		}

		return results;
	}

	/**
	 * Updates the error path to include the action
	 * @param action
	 * @param path
	 */
	private void updatePath( org.jts.jsidl.binding.Action action, StringBuffer path )
	{
		StringBuffer pbuf = new StringBuffer();
		List<org.jts.jsidl.binding.Argument> args = action.getArgument();

		if( args != null )
		{
			for( int ii=0; ii<args.size(); ii++ )
			{
				pbuf.append( args.get(ii).getValue() );
				if( ii < args.size() )
				{
					pbuf.append( ", " );
				}
			}
		}
		path.append(": "+action.getName()+"("+pbuf.toString()+")"+"[Action]");
	}

	/**
	 * Validates the action by verifying
	 * -action's name
	 * -action's arguments
	 * Expects a non-null action
	 * Expects a non-null transition
	 * @param sdefs
	 * @param transition
	 * @param action
	 * @param successorPath
	 */
	private void validateAction( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.Transition transition, org.jts.jsidl.binding.Action action, StringBuffer successorPath )
	{
		// validate arguments internally in this class

		//---------- name of action must be valid ----------//
		if( !verifyActionName( action, successorPath ) )
			return;

		//---------- arguments of action must be defined within transition that holds the action ----------//
		//---------- or only be constant arguments ----------//
		if( !verifyActionArguments( transition, action, successorPath ) )
			return;
	}

	/**
	 * Verifies that the action's name is a valid C identifier
	 * Expects a non-null action
	 * @param action
	 * @param successorPath
	 * @return
	 */
	private boolean verifyActionName( org.jts.jsidl.binding.Action action, StringBuffer successorPath )
	{
		String actionName = action.getName();
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameType( actionName ) )
		{
			results.add( new ValidatorResult( "Action name must be a valid identifier in the C programming language", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO: continue validation?
		}

		return true;
	}

	/**
	 * Verifies that all arguments for the action were defined in the transition's parameters
	 * Accepts a null transition
	 * Expects a non-null action
	 * @param transition
	 * @param action
	 * @param successorPath
	 * @return
	 */
	private boolean verifyActionArguments( org.jts.jsidl.binding.Transition transition, org.jts.jsidl.binding.Action action, StringBuffer successorPath )
	{
		if( !org.jts.pbValidator.ValidatorUtils.verifyActionArguments( transition, action.getArgument() ) )
		{
			results.add( new ValidatorResult( "Action argument must be a constant or string or part of the transition parameters", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
		}

		return true;
	}

}
