package com.mxgraph.swing.view.Editor.Sets;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;
import org.jts.validator.parsers.ParseException;

import com.mxgraph.swing.view.Editor.Nodes.GuardNode;
import com.mxgraph.swing.view.Editor.Nodes.GuardNodeContents;
import com.mxgraph.swing.view.Editor.Nodes.NodeSet;

public class GuardSet extends NodeSet
{

	public GuardSet( DefaultMutableTreeNode parent )
	{
		super( "Guards", parent );
	}
	
	/**
	 * Harvested from codegenerator guard class
	 * @param guard
	 */
	public void convertFrom( org.jts.jsidl.binding.Guard guard )
	{
		if( guard == null )
		{
			return;
		}

		ArrayList<GuardNodeContents> setContents = new ArrayList<GuardNodeContents>();
		
		// boolean logical operators may be included in each condition
		// we just want the function name with arguments
		String condition = guard.getCondition();
		
		String splits[] = condition.split("\\)");
		
		for(String split:splits)
		{
			// check for bracket ending (split will have empty string)
			if(split.lastIndexOf("(") == -1)
			{
				continue;
			}
			
			GuardNodeContents newContent = new GuardNodeContents();
			
			String functionName = split.substring(0, split.lastIndexOf("("));
			String functionArguments = split.substring(split.lastIndexOf("(")+1);
			
			if(functionName == null && functionArguments == null)
			{
				// ERROR
			}
			
			// function name may contain logic link from previous split
			addLogicLinkToPreviousContents( setContents, functionName );

			// filter out non-function name characters
			functionName = functionName.replace(" ", "");
			functionName = functionName.replace("||", "");
			functionName = functionName.replace("&&", "");
			functionName = functionName.replace("<", "");
			functionName = functionName.replace(">", "");
			functionName = functionName.replace("<=", "");
			functionName = functionName.replace(">=", "");
			functionName = functionName.replace("!=", "");
			functionName = functionName.replace("==", "");
			functionName = functionName.replace("(", "");
			newContent.name = functionName;
                                    
			// get rid of whitespace in variable name
			functionArguments = functionArguments.replaceAll("\\s", "");
							
			String[] variableSplitArray = functionArguments.split(",");

			for(int j = 0; j < variableSplitArray.length; j++)
			{
				String variable = variableSplitArray[j];
				
				// only add args that have strings
				if( variable != null && variable.length() > 0 )
				{
					newContent.args.add( variable );
				}
			}

			// add new content node to list
			setContents.add( newContent );
		}

		// make new nodes for each of the functions
		for( GuardNodeContents nodeContent : setContents )
		{
			GuardNode node = new GuardNode( thisNode );
			node.convertFrom( nodeContent );
		}
	}

	/**
	 * Convenience method to previous node's logic link
	 * @param contents
	 * @param functionName
	 */
	private void addLogicLinkToPreviousContents( ArrayList<GuardNodeContents> contents, String functionName )
	{
		// no link applicable for first functionName
		if( contents.size() == 0 )
		{
			return;
		}
		
		// add logic of link to previous guardnodecontents
		GuardNodeContents previousNodeContents = contents.get( contents.size() - 1 );

		if( functionName.contains( "||" ) )
		{
			previousNodeContents.linkStatement = "||";
		}
		else if( functionName.contains( "&&" ) )
		{
			previousNodeContents.linkStatement = "&&";
		}
		else if( functionName.contains( "<" ) )
		{
			previousNodeContents.linkStatement = "<";
		}
		else if( functionName.contains( ">" ) )
		{
			previousNodeContents.linkStatement = ">";
		}
		else if( functionName.contains( "<=" ) )
		{
			previousNodeContents.linkStatement = "<=";
		}
		else if( functionName.contains( ">=" ) )
		{
			previousNodeContents.linkStatement = ">=";
		}
		else if( functionName.contains( "!=" ) )
		{
			previousNodeContents.linkStatement = "!=";
		}
		else if( functionName.contains( "==" ) )
		{
			previousNodeContents.linkStatement = "==";
		}
	}
	
	public org.jts.jsidl.binding.Guard convertTo()
	{
		String condition = "";

		ArrayList< Object > items = getUserObjects();
		
		int itemSize = items.size();
		for( int i = 0; i < itemSize; i++ )
		{
			GuardNode node = ( GuardNode )items.get( i );
			
			if( node == null )
			{
				return null;
			}

			GuardNodeContents contents = node.convertTo();
			
			condition += " " + contents.name;
			
			condition += "(";

			int numberArgs = contents.args.size();
			for( int j = 0; j < numberArgs; j++ )
			{
				if( j != 0 )
				{
					condition += ",";
				}
				
				condition += contents.args.get( j );
			}

			condition += ")";
			
			if( contents.linkStatement != null )
			{
				condition += " " + contents.linkStatement;
			}
		}

		// validate here since we don't have an override for sets
		// NOTE: DO NOT validate from structured editor
		/*ByteArrayInputStream guardInputStream = new ByteArrayInputStream( condition.getBytes() );
		
		org.jts.validator.parsers.GuardParser gp = new org.jts.validator.parsers.GuardParser( guardInputStream );

		try
		{
			gp.Condition();
		}
		catch( ParseException e )
		{
			throw new ValidatorException( e.getMessage() );
		}
		catch( Error e )
		{
			throw new ValidatorException( e.getMessage() );
		}
		*/
		
		org.jts.jsidl.binding.Guard guard = null;
		if( !condition.isEmpty() )
		{
			guard = new org.jts.jsidl.binding.Guard();
			guard.setCondition( condition );
		}
		
		return guard;
	}
	
	@Override
	protected void addMember()
	{
		GuardNode action = new GuardNode( thisNode );
	}
	
}
