/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jts.deepDelete;

import com.u2d.generated.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * A TreeRepNode wrapping a dimension instance.  See ConcreteTreeRepNode for a full picture of a
 * TreeRepNode implementation's purpose and responsibilities.
 * @author idurkan
 */
class DimensionNode extends ConcreteTreeRepNode {

    private Dimension dimension = null;

    /**
     * Create a DimensionNode wrapping the given Dimension element.
     * @param element
     */
    DimensionNode(Dimension element) {
        super(element,element.getID(), "Dimension", element.getName().toString());
        dimension = element;
    }

    /**
     * Static factory method that creates a DimensionNode and adds it to the tree representation.
     * @param dimension
     * @param parent
     * @param nodeSet
     * @param leafSet
     */
    public static void addInstance(Dimension dimension, ConcreteTreeRepNode parent, MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        ConcreteTreeRepNode newNode = new DimensionNode(dimension);
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
        // dimensions have no children
        return false;
    }

    /**
     * See ConcreteTreeRepNode.getDependantsInDatabase
     * @param hbSession
     * @return
     */
    @Override
    public List<TreeRepNode> getDependantsInDatabase(Session hbSession) {
        ArrayList dependants = new ArrayList<TreeRepNode>();

        // arrays depend on dimensions.
        // find array parents in database.
        String queryArrays =
                "select array from Array array "
                + "join array.dimensions dim "
                + "where dim.id=:ID";

        Query hibQuery = hbSession.createQuery(queryArrays);
        hibQuery.setParameter("ID", dimension.getID());

        List arraysResult = hibQuery.list();

        // create node wrapping each found record.
        for (Object obj : arraysResult) {
            dependants.add(new ArrayNode(DeepDeleteUtil.getProxyImpl((com.u2d.generated.Array)obj)));
        }

        return dependants;
    }
}
