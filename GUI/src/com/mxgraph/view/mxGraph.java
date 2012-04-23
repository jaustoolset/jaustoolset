/**
 * $Id: mxGraph.java,v 1.211 2009/03/09 16:55:58 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import org.w3c.dom.Element;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxImageCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.model.mxGraphModel.Filter;
import com.mxgraph.model.mxGraphModel.mxChildChange;
import com.mxgraph.model.mxGraphModel.mxCollapseChange;
import com.mxgraph.model.mxGraphModel.mxGeometryChange;
import com.mxgraph.model.mxGraphModel.mxRootChange;
import com.mxgraph.model.mxGraphModel.mxStyleChange;
import com.mxgraph.model.mxGraphModel.mxTerminalChange;
import com.mxgraph.model.mxGraphModel.mxValueChange;
import com.mxgraph.model.mxGraphModel.mxVisibleChange;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxImage;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
// JTS
import org.jts.gui.mxGraphtoJAXB.mxInternalTransition;
import org.jts.gui.mxGraphtoJAXB.mxSimpleTransition;
import org.jts.gui.mxGraphtoJAXB.mxPushTransition;
import org.jts.gui.mxGraphtoJAXB.mxPopTransition;
import org.jts.gui.mxGraphtoJAXB.mxDefaultInternalTransition;
import org.jts.gui.mxGraphtoJAXB.mxDefaultSimpleTransition;
import org.jts.gui.mxGraphtoJAXB.mxDefaultPushTransition;
import org.jts.gui.mxGraphtoJAXB.mxDefaultPopTransition;
import org.jts.gui.mxGraphtoJAXB.mxParameter;
import org.jts.gui.mxGraphtoJAXB.parser.ParseException;

import javax.swing.JOptionPane;
// jts

/**
 * Implements a graph object that allows to create diagrams from a graph model
 * and stylesheet.
 *
 * <h3>Images</h3>
 * To create an image from a graph, use the following code for a given
 * XML document (doc) and File (file):
 *
 * <code>
 * Image img = mxCellRenderer.createBufferedImage(
 * 		graph, null, 1, Color.WHITE, false, null);
 * ImageIO.write(img, "png", file);
 * </code>
 *
 * If the XML is given as a string rather than a document, the document can
 * be obtained using mxUtils.parse.
 */
public class mxGraph extends mxEventSource
{

	/**
	 * Adds required resources.
	 */
	static
	{
		mxResources.add("com.mxgraph.resources.graph");
	}

	/**
	 * Holds the version number of this release. Current version
	 * is 0.16.0.3.
	 */
	public static final String VERSION = "0.16.0.3";

	/**
	 *
	 */
	public static String EVENT_BEFORE_SHOW = "beforeShow";

	/**
	 *
	 */
	public static String EVENT_BEFORE_HIDE = "beforeHide";

	/**
	 *
	 */
	public static String EVENT_BEFORE_REMOVE = "beforeRemove";

	/**
	 *
	 */
	public static String EVENT_SHOW = "show";

	/**
	 *
	 */
	public static String EVENT_HIDE = "hide";

	/**
	 *
	 */
	public static String EVENT_REMOVE = "remove";

	/**
	 *
	 */
	public static String EVENT_AFTER_SHOW = "afterShow";

	/**
	 *
	 */
	public static String EVENT_AFTER_HIDE = "afterHide";

	/**
	 *
	 */
	public static String EVENT_AFTER_REMOVE = "afterRemove";

	/**
	 *
	 */
	public static String EVENT_BEFORE_MOVE = "beforeMove";

	/**
	 *
	 */
	public static String EVENT_MOVE = "move";

	/**
	 *
	 */
	public static String EVENT_CLONE = "clone";

	/**
	 *
	 */
	public static String EVENT_AFTER_MOVE = "afterMove";

	/**
	 *
	 */
	public static String EVENT_BEFORE_ADD = "beforeAdd";

	/**
	 *
	 */
	public static String EVENT_ADD = "add";

	/**
	 *
	 */
	public static String EVENT_AFTER_ADD = "afterAdd";

	/**
	 *
	 */
	public static String EVENT_BEFORE_CONNECT = "beforeConnect";

	/**
	 *
	 */
	public static String EVENT_CONNECT = "connect";

	/**
	 *
	 */
	public static String EVENT_AFTER_CONNECT = "afterConnect";

	/**
	 *
	 */
	public static String EVENT_BEFORE_DISCONNECT = "beforeDisconnect";

	/**
	 *
	 */
	public static String EVENT_DISCONNECT = "disconnect";

	/**
	 *
	 */
	public static String EVENT_AFTER_DISCONNECT = "afterDisconnect";

	/**
	 *
	 */
	public static String EVENT_BEFORE_RESIZE = "beforeResize";

	/**
	 *
	 */
	public static String EVENT_RESIZE = "resize";

	/**
	 *
	 */
	public static String EVENT_AFTER_RESIZE = "afterResize";

	/**
	 *
	 */
	public static String EVENT_BEFORE_FOLD = "beforeFold";

	/**
	 *
	 */
	public static String EVENT_FOLD = "fold";

	/**
	 *
	 */
	public static String EVENT_AFTER_FOLD = "afterFold";

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
	 *
	 */
	public static String EVENT_BEFORE_UPDATESIZE = "beforeUpdateSize";

	/**
	 *
	 */
	public static String EVENT_UPDATESIZE = "updateSize";

	/**
	 *
	 */
	public static String EVENT_AFTER_UPDATESIZE = "afterUpdateSize";

	/**
	 *
	 */
	public static String EVENT_FLIP = "flip";

	/**
	 *
	 */
	public static String EVENT_INDEX_CHANGED = "indexChanged";

	/**
	 *
	 */
	public static String EVENT_REPAINT = "repaint";

	/**
	 *
	 */
	public interface mxICellVisitor
	{

		/**
		 *
		 * @param vertex
		 * @param edge
		 */
		boolean visit(Object vertex, Object edge);

	}

	/**
	 * Property change event handling.
	 */
	protected PropertyChangeSupport changeSupport = new PropertyChangeSupport(
			this);

	/**
	 * Holds the model that contains the cells to be displayed.
	 */
	protected mxIGraphModel model;

	/**
	 * Holds the view that caches the cell states.
	 */
	protected mxGraphView view;

	/**
	 * Holds the stylesheet that defines the appearance of the cells.
	 */
	protected mxStylesheet stylesheet;

	/**
	 * Holds the <mxGraphSelection> that models the current selection.
	 */
	protected mxGraphSelectionModel selectionModel;

	/**
	 * Specifies the grid size. Default is 10.
	 */
	protected int gridSize = 10;

	/**
	 * Specifies if the grid is enabled. Default is true.
	 */
	protected boolean gridEnabled = true;

	/**
	 * Value returned by getOverlap if isAllowOverlapParent returns
	 * true for the given cell. getOverlap is used in keepInside if
	 * isKeepInsideParentOnMove returns true. The value specifies the
	 * portion of the child which is allowed to overlap the parent.
	 */
	protected double defaultOverlap = 0.5;

	/**
	 * Specifies the default parent to be used to insert new cells.
	 * This is used in getDefaultParent. Default is null.
	 */
	protected Object defaultParent;

	/**
	 * Specifies the alternate edge style to be used if the main control point
	 * on an edge is being doubleclicked. Default is null.
	 */
	protected String alternateEdgeStyle;

	/**
	 * Specifies the return value for isEnabled. Default is true.
	 */
	protected boolean enabled = true;

	/**
	 * Specifies the return value for isCell(s)Locked. Default is false.
	 */
	protected boolean cellsLocked = false;

	/**
	 * Specifies the return value for isCell(s)Editable. Default is true.
	 */
	protected boolean cellsEditable = true;

	/**
	 * Specifies the return value for isCell(s)Sizable. Default is true.
	 */
	protected boolean cellsSizable = true;

	/**
	 * Specifies the return value for isCell(s)Movable. Default is true.
	 */
	protected boolean cellsMovable = true;

	/**
	 * Specifies the return value for isCell(s)Bendable. Default is true.
	 */
	protected boolean cellsBendable = true;

	/**
	 * Specifies the return value for isCell(s)Selectable. Default is true.
	 */
	protected boolean cellsSelectable = true;

	/**
	 * Specifies the return value for isCell(s)Deletable. Default is true.
	 */
	protected boolean cellsDeletable = true;

	/**
	 * Specifies the return value for isCell(s)Cloneable. Default is true.
	 */
	protected boolean cellsCloneable = true;


	/**
	 * Specifies the return value for isCellDisconntableFromTerminal. Default
	 * is true.
	 */
	protected boolean cellsDisconnectable = true;

	/**
	 * Specifies the return value for isLabel(s)Clipped. Default is false.
	 */
	protected boolean labelsClipped = false;

	/**
	 * Specifies the return value for edges in isLabelMovable. Default is true.
	 */
	protected boolean edgeLabelsMovable = true;

	/**
	 * Specifies the return value for vertices in isLabelMovable. Default is false.
	 */
	protected boolean vertexLabelsMovable = false;

	/**
	 * Specifies the return value for isDropEnabled. Default is false.
	 */
	protected boolean dropEnabled = true;

	/**
	 * Specifies if the graph should automatically update the cell size
	 * after an edit. This is used in isAutoSizeCell. Default is false.
	 */
	protected boolean autoSizeCells = false;

	/**
	 * <mxRectangle> that specifies the area in which all cells in the
	 * diagram should be placed. Uses in getMaximumGraphBounds. Use a width
	 * or height of 0 if you only want to give a upper, left corner.
	 */
	protected mxRectangle maximumGraphBounds = null;

	/**
	 * mxRectangle that specifies the minimum size of the graph canvas inside
	 * the scrollpane.
	 */
	protected mxRectangle minimumGraphSize = null;

	/**
	 * Border to be added to the bottom and right side when the container is
	 * being resized after the graph has been changed. Default is 0.
	 */
	protected int border = 0;

	/**
	 * Specifies if edges should appear in the foreground regardless of their
	 * order in the model. This has precendence over keepEdgeInBackground
	 * Default is false.
	 */
	protected boolean keepEdgesInForeground = false;

	/**
	 * Specifies if edges should appear in the background regardless of their
	 * order in the model. Default is false.
	 */
	protected boolean keepEdgesInBackground = false;

	/**
	 * Specifies the return value for isKeepInsideParentOnMove. Default is
	 * true.
	 */
	protected boolean keepInsideParentOnMove = true;


	/**
	 * Specifies if a parent should contain the child bounds after a resize of
	 * the child. Default is true.
	 */
	protected boolean extendParentOnResize = true;

	/**
	 * Specifies if the cell size should be changed to the preferred size when
	 * a cell is first collapsed. Default is true.
	 */
	protected boolean collapseToPreferredSize = true;

	/**
	 * Specifies if loops (aka self-references) are allowed.
	 * Default is false.
	 */
	protected boolean resetEdgesOnResize = false;

	/**
	 * Specifies if edge control points should be reset after
	 * the move of a connected cell. Default is false.
	 */
	protected boolean resetEdgesOnMove = false;

	/**
	 * Specifies if loops (aka self-references) are allowed.
	 * Default is false.
	 */
	protected boolean allowLoops = true;

	/**
	 * Specifies the multiplicities to be used for validation of the graph.
	 */
	protected mxMultiplicity[] multiplicities;

	/**
	 * Specifies the default style for loops.
	 */
	protected mxEdgeStyle.mxEdgeStyleFunction defaultLoopStyle = mxEdgeStyle.Loop;

	/**
	 * Specifies if multiple edges in the same direction between
	 * the same pair of vertices are allowed. Default is true.
	 */
	protected boolean multigraph = true;

	/**
	 * Specifies if edges are connectable. Default is false.
	 * This overrides the connectable field in edges.
	 */
	protected boolean connectableEdges = false;

	/**
	 * Specifies if edges with disconnected terminals are
	 * allowed in the graph. Default is false.
	 */
	protected boolean allowDanglingEdges = true;

	/**
	 * Specifies if edges that are cloned should be validated and only inserted
	 * if they are valid. Default is true.
	 */
	protected boolean cloneInvalidEdges = false;

	/**
	 * Specifies if edges should be disconnected from their terminals when they
	 * are moved. Default is true.
	 */
	protected boolean disconnectOnMove = true;

	/**
	 * Specifies if labels should be visible. This is used in
	 * getLabel. Default is true.
	 */
	protected boolean labelsVisible = true;

	/**
	 * Specifies the return value for isHtmlLabel. Default is false.
	 */
	protected boolean htmlLabels = false;

	/**
	 * Specifies if nesting of swimlanes is allowed. Default is true.
	 */
	protected boolean swimlaneNesting = true;

	/**
	 * Specifies the mxImage to indicate a collapsed state.
	 * Default value is mxClient.imageBasePath+'collapsed.gif'
	 */
	protected mxImage collapsedImage;

	/**
	 * Specifies the mxImage to indicate a expanded state.
	 * Default value is mxClient.imageBasePath+'expanded.gif'
	 */
	protected mxImage expandedImage;

	/**
	 * Specifies the mxImage for the image to be used to
	 * display a warning overlay. See setWarning. Default value is
	 * mxClient.imageBasePath+'warning'
	 */
	protected mxImage warningImage;

	/**
	 * Fires repaint events for full repaints.
	 */
	protected mxIEventListener fullRepaintHandler = new mxIEventListener()
	{
		public void invoke(Object sender, Object[] args)
		{
			fireEvent(EVENT_REPAINT);
		}
	};

	/**
	 * Fires repaint events for full repaints.
	 */
	protected mxIEventListener invalidateAndRepaintHandler = new mxIEventListener()
	{
		public void invoke(Object sender, Object[] args)
		{
			view.invalidate();
			fireEvent(EVENT_REPAINT);
		}
	};

	/**
	 * Fires repaint events for model changes.
	 */
	protected mxIEventListener graphModelChangeHandler = new mxIEventListener()
	{
		public void invoke(Object sender, Object[] args)
		{
			mxRectangle dirty = graphModelChanged((mxIGraphModel) sender,
					(List) args[0]);
			fireEvent(EVENT_REPAINT, new Object[] { dirty });
		}
	};

	/**
	 * Constructs a new graph with an empty
	 * {@link com.mxgraph.model.mxGraphModel}.
	 */
	public mxGraph()
	{
		this(null, null);
	}

	/**
	 * Constructs a new graph for the specified model. If no model is
	 * specified, then a new, empty {@link com.mxgraph.model.mxGraphModel} is
	 * used.
	 *
	 * @param model Model that contains the graph data
	 */
	public mxGraph(mxIGraphModel model)
	{
		this(model, null);
	}

	/**
	 * Constructs a new graph for the specified model. If no model is
	 * specified, then a new, empty {@link com.mxgraph.model.mxGraphModel} is
	 * used.
	 *
	 * @param stylesheet The stylesheet to use for the graph.
	 */
	public mxGraph(mxStylesheet stylesheet)
	{
		this(null, stylesheet);
	}

	/**
	 * Constructs a new graph for the specified model. If no model is
	 * specified, then a new, empty {@link com.mxgraph.model.mxGraphModel} is
	 * used.
	 *
	 * @param model Model that contains the graph data
	 */
	public mxGraph(mxIGraphModel model, mxStylesheet stylesheet)
	{
		selectionModel = createSelectionModel();
		setModel((model != null) ? model : new mxGraphModel());
		setStylesheet((stylesheet != null) ? stylesheet : createStylesheet());
		setView(createGraphView());
	}

	/**
	 * Constructs a new selection model to be used in this graph.
	 */
	protected mxGraphSelectionModel createSelectionModel()
	{
		return new mxGraphSelectionModel(this);
	}

	/**
	 * Constructs a new stylesheet to be used in this graph.
	 */
	protected mxStylesheet createStylesheet()
	{
		return new mxStylesheet();
	}

	/**
	 * Constructs a new view to be used in this graph.
	 */
	protected mxGraphView createGraphView()
	{
		return new mxGraphView(this);
	}

	/**
	 * Returns the graph model that contains the graph data.
	 *
	 * @return Returns the model that contains the graph data
	 */
	public mxIGraphModel getModel()
	{
		return model;
	}

	/**
	 * Sets the graph model that contains the data, and fires an
	 * EVENT_MODE_CHANGED followed by an EVENT_REPAINT event.
	 *
	 * @param model Model that contains the graph data
	 */
	public void setModel(mxIGraphModel model)
	{
		if (this.model != null)
		{
			this.model.removeListener(graphModelChangeHandler);
		}

		Object oldModel = this.model;
		this.model = model;

		if (view != null)
		{
			view.revalidate();
		}

		model.addListener(mxIGraphModel.EVENT_CHANGE, graphModelChangeHandler);
		changeSupport.firePropertyChange("model", oldModel, model);
		fireEvent(EVENT_REPAINT);
	}

	/**
	 * Returns the stylesheet that provides the style.
	 *
	 * @return Returns the stylesheet that provides the style.
	 */
	public mxStylesheet getStylesheet()
	{
		return stylesheet;
	}

	/**
	 * Sets the stylesheet that provides the style.
	 *
	 * @param stylesheet Stylesheet that provides the style.
	 */
	public void setStylesheet(mxStylesheet stylesheet)
	{
		mxStylesheet oldValue = this.stylesheet;
		this.stylesheet = stylesheet;

		changeSupport.firePropertyChange("stylesheet", oldValue, stylesheet);
	}

	/**
	 * Returns the view that contains the cell states.
	 *
	 * @return Returns the view that contains the cell states
	 */
	public mxGraphView getView()
	{
		return view;
	}

	/**
	 * Sets the view that contains the cell states and fires an
	 * EVENT_VIEW_CHANGED event.
	 *
	 * @param view View that contains the cell states
	 */
	public void setView(mxGraphView view)
	{
		if (this.view != null)
		{
			this.view.removeListener(fullRepaintHandler);
		}

		Object oldView = this.view;
		this.view = view;

		if (this.view != null)
		{
			this.view.revalidate();
		}

		// Listens to changes in the view
		view.addListener(mxGraphView.EVENT_SCALE, fullRepaintHandler);
		view.addListener(mxGraphView.EVENT_TRANSLATE, fullRepaintHandler);
		view.addListener(mxGraphView.EVENT_SCALE_AND_TRANSLATE,
				fullRepaintHandler);
		view.addListener(mxGraphView.EVENT_UP, fullRepaintHandler);
		view.addListener(mxGraphView.EVENT_DOWN, fullRepaintHandler);

		changeSupport.firePropertyChange("view", oldView, view);
	}

	/**
	 *
	 */
	public void selectCellsForChanges(List changes)
	{
		List cells = new ArrayList();
		Iterator it = changes.iterator();

		while (it.hasNext())
		{
			Object change = it.next();

			if (change instanceof mxChildChange)
			{
				cells.add(((mxChildChange) change).getChild());
			}
			else if (change instanceof mxTerminalChange)
			{
				cells.add(((mxTerminalChange) change).getCell());
			}
			else if (change instanceof mxValueChange)
			{
				cells.add(((mxValueChange) change).getCell());
			}
			else if (change instanceof mxStyleChange)
			{
				cells.add(((mxStyleChange) change).getCell());
			}
			else if (change instanceof mxGeometryChange)
			{
				cells.add(((mxGeometryChange) change).getCell());
			}
			else if (change instanceof mxCollapseChange)
			{
				cells.add(((mxCollapseChange) change).getCell());
			}
			else if (change instanceof mxVisibleChange)
			{
				mxVisibleChange vc = (mxVisibleChange) change;

				if (vc.isVisible())
				{
					cells.add(((mxVisibleChange) change).getCell());
				}
			}
		}

		setSelectionCells(cells);
	}

	/**
	 * Called when the graph model changes. Invokes processChange on each
	 * item of the given array to update the view accordingly.
	 */
	public mxRectangle graphModelChanged(mxIGraphModel sender, List changes)
	{
		mxRectangle dirty = processChanges(changes, true);
		view.validate();
		mxRectangle tmp = processChanges(changes, false);

		if (tmp != null)
		{
			if (dirty == null)
			{
				dirty = tmp;
			}
			else
			{
				dirty.add(tmp);
			}
		}

		return dirty;
	}

	/**
	 * Processes the changes and returns the minimal rectangle to be
	 * repainted in the buffer. A return value of null means no repaint
	 * is required.
	 */
	public mxRectangle processChanges(List changes, boolean invalidate)
	{
		mxRectangle bounds = null;
		Iterator it = changes.iterator();

		while (it.hasNext())
		{
			mxRectangle rect = processChange(it.next(), invalidate);

			if (bounds == null)
			{
				bounds = rect;
			}
			else
			{
				bounds.add(rect);
			}
		}

		return bounds;
	}

	/**
	 * Processes the given change and invalidates the respective cached data
	 * in <view>. This fires a <root> event if the root has changed in the
	 * model.
	 */
	public mxRectangle processChange(Object change, boolean invalidate)
	{
		mxRectangle result = null;

		if (change instanceof mxRootChange)
		{
			result = getBounds();

			if (invalidate)
			{
				clearSelection();
				cellRemoved(((mxRootChange) change).getPrevious(), false);
			}
		}
		else if (change instanceof mxChildChange)
		{
			mxChildChange cc = (mxChildChange) change;

			// Repaints the parent area if it is a rendered cell (vertex or
			// edge) otherwise only the child area is repainted, same holds
			// if the parent and previous are the same object, in which case
			// only the child area needs to be repainted (change of order)
			if (cc.getParent() != cc.getPrevious())
			{
				if (model.isVertex(cc.getParent())
						|| model.isEdge(cc.getParent()))
				{
					result = getBoundingBox(cc.getParent(), true, true);
				}

				if (model.isVertex(cc.getPrevious())
						|| model.isEdge(cc.getPrevious()))
				{
					if (result != null)
					{
						result
								.add(getBoundingBox(cc.getPrevious(), true,
										true));
					}
					else
					{
						result = getBoundingBox(cc.getPrevious(), true, true);
					}
				}
			}

			if (result == null)
			{
				result = getBoundingBox(cc.getChild(), true, true);
			}

			if (invalidate)
			{
				if (cc.getParent() != null)
				{
					view.clear(cc.getChild(), false, true);
				}
				else
				{
					cellRemoved(cc.getChild(), true);
				}
			}
		}
		else if (change instanceof mxTerminalChange)
		{
			Object cell = ((mxTerminalChange) change).getCell();
			result = getBoundingBox(cell, true);

			if (invalidate)
			{
				view.invalidate(cell);
			}
		}
		else if (change instanceof mxValueChange)
		{
			Object cell = ((mxValueChange) change).getCell();
			result = getBoundingBox(cell);

			if (invalidate)
			{
				view.clear(cell, false, false);
			}
		}
		else if (change instanceof mxStyleChange)
		{
			Object cell = ((mxStyleChange) change).getCell();
			result = getBoundingBox(cell, true);

			if (invalidate)
			{
				// TODO: Add includeEdges argument to clear method for
				// not having to call invalidate in this case (where it
				// is possible that the perimeter has changed, which
				// means the connected edges need to be invalidated)
				view.clear(cell, false, false);
				view.invalidate(cell);
			}
		}
		else if (change instanceof mxGeometryChange)
		{
			Object cell = ((mxGeometryChange) change).getCell();
			result = getBoundingBox(cell, true, true);

			if (invalidate)
			{
				view.invalidate(cell);
			}
		}
		else if (change instanceof mxCollapseChange)
		{
			Object cell = ((mxCollapseChange) change).getCell();
			result = getBoundingBox(((mxCollapseChange) change).getCell(),
					true, true);

			if (invalidate)
			{
				cellRemoved(cell, false);
			}
		}
		else if (change instanceof mxVisibleChange)
		{
			Object cell = ((mxVisibleChange) change).getCell();
			result = getBoundingBox(((mxVisibleChange) change).getCell(), true,
					true);

			if (invalidate)
			{
				cellRemoved(cell, true);
			}
		}

		return result;
	}

