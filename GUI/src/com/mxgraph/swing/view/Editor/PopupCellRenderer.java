package com.mxgraph.swing.view.Editor;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.mxgraph.swing.view.Editor.Nodes.EditableNode;

public class PopupCellRenderer extends DefaultTreeCellRenderer
{
	//private FieldWithPopup renderer;
	
	private DefaultTreeCellRenderer defaultRenderer;
	
	public PopupCellRenderer()
	{
		//renderer = new FieldWithPopup();
		defaultRenderer = new DefaultTreeCellRenderer();
		
		//renderer.addMouse();
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, 
													boolean selected, boolean expanded, 
													boolean leaf, int row, boolean hasFocus)
	{
		Component cellRenderer = null;
		
		if( value != null && value instanceof DefaultMutableTreeNode )
		{
			Object obj = ( ( DefaultMutableTreeNode )value ).getUserObject();
			
			if( obj instanceof EditableNode )
			{
				EditableNode node = ( EditableNode )obj;
				//renderer.setText( node.toString() + "fds" );
				//cellRenderer = renderer;
			}
		}
		
		if( cellRenderer == null )
		{
			cellRenderer = defaultRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		}

		return cellRenderer;
	}
}
