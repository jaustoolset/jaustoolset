/**
 * $Id: mxLightweightTextPane.java,v 1.2 2008/09/11 12:17:24 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.util;

import java.awt.Rectangle;

import javax.swing.JTextPane;

/**
 * @author Administrator
 * 
 */
public class mxLightweightTextPane extends JTextPane
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6771477489533614010L;

	/**
	 * 
	 */
	protected static mxLightweightTextPane sharedInstance;

	/**
	 * Initializes the shared instance.
	 */
	static
	{
		try
		{
			sharedInstance = new mxLightweightTextPane();
		}
		catch (Exception e)
		{
			// ignore
		}
	}

	/**
	 * 
	 */
	public static mxLightweightTextPane getSharedInstance()
	{
		return sharedInstance;
	}

	/**
	 * 
	 * 
	 */
	public mxLightweightTextPane()
	{
		setContentType("text/html");
		setOpaque(false);
		setBorder(null);
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void validate()
	{
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void revalidate()
	{
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void repaint(long tm, int x, int y, int width, int height)
	{
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void repaint(Rectangle r)
	{
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue)
	{
		// Strings get interned...
		if (propertyName == "document")
		{
			super.firePropertyChange(propertyName, oldValue, newValue);
		}
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void firePropertyChange(String propertyName, byte oldValue,
			byte newValue)
	{
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void firePropertyChange(String propertyName, char oldValue,
			char newValue)
	{
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void firePropertyChange(String propertyName, short oldValue,
			short newValue)
	{
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void firePropertyChange(String propertyName, int oldValue,
			int newValue)
	{
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void firePropertyChange(String propertyName, long oldValue,
			long newValue)
	{
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void firePropertyChange(String propertyName, float oldValue,
			float newValue)
	{
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void firePropertyChange(String propertyName, double oldValue,
			double newValue)
	{
	}

	/**
	 * Overridden for performance reasons.
	 * 
	 */
	public void firePropertyChange(String propertyName, boolean oldValue,
			boolean newValue)
	{
	}

}
