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
class PseudoStartState
{
	List<ValidatorResult> results = null;

	PseudoStartState()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.PseudoStartState )
		{
			// cast object to pseudo start state
			org.jts.jsidl.binding.PseudoStartState pss = (org.jts.jsidl.binding.PseudoStartState)toValidate;

			// update path
			updatePath( pss, path );

			// validate PseudoStartState
			validatePseudoStartState( sdefs, pss, path );
		}
		else
		{
			// no successors
		}

		return results;
	}

	/**
	 * Updates the error path to include the pseudo start state
	 * @param state
	 * @param path
	 */
	private void updatePath( org.jts.jsidl.binding.PseudoStartState pss, StringBuffer path )
	{
		path.append(": [PseudoStartState]");
	}

	/**
	 * Validates the pseudo start state by verifying
	 * -has a start state
	 * -has a simple transition
	 * -valid end state for transition
	 * -start state is identical to parent service
	 * and validates the pseudo start state's
	 * -state
	 * -transition
	 * Expects a non-null pseudo start state
	 * @param sdefs
	 * @param pss
	 * @param successorPath
	 */
	private void validatePseudoStartState( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.PseudoStartState pss, StringBuffer successorPath )
	{
		//---------- must have a state ----------//
		if( !verifyPseudoStartStateStateExists( pss, successorPath ) )
			return;

		//---------- must have a simple transition ----------//
		if( !verifyPseudoStartStateTransitionExists( pss, successorPath ) )
			return;

		// Validate the state cell within the pseudo start state
		if( pss.getState() != null )
		{
			org.jts.jsidl.binding.MxCell scell = pss.getState().getMxCell();
			if( scell != null )
			{
				results.addAll( (new MxCell()).validate( sdefs, scell, new StringBuffer( successorPath ) ) );
			}
		}

		// Validate the transition cell within the pseudo start state
		if( pss.getTransition() != null )
		{
			org.jts.jsidl.binding.MxCell tcell = pss.getTransition().getMxCell();
			if( tcell != null )
			{
				results.addAll( (new MxCell()).validate( sdefs, tcell, new StringBuffer( successorPath ) ) );
			}
		}
	}

	/**
	 * Verifies that the pseudo start state has a state
	 * Expects pseudo start state is non-null
	 * @param pss
	 * @param successorPath
	 * @return
	 */
	private boolean verifyPseudoStartStateStateExists( org.jts.jsidl.binding.PseudoStartState pss, StringBuffer successorPath )
	{
		if( pss.getState() == null )
		{
			results.add( new ValidatorResult( "Pseudo start state must be connected to a state", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
		}

		return true;
	}

	/**
	 * Verifies that the pseudo start state has a simple transition
	 * Expects pseudo start state is non-null
	 * @param pss
	 * @param successorPath
	 * @return
	 */
	private boolean verifyPseudoStartStateTransitionExists( org.jts.jsidl.binding.PseudoStartState pss, StringBuffer successorPath )
	{
		if( pss.getTransition() == null )
		{
			// TODO: check if simple?
			results.add( new ValidatorResult( "Pseudo start state must be connected with a transition", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO: continue?
		}

		return true;
	}

}
