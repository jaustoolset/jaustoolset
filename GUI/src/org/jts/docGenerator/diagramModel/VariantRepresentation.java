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
 * Representation of a JSIDL <variant> in a message_def or event_def.  Please see class comment on
 * StructureElementRepresentation for more info.
 * @author idurkan
 */
public class VariantRepresentation extends StructureElementRepresentation {

    protected static final String variantString = "variant ";
    protected static final String emptyString = "(empty)";
    protected static final Rectangle2D boldArea =
            DiagramSupport.instance().getBoldTextDimensions(variantString);

    protected String attributeFieldTypeString;

    protected int xAfterBold;
    protected int nonChildHeight;

    /**
     *   contains representations of the original <variant>'s children.
     */
    protected ArrayList<StructureElementRepresentation> contents;

    /**
     * Recursively creates the VariantRepresentation and its children based on the contents of the given JAXB JSIDL
     * representation.
     * @param sourceVariant The JAXB JSIDL representation to gather info from.
     */
    public VariantRepresentation(Variant sourceVariant) {
        contents = new ArrayList<StructureElementRepresentation>();
        attributeFieldTypeString = sourceVariant.getVtagField().getFieldTypeUnsigned();
        nameString = "name = " + sourceVariant.getName();
		if (sourceVariant.isOptional()) nameString += " (optional=true)";

        if (sourceVariant.getVtagField() != null
                && sourceVariant.getVtagField().getFieldTypeUnsigned() != null) {

            attributeFieldTypeString = "(vtag_field = "
                    + sourceVariant.getVtagField().getFieldTypeUnsigned() + ")";
        } else {
            attributeFieldTypeString = "(vtag_field = N/A)";
        }

        // variant contains a list of zero or more elements that may be records, lists, 
        // variants, or sequences, in any order.  their types don't inherit from a common parent other than Object,
        // however, so reflection is needed
        if (sourceVariant.getRecordOrDeclaredRecordOrList() != null) {
            for (Object seqElem : sourceVariant.getRecordOrDeclaredRecordOrList()) {
                if (seqElem instanceof Record) {
                    contents.add(new RecordRepresentation((Record)seqElem));
                } else if (seqElem instanceof List) {
                    contents.add(new ListRepresentation((List)seqElem));
                } else if (seqElem instanceof Sequence) {
                    contents.add(new SequenceRepresentation((Sequence)seqElem));
                } else if (seqElem instanceof Variant) {
                    contents.add(new VariantRepresentation((Variant)seqElem));
                } else {
                    throw new RuntimeException("Unexpected contents in source Variant.");
                }
            }
        }
    }

    /**
     * Renders the VariantRepresentation and its children onto canvas
     * @param canvas The Graphics2D "canvas" to draw on.
     * @param connectorStartX X-coordinate where the parent-child connector to this VariantRepresentation, as displayed
     * in the diagram, should begin.
     * @param connectorStartY Y-coordinate where the parent-child connector to this VariantRepresentation, as displayed
     * in the diagram, should begin.
     */
    public void renderToImage(Graphics2D canvas, int connectorStartX, int connectorStartY) {
        DiagramSupport.instance().drawBoldTextAtXY(variantString, posX, posY, canvas);
        DiagramSupport.instance().drawTextAtXY(nameString, xAfterBold, posY, canvas);
        DiagramSupport.instance().drawTextAtXY(attributeFieldTypeString, 
                posX+DiagramSupport.SECOND_LINE_TEXT_OFFSET,
                posY + (int)boldArea.getHeight(), canvas);
        DiagramSupport.instance().drawPerpConnectors(connectorStartX, connectorStartY,
                posX+DiagramSupport.CONNECTOR_END_X_OFFSET,
                posY+DiagramSupport.CONNECTOR_END_Y_OFFSET, canvas);

        int childConnectorStartY = posY + (int)boldArea.getHeight() 
                + DiagramSupport.CONNECTOR_START_Y_OFFSET;

        // variant may have no children
        if (!contents.isEmpty()) {
            for (StructureElementRepresentation subelem : contents) {
                subelem.renderToImage(canvas, posX+DiagramSupport.CONNECTOR_START_X_OFFSET,
                        childConnectorStartY);
            }
        } else {
            // assuming bold area's height is about the same height as the attribute field string
            DiagramSupport.instance().drawTextAtXY(emptyString, posX + DiagramSupport.X_STEP,
                    posY + nonChildHeight, canvas);

            // also draw connector to the empty label
            DiagramSupport.instance().drawPerpConnectors(
                    posX+DiagramSupport.CONNECTOR_START_X_OFFSET, // start X
                    childConnectorStartY, // start Y
                    posX + DiagramSupport.X_STEP + DiagramSupport.CONNECTOR_END_X_OFFSET, // end x
                    posY + nonChildHeight + DiagramSupport.CONNECTOR_END_Y_OFFSET, canvas); // end y
        }
    }

    /**
     * Computes the pixel bounds of the VariantRepresentation on the image to render.
     * @param upLeftX Upper-left x-coordinate where this VariantRepresentation should be rendered.
     * @param upLeftY Upper-left y-coordinate where this VariantRepresentation should be rendered.
     * @return Rectangle containing the pixel bounds where this VariantRepresentation will be rendered on output image.
     */
    public Rectangle2D computeImageBounds(int upLeftX, int upLeftY) {
        posX = upLeftX;
        posY = upLeftY;

        Rectangle2D attribFieldDims = DiagramSupport.instance().getNormalTextDimensions(attributeFieldTypeString);
        Rectangle2D nameStringDims = DiagramSupport.instance().getNormalTextDimensions(nameString);

        // compute values for re-use in renderToImage() call later.
        nonChildHeight = (int)boldArea.getHeight() + (int)attribFieldDims.getHeight();
        xAfterBold = posX + (int)boldArea.getWidth();

        int childULX = posX + DiagramSupport.X_STEP;
        double childULYRunner = posY + nonChildHeight;

        double maxChildWidth = 0;

        // may be no children
        if (!contents.isEmpty()) {
            for (StructureElementRepresentation childElem : contents) {
                Rectangle2D childArea = childElem.computeImageBounds(childULX, (int)childULYRunner);

                if (childArea.getWidth() > maxChildWidth) {
                    maxChildWidth = childArea.getWidth();
                }


                childULYRunner += childArea.getHeight();
            }
        } else {
            Rectangle2D emptyStringDims = DiagramSupport.instance().getNormalTextDimensions(emptyString);
            childULYRunner += emptyStringDims.getHeight();
            maxChildWidth = emptyStringDims.getWidth();
        }

        // get only height of children.
        double childHeight = childULYRunner - (nonChildHeight + posY);

        int line1Width = (int)boldArea.getWidth() + (int)nameStringDims.getWidth();
        int line2Width = (int)attribFieldDims.getWidth() + DiagramSupport.SUBINFO_OFFSET;
        
        double maxWidth = Math.max(maxChildWidth, Math.max(line1Width, line2Width));


        return new Rectangle2D.Double(
                posX,
                posY,
                maxWidth + DiagramSupport.X_STEP, // accounts for "shoving over" children.
                nonChildHeight + childHeight
                );
    }

}