	/**
	 * Removes all cached information for the given cell and its descendants
	 * and clears the cells selection state is isClearSelection is true. This
	 * is called when a cell has been removed from the model.
	 */
	protected void cellRemoved(Object cell, boolean clearSelection)
	{
		if (clearSelection && isSelected(cell))
		{
			removeSelectionCell(cell);
		}

		int childCount = model.getChildCount(cell);

		for (int i = 0; i < childCount; i++)
		{
			cellRemoved(model.getChildAt(cell, i), clearSelection);
		}

		view.removeState(cell);
	}

	//
	// Cell styles
	//

	/**
	 * Returns an array of key, value pairs representing the cell style for the
	 * given cell. If no string is defined in the model that specifies the
	 * style, then the default style for the cell is returned or <EMPTY_ARRAY>,
	 * if not style can be found.
	 *
	 * @param cell Cell whose style should be returned.
	 * @return Returns the style of the cell.
	 */
	public Hashtable getCellStyle(Object cell)
	{
		Hashtable style = (model.isEdge(cell)) ? stylesheet
				.getDefaultEdgeStyle() : stylesheet.getDefaultVertexStyle();

		String name = model.getStyle(cell);

		if (name != null)
		{
			style = stylesheet.getCellStyle(name, style);
		}

		if (style == null)
		{
			style = mxStylesheet.EMPTY_STYLE;
		}

		return style;
	}

	/**
	 * Sets the style of the selection cells to the given value.
	 *
	 * @param style String representing the new style of the cells.
	 */
	public void setCellStyle(String style)
	{
		setCellStyle(style, null);
	}

	/**
	 * Sets the style of the specified cells. If no cells are given, then the
	 * selection cells are changed.
	 *
	 * @param style String representing the new style of the cells.
	 * @param cells Optional array of <mxCells> to set the style for. Default is the
	 * selection cells.
	 */
	public void setCellStyle(String style, Object[] cells)
	{
		if (cells == null)
		{
			cells = getSelectionCells();
		}

		if (cells != null)
		{
			model.beginUpdate();
			try
			{
				for (int i = 0; i < cells.length; i++)
				{
					model.setStyle(cells[i], style);
				}
			}
			finally
			{
				model.endUpdate();
			}
		}
	}

	/**
	 * Toggles the boolean value for the given key in the style of the
	 * given cell. If no cell is specified then the selection cell is
	 * used.
	 *
	 * @param key Key for the boolean value to be toggled.
	 * @param defaultValue Default boolean value if no value is defined.
	 * @param cell Cell whose style should be modified.
	 */
	public void toggleCellStyle(String key, boolean defaultValue, Object cell)
	{
		toggleCellStyles(key, defaultValue, new Object[] { cell });
	}

	/**
	 * Toggles the boolean value for the given key in the style of the
	 * selection cells.
	 *
	 * @param key Key for the boolean value to be toggled.
	 * @param defaultValue Default boolean value if no value is defined.
	 */
	public void toggleCellStyles(String key, boolean defaultValue)
	{
		toggleCellStyles(key, defaultValue, null);
	}

	/**
	 * Toggles the boolean value for the given key in the style of the given
	 * cells. If no cells are specified, then the selection cells are used. For
	 * example, this can be used to toggle mxConstants.STYLE_ROUNDED or any
	 * other style with a boolean value.
	 *
	 * @param key String representing the key of the boolean style to be toggled.
	 * @param defaultValue Default boolean value if no value is defined.
	 * @param cells Cells whose styles should be modified.
	 */
	public void toggleCellStyles(String key, boolean defaultValue,
			Object[] cells)
	{
		if (cells == null)
		{
			cells = getSelectionCells();
		}

		if (cells != null && cells.length > 0)
		{
			mxCellState state = view.getState(cells[0]);
			Hashtable style = (state != null) ? state.getStyle()
					: getCellStyle(cells[0]);

			if (style != null)
			{
				String value = (mxUtils.isTrue(style, key, defaultValue)) ? "0"
						: "1";
				setCellStyles(key, value, cells);
			}
		}
	}

	/**
	 * Sets the key to value in the styles of the selection cells.
	 *
	 * @param key String representing the key to be assigned.
	 * @param value String representing the new value for the key.
	 */
	public void setCellStyles(String key, String value)
	{
		setCellStyles(key, value, null);
	}

	/**
	 * Sets the key to value in the styles of the given cells. This will modify
	 * the existing cell styles in-place and override any existing assignment
	 * for the given key. If no cells are specified, then the selection cells
	 * are changed. If no value is specified, then the respective key is
	 * removed from the styles.
	 *
	 * @param key String representing the key to be assigned.
	 * @param value String representing the new value for the key.
	 * @param cells Array of cells to change the style for.
	 */
	public void setCellStyles(String key, String value, Object[] cells)
	{
		if (cells == null)
		{
			cells = getSelectionCells();
		}

		mxUtils.setCellStyles(model, cells, key, value);
	}

	/**
	 * Toggles the given bit for the given key in the styles of the selection
	 * cells.
	 *
	 * @param key String representing the key to toggle the flag in.
	 * @param flag Integer that represents the bit to be toggled.
	 */
	public void toggleCellStyleFlags(String key, int flag)
	{
		toggleCellStyleFlags(key, flag, null);
	}

	/**
	 * Toggles the given bit for the given key in the styles of the specified
	 * cells.
	 *
	 * @param key String representing the key to toggle the flag in.
	 * @param flag Integer that represents the bit to be toggled.
	 * @param cells Optional array of <mxCells> to change the style for. Default is
	 * the selection cells.
	 */
	public void toggleCellStyleFlags(String key, int flag, Object[] cells)
	{
		setCellStyleFlags(key, flag, null, cells);
	}

	/**
	 * Sets or toggles the given bit for the given key in the styles of the
	 * selection cells.
	 *
	 * @param key String representing the key to toggle the flag in.
	 * @param flag Integer that represents the bit to be toggled.
	 * @param value Boolean value to be used or null if the value should be
	 * toggled.
	 */
	public void setCellStyleFlags(String key, int flag, boolean value)
	{
		setCellStyleFlags(key, flag, value, null);
	}

	/**
	 * Sets or toggles the given bit for the given key in the styles of the
	 * specified cells.
	 *
	 * @param key String representing the key to toggle the flag in.
	 * @param flag Integer that represents the bit to be toggled.
	 * @param value Boolean value to be used or null if the value should be
	 * toggled.
	 * @param cells Optional array of cells to change the style for. If no
	 * cells are specified then the selection cells are used.
	 */
	public void setCellStyleFlags(String key, int flag, Boolean value,
			Object[] cells)
	{
		if (cells == null)
		{
			cells = getSelectionCells();
		}

		if (cells != null && cells.length > 0)
		{
			if (value == null)
			{
				mxCellState state = view.getState(cells[0]);
				Hashtable style = (state != null) ? state.getStyle()
						: getCellStyle(cells[0]);

				if (style != null)
				{
					int current = mxUtils.getInt(style, key);
					value = !((current & flag) == flag);
				}
			}

			mxUtils.setCellStyleFlags(model, cells, key, flag, value);
		}
	}

	//
	// Cell alignment and orientation
	//

	/**
	 * Aligns the selection cells vertically or horizontally according to the
	 * given alignment.
	 *
	 * @param align Specifies the alignment. Possible values are all constants
	 * in mxConstants with an ALIGN prefix.
	 */
	public void alignCells(String align)
	{
		alignCells(align, null);
	}

	/**
	 * Aligns the given cells vertically or horizontally according to the given
	 * alignment.
	 *
	 * @param align Specifies the alignment. Possible values are all constants
	 * in mxConstants with an ALIGN prefix.
	 * @param cells Array of cells to be aligned.
	 */
	public void alignCells(String align, Object[] cells)
	{
		alignCells(align, cells, null);
	}

	/**
	 * Aligns the given cells vertically or horizontally according to the given
	 * alignment using the optional parameter as the coordinate.
	 *
	 * @param align Specifies the alignment. Possible values are all constants
	 * in mxConstants with an ALIGN prefix.
	 * @param cells Array of cells to be aligned.
	 * @param param Optional coordinate for the alignment.
	 */
	public void alignCells(String align, Object[] cells, Object param)
	{
		if (cells == null)
		{
			cells = getSelectionCells();
		}

		if (cells != null && cells.length > 1)
		{
			// Finds the required coordinate for the alignment
			if (param == null)
			{
				for (int i = 0; i < cells.length; i++)
				{
					mxGeometry g = getCellGeometry(cells[i]);

					if (g != null && !model.isEdge(cells[i]))
					{
						if (param == null)
						{
							if (align == null
									|| align.equals(mxConstants.ALIGN_LEFT))
							{
								param = g.getX();
							}
							else if (align.equals(mxConstants.ALIGN_CENTER))
							{
								param = g.getX() + g.getWidth() / 2;
								break;
							}
							else if (align.equals(mxConstants.ALIGN_RIGHT))
							{
								param = g.getX() + g.getWidth();
							}
							else if (align.equals(mxConstants.ALIGN_TOP))
							{
								param = g.getY();
							}
							else if (align.equals(mxConstants.ALIGN_MIDDLE))
							{
								param = g.getY() + g.getHeight() / 2;
								break;
							}
							else if (align.equals(mxConstants.ALIGN_BOTTOM))
							{
								param = g.getY() + g.getHeight();
							}
						}
						else
						{
							double tmp = Double.parseDouble(String
									.valueOf(param));

							if (align == null
									|| align.equals(mxConstants.ALIGN_LEFT))
							{
								param = Math.min(tmp, g.getX());
							}
							else if (align.equals(mxConstants.ALIGN_RIGHT))
							{
								param = Math.max(tmp, g.getX() + g.getWidth());
							}
							else if (align.equals(mxConstants.ALIGN_TOP))
							{
								param = Math.min(tmp, g.getY());
							}
							else if (align.equals(mxConstants.ALIGN_BOTTOM))
							{
								param = Math.max(tmp, g.getY() + g.getHeight());
							}
						}
					}
				}
			}

			// Aligns the cells to the coordinate
			model.beginUpdate();
			try
			{
				double tmp = Double.parseDouble(String.valueOf(param));

				for (int i = 0; i < cells.length; i++)
				{
					mxGeometry g = getCellGeometry(cells[i]);

					if (g != null && !model.isEdge(cells[i]))
					{
						g = (mxGeometry) g.clone();

						if (align == null
								|| align.equals(mxConstants.ALIGN_LEFT))
						{
							g.setX(tmp);
						}
						else if (align.equals(mxConstants.ALIGN_CENTER))
						{
							g.setX(tmp - g.getWidth() / 2);
						}
						else if (align.equals(mxConstants.ALIGN_RIGHT))
						{
							g.setX(tmp - g.getWidth());
						}
						else if (align.equals(mxConstants.ALIGN_TOP))
						{
							g.setY(tmp);
						}
						else if (align.equals(mxConstants.ALIGN_MIDDLE))
						{
							g.setY(tmp - g.getHeight() / 2);
						}
						else if (align.equals(mxConstants.ALIGN_BOTTOM))
						{
							g.setY(tmp - g.getHeight());
						}

						model.setGeometry(cells[i], g);
					}
				}
			}
			finally
			{
				model.endUpdate();
			}
		}
	}

	/**
	 * Called when the main control point of the edge is double-clicked. This
	 * implementation switches between null (default) and alternateEdgeStyle
	 * and resets the edges control points. Finally, a flip event is fired
	 * before endUpdate is called on the model.
	 *
	 * @param edge Cell that represents the edge to be flipped.
	 * @return Returns the edge that has been flipped.
	 */
	public Object flipEdge(Object edge)
	{
		if (edge != null && alternateEdgeStyle != null)
		{
			model.beginUpdate();
			try
			{
				String style = model.getStyle(edge);
                          
                         // JTS 
				//if (style == null || style.length() == 0)
				if ( style.endsWith("null") )
				// jts
				{
					model.setStyle(edge, alternateEdgeStyle);
				}
				else
				{
					model.setStyle(edge, null);
				}

				// Removes all existing control points
				mxGeometry geo = model.getGeometry(edge);

				if (geo != null)
				{
					geo = (mxGeometry) geo.clone();
					geo.setPoints(null);

					model.setGeometry(edge, geo);
				}

				fireEvent(EVENT_FLIP, new Object[] { edge });
			}
			finally
			{
				model.endUpdate();
			}
		}

		return edge;
	}

	//
	// Layers
	//

	/**
	 * Moves the selection cells to the background.
	 */
	public void cellsToBack()
	{
		cellsToBack(null);
	}

	/**
	 * Moves the given cells to the background. If no cells are given, then the
	 * selection cells are used.
	 *
	 * @param cells Array of cells to move to the background.
	 */
	public void cellsToBack(Object[] cells)
	{
		setIndexForCells(cells, 0);
	}

	/**
	 * Brings the selection cells to the front.
	 */
	public void cellsToFront()
	{
		cellsToFront(null);
	}

	/**
	 * Brings the given cells to the front. If no cells are given, then the
	 * selection cells are used.
	 *
	 * @param cells Array of cells to bring to front.
	 */
	public void cellsToFront(Object[] cells)
	{
		setIndexForCells(cells, null);
	}

	/**
	 * Reinserts the given cells into their parent at the given index. If the
	 * given index is null then the cells are appended.
	 *
	 * @param cells Optional array of cells to be reinserted. Default is the
	 * selection cells.
	 * @param index Integer that specifies the new index or null to append.
	 */
	public void setIndexForCells(Object[] cells, Integer index)
	{
		if (cells == null)
		{
			cells = getSelectionCells();
		}

		if (cells != null)
		{
			model.beginUpdate();
			try
			{
				for (int i = 0; i < cells.length; i++)
				{
					Object parent = model.getParent(cells[i]);

					if (index == null)
					{
						model.add(parent, cells[i],
								model.getChildCount(parent) - 1);
					}
					else
					{
						model.add(parent, cells[i], index);
					}
				}

				fireEvent(EVENT_INDEX_CHANGED, new Object[] { cells, index });
			}
			finally
			{
				model.endUpdate();
			}
		}
	}

	//
	// Cell cloning and insertion
	//

	/**
	 * Clones all cells in the given array.
	 */
	public Object[] cloneCells(Object[] cells)
	{
		return cloneCells(cells, true);
	}

	/**
	 * Returns the clones for the given cells. If the terminal of an edge is
	 * not in the given array, then the respective end is assigned a terminal
	 * point and the terminal is removed. If a cloned edge is invalid and
	 * allowInvalidEdges is false, then a null pointer will be at this position
	 * in the returned array. Use getCloneableCells on the input array to only
	 * clone the cells where isCellCloneable returns true.
	 *
	 * @param cells Array of mxCells to be cloned.
	 * @return Returns the clones of the given cells.
	 */
	public Object[] cloneCells(Object[] cells, boolean allowInvalidEdges)
	{
		Object[] clones = null;

		if (cells != null)
		{
			Collection tmp = new LinkedHashSet(cells.length);
			tmp.addAll(Arrays.asList(cells));

			if (!tmp.isEmpty())
			{
				double scale = view.getScale();
				mxPoint trans = view.getTranslate();
				clones = model.cloneCells(cells, true);

				for (int i = 0; i < cells.length; i++)
				{
					if (!allowInvalidEdges
							&& model.isEdge(clones[i])
							&& getEdgeValidationError(clones[i], model
									.getTerminal(clones[i], true), model
									.getTerminal(clones[i], false)) != null)
					{
						clones[i] = null;
					}
					else
					{
						mxGeometry g = model.getGeometry(clones[i]);

						if (g != null)
						{
							mxCellState state = view.getState(cells[i]);
							mxCellState pstate = view.getState(model
									.getParent(cells[i]));

							if (state != null && pstate != null)
							{
								double dx = pstate.getOrigin().getX();
								double dy = pstate.getOrigin().getY();

								if (model.isEdge(clones[i]))
								{
									// Checks if the source is cloned or sets the terminal point
									Object src = model.getTerminal(cells[i],
											true);

									while (src != null && !tmp.contains(src))
									{
										src = model.getParent(src);
									}

									if (src == null)
									{
										mxPoint pt = state.getAbsolutePoint(0);
										g.setTerminalPoint(new mxPoint(pt
												.getX()
												/ scale - trans.getX(), pt
												.getY()
												/ scale - trans.getY()), true);
									}

									// Checks if the target is cloned or sets the terminal point
									Object trg = model.getTerminal(cells[i],
											false);

									while (trg != null && !tmp.contains(trg))
									{
										trg = model.getParent(trg);
									}

									if (trg == null)
									{
										mxPoint pt = state
												.getAbsolutePoint(state
														.getAbsolutePointCount() - 1);
										g.setTerminalPoint(new mxPoint(pt
												.getX()
												/ scale - trans.getX(), pt
												.getY()
												/ scale - trans.getY()), false);
									}

									// Translates the control points
									List points = g.getPoints();

									if (points != null)
									{
										Iterator it = points.iterator();

										while (it.hasNext())
										{
											mxPoint pt = (mxPoint) it.next();

											pt.setX(pt.getX() + dx);
											pt.setY(pt.getY() + dy);
										}
									}
								}
								else
								{
									g.setX(g.getX() + dx);
									g.setY(g.getY() + dy);
								}
							}
						}
					}
				}
			}
		}

		return clones;
	}

	/**
	 * Creates and adds a new vertex with an empty style.
	 */
	public Object insertVertex(Object parent, String id, Object value,
			double x, double y, double width, double height)
	{
		return insertVertex(parent, id, value, x, y, width, height, null);
	}

	/**
	 * Adds a new vertex into the given parent using value as the user object
	 * and the given coordinates as the geometry of the new vertex. The id and
	 * style are used for the respective properties of the new cell, which is
	 * returned.
	 *
	 * @param parent Cell that specifies the parent of the new vertex.
	 * @param id Optional string that defines the Id of the new vertex.
	 * @param value Object to be used as the user object.
	 * @param x Integer that defines the x coordinate of the vertex.
	 * @param y Integer that defines the y coordinate of the vertex.
	 * @param width Integer that defines the width of the vertex.
	 * @param height Integer that defines the height of the vertex.
	 * @param style Optional string that defines the cell style.
	 * @return Returns the new vertex that has been inserted.
	 */
	public Object insertVertex(Object parent, String id, Object value,
			double x, double y, double width, double height, String style)
	{
		Object vertex = createVertex(parent, id, value, x, y, width, height,
				style);

		return addCell(vertex, parent);
	}

	/**
	 * Hook method that creates the new vertex for insertVertex.
	 *
	 * @param parent Cell that specifies the parent of the new vertex.
	 * @param id Optional string that defines the Id of the new vertex.
	 * @param value Object to be used as the user object.
	 * @param x Integer that defines the x coordinate of the vertex.
	 * @param y Integer that defines the y coordinate of the vertex.
	 * @param width Integer that defines the width of the vertex.
	 * @param height Integer that defines the height of the vertex.
	 * @param style Optional string that defines the cell style.
	 * @return Returns the new vertex to be inserted.
	 */
	public Object createVertex(Object parent, String id, Object value,
			double x, double y, double width, double height, String style)
	{
		mxGeometry geometry = new mxGeometry(x, y, width, height);
		mxCell vertex = new mxCell(value, geometry, style);

		vertex.setId(id);
		vertex.setVertex(true);
		vertex.setConnectable(true);

		return vertex;
	}

	/**
	 * Creates and adds a new edge with an empty style.
	 */
	public Object insertEdge(Object parent, String id, Object value,
			Object source, Object target)
	{
		return insertEdge(parent, id, value, source, target, null);
	}

	/**
	 * Adds a new edge into the given parent using value as the user object and
	 * the given source and target as the terminals of the new edge. The Id and
	 * style are used for the respective properties of the new cell, which is
	 * returned.
	 *
	 * @param parent Cell that specifies the parent of the new edge.
	 * @param id Optional string that defines the Id of the new edge.
	 * @param value Object to be used as the user object.
	 * @param source Cell that defines the source of the edge.
	 * @param target Cell that defines the target of the edge.
	 * @param style Optional string that defines the cell style.
	 * @return Returns the new edge that has been inserted.
	 */
	public Object insertEdge(Object parent, String id, Object value,
			Object source, Object target, String style)
	{
		Object edge = createEdge(parent, id, value, source, target, style);

		return addEdge(edge, parent, source, target, null);
	}

	/**
	 * Hook method that creates the new edge for insertEdge.
	 *
	 * @param parent Cell that specifies the parent of the new edge.
	 * @param id Optional string that defines the Id of the new edge.
	 * @param value Object to be used as the user object.
	 * @param source Cell that defines the source of the edge.
	 * @param target Cell that defines the target of the edge.
	 * @param style Optional string that defines the cell style.
	 * @return Returns the new edge to be inserted.
	 */
	public Object createEdge(Object parent, String id, Object value,
			Object source, Object target, String style)
	{
		mxCell edge = new mxCell(value, new mxGeometry(), style);

		edge.setId(id);
		edge.setEdge(true);
		edge.getGeometry().setRelative(true);

		return edge;
	}

	/**
	 * Adds the given edge to the model.
	 */
	public Object addEdge(Object edge, Object source, Object target)
	{
		return addEdge(edge, null, source, target, null);
	}

