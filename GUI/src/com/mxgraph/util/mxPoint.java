/**
 * $Id: mxPoint.java,v 1.2 2008/08/28 09:55:18 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.util;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * Implements a 2-dimensional point with double precision coordinates.
 */
public class mxPoint implements Serializable, Cloneable
{

	/**
	 * Holds the x- and y-coordinates of the point. Default is 0.
	 */
	protected double x, y;

	/**
	 * Constructs a new point at (0, 0).
	 */
	public mxPoint()
	{
		this(0, 0);
	}

	/**
	 * Constructs a new point at the location of the given point.
	 * 
	 * @param point Point that specifies the location.
	 */
	public mxPoint(Point2D point)
	{
		this(point.getX(), point.getY());
	}

	/**
	 * Constructs a new point at the location of the given point.
	 * 
	 * @param point Point that specifies the location.
	 */
	public mxPoint(mxPoint point)
	{
		this(point.getX(), point.getY());
	}

	/**
	 * Constructs a new point at (x, y).
	 * 
	 * @param x X-coordinate of the point to be created.
	 * @param y Y-coordinate of the point to be created.
	 */
	public mxPoint(double x, double y)
	{
		setX(x);
		setY(y);
	}

	/**
	 * Returns the x-coordinate of the point.
	 * 
	 * @return Returns the x-coordinate.
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * Sets the x-coordinate of the point.
	 * 
	 * @param value Double that specifies the new x-coordinate.
	 */
	public void setX(double value)
	{
		x = value;
	}

	/**
	 * Returns the x-coordinate of the point.
	 * 
	 * @return Returns the x-coordinate.
	 */
	public double getY()
	{
		return y;
	}

	/**
	 * Sets the x-coordinate of the point.
	 * 
	 * @param value Double that specifies the new x-coordinate.
	 */
	public void setY(double value)
	{
		y = value;
	}

	/**
	 * Returns the coordinates as a new point.
	 * 
	 * @return Returns a new point for the location.
	 */
	public Point getPoint()
	{
		return new Point((int) Math.round(x), (int) Math.round(y));
	}

	/**
	 * Returns a new instance of the same point.
	 */
	public Object clone()
	{
		return new mxPoint(this);
	}

}
