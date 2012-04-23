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

import com.u2d.generated.BitField;
import com.u2d.generated.SubField;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * A TreeRepNode wrapping a subfield instance.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class SubFieldNode extends ConcreteTreeRepNode{

    private SubField subField = null;

    /**
     * Create a SubFieldNode wrapping the given SubField element.
     * @param element
     */
    SubFieldNode(SubField element) {
        super(element,element.getID(), "SubField", element.getName().toString());
        subField = element;
    }

    /**
     * Static factory method that creates a SubFieldNode and adds it to the tree representation.
     * @param subField
     * @param parent
     * @param nodeSet
     * @param leafSet
     */
    public static void addInstance(SubField subField, ConcreteTreeRepNode parent, MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new SubFieldNode(subField);
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
//  * TODO: database has table for BitRange, but SubFields store their BitRange as a
//   component instead of having a relationship to the BitRange table.
// * Need to discuss this issue with team.  Will remove this class if it's not needed.
//      BitRangeNode.addInstance(subField.getBitRange(), this, nodeSet, leafSet);

        ValueSetNode.addInstance(subField.getValueSet(), this, nodeSet, leafSet);

        // sub_field must have children.
        return true;
    }

    /**
     * See ConcreteTreeRepNode.getDependantsInDatabase
     * @param hbSession
     * @return
     */
    @Override
    public List<TreeRepNode> getDependantsInDatabase(Session hbSession) {
        ArrayList<TreeRepNode> dependants = new ArrayList<TreeRepNode>();

        // subfields are used by bit fields

        // find bit fields that use this subfield.
        String queryBitfields =
                "select bitField from BitField bitField "
                + "join bitField.subFields subField "
                + "where subField.id=:ID";

        Query hibQuery = hbSession.createQuery(queryBitfields);
        hibQuery.setParameter("ID", subField.getID());

        List bitFields = hibQuery.list();

        // create node wrapping each found record.
        for (Object obj : bitFields) {
            dependants.add(new BitFieldNode(DeepDeleteUtil.getProxyImpl((BitField)obj)));
        }

        return dependants;
    }

}
