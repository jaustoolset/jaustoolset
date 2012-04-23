
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

// test for message structure with <list> containing a <variant>

using System;
using NUnit.Framework;
using urn_org_jts_test_List4_1_0;

[TestFixture]
public class List4_utst
{

    private MsgIn msgIn1;
    private MsgIn msgIn2;

    public List4_utst()
    {
    }

    protected void populate()
    {
        MsgIn.Body.List4 List = new MsgIn.Body.List4();
        MsgIn.Body.List4.Variant1 V1 = new MsgIn.Body.List4.Variant1();
        MsgIn.Body.List4.Variant1 V2 = new MsgIn.Body.List4.Variant1();
        MsgIn.Body.List4.Variant1 V3 = new MsgIn.Body.List4.Variant1();
        MsgIn.Body.List4.Variant1.List1.Record2 R1 = new MsgIn.Body.List4.Variant1.List1.Record2();
        MsgIn.Body.List4.Variant1.List1.Record2 R2 = new MsgIn.Body.List4.Variant1.List1.Record2();

        msgIn1.getBody().setList4(List);

        V1.setFieldValue(0);
        V1.getRecord1().setField1(1);
        V1.getRecord1().setField2(770);

        V2.setFieldValue(1);
        R1.setField1(3); V2.getList1().addElement(R1);
        R2.setField1(4); V2.getList1().addElement(R2);

        V3.setFieldValue(2);
        V3.getSequence1().getRecord3().setField1(3);
        V3.getSequence1().getRecord3().setField2(1284);

        msgIn1.getBody().getList4().addElement(V1);
        msgIn1.getBody().getList4().addElement(V2);
        msgIn1.getBody().getList4().addElement(V3);
    }

    protected void setUp()
    {
        msgIn1 = new MsgIn();
        msgIn2 = new MsgIn();
    }

    protected void tearDown()
    {
    }

