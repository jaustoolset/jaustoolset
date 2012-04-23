/**
 * $Id: mxGeometry.java,v 1.24 2009/02/10 17:10:07 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.model;

import java.util.ArrayList;
import java.util.List;

import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;

/**
 * Represents the geometry of a cell. For vertices, the geometry consists
 * of the x- and y-location, as well as the width and height. For edges,
 * the geometry either defines the source- and target-terminal, or it
 * defines the respective terminal points.
 * 
 * For edges, if the geometry is relative (default), then the x-coordinate
 * is used to describe the distance from the center of the edge from -1 to 1
 * with 0 being the center of the edge and the default value, and the
 * y-coordinate is used to describe the absolute, orthogonal distance in
 * pixels from that point. In addition, the offset is used as an absolute
 * offset vector from the resulting point. 
 */
public class mxGeometry extends mxRectangle
{

	/**
	 * Stores alternate values for x, y, width and height in a rectangle.
	 * Default is null.
	 */
	protected mxRectangle alternateBounds;

	/**
	 * Defines the source- and target-point of the edge. This is used if the
	 * corresponding edge does not have a source vertex. Otherwise it is
	 * ignored. Default is null.
	 */
	protected mxPoint sourcePoint, targetPoint;

	/**
	 * List of mxPoints which specifies the control points along the edge.
	 * These points are the intermediate points on the edge, for the endpoints
	 * use targetPoint and sourcePoint or set the terminals of the edge to
	 * a non-null value. Default is null.
	 */
	protected List points;

	/**
	 * Holds the offset of the label for edges. This is the absolute vector
	 * between the center of the edge and the top, left point of the label.
	 * Default is null.
	 */
	protected mxPoint offset;

	/**
	 * Specifies if the coordinates in the geometry are to be interpreted as
	 * relative coordinates. Default is false. This is used to mark a geometry
	 * with an x- and y-coordinate that is used to describe an edge label
	 * position, or a relative location with respect to a parent cell's
	 * width and height.
	 */
	protected boolean relative = false;

	/**
	 * Constructs a new geometry at (0, 0) with the width and height set to 0.
	 */
	public mxGeometry()
	{
		this(0, 0, 0, 0);
	}

	/**
	 * Constructs a geometry using the given parameters.
	 * 
	 * @param x X-coordinate of the new geometry.
	 * @param y Y-coordinate of the new geometry.
	 * @param width Width of the new geometry.
	 * @param height Height of the new geometry.
	 */
	public mxGeometry(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}

	/**
	 * Constructs a copy of the given geometry.
	 * 
	 * @param geometry Geometry to construct a copy of.
	 */
	public mxGeometry(mxGeometry geometry)
	{
		this(geometry.getX(), geometry.getY(), geometry.getWidth(), geometry
				.getHeight());

		if (geometry.points != null)
		{
			points = new ArrayList(geometry.points.size());

			for (int i = 0; i < geometry.points.size(); i++)
			{
				points.add(((mxPoint) geometry.points.get(i)).clone());
			}
		}

		if (geometry.targetPoint != null)
		{
			targetPoint = (mxPoint) geometry.targetPoint.clone();
		}

		if (geometry.sourcePoint != null)
		{
			sourcePoint = (mxPoint) geometry.sourcePoint.clone();
		}

		if (geometry.offset != null)
		{
			offset = (mxPoint) geometry.offset.clone();
		}

		if (geometry.alternateBounds != null)
		{
			alternateBounds = (mxRectangle) geometry.alternateBounds.clone();
		}

		relative = geometry.relative;
	}

	/**
	 * Returns the alternate bounds.
	 */
	public mxRectangle getAlternateBounds()
	{
		return alternateBounds;
	}

	/**
	 * Sets the alternate bounds to the given rectangle.
	 * 
	 * @param rect Rectangle to be used for the alternate bounds.
	 */
	public void setAlternateBounds(mxRectangle rect)
	{
		alternateBounds = rect;
	}
	
	/**
	 * Returns the source point.
	 * 
	 * @return
	 */
	public mxPoint getSourcePoint()
	{
		return sourcePoint;
	}
	
	/**
	 * Sets the source point.
	 * 
	 * @param sourcePoint
	 */
	public void setSourcePoint(mxPoint sourcePoint)
	{
		this.sourcePoint = sourcePoint;
	}
	
	/**
	 * Returns the target point.
	 * 
	 * @return
	 */
	public mxPoint getTargetPoint()
	{
		return targetPoint;
	}
	
