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

// test for message structure with <array> of <bit_field>s

using System;
using NUnit.Framework;
using urn_org_jts_test_Array8_1_0;


[TestFixture]
public class Array8_utst
{

    ushort rgbToUnsignedShortBitfield(ushort R, ushort G, ushort B)
    {
        return (ushort)((B << 11) | (G << 5) | (R));
    }

    public Array8_utst()
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

        Assert.AreEqual(2, msgIn1.getBody().getRecord().getArray8().getXSize());
        Assert.AreEqual(4, msgIn1.getBody().getRecord().getArray8().getYSize());
        Assert.AreEqual(2, msgIn2.getBody().getRecord().getArray8().getXSize());
        Assert.AreEqual(4, msgIn2.getBody().getRecord().getArray8().getYSize());

        Assert.AreEqual(0, msgIn1.getBody().getRecord().getArray8().getColorField(1, 3).getR());
        Assert.AreEqual(0, msgIn1.getBody().getRecord().getArray8().getColorField(1, 3).getG());
        Assert.AreEqual(0, msgIn1.getBody().getRecord().getArray8().getColorField(1, 3).getB());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray8().getColorField(1, 3).getR());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray8().getColorField(1, 3).getG());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray8().getColorField(1, 3).getB());

        Console.Out.WriteLine("  [completed test (testConstructionDefaults)] ");
    }

    [Test]
    public void testSetGetOperations()
    {
        Console.Out.WriteLine("  [executing test testSetGetOperations)(TP_3.3.5.1)] ");

        msgIn1.getHeader().getHeaderRec().setMessageID(5);
        Assert.AreEqual(msgIn1.getHeader().getHeaderRec().getMessageID(), 5);
        msgIn1.getHeader().getHeaderRec().setMessageID(1);
        Assert.AreEqual(msgIn1.getHeader().getHeaderRec().getMessageID(), 1);

        for (int row = 0; row < msgIn1.getBody().getRecord().getArray8().getYSize(); ++row)
        {
            for (int col = 0; col < msgIn1.getBody().getRecord().getArray8().getXSize(); ++col)
            {
                msgIn1.getBody().getRecord().getArray8().getColorField(col, row).setR(31);
                msgIn1.getBody().getRecord().getArray8().getColorField(col, row).setG(63);
                msgIn1.getBody().getRecord().getArray8().getColorField(col, row).setB(31);
            }
        }

        for (int row = 0; row < msgIn2.getBody().getRecord().getArray8().getYSize(); ++row)
        {
            for (int col = 0; col < msgIn2.getBody().getRecord().getArray8().getXSize(); ++col)
            {
                msgIn2.getBody().getRecord().getArray8().getColorField(col, row).setR(20);
                msgIn2.getBody().getRecord().getArray8().getColorField(col, row).setG(31);
                msgIn2.getBody().getRecord().getArray8().getColorField(col, row).setB(20);
            }
        }

        for (int row = 0; row < msgIn1.getBody().getRecord().getArray8().getYSize(); ++row)
        {
            for (int col = 0; col < msgIn1.getBody().getRecord().getArray8().getXSize(); ++col)
            {
                Assert.AreEqual(31, msgIn1.getBody().getRecord().getArray8().getColorField(col, row).getR());
                Assert.AreEqual(63, msgIn1.getBody().getRecord().getArray8().getColorField(col, row).getG());
                Assert.AreEqual(31, msgIn1.getBody().getRecord().getArray8().getColorField(col, row).getB());
            }
        }

        for (int row = 0; row < msgIn2.getBody().getRecord().getArray8().getYSize(); ++row)
        {
            for (int col = 0; col < msgIn2.getBody().getRecord().getArray8().getXSize(); ++col)
            {
                Assert.AreEqual(20, msgIn2.getBody().getRecord().getArray8().getColorField(col, row).getR());
                Assert.AreEqual(31, msgIn2.getBody().getRecord().getArray8().getColorField(col, row).getG());
                Assert.AreEqual(20, msgIn2.getBody().getRecord().getArray8().getColorField(col, row).getB());
            }
        }

        Console.Out.WriteLine("  [completed test (testSetGetOperations)] ");
    }

    [Test]
    public void testEncodeToSpec()
    {
        byte[] buff = new byte[1024];

        int i;

        for (i = 0; i < 1024; ++i)
        {
            buff[i] = 0;
        }

        Console.Out.WriteLine("  [executing test (testEncodeToSpec)(TP_3.3.5.2)] ");

        msgIn1.getHeader().getHeaderRec().setMessageID(99);

        // at X = 0, Y = 0 in array, RGB = (5, 10, 5)
        msgIn1.getBody().getRecord().getArray8().getColorField(0, 0).setR(5);
        msgIn1.getBody().getRecord().getArray8().getColorField(0, 0).setG(10);
        msgIn1.getBody().getRecord().getArray8().getColorField(0, 0).setB(5);

        // at 1 = 3, Y = 0 in array, RGB = (13, 27, 2)
        msgIn1.getBody().getRecord().getArray8().getColorField(1, 0).setR(13);
        msgIn1.getBody().getRecord().getArray8().getColorField(1, 0).setG(27);
        msgIn1.getBody().getRecord().getArray8().getColorField(1, 0).setB(2);

        // at X = 0, Y = 3 in array, RGB = (31,63,9)
        msgIn1.getBody().getRecord().getArray8().getColorField(0, 3).setR(31);
        msgIn1.getBody().getRecord().getArray8().getColorField(0, 3).setG(63);
        msgIn1.getBody().getRecord().getArray8().getColorField(0, 3).setB(9);

        // at X = 1, Y = 3 in array, RGB = (0,35,15)
        msgIn1.getBody().getRecord().getArray8().getColorField(1, 3).setR(0);
        msgIn1.getBody().getRecord().getArray8().getColorField(1, 3).setG(35);
        msgIn1.getBody().getRecord().getArray8().getColorField(1, 3).setB(15);

        msgIn1.encode(buff, 0);

        // offset 0   : ushort with message ID
        Assert.AreEqual(99, BitConverter.ToUInt16(buff, 0));
        // offset 2   : start of array data, ushort at x = 0, y = 0
        Assert.AreEqual(rgbToUnsignedShortBitfield(5, 10, 5), BitConverter.ToUInt16(buff, 2 + 0));
        // offset 4   : 2nd entry in array data, ushort at x = 1, y = 0
        Assert.AreEqual(rgbToUnsignedShortBitfield(13, 27, 2), BitConverter.ToUInt16(buff, 2 + 2));
        // offset 14   : 7th entry in array data, ushort at x = 0, y = 3
        Assert.AreEqual(rgbToUnsignedShortBitfield(31, 63, 9), BitConverter.ToUInt16(buff, 2 + 12));
        // offset 16   : 8th entry in array data, ushort at x = 1, y = 3
        Assert.AreEqual(rgbToUnsignedShortBitfield(0, 35, 15), BitConverter.ToUInt16(buff, 2 + 14));

        // offset 18   : 0, past end of encoded message!
        Assert.AreEqual(0, buff[18]);

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

        // set initial contents of messages to differ.

        msgIn1.getHeader().getHeaderRec().setMessageID(1);

        for (int row = 0; row < msgIn1.getBody().getRecord().getArray8().getYSize(); ++row)
        {
            for (int col = 0; col < msgIn1.getBody().getRecord().getArray8().getXSize(); ++col)
            {

                msgIn1.getBody().getRecord().getArray8().getColorField(col, row).setR(31);
                msgIn1.getBody().getRecord().getArray8().getColorField(col, row).setG(63);
                msgIn1.getBody().getRecord().getArray8().getColorField(col, row).setB(31);
            }
        }

        msgIn2.getHeader().getHeaderRec().setMessageID(999);

        for (int row = 0; row < msgIn2.getBody().getRecord().getArray8().getYSize(); ++row)
        {
            for (int col = 0; col < msgIn2.getBody().getRecord().getArray8().getXSize(); ++col)
            {
                msgIn2.getBody().getRecord().getArray8().getColorField(col, row).setR(0);
                msgIn2.getBody().getRecord().getArray8().getColorField(col, row).setG(0);
                msgIn2.getBody().getRecord().getArray8().getColorField(col, row).setB(0);
            }
        }

        msgIn1.encode(buff, 0);

        // decode contents of msg1 into msg2.  fields of msg2 should now match fields of msg1.

        msgIn2.decode(buff, 0);

        Assert.AreEqual(msgIn1.getHeader().getHeaderRec().getMessageID(), msgIn2.getHeader().getHeaderRec().getMessageID());

        for (int row = 0; row < msgIn2.getBody().getRecord().getArray8().getYSize(); ++row)
        {
            for (int col = 0; col < msgIn2.getBody().getRecord().getArray8().getXSize(); ++col)
            {
                Assert.AreEqual(msgIn1.getBody().getRecord().getArray8().getColorField(col, row).getR(), msgIn2.getBody().getRecord().getArray8().getColorField(col, row).getR());
                Assert.AreEqual(msgIn1.getBody().getRecord().getArray8().getColorField(col, row).getG(), msgIn2.getBody().getRecord().getArray8().getColorField(col, row).getG());
                Assert.AreEqual(msgIn1.getBody().getRecord().getArray8().getColorField(col, row).getB(), msgIn2.getBody().getRecord().getArray8().getColorField(col, row).getB());
            }
        }

        // change msg1's message ID..
        msgIn1.getHeader().getHeaderRec().setMessageID(9000);

        // change contents of msg2's array, encode the array *alone* into the buffer, then decode
        // the buffer into msg1's array.
        for (int row = 0; row < msgIn2.getBody().getRecord().getArray8().getYSize(); ++row)
        {
            for (int col = 0; col < msgIn2.getBody().getRecord().getArray8().getXSize(); ++col)
            {
                msgIn2.getBody().getRecord().getArray8().getColorField(col, row).setR(15);
                msgIn2.getBody().getRecord().getArray8().getColorField(col, row).setG(8);
                msgIn2.getBody().getRecord().getArray8().getColorField(col, row).setB(4);
            }
        }

        msgIn2.getBody().getRecord().getArray8().encode(buff, 0);
        msgIn1.getBody().getRecord().getArray8().decode(buff, 0);

        // since the encode/decode only touched the array, msg1's message ID should still be 9000!
        Assert.AreEqual(9000, msgIn1.getHeader().getHeaderRec().getMessageID());

        // the R/G/B values for msg1's array should now match what was set in msg2's array.
        for (int row = 0; row < msgIn1.getBody().getRecord().getArray8().getYSize(); ++row)
        {
            for (int col = 0; col < msgIn1.getBody().getRecord().getArray8().getXSize(); ++col)
            {
                Assert.AreEqual(15, msgIn1.getBody().getRecord().getArray8().getColorField(col, row).getR());
                Assert.AreEqual(8, msgIn1.getBody().getRecord().getArray8().getColorField(col, row).getG());
                Assert.AreEqual(4, msgIn1.getBody().getRecord().getArray8().getColorField(col, row).getB());
            }
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