    public void testConstructionDefaults()
    {
        Console.Out.WriteLine("\n[executing test (testConstructionDefaults)]: ");

        // verifying MsgIn constructs with correct default MessageID"
        Assert.AreEqual(1, msgIn1.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(1, msgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying MsgIn constructs with default empty lists"

        Assert.AreEqual(0, msgIn1.getBody().getList4().getNumberOfElements());
        Assert.AreEqual(0, msgIn2.getBody().getList4().getNumberOfElements());

    }

    public void testSetGetOperations()
    {
        Console.Out.WriteLine("\n[executing test (testSetGetOperations)(TP_3.3.5.1)]: ");

        // verifying setMessageID/getMessageID reciprocity"
        msgIn1.getHeader().getHeaderRec().setMessageID(5);
        Assert.AreEqual(5, msgIn1.getHeader().getHeaderRec().getMessageID());
        msgIn1.getHeader().getHeaderRec().setMessageID(1);
        Assert.AreEqual(1, msgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying setField/getField reciprocity"
        MsgIn.Body.List4.Variant1 V1 = new MsgIn.Body.List4.Variant1();
        MsgIn.Body.List4.Variant1 V2 = new MsgIn.Body.List4.Variant1();

        V1.getRecord1().setField1(5);
        V1.getRecord1().setField2(500);
        V1.setFieldValue(0);
        V2.getSequence1().getRecord3().setField1(6);
        V2.getSequence1().getRecord3().setField2(600);
        V2.setFieldValue(2);
        msgIn1.getBody().getList4().addElement(V1);
        msgIn2.getBody().getList4().addElement(V2);
        Assert.AreEqual(msgIn1.getBody().getList4().getElement(0).getRecord1().getField1(), 5);
        Assert.AreEqual(msgIn1.getBody().getList4().getElement(0).getRecord1().getField2(), 500);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getSequence1().getRecord3().getField1(), 6);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getSequence1().getRecord3().getField2(), 600);

        // Test copy constructor
        msgIn2 = msgIn1;
        Assert.AreEqual(msgIn2, msgIn1);
        Assert.AreEqual(msgIn2.getBody().getList4().getNumberOfElements(), msgIn1.getBody().getList4().getNumberOfElements());
    }

    public void testEquality()
    {
        Console.Out.WriteLine("\n[executing test (testEquality)]: ");
        // verifying message equality operator"    
        Assert.IsTrue(msgIn1.isEqual(msgIn1));
        Assert.IsTrue(msgIn1.isEqual(msgIn2));
        Assert.IsTrue(msgIn2.isEqual(msgIn1));
    }

    public void testInequality()
    {
        Console.Out.WriteLine("\n[executing test (testInequality)]: ");
        // verifying message inequality operator"    
        Assert.IsFalse(msgIn1.notEquals(msgIn2));
        Assert.IsFalse(msgIn2.notEquals(msgIn1));
    }

    public void testEncodeToSpec()
    {
        byte[] buff = new byte[1024];
        int i, j;

        Console.Out.WriteLine("\n[executing test (testEncodeToSpec)(TP_3.3.5.2)]: ");

        populate();
        msgIn1.getHeader().getHeaderRec().setMessageID(256);
        msgIn1.encode(buff, 0);

        Assert.AreEqual(buff[0], 0); Assert.AreEqual(buff[1], 1); // msg_id
        Assert.AreEqual(buff[2], 3);                                // str length
        for (i = 3, j = 0; i < 7; i++, j++) Assert.AreEqual(buff[i], j); // record type
        for (i = 7, j = 1; i < 11; i++, j++) Assert.AreEqual(buff[i], j); // list type
        for (i = 11, j = 2; i < 15; i++, j++) Assert.AreEqual(buff[i], j); // sequence type
    }

    public void testEncodeDecodeOperations()
    {
        byte[] buff = new byte[1024];
        MsgIn.Body.List4 List = new MsgIn.Body.List4();
        MsgIn.Body.List4.Variant1 V1 = new MsgIn.Body.List4.Variant1();
        MsgIn.Body.List4.Variant1 V2 = new MsgIn.Body.List4.Variant1();
        MsgIn.Body.List4.Variant1 V3 = new MsgIn.Body.List4.Variant1();

        for (int i = 0; i < 1024; i++)
        {
            buff[i] = 0;
        }

        Console.Out.WriteLine("\n[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]: ");


        // verifying message level encode/decode reciprocity"
        msgIn1.getHeader().getHeaderRec().setMessageID(41);
        msgIn1.getBody().setList4(List);
        msgIn1.getBody().getList4().addElement(V1);
        msgIn1.getBody().getList4().addElement(V2);
        msgIn1.getBody().getList4().getElement(0).setFieldValue(0);
        msgIn1.getBody().getList4().getElement(0).getRecord1().setField1(15);
        msgIn1.getBody().getList4().getElement(0).getRecord1().setField2(300);
        msgIn1.getBody().getList4().getElement(1).setFieldValue(2);
        msgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().setField1(0);
        msgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().setField2(6525);

        msgIn2.getHeader().getHeaderRec().setMessageID(127);
        msgIn2.getBody().getList4().addElement(V3);
        msgIn2.getBody().getList4().getElement(0).setFieldValue(0);
        msgIn2.getBody().getList4().getElement(0).getRecord1().setField1(127);
        msgIn2.getBody().getList4().getElement(0).getRecord1().setField2(127);

        msgIn1.encode(buff, 0);
        msgIn2.decode(buff, 0);

        Assert.AreEqual(msgIn2.getHeader().getHeaderRec().getMessageID(), 41);
        Assert.AreEqual(msgIn2.getBody().getList4().getNumberOfElements(), 2);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getFieldValue(), 0);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getRecord1().getField1(), 15);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getRecord1().getField2(), 300);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(1).getFieldValue(), 2);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1(), 0);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2(), 6525);


        // verifying header level encode/decode reciprocity

        msgIn2.getHeader().getHeaderRec().setMessageID(0);
        msgIn1.getHeader().encode(buff, 0);
        msgIn2.getHeader().decode(buff, 0);
        Assert.AreEqual(msgIn2.getHeader().getHeaderRec().getMessageID(), 41);

        msgIn2.getHeader().getHeaderRec().setMessageID(0);
        msgIn1.getHeader().getHeaderRec().encode(buff, 0);
        msgIn2.getHeader().getHeaderRec().decode(buff, 0);
        Assert.AreEqual(msgIn2.getHeader().getHeaderRec().getMessageID(), 41);

        //     --- verifying body level encode/decode reciprocity

        msgIn2.getBody().getList4().getElement(0).setFieldValue(0);
        msgIn2.getBody().getList4().getElement(0).getRecord1().setField1(255);
        msgIn2.getBody().getList4().getElement(0).getRecord1().setField2(255);
        msgIn2.getBody().getList4().getElement(0).setFieldValue(0);
        msgIn2.getBody().getList4().getElement(1).getRecord1().setField1(255);
        msgIn2.getBody().getList4().getElement(1).getRecord1().setField2(255);
        V3.getRecord1().setField1(255); V3.getRecord1().setField2(255);
        msgIn2.getBody().getList4().addElement(V3);

        msgIn1.getBody().encode(buff, 0);
        msgIn2.getBody().decode(buff, 0);

        Assert.AreEqual(msgIn2.getBody().getList4().getNumberOfElements(), 2);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getFieldValue(), 0);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getRecord1().getField1(), 15);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getRecord1().getField2(), 300);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(1).getFieldValue(), 2);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1(), 0);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2(), 6525);


        //     --- verifying list level encode/decode reciprocity

        msgIn2.getBody().getList4().getElement(0).setFieldValue(0);
        msgIn2.getBody().getList4().getElement(0).getRecord1().setField1(255);
        msgIn2.getBody().getList4().getElement(0).getRecord1().setField2(255);
        msgIn2.getBody().getList4().getElement(0).setFieldValue(1);
        msgIn2.getBody().getList4().getElement(1).getRecord1().setField1(255);
        msgIn2.getBody().getList4().getElement(1).getRecord1().setField2(255);
        V3.getRecord1().setField1(255); V3.getRecord1().setField2(255);
        msgIn2.getBody().getList4().addElement(V3);

        msgIn1.getBody().getList4().encode(buff, 0);
        msgIn2.getBody().getList4().decode(buff, 0);

        Assert.AreEqual(msgIn2.getBody().getList4().getNumberOfElements(), 2);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getFieldValue(), 0);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getRecord1().getField1(), 15);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getRecord1().getField2(), 300);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(1).getFieldValue(), 2);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1(), 0);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2(), 6525);

        //     --- verifying record level encode/decode reciprocity
        msgIn2.getBody().getList4().getElement(0).getRecord1().setField1(255);
        msgIn2.getBody().getList4().getElement(0).getRecord1().setField2(255);

        msgIn1.getBody().getList4().getElement(0).encode(buff, 0);
        msgIn2.getBody().getList4().getElement(0).decode(buff, 0);

        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getFieldValue(), 0);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getRecord1().getField1(), 15);
        Assert.AreEqual(msgIn2.getBody().getList4().getElement(0).getRecord1().getField2(), 300);

    }



    public void testListOperations()
    {
        MsgIn.Body.List4 List = new MsgIn.Body.List4();
        MsgIn.Body.List4.Variant1 V1;
        msgIn2.getBody().setList4(List);
        int i, j;

        Console.Out.WriteLine("\n[executing test (testListOperations)]: ");

        //     --- verifying MsgIn getNumberOfElements with Add and Delete operations    
        i = j = 0;
        for (i = 0; i < 4; i++)
        {
            V1 = new MsgIn.Body.List4.Variant1();
            Assert.AreEqual(msgIn2.getBody().getList4().getNumberOfElements(), i);
            msgIn2.getBody().getList4().addElement(V1);
        }
        for (j = 0, i = 4; j < 4; j++, i--)
        {
            Assert.AreEqual(msgIn2.getBody().getList4().getNumberOfElements(), i);
            msgIn2.getBody().getList4().deleteLastElement();
        }

        //     --- verifying deleteElement preserves order & corrects count of remaining elements                          

        populate();

        msgIn1.getBody().getList4().deleteElement(0);
        Assert.AreEqual(msgIn1.getBody().getList4().getNumberOfElements(), 2);

        Assert.AreEqual(msgIn1.getBody().getList4().getElement(0).getFieldValue(), 1);
        Assert.AreEqual(msgIn1.getBody().getList4().getElement(0).getList1().getNumberOfElements(), 2);
        Assert.AreEqual(msgIn1.getBody().getList4().getElement(0).getList1().getElement(0).getField1(), 3);
        Assert.AreEqual(msgIn1.getBody().getList4().getElement(0).getList1().getElement(1).getField1(), 4);
        Assert.AreEqual(msgIn1.getBody().getList4().getElement(1).getFieldValue(), 2);
        Assert.AreEqual(msgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1(), 3);
        Assert.AreEqual(msgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2(), 1284);

        msgIn1.getBody().getList4().deleteElement(1);
        Assert.AreEqual(msgIn1.getBody().getList4().getNumberOfElements(), 1);

        Assert.AreEqual(msgIn1.getBody().getList4().getElement(0).getFieldValue(), 1);
        Assert.AreEqual(msgIn1.getBody().getList4().getElement(0).getList1().getNumberOfElements(), 2);
        Assert.AreEqual(msgIn1.getBody().getList4().getElement(0).getList1().getElement(0).getField1(), 3);
        Assert.AreEqual(msgIn1.getBody().getList4().getElement(0).getList1().getElement(1).getField1(), 4);

        msgIn1.getBody().getList4().deleteElement(0);
        Assert.AreEqual(msgIn1.getBody().getList4().getNumberOfElements(), 0);

    }

    public static void Main()
    {
    }
}