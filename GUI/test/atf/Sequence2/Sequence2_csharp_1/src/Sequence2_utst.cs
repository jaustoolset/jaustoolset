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
using urn_org_jts_test_Sequence2_1_0;

[TestFixture]
public class Sequence2_utst
{
    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Sequence2_utst()
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

        // verifying setField1/getField1 reciprocity"    
        m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().setField1(25);
        m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().setField1(-15);
        Assert.AreEqual(25, m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().getField1());
        Assert.AreEqual(-15, m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().getField1());

        m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().setField2(3000);
        m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().setField2(-3125);

        Assert.AreEqual(25, m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().getField1());
        Assert.AreEqual(-15, m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().getField1());
        Assert.AreEqual(3000, m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().getField2());
        Assert.AreEqual(-3125, m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().getField2());

        MsgIn.Body.Sequence2.List1.Record1 R1 = new MsgIn.Body.Sequence2.List1.Record1();
        MsgIn.Body.Sequence2.List1.Record1 R2 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(99);
        R2.setField1(7);
        m_MsgIn1.getBody().getSequence2().getList1().addElement(R1);
        m_MsgIn2.getBody().getSequence2().getList1().addElement(R2);

        Assert.AreEqual(25, m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().getField1());
        Assert.AreEqual(-15, m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().getField1());
        Assert.AreEqual(3000, m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().getField2());
        Assert.AreEqual(-3125, m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().getField2());
        Assert.AreEqual(99, m_MsgIn1.getBody().getSequence2().getList1().getElement(0).getField1());
        Assert.AreEqual(7, m_MsgIn2.getBody().getSequence2().getList1().getElement(0).getField1());
    }

    [Test]
    public void testEncodeToSpec()
    {
        byte[] buff = new byte[1024];
        int i;

        Console.WriteLine("[executing test (testEncodeToSpec)(TP_3.3.5.2)]");

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);
        m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().setField1(2);
        m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().setField2(100992003);
        MsgIn.Body.Sequence2.List1.Record1 R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(2312);
        m_MsgIn1.getBody().getSequence2().getList1().addElement(R1);
        R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(2826);
        m_MsgIn1.getBody().getSequence2().getList1().addElement(R1);
        R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(3340);
        m_MsgIn1.getBody().getSequence2().getList1().addElement(R1);
        R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(3854);
        m_MsgIn1.getBody().getSequence2().getList1().addElement(R1);
        R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(4368);
        m_MsgIn1.getBody().getSequence2().getList1().addElement(R1);
        R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(4882);
        m_MsgIn1.getBody().getSequence2().getList1().addElement(R1);
        R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(5396);
        m_MsgIn1.getBody().getSequence2().getList1().addElement(R1);


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

        for (i = 0; i < 1024; i++)
        {
            buff[i] = (byte)(((3711 * i) + 65543) % 256);
        }

        Console.WriteLine("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]");

        // verifying message level encode/decode reciprocity"

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(81);
        m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().setField1(71);
        m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().setField2(1234);
        MsgIn.Body.Sequence2.List1.Record1 R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(99);
        m_MsgIn1.getBody().getSequence2().getList1().addElement(R1);

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(127);
        m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().setField1(127);
        m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().setField2(127);

        R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(127);
        m_MsgIn2.getBody().getSequence2().getList1().addElement(R1);
        R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(127);
        m_MsgIn2.getBody().getSequence2().getList1().addElement(R1);

        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        Assert.AreEqual(81, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(71, m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().getField1());
        Assert.AreEqual(1234, m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().getField2());
        Assert.AreEqual(1, m_MsgIn2.getBody().getSequence2().getList1().getNumberOfElements());
        Assert.AreEqual(99, m_MsgIn2.getBody().getSequence2().getList1().getElement(0).getField1());

        // verifying header level encode/decode reciprocity"

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().encode(buff, 0);
        m_MsgIn2.getHeader().decode(buff, 0);
        Assert.AreEqual(81, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().getHeaderRec().encode(buff, 0);
        m_MsgIn2.getHeader().getHeaderRec().decode(buff, 0);
        Assert.AreEqual(81, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying record level encode/decode reciprocity"

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().setField1(0);
        m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().setField2(0);

        R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(1);
        m_MsgIn2.getBody().getSequence2().getList1().addElement(R1);
        R1 = new MsgIn.Body.Sequence2.List1.Record1();
        R1.setField1(254);
        m_MsgIn2.getBody().getSequence2().getList1().addElement(R1);

        m_MsgIn1.getBody().getSequence2().encode(buff, 0);
        m_MsgIn2.getBody().getSequence2().decode(buff, 0);

        Assert.AreEqual(71, m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().getField1());
        Assert.AreEqual(1234, m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().getField2());
        Assert.AreEqual(1, m_MsgIn2.getBody().getSequence2().getList1().getNumberOfElements());
        Assert.AreEqual(99, m_MsgIn2.getBody().getSequence2().getList1().getElement(0).getField1());

        m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().setField1(0);
        m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().setField2(0);

        m_MsgIn1.getBody().getSequence2().getSubsequence1().getRecord1().encode(buff, 0);
        m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().decode(buff, 0);

        Assert.AreEqual(71, m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().getField1());
        Assert.AreEqual(1234, m_MsgIn2.getBody().getSequence2().getSubsequence1().getRecord1().getField2());
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

    public static void Main()
    {
    }
}
