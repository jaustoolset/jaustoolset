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

package org.jts.deepDelete;

import com.u2d.generated.VariableFormatField;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

/**
 * A TreeRepNode wrapping a variable format field instance.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class VariableFormatFieldNode extends ConcreteTreeRepNode {

    VariableFormatField variableFormatField = null;

    /**
     * Create a VariableFormatFieldNode wrapping the given VariableFormatField element.
     * @param element
     */
    VariableFormatFieldNode(VariableFormatField element) {
        super(element,element.getID(), "VariableFormatField", element.getName().toString());
        variableFormatField = element;
    }

    /**
     * Static factory method that creates a VariableFormatField and adds it to the tree representation.
     * @param variableFormatField
     * @param parent
     * @param nodeSet
     * @param leafSet
     */
    public static void addInstance(VariableFormatField variableFormatField, ConcreteTreeRepNode parent, MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new VariableFormatFieldNode(variableFormatField);
        ConcreteTreeRepNode.addNodeToTree(newNode, parent, nodeSet, leafSet);
    }

    /**
     * See ConcreteTreeRepNode.makeChildren
     * @param nodeSet
     * @param leafSet
     * @return
     */
    @Override
    protected boolean makeChildren(MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        // variable_format_field must have a format_field
        FormatFieldNode.addInstance(variableFormatField.getFormatField(), this, nodeSet, leafSet);

        // note variable_format_field has a count_field in JSIDL, but in our database representation the count_field
        // is stored along with the variable format field so we don't need a count field node.

        return true;
    }

    /**
     * See ConcreteTreeRepNode.getDependantsInDatabase
     * @param hbSession
     * @return
     */
    @Override
    public List<TreeRepNode> getDependantsInDatabase(Session hbSession) {
        return DeepDeleteUtil.getSimpleFieldDependantsFromDB(variableFormatField, hbSession);
    }

}