	/**
	 * Sets the target point.
	 * 
	 * @param sourcePoint
	 */
	public void setTargetPoint(mxPoint targetPoint)
	{
		this.targetPoint = targetPoint;
	}

	/**
	 * Returns the list of control points.
	 */
	public List getPoints()
	{
		return points;
	}

	/**
	 * Sets the list of control points to the given list.
	 * 
	 * @param value List that contains the new control points.
	 */
	public void setPoints(List value)
	{
		points = value;
	}

	/**
	 * Returns the offset.
	 */
	public mxPoint getOffset()
	{
		return offset;
	}

	/**
	 * Sets the offset to the given point.
	 * 
	 * @param offset Point to be used for the offset.
	 */
	public void setOffset(mxPoint offset)
	{
		this.offset = offset;
	}

	/**
	 * Returns true of the geometry is relative.
	 */
	public boolean isRelative()
	{
		return relative;
	}

	/**
	 * Sets the relative state of the geometry.
	 * 
	 * @param value Boolean value to be used as the new relative state.
	 */
	public void setRelative(boolean value)
	{
		relative = value;
	}

	/**
	 * Swaps the x, y, width and height with the values stored in
	 * alternateBounds and puts the previous values into alternateBounds as
	 * a rectangle. This operation is carried-out in-place, that is, using the
	 * existing geometry instance. If this operation is called during a graph
	 * model transactional change, then the geometry should be cloned before
	 * calling this method and setting the geometry of the cell using
	 * mxGraphModel.setGeometry.
	 */
	public void swap()
	{
		if (alternateBounds != null)
		{
			mxRectangle old = new mxRectangle(this);

			x = alternateBounds.getX();
			y = alternateBounds.getY();
			width = alternateBounds.getWidth();
			height = alternateBounds.getHeight();

			alternateBounds = old;
		}
	}

	/**
	 * Returns the point representing the source or target point of this edge.
	 * This is only used if the edge has no source or target vertex.
	 * 
	 * @param isSource Boolean that specifies if the source or target point
	 * should be returned.
	 * @return Returns the source or target point.
	 */
	public mxPoint getTerminalPoint(boolean isSource)
	{
		return (isSource) ? sourcePoint : targetPoint;
	}

	/**
	 * Sets the sourcePoint or targetPoint to the given point and returns the
	 * new point.
	 * 
	 * @param point Point to be used as the new source or target point.
	 * @param isSource Boolean that specifies if the source or target point
	 * should be set.
	 * @return Returns the new point.
	 */
	public mxPoint setTerminalPoint(mxPoint point, boolean isSource)
	{
		if (isSource)
		{
			sourcePoint = point;
		}
		else
		{
			targetPoint = point;
		}

		return point;
	}

	/**
	 * Returns a modified clone of this geometry which is translated by the
	 * specified amount. That is, in the returned geometry the x and y of the
	 * geometry, the sourcePoint, targetPoint and all elements of points are
	 * translated by the given amount. Keep in mind that this does NOT modify
	 * this geometry object in-place, instead it returns a modified cloned
	 * instance of this geometry.
	 * 
	 * @param dx Integer that specifies the x-coordinate of the translation.
	 * @param dy Integer that specifies the y-coordinate of the translation.
	 * @return Returns a new geometry translated by the given amount.
	 */
	public mxGeometry translate(double dx, double dy)
	{
		mxGeometry clone = (mxGeometry) clone();

		// Translates the geometry
		if (!clone.isRelative())
		{
			clone.x += dx;
			clone.y += dy;
		}

		// Translates the source point
		if (clone.sourcePoint != null)
		{
			clone.sourcePoint.setX(clone.sourcePoint.getX() + dx);
			clone.sourcePoint.setY(clone.sourcePoint.getY() + dy);
		}

		// Translates the target point
		if (clone.targetPoint != null)
		{
			clone.targetPoint.setX(clone.targetPoint.getX() + dx);
			clone.targetPoint.setY(clone.targetPoint.getY() + dy);
		}

		// Translate the control points
		if (clone.points != null)
		{
			int count = clone.points.size();

			for (int i = 0; i < count; i++)
			{
				mxPoint pt = (mxPoint) clone.points.get(i);

				pt.setX(pt.getX() + dx);
				pt.setY(pt.getY() + dy);
			}
		}

		return clone;
	}

	/**
	 * Returns a new instance of the same geometry.
	 */
	public Object clone()
	{
		return new mxGeometry(this);
	}

}
