package com.mxgraph.swing.view.Editor.Nodes;

import java.util.ArrayList;

public class GuardNodeContents
{
	public String name;
	public ArrayList<String> args;
	public String linkStatement;
	
	public GuardNodeContents()
	{
		args = new ArrayList<String>();
	}
	
	public void setArgs( ArrayList<org.jts.jsidl.binding.Argument> input )
	{
		if( input == null )
		{
			return;
		}
		
		for( org.jts.jsidl.binding.Argument arg : input )
		{
			args.add( arg.getValue() );
		}
	}
	
	public ArrayList<org.jts.jsidl.binding.Argument> getArgsAsJaxb()
	{
		ArrayList<org.jts.jsidl.binding.Argument> output = new ArrayList<org.jts.jsidl.binding.Argument>();
		
		for( String arg : args )
		{
			org.jts.jsidl.binding.Argument argument = new org.jts.jsidl.binding.Argument();
			
			argument.setValue( arg );
			
			output.add( argument );
		}
		
		return output;
	}
}
