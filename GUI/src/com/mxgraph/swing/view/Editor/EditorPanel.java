package com.mxgraph.swing.view.Editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.view.mxICellEditor;
import com.mxgraph.swing.view.Editor.Editors.*;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;

public class EditorPanel implements mxICellEditor
{
	protected transient Object editingCell;
	protected Editor editor;
	protected mxGraphComponent graphComponent;
	
	private String currentCellType;
	private String currentCellValue;
	private com.mxgraph.model.mxCell currentCell;

	
	public EditorPanel( mxGraphComponent graphComponent )
	{
		this.graphComponent = graphComponent;
	}
	
	private com.u2d.generated.ProtocolBehavior getPb()
	{
		if( graphComponent == null )
		{
			return null;
		}
		
		return graphComponent.protocolBehavior;
	}

	@Override
	public Object getEditingCell()
	{
		return editingCell;
	}

	@Override
	public void startEditing(Object cell, EventObject trigger)
	{
		if( editingCell != null )
		{
			stopEditing( true );
		}
		
		editingCell = cell;

		mxCellState state = graphComponent.getGraph().getView().getState(cell);

		if (state != null)
		{
			currentCell = ( mxCell )state.getCell();
			
			if( currentCell != null )
			{
				currentCellValue = ( String )currentCell.getValue();
				currentCellType = ( String )currentCell.getStyle();
				
				if( !loadEditor( currentCellValue, currentCellType ) )
				{
					return;
				}
			}

			if (graphComponent.getGraph().isHtmlLabel(cell))
			{
				//editorPane.setDocument(mxUtils.createHtmlDocument(state
				//		.getStyle(), scale));
				//editorPane.setText(mxUtils.getBodyMarkup(
				//		getInitialValue(state), true));

				// Workaround for wordwrapping in editor pane
				// FIXME: Cursor not visible at end of line
				JPanel wrapper = new JPanel(new BorderLayout());
				wrapper.setOpaque(false);
				//wrapper.add(editorPane, BorderLayout.CENTER);
				//scrollPane.setViewportView(wrapper);

				//currentEditor = editorPane;
			}
			else
			{
				//textArea.setFont(mxUtils.getFont(state.getStyle(), scale));
				Color fontColor = mxUtils.getColor(state.getStyle(),
						mxConstants.STYLE_FONTCOLOR, Color.black);
				//textArea.setForeground(fontColor);
				//textArea.setText(getInitialValue(state));
                
				//scrollPane.setViewportView(textArea);
				//currentEditor = editor;
			}
			
			editor.setCurrentCellStyle( currentCellType );
			
			editor.setBounds( getEditorBounds( state ) );
			editor.setSize( 200,200 );
			editor.setVisible( true );

			graphComponent.getGraphControl().add( editor, 0 );
			graphComponent.redraw( state );

			editor.revalidate();
			editor.requestFocusInWindow();
		}
	}
	
	private boolean loadEditor( String currentCellValue, String currentCellType )
	{
		if( currentCellType.contains("finiteStateMachine") )
		{
			editor = StateMachineEditor.loadEditor( currentCellValue, getPb() );
		}
		else if( currentCellType.contains("defaultState") )
		{
			editor = DefaultStateEditor.loadEditor( currentCellValue, getPb() );
		}
		else if( currentCellType.contains("state") )
		{
			editor = StateEditor.loadEditor( currentCellValue, getPb() );
		}
		else if( currentCellType.contains("internalTransition") )
		{
			editor = InternalTransitionEditor.loadEditor( currentCellValue, getPb() );
		}
		else if( currentCellType.contains( "simpleTransition" ) )
		{
			editor = SimpleTransitionEditor.loadEditor( currentCellValue, getPb() );
		}
		else if( currentCellType.contains( "pushTransition" ) )
		{
			editor = PushTransitionEditor.loadEditor( currentCellValue, getPb() );
		}
		else if( currentCellType.contains("popTransition"))
		{
			editor = PopTransitionEditor.loadEditor( currentCellValue, getPb() );
		}
		else if( currentCellType.contains("defaultInternalTransition"))
		{
			editor = DefaultInternalTransitionEditor.loadEditor( currentCellValue, getPb() );
		}
		else if( currentCellType.contains("defaultSimpleTransition"))
		{
			editor = DefaultSimpleTransitionEditor.loadEditor( currentCellValue, getPb() );
		}
		else if( currentCellType.contains("defaultPushTransition"))
		{
			editor = DefaultPushTransitionEditor.loadEditor( currentCellValue, getPb() );
		}
		else if( currentCellType.contains("defaultPopTransition"))
		{
			editor = DefaultPopTransitionEditor.loadEditor( currentCellValue, getPb() );
		}
		else
		{
			return false;
		}
		
		editor.setController( this );
		
		return true;
	}
	
