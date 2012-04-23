/**
 * $Id: mxGraphViewImageReader.java,v 1.1 2008/11/26 13:05:18 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */

package com.mxgraph.reader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxImageCanvas;
import com.mxgraph.util.mxUtils;

/**
 * A converter that renders display XML data onto a graphics canvas.
 */
public class mxGraphViewImageReader extends mxGraphViewReader
{

	/**
	 * Specifies the background color. Default is null.
	 */
	protected Color background;

	/**
	 * Specifies if the image should be anti-aliased. Default is true.
	 */
	protected boolean antiAlias;

	/**
	 * Specifies the border which is added to the size of the graph. Default is
	 * 0.
	 */
	protected int border;

	/**
	 * Constructs a new reader with a transparent background.
	 */
	public mxGraphViewImageReader()
	{
		this(null);
	}

	/**
	 * Constructs a new reader with the given background color.
	 */
	public mxGraphViewImageReader(Color background)
	{
		this(background, 0);
	}

	/**
	 * Constructs a new reader with a transparent background.
	 */
	public mxGraphViewImageReader(Color background, int border)
	{
		this(background, border, true);
	}

	/**
	 * Constructs a new reader with a transparent background.
	 */
	public mxGraphViewImageReader(Color background, int border, boolean antiAlias)
	{
		this.background = background;
		this.border = border;
		this.antiAlias = antiAlias;
	}

	/**
	 * 
	 */
	public void setBackground(Color background)
	{
		this.background = background;
	}
	
	/**
	 * 
	 */
	public Color getBackground()
	{
		return background;
	}
	
	/**
	 * 
	 */
	public void setBorder(int border)
	{
		this.border = border;
	}
	
	/**
	 * 
	 */
	public int getBorder()
	{
		return border;
	}
	
	/**
	 * 
	 */
	public void setAntiAlias(boolean antiAlias)
	{
		this.antiAlias = antiAlias;
	}

	/**
	 * 
	 */
	public boolean isAntiAlias()
	{
		return antiAlias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mxgraph.reader.mxGraphViewReader#createCanvas(java.util.Hashtable)
	 */
	public mxICanvas createCanvas(Hashtable attrs)
	{
		int width = (int) (Math.round(mxUtils.getDouble(attrs, "x")) + Math
				.round(mxUtils.getDouble(attrs, "width")))
				+ border + 1;
		int height = (int) (Math.round(mxUtils.getDouble(attrs, "y")) + Math
				.round(mxUtils.getDouble(attrs, "height")))
				+ border + 1;

		return new mxImageCanvas(new mxGraphics2DCanvas(), width, height,
				background, antiAlias);
	}

	/**
	 * Creates the image for the given display XML file. (Note: The XML file is
	 * an encoded mxGraphView, not mxGraphModel.)
	 * 
	 * @param filename
	 *            Filename of the display XML file.
	 * @return Returns an image representing the display XML file.
	 */
	public static BufferedImage convert(String filename)
			throws ParserConfigurationException, SAXException, IOException
	{
		return convert(new InputSource(new FileInputStream(filename)));
	}

	/**
	 * Creates the image for the given display XML input source. (Note: The XML
	 * is an encoded mxGraphView, not mxGraphModel.)
	 * 
	 * @param inputSource
	 *            Input source that contains the display XML.
	 * @return Returns an image representing the display XML input source.
	 */
	public static BufferedImage convert(InputSource inputSource)
			throws ParserConfigurationException, SAXException, IOException
	{
		BufferedImage result = null;
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		XMLReader reader = parser.getXMLReader();

		mxGraphViewImageReader viewReader = new mxGraphViewImageReader();
		reader.setContentHandler(viewReader);
		reader.parse(inputSource);
		
		if (viewReader.getCanvas() instanceof mxImageCanvas)
		{
			return ((mxImageCanvas) viewReader.getCanvas()).destroy();
		}
		
		return null;
	}

}