	/**
	 * Adds the given edge to the children of the given parent at the given
	 * index. If source and target are specified, then they will be used as the
	 * new source and target terminals and connect events will be fired for
	 * each end. Finally, a add event is fired for the inserted edge and
	 * the inserted edge is returned.
	 *
	 * @param edge Cell to be inserted into the given parent.
	 * @param parent Cell that represents the new parent. Default is
	 * defaultParent.
	 * @param source Optional Cell that represents the source terminal.
	 * @param target Optional Cell that represents the target terminal.
	 * @param index Optional index to insert the cells at. Default is to append.
	 * @return Returns the cell that has been inserted.
	 */
	public Object addEdge(Object edge, Object parent, Object source,
			Object target, Integer index)
	{
		return addCell(edge, parent, index, source, target);
	}

	/**
	 * Adds the given cell to the children of the default parent.
	 *
	 * @param cell Cell to be inserted into the default parent.
	 * @return Returns the cell that has been added.
	 */
	public Object addCell(Object cell)
	{
		return addCell(cell, null);
	}

	/**
	 * Adds the given cell to the children of the given parent.
	 *
	 * @param cell Cell to be inserted into the given parent.
	 * @return Returns the cell that has been added.
	 */
	public Object addCell(Object cell, Object parent)
	{
		return addCell(cell, parent, null, null, null);
	}

	/**
	 * Adds the given cell to the children of the given parent at the given
	 * index. If source and target are specified, then they will be used as the
	 * new source and target terminals and connect events will be fired for
	 * each end. Finally, a add event is fired for the inserted cell. After
	 * the display has been updated an afterAdd event is fired for the new
	 * cell.
	 *
	 * @param cell Cell to be inserted into the given parent.
	 * @param parent Object that represents the new parent. If no parent is
	 * given then the default parent is used.
	 * @param index Optional index to insert the cells at. Default is to append.
	 * @param source Optional cell that represents the source terminal.
	 * @param target Optional cell that represents the target terminal.
	 * @return Returns the cell that has been added.
	 */
	public Object addCell(Object cell, Object parent, Integer index,
			Object source, Object target)
	{
		if (parent == null)
		{
			parent = getDefaultParent();
		}

		if (index == null)
		{
			index = model.getChildCount(parent);
		}

		fireEvent(EVENT_BEFORE_ADD, new Object[] { new Object[] { cell },
				parent, index, source, target });

		model.beginUpdate();
		try
		{
			cell = model.add(parent, cell, index);

			if (cell != null)
			{
				// Sets the source terminal
				if (source != null)
				{
					model.setTerminal(cell, source, true);
					fireEvent(EVENT_CONNECT,
							new Object[] { cell, source, true });
				}

				// Sets the target terminal
				if (target != null)
				{
					model.setTerminal(cell, target, false);
					fireEvent(EVENT_CONNECT,
							new Object[] { cell, target, false });
				}

				fireEvent(EVENT_ADD, new Object[] { new Object[] { cell } });
			}
		}
		finally
		{
			model.endUpdate();
		}

		if (cell != null)
		{
			fireEvent(EVENT_AFTER_ADD, new Object[] { new Object[] { cell } });
		}

		return cell;
	}

	/**
	 * Adds the given cells to the children of the default parent.
	 *
	 * @param cells Array of cells to be inserted.
	 * @return Returns the cells that have been added.
	 */
	public Object[] addCells(Object[] cells)
	{
		return addCells(cells, null);
	}

	/**
	 * Adds the given cells to the children of the given parent.
	 *
	 * @param cells Array of cells to be inserted.
	 * @param parent Optional cell that represents the new parent. If no parent
	 * is specified then the default parent is used.
	 * @return Returns the cells that have been added.
	 */
	public Object[] addCells(Object[] cells, Object parent)
	{
		return addCells(cells, parent, null);
	}

	/**
	 * Adds the given cells to the children of the given parent starting at the
	 * given index and fires an add event. The automatic layout is called
	 * using layoutAfterAdd. After the display has been updated an
	 * afterAddd event is fired for the new cells.
	 *
	 * @param cells Array of cells to be inserted.
	 * @param parent Optional cell that represents the new parent. If no parent
	 * is specified then the default parent is used.
	 * @param index Optional index to insert the cells at. Default is to append.
	 * @return Returns the cells that have been added.
	 */
	public Object[] addCells(Object[] cells, Object parent, Integer index)
	{
		if (parent == null)
		{
			parent = getDefaultParent();
		}

		if (index == null)
		{
			index = model.getChildCount(parent);
		}

		fireEvent(EVENT_BEFORE_ADD, new Object[] { cells, parent, index });

		model.beginUpdate();
		try
		{
			for (int i = 0; i < cells.length; i++)
			{
				model.add(parent, cells[i], index + i);
			}

			fireEvent(EVENT_ADD, new Object[] { cells });
		}
		finally
		{
			model.endUpdate();
		}

		fireEvent(EVENT_AFTER_ADD, new Object[] { cells });

		return cells;
	}

	/**
	 *
	 */
	public Object splitEdge(Object edge, Object cell)
	{
		return splitEdge(edge, cell, null);
	}

	/**
	 * Splits the given edge by adding a newEdge between the previous source
	 * and the given cell and reconnecting the source of the given edge to the
	 * given cell. Fires the respective connect and add events. After the
	 * display has been updated an afterAdd event is fired for the new edge.
	 *
	 * @param edge Object that represents the edge to be splitted.
	 * @param cell Object that represents the cell to insert into the edge.
	 * @param newEdge Object that represents the edge to be inserted.
	 * @return Returns the new edge that has been inserted.
	 */
	public Object splitEdge(Object edge, Object cell, Object newEdge)
	{
		if (newEdge == null)
		{
			newEdge = cloneCells(new Object[] { edge })[0];
		}

		Object parent = model.getParent(edge);
		Object source = model.getTerminal(edge, true);
		int index = model.getChildCount(parent);

		fireEvent(EVENT_BEFORE_ADD, new Object[] { new Object[] { newEdge },
				parent, index, source, cell });

		model.beginUpdate();
		try
		{
			model.add(parent, newEdge, index);
			model.setTerminal(newEdge, source, true);
			model.setTerminal(newEdge, cell, false);

			fireEvent(EVENT_CONNECT, new Object[] { new Object[] { newEdge,
					source, true } });
			fireEvent(EVENT_CONNECT, new Object[] { new Object[] { newEdge,
					cell, false } });

			model.setTerminal(edge, cell, true);

			fireEvent(EVENT_CONNECT, new Object[] { new Object[] { edge, cell,
					true } });
			fireEvent(EVENT_ADD, new Object[] { new Object[] { newEdge } });
		}
		finally
		{
			model.endUpdate();
		}

		fireEvent(EVENT_AFTER_ADD, new Object[] { new Object[] { newEdge } });

		return newEdge;
	}

	/**
	 * Removes the selection cells from the graph.
	 *
	 * @return Returns the cells that have been removed.
	 */
	public Object[] removeCells()
	{
		return removeCells(null);
	}

	/**
	 * Removes the given cells from the graph.
	 *
	 * @return Returns the cells that have been removed.
	 */
	public Object[] removeCells(Object[] cells)
	{
		return removeCells(cells, true, false, false);
	}

	/**
	 * Removes the given cells from the graph. If includeEdges is true then all
	 * connected edges are removed as well.
	 *
	 * @return Returns all cells that have been removed.
	 */
	public Object[] removeCells(Object[] cells, boolean includeEdges)
	{
		return removeCells(cells, includeEdges, false, false);
	}

	/**
	 * Hides the seletion cells.
	 *
	 * @return Returns the cells that have been hidden.
	 */
	public Object[] hideCells()
	{
		return hideCells(null, true);
	}

	/**
	 * Hides the given cells. If includeEdges is true then all connected edges
	 * are hidden as well.
	 *
	 * @return Returns all cells that have been hidden.
	 */
	public Object[] hideCells(Object[] cells, boolean includeEdges)
	{
		return removeCells(cells, includeEdges, true, false);
	}

	/**
	 * Shows the seletion cells.
	 *
	 * @return Returns the cells that have been shown.
	 */
	public Object[] showCells()
	{
		return showCells(null, true);
	}

	/**
	 * Shows the given cells. If includeEdges is true then all connected edges
	 * are shown as well.
	 *
	 * @return Returns all cells that have been shown.
	 */
	public Object[] showCells(Object[] cells, boolean includeEdges)
	{
		return removeCells(cells, includeEdges, false, true);
	}

	/**
	 * Removes, shows or hides the given cells including the connected edges if
	 * includeEdges is true. If isShow is true, then the cells are shown, else
	 * if isHide is true, then the cells are hidden. Otherwise, the cells are
	 * removed. Fires a <show>, <hide> or <remove> event depending on the
	 * action that has been carried out.  After the display has been updated,
	 * a <shown>, <hidden> or <removed> event is fired for the affected cells.
	 */
	public Object[] removeCells(Object[] cells, boolean includeEdges,
			boolean isHide, boolean isShow)
	{
		if (cells == null)
		{
			cells = getSelectionCells();

			if (!isHide && !isShow)
			{
				cells = getDeletableCells(cells);
			}
		}

		List tmp = null;

		if (cells != null && cells.length > 0)
		{
			String before = EVENT_BEFORE_REMOVE;
			String eventName = EVENT_REMOVE;
			String after = EVENT_AFTER_REMOVE;

			if (isHide)
			{
				before = EVENT_BEFORE_HIDE;
				eventName = EVENT_HIDE;
				after = EVENT_AFTER_HIDE;
			}
			else if (isShow)
			{
				before = EVENT_BEFORE_SHOW;
				eventName = EVENT_SHOW;
				after = EVENT_AFTER_SHOW;
			}

			double scale = view.getScale();
			mxPoint tr = view.getTranslate();

			tmp = new ArrayList(cells.length);
			fireEvent(before, new Object[] { cells });

			model.beginUpdate();
			try
			{
				Object[] parents = mxGraphModel.getParents(getModel(), cells);

				for (int i = 0; i < cells.length; i++)
				{
					if (isHide || isShow)
					{
						if (includeEdges)
						{
							removeEdges(cells[i], true, isHide, isShow);
						}

						model.setVisible(cells[i], isShow);
						tmp.add(cells[i]);
					}
					else
					{
						if (includeEdges)
						{
							tmp.addAll(Arrays
									.asList(removeEdges(cells[i], true)));
						}
						else
						{
							// Disconnects edges which are not in cells
							Collection cellSet = new HashSet();
							cellSet.addAll(Arrays.asList(cells));
							Object[] edges = getConnections(cells[i]);

							for (int j = 0; j < edges.length; j++)
							{
								if (!cellSet.contains(edges[j]))
								{
									mxGeometry g = model.getGeometry(edges[j]);

									if (g != null)
									{
										mxCellState state = view
												.getState(edges[j]);

										if (state != null)
										{
											g = (mxGeometry) g.clone();
											boolean source = view
													.getVisibleTerminal(
															edges[j], true) == cells[i];
											int n = (source) ? 0
													: state
															.getAbsolutePointCount() - 1;
											mxPoint pt = state
													.getAbsolutePoint(n);

											g.setTerminalPoint(new mxPoint(pt
													.getX()
													/ scale - tr.getX(), pt
													.getY()
													/ scale - tr.getY()),
													source);
											model.setTerminal(edges[j], null,
													source);
											model.setGeometry(edges[j], g);
										}
									}
								}
							}
						}

						tmp.add(model.remove(cells[i]));
					}
				}

				// Dispatches the event while the transaction is in progress
				Object[] tmpArray = tmp.toArray();
				fireEvent(eventName, new Object[] { tmpArray, parents });
			}
			finally
			{
				model.endUpdate();
			}

			// Dispatches the event after the display has been updated
			fireEvent(after, tmp.toArray());
		}

		return (tmp != null) ? tmp.toArray() : null;
	}

	/**
	 *
	 */
	public Object[] removeEdges(Object cell, boolean recurse)
	{
		return removeEdges(cell, recurse, false, false);
	}

	/**
	 *
	 */
	public Object[] hideEdges(Object cell, boolean recurse)
	{
		return removeEdges(cell, recurse, true, false);
	}

	/**
	 *
	 */
	public Object[] showEdges(Object cell, boolean recurse)
	{
		return removeEdges(cell, recurse, false, true);
	}

	/**
	 * Removes, shows or hides the edges connected to the given cell
	 * recursively. If isShow is true, then the edges are shown, else if isHide
	 * is true, then the edges are hidden. Otherwise, the edges are removed.
	 * Fires a <show>, <hide> or <remove> event depending on the action that
	 * has been carried out. After the display has been updated, a <shown>,
	 * <hidden> or <removed> event is fired for the affected cells.
	 *
	 * @param cell
	 * @param recurse
	 */
	public Object[] removeEdges(Object cell, boolean recurse, boolean isHide,
			boolean isShow)
	{
		List tmp = null;

		if (cell != null)
		{
			// Assigns event names
			String before = EVENT_BEFORE_REMOVE;
			String eventName = EVENT_REMOVE;
			String after = EVENT_AFTER_REMOVE;

			if (isShow)
			{
				before = EVENT_BEFORE_SHOW;
				eventName = EVENT_SHOW;
				after = EVENT_AFTER_SHOW;
			}
			else if (isHide)
			{
				before = EVENT_BEFORE_HIDE;
				eventName = EVENT_HIDE;
				after = EVENT_AFTER_HIDE;
			}

			int edgeCount = model.getEdgeCount(cell);
			tmp = new ArrayList(edgeCount);

			model.beginUpdate();
			try
			{
				if (edgeCount > 0)
				{
					fireEvent(before, new Object[] { tmp });

					// Shows or hides the edges
					if (isShow || isHide)
					{
						for (int i = 0; i < edgeCount; i++)
						{
							Object edge = model.getEdgeAt(cell, i);
							model.setVisible(edge, isShow);
							tmp.add(edge);
						}
					}

					// Removes the edges
					else
					{
						for (int i = 0; i < edgeCount; i++)
						{
							Object edge = model.getEdgeAt(cell, 0);

							if (isCellDeletable(edge))
							{
								tmp.add(model.remove(edge));
							}
						}
					}

					// Dispatches the event while the transaction is in progress
					fireEvent(eventName, new Object[] { tmp });
				}

				// Recurse
				if (recurse)
				{
					Object[] children = mxGraphModel.getChildren(model, cell);

					for (int i = 0; i < children.length; i++)
					{
						removeEdges(children[i], true, isHide, isShow);
					}
				}
			}
			finally
			{
				model.endUpdate();
			}

			// Dispatches the event after the display has been updated
			if (!tmp.isEmpty())
			{
				fireEvent(after, new Object[] { tmp });
			}
		}

		return tmp.toArray();
	}

	//
	// Cell sizing
	//

	/**
	 * Updates the size of the given cell in the model using
	 * getPreferredSizeForCell to get the new size. This function
	 * fires beforeUpdateSize and afterUpdateSize events.
	 *
	 * @param cell <mxCell> for which the size should be changed.
	 */
	public Object updateCellSize(Object cell)
	{
		if (cell != null)
		{
			mxRectangle size = getPreferredSizeForCell(cell);
			mxGeometry geo = model.getGeometry(cell);

			if (size != null && geo != null)
			{
				fireEvent(EVENT_BEFORE_UPDATESIZE, new Object[] { cell, size });

				model.beginUpdate();
				try
				{
					mxGeometry geometry = (mxGeometry) geo.clone();

					if (isSwimlane(cell))
					{
						geometry = updateSwimlaneSize(cell, geometry, size);
					}
					else
					{
						geometry.setWidth(size.getWidth());
						geometry.setHeight(size.getHeight());
					}

					if (geo.getWidth() != geometry.getWidth()
							|| geo.getHeight() != geometry.getHeight())
					{
						resizeCell(cell, geometry);
					}

					fireEvent(EVENT_UPDATESIZE, new Object[] { cell, geometry });
				}
				finally
				{
					model.endUpdate();
				}

				fireEvent(EVENT_AFTER_UPDATESIZE, new Object[] { cell });
			}
		}

		return cell;
	}

	/**
	 * Updates the size of the given swimlane and returns the updated geometry
	 * to be used in the model.
	 *
	 * @param swimlane <mxCell> that represents the swimlane to be udpated.
	 * @param geometry
	 * @param pSize
	 */
	public mxGeometry updateSwimlaneSize(Object swimlane, mxGeometry geometry,
			mxRectangle pSize)
	{
		if (swimlane != null && geometry != null && pSize != null)
		{
			model.beginUpdate();
			try
			{
				boolean collapsed = isCellCollapsed(swimlane);
				Hashtable style = getCellStyle(swimlane);
				String cellStyle = model.getStyle(swimlane);

				if (cellStyle == null)
				{
					cellStyle = "";
				}

				if (mxUtils.isTrue(style, mxConstants.STYLE_HORIZONTAL))
				{
					cellStyle = mxUtils.setStyle(cellStyle,
							mxConstants.STYLE_STARTSIZE, String.valueOf(pSize
									.getWidth()));

					if (collapsed)
					{
						geometry.setWidth(pSize.getWidth());
					}

					geometry.setHeight(pSize.getHeight());
				}
				else
				{
					cellStyle = mxUtils.setStyle(cellStyle,
							mxConstants.STYLE_STARTSIZE, String.valueOf(pSize
									.getHeight()));

					if (collapsed)
					{
						geometry.setHeight(pSize.getHeight());
					}

					geometry.setWidth(pSize.getWidth());
				}

				model.setStyle(swimlane, cellStyle);

				if (!collapsed)
				{
					mxRectangle cBounds = view.getBounds(mxGraphModel
							.getChildren(model, swimlane));

					if (cBounds != null)
					{
						mxPoint t = view.getTranslate();
						double s = view.getScale();

						double width = (cBounds.getX() + cBounds.getWidth())
								/ s - geometry.getX() - t.getX();
						double height = (cBounds.getY() + cBounds.getHeight())
								/ s - geometry.getY() - t.getY();

						geometry.setWidth(Math.max(geometry.getWidth(), width));
						geometry.setHeight(Math.max(geometry.getHeight(),
								height));
					}
				}
			}
			finally
			{
				model.endUpdate();
			}
		}

		return geometry;
	}

	/**
	 * Returns the preferred width and height of the given <mxCell> as an
	 * <mxRectangle>.
	 *
	 * @param cell <mxCell> for which the preferred size should be returned.
	 */
	public mxRectangle getPreferredSizeForCell(Object cell)
	{
		mxRectangle result = null;

		if (cell != null)
		{
			mxCellState state = view.getState(cell);
			Hashtable style = (state != null) ? state.style
					: getCellStyle(cell);

			if (style != null && !model.isEdge(cell))
			{
				double dx = 0;
				double dy = 0;

				// Adds dimension of image if shape is a label
				if (getImage(state) != null
						|| mxUtils.getString(style, mxConstants.STYLE_IMAGE) != null)
				{
					if (mxUtils.getString(style, mxConstants.STYLE_SHAPE, "")
							.equals(mxConstants.SHAPE_LABEL))
					{
						if (mxUtils.getString(style,
								mxConstants.STYLE_VERTICAL_ALIGN, "").equals(
								mxConstants.ALIGN_MIDDLE))
						{
							dx += mxUtils.getDouble(style,
									mxConstants.STYLE_IMAGE_WIDTH,
									mxConstants.DEFAULT_IMAGESIZE);
						}

						if (mxUtils.getString(style, mxConstants.STYLE_ALIGN,
								"").equals(mxConstants.ALIGN_CENTER))
						{
							dy += mxUtils.getDouble(style,
									mxConstants.STYLE_IMAGE_HEIGHT,
									mxConstants.DEFAULT_IMAGESIZE);
						}
					}
				}

				// Adds spacings
				double spacing = mxUtils.getDouble(style,
						mxConstants.STYLE_SPACING);
				dx += 2 * spacing;
				dx += mxUtils.getDouble(style, mxConstants.STYLE_SPACING_LEFT);
				dx += mxUtils.getDouble(style, mxConstants.STYLE_SPACING_RIGHT);

				dy += 2 * spacing;
				dy += mxUtils.getDouble(style, mxConstants.STYLE_SPACING_TOP);
				dy += mxUtils
						.getDouble(style, mxConstants.STYLE_SPACING_BOTTOM);

				// LATER: Add space for collapse/expand icon if applicable

				// Adds space for label
				// JTS
				//String value = getLabel(cell);
				String value = getAbbreviation( getLabel(cell), ((mxCell)cell).getStyle() );
				// jts

				if (value != null && value.length() > 0)
				{
				          mxRectangle size = mxUtils.getLabelSize(value, style,
							isHtmlLabel(cell));
					double width = size.getWidth() + dx;
					double height = size.getHeight() + dy;

					if (!mxUtils.isTrue(style, mxConstants.STYLE_HORIZONTAL,
							true))
					{
						double tmp = height;

						height = width;
						width = tmp;
					}

					if (gridEnabled)
					{
						width = snap(width + gridSize / 2);
						height = snap(height + gridSize / 2);
					}

					result = new mxRectangle(0, 0, width, height);
				}
				else
				{
					double gs2 = 4 * gridSize;
					result = new mxRectangle(0, 0, gs2, gs2);
				}
			}
		}

		return result;
	}

	/**
	 * Sets the bounds of the given cell using <resizeCells>.
	 *
	 * @param cell <mxCell> whose bounds should be changed.
	 * @param bounds <mxRectangle> that represents the new bounds.
	 */
	public void resizeCell(Object cell, mxRectangle bounds)
	{
		resizeCells(new Object[] { cell }, new mxRectangle[] { bounds });
	}

	/**
	 * Sets the bounds of the given cells, cascades the resize using
	 * <cascadeResize> and fires a <resize> event. If <extendParentOnResize>
	 * is true, then the parent is extended if a child size is changed so that
	 * it overlaps with the parent.
	 *
	 * @param cells Array of <mxCells> whose bounds should be changed.
	 * @param boundsArray Array of <mxRectangles> that represents the new bounds.
	 */
	public void resizeCells(Object[] cells, mxRectangle[] boundsArray)
	{
		if (cells != null && boundsArray != null)
		{
			fireEvent(EVENT_BEFORE_RESIZE, new Object[] { cells, boundsArray });

			List tmp = new ArrayList(cells.length);
			List tmpBounds = new ArrayList(cells.length);
			List oldBounds = new ArrayList(cells.length);

			model.beginUpdate();
			try
			{

				for (int i = 0; i < cells.length; i++)
				{
					Object cell = cells[i];
					mxRectangle bounds = boundsArray[i];
					mxGeometry g = model.getGeometry(cell);

					if (g.getX() != bounds.getX() || g.getY() != bounds.getY()
							|| g.getWidth() != bounds.getWidth()
							|| g.getHeight() != bounds.getHeight())
					{
						tmp.add(cell);
						tmpBounds.add(bounds);
						oldBounds.add(g);

						g = (mxGeometry) g.clone();

						if (g.isRelative())
						{
							mxPoint offset = g.getOffset();

							if (offset != null)
							{
								offset.setX(offset.getX() + bounds.getX()
										- g.getX());
								offset.setY(offset.getY() + bounds.getY()
										- g.getY());
							}
						}
						else
						{
							g.setX(bounds.getX());
							g.setY(bounds.getY());
						}

						g.setWidth(bounds.getWidth());
						g.setHeight(bounds.getHeight());

						model.setGeometry(cell, g);

						if (isExtendParentOnResize(cell))
						{
							extendParent(cell);
						}
					}
				}

				if (!tmp.isEmpty())
				{
					Object[] tmpArray = tmp.toArray();
					fireEvent(EVENT_RESIZE, new Object[] { tmpArray, tmpBounds,
							oldBounds });

					if (resetEdgesOnResize)
					{
						resetEdges(tmpArray);
					}
				}
			}
			finally
			{
				model.endUpdate();
			}

			fireEvent(EVENT_AFTER_RESIZE, new Object[] { tmp });
		}
	}

