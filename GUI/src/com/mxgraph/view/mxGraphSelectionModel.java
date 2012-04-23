/*
 * $Id: mxGraphSelectionModel.java,v 1.2 2009/02/24 14:48:49 gaudenz Exp $
 * Copyright (c) 2001-2005, Gaudenz Alder
 * 
 * All rights reserved.
 * 
 * See LICENSE file for license details. If you are unable to locate
 * this file please contact info (at) jgraph (dot) com.
 */
package com.mxgraph.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxUndoableEdit;
import com.mxgraph.util.mxUndoableEdit.mxUndoableChange;

/**
 * @author Administrator
 * 
 */
public class mxGraphSelectionModel extends mxEventSource
{
	/**
	 * Holds the name for the change event. First argument in the
	 * argument array is the collection of added cells, second argument
	 * is the collection of removed cells.
	 * 
	 * To add a change listener to the graph model:
	 * 
	 * <code>
	 * addListener(
	 *   mxSelectionModel.EVENT_CHANGE, new mxEventListener()
	 *   {
	 *     public void invoke(Object source, Object[] args)
	 *     {
	 *       selectionChanged((mxSelectionModel) source, (Collection) args[0],
	 *       	(Collection) args[1]);
	 *     }
	 *   });
	 * </code>
	 */
	public static String EVENT_CHANGE = "change";

	/**
	 * 
	 */
	public static String EVENT_UNDO = "undo";

	/**
	 * Reference to the enclosing graph.
	 */
	protected mxGraph graph;

	/**
	 * Specifies if only one selected item at a time is allowed.
	 * Default is false.
	 */
	protected boolean singleSelection = false;

	/**
	 * Holds the selection cells.
	 */
	protected Set cells = new LinkedHashSet();

	/**
	 * Constructs a new selection model for the specified graph.
	 * 
	 * @param graph
	 */
	public mxGraphSelectionModel(mxGraph graph)
	{
		this.graph = graph;
	}

	/**
	 * @return the singleSelection
	 */
	public boolean isSingleSelection()
	{
		return singleSelection;
	}

	/**
	 * @param singleSelection the singleSelection to set
	 */
	public void setSingleSelection(boolean singleSelection)
	{
		this.singleSelection = singleSelection;
	}

	/**
	 * Returns true if the given cell is selected.
	 * 
	 * @param cell
	 * @return Returns true if the given cell is selected.
	 */
	public boolean isSelected(Object cell)
	{
		return (cell == null) ? false : cells.contains(cell);
	}

	/**
	 * Returns true if no cells are selected.
	 */
	public boolean isEmpty()
	{
		return cells.isEmpty();
	}

	/**
	 * Returns the number of selected cells.
	 */
	public int size()
	{
		return cells.size();
	}

	/**
	 * Clears the selection.
	 */
	public void clear()
	{
		changeSelection(null, cells);
	}

	/**
	 * Returns the first selected cell.
	 */
	public Object getCell()
	{
		return (cells.isEmpty()) ? null : cells.iterator().next();
	}

	/**
	 * Returns the selection cells.
	 */
	public Object[] getCells()
	{
		return cells.toArray();
	}

	/**
	 * Clears the selection and adds the given cell to the selection.
	 */
	public void setCell(Object cell)
	{
		if (cell != null)
		{
			setCells(new Object[] { cell });
		}
		else
		{
			clear();
		}
	}

	/**
	 * Clears the selection and adds the given cells.
	 */
	public void setCells(Object[] cells)
	{
		if (cells != null)
		{
			if (singleSelection)
			{
				cells = new Object[] { getFirstSelectableCell(cells) };
			}

			List tmp = new ArrayList(cells.length);

			for (int i = 0; i < cells.length; i++)
			{
				if (graph.isCellSelectable(cells[i]))
				{
					tmp.add(cells[i]);
				}
			}

			changeSelection(tmp, this.cells);
		}
		else
		{
			clear();
		}
	}

	/**
	 * Returns the first selectable cell in the given array of cells.
	 * 
	 * @param cells Array of cells to return the first selectable cell for.
	 * @return Returns the first cell that may be selected.
	 */
	protected Object getFirstSelectableCell(Object[] cells)
	{
		if (cells != null)
		{
			for (int i = 0; i < cells.length; i++)
			{
				if (graph.isCellSelectable(cells[i]))
				{
					return cells[i];
				}
			}
		}

		return null;
	}

	/**
	 * Adds the given cell to the selection.
	 */
	public void addCell(Object cell)
	{
		if (cell != null)
		{
			addCells(new Object[] { cell });
		}
	}

	/**
	 * 
	 */
	public void addCells(Object[] cells)
	{
		if (cells != null)
		{
			Collection remove = null;

			if (singleSelection)
			{
				remove = this.cells;
				cells = new Object[] { getFirstSelectableCell(cells) };
			}

			List tmp = new ArrayList(cells.length);

			for (int i = 0; i < cells.length; i++)
			{
				if (!isSelected(cells[i]) && graph.isCellSelectable(cells[i]))
				{
					tmp.add(cells[i]);
				}
			}

			changeSelection(tmp, remove);
		}
	}

	/**
	 * Removes the given cell from the selection.
	 */
	public void removeCell(Object cell)
	{
		if (cell != null)
		{
			removeCells(new Object[] { cell });
		}
	}

	/**
	 * 
	 */
	public void removeCells(Object[] cells)
	{
		if (cells != null)
		{
			List tmp = new ArrayList(cells.length);

			for (int i = 0; i < cells.length; i++)
			{
				if (isSelected(cells[i]))
				{
					tmp.add(cells[i]);
				}
			}

			changeSelection(null, tmp);
		}
	}

	/**
	 * 
	 */
	protected void changeSelection(Collection added, Collection removed)
	{
		if ((added != null && !added.isEmpty())
				|| (removed != null && !removed.isEmpty()))
		{
			mxSelectionChange change = new mxSelectionChange(this, added,
					removed);
			change.execute();

			mxUndoableEdit edit = new mxUndoableEdit(this);
			edit.add(change);

			fireEvent(EVENT_UNDO, new Object[] { edit });
		}
	}

	/**
	 * 
	 */
	protected void cellAdded(Object cell)
	{
		if (cell != null)
		{
			cells.add(cell);
		}
	}

	/**
	 * 
	 */
	protected void cellRemoved(Object cell)
	{
		if (cell != null)
		{
			cells.remove(cell);
		}
	}

	/**
	 *
	 */
	public static class mxSelectionChange implements mxUndoableChange
	{

		/**
		 * 
		 */
		protected mxGraphSelectionModel model;

		/**
		 * 
		 */
		protected Collection added, removed;

		/**
		 * 
		 * @param model
		 * @param added
		 * @param removed
		 */
		public mxSelectionChange(mxGraphSelectionModel model, Collection added,
				Collection removed)
		{
			this.model = model;
			this.added = (added != null) ? new ArrayList(added) : null;
			this.removed = (removed != null) ? new ArrayList(removed) : null;
		}

		/**
		 * 
		 */
		public void execute()
		{
			if (removed != null)
			{
				Iterator it = removed.iterator();

				while (it.hasNext())
				{
					model.cellRemoved(it.next());
				}
			}

			if (added != null)
			{
				Iterator it = added.iterator();

				while (it.hasNext())
				{
					model.cellAdded(it.next());
				}
			}

			Collection tmp = added;
			added = removed;
			removed = tmp;

			model.fireEvent(EVENT_CHANGE, new Object[] { removed, added });
		}

	}

}
