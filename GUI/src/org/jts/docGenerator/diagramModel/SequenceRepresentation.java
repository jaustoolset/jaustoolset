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

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import org.jts.jsidl.binding.List;
import org.jts.jsidl.binding.Record;
import org.jts.jsidl.binding.Sequence;
import org.jts.jsidl.binding.Variant;

/**
 * Representation of a JSIDL <sequence> in a message_def or event_def.  Please see class comment on
 * StructureElementRepresentation for more info.
 * @author idurkan
 */
public class SequenceRepresentation extends StructureElementRepresentation {

    protected static final String sequenceString = "sequence ";
    protected static final Rectangle2D boldArea = DiagramSupport.instance().getBoldTextDimensions(sequenceString);

    protected int xAfterBold;

    // contains representations of the original <sequence>'s children.
    // should not be empty
    protected ArrayList<StructureElementRepresentation> contents;

    /**
     * Recursively creates the RecordRepresentation and its children based on the contents of the given JAXB JSIDL
     * representation.
     * @param sourceVariant The JAXB JSIDL representation to gather info from.
     */
    public SequenceRepresentation(Sequence sourceSequence) {

        nameString = "name = " + sourceSequence.getName();
        contents = new ArrayList<StructureElementRepresentation>();

        // sequence contains a list of one or more elements that may be records, lists, variants, or sequences, in any
        // order.  their types don't inherit from a common parent other than Object, however.
        for (Object seqElem : sourceSequence.getRecordOrDeclaredRecordOrList()) {
            if (seqElem instanceof Record) {
                contents.add(new RecordRepresentation((Record)seqElem));
            } else if (seqElem instanceof List) {
                contents.add(new ListRepresentation((List)seqElem));
            } else if (seqElem instanceof Sequence) {
                contents.add(new SequenceRepresentation((Sequence)seqElem));
            } else if (seqElem instanceof Variant) {
                contents.add(new VariantRepresentation((Variant)seqElem));
            } else {
                throw new RuntimeException("Unexpected contents in source Sequence.");
            }
        }
    }

    /**
     * Renders the RecordRepresentation and its children onto canvas
     * @param canvas The Graphics2D "canvas" to draw on.
     * @param connectorStartX X-coordinate where the parent-child connector to this RecordRepresentation, as displayed
     * in the diagram, should begin.
     * @param connectorStartY Y-coordinate where the parent-child connector to this RecordRepresentation, as displayed
     * in the diagram, should begin.
     */
    public void renderToImage(Graphics2D canvas, int connectorStartX, int connectorStartY) {
        DiagramSupport.instance().drawBoldTextAtXY(sequenceString, posX, posY, canvas);
        DiagramSupport.instance().drawTextAtXY(nameString, xAfterBold, posY, canvas);
        DiagramSupport.instance().drawPerpConnectors(connectorStartX, connectorStartY,
                posX+DiagramSupport.CONNECTOR_END_X_OFFSET, posY+DiagramSupport.CONNECTOR_END_Y_OFFSET, canvas);

        int childConnectorStartY = posY + (int)boldArea.getHeight() 
                + DiagramSupport.CONNECTOR_START_Y_OFFSET;

        for (StructureElementRepresentation subelem : contents) {
            subelem.renderToImage(canvas, posX+DiagramSupport.CONNECTOR_START_X_OFFSET,
                    childConnectorStartY);
        }
    }

    /**
     * Computes the pixel bounds of the RecordRepresentation on the image to render.
     * @param upLeftX Upper-left x-coordinate where this RecordRepresentation should be rendered.
     * @param upLeftY Upper-left y-coordinate where this RecordRepresentation should be rendered.
     * @return Rectangle containing the pixel bounds where this RecordRepresentation will be rendered on output image.
     */
    public Rectangle2D computeImageBounds(int upLeftX, int upLeftY) {
        posX = upLeftX;
        posY = upLeftY;

        Rectangle2D nameStringDims = DiagramSupport.instance().getNormalTextDimensions(nameString);

        double nonChildHeight = (int)boldArea.getHeight();

        // compute values for re-use in renderToImage() call later.
        xAfterBold = posX + (int)boldArea.getWidth();
        
        int childULX = posX + DiagramSupport.X_STEP;

        // y-coordinate of children; starts at position for first child, incremented as each child is positioned.
        // also functions to keep sum of heights of all children, and the non-child part
        int childULYRunner = posY + (int)nonChildHeight;

        // store largest observed child width, compute while positioning children
        double maxChildWidth = 0;

        for (StructureElementRepresentation childElem : contents) {
            Rectangle2D childArea = childElem.computeImageBounds(childULX, childULYRunner);

            if (childArea.getWidth() > maxChildWidth) {
                maxChildWidth = childArea.getWidth();
            }
            childULYRunner += (int)childArea.getHeight();
        }

        // get only height of children
        double childHeight = childULYRunner - (nonChildHeight + posY);

        // are children or name string wider?
        double maxWidth = Math.max(nameStringDims.getWidth(), maxChildWidth);

        bounds = new Rectangle2D.Double(
                posX,
                posY,
                maxWidth + DiagramSupport.X_STEP, // accounts for "shoving over" children.
                nonChildHeight + childHeight);

        return bounds;

    }

}
