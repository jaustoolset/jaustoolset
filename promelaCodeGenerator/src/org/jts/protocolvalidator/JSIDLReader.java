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

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 * Used to create the data binding for a specific IDL file
 * @author cmessmer
 */
public class JSIDLReader {
    // unmarshaller for the JSIDL data
    private Unmarshaller um;

    // path to the file being read
    String JSIDLPath;

    /**
     * Creates reader for a specified IDL file
     * @param inJSIDLPath - the path to the file that will be read
     * @param schemaPath - JSIDL schema path
     */
    public JSIDLReader(String inJSIDLPath, String schemaPath) throws JAXBException, SAXException
    {
        JSIDLPath = inJSIDLPath;
        LoadSchema(schemaPath);

    }

    /**
     * Loads the JSIDL schema
     * @param schemaPath - path to the schema file
     */
    private void LoadSchema(String schemaPath) throws JAXBException, SAXException {
        try {
            JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
            um = jc.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(schemaPath));
            um.setSchema(schema);

        } catch (JAXBException jaxbe) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                    jaxbe.getMessage(), jaxbe);
            throw jaxbe;
        } catch (SAXException saxe) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                    saxe.getMessage(), saxe);
            throw saxe;
        }
    }

    /**
     * Retrieve the root element for the JSIDL file
     * @return - root element for the JSIDL file
     */
    public Object getRootElement() throws JAXBException
    {
        Object obj = null;

        try
        {
            obj = um.unmarshal(new File(JSIDLPath));
        }
        catch (final JAXBException jaxbe)
        {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                    "Unable to unmarshal file" + JSIDLPath + " \n " + jaxbe.toString(), jaxbe);
            throw jaxbe;
        }

        return obj;
    }



}
