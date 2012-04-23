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
class Exit
{
	List<ValidatorResult> results = null;

	Exit()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.Exit )
		{
			// cast object to exit
			org.jts.jsidl.binding.Exit exit = (org.jts.jsidl.binding.Exit)toValidate;

			// update path
			updatePath( exit, path  );

			// validate Exit
			validateExit( sdefs, exit, path );
		}
		else
		{
			// validate successors
			Action vaction = new Action();
			results.addAll( vaction.validate( sdefs, toValidate, path ) );

			SendAction vsa = new SendAction();
			results.addAll( vsa.validate( sdefs, toValidate, path ) );
		}

		return results;
	}

	/**
	 * Updates the error path to include the exit
	 * @param exit
	 * @param path
	 */
	private void updatePath( org.jts.jsidl.binding.Exit exit, StringBuffer path )
	{
		path.append(": [Exit]");
	}

	/**
	 * Validates the exit by validating the exit's
	 * -actions
	 * -send actions
	 * Expects a non-null exit
	 * @param sdefs
	 * @param entry
	 * @param successorPath
	 */
	private void validateExit( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.Exit exit, StringBuffer successorPath )
	{
		// validate zero or more actions or send actions within the exit
		List actionOrSendActions = exit.getActionOrSendAction();
		if( actionOrSendActions != null )
		{
			for( Object actionOrSendAction : actionOrSendActions )
			{
				results.addAll( (new Action()).validate( sdefs, actionOrSendAction, new StringBuffer( successorPath ) ) );
				results.addAll( (new SendAction()).validate( sdefs, actionOrSendAction, new StringBuffer( successorPath ) ) );
			}
		}
	}
}
