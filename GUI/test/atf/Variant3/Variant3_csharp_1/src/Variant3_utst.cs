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
using urn_org_jts_test_Variant3_1_0;

[TestFixture]
public class Variant3_utst
{
    public Variant3_utst()
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

        // check vtags are assigned 0 initially
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getFieldValue());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getFieldValue());

        // check lists are empty initially
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getListA().getNumberOfElements());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getListB().getNumberOfElements());

        Console.Out.WriteLine("  [completed test testConstructionDefaults] ");
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

        // add elements (records with ushorts) to ListA.
        for (ushort i = 0; i < 10; ++i)
        {
            MsgIn.Body.VariantTop.ListA.RecA1 temprec = new MsgIn.Body.VariantTop.ListA.RecA1();
            temprec.setFieldA1((byte)(10 + i));
            msgIn1.getBody().getVariantTop().getListA().addElement(temprec);
        }

        // verify new contents were stored 
        Assert.AreEqual(10, msgIn1.getBody().getVariantTop().getListA().getNumberOfElements());

        for (ushort i = 0; i < 10; ++i)
        {
            Assert.AreEqual(10 + i, msgIn1.getBody().getVariantTop().getListA().getElement(i).getFieldA1());
        }

        // try changing and deleting some elements and ensure expected results occur.
        MsgIn.Body.VariantTop.ListA list_alias = msgIn1.getBody().getVariantTop().getListA(); // less typing

        list_alias.deleteElement(1);
        list_alias.deleteElement(1);
        list_alias.deleteLastElement();
        list_alias.deleteLastElement();

        Assert.AreEqual(6, list_alias.getNumberOfElements());

        Assert.AreEqual(10, list_alias.getElement(0).getFieldA1());
        Assert.AreEqual(13, list_alias.getElement(1).getFieldA1());
        Assert.AreEqual(14, list_alias.getElement(2).getFieldA1());
        Assert.AreEqual(15, list_alias.getElement(3).getFieldA1());
        Assert.AreEqual(16, list_alias.getElement(4).getFieldA1());
        Assert.AreEqual(17, list_alias.getElement(5).getFieldA1());

        MsgIn.Body.VariantTop.ListA.RecA1 rec1 = new MsgIn.Body.VariantTop.ListA.RecA1();
        MsgIn.Body.VariantTop.ListA.RecA1 rec2 = new MsgIn.Body.VariantTop.ListA.RecA1();
        MsgIn.Body.VariantTop.ListA.RecA1 rec3 = new MsgIn.Body.VariantTop.ListA.RecA1();
        rec1.setFieldA1(99);
        rec2.setFieldA1(199);
        rec3.setFieldA1(299);

        list_alias.setElement(5, rec1);
        list_alias.addElement(rec2);
        list_alias.addElement(rec3);

        Assert.AreEqual(10, list_alias.getElement(0).getFieldA1());
        Assert.AreEqual(13, list_alias.getElement(1).getFieldA1());
        Assert.AreEqual(14, list_alias.getElement(2).getFieldA1());
        Assert.AreEqual(15, list_alias.getElement(3).getFieldA1());
        Assert.AreEqual(16, list_alias.getElement(4).getFieldA1());
        Assert.AreEqual(99, list_alias.getElement(5).getFieldA1());
        Assert.AreEqual(199, list_alias.getElement(6).getFieldA1());
        Assert.AreEqual(299, list_alias.getElement(7).getFieldA1());

        // check size is correct
        // 2-byte header, 1 1-byte vtag field, 1 1-byte count field, 8 2-byte store shorts
        Assert.AreEqual(2 + 1 + 1 + 2 * 8, msgIn1.getSize());

        Console.Out.WriteLine("  [completed test (testSetGetOperations)] ");
    }

    [Test]
    public void testEncodeToSpec()
    {
        byte[] buff = new byte[1024];
        for (int i = 0; i < 1024; i++)
        {
            buff[i] = 0;
        }

        Console.Out.WriteLine("  [executing test (testEncodeToSpec)(TP_3.3.5.2)] ");

        msgIn1.getHeader().getHeaderRec().setMessageID(500);
        msgIn1.getBody().getVariantTop().setFieldValue(1);

        // fill list and encode into buff
        for (ushort i = 0; i < 10; ++i)
        {
            MsgIn.Body.VariantTop.ListB.RecB1 temprec = new MsgIn.Body.VariantTop.ListB.RecB1();
            temprec.setFieldB1((short)(20 + i));
            msgIn1.getBody().getVariantTop().getListB().addElement(temprec);
        }

        msgIn1.encode(buff, 0);

        // verify encoding matches expectations from spec document.

        // offset 0:   ushort w/ message ID
        Assert.AreEqual(500, BitConverter.ToUInt16(buff, 0));

        // offset 2:   byte w/ VariantTop's vtag field
        Assert.AreEqual(1, buff[2]);

        // offset 3:   byte w/ ListB's length
        Assert.AreEqual(10, buff[3]);

        int offset = 4; // list data starts at offset 4, ends at offset 22.
        for (ushort i = 0; i < 10; ++i)
        {
            Assert.AreEqual(20 + i, BitConverter.ToUInt16(buff, offset));
            offset += 2;
        }

        // byte 23 should be 0, past end of message data.
        Assert.AreEqual(0, buff[23]);

        Console.Out.WriteLine("  [completed test (testEncodeToSpec)] ");
    }

    [Test]
    public void testEncodeDecodeOperations()
    {
        byte[] buff = new byte[1024];

        for (int i = 0; i < 1024; i++)
        {
            buff[i] = 0;
        }

        Console.Out.WriteLine("  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] ");

        // fill msg1, select ListA as active.
        msgIn1.getHeader().getHeaderRec().setMessageID(500);
        msgIn1.getBody().getVariantTop().setFieldValue(0);
        for (ushort i = 0; i < 5; ++i)
        {
            MsgIn.Body.VariantTop.ListA.RecA1 temprec = new MsgIn.Body.VariantTop.ListA.RecA1();
            temprec.setFieldA1((short)(10 + i));
            msgIn1.getBody().getVariantTop().getListA().addElement(temprec);
        }

        // fill msg2, select ListB as active.
        msgIn2.getHeader().getHeaderRec().setMessageID(1000);
        msgIn2.getBody().getVariantTop().setFieldValue(1);
        for (ushort i = 0; i < 10; ++i)
        {
            MsgIn.Body.VariantTop.ListB.RecB1 temprec = new MsgIn.Body.VariantTop.ListB.RecB1();
            temprec.setFieldB1((short)(20 + i));
            msgIn2.getBody().getVariantTop().getListB().addElement(temprec);
        }

        // encode/decode msg2 into msg1.  verify ListB now active in msg1 and verify list contents copied over
        msgIn2.encode(buff, 0);
        msgIn1.decode(buff, 0);

        Assert.AreEqual(1000, msgIn1.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getFieldValue());
        for (ushort i = 0; i < 10; ++i)
        {
            Assert.AreEqual(20 + i, msgIn1.getBody().getVariantTop().getListB().getElement(i).getFieldB1());
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

