package com.mxgraph.swing.view.Editor.Sets;

import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;

import com.mxgraph.swing.view.Editor.Nodes.NodeSet;

public abstract class ExtendableSet< T1, T2 > extends NodeSet
{
	public ExtendableSet( String name, DefaultMutableTreeNode parent )
	{
		super( name, parent );
	}
	
	public void convertFrom( Object input )
	{
		if( input == null )
		{
			return;
		}

		if( input instanceof ArrayList )
		{
			ArrayList< Object > casted = ( ArrayList< Object > )input;
			for( Object obj : casted )
			{
				T1 in = ( T1 )obj;
				T2 node = getNewT2();
				
				Class<?> cls = null;
				try
				{
					cls = node.getClass();
					Class[] parm = new Class[1];
					parm[0] = in.getClass();
					Method convert = cls.getMethod( "convertFrom", parm );
					convert.invoke( node, new Object[]{ in } );
				}
				catch( NoSuchMethodException e )
				{
					throw new ValidatorException("EditableSet: Attempted to get convert method from class " + cls.getName());
				}
				catch( Exception e )
				{
					throw new ValidatorException("EditableSet convertFrom: generic exception: " +  e.toString() );
				}
			}
		}
		else
		{
			Class<?> cls = null;
			try
			{
				T1 in = ( T1 )input;
				T2 node = getNewT2();

				cls = node.getClass();
				Class[] parm = new Class[1];
				parm[0] = in.getClass();
				Method convert = cls.getMethod( "convertFrom", parm );
				convert.invoke( node, new Object[]{ in } );
			}
			catch( NoSuchMethodException e )
			{
				throw new ValidatorException("EditableSet: Attempted to get convert method from class " + cls.getName());
			}
			catch( Exception e )
			{
				throw new ValidatorException("EditableSet convertFrom: generic exception: " +  e.toString() );
			}
		}
	}

	public ArrayList< T1 > convertTo()
	{
		ArrayList< T1 > output = getNewT1List();
		
		ArrayList< Object > items = getUserObjects();
		
		int itemSize = items.size();
		for( int i = 0; i < itemSize; i++ )
		{
			T1 converted = null;
			T2 node = ( T2 )items.get( i );
			
			if( node == null )
			{
				return output;
			}

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
				// Addition nodes may be called with the conversion
				continue;
			}
			catch( Exception e )
			{
				throw new ValidatorException("ExtendableSet convertTo: generic exception: " +  e.toString() );
			}
			
			output.add( converted );
		}
		
		return output;
	}
	
	public abstract ArrayList< T1 > getNewT1List();
	
	public abstract T2 getNewT2();
	
}
