/**
 * $Id: mxGraphViewReader.java,v 1.21 2009/02/11 16:38:51 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.reader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxUtils;

/**
 * An abstract converter that renders display XML data onto a canvas.
 */
public abstract class mxGraphViewReader extends DefaultHandler
{

	/**
	 * Holds the canvas to be used for rendering the graph.
	 */
	protected mxICanvas canvas;

	/**
	 * Holds the global scale of the graph. This is set just before
	 * createCanvas is called.
	 */
	protected double scale = 1;

	/**
	 * Returns the canvas to be used for rendering.
	 * 
	 * @param attrs Specifies the attributes of the new canvas.
	 * @return Returns a new canvas.
	 */
	public abstract mxICanvas createCanvas(Hashtable attrs);

	/**
	 * Returns the canvas that is used for rendering the graph.
	 * 
	 * @return Returns the canvas.
	 */
	public mxICanvas getCanvas()
	{
		return canvas;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException
	{
		String tagName = qName.toUpperCase();
		Hashtable attrs = new Hashtable();

		for (int i = 0; i < atts.getLength(); i++)
		{
			String name = atts.getLocalName(i);

			// Workaround for possible null name
			if (name == null)
			{
				name = atts.getQName(i);
			}

			attrs.put(name, atts.getValue(i));
		}

		parseElement(tagName, attrs);
	}

	/**
	 * Parses the given element and paints it onto the canvas.
	 * 
	 * @param tagName Name of the node to be parsed.
	 * @param attrs Attributes of the node to be parsed.
	 */
	public void parseElement(String tagName, Hashtable attrs)
	{
		if (canvas == null && tagName.equalsIgnoreCase("GRAPH"))
		{
			scale = mxUtils.getDouble(attrs, "scale", 1);
			canvas = createCanvas(attrs);
			
			if (canvas != null)
			{
				canvas.setScale(scale);
			}
		}
		else if (canvas != null)
		{
			if (tagName.equalsIgnoreCase("VERTEX")
					|| tagName.equalsIgnoreCase("GROUP"))
			{
				drawVertex(attrs);
			}
			else if (tagName.equalsIgnoreCase("EDGE"))
			{
				drawEdge(attrs);
			}

			drawLabel(tagName.equalsIgnoreCase("EDGE"), attrs);
		}
	}

	/**
	 * Draws the specified vertex using the canvas.
	 * 
	 * @param attrs Specifies the attributes of the vertex.
	 */
	public void drawVertex(Hashtable attrs)
	{
		int width = mxUtils.getInt(attrs, "width");
		int height = mxUtils.getInt(attrs, "height");

		if (width > 0 && height > 0)
		{
			int x = (int) Math.round(mxUtils.getDouble(attrs, "x"));
			int y = (int) Math.round(mxUtils.getDouble(attrs, "y"));

			canvas.drawVertex(x, y, width, height, attrs);
		}
	}

	/**
	 * Draws the specified edge using the canvas.
	 * 
	 * @param attrs Specifies the attribute of the edge.
	 */
	public void drawEdge(Hashtable attrs)
	{
		List pts = parsePoints(mxUtils.getString(attrs, "points"));

		if (pts.size() > 0)
		{
			canvas.drawEdge(pts, attrs);
		}
	}

	/**
	 * Draws the specified label using the canvas.
	 * 
	 * @param attrs Specifies the attributes of the label.
	 */
	public void drawLabel(boolean isEdge, Hashtable attrs)
	{
		String label = mxUtils.getString(attrs, "label");

		if (label != null && label.length() > 0)
		{
			int x = (int) Math.round(mxUtils.getDouble(attrs, "offsetX"));
			int y = (int) Math.round(mxUtils.getDouble(attrs, "offsetY"));
			int width = 0;
			int height = 0;

			if (!isEdge)
			{
				x += (int) Math.round(mxUtils.getDouble(attrs, "x"));
				y += (int) Math.round(mxUtils.getDouble(attrs, "y"));
				width = mxUtils.getInt(attrs, "width");
				height = mxUtils.getInt(attrs, "height");
			}

			//mxRectangle size = mxUtils.getLabelSize(label, attrs, false);
			//mxRectangle bounds = mxUtils.getScaledLabelBounds(x, y, size,
			//		width, height, attrs, scale);
			canvas.drawLabel(label, x, y, width, height, attrs, false);
		}
	}

	/**
	 * Parses the list of points into an object-oriented representation.
	 * 
	 * @param pts String containing a list of points.
	 * @return Returns the points as a list of mxPoints.
	 */
	public static List parsePoints(String pts)
	{
		List result = new ArrayList();

		if (pts != null)
		{
			int len = pts.length();
			String tmp = "";
			String x = null;

			for (int i = 0; i < len; i++)
			{
				char c = pts.charAt(i);

				if (c == ',' || c == ' ')
				{
					if (x == null)
					{
						x = tmp;
					}
					else
					{
						result.add(new mxPoint(Double.parseDouble(x), Double
								.parseDouble(tmp)));
						x = null;
					}
					tmp = "";
				}
				else
				{
					tmp += c;
				}
			}

			result.add(new mxPoint(Double.parseDouble(x), Double
					.parseDouble(tmp)));
		}

		return result;
	}

}
