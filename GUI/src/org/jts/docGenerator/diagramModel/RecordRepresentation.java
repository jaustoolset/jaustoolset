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
import org.jts.jsidl.binding.Record;

/**
 * Representation of a JSIDL <record> in a message_def or event_def.  Please see class comment on
 * StructureElementRepresentation for more info.
 * @author idurkan
 */
public class RecordRepresentation extends StructureElementRepresentation {

    private int xAfterBold;
    private static final String recordString = "record ";
    private static final Rectangle2D boldArea = DiagramSupport.instance().getBoldTextDimensions(recordString);

    /**
     * Recursively creates the RecordRepresentation and its children based on the contents of the given JAXB JSIDL
     * representation.
     * @param sourceVariant The JAXB JSIDL representation to gather info from.
     */
    public RecordRepresentation(Record sourceRecord) {
        xAfterBold = 0;
        nameString = "name = " + sourceRecord.getName();
		if (sourceRecord.isOptional()) nameString += " (optional=true)";
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
        DiagramSupport.instance().drawBoldTextAtXY(recordString, posX, posY, canvas);

        DiagramSupport.instance().drawTextAtXY(nameString, xAfterBold, posY, canvas);

        DiagramSupport.instance().drawPerpConnectors(connectorStartX, connectorStartY, 
                posX+DiagramSupport.CONNECTOR_END_X_OFFSET,
                posY+DiagramSupport.CONNECTOR_END_Y_OFFSET, canvas);
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

        xAfterBold = upLeftX + (int)boldArea.getWidth();

        bounds = new Rectangle2D.Double(
                upLeftX,
                upLeftY,
                boldArea.getWidth() + (int)DiagramSupport.instance().getNormalTextDimensions(nameString).getWidth()
                    + DiagramSupport.X_STEP, // accounting for the X "shove over" here.
                boldArea.getHeight());
        
        return bounds;
    }

}
