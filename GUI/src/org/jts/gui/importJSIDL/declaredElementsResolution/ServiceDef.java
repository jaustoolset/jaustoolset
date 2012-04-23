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
package org.jts.gui.importJSIDL.declaredElementsResolution;

import org.jts.gui.importJSIDL.ImportException;
import java.util.Map;

public class ServiceDef {
    /**
     * Transform the given JAXB ServiceDef representation with declared elements into another ServiceDef
     * with only concrete elements, and no declared elements.
     * @param jxServiceDef
     * @param map A mapping from JSIDL entity IDs to JAXB JSIDL object instances.
     * @return A "flattened" ServiceDef, with all declared elements filled-in with concrete instances.
     * @throws ImportException
     */
    public static org.jts.jsidl.binding.ServiceDef resolveDeclaredElements(org.jts.jsidl.binding.ServiceDef jxServiceDef,
            Map map) throws ImportException {
        try {

            // Import declared type sets
            if (jxServiceDef.getDeclaredTypeSet() != null) {
                DeclaredTypeSet.importDeclaredTypeSet(jxServiceDef.getDeclaredTypeSet(), map);
                DeclaredTypeMap.getInstance().setPackage(jxServiceDef.getDeclaredTypeSet().getName());
            }

            // Import declared constants
            if (jxServiceDef.getDeclaredConstSet() != null) {
                DeclaredConstantSet.resolveReferencedConstantSets(jxServiceDef.getDeclaredConstSet(), map);
            }

            // Resolve any declared types..
            // Declared types can only exist in the InputSet, OutputSet, InternalEvent entities (and their children)

            // InputSet
            if (jxServiceDef.getMessageSet() != null && jxServiceDef.getMessageSet().getInputSet() != null) {
                InputSet.resolveDeclaredElements(jxServiceDef.getMessageSet().getInputSet());
            }

            // OutputSet
            if (jxServiceDef.getMessageSet() != null && jxServiceDef.getMessageSet().getOutputSet() != null) {
                OutputSet.resolveDeclaredElements(jxServiceDef.getMessageSet().getOutputSet());
            }

            // Internal Events
            if (jxServiceDef.getInternalEventsSet() != null) {
                InternalEventsSet.resolveDeclaredElements(jxServiceDef.getInternalEventsSet());
            }

            // use constants stored in mapping to replace constants in any field values that have constants within the service definition
            if (jxServiceDef != null) {
                // InputSet
                if (jxServiceDef.getMessageSet() != null && jxServiceDef.getMessageSet().getInputSet() != null) {
                    InputSet.resolveDeclaredConstantElements(jxServiceDef.getMessageSet().getInputSet());
                }

                // OutputSet
                if (jxServiceDef.getMessageSet() != null && jxServiceDef.getMessageSet().getOutputSet() != null) {
                    OutputSet.resolveDeclaredConstantElements(jxServiceDef.getMessageSet().getOutputSet());
                }

                // Internal Events
                if (jxServiceDef.getInternalEventsSet() != null) {
                    InternalEventsSet.resolveDeclaredConstantElements(jxServiceDef.getInternalEventsSet());
                }
            }

            DeclaredTypeMap.getInstance().setPackage(null);
            DeclaredConstantMap.getInstance().setPackage(null);

        } catch (ImportException ex) {
            // empty the type and constant maps so no 'corrupt' preexisting mappings can interfere with
            // future import operations.
            DeclaredTypeMap.getInstance().clear();
            DeclaredConstantMap.getInstance().clear();
            throw ex;
        } catch (Exception ex) {
            // same as above.
            DeclaredTypeMap.getInstance().clear();
            DeclaredConstantMap.getInstance().clear();
            throw new RuntimeException(ex);
        }

        return jxServiceDef;

    }
}
