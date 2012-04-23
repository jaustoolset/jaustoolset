package com.mxgraph.swing.view.Editor.Nodes;

public interface JaxbNodeConversion< T >
{
	public void convertFrom( T input );
	public T convertTo();
}
