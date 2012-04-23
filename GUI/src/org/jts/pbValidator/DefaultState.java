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
class DefaultState
{
	List<ValidatorResult> results = null;

	DefaultState()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.DefaultState )
		{
			// cast object to default state
			org.jts.jsidl.binding.DefaultState ds = (org.jts.jsidl.binding.DefaultState)toValidate;

			// update path
			updatePath( ds, path  );

			// validate default state
			validateDefaultState( sdefs, null, ds, path );
		}
		else
		{
			// validate successors
			results.addAll( (new Transition()).validate( sdefs, toValidate, path ) );

			results.addAll( (new DefaultTransition()).validate( sdefs, toValidate, path ) );

			results.addAll( (new MxCell()).validate( sdefs, toValidate, path ) );
		}

		return results;
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.StateMachine sm, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.DefaultState )
		{
			// cast object to default state
			org.jts.jsidl.binding.DefaultState ds = (org.jts.jsidl.binding.DefaultState)toValidate;

			// update path
			updatePath( ds, path  );

			// validate default state
			validateDefaultState( sdefs, sm, ds, path );
		}
		else
		{
			// validate successors
			results.addAll( (new Transition()).validate( sdefs, toValidate, path ) );

			results.addAll( (new DefaultTransition()).validate( sdefs, toValidate, path ) );

			results.addAll( (new MxCell()).validate( sdefs, toValidate, path ) );
		}

		return results;
	}

	private void updatePath( org.jts.jsidl.binding.DefaultState ds, StringBuffer path )
	{
		path.append(": [DefaultState]");
	}

	private void validateDefaultState( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.StateMachine sm, org.jts.jsidl.binding.DefaultState ds, StringBuffer successorPath )
	{
		List<org.jts.jsidl.binding.Transition> transitions = ds.getTransition();
		if( transitions != null )
		{
			for( org.jts.jsidl.binding.Transition transition : transitions )
			{
				results.addAll( (new Transition()).validate( sdefs, transition, new org.jts.pbValidator.StateMap( sm ), new StringBuffer( successorPath ) ) );
			}
		}

		List<org.jts.jsidl.binding.DefaultTransition> dts = ds.getDefaultTransition();
		if( dts != null )
		{
			for( org.jts.jsidl.binding.DefaultTransition dt : dts )
			{
				results.addAll( (new DefaultTransition()).validate( sdefs, dt, new org.jts.pbValidator.StateMap( sm ), new StringBuffer( successorPath ) ) );
			}
		}

		org.jts.jsidl.binding.MxCell cell = ds.getMxCell();
		if( cell != null )
		{
			results.addAll( (new MxCell()).validate( sdefs, cell, new StringBuffer( successorPath ) ) );
		}
	}
}
