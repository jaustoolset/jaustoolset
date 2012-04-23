package com.mxgraph.swing.view.Editor.Editors;

import java.util.ArrayList;
import java.util.List;

import com.mxgraph.swing.view.Editor.Sets.DefaultInternalTransitionSet;


public class DefaultInternalTransitionEditor< T > extends Editor
{
	private DefaultInternalTransitionSet triggers;
	
	public DefaultInternalTransitionEditor( com.u2d.generated.ProtocolBehavior pb )
	{
		super( pb );
		
		triggers = new DefaultInternalTransitionSet( null );
		
		tree.setRoot( triggers.getNode() );

		setup();
	}

	public void convertFrom( List< org.jts.jsidl.binding.DefaultTransition > input )
	{
		triggers.convertFrom( input );
		
		// set root for tree after nodes are filled
		tree.setRoot( triggers.getNode() );
	}

	public ArrayList< T > convertTo()
	{
		return (ArrayList<T>) triggers.convertTo();
	}
	
	public static DefaultInternalTransitionEditor< org.jts.jsidl.binding.DefaultTransition > loadEditor( String currentCellValue, com.u2d.generated.ProtocolBehavior pb )
	{
		DefaultInternalTransitionEditor< org.jts.jsidl.binding.DefaultTransition > editor = 
			new DefaultInternalTransitionEditor< org.jts.jsidl.binding.DefaultTransition >( pb );
		
		if( currentCellValue == null || currentCellValue.isEmpty() )
		{
			return editor;
		}

		try
		{
			List< org.jts.gui.mxGraphtoJAXB.mxDefaultInternalTransition > parsed = 
				org.jts.gui.mxGraphtoJAXB.mxDefaultInternalTransition.parseValueAttrib( currentCellValue );
			
			List< org.jts.jsidl.binding.DefaultTransition > input = new ArrayList< org.jts.jsidl.binding.DefaultTransition >();
	
			for( org.jts.gui.mxGraphtoJAXB.mxDefaultInternalTransition t : parsed )
			{
				org.jts.jsidl.binding.DefaultTransition tr = t.convert();
				
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
