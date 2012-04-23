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

import com.u2d.generated.ComplexField;
import com.u2d.generated.EventDef;
import com.u2d.generated.Footer;
import com.u2d.generated.MessageDef;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * TreeRepNode wrapping a footer element.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class FooterNode extends ConcreteTreeRepNode {

    Footer footer = null;

    /**
     * Create a FooterNode wrapping the given Footer element.
     * @param element
     */
    public FooterNode(Footer element) {
        super(element,element.getID(), "Footer", element.getName().toString());
        footer = element;
    }

    /**
     * Static factory method that creates a FooterNode and adds it to the tree representation.
     * @param footer
     * @param parent
     * @param nodeSet
     * @param leafSet
     */
    public static void addInstance(Footer footer, ConcreteTreeRepNode parent, MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new FooterNode(footer);
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
        ComplexField complexField = footer.getComplexField();

        // the complex field may be null/empty.
        if (complexField != null) {
            DeepDeleteUtil.addNodeForComplexType(complexField, this, nodeSet, leafSet);
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
        ArrayList dependants = new ArrayList<TreeRepNode>();

        // footer may be used by message_def and event_def.

        // find message_def parents in database.
        String queryMessageDefs =
                "select messageDef from MessageDef messageDef "
                + "join messageDef.footer footer "
                + "where footer.id=:ID";

        Query hibQuery = hbSession.createQuery(queryMessageDefs);
        hibQuery.setParameter("ID", footer.getID());

        List messageDefs = hibQuery.list();

        // create node wrapping each found record.
        for (Object obj : messageDefs) {
            dependants.add(new MessageDefNode(DeepDeleteUtil.getProxyImpl((MessageDef)obj)));
        }

        // find event_def parents in database.
        String queryEventDefs =
                "select eventDef from EventDef eventDef "
                + "join eventDef.footer footer "
                + "where footer.id=:ID";

        Query hibQuery2 = hbSession.createQuery(queryEventDefs);
        hibQuery2.setParameter("ID", footer.getID());

        List eventDefs = hibQuery2.list();

        // create node wrapping each found record.
        for (Object obj : eventDefs) {
            dependants.add(new EventDefNode(DeepDeleteUtil.getProxyImpl((EventDef)obj)));
        }

        return dependants;
    }

}
