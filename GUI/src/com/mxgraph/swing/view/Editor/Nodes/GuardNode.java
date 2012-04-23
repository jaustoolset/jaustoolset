package com.mxgraph.swing.view.Editor.Nodes;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;
import org.jts.validator.parsers.ParseException;

import com.mxgraph.swing.view.Editor.Sets.ArgumentsSet;
import com.mxgraph.swing.view.Editor.Sets.GuardLinkSet;


public class GuardNode extends EditableDeletableNode
{
	private ArgumentsSet arguments;
	private GuardLinkSet link;

	public GuardNode( DefaultMutableTreeNode parent )
	{
		super( "modifyName", parent );
		
		link = new GuardLinkSet( "Link Statement", thisNode );
		arguments = new ArgumentsSet( thisNode );
	}

	public void convertFrom( GuardNodeContents input )
	{
		setString( input.name );
		arguments.convertFrom( input.getArgsAsJaxb() );
		link.convertFrom( input.linkStatement );
	}
	
	public GuardNodeContents convertTo()
	{
		GuardNodeContents output = new GuardNodeContents();
		
		output.name = toString();
		output.setArgs( arguments.convertTo() );
		ArrayList<String> links = link.convertTo();
		if( links.size() == 1 )
		{
			output.linkStatement = link.convertTo().get( 0 );
		}
		
		return output;
	}

	@Override
	public void validate() throws ValidatorException
	{
		String name = toString();
		if( name.contains( "!" ) )
		{
			String shortName = name.replaceAll("\\s", "");
			if( !shortName.startsWith( "!" ) )
			{
				throw new ValidatorException( "Incorrect placement of ! : " + name );
			}
			
			// get rid of negation to make sure function is valid c name
			name = name.replaceFirst( "!", "" );
		}
		
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameWithNamespace( name ) )
		{
			throw new ValidatorException( "Invalid C Name" );
		}
	}
}
