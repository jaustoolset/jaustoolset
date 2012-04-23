/**
 * $Id: mxConstants.java,v 1.20 2009/03/04 10:02:01 gaudenz Exp $
 * Copyright (c) 2007, Gaudenz Alder
 */
package com.mxgraph.util;
                                               
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Contains all global constants.
 */
public class mxConstants
{
	/**
	 * Defines the portion of the cell which is to be used as a connectable
	 * region. Default is 0.5.
	 */
	//public static double DEFAULT_HOTSPOT = 0.3;
	// JTS
	public static double DEFAULT_HOTSPOT = 1.0;
	// jts

	/**
	 * Defines the minimum size in pixels of the portion of the cell which is
	 * to be used as a connectable region. Default is 8.
	 */
	public static int MIN_HOTSPOT_SIZE = 8;

	/**
	 * Defines the maximum size in pixels of the portion of the cell which is
	 * to be used as a connectable region. Use 0 for no maximum. Default is 0.
	 */
	public static int MAX_HOTSPOT_SIZE = 0;

	/**
	 * Defines the SVG namespace.
	 */
	public static String NS_SVG = "http =//www.w3.org/2000/svg";

	/**
	 * Defines the XHTML namespace.
	 */
	public static String NS_XHTML = "http://www.w3.org/1999/xhtml";

	/**
	 * Defines the XLink namespace.
	 */
	public static String NS_XLINK = "http =//www.w3.org/1999/xlink";

	/**
	 * Contains an empty image of size 1, 1.
	 */
	public static Image EMPTY_IMAGE = new BufferedImage(1, 1,
			BufferedImage.TYPE_INT_RGB);

	/**
	 * Comma separated list of default fonts for CSS properties.
	 * Default is Arial, Helvetica.
	 */
	public static String DEFAULT_FONTFAMILIES = "Courier,Courier";

	/**
	 * Defines the default font family. Default is Arial.
	 */
	public static String DEFAULT_FONTFAMILY = "Courier";

	/**
	 * Defines the default font size. Default is 11.
	 */
	public static int DEFAULT_FONTSIZE = 11;

	/**
	 * Specifies the line spacing. Default is 0.
	 */
	public static int LINESPACING = 0;

	/**
	 * Defines the inset in absolute pixels between the label bounding box and
	 * the label text. Default is 3.
	 */
	public static int LABEL_INSET = 3;

	/**
	 * Defines the default marker size. Default is 6.
	 */
	public static int DEFAULT_MARKERSIZE = 6;

	/**
	 * Defines the default image size. Default is 24.
	 */
	public static int DEFAULT_IMAGESIZE = 24;

	/**
	 * Defines the color to be used for shadows. Default is gray.
	 */
	public static Color SHADOW_COLOR = Color.gray;

	/**
	 * Defines the x-offset to be used for shadows. Default is 2.
	 */
	public static int SHADOW_OFFSETX = 2;

	/**
	 * Defines the y-offset to be used for shadows. Default is 3.
	 */
	public static int SHADOW_OFFSETY = 3;

	/**
	 * Defines the color to be used to draw shadows in W3C standards. Default
	 * is gray.
	 */
	public static String W3C_SHADOWCOLOR = "gray";

	/**
	 * Defines the transformation used to draw shadows in SVG.
	 */
	public static String SVG_SHADOWTRANSFORM = "translate(2 3)";

	/**
	 * Specifies the default valid color. Default is green.
	 */
	public static Color DEFAULT_VALID_COLOR = Color.GREEN;

	/**
	 * Specifies the default invalid color. Default is red.
	 */
	public static Color DEFAULT_INVALID_COLOR = Color.RED;

	/**
	 * Defines the rubberband border color. 
	 */
	public static Color RUBBERBAND_BORDERCOLOR = new Color(51, 153, 255);

	/**
	 * Defines the rubberband fill color with an alpha of 80.
	 */
	public static Color RUBBERBAND_FILLCOLOR = new Color(51, 153, 255, 80);

	/**
	 * Defines the handle size. Default is 8.
	 */
	 //JTS
	//public static int HANDLE_SIZE = 7;
	public static int HANDLE_SIZE = 11;
	//jts

