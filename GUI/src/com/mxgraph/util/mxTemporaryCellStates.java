package com.mxgraph.util;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Hashtable;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;

public class mxTemporaryCellStates
{
	/**
	 * 
	 */
	protected mxGraphView view;
	
	/**
	 * 
	 */
	protected Hashtable oldStates;
	
	/**
	 * 
	 */
	protected double oldScale;
	
	/**
	 * 
	 * @param view
	 */
	public mxTemporaryCellStates(mxGraphView view)
	{
		this(view, 1, null);
	}
	
	/**
	 * 
	 * @param view
	 * @param scale
	 */
	public mxTemporaryCellStates(mxGraphView view, double scale)
	{
		this(view, scale, null);
	}
	
	/**
	 * 
	 * @param view
	 * @param scale
	 * @param cells
	 */
	public mxTemporaryCellStates(mxGraphView view, double scale, Object[] cells)
	{
		this.view = view;
		
		// Stores the old states
		oldStates = view.getStates();
		oldScale = view.getScale();
		
		// Creates space for the new states
		view.setStates(new Hashtable());
		view.setScale(scale);

		if (cells != null)
		{
			// Creates virtual parent state for validation
			mxCellState state = view.createState(new mxCell());
	
			// Validates the vertices and edges without adding them to
			// the model so that the original cells are not modified
			for (int i = 0; i < cells.length; i++)
			{
				view.validateBounds(state, cells[i]);
			}
	
			for (int i = 0; i < cells.length; i++)
			{
				view.validatePoints(state, cells[i]);
			}
		}
	}
	
	/**
	 * 
	 * @param graph
	 * @param cells
	 * @param scale
	 * @param background
	 * @param clip
	 */
	public void destroy()
	{
		view.setScale(oldScale);
		view.setStates(oldStates);
	}

}
