/**
 * $Id: mxUtils.java,v 1.30 2009/03/05 14:30:40 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.text.html.HTMLDocument;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
// JTS
import org.w3c.dom.NodeList;
import org.jts.gui.mxGraphtoJAXB.mxProtocolBehavior;
import org.jts.gui.mxGraphtoJAXB.parser.ParseException;
import com.mxgraph.swing.examples.FreeGraphEditor;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
//import org.jts.gui.JAXBtomxGraph.CustomCharacterEscapeHandler;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBContext;
// jts
import org.xml.sax.InputSource;

import com.mxgraph.io.mxCodecRegistry;
import com.mxgraph.model.mxIGraphModel;

/**
 * Contains various helper methods for use with mxGraph.
 */
public class mxUtils
{

	/**
	 * Static Graphics used for Font Metrics.
	 */
	protected static transient Graphics fontGraphics;

	// Creates a renderer for HTML markup (only possible in
	// non-headless environment)
	static
	{
		try
		{
			fontGraphics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
					.getGraphics();
		}
		catch (Exception e)
		{
			// ignore
		}
	}

	/**
	 * Returns the size for the given label. If isHtml is true then any HTML
	 * markup in the label is computed as HTML and all newlines inside the
	 * HTML body are converted into linebreaks.
	 */
	public static mxRectangle getLabelSize(String label, Hashtable style,
			boolean isHtml)
	{
		mxRectangle size;

		if (isHtml)
		{
			size = getSizeForHtml(getBodyMarkup(label, true), style);
		}
		else
		{
			float fontSize = getFloat(style, mxConstants.STYLE_FONTSIZE,
					mxConstants.DEFAULT_FONTSIZE);
			String fontFamily = getString(style, mxConstants.STYLE_FONTFAMILY,
					mxConstants.DEFAULT_FONTFAMILY);
			int fontStyle = getInt(style, mxConstants.STYLE_FONTSTYLE);
			
			size = getSizeForString(label, fontSize, fontFamily, fontStyle);
		}

		return size;
	}

	/**
	 * Returns the body part of the given HTML markup.
	 */
	public static String getBodyMarkup(String markup, boolean replaceLinefeeds)
	{
		String lowerCase = markup.toLowerCase();
		int bodyStart = lowerCase.indexOf("<body>");

		if (bodyStart >= 0)
		{
			bodyStart += 7;
			int bodyEnd = lowerCase.lastIndexOf("</body>");

			if (bodyEnd > bodyStart)
			{
				markup = markup.substring(bodyStart, bodyEnd).trim();
			}
		}

		if (replaceLinefeeds)
		{
			markup = markup.replaceAll("\n", "<br>");
		}

		return markup;
	}

	/**
	 * Returns the bounds for a label for the given location and size, taking
	 * into account the alignment and spacing in the specified style, as well
	 * as the width and height of the rectangle that contains the label.
	 * (For edge labels this width and height is 0.) The scale is used to scale
	 * the given size and the spacings in the specified style.
	 */
	public static mxRectangle getScaledLabelBounds(double x, double y,
			mxRectangle size, double outerWidth, double outerHeight,
			Hashtable style, double scale)
	{
		// Adds an inset of 3 pixels
		double inset = mxConstants.LABEL_INSET * scale;

		// Scales the size of the label
		double width = size.getWidth() * scale + 2 * inset;
		double height = size.getHeight() * scale + 2 * inset;

		// Gets the global spacing and orientation
		boolean horizontal = isTrue(style, mxConstants.STYLE_HORIZONTAL, true);
		int spacing = (int) (getInt(style, mxConstants.STYLE_SPACING) * scale);

		// Gets the alignment settings
		Object align = getString(style, mxConstants.STYLE_ALIGN,
				mxConstants.ALIGN_CENTER);
		Object valign = getString(style, mxConstants.STYLE_VERTICAL_ALIGN,
				mxConstants.ALIGN_MIDDLE);

		// Gets the vertical spacing
		int top = (int) (getInt(style, mxConstants.STYLE_SPACING_TOP) * scale);
		int bottom = (int) (getInt(style, mxConstants.STYLE_SPACING_BOTTOM) * scale);

		// Gets the horizontal spacing
		int left = (int) (getInt(style, mxConstants.STYLE_SPACING_LEFT) * scale);
		int right = (int) (getInt(style, mxConstants.STYLE_SPACING_RIGHT) * scale);

		// Applies the orientation to the spacings and dimension
		if (!horizontal)
		{
			int tmp = top;
			top = right;
			right = bottom;
			bottom = left;
			left = tmp;

			double tmp2 = width;
			width = height;
			height = tmp2;
		}

		// Computes the position of the label for the horizontal alignment
		if ((horizontal && align.equals(mxConstants.ALIGN_CENTER))
				|| (!horizontal && valign.equals(mxConstants.ALIGN_MIDDLE)))
		{
			x += (outerWidth - width) / 2 + left - right;
		}
		else if ((horizontal && align.equals(mxConstants.ALIGN_RIGHT))
				|| (!horizontal && valign.equals(mxConstants.ALIGN_BOTTOM)))
		{
			x += outerWidth - width - spacing - right;
		}
		else
		{
			x += spacing + left;
		}

		// Computes the position of the label for the vertical alignment
		if ((!horizontal && align.equals(mxConstants.ALIGN_CENTER))
				|| (horizontal && valign.equals(mxConstants.ALIGN_MIDDLE)))
		{
			y += (outerHeight - height) / 2 + top - bottom;
		}
		else if ((!horizontal && align.equals(mxConstants.ALIGN_LEFT))
				|| (horizontal && valign.equals(mxConstants.ALIGN_BOTTOM)))
		{
			y += outerHeight - height - spacing - bottom;
		}
		else
		{
			y += spacing + top;
		}

		return new mxRectangle(x, y, width, height);
	}

	/**
	 * Returns the size of the given text.
	 * 
	 * @param text String whose size should be returned.
	 * @return Returns the size of the given text.
	 */
	public static mxRectangle getSizeForString(String text)
	{
		return getSizeForString(text, 0);
	}