	/**
	 * Defines the handle border color. Default is black.
	 */
	public static Color HANDLE_BORDERCOLOR = Color.black;

	/**
	 * Defines the handle fill color. Default is green.
	 */
	public static Color HANDLE_FILLCOLOR = Color.green;

	/**
	 * Defines the label handle fill color. Default is yellow.
	 */
	public static Color LABEL_HANDLE_FILLCOLOR = Color.yellow;

	/**
	 * Defines the connect handle fill color. Default is blue.
	 */
	public static Color CONNECT_HANDLE_FILLCOLOR = Color.blue;

	/**
	 * Defines the handle fill color for locked handles. Default is red.
	 */
	public static Color LOCKED_HANDLE_FILLCOLOR = Color.red;

	/**
	 * Defines the default value for the connect handle. Default is false.
	 */
	public static boolean CONNECT_HANDLE_ENABLED = false;

	/**
	 * Defines the connect handle size. Default is 8.
	 */
	public static int CONNECT_HANDLE_SIZE = 8;

	/**
	 * Defines the selection color. Default is green.
	 */
	public static Color SELECTION_COLOR = Color.green;

	/**
	 * Defines the stroke used for painting selected edges and the border
	 * of selected vertices. Default is a dashed line.
	 */
	public static Stroke SELECTION_STROKE = new BasicStroke(1,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {
					3, 3 }, 0.0f);

	/**
	 * Defines the stroke used for painting the preview for new and existing edges
	 * that are being changed. Default is a dashed line.
	 */
	public static Stroke PREVIEW_STROKE = new BasicStroke(1,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {
					3, 3 }, 0.0f);

	/**
	 * Defines the border used for painting the preview when vertices are being
	 * resized, or cells and labels are being moved.
	 */
	public static Border PREVIEW_BORDER = new LineBorder(
			mxConstants.HANDLE_BORDERCOLOR)
	{
		public void paintBorder(Component c, Graphics g, int x, int y,
				int width, int height)
		{
			((Graphics2D) g).setStroke(SELECTION_STROKE);
			super.paintBorder(c, g, x, y, width, height);
		}
	};

	/**
	 * Defines the length of the horizontal segment of an Entity Relation.
	 * This can be overridden using mxConstants.STYLE_STARTSIZE style.
	 * Default is 30.
	 */
	public static int ENTITY_SEGMENT = 30;

	/**
	 * Defines the spacing between the arrow shape and its terminals. Default
	 * is 10.
	 */
	public static int ARROW_SPACING = 10;

	/**
	 * Defines the width of the arrow shape. Default is 30.
	 */
	public static int ARROW_WIDTH = 30;

	/**
	 * Defines the size of the arrowhead in the arrow shape. Default is 30.
	 */
	public static int ARROW_SIZE = 30;

	/**
	 * Defines the value for none. Default is "none".
	 */
	public static String NONE = "none";

	/**
	 * Defines the key for the perimeter style.
	 * Possible values are the functions defined
	 * in mxPerimeter.
	 */
	public static String STYLE_PERIMETER = "perimeter";

	/**
	 * Defines the key for the opacity style (0-100).
	 */
	public static String STYLE_OPACITY = "opacity";

	/**
	 * Defines the key for the text opacity style (0-100).
	 */
	public static String STYLE_TEXT_OPACITY = "textOpacity";

	/**
	 * Defines the key for the rotation style (0-360).
	 */
	public static String STYLE_ROTATION = "rotation";

	/**
	 * Defines the key for the fillColor style.
	 * Possible values are all color codes.
	 */
	public static String STYLE_FILLCOLOR = "fillColor";

	/**
	 * Defines the key for the gradientColor style.
	 * Possible values are all color codes.
	 */
	public static String STYLE_GRADIENTCOLOR = "gradientColor";

	/**
	 * Defines the key for the gradient direction. Possible values
	 * Possible values are DIRECTION_EAST (default), DIRECTION_WEST,
	 * DIRECTION_NORTH and DIRECTION_SOUTH. Default is DIRECTION_SOUTH.
	 */
	public static String STYLE_GRADIENT_DIRECTION = "gradientDirection";