	/**
	 * Resizes the parents recursively so that they contain the complete area
	 * of the resized child cell.
	 *
	 * @param cell <mxCell> that has been resized.
	 */
	public void extendParent(Object cell)
	{
		if (cell != null)
		{
			Object parent = model.getParent(cell);
			mxGeometry p = model.getGeometry(parent);

			if (parent != null && p != null && !isCellCollapsed(parent))
			{
				mxGeometry g = model.getGeometry(cell);

				if (g != null
						&& (p.getWidth() < g.getX() + g.getWidth() || p
								.getHeight() < g.getY() + g.getHeight()))
				{
					p = (mxGeometry) p.clone();

					p.setWidth(Math.max(p.getWidth(), g.getX() + g.getWidth()));
					p.setHeight(Math.max(p.getHeight(), g.getY()
							+ g.getHeight()));

					resizeCell(parent, p);
				}
			}
		}
	}

	//
	// Cell moving
	//

	/**
	 *
	 */
	public Object[] moveCells(Object[] cells, double dx, double dy)
	{
		return moveCells(cells, dx, dy, false);
	}

	/**
	 *
	 */
	public Object[] moveCells(Object[] cells, double dx, double dy,
			boolean clone)
	{
		return moveCells(cells, dx, dy, clone, null, null);
	}

	/**
	 * Moves the given cells by the given vector (dx, dy) and clones the cells
	 * if clone is true. If a target is given, then the cells are appended as
	 * children in the given target. If cells are cloned then a clone event
	 * is fired while the transaction is in progress and a afterMove event is
	 * fired after the display has been updated.
	 *
	 * @param cells Array of cells to be moved.
	 * @param dx Integer that specifies the x-coordinate of the move.
	 * @param dy Integer that specifies the y-coordinate of the move.
	 * @param clone Boolean indicating if the cells should be cloned.
	 * @param target Cell that represents the new parent of the cells.
	 * @param location Location where the mouse was released.
	 */
	public Object[] moveCells(Object[] cells, double dx, double dy,
			boolean clone, Object target, Point location)
	{
		Object[] clones = cells;
		

		if (clones != null && (dx != 0 || dy != 0 || clone || target != null))
		{
			fireEvent(EVENT_BEFORE_MOVE, new Object[] { cells, dx, dy, clone,
					target, location });

			model.beginUpdate();
			try
			{
				if (clone)
				{
					clones = cloneCells(getCloneableCells(cells),
							isCloneInvalidEdges());
					addCells(clones);

					// Dispatches a clone event if cells were cloned
					fireEvent(EVENT_CLONE, new Object[] { clones, cells });
				}
				else if (disconnectOnMove && allowDanglingEdges)
				{
					disconnectGraph(cells);
				}

				for (int i = 0; i < clones.length; i++)
				{
					mxGeometry g = model.getGeometry(clones[i]);

					if (g != null && isCellMovable(clones[i]))
					{
						g = g.translate(dx, dy);

						if (g.isRelative() && !model.isEdge(clones[i]))
						{
							if (g.getOffset() == null)
							{
								g.setOffset(new mxPoint(dx, dy));
							}
							else
							{
								mxPoint off = g.getOffset();

								off.setX(off.getX() + dx);
								off.setY(off.getY() + dx);
							}
						}

						model.setGeometry(clones[i], g);
					}
				}

				// Moves into group and dispatches move event
				moveCellsIntoParent(clones, target, location);
			}
			finally
			{
				model.endUpdate();
			}

			// Dispatches a move event
			fireEvent(EVENT_AFTER_MOVE, new Object[] { cells, clones, dx, dy,
					clone, target, location });
		}

		return clones;
	}

	/**
	 * Returns the mxRectangle inside which a cell is to be kept.
	 */
	public mxRectangle getCellContentArea(Object cell)
	{
		if (cell != null && !model.isEdge(cell))
		{
			Object parent = model.getParent(cell);

			if (parent == getDefaultParent() || parent == getCurrentRoot())
			{
				return getMaximumGraphBounds();
			}
			else if (parent != null && parent != getDefaultParent())
			{
				mxGeometry g = model.getGeometry(parent);

				if (g != null)
				{
					double x = 0;
					double y = 0;
					double w = g.getWidth();
					double h = g.getHeight();

					if (isSwimlane(parent))
					{
						mxRectangle size = getStartSize(parent);

						x = size.getWidth();
						w -= size.getWidth();
						y = size.getHeight();
						h -= size.getHeight();
					}

					return new mxRectangle(x, y, w, h);
				}
			}
		}

		return null;
	}

	/**
	 * @return the maximumGraphBounds
	 */
	public mxRectangle getMaximumGraphBounds()
	{
		return maximumGraphBounds;
	}

	/**
	 * @param maximumGraphBounds the maximumGraphBounds to set
	 */
	public void setMaximumGraphBounds(mxRectangle maximumGraphBounds)
	{
		mxRectangle oldValue = this.maximumGraphBounds;
		this.maximumGraphBounds = maximumGraphBounds;

		changeSupport.firePropertyChange("maximumGraphBounds", oldValue,
				maximumGraphBounds);
	}

	/**
	 * Removes the selection cells from their parents and adds them to the
	 * default parent returned by getDefaultParent.
	 */
	public void removeCellsFromParent()
	{
		removeCellsFromParent(getSelectionCells());
	}

	/**
	 * Removes the specified cells from their parents and adds them to the
	 * default parent returned by getDefaultParent.
	 *
	 * @param cells Array of <mxCells> to be removed from their parents.
	 */
	public void removeCellsFromParent(Object[] cells)
	{
		if (cells == null)
		{
			cells = getSelectionCells();
		}

		if (cells != null)
		{
			Object parent = getDefaultParent();

			// Removes the cells whose parent is different
			// from the default parent
			List tmp = new ArrayList(cells.length);

			for (int i = 0; i < cells.length; i++)
			{
				if (model.getParent(cells[i]) != parent)
				{
					tmp.add(cells[i]);
				}
			}

			if (!tmp.isEmpty())
			{
				moveCellsIntoParent(tmp.toArray(), parent);
			}
		}
	}

	/**
	 *
	 */
	public void moveCellsIntoParent(Object[] cells, Object target)
	{
		moveCellsIntoParent(cells, target, null);
	}

	/**
	 * Appends the given cells to the children of the given target and fires a
	 * move event.
	 *
	 * @param cells Array of mxCells to be moved inside the target.
	 * @param target mxCell that represents the new parent.
	 * @param location Point where the mouse was released.
	 */
	public void moveCellsIntoParent(Object[] cells, Object target,
			Point location)
	{
		if (cells != null && cells.length > 0)
		{
			model.beginUpdate();
			try
			{
				if (target != null)
				{
					Object cell = cells[0];

					// Splits if a connectable cell is dropped onto an edge
					if (model.isEdge(target) && isCellConnectable(cell))
					{
						if (getEdgeValidationError(target, model.getTerminal(
								target, true), cell) == null)
						{
							splitEdge(target, cell);
						}
					}
					else
					{
						// Move cells to reflect the change of the parent. The
						// adding is done below to avoid interference with the
						// maintaining of the edge parents in the graph model.
						for (int i = 0; i < cells.length; i++)
						{
							if (cells[i] != target)
							{
								int index = model.getChildCount(target);
								Object parent = model.getParent(cells[i]);

								if (target != parent)
								{
									mxCellState state = view.getState(target);
									mxCellState pstate = view.getState(parent);
									mxGeometry g = model.getGeometry(cells[i]);

									// Keeps the cell at its old location
									if (g != null && state != null
											&& pstate != null)
									{
										g = g.translate(pstate.getOrigin()
												.getX()
												- state.getOrigin().getX(),
												pstate.getOrigin().getY()
														- state.getOrigin()
																.getY());
										model.setGeometry(cells[i], g);
									}
								}
								else
								{
									index--;
								}

								model.add(target, cells[i], index);
							}
						}
					}
				}

				// Keeps the cells inside their parents
				keepCellsInsideParent(cells);

				// Resets connected edges of moved cells
				if (resetEdgesOnMove)
				{
					resetEdges(cells);
				}

				fireEvent(EVENT_MOVE, new Object[] { cells, target, location });
			}
			finally
			{
				model.endUpdate();
			}
		}
	}

	/**
	 * Keeps the given cells inside the bounds returned by <getContentArea> for
	 * their respective parents, according to the rules defined by
	 * <getOverlap> and if <isKeepInsideParentOnMove> returns true.
	 *
	 * @param cells Array of mxCells which should be kept inside their parents.
	 */
	public void keepCellsInsideParent(Object[] cells)
	{
		if (cells != null)
		{
			model.beginUpdate();
			try
			{
				for (int i = 0; i < cells.length; i++)
				{
					Object cell = cells[i];
					mxRectangle c = (isKeepInsideParentOnMove(cell) ? getCellContentArea(cell)
							: getMaximumGraphBounds());

					if (c != null)
					{
						mxGeometry g = model.getGeometry(cell);

						// Keeps child within the content area of the parent
						if (!g.isRelative()
								&& (g.getX() < c.getX()
										|| g.getY() < c.getY()
										|| c.getWidth() < g.getX()
												+ g.getWidth() || c.getHeight() < g
										.getY()
										+ g.getHeight()))
						{
							double overlap = getOverlap(cell);

							if (c.getWidth() > 0)
							{
								g.setX(Math.min(g.getX(), c.getX()
										+ c.getWidth() - (1 - overlap)
										* g.getWidth()));
							}

							if (c.getHeight() > 0)
							{
								g.setY(Math.min(g.getY(), c.getY()
										+ c.getHeight() - (1 - overlap)
										* g.getHeight()));
							}

							g.setX(Math.max(g.getX(), c.getX() - g.getWidth()
									* overlap));
							g.setY(Math.max(g.getY(), c.getY() - g.getHeight()
									* overlap));
						}
					}
				}
			}
			finally
			{
				model.endUpdate();
			}
		}
	}

	/**
	 * Resets the control points of the edges that are connected to the given
	 * cells if not both ends of the edge are in the given cells array.
	 *
	 * @param cells Array of mxCells for which the connected edges should be
	 * reset.
	 */
	public void resetEdges(Object[] cells)
	{
		if (cells != null)
		{
			// Prepares a hashtable for faster cell lookups
			HashSet set = new HashSet(Arrays.asList(cells));

			model.beginUpdate();
			try
			{
				for (int i = 0; i < cells.length; i++)
				{
					Object[] edges = mxGraphModel.getEdges(model, cells[i]);

					if (edges != null)
					{
						for (int j = 0; j < edges.length; j++)
						{
							Object source = view.getVisibleTerminal(edges[j],
									true);
							Object target = view.getVisibleTerminal(edges[j],
									false);

							// Checks if one of the terminals is not in the given array
							if (!set.contains(source) || !set.contains(target))
							{
								mxGeometry geo = model.getGeometry(edges[j]);

								// Resets the control points
								List points = geo.getPoints();

								if (geo != null && points != null
										&& !points.isEmpty())
								{
									geo = (mxGeometry) geo.clone();
									geo.setPoints(null);
									model.setGeometry(edges[j], geo);
								}
							}
						}
					}

					resetEdges(mxGraphModel.getChildren(model, cells[i]));
				}
			}
			finally
			{
				model.endUpdate();
			}
		}
	}

	//
	// Cell connecting
	//

	/**
	 * Assigns the given edge the new source or target terminal depending on
	 * isSource and fires a EVENT_BEFORE_CONNECT, EVENT_CONNECT and
	 * EVENT_AFTER_CONNECT event.
	 */
	public void connectCell(Object edge, Object terminal, boolean isSource)
	{
		if (edge != null)
		{
			fireEvent(EVENT_BEFORE_CONNECT, new Object[] { edge, terminal,
					isSource });

			model.beginUpdate();
			try
			{
				model.setTerminal(edge, terminal, isSource);
				mxGeometry geo = model.getGeometry(edge);

				if (geo != null && geo.getPoints() != null)
				{
					geo = (mxGeometry) geo.clone();
					geo.setPoints(null);
					model.setGeometry(edge, geo);
				}

				fireEvent(EVENT_CONNECT, new Object[] { edge, terminal,
						isSource });
			}
			finally
			{
				model.endUpdate();
			}

			fireEvent(EVENT_AFTER_CONNECT, new Object[] { edge, terminal,
					isSource });
		}
	}

	/**
	 * Disconnects the given edges from the terminals which are not in the
	 * given array.
	 *
	 * @param cells Array of <mxCells> to be disconnected.
	 */
	public void disconnectGraph(Object[] cells)
	{
		if (cells != null)
		{
			fireEvent(EVENT_BEFORE_DISCONNECT, new Object[] { cells });

			model.beginUpdate();
			try
			{
				double scale = view.getScale();
				mxPoint tr = view.getTranslate();

				// Prepares a hashtable for faster cell lookups
				Set hash = new HashSet();

				for (int i = 0; i < cells.length; i++)
				{
					hash.add(cells[i]);
				}

				for (int i = 0; i < cells.length; i++)
				{
					if (model.isEdge(cells[i]))
					{
						mxGeometry g = model.getGeometry(cells[i]);

						if (g != null)
						{
							g = (mxGeometry) g.clone();

							mxCellState state = view.getState(cells[i]);
							mxCellState pstate = view.getState(model
									.getParent(cells[i]));

							if (state != null && pstate != null)
							{
								double dx = -pstate.getOrigin().getX();
								double dy = -pstate.getOrigin().getY();

								Object src = model.getTerminal(cells[i], true);

								if (src != null
										&& isCellDisconnectable(cells[i], src,
												true))
								{
									while (src != null && !hash.contains(src))
									{
										src = model.getParent(src);
									}

									if (src == null)
									{
										mxPoint pt = state.getAbsolutePoint(0);
										g
												.setTerminalPoint(new mxPoint(
														pt.getX() / scale
																- tr.getX()
																+ dx, pt.getY()
																/ scale
																- tr.getY()
																+ dy), true);
										model.setTerminal(cells[i], null, true);
									}
								}

								Object trg = model.getTerminal(cells[i], false);

								if (trg != null
										&& isCellDisconnectable(cells[i], trg,
												false))
								{
									while (trg != null && !hash.contains(trg))
									{
										trg = model.getParent(trg);
									}

									if (trg == null)
									{
										int n = state.getAbsolutePointCount() - 1;
										mxPoint pt = state.getAbsolutePoint(n);
										g.setTerminalPoint(new mxPoint(pt
												.getX()
												/ scale - tr.getX() + dx, pt
												.getY()
												/ scale - tr.getY() + dy),
												false);
										model
												.setTerminal(cells[i], null,
														false);
									}
								}
							}

							model.setGeometry(cells[i], g);
						}
					}
				}

				fireEvent(EVENT_DISCONNECT, new Object[] { cells });
			}
			finally
			{
				model.endUpdate();
			}

			fireEvent(EVENT_AFTER_DISCONNECT, new Object[] { cells });
		}
	}

	//
	// Graph display
	//

	/**
	 * Returns the bounds of the visible graph.
	 */
	public mxRectangle getBounds()
	{
		return view.getBounds();
	}

	/**
	 * Returns the bounds of the given cell.
	 */
	public mxRectangle getCellBounds(Object cell)
	{
		return getCellBounds(cell, false);
	}

	/**
	 * Returns the bounds of the given cell including all connected edges
	 * if includeEdge is true.
	 */
	public mxRectangle getCellBounds(Object cell, boolean includeEdges)
	{
		return getCellBounds(cell, includeEdges, false);
	}

	/**
	 * Returns the bounds of the given cell including all connected edges
	 * if includeEdge is true.
	 */
	public mxRectangle getCellBounds(Object cell, boolean includeEdges,
			boolean includeDescendants)
	{
		return getCellBounds(cell, includeEdges, includeDescendants, false);
	}

	/**
	 * Returns the bounds of the given cell.
	 */
	public mxRectangle getBoundingBox(Object cell)
	{
		return getBoundingBox(cell, false);
	}

	/**
	 * Returns the bounding box of the given cell including all connected edges
	 * if includeEdge is true.
	 */
	public mxRectangle getBoundingBox(Object cell, boolean includeEdges)
	{
		return getBoundingBox(cell, includeEdges, false);
	}

	/**
	 * Returns the bounding box of the given cell including all connected edges
	 * if includeEdge is true.
	 */
	public mxRectangle getBoundingBox(Object cell, boolean includeEdges,
			boolean includeDescendants)
	{
		return getCellBounds(cell, includeEdges, includeDescendants, true);
	}

	/**
	 * Returns the bounding box of the given cells and their descendants.
	 */
	public mxRectangle getPaintBounds(Object[] cells)
	{
		return getBoundsForCells(cells, false, true, true);
	}

	/**
	 * Returns the bounds for the given cells.
	 */
	public mxRectangle getBoundsForCells(Object[] cells, boolean includeEdges,
			boolean includeDescendants, boolean boundingBox)
	{
		mxRectangle result = null;

		if (cells != null && cells.length > 0)
		{
			for (int i = 0; i < cells.length; i++)
			{
				mxRectangle tmp = getCellBounds(cells[i], includeEdges,
						includeDescendants, boundingBox);

				if (tmp != null)
				{
					if (result == null)
					{
						result = new mxRectangle(tmp);
					}
					else
					{
						result.add(tmp);
					}
				}
			}
		}

		return result;
	}

	/**
	 * Returns the bounds of the given cell including all connected edges
	 * if includeEdge is true.
	 */
	public mxRectangle getCellBounds(Object cell, boolean includeEdges,
			boolean includeDescendants, boolean boundingBox)
	{
		mxRectangle result = null;
		Object[] cells;

		// Recursively includes connected edges
		if (includeEdges)
		{
			Set allCells = new HashSet();
			allCells.add(cell);

			Set edges = new HashSet(Arrays.asList(getEdges(cell)));

			while (!edges.isEmpty() && !allCells.containsAll(edges))
			{
				allCells.addAll(edges);

				Set tmp = new HashSet();
				Iterator it = edges.iterator();

				while (it.hasNext())
				{
					Object edge = it.next();
					tmp.addAll(Arrays.asList(getEdges(edge)));
				}

				edges = tmp;
			}

			cells = allCells.toArray();
		}
		else
		{
			cells = new Object[] { cell };
		}

		if (boundingBox)
		{
			result = view.getBoundingBox(cells);
		}
		else
		{
			result = view.getBounds(cells);
		}

		// Recursively includes the bounds of the children
		if (includeDescendants)
		{
			int childCount = model.getChildCount(cell);

			for (int i = 0; i < childCount; i++)
			{
				mxRectangle tmp = getCellBounds(model.getChildAt(cell, i),
						includeEdges, true, boundingBox);

				if (result != null)
				{
					result.add(tmp);
				}
				else
				{
					result = tmp;
				}
			}
		}

		return result;
	}

	/**
	 * Clears all cell states or the states for the hierarchy starting at the
	 * given cell and validates the graph.
	 */
	public void refresh()
	{
		view.revalidate();
		repaint();
	}

	/**
	 * Fires a repaint event.
	 */
	public void repaint()
	{
		fireEvent(EVENT_REPAINT);
	}

	/**
	 * Snaps the given numeric value to the grid if <gridEnabled> is true.
	 *
	 * @param value Numeric value to be snapped to the grid.
	 * @return Returns the value aligned to the grid.
	 */
	public double snap(double value)
	{
		if (gridEnabled)
		{
			value = Math.round(value / gridSize) * gridSize;
		}

		return value;
	}

	/**
	 * Returns the geometry for the given cell.
	 *
	 * @param cell Cell whose geometry should be returned.
	 * @return Returns the geometry of the cell.
	 */
	public mxGeometry getCellGeometry(Object cell)
	{
		return model.getGeometry(cell);
	}

	/**
	 * Returns true if the given cell is visible in this graph. This
	 * implementation uses <mxGraphModel.isVisible>. Subclassers can override
	 * this to implement specific visibility for cells in only one graph, that
	 * is, without affecting the visible state of the cell.
	 *
	 * When using dynamic filter expressions for cell visibility, then the
	 * graph should be revalidated after the filter expression has changed.
	 *
	 * @param cell Cell whose visible state should be returned.
	 * @return Returns the visible state of the cell.
	 */
	public boolean isCellVisible(Object cell)
	{
		return model.isVisible(cell);
	}

	/**
	 * Returns true if the given cell is collapsed in this graph. This
	 * implementation uses <mxGraphModel.isCollapsed>. Subclassers can override
	 * this to implement specific collapsed states for cells in only one graph,
	 * that is, without affecting the collapsed state of the cell.
	 *
	 * When using dynamic filter expressions for the collapsed state, then the
	 * graph should be revalidated after the filter expression has changed.
	 *
	 * @param cell Cell whose collapsed state should be returned.
	 * @return Returns the collapsed state of the cell.
	 */
	public boolean isCellCollapsed(Object cell)
	{
		return model.isCollapsed(cell);
	}

	/**
	 * Returns true if the given cell is connectable in this graph. This
	 * implementation uses <mxGraphModel.isConnectable>. Subclassers can override
	 * this to implement specific connectable states for cells in only one graph,
	 * that is, without affecting the connectable state of the cell in the model.
	 *
	 * @param cell Cell whose connectable state should be returned.
	 * @return Returns the connectable state of the cell.
	 */
	public boolean isCellConnectable(Object cell)
	{
		return model.isConnectable(cell);
	}

	/**
	 * Returns true if perimeter points should be computed such that the
	 * resulting edge has only horizontal or vertical segments.
	 *
	 * @param edge Cell state that represents the edge.
	 * @param vertex Cell state that represents the vertex.
	 */
	public boolean isOrthogonal(mxCellState edge, mxCellState vertex)
	{
		mxEdgeStyle.mxEdgeStyleFunction tmp = view.getEdgeStyle(edge, null,
				null);

		return tmp == mxEdgeStyle.ElbowConnector
				|| tmp == mxEdgeStyle.SideToSide
				|| tmp == mxEdgeStyle.TopToBottom
				|| tmp == mxEdgeStyle.EntityRelation;
	}

