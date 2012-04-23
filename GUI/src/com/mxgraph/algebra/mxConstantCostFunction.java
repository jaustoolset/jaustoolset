/**
 * $Id: mxConstantCostFunction.java,v 1.1 2008/09/26 14:47:40 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.algebra;

import com.mxgraph.view.mxCellState;

/**
 * Implements a cost function for a constant cost per traversed cell.
 */
public class mxConstantCostFunction implements mxICostFunction
{

	/**
	 * 
	 */
	protected double cost = 0;

	/**
	 * 
	 * @param cost
	 */
	public mxConstantCostFunction(double cost)
	{
		this.cost = cost;
	}

	/**
	 *
	 */
	public double getCost(mxCellState state)
	{
		return cost;
	}

}