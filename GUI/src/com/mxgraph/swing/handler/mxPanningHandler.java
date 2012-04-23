/**
 * $Id: mxPanningHandler.java,v 1.3 2009/01/14 14:39:55 gaudenz Exp $
 * Copyright (c) 2008, Gaudenz Alder
 */
package com.mxgraph.swing.handler;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxMouseControl;

/**
 * 
 */
public class mxPanningHandler extends mxMouseControl
{

	/**
	 * 
	 */
	protected mxGraphComponent graphComponent;

	/**
	 * 
	 */
	protected transient Point start;

	/**
	 * 
	 * @param graphComponent
	 */
	public mxPanningHandler(mxGraphComponent graphComponent)
	{
		this.graphComponent = graphComponent;

		graphComponent.getGraphControl().add(this);
		graphComponent.getGraphControl().addMouseListener(this);
		graphComponent.getGraphControl().addMouseMotionListener(this);
	}

	/**
	 * 
	 */
	public void mousePressed(MouseEvent e)
	{
		if (isEnabled() && !e.isConsumed() && graphComponent.isPanningEvent(e)
				&& !e.isPopupTrigger())
		{
			start = e.getPoint();
			// JTS
			// consume mouse event so no other movement handlers are triggered
			e.consume();
			// jts
		}
	}

	/**
	 * 
	 */
	public void mouseDragged(MouseEvent e)
	{
		if (!e.isConsumed() && start != null)
		{
			int dx = e.getX() - start.x;
			int dy = e.getY() - start.y;

			Rectangle r = graphComponent.getViewport().getViewRect();

			int right = r.x + ((dx > 0) ? 0 : r.width) - dx;
			int bottom = r.y + ((dy > 0) ? 0 : r.height) - dy;

			graphComponent.getGraphControl().scrollRectToVisible(
					new Rectangle(right, bottom, 1, 1));

			e.consume();
		}
	}

	/**
	 * 
	 */
	public void mouseReleased(MouseEvent e)
	{
		if (!e.isConsumed() && start != null)
		{
			int dx = Math.abs(start.x - e.getX());
			int dy = Math.abs(start.y - e.getY());

			if (graphComponent.isSignificant(dx, dy))
			{
				e.consume();
			}
		}

		start = null;
	}

}
