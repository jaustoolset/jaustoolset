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

import com.u2d.generated.ServiceDef;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;

/**
 * ServiceDefinitionTreeRepresentation's methods create a "tree representation" of a ServiceDef's structure to 
 * facilitate finding out what elements of the service definition may be deleted and what elements must be retained due
 * to outside dependant service defs.
 *
 * Note that the representation is not really a tree, but rather a directed acyclic graph.  Each node wraps an element
 * of the service definition targetServiceDef.  Considering a service definition as a tree, with the service definition
 * itself as the tree's root, a ServiceDefinitionTreeRepresentation is an *inverted* representation of the tree.  Edges
 * are directed "up" the original tree, with multiple root nodes wrapping the leaf elements of the original service
 * definition tree.  All edges in the graph converge on a node wrapping the original tree's root, the ServiceDef 
 * element.
 *
 * When creating the graph, a set containing all nodes and a set containing only leaf nodes are also created.
 * Again--"leaf nodes" wrap elements that contained no sub-elements in the original ServiceDef tree, for example
 * FixedLengthStrings.  Node type names correspond to the types they wrap.  e.g. FixedFieldNode wraps an
 * instance of com.u2d.generated.FixedField.
 *
 * When determining which nodes may be deleted, the search begins at the nodes wrapping the leaves of the target
 * service definition, and works toward the service definition itself.
 *
 * See ConcreteTreeRepNode for a more complete description of the algorithms at work.
 *
 * @author idurkan
 */
class ServiceDefinitionTreeRepresentation {

    private ServiceDef targetServiceDef = null;
    private ConcreteTreeRepNode treeRoot = null;
    private Set<TreeRepNode> leafSet = null;
    private MutableSet<TreeRepNode> nodeSet = null;
    private Set<TreeRepNode> retainedSet = null;
    private boolean populated = false;
    private boolean computedRetainedSet = false;

    /**
     * Creates a ServiceDefinitionTreeRepresentation targetting targetServiceDef for deletion.
     * @param targetServiceDef
     */
    public ServiceDefinitionTreeRepresentation(ServiceDef targetServiceDef) {
        this.targetServiceDef = targetServiceDef;
        leafSet = new HashSet<TreeRepNode>();
        nodeSet = new MutableSet<TreeRepNode>();
        retainedSet = new HashSet<TreeRepNode>();
    }

    /**
     * Recursively creates the service definition graph/tree.
     */
    public void populate() {
        ServiceDefinitionNode.addInstance(targetServiceDef, nodeSet, leafSet);

        populated = true;
    }

    /**
     * Traverses the graph, determining which nodes' wrapped elements have dependant elements in the database that are
     * outside of targetServiceDef.
     * @param hbSession
     */
    public void computeRetainedSet(Session hbSession) {
                // search from each leaf toward the root for elements with dependers outside the service
        for (TreeRepNode leafNode : leafSet) {
            // fill in the retained nodes set based on what elements in the database outside the service in question
            // are found to depend on elements inside the service.  search starts at the leaf nodes.
            leafNode.expandRetainedSet(nodeSet.asSet(), retainedSet, hbSession);
        }

        computedRetainedSet = true;
    }

    /**
     * Returns the list of leaf TreeRepNodes within the tree representation.  These correspond with
     * elements within the service definition whose contents are empty, or elements that cannot have
     * contents.  Throws an IllegalStateException if populate() has not been called successfully.
     * @return
     */
    Set<TreeRepNode> getLeafNodeSet() {
        if (populated) {
            return leafSet;
        } else {
            throw new IllegalStateException("getleafSet called before calling populate().");
        }
    }

    /**
     * Returns a Set containing TreeRepNodes representing every element within the service definition
     * including the service definition.  Throws an IllegalStateException if populate() has not
     * been called successfully.
     * @return
     */
    Set<TreeRepNode> getAllNodeSet() {
        if (populated) {
            return nodeSet.asSet();
        } else {
            throw new IllegalStateException("getleafSet called before calling populate().");
        }
    }

    /**
     * Returns a set containing the TreeRepNodes that must be retained during the deep delete
     * operation, due to dependencies on them outside the target service definition.  This set
     * is not available until populate() and computeRetainedSet() have been called.
     * @return
     */
    Set<TreeRepNode> getRetainedNodeSet() {
        if (populated && computedRetainedSet) {
            return retainedSet;
        } else {
            throw new IllegalStateException("populate() and computeRetainedSet() not called before "
                    + "calling getRetainedNodeSet");
        }
    }
}
