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

import com.u2d.app.Context;
import com.u2d.generated.ServiceDef;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Allows "deep deletion" of ServiceDefs.
 *
 * Deep deletion deletes a target service definition *and* all of its sub-elements recursively, while not deleting those
 * sub-elements that have dependant elements outside the target ServiceDef.  For example, if two ServiceDefs S1 and S2
 * make use of the same message def M1, but only S1 uses message def M2, and a DeepDeleteProcessor were used to delete
 * S1, M1 would not be deleted, but M2 would be deleted.
 * 
 * Construct a DeepDeleteProcessor, supplying a ServiceDef as a target for deletion, then call
 * computeDeletionResults to determine what sub-elements of the target may be deleted and which sub-elements
 * must be retained.
 *
 * Use getDeletableElementsInfo and getRetainedElements to get string information to display to the user
 * enumerating and describing the deletable elements and retained elements, respectively.
 * computeDeletionResults must be called prior to calling either getDeletableElementsInfo or getRetainedElements.
 * 
 * Use performDeletion to actually remove the deletable elements from the database.  computeDeletionResults must be
 * called beforehand.
 *
 * getReferencingEntityInfo provides information about what service *sets* and service defs depend on the target
 * service def.  Service defs may depend on other service defs via inheritance.
 *
 * If service sets and/or other service defs depend on the target service def directly it should not be deleted.
 *
 * See ConcreteTreeRepNode for a more complete description of the algorithms at work.
 *
 * @author idurkan
 */
public class DeepDeleteProcessor {

    private ServiceDef targetServiceDef = null;
    private String targetDefNameStr = null;
    private boolean deletionResultsComputed = false;

    private Set<TreeRepNode> retainedNodeSet = null;
    private Set<TreeRepNode> deletableNodeSet = null;

    private Session hbSession = null;