	/**
	 * Defines the key for the strokeColor style.
	 * Possible values are all color codes.
	 */
	public static String STYLE_STROKECOLOR = "strokeColor";

	/**
	 * Defines the key for the separatorColor style.
	 * Possible values are all color codes.
	 */
	public static String STYLE_SEPARATORCOLOR = "separatorColor";

	/**
	 * Defines the key for the strokeWidth style (in px).
	 */
	public static String STYLE_STROKEWIDTH = "strokeWidth";

	/**
	 * Defines the key for the align style.
	 * Possible values are ALIGN_LEFT,
	 * ALIGN_CENTER and ALIGN_RIGHT.
	 */
	public static String STYLE_ALIGN = "align";

	/**
	 * Defines the key for the verticalAlign style.
	 * Possible values are ALIGN_TOP,
	 * ALIGN_MIDDLE and ALIGN_BOTTOM.
	 */
	public static String STYLE_VERTICAL_ALIGN = "verticalAlign";

	/**
	 * Defines the key for the horizontal label position of vertices.
	 * Possible values are ALIGN_LEFT, ALIGN_CENTER and ALIGN_RIGHT.
	 * Default is ALIGN_CENTER. If left or right are used, then the
	 * text alignment should be the opposite value of this.
	 */
	public static String STYLE_LABEL_POSITION = "labelPosition";

	/**
	 * Defines the key for the vertical label position of vertices.
	 * Possible values are ALIGN_TOP, ALIGN_MIDDLE and ALIGN_BOTTOM.
	 * Default is ALIGN_MIDDLE. If top or bottom are used, then the
	 * vertical text alignment should be the opposite value of this.
	 */
	public static String STYLE_VERTICAL_LABEL_POSITION = "verticalLabelPosition";

	/**
	 * Defines the key for the align style.
	 * Possible values are ALIGN_LEFT,
	 * ALIGN_CENTER and ALIGN_RIGHT.
	 */
	public static String STYLE_IMAGE_ALIGN = "imageAlign";

	/**
	 * Defines the key for the verticalAlign style.
	 * Possible values are ALIGN_TOP,
	 * ALIGN_MIDDLE and ALIGN_BOTTOM.
	 */
	public static String STYLE_IMAGE_VERTICAL_ALIGN = "imageVerticalAlign";

	/**
	 * Defines the key for the image style.
	 * Possible values are any image URL.
	 */
	public static String STYLE_IMAGE = "image";

	/**
	 * Defines the key for the imageWidth style (in px).
	 */
	public static String STYLE_IMAGE_WIDTH = "imageWidth";

	/**
	 * Defines the key for the imageHeight style (in px).
	 */
	public static String STYLE_IMAGE_HEIGHT = "imageHeight";

	/**
	 * Defines the key for the noLabel style. If this is
	 * true then no label is visible for a given cell.
	 * Possible values are true or false (1 or 0).
	 * Default is false.
	 */
	public static String STYLE_NOLABEL = "noLabel";

	/**
	 * Defines the key for the noEdgeStyle style. If this is
	 * true then no edge style is applied for a given edge.
	 * Possible values are true or false (1 or 0).
	 * Default is false.
	 */
	public static String STYLE_NOEDGESTYLE = "noEdgeStyle";

	/**
	 * Defines the key for the label background color.
	 * Possible values are all color codes.
	 */
	public static String STYLE_LABEL_BACKGROUNDCOLOR = "labelBackgroundColor";

	/**
	 * Defines the key for the label border color.
	 * Possible values are all color codes.
	 */
	public static String STYLE_LABEL_BORDERCOLOR = "labelBorderColor";

	/**
	 * Defines the key for the indicatorShape style.
	 * Possible values are any of the SHAPE_*
	 * constants.
	 */
	public static String STYLE_INDICATOR_SHAPE = "indicatorShape";

	/**
	 * Defines the key for the indicatorImage style.
	 * Possible values are any image URL.
	 */
	public static String STYLE_INDICATOR_IMAGE = "indicatorImage";