	public Rectangle getEditorBounds(mxCellState state)
	{
		mxIGraphModel model = state.getView().getGraph().getModel();
		mxGeometry geometry = model.getGeometry(state.getCell());
		Rectangle bounds = null;

		if ((geometry != null && geometry.getOffset() != null && (geometry
				.getOffset().getX() != 0 || geometry.getOffset().getY() != 0))
				|| model.isEdge(state.getCell()))
		{
			bounds = state.getLabelBounds().getRectangle();
			bounds.height += 10;
		}
		else
		{
			bounds = state.getRectangle();
		}
		
		// Applies the horizontal and vertical label positions
		if (model.isVertex(state.getCell()))
		{
			String horizontal = mxUtils.getString(state.getStyle(),
					mxConstants.STYLE_LABEL_POSITION, mxConstants.ALIGN_CENTER);
	
			if (horizontal.equals(mxConstants.ALIGN_LEFT))
			{
				bounds.x -= state.getWidth();
			}
			else if (horizontal.equals(mxConstants.ALIGN_RIGHT))
			{
				bounds.x += state.getWidth();
			}
	
			String vertical = mxUtils.getString(state.getStyle(),
					mxConstants.STYLE_VERTICAL_LABEL_POSITION,
					mxConstants.ALIGN_MIDDLE);
	
			if (vertical.equals(mxConstants.ALIGN_TOP))
			{
				bounds.y -= state.getHeight();
			}
			else if (vertical.equals(mxConstants.ALIGN_BOTTOM))
			{
				bounds.y += state.getHeight();
			}
		}

		return bounds;
	}

	@Override
	public void stopEditing( boolean cancel )
	{
		if( editingCell == null || editor == null )
		{
			graphComponent.requestFocusInWindow();
			editingCell = null;
			
			return;
		}

		
		// only change cell value when cancel was not requested
		if( !cancel )
		{
			String newValue = "";
			
			if( editor.isEditingText() )
			{
				newValue = getCurrentCellToTextEditor();
			}
			else
			{
				newValue = getCurrentCellToStructuredEditor();
			}
			
			// don't allow a close of the editor if text is found to be inc
			try
			{
				checkNewCellValue( newValue );
			}
			catch( Exception e )
			{
				JOptionPane.showMessageDialog( graphComponent, "Validation error: " + e.getMessage() );
				return;
			}
			catch( Error e )
			{
				JOptionPane.showMessageDialog( graphComponent, "Validation error: " + e.getMessage() );
				return;				
			}
			
			// set cell value
			currentCell.setValue( newValue );
		}

		mxCellState state = graphComponent.getGraph().getView().getState( editingCell );
		graphComponent.redraw( state );


		if( editor.getParent() != null )
		{
			editor.setVisible( false );
			editor.getParent().remove( editor );
		}

		graphComponent.requestFocusInWindow();
		editingCell = null;
	}
	
	private String getCurrentCellToTextEditor()
	{
		return editor.getEditingText();
	}

