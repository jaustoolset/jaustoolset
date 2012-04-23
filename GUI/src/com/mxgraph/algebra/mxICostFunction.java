/**
 * $Id: mxICostFunction.java,v 1.2 2008/09/26 18:45:31 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.algebra;

import com.mxgraph.view.mxCellState;

/**
 * The cost function takes a cell and returns it's cost as a double. Two typical
 * examples of cost functions are the euclidian length of edges or a constant
 * number for each edge. To use one of the built-in cost functions, use either
 * <code>new mxDistanceCostFunction(graph)</code> or
 * <code>new mxConstantCostFunction(1)</code>.
 */
public interface mxICostFunction
{

	/**
	 * Evaluates the cost of the given cell state.
	 * 
	 * @param state The cell state to be evaluated
	 * @return Returns the cost to traverse the given cell state.
	 */
	double getCost(mxCellState state);

}