	/**
	 * Returns the size of the given text.
	 * 
	 * @param text String whose size should be returned.
	 * @param fontSize Integer that specifies the font size in pixels. Default is
	 * <mxConstants.DEFAULT_FONTSIZE>.
	 * @return Returns the size of the given text.
	 */
	public static mxRectangle getSizeForString(String text, float fontSize)
	{
		return getSizeForString(text, fontSize, null);
	}

	/**
	 * Returns an <mxRectangle> with the size (width and height in pixels) of
	 * the given text.
	 * 
	 * @param text String whose size should be returned.
	 * @param fontSize Integer that specifies the font size in pixels. Default is
	 * <mxConstants.DEFAULT_FONTSIZE>.
	 * @param fontFamily String that specifies the name of the font famil.y Default
	 * is <mxConstants.DEFAULT_FONTFAMILY>.
	 * @return Returns the size of the given text.
	 */
	public static mxRectangle getSizeForString(String text, float fontSize,
			String fontFamily)
	{
		return getSizeForString(text, fontSize, fontFamily, 0);
	}

	/**
	 * Returns an <mxRectangle> with the size (width and height in pixels) of
	 * the given string.
	 * 
	 * @param text String whose size should be returned.
	 * @param fontSize Integer that specifies the font size in pixels. Default is
	 * <mxConstants.DEFAULT_FONTSIZE>.
	 * @param fontFamily String that specifies the name of the font famil.y Default
	 * is <mxConstants.DEFAULT_FONTFAMILY>.
	 * @param fontStyle Integer that specifies the font style. Default is 0.
	 * @return Returns the size of the given text.
	 */
	public static mxRectangle getSizeForString(String text, float fontSize,
			String fontFamily, int fontStyle)
	{
		if (fontSize == 0)
		{
			fontSize = mxConstants.DEFAULT_FONTSIZE;
		}

		if (fontFamily == null)
		{
			fontFamily = mxConstants.DEFAULT_FONTFAMILY;
		}

		int swingFontStyle = ((fontStyle & mxConstants.FONT_BOLD) == mxConstants.FONT_BOLD) ? Font.BOLD
				: Font.PLAIN;
		swingFontStyle += ((fontStyle & mxConstants.FONT_ITALIC) == mxConstants.FONT_ITALIC) ? Font.ITALIC
				: Font.PLAIN;

		FontRenderContext frc = new FontRenderContext(null, false, false);
		Font font = new Font(fontFamily, swingFontStyle, (int) fontSize);
		FontMetrics metrics = null;

		if (fontGraphics != null)
		{
			metrics = fontGraphics.getFontMetrics(font);
		}

		double lineHeight = mxConstants.LINESPACING;

		if (metrics != null)
		{
			lineHeight += metrics.getHeight();
		}
		else
		{
			lineHeight += fontSize * 1.27;
		}

		String[] lines = text.split("\n");
		Rectangle2D boundingBox = null;

		for (int i = 0; i < lines.length; i++)
		{
			GlyphVector gv = font.createGlyphVector(frc, lines[i]);
			Rectangle2D bounds = gv.getVisualBounds();

			if (boundingBox == null)
			{
				boundingBox = bounds;
			}
			else
			{
				boundingBox.setFrame(0, 0, Math.max(boundingBox.getWidth(),
						bounds.getWidth()), boundingBox.getHeight()
						+ lineHeight);
			}
		}

		// JTS
		// sometimes exception of points error when no text
		if( lines.length == 0 )
		{
			GlyphVector gv = font.createGlyphVector(frc, "");
			Rectangle2D bounds = gv.getVisualBounds();

			boundingBox = bounds;
		}
		// jts

		return new mxRectangle(boundingBox);
	}

	/**
	 * Returns an mxRectangle with the size (width and height in pixels) of
	 * the given HTML markup.
	 * 
	 * @param markup HTML markup whose size should be returned.
	 */
	public static mxRectangle getSizeForHtml(String markup, Hashtable style)
	{
		mxLightweightTextPane textRenderer = mxLightweightTextPane
				.getSharedInstance();

		if (textRenderer != null)
		{
			textRenderer.setStyledDocument(createHtmlDocument(style));
			textRenderer.setText(markup);
			Dimension size = textRenderer.getPreferredSize();

			return new mxRectangle(0, 0, size.width, size.height);
		}

		return getSizeForString(markup);
	}

	/**
	 * Returns the bounding box for the rotated rectangle.
	 */
	public static mxRectangle getBoundingBox(mxRectangle rect, double rotation)
	{
		mxRectangle result = null;

		if (rect != null && rotation != 0)
		{
			double rad = Math.toRadians(rotation);
			double cos = Math.cos(rad);
			double sin = Math.sin(rad);

			mxPoint cx = new mxPoint(rect.getX() + rect.getWidth() / 2, rect
					.getY()
					+ rect.getHeight() / 2);

			mxPoint p1 = new mxPoint(rect.getX(), rect.getY());
			mxPoint p2 = new mxPoint(rect.getX() + rect.getWidth(), rect.getY());
			mxPoint p3 = new mxPoint(p2.getX(), rect.getY() + rect.getHeight());
			mxPoint p4 = new mxPoint(rect.getX(), p3.getY());

			p1 = getRotatedPoint(p1, cos, sin, cx);
			p2 = getRotatedPoint(p2, cos, sin, cx);
			p3 = getRotatedPoint(p3, cos, sin, cx);
			p4 = getRotatedPoint(p4, cos, sin, cx);

			Rectangle tmp = new Rectangle((int) p1.getX(), (int) p1.getY(), 0,
					0);
			tmp.add(p2.getPoint());
			tmp.add(p3.getPoint());
			tmp.add(p4.getPoint());

			result = new mxRectangle(tmp);
		}

		return result;
	}

	/**
	 * Rotates the given point by the given cos and sin.
	 */
	public static mxPoint getRotatedPoint(mxPoint pt, double cos, double sin)
	{
		return getRotatedPoint(pt, cos, sin, new mxPoint());
	}