	/**
	 * Returns true if the given cell state is a loop.
	 *
	 * @param state <mxCellState> that represents a potential loop.
	 * @return Returns true if the given cell is a loop.
	 */
	public boolean isLoop(mxCellState state)
	{
		Object src = view.getVisibleTerminal(state.getCell(), true);
		Object trg = view.getVisibleTerminal(state.getCell(), false);

		return (src != null && src == trg);
	}

	//
	// Folding
	//

	/**
	 *
	 */
	public void foldCells(boolean collapse)
	{
		foldCells(collapse, false);

	}

	/**
	 *
	 */
	public void foldCells(boolean collapse, boolean recurse)
	{
		foldCells(collapse, recurse, null);
	}

	/**
	 * Sets the collapsed state of the given cells or the selection cells if no
	 * cells are specified and fires a beforeCollapse, collapse and
	 * afterCollapse or beforeExpand, expand and afterExpand event.
	 *
	 * @param cells Optional array of cells whose collapsed state should be set.
	 * Default is the selection cells.
	 * @param collapse Boolean indicating the collapsed state to be assigned.
	 * @param recurse Boolean indicating if the collapsed state of all
	 * descendants should be set.
	 */
	public void foldCells(boolean collapse, boolean recurse, Object[] cells)
	{
		if (cells == null)
		{
			cells = getFoldableCells(getSelectionCells(), collapse);
		}

		if (cells != null && cells.length > 0)
		{
			fireEvent(EVENT_BEFORE_FOLD, new Object[] { cells, collapse,
					recurse });

			model.beginUpdate();
			try
			{
				for (int i = 0; i < cells.length; i++)
				{
					if (collapse != isCellCollapsed(cells[i]))
					{
						model.setCollapsed(cells[i], collapse);
						swapBounds(cells[i], collapse);

						if (isExtendParentOnResize(cells[i]))
						{
							extendParent(cells[i]);
						}

						if (recurse)
						{
							Object[] children = mxGraphModel.getChildren(model,
									cells[i]);
							foldCells(collapse, true, children);
						}
					}
				}

				// Fires the collapse or expand event
				fireEvent(EVENT_FOLD, new Object[] { cells, collapse, recurse });
			}
			finally
			{
				model.endUpdate();
			}

			fireEvent(EVENT_AFTER_FOLD,
					new Object[] { cells, collapse, recurse });
		}
	}

	/**
	 * Swaps the alternate and the actual bounds in the geometry of the given
	 * cell invoking <updateAlternateBounds> before carrying out the swap.
	 *
	 * @param cell <mxCell> for which the bounds should be swapped.
	 * @param willCollapse Boolean indicating if the cell is going to be collapsed.
	 */
	public void swapBounds(Object cell, boolean willCollapse)
	{
		if (cell != null)
		{
			mxGeometry g = model.getGeometry(cell);

			if (g != null)
			{
				g = (mxGeometry) g.clone();

				updateAlternateBounds(cell, g, willCollapse);
				g.swap();

				model.setGeometry(cell, g);
			}
		}
	}

	/**
	 * Updates or sets the alternate bounds in the given geometry for the given
	 * cell depending on whether the cell is going to be collapsed. If no
	 * alternate bounds are defined in the geometry and
	 * <collapseToPreferredSize> is true, then the preferred size is used for
	 * the alternate bounds. The top, left corner is always kept at the same
	 * location.
	 *
	 * @param cell <mxCell> for which the geometry is being udpated.
	 * @param g <mxGeometry> for which the alternate bounds should be updated.
	 * @param willCollapse Boolean indicating if the cell is going to be collapsed.
	 */
	public void updateAlternateBounds(Object cell, mxGeometry g,
			boolean willCollapse)
	{
		if (cell != null && g != null)
		{
			if (g.getAlternateBounds() == null)
			{
				mxRectangle bounds = null;

				if (collapseToPreferredSize)
				{
					bounds = getPreferredSizeForCell(cell);
					mxCellState state = getView().getState(cell);
					Hashtable style = (state != null) ? state.getStyle()
							: getCellStyle(cell);

					int startSize = mxUtils.getInt(style,
							mxConstants.STYLE_STARTSIZE);

					if (startSize > 0)
					{
						bounds.setHeight(Math
								.max(bounds.getHeight(), startSize));
					}
				}

				if (bounds == null)
				{
					bounds = g;
				}

				g.setAlternateBounds(new mxRectangle(g.getX(), g.getY(), bounds
						.getWidth(), bounds.getHeight()));
			}
			else
			{
				g.getAlternateBounds().setX(g.getX());
				g.getAlternateBounds().setY(g.getY());
			}
		}
	}

	//
	// Drilldown
	//

	/**
	 * Returns the current root of the displayed cell hierarchy. This is a
	 * shortcut to <mxGraphView.currentRoot> in <view>.
	 *
	 * @return Returns the current root in the view.
	 */
	public Object getCurrentRoot()
	{
		return view.getCurrentRoot();
	}

	/**
	 * Returns the translation to be used if the given cell is the root cell as
	 * an <mxPoint>. This implementation returns null.
	 *
	 * @param cell Cell that represents the root of the view.
	 * @return Returns the translation of the graph for the given root cell.
	 */
	public mxPoint getTranslateForRoot(Object cell)
	{
		return null;
	}

	/**
	 * Returns the offset to be used for the cells inside the given cell. The
	 * root and layer cells may be identified using mxGraphModel.isRoot and
	 * mxGraphModel.isLayer. This implementation returns null.
	 *
	 * @param cell Cell whose offset should be returned.
	 * @return Returns the child offset for the given cell.
	 */
	public mxPoint getChildOffsetForCell(Object cell)
	{
		return null;
	}

	/**
	 *
	 */
	public void enterGroup()
	{
		enterGroup(null);
	}

	/**
	 * Uses the given cell as the root of the displayed cell hierarchy. If no
	 * cell is specified then the selection cell is used. The cell is only used
	 * if <isValidRoot> returns true.
	 *
	 * @param cell
	 */
	public void enterGroup(Object cell)
	{
		if (cell == null)
		{
			cell = getSelectionCell();
		}

		if (cell != null && isValidRoot(cell))
		{
			view.setCurrentRoot(cell);
			clearSelection();
		}
	}

	/**
	 * Changes the current root to the next valid root in the displayed cell
	 * hierarchy.
	 */
	public void exitGroup()
	{
		Object root = model.getRoot();
		Object current = getCurrentRoot();

		if (current != null)
		{
			Object next = model.getParent(current);

			// Finds the next valid root in the hierarchy
			while (next != root && !isValidRoot(next)
					&& model.getParent(next) != root)
			{
				next = model.getParent(next);
			}

			// Clears the current root if the new root is
			// the model's root or one of the layers.
			if (next == root || model.getParent(next) == root)
			{
				view.setCurrentRoot(null);
			}
			else
			{
				view.setCurrentRoot(next);
			}

			mxCellState state = view.getState(current);

			// Selects the previous root in the graph
			if (state != null)
			{
				setSelectionCell(current);
			}
		}
	}

	/**
	 * Uses the root of the model as the root of the displayed cell hierarchy
	 * and selects the previous root.
	 */
	public void home()
	{
		Object current = getCurrentRoot();

		if (current != null)
		{
			view.setCurrentRoot(null);
			mxCellState state = view.getState(current);

			if (state != null)
			{
				setSelectionCell(current);
			}
		}
	}

	/**
	 * Returns true if the given cell is a valid root for the cell display
	 * hierarchy. This implementation returns true for all non-null values.
	 *
	 * @param cell <mxCell> which should be checked as a possible root.
	 * @return Returns true if the given cell is a valid root.
	 */
	public boolean isValidRoot(Object cell)
	{
		return (cell != null);
	}

	//
	// Grouping
	//

	/**
	 *
	 * @return Returns the group that has been inserted.
	 */
	public Object groupCells()
	{
		return groupCells(null);
	}

	/**
	 *
	 * @param group
	 * @return Returns the group that has been inserted.
	 */
	public Object groupCells(Object group)
	{
		return groupCells(group, 0);
	}

	/**
	 *
	 * @param group
	 * @param border
	 */
	public Object groupCells(Object group, double border)
	{
		return groupCells(group, border, null);
	}

	/**
	 * Puts the selection cells into the given group using border around the
	 * children and the group bounds. Only the selection cells that have the
	 * same parent as the first selection cell are added to the group. If no
	 * group is specified then a new vertex <mxCell> is created with an empty
	 * string user object. Finally, the group is selected.
	 *
	 * @param group <mxCell> that represents the target group.
	 * @param border Optional integer that specifies the border between the child
	 * area and the group bounds.
	 * @param tmp Optional array of cells to be grouped.
	 */
	public Object groupCells(Object group, double border, Object[] tmp)
	{
		if (tmp == null)
		{
			tmp = getSelectionCells();
		}

		if (tmp != null && tmp.length > 1)
		{
			Object parent = model.getParent(tmp[0]);
			List cells = new ArrayList(tmp.length);
			cells.add(tmp[0]);

			// Filters selection cells with the same parent
			for (int i = 1; i < tmp.length; i++)
			{
				if (model.getParent(tmp[i]) == parent)
				{
					cells.add(tmp[i]);
				}
			}

			// Checks if more than 1 selection cells have the same parent
			Object[] cellArray = cells.toArray();

			if (cellArray.length > 1)
			{
				if (group == null)
				{
					group = createGroupCell(cellArray);
				}

				group = addGroup(group, cellArray, border);

				// Selects the group cell
				if (group != null)
				{
					setSelectionCell(group);
				}
			}
		}

		return group;
	}

	/**
	 * Hook for creating the group cell to hold the given array of <mxCells> if
	 * no group cell was given to the <group> function.
	 *
	 * @param cells
	 * @return Returns a new group cell.
	 */
	public Object createGroupCell(Object[] cells)
	{
		mxCell group = new mxCell("");
		group.setVertex(true);
		group.setConnectable(false);

		return group;
	}

	/**
	 *
	 */
	public void ungroupCells()
	{
		ungroupCells(null);
	}

	/**
	 * Ungroups the given group cells and selects the children.
	 *
	 * @param cells Optional array of <mxCells> to be ungrouped. Default is the
	 * selection cells.
	 */
	public void ungroupCells(Object[] cells)
	{
		if (cells == null)
		{
			cells = getSelectionCells();
		}

		if (cells != null)
		{
			clearSelection();

			model.beginUpdate();
			try
			{
				for (int i = 0; i < cells.length; i++)
				{
					Object[] children = mxGraphModel.getChildren(model,
							cells[i]);

					if (children != null && children.length > 0)
					{
						moveCellsIntoParent(children, model.getParent(cells[i]));
						addSelectionCells(children);
						removeCells(new Object[] { cells[i] });
					}
				}
			}
			finally
			{
				model.endUpdate();
			}
		}
	}

	/**
	 *
	 * @param group
	 * @param cells
	 * @return Returns the group cell that has been inserted.
	 */
	public Object addGroup(Object group, Object[] cells)
	{
		return addGroup(group, cells, 0);
	}

	/**
	 * Sets the bounds of the given group to cover the area of the given cells
	 * and adds the given border around the area. If the group is a swimlane,
	 * then the title area is also taken into account. Returns the given group
	 * if the area of the children is not null.
	 *
	 * @param group <mxCell> that represents the new parent group.
	 * @param cells Array of <mxCells> to be added into the group.
	 * @param border Optional border for the group area in pixels.
	 * @return Returns the group cell that has been inserted.
	 */
	public Object addGroup(Object group, Object[] cells, double border)
	{
		Object parent = model.getParent(cells[0]);
		mxCellState pstate = view.getState(parent);
		mxRectangle bounds = view.getBounds(cells);

		if (bounds != null)
		{
			double scale = view.getScale();
			mxPoint translate = view.getTranslate();

			double x = bounds.getX() - pstate.getOrigin().getX() * scale;
			double y = bounds.getY() - pstate.getOrigin().getY() * scale;
			double width = bounds.getWidth();
			double height = bounds.getHeight();

			// Adds the startsize to the dimension
			if (isSwimlane(group))
			{
				mxRectangle size = getStartSize(group);

				x -= size.getWidth();
				width += size.getWidth();
				y -= size.getHeight();
				height += size.getHeight();
			}

			mxGeometry geo = new mxGeometry(x / scale - border
					- translate.getX(), y / scale - border - translate.getY(),
					width / scale + 2 * border, height / scale + 2 * border);

			model.beginUpdate();
			try
			{
				group = addGroupCells(parent, group, cells, -geo.getX(), -geo
						.getY());

				if (group != null)
				{
					model.setGeometry(group, geo);
				}
			}
			finally
			{
				model.endUpdate();
			}

			return group;
		}

		return null;
	}

	/**
	 * Adds the group into the given parent and the given cells into the group.
	 * The cells are translated by (dx, dy) before being added into the group.
	 *
	 * @param parent <mxCell> that represents the parent of the group.
	 * @param group <mxCell> that represents the group to be added.
	 * @param cells Array of <mxCells> to be translated and added into the group.
	 * @param dx X-coordinate of the translation to be applied.
	 * @param dy Y-coordinate of the translation to be applied.
	 */
	public Object addGroupCells(Object parent, Object group, Object[] cells,
			double dx, double dy)
	{
		model.beginUpdate();
		try
		{
			int index = model.getChildCount(parent);
			group = model.add(parent, group, index);

			if (group != null)
			{
				for (int i = 0; i < cells.length; i++)
				{
					if (model.getParent(cells[i]) != group)
					{
						index = model.getChildCount(group);
						model.add(group, cells[i], index);
					}

					mxGeometry geometry = model.getGeometry(cells[i]);

					if (geometry != null)
					{
						geometry = geometry.translate(dx, dy);
						model.setGeometry(cells[i], geometry);
					}
				}
			}
		}
		finally
		{
			model.endUpdate();
		}

		return group;
	}

	//
	// Cell validation
	//

	/**
	 *
	 */
	public void setMultiplicities(mxMultiplicity[] multiplicities)
	{
		mxMultiplicity[] oldValue = this.multiplicities;
		this.multiplicities = multiplicities;

		changeSupport.firePropertyChange("multiplicities", oldValue,
				multiplicities);
	}

	/**
	 *
	 */
	public mxMultiplicity[] getMultiplicities()
	{
		return multiplicities;
	}

	/**
	 * Checks if the return value of getEdgeValidationError for the given
	 * arguments is null.
	 *
	 * @param edge Cell that represents the edge to validate.
	 * @param source Cell that represents the source terminal.
	 * @param target Cell that represents the target terminal.
	 */
	public boolean isEdgeValid(Object edge, Object source, Object target)
	{
		return getEdgeValidationError(edge, source, target) == null;
	}

	/**
	 * Returns the validation error message to be displayed when inserting or
	 * changing an edges' connectivity. A return value of null means the edge
	 * is valid, a return value of '' means it's not valid, but do not display
	 * an error message. Any other (non-empty) string returned from this method
	 * is displayed as an error message when trying to connect an edge to a
	 * source and target. This implementation uses the multiplicities, as
	 * well as multigraph and allowDanglingEdges to generate validation
	 * errors.
	 *
	 * @param edge Cell that represents the edge to validate.
	 * @param source Cell that represents the source terminal.
	 * @param target Cell that represents the target terminal.
	 */
	public String getEdgeValidationError(Object edge, Object source,
			Object target)
	{
		if (edge != null && model.getTerminal(edge, true) == null
				&& model.getTerminal(edge, false) == null)
		{
			return null;
		}

		// Checks if we're dealing with a loop
		if (!allowLoops && source == target && source != null)
		{
			return "";
		}

		// Checks if the connection is generally allowed
		if (!isValidConnection(source, target))
		{
			return "";
		}

		if (source != null && target != null)
		{
			StringBuffer error = new StringBuffer();

			// Checks if the cells are already connected
			// and adds an error message if required
			if (!multigraph)
			{
				Object[] tmp = mxGraphModel.getEdgesBetween(model, source,
						target, true);

				// Checks if the source and target are not connected by another edge
				if (tmp.length > 1 || (tmp.length == 1 && tmp[0] != edge))
				{
					error.append(mxResources.get("alreadyConnected",
							"Already Connected")
							+ "\n");
				}
			}

			// Gets the number of outgoing edges from the source
			// and the number of incoming edges from the target
			// without counting the edge being currently changed.
			int sourceOut = mxGraphModel.getDirectedEdgeCount(model, source,
					true, edge);
			int targetIn = mxGraphModel.getDirectedEdgeCount(model, target,
					false, edge);

			// Checks the change against each multiplicity rule
			if (multiplicities != null)
			{
				for (int i = 0; i < multiplicities.length; i++)
				{
					String err = multiplicities[i].check(this, edge, source,
							target, sourceOut, targetIn);

					if (err != null)
					{
						error.append(err);
					}
				}
			}

			// Validates the source and target terminals independently
			String err = validateEdge(edge, source, target);

			if (err != null)
			{
				error.append(err);
			}

			return (error.length() > 0) ? error.toString() : null;
		}

		return (allowDanglingEdges) ? null : "";
	}

	/**
	 * Hook method for subclassers to return an error message for the given
	 * edge and terminals. This implementation returns null.
	 *
	 * @param edge Cell that represents the edge to validate.
	 * @param source Cell that represents the source terminal.
	 * @param target Cell that represents the target terminal.
	 */
	public String validateEdge(Object edge, Object source, Object target)
	{
		return null;
	}

	/**
	 * Validates the graph by validating each descendant of the given cell or
	 * the root of the model. Context is an object that contains the validation
	 * state for the complete validation run. The validation errors are
	 * attached to their cells using <setWarning>. This function returns true
	 * if no validation errors exist in the graph.
	 */
	public String validate()
	{
		return validate(model.getRoot(), new Hashtable());
	}

	/**
	 * Validates the graph by validating each descendant of the given cell or
	 * the root of the model. Context is an object that contains the validation
	 * state for the complete validation run. The validation errors are
	 * attached to their cells using <setWarning>. This function returns true
	 * if no validation errors exist in the graph.
	 *
	 * @param cell Cell to start the validation recursion.
	 * @param context Object that represents the global validation state.
	 */
	public String validate(Object cell, Hashtable context)
	{
		boolean isValid = true;
		int childCount = model.getChildCount(cell);

		for (int i = 0; i < childCount; i++)
		{
			Object tmp = model.getChildAt(cell, i);
			Hashtable ctx = context;

			if (isValidRoot(tmp))
			{
				ctx = new Hashtable();
			}

			String warn = validate(tmp, ctx);

			if (warn != null)
			{
				String html = warn.replaceAll("\n", "<br>");
				int len = html.length();
				//setWarning(tmp, html.substring(0, Math.max(0, len - 4)));
			}
			else
			{
				//setWarning(tmp, null);
			}

			isValid = isValid && warn == null;
		}

		StringBuffer warning = new StringBuffer();

		// Adds error for invalid children if collapsed (children invisible)
		if (isCellCollapsed(cell) && !isValid)
		{
			warning.append(mxResources.get("containsValidationErrors",
					"Contains Validation Errors")
					+ "\n");
		}

		// Checks edges and cells using the defined multiplicities
		if (model.isEdge(cell))
		{
			String tmp = getEdgeValidationError(cell, model.getTerminal(cell,
					true), model.getTerminal(cell, false));

			if (tmp != null)
			{
				warning.append(tmp);
			}
		}
		else
		{
			String tmp = getCellValidationError(cell);

			if (tmp != null)
			{
				warning.append(tmp);
			}
		}

		// Checks custom validation rules
		String err = validateCell(cell, context);

		if (err != null)
		{
			warning.append(err);
		}

		// Updates the display with the warning icons before any potential
		// alerts are displayed
		if (model.getParent(cell) == null)
		{
			view.validate();
		}

		return (warning.length() > 0 || !isValid) ? warning.toString() : null;
	}

	/**
	 * Checks all multiplicities that cannot be enforced while the graph is
	 * being modified, namely, all multiplicities that require a minimum of
	 * 1 edge.
	 *
	 * @param cell Cell for which the multiplicities should be checked.
	 */
	public String getCellValidationError(Object cell)
	{
		int outCount = mxGraphModel.getDirectedEdgeCount(model, cell, true);
		int inCount = mxGraphModel.getDirectedEdgeCount(model, cell, false);
		StringBuffer error = new StringBuffer();
		Object value = model.getValue(cell);

		for (int i = 0; i < multiplicities.length; i++)
		{
			mxMultiplicity rule = multiplicities[i];
			int max = rule.getMaxValue();

			if (rule.source
					&& mxUtils.isNode(value, rule.type, rule.attr, rule.value)
					&& ((max == 0 && outCount > 0)
							|| (rule.min == 1 && outCount == 0) || (max == 1 && outCount > 1)))
			{
				error.append(rule.countError + '\n');
			}
			else if (!rule.source
					&& mxUtils.isNode(value, rule.type, rule.attr, rule.value)
					&& ((max == 0 && inCount > 0)
							|| (rule.min == 1 && inCount == 0) || (max == 1 && inCount > 1)))
			{
				error.append(rule.countError + '\n');
			}
		}

		return (error.length() > 0) ? error.toString() : null;
	}

	/**
	 * Hook method for subclassers to return an error message for the given
	 * cell and validation context. This implementation returns null.
	 *
	 * @param cell Cell that represents the cell to validate.
	 * @param context Hashtable that represents the global validation state.
	 */
	public String validateCell(Object cell, Hashtable context)
	{
		return null;
	}

	//
	// Graph appearance
	//

	/**
	 * @return the labelsVisible
	 */
	public boolean isLabelsVisible()
	{
		return labelsVisible;
	}

	/**
	 * @param labelsVisible the labelsVisible to set
	 */
	public void setLabelsVisible(boolean labelsVisible)
	{
		boolean oldValue = this.labelsVisible;
		this.labelsVisible = labelsVisible;

		changeSupport.firePropertyChange("labelsVisible", oldValue,
				labelsVisible);
	}

	/**
	 * @param htmlLabels the htmlLabels to set
	 */
	public void setHtmlLabels(boolean htmlLabels)
	{
		boolean oldValue = this.htmlLabels;
		this.htmlLabels = htmlLabels;

		changeSupport.firePropertyChange("htmlLabels", oldValue, htmlLabels);
	}

	/**
	 *
	 */
	public boolean isHtmlLabels()
	{
		return htmlLabels;
	}

	/**
	 * Returns the textual representation for the given cell.
	 *
	 * @param cell Cell to be converted to a string.
	 * @return Returns the textual representation of the cell.
	 */
	public String convertValueToString(Object cell)
	{
		Object result = model.getValue(cell);

		return (result != null) ? result.toString() : "";
	}

