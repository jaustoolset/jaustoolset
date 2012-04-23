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

package org.jts.docGenerator;

import org.jts.jsidl.binding.ServiceDef;
import org.jts.jsidl.binding.ServiceSet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Provides methods for creating PNG images displaying the protocol behavior diagrams for ServiceDefs.  Note these
 * methods currently don't produce useful output unless the JTS user has saved MXE files for the service defs'
 * protcol descriptions.  If a service def's MXE file does not exist, the image output for that service def is
 * a placeholder image.  This is an issue with how the protcol behavior editor is designed.
 * @author idurkan
 */
public class ProtocolImageGenerator {

    /**
     * Creates protocol behavior diagrams for a List of ServiceDefs.  Each image is output into a subdirectory under
     * destDir, and the image itself is called "protocolBehavior.png".
     * @param destDir
     *     Directory where the images will be output
     * @param serviceDefList
     *     Abstract list of ServiceDefs whose diagrams will be output
     */
    public static void generateProtocolBehaviorImagesForServiceDefs( File destDir, List<ServiceDef> serviceDefList) {
        
        // loop through for each service def in the list
        for (int jj = 0; jj < serviceDefList.size(); jj++) {
            ServiceDef sDef = (ServiceDef) serviceDefList.get(jj);

            // directory where image for the service definition will go
            File sDefDir = new File(destDir, sDef.getName());

            // Lookup the service in the db
            com.u2d.generated.ServiceDef jmServiceDef = org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef(
                    sDef.getId(), sDef.getVersion());

            if (jmServiceDef != null) {

                Image image = jmServiceDef.getProtocolBehavior().getImage().imageValue().getImage();

                if (image != null) {

                    // Image.getWidth and Image.getHeight require an ImageObserver be passed in.
                    // Create a dummy ImageObserver.  See Java API on Image class for their rationale.
                    ImageObserver observer = new ImageObserver() {
                        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                            return false;
                        }
                    };

                    // create a BufferedImage, render the image onto its Graphics, then output
                    // the BufferedImage as a file.
                    BufferedImage bufImage = new BufferedImage(image.getWidth(observer), image.getHeight(observer),
                            BufferedImage.TYPE_INT_ARGB);
                    Graphics canvas = bufImage.getGraphics();
                    canvas.drawImage(image, 0, 0, observer);

                    try {
                        
                        if (!sDefDir.exists()) {
                            sDefDir.mkdirs();
                        }
                        
                        ImageIO.write(bufImage, "png", new File(sDefDir + File.separator + "protocolBehavior.png"));

                    } catch (IOException ioex) {
                        throw new RuntimeException("IO error while outputting a protocol behavior image: ", ioex);
                    }
                } 
            }
        }
   }

    /**
     * Operates on a list of ServiceSets, outputting protocol behavior diagrams for each ServiceDef in each ServiceSet.
     * Each ServiceSet gets a directory below destDir, and each service def's diagram is output into a subdirectory
     * below.
     * @param destDir
     *     Directory where output is generated.
     * @param serviceSetList
     *     Contains many service defs in service sets.
     */
   public static void generateProtocolBehaviorImagesForServiceSets( File destDir, List<ServiceSet> serviceSetList) {
        // loop through for each service set in the list
        for (int ii = 0; ii < serviceSetList.size(); ii++) {
            ServiceSet sSet = (ServiceSet) serviceSetList.get(ii);
            List<ServiceDef> serviceDefinitionList = sSet.getServiceDef();

            // directory where image output for current service set will go.
            File sSetDir = new File(destDir, sSet.getName());

            generateProtocolBehaviorImagesForServiceDefs(sSetDir, serviceDefinitionList);
        }
    }

}
