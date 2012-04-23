package com.mxgraph.swing.view.Editor.Editors;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.jts.gui.mxGraphtoJAXB.parser.ParseException;
import org.jts.gui.mxGraphtoJAXB.parser.StateMachineParser;

import com.mxgraph.swing.view.Editor.Nodes.IsStatelessNode;
import com.mxgraph.swing.view.Editor.Nodes.StateMachineNode;


public class StateMachineEditor< T > extends Editor
{
	private StateMachineNode stateMachine;
	private IsStatelessNode isStateless;
	
	public StateMachineEditor( com.u2d.generated.ProtocolBehavior pb )
	{
		super( pb );
		
		stateMachine = new StateMachineNode( "StateMachineName", null );
		isStateless = new IsStatelessNode( "Is Stateless", stateMachine.getNode() );
		
		tree.setRoot( stateMachine.getNode() );

		setup();
	}

	public void convertFrom( org.jts.jsidl.binding.ProtocolBehavior input )
	{
		stateMachine.setString( input.getStateMachine().get( 0 ).getName() );

		if( input.isIsStateless() )
		{
			isStateless.setSubValue( "true" );
		}
		else
		{
			isStateless.setSubValue( "false" );
		}
		
		// set root for tree after nodes are filled
		tree.setRoot( stateMachine.getNode() );
	}

	public ArrayList< T > convertTo()
	{
		ArrayList< org.jts.jsidl.binding.ProtocolBehavior > tempPb = new ArrayList< org.jts.jsidl.binding.ProtocolBehavior >();
		
		org.jts.jsidl.binding.ProtocolBehavior pb = new org.jts.jsidl.binding.ProtocolBehavior();
		if( isStateless.getSubValue().compareTo( "true" ) == 0 )
		{
			pb.setIsStateless( true );
		}
		else
		{
			pb.setIsStateless( false );
		}
		
		org.jts.jsidl.binding.StateMachine sm = new org.jts.jsidl.binding.StateMachine();
		sm.setName( stateMachine.toString() );
		pb.getStateMachine().add( sm );
		
		tempPb.add( pb );

		return (ArrayList<T>) tempPb;
	}
	
	public static StateMachineEditor< org.jts.jsidl.binding.ProtocolBehavior > loadEditor( String currentCellValue, com.u2d.generated.ProtocolBehavior pb )
	{
		StateMachineEditor< org.jts.jsidl.binding.ProtocolBehavior > editor = new StateMachineEditor< org.jts.jsidl.binding.ProtocolBehavior >( pb );
		
		if( currentCellValue == null || currentCellValue.isEmpty() )
		{
			return editor;
		}

		try
		{
			ByteArrayInputStream stream = new ByteArrayInputStream( currentCellValue.getBytes() );
	
			StateMachineParser parser = new StateMachineParser( stream );
			parser.Input();
			
			org.jts.jsidl.binding.ProtocolBehavior protocol = new org.jts.jsidl.binding.ProtocolBehavior();
			org.jts.jsidl.binding.StateMachine sm = new org.jts.jsidl.binding.StateMachine();
	
			sm.setName( parser.name );
			
			protocol.getStateMachine().add( sm );
			
			if( parser.isStateless.compareToIgnoreCase( "true" ) == 0 )
			{
				protocol.setIsStateless( true );
			}
			else
			{
				protocol.setIsStateless( false );
			}
			
			editor.convertFrom( protocol );
			editor.setTextArea( currentCellValue );
		}
		catch( ParseException e )
		{
			
		}

		return editor;
	}

}
