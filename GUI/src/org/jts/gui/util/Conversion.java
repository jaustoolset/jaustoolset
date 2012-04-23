package org.jts.gui.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jts.validator.ValidatorException;

import com.u2d.app.Context;
import com.u2d.generated.Array;
import com.u2d.type.atom.BooleanEO;
import com.u2d.type.atom.StringEO;

public class Conversion
{
	/**
	 * Convenience method for setting interpretation of field only when 
	 * the interpretation is not empty
	 * @param output
	 * @param input
	 */
	public static void setNonEmptyDescription( Object output, Object input )
	{
		Class<?> inputClass = null;
		Class<?> outputClass = null;
		try
		{
			inputClass = input.getClass();
			outputClass = output.getClass();

			Method getInterpretation = inputClass.getMethod("getInterpretation");
			Method setInterpretation = outputClass.getMethod("setInterpretation", String.class);
			
			StringEO interpretationEO = (StringEO)getInterpretation.invoke( input );
			Method toString = StringEO.class.getMethod("toString");
			
			String interpretation = (String)toString.invoke(interpretationEO);
			
			if( !interpretation.isEmpty() )
			{
				setInterpretation.invoke( output, interpretation );
			}
		}
		catch(ValidatorException e)
		{
			throw e;
		}
		catch(NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();				
		}
	}
	
	public static class DBOptions
	{
		private String query;
		private List< Pair > args;
		private boolean fixCount; 
	}
	
	/**
	 * Method to get a stored object from the db 
	 * -allows for fixing the count field before comparing if necessary
	 * @param persistenceMechanism
	 * @param query
	 * @param args
	 * @param fixCountFieldBefore
	 * @param jaxbObject
	 * @param generatedId
	 * @return
	 */
	public static Object getStored( com.u2d.persist.HBMSingleSession persistenceMechanism, String query, List< Pair > args, boolean fixCountFieldBefore, Object jaxbObject, Long generatedId )
	{
		// Form our Hibernate Query Language (hql) query into the hibernate database
		// This will test if any object in the db is equal to our object
		if( persistenceMechanism == null )
		{
			persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism();
		}

		Session session = persistenceMechanism.getSession();
		String hql = query;
		Query hibernateQuery = session.createQuery(hql);
		
		for( Pair p : args )
		{
			hibernateQuery.setParameter( (String)p.first, p.second );
		}
		
		if( fixCountFieldBefore )
		{
			org.jts.gui.JAXBtoJmatter.CountField.fix( jaxbObject );
		}

		// Get result of our query		
		java.util.List resultsList = hibernateQuery.list();

		for( Object generatedObject : resultsList )
		{			
		    if( jaxbObject.equals( jmatterToJaxb( generatedObject ) ) )
			{
		    	// make sure the comparison is not with the current object(compared to itself) 
		    	if( generatedId != ((com.u2d.model.AbstractComplexEObject_JTS)generatedObject).getID() )
		    		return generatedObject;
			}
		}
		
		return null;
	}

	/**
	 * Convenience method to convert jmatter objects to jaxb objects
	 * @param jmatterObject
	 * @return
	 */
	public static Object jmatterToJaxb( Object jmatterObject )
	{
		Class<?> jmatterClass = jmatterObject.getClass();
		String leafClassName = jmatterClass.getSimpleName();
		
		try
		{
			// take care of classes that don't have direct name correlation
			if( leafClassName.compareTo( "Constant" ) == 0 )
			{
				leafClassName = "ConstDef";
			}
			else if( leafClassName.compareTo( "ConstantSet" ) == 0 )
			{
				leafClassName = "DeclaredConstSet";
			}

			Class<?> jmatterToJaxbClass = Class.forName( "org.jts.gui.jmatterToJAXB." + leafClassName );
			Class<?> generatedClass = Class.forName( "com.u2d.generated." + leafClassName );
			Method convertMethod = jmatterToJaxbClass.getMethod( "convert", new Class[] { generatedClass } );
			
			return convertMethod.invoke( null, jmatterObject );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
