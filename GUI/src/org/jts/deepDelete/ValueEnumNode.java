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

import com.u2d.generated.ValueEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * A TreeRepNode wrapping a value enum instance.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class ValueEnumNode extends ConcreteTreeRepNode {

    private ValueEnum valueEnum = null;

    /**
     * Create a ValueEnumNode wrapping the given ValueEnum element.
     * @param element
     */
    private ValueEnumNode(ValueEnum element) {
        super(element,element.getID(), "ValueEnum", element.getName().toString());
        valueEnum = element;
    }

    /**
     * Static factory method that creates a ValueEnumNode and adds it to the tree representation.
     * @param valueEnum
     * @param parent
     * @param nodeSet
     * @param leafSet
     */
    public static void addInstance(ValueEnum valueEnum, ConcreteTreeRepNode parent, MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new ValueEnumNode(valueEnum);
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
        // value enums have no child elements.
        return false;
    }

    /**
     * See ConcreteTreeRepNode.getDependantsInDatabase
     * @param hbSession
     * @return
     */
    @Override
    public List<TreeRepNode> getDependantsInDatabase(Session hbSession) {
        List<TreeRepNode> dependants = new ArrayList<TreeRepNode>();

        // find the ValueSet dependants of this ValueEnum in database.
        String queryValSets =
                "select valSet from ValueSet valSet "
                + "join valSet.valueEnums valEnum "
                + "where valEnum.id=:ID";

        Query hibQuery = hbSession.createQuery(queryValSets);
        hibQuery.setParameter("ID", this.valueEnum.getID());

        List valSetsResult = hibQuery.list();

        // create node wrapping each found element.
        for (Object obj : valSetsResult) {
            dependants.add(new ValueSetNode(DeepDeleteUtil.getProxyImpl((com.u2d.generated.ValueSet)obj)));
        }

        return dependants;
    }
}
