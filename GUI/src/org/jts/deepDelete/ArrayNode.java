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

import com.u2d.generated.Array;
import com.u2d.generated.Dimension;
import com.u2d.generated.SimpleField;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

/**
 * A TreeRepNode wrapping an array instance.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class ArrayNode extends ConcreteTreeRepNode {

    private Array array = null;

    /**
     * Create an ArrayNode wrapping the given Array element.
     * @param element
     */
    public ArrayNode(Array element) {
        super(element,element.getID(), "Array", element.getName().toString());
        array = element;
    }

    /**
     * Static factory method that creates an ArrayNode and adds it to the tree representation.
     * @param array
     * @param parent
     * @param nodeSet
     * @param leafSet
     */
    public static void addInstance(Array array, ConcreteTreeRepNode parent, MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new ArrayNode(array);
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
        // get all dimensions
        List<Dimension> arrays = array.getDimensions().getItems();
        for (Dimension dimension : arrays) {
            DimensionNode.addInstance(dimension, this, nodeSet, leafSet);
        }

        // contents of getItems should be no other type than SimpleField.
        SimpleField simpleField = array.getArrayElementType();

        DeepDeleteUtil.addNodeForSimpleType(simpleField, this, nodeSet, leafSet);

        // array must have children.
        return true;
    }

    /**
     * See ConcreteTreeRepNode.getDependantsInDatabase
     * @param hbSession
     * @return
     */
    @Override
    public List<TreeRepNode> getDependantsInDatabase(Session hbSession) {
        return DeepDeleteUtil.getSimpleFieldDependantsFromDB(this.array, hbSession);
    }

}
