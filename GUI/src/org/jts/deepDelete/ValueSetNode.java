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
import com.u2d.generated.SubField;
import com.u2d.generated.TypeAndUnitsEnum;
import com.u2d.generated.ValueEnum;
import com.u2d.generated.ValueRange;
import com.u2d.generated.ValueSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * A TreeRepNode wrapping a value set instance.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class ValueSetNode extends ConcreteTreeRepNode {

    private ValueSet valueSet = null;

    /**
     * Create a ValueSetNode wrapping the given ValueSet element.
     * @param element
     */
    ValueSetNode(ValueSet element) {
        super(element,element.getID(), "ValueSet", element.getName().toString());
        valueSet = element;
    }

    /**
     * Static factory method that creates a ValueSetNode and adds it to the tree representation.
     * @param valueSet
     * @param parent
     * @param nodeSet
     * @param leafSet
     */
    static void addInstance(ValueSet valueSet, ConcreteTreeRepNode parent, MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new ValueSetNode(valueSet);
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
        boolean childrenPresent = false;

        for (Object valRangeObj : valueSet.getValueRanges().getItems()) {
            ValueRangeNode.addInstance((ValueRange)valRangeObj, this, nodeSet, leafSet);

            childrenPresent = true;
        }

        for (Object valEnumObj : valueSet.getValueEnums().getItems()) {
            ValueEnumNode.addInstance((ValueEnum)valEnumObj, this, nodeSet, leafSet);

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
        ArrayList dependants = new ArrayList<TreeRepNode>();

        // find TypeAndUnitsEnum dependants in database.
        String queryTUEnums =
                "select tuEnum from TypeAndUnitsEnum tuEnum "
                + "join tuEnum.valueSet valueSet "
                + "where valueSet.id=:ID";

        Query hibQuery = hbSession.createQuery(queryTUEnums);
        hibQuery.setParameter("ID", valueSet.getID());

        List TUEnums = hibQuery.list();

        // create node wrapping each found record.
        for (Object obj : TUEnums) {
            dependants.add(new TypeAndUnitsEnumNode(DeepDeleteUtil.getProxyImpl((TypeAndUnitsEnum)obj)));
        }

        // find FixedField dependants in database.
        String queryFixedFields =
                "select fixedField from FixedField fixedField "
                + "join fixedField.valueSet valueSet "
                + "where valueSet.id=:ID";

        Query hibQuery2 = hbSession.createQuery(queryFixedFields);
        hibQuery2.setParameter("ID", valueSet.getID());

        List fixedFields = hibQuery2.list();

        // create node wrapping each found record.
        for (Object obj : fixedFields) {
            dependants.add(new FixedFieldNode(DeepDeleteUtil.getProxyImpl((FixedField)obj)));
        }

        // find SubField dependants in database.
        String querySubFields =
                "select subField from SubField subField "
                + "join subField.valueSet valueSet "
                + "where valueSet.id=:ID";

        Query hibQuery3 = hbSession.createQuery(querySubFields);
        hibQuery3.setParameter("ID", valueSet.getID());

        List subFields = hibQuery3.list();

        // create node wrapping each found record.
        for (Object obj : subFields) {
            dependants.add(new SubFieldNode(DeepDeleteUtil.getProxyImpl((SubField)obj)));
        }

        return dependants;
    }

}
