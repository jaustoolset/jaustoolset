package com.mxgraph.swing.view.Editor.Editors;

import java.util.ArrayList;
import java.util.List;

import com.mxgraph.swing.view.Editor.Sets.PushTransitionSet;

public class PushTransitionEditor< T > extends Editor
{
	private PushTransitionSet triggers;
	
	public PushTransitionEditor( com.u2d.generated.ProtocolBehavior pb )
	{
		super( pb );
		
		triggers = new PushTransitionSet( null );
		
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
	PushTransitionEditor< org.jts.jsidl.binding.Transition > 
	loadEditor( 
			String currentCellValue, 
			com.u2d.generated.ProtocolBehavior pb )
	{
		PushTransitionEditor< org.jts.jsidl.binding.Transition > editor = 
			new PushTransitionEditor< org.jts.jsidl.binding.Transition >( pb );
		
		if( currentCellValue == null || currentCellValue.isEmpty() )
		{
			return editor;
		}

		try
		{
			List< org.jts.gui.mxGraphtoJAXB.mxPushTransition > parsed = 
				org.jts.gui.mxGraphtoJAXB.mxPushTransition.parseValueAttrib( currentCellValue );
			
			List< org.jts.jsidl.binding.Transition > input = new ArrayList< org.jts.jsidl.binding.Transition >();
	
			for( org.jts.gui.mxGraphtoJAXB.mxPushTransition t : parsed )
			{
				//org.jts.jsidl.binding.Transition tr = t.convert();
				// convert() expects an endstate, convert manually instead
				org.jts.jsidl.binding.Transition tr = new org.jts.jsidl.binding.Transition();
				
				// name
				tr.setName( t.name );
				
				// parameters
				for( org.jts.gui.mxGraphtoJAXB.mxParameter p : t.params )
				{
					tr.getParameter().add( p.convert() );
				}
				
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
