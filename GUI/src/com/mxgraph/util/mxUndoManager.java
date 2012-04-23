/**
 * $Id: mxUndoManager.java,v 1.3 2008/07/10 15:35:37 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a 2-dimensional rectangle with double precision coordinates.
 */
public class mxUndoManager extends mxEventSource
{

	/**
	 * 
	 */
	public static final String EVENT_ADD = "add";

	/**
	 * 
	 */
	public static final String EVENT_UNDO = "undo";

	/**
	 * 
	 */
	public static final String EVENT_REDO = "redo";

	/**
	 * Maximum command history size. Default is 100.
	 */
	protected int size;

	/**
	 * List that contains the steps of the command history.
	 */
	protected List history;

	/**
	 * Index of the element to be added next.
	 */
	protected int indexOfNextAdd;

	/**
	 * Constructs a new undo manager with a default history size.
	 */
	public mxUndoManager()
	{
		this(100);
	}

	/**
	 * Constructs a new undo manager for the specified size.
	 */
	public mxUndoManager(int size)
	{
		this.size = size;
		reset();
	}

	/**
	 * Resets the command history.
	 */
	public void reset()
	{
		history = new ArrayList(size);
		indexOfNextAdd = 0;
	}

	/**
	 * Returns true if an undo is possible.
	 */
	public boolean canUndo()
	{
		return indexOfNextAdd > 0;
	}

	/**
	 * Undoes the last change.
	 */
	public void undo()
	{
		while (indexOfNextAdd > 0)
		{
			mxUndoableEdit edit = (mxUndoableEdit) history
					.get(--indexOfNextAdd);
			edit.undo();

			if (edit.isSignificant())
			{
				fireEvent(EVENT_UNDO, new Object[] { edit });
				break;
			}
		}
	}

	/**
	 * Returns true if a redo is possible.
	 */
	public boolean canRedo()
	{
		return indexOfNextAdd < history.size();
	}

	/**
	 * Redoes the last change.
	 */
	public void redo()
	{
		int n = history.size();

		while (indexOfNextAdd < n)
		{
			mxUndoableEdit edit = (mxUndoableEdit) history
					.get(indexOfNextAdd++);
			edit.redo();

			if (edit.isSignificant())
			{
				fireEvent(EVENT_REDO, new Object[] { edit });
				break;
			}
		}
	}

	/**
	 * Method to be called to add new undoable edits to the history.
	 */
	public void undoableEditHappened(mxUndoableEdit undoableEdit)
	{
		trim();

		if (size == history.size())
		{
			history.remove(0);
		}

		history.add(undoableEdit);
		indexOfNextAdd = history.size();
		fireEvent(EVENT_ADD, new Object[] { undoableEdit });
	}

	/**
	 * Removes all pending steps after indexOfNextAdd from the history,
	 * invoking die on each edit. This is called from undoableEditHappened.
	 */
	protected void trim()
	{
		while (history.size() > indexOfNextAdd)
		{
			mxUndoableEdit edit = (mxUndoableEdit) history
					.remove(indexOfNextAdd);
			edit.die();
		}
	}

}
