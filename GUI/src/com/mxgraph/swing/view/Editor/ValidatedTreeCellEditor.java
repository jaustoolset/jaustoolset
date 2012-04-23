package com.mxgraph.swing.view.Editor;

import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;


public class ValidatedTreeCellEditor extends DefaultTreeCellEditor
{
	private ValidatedCellEditor editor;
	
	public ValidatedTreeCellEditor( Tree inputTree )
	{
		super( inputTree, (DefaultTreeCellRenderer) inputTree.getCellRenderer() );

		// must set tree for editor AFTER instance createTreeCellEditor is called
		editor.setTree( inputTree );
	}

	@Override
	protected TreeCellEditor createTreeCellEditor()
	{
		final AutoCompleteField field = new AutoCompleteField( null );
		
		editor = new ValidatedCellEditor( field );
		
		return editor;
	}

}

