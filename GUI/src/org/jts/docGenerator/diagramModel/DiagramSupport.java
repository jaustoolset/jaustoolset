/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of
its contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/

package org.jts.docGenerator.diagramModel;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Singleton class providing utility methods methods and constants for diagramModel classes.
 *
 * please see http://stackoverflow.com/questions/70689/best-singleton-implementation-in-java for reference
 * on why this singleton implemention was used. 
 * @author idurkan
 */
public final class DiagramSupport {

    public static final int X_STEP = 30;
    public static final int Y_STEP = 30;
    public static final int SUBINFO_OFFSET = 20;
    public static final int DIAGRAM_FONT_SIZE = 12;

    /**
     * current representation object should offset its connector line endpoint from its upper-left corner
     * by the following pixel amount in X:
     */
    public static final int CONNECTOR_END_X_OFFSET = -5;

    /**
     * current representation object should offset its connector line endpoint from its upper-left corner
     * by the following pixel amount in X:
     */
    public static final int CONNECTOR_END_Y_OFFSET = 8;

    /**
     * current representation object should offset connector line start point 10 px
     * to the right when drawing its children.
     */
    public static final int CONNECTOR_START_X_OFFSET = 10;

    /**
     * current representation object should offset connector line start point 2 px
     * down when drawing its children.
     */
    public static final int CONNECTOR_START_Y_OFFSET = 2;

    /**
     * Any repr. object that needs a second line of text should offset that text by this
     * amount of pixels in X
     */
    public static final int SECOND_LINE_TEXT_OFFSET = 20;

    /**
     * NSTANCE is instantiated the first time another class imports DiagramSupport.
     */
    private static final DiagramSupport INSTANCE = new DiagramSupport();

    /**
     *  Set up all instance fields
     */
    private DiagramSupport() {
        if (INSTANCE != null) {
            throw new IllegalStateException("DiagramSupport constructor somehow called more than once!");
        }

        normalFont = new Font("SansSerif", Font.PLAIN, DIAGRAM_FONT_SIZE);
        boldFont = new Font("SansSerif", Font.BOLD, DIAGRAM_FONT_SIZE);

        // prepare the objects used by drawText functions.
        drawTextSetup();
    }

    /**
     * Get the instance of DiagramSupport
     * @return instance
     */
    public static DiagramSupport instance() {
        return INSTANCE;
    }

    private FontMetrics normalMetrics;
    private FontMetrics boldMetrics;

    private int normalVertOffset;
    private int boldVertOffset;

    private static Graphics2D storedGraphics;

    /**
     * Before using drawTextAtXY we need some info about the Graphics2D to use for output.  Must be called from
     * constructor.
     * @param destCanvas
     */
    private void drawTextSetup() {
        // setup a dummy in-memory 1x1 pixel Graphics2D, with same image type as planned to be used in output.
        BufferedImage dummyImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        storedGraphics = dummyImg.createGraphics();

        normalMetrics = storedGraphics.getFontMetrics(getNormalFont());
        boldMetrics = storedGraphics.getFontMetrics(getBoldFont());

        normalVertOffset = normalMetrics.getMaxAscent() + (normalMetrics.getLeading() / 2);
        boldVertOffset = boldMetrics.getMaxAscent() + (boldMetrics.getLeading() / 2);
    }

    private Font normalFont;
    /**
     * Get the SansSerif font to be used in diagrams
     * @return
     */
    public Font getNormalFont() {
        return normalFont;
    }

    private Font boldFont;
    /**
     * Get the bold SansSerif font to be used in diagrams.
     * @return
     */
    public Font getBoldFont() {
        return boldFont;
    }

    /**
     * Get a rectangle describing the dimensions of a string rendered in 12-point SansSerif
     * font, in a TYPE_INT_RGB image.
     * @param text
     *     The string to try rendering
     * @return
     *     Rectangle giving dimensions of the string.
     */
    public Rectangle2D getNormalTextDimensions(String text) {
        return normalMetrics.getStringBounds(text, storedGraphics);
    }

    /**
     * Get a rectangle describing the dimensions of a string rendered in *bold* 12-point SansSerif
     * font, in a TYPE_INT_RGB image.
     * @param text
     *     The string to try rendering
     * @return
     *     Rectangle giving dimensions of the string.
     */
    public Rectangle2D getBoldTextDimensions(String text) {
        return boldMetrics.getStringBounds(text, storedGraphics);
    }

    /**
     * Draw 12-point SansSerif-font text at coordinates X, Y on the given Graphics2D.  The text is
     * positioned so the upper boundary of its enclosing rectangle is at coordinate Y on the canvas,
     * using Graphics2D.drawString.  This function is needed to compensate for how drawString positions
     * output text according to the text's baseline.  Note: Sets canvas' font to normalFont.
     * @param text
     *     Text to render
     * @param x
     * @param y
     * @param canvas
     *     Graphics2D to render to.
     */
    public void drawTextAtXY(String text, int x, int y, Graphics2D canvas) {
        canvas.setFont(getNormalFont());
        canvas.drawString(text, x, y + normalVertOffset);
    }

    /**
     * Same as drawTextAtXY but uses bold font.  Note: Sets canvas' current font to result
     * of getBoldFont().
     * @param text
     * @param x
     * @param y
     */
    public void drawBoldTextAtXY(String text, int x, int y, Graphics2D canvas) {
        canvas.setFont(getBoldFont());
        canvas.drawString(text, x, y + boldVertOffset);
    }

     /**
     * Draws a pair of lines connecting one point to another.
     * First line is vertical, connecting (x1, y1) to (x1, y2).
     * Second line is horizontal, connecting (x1, y2) to (x2, y2).
     * Note: will use whatever line style canvas is already using.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void drawPerpConnectors(int x1, int y1, int x2, int y2, Graphics2D canvas) {
        canvas.drawLine(x1, y1, x1, y2);
        canvas.drawLine(x1, y2, x2, y2);
    }
}

