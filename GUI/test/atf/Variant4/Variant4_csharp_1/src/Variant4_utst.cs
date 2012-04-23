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

// test for message structure with <variant> containing <sequence>s

using System;
using NUnit.Framework;
using urn_org_jts_test_Variant4_1_0;


[TestFixture]
public class Variant4_utst
{

    // need to repeatedly reset the contents of msgIn1 in during testEncodeDecodeOperations.
    void resetMessage(MsgIn for_reset)
    {
        for_reset.getHeader().getHeaderRec().setMessageID(1000);
        for_reset.getBody().getVariantTop().setFieldValue(1);
        for_reset.getBody().getVariantTop().getSeqB().getRecB().setFieldB1(9000);

        for (int i = 0; i < 10; ++i)
        {
            MsgIn.Body.VariantTop.SeqB.ListB.RecBInner temprec = new MsgIn.Body.VariantTop.SeqB.ListB.RecBInner();
            temprec.setFieldB2((short)(20 + i));
            for_reset.getBody().getVariantTop().getSeqB().getListB().addElement(temprec);
        }
    }

    public Variant4_utst()
    {
    }

    private MsgIn msgIn1;
    private MsgIn msgIn2;

    [SetUp]
    public void setUp()
    {
        msgIn1 = new MsgIn();
        msgIn2 = new MsgIn();
    }

