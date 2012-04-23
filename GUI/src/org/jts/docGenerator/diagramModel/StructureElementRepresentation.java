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

/**
 * A StructureElementRepresentation represents a structural component of a message_def or event_def in JSIDL.
 * message_defs and event_defs have identical child structure; let "message definition" refer to either.
 * Message definitions' structural components include the following:
 *  * Lists
 *  * Sequences
 *  * Variants
 *  * Records
 * Lists, sequences, and variants in turn contain additional structural components, while records do not.  In other
 * words, structural components form a tree, with records as leaves.
 *
 * Concrete instances of StructureElementRepresentation's extenders are used to create an in-memory representation of
 * a message definition's structural components, for the purpose of creating a a diagram image describing the message
 * structure.  The extenders are (as of present):
 *  * ListRepresentation
 *  * SequenceRepresentation
 *  * VariantRepresentation
 *  * RecordRepresentation
 *
 * The constructors of these extenders take JAXB JSIDL representation instances of their associated JAXB JSIDL classes,
 * extract needed data such as names, then recursively construct their children, passing JSIDL JAXB representation(s)
 * of their children.
 *
 * Similarly, extenders implement computeImageBounds to compute their bounds on the diagram to render, after
 * recursively calling computeImageBounds on their children to determine the children's bounds.  For example, the
 * bounds of a SequenceRepresentation must include the space required by the SequenceRepresentation's children, plus
 * its name and other info.
 *
 * Finally, extenders implement renderToImage to recursively render their children onto the Graphics2D canvas.
 *
 * Note StructureElementRepresentation is an abstract class rather than an interface since some fields are needed
 * in by all implementers, reducing repeated code.
 * 
 * @author idurkan
 */
public abstract class StructureElementRepresentation {

        /**
         * X-coordinate of element's upper-left corner in the diagram to render
         */
        protected int posX;
        /**
         * Y-coordinate of elem's upper-left corner in the diagram to render.
         */
        protected int posY;
        /**
         * Rectangular bounds of the element in the diagram to render, including area of its children.  X-coordinates
         * are pixels right of image's upper-left corner, Y-coordinates are pixels down from image's upper-left corner.
         */
        protected Rectangle2D bounds;
        /**
         * String to contain the element's name
         */
        protected String nameString;

        /**
         * Render an image representation of this StructureElementRepresentation onto the given Graphics2D canvas.  
         * Must also draw a connector line to the parent's connectorStartX, connectorStartY position.
         * @param canvas
         *     Graphics2D to render to.
         * @param connectorStartX
         *     X-position of parent's connector line start point.
         * @param connectorStartY
         *     Y-position of parent's connector line start point.
         */
        public abstract void renderToImage(Graphics2D canvas, int connectorStartX, int connectorStartY);

        /**
         * Compute the bounds of the representation, including children to be used in the output image,
         * in absolute coordinates.  Use position of the upper-left corner of the bounds, given in absolute
         * coordinates as a starting point.
         * @param upLeftX
         *     Absolute upper-left X position
         * @param upLeftY
         *     Absolute upper-left Y position
         * @return
         *     Rectangle indicating total bounds
         */
        public abstract Rectangle2D computeImageBounds(int upLeftX, int upLeftY);

        /**
         * Implementers must have actually created bounds before calling this function.  This should be done
         * in computeImageBounds!
         * @return
         */
        public Rectangle2D getImageBounds() {
            return bounds;
        }
}
