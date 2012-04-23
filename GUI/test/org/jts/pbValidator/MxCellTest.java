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

import org.jts.gui.JAXBtomxGraph.jxProtocolBehavior;
import org.jts.pbValidator.Validator;
import org.jts.pbValidator.ValidatorException;
import org.jts.pbValidator.ValidatorResult;
import org.jts.pbValidator.ValidatorUtils;
import org.junit.*;
import org.w3c.dom.Document;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.view.mxGraph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

/**
 *
 * @author Drew Lucas
 *
 */
public class MxCellTest 
{
	private JAXBContext jc;
	private Unmarshaller unmarshaller;
	private int numberXMLTests = 14;
	public List< org.jts.jsidl.binding.ProtocolBehavior > sdefs = new ArrayList<org.jts.jsidl.binding.ProtocolBehavior>();

	public static void main(String [ ] args)
	{
		MxCellTest test = new MxCellTest();
		test.createUnmarshaller();

		test.testValidate1();
		test.testValidate2();
		test.testValidate3();
		test.testValidate4();
		test.testValidate5();
		test.testValidate6();
		test.testValidate7();
		test.testValidate8();
		test.testValidate9();
		test.testValidate10();
		test.testValidate11();
		test.testValidate12();
		test.testValidate13();
		test.testValidate14();
		test.testValidate15();
		test.testValidate16();
		test.testValidate17();
		test.testValidate18();
		test.testValidate19();
		test.testValidate20();
	}

	private com.mxgraph.model.mxCell getPbCell( int testNumber, int smNumber )
	{
		jxProtocolBehavior jxConverter = new jxProtocolBehavior( sdefs.get( testNumber - 1 ) );
		Document document = jxConverter.convert();
		
		mxCodec codec = new mxCodec( document );
		mxGraph graph = new mxGraph();
		
		codec.decode( document.getDocumentElement(), graph.getModel() );
		
		mxCell root = ( mxCell )graph.getModel().getRoot();
		mxICell container = root.getChildAt( 0 );
		
		Object[] stateMachines = new Object[ container.getChildCount() ];
		for( int i = 0; i < container.getChildCount(); i++ )
		{
			stateMachines[ i ] = container.getChildAt( i );
		}
		
		return ( com.mxgraph.model.mxCell ) stateMachines[ smNumber ];
	}

	@Before
	public void createUnmarshaller()
	{
		try
		{
			jc = JAXBContext.newInstance("org.jts.jsidl.binding");
			unmarshaller = jc.createUnmarshaller();

			unmarshaller.setSchema(SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File("resources/schema/JSIDL_Plus/jsidl_plus.xsd")));

			for( int i = 1; i <= numberXMLTests; i++ )
			{
				sdefs.add(
				(org.jts.jsidl.binding.ProtocolBehavior)unmarshaller.unmarshal(new File("test/org/jts/pbValidator/xml/MxCellTest" + Integer.toString( i ) + ".xml"))
				);
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}

	private void testMe( int testNumber, String expectedDescription, int expectedErrorSize, com.mxgraph.model.mxCell overrideCell )
	{
		Validator validator = new Validator();
		int errorSize = 0;

		try
		{
			if( overrideCell == null )
			{
				validator.validate( getPbCell( testNumber, 0 )  );
			}
			else
			{
				validator.validate( overrideCell  );
			}
		}
		catch( ValidatorException ve )
		{
			List<ValidatorResult> results = null;

			results = ve.getResults();
			errorSize = ValidatorUtils.getErrorCount( results );

			Assert.assertEquals( "\nMxCellTest.testValidate" + Integer.toString( testNumber ) + "() failed, Description:  ",
									results.get(0).getDescription(),
									expectedDescription);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		Assert.assertEquals( "\nMxCellTest.testValidate" + Integer.toString( testNumber ) + "() failed : size\n", expectedErrorSize, errorSize );
	}

	@Test
	public void testValidate1()
	{
		// testing state machine input
		// MxCellPseudoStartState: state machine must have a pseudo start state
		int testNumber = 1;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell modifyCell = getPbCell( testNumber, 0 );
		for( int i = 0; i < modifyCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( modifyCell.getChildAt( i ) ) )
			{
				modifyCell.getChildAt( i ).removeFromParent();
				break;
			}
		}

		// now test the class for an error 
		testMe( testNumber, "There must be a pseudo start state at each level of nesting", 1, modifyCell );
	}

	@Test
	public void testValidate2()
	{
		// testing state machine input
		// MxCellPseudoStartState: state machine must have only one pseudo start state
		int testNumber = 2;

		com.mxgraph.model.mxCell modifyCell = getPbCell( testNumber, 0 );

		// we want the state machine to have multiple pseudo start states
		com.mxgraph.model.mxCell inserted = new com.mxgraph.model.mxCell( "" );
		inserted.setStyle( "pseudoStartState;" );
		
		modifyCell.insert( inserted );

		// now test the class for an error 
		testMe( testNumber, "There must only be one pseudo start state at each level of nesting", 5, modifyCell );
	}

	@Test
	public void testValidate3()
	{
		// testing state input
		// MxCellPseudoStartState: state must have a pseudo start state when there are nested states
		int testNumber = 3;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell modifyCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxState( pbCell.getChildAt( i ) ) )
			{
				modifyCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
				break;
			}
		}

