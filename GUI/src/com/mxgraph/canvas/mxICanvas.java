/**
 * $Id: mxICanvas.java,v 1.16 2008/11/25 17:32:52 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.canvas;

import java.awt.Point;
import java.util.Hashtable;
import java.util.List;

/**
 * Defines the requirements for a canvas that paints the vertices and edges of
 * a graph.
 */
public interface mxICanvas
{
	/**
	 * Sets the translation for the following drawing requests.
	 */
	void setTranslate(int x, int y);
	
	/**
	 * 
	 * @return
	 */
	Point getTranslate();

	/**
	 * Sets the scale for the following drawing requests.
	 */
	void setScale(double scale);
	
	/**
	 * 
	 */
	double getScale();

	/**
	 * Draws the given vertex.
	 * 
	 * @param x X-coordinate of the vertex.
	 * @param y Y-coordinate of the vertex.
	 * @param width Width of the vertex.
	 * @param height Height of the vertex.
	 * @param style Style of the vertex.
	 * @return Optional object that represents the vertex.
	 */
	Object drawVertex(int x, int y, int width, int height, Hashtable style);

	/**
	 * Draws the given edge.
	 * 
	 * @param pts List of mxPoints that make up the edge.
	 * @param style Style of the edge.
	 * @return Optional object that represents the edge.
	 */
	Object drawEdge(List pts, Hashtable style);

	/**
	 * Draws the given label.
	 * 
	 * @param label String that represents the label.
	 * @param x X-coordinate of the label.
	 * @param y Y-coordinate of the label.
	 * @param width Width of the label.
	 * @param height Height of the label.
	 * @param style Style of the label.
	 * @param isHtml Specifies if the label contains HTML markup.
	 * @return Optional object that represents the label.
	 */
	Object drawLabel(String label, int x, int y, int width, int height,
			Hashtable style, boolean isHtml);

}
