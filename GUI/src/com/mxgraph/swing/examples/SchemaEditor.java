package com.mxgraph.swing.examples;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.examples.editor.EditorPalette;
import com.mxgraph.swing.examples.editor.SchemaEditorMenuBar;
import com.mxgraph.swing.examples.editor.SchemaEditorToolBar;
import com.mxgraph.swing.examples.editor.SchemaGraphComponent;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

public class SchemaEditor extends BasicGraphEditor
{

	/**
	 * 
	 */
	public SchemaEditor()
	{
		super("mxGraph for JFC/Swing", new SchemaGraphComponent(new mxGraph()
		{
			/**
			 * Allows expanding tables
			 */
			public boolean isCellExpandable(Object cell)
			{
				return model.isVertex(cell);
			}
		})

		{
			/**
			 * Disables folding icons.
			 */
			public ImageIcon getFoldingIcon(mxCellState state)
			{
				return null;
			}

		});

		// Creates a single shapes palette
		EditorPalette shapesPalette = insertPalette("Schema");
		graphOutline.setVisible(false);

		mxCell tableTemplate = new mxCell("New Table", new mxGeometry(0, 0,
				200, 280), null);
		tableTemplate.getGeometry().setAlternateBounds(
				new mxRectangle(0, 0, 140, 25));
		tableTemplate.setVertex(true);

		shapesPalette
				.addTemplate(
						"Table",
						new ImageIcon(
								FreeGraphEditor.class
										.getResource("/com/mxgraph/swing/examples/images/rectangle.png")),
						tableTemplate);

		getGraphComponent().getGraph().setCellsSizable(false);
		getGraphComponent().setConnectable(false);
		getGraphComponent().getGraphHandler().setCloneEnabled(false);
		getGraphComponent().getGraphHandler().setImagePreview(false);

		// Prefers default JComponent event-handling before mxCellHandler handling
		getGraphComponent().getGraphHandler().setKeepOnTop(false);

		mxGraph graph = getGraphComponent().getGraph();
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try
		{
			mxCell v1 = (mxCell) graph.insertVertex(parent, null, "Customers",
					20, 20, 200, 280);
			v1.getGeometry().setAlternateBounds(new mxRectangle(0, 0, 140, 25));
			mxCell v2 = (mxCell) graph.insertVertex(parent, null, "Orders",
					280, 20, 200, 280);
			v2.getGeometry().setAlternateBounds(new mxRectangle(0, 0, 140, 25));
		}
		finally
		{
			graph.getModel().endUpdate();
		}
	}

	/**
	 * 
	 */
	protected void installToolBar()
	{
		add(new SchemaEditorToolBar(this, JToolBar.HORIZONTAL),
				BorderLayout.NORTH);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}

		SchemaEditor editor = new SchemaEditor();
		editor.createFrame(new SchemaEditorMenuBar(editor)).setVisible(true);
	}

}
