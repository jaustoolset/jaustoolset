package com.mxgraph.swing.view.Editor.Editors;

import java.util.ArrayList;
import java.util.List;

import com.mxgraph.swing.view.Editor.Sets.InternalTransitionSet;

public class InternalTransitionEditor< T > extends Editor
{
	private InternalTransitionSet triggers;
	
	public InternalTransitionEditor( com.u2d.generated.ProtocolBehavior pb )
	{
		super( pb );
		
		triggers = new InternalTransitionSet( null );
		
		tree.setRoot( triggers.getNode() );

		setup();
	}

	public void convertFrom( List< org.jts.jsidl.binding.Transition > input )
	{
		triggers.convertFrom( input );

		// set root for tree after nodes are filled
		tree.setRoot( triggers.getNode() );
	}

	public ArrayList< T > convertTo()
	{
		return (ArrayList<T>) triggers.convertTo();
	}
	
	public static 
	InternalTransitionEditor< org.jts.jsidl.binding.Transition > 
	loadEditor( 
			String currentCellValue, 
			com.u2d.generated.ProtocolBehavior pb )
	{
		InternalTransitionEditor< org.jts.jsidl.binding.Transition > editor = 
			new InternalTransitionEditor< org.jts.jsidl.binding.Transition >( pb );
		
		if( currentCellValue == null || currentCellValue.isEmpty() )
		{
			return editor;
		}

		try
		{
			List< org.jts.gui.mxGraphtoJAXB.mxInternalTransition > parsed = 
				org.jts.gui.mxGraphtoJAXB.mxInternalTransition.parseValueAttrib( currentCellValue );
			
			List< org.jts.jsidl.binding.Transition > input = new ArrayList< org.jts.jsidl.binding.Transition >();
	
			for( org.jts.gui.mxGraphtoJAXB.mxInternalTransition t : parsed )
			{
				org.jts.jsidl.binding.Transition tr = t.convert();
				
				input.add( tr );
			}
			
			
			editor.convertFrom( input );
			editor.setTextArea( currentCellValue );
			
			return editor;
		}
		catch( org.jts.gui.mxGraphtoJAXB.parser.ParseException e)
		{
			
		}
		
		return null;
	}

}
