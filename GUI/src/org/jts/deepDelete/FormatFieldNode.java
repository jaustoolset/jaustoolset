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

import com.u2d.generated.FormatEnum;
import com.u2d.generated.FormatField;
import com.u2d.generated.VariableFormatField;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * A TreeRepNode wrapping a format field instance.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class FormatFieldNode extends ConcreteTreeRepNode {

    FormatField formatField = null;

    /**
     * Create a FormatFieldNode wrapping the given FormatField element.
     * @param element
     */
    FormatFieldNode(FormatField element) {
        super(element,element.getID(), "FormatField", "FormatField (nameless)");
        formatField = element;
    }

    /**
     * Static factory method that creates a FormatFieldNode and adds it to the tree representation.
     * @param formatField
     * @param parent
     * @param nodeSet
     * @param leafSet
     */
    public static void addInstance(FormatField formatField, ConcreteTreeRepNode parent,
            MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new FormatFieldNode(formatField);
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
        // format_field has one or more format_enums inside
        if (!formatField.getFormatEnums().isEmpty()) {
            for (Object formEnumObj : formatField.getFormatEnums().getItems()) {
                FormatEnum formEnum = (FormatEnum) formEnumObj;
                FormatEnumNode.addInstance(formEnum, this, nodeSet, leafSet);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * See ConcreteTreeRepNode.getDependantsInDatabase
     * @param hbSession
     * @return
     */
    @Override
    public List<TreeRepNode> getDependantsInDatabase(Session hbSession) {
        ArrayList<TreeRepNode> dependants = new ArrayList<TreeRepNode>();

        // format fields are used by variable format fields

        // find variable format fields in database
        String queryVariableFormatFields =
                "select varFormatField from VariableFormatField varFormatField "
                + "join varFormatField.formatField formatField "
                + "where formatField.id=:ID";

        Query hibQuery = hbSession.createQuery(queryVariableFormatFields);
        hibQuery.setParameter("ID", formatField.getID());

        List variableFormatFields = hibQuery.list();

        // create node wrapping each found record.
        for (Object obj : variableFormatFields) {
            dependants.add(new VariableFormatFieldNode(DeepDeleteUtil.getProxyImpl((VariableFormatField)obj)));
        }

        return dependants;
    }
}
