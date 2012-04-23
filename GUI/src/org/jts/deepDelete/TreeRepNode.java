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

import java.util.List;
import java.util.Set;
import org.hibernate.Session;

/**
 * Interface defining operations supported by all nodes in the service definition tree representation graph.  Most
 * methods are implemented in ConcreteTreepRepNode.  See ServiceDefinitionTreeRepresentation for more information.
 * Note "Rep" stands for "representation" here.
 *
 * See ConcreteTreeRepNode for a more complete description of the algorithms at work.
 * @author idurkan
 */
interface TreeRepNode {
    /**
     * Should add the node and its parents to the retainedSet, if node's wrapped instance is part of the retained set.
     * @param elementSet
     * @param retainedSet
     * @param session
     * @return
     */
    public boolean expandRetainedSet(Set<TreeRepNode> elementSet,
            Set<TreeRepNode> retainedSet, Session session);

    /**
     * Should delete the node's wrapped instance from the Hibernate database.
     * @param session
     */
    public void deleteFromDatabase(Session session);

    /**
     * Returns the entire list of parents for the node.
     * @return
     */
    List<TreeRepNode> getParents();

    /**
     * Returns the first parent in the node's list of parents.
     * @return
     */
    TreeRepNode getFirstParent();

    /**
     * Adds a new parent to the node's list of parents.
     * @param newParent
     * @return
     */
    boolean addParent(TreeRepNode newParent);

    /**
     * should produce a string describing the node, for debug purposes.
     * @return
     */
    public String getDebugInfo();

    /**
     * Should return a String representing the wrapped instance's name.
     * @return
     */
    public String getName();

    /**
     * Should return a String indicating the wrapped instance's element type.
     * @return
     */
    public String getTypeName();

    /**
     * Should return the wrapped instance's Hibernate database ID.
     * @return
     */
    public String getID();
}