	/**
	 * Defines the key for the indicatorColor style.
	 * Possible values are all color codes.
	 */
	public static String STYLE_INDICATOR_COLOR = "indicatorColor";

	/**
	 * Defines the key for the indicatorGradientColor style.
	 * Possible values are all color codes.
	 */
	public static String STYLE_INDICATOR_GRADIENTCOLOR = "indicatorGradientColor";

	/**
	 * Defines the key for the indicatorSpacing style (in px).
	 */
	public static String STYLE_INDICATOR_SPACING = "indicatorSpacing";

	/**
	 * Defines the key for the indicatorWidth style (in px).
	 */
	public static String STYLE_INDICATOR_WIDTH = "indicatorWidth";

	/**
	 * Defines the key for the indicatorHeight style (in px).
	 */
	public static String STYLE_INDICATOR_HEIGHT = "indicatorHeight";

	/**
	 * Defines the key for the shadow style.
	 * Possible values are true or false.
	 */
	public static String STYLE_SHADOW = "shadow";

	/**
	 * Defines the key for the endArrow style.
	 * Possible values are all constants in this
	 * class that start with ARROW_. This style is
	 * supported in the mxConnector shape.
	 */
	public static String STYLE_ENDARROW = "endArrow";

	/**
	 * Defines the key for the startArrow style.
	 * Possible values are all constants in this
	 * class that start with ARROW_.
	 * See STYLE_ENDARROW.
	 * This style is supported in the mxConnector shape.
	 */
	public static String STYLE_STARTARROW = "startArrow";

	/**
	 * Defines the key for the endSize style (in px).
	 */
	public static String STYLE_ENDSIZE = "endSize";

	/**
	 * Defines the key for the startSize style (in px).
	 */
	public static String STYLE_STARTSIZE = "startSize";

	/**
	 * Defines the key for the dashed style.
	 * Possible values are true or false.
	 */
	public static String STYLE_DASHED = "dashed";

	/**
	 * Defines the key for the rounded style.
	 * Possible values are true or false.
	 */
	public static String STYLE_ROUNDED = "rounded";

	/**
	 * Defines the key for the source perimeter spacing (in px).
	 * This is the distance between the source connection point of an edge and
	 * the the perimeter of the source vertex.
	 */
	public static String STYLE_SOURCE_PERIMETER_SPACING = "sourcePerimeterSpacing";

	/**
	 * Defines the key for the source perimeter spacing (in px).
	 * This is the distance between the target connection point of an edge and
	 * the the perimeter of the target vertex.
	 */
	public static String STYLE_TARGET_PERIMETER_SPACING = "targetPerimeterSpacing";

	/**
	 * Defines the key for the perimeter spacing (in px).
	 * This is the distance between connection point and the perimeter.
	 */
	public static String STYLE_PERIMETER_SPACING = "perimeterSpacing";

	/**
	 * Defines the key for the spacing (in px). The
	 * spacings below are added to this value on each
	 * side when appropriate.
	 */
	public static String STYLE_SPACING = "spacing";

	/**
	 * Defines the key for the spacingTop style (in px).
	 */
	public static String STYLE_SPACING_TOP = "spacingTop";

	/**
	 * Defines the key for the spacingLeft style (in px).
	 */
	public static String STYLE_SPACING_LEFT = "spacingLeft";

	/**
	 * Defines the key for the spacingBottom style (in px).
	 */
	public static String STYLE_SPACING_BOTTOM = "spacingBottom";

	/**
	 * Defines the key for the spacingRight style (in px).
	 */
	public static String STYLE_SPACING_RIGHT = "spacingRight";

	/**
	 * Defines the key for the horizontal style.
	 * Possible values are true or false.
	 */
	public static String STYLE_HORIZONTAL = "horizontal";

	/**
	 * Defines the key for the direction style. The direction style is used
	 * to specify the direction of certain shapes (eg. mxTriangle).
	 * Possible values are DIRECTION_EAST (default), DIRECTION_WEST,
	 * DIRECTION_NORTH> and DIRECTION_SOUTH.
	 */
	public static String STYLE_DIRECTION = "direction";

	/**
	 * Defines the key for the elbow style.
	 * Possible values are "horizontal" and "vertical".
	 * Default is "horizontal".
	 */
	public static String STYLE_ELBOW = "elbow";

