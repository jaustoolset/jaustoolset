/**
 * $Id: mxGraphHandler.java,v 1.31 2009/03/05 15:39:44 gaudenz Exp $
 * Copyright (c) 2008, Gaudenz Alder
 * 
 * Known issue: Drag image size depends on the initial position and may sometimes
 * not align with the grid when dragging. This is because the rounding of the width
 * and height at the initial position may be different than that at the current
 * position as the left and bottom side of the shape must align to the grid lines.
 */
package com.mxgraph.swing.handler;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceAdapter;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TooManyListenersException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.swing.util.mxMouseControl;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphSelectionModel;

public class mxGraphHandler extends mxMouseControl implements
		DropTargetListener
{

	/**
	 * 
	 */
	public static Cursor DEFAULT_CURSOR = new Cursor(Cursor.MOVE_CURSOR);

	/**
	 * Defines the default value for maxHandlers. Default is 100.
	 */
	public static int DEFAULT_MAX_HANDLERS = 100;

	/**
	 * Reference to the enclosing graph component.
	 */
	protected mxGraphComponent graphComponent;

	/**
	 * Specifies if cloning by control-drag is enabled. Default is true.
	 */
	protected boolean cloneEnabled = true;

	/**
	 * Specifies if an image should be used for preview. Default is true.
	 */
	protected boolean imagePreview = true;

	/**
	 * Defines the maximum number of handlers to paint individually.
	 * Default is DEFAULT_MAX_HANDLES.
	 */
	protected int maxHandlers = DEFAULT_MAX_HANDLERS;

	/**
	 * Specifies if the preview should be centered around the mouse cursor if there
	 * was no mouse click to define the offset within the shape (eg. drag from
	 * external source). Default is true.
	 */
	protected boolean centerPreview = true;

	/**
	 * Specifies if this handler should be painted on top of all other components.
	 * Default is true.
	 */
	protected boolean keepOnTop = true;

	/**
	 * Maps from cells to handlers in the order of the selection cells.
	 */
	protected transient Map handlers = new LinkedHashMap();

	/**
	 * Holds the cells that are being moved by this handler.
	 */
	protected transient Object[] cells;

	/**
	 * Holds the component that is being used as a preview.
	 */
	protected transient JComponent preview;

	/**
	 * Holds the image that is being used for the preview.
	 */
	protected transient ImageIcon dragImage;

	/**
	 * Holds the start location of the mouse gesture.
	 */
	protected transient Point first;

	/**
	 * 
	 */
	protected transient Object cell;

	/**
	 * 
	 */
	protected transient Object initialCell;

	/**
	 * 
	 */
	protected transient Object[] dragCells;

	/**
	 * 
	 */
	protected transient mxCellMarker marker;

	/**
	 * 
	 */
	protected transient boolean canImport;

	/**
	 * Scaled, translated bounds of the selection cells.
	 */
	protected transient mxRectangle cellBounds;

	/**
	 * Scaled, translated bounding box of the selection cells.
	 */
	protected transient mxRectangle bbox;

	/**
	 * 
	 */
	protected transient mxRectangle previewBounds;

	/**
	 * 
	 */
	protected transient mxRectangle previewBbox;

	/**
	 * Unscaled, untranslated bounding box of the selection cells.
	 */
	protected transient mxRectangle transferBounds;

	/**
	 * Workaround for alt-key-state not correct in mouseReleased. Note: State
	 * of the alt-key is not available during drag-and-drop.
	 */
	private transient boolean gridEnabledEvent = false;

	/**
	 * Workaround for shift-key-state not correct in mouseReleased.
	 */
	protected transient boolean constrainedEvent = false;

	/**
	 * 
	 */
	protected transient mxIEventListener refreshHandler = new mxIEventListener()
	{
		public void invoke(Object source, Object[] args)
		{
			refresh();
		}
	};

	/**
	 * 
	 * @param graphComponent
	 */
	public mxGraphHandler(final mxGraphComponent graphComponent)
	{
		this.graphComponent = graphComponent;

		marker = new mxCellMarker(graphComponent, Color.BLUE)
		{
			/**
			 * 
			 */
			public boolean isEnabled()
			{
				return graphComponent.getGraph().isDropEnabled();
			}

			/**
			 * 
			 */
			public Object getCell(MouseEvent e)
			{
				TransferHandler th = graphComponent.getTransferHandler();
				boolean isLocal = th instanceof mxGraphTransferHandler
						&& ((mxGraphTransferHandler) th).isLocalDrag();

				Object cell = super.getCell(e);
				Object[] cells = (isLocal) ? handlers.keySet().toArray()
						: dragCells;
				mxGraph graph = graphComponent.getGraph();
				cell = graph.getDropTarget(cells, e.getPoint(), cell);
				boolean clone = graphComponent.isCloneEvent(e) && cloneEnabled;

				if (isLocal
						&& cell != null
						&& cells.length > 0
						&& !clone
						&& (handlers.keySet().contains(cell) || graph
								.getModel().getParent(cells[0]) == cell))
				{
					cell = null;
				}

				return cell;
			}

		};

		// Swimlane content area will not be transparent drop targets
		marker.setSwimlaneContentEnabled(true);

		// Adds component for rendering the handles (preview is separate)
		graphComponent.getGraphControl().add(this, 0);

		// Listens to all mouse events on the rendering control
		graphComponent.getGraphControl().addMouseListener(this);
		graphComponent.getGraphControl().addMouseMotionListener(this);

		// Redirects events from component to rendering control so
		// that the event handling order is maintained if other controls
		// such as overlays are added to the component hierarchy and
		// consume events before they reach the rendering control
		MouseRedirector redirector = new MouseRedirector();
		addMouseMotionListener(redirector);
		addMouseListener(redirector);

		// Refreshes the handles after any changes
		graphComponent.getGraph().getSelectionModel().addListener(
				mxGraphSelectionModel.EVENT_CHANGE, refreshHandler);
		graphComponent.getGraph().addListener(mxGraph.EVENT_REPAINT,
				refreshHandler);

		// Refreshes the handles if moveVertexLabels or moveEdgeLabels changes
		graphComponent.getGraph().addPropertyChangeListener(
				new PropertyChangeListener()
				{

					/*
					 * (non-Javadoc)
					 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
					 */
					public void propertyChange(PropertyChangeEvent evt)
					{
						if (evt.getPropertyName().equals("vertexLabelsMovable")
								|| evt.getPropertyName().equals(
										"edgeLabelsMovable"))
						{
							refresh();
						}
					}

				});

		// Drag target creates preview image
		DragGestureListener dragGestureListener = new DragGestureListener()
		{
			public void dragGestureRecognized(DragGestureEvent e)
			{
				if (graphComponent.isDragEnabled() && preview != null
						&& first != null)
				{
					final TransferHandler th = graphComponent
							.getTransferHandler();

					if (th instanceof mxGraphTransferHandler)
					{
						final mxGraphTransferable t = (mxGraphTransferable) ((mxGraphTransferHandler) th)
								.createTransferable(graphComponent);

						if (t != null)
						{
							e.startDrag(null, mxConstants.EMPTY_IMAGE,
									new Point(), t, new DragSourceAdapter()
									{

										/**
										 * 
										 */
										public void dragDropEnd(
												DragSourceDropEvent dsde)
										{
											((mxGraphTransferHandler) th)
													.exportDone(
															graphComponent,
															t,
															TransferHandler.NONE);
										}
									});
						}
					}
				}
			}
		};

		DragSource dragSource = new DragSource();
		DragGestureRecognizer dgr = dragSource
				.createDefaultDragGestureRecognizer(graphComponent
						.getGraphControl(), DnDConstants.ACTION_COPY_OR_MOVE,
						dragGestureListener);

		// Listens to dropped graph cells
		DropTarget dropTarget = graphComponent.getDropTarget();

		try
		{
			if (dropTarget != null)
			{
				dropTarget.addDropTargetListener(this);
			}
		}
		catch (TooManyListenersException tmle)
		{
			// should not happen... swing drop target is multicast
		}

		setVisible(false);
	}

	/**
	 * 
	 */
	public mxGraphComponent getGraphComponent()
	{
		return graphComponent;
	}

	/**
	 * Returns the component that is used for drawing the preview.
	 */
	public JComponent getPreview()
	{
		return preview;
	}

	/**
	 * 
	 */
	public void setKeepOnTop(boolean keepOnTop)
	{
		this.keepOnTop = keepOnTop;
	}

	/**
	 * 
	 */
	public boolean isKeepOnTop()
	{
		return keepOnTop;
	}

	/**
	 * 
	 */
	public void setCloneEnabled(boolean cloneEnabled)
	{
		this.cloneEnabled = cloneEnabled;
	}

	/**
	 * 
	 */
	public boolean isCloneEnabled()
	{
		return cloneEnabled;
	}

	/**
	 * 
	 */
	public void setImagePreview(boolean imagePreview)
	{
		this.imagePreview = imagePreview;
	}

	/**
	 * 
	 */
	public boolean isImagePreview()
	{
		return imagePreview;
	}

	/**
	 * 
	 */
	public void setCenterPreview(boolean centerPreview)
	{
		this.centerPreview = centerPreview;
	}

	/**
	 * 
	 */
	public boolean isCenterPreview()
	{
		return centerPreview;
	}

	/**
	 * 
	 */
	public int getMaxHandlers()
	{
		return maxHandlers;
	}

	/**
	 * 
	 */
	public void setMaxHandlers(int maxHandles)
	{
		this.maxHandlers = maxHandlers;
	}

	/**
	 * 
	 */
	protected void createPreview()
	{
		if (preview == null)
		{
			preview = new JPanel()
			{
				/**
				 * Paints the preview.
				 */
				public void paint(Graphics g)
				{
					if (dragImage != null)
					{
						// LATER: Clipping with mxUtils doesnt fix the problem
						// of the drawImage being painted over the scrollbars
						g.drawImage(dragImage.getImage(), 0, 0, dragImage
								.getIconWidth(), dragImage.getIconHeight(),
								this);
					}
					else if (!imagePreview)
					{
						super.paint(g);
					}
				}
			};

			preview.setOpaque(false);
			preview.setVisible(false);
			preview.setBorder(mxConstants.PREVIEW_BORDER);
			graphComponent.getGraphControl().add(preview, 0);
		}
	}

	/**
	 * 
	 */
	protected void updatePreviewBounds()
	{
		if (preview != null)
		{
			Rectangle bounds = getBounds();
			bounds.grow(-5, -5);
			preview.setBounds(bounds);
		}
	}

	/**
	 * 
	 */
	public void updateDragImage(Object[] cells)
	{
		Image img = mxCellRenderer.createBufferedImage(graphComponent
				.getGraph(), cells, graphComponent.getGraph().getView()
				.getScale(), null, graphComponent.isAntiAlias(), null,
				graphComponent.getCanvas());

		if (img != null)
		{
			dragImage = new ImageIcon(img);

			preview
					.setSize(dragImage.getIconWidth(), dragImage
							.getIconHeight());
			preview.getParent().setComponentZOrder(preview, 0);
		}
	}

	/**
	 * 
	 */
	protected void destroyPreview()
	{
		if (preview != null)
		{
			preview.setVisible(false);
			preview.getParent().remove(preview);
			dragImage = null;
			preview = null;
		}

		marker.reset();
	}

	/**
	 * 
	 */
	public void mouseMoved(MouseEvent e)
	{
		if (graphComponent.isEnabled() && isEnabled())
		{
			Iterator it = handlers.values().iterator();

			while (it.hasNext() && !e.isConsumed())
			{
				((mxCellHandler) it.next()).mouseMoved(e);
			}

			if (!e.isConsumed())
			{
				graphComponent.getGraphControl().setCursor(getCursor(e));
				e.consume();
			}
		}
	}

	/**
	 * 
	 */
	protected Cursor getCursor(MouseEvent e)
	{
		Object cell = graphComponent.getCellAt(e.getX(), e.getY(), false);
		Cursor cursor = null;

		if (cell != null)
		{
			if (graphComponent.isFoldingEnabled()
					&& graphComponent.hitFoldingIcon(cell, e.getX(), e.getY()))
			{
				cursor = new Cursor(Cursor.HAND_CURSOR);
			}
			else if (graphComponent.getGraph().isCellMovable(cell))
			{
				cursor = DEFAULT_CURSOR;
			}
		}

		return cursor;
	}

	/**
	 * 
	 */
	public void dragEnter(DropTargetDragEvent e)
	{
		JComponent component = getDropTarget(e);
		TransferHandler th = component.getTransferHandler();
		boolean isLocal = th instanceof mxGraphTransferHandler
				&& ((mxGraphTransferHandler) th).isLocalDrag();

		if (isLocal)
		{
			canImport = true;
		}
		else
		{
			canImport = graphComponent.isImportEnabled()
					&& th.canImport(component, e.getCurrentDataFlavors());
		}

		if (canImport)
		{
			e.acceptDrag(TransferHandler.COPY_OR_MOVE);
			transferBounds = null;
			createPreview();

			try
			{
				Transferable t = e.getTransferable();

				if (t.isDataFlavorSupported(mxGraphTransferable.dataFlavor))
				{
					mxGraphTransferable gt = (mxGraphTransferable) t
							.getTransferData(mxGraphTransferable.dataFlavor);
					dragCells = gt.getCells();

					if (gt.getBounds() != null)
					{
						mxGraph graph = graphComponent.getGraph();
						double scale = graph.getView().getScale();

						transferBounds = gt.getBounds();
						mxRectangle bounds = new mxRectangle(transferBounds);
						bounds.setWidth(bounds.getWidth() * scale);
						bounds.setHeight(bounds.getHeight() * scale);
						preview.setBounds(bounds.getRectangle());

						if (imagePreview)
						{
							// Does not render fixed cells for local preview
							// but ignores movable state for non-local previews
							if (isLocal)
							{
								updateDragImage(graph
										.getMovableCells(dragCells));
							}
							else
							{
								updateDragImage(graphComponent
										.getImportableCells(dragCells));
							}
						}

						preview.setVisible(true);
					}
				}
			}
			catch (Exception ex)
			{
				// do nothing
				ex.printStackTrace();
			}

		}
		else
		{
			e.rejectDrag();
		}
	}

	/**
	 * Redirects the tooltip handling of the JComponent to the graph
	 * component, which in turn may use getHandleToolTipText in this class to
	 * find a tooltip associated with a handle.
	 */
	public String getToolTipText(MouseEvent e)
	{
		return graphComponent.getGraphControl().getToolTipText(
				SwingUtilities.convertMouseEvent(e.getComponent(), e,
						graphComponent.getGraphControl()));
	}

	/**
	 * Redirects a tooltip request from the graph component to the handles.
	 * This is called from the graph component in getToolTipText which in turn
	 * may be called from getToolTipText in this class.
	 */
	public String getHandleToolTipText(MouseEvent e)
	{
		String toolTip = null;
		Iterator it = handlers.values().iterator();

		while (it.hasNext() && toolTip == null)
		{
			toolTip = ((mxCellHandler) it.next()).getToolTipText(e);
		}

		return toolTip;
	}

	/**
	 * Dispatches the mousepressed event to the subhandles. This is
	 * called from the connection handler as subhandles have precedence
	 * over the connection handler.
	 */
	public void dispatchMousePressed(MouseEvent e)
	{
		Iterator it = handlers.values().iterator();

		while (it.hasNext() && !e.isConsumed())
		{
			((mxCellHandler) it.next()).mousePressed(e);
		}
	}

	/**
	 * 
	 */
	public void mousePressed(MouseEvent e)
	{
		if (graphComponent.isEnabled() && isEnabled() && !e.isConsumed())
		{
			cell = graphComponent.getCellAt(e.getX(), e.getY(), false);
			initialCell = cell;

			if (cell != null)
			{
				if (!graphComponent.getGraph().isSelected(cell)
						&& !graphComponent.isToggleEvent(e))
				{
					graphComponent.selectCellForEvent(cell, e);
					cell = null;
				}

				// Starts move if the cell under the mouse is movable and/or any
				// cells of the selection are movable
				if (!e.isPopupTrigger())
				{
					start(e);
					e.consume();
				}
			}
			else if (e.isPopupTrigger())
			{
				graphComponent.getGraph().clearSelection();
			}
		}
	}

	/**
	 * 
	 */
	public void start(MouseEvent e)
	{
		first = e.getPoint();
		createPreview();
		updatePreviewBounds();
	}

	/**
	 * 
	 */
	public void dropActionChanged(DropTargetDragEvent e)
	{
		// do nothing
	}

	/**
	 * 
	 * @param e
	 */
	public void dragOver(DropTargetDragEvent e)
	{
		if (canImport)
		{
			mouseDragged(createEvent(e));
			mxGraphTransferHandler handler = getGraphTransferHandler(e);

			if (handler != null)
			{
				double scale = graphComponent.getGraph().getView().getScale();
				Point pt = SwingUtilities.convertPoint(graphComponent, e
						.getLocation(), graphComponent.getGraphControl());

				// Centers the preview image
				if (centerPreview && transferBounds != null)
				{
					pt.x -= Math.round((double) transferBounds.getWidth()
							* scale / 2);
					pt.y -= Math.round((double) transferBounds.getHeight()
							* scale / 2);
				}

				pt = graphComponent.snapScaledPoint(new mxPoint(pt)).getPoint();
				handler.setLocation(new Point(pt));

				// Shifts the preview so that overlapping parts do not
				// affect the centering
				if (transferBounds != null && dragImage != null)
				{
					int dx = (int) Math
							.round((dragImage.getIconWidth() - 2 - transferBounds
									.getWidth()
									* scale) / 2);
					int dy = (int) Math
							.round((dragImage.getIconHeight() - 2 - transferBounds
									.getHeight()
									* scale) / 2);
					pt.translate(-dx, -dy);
				}

				if (!handler.isLocalDrag())
				{
					preview.setLocation(pt.x, pt.y);
				}
			}
		}
		else
		{
			e.rejectDrag();
		}
	}

	/**
	 * 
	 */
	public Point convertPoint(Point pt)
	{
		pt = SwingUtilities.convertPoint(graphComponent, pt, graphComponent
				.getGraphControl());

		pt.x -= graphComponent.getHorizontalScrollBar().getValue();
		pt.y -= graphComponent.getVerticalScrollBar().getValue();

		return pt;
	}

	/**
	 * 
	 */
	public void mouseDragged(MouseEvent e)
	{
		// LATER: Check scrollborder, use scroll-increments, do not
		// scroll when over ruler dragging from library
		graphComponent.getGraphControl().scrollRectToVisible(
				new Rectangle(e.getPoint()));

		Iterator it = handlers.values().iterator();

		while (it.hasNext() && !e.isConsumed())
		{
			((mxCellHandler) it.next()).mouseDragged(e);
		}

		if (!e.isConsumed() && preview != null)
		{
			gridEnabledEvent = graphComponent.isGridEnabledEvent(e);
			constrainedEvent = graphComponent.isConstrainedEvent(e);

			if (constrainedEvent && first != null)
			{
				int x = e.getX();
				int y = e.getY();

				if (Math.abs(e.getX() - first.x) > Math.abs(e.getY() - first.y))
				{
					y = first.y;
				}
				else
				{
					x = first.x;
				}

				e = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e
						.getModifiers(), x, y, e.getClickCount(), e
						.isPopupTrigger(), e.getButton());
			}

			if (preview.isVisible())
			{
				marker.process(e);
			}
			else if (cell != null
					&& !graphComponent.getGraph().isSelected(cell)
					&& graphComponent.isToggleEvent(e))
			{
				graphComponent.selectCellForEvent(cell, e);
				cell = null;
			}

			if (first != null)
			{
				double dx = e.getX() - first.x;
				double dy = e.getY() - first.y;

				preview.setLocation(getPreviewLocation(e, gridEnabledEvent));

				if (!preview.isVisible()
						&& graphComponent.isSignificant(dx, dy))
				{
					if (imagePreview && dragImage == null
							&& !graphComponent.isDragEnabled())
					{
						updateDragImage(cells);
					}

					preview.setVisible(true);
				}

				e.consume();
			}
		}
	}

	/**
	 * 
	 */
	protected Point getPreviewLocation(MouseEvent e, boolean gridEnabled)
	{
		int x = 0;
		int y = 0;

		if (first != null && previewBounds != null)
		{
			mxGraph graph = graphComponent.getGraph();
			double scale = graph.getView().getScale();
			mxPoint trans = graph.getView().getTranslate();

			// LATER: Drag image _size_ depends on the initial position and may sometimes
			// not align with the grid when dragging. This is because the rounding of the width
			// and height at the initial position may be different than that at the current
			// position as the left and bottom side of the shape must align to the grid lines.
			// Only fix is a full repaint of the drag cells at each new mouse location.
			double dx = e.getX() - first.x;
			double dy = e.getY() - first.y;

			double dxg = ((previewBounds.getX() + dx) / scale) - trans.getX();
			double dyg = ((previewBounds.getY() + dy) / scale) - trans.getY();

			if (gridEnabled)
			{
				dxg = graph.snap(dxg);
				dyg = graph.snap(dyg);
			}

			x = (int) Math.round((dxg + trans.getX()) * scale)
					+ (int) Math.round(previewBbox.getX())
					- (int) Math.round(previewBounds.getX());
			y = (int) Math.round((dyg + trans.getY()) * scale)
					+ (int) Math.round(previewBbox.getY())
					- (int) Math.round(previewBounds.getY());
		}

		return new Point(x, y);
	}

	/**
	 * 
	 * @param e
	 */
	public void dragExit(DropTargetEvent e)
	{
		mxGraphTransferHandler handler = getGraphTransferHandler(e);

		if (handler != null)
		{
			handler.setLocation(null);
		}

		dragCells = null;
		destroyPreview();
	}

	/**
	 * 
	 * @param e
	 */
	public void drop(DropTargetDropEvent e)
	{
		if (canImport)
		{
			mxGraphTransferHandler handler = getGraphTransferHandler(e);
			MouseEvent event = createEvent(e);

			// Ignores the event in mouseReleased if it is
			// handled by the transfer handler as a drop
			if (handler != null && !handler.isLocalDrag())
			{
				event.consume();
			}

			mouseReleased(event);
		}
	}

	/**
	 * 
	 */
	public void mouseReleased(MouseEvent e)
	{
		if (graphComponent.isEnabled() && isEnabled())
		{
			mxGraph graph = graphComponent.getGraph();
			Iterator it = handlers.values().iterator();

			while (it.hasNext() && !e.isConsumed())
			{
				((mxCellHandler) it.next()).mouseReleased(e);
			}

			if (!e.isConsumed())
			{
				double dx = 0;
				double dy = 0;

				if (first != null && cellBounds != null)
				{
					double scale = graph.getView().getScale();
					mxPoint trans = graph.getView().getTranslate();

					// TODO: Simplify math below, this was copy pasted from
					// getPreviewLocation with the rounding removed
					dx = e.getX() - first.x;
					dy = e.getY() - first.y;

					double dxg = ((cellBounds.getX() + dx) / scale)
							- trans.getX();
					double dyg = ((cellBounds.getY() + dy) / scale)
							- trans.getY();

					if (gridEnabledEvent)
					{
						dxg = graph.snap(dxg);
						dyg = graph.snap(dyg);
					}

					double x = ((dxg + trans.getX()) * scale) + (bbox.getX())
							- (cellBounds.getX());
					double y = ((dyg + trans.getY()) * scale) + (bbox.getY())
							- (cellBounds.getY());

					dx = Math.round((x - bbox.getX()) / scale);
					dy = Math.round((y - bbox.getY()) / scale);
				}

				if (preview != null && preview.isVisible())
				{
					if (constrainedEvent)
					{
						if (Math.abs(dx) > Math.abs(dy))
						{
							dy = 0;
						}
						else
						{
							dx = 0;
						}
					}

					moveCells(cells, dx, dy, e);
					e.consume();
				}
				else if (first == null
						|| !graphComponent.isSignificant(e.getX() - first.x, e
								.getY()
								- first.y))
				{
					// Delayed handling of selection
					if (cell != null && !e.isPopupTrigger() && first != null)
					{
						graphComponent.selectCellForEvent(cell, e);
					}

					// Delayed folding for cell that was initially under the mouse
					if (graphComponent.isFoldingEnabled()
							&& graphComponent.hitFoldingIcon(initialCell, e
									.getX(), e.getY()))
					{
						fold(initialCell);
					}
					else
					{
						// Handles selection if no cell was initially under the mouse
						Object tmp = graphComponent.getCellAt(e.getX(), e
								.getY(), graphComponent
								.isSwimlaneSelectionEnabled());

						if (cell == null && first == null)
						{
							if (tmp == null)
							{
								graph.clearSelection();
							}
							else if (graph.isSwimlane(tmp)
									&& graphComponent.getCanvas()
											.hitSwimlaneContent(
													graphComponent,
													graph.getView().getState(
															tmp), e.getX(),
													e.getY()))
							{
								graphComponent.selectCellForEvent(tmp, e);
							}
						}

						if (graphComponent.isFoldingEnabled()
								&& graphComponent.hitFoldingIcon(tmp, e.getX(),
										e.getY()))
						{
							fold(tmp);
							e.consume();
						}
					}
				}
			}

			reset();
		}
	}

	/**
	 * 
	 */
	protected void fold(Object cell)
	{
		boolean collapse = !graphComponent.getGraph().isCellCollapsed(cell);
		graphComponent.getGraph().foldCells(collapse, false,
				new Object[] { cell });
	}

	/**
	 * 
	 */
	public void reset()
	{
		Iterator it = handlers.values().iterator();

		while (it.hasNext())
		{
			((mxCellHandler) it.next()).reset();
		}

		destroyPreview();
		initialCell = null;
		dragCells = null;
		first = null;
		cell = null;
	}

	/**
	 * 
	 * @param dx
	 * @param dy
	 * @param e
	 */
	protected void moveCells(Object[] cells, double dx, double dy, MouseEvent e)
	{
		mxGraph graph = graphComponent.getGraph();
		mxCellState targetState = marker.getValidState();
		Object target = (targetState != null) ? targetState.getCell() : null;
		boolean clone = e.isControlDown() && cloneEnabled;
		Object[] tmp = graph.moveCells(cells, dx, dy, clone, target, e
				.getPoint());

		if (clone && tmp.length == cells.length)
		{
			graph.setSelectionCells(tmp);
		}
		else
		{
			refresh();
		}
	}

	/**
	 * 
	 */
	public mxCellHandler getHandler(Object cell)
	{
		return (mxCellHandler) handlers.get(cell);
	}

	/**
	 * 
	 */
	public void refresh()
	{
		mxGraph graph = graphComponent.getGraph();

		// Creates a new map for the handlers and tries to
		// to reuse existing handlers from the old map
		Map oldHandlers = handlers;
		handlers = new LinkedHashMap();

		// Creates handles for all selection cells
		Object[] tmp = graph.getSelectionCells();
		boolean handlesVisible = tmp.length <= getMaxHandlers();
		Rectangle handleBounds = null;

		for (int i = 0; i < tmp.length; i++)
		{
			mxCellState state = graph.getView().getState(tmp[i]);

			if (state != null)
			{
				mxCellHandler handler = (mxCellHandler) oldHandlers.get(tmp[i]);

				if (handler != null)
				{
					handler.refresh(state);
				}
				else
				{
					handler = graphComponent.createHandler(state);
				}

				if (handler != null)
				{
					handler.setHandlesVisible(handlesVisible);
					handlers.put(tmp[i], handler);

					if (handleBounds == null)
					{
						handleBounds = handler.getBounds();
					}
					else
					{
						handleBounds.add(handler.getBounds());
					}
				}
			}
		}

		// Constructs an array with cells that are indeed movable
		cells = graph.getMovableCells(handlers.keySet().toArray());
		setVisible(!handlers.isEmpty());

		if (isVisible())
		{
			// Keeps this handler in the foreground
			if (isKeepOnTop())
			{
				getParent().setComponentZOrder(this, 0);
			}

			// Updates the size of the graph handler that is in
			// charge of painting all other handlers
			cellBounds = graph.getView().getBounds(tmp);
			bbox = graph.getView().getBoundingBox(tmp);

			previewBounds = graph.getView().getBounds(cells);
			previewBbox = graph.getView().getBoundingBox(cells);

			if (handleBounds != null)
			{
				handleBounds.grow(1, 1);
				setBounds(handleBounds);
			}
			else
			{
				setBounds(graphComponent.getViewport().getVisibleRect());
			}

			updatePreviewBounds();
			repaint();
		}
	}

	/**
	 * 
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.translate(-getX(), -getY());
		Iterator it = handlers.values().iterator();

		while (it.hasNext())
		{
			((mxCellHandler) it.next()).paint(g);
		}

		g.translate(getX(), getY());
	}

	/**
	 * 
	 */
	protected MouseEvent createEvent(DropTargetEvent e)
	{
		JComponent component = getDropTarget(e);
		Point location = null;
		int action = 0;

		if (e instanceof DropTargetDropEvent)
		{
			location = ((DropTargetDropEvent) e).getLocation();
			action = ((DropTargetDropEvent) e).getDropAction();
		}
		else if (e instanceof DropTargetDragEvent)
		{
			location = ((DropTargetDragEvent) e).getLocation();
			action = ((DropTargetDragEvent) e).getDropAction();
		}

		if (location != null)
		{
			location = convertPoint(location);
			Rectangle r = graphComponent.getViewport().getViewRect();
			location.translate(r.x, r.y);
		}

		// LATER: Fetch state of modifier keys from event or via global
		// key listener using Toolkit.getDefaultToolkit().addAWTEventListener(
		// new AWTEventListener() {...}, AWTEvent.KEY_EVENT_MASK). Problem
		// is the event does not contain the modifier keys and the global
		// handler is not called during drag and drop.
		int mod = (action == TransferHandler.COPY) ? InputEvent.CTRL_MASK : 0;

		return new MouseEvent(component, 0, System.currentTimeMillis(), mod,
				(int) location.getX(), (int) location.getY(), 1, false,
				MouseEvent.BUTTON1);
	}

	/**
	 * Helper method to return the component for a drop target event.
	 */
	protected static final mxGraphTransferHandler getGraphTransferHandler(
			DropTargetEvent e)
	{
		JComponent component = getDropTarget(e);
		TransferHandler transferHandler = component.getTransferHandler();

		if (transferHandler instanceof mxGraphTransferHandler)
		{
			return (mxGraphTransferHandler) transferHandler;
		}

		return null;
	}

	/**
	 * Helper method to return the component for a drop target event.
	 */
	protected static final JComponent getDropTarget(DropTargetEvent e)
	{
		return (JComponent) e.getDropTargetContext().getComponent();
	}

	/**
	 * 
	 */
	public class MouseRedirector implements MouseListener, MouseMotionListener
	{

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(MouseEvent e)
		{
			graphComponent.getGraphControl().dispatchEvent(
					SwingUtilities.convertMouseEvent(e.getComponent(), e,
							graphComponent.getGraphControl()));
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
		 */
		public void mouseEntered(MouseEvent e)
		{
			// Redirecting this would cause problems on the Mac
			// and is technically incorrect anyway
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
		 */
		public void mouseExited(MouseEvent e)
		{
			mouseClicked(e);
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
		 */
		public void mousePressed(MouseEvent e)
		{
			mouseClicked(e);
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
		 */
		public void mouseReleased(MouseEvent e)
		{
			mouseClicked(e);
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
		 */
		public void mouseDragged(MouseEvent e)
		{
			mouseClicked(e);
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
		 */
		public void mouseMoved(MouseEvent e)
		{
			mouseClicked(e);
		}

	}

}
