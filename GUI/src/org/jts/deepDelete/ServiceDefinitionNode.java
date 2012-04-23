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

import com.u2d.generated.EventDef;
import com.u2d.generated.ServiceDef;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

/**
 * A TreeRepNode wrapping a service definition instance.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class ServiceDefinitionNode extends ConcreteTreeRepNode {

    private ServiceDef serviceDef;

    /**
     * Create a ServiceDefinitionNode wrapping the given ServiceDefinition element.
     * @param element
     */
    ServiceDefinitionNode(ServiceDef element) {
        super(element,element.getID(), "ServiceDef", element.getName().toString());
        serviceDef = element;
    }

    /**
     * Static factory method that creates a ServiceDefNode and adds it to the tree representation.
     * @param serviceDef
     * @param nodeSet
     * @param leafSet
     */
    public static void addInstance(ServiceDef serviceDef, MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new ServiceDefinitionNode(serviceDef);
        ConcreteTreeRepNode.addNodeToTree(newNode, null, nodeSet, leafSet);
    }

    /**
     * See ConcreteTreeRepNode.makeChildren
     * @param nodeSet
     * @param leafSet
     * @return
     */
    @Override
    protected boolean makeChildren(MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        // recursively construct the nodes for input set, output set, event defs, and constant set and protocol behavior
        InputSetNode.addInstance(serviceDef.getInputSet(), this, nodeSet, leafSet);
        OutputSetNode.addInstance(serviceDef.getOutputSet(), this, nodeSet, leafSet);

        for (Object eventDef : serviceDef.getEventDefs().getItems()) {
            EventDefNode.addInstance((EventDef)eventDef, this, nodeSet, leafSet);
        }

        // protocol behavior may be nonexistent
        if (serviceDef.getProtocolBehavior() != null) {
            ProtocolBehaviorNode.addInstance(serviceDef.getProtocolBehavior(),
                    this, nodeSet, leafSet);
        }

        // constant set may be nonexistent
        if (serviceDef.getConstantSet() != null) {
            ConstantSetNode.addInstance(serviceDef.getConstantSet(),
                    this, nodeSet, leafSet);
        }

        // the service def itself can't be a leaf.
        return true;
    }

    /**
     * See ConcreteTreeRepNode.getDependantsInDatabase
     * @param hbSession
     * @return
     */
    @Override
    public List<TreeRepNode> getDependantsInDatabase(Session hbSession) {
        // the service definition's dependants are dealt with elsewhere.  at this point in the code,
        // we know there are no dependants preventing the service definition from being deleted.
        return new ArrayList<TreeRepNode>();
    }

}
