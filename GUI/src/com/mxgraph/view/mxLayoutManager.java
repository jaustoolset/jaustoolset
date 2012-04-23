package com.mxgraph.view;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxCellPath;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.model.mxGraphModel.mxChildChange;
import com.mxgraph.model.mxGraphModel.mxGeometryChange;
import com.mxgraph.model.mxGraphModel.mxRootChange;
import com.mxgraph.model.mxGraphModel.mxTerminalChange;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxUndoableEdit;

public class mxLayoutManager extends mxEventSource
{

	/**
	 * 
	 */
	public static String EVENT_BEFORE_LAYOUT = "beforeLayout";

	/**
	 * 
	 */
	public static String EVENT_LAYOUT = "layout";

	/**
	 * 
	 */
	public static String EVENT_AFTER_LAYOUT = "afterLayout";

	/**
	 * Defines the type of the source or target terminal. The type is a string
	 * passed to mxCell.is to check if the rule applies to a cell.
	 */
	protected mxGraph graph;

	/**
	 * Optional string that specifies the value of the attribute to be passed
	 * to mxCell.is to check if the rule applies to a cell.
	 */
	protected boolean enabled;

	/**
	 * Optional string that specifies the attributename to be passed to
	 * mxCell.is to check if the rule applies to a cell.
	 */
	protected boolean bubbling;

	/**
	 * 
	 */
	protected mxIEventListener updateHandler = new mxIEventListener()
	{
		public void invoke(Object source, Object[] args)
		{
			if (isEnabled() && source instanceof mxGraphModel
					&& ((mxGraphModel) source).getUpdateLevel() == 0)
			{
				beforeUpdate((mxUndoableEdit) args[0]);
			}
		}
	};

	/**
	 * 
	 */
	protected mxIEventListener moveHandler = new mxIEventListener()
	{
		public void invoke(Object source, Object[] args)
		{
			if (isEnabled())
			{
				cellsMoved((Object[]) args[0], (Point) args[2]);
			}
		}
	};

	/**
	 * 
	 */
	public mxLayoutManager(mxGraph graph)
	{
		setGraph(graph);
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled()
	{
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean value)
	{
		enabled = value;
	}

	/**
	 * @return the bubbling
	 */
	public boolean isBubbling()
	{
		return bubbling;
	}

	/**
	 * @param bubbling the bubbling to set
	 */
	public void setBubbling(boolean value)
	{
		bubbling = value;
	}

	/**
	 * @return the graph
	 */
	public mxGraph getGraph()
	{
		return graph;
	}

	/**
	 * @param graph the graph to set
	 */
	public void setGraph(mxGraph graph)
	{
		if (this.graph != null)
		{
			mxIGraphModel model = this.graph.getModel();
			model.removeListener(updateHandler);
			this.graph.removeListener(moveHandler);
		}

		this.graph = graph;

		if (this.graph != null)
		{
			mxIGraphModel model = this.graph.getModel();
			model
					.addListener(mxGraphModel.EVENT_END_UPDATE,
							this.updateHandler);
			this.graph.addListener(mxGraph.EVENT_MOVE, this.moveHandler);
		}
	}

	/**
	 * 
	 */
	protected mxIGraphLayout getLayout(Object parent)
	{
		return null;
	}

	/**
	 * 
	 */
	protected void cellsMoved(Object[] cells, Point location)
	{
		if (cells != null && location != null)
		{
			mxIGraphModel model = getGraph().getModel();

			// Checks if a layout exists to take care of the moving
			for (int i = 0; i < cells.length; i++)
			{
				mxIGraphLayout layout = getLayout(model.getParent(cells[i]));

				if (layout != null)
				{
					layout.moveCell(cells[i], location.x, location.y);
				}
			}
		}
	}

	/**
	 * 
	 */
	protected void beforeUpdate(mxUndoableEdit edit)
	{
		Collection cells = getCellsForChanges(edit.getChanges());
		mxIGraphModel model = getGraph().getModel();

		if (isBubbling())
		{
			Object[] tmp = mxGraphModel.getParents(model, cells.toArray());

			while (tmp.length > 0)
			{
				cells.addAll(Arrays.asList(tmp));
				tmp = mxGraphModel.getParents(model, tmp);
			}
		}

		layoutCells(sortCells(cells).toArray());
	}

	/**
	 * 
	 */
	protected Collection getCellsForChanges(List changes)
	{
		Set result = new HashSet();

		Iterator it = changes.iterator();

		while (it.hasNext())
		{
			Object change = it.next();

			if (change instanceof mxRootChange)
			{
				return new HashSet();
			}
			else
			{
				result.addAll(getCellsForChange(change));
			}
		}

		return result;
	}

	/**
	 * 
	 */
	protected Collection getCellsForChange(Object change)
	{
		mxIGraphModel model = getGraph().getModel();
		Set result = new HashSet();

		if (change instanceof mxChildChange)
		{
			mxChildChange cc = (mxChildChange) change;
			Object parent = model.getParent(cc.getChild());

			if(cc.getChild() != null)
			{
				result.add(cc.getChild());
			}
			
			if (parent != null)
			{
				result.add(parent);
			}

			if (cc.getPrevious() != null)
			{
				result.add(cc.getPrevious());
			}
		}
		else if (change instanceof mxTerminalChange
				|| change instanceof mxGeometryChange)
		{
			Object cell = (change instanceof mxTerminalChange) ? ((mxTerminalChange) change)
					.getCell()
					: ((mxGeometryChange) change).getCell();
			
			if (cell != null)
			{
				result.add(cell);
				Object parent = model.getParent(cell);
				
				if (parent != null)
				{
					result.add(parent);
				}
			}
		}

		return result;
	}

	/**
	 * 
	 */
	protected Collection sortCells(Collection cells)
	{
		mxIGraphModel model = getGraph().getModel();
		SortedSet result = new TreeSet(new Comparator()
		{
			public int compare(Object o1, Object o2)
			{
				String acp = mxCellPath.create((mxICell) o1);
				String bcp = mxCellPath.create((mxICell) o2);

				int comp = acp.compareTo(bcp);

				return (comp == 0) ? comp : ((comp < 0) ? 1 : -1);
			}
		});

		result.addAll(cells);

		return result;
	}

	/**
	 * 
	 */
	protected void layoutCells(Object[] cells)
	{
		if (cells.length > 0)
		{
			// Invokes the layouts while removing duplicates
			fireEvent(EVENT_BEFORE_LAYOUT, new Object[] { cells });
			mxIGraphModel model = getGraph().getModel();

			model.beginUpdate();
			try
			{
				for (int i = 0; i < cells.length; i++)
				{
					if (cells[i] != model.getRoot())
					{
						executeLayout(getLayout(cells[i]), cells[i]);
					}
				}

				fireEvent(EVENT_LAYOUT, new Object[] { cells });
			}
			finally
			{
				model.endUpdate();
			}

			fireEvent(EVENT_AFTER_LAYOUT, new Object[] { cells });
		}
	}

	/**
	 * 
	 */
	protected void executeLayout(mxIGraphLayout layout, Object parent)
	{
		if (layout != null && parent != null)
		{
			layout.execute(parent);
		}
	}

	/**
	 * 
	 */
	public void destroy()
	{
		setGraph(null);
	}

}