	/**
	 * Checks to see if the currentCell string value is valid
	 * by trying to convert it with the mxgraph parsers
	 * @throws Exception
	 */
	private void checkNewCellValue( String valueToCheck ) throws Exception, Error
	{		
		if( currentCellType.contains("finiteStateMachine") )
		{
			ByteArrayInputStream stream = new ByteArrayInputStream( valueToCheck.getBytes() );
			
			org.jts.gui.mxGraphtoJAXB.parser.StateMachineParser parser = 
				new org.jts.gui.mxGraphtoJAXB.parser.StateMachineParser( stream );
			parser.Input();
		}
		else if( currentCellType.contains("defaultState") )
		{
			if( valueToCheck == null ||
					!valueToCheck.equals( "default_state" ) )
			{
				throw new Exception( "Default state cell name must be 'default_state'");
			}
		}
		else if( currentCellType.contains("state") )
		{
			ByteArrayInputStream stream = new ByteArrayInputStream( valueToCheck.getBytes() );
			
			org.jts.gui.mxGraphtoJAXB.parser.StateParser parser = 
				new org.jts.gui.mxGraphtoJAXB.parser.StateParser( stream );
			parser.Input();
		}
		else if( currentCellType.contains("internalTransition") )
		{
			org.jts.gui.mxGraphtoJAXB.mxInternalTransition.parseValueAttrib( valueToCheck );
		}
		else if( currentCellType.contains( "simpleTransition" ) )
		{
			org.jts.gui.mxGraphtoJAXB.mxSimpleTransition.parseValueAttrib( valueToCheck );
		}
		else if( currentCellType.contains( "pushTransition" ) )
		{
			org.jts.gui.mxGraphtoJAXB.mxPushTransition.parseValueAttrib( valueToCheck );
		}
		else if( currentCellType.contains("popTransition"))
		{
			org.jts.gui.mxGraphtoJAXB.mxPopTransition.parseValueAttrib( valueToCheck );
		}
		else if( currentCellType.contains("defaultInternalTransition"))
		{
			org.jts.gui.mxGraphtoJAXB.mxDefaultInternalTransition.parseValueAttrib( valueToCheck );
		}
		else if( currentCellType.contains("defaultSimpleTransition"))
		{
			org.jts.gui.mxGraphtoJAXB.mxDefaultSimpleTransition.parseValueAttrib( valueToCheck );
		}
		else if( currentCellType.contains("defaultPushTransition"))
		{
			org.jts.gui.mxGraphtoJAXB.mxDefaultPushTransition.parseValueAttrib( valueToCheck );
		}
		else if( currentCellType.contains("defaultPopTransition"))
		{
			org.jts.gui.mxGraphtoJAXB.mxDefaultPopTransition.parseValueAttrib( valueToCheck );
		}
	}
	
	private String getCurrentCellToStructuredEditor()
	{
		String value = "";
		org.jts.mxGraph.binding.MxCell cell = null;

		// state machine
		if( currentCellType.contains( "finiteStateMachine" ) )
		{
			ArrayList< org.jts.jsidl.binding.ProtocolBehavior > items = editor.convertTo();

			org.jts.gui.JAXBtomxGraph.jxStateMachine sm = 
				new org.jts.gui.JAXBtomxGraph.jxStateMachine( items.get( 0 ) );
			
			List< org.jts.mxGraph.binding.MxCell > tempList = sm.convert();
			
			value = tempList.get( 0 ).getValue();
		}
		// default state
		else if( currentCellType.contains( "defaultState" ) )
		{
			ArrayList< org.jts.jsidl.binding.DefaultState > items = editor.convertTo();

			org.jts.gui.JAXBtomxGraph.jxDefaultState state = 
				new org.jts.gui.JAXBtomxGraph.jxDefaultState( items.get( 0 ) );

			List< org.jts.mxGraph.binding.MxCell > tempList = state.convert();
			
			value = tempList.get( 0 ).getValue();
		}
		// state
		else if( currentCellType.contains( "state" ) )
		{
			ArrayList< org.jts.jsidl.binding.State > items = editor.convertTo();

			org.jts.gui.JAXBtomxGraph.jxState state = 
				new org.jts.gui.JAXBtomxGraph.jxState( items, 0, 0, 0 );
			
			List< org.jts.mxGraph.binding.MxCell > tempList = state.convert();
			
			value = tempList.get( 0 ).getValue();
		}
		// transition cell
		else if( currentCellType.contains("Transition") && !( currentCellType.contains( "default" )  ) )
		{
			ArrayList< org.jts.jsidl.binding.Transition > items = editor.convertTo();
			
			for( org.jts.jsidl.binding.Transition item : items )
			{
				value += getTransitionString( item );
			}
		}
		// default transition
		else if( currentCellType.contains( "default" ) )
		{
			ArrayList< org.jts.jsidl.binding.DefaultTransition > items = editor.convertTo();
			
			for( org.jts.jsidl.binding.DefaultTransition item : items )
			{
				value += getDefaultTransitionString( item );
			}
		}
		
		// NOTE: abbreviation set by mxGraph automatically
		//value = com.mxgraph.util.mxUtils.getAbbreviation( value, currentCellType );

		return value;
	}
	
