package com.mxgraph.swing.view.Editor.Editors;

import java.util.ArrayList;
import java.util.List;

import com.mxgraph.swing.view.Editor.Sets.DefaultSimpleTransitionSet;


public class DefaultSimpleTransitionEditor< T > extends Editor
{
	private DefaultSimpleTransitionSet triggers;
	
	public DefaultSimpleTransitionEditor( com.u2d.generated.ProtocolBehavior pb )
	{
		super( pb );
		
		triggers = new DefaultSimpleTransitionSet( null );
		
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
	
	public static DefaultSimpleTransitionEditor< org.jts.jsidl.binding.DefaultTransition > loadEditor( String currentCellValue, com.u2d.generated.ProtocolBehavior pb )
	{
		DefaultSimpleTransitionEditor< org.jts.jsidl.binding.DefaultTransition > editor = 
			new DefaultSimpleTransitionEditor< org.jts.jsidl.binding.DefaultTransition >( pb );
		
		if( currentCellValue == null || currentCellValue.isEmpty() )
		{
			return editor;
		}

		try
		{
			List< org.jts.gui.mxGraphtoJAXB.mxDefaultSimpleTransition > parsed = 
				org.jts.gui.mxGraphtoJAXB.mxDefaultSimpleTransition.parseValueAttrib( currentCellValue );
			
			List< org.jts.jsidl.binding.DefaultTransition > input = new ArrayList< org.jts.jsidl.binding.DefaultTransition >();
	
			for( org.jts.gui.mxGraphtoJAXB.mxDefaultSimpleTransition t : parsed )
			{				
				//org.jts.jsidl.binding.DefaultTransition tr = t.convert();
				// convert() expects an endstate, convert manually instead
				org.jts.jsidl.binding.DefaultTransition tr = new org.jts.jsidl.binding.DefaultTransition();
				
				// guard
				if( t.guard != null )
				{
					org.jts.jsidl.binding.Guard g = new org.jts.jsidl.binding.Guard();
					g.setCondition( t.guard );
					tr.setGuard( g );
				}
				
				// actions
				for( org.jts.gui.mxGraphtoJAXB.mxAction a : t.actions )
				{
					tr.getActionOrSendAction().add( a.convert() );
				}
				
				// simple
				org.jts.jsidl.binding.Simple s  = new org.jts.jsidl.binding.Simple();
				tr.setSimple( s );
				
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
