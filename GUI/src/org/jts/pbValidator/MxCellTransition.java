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
 * @author Drew Lucas
 *
 */
class MxCellTransition
{
	List<ValidatorResult> results = null;

	MxCellTransition()
	{
		results = new ArrayList< ValidatorResult >();
	}

	List<ValidatorResult> validate( com.mxgraph.model.mxCell cell )
	{
		if( org.jts.pbValidator.ValidatorUtils.isMxTransition( cell ) )
		{
			if( !verifyTargetIsState( cell ) )
			{
				return results;
			}
			
			if( !verifySourceIsState( cell ) )
			{
				return results;
			}
	
			if( !verifyInternalSourceTargetSame( cell ) )
			{
				return results;
			}
		}
		else
		{
			// transition cell will have no children to validate
		}

		return results;
	}

	private StringBuffer getPath( com.mxgraph.model.mxCell cell )
	{
		StringBuffer buffer = new StringBuffer();

		// continue up chain until the root is reached ( don't process it )
		while( cell.getParent() != null && cell.getParent().getParent() != null )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxStateMachine( cell ) )
			{
				String value = ( String ) cell.getValue();
				if( value.indexOf( ";" ) != -1 )
				{
					buffer.insert( 0, "[StateMachine] " + value.substring( 0, value.indexOf( ";" ) ) );
				}
				else
				{
					buffer.insert( 0, "[StateMachine] " + value );
				}
			}
			else if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( cell ) )
			{
				buffer.insert( 0, "[PseudoStartState] " );
			}
			else if( org.jts.pbValidator.ValidatorUtils.isMxState( cell ) )
			{
				String value = ( String ) cell.getValue();
				
				if( value.indexOf( ";" ) != -1 )
				{
					buffer.insert( 0, "[State] " + value.substring( 0, value.indexOf( ";" ) ) );
				}
				else
				{
					buffer.insert( 0, "[State] " + value );
				}
			}
			else if( org.jts.pbValidator.ValidatorUtils.isMxTransition( cell ) )
			{
				buffer.insert( 0, "[Transition] " );
			}

			buffer.insert( 0, " : " );

			cell = ( com.mxgraph.model.mxCell ) cell.getParent();
		}
		
		return buffer;
	}

	private boolean verifyTargetIsState( com.mxgraph.model.mxCell cell )
	{
		// deal with pop case, it does not need a target
		if( org.jts.pbValidator.ValidatorUtils.isMxPopTransition( cell ) ||
		    org.jts.pbValidator.ValidatorUtils.isMxDefaultPopTransition( cell ) )
		{
			return true;
		}

		if( cell.getTarget() == null || 
				!org.jts.pbValidator.ValidatorUtils.isMxState( cell.getTarget() ) &&
				!org.jts.pbValidator.ValidatorUtils.isMxDefaultState( cell.getTarget() ) )
		{
			results.add( new ValidatorResult( "Transition must be connected to a target state", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
			return false;
		}

		return true;
	}

	private boolean verifySourceIsState( com.mxgraph.model.mxCell cell )
	{
		if( cell.getSource() == null || 
				!org.jts.pbValidator.ValidatorUtils.isMxState( cell.getSource() ) && 
				!org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( cell.getSource() ) && 
				!org.jts.pbValidator.ValidatorUtils.isMxDefaultState( cell.getSource() ) )
		{
			results.add( new ValidatorResult( "Transition must be connected to a source state or pseudo start state", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
			return false;
		}

		return true;
	}

	private boolean verifyInternalSourceTargetSame( com.mxgraph.model.mxCell cell )
	{
		if( org.jts.pbValidator.ValidatorUtils.isMxInternalTransition( cell ) )
		{
			String sourceId = cell.getSource().getId();
			String targetId = cell.getTarget().getId();

			if( sourceId.compareTo( targetId ) != 0 )
			{
				results.add( new ValidatorResult( "Internal transition source and target must be the same state", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
				return false;
			}
		}

		return true;
	}

}