    [Test]
    public void testConstructionDefaults()
    {
        Console.Out.WriteLine("  [executing test (testConstructionDefaults)] ");

        Assert.AreEqual(msgIn1.getHeader().getHeaderRec().getMessageID(), 1);
        Assert.AreEqual(msgIn2.getHeader().getHeaderRec().getMessageID(), 1);

        // check vtag fields are assigned 0 initially
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getFieldValue());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getFieldValue());

        // check fixed fields are zeroed and lists are empty initially.
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getSeqA().getRecA().getFieldA1());
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getSeqB().getRecB().getFieldB1());
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getSeqA().getListA().getNumberOfElements());
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getSeqB().getListB().getNumberOfElements());

        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getSeqA().getRecA().getFieldA1());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getSeqB().getRecB().getFieldB1());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getSeqA().getListA().getNumberOfElements());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getSeqB().getListB().getNumberOfElements());

        Console.Out.WriteLine("  [completed test (testSetGetOperations)] ");
    }

    [Test]
    public void testSetGetOperations()
    {
        Console.Out.WriteLine("  [executing test (testSetGetOperations)(TP_3.3.5.1)] ");

        // check header field changes are recorded properly
        msgIn1.getHeader().getHeaderRec().setMessageID(5);
        Assert.AreEqual(5, msgIn1.getHeader().getHeaderRec().getMessageID());
        msgIn1.getHeader().getHeaderRec().setMessageID(1);
        Assert.AreEqual(1, msgIn1.getHeader().getHeaderRec().getMessageID());

        // check vtag field changes are recorded properly
        msgIn1.getBody().getVariantTop().setFieldValue(1);
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getFieldValue());
        msgIn1.getBody().getVariantTop().setFieldValue(0);
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getFieldValue());

        // set/add elements to active sequence, SeqA, verify changes are recorded.
        msgIn1.getBody().getVariantTop().getSeqA().getRecA().setFieldA1(45);
        for (int i = 0; i < 10; ++i)
        {
            MsgIn.Body.VariantTop.SeqA.ListA.RecAInner temprec = new MsgIn.Body.VariantTop.SeqA.ListA.RecAInner();
            temprec.setFieldA2((short)(10 + i));
            msgIn1.getBody().getVariantTop().getSeqA().getListA().addElement(temprec);
        }

        Assert.AreEqual(45, msgIn1.getBody().getVariantTop().getSeqA().getRecA().getFieldA1());
        for (int i = 0; i < 10; ++i)
        {
            Assert.AreEqual(10 + i, msgIn1.getBody().getVariantTop().getSeqA().getListA().getElement(i).getFieldA2());
        }


        // set/add elements to active sequence, SeqB, verify changes are recorded.
        msgIn1.getBody().getVariantTop().setFieldValue(1);
        msgIn1.getBody().getVariantTop().getSeqB().getRecB().setFieldB1(90);
        for (int i = 0; i < 5; ++i)
        {
            MsgIn.Body.VariantTop.SeqB.ListB.RecBInner temprec = new MsgIn.Body.VariantTop.SeqB.ListB.RecBInner();
            temprec.setFieldB2((short)(20 + i));
            msgIn1.getBody().getVariantTop().getSeqB().getListB().addElement(temprec);
        }

        Assert.AreEqual(90, msgIn1.getBody().getVariantTop().getSeqB().getRecB().getFieldB1());
        for (int i = 0; i < 5; ++i)
        {
            Assert.AreEqual(20 + i, msgIn1.getBody().getVariantTop().getSeqB().getListB().getElement(i).getFieldB2());
        }

        // check message size is correct
        // 2-byte ushort msg ID header, 1-byte vtag field, 2-byte ushort in RecB, 1-byte count field for ListB, 
        // 5 2-byte ushorts in ListB
        Assert.AreEqual(2 + 1 + 2 + 1 + 5 * 2, msgIn1.getSize());

        Console.Out.WriteLine("   [completed test (testSetGetOperations)] ");

    }

    [Test]
    public void testEncodeToSpec()
    {
        byte[] buff = new byte[1024];
        int i = 0;

        for (i = 0; i < 1024; i++)
        {
            buff[i] = 0;
        }

        Console.Out.WriteLine("  [executing test (testEncodeToSpec)(TP_3.3.5.2)] ");

        // fill the message in and copy to buffer
        resetMessage(msgIn1);
        msgIn1.encode(buff, 0);

        // verify encoding matches expectations from spec document

        // offset 0:   ushort w/ message ID
        Assert.AreEqual(1000, BitConverter.ToUInt16(buff, 0));

        // offset 2:   byte w/ VariantTop's vtag field
        Assert.AreEqual(1, buff[2]);

        // offset 3:   ushort, FieldB1
        Assert.AreEqual(9000, BitConverter.ToUInt16(buff, 3));

        // offset 5:   byte w/ ListB count field
        Assert.AreEqual(10, buff[5]);

        // offsets 6-24: 10 ushort values in list
        int offset = 6;
        for (i = 0; i < 10; ++i)
        {
            Assert.AreEqual(20 + i, BitConverter.ToUInt16(buff, offset));
            offset += 2;
        }

        Console.Out.WriteLine("  [completed test (testEncodeToSpec)] ");

    }

    [Test]
    public void testEncodeDecodeOperations()
    {
        byte[] buff = new byte[1024];
        int i;

        for (i = 0; i < 1024; i++)
        {
            buff[i] = 0;
        }

        Console.Out.WriteLine("  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] ");

        // setup msg1
        resetMessage(msgIn1);

        // setup msg2
        msgIn2.getHeader().getHeaderRec().setMessageID(500);
        msgIn2.getBody().getVariantTop().setFieldValue(1);
        msgIn2.getBody().getVariantTop().getSeqB().getRecB().setFieldB1(4500);

        for (i = 0; i < 15; ++i)
        {
            MsgIn.Body.VariantTop.SeqB.ListB.RecBInner temprec = new MsgIn.Body.VariantTop.SeqB.ListB.RecBInner();
            temprec.setFieldB2((short)(5 + i));
            msgIn2.getBody().getVariantTop().getSeqB().getListB().addElement(temprec);
        }

        // copy entire msg2 into msg1, verify new contents are correct
        msgIn2.encode(buff, 0);
        msgIn1.decode(buff, 0);

        Assert.AreEqual(500, msgIn1.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getFieldValue());
        Assert.AreEqual(4500, msgIn1.getBody().getVariantTop().getSeqB().getRecB().getFieldB1());

        for (i = 0; i < 15; ++i)
        {
            Assert.AreEqual(5 + i, msgIn1.getBody().getVariantTop().getSeqB().getListB().getElement(i).getFieldB2());
        }

        // reset msg1.
        resetMessage(msgIn1);

        // copy only variant from msg2 into msg1, verify contents
        msgIn2.getBody().getVariantTop().encode(buff, 0);
        msgIn1.getBody().getVariantTop().decode(buff, 0);

        Assert.AreEqual(1000, msgIn1.getHeader().getHeaderRec().getMessageID()); // unchanged from reset values
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getFieldValue());
        Assert.AreEqual(4500, msgIn1.getBody().getVariantTop().getSeqB().getRecB().getFieldB1());

        for (i = 0; i < 15; ++i)
        {
            Assert.AreEqual(5 + i, msgIn1.getBody().getVariantTop().getSeqB().getListB().getElement(i).getFieldB2());
        }

        // reset msg1.
        resetMessage(msgIn1);

        // copy only seqB's listB from msg2 into msg1, verify contents.
        msgIn2.getBody().getVariantTop().getSeqB().getListB().encode(buff, 0);
        msgIn1.getBody().getVariantTop().getSeqB().getListB().decode(buff, 0);

        Assert.AreEqual(1000, msgIn1.getHeader().getHeaderRec().getMessageID()); // unchanged from reset values
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getFieldValue());
        Assert.AreEqual(9000, msgIn1.getBody().getVariantTop().getSeqB().getRecB().getFieldB1()); // should also be unchanged.

        for (i = 0; i < 15; ++i)
        {
            Assert.AreEqual(5 + i, msgIn1.getBody().getVariantTop().getSeqB().getListB().getElement(i).getFieldB2());
        }

        Console.Out.WriteLine("  [completed test (testEncodeDecodeOperations)] ");

    }

    [Test]
    public void testEquality()
    {
        Console.Out.WriteLine("  [executing test (testEquality)] ");
        Assert.IsTrue(msgIn1.isEqual(msgIn2));
        Assert.IsTrue(msgIn1.isEqual(msgIn1));
        Assert.IsTrue(msgIn2.isEqual(msgIn1));
        Console.Out.WriteLine("  [completed test (testEquality)] ");

    }

    [Test]
    public void testInequality()
    {
        Console.Out.WriteLine("  [executing test (testInequality)] ");
        Assert.IsFalse(msgIn1.notEquals(msgIn2));
        Assert.IsFalse(msgIn2.notEquals(msgIn1));
        Console.Out.WriteLine("  [completed test (testInequality)] ");

    }

    public static void Main()
    {
    }
}

