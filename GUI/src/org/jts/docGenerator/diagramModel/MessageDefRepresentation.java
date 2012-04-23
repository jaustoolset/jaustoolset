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
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jts.jsidl.binding.Body;
import org.jts.jsidl.binding.EventDef;
import org.jts.jsidl.binding.MessageDef;

/**
 * Creates a tree of StructureElementRepresentation objects and provides a method to compute their positioning for
 * diagram rendering, plus a method to perform the rendering itself.  Please see the class comment for
 * StructureElementRepresentation for more info about this process.
 * @author idurkan
 */
public class MessageDefRepresentation {

    /**
     * Total height of the diagram to output
     */
    private int totalHeight;

    /**
     * Total width of the diagram to output.
     */
    private int totalWidth;

    /**
     * The top-level structural component
     */
    private StructureElementRepresentation bodyContents;

    /**
     * Create the StructureElementRepresentation tree based on JAXB representation of a message_def.
     * @param sourceMsgDef The input message_def, in JAXB instances.
     */
    public MessageDefRepresentation(MessageDef sourceMsgDef) {
        totalHeight = 0;
        totalWidth = 0;

        fillContents(sourceMsgDef.getBody());
    }

    /**
     * Create the StructureElementRepresentation tree based on JAXB representation of a message_def.
     * @param sourceEventDef The input event_def, in JAXB instances.
     */
    public MessageDefRepresentation(EventDef sourceEventDef) {
        totalHeight = 0;
        totalWidth = 0;

        fillContents(sourceEventDef.getBody());
    }

    /**
     * Create representation of the event_def or message_def's structural components by recursively
     * constructing StructureElementRepresentation implementers.
     * @param defBody
     */
    public final void fillContents(Body defBody) {

        // only interested in body contents - only one of record, list, sequence, or variant should be non-null.
        // the body may also be empty, leading to a null result to all queries.
        if (defBody.getRecord() != null) {
            bodyContents = new RecordRepresentation(defBody.getRecord());
        } else if (defBody.getList() != null) {
            bodyContents = new ListRepresentation(defBody.getList());
        } else if (defBody.getSequence() != null) {
            bodyContents = new SequenceRepresentation(defBody.getSequence());
        } else if (defBody.getVariant() != null) {
            bodyContents = new VariantRepresentation(defBody.getVariant());
        } 
    }

    // some arbitrary pixel lengths used for laying out the output diagram image.
    protected final static int FIRST_Y_STEP = 20;
    protected final static int RIGHT_X_BUFFER = 20;
    protected final static int BOTTOM_Y_BUFFER = 25;
    protected final static int EMPTY_WIDTH = 80;
    protected final static int EMPTY_HEIGHT = 55;

    /**
     * Recursively determine the positioning of the message_def or event_def's structural components by
     * calling computeImageBounds.
     */
    public void computePositioning() {
        // no positioning for the msg def repr itself, just its contents.

        // the body may be empty
        if (bodyContents != null) {
            Rectangle2D bounds = bodyContents.computeImageBounds(DiagramSupport.X_STEP, FIRST_Y_STEP);

            // leave room for the "body" text plus some space @ bottom of image.

            totalWidth = (int)bounds.getWidth() + RIGHT_X_BUFFER;
            totalHeight = (int)bounds.getHeight() + BOTTOM_Y_BUFFER;

        } else {
            totalWidth = EMPTY_WIDTH;
            totalHeight = EMPTY_HEIGHT;
        }
    }

    /**
     * Render the image to outputFile as a PNG, recursively calling renderToImage on the message_def or event_def's
     * structural components.
     * @param outputFile File location for output.
     */
    public void renderImage(File outputFile) {
        // create image, renderToImage children into it, dump to file.

        BufferedImage imgOut = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D canvasGfx = imgOut.createGraphics();

        canvasGfx.setFont(DiagramSupport.instance().getNormalFont());
        canvasGfx.setBackground(Color.WHITE);
        canvasGfx.setColor(Color.BLACK);
        canvasGfx.clearRect(0, 0, totalWidth, totalHeight);

        // numbers below are arbitrary pixel position to make the output image 'look good'.
        DiagramSupport.instance().drawTextAtXY("body", 5, 5, canvasGfx);

        if (bodyContents != null) {
           bodyContents.renderToImage(canvasGfx, 15, 20);
        } else {
            DiagramSupport.instance().drawTextAtXY("(empty)", 30, 30, canvasGfx);
            DiagramSupport.instance().drawPerpConnectors(15, 20, 25, 36, canvasGfx);
        }

        try {
            ImageIO.write(imgOut, "png", outputFile);
        } catch (IOException ioex) {
            throw new RuntimeException("Exception while outputting message def diagram image.", ioex);
        }

    }
}