	/**
	 * Returns a string or DOM node that represents the label for the given
	 * cell. This implementation uses <convertValueToString> if <labelsVisible>
	 * is true. Otherwise it returns an empty string.
	 *
	 * @param cell <mxCell> whose label should be returned.
	 * @return Returns the label for the given cell.
	 */
	public String getLabel(Object cell)
	{
		String result = "";
		Hashtable style = getCellStyle(cell);

		if (cell != null && labelsVisible
				&& !mxUtils.isTrue(style, mxConstants.STYLE_NOLABEL, false))
		{
			result = convertValueToString(cell);
		}

		return result;
	}

	/**
	 * Sets the label for a cell after an in-place edit.
	 *
	 * @param cell
	 * @param newValue
	 */
	public void setLabelForCell(Object cell, Object newValue)
	{
        getModel().setValue(cell, newValue);
	}

	/**
	 * Returns true if the label must be rendered as HTML markup. The default
	 * implementation returns <htmlLabels>.
	 *
	 * @param cell <mxCell> whose label should be displayed as HTML markup.
	 * @return Returns true if the given cell label is HTML markup.
	 */
	public boolean isHtmlLabel(Object cell)
	{
		return htmlLabels;
	}

	/**
	 * Returns the tooltip to be used for the given cell.
	 */
	public String getToolTipForCell(Object cell)
	{
		return convertValueToString(cell);
	}

	/**
	 * Returns the start size of the given swimlane, that is, the width or
	 * height of the part that contains the title, depending on the
	 * horizontal style. The return value is an <mxRectangle> with either
	 * width or height set as appropriate.
	 *
	 * @param swimlane <mxCell> whose start size should be returned.
	 * @return Returns the startsize for the given swimlane.
	 */
	public mxRectangle getStartSize(Object swimlane)
	{
		mxRectangle result = new mxRectangle();
		Hashtable style = getCellStyle(swimlane);

		if (style != null)
		{
			double size = mxUtils.getDouble(style, mxConstants.STYLE_STARTSIZE);

			if (mxUtils.isTrue(style, mxConstants.STYLE_HORIZONTAL, true))
			{
				result.setHeight(size);
			}
			else
			{
				result.setWidth(size);
			}
		}

		return result;
	}

	/**
	 * Returns the image URL for the given cell state. This implementation
	 * returns the value stored under <mxConstants.STYLE_IMAGE> in the cell
	 * style.
	 *
	 * @param state
	 * @return Returns the image associated with the given cell state.
	 */
	public String getImage(mxCellState state)
	{
		return (state != null && state.getStyle() != null) ? mxUtils.getString(
				state.getStyle(), mxConstants.STYLE_IMAGE) : null;
	}

	/**
	 * Returns the value of <border>.
	 *
	 * @return Returns the border.
	 */
	public int getBorder()
	{
		return border;
	}

	/**
	 * Sets the value of <border>.
	 *
	 * @param border Positive integer that represents the border to be used.
	 */
	public void setBorder(int border)
	{
		this.border = border;
	}

	/**
	 * Returns the default edge style used for loops.
	 *
	 * @return Returns the default loop style.
	 */
	public mxEdgeStyle.mxEdgeStyleFunction getDefaultLoopStyle()
	{
		return defaultLoopStyle;
	}

	/**
	 * Sets the default style used for loops.
	 *
	 * @param loopStyle Default style to be used for loops.
	 */
	public void setDefaultLoopStyle(mxEdgeStyle.mxEdgeStyleFunction loopStyle)
	{
		mxEdgeStyle.mxEdgeStyleFunction oldValue = defaultLoopStyle;
		this.defaultLoopStyle = loopStyle;

		changeSupport.firePropertyChange("defaultLoopStyle", oldValue,
				defaultLoopStyle);
	}

	/**
	 * Returns true if the given cell is a swimlane. This implementation always
	 * returns false.
	 *
	 * @param cell Cell that should be checked.
	 * @return Returns true if the cell is a swimlane.
	 */
	public boolean isSwimlane(Object cell)
	{
		if (cell != null)
		{
			if (model.getParent(cell) != model.getRoot())
			{
				mxCellState state = view.getState(cell);
				Hashtable style = (state != null) ? state.getStyle()
						: getCellStyle(cell);

				if (style != null && !model.isEdge(cell))
				{
					return mxUtils
							.getString(style, mxConstants.STYLE_SHAPE, "")
							.equals(mxConstants.SHAPE_SWIMLANE);
				}
			}
		}

		return false;
	}

	//
	// Cells and labels control options.
	//

	/**
	 * Returns true if the given cell may not be moved, sized, bended,
	 * disconnected, edited or selected. This implementation returns true for
	 * all vertices with a relative geometry if cellsLocked is false.
	 *
	 * @param cell Cell whose locked state should be returned.
	 * @return Returns true if the given cell is locked.
	 */
	public boolean isCellLocked(Object cell)
	{
		mxGeometry geometry = model.getGeometry(cell);

		return isCellsLocked()
				|| (geometry != null && model.isVertex(cell) && geometry
						.isRelative());
	}

	/**
	 * Returns cellsLocked, the default return value for isCellLocked.
	 */
	public boolean isCellsLocked()
	{
		return cellsLocked;
	}

	/**
	 * Sets cellsLocked, the default return value for isCellLocked and fires a
	 * property change event for cellsLocked.
	 */
	public void setCellsLocked(boolean value)
	{
		boolean oldValue = cellsLocked;
		cellsLocked = value;

		changeSupport.firePropertyChange("cellsLocked", oldValue, cellsLocked);
	}

	/**
	 * Returns true if the given cell is movable. This implementation returns editable.
	 *
	 * @param cell Cell whose editable state should be returned.
	 * @return Returns true if the cell is editable.
	 */
	public boolean isCellEditable(Object cell)
	{
		return isCellsEditable();
	}

	/**
	 * Returns true if editing is allowed in this graph.
	 *
	 * @return Returns true if the graph is editable.
	 */
	public boolean isCellsEditable()
	{
		return cellsEditable;
	}

	/**
	 * Sets if the graph is editable.
	 */
	public void setCellsEditable(boolean value)
	{
		boolean oldValue = cellsEditable;
		cellsEditable = value;

		changeSupport.firePropertyChange("cellsEditable", oldValue,
				cellsEditable);
	}

	/**
	 * Returns true if the given cell is sizable. This implementation returns
	 * cellsSizable for all cells.
	 *
	 * @param cell Cell whose sizable state should be returned.
	 * @return Returns true if the cell is sizable.
	 */
	public boolean isCellSizable(Object cell)
	{
		// JTS
		if( ((mxCell) cell).getStyle().contains("pseudoStartState") ) 
		   return false;
		// jts
		   
		return isCellsSizable();
	}

	/**
	 * Returns true if the given cell is sizable. This implementation return sizable.
	 *
	 * @param cell Cell whose sizable state should be returned.
	 * @return Returns true if the cell is sizable.
	 */
	public boolean isCellsSizable()
	{
		return cellsSizable;
	}

	/**
	 * Sets if the graph is sizable.
	 */
	public void setCellsSizable(boolean value)
	{
		boolean oldValue = cellsSizable;
		cellsSizable = value;

		changeSupport
				.firePropertyChange("cellsSizable", oldValue, cellsSizable);
	}

	/**
	 * Returns the cells which are movable in the given array of cells.
	 */
	public Object[] getMovableCells(Object[] cells)
	{
		return mxGraphModel.filterCells(cells, new Filter()
		{
			public boolean filter(Object cell)
			{
				return isCellMovable(cell);
			}
		});
	}

	/**
	 * Returns true if the given cell is movable. This implementation
	 * returns movable.
	 *
	 * @param cell Cell whose movable state should be returned.
	 * @return Returns true if the cell is movable.
	 */
	public boolean isCellMovable(Object cell)
	{
		return isCellsMovable() && !isCellLocked(cell);
	}

	/**
	 * Returns true if the given cell is movable. This implementation
	 * returns movable.
	 *
	 * @param cell Cell whose movable state should be returned.
	 * @return Returns true if the cell is movable.
	 */
	public boolean isCellsMovable()
	{
		return cellsMovable;
	}

	/**
	 *
	 */
	public void setCellsMovable(boolean value)
	{
		boolean oldValue = cellsMovable;
		cellsMovable = value;

		changeSupport
				.firePropertyChange("cellsMovable", oldValue, cellsMovable);
	}

	/**
	 * Returns true if the given cell is bendable. This implementation returns
	 * bendable. This is used in mxElbowEdgeHandler to determine if the middle
	 * handle should be shown.
	 *
	 * @param cell Cell whose bendable state should be returned.
	 * @return Returns true if the cell is bendable.
	 */
	public boolean isCellBendable(Object cell)
	{
		return isCellsBendable() && !isCellLocked(cell);
	}

	/**
	 * Returns true if the given cell is bendable. This implementation returns
	 * bendable. This is used in mxElbowEdgeHandler to determine if the middle
	 * handle should be shown.
	 *
	 * @param cell Cell whose bendable state should be returned.
	 * @return Returns true if the cell is bendable.
	 */
	public boolean isCellsBendable()
	{
		return cellsBendable;
	}

	/**
	 * Sets if the graph is bendable.
	 */
	public void setCellsBendable(boolean value)
	{
		boolean oldValue = cellsBendable;
		cellsBendable = value;

		changeSupport.firePropertyChange("cellsBendable", oldValue,
				cellsBendable);
	}

	/**
	 * Returns true if the given cell is selectable. This implementation returns
	 * <selectable>.
	 *
	 * @param cell <mxCell> whose selectable state should be returned.
	 * @return Returns true if the given cell is selectable.
	 */
	public boolean isCellSelectable(Object cell)
	{
		return isCellsSelectable();
	}

	/**
	 * Returns true if the given cell is selectable. This implementation returns
	 * <selectable>.
	 *
	 * @param cell <mxCell> whose selectable state should be returned.
	 * @return Returns true if the given cell is selectable.
	 */
	public boolean isCellsSelectable()
	{
		return cellsSelectable;
	}

	/**
	 * Sets the seletable state of the graph.
	 *
	 * @param selectable Boolean indicating if the graph should allow selection of cells.
	 */
	public void setCellsSelectable(boolean value)
	{
		boolean oldValue = cellsSelectable;
		cellsSelectable = value;

		changeSupport.firePropertyChange("cellsSelectable", oldValue,
				cellsSelectable);
	}

	/**
	 * Returns the cells which are movable in the given array of cells.
	 */
	public Object[] getDeletableCells(Object[] cells)
	{
		return mxGraphModel.filterCells(cells, new Filter()
		{
			public boolean filter(Object cell)
			{
				return isCellDeletable(cell);
			}
		});
	}

	/**
	 * Returns true if the given cell is movable. This implementation always
	 * returns true.
	 *
	 * @param cell Cell whose movable state should be returned.
	 * @return Returns true if the cell is movable.
	 */
	public boolean isCellDeletable(Object cell)
	{
		return isCellsDeletable();
	}

	/**
	 * Returns true if the given cell is movable. This implementation always
	 * returns true.
	 *
	 * @param cell Cell whose movable state should be returned.
	 * @return Returns true if the cell is movable.
	 */
	public boolean isCellsDeletable()
	{
		return cellsDeletable;
	}

	/**
	 * Specifies if the graph should allow deleting of cells. This implementation
	 * updates <deletable>.
	 *
	 * @param deletable Boolean indicating if the graph should allow deletion of cells.
	 */
	public void setCellsDeletable(boolean value)
	{
		boolean oldValue = cellsDeletable;
		cellsDeletable = value;

		changeSupport.firePropertyChange("cellsDeletable", oldValue,
				cellsDeletable);
	}

	/**
	 * Returns the cells which are movable in the given array of cells.
	 */
	public Object[] getCloneableCells(Object[] cells)
	{
		return mxGraphModel.filterCells(cells, new Filter()
		{
			public boolean filter(Object cell)
			{
				return isCellCloneable(cell);
			}
		});
	}

	/**
	 * Returns the constant true. This does not use the cloneable field to
	 * return a value for a given cell, it is simply a hook for subclassers
	 * to disallow cloning of individual cells.
	 */
	public boolean isCellCloneable(Object cell)
	{
		return isCellsCloneable();
	}

	/**
	 * Returns cellsCloneable.
	 */
	public boolean isCellsCloneable()
	{
		return cellsCloneable;
	}

	/**
	 * Specifies if the graph should allow cloning of cells by holding down the
	 * control key while cells are being moved. This implementation updates
	 * cellsCloneable.
	 *
	 * @param value Boolean indicating if the graph should be cloneable.
	 */
	public void setCellsCloneable(boolean value)
	{
		boolean oldValue = cellsCloneable;
		cellsCloneable = value;

		changeSupport.firePropertyChange("cellsCloneable", oldValue,
				cellsCloneable);
	}

	/**
	 * Returns true if the given cell is disconnectable from the source or
	 * target terminal. This returns <disconnectable> for all given cells if
	 * <isLocked> does not return true for the given cell.
	 *
	 * @param cell <mxCell> whose disconnectable state should be returned.
	 * @param terminal <mxCell> that represents the source or target terminal.
	 * @param source Boolean indicating if the source or target terminal is to be
	 * disconnected.
	 * @return Returns true if the given edge can be disconnected from the given
	 * terminal.
	 */
	public boolean isCellDisconnectable(Object cell, Object terminal,
			boolean source)
	{
		return isCellsDisconnectable() && !isCellLocked(cell);
	}

	/**
	 * Returns cellsDisconnectable.
	 */
	public boolean isCellsDisconnectable()
	{
		return cellsDisconnectable;
	}

	/**
	 * Sets cellsDisconnectable.
	 *
	 * @param value Boolean indicating if the graph should allow disconnecting of
	 * edges.
	 */
	public void setCellsDisconnectable(boolean value)
	{
		boolean oldValue = cellsDisconnectable;
		cellsDisconnectable = value;

		changeSupport.firePropertyChange("cellsDisconnectable", oldValue,
				cellsDisconnectable);
	}

	/**
	 * Returns true if the label for the given cell should be clipped.
	 *
	 * @param cell Cell whose clipping state should be returned.
	 * @return Returns true if the cell label should be clipped.
	 */
	public boolean isLabelClipped(Object cell)
	{
		return !model.isEdge(cell) && isLabelsClipped();
	}

	/**
	 * Returns true if the label for the given cell should be clipped.
	 *
	 * @param cell Cell whose clipping state should be returned.
	 * @return Returns true if the cell label should be clipped.
	 */
	public boolean isLabelsClipped()
	{
		return labelsClipped;
	}

	/**
	 *
	 */
	public void setLabelsClipped(boolean value)
	{
		boolean oldValue = labelsClipped;
		labelsClipped = value;

		changeSupport.firePropertyChange("labelsClipped", oldValue,
				labelsClipped);
	}

	/**
	 * Returns true if the given edges's label is moveable. This returns
	 * <movable> for all given cells if <isLocked> does not return true
	 * for the given cell.
	 *
	 * @param cell <mxCell> whose label should be moved.
	 * @return Returns true if the label of the given cell is movable.
	 */
	public boolean isLabelMovable(Object cell)
	{
		return !isCellLocked(cell)
				&& ((model.isEdge(cell) && isEdgeLabelsMovable()) || (model
						.isVertex(cell) && isVertexLabelsMovable()));
	}

	/**
	 *
	 */
	public boolean isVertexLabelsMovable()
	{
		return vertexLabelsMovable;
	}

	/**
	 *
	 * @param movable
	 */
	public void setVertexLabelsMovable(boolean value)
	{
		boolean oldValue = vertexLabelsMovable;
		vertexLabelsMovable = value;

		changeSupport.firePropertyChange("vertexLabelsMovable", oldValue,
				vertexLabelsMovable);
	}

	/**
	 *
	 */
	public boolean isEdgeLabelsMovable()
	{
		return edgeLabelsMovable;
	}

	/**
	 *
	 * @param movable
	 */
	public void setEdgeLabelsMovable(boolean value)
	{
		boolean oldValue = edgeLabelsMovable;
		edgeLabelsMovable = value;

		changeSupport.firePropertyChange("edgeLabelsMovable", oldValue,
				edgeLabelsMovable);
	}

	//
	// Graph control options
	//

	/**
	 * Returns true if the graph is <enabled>.
	 *
	 * @return Returns true if the graph is enabled.
	 */
	public boolean isEnabled()
	{
		return enabled;
	}

	/**
	 * Specifies if the graph should allow any interactions. This
	 * implementation updates <enabled>.
	 *
	 * @param enabled Boolean indicating if the graph should be enabled.
	 */
	public void setEnabled(boolean value)
	{
		boolean oldValue = enabled;
		enabled = value;

		changeSupport.firePropertyChange("enabled", oldValue, enabled);
	}

	/**
	 * Returns true if the graph allows drop into other cells.
	 */
	public boolean isDropEnabled()
	{
		return dropEnabled;
	}

	/**
	 *
	 */
	public void setDropEnabled(boolean value)
	{
		boolean oldValue = dropEnabled;
		dropEnabled = value;

		changeSupport.firePropertyChange("dropEnabled", oldValue, dropEnabled);
	}

	/**
	 * @return the multigraph
	 */
	public boolean isMultigraph()
	{
		return multigraph;
	}

	/**
	 * @param multigraph the multigraph to set
	 */
	public void setMultigraph(boolean value)
	{
		boolean oldValue = multigraph;
		multigraph = value;

		changeSupport.firePropertyChange("multigraph", oldValue, multigraph);
	}

	/**
	 *
	 */
	public void setSwimlaneNesting(boolean value)
	{
		boolean oldValue = swimlaneNesting;
		swimlaneNesting = value;

		changeSupport.firePropertyChange("swimlaneNesting", oldValue,
				swimlaneNesting);
	}

	/**
	 *
	 */
	public boolean isSwimlaneNesting()
	{
		return swimlaneNesting;
	}

	/**
	 * @return the allowDanglingEdges
	 */
	public boolean isAllowDanglingEdges()
	{
		return allowDanglingEdges;
	}

	/**
	 * @param allowDanglingEdges the allowDanglingEdges to set
	 */
	public void setAllowDanglingEdges(boolean value)
	{
		boolean oldValue = allowDanglingEdges;
		allowDanglingEdges = value;

		changeSupport.firePropertyChange("allowDanglingEdges", oldValue,
				allowDanglingEdges);
	}

	/**
	 * @return the cloneInvalidEdges
	 */
	public boolean isCloneInvalidEdges()
	{
		return cloneInvalidEdges;
	}

	/**
	 * @param cloneInvalidEdges the cloneInvalidEdges to set
	 */
	public void setCloneInvalidEdges(boolean value)
	{
		boolean oldValue = cloneInvalidEdges;
		cloneInvalidEdges = value;

		changeSupport.firePropertyChange("cloneInvalidEdges", oldValue,
				cloneInvalidEdges);
	}

	/**
	 * @return the disconnectOnMove
	 */
	public boolean isDisconnectOnMove()
	{
		return disconnectOnMove;
	}

	/**
	 * @param disconnectOnMove the disconnectOnMove to set
	 */
	public void setDisconnectOnMove(boolean value)
	{
		boolean oldValue = disconnectOnMove;
		disconnectOnMove = value;

		changeSupport.firePropertyChange("disconnectOnMove", oldValue,
				disconnectOnMove);

	}

	/**
	 * @return the allowLoops
	 */
	public boolean isAllowLoops()
	{
		return allowLoops;
	}

	/**
	 * @param allowLoops the allowLoops to set
	 */
	public void setAllowLoops(boolean value)
	{
		boolean oldValue = allowLoops;
		allowLoops = value;

		changeSupport.firePropertyChange("allowLoops", oldValue, allowLoops);
	}

	/**
	 * @return the connectableEdges
	 */
	public boolean isConnectableEdges()
	{
		return connectableEdges;
	}

	/**
	 * @param connectableEdges the connectableEdges to set
	 */
	public void setConnectableEdges(boolean value)
	{
		boolean oldValue = connectableEdges;
		connectableEdges = value;

		changeSupport.firePropertyChange("connectableEdges", oldValue,
				connectableEdges);

	}

	/**
	 * @return the resetEdgesOnMove
	 */
	public boolean isResetEdgesOnMove()
	{
		return resetEdgesOnMove;
	}

	/**
	 * @param resetEdgesOnMove the resetEdgesOnMove to set
	 */
	public void setResetEdgesOnMove(boolean value)
	{
		boolean oldValue = resetEdgesOnMove;
		resetEdgesOnMove = value;

		changeSupport.firePropertyChange("resetEdgesOnMove", oldValue,
				resetEdgesOnMove);
	}

	/**
	 * @return the resetEdgesOnResize
	 */
	public boolean isResetEdgesOnResize()
	{
		return resetEdgesOnResize;
	}

	/**
	 * @param resetEdgesOnResize the resetEdgesOnResize to set
	 */
	public void setResetEdgesOnResize(boolean value)
	{
		boolean oldValue = resetEdgesOnResize;
		resetEdgesOnResize = value;

		changeSupport.firePropertyChange("resetEdgesOnResize", oldValue,
				resetEdgesOnResize);
	}

	/**
	 * Returns true if the size of the given cell should automatically be
	 * updated after a change of the label. This implementation returns
	 * autoSize for all given cells.
	 *
	 * @param cell Cell that should be resized.
	 * @return Returns true if the size of the given cell should be updated.
	 */
	public boolean isAutoSizeCell(Object cell)
	{
		return isAutoSizeCells();
	}

	/**
	 * Returns true if the size of the given cell should automatically be
	 * updated after a change of the label. This implementation returns
	 * autoSize for all given cells.
	 *
	 * @param cell Cell that should be resized.
	 * @return Returns true if the size of the given cell should be updated.
	 */
	public boolean isAutoSizeCells()
	{
		return autoSizeCells;
	}

	/**
	 * Specifies if cell sizes should be automatically updated after a label
	 * change. This implementation sets autoSize to the given parameter.
	 *
	 * @param value Boolean indicating if cells should be resized
	 * automatically.
	 */
	public void setAutoSizeCells(boolean value)
	{
		boolean oldValue = autoSizeCells;
		autoSizeCells = value;

		changeSupport.firePropertyChange("autoSizeCells", oldValue,
				autoSizeCells);
	}

	/**
	 * Returns true if the parent of the given cell should be extended if the
	 * child has been resized so that it overlaps the parent. This
	 * implementation returns extendParentOnResize.
	 *
	 * @param cell Cell that has been resized.
	 */
	public boolean isExtendParentOnResize(Object cell)
	{
		return extendParentOnResize;
	}

	/**
	 *
	 */
	public void setExtendParentOnResize(boolean value)
	{
		boolean oldValue = extendParentOnResize;
		extendParentOnResize = value;

		changeSupport.firePropertyChange("extendParentOnResize", oldValue,
				extendParentOnResize);
	}