	/**
	 * Defines the key for the fontColor style.
	 * Possible values are all color codes.
	 */
	public static String STYLE_FONTCOLOR = "fontColor";

	/**
	 * Defines the key for the fontFamily style.
	 * Possible values are names such as Arial;
	 * Dialog; Verdana; Times New Roman.
	 */
	public static String STYLE_FONTFAMILY = "fontFamily";

	/**
	 * Defines the key for the fontSize style (in pt).
	 */
	public static String STYLE_FONTSIZE = "fontSize";

	/**
	 * Defines the key for the fontStyle style.
	 * Values may be any logical AND (sum) of
	 * FONT_BOLD, FONT_ITALIC,
	 * FONT_UNDERLINE and FONT_SHADOW.
	 */
	public static String STYLE_FONTSTYLE = "fontStyle";

	/**
	 * Defines the key for the shape style.
	 * Possible values are any of the SHAPE_*
	 * constants.
	 */
	public static String STYLE_SHAPE = "shape";

	/**
	 * Takes a function that creates points. Possible values are the
	 * functions defined in mxEdgeStyle.
	 */
	public static String STYLE_EDGE = "edgeStyle";

	/**
	 * Defines the key for the loop style. Possible values are the
	 * functions defined in mxEdgeStyle.
	 */
	public static String STYLE_LOOP = "loopStyle";

	/**
	 * Defines the key for the horizontal routing center. Possible values are
	 * between -0.5 and 0.5. This is the relative offset from the center used
	 * for connecting edges.
	 */
	public static String STYLE_ROUTING_CENTER_X = "routingCenterX";

	/**
	 * Defines the key for the vertical routing center. Possible values are
	 * between -0.5 and 0.5. This is the relative offset from the center used
	 * for connecting edges.
	 */
	public static String STYLE_ROUTING_CENTER_Y = "routingCenterY";

	/**
	 * FONT_BOLD
	 */
	public static final int FONT_BOLD = 1;

	/**
	 * FONT_ITALIC
	 */
	public static final int FONT_ITALIC = 2;

	/**
	 * FONT_UNDERLINE
	 */
	public static final int FONT_UNDERLINE = 4;

	/**
	 * FONT_SHADOW
	 */
	public static final int FONT_SHADOW = 8;

	/**
	 * SHAPE_RECTANGLE
	 */
	public static final String SHAPE_RECTANGLE = "rectangle";

	/**
	 * SHAPE_ELLIPSE
	 */
	public static final String SHAPE_ELLIPSE = "ellipse";

    /**
	 * SHAPE_CIRCLE
	 */
	public static final String SHAPE_CIRCLE = "circle";

	/**
	 * SHAPE_DOUBLE_ELLIPSE
	 */
	public static final String SHAPE_DOUBLE_ELLIPSE = "doubleEllipse";

	/**
	 * SHAPE_RHOMBUS
	 */
	public static final String SHAPE_RHOMBUS = "rhombus";

	/**
	 * SHAPE_LINE
	 */
	public static final String SHAPE_LINE = "line";

	/**
	 * SHAPE_IMAGE
	 */
	public static final String SHAPE_IMAGE = "image";

	/**
	 * SHAPE_ARROW
	 */
	public static final String SHAPE_ARROW = "arrow";

	/**
	 * SHAPE_LABEL
	 */
	public static final String SHAPE_LABEL = "label";

	/**
	 * SHAPE_CYLINDER
	 */
	public static final String SHAPE_CYLINDER = "cylinder";

	/**
	 * SHAPE_SWIMLANE
	 */
	public static final String SHAPE_SWIMLANE = "swimlane";

	/**
	 * SHAPE_CONNECTOR
	 */
	public static final String SHAPE_CONNECTOR = "connector";

	/**
	 * SHAPE_ACTOR
	 */
	public static final String SHAPE_ACTOR = "actor";

	/**
	 * SHAPE_CLOUD
	 */
	public static final String SHAPE_CLOUD = "cloud";

	/**
	 * SHAPE_TRIANGLE
	 */
	public static final String SHAPE_TRIANGLE = "triangle";

