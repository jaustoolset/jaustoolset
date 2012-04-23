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
class Start
{
	List<ValidatorResult> results = null;

	Start()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.Start )
		{
			// cast object to state
			org.jts.jsidl.binding.Start start = (org.jts.jsidl.binding.Start)toValidate;

			// validate start
			validateStart( sdefs, start, null, path );
		}
		else 
		{
			// no successors
		}

		return results;
	}
	
	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, org.jts.jsidl.binding.ProtocolBehavior pb, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.Start )
		{
			// cast object to state
			org.jts.jsidl.binding.Start start = (org.jts.jsidl.binding.Start)toValidate;

			// validate start
			validateStart( sdefs, start, pb, path );
		}
		else 
		{
			// no successors
		}

		return results;
	}

	private void validateStart( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.Start start, org.jts.jsidl.binding.ProtocolBehavior pb, StringBuffer successorPath )
	{
		//---------- name of state machine must be valid C identifier ----------//
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameType( start.getStateMachineName() ) )
		{
			results.add( new ValidatorResult( "Start state machine must be a valid C identifier", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return;
		}

		//---------- name of state must be valid C identifier ----------//
		// check valid state name
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameType( start.getStateName() ) )
		{
			results.add( new ValidatorResult( "Start state name must be a valid C identifier", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return;
		}

		//---------- per unfixed SMC bug, state names must be capitalized ---------//
		verifyStateNameIsCapital( start, successorPath );

		//---------- name of state machine must be valid within context ----------//
		//---------- warning given when not validating with parent protocol behavior ----------//
		if( pb == null)
		{
			results.add( new ValidatorResult( "State machine must be defined to validate a start", successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
		}
		else
		{
			if( pb.getStateMachine() == null )
			{
				results.add( new ValidatorResult( "State machine must be defined to validate a start", successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
				return;
			}

			int count = 0;
			org.jts.jsidl.binding.StateMachine matchedStateMachine = null;

			for( org.jts.jsidl.binding.StateMachine sm : pb.getStateMachine() )
			{
				if( sm.getName().compareTo( start.getStateMachineName() ) == 0 )
				{
					count++;
					matchedStateMachine = sm;
				}
			}

			if( count != 1 )
			{
				results.add( new ValidatorResult( "A start must have a valid state machine within the protocol behavior", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
				return;
			}
			else
			{
				//---------- start state name must be valid within the context of the matched state name ----------//
				if( !org.jts.pbValidator.ValidatorUtils.isStateInStateMachine( matchedStateMachine, start.getStateName() ) )
				{
					results.add( new ValidatorResult( "A start must have a valid state within the matching state machine", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
					return;
				}
			}
			
			//---------- check state not a default state and not other element ----------//
			if( start.getStateName().compareTo("default_state") == 0 )
			{
				results.add( new ValidatorResult( "A state that contains nested states must have a pseudo start state", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
				// TODO:continue validation?
			}
		}
	}

	/**
	 * SMC has an unfixed bug which requires state names be be capital.  This method
	 * checks for a capital start and changes it to upper case when the state name starts
	 * with lower case.  A warning is then sent to the user notifying of the change
	 * @param state
	 * @param successorPath
	 */
	private void verifyStateNameIsCapital( org.jts.jsidl.binding.Start start, StringBuffer successorPath )
	{
		try
		{
			String stateName = start.getStateName();

			String upperCase = stateName.substring( 0, 1 ).toUpperCase().concat( stateName.substring( 1 ) );

			if( stateName.compareTo( upperCase ) != 0 )
			{
				// change the name of the state
				start.setStateName( upperCase );

				results.add( new ValidatorResult( "State names must be capital, name modified to " + upperCase, successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
			}
		}
		catch( Exception e )
		{
			System.out.println("State validation: verifyStateNameIsCapital: " + e.toString() );
		}
	}

}
