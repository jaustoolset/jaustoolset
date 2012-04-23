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

using System;
using NUnit.Framework;
using urn_org_jts_test_List1_1_0;

[TestFixture]
public class List1_utst
{
    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public List1_utst()
    {
    }

    [SetUp]
    public void setUp()
    {
        m_MsgIn1 = new MsgIn();
        m_MsgIn2 = new MsgIn();
    }

    [Test]
    public void testConstructionDefaults()
    {
        Console.WriteLine("[executing test (testConstructionDefaults)]");

        // verifying MsgIn constructs with correct default MessageID"
        Assert.AreEqual(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(1, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying MsgIn constructs with default empty lists"

        Assert.AreEqual(0, m_MsgIn1.getBody().getList1().getNumberOfElements());
        Assert.AreEqual(0, m_MsgIn2.getBody().getList1().getNumberOfElements());

    }

    [Test]
    public void testSetGetOperations()
    {
        Console.WriteLine("[executing test (testSetGetOperations)(TP_3.3.5.1)]");

        // verifying setMessageID/getMessageID reciprocity"
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(5);
        Assert.AreEqual(5, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(1);
        Assert.AreEqual(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying setField/getField reciprocity"

        MsgIn.Body.List1.Record1 R1 = new MsgIn.Body.List1.Record1();
        MsgIn.Body.List1.Record1 R2 = new MsgIn.Body.List1.Record1();

        R1.setField1(5);
        R1.setField2(500);
        R2.setField1(6);
        R2.setField2(600);
        m_MsgIn1.getBody().getList1().addElement(R1);
        m_MsgIn2.getBody().getList1().addElement(R2);
        Assert.AreEqual(5, m_MsgIn1.getBody().getList1().getElement(0).getField1());
        Assert.AreEqual(500, m_MsgIn1.getBody().getList1().getElement(0).getField2());
        Assert.AreEqual(6, m_MsgIn2.getBody().getList1().getElement(0).getField1());
        Assert.AreEqual(600, m_MsgIn2.getBody().getList1().getElement(0).getField2());
        m_MsgIn2.getBody().getList1().getElement(0).setField1(5);
        m_MsgIn2.getBody().getList1().getElement(0).setField2(500);
        Assert.AreEqual(m_MsgIn2.getBody().getList1().getElement(0).getField1(), m_MsgIn1.getBody().getList1().getElement(0).getField1());
        Assert.AreEqual(m_MsgIn2.getBody().getList1().getElement(0).getField2(), m_MsgIn1.getBody().getList1().getElement(0).getField2());

        // Test copy constructor
        // verifying copy constructor"    
        MsgIn copy1 = m_MsgIn1;
        Assert.AreEqual(copy1, m_MsgIn1);
        Assert.AreEqual(copy1.getBody().getList1().getNumberOfElements(), m_MsgIn1.getBody().getList1().getNumberOfElements());
    }

    [Test]
    public void testEncodeToSpec()
    {
        byte[] buff = new byte[1024];
        int i;
        MsgIn.Body.List1.Record1 R1 = new MsgIn.Body.List1.Record1();
        MsgIn.Body.List1.Record1 R2 = new MsgIn.Body.List1.Record1();


        Console.WriteLine("[executing test (testEncodeToSpec)(TP_3.3.5.2)]");

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);

        R1.setField1(3);
        R1.setField2(117835012);
        R2.setField1(8);
        R2.setField2(202050057);
        m_MsgIn1.getBody().getList1().addElement(R1);
        m_MsgIn1.getBody().getList1().addElement(R2);


        // verifying message level encode is AS-5684 compliant"
        m_MsgIn1.encode(buff, 0);

        for (i = 0; i < m_MsgIn1.getSize(); i++)
            Assert.AreEqual(i, buff[i]);
    }

    [Test]
    public void testEncodeDecodeOperations()
    {
        byte[] buff = new byte[1024];
        int i;
        MsgIn.Body.List1.Record1 R1 = new MsgIn.Body.List1.Record1();

        for (i = 0; i < 1024; i++)
        {
            buff[i] = (byte)(((2101 * i) + 65543) % 256);
        }

        Console.WriteLine("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]");

        // verifying message level encode/decode reciprocity"


        m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
        m_MsgIn1.getBody().getList1().addElement(R1);

        R1 = new MsgIn.Body.List1.Record1();
        m_MsgIn1.getBody().getList1().addElement(R1);
        m_MsgIn1.getBody().getList1().getElement(0).setField1(15);
        m_MsgIn1.getBody().getList1().getElement(0).setField2(300);
        m_MsgIn1.getBody().getList1().getElement(1).setField1(0);
        m_MsgIn1.getBody().getList1().getElement(1).setField2(65825);

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(127);
        R1 = new MsgIn.Body.List1.Record1();
        m_MsgIn2.getBody().getList1().addElement(R1);

        m_MsgIn2.getBody().getList1().getElement(0).setField1(127);
        m_MsgIn2.getBody().getList1().getElement(0).setField2(127);

        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        Assert.AreEqual(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(2, m_MsgIn2.getBody().getList1().getNumberOfElements());
        Assert.AreEqual(15, m_MsgIn2.getBody().getList1().getElement(0).getField1());
        Assert.AreEqual(300, m_MsgIn2.getBody().getList1().getElement(0).getField2());
        Assert.AreEqual(0, m_MsgIn2.getBody().getList1().getElement(1).getField1());
        Assert.AreEqual(65825, m_MsgIn2.getBody().getList1().getElement(1).getField2());


        // verifying header level encode/decode reciprocity"

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().encode(buff, 0);
        m_MsgIn2.getHeader().decode(buff, 0);
        Assert.AreEqual(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().getHeaderRec().encode(buff, 0);
        m_MsgIn2.getHeader().getHeaderRec().decode(buff, 0);
        Assert.AreEqual(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying body level encode/decode reciprocity"

        m_MsgIn2.getBody().getList1().getElement(0).setField1(0);
        m_MsgIn2.getBody().getList1().getElement(0).setField2(0);
        m_MsgIn2.getBody().getList1().getElement(1).setField1(0);
        m_MsgIn2.getBody().getList1().getElement(1).setField2(0);
        R1 = new MsgIn.Body.List1.Record1();
        R1.setField1(0);
        R1.setField2(0);
        m_MsgIn2.getBody().getList1().addElement(R1);

        m_MsgIn1.getBody().encode(buff, 0);
        m_MsgIn2.getBody().decode(buff, 0);

        Assert.AreEqual(2, m_MsgIn2.getBody().getList1().getNumberOfElements());
        Assert.AreEqual(15, m_MsgIn2.getBody().getList1().getElement(0).getField1());
        Assert.AreEqual(300, m_MsgIn2.getBody().getList1().getElement(0).getField2());
        Assert.AreEqual(0, m_MsgIn2.getBody().getList1().getElement(1).getField1());
        Assert.AreEqual(65825, m_MsgIn2.getBody().getList1().getElement(1).getField2());


        // verifying list level encode/decode reciprocity"

        m_MsgIn2.getBody().getList1().getElement(0).setField1(0);
        m_MsgIn2.getBody().getList1().getElement(0).setField2(0);
        m_MsgIn2.getBody().getList1().getElement(1).setField1(0);
        m_MsgIn2.getBody().getList1().getElement(1).setField2(0);
        R1 = new MsgIn.Body.List1.Record1();
        R1.setField1(0);
        R1.setField2(0);
        m_MsgIn2.getBody().getList1().addElement(R1);

        m_MsgIn1.getBody().getList1().encode(buff, 0);
        m_MsgIn2.getBody().getList1().decode(buff, 0);

        Assert.AreEqual(2, m_MsgIn2.getBody().getList1().getNumberOfElements());
        Assert.AreEqual(15, m_MsgIn2.getBody().getList1().getElement(0).getField1());
        Assert.AreEqual(300, m_MsgIn2.getBody().getList1().getElement(0).getField2());
        Assert.AreEqual(0, m_MsgIn2.getBody().getList1().getElement(1).getField1());
        Assert.AreEqual(65825, m_MsgIn2.getBody().getList1().getElement(1).getField2());

        // verifying record level encode/decode reciprocity"
        m_MsgIn2.getBody().getList1().getElement(0).setField1(0);
        m_MsgIn2.getBody().getList1().getElement(0).setField2(0);

        m_MsgIn1.getBody().getList1().getElement(0).encode(buff, 0);
        m_MsgIn2.getBody().getList1().getElement(0).decode(buff, 0);

        Assert.AreEqual(15, m_MsgIn2.getBody().getList1().getElement(0).getField1());
        Assert.AreEqual(300, m_MsgIn2.getBody().getList1().getElement(0).getField2());
    }

    [Test]
    public void testEquality()
    {
        Console.WriteLine("[executing test (testEquality)]");
        // verifying message equality operator"    
        Assert.IsTrue(m_MsgIn1.isEqual(m_MsgIn1));
        Assert.IsTrue(m_MsgIn2.isEqual(m_MsgIn1));
        Assert.IsTrue(m_MsgIn1.isEqual(m_MsgIn2));
    }

    [Test]
    public void testInequality()
    {
        Console.WriteLine("[executing test (testInequality)]");
        // verifying message inequality operator"    

        Assert.IsFalse(m_MsgIn1.notEquals(m_MsgIn2));
        Assert.IsFalse(m_MsgIn2.notEquals(m_MsgIn1));
    }

    [Test]
    public void testListOperations()
    {
        MsgIn.Body.List1.Record1 R1 = new MsgIn.Body.List1.Record1();
        int i, j;

        Console.WriteLine("[executing test (testListOperations)]");

        // verifying MsgIn getNumberOfElements with Add and Delete operations"    
        i = j = 0;
        for (i = 0; i < 4; i++)
        {
            Assert.AreEqual(i, m_MsgIn1.getBody().getList1().getNumberOfElements());
            for (j = 0; j < 4; j++)
            {
                Assert.AreEqual((i * 4) + j, m_MsgIn2.getBody().getList1().getNumberOfElements());
                R1 = new MsgIn.Body.List1.Record1();
                m_MsgIn2.getBody().getList1().addElement(R1);
            }
            R1 = new MsgIn.Body.List1.Record1();    
            m_MsgIn1.getBody().getList1().addElement(R1);
        }
        for (j = 0, i = 16; j < 16; j++, i--)
        {
            Assert.AreEqual(i, m_MsgIn2.getBody().getList1().getNumberOfElements());
            m_MsgIn2.getBody().getList1().deleteLastElement();
        }

        // verifying deleteElement preserves order & corrects count of remaining elements"                          
        for (i = 0; i < 4; i++)
        {
            m_MsgIn1.getBody().getList1().getElement(i).setField1((sbyte)(i + 1));
            m_MsgIn1.getBody().getList1().getElement(i).setField2((i + 1) * 10);
        }


        m_MsgIn1.getBody().getList1().deleteElement(0);
        Assert.AreEqual(3, m_MsgIn1.getBody().getList1().getNumberOfElements());

        Assert.AreEqual(2, m_MsgIn1.getBody().getList1().getElement(0).getField1());
        Assert.AreEqual(20, m_MsgIn1.getBody().getList1().getElement(0).getField2());
        Assert.AreEqual(3, m_MsgIn1.getBody().getList1().getElement(1).getField1());
        Assert.AreEqual(30, m_MsgIn1.getBody().getList1().getElement(1).getField2());
        Assert.AreEqual(4, m_MsgIn1.getBody().getList1().getElement(2).getField1());
        Assert.AreEqual(40, m_MsgIn1.getBody().getList1().getElement(2).getField2());

        m_MsgIn1.getBody().getList1().deleteElement(1);
        Assert.AreEqual(2, m_MsgIn1.getBody().getList1().getNumberOfElements());

        Assert.AreEqual(2, m_MsgIn1.getBody().getList1().getElement(0).getField1());
        Assert.AreEqual(20, m_MsgIn1.getBody().getList1().getElement(0).getField2());
        Assert.AreEqual(4, m_MsgIn1.getBody().getList1().getElement(1).getField1());
        Assert.AreEqual(40, m_MsgIn1.getBody().getList1().getElement(1).getField2());

        m_MsgIn1.getBody().getList1().deleteElement(1);
        Assert.AreEqual(1, m_MsgIn1.getBody().getList1().getNumberOfElements());

        Assert.AreEqual(2, m_MsgIn1.getBody().getList1().getElement(0).getField1());
        Assert.AreEqual(20, m_MsgIn1.getBody().getList1().getElement(0).getField2());

        m_MsgIn1.getBody().getList1().deleteElement(0);
        Assert.AreEqual(0, m_MsgIn1.getBody().getList1().getNumberOfElements());
    }

    public static void Main()
    {
    }
}