	/**
	 * Rotates the given point by the given cos and sin.
	 */
	public static mxPoint getRotatedPoint(mxPoint pt, double cos, double sin,
			mxPoint c)
	{
		double x = pt.getX() - c.getX();
		double y = pt.getY() - c.getY();

		double x1 = x * cos - y * sin;
		double y1 = y * cos + x * sin;

		return new mxPoint(x1 + c.getX(), y1 + c.getY());
	}

	/**
	 * Draws the image inside the clip bounds to the given graphics object.
	 */
	public static void drawImageClip(Graphics g, BufferedImage image,
			ImageObserver observer)
	{
		Rectangle clip = g.getClipBounds();

		if (clip != null)
		{
			int w = image.getWidth();
			int h = image.getHeight();

			int x = Math.max(0, Math.min(clip.x, w));
			int y = Math.max(0, Math.min(clip.y, h));

			w = Math.min(clip.width, w - x);
			h = Math.min(clip.height, h - y);

			if (w > 0 && h > 0)
			{
				// TODO: Support for normal images using fast subimage copies
				g.drawImage(image.getSubimage(x, y, w, h), clip.x, clip.y,
						observer);
			}
		}
		else
		{
			g.drawImage(image, 0, 0, observer);
		}
	}

	/**
	 * 
	 */
	public static void fillClippedRect(Graphics g, int x, int y, int width,
			int height)
	{
		Rectangle bg = new Rectangle(x, y, width, height);

		try
		{
			if (g.getClipBounds() != null)
			{
				bg = bg.intersection(g.getClipBounds());
			}
		}
		catch (Exception e)
		{
			// FIXME: Getting clipbounds sometimes throws an NPE
		}

		g.fillRect(bg.x, bg.y, bg.width, bg.height);
	}

	/**
	 * Creates a new list of new points obtained by translating the points in
	 * the given list by the given vector. Elements that are not mxPoints are
	 * added to the result as-is.
	 */
	public static List translatePoints(List pts, double dx, double dy)
	{
		List result = null;

		if (pts != null)
		{
			result = new ArrayList(pts.size());
			Iterator it = pts.iterator();

			while (it.hasNext())
			{
				Object obj = it.next();

				if (obj instanceof mxPoint)
				{
					mxPoint point = (mxPoint) ((mxPoint) obj).clone();

					point.setX(point.getX() + dx);
					point.setY(point.getY() + dy);

					result.add(point);
				}
				else
				{
					result.add(obj);
				}
			}
		}

		return result;
	}

	/**
	 * Returns the intersection of two lines as an mxPoint.
	 * 
	 * @param x0 X-coordinate of the first line's startpoint.
	 * @param y0 Y-coordinate of the first line's startpoint.
	 * @param x1 X-coordinate of the first line's endpoint.
	 * @param y1 Y-coordinate of the first line's endpoint.
	 * @param x2 X-coordinate of the second line's startpoint.
	 * @param y2 Y-coordinate of the second line's startpoint.
	 * @param x3 X-coordinate of the second line's endpoint.
	 * @param y3 Y-coordinate of the second line's endpoint.
	 * @return Returns the intersection between the two lines.
	 */
	public static mxPoint intersection(double x0, double y0, double x1,
			double y1, double x2, double y2, double x3, double y3)
	{
		double denom = ((y3 - y2) * (x1 - x0)) - ((x3 - x2) * (y1 - y0));
		double nume_a = ((x3 - x2) * (y0 - y2)) - ((y3 - y2) * (x0 - x2));
		double nume_b = ((x1 - x0) * (y0 - y2)) - ((y1 - y0) * (x0 - x2));

		double ua = nume_a / denom;
		double ub = nume_b / denom;

		if (ua >= 0.0 && ua <= 1.0 && ub >= 0.0 && ub <= 1.0)
		{
			// Get the intersection point
			double intersectionX = x0 + ua * (x1 - x0);
			double intersectionY = y0 + ua * (y1 - y0);

			return new mxPoint(intersectionX, intersectionY);
		}

		// No intersection
		return null;
	}

	/**
	 * Returns the stylename in a style of the form stylename[;key=value] or an
	 * empty string if the given style does not contain a stylename.
	 * 
	 * @param style String of the form stylename[;key=value].
	 * @return Returns the stylename from the given formatted string.
	 */
	public static String getStylename(String style)
	{
		if (style != null)
		{
			String[] pairs = style.split(";");
			String stylename = pairs[0];

			if (stylename.indexOf("=") < 0)
			{
				return stylename;
			}
		}

		return "";
	}

	/**
	 * Returns the stylenames in a style of the form stylename[;key=value] or an
	 * empty array if the given style does not contain any stylenames.
	 * 
	 * @param style String of the form stylename[;stylename][;key=value].
	 * @return Returns the stylename from the given formatted string.
	 */
	public static String[] getStylenames(String style)
	{
		List result = new ArrayList();

		if (style != null)
		{
			String[] pairs = style.split(";");

			for (int i = 0; i < pairs.length; i++)
			{
				if (pairs[i].indexOf("=") < 0)
				{
					result.add(pairs[i]);
				}
			}
		}

		return (String[]) result.toArray();
	}

	/**
	 * Returns the index of the given stylename in the given style. This
	 * returns -1 if the given stylename does not occur (as a stylename) in the
	 * given style, otherwise it returns the index of the first character.
	 */
	public static int indexOfStylename(String style, String stylename)
	{
		if (style != null && stylename != null)
		{
			String[] tokens = style.split(";");
			int pos = 0;

			for (int i = 0; i < tokens.length; i++)
			{
				if (tokens[i].equals(stylename))
				{
					return pos;
				}

				pos += tokens[i].length() + 1;
			}
		}

		return -1;
	}

	/**
	 * Adds the specified stylename to the given style if it does not already
	 * contain the stylename.
	 */
	public String addStylename(String style, String stylename)
	{
		if (indexOfStylename(style, stylename) < 0)
		{
			if (style == null)
			{
				style = "";
			}
			else if (style.length() > 0
					&& style.charAt(style.length() - 1) != ';')
			{
				style += ';';
			}

			style += stylename;
		}

		return style;
	}

