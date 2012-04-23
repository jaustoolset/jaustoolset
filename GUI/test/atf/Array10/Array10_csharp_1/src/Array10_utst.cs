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

// test for message structure with <array> of <variable_format_field>s
using System;
using NUnit.Framework;
using urn_org_jts_test_Array10_1_0;


[TestFixture]
public class Array10_utst
{
    // assert if two arrays of bytes are equal, given a length value.
    void assertArraysEqual(byte[] arr_data1, byte[] arr_data2, int length)
    {
        for (int i = 0; i < length; ++i)
        {
            Assert.AreEqual(arr_data1[i], arr_data2[i]);
        }
    }

    // assert if a region of one array, data1, has the same contents as array data2.  
    // the contents of data2 from index first to first+length-1 are compared with the contents of data2
    // from index 0 to index length-1.
    void assertArraySlicesEqual(byte[] data1, byte[] data2, int first, int length)
    {
        for (int i = 0; i < length; ++i)
        {
            Assert.AreEqual(data1[first + i], data2[i]);
        }
    }

    public Array10_utst()
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

        Assert.AreEqual(10, msgIn1.getBody().getRecord().getArray10().getIDXSize());
        Assert.AreEqual(10, msgIn2.getBody().getRecord().getArray10().getIDXSize());

        for (int i = 0; i < msgIn1.getBody().getRecord().getArray10().getIDXSize(); ++i)
        {
            Assert.AreEqual(0, msgIn1.getBody().getRecord().getArray10().getVFF1(i).getData().Length);
            Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(i).getData().Length);
            Assert.AreEqual(0, msgIn1.getBody().getRecord().getArray10().getVFF1(i).getFormat());
            Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(i).getFormat());
        }

        Console.Out.WriteLine("  [completed test (testConstructionDefaults)] ");
    }

    [Test]
    public void testSetGetOperations()
  {
    Console.Out.WriteLine("  [executing test (testSetGetOperations)(TP_3.3.5.1)] ");
    
    msgIn1.getHeader().getHeaderRec().setMessageID(99);

    byte[] data1 = new byte[1] {0};
    byte[] data2 = new byte[2] {0, 1};
    byte[] data3 = new byte[3] {0, 1, 2};
    byte[] data4 = new byte[4] {0, 1, 2, 3};
    byte[] data5 = new byte[5] {0, 1, 2, 3, 4};
    byte[] data6 = new byte[6] {0, 1, 2, 3, 4, 5};
    byte[] data7 = new byte[7] {0, 1, 2, 3, 4, 5, 6};
    byte[] data8 = new byte[8] {0, 1, 2, 3, 4, 5, 6, 7};
    byte[] data9 = new byte[9] {0, 1, 2, 3, 4, 5, 6, 7, 8};
    byte[] data10 = new byte[10] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    msgIn1.getBody().getRecord().getArray10().getVFF1(0).set(0, 1, data1);
    msgIn1.getBody().getRecord().getArray10().getVFF1(1).set(0, 2, data2);
    msgIn1.getBody().getRecord().getArray10().getVFF1(2).set(0, 3, data3);
    msgIn1.getBody().getRecord().getArray10().getVFF1(3).set(0, 4, data4);
    msgIn1.getBody().getRecord().getArray10().getVFF1(4).set(0, 5, data5);
    msgIn1.getBody().getRecord().getArray10().getVFF1(5).set(0, 6, data6);
    msgIn1.getBody().getRecord().getArray10().getVFF1(6).set(0, 7, data7);
    msgIn1.getBody().getRecord().getArray10().getVFF1(7).set(0, 8, data8);
    msgIn1.getBody().getRecord().getArray10().getVFF1(8).set(0, 9, data9);
    msgIn1.getBody().getRecord().getArray10().getVFF1(9).set(0, 10, data10);

    // check get results against what we set...
    Assert.AreEqual(99 , msgIn1.getHeader().getHeaderRec().getMessageID());

    // check format was recorded for some examples
    Assert.AreEqual(0 , msgIn1.getBody().getRecord().getArray10().getVFF1(0).getFormat());
    Assert.AreEqual(0 , msgIn1.getBody().getRecord().getArray10().getVFF1(5).getFormat());
    Assert.AreEqual(0 , msgIn1.getBody().getRecord().getArray10().getVFF1(9).getFormat());

    assertArraysEqual(data1, msgIn1.getBody().getRecord().getArray10().getVFF1(0).getData(), 1);
    assertArraysEqual(data2, msgIn1.getBody().getRecord().getArray10().getVFF1(1).getData(), 2);
    assertArraysEqual(data3, msgIn1.getBody().getRecord().getArray10().getVFF1(2).getData(), 3);
    assertArraysEqual(data4, msgIn1.getBody().getRecord().getArray10().getVFF1(3).getData(), 4);
    assertArraysEqual(data5, msgIn1.getBody().getRecord().getArray10().getVFF1(4).getData(), 5);
    assertArraysEqual(data6, msgIn1.getBody().getRecord().getArray10().getVFF1(5).getData(), 6);
    assertArraysEqual(data7, msgIn1.getBody().getRecord().getArray10().getVFF1(6).getData(), 7);
    assertArraysEqual(data8, msgIn1.getBody().getRecord().getArray10().getVFF1(7).getData(), 8);
    assertArraysEqual(data9, msgIn1.getBody().getRecord().getArray10().getVFF1(8).getData(), 9);
    assertArraysEqual(data10, msgIn1.getBody().getRecord().getArray10().getVFF1(9).getData(), 10);

    // change format...
    msgIn1.getBody().getRecord().getArray10().getVFF1(0).set(1, 1, data1);
    msgIn1.getBody().getRecord().getArray10().getVFF1(1).set(1, 2, data2);
    msgIn1.getBody().getRecord().getArray10().getVFF1(2).set(1, 3, data3);
    msgIn1.getBody().getRecord().getArray10().getVFF1(3).set(1, 4, data4);
    msgIn1.getBody().getRecord().getArray10().getVFF1(4).set(1, 5, data5);
    msgIn1.getBody().getRecord().getArray10().getVFF1(5).set(1, 6, data6);
    msgIn1.getBody().getRecord().getArray10().getVFF1(6).set(1, 7, data7);
    msgIn1.getBody().getRecord().getArray10().getVFF1(7).set(1, 8, data8);
    msgIn1.getBody().getRecord().getArray10().getVFF1(8).set(1, 9, data9);
    msgIn1.getBody().getRecord().getArray10().getVFF1(9).set(1, 10, data10);

    // check new format was recorded
    Assert.AreEqual(1 , msgIn1.getBody().getRecord().getArray10().getVFF1(0).getFormat());
    Assert.AreEqual(1 , msgIn1.getBody().getRecord().getArray10().getVFF1(5).getFormat());
    Assert.AreEqual(1 , msgIn1.getBody().getRecord().getArray10().getVFF1(9).getFormat());

    // data arrays should be unchanged.
    assertArraysEqual(data1, msgIn1.getBody().getRecord().getArray10().getVFF1(0).getData(), 1);
    assertArraysEqual(data2, msgIn1.getBody().getRecord().getArray10().getVFF1(1).getData(), 2);
    assertArraysEqual(data3, msgIn1.getBody().getRecord().getArray10().getVFF1(2).getData(), 3);
    assertArraysEqual(data4, msgIn1.getBody().getRecord().getArray10().getVFF1(3).getData(), 4);
    assertArraysEqual(data5, msgIn1.getBody().getRecord().getArray10().getVFF1(4).getData(), 5);
    assertArraysEqual(data6, msgIn1.getBody().getRecord().getArray10().getVFF1(5).getData(), 6);
    assertArraysEqual(data7, msgIn1.getBody().getRecord().getArray10().getVFF1(6).getData(), 7);
    assertArraysEqual(data8, msgIn1.getBody().getRecord().getArray10().getVFF1(7).getData(), 8);
    assertArraysEqual(data9, msgIn1.getBody().getRecord().getArray10().getVFF1(8).getData(), 9);
    assertArraysEqual(data10, msgIn1.getBody().getRecord().getArray10().getVFF1(9).getData(), 10);

    // check getSize() returns accurate value.
    // 2-byte header, 10 2-byte count fields, 10 1-byte format fields, 55 total VFF data bytes.
    Assert.AreEqual(msgIn1.getSize() , 2 + 10*2 + 10*1 + 55);

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

        msgIn1.getHeader().getHeaderRec().setMessageID(999);

        byte[] data1 = new byte[1] { 10 };
        byte[] data2 = new byte[2] { 10, 11 };
        byte[] data3 = new byte[3] { 10, 11, 12 };
        byte[] data4 = new byte[4] { 10, 11, 12, 13 };
        byte[] data5 = new byte[5] { 10, 11, 12, 13, 14 };
        byte[] data6 = new byte[6] { 10, 11, 12, 13, 14, 15 };
        byte[] data7 = new byte[7] { 10, 11, 12, 13, 14, 15, 16 };
        byte[] data8 = new byte[8] { 10, 11, 12, 13, 14, 15, 16, 17 };
        byte[] data9 = new byte[9] { 10, 11, 12, 13, 14, 15, 16, 17, 18 };
        byte[] data10 = new byte[10] { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };

        msgIn1.getBody().getRecord().getArray10().getVFF1(0).set(1, 1, data1);
        msgIn1.getBody().getRecord().getArray10().getVFF1(1).set(1, 2, data2);
        msgIn1.getBody().getRecord().getArray10().getVFF1(2).set(1, 3, data3);
        msgIn1.getBody().getRecord().getArray10().getVFF1(3).set(1, 4, data4);
        msgIn1.getBody().getRecord().getArray10().getVFF1(4).set(1, 5, data5);
        msgIn1.getBody().getRecord().getArray10().getVFF1(5).set(1, 6, data6);
        msgIn1.getBody().getRecord().getArray10().getVFF1(6).set(1, 7, data7);
        msgIn1.getBody().getRecord().getArray10().getVFF1(7).set(1, 8, data8);
        msgIn1.getBody().getRecord().getArray10().getVFF1(8).set(1, 9, data9);
        msgIn1.getBody().getRecord().getArray10().getVFF1(9).set(1, 10, data10);

        msgIn1.encode(buff, 0);

        // offset 0:   ushort w/ message ID
        Assert.AreEqual(999, BitConverter.ToUInt16(buff, 0));

        // offset 2: byte w/ format field
        Assert.AreEqual(1, buff[2]);

        // offset 3:  ushort w/ count field = 1
        Assert.AreEqual(1, BitConverter.ToUInt16(buff, 3));

        // offset 5: single byte from 1st VFF
        Assert.AreEqual(10, buff[5]);

        // offset 6: byte w/ format field
        Assert.AreEqual(1, buff[6]);

        // offset 7:  ushort w/ count field = 2
        Assert.AreEqual(2, BitConverter.ToUInt16(buff, 7));

        // offset 9: byte from 2nd VFF
        Assert.AreEqual(10, buff[9]);

        // offset 10: byte from 2nd VFF
        Assert.AreEqual(11, buff[10]);

        // offset 11: byte w/ format field
        Assert.AreEqual(1, buff[11]);

        // offset 12:  ushort w/ count field = 3
        Assert.AreEqual(3, BitConverter.ToUInt16(buff, 12));

        // array of uchar starting at offset 14: 3rd VFF data
        assertArraySlicesEqual(buff, data3, 14, 3);

        // skip to the last VFF
        // offset 74: byte w/ format field
        Assert.AreEqual(1, buff[74]);

        // offset 75: ushort w/ count field = 10
        Assert.AreEqual(10, BitConverter.ToUInt16(buff, 75));

        // array of uchar starting at offset 77: 10th VFF data
        assertArraySlicesEqual(buff, data10, 77, 10);

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

        msgIn1.getHeader().getHeaderRec().setMessageID(50);

        byte[] data1 = new byte[1] { 10 };
        byte[] data2 = new byte[2] { 10, 11 };
        byte[] data3 = new byte[3] { 10, 11, 12 };
        byte[] data4 = new byte[4] { 10, 11, 12, 13 };
        byte[] data5 = new byte[5] { 10, 11, 12, 13, 14 };
        byte[] data6 = new byte[6] { 10, 11, 12, 13, 14, 15 };
        byte[] data7 = new byte[7] { 10, 11, 12, 13, 14, 15, 16 };
        byte[] data8 = new byte[8] { 10, 11, 12, 13, 14, 15, 16, 17 };
        byte[] data9 = new byte[9] { 10, 11, 12, 13, 14, 15, 16, 17, 18 };
        byte[] data10 = new byte[10] { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };

        byte[] data1_b = new byte[1] { 20 };
        byte[] data2_b = new byte[2] { 20, 11 };
        byte[] data3_b = new byte[3] { 20, 11, 12 };
        byte[] data4_b = new byte[4] { 20, 11, 12, 13 };
        byte[] data5_b = new byte[5] { 20, 11, 12, 13, 14 };
        byte[] data6_b = new byte[6] { 20, 11, 12, 13, 14, 15 };
        byte[] data7_b = new byte[7] { 20, 11, 12, 13, 14, 15, 16 };
        byte[] data8_b = new byte[8] { 20, 11, 12, 13, 14, 15, 16, 17 };
        byte[] data9_b = new byte[9] { 20, 11, 12, 13, 14, 15, 16, 17, 18 };
        byte[] data10_b = new byte[10] { 20, 11, 12, 13, 14, 15, 16, 17, 18, 19 };

        byte[] data1_c = new byte[1] { 30 };
        byte[] data2_c = new byte[2] { 30, 11 };
        byte[] data3_c = new byte[3] { 30, 11, 12 };
        byte[] data4_c = new byte[4] { 30, 11, 12, 13 };
        byte[] data5_c = new byte[5] { 30, 11, 12, 13, 14 };
        byte[] data6_c = new byte[6] { 30, 11, 12, 13, 14, 15 };
        byte[] data7_c = new byte[7] { 30, 11, 12, 13, 14, 15, 16 };
        byte[] data8_c = new byte[8] { 30, 11, 12, 13, 14, 15, 16, 17 };
        byte[] data9_c = new byte[9] { 30, 11, 12, 13, 14, 15, 16, 17, 18 };
        byte[] data10_c = new byte[10] { 30, 11, 12, 13, 14, 15, 16, 17, 18, 19 };

        msgIn1.getBody().getRecord().getArray10().getVFF1(0).set(0, 1, data1);
        msgIn1.getBody().getRecord().getArray10().getVFF1(1).set(0, 2, data2);
        msgIn1.getBody().getRecord().getArray10().getVFF1(2).set(0, 3, data3);
        msgIn1.getBody().getRecord().getArray10().getVFF1(3).set(0, 4, data4);
        msgIn1.getBody().getRecord().getArray10().getVFF1(4).set(0, 5, data5);
        msgIn1.getBody().getRecord().getArray10().getVFF1(5).set(0, 6, data6);
        msgIn1.getBody().getRecord().getArray10().getVFF1(6).set(0, 7, data7);
        msgIn1.getBody().getRecord().getArray10().getVFF1(7).set(0, 8, data8);
        msgIn1.getBody().getRecord().getArray10().getVFF1(8).set(0, 9, data9);
        msgIn1.getBody().getRecord().getArray10().getVFF1(9).set(0, 10, data10);

        msgIn2.getHeader().getHeaderRec().setMessageID(100);

        msgIn2.getBody().getRecord().getArray10().getVFF1(0).set(1, 1, data1_b);
        msgIn2.getBody().getRecord().getArray10().getVFF1(1).set(1, 2, data2_b);
        msgIn2.getBody().getRecord().getArray10().getVFF1(2).set(1, 3, data3_b);
        msgIn2.getBody().getRecord().getArray10().getVFF1(3).set(1, 4, data4_b);
        msgIn2.getBody().getRecord().getArray10().getVFF1(4).set(1, 5, data5_b);
        msgIn2.getBody().getRecord().getArray10().getVFF1(5).set(1, 6, data6_b);
        msgIn2.getBody().getRecord().getArray10().getVFF1(6).set(1, 7, data7_b);
        msgIn2.getBody().getRecord().getArray10().getVFF1(7).set(1, 8, data8_b);
        msgIn2.getBody().getRecord().getArray10().getVFF1(8).set(1, 9, data9_b);
        msgIn2.getBody().getRecord().getArray10().getVFF1(9).set(1, 10, data10_b);

        // decode contents of msg1 into msg2.  fields of msg2 should now match fields of msg1.
        msgIn1.encode(buff, 0);
        msgIn2.decode(buff, 0);

        Assert.AreEqual(msgIn1.getHeader().getHeaderRec().getMessageID(), msgIn2.getHeader().getHeaderRec().getMessageID());

        assertArraysEqual(msgIn1.getBody().getRecord().getArray10().getVFF1(0).getData(),
            msgIn2.getBody().getRecord().getArray10().getVFF1(0).getData(), 1);
        assertArraysEqual(msgIn1.getBody().getRecord().getArray10().getVFF1(1).getData(),
            msgIn2.getBody().getRecord().getArray10().getVFF1(1).getData(), 2);
        assertArraysEqual(msgIn1.getBody().getRecord().getArray10().getVFF1(2).getData(),
            msgIn2.getBody().getRecord().getArray10().getVFF1(2).getData(), 3);
        assertArraysEqual(msgIn1.getBody().getRecord().getArray10().getVFF1(3).getData(),
            msgIn2.getBody().getRecord().getArray10().getVFF1(3).getData(), 4);
        assertArraysEqual(msgIn1.getBody().getRecord().getArray10().getVFF1(4).getData(),
            msgIn2.getBody().getRecord().getArray10().getVFF1(4).getData(), 5);
        assertArraysEqual(msgIn1.getBody().getRecord().getArray10().getVFF1(5).getData(),
            msgIn2.getBody().getRecord().getArray10().getVFF1(5).getData(), 6);
        assertArraysEqual(msgIn1.getBody().getRecord().getArray10().getVFF1(6).getData(),
            msgIn2.getBody().getRecord().getArray10().getVFF1(6).getData(), 7);
        assertArraysEqual(msgIn1.getBody().getRecord().getArray10().getVFF1(7).getData(),
            msgIn2.getBody().getRecord().getArray10().getVFF1(7).getData(), 8);
        assertArraysEqual(msgIn1.getBody().getRecord().getArray10().getVFF1(8).getData(),
            msgIn2.getBody().getRecord().getArray10().getVFF1(8).getData(), 9);
        assertArraysEqual(msgIn1.getBody().getRecord().getArray10().getVFF1(9).getData(),
            msgIn2.getBody().getRecord().getArray10().getVFF1(9).getData(), 10);

        // check the format fields also

        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(0).getFormat());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(1).getFormat());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(2).getFormat());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(3).getFormat());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(4).getFormat());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(5).getFormat());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(6).getFormat());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(7).getFormat());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(8).getFormat());
        Assert.AreEqual(0, msgIn2.getBody().getRecord().getArray10().getVFF1(9).getFormat());

        // change msg1's message ID..
        msgIn1.getHeader().getHeaderRec().setMessageID(9000);

        // change contents of msg2's array, encode the array *alone* into the buffer, then decode
        // the buffer into msg1's array.
        msgIn2.getHeader().getHeaderRec().setMessageID(18000);
        msgIn2.getBody().getRecord().getArray10().getVFF1(0).set(1, 1, data1_c);
        msgIn2.getBody().getRecord().getArray10().getVFF1(1).set(1, 2, data2_c);
        msgIn2.getBody().getRecord().getArray10().getVFF1(2).set(1, 3, data3_c);
        msgIn2.getBody().getRecord().getArray10().getVFF1(3).set(1, 4, data4_c);
        msgIn2.getBody().getRecord().getArray10().getVFF1(4).set(1, 5, data5_c);
        msgIn2.getBody().getRecord().getArray10().getVFF1(5).set(1, 6, data6_c);
        msgIn2.getBody().getRecord().getArray10().getVFF1(6).set(1, 7, data7_c);
        msgIn2.getBody().getRecord().getArray10().getVFF1(7).set(1, 8, data8_c);
        msgIn2.getBody().getRecord().getArray10().getVFF1(8).set(1, 9, data9_c);
        msgIn2.getBody().getRecord().getArray10().getVFF1(9).set(1, 10, data10_c);

        msgIn2.getBody().getRecord().getArray10().encode(buff, 0);
        msgIn1.getBody().getRecord().getArray10().decode(buff, 0);

        // since the encode/decode only touched the array, msg1's message ID should still be 9000!
        Assert.AreEqual(9000, msgIn1.getHeader().getHeaderRec().getMessageID());

        // contents of msg1's array should be equal to the _c arrays now...
        assertArraysEqual(data1_c, msgIn1.getBody().getRecord().getArray10().getVFF1(0).getData(), 1);
        assertArraysEqual(data2_c, msgIn1.getBody().getRecord().getArray10().getVFF1(1).getData(), 2);
        assertArraysEqual(data3_c, msgIn1.getBody().getRecord().getArray10().getVFF1(2).getData(), 3);
        assertArraysEqual(data4_c, msgIn1.getBody().getRecord().getArray10().getVFF1(3).getData(), 4);
        assertArraysEqual(data5_c, msgIn1.getBody().getRecord().getArray10().getVFF1(4).getData(), 5);
        assertArraysEqual(data6_c, msgIn1.getBody().getRecord().getArray10().getVFF1(5).getData(), 6);
        assertArraysEqual(data7_c, msgIn1.getBody().getRecord().getArray10().getVFF1(6).getData(), 7);
        assertArraysEqual(data8_c, msgIn1.getBody().getRecord().getArray10().getVFF1(7).getData(), 8);
        assertArraysEqual(data9_c, msgIn1.getBody().getRecord().getArray10().getVFF1(8).getData(), 9);
        assertArraysEqual(data10_c, msgIn1.getBody().getRecord().getArray10().getVFF1(9).getData(), 10);

        // format fields should now be 1.
        Assert.AreEqual(1, msgIn1.getBody().getRecord().getArray10().getVFF1(0).getFormat());
        Assert.AreEqual(1, msgIn1.getBody().getRecord().getArray10().getVFF1(1).getFormat());
        Assert.AreEqual(1, msgIn1.getBody().getRecord().getArray10().getVFF1(2).getFormat());
        Assert.AreEqual(1, msgIn1.getBody().getRecord().getArray10().getVFF1(3).getFormat());
        Assert.AreEqual(1, msgIn1.getBody().getRecord().getArray10().getVFF1(4).getFormat());
        Assert.AreEqual(1, msgIn1.getBody().getRecord().getArray10().getVFF1(5).getFormat());
        Assert.AreEqual(1, msgIn1.getBody().getRecord().getArray10().getVFF1(6).getFormat());
        Assert.AreEqual(1, msgIn1.getBody().getRecord().getArray10().getVFF1(7).getFormat());
        Assert.AreEqual(1, msgIn1.getBody().getRecord().getArray10().getVFF1(8).getFormat());
        Assert.AreEqual(1, msgIn1.getBody().getRecord().getArray10().getVFF1(9).getFormat());

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