    /**
     * Creates a DeepDeleteProcessor with targetServiceDef as the service definition targeted for deletion.
     * @param targetServiceDef
     */
    public DeepDeleteProcessor(ServiceDef targetServiceDef) {
        this.targetServiceDef = targetServiceDef;
        this.targetDefNameStr = targetServiceDef.getName().toString();

        retainedNodeSet = new HashSet<TreeRepNode>();
        deletableNodeSet = new HashSet<TreeRepNode>();

        // Get handle on a hibernate session
        com.u2d.persist.HBMSingleSession persistenceMechanism =
                (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism();
        hbSession = persistenceMechanism.getSession();
    }

    /**
     * Returns a list of String information about service definitions that reference the target service definition via
     * inheritance
     * @return List of strings with information about each service def that references the target service definition
     * via inheritance.
     */
    private List<String> getInheritingServiceDefInfo() {
        ArrayList<String> serviceDefInfo = new ArrayList<String>();

        List<ServiceDef> allDefs = hbSession.createQuery("from ServiceDef").list();

        if (allDefs == null || allDefs.isEmpty()) {
            throw new RuntimeException("Found no service definitions at all in database!");
        }

        for (ServiceDef otherDef : allDefs) {

            // note: The UID and version number make up the uniqueness criteria for a service def.
            // we find every other service def that inherits from a service def with the same
            // UID and version number as targetServiceDef.

            // the list of all defs contains targetServiceDef; a def's inheritsFrom field cannot be
            // set to itself, so ignore targetServiceDef itself in the search for inheriting service
            // defs.
            if (otherDef.getID().equals(targetServiceDef.getID())) {
                continue;
            }

            if (otherDef.getInheritsFrom() != null ) {
                String otherVersion = otherDef.getInheritsFrom().get_version().toString();
                String otherSvcId = otherDef.getInheritsFrom().getServiceId().toString();

                if (targetServiceDef.get_version().toString().equals(otherVersion)
                        && targetServiceDef.getServiceId().toString().equals(otherSvcId)) {
                    serviceDefInfo.add(
                            otherDef.getName().toString()
                        + " ( Service UID: " + otherDef.getServiceId().toString()
                        + ", service version: " + otherDef.get_version().toString()
                            + " ) [ ServiceDef (inherits from "
                            + targetDefNameStr + " )]");
                }
            } 
        }

        return serviceDefInfo;
    }

    /**
     * Returns a list of String information about service definitions AND service sets that reference the target service
     * definition.
     * @return list of String information about service definitions AND service sets that reference
     * the target service definition.
     */
    public List<String> getReferencingEntityInfo() {
        ArrayList<String> serviceSetNames = new ArrayList<String>();

        // an HQL query: select service sets whose service definitions contain the service definition
        // with given name.
        String hql =
                "select sset from ServiceSet sset "
                + "join sset.serviceDefs sdef "
                + "where sdef.name=:sdefName";

        Query hibQuery = hbSession.createQuery(hql);
        hibQuery.setParameter("sdefName", targetServiceDef.getName());

        List resultsList = hibQuery.list();

        for (Object ssetObj : resultsList) {
            if (ssetObj instanceof com.u2d.generated.ServiceSet) {
                com.u2d.generated.ServiceSet sset = (com.u2d.generated.ServiceSet) ssetObj;
                serviceSetNames.add(sset.getName().toString() + "  [ ServiceSet (uses " + targetDefNameStr + ")]");
            } else {
                throw new RuntimeException("Found a non-ServiceSet when querying for ServiceSets; type is "
                        + ssetObj.getClass().getCanonicalName());
            }
        }

        // also add any service defs that inherit from this service def
        serviceSetNames.addAll(getInheritingServiceDefInfo());

        return serviceSetNames;
    }

    /**
     * Divides the elements of the service definition into two sets: elements which can be deleted, and elements which
     * cannot be deleted due to dependant elements among other service definitions (retained elements).  These sets
     * are stored in deletableNodeSet and retainedNodeSet respectively.
     */
    public void computeDeletionResults() {
        // find the "leaf" elements of the target for deletion - elements with no contents of their own.
        // certain element types cannot have contents, and are leaves.  others have optional contents; if
        // these contents are empty the element is a leaf.
        ServiceDefinitionTreeRepresentation treerep =
                new ServiceDefinitionTreeRepresentation(targetServiceDef);

        // Recursively creates the graph representing the service definition and its subelements.
        treerep.populate();

        // set containing all elements including the service definition itself.
        Set<TreeRepNode> allNodes = treerep.getAllNodeSet();

        retainedNodeSet.clear();
        deletableNodeSet.clear();

        treerep.computeRetainedSet(hbSession);

        retainedNodeSet = treerep.getRetainedNodeSet();

        // the elements of the service that may be safely deleted are those not in the retained set.
        deletableNodeSet = new HashSet(allNodes);
        deletableNodeSet.removeAll(retainedNodeSet);

        deletionResultsComputed = true;
    }

    /**
     * Gets a list of strings describing the tree rep nodes in nodeSet, for presentation via GUI.
     * @param nodeSet Set of TreeRepNodes to process.
     * @return List of strings describing the TreeRepNodes in node set.  Each string has
     *     the name of a node and the type of element it represents, in square brackets.
     */
    private List<String> getInfoStringsFromNodeSet(Set<TreeRepNode> nodeSet) {
        List<String> nodeInfo = new ArrayList<String>();

        for (TreeRepNode node : nodeSet) {
            nodeInfo.add(node.getName() + "  [ " + node.getTypeName() + " ]");
        }

        return nodeInfo;
    }

    /**
     * Get the elements found to be deletable during completeDeletionResults.
     * @return List of Strings containing info about the deletable elements, with type name and element name.
     */
    public List<String> getDeletableElementsInfo() {
        return getInfoStringsFromNodeSet(deletableNodeSet);
    }

    /**
     * Get strings describing the elements found to be non-deletable during completeDeletionResults.
     * @return List of Strings containing info about the non-deletable elements, with type name and element name.
     */
    public List<String> getRetainedElementsInfo() {
        return getInfoStringsFromNodeSet(retainedNodeSet);
    }

    /**
     * Carry out deletion on the elements found to be deletable during computeDeletionResults, plus the
     * target ServiceDef itself.  Throws an IllegalStateException if computeDeletionResults has not yet been called.
     */
    public void performDeletion() throws Exception {
        if (!deletionResultsComputed) {
            throw new IllegalStateException("DeepDeleteProcessor not ready for performDeletion - "
                    + "computeDeletionResults must be called first.");
        }

        // roll back the deletion transaction if an error occurs during the deletions.
        Transaction trans = null;
        try {
            trans = hbSession.beginTransaction();
            for (TreeRepNode node : deletableNodeSet) {
                node.deleteFromDatabase(hbSession);
            }
            trans.commit();

        } catch (Exception ex) {
            if (trans != null) {
                trans.rollback();
            }
            throw ex;
        }

        hbSession.flush();
    }
}