		// remove state pseudo start state
		for( int i = 0; i < modifyCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( modifyCell.getChildAt( i ) ) )
			{
				modifyCell.remove( i );
				break;
			}
		}

		// now test the class for an error 
		testMe( testNumber, "There must be a pseudo start state at each level of nesting", 1, modifyCell );
	}

	@Test
	public void testValidate4()
	{
		// testing state input
		// MxCellPseudoStartState: state must not have more than 1 pseudo start state when there are nested states
		int testNumber = 4;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell modifyCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxState( pbCell.getChildAt( i ) ) )
			{
				modifyCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
				break;
			}
		}

		// we want the state to have multiple pseudo start states
		com.mxgraph.model.mxCell inserted = new com.mxgraph.model.mxCell( "" );
		inserted.setStyle( "pseudoStartState;" );
		
		modifyCell.insert( inserted );

		// now test the class for an error 
		testMe( testNumber, "There must only be one pseudo start state at each level of nesting", 5, modifyCell );
	}

	@Test
	public void testValidate5()
	{
		// testing pseudo start state input
		// MxCellPseudoStartState: pseudo start state must be a child of a state machine or a state
		int testNumber = 5;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell modifyCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( pbCell.getChildAt( i ) ) )
			{
				modifyCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
				break;
			}
		}

		// we want to test a pseudo start state that has no parent, break connection
		modifyCell.removeFromParent();

		// now test the class for an error 
		testMe( testNumber, "Pseudo start state must be nested in a state or state machine", 1, modifyCell );
	}

	@Test
	public void testValidate6()
	{
		// testing pseudo start state input
		// MxCellPseudoStartState: pseudo start state must be a only pseudo start state of parent
		int testNumber = 6;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell modifyCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( pbCell.getChildAt( i ) ) )
			{
				modifyCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
				break;
			}
		}

		// we want the parent to have multiple pseudo start states
		com.mxgraph.model.mxCell inserted = new com.mxgraph.model.mxCell( "" );
		inserted.setStyle( "pseudoStartState;" );
		
		modifyCell.getParent().insert( inserted );

		// now test the class for an error 
		testMe( testNumber, "There must only be one pseudo start state at each level of nesting", 1, modifyCell );
	}

	@Test
	public void testValidate7()
	{
		// testing pseudo start state input
		// MxCellPseudoStartState: there must be sub states for the pseudo start state to be valid at a level of nesting
		int testNumber = 7;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell pseudoCell = null;
		com.mxgraph.model.mxCell stateCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( pbCell.getChildAt( i ) ) )
			{
				pseudoCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
			}
			else if( org.jts.pbValidator.ValidatorUtils.isMxState( pbCell.getChildAt( i ) ) )
			{
				stateCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
			}
		}

		// remove state from pb		
		stateCell.removeFromParent();

		// now test the class for an error 
		testMe( testNumber, "Pseudo start state not valid when there are no substates", 1, pseudoCell );
	}

	@Test
	public void testValidate8()
	{
		// testing pseudo start state input
		// MxCellPseudoStartState: transition from pseudo start state must be linked to a state ( no connection test )
		int testNumber = 8;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell pseudoCell = null;
		com.mxgraph.model.mxCell transitionCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( pbCell.getChildAt( i ) ) )
			{
				pseudoCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
			}
			else if( org.jts.pbValidator.ValidatorUtils.isMxState( pbCell.getChildAt( i ) ) )
			{
				transitionCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
			}
		}

		// remove state from pb		
		transitionCell.removeFromParent();

		// now test the class for an error 
		testMe( testNumber, "Pseudo start state not valid when there are no substates", 1, pseudoCell );
	}

	@Test
	public void testValidate9()
	{
		// testing pseudo start state input
		// MxCellPseudoStartState: pseudo start state must only have one transition
		int testNumber = 9;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell pseudoCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( pbCell.getChildAt( i ) ) )
			{
				pseudoCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
				break;
			}
		}

		// we want the pseudo start state to have multiple transitions
		com.mxgraph.model.mxCell inserted = new com.mxgraph.model.mxCell( "" );
		inserted.setStyle( "simpleTransition;" );
		pseudoCell.insertEdge( inserted, true );

		pseudoCell.getParent().insert( inserted );

		// now test the class for an error 
		testMe( testNumber, "Pseudo Start State must only have a single transition", 1, pseudoCell );
	}

	@Test
	public void testValidate10()
	{
		// testing pseudo start state input
		// MxCellPseudoStartState: pseudo start state transition must be a simple transition
		int testNumber = 10;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell pseudoCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( pbCell.getChildAt( i ) ) )
			{
				pseudoCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
				break;
			}
		}

		// now test the class for an error 
		testMe( testNumber, "Pseudo Start State transition must be a simple transition", 1, pseudoCell );
	}

	@Test
	public void testValidate11()
	{
		// testing pseudo start state input
		// MxCellPseudoStartState: transition from pseudo start state must be linked to a state ( transition target set as state machine )
		int testNumber = 11;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell pseudoCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( pbCell.getChildAt( i ) ) )
			{
				pseudoCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
				break;
			}
		}

		// now test the class for an error 
		testMe( testNumber, "Pseudo Start State transition point to a state", 1, pseudoCell );
	}

	@Test
	public void testValidate12()
	{
		// testing state input
		// MxCellState: state must have a parent ( null parent )
		int testNumber = 12;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell stateCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxState( pbCell.getChildAt( i ) ) )
			{
				stateCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
				break;
			}
		}

		// remove link between state and parent
		stateCell.removeFromParent();

		// now test the class for an error 
		testMe( testNumber, "State must be contained in a state or a state machine", 1, stateCell );
	}

	@Test
	public void testValidate13()
	{
		// testing state input
		// MxCellState: state must have a parent ( default state parent )
		int testNumber = 13;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell stateCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxState( pbCell.getChildAt( i ) ) )
			{
				stateCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
				break;
			}
		}

		// modify state parent by adding two default states and one state
		com.mxgraph.model.mxCell inserted1 = new com.mxgraph.model.mxCell( "" );
		inserted1.setStyle( "state;" );
		stateCell.insert( inserted1 );
		
		com.mxgraph.model.mxCell inserted2 = new com.mxgraph.model.mxCell( "" );
		inserted2.setStyle( "defaultState;" );

		stateCell.insert( inserted2 );

		com.mxgraph.model.mxCell inserted3 = new com.mxgraph.model.mxCell( "" );
		inserted3.setStyle( "defaultState;" );

		stateCell.insert( inserted3 );

		// now test the class for an error 
		testMe( testNumber, "There may only be one default state at each level of nesting", 1, stateCell );
	}

	@Test
	public void testValidate14()
	{
		// testing state input
		// MxCellState: state may have 1 default state ( 2 default states when substates )
		int testNumber = 14;

		// NOTE: decode method run against the jxProtocolBehavior element inserts this into the object
		// so we have to manually remove the element
		com.mxgraph.model.mxCell pbCell = getPbCell( testNumber, 0 );
		com.mxgraph.model.mxCell stateCell = null;
		for( int i = 0; i < pbCell.getChildCount(); i++ )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxState( pbCell.getChildAt( i ) ) )
			{
				stateCell = ( com.mxgraph.model.mxCell ) pbCell.getChildAt( i );
				break;
			}
		}

		// modify state parent by adding a default state
		com.mxgraph.model.mxCell inserted1 = new com.mxgraph.model.mxCell( "" );
		inserted1.setStyle( "defaultState;" );
		com.mxgraph.model.mxCell inserted2 = new com.mxgraph.model.mxCell( "" );
		inserted2.setStyle( "defaultState;" );

		stateCell.insert( inserted1 );
		stateCell.insert( inserted2 );

		// now test the class for an error 
		testMe( testNumber, "There may only be one default state at each level of nesting", 1, stateCell );
	}

	@Test
	public void testValidate15()
	{
		// testing transition input
		// MxCellTransition: transition does not need a target if it is a pop
		int testNumber = 15;

		// modify state parent by adding a default state
		com.mxgraph.model.mxCell inserted1 = new com.mxgraph.model.mxCell( "" );
		inserted1.setStyle( "popTransition;" );

		com.mxgraph.model.mxCell inserted2 = new com.mxgraph.model.mxCell( "" );
		inserted2.setStyle( "state;" );

		inserted1.setSource( inserted2 );

		// now test the class for an error 
		testMe( testNumber, "", 0, inserted1 );
	}

	@Test
	public void testValidate16()
	{
		// testing transition input
		// MxCellTransition: simple transition needs a target
		int testNumber = 16;

		// validate a transition with no target
		com.mxgraph.model.mxCell inserted1 = new com.mxgraph.model.mxCell( "" );
		inserted1.setStyle( "simpleTransition;" );

		// now test the class for an error 
		testMe( testNumber, "Transition must be connected to a target state", 1, inserted1 );
	}

	@Test
	public void testValidate17()
	{
		// testing transition input
		// MxCellTransition: simple transition target needs to be a state
		int testNumber = 17;

		// validate a transition with a default state target
		com.mxgraph.model.mxCell inserted1 = new com.mxgraph.model.mxCell( "" );
		inserted1.setStyle( "simpleTransition;" );

		com.mxgraph.model.mxCell inserted2 = new com.mxgraph.model.mxCell( "" );
		inserted2.setStyle( "defaultState;" );

		inserted1.setSource( inserted2 );

		// now test the class for an error 
		testMe( testNumber, "Transition must be connected to a target state", 1, inserted1 );
	}

	@Test
	public void testValidate18()
	{
		// testing transition input
		// MxCellTransition: transition needs a source
		int testNumber = 18;

		// validate a transition with no source
		com.mxgraph.model.mxCell inserted1 = new com.mxgraph.model.mxCell( "" );
		inserted1.setStyle( "simpleTransition;" );

		com.mxgraph.model.mxCell inserted2 = new com.mxgraph.model.mxCell( "" );
		inserted2.setStyle( "state;" );

		inserted1.setTarget( inserted2 );

		// now test the class for an error 
		testMe( testNumber, "Transition must be connected to a source state or pseudo start state", 1, inserted1 );
	}

	@Test
	public void testValidate19()
	{
		// testing transition input
		// MxCellTransition: transition source needs to be a state or a pseudo start state
		int testNumber = 19;

		// validate a transition with a default state source
		com.mxgraph.model.mxCell inserted1 = new com.mxgraph.model.mxCell( "" );
		inserted1.setStyle( "simpleTransition;" );

		com.mxgraph.model.mxCell inserted2 = new com.mxgraph.model.mxCell( "" );
		inserted2.setStyle( "defaultState;" );

		//inserted1.setSource( inserted2 );

		com.mxgraph.model.mxCell inserted3 = new com.mxgraph.model.mxCell( "" );
		inserted3.setStyle( "state;" );

		inserted1.setTarget( inserted3 );		

		// now test the class for an error 
		testMe( testNumber, "Transition must be connected to a source state or pseudo start state", 1, inserted1 );
	}

	@Test
	public void testValidate20()
	{
		// testing transition input
		// MxCellTransition: internal transition source needs to be same as target
		int testNumber = 20;

		// validate an internal transition with no source or target
		com.mxgraph.model.mxCell inserted1 = new com.mxgraph.model.mxCell( "" );
		inserted1.setStyle( "internalTransition;" );

		com.mxgraph.model.mxCell inserted2 = new com.mxgraph.model.mxCell( "" );
		inserted2.setStyle( "state;" );
		inserted2.setId( "4" );

		inserted1.setSource( inserted2 );

		com.mxgraph.model.mxCell inserted3 = new com.mxgraph.model.mxCell( "" );
		inserted3.setStyle( "state;" );
		inserted3.setId( "14" );

		inserted1.setTarget( inserted3 );

		// now test the class for an error 
		testMe( testNumber, "Internal transition source and target must be the same state", 1, inserted1 );
	}

}
