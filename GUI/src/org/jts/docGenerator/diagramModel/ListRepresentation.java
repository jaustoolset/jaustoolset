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
import org.jts.jsidl.binding.List;

/**
 * Representation of a JSIDL <list> in a message_def or event_def.  Please see class comment on
 * StructureElementRepresentation for more info.
 * @author idurkan
 */
public class ListRepresentation extends StructureElementRepresentation {
    private String countFieldTypeString;
    private StructureElementRepresentation listContents;
    private static final String listString = "list ";
    private static final Rectangle2D boldArea = DiagramSupport.instance().getBoldTextDimensions(listString);

    private int xAfterBold;
    
    /**
     * Recursively creates the ListRepresentation and its children based on the contents of the given JAXB JSIDL
     * representation.
     * @param sourceVariant The JAXB JSIDL representation to gather info from.
     */
    public ListRepresentation(List sourceList) {
        nameString = "name = " + sourceList.getName();

        if (sourceList.getCountField() != null
                && sourceList.getCountField().getFieldTypeUnsigned() != null) {
            
            countFieldTypeString = "(count_field = "
                    + sourceList.getCountField().getFieldTypeUnsigned() + ")";
        } else {
            countFieldTypeString = "(count_field = N/A)";
        }

        // list have one of either record, list, sequence, or variant as child.
        if (sourceList.getRecord() != null) {
            listContents = new RecordRepresentation(sourceList.getRecord());
        } else if (sourceList.getList() != null) {
            listContents = new ListRepresentation(sourceList.getList());
        } else if (sourceList.getSequence() != null) {
            listContents = new SequenceRepresentation(sourceList.getSequence());
        } else if (sourceList.getVariant() != null) {
            listContents = new VariantRepresentation(sourceList.getVariant());
        }
    }

    /**
     * Renders the ListRepresentation and its children onto canvas
     * @param canvas The Graphics2D "canvas" to draw on.
     * @param connectorStartX X-coordinate where the parent-child connector to this ListRepresentation, as displayed
     * in the diagram, should begin.
     * @param connectorStartY Y-coordinate where the parent-child connector to this ListRepresentation, as displayed
     * in the diagram, should begin.
     */
    public void renderToImage(Graphics2D canvas, int connectorStartX, int connectorStartY) {
        DiagramSupport.instance().drawBoldTextAtXY(listString, posX, posY, canvas);
        DiagramSupport.instance().drawTextAtXY(nameString, xAfterBold, posY, canvas);
        DiagramSupport.instance().drawTextAtXY(countFieldTypeString, posX+DiagramSupport.SECOND_LINE_TEXT_OFFSET,
                posY + (int)boldArea.getHeight(), canvas);
        DiagramSupport.instance().drawPerpConnectors(connectorStartX, connectorStartY,
                posX+DiagramSupport.CONNECTOR_END_X_OFFSET, posY+DiagramSupport.CONNECTOR_END_Y_OFFSET, canvas);

        int childConnectorStartY = posY + (int)boldArea.getHeight() 
                + DiagramSupport.CONNECTOR_START_Y_OFFSET;

        listContents.renderToImage(canvas, posX+DiagramSupport.CONNECTOR_START_X_OFFSET,
                childConnectorStartY);
    }

    /**
     * Computes the pixel bounds of the ListRepresentation on the image to render.
     * @param upLeftX Upper-left x-coordinate where this ListRepresentation should be rendered.
     * @param upLeftY Upper-left y-coordinate where this ListRepresentation should be rendered.
     * @return Rectangle containing the pixel bounds where this ListRepresentation will be rendered on output image.
     */
    public Rectangle2D computeImageBounds(int upLeftX, int upLeftY) {
        posX = upLeftX;
        posY = upLeftY;

        Rectangle2D countFieldDims = DiagramSupport.instance().getNormalTextDimensions(countFieldTypeString);
        Rectangle2D nameStringDims = DiagramSupport.instance().getNormalTextDimensions(nameString);

        int nonChildHeight = (int)boldArea.getHeight() + (int)countFieldDims.getHeight();

        // compute values for re-use in renderToImage() call later.
        xAfterBold = posX + (int)boldArea.getWidth();

        int childULX = posX + DiagramSupport.X_STEP;
        int childULY = posY + nonChildHeight;

        Rectangle2D childArea = listContents.computeImageBounds(childULX, childULY);

        int line1Width = (int)boldArea.getWidth() + (int)nameStringDims.getWidth();
        int line2Width = (int)countFieldDims.getWidth() + DiagramSupport.SUBINFO_OFFSET;
        int childWidth = (int)childArea.getWidth();

        int maxWidth = Math.max(childWidth, Math.max(line1Width, line2Width));

        bounds = new Rectangle2D.Double(
                posX,
                posY,
                maxWidth + DiagramSupport.X_STEP, // accounts for "shoving over" children.
                nonChildHeight + (int)childArea.getHeight());

        return bounds;
    }

}
