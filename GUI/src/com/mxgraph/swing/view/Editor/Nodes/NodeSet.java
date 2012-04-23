package com.mxgraph.swing.view.Editor.Nodes;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;


public abstract class NodeSet extends NonEditableNode
{
	public NodeSet( String name, DefaultMutableTreeNode parent )
	{
		super( name, parent );
		
		createAdditionNode();
	}
	
	private void createAdditionNode()
	{
		AdditionNode addNode = new AdditionNode( this );
		addChildMember( addNode );
	}
	
	protected abstract void addMember();
	
	protected ArrayList< Object > getUserObjects()
	{
		ArrayList< Object > output = new ArrayList< Object >();
		
		int childCount = thisNode.getChildCount();
		
		for( int i = 0; i < childCount; i++ )
		{
			DefaultMutableTreeNode node = ( DefaultMutableTreeNode ) thisNode.getChildAt( i );
			
			Object userObject = node.getUserObject();
			
			// don't add ADD nodes to list
			if( !( userObject instanceof AdditionNode ) )
			{
				output.add( node.getUserObject() );
			}
		}
		
		return output;
	}
}
