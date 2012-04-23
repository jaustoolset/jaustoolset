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
class SendAction
{
	List<ValidatorResult> results = null;

	SendAction()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List <org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.SendAction )
		{
			// cast object to sendAction
			org.jts.jsidl.binding.SendAction sa = (org.jts.jsidl.binding.SendAction)toValidate;

			// update path
			updatePath( sa, path  );

			// validate sendAction
			validateSendAction( sdefs, null, sa, path );
		}
		else
		{
			// no successors
		}

		return results;
	}

	/**
	 * Updates the error path to include the send action
	 * @param sa
	 * @param path
	 */
	private void updatePath( org.jts.jsidl.binding.SendAction sa, StringBuffer path )
	{
		path.append(": "+sa.getName()+"[SendAction]");
	}

	/**
	 * Validates the send action by verifying
	 * -action's name
	 * -action's arguments
	 * Expects a non-null action
	 * @param sdefs
	 * @param transition
	 * @param sendAction
	 * @param successorPath
	 */
	private void validateSendAction( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.Transition transition, org.jts.jsidl.binding.SendAction sendAction, StringBuffer successorPath )
	{
		//---------- name of send action must be valid ----------//
		if(!verifySendActionName(sendAction, successorPath))
			return;

		//---------- arguments of send action must be defined within transition that holds the action ----------//
		//---------- or when transition is null, there must only be constant arguments ----------//
		//if(!verifySendActionArguments(transition, sendAction, successorPath))
		//	return;
	}

	/**
	 * Verifies that the action's name is a valid C identifier
	 * Expects a non-null action
	 * @param sendAction
	 * @param successorPath
	 * @return
	 */
	private boolean verifySendActionName( org.jts.jsidl.binding.SendAction sendAction, StringBuffer successorPath )
	{
		String actionName = sendAction.getName();
		// Make sure the string exists
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameType( actionName ) )
		{
			results.add( new ValidatorResult( "Send Action name must be a valid identifier in the C programming language", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
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
	private boolean verifySendActionArguments( org.jts.jsidl.binding.Transition transition, org.jts.jsidl.binding.SendAction sendAction, StringBuffer successorPath )
	{
		if( !org.jts.pbValidator.ValidatorUtils.verifyActionArguments( transition, sendAction.getArgument() ) )
		{
			results.add( new ValidatorResult( "Send Action argument must be a constant or string or part of the transition parameters", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
		}

		return true;
	}

}
