/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
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
package org.jts.protocolvalidator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;

/**
 * Used to write JTS Promela code to a file.
 * @author cmessmer
 */
public class JTSFileWriter {

    // writer used to write the file output
    protected BufferedWriter out;
    // path to the file being written
    protected String filePath;
    // ID for the JSIDL binding going to this file
    protected String ID;
    // version for the JSIDL
    protected String version;

    /**
     * Create writer for a file
     * @param newFilePath - path for the file
     * @param id - JSIDL id
     * @param version - JSIDL version
     */
    JTSFileWriter(String newFilePath, String id, String version) throws Exception {
        filePath = newFilePath;
        ID = id;
        this.version = version;
        try {
            File tmpFile = new File(newFilePath);

             if (!tmpFile.exists()) {
                if (!tmpFile.getParentFile().exists()) {
                   boolean success = tmpFile.getParentFile().mkdirs();
                    if(!success)
                    {
                        Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                            "Failed to create " +tmpFile.getParentFile());
                        throw new Exception("Failed to create " +tmpFile.getParentFile());
                    }
                }
                tmpFile.createNewFile();
            }

            out = new BufferedWriter(new FileWriter(newFilePath));

        } catch (java.io.IOException ex) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                    "Failed to create " + newFilePath + ":  " + ex.getMessage(), ex);
            throw new Exception("Failed to create " + newFilePath);
        }
        writeFileHeader();
    }

    /**
     * Create writer for a file
     * @param newFilePath - path for the file
     * @param includelist - list of files that need to be included by this file
     * @param id - JSIDL id
     * @param version - JSIDL version
     */
    JTSFileWriter(String newFilePath, List<String> includelist, String id, String version) throws IOException {
        filePath = newFilePath;
        ID = id;
        this.version = version;
        try {
            File tmpFile = new File(newFilePath);
            if (!tmpFile.exists()) {
                if (!tmpFile.getParentFile().exists()) {
                    tmpFile.getParentFile().mkdirs();
                }
                tmpFile.createNewFile();
            }

            out = new BufferedWriter(new FileWriter(newFilePath));

        } catch (java.io.IOException ex) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                    "Failed to create " + newFilePath + ":  " + ex.getMessage(), ex);
            throw ex;
        }
        writeFileHeader();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        out.close();
    }

    /**
     * Writes lines of text to the file
     * @param data - the data to be written
     */
    protected void writeDataToFile(List<String> data) throws IOException {
        for (Iterator<String> tmpItr = data.iterator(); tmpItr.hasNext();) {
            String line = tmpItr.next();
            {
                out.write(line + "\n");
            }
        }
        out.flush();
    }

    /**
     * Writes out the file header comments
     */
    protected void writeFileHeader() throws IOException {
            out.write("/** \n");
            out.write(" *  This Promela file was auto-generated using JTS on " + Calendar.getInstance().getTime() + ".\n");
            out.write(" *  ID=" + ID + "  vsersion=" + version + "\n");
            out.write(" *  DO NOT MODIFY\n");
            out.write(" *  This file contains type definitions found in the JSIDL that was used to generate it.\n");
            out.write(" */ \n\n");
            out.flush();
    }

    /**
     * Retrieve the file path for this object
     * @return - copy of the file path to which this writer is writing.
     */
    public String getFilePath() {
        return filePath;
    }
}
