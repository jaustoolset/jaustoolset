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
using urn_org_jts_test_Variant2_1_0;

[TestFixture]
public class Variant2_utst
{

    public Variant2_utst()
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

        Assert.AreEqual(1, msgIn1.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(1, msgIn2.getHeader().getHeaderRec().getMessageID());


        // check vtag fields are assigned 0 initially
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getFieldValue());
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getVariantA().getFieldValue());
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getVariantB().getFieldValue());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getFieldValue());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getVariantA().getFieldValue());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getVariantB().getFieldValue());

        // check all fields are 0 intially.
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getVariantA().getRecA1().getFieldA1());
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getVariantA().getRecA2().getFieldA2());
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getVariantB().getRecB1().getFieldB1());
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getVariantB().getRecB2().getFieldB2());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getVariantA().getRecA1().getFieldA1());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getVariantA().getRecA2().getFieldA2());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getVariantB().getRecB1().getFieldB1());
        Assert.AreEqual(0, msgIn2.getBody().getVariantTop().getVariantB().getRecB2().getFieldB2());

        Console.Out.WriteLine("  [completed test (testSetGetOperations)] ");
    }

    [Test]
    public void testSetGetOperations()
    {
        Console.Out.WriteLine("  [executing test (testSetGetOperations)(TP_3.3.5.1)] ");

        msgIn1.getHeader().getHeaderRec().setMessageID(5);
        Assert.AreEqual(msgIn1.getHeader().getHeaderRec().getMessageID(), 5);
        msgIn1.getHeader().getHeaderRec().setMessageID(1);
        Assert.AreEqual(msgIn1.getHeader().getHeaderRec().getMessageID(), 1);

        // check vtag field changes are recorded properly
        msgIn1.getBody().getVariantTop().setFieldValue(1);
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getFieldValue());
        msgIn1.getBody().getVariantTop().getVariantA().setFieldValue(1);
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getVariantA().getFieldValue());
        msgIn1.getBody().getVariantTop().getVariantB().setFieldValue(1);
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getVariantB().getFieldValue());

        // check field changes are recorded properly
        msgIn1.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1(99);
        Assert.AreEqual(99, msgIn1.getBody().getVariantTop().getVariantA().getRecA1().getFieldA1());
        msgIn1.getBody().getVariantTop().getVariantA().getRecA2().setFieldA2(88);
        Assert.AreEqual(88, msgIn1.getBody().getVariantTop().getVariantA().getRecA2().getFieldA2());
        msgIn1.getBody().getVariantTop().getVariantB().getRecB1().setFieldB1(77);
        Assert.AreEqual(77, msgIn1.getBody().getVariantTop().getVariantB().getRecB1().getFieldB1());
        msgIn1.getBody().getVariantTop().getVariantB().getRecB2().setFieldB2(66);
        Assert.AreEqual(66, msgIn1.getBody().getVariantTop().getVariantB().getRecB2().getFieldB2());

        // chech getsize() returns accurate value
        // 2-byte header, 2 1-byte vtag fields, 1 2-byte ushort
        Assert.AreEqual(2 + 1 + 1 + 2, msgIn1.getSize());

        Console.Out.WriteLine("  [completed test (testSetGetOperations)] ");
    }

    [Test]
    public void testEncodeToSpec()
    {
        byte[] buff = new byte[1024];
        int i;

        for (i = 0; i < 1024; i++)
        {
            buff[i] = 0;
        }

        Console.Out.WriteLine("  [executing test (testEncodeToSpec)(TP_3.3.5.2)] ");

        // fill msg1, select VariantA, RecA1 as active, encode.
        msgIn1.getHeader().getHeaderRec().setMessageID(500);

        msgIn1.getBody().getVariantTop().setFieldValue(0);
        msgIn1.getBody().getVariantTop().getVariantA().setFieldValue(0);
        msgIn1.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1(42);

        msgIn1.encode(buff, 0);

        // verify encoding matches expectations from spec document.

        // offset 0:   ushort w/ message ID
        Assert.AreEqual(500, BitConverter.ToUInt16(buff, 0));

        // offset 2:   byte w/ VariantTop's vtag field
        Assert.AreEqual(0, buff[2]);

        // offset 3:   byte w/ VariantA's vtag field
        Assert.AreEqual(0, buff[3]);

        // offset 4:   ushort w/ message ID
        Assert.AreEqual(42, BitConverter.ToUInt16(buff, 4));

        // fill msg1, select VariantB, RecB1 as active
        msgIn1.getHeader().getHeaderRec().setMessageID(600);

        msgIn1.getBody().getVariantTop().setFieldValue(1);
        msgIn1.getBody().getVariantTop().getVariantB().setFieldValue(0);
        msgIn1.getBody().getVariantTop().getVariantB().getRecB1().setFieldB1(3456);

        msgIn1.encode(buff, 0);

        // verify encoding matches expectations from spec document
        // offset 0:   ushort w/ message ID
        Assert.AreEqual(600, BitConverter.ToUInt16(buff, 0));

        // offset 2:   byte w/ VariantTop's vtag field
        Assert.AreEqual(1, buff[2]);

        // offset 3:   byte w/ VariantA's vtag field
        Assert.AreEqual(0, buff[3]);

        // offset 4:   ushort w/ message ID
        Assert.AreEqual(3456, BitConverter.ToUInt16(buff, 4));

        // offset 6: 0, past end of message data.


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

        // fill msg1, select VariantA, RecA1 as active.
        msgIn1.getHeader().getHeaderRec().setMessageID(500);

        msgIn1.getBody().getVariantTop().setFieldValue(0);
        msgIn1.getBody().getVariantTop().getVariantA().setFieldValue(0);
        msgIn1.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1(19);
        msgIn1.getBody().getVariantTop().getVariantB().getRecB2().setFieldB2(0);

        // fill msg2, select VariantB, RecB2 as active.
        msgIn2.getHeader().getHeaderRec().setMessageID(1000);

        msgIn2.getBody().getVariantTop().setFieldValue(1);
        msgIn2.getBody().getVariantTop().getVariantB().setFieldValue(1);
        msgIn2.getBody().getVariantTop().getVariantB().getRecB2().setFieldB2(29);

        // encode/decode msg2 into msg1.  msg1 should now show active VariantB, RecB2; verify field contents are
        // correct.

        msgIn2.encode(buff, 0);
        msgIn1.decode(buff, 0);

        Assert.AreEqual(1000, msgIn1.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getFieldValue());
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getVariantB().getFieldValue());
        Assert.AreEqual(29, msgIn1.getBody().getVariantTop().getVariantB().getRecB2().getFieldB2());

        // change the msg ID on msg1 and fill new values in msg1.
        // fill msg1, select VariantA, RecA1 as active.
        msgIn1.getHeader().getHeaderRec().setMessageID(1999);

        msgIn1.getBody().getVariantTop().setFieldValue(0);
        msgIn1.getBody().getVariantTop().getVariantA().setFieldValue(0);
        msgIn1.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1(0);
        msgIn1.getBody().getVariantTop().getVariantA().getRecA2().setFieldA2(0);
        msgIn1.getBody().getVariantTop().getVariantB().setFieldValue(0);
        msgIn1.getBody().getVariantTop().getVariantB().getRecB1().setFieldB1(0);
        msgIn1.getBody().getVariantTop().getVariantB().getRecB2().setFieldB2(0);

        // encode/decode msg2's VariantTop into msg1.  msg1 should now show changed variant, but keep the msg ID.
        msgIn2.getBody().getVariantTop().encode(buff, 0);
        msgIn1.getBody().getVariantTop().decode(buff, 0);

        Assert.AreEqual(1999, msgIn1.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getFieldValue());
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getVariantA().getFieldValue());
        Assert.AreEqual(1, msgIn1.getBody().getVariantTop().getVariantB().getFieldValue());
        Assert.AreEqual(29, msgIn1.getBody().getVariantTop().getVariantB().getRecB2().getFieldB2());

        // set msg1's VariantTop so VariantA is active, also make RecA2 active inside.
        //  Also make VariantA active in msg2, but make RecA1 active.
        msgIn1.getBody().getVariantTop().setFieldValue(0);
        msgIn1.getBody().getVariantTop().getVariantA().setFieldValue(1);
        msgIn1.getBody().getVariantTop().getVariantA().getRecA2().setFieldA2(9000);
        msgIn1.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1(0);

        msgIn2.getBody().getVariantTop().setFieldValue(0);
        msgIn2.getBody().getVariantTop().getVariantA().setFieldValue(0);
        msgIn2.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1(4500);

        // encode/decode msg2's VariantA into msg1.  verify msg1 shows contents from msgIn2.
        msgIn2.getBody().getVariantTop().getVariantA().encode(buff, 0);
        msgIn1.getBody().getVariantTop().getVariantA().decode(buff, 0);

        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getFieldValue());
        Assert.AreEqual(0, msgIn1.getBody().getVariantTop().getVariantA().getFieldValue());
        Assert.AreEqual(4500, msgIn1.getBody().getVariantTop().getVariantA().getRecA1().getFieldA1());

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

