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

package org.jts.gui.mxGraphtoJAXB;

import org.jts.pbValidator.ValidatorResult;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mxgraph.io.mxCodec;

import java.util.ArrayList;
import java.util.List;

public class mxGraphToJAXBUtils
{
	public static Object convert(NodeList cellNodes, com.mxgraph.model.mxCell cell) throws Exception
	{
		if( cell == null )
			return null;

		if( cell.getStyle().startsWith( "finiteStateMachine" ) )
		{
            mxCodec codec = new mxCodec();
            Node node = codec.encode(cell);

            try
            {
            	org.jts.gui.mxGraphtoJAXB.mxStateMachine mxStateMachine = new org.jts.gui.mxGraphtoJAXB.mxStateMachine( node, cellNodes );
            	
            	return mxStateMachine.convert();
            }
            catch(org.jts.gui.mxGraphtoJAXB.parser.ParseException e)
            {
            	throw new Exception("MxFiniteStateMachine Parse Exception: " + e);
            }
		}
		/*
		else if( cell.getStyle().startsWith( "pseudoStartState" ) )
		{
			// TODO: don't convert because validation is done within mxgraph
			throw new Exception( "MxPseudoStartState Parse Exception: not supported yet" );
		}
		else if( cell.getStyle().compareTo( "state" ) == 0 )
		{
            mxCodec codec = new mxCodec();
            Node node = codec.encode(cell);

            try
            {
            	org.jts.gui.mxGraphtoJAXB.mxState mxState = new org.jts.gui.mxGraphtoJAXB.mxState( node, cellNodes );
            	
            	return mxState.convert();
            }
            catch(org.jts.gui.mxGraphtoJAXB.parser.ParseException e)
            {
            	throw new Exception("MxState Parse Exception: " + e);
            }

		}
		else if( cell.getStyle().startsWith( "defaultState" ) )
		{
			
		}
		else if( cell.getStyle().startsWith( "internalTransition" ) )
		{
            mxCodec codec = new mxCodec();
            Node node = codec.encode(cell);

            try
            {
            	List < org.jts.gui.mxGraphtoJAXB.mxInternalTransition > mxInternalTransition = org.jts.gui.mxGraphtoJAXB.mxInternalTransition.getInternalTransitions(node, cellNodes);

                // only expect one
            	if( mxInternalTransition.isEmpty() )
            	{
            		throw new Exception( "MxInternalTransition Parse Exception: no transitions in list" );
            	}
            	
                org.jts.gui.mxGraphtoJAXB.mxInternalTransition tr = mxInternalTransition.get( 0 );
                
            	return tr.convert();
            }
            catch(org.jts.gui.mxGraphtoJAXB.parser.ParseException e)
            {
            	throw new Exception("MxState Parse Exception: " + e);
            }
		}
		else if( cell.getStyle().startsWith( "simpleTransition" ) )
		{
            mxCodec codec = new mxCodec();
            Node node = codec.encode(cell);

            try
            {
            	List < org.jts.gui.mxGraphtoJAXB.mxSimpleTransition > mxSimpleTransitions = org.jts.gui.mxGraphtoJAXB.mxSimpleTransition.getSimpleTransitions( node, cellNodes );

                // only expect one
            	if( mxSimpleTransitions.isEmpty() )
            	{
            		throw new Exception( "mxSimpleTransitions Parse Exception: no transitions in list" );
            	}
            	
                org.jts.gui.mxGraphtoJAXB.mxSimpleTransition tr = mxSimpleTransitions.get( 0 );
                
            	return tr.convert();
            }
            catch(org.jts.gui.mxGraphtoJAXB.parser.ParseException e)
            {
            	throw new Exception("MxState Parse Exception: " + e);
            }
		}
		else if( cell.getStyle().startsWith( "pushTransition" ) )
		{
			
		}
		else if( cell.getStyle().startsWith( "popTransition" ) )
		{
			
		}
		else if( cell.getStyle().startsWith( "defaultInternalTransition" ) )
		{
			
		}
		else if( cell.getStyle().startsWith( "defaultSimpleTransition" ) )
		{
			
		}
		else if( cell.getStyle().startsWith( "defaultPushTransition" ) )
		{
			
		}
		else if( cell.getStyle().startsWith( "defaultPopTransition" ) )
		{
			
		}
		else
		{
			throw new Exception( "Parse Exception: Mx style value not supported : " + cell.getStyle() );
		}
		*/
		else
		{
			throw new Exception( "Parse Exception: Currently, only state machine validation is supported");
		}
		//return null;
	}
}