	/**
	 * SHAPE_HEXAGON
	 */
	public static final String SHAPE_HEXAGON = "hexagon";

	/**
	 * ARROW_CLASSIC
	 */
	public static final String ARROW_CLASSIC = "classic";

	/**
	 * ARROW_BLOCK
	 */
	public static final String ARROW_BLOCK = "block";

	/**
	 * ARROW_OPEN
	 */
	public static final String ARROW_OPEN = "open";

	/**
	 * ARROW_BLOCK
	 */
	public static final String ARROW_OVAL = "oval";

	/**
	 * ARROW_OPEN
	 */
	public static final String ARROW_DIAMOND = "diamond";

    //JTS
    /**
	 * ARROW_RECTANGLE
	 */
	public static final String ARROW_RECTANGLE = "rectangle";
    //jts

	/**
	 * ALIGN_LEFT
	 */
	public static final String ALIGN_LEFT = "left";

	/**
	 * ALIGN_CENTER
	 */
	public static final String ALIGN_CENTER = "center";

	/**
	 * ALIGN_RIGHT
	 */
	public static final String ALIGN_RIGHT = "right";

	/**
	 * ALIGN_TOP
	 */
	public static final String ALIGN_TOP = "top";

	/**
	 * ALIGN_MIDDLE
	 */
	public static final String ALIGN_MIDDLE = "middle";

	/**
	 * ALIGN_BOTTOM
	 */
	public static final String ALIGN_BOTTOM = "bottom";

	/**
	 * DIRECTION_NORTH
	 */
	public static final String DIRECTION_NORTH = "north";

	/**
	 * DIRECTION_SOUTH
	 */
	public static final String DIRECTION_SOUTH = "south";

	/**
	 * DIRECTION_EAST
	 */
	public static final String DIRECTION_EAST = "east";

	/**
	 * DIRECTION_WEST
	 */
	public static final String DIRECTION_WEST = "west";

	/**
	 * ELBOW_VERTICAL
	 */
	public static final String ELBOW_VERTICAL = "vertical";

	/**
	 * ELBOW_HORIZONTAL
	 */
	public static final String ELBOW_HORIZONTAL = "horizontal";

	/**
	 * Name of the elbow edge style. Can be used as a string value
	 * for the STYLE_EDGE style.
	 */
	public static final String EDGESTYLE_ELBOW = "elbowEdgeStyle";

	/**
	 * Name of the entity relation edge style. Can be used as a string value
	 * for the STYLE_EDGE style.
	 */
	public static final String EDGESTYLE_ENTITY_RELATION = "entityRelationEdgeStyle";

	/**
	 * Name of the loop edge style. Can be used as a string value
	 * for the STYLE_EDGE style.
	 */
	public static final String EDGESTYLE_LOOP = "loopEdgeStyle";

	/**
	 * Name of the side to side edge style. Can be used as a string value
	 * for the STYLE_EDGE style.
	 */
	public static final String EDGESTYLE_SIDETOSIDE = "sideToSideEdgeStyle";

	/**
	 * Name of the top to bottom edge style. Can be used as a string value
	 * for the STYLE_EDGE style.
	 */
	public static final String EDGESTYLE_TOPTOBOTTOM = "topToBottomEdgeStyle";

	/**
	 * Name of the ellipse perimeter. Can be used as a string value
	 * for the STYLE_PERIMETER style.
	 */
	public static final String PERIMETER_ELLIPSE = "ellipsePerimeter";

	/**
	 * Name of the rectangle perimeter. Can be used as a string value
	 * for the STYLE_PERIMETER style.
	 */
	public static final String PERIMETER_RECTANGLE = "rectanglePerimeter";

	/**
	 * Name of the rhombus perimeter. Can be used as a string value
	 * for the STYLE_PERIMETER style.
	 */
	public static final String PERIMETER_RHOMBUS = "rhombusPerimeter";

	/**
	 * Name of the triangle perimeter. Can be used as a string value
	 * for the STYLE_PERIMETER style.
	 */
	public static final String PERIMETER_TRIANGLE = "trianglePerimeter";

}
