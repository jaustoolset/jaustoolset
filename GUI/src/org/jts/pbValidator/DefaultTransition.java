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
class DefaultTransition
{
	List<ValidatorResult> results = null;

	DefaultTransition()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.DefaultTransition )
		{
			// cast object as a default transition
			org.jts.jsidl.binding.DefaultTransition dt = (org.jts.jsidl.binding.DefaultTransition)toValidate;

			// update path
			updatePath( dt, path  );

			// validate default transition
			validateDefaultTransition( sdefs, dt, null, path );
		}
		else
		{
			// no successors
		}

		return results;
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, org.jts.pbValidator.StateMap map, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.DefaultTransition )
		{
			// cast object as a default transition
			org.jts.jsidl.binding.DefaultTransition dt = (org.jts.jsidl.binding.DefaultTransition)toValidate;

			// update path
			updatePath( dt, path  );

			// validate default transition
			validateDefaultTransition( sdefs, dt, map, path );
		}
		else
		{
			// no successors
		}

		return results;
	}

	private void updatePath( org.jts.jsidl.binding.DefaultTransition dt, StringBuffer path )
	{
		if( dt != null && dt.getGuard() != null && dt.getGuard().getCondition() != null )
		{
			path.append(": " + dt.getGuard().getCondition() + "[DefaultTransition]");
		}
		else if( dt != null && dt.getActionOrSendAction() != null )
		{
			String list = "";
			
			for( Object obj : dt.getActionOrSendAction() )
			{
				if( obj instanceof org.jts.jsidl.binding.Action )
				{
					org.jts.jsidl.binding.Action action = ( org.jts.jsidl.binding.Action )obj;
					list += " " + action.getName();
				}
				else if( obj instanceof org.jts.jsidl.binding.SendAction )
				{
					org.jts.jsidl.binding.SendAction sendAction = ( org.jts.jsidl.binding.SendAction )obj;
					list += " " + sendAction.getName();
				}
			}
			
			path.append( ": " + list + "[DefaultTransition]" );
		}
		else
		{
			path.append(": [DefaultTransition]");
		}
	}

	private void validateDefaultTransition( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.DefaultTransition dt, org.jts.pbValidator.StateMap map, StringBuffer successorPath )
	{
		//---------- transition must be one of the types ----------//
		if( !org.jts.pbValidator.ValidatorUtils.verifyTransitionHasType( dt, results, successorPath ) )
		{
			// TODO: continue validation?
			return;
		}

		//---------- simple definition must be valid ----------//
		if( !org.jts.pbValidator.ValidatorUtils.verifySimpleTransition( dt.getSimple(), map, results, successorPath ) )
		{
			// TODO: continue validation?
			return;
		}

		//---------- push definition must be valid ----------//
		if( !org.jts.pbValidator.ValidatorUtils.verifyPushTransition( dt.getPush(), map, results, successorPath ) )
		{
			// TODO: continue validation?
			return;
		}

		//---------- pop definition must be valid ----------//
		if( !org.jts.pbValidator.ValidatorUtils.verifyPopTransition( null, dt.getPop(), results, successorPath ) )
		{
			// TODO: continue validation?
			return;
		}

		//---------- internal definition must be valid ----------//
		if( !org.jts.pbValidator.ValidatorUtils.verifyInternalTransition( dt.getInternal(), results, successorPath ) )
		{
			// TODO: continue validation?
			return;
		}

		org.jts.jsidl.binding.Guard guard = dt.getGuard();
		if( guard != null )
		{
			results.addAll( (new Guard()).validate( sdefs, guard, new StringBuffer( successorPath ) ) );
		}

		List actionOrSendActions = dt.getActionOrSendAction();
		if( actionOrSendActions != null )
		{
			for( Object actionOrSendAction : actionOrSendActions )
			{
				results.addAll( (new Action()).validate( sdefs, actionOrSendAction, new StringBuffer( successorPath ) ) );
				results.addAll( (new SendAction()).validate( sdefs, actionOrSendAction, new StringBuffer( successorPath ) ) );
			}
		}

		org.jts.jsidl.binding.MxCell cell = dt.getMxCell();
		if( cell != null )
		{
			results.addAll( (new MxCell()).validate( sdefs, cell, new StringBuffer( successorPath ) ) );
		}

		// validate internal, simple, push, pop, end_state internally in this class
	}
}
