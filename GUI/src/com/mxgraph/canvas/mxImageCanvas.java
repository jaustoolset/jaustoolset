/**
 * $Id: mxImageCanvas.java,v 1.2 2008/11/26 13:05:18 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.canvas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.List;

import com.mxgraph.util.mxUtils;

/**
 * An implementation of a canvas that uses Graphics2D for painting.
 */
public class mxImageCanvas implements mxICanvas
{
	
	/**
	 * 
	 */
	protected mxGraphics2DCanvas canvas;

	/**
	 * 
	 */
	protected Graphics2D previousGraphics;
	
	/**
	 * 
	 */
	protected BufferedImage image;

	/**
	 * 
	 */
	public mxImageCanvas(mxGraphics2DCanvas canvas, int width, int height,
			Color background, boolean antiAlias)
	{
		this.canvas = canvas;
		previousGraphics = canvas.getGraphics();
		image = mxUtils.createBufferedImage(width, height, background);

		if (image != null)
		{
			Graphics2D g = (Graphics2D) image.createGraphics();
			mxUtils.setAntiAlias(g, antiAlias, true);
			canvas.setGraphics(g);
		}
	}
	
	/**
	 * 
	 */
	public mxGraphics2DCanvas getGraphicsCanvas()
	{
		return canvas;
	}

	/**
	 * 
	 */
	public BufferedImage getImage()
	{
		return image;
	}

	/**
	 * 
	 */
	public Object drawEdge(List pts, Hashtable style)
	{
		return canvas.drawEdge(pts, style);
	}

	/**
	 * 
	 */
	public Object drawLabel(String label, int x, int y, int w, int h,
			Hashtable style, boolean isHtml)
	{
		return canvas.drawLabel(label, x, y, w, h, style, isHtml);
	}

	/**
	 * 
	 */
	public Object drawVertex(int x, int y, int w, int h, Hashtable style)
	{
		return canvas.drawVertex(x, y, w, h, style);
	}

	/**
	 * 
	 */
	public double getScale()
	{
		return canvas.getScale();
	}

	/**
	 * 
	 */
	public Point getTranslate()
	{
		return canvas.getTranslate();
	}

	/**
	 * 
	 */
	public void setScale(double scale)
	{
		canvas.setScale(scale);
	}

	/**
	 * 
	 */
	public void setTranslate(int dx, int dy)
	{
		canvas.setTranslate(dx, dy);
	}

	/**
	 * 
	 */
	public BufferedImage destroy()
	{
		BufferedImage tmp = image;
		
		canvas.getGraphics().dispose();
		canvas.setGraphics(previousGraphics);
		
		previousGraphics = null;
		canvas = null;
		image = null;
		
		return tmp;
	}

}