	/**
	 * Returns true if the given cell should be kept inside the bounds of its
	 * parent according to the rules defined by getOverlap and
	 * isAllowOverlapParent. This implementation returns
	 * keepInsideParentOnMove for all given cells.
	 *
	 * @return the keepInsideParentOnMove
	 */
	public boolean isKeepInsideParentOnMove(Object cell)
	{
		return keepInsideParentOnMove;
	}

	/**
	 * @param keepInsideParentOnMove the keepInsideParentOnMove to set
	 */
	public void setKeepInsideParentOnMove(boolean value)
	{
		boolean oldValue = keepInsideParentOnMove;
		keepInsideParentOnMove = value;

		changeSupport.firePropertyChange("keepInsideParentOnMove", oldValue,
				gridSize);
	}

	/**
	 * @return Returns true if edges are rendered in the foreground.
	 */
	public boolean isKeepEdgesInForeground()
	{
		return keepEdgesInForeground;
	}

	/**
	 * @param keepEdgesInForeground the keepEdgesInForeground to set
	 */
	public void setKeepEdgesInForeground(boolean value)
	{
		boolean oldValue = keepEdgesInForeground;
		keepEdgesInForeground = value;

		changeSupport.firePropertyChange("keepEdgesInForeground", oldValue,
				keepEdgesInForeground);
	}

	/**
	 * @return Returns true if edges are rendered in the background.
	 */
	public boolean isKeepEdgesInBackground()
	{
		return keepEdgesInBackground;
	}

	/**
	 * @param keepEdgesInBackground the keepEdgesInBackground to set
	 */
	public void setKeepEdgesInBackground(boolean value)
	{
		boolean oldValue = keepEdgesInBackground;
		keepEdgesInBackground = value;

		changeSupport.firePropertyChange("keepEdgesInBackground", oldValue,
				keepEdgesInBackground);
	}

	/**
	 * Returns true if the given cell is a valid source for new connections.
	 * This implementation returns true for all non-null values and is
	 * called by is called by <isValidConnection>.
	 *
	 * @param cell Object that represents a possible source or null.
	 * @return Returns true if the given cell is a valid source terminal.
	 */
	public boolean isValidSource(Object cell)
	{
		return (cell == null && allowDanglingEdges)
				|| (cell != null && (!model.isEdge(cell) || connectableEdges) && isCellConnectable(cell));
	}

	/**
	 * Returns isValidSource for the given cell. This is called by
	 * isValidConnection.
	 *
	 * @param cell Object that represents a possible target or null.
	 * @return Returns true if the given cell is a valid target.
	 */
	public boolean isValidTarget(Object cell)
	{
		return isValidSource(cell);
	}

	/**
	 * Returns true if the given target cell is a valid target for source.
	 * This is a boolean implementation for not allowing connections between
	 * certain pairs of vertices and is called by <getEdgeValidationError>.
	 * This implementation returns true if <isValidSource> returns true for
	 * the source and <isValidTarget> returns true for the target.
	 *
	 * @param source Object that represents the source cell.
	 * @param target Object that represents the target cell.
	 * @return Returns true if the the connection between the given terminals
	 * is valid.
	 */
	public boolean isValidConnection(Object source, Object target)
	{
		return isValidSource(source) && isValidTarget(target)
				&& (allowLoops || source != target);
	}

	/**
	 * Returns the minimum size of the diagram.
	 *
	 * @return Returns the minimum container size.
	 */
	public mxRectangle getMinimumGraphSize()
	{
		return minimumGraphSize;
	}

	/**
	 * @param minimumGraphSize the minimumGraphSize to set
	 */
	public void setMinimumGraphSize(mxRectangle minimumGraphSize)
	{
		mxRectangle oldValue = this.minimumGraphSize;
		this.minimumGraphSize = minimumGraphSize;

		changeSupport.firePropertyChange("minimumGraphSize", oldValue,
				minimumGraphSize);
	}

	/**
	 * Returns a decimal number representing the amount of the width and height
	 * of the given cell that is allowed to overlap its parent. A value of 0
	 * means all children must stay inside the parent, 1 means the child is
	 * allowed to be placed outside of the parent such that it touches one of
	 * the parents sides. If <isAllowOverlapParent> returns false for the given
	 * cell, then this method returns 0.
	 *
	 * @param cell
	 * @return Returns the overlapping value for the given cell inside its
	 * parent.
	 */
	public double getOverlap(Object cell)
	{
		return (isAllowOverlapParent(cell)) ? defaultOverlap : 0;
	}

	/**
	 * Returns true if the given cell is allowed to be placed outside of the
	 * parents area.
	 *
	 * @param cell
	 * @return Returns true if the given cell may overlap its parent.
	 */
	public boolean isAllowOverlapParent(Object cell)
	{
		return false;
	}

	/**
	 * Returns the cells which are movable in the given array of cells.
	 */
	public Object[] getFoldableCells(Object[] cells, final boolean collapse)
	{
		return mxGraphModel.filterCells(cells, new Filter()
		{
			public boolean filter(Object cell)
			{
				return isCellFoldable(cell, collapse);
			}
		});
	}

	/**
	 * Returns true if the given cell is expandable. This implementation
	 * returns true if the cell has at least one child.
	 *
	 * @param cell <mxCell> whose expandable state should be returned.
	 * @return Returns true if the given cell is expandable.
	 */
	public boolean isCellFoldable(Object cell, boolean collapse)
	{
		return model.getChildCount(cell) > 0;
	}

	/**
	 * Returns true if the grid is enabled.
	 *
	 * @return Returns the enabled state of the grid.
	 */
	public boolean isGridEnabled()
	{
		return gridEnabled;
	}

	/**
	 * Sets if the grid is enabled.
	 *
	 * @param gridEnabled Specifies if the grid should be enabled.
	 */
	public void setGridEnabled(boolean value)
	{
		boolean oldValue = gridEnabled;
		gridEnabled = value;

		changeSupport.firePropertyChange("gridEnabled", oldValue, gridSize);
	}

	/**
	 * Returns the grid size.
	 *
	 * @return Returns the grid size
	 */
	public int getGridSize()
	{
		return gridSize;
	}

	/**
	 * Sets the grid size and fires a property change event for gridSize.
	 *
	 * @param gridSize New grid size to be used.
	 */
	public void setGridSize(int value)
	{
		int oldValue = gridSize;
		gridSize = value;

		changeSupport.firePropertyChange("gridSize", oldValue, gridSize);
	}

	/**
	 * @return the tolerance
	 */
	public String getAlternateEdgeStyle()
	{
		return alternateEdgeStyle;
	}

	/**
	 * @param alternateEdgeStyle the alternateEdgeStyle to set
	 */
	public void setAlternateEdgeStyle(String value)
	{
		String oldValue = alternateEdgeStyle;
		alternateEdgeStyle = value;

		changeSupport.firePropertyChange("alternateEdgeStyle", oldValue,
				alternateEdgeStyle);
	}

	/**
	 * Returns true if the given cell is a valid drop target for the specified
	 * cells. If the given cell is an edge, then <isSplitDropTarget> is used,
	 * else <isParentDropTarget> is used to compute the return value.
	 *
	 * @param cell Object that represents the possible drop target.
	 * @param cells
	 * @return Returns true if the cell is a valid drop target for the given
	 * cells.
	 */
	public boolean isValidDropTarget(Object cell, Object[] cells)
	{
		if (model.isEdge(cell))
		{
			return cells != null && cells.length == 1
					&& isSplitDropTarget(cell, cells[0]);
		}
		else
		{
			return isParentDropTarget(cell, cells);
		}
	};

	/**
	 * Returns true if the given edge may be splitted into two edges with the
	 * given cell as a new terminal between the two.
	 *
	 * @param edge Object that represents the edge to be splitted.
	 * @param cell Object that represents the new terminal.
	 * @return Returns true if the given edge may be splitted by the given
	 * cell.
	 */
	public boolean isSplitDropTarget(Object edge, Object cell)
	{
		if (edge != null)
		{
			Object src = model.getTerminal(edge, true);
			Object trg = model.getTerminal(edge, false);

			return (!model.isAncestor(cell, src) && !model
					.isAncestor(cell, trg));
		}

		return false;
	};

	/**
	 * Returns true if the given target is a valid parent for the specified
	 * cells.
	 *
	 * @param target Object that represents the target cell.
	 * @param cells
	 * @return Returns true if the given target is a possible parent for the
	 * given cells.
	 */
	public boolean isParentDropTarget(Object target, Object[] cells)
	{
		if (target != null)
		{
			return isSwimlane(target)
					|| (model.getChildCount(target) > 0 && !isCellCollapsed(target));
		}

		return false;
	};

	/**
	 * Function: getDropTarget
	 *
	 * Returns the given cell if it is a drop target for the given cells or the
	 * nearest ancestor that may be used as a drop target for the given cells.
	 * If the given array contains a swimlane and <swimlaneNesting> is false
	 * then this always returns null. If no cell is given, then the bottommost
	 * swimlane at the location of the given event is returned.
	 *
	 * This function should only be used if <isDropEnabled> returns true.
	 *
	 * Parameters:
	 *
	 * cells - Array of <mxCells> which are to be dropped onto the target.
	 * evt - Mouseevent for the drag and drop.
	 * cell - <mxCell> that is under the mousepointer.
	 */
	public Object getDropTarget(Object[] cells, Point pt, Object cell)
	{
		if (!swimlaneNesting)
		{
			for (int i = 0; i < cells.length; i++)
			{
				if (isSwimlane(cells[i]))
				{
					return null;
				}
			}
		}

		Object swimlane = null; //getSwimlaneAt(pt.x, pt.y);

		if (cell == null)
		{
			cell = swimlane;
		}
		else if (swimlane != null)
		{
			// Checks if the cell is an ancestor of the swimlane
			// under the mouse and uses the swimlane in that case
			Object tmp = model.getParent(swimlane);

			while (tmp != null && isSwimlane(tmp) && tmp != cell)
			{
				tmp = model.getParent(tmp);
			}

			if (tmp == cell)
			{
				cell = swimlane;
			}
		}

		while (cell != null && !isValidDropTarget(cell, cells)
				&& model.getParent(cell) != model.getRoot())
		{
			cell = model.getParent(cell);
		}

		return (model.getParent(cell) != model.getRoot()) ? cell : null;
	};

	//
	// Cell retrieval
	//

	/**
	 * Returns the first child of the root in the model, that is, the first or
	 * default layer of the diagram.
	 *
	 * @return Returns the default parent for new cells.
	 */
	public Object getDefaultParent()
	{
		Object parent = defaultParent;

		if (parent == null)
		{
			parent = view.getCurrentRoot();

			if (parent == null)
			{
				Object root = model.getRoot();
				parent = model.getChildAt(root, 0);
			}
		}

		return parent;
	}

	/**
	 * Sets the default parent to be returned by getDefaultParent.
	 * Set this to null to return the first child of the root in
	 * getDefaultParent.
	 */
	public void setDefaultParent(Object value)
	{
		defaultParent = value;
	}

	/**
	 * Returns the visible child vertices of the given parent.
	 *
	 * @param parent Cell whose children should be returned.
	 */
	public Object[] getChildVertices(Object parent)
	{
		return getChildCells(parent, true, false);
	}

	/**
	 * Returns the visible child edges of the given parent.
	 *
	 * @param parent Cell whose children should be returned.
	 */
	public Object[] getChildEdges(Object parent)
	{
		return getChildCells(parent, false, true);
	}

	/**
	 * Returns the visible children of the given parent.
	 *
	 * @param parent Cell whose children should be returned.
	 */
	public Object[] getChildCells(Object parent)
	{
		return getChildCells(parent, false, false);
	}

	/**
	 * Returns the visible child vertices or edges in the given parent. If
	 * vertices and edges is false, then all children are returned.
	 *
	 * @param parent Cell whose children should be returned.
	 * @param vertices Specifies if child vertices should be returned.
	 * @param edges Specifies if child edges should be returned.
	 * @return Returns the child vertices and edges.
	 */
	public Object[] getChildCells(Object parent, boolean vertices, boolean edges)
	{
		Object[] cells = mxGraphModel.getChildCells(model, parent, vertices,
				edges);
		List result = new ArrayList(cells.length);

		// Filters out the non-visible child cells
		for (int i = 0; i < cells.length; i++)
		{
			if (isCellVisible(cells[i]))
			{
				result.add(cells[i]);
			}
		}

		return result.toArray();
	}

	/**
	 * Returns all visible edges connected to the given cell without loops.
	 *
	 * @param cell Cell whose connections should be returned.
	 * @return Returns the connected edges for the given cell.
	 */
	public Object[] getConnections(Object cell)
	{
		return getConnections(cell, null);
	}

	/**
	 * Returns all visible edges connected to the given cell without loops.
	 * If the optional parent argument is specified, then only child
	 * edges of the given parent are returned.
	 *
	 * @param cell Cell whose connections should be returned.
	 * @param parent Optional parent of the opposite end for a connection
	 * to be returned.
	 * @return Returns the connected edges for the given cell.
	 */
	public Object[] getConnections(Object cell, Object parent)
	{
		return getEdges(cell, parent, true, true, false);
	}

	/**
	 * Returns all incoming visible edges connected to the given cell without
	 * loops.
	 *
	 * @param cell Cell whose incoming edges should be returned.
	 * @return Returns the incoming edges of the given cell.
	 */
	public Object[] getIncomingEdges(Object cell)
	{
		return getIncomingEdges(cell, null);
	}

	/**
	 * Returns the visible incoming edges for the given cell. If the optional
	 * parent argument is specified, then only child edges of the given parent
	 * are returned.
	 *
	 * @param cell Cell whose incoming edges should be returned.
	 * @param parent Optional parent of the opposite end for an edge
	 * to be returned.
	 * @return Returns the incoming edges of the given cell.
	 */
	public Object[] getIncomingEdges(Object cell, Object parent)
	{
		return getEdges(cell, parent, true, false, false);
	}

	/**
	 * Returns all outgoing visible edges connected to the given cell without
	 * loops.
	 *
	 * @param cell Cell whose outgoing edges should be returned.
	 * @return Returns the outgoing edges of the given cell.
	 */
	public Object[] getOutgoingEdges(Object cell)
	{
		return getOutgoingEdges(cell, null);
	}

	/**
	 * Returns the visible outgoing edges for the given cell. If the optional
	 * parent argument is specified, then only child edges of the given parent
	 * are returned.
	 *
	 * @param cell Cell whose outgoing edges should be returned.
	 * @param parent Optional parent of the opposite end for an edge
	 * to be returned.
	 * @return Returns the outgoing edges of the given cell.
	 */
	public Object[] getOutgoingEdges(Object cell, Object parent)
	{
		return getEdges(cell, parent, false, true, false);
	}

	/**
	 * Returns all visible edges connected to the given cell including loops.
	 *
	 * @param cell Cell whose edges should be returned.
	 * @return Returns the edges of the given cell.
	 */
	public Object[] getEdges(Object cell)
	{
		return getEdges(cell, null);
	}

	/**
	 * Returns all visible edges connected to the given cell including loops.
	 *
	 * @param cell Cell whose edges should be returned.
	 * @param parent Optional parent of the opposite end for an edge
	 * to be returned.
	 * @return Returns the edges of the given cell.
	 */
	public Object[] getEdges(Object cell, Object parent)
	{
		return getEdges(cell, parent, true, true, true);
	}

	/**
	 * Returns the incoming and/or outgoing edges for the given cell.
	 * If the optional parent argument is specified, then only edges are returned
	 * where the opposite is in the given parent cell.
	 *
	 * @param cell Cell whose edges should be returned.
	 * @param parent Optional parent of the opposite end for an edge to be
	 * returned.
	 * @param incoming Specifies if incoming edges should be included in the
	 * result.
	 * @param outgoing Specifies if outgoing edges should be included in the
	 * result.
	 * @param includeLoops Specifies if loops should be included in the result.
	 * @return Returns the edges connected to the given cell.
	 */
	public Object[] getEdges(Object cell, Object parent, boolean incoming,
			boolean outgoing, boolean includeLoops)
	{
		boolean isCollapsed = isCellCollapsed(cell);
		List edges = new ArrayList();
		int childCount = model.getChildCount(cell);

		for (int i = 0; i < childCount; i++)
		{
			Object child = model.getChildAt(cell, i);

			if (isCollapsed || !isCellVisible(child))
			{
				edges.addAll(Arrays.asList(mxGraphModel.getEdges(model, child,
						incoming, outgoing, includeLoops)));
			}
		}

		edges.addAll(Arrays.asList(mxGraphModel.getEdges(model, cell, incoming,
				outgoing, includeLoops)));
		List result = new ArrayList(edges.size());
		Iterator it = edges.iterator();

		while (it.hasNext())
		{
			Object edge = it.next();
			Object source = view.getVisibleTerminal(edge, true);
			Object target = view.getVisibleTerminal(edge, false);

			if (includeLoops
					|| ((source != target) && ((incoming && target == cell && (parent == null || model
							.getParent(source) == parent)) || (outgoing
							&& source == cell && (parent == null || model
							.getParent(target) == parent)))))
			{
				result.add(edge);
			}
		}

		return result.toArray();
	}

	/**
	 * Returns all distinct visible opposite cells of the terminal on the given
	 * edges.
	 *
	 * @param edges
	 * @param terminal
	 * @return Returns the terminals at the opposite ends of the given edges.
	 */
	public Object[] getOpposites(Object[] edges, Object terminal)
	{
		return getOpposites(edges, terminal, true, true);
	}

	/**
	 * Returns all distincts visible opposite cells for the specified terminal
	 * on the given edges.
	 *
	 * @param edges Edges whose opposite terminals should be returned.
	 * @param terminal Terminal that specifies the end whose opposite should be
	 * returned.
	 * @param sources Specifies if source terminals should be included in the
	 * result.
	 * @param targets Specifies if targer terminals should be included in the
	 * result.
	 * @return Returns the cells at the oppsite ends of the given edges.
	 */
	public Object[] getOpposites(Object[] edges, Object terminal,
			boolean sources, boolean targets)
	{
		Collection terminals = new LinkedHashSet();

		if (edges != null)
		{
			for (int i = 0; i < edges.length; i++)
			{
				Object source = view.getVisibleTerminal(edges[i], true);
				Object target = view.getVisibleTerminal(edges[i], false);

				// Checks if the terminal is the source of
				// the edge and if the target should be
				// stored in the result
				if (targets && source == terminal && target != null
						&& target != terminal)
				{
					terminals.add(target);
				}

				// Checks if the terminal is the taget of
				// the edge and if the source should be
				// stored in the result
				else if (sources && target == terminal && source != null
						&& source != terminal)
				{
					terminals.add(source);
				}
			}
		}

		return terminals.toArray();
	}

	/**
	 * Returns the edges between the given source and target. This takes into
	 * account collapsed and invisible cells and returns the connected edges
	 * as displayed on the screen.
	 *
	 * @param source
	 * @param target
	 * @return Returns all edges between the given terminals.
	 */
	public Object[] getEdgesBetween(Object source, Object target)
	{
		return getEdgesBetween(source, target, false);
	}

	/**
	 * Returns the edges between the given source and target. This takes into
	 * account collapsed and invisible cells and returns the connected edges
	 * as displayed on the screen.
	 *
	 * @param source
	 * @param target
	 * @param directed
	 * @return Returns all edges between the given terminals.
	 */
	public Object[] getEdgesBetween(Object source, Object target,
			boolean directed)
	{
		Object[] edges = getEdges(source);
		List result = new ArrayList(edges.length);

		// Checks if the edge is connected to the correct
		// cell and returns the first match
		for (int i = 0; i < edges.length; i++)
		{
			Object trg = view.getVisibleTerminal(edges[i], false);

			if (trg == target
					|| (!directed && view.getVisibleTerminal(edges[i], true) == target))
			{
				result.add(edges[i]);
			}
		}

		return result.toArray();
	}

	/**
	 * Returns the children of the given parent that are contained in the
	 * halfpane from the given point (x0, y0) rightwards and downwards
	 * depending on rightHalfpane and bottomHalfpane.
	 *
	 * @param x0 X-coordinate of the origin.
	 * @param y0 Y-coordinate of the origin.
	 * @param parent <mxCell> whose children should be checked.
	 * @param rightHalfpane Boolean indicating if the cells in the right halfpane
	 * from the origin should be returned.
	 * @param bottomHalfpane Boolean indicating if the cells in the bottom halfpane
	 * from the origin should be returned.
	 * @return Returns the cells beyond the given halfpane.
	 */
	public Object[] getCellsBeyond(double x0, double y0, Object parent,
			boolean rightHalfpane, boolean bottomHalfpane)
	{
		if (parent == null)
		{
			parent = getDefaultParent();
		}

		int childCount = model.getChildCount(parent);
		List result = new ArrayList(childCount);

		if (rightHalfpane || bottomHalfpane)
		{

			if (parent != null)
			{
				for (int i = 0; i < childCount; i++)
				{
					Object child = model.getChildAt(parent, i);
					mxCellState state = view.getState(child);

					if (isCellVisible(child) && state != null)
					{
						if ((!rightHalfpane || state.getX() >= x0)
								&& (!bottomHalfpane || state.getY() >= y0))
						{
							result.add(child);
						}
					}
				}
			}
		}

		return result.toArray();
	}

	/**
	 * Returns all visible children in the given parent which do not have
	 * incoming edges. If the result is empty then the with the greatest
	 * difference between incoming and outgoing edges is returned. This
	 * takes into account edges that are being promoted to the given
	 * root due to invisible children or collapsed cells.
	 *
	 * @param parent Cell whose children should be checked.
	 * @return Array of tree roots in parent.
	 */
	public Object[] findTreeRoots(Object parent)
	{
		return findTreeRoots(parent, false);
	}

	/**
	 * Returns all visible children in the given parent which do not have
	 * incoming edges. If the result is empty then the children with the
	 * maximum difference between incoming and outgoing edges are returned.
	 * This takes into account edges that are being promoted to the given
	 * root due to invisible children or collapsed cells.
	 *
	 * @param parent Cell whose children should be checked.
	 * @param isolate Specifies if edges should be ignored if the opposite
	 * end is not a child of the given parent cell.
	 * @return Array of tree roots in parent.
	 */
	public Object[] findTreeRoots(Object parent, boolean isolate)
	{
		return findTreeRoots(parent, isolate, false);
	}

