package com.mxgraph.swing.view.Editor.Editors;

import java.util.ArrayList;
import java.util.List;

import com.mxgraph.swing.view.Editor.Sets.DefaultPushTransitionSet;


public class DefaultPushTransitionEditor< T > extends Editor
{
	private DefaultPushTransitionSet triggers;
	
	public DefaultPushTransitionEditor( com.u2d.generated.ProtocolBehavior pb )
	{
		super( pb );
		
		triggers = new DefaultPushTransitionSet( null );
		
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
	
	public static DefaultPushTransitionEditor< org.jts.jsidl.binding.DefaultTransition > loadEditor( String currentCellValue, com.u2d.generated.ProtocolBehavior pb )
	{
		DefaultPushTransitionEditor< org.jts.jsidl.binding.DefaultTransition > editor = 
			new DefaultPushTransitionEditor< org.jts.jsidl.binding.DefaultTransition >( pb );
		
		if( currentCellValue == null || currentCellValue.isEmpty() )
		{
			return editor;
		}

		try
		{
			List< org.jts.gui.mxGraphtoJAXB.mxDefaultPushTransition > parsed = 
				org.jts.gui.mxGraphtoJAXB.mxDefaultPushTransition.parseValueAttrib( currentCellValue );
			
			List< org.jts.jsidl.binding.DefaultTransition > input = new ArrayList< org.jts.jsidl.binding.DefaultTransition >();
	
			for( org.jts.gui.mxGraphtoJAXB.mxDefaultPushTransition t : parsed )
			{
				//org.jts.jsidl.binding.DefaultTransition tr = t.convert();
				// convert() expects an endstate, convert manually instead
				org.jts.jsidl.binding.DefaultTransition tr = new org.jts.jsidl.binding.DefaultTransition();
				
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
				org.jts.jsidl.binding.Push p  = new org.jts.jsidl.binding.Push();
				tr.setPush( p );
				
				if( t.pushEndState != null )
				{
					org.jts.jsidl.binding.Simple s = new org.jts.jsidl.binding.Simple();
					org.jts.jsidl.binding.EndState end = new org.jts.jsidl.binding.EndState();
					end.setState( t.pushEndState );
					s.setEndState( end );
					
					p.setSimple( s );
				}
				
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
