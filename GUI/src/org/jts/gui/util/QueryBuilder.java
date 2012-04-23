package org.jts.gui.util;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.u2d.type.atom.BooleanEO;

public class QueryBuilder
{
	private com.u2d.persist.HBMSingleSession persistenceMechanism = null;
	private String tableName = "";
	private boolean fixCount = false;
	private ArrayList< org.jts.gui.util.Pair > args = new ArrayList< org.jts.gui.util.Pair >();
	
	private boolean nameArg = false;
	private boolean optionalArg = false;
	private boolean descriptionArg = false;
	private boolean idArg = false;
	private boolean versionArg = false;

	public QueryBuilder( com.u2d.persist.HBMSingleSession persistenceMechanism )
	{
		this.persistenceMechanism = persistenceMechanism;
	}
	
	public QueryBuilder()
	{
		this.persistenceMechanism = null;
	}
	
	public void setTableName( String name )
	{
		this.tableName = name;
	}
	
	public void setFixCount()
	{
		this.fixCount = true;
	}
	
	public void addNameArg()
	{
		this.nameArg = true;
	}
	
	public void addOptionalArg()
	{
		this.optionalArg = true;
	}
	
	public void addDescriptionArg()
	{
		this.descriptionArg = true;
	}
	
	public void addIdArg()
	{
		this.idArg = true;
	}	
	
	public void addVersionArg()
	{
		this.versionArg = true;
	}
	
	public void addArg( org.jts.gui.util.Pair arg )
	{
		this.args.add( arg );
	}
	
	public void addArg( String name, Object obj )
	{
		this.args.add( new org.jts.gui.util.Pair( name, obj ) );
	}

	private String getQueryString()
	{
		// ex: "from Array jmArray where jmArray.name=:name and jmArray.optional=:optional";

		String query = "from " + this.tableName + " generated where";
		for( int i = 0; i < args.size(); i++ )
		{
			org.jts.gui.util.Pair arg = args.get( i );
			query += " generated." + arg.first + "=:" + arg.first;
			
			if( i < args.size() - 1 )
			{
				query += " and";
			}
		}

		return query;
	}

	public Object getStored( Object jaxbObject, Long generatedId )
	{
		try
		{
			if( this.nameArg == true )
			{
				Class<?> jaxbClass = jaxbObject.getClass();
				Method getMethod = jaxbClass.getMethod( "getName" );
				
				Object name = getMethod.invoke( jaxbObject );
				
				args.add( new org.jts.gui.util.Pair( "name", name ) );
			}
			
			if( this.optionalArg == true )
			{
				Class<?> jaxbClass = jaxbObject.getClass();
				Method getMethod = jaxbClass.getMethod( "isOptional" );
				
				Boolean optional = (Boolean)getMethod.invoke( jaxbObject );
				
				args.add( new org.jts.gui.util.Pair( "optional", new BooleanEO( optional ) ) );
			}
			
			if( this.descriptionArg == true )
			{
				Class<?> jaxbClass = jaxbObject.getClass();
				Method getMethod = jaxbClass.getMethod( "getDescription" );
				
				org.jts.jsidl.binding.Description description = (org.jts.jsidl.binding.Description)getMethod.invoke( jaxbObject );
				
				args.add( new org.jts.gui.util.Pair( "description", description.getContent() ) );
			}
			
			if( this.idArg == true )
			{
				Class<?> jaxbClass = jaxbObject.getClass();
				Method getMethod = jaxbClass.getMethod( "getId" );
				
				String id = (String)getMethod.invoke( jaxbObject );
				
				args.add( new org.jts.gui.util.Pair( "id", id ) );
			}

			if( this.versionArg == true )
			{
				Class<?> jaxbClass = jaxbObject.getClass();
				Method getMethod = jaxbClass.getMethod( "getVersion" );
				
				String version = (String)getMethod.invoke( jaxbObject );
				
				args.add( new org.jts.gui.util.Pair( "version", version ) );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		return org.jts.gui.util.Conversion.getStored( persistenceMechanism, getQueryString(), args, fixCount, jaxbObject, generatedId );
	}
	
}
