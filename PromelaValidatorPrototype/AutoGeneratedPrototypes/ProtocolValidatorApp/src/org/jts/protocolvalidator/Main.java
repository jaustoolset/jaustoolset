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

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Main class for the Promela code generator, used to convert JSIDL files into
 * a promela implementation for the state machine.
 * @author cmessmer
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        if(args.length < 3 || args.length > 4)
        {
            System.err.println("Error initializing Promela Protocol Validator. Invalid number of parameters");
            System.err.println("Usage:");
            System.err.println("java -jar validator.jar <main JSIDL file path> <JSIDL Dependency Search Path> <JSIDL Schema Path> <optional: Output Path>");
        }
        String mainJSIDLFilePath = args[0];
        String JSIDLSearchPath = args[1];
        String JSIDLSchemaPath = args[2];
        // set output path to use system temp directory for output
        String outputPath = System.getProperty("java.io.tmpdir") + "/JSIDL_Validation/PromelaOutput/";

        // override output path settings if specified by the user
        if(args.length > 3)
        {
            outputPath = args[3] + "JSIDL_Validation/PromelaOutput/";
        }
        try {
            ProtocolGenerator gen = new ProtocolGenerator(mainJSIDLFilePath, JSIDLSearchPath, JSIDLSchemaPath, outputPath);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
