package com.mxgraph.swing.view.Editor.Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;

public class GuardLinkNode extends EditableDeletableNode
{

	public GuardLinkNode( String name, DefaultMutableTreeNode parent )
	{
		super(name, parent);
	}
	
	public void convertFrom( String input )
	{
		setString( input );
	}
	
	public String convertTo()
	{
		// name
		String output = toString();
		
		return output;
	}

	@Override
	public void validate() throws ValidatorException
	{
		String name = toString();
		
		// get rid of whitespace in name
		name = name.replaceAll("\\s", "");
		
		if( !name.equals("||") &&
				!name.equals("&&") &&
				!name.equals("<") &&
				!name.equals(">") &&
				!name.equals(">=") &&
				!name.equals("<=") &&
				!name.equals("!=") &&
				!name.equals("==") )
		{
			throw new ValidatorException( "Must use ||, &&, <, >, <=, >=, !=, or ==" );
		}
	}
}
