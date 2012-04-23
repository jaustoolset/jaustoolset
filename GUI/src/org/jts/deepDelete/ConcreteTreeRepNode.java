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

import com.u2d.model.AbstractComplexEObject_JTS;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

/**
 * Defines functionality common to all TreeRepNode implementations.  Child classes must implement makeChildren
 * and getDependantsInDatabase.  Note "Rep" stands for "representation" here.
 *
 * The functions in ConcreteTreeRepNode are used in two stages of processing:
 *  1. Tree representation creation
 *  2. Retained-set computation
 *
 * 1. During tree representation creation, a ServiceDefinitionTreeRepresentation calls the addInstance method of
 * ServiceDefinitionNode, which creates a new ServiceDefinitionNode wrapping the ServiceDef to delete.  addInstance then
 * calls addNodeToTree, which stores the ServiceDefinitionNode in the set of all nodes, and calls 
 * ServiceDefinitionNode.makeChildren to determine what nodes must be created to wrap the service definition's child
 * elements.  ServiceDefinitionNode.makeChildren then calls addInstance for the new nodes it finds necessary, in a
 * pattern of mutual recursion:
 *  I.  A ConcreteTreeRepNode implementer's addInstance implementation creates a new node instance and calls
 *      addNodeToTree
 *  II. addNodeToTree adds the new node instance to the set of all nodes, then calls the node's makeChildren method.
 *  III.makeChildren recursively calls addInstance implementation for each child node it finds necessary...
 *  IV. When each makeChildren call eventually returns, if it returned false indicating no children were necessary,
 *      addNodeToTree adds the node in question to the set of leaf nodes.
 * 
 * During the whole process, directed graph edges between nodes are recorded as a list of references from each node N
 * to the nodes wrapping the elements that were N's parent(s) in the original service definition.  Call this list the
 * "recorded parent nodes".
 *
 * Note ConcreteTreeRepNode defines its own equals() and hashCode() methods that consider only the id and typename
 * fields.  During tree representation creation, the algorithm may encounter a particular element several times
 * due to the way a particular element can be reused in several places in a single service definition.  Each time
 * the same element is encountered, the resulting ConcreteTreeRepNode will have a hashcode that already exists in
 * the keys of the of all nodes, so that ConcreteTreeRepNode can be discarded from storage safely.
 *
 * 2. During retained-set computation, a ServiceDefinitionTreeRepresentation calls expandRetainedSet on every
 * node that was stored in the set of leaf nodes during tree representation generation.  expandRetainedSet calls
 * getDependantsInDatabase for this node.  Each ConcreteTreeRepNode implementor implements getDependantsInDatabase to
 * issue HQL queries to find out what elements in the database depend on its wrapped element, and returns these in a
 * List.  Returning to expandRetainedSet, this list of elements is checked for presence in the set of all nodes, and if
 * any elements are *not* found in the set of all nodes this means they are a "foreign" ancestor, and the wrapped
 * element must not be deleted (since something outside the target service def depends on it), so the node is added to
 * the retained set.
 *
 * expandRetainedSet then recursively calls into expandRetainedSet for each node in the recorded parent nodes for the
 * current node, further expanding the retained set.
 *
 * @author idurkan
 */
abstract class ConcreteTreeRepNode implements TreeRepNode {

    // implementer constructors should populate their recorded parent nodes.
    protected List<TreeRepNode> parents = new ArrayList<TreeRepNode>();
    // implementer constructors must populate their ID with a value unique within their type.
    // the AbstractComplexEObject.getID function should be used to get this number.
    protected Long id = null;
    // implementer constructors must populate this field with their type typename.
    // pairing id and typename will provide an ID that uniquely distinguishes a particular instance of a
    // particular type.
    protected String typename = null;
    // debugging purposes only
    protected String name = null;

    protected AbstractComplexEObject_JTS contents = null;

