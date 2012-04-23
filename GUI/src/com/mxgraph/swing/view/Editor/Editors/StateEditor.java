package com.mxgraph.swing.view.Editor.Editors;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Vector;

import org.jts.gui.mxGraphtoJAXB.parser.ParseException;
import org.jts.gui.mxGraphtoJAXB.parser.StateParser;

import com.mxgraph.swing.view.Editor.Nodes.StateNameNode;
import com.mxgraph.swing.view.Editor.Sets.EntryActionSet;
import com.mxgraph.swing.view.Editor.Sets.ExitActionSet;


public class StateEditor< T > extends Editor
{
	private EntryActionSet entryAction;
	private ExitActionSet exitAction;
	private StateNameNode state;
	
	public StateEditor( com.u2d.generated.ProtocolBehavior pb )
	{
		super( pb );

		state = new StateNameNode( "State", null );
		entryAction = new EntryActionSet( state.getNode() );
		exitAction = new ExitActionSet( state.getNode() );
		
		tree.setRoot( state.getNode() );

		setup();
	}

	public void convertFrom( org.jts.jsidl.binding.State input )
	{
		entryAction.convertFrom( input.getEntry().getActionOrSendAction() );
		exitAction.convertFrom( input.getExit().getActionOrSendAction() );
		state.setString( input.getName() );
		
		// set root for tree after nodes are filled
		tree.setRoot( state.getNode() );
	}

	public ArrayList< T > convertTo()
	{
		ArrayList< org.jts.jsidl.binding.State > tempStates = new ArrayList< org.jts.jsidl.binding.State >();
		
		org.jts.jsidl.binding.State st = new org.jts.jsidl.binding.State();
		org.jts.jsidl.binding.Entry entry = new org.jts.jsidl.binding.Entry();
		org.jts.jsidl.binding.Exit exit = new org.jts.jsidl.binding.Exit();

		st.setEntry( entry );
		st.setExit( exit );

		st.setName( state.toString() );
		
		entry.getActionOrSendAction().addAll( entryAction.convertTo() );
		exit.getActionOrSendAction().addAll( exitAction.convertTo() );
		
		tempStates.add( st );

		return (ArrayList<T>) tempStates;
	}
	
	public static StateEditor< org.jts.jsidl.binding.State > loadEditor( String currentCellValue, com.u2d.generated.ProtocolBehavior pb )
	{
		StateEditor< org.jts.jsidl.binding.State > editor = new StateEditor< org.jts.jsidl.binding.State >( pb );
		
		if( currentCellValue == null || currentCellValue.isEmpty() )
		{
			return editor;
		}

		try
		{
			ByteArrayInputStream stream = new ByteArrayInputStream( currentCellValue.getBytes() );
	
			StateParser parser = new StateParser( stream );
			parser.Input();

			org.jts.jsidl.binding.State st = new org.jts.jsidl.binding.State();

			Vector< org.jts.gui.mxGraphtoJAXB.mxAction > entries = parser.entryActionList;
			
			org.jts.jsidl.binding.Entry entry = new org.jts.jsidl.binding.Entry();
			for( org.jts.gui.mxGraphtoJAXB.mxAction in : entries )
			{
				org.jts.jsidl.binding.Action action = in.convert();
				entry.getActionOrSendAction().add( action );
			}

			Vector< org.jts.gui.mxGraphtoJAXB.mxAction > exits = parser.exitActionList;
			
			org.jts.jsidl.binding.Exit exit = new org.jts.jsidl.binding.Exit();
			for( org.jts.gui.mxGraphtoJAXB.mxAction in : exits )
			{
				org.jts.jsidl.binding.Action action = in.convert();				
				exit.getActionOrSendAction().add( action );
			}

			st.setEntry( entry );
			st.setExit( exit );
			st.setName( parser.name );
			
			editor.convertFrom( st );
			editor.setTextArea( currentCellValue );
		}
		catch( ParseException e )
		{
			
		}

		return editor;
	}

}
