/**
 * $Id: mxDistanceCostFunction.java,v 1.1 2008/09/26 14:47:40 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.algebra;

import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;

/**
 * Implements a cost function for the euclidian length of an edge.
 */
public class mxDistanceCostFunction implements mxICostFunction
{

	/**
	 * Returns the euclidion length of the edge defined by the absolute
	 * points in the given state or 0 if no points are defined.
	 */
	public double getCost(mxCellState state)
	{
		double cost = 0;
		int pointCount = state.getAbsolutePointCount();

		if (pointCount > 0)
		{
			mxPoint last = state.getAbsolutePoint(0);

			for (int i = 1; i < pointCount; i++)
			{
				mxPoint point = state.getAbsolutePoint(i);
				cost += point.getPoint().distance(last.getPoint());
				last = point;
			}
		}

		return cost;
	}

}
