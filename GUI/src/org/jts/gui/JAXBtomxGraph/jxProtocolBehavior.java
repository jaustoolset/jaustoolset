/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
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
package org.jts.gui.JAXBtomxGraph;

import java.util.List;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.bind.JAXBException;

import org.jts.jsidl.binding.StateMachine;
import org.jts.jsidl.binding.ProtocolBehavior;

import org.jts.mxGraph.binding.MxGraphModel;
import org.jts.mxGraph.binding.Root;
import org.jts.mxGraph.binding.MxCell;
import org.jts.mxGraph.binding.ObjectFactory;

public class jxProtocolBehavior {

  private MxGraphModel model = null;
  private ProtocolBehavior pb = null;
  private ObjectFactory objf = null;

  public jxProtocolBehavior(ProtocolBehavior pb) {
    this.pb = pb;

    // create an mxGraph binding object
    objf = new ObjectFactory();
    model = objf.createMxGraphModel();
  }

  public Document convert() {

    Root root = model.getRoot();
    if (root == null) {   // implies jsidl protocol behavior
      root = objf.createRoot();
      model.setRoot(root);
    }
    List<MxCell> cells = root.getMxCell();

    // add first two cells that are empty
    MxCell emptyCell = objf.createMxCell();
    emptyCell.setId(0);
    cells.add(emptyCell);
    emptyCell = objf.createMxCell();
    emptyCell.setId(1);
    emptyCell.setParent(new Long(0));
    cells.add(emptyCell);

    // populate the mxGraph binding object
    populate(pb, cells);

    // group common transitions under a single edge
    groupCommonTransitions(cells);

    // convert model into Document object
    Document doc = null;
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      doc = db.newDocument();

///////////////////////////////////////////////////////////////////
      try {
        jxUtil.getMxGraphMarshaller().marshal(model, new java.io.FileOutputStream("testmx.xml"));
      } catch (Exception fe) {
        fe.printStackTrace();
      }
///////////////////////////////////////////////////////////////////

      jxUtil.getMxGraphMarshaller().marshal(model, doc);

    } catch (JAXBException jaxbe) {
      jaxbe.printStackTrace();
    } catch (ParserConfigurationException pce) {
      pce.printStackTrace();
    }

    return doc;
  }

  private void populate(ProtocolBehavior pb, List<MxCell> cells) {
    jxStateMachine jxfsm = new jxStateMachine(pb);
    // first seed id
    jxfsm.seedId();

    List<MxCell> fsmCells = jxfsm.convert();

    for (int jj = 0; jj < fsmCells.size(); jj++) {
      cells.add(fsmCells.get(jj));
    }
  }

  private void groupCommonTransitions(List<MxCell> cells) {

    // compare primary cell with secondary.
    // if common endpoints and similar transition types,
    // add secondary to primary and remove secondary from list
    for (int ii = 0; ii < cells.size(); ii++) {
      MxCell primary = cells.get(ii);

      for (int jj = ii + 1; jj < cells.size(); jj++) {

        MxCell secondary = cells.get(jj);

        if (groupable(primary, secondary)) {
          String value = primary.getValue().trim();

          if (!value.isEmpty() && !value.endsWith(";")) {
            value = value.concat(";");
          }
          value = value.concat(secondary.getValue().trim());

          primary.setValue(value);

          cells.remove(jj);
          jj--;
        }
      }
    }
  }

  private boolean groupable(MxCell cell1, MxCell cell2) {
    String style1 = cell1.getStyle();
    String style2 = cell2.getStyle();

    if (style1 == null || style2 == null) {
      return false;
    }

    int index = 0;
    if ((index = style1.indexOf(";")) != -1) {
      style1 = style1.substring(0, index);
    }
    if ((index = style2.indexOf(";")) != -1) {
      style2 = style2.substring(0, index);
    }

    // transitions of the same type
    if (style1.contains("Transition") && style1.equals(style2)) {
      Long source1 = cell1.getSource();
      Long source2 = cell2.getSource();

      // same source states
      if (source1.equals(source2)) {
        Long target1 = cell1.getTarget();
        Long target2 = cell2.getTarget();

        // same target states
        if (target1 == null) {
          if (target2 == null) {
            return true;
          } else {
            return false;
          }
        } else if (target1.equals(target2)) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}
