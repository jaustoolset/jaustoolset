package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.EndStateValueNode;

public class EndStateSet extends ZeroOrOneSet< org.jts.jsidl.binding.EndState, EndStateValueNode >
{
	public EndStateSet(String name, DefaultMutableTreeNode parent)
	{
		super(name, parent);
	}

	@Override
	public EndStateValueNode getNewT2()
	{
		return new EndStateValueNode( "Modify end state name", thisNode );
	}

	@Override
	protected void addSingleMember()
	{
		EndStateValueNode tr = new EndStateValueNode( "Modify end state name", thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.EndState > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.EndState > output = new ArrayList< org.jts.jsidl.binding.EndState >();
		
		return output;
	}

}
