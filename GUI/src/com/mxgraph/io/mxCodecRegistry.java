/**
 * $Id: mxCodecRegistry.java,v 1.11 2009/01/21 16:37:20 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.io;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Singleton class that acts as a global registry for codecs. See
 * {@link mxCodec} for an example.
 */
public class mxCodecRegistry
{

	/**
	 * Maps from constructor names to codecs.
	 */
	static Hashtable codecs = new Hashtable();

	/**
	 * Holds the list of known packages. Packages are used to prefix short
	 * class names (eg. mxCell) in XML markup.
	 */
	static List packages = new ArrayList();

	// Registers the known codecs and package names
	static
	{
		addPackage("com.mxgraph");
		addPackage("com.mxgraph.util");
		addPackage("com.mxgraph.model");
		addPackage("com.mxgraph.view");
		addPackage("java.lang");
		addPackage("java.util");
		
		register(new mxObjectCodec(new ArrayList()));
		register(new mxModelCodec());
		register(new mxCellCodec());
		register(new mxStylesheetCodec());
	}

	/**
	 * Registers a new codec and associates the name of the template constructor
	 * in the codec with the codec object.
	 * 
	 * @param codec Codec to be registered under the name returned by
	 * {@link #getName(Class)} for the class of the codec's template.
	 */
	public static mxObjectCodec register(mxObjectCodec codec)
	{
		if (codec != null)
		{
			String name = getName(codec.getTemplate().getClass());
			codecs.put(name, codec);
		}
		
		return codec;
	}

	/**
	 * Returns a codec that handles the given object, which can be an object
	 * instance or an XML node.
	 * 
	 * @param name Java class name.
	 */
	public static mxObjectCodec getCodec(String name)
	{
		mxObjectCodec codec = (mxObjectCodec) codecs.get(name);

		// Registers a new default codec for the given name
		// if no codec has been previously defined.
		if (codec == null)
		{
			Object instance = getInstanceForName(name);
			
			if (instance != null)
			{
				try
				{
					codec = new mxObjectCodec(instance);
					register(codec);
				}
				catch (Exception e)
				{
					// ignore
				}
			}
		}
		
		return codec;
	}

	/**
	 * Adds the given package name to the list of known package names.
	 * 
	 * @param packagename Name of the package to be added.
	 */
	public static void addPackage(String packagename)
	{
		packages.add(packagename);
	}

	/**
	 * Creates and returns a new instance for the given class name.
	 * 
	 * @param name Name of the class to be instantiated.
	 * @return Returns a new instance of the given class.
	 */
	public static Object getInstanceForName(String name)
	{
		Class clazz = getClassForName(name);
		
		if (clazz != null)
		{
			try
			{
				return clazz.newInstance();
			}
			catch (Exception e)
			{
				// ignore
			}
		}
		
		return null;
	}
	
	/**
	 * Returns a class that corresponds to the given name.
	 * 
	 * @param name
	 * @return
	 */
	public static Class getClassForName(String name)
	{
		try
		{
			return Class.forName(name);
		}
		catch (Exception e2)
		{
			// ignore
		}
		
		for (int i = 0; i < packages.size(); i++)
		{
			try
			{
				String s = String.valueOf(packages.get(i));
				
				return Class.forName(s + "." + name);
			}
			catch (Exception e)
			{
				// ignore
			}
		}
		
		return null;
	}

	/**
	 * Returns the name that identifies the codec associated
	 * with the given class.
	 *
	 * The I/O system uses unqualified classnames, eg. for
	 * <code>com.mxgraph.model.mxCell</code> this returns
	 * <code>mxCell</code>.
	 * 
	 * @param type Class whose name should be returned.
	 * @return Returns a string that identifies the codec.
	 */
	public static String getName(Class type)
	{
		if (type.isArray() || Collection.class.isAssignableFrom(type)
				|| Map.class.isAssignableFrom(type))
		{
			return "Array";
		}
		else
		{
			if (packages.contains(type.getPackage().getName()))
			{
				return type.getSimpleName();
			}
			else
			{
				return type.getName();
			}
		}
	}

}
