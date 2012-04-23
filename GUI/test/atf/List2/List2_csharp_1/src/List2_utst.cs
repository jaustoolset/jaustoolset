
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
using urn_org_jts_test_List2_1_0;

[TestFixture]
public class List2_utst
{

    private MsgIn msgIn1;
    private MsgIn msgIn2;

    public List2_utst()
    {
    }

    [SetUp]
    protected void setUp()
    {
        msgIn1 = new MsgIn();
        msgIn2 = new MsgIn();
    }

    [Test]
    public void testConstructionDefaults()
    {
        Console.Out.WriteLine("\n[executing test (testConstructionDefaults)]: ");

        // verifying MsgIn constructs with correct default MessageID"
        Assert.AreEqual(1, msgIn1.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(1, msgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying MsgIn constructs with default empty lists"

        Assert.AreEqual(0, msgIn1.getBody().getList2().getNumberOfElements());
        Assert.AreEqual(0, msgIn2.getBody().getList2().getNumberOfElements());

    }

    [Test]
    public void testSetGetOperations()
    {
        Console.Out.WriteLine("\nexecuting test (testSetGetOperations)(TP_3.3.5.1)]: ");

        // verifying setMessageID/getMessageID reciprocity"
        msgIn1.getHeader().getHeaderRec().setMessageID(5);
        Assert.AreEqual(5, msgIn1.getHeader().getHeaderRec().getMessageID());
        msgIn1.getHeader().getHeaderRec().setMessageID(1);
        Assert.AreEqual(1, msgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying setField/getField reciprocity"

        MsgIn.Body.List2.Sublist1 L1 = new MsgIn.Body.List2.Sublist1();
        MsgIn.Body.List2.Sublist1 L2 = new MsgIn.Body.List2.Sublist1();
        MsgIn.Body.List2.Sublist1.Record1 R1 = new MsgIn.Body.List2.Sublist1.Record1();
        MsgIn.Body.List2.Sublist1.Record1 R2 = new MsgIn.Body.List2.Sublist1.Record1();
        MsgIn.Body.List2.Sublist1.Record1 R3 = new MsgIn.Body.List2.Sublist1.Record1();
        MsgIn.Body.List2.Sublist1.Record1 R4 = new MsgIn.Body.List2.Sublist1.Record1();

        R1.setField1((byte)5);
        R2.setField1((byte)6);
        R3.setField1((byte)7);
        R4.setField1((byte)8);
        L1.addElement(R1);
        L1.addElement(R2);
        L2.addElement(R3);
        L2.addElement(R4);
        msgIn1.getBody().getList2().addElement(L1);
        msgIn1.getBody().getList2().addElement(L2);

        Assert.AreEqual(5, msgIn1.getBody().getList2().getElement(0).getElement(0).getField1());
        Assert.AreEqual(6, msgIn1.getBody().getList2().getElement(0).getElement(1).getField1());
        Assert.AreEqual(7, msgIn1.getBody().getList2().getElement(1).getElement(0).getField1());
        Assert.AreEqual(8, msgIn1.getBody().getList2().getElement(1).getElement(1).getField1());

        // Test copy constructor
        // verifying copy constructor"
        msgIn2 = msgIn1;
        Assert.AreEqual(msgIn2, msgIn1);
        Assert.AreEqual(msgIn2.getBody().getList2().getNumberOfElements(), msgIn1.getBody().getList2().getNumberOfElements());
        Assert.AreEqual(msgIn2.getBody().getList2().getElement(0).getNumberOfElements(), msgIn1.getBody().getList2().getElement(0).getNumberOfElements());
    }

    [Test]
    public void testEquality()
    {
        Console.Out.WriteLine("executing test (testEquality)]: ");
        // verifying message equality operator"    
        Assert.IsTrue(msgIn1.isEqual(msgIn1));
        Assert.IsTrue(msgIn1.isEqual(msgIn2));
        Assert.IsTrue(msgIn2.isEqual(msgIn1));
    }

    [Test]
    public void testInequality()
    {
        Console.Out.WriteLine("\n[executing test (testInequality)]: ");
        // verifying message inequality operator"    
        Assert.IsFalse(msgIn1.notEquals(msgIn2));
        Assert.IsFalse(msgIn2.notEquals(msgIn1));
    }

    [Test]
    public void testEncodeToSpec()
    {
        byte[] buff = new byte[1024];
        int i;

        MsgIn.Body.List2 list = new MsgIn.Body.List2();
        msgIn1.getBody().setList2(list);
        MsgIn.Body.List2.Sublist1 L1 = new MsgIn.Body.List2.Sublist1();
        MsgIn.Body.List2.Sublist1 L2 = new MsgIn.Body.List2.Sublist1();
        MsgIn.Body.List2.Sublist1.Record1 R1 = new MsgIn.Body.List2.Sublist1.Record1();

        Console.Out.WriteLine("\n[executing test (testEncodeToSpec)(TP_3.3.5.2)]: ");

        msgIn1.getHeader().getHeaderRec().setMessageID(256);

        // First sublist should be three elements long
        for (i = 0; i < 3; i++)
        {
            R1 = new MsgIn.Body.List2.Sublist1.Record1();
            R1.setField1((byte)(i + 4));
            L1.addElement(R1);
        }

        // Second sublist should be 7 elements long
        for (i = 0; i < 7; i++)
        {
            R1 = new MsgIn.Body.List2.Sublist1.Record1();
            R1.setField1((byte)(i + 8));
            L2.addElement(R1);
        }

        // Add both sublists to the main list
        msgIn1.getBody().getList2().addElement(L1);
        msgIn1.getBody().getList2().addElement(L2);


        // verifying message level encode is AS-5684 compliant"
        msgIn1.encode(buff, 0);
        Assert.AreEqual(15, msgIn1.getSize());
        for (i = 0; i < msgIn1.getSize(); i++)
        {
            Assert.AreEqual(i, buff[i]);
        }
    }

    [Test]
    public void testEncodeDecodeOperations()
    {
        byte[] buff = new byte[1024];
        int i;
        MsgIn.Body.List2 list1 = new MsgIn.Body.List2();
        MsgIn.Body.List2 list2 = new MsgIn.Body.List2();
        msgIn1.getBody().setList2(list1);
        msgIn1.getBody().setList2(list2);
        MsgIn.Body.List2.Sublist1 L1 = new MsgIn.Body.List2.Sublist1();
        MsgIn.Body.List2.Sublist1 L2 = new MsgIn.Body.List2.Sublist1();
        MsgIn.Body.List2.Sublist1.Record1 R1 = new MsgIn.Body.List2.Sublist1.Record1();
        MsgIn.Body.List2.Sublist1.Record1 R2 = new MsgIn.Body.List2.Sublist1.Record1();
        MsgIn.Body.List2.Sublist1.Record1 R3 = new MsgIn.Body.List2.Sublist1.Record1();


        for (i = 0; i < 1024; i++)
        {
            buff[i] = 0;
        }

        Console.Out.WriteLine("\n[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]: ");

        // verifying message level encode/decode reciprocity"


        msgIn1.getHeader().getHeaderRec().setMessageID(41);
        msgIn1.getBody().getList2().addElement(L1);
        msgIn1.getBody().getList2().getElement(0).addElement(R1);
        msgIn1.getBody().getList2().getElement(0).addElement(R2);
        msgIn1.getBody().getList2().getElement(0).getElement(0).setField1((byte)15);
        msgIn1.getBody().getList2().getElement(0).getElement(1).setField1((byte)0);

        msgIn2.getHeader().getHeaderRec().setMessageID(127);
        msgIn2.getBody().getList2().addElement(L2);
        msgIn2.getBody().getList2().getElement(0).addElement(R3);
        msgIn2.getBody().getList2().getElement(0).getElement(0).setField1((byte)127);

        msgIn1.encode(buff, 0);
        msgIn2.decode(buff, 0);

        Assert.AreEqual(41, msgIn2.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(1, msgIn2.getBody().getList2().getNumberOfElements());
        Assert.AreEqual(2, msgIn2.getBody().getList2().getElement(0).getNumberOfElements());
        Assert.AreEqual(15, msgIn2.getBody().getList2().getElement(0).getElement(0).getField1());
        Assert.AreEqual(0, msgIn2.getBody().getList2().getElement(0).getElement(1).getField1());


        // verifying header level encode/decode reciprocity"

        msgIn2.getHeader().getHeaderRec().setMessageID(0);
        msgIn1.getHeader().encode(buff, 0);
        msgIn2.getHeader().decode(buff, 0);
        Assert.AreEqual(41, msgIn2.getHeader().getHeaderRec().getMessageID());

        msgIn2.getHeader().getHeaderRec().setMessageID(0);
        msgIn1.getHeader().getHeaderRec().encode(buff, 0);
        msgIn2.getHeader().getHeaderRec().decode(buff, 0);
        Assert.AreEqual(41, msgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying body level encode/decode reciprocity"

        msgIn2.getBody().getList2().getElement(0).getElement(0).setField1((byte)255);
        msgIn2.getBody().getList2().getElement(0).getElement(1).setField1((byte)255);
        msgIn2.getBody().getList2().getElement(0).addElement(R1);

        msgIn1.getBody().encode(buff, 0);
        msgIn2.getBody().decode(buff, 0);

        Assert.AreEqual(1, msgIn2.getBody().getList2().getNumberOfElements());
        Assert.AreEqual(2, msgIn2.getBody().getList2().getElement(0).getNumberOfElements());
        Assert.AreEqual(15, msgIn2.getBody().getList2().getElement(0).getElement(0).getField1());
        Assert.AreEqual(0, msgIn2.getBody().getList2().getElement(0).getElement(1).getField1());


        // verifying list level encode/decode reciprocity"

        msgIn2.getBody().getList2().getElement(0).getElement(0).setField1((byte)255);
        msgIn2.getBody().getList2().getElement(0).getElement(1).setField1((byte)255);
        msgIn2.getBody().getList2().getElement(0).addElement(R1);

        msgIn1.getBody().getList2().encode(buff, 0);
        msgIn2.getBody().getList2().decode(buff, 0);

        Assert.AreEqual(1, msgIn2.getBody().getList2().getNumberOfElements());
        Assert.AreEqual(2, msgIn2.getBody().getList2().getElement(0).getNumberOfElements());
        Assert.AreEqual(15, msgIn2.getBody().getList2().getElement(0).getElement(0).getField1());
        Assert.AreEqual(0, msgIn2.getBody().getList2().getElement(0).getElement(1).getField1());

        // verifying record level encode/decode reciprocity"
        msgIn2.getBody().getList2().getElement(0).getElement(0).setField1((byte)255);

        msgIn1.getBody().getList2().getElement(0).getElement(0).encode(buff, 0);
        msgIn2.getBody().getList2().getElement(0).getElement(0).decode(buff, 0);

        Assert.AreEqual(15, msgIn2.getBody().getList2().getElement(0).getElement(0).getField1());
    }

    [Test]
    public void testListOperations()
    {
        MsgIn.Body.List2 list = new MsgIn.Body.List2();
        MsgIn.Body.List2.Sublist1 L1 = new MsgIn.Body.List2.Sublist1();
        MsgIn.Body.List2.Sublist1.Record1 R1 = new MsgIn.Body.List2.Sublist1.Record1();
        msgIn1.getBody().setList2(list);
        msgIn2.getBody().setList2(list);
        int i, j;

        Console.Out.WriteLine("\n[executing test (testListOperations)]: ");

        // verifying MsgIn getNumberOfElements with Add and Delete operations"    
        for (i = 0; i < 4; i++)
        {

            Assert.AreEqual(msgIn2.getBody().getList2().getNumberOfElements(), i);
            L1 = new MsgIn.Body.List2.Sublist1();
            msgIn2.getBody().getList2().addElement(L1);

            for (j = 0; j < 4; j++)
            {
                Assert.AreEqual(msgIn2.getBody().getList2().getElement(i).getNumberOfElements(), j);
                R1 = new MsgIn.Body.List2.Sublist1.Record1();
                msgIn2.getBody().getList2().getElement(i).addElement(R1);
            }
        }
        for (i = 4; i > 0; i--)
        {
            Assert.AreEqual(msgIn2.getBody().getList2().getNumberOfElements(), i);
            msgIn2.getBody().getList2().deleteLastElement();
        }

        for (i = 0; i < 4; i++)
        {
            L1 = new MsgIn.Body.List2.Sublist1();
            msgIn1.getBody().getList2().addElement(L1);

            for (j = 0; j < 4; j++)
            {
                R1 = new MsgIn.Body.List2.Sublist1.Record1();
                msgIn1.getBody().getList2().getElement(i).addElement(R1);
                msgIn1.getBody().getList2().getElement(i).getElement(j).setField1((byte)((i * 4) + j));
            }
        }

        // Delete a sampling of elemments from the top list and the sublists...
        msgIn1.getBody().getList2().deleteElement(0);
        msgIn1.getBody().getList2().getElement(0).deleteElement(0);
        msgIn1.getBody().getList2().getElement(1).deleteElement(1);
        msgIn1.getBody().getList2().getElement(2).deleteElement(2);

        // Verify...
        Assert.AreEqual(msgIn1.getBody().getList2().getNumberOfElements(), 3);
        for (i = 0; i < 3; i++)
        {

            Assert.AreEqual(msgIn1.getBody().getList2().getElement(i).getNumberOfElements(), 3);

            for (j = 0; j < 3; j++)
            {
                int target_value = (i > j ? ((i + 1) * 4) + j : ((i + 1) * 4) + j + 1);
                Assert.AreEqual(msgIn1.getBody().getList2().getElement(i).getElement(j).getField1(), target_value);
            }
        }
    }

    public static void Main()
    {
    }
}
