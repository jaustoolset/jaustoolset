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

import com.u2d.generated.FixedField;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

/**
 * TreeRepNode wrapping a fixed field.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class FixedFieldNode extends ConcreteTreeRepNode {

    private FixedField fixedField = null;

    /**
     * Create a FixedFieldNode wrapping the given FixedField element.
     * @param element
     */
    FixedFieldNode(FixedField element) {
        super(element,element.getID(), "FixedField", element.getName().toString());
        fixedField = element;
    }

    /**
     * Static factory method that creates a FixedFieldNode and adds it to the tree representation.
     * @param fixedField
     * @param parent
     * @param nodeSet
     * @param leafSet
     */
    public static void addInstance(FixedField fixedField, ConcreteTreeRepNode parent, MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new FixedFieldNode(fixedField);
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

        // scale range and value set are optional.
        boolean childrenPresent = false;

        if (fixedField.getValueSet() != null) {
            ValueSetNode.addInstance(fixedField.getValueSet(), this, nodeSet, leafSet);

            childrenPresent = true;
        }

        return childrenPresent;
    }

    /**
     * See ConcreteTreeRepNode.getDependantsInDatabase
     * @param hbSession
     * @return
     */
    @Override
    public List<TreeRepNode> getDependantsInDatabase(Session hbSession) {
        return DeepDeleteUtil.getSimpleFieldDependantsFromDB(fixedField, hbSession);
    }

}
