package com.mxgraph.swing.view.Editor.Editors;

import java.util.ArrayList;

import org.jts.jsidl.binding.MxCell;

import com.mxgraph.swing.view.Editor.Nodes.DefaultStateNameNode;


public class DefaultStateEditor< T > extends Editor
{
	private DefaultStateNameNode state;
	
	public DefaultStateEditor( com.u2d.generated.ProtocolBehavior pb )
	{
		super( pb );
		
		state = new DefaultStateNameNode("default_state", null);
		
		tree.setRoot( state.getNode() );
		
		setup();
	}

	public void convertFrom( org.jts.jsidl.binding.DefaultState input )
	{
		//entryAction.convertFrom( input.getEntry().getActionOrSendAction() );
		//exitAction.convertFrom( input.getEntry().getActionOrSendAction() );
	}

	public ArrayList< T > convertTo()
	{
		ArrayList< org.jts.jsidl.binding.DefaultState > tempStates = new ArrayList< org.jts.jsidl.binding.DefaultState >();
		
		org.jts.jsidl.binding.DefaultState st = new org.jts.jsidl.binding.DefaultState();
		
		// must seed id for cell here because conversion expects a parent cell id
		MxCell tempCell = new MxCell();
		tempCell.setId( 0 );
		st.setMxCell( tempCell );
		
		st.getMxCell().setStyle( "defaultState" );
		st.getMxCell().setValue( state.toString() );
		
		tempStates.add( st );

		return (ArrayList<T>) tempStates;
	}
	
	public static DefaultStateEditor< org.jts.jsidl.binding.DefaultState > loadEditor( String currentCellValue, com.u2d.generated.ProtocolBehavior pb )
	{
		DefaultStateEditor< org.jts.jsidl.binding.DefaultState > editor = new DefaultStateEditor< org.jts.jsidl.binding.DefaultState >( pb );
		
		if( currentCellValue == null || currentCellValue.isEmpty() )
		{
			return editor;
		}

		org.jts.jsidl.binding.DefaultState st = new org.jts.jsidl.binding.DefaultState();
		
		editor.convertFrom( st );
		editor.setTextArea( currentCellValue );

		return editor;
	}

}