	/**
	 * Returns all visible children in the given parent which do not have
	 * incoming edges. If the result is empty then the children with the
	 * maximum difference between incoming and outgoing edges are returned.
	 * This takes into account edges that are being promoted to the given
	 * root due to invisible children or collapsed cells.
	 *
	 * @param parent Cell whose children should be checked.
	 * @param isolate Specifies if edges should be ignored if the opposite
	 * end is not a child of the given parent cell.
	 * @param invert Specifies if outgoing or incoming edges should be counted
	 * for a tree root. If false then outgoing edges will be counted.
	 * @return Array of tree roots in parent.
	 */
	public Object[] findTreeRoots(Object parent, boolean isolate, boolean invert)
	{
		List roots = new ArrayList();

		if (parent != null)
		{
			int childCount = model.getChildCount(parent);
			Object best = null;
			int maxDiff = 0;

			for (int i = 0; i < childCount; i++)
			{
				Object cell = model.getChildAt(parent, i);

				if (model.isVertex(cell) && isCellVisible(cell))
				{
					Object[] conns = getConnections(cell, (isolate) ? parent
							: null);
					int fanOut = 0;
					int fanIn = 0;

					for (int j = 0; j < conns.length; j++)
					{
						Object src = view.getVisibleTerminal(conns[j], true);

						if (src == cell)
						{
							fanOut++;
						}
						else
						{
							fanIn++;
						}
					}

					if ((invert && fanOut == 0 && fanIn > 0)
							|| (!invert && fanIn == 0 && fanOut > 0))
					{
						roots.add(cell);
					}

					int diff = (invert) ? fanIn - fanOut : fanOut - fanIn;

					if (diff > maxDiff)
					{
						maxDiff = diff;
						best = cell;
					}
				}
			}

			if (roots.isEmpty() && best != null)
			{
				roots.add(best);
			}
		}

		return roots.toArray();
	}

	/**
	 *
	 * @param vertex
	 * @param directed
	 * @param visitor
	 */
	public void traverse(Object vertex, boolean directed, mxICellVisitor visitor)
	{
		traverse(vertex, directed, visitor, null, null);
	}

	/**
	 * Traverses the (directed) graph invoking the given function for each
	 * visited vertex and edge. The function is invoked with the current vertex
	 * and the incoming edge as a parameter. This implementation makes sure
	 * each vertex is only visited once. The function may return false if the
	 * traversal should stop at the given vertex.
	 *
	 * @param vertex <mxCell> that represents the vertex where the traversal starts.
	 * @param directed Optional boolean indicating if edges should only be traversed
	 * from source to target. Default is true.
	 * @param visitor Visitor that takes the current vertex and the incoming edge.
	 * The traversal stops if the function returns false.
	 * @param edge Optional <mxCell> that represents the incoming edge. This is
	 * null for the first step of the traversal.
	 * @param visited Optional array of cell paths for the visited cells.
	 */
	public void traverse(Object vertex, boolean directed,
			mxICellVisitor visitor, Object edge, Set visited)
	{
		if (vertex != null && visitor != null)
		{
			if (visited == null)
			{
				visited = new HashSet();
			}

			if (!visited.contains(vertex))
			{
				visited.add(vertex);

				if (visitor.visit(vertex, edge))
				{
					int edgeCount = model.getEdgeCount(vertex);

					if (edgeCount > 0)
					{
						for (int i = 0; i < edgeCount; i++)
						{
							Object e = model.getEdgeAt(vertex, i);
							boolean isSource = model.getTerminal(e, true) == vertex;

							if (!directed || isSource)
							{
								Object next = model.getTerminal(e, !isSource);
								traverse(next, directed, visitor, e, visited);
							}
						}
					}
				}
			}
		}
	}

	//
	// Selection
	//

	/**
	 *
	 */
	public mxGraphSelectionModel getSelectionModel()
	{
		return selectionModel;
	}

	/**
	 *
	 */
	public int getSelectionCount()
	{
		return selectionModel.size();
	}

	/**
	 *
	 * @param cell
	 * @return Returns true if the given cell is selected.
	 */
	public boolean isSelected(Object cell)
	{
		return selectionModel.isSelected(cell);
	}

	/**
	 *
	 * @return Returns true if the selection is empty.
	 */
	public boolean isSelectionEmpty()
	{
		return selectionModel.isEmpty();
	}

	/**
	 *
	 */
	public void clearSelection()
	{
		selectionModel.clear();
	}

	/**
	 *
	 * @return Returns the selection cell.
	 */
	public Object getSelectionCell()
	{
		return selectionModel.getCell();
	}

	/**
	 *
	 * @param cell
	 */
	public void setSelectionCell(Object cell)
	{
		selectionModel.setCell(cell);
	}

	/**
	 *
	 * @return Returns the selection cells.
	 */
	public Object[] getSelectionCells()
	{
		return selectionModel.getCells();
	}

	/**
	 *
	 */
	public void setSelectionCells(Object[] cells)
	{
		selectionModel.setCells(cells);
	}

	/**
	 *
	 * @param cells
	 */
	public void setSelectionCells(Collection cells)
	{
		if (cells != null)
		{
			setSelectionCells(cells.toArray());
		}
	}

	/**
	 *
	 */
	public void addSelectionCell(Object cell)
	{
		selectionModel.addCell(cell);
	}

	/**
	 *
	 */
	public void addSelectionCells(Object[] cells)
	{
		selectionModel.addCells(cells);
	}

	/**
	 *
	 */
	public void removeSelectionCell(Object cell)
	{
		selectionModel.removeCell(cell);
	}

	/**
	 *
	 */
	public void removeSelectionCells(Object[] cells)
	{
		selectionModel.removeCells(cells);
	}

	/**
	 * Selects the next cell.
	 */
	public void selectNextCell()
	{
		selectCell(true, false, false);
	}

	/**
	 * Selects the previous cell.
	 */
	public void selectPreviousCell()
	{
        selectCell(false, false, false);
	}

	/**
	 * Selects the parent cell.
	 */
	public void selectParentCell()
	{
		selectCell(false, true, false);
	}

	/**
	 * Selects the first child cell.
	 */
	public void selectChildCell()
	{
		selectCell(false, false, true);
	}

	/**
	 * Selects the next, parent, first child or previous cell, if all arguments
	 * are false.
	 *
	 * @param isNext
	 * @param isParent
	 * @param isChild
	 */
	public void selectCell(boolean isNext, boolean isParent, boolean isChild)
	{
		Object cell = getSelectionCell();

		if (getSelectionCount() > 1)
		{
			clearSelection();
		}

		Object parent = (cell != null) ? model.getParent(cell)
				: getDefaultParent();
		int childCount = model.getChildCount(parent);

		if (cell == null && childCount > 0)
		{
			Object child = model.getChildAt(parent, 0);
			setSelectionCell(child);
		}
		else if ((cell == null || isParent) && view.getState(parent) != null
				&& model.getGeometry(parent) != null)
		{
			if (getCurrentRoot() != parent)
			{
				setSelectionCell(parent);
			}
		}
		else if (cell != null && isChild)
		{
			int tmp = model.getChildCount(cell);

			if (tmp > 0)
			{
				Object child = model.getChildAt(cell, 0);
				setSelectionCell(child);
			}
		}
		else if (childCount > 0)
		{
			int i = ((mxICell) parent).getIndex((mxICell) cell);

			if (isNext)
			{
				i++;
				setSelectionCell(model.getChildAt(parent, i % childCount));
			}
			else
			{
				i--;
				int index = (i < 0) ? childCount - 1 : i;
				setSelectionCell(model.getChildAt(parent, index));
			}
		}
	}

	/**
	 * Selects all vertices inside the default parent.
	 */
	public void selectVertices()
	{
		selectVertices(null);
	}

	/**
	 * Selects all vertices inside the given parent or the default parent
	 * if no parent is given.
	 */
	public void selectVertices(Object parent)
	{
		selectCells(true, false, parent);
	}

	/**
	 * Selects all vertices inside the default parent.
	 */
	public void selectEdges()
	{
		selectEdges(null);
	}

	/**
	 * Selects all vertices inside the given parent or the default parent
	 * if no parent is given.
	 */
	public void selectEdges(Object parent)
	{
		selectCells(false, true, parent);
	}

	/**
	 * Selects all vertices and/or edges depending on the given boolean
	 * arguments recursively, starting at the default parent. Use
	 * <code>selectAll</code> to select all cells.
	 *
	 * @param vertices Boolean indicating if vertices should be selected.
	 * @param edges Boolean indicating if edges should be selected.
	 */
	public void selectCells(boolean vertices, boolean edges)
	{
		selectCells(vertices, edges, null);
	}

	/**
	 * Selects all vertices and/or edges depending on the given boolean
	 * arguments recursively, starting at the given parent or the default
	 * parent if no parent is specified. Use <code>selectAll</code> to select
	 * all cells.
	 *
	 * @param vertices Boolean indicating if vertices should be selected.
	 * @param edges Boolean indicating if edges should be selected.
	 * @param parent Optional cell that acts as the root of the recursion.
	 * Default is <code>defaultParent</code>.
	 */
	public void selectCells(final boolean vertices, final boolean edges,
			Object parent)
	{
		if (parent == null)
		{
			parent = getDefaultParent();
		}

		Collection cells = mxGraphModel.filterDescandants(getModel(),
				new mxGraphModel.Filter()
				{

					/**
					 *
					 */
					public boolean filter(Object cell)
					{
						return view.getState(cell) != null
								&& model.getChildCount(cell) == 0
								&& ((model.isVertex(cell) && vertices) || (model
										.isEdge(cell) && edges));
					}

				});
		setSelectionCells(cells);
	}

	/**
	 *
	 */
	public void selectAll()
	{
		selectAll(null);
	}

	/**
	 * Selects all children of the given parent cell or the children of the
	 * default parent if no parent is specified. To select leaf vertices and/or
	 * edges use <selectCells>.
	 *
	 * @param parent  Optional <mxCell> whose children should be selected.
	 * Default is <defaultParent>.
	 */
	public void selectAll(Object parent)
	{
		if (parent == null)
		{
			parent = getDefaultParent();
		}

		Object[] children = mxGraphModel.getChildren(model, parent);

		if (children != null)
		{
			setSelectionCells(children);
		}
	}

	//
	// Images and drawing
	//

	/**
	 * Draws the graph onto the given canvas.
	 *
	 * @param canvas Canvas onto which the graph should be drawn.
	 */
	public void draw(mxICanvas canvas)
	{
		drawCell(canvas, getModel().getRoot());
	}

	/**
	 * Draws the given cell and its descendants onto the specified canvas.
	 *
	 * @param canvas Canvas onto which the cell should be drawn.
	 * @param cell Cell that should be drawn onto the canvas.
	 */
	public void drawCell(mxICanvas canvas, Object cell)
	{
		drawStateWithLabel(canvas, getView().getState(cell), getLabel(cell));

		// Draws the children on top of their parent
		int childCount = model.getChildCount(cell);

		for (int i = 0; i < childCount; i++)
		{
			Object child = model.getChildAt(cell, i);
			drawCell(canvas, child);
		}
	}

	/**
	 * Draws the given cell and label onto the specified canvas. No
	 * children or descendants are painted here. This method invokes
	 * cellDrawn after the cell, but not its descendants have been
	 * painted.
	 *
	 * @param canvas Canvas onto which the cell should be drawn.
	 * @param cell Cell that should be drawn onto the canvas.
	 */
	public void drawStateWithLabel(mxICanvas canvas, mxCellState state,
			String label)
	{
		Object cell = (state != null) ? state.getCell() : null;

		if (cell != null && cell != view.getCurrentRoot()
				&& cell != model.getRoot())
		{
			Object obj = null;
			Object lab = null;

			int x = (int) Math.round(state.getX());
			int y = (int) Math.round(state.getY());
			int w = (int) Math.round(state.getWidth() - x + state.getX());
			int h = (int) Math.round(state.getHeight() - y + state.getY());

			if (model.isVertex(cell))
			{
				obj = canvas.drawVertex(x, y, w, h, state.getStyle());
			}
			else if (model.isEdge(cell))
			{
				obj = canvas.drawEdge(state.getAbsolutePoints(), state
						.getStyle());
			}

			// Holds the current clipping region in case the label will
			// be clipped
			Shape clip = null;
			Rectangle newClip = state.getRectangle();

			// Indirection for image canvas that contains a graphics canvas
			mxICanvas clippedCanvas = (isLabelClipped(state.getCell())) ? canvas
					: null;

			if (clippedCanvas instanceof mxImageCanvas)
			{
				clippedCanvas = ((mxImageCanvas) clippedCanvas)
						.getGraphicsCanvas();
				Point pt = ((mxImageCanvas) canvas).getTranslate();
				// TODO: Shift newClip to match the image offset
				//newClip.translate(-pt.x, -pt.y);
			}

			if (clippedCanvas instanceof mxGraphics2DCanvas)
			{
				Graphics g = ((mxGraphics2DCanvas) clippedCanvas).getGraphics();
				clip = g.getClip();
				g.setClip(newClip);
			}

			mxRectangle bounds = state.getLabelBounds();
 
			if (label != null && bounds != null)
			{
				x = (int) Math.round(bounds.getX());
				y = (int) Math.round(bounds.getY());
				w = (int) Math.round(bounds.getWidth() - x + bounds.getX());
				h = (int) Math.round(bounds.getHeight() - y + bounds.getY());
                //JTS
                //lab = canvas.drawLabel(label, x, y, w, h, state.getStyle(),	isHtmlLabel(cell));
                String shortLabel = getAbbreviation(label, ((mxCell)cell).getStyle());
                
                lab = canvas.drawLabel(shortLabel, x, y, w, h, state.getStyle(),	isHtmlLabel(cell));
                updateLabelBounds(state, shortLabel);
                //jts

			}

			// Restores the previous clipping region
			if (clippedCanvas instanceof mxGraphics2DCanvas)
			{
				((mxGraphics2DCanvas) clippedCanvas).getGraphics()
						.setClip(clip);
			}

			// Invokes the cellDrawn callback with the object which was created
			// by the canvas to represent the cell graphically
			if (obj != null)
			{
				cellDrawn(cell, obj, lab);
			}
		}
	}

    //JTS
    /** Returns the abbreviated form of the cell value (to be used as a label
     * when cell label is drawn). If abbreviated form cannot be computed, the 
     * label provided is simply returned.
     *
     * @param cell
     */
   String getAbbreviation(String label, String style)
   {    
    	
    	// allow empty triggers
    	if(label.length() == 0)
    	{
    	   return label;
    	}
    	
    	// assume new triggers (from pulling from a state) is a simple transition
    	if(style == null)
    	{
    		style = "simpleTransition";
    	}
    	
    	if( style.contains("internalTransition"))
    	{
    	   try {
    	      List<mxInternalTransition> list = mxInternalTransition.parseValueAttrib( label );
    		// pseudo start state transitions
    		if( list == null ) 
    		  return label;
			    
    		StringBuffer strBuf = new StringBuffer();  
			
    		for( int ii=0; ii<list.size(); ii++ ) {
    			mxInternalTransition tr = list.get(ii);
    			strBuf.append( formatTransition(tr.name, tr.params ) );
    		}
			  
    		return strBuf.toString();
         }
    	  catch( ParseException pe ) {
    			pe.printStackTrace();
         }
    		
         return label;	
      	}
	else if( style.contains("simpleTransition"))
	{
         try {
            List<mxSimpleTransition> list = mxSimpleTransition.parseValueAttrib( label );
            	// pseudo start state transitions
            	if( list == null ) 
            		return label;
                
            	StringBuffer strBuf = new StringBuffer();  

            	for( int ii=0; ii<list.size(); ii++ ) {
            	  mxSimpleTransition tr = list.get(ii);
            	  strBuf.append( formatTransition(tr.name, tr.params ) );
            	}
              
            	return strBuf.toString();
         } catch( ParseException pe ) {
            	   pe.printStackTrace();
         }
            
         return label;   
      }
	else if( style.contains("pushTransition")) 
	{
	   try {
	      List<mxPushTransition> list = mxPushTransition.parseValueAttrib( label );
		// pseudo start state transitions
		if( list == null ) 
		   return label;
                
		StringBuffer strBuf = new StringBuffer();  

		for( int ii=0; ii<list.size(); ii++ ) {
		   mxPushTransition tr = list.get(ii);
		   strBuf.append( formatTransition(tr.name, tr.params ) );
		}
              
		return strBuf.toString();
          } catch( ParseException pe ) {
		pe.printStackTrace();
          }
			 
	    return label;
	}
	else if( style.contains("popTransition")) 
	{
	   try {
	      List<mxPopTransition> list = mxPopTransition.parseValueAttrib( label );
		// pseudo start state transitions
		if( list == null ) 
		   return label;
                
	      StringBuffer strBuf = new StringBuffer();  

		for( int ii=0; ii<list.size(); ii++ ) {
		   mxPopTransition tr = list.get(ii);
		   strBuf.append( formatTransition(tr.name, tr.params ) );
		}
              
		return strBuf.toString();
         } catch( ParseException pe ) {
	     pe.printStackTrace();
         } 
	  
	  return label;
     } 
     else if( style.contains("defaultInternalTransition"))
     {
        try {
	     List<mxDefaultInternalTransition> list = mxDefaultInternalTransition.parseValueAttrib( label );
	     // pseudo start state transitions
	     if( list == null ) 
		 return label;
                
	     StringBuffer strBuf = new StringBuffer();  

            for( int ii=0; ii<list.size(); ii++ ) {
	         mxDefaultInternalTransition tr = list.get(ii);
	         strBuf.append( formatDefaultTransition( tr.guard ) );
	      }
              
	     return strBuf.toString();
        } catch( ParseException pe ) {
		pe.printStackTrace();
        } 
	  
	  return label;
     }
     else if( style.contains("defaultSimpleTransition"))
     {
        try {
           List<mxDefaultSimpleTransition> list = mxDefaultSimpleTransition.parseValueAttrib( label );
           // pseudo start state transitions
           if( list == null ) 
	    return label;
                
	 StringBuffer strBuf = new StringBuffer();  

	 for( int ii=0; ii<list.size(); ii++ ) {
	    mxDefaultSimpleTransition tr = list.get(ii);
	    strBuf.append( formatDefaultTransition( tr.guard ) );
	 }
              
	 return strBuf.toString();
        }
        catch( ParseException pe ) {
           pe.printStackTrace();
        } 

        return label;
     }
     else if( style.contains("defaultPushTransition"))
     {
        try {
           List<mxDefaultPushTransition> list = mxDefaultPushTransition.parseValueAttrib( label );
           // pseudo start state transitions
           if( list == null ) 
              return label;
                
           StringBuffer strBuf = new StringBuffer();  

           for( int ii=0; ii<list.size(); ii++ )  {
              mxDefaultPushTransition tr = list.get(ii);
              strBuf.append( formatDefaultTransition( tr.guard ) );
           }
              
           return strBuf.toString();
        } catch( ParseException pe ) {
           pe.printStackTrace();
        }
        
        return label;
     }
     else if( style.contains("defaultPopTransition")) {
        try {
           List<mxDefaultPopTransition> list = mxDefaultPopTransition.parseValueAttrib( label );
           // pseudo start state transitions
           if( list == null ) 
              return label;
                
           StringBuffer strBuf = new StringBuffer();  

           for( int ii=0; ii<list.size(); ii++ ) {
              mxDefaultPopTransition tr = list.get(ii);
              strBuf.append( formatDefaultTransition( tr.guard ) );
           }
              
           return strBuf.toString();
        } catch( ParseException pe ) {
           pe.printStackTrace();
        } 
        
        return label;
     } 
     else
     {
        return label;
     }
  }
       
       private String formatTransition( String name, List<mxParameter> params ) {
         StringBuffer strBuf = new StringBuffer();  
     
          strBuf.append( removeNamespace( name ) + "(" );
          for( int ii=0; ii<params.size(); ii++ ) {
            strBuf.append( removeNamespace( params.get(ii).type ) );
            if( params.size() > 1 & ii < ( params.size() -1) )
              strBuf.append( ", " );
          }
          strBuf.append( ");\r\n" );
          
          return strBuf.toString();
       }
       
       private String formatDefaultTransition( String guard ) {
         return "["+guard+"];\r\n";
       }
       
       private String removeNamespace( String name ) {
         int index = name.lastIndexOf(".");
         if( index > 0 )
            return name.substring( index+1, name.length() );
         else
           return name;
       }
       
       private void updateLabelBounds(mxCellState state, String label) {
         Object cell = state.getCell();
         Hashtable style = state.getStyle();
	  

	   mxRectangle size = mxUtils.getLabelSize(label, style, isHtmlLabel(cell));
	   mxPoint offset = state.getAbsoluteOffset();

		double x = offset.getX();
		double y = offset.getY();
		double width = 0;
		double height = 0;

		if (getModel().isEdge(cell))
		{
			if( size.getWidth() < 300 ) size.setWidth( 300 );
			state.setLabelBounds(mxUtils.getScaledLabelBounds(x, y, size, width,
				height, style, view.getScale()));
		}
       }
      //jts

	/**
	 * Called when a cell has been painted as the specified object, typically a
	 * DOM node that represents the given cell graphically in a document.
	 */
	protected void cellDrawn(Object cell, Object element, Object labelElement)
	{
		if (element instanceof Element)
		{
			String link = getLinkForCell(cell);

			if (link != null)
			{
				String title = getToolTipForCell(cell);
				Element elem = (Element) element;

				if (elem.getNodeName().startsWith("v:"))
				{
					elem.setAttribute("href", link.toString());

					if (title != null)
					{
						elem.setAttribute("title", title);
					}
				}
				else if (elem.getOwnerDocument().getElementsByTagName("svg")
						.getLength() > 0)
				{
					Element xlink = elem.getOwnerDocument().createElement("a");
					xlink.setAttribute("xlink:href", link.toString());

					elem.getParentNode().replaceChild(xlink, elem);
					xlink.appendChild(elem);

					if (title != null)
					{
						xlink.setAttribute("xlink:title", title);
					}

					elem = xlink;
				}
				else
				{
					Element a = elem.getOwnerDocument().createElement("a");
					a.setAttribute("href", link.toString());
					a.setAttribute("style", "text-decoration:none;");

					elem.getParentNode().replaceChild(a, elem);
					a.appendChild(elem);

					if (title != null)
					{
						a.setAttribute("title", title);
					}

					elem = a;
				}

				String target = getTargetForCell(cell);

				if (target != null)
				{
					elem.setAttribute("target", target);
				}
			}
		}
	}

	/**
	 * Returns the hyperlink to be used for the given cell.
	 */
	protected String getLinkForCell(Object cell)
	{
		return null;
	}

	/**
	 * Returns the hyperlink to be used for the given cell.
	 */
	protected String getTargetForCell(Object cell)
	{
		return null;
	}

	//
	// Redirected to change support
	//

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		changeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener)
	{
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		changeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener)
	{
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	/**
	 * Prints the version number on the console.
	 */
	public static void main(String[] args)
	{
		System.out.println("mxGraph version \"" + VERSION + "\"");
	}

}