	/**
	 * Removes all occurrences of the specified stylename in the given style
	 * and returns the updated style. Trailing semicolons are preserved.
	 */
	public String removeStylename(String style, String stylename)
	{
		StringBuffer buffer = new StringBuffer();

		if (style != null)
		{
			String[] tokens = style.split(";");

			for (int i = 0; i < tokens.length; i++)
			{
				if (!tokens[i].equals(stylename))
				{
					buffer.append(tokens[i] + ";");
				}
			}
		}

		return (buffer.length() > 1) ? buffer.substring(0, buffer.length() - 1)
				: buffer.toString();
	}

	/**
	 * Removes all stylenames from the given style and returns the updated
	 * style.
	 */
	public static String removeAllStylenames(String style)
	{
		StringBuffer buffer = new StringBuffer();

		if (style != null)
		{
			String[] tokens = style.split(";");

			for (int i = 0; i < tokens.length; i++)
			{
				if (tokens[i].indexOf('=') >= 0)
				{
					buffer.append(tokens[i] + ";");
				}
			}
		}

		return (buffer.length() > 1) ? buffer.substring(0, buffer.length() - 1)
				: buffer.toString();
	}

	/**
	 * Assigns the value for the given key in the styles of the given cells, or
	 * removes the key from the styles if the value is null.
	 * 
	 * @param model Model to execute the transaction in.
	 * @param cells Array of cells to be updated.
	 * @param key Key of the style to be changed.
	 * @param value New value for the given key.
	 */
	public static void setCellStyles(mxIGraphModel model, Object[] cells,
			String key, String value)
	{
		if (cells != null && cells.length > 0)
		{
			model.beginUpdate();
			try
			{
				for (int i = 0; i < cells.length; i++)
				{
					if (cells[i] != null)
					{
						String style = setStyle(model.getStyle(cells[i]), key,
								value);
						model.setStyle(cells[i], style);
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
	 * Adds or removes the given key, value pair to the style and returns the
	 * new style. If value is null or zero length then the key is removed from
	 * the style.
	 * 
	 * @param style String of the form <code>stylename[;key=value]</code>.
	 * @param key Key of the style to be changed.
	 * @param value New value for the given key.
	 * @return Returns the new style.
	 */
	public static String setStyle(String style, String key, String value)
	{
		boolean isValue = value != null && value.length() > 0;

		if (style == null || style.length() == 0)
		{
			if (isValue)
			{
				style = key + "=" + value;
			}
		}
		else
		{
			int index = style.indexOf(key + "=");

			if (index < 0)
			{
				if (isValue)
				{
					String sep = (style.endsWith(";")) ? "" : ";";
					style = style + sep + key + '=' + value;
				}
			}
			else
			{
				String tmp = (isValue) ? key + "=" + value : "";
				int cont = style.indexOf(";", index);
				style = style.substring(0, index) + tmp
						+ ((cont >= 0) ? style.substring(cont) : "");
			}
		}

		return style;
	}

	/**
	 * Sets or toggles the flag bit for the given key in the cell's styles.
	 * If value is null then the flag is toggled.
	 * 
	 * <code>
	 * mxUtils.setCellStyleFlags(graph.getModel(),
	 * 			cells,
	 * 			mxConstants.STYLE_FONTSTYLE,
	 * 			mxConstants.FONT_BOLD, null);
	 * </code>
	 * 
	 * Toggles the bold font style.
	 * 
	 * @param model Model that contains the cells.
	 * @param cells Array of cells to change the style for.
	 * @param key Key of the style to be changed.
	 * @param flag Integer for the bit to be changed.
	 * @param value Optional boolean value for the flag.
	 */
	public static void setCellStyleFlags(mxIGraphModel model, Object[] cells,
			String key, int flag, Boolean value)
	{
		if (cells != null && cells.length > 0)
		{
			model.beginUpdate();
			try
			{
				for (int i = 0; i < cells.length; i++)
				{
					if (cells[i] != null)
					{
						String style = setStyleFlag(model.getStyle(cells[i]),
								key, flag, value);
						model.setStyle(cells[i], style);
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
	 * Sets or removes the given key from the specified style and returns the
	 * new style. If value is null then the flag is toggled.
	 * 
	 * @param style String of the form stylename[;key=value].
	 * @param key Key of the style to be changed.
	 * @param flag Integer for the bit to be changed.
	 * @param value Optional boolean value for the given flag.
	 */
	public static String setStyleFlag(String style, String key, int flag,
			Boolean value)
	{
		if (style == null || style.length() == 0)
		{
			if (value == null || value.booleanValue())
			{
				style = key + "=" + flag;
			}
			else
			{
				style = key + "=0";
			}
		}
		else
		{
			int index = style.indexOf(key + "=");

			if (index < 0)
			{
				String sep = (style.endsWith(";")) ? "" : ";";

				if (value == null || value.booleanValue())
				{
					style = style + sep + key + "=" + flag;
				}
				else
				{
					style = style + sep + key + "=0";
				}
			}
			else
			{
				int cont = style.indexOf(";", index);
				String tmp = "";
				int result = 0;

				if (cont < 0)
				{
					tmp = style.substring(index + key.length() + 1);
				}
				else
				{
					tmp = style.substring(index + key.length() + 1, cont);
				}

				if (value == null)
				{
					result = Integer.parseInt(tmp) ^ flag;
				}
				else if (value.booleanValue())
				{
					result = Integer.parseInt(tmp) | flag;
				}
				else
				{
					result = Integer.parseInt(tmp) & ~flag;
				}

				style = style.substring(0, index) + key + "=" + result
						+ ((cont >= 0) ? style.substring(cont) : "");
			}
		}

		return style;
	}

	/**
	 * Returns true if the dictionary contains true for the given key or
	 * false if no value is defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @return Returns the boolean value for key in dict.
	 */
	public static boolean isTrue(Hashtable dict, String key)
	{
		return isTrue(dict, key, false);
	}

	/**
	 * Returns true if the dictionary contains true for the given key or the
	 * given default value if no value is defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @param defaultValue Default value to return if the key is undefined.
	 * @return Returns the boolean value for key in dict.
	 */
	public static boolean isTrue(Hashtable dict, String key,
			boolean defaultValue)
	{
		Object value = dict.get(key);

		if (value == null)
		{
			return defaultValue;
		}
		else
		{
			if (value.equals("0"))
			{
				return false;
			}

			return value.equals("1") || Boolean.parseBoolean(value.toString());
		}
	}

	/**
	 * Returns the value for key in dictionary as an int or 0 if no value is
	 * defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @return Returns the integer value for key in dict.
	 */
	public static int getInt(Hashtable dict, String key)
	{
		return getInt(dict, key, 0);
	}

	/**
	 * Returns the value for key in dictionary as an int or the given default
	 * value if no value is defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @param defaultValue Default value to return if the key is undefined.
	 * @return Returns the integer value for key in dict.
	 */
	public static int getInt(Hashtable dict, String key, int defaultValue)
	{
		Object value = dict.get(key);

		if (value == null)
		{
			return defaultValue;
		}
		else
		{
			// Handles commas by casting them to an int
			return (int) Float.parseFloat(value.toString());
		}
	}

	/**
	 * Returns the value for key in dictionary as a float or 0 if no value is
	 * defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @return Returns the float value for key in dict.
	 */
	public static float getFloat(Hashtable dict, String key)
	{
		return getFloat(dict, key, 0);
	}

	/**
	 * Returns the value for key in dictionary as a float or the given default
	 * value if no value is defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @param defaultValue Default value to return if the key is undefined.
	 * @return Returns the float value for key in dict.
	 */
	public static float getFloat(Hashtable dict, String key, float defaultValue)
	{
		Object value = dict.get(key);

		if (value == null)
		{
			return defaultValue;
		}
		else
		{
			return Float.parseFloat(value.toString());
		}
	}

	/**
	 * Returns the value for key in dictionary as a double or 0 if no value is
	 * defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @return Returns the double value for key in dict.
	 */
	public static double getDouble(Hashtable dict, String key)
	{
		return getDouble(dict, key, 0);
	}

	/**
	 * Returns the value for key in dictionary as a double or the given default
	 * value if no value is defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @param defaultValue Default value to return if the key is undefined.
	 * @return Returns the double value for key in dict.
	 */
	public static double getDouble(Hashtable dict, String key,
			double defaultValue)
	{
		Object value = dict.get(key);

		if (value == null)
		{
			return defaultValue;
		}
		else
		{
			return Double.parseDouble(value.toString());
		}
	}

	/**
	 * Returns the value for key in dictionary as a string or null if no value
	 * is defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @return Returns the string value for key in dict.
	 */
	public static String getString(Hashtable dict, String key)
	{
		return getString(dict, key, null);
	}

	/**
	 * Returns the value for key in dictionary as a string or the given default
	 * value if no value is defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @param defaultValue Default value to return if the key is undefined.
	 * @return Returns the string value for key in dict.
	 */
	public static String getString(Hashtable dict, String key,
			String defaultValue)
	{
		Object value = dict.get(key);

		if (value == null)
		{
			return defaultValue;
		}
		else
		{
			return value.toString();
		}
	}

	/**
	 * Returns the value for key in dictionary as a color or null if no value
	 * is defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @return Returns the color value for key in dict.
	 */
	public static Color getColor(Hashtable dict, String key)
	{
		return getColor(dict, key, null);
	}

	/**
	 * Returns the value for key in dictionary as a color or the given default
	 * value if no value is defined for the key.
	 * 
	 * @param dict Dictionary that contains the key, value pairs.
	 * @param key Key whose value should be returned.
	 * @param defaultValue Default value to return if the key is undefined.
	 * @return Returns the color value for key in dict.
	 */
	public static Color getColor(Hashtable dict, String key, Color defaultValue)
	{
		Object value = dict.get(key);

		if (value == null)
		{
			return defaultValue;
		}
		else
		{
			return parseColor(value.toString());
		}
	}

	/**
	 * 
	 */
	public static Font getFont(Hashtable style)
	{
		return getFont(style, 1);
	}

	/**
	 * 
	 */
	public static Font getFont(Hashtable style, double scale)
	{
		String fontFamily = getString(style, mxConstants.STYLE_FONTFAMILY,
				mxConstants.DEFAULT_FONTFAMILY);

		int fontSize = getInt(style, mxConstants.STYLE_FONTSIZE,
				mxConstants.DEFAULT_FONTSIZE);
		int fontStyle = getInt(style, mxConstants.STYLE_FONTSTYLE);

		int swingFontStyle = ((fontStyle & mxConstants.FONT_BOLD) == mxConstants.FONT_BOLD) ? Font.BOLD
				: Font.PLAIN;
		swingFontStyle += ((fontStyle & mxConstants.FONT_ITALIC) == mxConstants.FONT_ITALIC) ? Font.ITALIC
				: Font.PLAIN;

		return new Font(fontFamily, swingFontStyle, (int) Math.round(fontSize
				* scale));
	}

	/**
	 * 
	 */
	public static String hexString(Color color)
	{
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();

		return String.format("#%02X%02X%02X", r, g, b);
	}

	/**
	 * Convert a string representing a 24/32bit hex color value into a Color
	 * value.
	 * 
	 * @param colorString
	 *            the 24/32bit hex string value (ARGB)
	 * @return java.awt.Color (24bit RGB on JDK 1.1, 24/32bit ARGB on JDK1.2)
	 * @exception NumberFormatException
	 *                if the specified string cannot be interpreted as a
	 *                hexidecimal integer
	 */
	public static Color parseColor(String colorString)
			throws NumberFormatException
	{
		if (colorString.equalsIgnoreCase("white"))
		{
			return Color.white;
		}
		else if (colorString.equalsIgnoreCase("black"))
		{
			return Color.black;
		}
		else if (colorString.equalsIgnoreCase("red"))
		{
			return Color.red;
		}
		else if (colorString.equalsIgnoreCase("green"))
		{
			return Color.green;
		}
		else if (colorString.equalsIgnoreCase("blue"))
		{
			return Color.blue;
		}
		else if (colorString.equalsIgnoreCase("orange"))
		{
			return Color.orange;
		}
		else if (colorString.equalsIgnoreCase("yellow"))
		{
			return Color.yellow;
		}
		else if (colorString.equalsIgnoreCase("pink"))
		{
			return Color.pink;
		}
		else if (colorString.equalsIgnoreCase("turqoise"))
		{
			return new Color(0, 255, 255);
		}
		else if (colorString.equalsIgnoreCase("gray"))
		{
			return Color.gray;
		}
		else if (colorString.equalsIgnoreCase("none"))
		{
			return null;
		}

		int value;
		try
		{
			value = (int) Long.parseLong(colorString, 16);
		}
		catch (NumberFormatException nfe)
		{
			value = Long.decode(colorString).intValue();
		}

		return new Color(value);
	}

	/**
	 * Returns a hex representation for the given color.
	 * 
	 * @param color Color to return the hex string for.
	 * @return Returns a hex string for the given color.
	 */
	public static String getHexColorString(Color color)
	{
		return Integer.toHexString((color.getRGB() & 0x00FFFFFF)
				| (color.getAlpha() << 24));
	}

	/**
	 * Reads the given filename into a string.
	 * 
	 * @param filename Name of the file to be read.
	 * @return Returns a string representing the file contents.
	 * @throws IOException
	 */
	public static String readFile(String filename) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(filename)));
		StringBuffer result = new StringBuffer();
		String tmp = reader.readLine();

		while (tmp != null)
		{
			result.append(tmp + "\n");
			tmp = reader.readLine();
		}

		reader.close();

		return result.toString();
	}

	/**
	 * Writes the given string into the given file.
	 * 
	 * @param contents String representing the file contents.
	 * @param filename Name of the file to be written.
	 * @throws IOException
	 */
	public static void writeFile(String contents, String filename)
			throws IOException
	{
		FileWriter fw = new FileWriter(filename);
		fw.write(contents);
		fw.flush();
		fw.close();
	}

	/**
	 * Returns the Md5 hash for the given text.
	 * 
	 * @param text String whose Md5 hash should be returned.
	 * @return Returns the Md5 hash for the given text.
	 */
	public static String getMd5Hash(String text)
	{
		StringBuffer result = new StringBuffer(32);
		try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(text.getBytes());
			Formatter f = new Formatter(result);

			byte[] digest = md5.digest();

			for (int i = 0; i < digest.length; i++)
			{
				f.format("%02x", new Object[] { new Byte(digest[i]) });
			}
		}
		catch (NoSuchAlgorithmException ex)
		{
			ex.printStackTrace();
		}

		return result.toString();
	}

	/**
	 * Returns true if the user object is an XML  with the specified type
	 * and and the optional attribute has the specified value or is not
	 * specified.
	 * 
	 * @param value Object that should be examined as a node.
	 * @param nodeName String that specifies the node name.
	 * @return Returns true if the node name of the user object is equal to the
	 * given type.
	 */

	public static boolean isNode(Object value, String nodeName)
	{
		return isNode(value, nodeName, null, null);
	}

	/**
	 * Returns true if the given value is an XML node with the node name
	 * and if the optional attribute has the specified value.
	 * 
	 * @param value Object that should be examined as a node.
	 * @param nodeName String that specifies the node name.
	 * @param attributeName Optional attribute name to check.
	 * @param attributeValue Optional attribute value to check.
	 * @return Returns true if the value matches the given conditions.
	 */
	public static boolean isNode(Object value, String nodeName,
			String attributeName, String attributeValue)
	{
		if (value instanceof Element)
		{
			Element element = (Element) value;

			if (nodeName == null
					|| element.getNodeName().equalsIgnoreCase(nodeName))
			{
				String tmp = (attributeName != null) ? element
						.getAttribute(attributeName) : null;

				return attributeName == null
						|| (tmp != null && tmp.equals(attributeValue));
			}
		}

		return false;
	}

	/**
	 * 
	 * @param g
	 * @param antiAlias
	 * @param textAntiAlias
	 */
	public static void setAntiAlias(Graphics2D g, boolean antiAlias,
			boolean textAntiAlias)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				(antiAlias) ? RenderingHints.VALUE_ANTIALIAS_ON
						: RenderingHints.VALUE_ANTIALIAS_OFF);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				(textAntiAlias) ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON
						: RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}

	/**
	 * Clears the given area of the specified graphics object with the given
	 * color or makes the region transparent.
	 */
	public static void clearRect(Graphics2D g, Rectangle rect, Color background)
	{
		if (background != null)
		{
			g.setColor(background);
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
		else
		{
			Composite composite = g.getComposite();
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR,
					0.0f));
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			g.setComposite(AlphaComposite.SrcOver);
		}
	}

	/**
	 * Creates a buffered image for the given parameters.
	 */
	public static BufferedImage createBufferedImage(int w, int h,
			Color background) throws OutOfMemoryError
	{
		BufferedImage result = null;

		if (w > 0 && h > 0)
		{
			// Checks if there is enough memory for allocating the buffer
			Runtime runtime = Runtime.getRuntime();
			long maxMemory = runtime.maxMemory();
			long allocatedMemory = runtime.totalMemory();
			long freeMemory = runtime.freeMemory();
			long totalFreeMemory = (freeMemory + (maxMemory - allocatedMemory)) / 1024;

			int bytes = 4; // 1 if indexed
			long memoryRequired = w * h * bytes / 1024;

			if (memoryRequired <= totalFreeMemory)
			{
				int type = (background != null) ? BufferedImage.TYPE_INT_RGB
						: BufferedImage.TYPE_INT_ARGB;
				result = new BufferedImage(w, h, type);

				// Clears background
				Graphics2D g2 = (Graphics2D) result.createGraphics();
				clearRect(g2, new Rectangle(w, h), background);
				g2.dispose();
			}
		}

		return result;
	}

	/**
	 * Returns a new, empty DOM document.
	 * 
	 * @return Returns a new DOM document.
	 */
	public static Document createDocument()
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder parser = factory.newDocumentBuilder();

			return parser.newDocument();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return null;
	}

	/**
	 * 
	 */
	public static Document createSvgDocument(int width, int height)
	{
		Document document = createDocument();
		Element root = document.createElement("svg");

		String w = String.valueOf(width);
		String h = String.valueOf(height);

		root.setAttribute("width", w);
		root.setAttribute("height", h);
		root.setAttribute("viewBox", "0 0 " + w + " " + h);
		root.setAttribute("version", "1.1");
		root.setAttribute("xmlns", "http://www.w3.org/2000/svg");
		root.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");

		document.appendChild(root);

		return document;
	}

	/**
	 * 
	 */
	public static Document createVmlDocument()
	{
		Document document = createDocument();

		Element root = document.createElement("html");
		root.setAttribute("xmlns:v", "urn:schemas-microsoft-com:vml");
		root.setAttribute("xmlns:o", "urn:schemas-microsoft-com:office:office");

		document.appendChild(root);

		Element head = document.createElement("head");

		Element style = document.createElement("style");
		style.setAttribute("type", "text/css");
		style
				.appendChild(document
						.createTextNode("<!-- v\\:* {behavior: url(#default#VML);} -->"));

		head.appendChild(style);
		root.appendChild(head);

		Element body = document.createElement("body");
		root.appendChild(body);

		return document;
	}

	/**
	 * 
	 */
	public static Document createHtmlDocument()
	{
		Document document = createDocument();

		Element root = document.createElement("html");

		document.appendChild(root);

		Element head = document.createElement("head");
		root.appendChild(head);

		Element body = document.createElement("body");
		root.appendChild(body);

		return document;
	}

	/**
	 * Returns a new, empty DOM document.
	 * 
	 * @return Returns a new DOM document.
	 */
	public static HTMLDocument createHtmlDocument(Hashtable style)
	{
		return createHtmlDocument(style, 1);
	}

	/**
	 * Returns a new, empty DOM document.
	 * 
	 * @return Returns a new DOM document.
	 */
	public static HTMLDocument createHtmlDocument(Hashtable style, double scale)
	{
		// Applies the font settings
		HTMLDocument document = new HTMLDocument();

		StringBuffer rule = new StringBuffer("body {");
		rule.append(" font-family: "
				+ getString(style, mxConstants.STYLE_FONTFAMILY,
						mxConstants.DEFAULT_FONTFAMILIES) + " ; ");
		rule.append(" font-size: "
				+ (int) (getInt(style, mxConstants.STYLE_FONTSIZE,
						mxConstants.DEFAULT_FONTSIZE) * scale) + " pt ;");

		String color = mxUtils.getString(style, mxConstants.STYLE_FONTCOLOR);

		if (color != null)
		{
			rule.append("color: " + color + " ; ");
		}

		int fontStyle = mxUtils.getInt(style, mxConstants.STYLE_FONTSTYLE);

		if ((fontStyle & mxConstants.FONT_BOLD) == mxConstants.FONT_BOLD)
		{
			rule.append(" font-weight: bold ; ");
		}

		if ((fontStyle & mxConstants.FONT_ITALIC) == mxConstants.FONT_ITALIC)
		{
			rule.append(" font-style: italic ; ");
		}

		if ((fontStyle & mxConstants.FONT_UNDERLINE) == mxConstants.FONT_UNDERLINE)
		{
			rule.append(" text-decoration: underline ; ");
		}

		String align = getString(style, mxConstants.STYLE_ALIGN,
				mxConstants.ALIGN_LEFT);

		if (align.equals(mxConstants.ALIGN_CENTER))
		{
			rule.append(" text-align: center ; ");
		}
		else if (align.equals(mxConstants.ALIGN_RIGHT))
		{
			rule.append(" text-align: right ; ");
		}

		rule.append(" } ");
		document.getStyleSheet().addRule(rule.toString());

		return document;
	}

	/**
	 * Creates a table for the given text using the given document to create
	 * the DOM nodes. Returns the outermost table node.
	 */
	public static Element createTable(Document document, String text, int x,
			int y, int w, int h, double scale, Hashtable style)
	{
		// Does not use a textbox as this must go inside another VML shape
		Element table = document.createElement("table");

		if (text != null && text.length() > 0)
		{
			Element tr = document.createElement("tr");
			Element td = document.createElement("td");

			table.setAttribute("cellspacing", "0");
			table.setAttribute("border", "0");
			td.setAttribute("align", "center");

			String fontColor = getString(style, mxConstants.STYLE_FONTCOLOR,
					"black");
			String fontFamily = getString(style, mxConstants.STYLE_FONTFAMILY,
					mxConstants.DEFAULT_FONTFAMILIES);
			int fontSize = (int) (getInt(style, mxConstants.STYLE_FONTSIZE,
					mxConstants.DEFAULT_FONTSIZE) * scale);

			String s = "position:absolute;" + "left:" + String.valueOf(x)
					+ "px;" + "top:" + String.valueOf(y) + "px;" + "width:"
					+ String.valueOf(w) + "px;" + "height:" + String.valueOf(h)
					+ "px;" + "font-size:" + String.valueOf(fontSize) + "px;"
					+ "font-family:" + fontFamily + ";" + "color:" + fontColor
					+ ";";

			// Applies the background color
			String background = getString(style,
					mxConstants.STYLE_LABEL_BACKGROUNDCOLOR);

			if (background != null)
			{
				s += "background:" + background + ";";
			}

			// Applies the border color
			String border = getString(style,
					mxConstants.STYLE_LABEL_BORDERCOLOR);

			if (border != null)
			{
				s += "border:" + border + " solid 1pt;";
			}

			// Applies the opacity
			float opacity = getFloat(style, mxConstants.STYLE_TEXT_OPACITY, 100);

			if (opacity < 100)
			{
				// Adds all rules (first for IE)
				s += "filter:alpha(opacity=" + opacity + ");";
				s += "opacity:" + (opacity / 100) + ";";
			}

			td.setAttribute("style", s);
			String[] lines = text.split("\n");

			for (int i = 0; i < lines.length; i++)
			{
				td.appendChild(document.createTextNode(lines[i]));
				td.appendChild(document.createElement("br"));
			}

			tr.appendChild(td);
			table.appendChild(tr);
		}

		return table;
	}

	/**
	 * 
	 */
	public static Image loadImage(String url)
	{
		Image img = null;
		URL realUrl = null;

		try
		{
			realUrl = new URL(url);

		}
		catch (Exception e)
		{
			realUrl = mxUtils.class.getResource(url);
		}

		if (url != null)
		{
			try
			{
				img = ImageIO.read(realUrl);
			}
			catch (IOException e1)
			{
				// ignore
			}
		}

		return img;
	}

	/**
	 * Returns a new DOM document for the given URI.
	 * 
	 * @param uri URI to parse into the document.
	 * @return Returns a new DOM document for the given URI.
	 */
	public static Document loadDocument(String uri)
	{
		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			return docBuilder.parse(uri);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Returns a new document for the given XML string.
	 * 
	 * @param xml String that represents the XML data.
	 * @return Returns a new XML document.
	 */
	public static Document parse(String xml)
	{
		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

			return docBuilder.parse(new InputSource(new StringReader(xml)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Evaluates a Java expression to a class member using mxCodecRegistry.
	 * The range of supported expressions is limited to static class
	 * members such as mxEdgeStyle.ElbowConnector.
	 * 
	 * @param expression
	 * @return
	 */
	public static Object eval(String expression)
	{
		int dot = expression.lastIndexOf(".");
		
		if (dot > 0)
		{
			Class clazz = mxCodecRegistry.getClassForName(expression.substring(0, dot));
			
			if (clazz != null)
			{
				try
				{
					return clazz.getField(expression.substring(dot + 1)).get(null);
				}
				catch (Exception e)
				{
					// ignore
				}
			}
		}
		
		return expression;
	}

	/**
	 * Returns a single node that matches the given XPath expression.
	 * 
	 * @param doc Document that contains the nodes.
	 * @param expression XPath expression to be matched.
	 * @return Returns a single node matching the given expression.
	 */
	public static Node selectSingleNode(Document doc, String expression)
	{
		try
		{
			XPath xpath = XPathFactory.newInstance().newXPath();

			return (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
		}
		catch (XPathExpressionException e)
		{
			// ignore
		}

		return null;
	}

	/**
	 * Converts the ampersand, quote, prime, less-than and greater-than characters
	 * to their corresponding HTML entities in the given string.
	 */
	public static String htmlEntities(String text)
	{
		return text.replaceAll("&", "&amp;").replaceAll("\"", "&quot;")
				.replaceAll("'", "&prime;").replaceAll("<", "&lt;").replaceAll(
						">", "&gt;");
	}

	/**
	 * Returns a string that represents the given node.
	 * 
	 * @param node Node to return the XML for.
	 * @return Returns an XML string.
	 */
	public static String getXml(Node node)
	{
		try
		{
			TransformerFactory tranFactory = TransformerFactory.newInstance();
			Transformer aTransformer = tranFactory.newTransformer();

			Source src = new DOMSource(node);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Result dest = new StreamResult(stream);
			aTransformer.transform(src, dest);

			return stream.toString("UTF-8");
		}
		catch (Exception e)
		{
			// ignore
		}

		return "";
	}

	/**
	 * Returns a pretty-printed XML string for the given node.
	 * 
	 * @param node Node to return the XML for.
	 * @return Returns a formatted XML string.
	 */
	public static String getPrettyXml(Node node)
	{
		return getPrettyXml(node, "  ", "");
	}

	/**
	 * Returns a pretty-printed XML string for the given node.
	 * 
	 * @param node Node to return the XML for.
	 * @param tab String to be used for indentation of inner nodes.
	 * @param indent Current indentation for the node.
	 * @return Returns a formatted XML string.
	 */
	public static String getPrettyXml(Node node, String tab, String indent)
	{
		StringBuffer result = new StringBuffer();

		if (node != null)
		{
			if (node.getNodeType() == Node.TEXT_NODE)
			{
				result.append(node.getNodeValue());
			}
			else
			{
				result.append(indent + "<" + node.getNodeName());
				NamedNodeMap attrs = node.getAttributes();

				if (attrs != null)
				{
					for (int i = 0; i < attrs.getLength(); i++)
					{
						// TODO: htmlEntities
						String value = attrs.item(i).getNodeValue();
						result.append(" " + attrs.item(i).getNodeName() + "=\""
								+ value + "\"");
					}
				}
				Node tmp = node.getFirstChild();

				if (tmp != null)
				{
					result.append(">\n");

					while (tmp != null)
					{
						result.append(getPrettyXml(tmp, tab, indent + tab));
						tmp = tmp.getNextSibling();
					}

					result.append(indent + "</" + node.getNodeName() + ">\n");
				}
				else
				{
					result.append("/>\n");
				}
			}
		}

		return result.toString();
	}
	
	// JTS
	public static void writeJmatter(Node node, FreeGraphEditor editor) {		
		Node root = node.getFirstChild();
		NodeList cells = root.getChildNodes();
		
		
		// first convert to JAXB
		org.jts.jsidl.binding.ProtocolBehavior jxpb = null;
            
            try {
 
              mxProtocolBehavior mxpb = new mxProtocolBehavior( cells ); 
              jxpb = mxpb.convert();
               
              //**************** test with unmarshal
             java.io.FileOutputStream out = new java.io.FileOutputStream( new java.io.File("test.xml") );
             //javax.xml.bind.JAXB.marshal( jxpb, out );
             JAXBContext jsidlJc = JAXBContext.newInstance( "org.jts.jsidl.binding" );
            Marshaller jsidlMarshaller = jsidlJc.createMarshaller();
            //jsidlMarshaller.setProperty( "jaxb.encoding", "US-ASCII" );
            //jsidlMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
            //jsidlMarshaller.setProperty(
            //        CharacterEscapeHandler.class.getName(),
            //        new CustomCharacterEscapeHandler() );
            jsidlMarshaller.marshal(jxpb, out);
                    
              // save behavior to database 
              editor.saveProtocolBehavior( jxpb );
            } catch( ParseException pe ) {
              pe.printStackTrace();
            } catch( Exception ee ) {
              ee.printStackTrace();
            }
		// as next step, need to add pb to service def if pb was created for a service def. else do nothing.  
	}
	
	// jts

}
