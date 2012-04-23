package com.mxgraph.swing.view.Editor.Sets;

import java.lang.reflect.Method;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;

import com.mxgraph.swing.view.Editor.Nodes.AdditionNode;
import com.mxgraph.swing.view.Editor.Nodes.NodeSet;

public abstract class NonExtendableSet< T1, T2 > extends NodeSet
{
	public NonExtendableSet( String name, DefaultMutableTreeNode parent )
	{
		super( name, parent );
	}
	
	protected Object getUserObject()
	{
		Object object = thisNode.getChildAt( 0 );
		
		if( object instanceof AdditionNode )
		{
			return null;
		}

		DefaultMutableTreeNode node = ( DefaultMutableTreeNode ) thisNode.getChildAt( 0 );

		Object output = node.getUserObject();

		return output;
	}
	
	public void convertFrom( T1 input )
	{
		if( input == null )
		{
			return;
		}

		T2 node = getNewT2();
		
		Class<?> cls = null;
		try
		{
			cls = node.getClass();
			Class[] parm = new Class[1];
			parm[0] = input.getClass();
			Method convert = cls.getMethod( "convertFrom", parm );
			convert.invoke( node, new Object[]{ input } );
		}
		catch( NoSuchMethodException e )
		{
			throw new ValidatorException("EditableSet: Attempted to get convert method from class " + cls.getName());
		}
		catch( Exception e )
		{
			throw new ValidatorException("NonExtendableSet convertFrom: generic exception: " +  e.toString() );
		}
	}

	public T1 convertTo()
	{
		Object item = getUserObject();
		
		if( item == null )
		{
			return null;
		}
		
		T1 converted = null;
		T2 node = ( T2 )item;

		Class<?> cls = null;
		try
		{
			cls = node.getClass();
			Method convert = cls.getMethod("convertTo");
			converted = ( T1 )convert.invoke( node );
		}
		catch( NoSuchMethodException e )
		{
			//throw new ValidatorException("EditableSet: Attempted to get convert method from class " + cls.getName());
			// Addition nodes may be called with the convertion
			//continue;
		}
		catch( Exception e )
		{
			throw new ValidatorException("ExtendableSet convertFrom: generic exception: " +  e.toString() );
		}
			
		return converted;
	}
	
	public abstract T2 getNewT2();
}
