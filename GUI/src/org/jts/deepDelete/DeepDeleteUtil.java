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

import com.u2d.generated.Array;
import com.u2d.generated.BitField;
import com.u2d.generated.ComplexField;
import com.u2d.generated.FixedField;
import com.u2d.generated.FixedLengthString;
import com.u2d.generated.Footer;
import com.u2d.generated.HasHeaderBodyFooter;
import com.u2d.generated.Header;
import com.u2d.generated.Record;
import com.u2d.generated.Sequence;
import com.u2d.generated.SimpleField;
import com.u2d.generated.VariableField;
import com.u2d.generated.VariableFormatField;
import com.u2d.generated.VariableLengthField;
import com.u2d.generated.VariableLengthString;
import com.u2d.generated.Variant;
import com.u2d.model.AbstractComplexEObject_JTS;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;

/**
 * DeepDeleteUtil provides helper functions used throughout the deepDelete package.
 * @author idurkan
 */
public class DeepDeleteUtil {

    /**
     * Determines the implementation type of simpleField and calls addInstance for the appropriate node type,
     * adding a new node to the service def tree representation wrapping complexField.  parentNode is added as the
     * parent node.
     * @param simpleField Field from the service definition to wrap in the new node.  Type must derive from
     *      SimpleField
     * @param parentNode Node to set as the new node's parent
     * @param nodeSet The MutableSet of all nodes in the tree representation.
     * @param leafSet The Set of all leaf nodes in the tree representation.
     */
    static void addNodeForSimpleType(SimpleField simpleField, ConcreteTreeRepNode parentNode,
            MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {

        // deal with hibernate "lazy loading".  Hibernate may decide not to finish loading persisted
        // instances until forced to.
        SimpleField trueField = getProxyImpl(simpleField);

        if (trueField instanceof Array) {
            ArrayNode.addInstance((Array) trueField, parentNode, nodeSet, leafSet);
        } else if (trueField instanceof FixedField) {
            FixedFieldNode.addInstance((FixedField) trueField, parentNode, nodeSet, leafSet);
        } else if (trueField instanceof VariableField) {
            VariableFieldNode.addInstance((VariableField) trueField, parentNode, nodeSet, leafSet);
        } else if (trueField instanceof BitField) {
            BitFieldNode.addInstance((BitField) trueField, parentNode, nodeSet, leafSet);
        } else if (trueField instanceof FixedLengthString) {
            FixedLengthStringNode.addInstance((FixedLengthString) trueField, parentNode, nodeSet, leafSet);
        } else if (trueField instanceof VariableLengthString) {
            VariableLengthStringNode.addInstance((VariableLengthString) trueField, parentNode, nodeSet, leafSet);
        } else if (trueField instanceof VariableLengthField) {
            VariableLengthFieldNode.addInstance((VariableLengthField) trueField, parentNode, nodeSet, leafSet);
        } else if (trueField instanceof VariableFormatField) {
            VariableFormatFieldNode.addInstance((VariableFormatField) trueField, parentNode, nodeSet, leafSet);
        } else {
            throw new RuntimeException("simpleField has unexpected type: "
                    + trueField.getClass().getCanonicalName());
        }
    }

    /**
     * Determines the implementation type of complexField and calls addInstance for the appropriate node type,
     * adding a new node to the service def tree representation wrapping complexField.  parentNode is added as the
     * parent node.
     * @param complexField Field from the service definition to wrap in the new node.  Type must derive from
     *      ComplexField
     * @param parentNode Node to set as the new node's parent
     * @param nodeSet The MutableSet of all nodes in the tree representation.
     * @param leafSet The Set of all leaf nodes in the tree representation.
     */
    static void addNodeForComplexType(ComplexField complexField, ConcreteTreeRepNode parentNode,
            MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {

        ComplexField trueField = null;

        trueField = DeepDeleteUtil.getProxyImpl(complexField);

        if (trueField instanceof Record) {
            RecordNode.addInstance((Record) trueField, parentNode, nodeSet, leafSet);
        } else if (trueField instanceof com.u2d.generated.List) {
            ListNode.addInstance((com.u2d.generated.List) trueField, parentNode, nodeSet, leafSet);
        } else if (trueField instanceof Variant) {
            VariantNode.addInstance((Variant) trueField, parentNode, nodeSet, leafSet);
        } else if (trueField instanceof Sequence) {
            SequenceNode.addInstance((Sequence) trueField, parentNode, nodeSet, leafSet);
        } else {
            throw new RuntimeException("complexField has unexpected type: "
                    + trueField.getClass().getCanonicalName());
        }
    }

    /**
     * Creates Nodes for the header, body, and footer of the given HasHeaderBodyFooter implementer (MessageDef or
     * EventDef), giving them parentNode as a parent.  
     * @param sourceDef
     * @param parentNode
     * @param nodeSet
     * @param leafSet
     */
    static void addHeaderBodyFooterNodes(HasHeaderBodyFooter sourceDef, ConcreteTreeRepNode parentNode,
            MutableSet<TreeRepNode> nodeSet, Set<TreeRepNode> leafSet) {
        // header, body, and footer must be defined, although any of them may in turn be empty.
        HeaderNode.addInstance(sourceDef.getHeader(), parentNode, nodeSet, leafSet);
        FooterNode.addInstance(sourceDef.getFooter(), parentNode, nodeSet, leafSet);
        BodyNode.addInstance(sourceDef.getBody(), parentNode, nodeSet, leafSet);
    }

    /**
     * Determines if the given proxyObj is an instance of HibernateProxy, and if so gets the implementation, casting to
     * the type T.  This is necessary because of how Hibernate "lazily" loads instances related to a persistent instance
     * that was fetched earlier.  Instead of actually fetching every related instance, it fetches placeholder instances
     * of the dynamically-generated type HibernateProxy, which inherits from the type of the concrete instance the
     * placeholder replaces.
     * Returns proxyObj unchanged if it is not an instance of HibernateProxy.  Please read
     * http://blog.xebia.com/2008/03/08/advanced-hibernate-proxy-pitfalls/, section "Proxy Pitall 2" for more info
     * about what this code is doing.
     * @param <T> The known runtime type of proxyObj.  Java often infers this automatically from the context of the
     *      getProxyImpl call, without needing explicit specification.
     * @param proxyObj
     * @return The concrete implementation proxyObj represents.
     */
    static <T extends AbstractComplexEObject_JTS> T getProxyImpl(T proxyObj) {
        if (proxyObj instanceof HibernateProxy) {
            return (T)((HibernateProxy) proxyObj).getHibernateLazyInitializer().getImplementation();
        } else {
            return (T)proxyObj;
        }
    }

    /**
     * get a list of nodes wrapping the elements in the database depending on a given SimpleField instance.
     * @param simpleField SimpleFields whose dependants will be found.
     * @param hbSession
     * @return
     */
    static List<TreeRepNode> getSimpleFieldDependantsFromDB(com.u2d.generated.SimpleField simpleField,
            Session hbSession) {
        ArrayList dependants = new ArrayList<TreeRepNode>();

        // find record parents in database.
        String queryRecords =
                "select record from Record record "
                + "join record.simpleFields sfield "
                + "where sfield.id=:ID";

        Query hibQuery = hbSession.createQuery(queryRecords);
        hibQuery.setParameter("ID", simpleField.getID());

        List recordsResult = hibQuery.list();

        // create node wrapping each found record.
        for (Object obj : recordsResult) {
            dependants.add(new RecordNode(DeepDeleteUtil.getProxyImpl((Record)obj)));
        }

        // find array parents in database
        String queryArrays =
                "select array from Array array "
                + "join array.arrayElementType sfield "
                + "where sfield.id=:ID";

        Query hibQuery2 = hbSession.createQuery(queryArrays);
        hibQuery2.setParameter("ID", simpleField.getID());

        List arraysResult = hibQuery2.list();

        // create node wrapping each found array.
        for (Object obj : arraysResult) {
            dependants.add(new ArrayNode(DeepDeleteUtil.getProxyImpl((Array)obj)));
        }

        return dependants;
    }

    /**
     * Get a list of nodes wrapping the elements in the database depending on a given ComplexField instance.
     * Nodes may be ListNodes, SequenceNodes, VariantNodes, HeaderNodes, FooterNodes, or BodyNodes.
     * @param complexField ComplexField whose dependants will be found.
     * @param hbSession Hibernate session to use
     * @return
     */
    static List<TreeRepNode> getComplexFieldDependantsFromDB(com.u2d.generated.ComplexField complexField,
            Session hbSession) {
        ArrayList dependants = new ArrayList<TreeRepNode>();

        // find list parents in database.
        String queryLists =
                "select list from List list "
                + "join list.listElementType cfield "
                + "where cfield.id=:ID";

        Query hibQuery = hbSession.createQuery(queryLists);
        hibQuery.setParameter("ID", complexField.getID());

        List listsResult = hibQuery.list();

        // create node wrapping each found list.
        for (Object obj : listsResult) {
            dependants.add(new ListNode(DeepDeleteUtil.getProxyImpl((com.u2d.generated.List)obj)));
        }

        // find sequence parents in database
        String querySeqs =
                "select seq from Sequence seq "
                + "join seq.complexFields cfield "
                + "where cfield.id=:ID";

        Query hibQuery2 = hbSession.createQuery(querySeqs);
        hibQuery2.setParameter("ID", complexField.getID());

        List seqsResult = hibQuery2.list();

        // create node wrapping each found list.
        for (Object obj : seqsResult) {
            dependants.add(new SequenceNode(DeepDeleteUtil.getProxyImpl((Sequence)obj)));
        }

        // find sequence parents in database
        String queryVariants =
                "select variant from Variant variant "
                + "join variant.complexFields cfield "
                + "where cfield.id=:ID";

        Query hibQuery3 = hbSession.createQuery(queryVariants);
        hibQuery3.setParameter("ID", complexField.getID());

        List variantsResult = hibQuery3.list();

        // create node wrapping each found list.
        for (Object obj : variantsResult) {
            dependants.add(new VariantNode(DeepDeleteUtil.getProxyImpl((Variant)obj)));
        }

        // find header parents in database
        String queryHeaders =
                "select header from Header header "
                + "join header.complexField cfield "
                + "where cfield.id=:ID";

        Query hibQuery4 = hbSession.createQuery(queryHeaders);
        hibQuery4.setParameter("ID", complexField.getID());

        List headersResult = hibQuery4.list();

        // create node wrapping each found list.
        for (Object obj : headersResult) {
            dependants.add(new HeaderNode(DeepDeleteUtil.getProxyImpl((Header)obj)));
        }

        // find footer parents in database
        String queryFooters =
                "select footer from Footer footer "
                + "join footer.complexField cfield "
                + "where cfield.id=:ID";

        Query hibQuery5 = hbSession.createQuery(queryFooters);
        hibQuery5.setParameter("ID", complexField.getID());

        List footersResult = hibQuery5.list();

        // create node wrapping each found list.
        for (Object obj : footersResult) {
            dependants.add(new FooterNode(DeepDeleteUtil.getProxyImpl((Footer)obj)));
        }

        // find body parents in database.
        String queryBodies =
                "select body from Body body "
                + "join body.complexField cfield "
                + "where cfield.id=:ID";

        Query hibQuery6 = hbSession.createQuery(queryBodies);
        hibQuery6.setParameter("ID", complexField.getID());

        List bodiesResult = hibQuery6.list();

        // create node wrapping each found list.
        for (Object obj : bodiesResult) {
            dependants.add(new BodyNode(DeepDeleteUtil.getProxyImpl((com.u2d.generated.Body)obj)));
        }

        return dependants;
    }

    // i want the following code to work, but it can't because type parameters cannot be used for calling the
    // underlying type's constructor(s) in Java.

//    public <NodeType, DataType> List<TreeRepNode> queryDbForDependencies(Session hbSession, String depFieldName,
//            String depFieldTable, String relationName, String thisTypeFieldName, Long id) {
//        ArrayList<TreeRepNode> dependants = new ArrayList<TreeRepNode>();
//
//        String queryText =
//                "select " + depFieldName + " from " + depFieldTable + " " + depFieldName + " "
//                + "join " + depFieldName + "." + relationName + " " + thisTypeFieldName + " "
//                + "where " + thisTypeFieldName + ".id=:ID";
//
//        System.out.println("assembled query: " + queryText);
//
//        Query hibQuery = hbSession.createQuery(queryText);
//        hibQuery.setParameter("ID", id);
//
//        List resultFields = hibQuery.list();
//
//        for (Object obj : resultFields) {
//            dependants.add(new NodeType(DeepDeleteUtil.getProxyImpl((DataType)obj)));
//        }
//
//
//        return dependants;
//    }

}