	private String getTransitionString( org.jts.jsidl.binding.Transition item )
	{
		if( item == null )
		{
			return "";
		}
		
		org.jts.mxGraph.binding.MxCell cell = null;
		
		if( currentCellType.contains( "simpleTransition" ) )
		{
			org.jts.gui.JAXBtomxGraph.jxSimpleTransition transition = 
				new org.jts.gui.JAXBtomxGraph.jxSimpleTransition( item, 0, 0, 0 );
			cell = transition.convert();
		}
		else if( currentCellType.contains( "internalTransition" ) )
		{
			org.jts.gui.JAXBtomxGraph.jxInternalTransition transition = 
				new org.jts.gui.JAXBtomxGraph.jxInternalTransition( item, 0, 0 );
			cell = transition.convert();
		}
		else if( currentCellType.contains( "pushTransition" ) )
		{
			org.jts.gui.JAXBtomxGraph.jxPushTransition transition = 
				new org.jts.gui.JAXBtomxGraph.jxPushTransition( item, 0, 0, 0, new org.jts.jsidl.binding.State() );
			cell = transition.convert();
		}
		else if( currentCellType.contains( "popTransition" ) )
		{
			org.jts.gui.JAXBtomxGraph.jxPopTransition transition = 
				new org.jts.gui.JAXBtomxGraph.jxPopTransition( item, 0, 0 );
			cell = transition.convert();
		}
		
		if( cell == null || cell.getValue() == null )
		{
			return "";
		}
		
		return cell.getValue() + System.getProperty("line.separator", "\n");
	}
	
	private String getDefaultTransitionString( org.jts.jsidl.binding.DefaultTransition item )
	{
		if( item == null )
		{
			return "";
		}

		org.jts.mxGraph.binding.MxCell cell = null;
		
		if( currentCellType.contains( "Simple" ) )
		{
			org.jts.gui.JAXBtomxGraph.jxDefaultSimpleTransition transition = 
				new org.jts.gui.JAXBtomxGraph.jxDefaultSimpleTransition( item, 0, 0, 0 );
			cell = transition.convert();
		}
		else if( currentCellType.contains( "Internal" ) )
		{
			org.jts.gui.JAXBtomxGraph.jxDefaultInternalTransition transition = 
				new org.jts.gui.JAXBtomxGraph.jxDefaultInternalTransition( item, 0, 0 );
			cell = transition.convert();
		}
		else if( currentCellType.contains( "Push" ) )
		{
			org.jts.gui.JAXBtomxGraph.jxDefaultPushTransition transition = 
				new org.jts.gui.JAXBtomxGraph.jxDefaultPushTransition( item, 0, 0, 0, new org.jts.jsidl.binding.State() );
			cell = transition.convert();
		}
		else if( currentCellType.contains( "Pop" ) )
		{
			org.jts.gui.JAXBtomxGraph.jxDefaultPopTransition transition = 
				new org.jts.gui.JAXBtomxGraph.jxDefaultPopTransition( item, 0, 0 );
			cell = transition.convert();
		}
		
		if( cell == null || cell.getValue() == null )
		{
			return "";
		}
		
		return cell.getValue() + System.getProperty("line.separator", "\n");
	}

}
