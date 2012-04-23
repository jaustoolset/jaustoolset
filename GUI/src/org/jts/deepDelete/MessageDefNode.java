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

import com.u2d.generated.InputSet;
import com.u2d.generated.MessageDef;
import com.u2d.generated.OutputSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * A TreeRepNode wrapping a message def instance.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class MessageDefNode extends ConcreteTreeRepNode{

    MessageDef messageDef = null;

    /**
     * Create a MessageDefNode wrapping the given MessageDef element.
     * @param element
     */
    MessageDefNode(MessageDef element) {
        super(element,element.getID(), "MessageDef", element.getName().toString());
        messageDef = element;
    }

    /**
     * Static factory method that creates a MessageDefNode and adds it to the tree representation.
     * @param messageDef
     * @param parent
     * @param nodeSet
     * @param leafSet
     */
    public static void addInstance(MessageDef messageDef, ConcreteTreeRepNode parent, MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new MessageDefNode(messageDef);
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
        // addHeaderBodyFooterNodes creates nodes for the header, body, and footer, then adds the new nodes to nodeSet.
        DeepDeleteUtil.addHeaderBodyFooterNodes(messageDef, this, nodeSet, leafSet);

        // message def always has children.
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

        // message defs are use by input sets and output sets.

        // find input sets in database that use this message def.
        String queryInputSets =
                "select inputSet from InputSet inputSet "
                + "join inputSet.messageDefs messageDef "
                + "where messageDef.id=:ID";

        Query hibQuery = hbSession.createQuery(queryInputSets);
        hibQuery.setParameter("ID", messageDef.getID());

        List inputSets = hibQuery.list();

        // create node wrapping each found input set
        for (Object obj : inputSets) {
            dependants.add(new InputSetNode(DeepDeleteUtil.getProxyImpl((InputSet)obj)));
        }

        // find output sets in database that use this message def.
        String queryOutputSets =
                "select outputSet from OutputSet outputSet "
                + "join outputSet.messageDefs messageDef "
                + "where messageDef.id=:ID";

        Query hibQuery2 = hbSession.createQuery(queryOutputSets);
        hibQuery2.setParameter("ID", messageDef.getID());

        List outputSets = hibQuery2.list();

        // create node wrapping each found output set.
        for (Object obj : outputSets) {
            dependants.add(new OutputSetNode(DeepDeleteUtil.getProxyImpl((OutputSet)obj)));
        }

        return dependants;
    }

}