    /**
     * Populates fields common to all ConcreteTreeRepNodes.  Constructors in implementations of ConcreteTreeRepNode
     * should call this constructor via super() to provide these values which must be stored.
     *
     * @param contents The com.2d.generated type representing the ConcreteTreeRepNode's wrapped instance.
     *
     * @param id The Hibernate database ID corresponding to the instance in contents.
     *
     * @param typename The type of the instance, such as service_def, fixed_field, etc.
     *
     * @param name The name of the instance.
     */
    public ConcreteTreeRepNode(AbstractComplexEObject_JTS contents,Long id, String typename, String name) {
        this.contents = contents;
        this.id = id;
        this.typename = typename;
        this.name = name;
    }

    /**
     * Adds the node newNode to the service definition tree representation if no node with matching ID/type is found
     * in nodeSet, then recursively adds nodes for the newNode's wrapped instance's children.    If a matching node
     * is found, newNode's parent is added to the existing node's parents, but newNode itself is discarded.
     * Note that addNodeToTree is intended to be mutually recursive with makeChildren.
     *
     * @param newNode A new TreeRepNode to add to the tree representation.
     *
     * @param parent A TreeRepNode that will be added as TreeRepNode's parent (or as the matching previously-existing
     *      node's parent).
     *
     * @param nodeSet The MutableSet of all nodes in the service definition tree representation.
     *
     * @param leafSet The set of all leaf nodes in the service definition tree representation (nodes with no children)
     */
    static void addNodeToTree(ConcreteTreeRepNode newNode, ConcreteTreeRepNode parent,
            MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {

        TreeRepNode existingNode = nodeSet.get(newNode); // returns null if object not found.

        // node already exists?
        if (existingNode != null) {
            if (parent != null) {
                existingNode.addParent(parent);
            }
        } else {
            // tree must be populated with node's children.
            if (parent != null) {
                newNode.addParent(parent);
            }
            nodeSet.add(newNode);
            boolean hadChildren = newNode.makeChildren(nodeSet, leafSet);

            // no children?  it's a leaf.
            if (!hadChildren) {
                leafSet.add(newNode);
            }
        }

    }

    /**
     * Child classes must override makeChildren to add TreeRepNodes to the service definition tree representation for
     * their wrapped element's child elements.  makeChildren implementations are called during the populate() call
     * initiated in the ServiceDefinitionTreeRepresentation, by way of addNodeToTree, above.  
     * 
     * addNodeToTree calls makeChildren on its newNode argument after determining newNode is not already part of the
     * service definition tree representation.  The makeChildren must then determine what child nodes must be
     * created and each node's addInstance method.  addInstance implementations in turn call addNodeToTree.  Thus
     * makeChildren implementations are mutually recursive with addNodeToTree, in general.
     *
     * @param nodeSet The MutableSet of all nodes in the service definition tree representation.
     *
     * @param leafSet The set of all leaf nodes in the service definition tree representation (nodes with no children)
     *
     * @return Boolean value indicating whether the node was able to make child nodes.  If true, the node
     * had children that needed to have their own node(s) added via mutually recursive call to addNodeToTree.  If
     * false, the node had no children to add, or wraps an element that is naturally childless, such as a
     * fixed length string.
     */
    protected abstract boolean makeChildren(MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet);

    /**
     * adds the current node to the retained set if elements outside the target service definition
     * depend on it.  recurses into parents to find ancestral dependencies as well.
     * @param definitionNodes
     * @param retainedSet
     * @param session A Hibernate database session to use for finding dependants.
     * @return Boolean value indicating whether the current node or any of its ancestors within the
     * target service have dependants outside the target service.
     */
    @Override
    public final boolean expandRetainedSet(Set<TreeRepNode> definitionNodes, Set<TreeRepNode> retainedSet,
            org.hibernate.Session session) {

        // construct nodes wrapping ID info for hashcode/equals computation for elements in the database
        List<TreeRepNode> dbDependantNodes = getDependantsInDatabase(session);

        // will indicate whether the database contains dependants on this node's element that are not part of the target
        // service definition.
        boolean hasForeignDependant = false;

        for (TreeRepNode dependantNode : dbDependantNodes) {
            // is there a node that's not part of the target service definition?
            if (!definitionNodes.contains(dependantNode)) {
                hasForeignDependant = true;
                break;
            }
        }

        // will indicate whether parent nodes of this node have dependants that are not part of the target service
        // definition.
        boolean ancestorHasForeignDependant = false;

        // recurse into recorded parent nodes.
        for (TreeRepNode parentNode : this.parents) {
            boolean parentResult = parentNode.expandRetainedSet(definitionNodes, retainedSet, session);
            ancestorHasForeignDependant |= parentResult;
        }

        if (hasForeignDependant || ancestorHasForeignDependant) {
            retainedSet.add(this);
        }

        return hasForeignDependant || ancestorHasForeignDependant;
    }

    /**
     * Child classes must implement this method to return a list of all immediate dependants on this node's wrapped 
     * element found in the Hibernate database.  expandRetainedSet calls getDependantsInDatabase, allowing each
     * ConcreteTreeRepNode implementer to specify its own HQL queries for finding its dependants.
     * @param hbSession Hibernate database session.
     * @return
     */
    public abstract List<TreeRepNode> getDependantsInDatabase(org.hibernate.Session hbSession);

    /**
     * Deletes the node's wrapped element from the Hibernate database.
     * @param A Hibernate session to use
     */
    @Override
    public void deleteFromDatabase(Session session) {
        session.delete(contents);
    }

    /**
     * Returns list of all TreeRepNode parents of this ConcreteTreeRepNode.
     * @return
     */
    @Override
    public final List<TreeRepNode> getParents() {
        return parents;
    }

    /**
     * Get the first parent of the node (useful for determining if node has any parent at all)
     * @return
     */
    @Override
    public final TreeRepNode getFirstParent() {
        if (!parents.isEmpty()) {
            return parents.get(0);
        } else {
            return null;
        }
    }

    /**
     * Adds a parent to the ConcreteTreeRepNode's parent list if it's not already in that list.
     * @param newParent
     * @return True if the new parent was added, or false if the parent already existed in the list.
     */
    public final boolean addParent(TreeRepNode newParent) {
        if (!parents.contains(newParent)) {
            parents.add(newParent);
            return true;
        }
        return false;

    }

    /**
     * Indicates equality between this ConcreteTreeRepNode and another.  "Equality" is defined as the two instances
     * sharing the same ID and typename values, or reference equality.  Fields other than id and typename *are not
     * considered*.
     * @param other
     * @return Whether this node and the other are equal when considering their id and typename fields.
     */
    @Override
    public final boolean equals(Object other) {
        if (other == this) { // reference comparison
            return true;
        }
        if (!(other instanceof ConcreteTreeRepNode)) {
            return false;
        }

        // it has the correct type, so compare on the hashcode fields.
        ConcreteTreeRepNode otherNode = (ConcreteTreeRepNode) other;
        return (id.equals(otherNode.id)) && (typename.equals(otherNode.typename));
    }

    /**
     * Generates an int hash code for the ConcreteTreeRepNode based on the typename and id fields *alone*.
     * Note the mathematical computations were generated by Netbeans for this class.
     * @return A hash code blending the id and typename field values for this node.
     */
    @Override
    public final int hashCode() {
        // See http://computinglife.wordpress.com/2008/11/20/why-do-hash-functions-use-prime-numbers/
        // for explanation of the constant prime numbers below.
        final int PRIME_FACTOR = 67;
        int hash = 7;
        hash = PRIME_FACTOR * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = PRIME_FACTOR * hash + (this.typename != null ? this.typename.hashCode() : 0);
        return hash;
    }

    /**
     * Get name, type, ID, parent info for sake of debugging.
     * @return
     */
    @Override
    public String getDebugInfo() {
        ConcreteTreeRepNode parent = (ConcreteTreeRepNode) getFirstParent();
        return "Name = " + name + "  Typename = " + typename + "  ID = " + id 
                + "  Parent = " + (parent == null ? "(at root)" : parent.typename);
    }

    /**
     * Get the node's element's name
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the node's element's type name
     * @return
     */
    @Override
    public String getTypeName() {
        return typename;
    }

    /**
     * Get the node's element's database ID.
     * @return
     */
    @Override
    public String getID() {
        return id.toString();
    }
}
