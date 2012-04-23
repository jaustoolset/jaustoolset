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

import com.u2d.generated.TypeAndUnitsEnum;
import com.u2d.generated.VariableField;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

/**
 * A TreeRepNode wrapping a variable field instance.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class VariableFieldNode extends ConcreteTreeRepNode {

    private VariableField variableField = null;

    /**
     * Create a VariableFieldNode wrapping the given VariableField element.
     * @param element
     */
    VariableFieldNode(VariableField element) {
        super(element,element.getID(), "VariableField", element.getName().toString());
        variableField = element;
    }

    /**
     * Static factory method that creates a VariableFieldNode and adds it to the tree representation.
     * @param VariableField
     * @param parent
     * @param nodeSet
     * @param leafSet
     */
    public static void addInstance(VariableField VariableField, ConcreteTreeRepNode parent, MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new VariableFieldNode(VariableField);
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
        List<TypeAndUnitsEnum> typeUnitsEnums = variableField.getTypeAndUnitsEnums().getItems();

        if (!typeUnitsEnums.isEmpty()) {
            for (TypeAndUnitsEnum typeUnitsEnum : typeUnitsEnums) {
                TypeAndUnitsEnumNode.addInstance(typeUnitsEnum, this, nodeSet, leafSet);
            }
        } else {
            throw new RuntimeException("VariableField with no TypeAndUnitsEnums - unexpected!");
        }

        // variable fields must have one or more type_and_units_field
        return true;
    }

    /**
     * See ConcreteTreeRepNode.getDependantsInDatabase
     * @param hbSession
     * @return
     */
    @Override
    public List<TreeRepNode> getDependantsInDatabase(Session hbSession) {
        return DeepDeleteUtil.getSimpleFieldDependantsFromDB(variableField, hbSession);
    }

}
