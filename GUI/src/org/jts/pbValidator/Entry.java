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
class Entry
{
	List<ValidatorResult> results = null;

	Entry()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.Entry )
		{
			// cast object to entry
			org.jts.jsidl.binding.Entry entry = (org.jts.jsidl.binding.Entry)toValidate;

			// update path
			updatePath( entry, path  );

			// validate Entry
			validateEntry( sdefs, entry, path );
		}
		else
		{
			// validate successors
			results.addAll( (new Action()).validate( sdefs, toValidate, path ) );

			results.addAll( (new SendAction()).validate( sdefs, toValidate, path ) );
		}

		return results;
	}

	/**
	 * Updates the error path to include the entry
	 * @param entry
	 * @param path
	 */
	private void updatePath( org.jts.jsidl.binding.Entry entry, StringBuffer path )
	{
		path.append(": [Entry]");
	}

	/**
	 * Validates the entry by validating the entry's
	 * -actions
	 * -send actions
	 * Expects a non-null entry
	 * @param sdefs
	 * @param entry
	 * @param successorPath
	 */
	private void validateEntry( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.Entry entry, StringBuffer successorPath )
	{
		// validate zero or more actions or send actions within the entry
		List actionOrSendActions = entry.getActionOrSendAction();
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
