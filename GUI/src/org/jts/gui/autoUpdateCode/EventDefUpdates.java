/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jts.gui.autoUpdateCode;

import java.io.*;

/**
 *
 * @author idurkan
 */
public class EventDefUpdates {
    public EventDefUpdates() {
        try {
            String targetFilename = "src" + File.separator + "com" + File.separator + "u2d" + File.separator +
                    "generated" + File.separator + "EventDef.java";

            // add "implements HasHeaderBodyFooter" to class type.
            FindReplace.replaceInFile( targetFilename, targetFilename, "extends AbstractComplexEObject_JTS{",
                    "extends AbstractComplexEObject_JTS implements HasHeaderBodyFooter {");

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
