package com.mxgraph.swing.view.Editor.Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

public class IsStatelessNode extends NonEditableNode
{
	IsStatelessValueNode isStateless;

	public IsStatelessNode( String name, DefaultMutableTreeNode parent )
	{
		super(name, parent);
		
		isStateless = new IsStatelessValueNode( "modifyName", thisNode );
	}
	
	public void setSubValue( String input )
	{
		isStateless.setString( input );
	}
	
	public String getSubValue()
	{
		return isStateless.toString();
	}

}
