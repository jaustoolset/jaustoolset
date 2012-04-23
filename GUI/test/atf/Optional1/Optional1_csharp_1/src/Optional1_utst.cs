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
using urn_org_jts_test_Optional1_1_0;

[TestFixture]
public class Optional1_utst
{
    // assert if the contents of two byte buffers are equal, given first offset into buff one and length following
    // that offset.  comparison is against contents of buff2 from offset zero to offset length-1.
    public void assertBufferSliceEqual(byte[] data1, byte[] data2, int first, int length)
    {
        for (int i = 0; i < length; ++i)
        {
            Assert.AreEqual(data2[i], data1[first + i]);
        }
    }

    public void assertBuffersEqual(byte[] data1, byte[] data2, int length)
    {
        for (int i = 0; i < length; ++i)
        {
            Assert.AreEqual(data2[i], data1[i]);
        }
    }

    public Optional1_utst()
    {
    }

    [SetUp]
    public void setUp()
    {
    }

    void tearDown()
    {
    }


    [Test]
    public void testSetGetOperations()
    {
        byte[] data = new byte[10] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Console.WriteLine("[executing test (testSetGetOperations)(TP_3.3.5.1)]");

        // Test initial state
        MsgIn msgIn1 = new MsgIn();
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 0);
        Assert.AreEqual(msgIn1.getBody().getSequence().getRecord1().getPresenceVector(), 0);

        // Set the first entry in the first optional record, then ensure all the PVs got set correctly
        msgIn1.getBody().getSequence().getRecord1().setField1(1);
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 1);
        Assert.AreEqual(msgIn1.getBody().getSequence().getRecord1().getPresenceVector(), 1);

        // Set the first entry in the first optional record, then ensure all the PVs got set correctly
        msgIn1.getBody().getSequence().getRecord2().getVariableLengthField1().set(10, data);
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 3);
        Assert.AreEqual(msgIn1.getBody().getSequence().getRecord2().getPresenceVector(), 2);

        // Set the first entry in the first optional record, then ensure all the PVs got set correctly
        for (int i = 0; i < 10; i++)
            msgIn1.getBody().getSequence().getRecord3().getArray1().setElement1(i, (sbyte)data[i]);
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 7);
        Assert.AreEqual(msgIn1.getBody().getSequence().getRecord3().getPresenceVector(), 4);

        // Set the first entry in the first optional record, then ensure all the PVs got set correctly
        msgIn1.getBody().getSequence().getRecord4().getBitField1().setSubfield1(1);
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 15);
        Assert.AreEqual(msgIn1.getBody().getSequence().getRecord4().getPresenceVector(), 8);

        // Set the first entry in the first optional record, then ensure all the PVs got set correctly
        msgIn1.getBody().getSequence().getRecord5().setFixedLengthString1("Hello World!");
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 31);
        Assert.AreEqual(msgIn1.getBody().getSequence().getRecord5().getPresenceVector(), 16);

        // Set the first entry in the first optional record, then ensure all the PVs got set correctly
        msgIn1.getBody().getSequence().getRecord6().getVariableField1().setIndex(0);
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 63);
        Assert.AreEqual(msgIn1.getBody().getSequence().getRecord6().getPresenceVector(), 32);

        // Set the first entry in the first optional record, then ensure all the PVs got set correctly
        msgIn1.getBody().getSequence().getRecord7().setVariableLengthString1("I'm Gumby, Dammit!");
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 127);
        Assert.AreEqual(msgIn1.getBody().getSequence().getRecord7().getPresenceVector(), 64);

        // Set the first entry in the first optional record, then ensure all the PVs got set correctly
        msgIn1.getBody().getSequence().getRecord8().getVariableFormatField1().set(0, 10, data);
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 255);
        Assert.AreEqual(msgIn1.getBody().getSequence().getRecord8().getPresenceVector(), 128);

        // Add an element to the optional list
        MsgIn.Body.Sequence.List.Record rec1 = new MsgIn.Body.Sequence.List.Record();
        msgIn1.getBody().getSequence().getList().addElement(rec1);
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 511);
        Assert.AreEqual(msgIn1.getBody().getSequence().getList().getElement(0).getPresenceVector(), 0);

        // Nested sequences...
        msgIn1.getBody().getSequence().getSubsequence().getRecord().getVariableField1().setIndex(0);
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 1023);
        Assert.AreEqual(msgIn1.getBody().getSequence().getSubsequence().getPresenceVector(), 1);
        Assert.AreEqual(msgIn1.getBody().getSequence().getSubsequence().getRecord().getPresenceVector(), 32);

        // Variants
        msgIn1.getBody().getSequence().getVariant().setFieldValue(1);
        Assert.AreEqual(msgIn1.getBody().getSequence().getPresenceVector(), 2047);
        Assert.AreEqual(msgIn1.getBody().getSequence().getVariant().getRecord().getPresenceVector(), 0);
    }

    [Test]
    public void testEncodeToSpec()
    {
        Console.Out.WriteLine("");
        Console.Out.Write("[executing test (testEncodeToSpec)]");

        // buffer for encoding
        byte[] buff = new byte[10000];

        // *** first test message: activate only Record1, activate all fields inside of record1.
        byte[] data1 = new byte[10];
        byte[] data2 = new byte[5];

        for (int i = 0; i < 10; ++i)
        {
            data1[i] = (byte)(10 + i);
        }
        for (int i = 0; i < 5; ++i)
        {
            data2[i] = (byte)(20 + i);
        }

        MsgIn msg1 = new MsgIn();

        msg1.getHeader().getHeaderRec().setMessageID(999);

        msg1.getBody().getSequence().getRecord1().setField1((sbyte)77);
        msg1.getBody().getSequence().getRecord1().getVariableLengthField1().set(10, data1);
        msg1.getBody().getSequence().getRecord1().getArray1().setElement1(1, (sbyte)10);
        msg1.getBody().getSequence().getRecord1().getArray1().setElement1(3, (sbyte)20);
        msg1.getBody().getSequence().getRecord1().getArray1().setElement1(5, (sbyte)30);
        msg1.getBody().getSequence().getRecord1().getArray1().setElement1(7, (sbyte)40);
        msg1.getBody().getSequence().getRecord1().getArray1().setElement1(9, (sbyte)50);
        msg1.getBody().getSequence().getRecord1().getBitField1().setSubfield1((byte)1);
        msg1.getBody().getSequence().getRecord1().setFixedLengthString1("ABCDE");
        msg1.getBody().getSequence().getRecord1().getVariableField1().setMeterAsUnsignedByteAt1((byte)33);
        msg1.getBody().getSequence().getRecord1().setVariableLengthString1("hello world");
        msg1.getBody().getSequence().getRecord1().getVariableFormatField1().set((byte)0, 5, data2);

        msg1.encode(buff, 0);

        // offset 0:    ushort w/ message ID
        Assert.AreEqual(999, BitConverter.ToUInt16(buff, 0));

        // offset 2:    ushort w/ Sequence's presence vector - should be 1 indicating only first
        // record is present
        Assert.AreEqual(1, BitConverter.ToUInt16(buff, 2));

        // offset 4: unsigned byte w/ Record1's presence vector, 8 bits all on, 255
        Assert.AreEqual(255, buff[4]);

        // offset 5:    signed byte, Field1 fixed field
        Assert.AreEqual(77, buff[5]);

        // offset 6:    uint, variableLengthField1 count field...
        Assert.AreEqual(10, buff[6]);

        // offset 10-19: 10-byte variableLengthField1.
        assertBufferSliceEqual(buff, data1, 10, 10);

        // offset 20: Array1[0]
        Assert.AreEqual(0, buff[20]);

        // offset 21: Array1[1]
        Assert.AreEqual(10, buff[21]);

        // offset 22: Array1[2]
        Assert.AreEqual(0, buff[22]);

        // offset 23: Array1[3]
        Assert.AreEqual(20, buff[23]);

        // offset 25: Array1[5]
        Assert.AreEqual(30, buff[25]);

        // offset 27: Array1[7]
        Assert.AreEqual(40, buff[27]);

        // offset 29: Array1[9]
        Assert.AreEqual(50, buff[29]);

        // offset 30: BitField1, single unsigned byte
        Assert.AreEqual(1, buff[30]);

        // offset 31: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(65, buff[31]);

        // offset 32: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(66, buff[32]);

        // offset 33: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(67, buff[33]);

        // offset 34: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(68, buff[34]);

        // offset 35: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(69, buff[35]);

        // offset 36: zeroed part of fixed length string
        Assert.AreEqual(0, buff[36]);

        // offset 45: last zeroed byte of fixed length string
        Assert.AreEqual(0, buff[45]);

        // offset 46: type/units enum of VariableField1, single byte
        Assert.AreEqual(1, buff[46]);

        // offset 47: contents of VariableField1
        Assert.AreEqual(33, buff[47]);

        // offset 48: 2-byte unsigned short, count field for VariableLengthString1
        Assert.AreEqual(11, BitConverter.ToUInt16(buff, 48));

        // offset 50-60: 11 characters of VariableLengthString1 "hello world"
        Assert.AreEqual(104, buff[50]);
        Assert.AreEqual(101, buff[51]);
        Assert.AreEqual(108, buff[52]);
        Assert.AreEqual(108, buff[53]);
        Assert.AreEqual(111, buff[54]);
        Assert.AreEqual(32, buff[55]);
        Assert.AreEqual(119, buff[56]);
        Assert.AreEqual(111, buff[57]);
        Assert.AreEqual(114, buff[58]);
        Assert.AreEqual(108, buff[59]);
        Assert.AreEqual(100, buff[60]);

        // offset 61: format field of VariableFormatField1, unsigned byte
        Assert.AreEqual(0, buff[61]);

        // offset 62: count field of VariableFormatField1, unsigned int
        Assert.AreEqual(5, BitConverter.ToUInt16(buff, 62));

        // offset 66-70: 5 byte data of VariableFormatField1
        assertBufferSliceEqual(buff, data2, 66, 5);

        buff = new byte[10000];

        // *** 2nd test message, activate Record 2, Record 4, Record 6, Record 8, activate different
        // fields within each record.
        MsgIn msg2 = new MsgIn();

        msg2.getHeader().getHeaderRec().setMessageID(999);

        msg2.getBody().getSequence().getRecord2().setField1((sbyte)77);
        msg2.getBody().getSequence().getRecord2().getVariableLengthField1().set(10, data1);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(1, (sbyte)10);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(3, (sbyte)20);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(5, (sbyte)30);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(7, (sbyte)40);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(9, (sbyte)50);
        msg2.getBody().getSequence().getRecord4().getBitField1().setSubfield1((byte)1);
        msg2.getBody().getSequence().getRecord6().setFixedLengthString1("ABCDE");
        msg2.getBody().getSequence().getRecord6().getVariableField1().setMeterAsUnsignedByteAt1((byte)33);
        msg2.getBody().getSequence().getRecord8().setVariableLengthString1("hello world");
        msg2.getBody().getSequence().getRecord8().getVariableFormatField1().set((byte)0, 5, data2);

        msg2.encode(buff, 0);

        // offset 0:   ushort w/ msg ID
        Assert.AreEqual(999, BitConverter.ToUInt16(buff, 0));

        // offset 2:   ushort w/ Sequence pv
        Assert.AreEqual(170, BitConverter.ToUInt16(buff, 2));

        // offset 4:   ubyte w/ Record2 pv
        Assert.AreEqual(3, buff[4]);

        // offset 5:   byte w/ Record2.Field1
        Assert.AreEqual(77, buff[5]);

        // offset 6:   uint w/ Record2.VariableLengthField1 count field
        Assert.AreEqual(10, buff[6]);

        // offset 10-19: ubyte data of Record2.VariableLengthField1
        assertBufferSliceEqual(buff, data1, 10, 10);

        // offset 20:   ubyte w/ Record4 pv
        Assert.AreEqual(12, buff[20]);

        // offset 21:   byte, Record4.Array1[0]
        Assert.AreEqual(0, buff[21]);

        // offset 22:   byte, Record4.Array1[1]
        Assert.AreEqual(10, buff[22]);

        // offset 23:   byte, Record4.Array1[2]
        Assert.AreEqual(0, buff[23]);

        // offset 29:   byte, Record4.Array1[8]
        Assert.AreEqual(0, buff[29]);

        // offset 30:   byte, Record4.Array1[9]
        Assert.AreEqual(50, buff[30]);

        // offset 31:   byte, Record4.BitField1
        Assert.AreEqual(1, buff[31]);

        // offset 32:   ubyte w/ Record6 pv
        Assert.AreEqual(48, buff[32]);

        // offset 33: 15-byte Record6.FixedLengthString1.  first 5 chars: "ABCDE"
        Assert.AreEqual(65, buff[33]);

        // offset 34: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(66, buff[34]);

        // offset 35: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(67, buff[35]);

        // offset 36: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(68, buff[36]);

        // offset 37: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(69, buff[37]);

        // offset 38: zeroed part of fixed length string
        Assert.AreEqual(0, buff[38]);

        // offset 47: last zeroed byte of fixed length string
        Assert.AreEqual(0, buff[47]);

        // offset 48: type/units enum of VariableField1, single byte
        Assert.AreEqual(1, buff[48]);

        // offset 49: byte contents of VariableField1
        Assert.AreEqual(33, buff[49]);

        // offset 50:   ubyte w/ Record8 pv
        Assert.AreEqual(192, buff[50]);

        // offset 51: 2-byte unsigned short, count field for Record8.VariableLengthString1
        Assert.AreEqual(11, BitConverter.ToUInt16(buff, 51));

        // offset 53-63: 11 characters of Record8.VariableLengthString1 "hello world"
        Assert.AreEqual(104, buff[53]);
        Assert.AreEqual(101, buff[54]);
        Assert.AreEqual(108, buff[55]);
        Assert.AreEqual(108, buff[56]);
        Assert.AreEqual(111, buff[57]);
        Assert.AreEqual(32, buff[58]);
        Assert.AreEqual(119, buff[59]);
        Assert.AreEqual(111, buff[60]);
        Assert.AreEqual(114, buff[61]);
        Assert.AreEqual(108, buff[62]);
        Assert.AreEqual(100, buff[63]);

        // offset 64: format field of Record8.VariableFormatField1, unsigned byte
        Assert.AreEqual(0, buff[64]);

        // offset 65: count field of Record8.VariableFormatField1, unsigned int
        Assert.AreEqual(5, BitConverter.ToUInt16(buff, 65));

        // offset 69-73: 5 byte data of Record8.VariableFormatField1
        assertBufferSliceEqual(buff, data2, 69, 5);

        buff = new byte[10000];

        // 3rd msg: activate record 3, List, and Variant, and activate the record
        // inside List and Variant, activate all fields inside each record.
        MsgIn msg3 = new MsgIn();

        msg3.getHeader().getHeaderRec().setMessageID(999);

        MsgIn.Body.Sequence.Record3 rec3 = msg3.getBody().getSequence().getRecord3();

        rec3.setField1((sbyte)77);
        rec3.getVariableLengthField1().set(10, data1);
        rec3.getArray1().setElement1(1, (sbyte)10);
        rec3.getArray1().setElement1(3, (sbyte)20);
        rec3.getArray1().setElement1(5, (sbyte)30);
        rec3.getArray1().setElement1(7, (sbyte)40);
        rec3.getArray1().setElement1(9, (sbyte)50);
        rec3.getBitField1().setSubfield1((byte)1);
        rec3.setFixedLengthString1("ABCDE");
        rec3.getVariableField1().setMeterAsUnsignedByteAt1((byte)33);
        rec3.setVariableLengthString1("hello world");
        rec3.getVariableFormatField1().set((byte)0, 5, data2);

	    MsgIn.Body.Sequence.List.Record listrec = new MsgIn.Body.Sequence.List.Record();
	    msg3.getBody().getSequence().getList().addElement( listrec );

        listrec.setField1((sbyte)77);
        listrec.getVariableLengthField1().set(10, data1);
        listrec.getArray1().setElement1(1, (sbyte)60);
        listrec.getArray1().setElement1(3, (sbyte)70);
        listrec.getArray1().setElement1(5, (sbyte)80);
        listrec.getArray1().setElement1(7, (sbyte)90);
        listrec.getArray1().setElement1(9, (sbyte)100);
        listrec.getBitField1().setSubfield1((byte)1);
        listrec.setFixedLengthString1("FGHIJK");
        listrec.getVariableField1().setMeterAsUnsignedByteAt1((byte)44);
        listrec.setVariableLengthString1("aabbccddeeff");
        listrec.getVariableFormatField1().set((byte)0, 5, data2);

        // Variants
        msg3.getBody().getSequence().getVariant().setFieldValue((byte)1); // option 1: Record
        MsgIn.Body.Sequence.Variant.Record varrec = msg3.getBody().getSequence().getVariant().getRecord();

        varrec.setField1((sbyte)99);
        varrec.getVariableLengthField1().set(10, data1);
        varrec.getArray1().setElement1(1, (sbyte)65);
        varrec.getArray1().setElement1(3, (sbyte)75);
        varrec.getArray1().setElement1(5, (sbyte)85);
        varrec.getArray1().setElement1(7, (sbyte)95);
        varrec.getArray1().setElement1(9, (sbyte)105);
        varrec.getBitField1().setSubfield1((byte)1);
        varrec.setFixedLengthString1("LMNOPQR");
        varrec.getVariableField1().setMeterAsUnsignedByteAt1((byte)55);
        varrec.setVariableLengthString1("hello world");
        varrec.getVariableFormatField1().set((byte)0, 5, data2);

        msg3.encode(buff, 0);

        // offset 0:    ushort w/ message ID
        Assert.AreEqual(999, BitConverter.ToUInt16(buff, 0));

        // offset 2:    ushort w/ Sequence's presence vector - 1284 indicates record 3, list, variant present
        Assert.AreEqual(1284, BitConverter.ToUInt16(buff, 2));

        // offset 4: unsigned byte w/ Record3's presence vector, 8 bits all on, 255
        Assert.AreEqual(255, buff[4]);

        // offset 5:    signed byte, Record3.Field1 fixed field
        Assert.AreEqual(77, buff[5]);

        // offset 6:    uint, Record3.variableLengthField1 count field...
        Assert.AreEqual(10, buff[6]);

        // offset 10-19: 10-byte Record3.variableLengthField1.
        assertBufferSliceEqual(buff, data1, 10, 10);

        // offset 20: Record3.Array1[0]
        Assert.AreEqual(0, buff[20]);

        // offset 21: Record3.Array1[1]
        Assert.AreEqual(10, buff[21]);

        // offset 22: Record3.Array1[2]
        Assert.AreEqual(0, buff[22]);

        // offset 23: Record3.Array1[3]
        Assert.AreEqual(20, buff[23]);

        // offset 25: Record3.Array1[5]
        Assert.AreEqual(30, buff[25]);

        // offset 27: Record3.Array1[7]
        Assert.AreEqual(40, buff[27]);

        // offset 29: Record3.Array1[9]
        Assert.AreEqual(50, buff[29]);

        // offset 30: Record3.BitField1, single unsigned byte
        Assert.AreEqual(1, buff[30]);

        // offset 31: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(65, buff[31]);

        // offset 32: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(66, buff[32]);

        // offset 33: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(67, buff[33]);

        // offset 34: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(68, buff[34]);

        // offset 35: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(69, buff[35]);

        // offset 36: zeroed part of fixed length string
        Assert.AreEqual(0, buff[36]);

        // offset 45: last zeroed byte of fixed length string
        Assert.AreEqual(0, buff[45]);

        // offset 46: type/units enum of Record3.VariableField1, single byte
        Assert.AreEqual(1, buff[46]);

        // offset 47: contents of Record3.VariableField1
        Assert.AreEqual(33, buff[47]);

        // offset 48: 2-byte unsigned short, count field for Record3.VariableLengthString1
        Assert.AreEqual(11, BitConverter.ToUInt16(buff, 48));

        // offset 50-60: 11 characters of VariableLengthString1 "hello world"
        Assert.AreEqual(104, buff[50]);
        Assert.AreEqual(101, buff[51]);
        Assert.AreEqual(108, buff[52]);
        Assert.AreEqual(108, buff[53]);
        Assert.AreEqual(111, buff[54]);
        Assert.AreEqual(32, buff[55]);
        Assert.AreEqual(119, buff[56]);
        Assert.AreEqual(111, buff[57]);
        Assert.AreEqual(114, buff[58]);
        Assert.AreEqual(108, buff[59]);
        Assert.AreEqual(100, buff[60]);

        // offset 61: format field of VariableFormatField1, unsigned byte
        Assert.AreEqual(0, buff[61]);

        // offset 62: count field of VariableFormatField1, unsigned int
        Assert.AreEqual(5, BitConverter.ToUInt16(buff, 62));

        // offset 66-70: 5 byte data of VariableFormatField1
        assertBufferSliceEqual(buff, data2, 66, 5);

        // *** list and record inside list ***

        // offset 71: ubyte count field for list == 1
        Assert.AreEqual(1, buff[71]);

        // offset 72: ubyte presence vector for List.Record
        Assert.AreEqual(255, buff[72]);

        // offset 73:    signed byte, Record3.Field1 fixed field
        Assert.AreEqual(77, buff[73]);

        // offset 74:    uint, Record3.variableLengthField1 count field...
        Assert.AreEqual(10, buff[74]);

        // offset 78-87: 10-byte Record3.variableLengthField1.
        assertBufferSliceEqual(buff, data1, 78, 10);

        // offset 88: Record3.Array1[0]
        Assert.AreEqual(0, buff[88]);

        // offset 89: Record3.Array1[1]
        Assert.AreEqual(60, buff[89]);

        // offset 90: Record3.Array1[2]
        Assert.AreEqual(0, buff[90]);

        // offset 91: Record3.Array1[3]
        Assert.AreEqual(70, buff[91]);

        // offset 93: Record3.Array1[5]
        Assert.AreEqual(80, buff[93]);

        // offset 95: Record3.Array1[7]
        Assert.AreEqual(90, buff[95]);

        // offset 97: Record3.Array1[9]
        Assert.AreEqual(100, buff[97]);

        // offset 98: Record3.BitField1, single unsigned byte
        Assert.AreEqual(1, buff[98]);

        // offset 99: 15-byte fixed length string.  first 6 chars: "FGHIJK"
        Assert.AreEqual(70, buff[99]);

        // offset 100: 15-byte fixed length string.
        Assert.AreEqual(71, buff[100]);

        // offset 101: 15-byte fixed length string.
        Assert.AreEqual(72, buff[101]);

        // offset 102: 15-byte fixed length string.
        Assert.AreEqual(73, buff[102]);

        // offset 103: 15-byte fixed length string.
        Assert.AreEqual(74, buff[103]);

        // offset 104: 15-byte fixed length string.
        Assert.AreEqual(75, buff[104]);

        // offset 105: zeroed part of fixed length string
        Assert.AreEqual(0, buff[105]);

        // offset 113: last zeroed byte of fixed length string
        Assert.AreEqual(0, buff[113]);

        // offset 114: type/units enum of Record3.VariableField1, single byte
        Assert.AreEqual(1, buff[114]);

        // offset 115: contents of Record3.VariableField1
        Assert.AreEqual(44, buff[115]);

        // offset 116: 2-byte unsigned short, count field for Record3.VariableLengthString1
        Assert.AreEqual(12, BitConverter.ToUInt16(buff, 116));

        // offset 118-129: 12 characters of VariableLengthString1 "aabbccddeeff"
        Assert.AreEqual(97, buff[118]);
        Assert.AreEqual(97, buff[119]);
        Assert.AreEqual(98, buff[120]);
        Assert.AreEqual(98, buff[121]);
        Assert.AreEqual(99, buff[122]);
        Assert.AreEqual(99, buff[123]);
        Assert.AreEqual(100, buff[124]);
        Assert.AreEqual(100, buff[125]);
        Assert.AreEqual(101, buff[126]);
        Assert.AreEqual(101, buff[127]);
        Assert.AreEqual(102, buff[128]);
        Assert.AreEqual(102, buff[129]);

        // offset 130: format field of VariableFormatField1, unsigned byte
        Assert.AreEqual(0, buff[130]);

        // offset 131: count field of VariableFormatField1, unsigned int
        Assert.AreEqual(5, BitConverter.ToUInt16(buff, 131));

        // offset 135-139: 5 byte data of VariableFormatField1
        assertBufferSliceEqual(buff, data2, 135, 5);

        // *** variant and record inside variant ***

        // offset 140: ubyte vtag field for variant
        Assert.AreEqual(1, buff[140]);

        // offset 141: ubyte presence vector for Variant.Record.
        Assert.AreEqual(255, buff[141]);

        // offset 142:    signed byte, Record3.Field1 fixed field
        Assert.AreEqual(99, buff[142]);

        // offset 143:    uint, Record3.variableLengthField1 count field...
        Assert.AreEqual(10, buff[143]);

        // offset 147-156: 10-byte Record3.variableLengthField1.
        assertBufferSliceEqual(buff, data1, 147, 10);

        // offset 157: Record3.Array1[0]
        Assert.AreEqual(0, buff[157]);

        // offset 158: Record3.Array1[1]
        Assert.AreEqual(65, buff[158]);

        // offset 159: Record3.Array1[2]
        Assert.AreEqual(0, buff[159]);

        // offset 160: Record3.Array1[3]
        Assert.AreEqual(75, buff[160]);

        // offset 162: Record3.Array1[5]
        Assert.AreEqual(85, buff[162]);

        // offset 164: Record3.Array1[7]
        Assert.AreEqual(95, buff[164]);

        // offset 166: Record3.Array1[9]
        Assert.AreEqual(105, buff[166]);

        // offset 167: Record3.BitField1, single unsigned byte
        Assert.AreEqual(1, buff[167]);

        // offset 168: 15-byte fixed length string.  first 7 chars: "LMNOPQR"
        Assert.AreEqual(76, buff[168]);

        // offset 169: 15-byte fixed length string.
        Assert.AreEqual(77, buff[169]);

        // offset 170: 15-byte fixed length string.
        Assert.AreEqual(78, buff[170]);

        // offset 171: 15-byte fixed length string.
        Assert.AreEqual(79, buff[171]);

        // offset 172: 15-byte fixed length string.
        Assert.AreEqual(80, buff[172]);

        // offset 173: 15-byte fixed length string.
        Assert.AreEqual(81, buff[173]);

        // offset 174: 15-byte fixed length string.
        Assert.AreEqual(82, buff[174]);

        // offset 175: zeroed part of fixed length string
        Assert.AreEqual(0, buff[175]);

        // offset 182: last zeroed byte of fixed length string
        Assert.AreEqual(0, buff[182]);

        // offset 183: type/units enum of Record3.VariableField1, single byte
        Assert.AreEqual(1, buff[183]);

        // offset 184: contents of Record3.VariableField1
        Assert.AreEqual(55, buff[184]);

        // offset 185: 2-byte unsigned short, count field for Record3.VariableLengthString1
        Assert.AreEqual(11, BitConverter.ToUInt16(buff, 185));

        // offset 50-60: 11 characters of VariableLengthString1 "hello world"
        Assert.AreEqual(104, buff[187]);
        Assert.AreEqual(101, buff[188]);
        Assert.AreEqual(108, buff[189]);
        Assert.AreEqual(108, buff[190]);
        Assert.AreEqual(111, buff[191]);
        Assert.AreEqual(32, buff[192]);
        Assert.AreEqual(119, buff[193]);
        Assert.AreEqual(111, buff[194]);
        Assert.AreEqual(114, buff[195]);
        Assert.AreEqual(108, buff[196]);
        Assert.AreEqual(100, buff[197]);

        // offset 61: format field of VariableFormatField1, unsigned byte
        Assert.AreEqual(0, buff[198]);

        // offset 62: count field of VariableFormatField1, unsigned int
        Assert.AreEqual(5, BitConverter.ToUInt16(buff, 199));

        // offset 66-70: 5 byte data of VariableFormatField1
        assertBufferSliceEqual(buff, data2, 203, 5);


        buff = new byte[10000];

        // 4th msg: activate the subsequence, activate only some fields inside subsequence's record...
        MsgIn msg4 = new MsgIn();

        msg4.getHeader().getHeaderRec().setMessageID(999);

        MsgIn.Body.Sequence.Subsequence.Record sseqRec =
            msg4.getBody().getSequence().getSubsequence().getRecord();

        sseqRec.setField1((sbyte)77);
        sseqRec.getVariableLengthField1().set(10, data1);
        sseqRec.getArray1().setElement1(1, (sbyte)10);
        sseqRec.getArray1().setElement1(3, (sbyte)20);
        sseqRec.getArray1().setElement1(5, (sbyte)30);
        sseqRec.getArray1().setElement1(7, (sbyte)40);
        sseqRec.getArray1().setElement1(9, (sbyte)50);
        sseqRec.getBitField1().setSubfield1((byte)1);
        sseqRec.setFixedLengthString1("ABCDE");
        sseqRec.getVariableField1().setMeterAsUnsignedByteAt1((byte)33);
        sseqRec.setVariableLengthString1("JTS=PWNT");
        sseqRec.getVariableFormatField1().set((byte)0, 5, data2);

        msg4.encode(buff, 0);

        // offset 0:    ushort w/ message ID
        Assert.AreEqual(999, BitConverter.ToUInt16(buff, 0));

        // offset 2:    ushort w/ Sequence's presence vector - 512 indicates only Subsequence present
        Assert.AreEqual(512, BitConverter.ToUInt16(buff, 2));

        // offset 4:    ubyte w/ Subsequence's presence vector - should be 1 indicating only first
        // record is present
        Assert.AreEqual(1, buff[4]);

        // offset 5: ubyte w/ Record's presence vector - 255 indicates all elements present
        Assert.AreEqual(255, buff[5]);

        // offset 6:    signed byte, Field1 fixed field
        Assert.AreEqual(77, buff[6]);

        // offset 7:    uint, variableLengthField1 count field...
        Assert.AreEqual(10, buff[7]);

        // offset 11-20: 10-byte variableLengthField1.
        assertBufferSliceEqual(buff, data1, 11, 10);

        // offset 21: Array1[0]
        Assert.AreEqual(0, buff[21]);

        // offset 22: Array1[1]
        Assert.AreEqual(10, buff[22]);

        // offset 23: Array1[2]
        Assert.AreEqual(0, buff[23]);

        // offset 24: Array1[3]
        Assert.AreEqual(20, buff[24]);

        // offset 26: Array1[5]
        Assert.AreEqual(30, buff[26]);

        // offset 28: Array1[7]
        Assert.AreEqual(40, buff[28]);

        // offset 30: Array1[9]
        Assert.AreEqual(50, buff[30]);

        // offset 31: BitField1, single unsigned byte
        Assert.AreEqual(1, buff[31]);

        // offset 32: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(65, buff[32]);

        // offset 33: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(66, buff[33]);

        // offset 34: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(67, buff[34]);

        // offset 35: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(68, buff[35]);

        // offset 36: 15-byte fixed length string.  first 5 chars: "ABCDE"
        Assert.AreEqual(69, buff[36]);

        // offset 37: zeroed part of fixed length string
        Assert.AreEqual(0, buff[37]);

        // offset 46: last zeroed byte of fixed length string
        Assert.AreEqual(0, buff[46]);

        // offset 47: type/units enum of VariableField1, single byte
        Assert.AreEqual(1, buff[47]);

        // offset 48: contents of VariableField1
        Assert.AreEqual(33, buff[48]);

        // offset 49: 2-byte unsigned short, count field for VariableLengthString1
        Assert.AreEqual(8, BitConverter.ToUInt16(buff, 49));

        // offset 51-61: 11 characters of VariableLengthString1 "hello world"
        Assert.AreEqual(74, buff[51]);
        Assert.AreEqual(84, buff[52]);
        Assert.AreEqual(83, buff[53]);
        Assert.AreEqual(61, buff[54]);
        Assert.AreEqual(80, buff[55]);
        Assert.AreEqual(87, buff[56]);
        Assert.AreEqual(78, buff[57]);
        Assert.AreEqual(84, buff[58]);

        // offset 59: format field of VariableFormatField1, unsigned byte
        Assert.AreEqual(0, buff[59]);

        // offset 60: count field of VariableFormatField1, unsigned int
        Assert.AreEqual(5, BitConverter.ToUInt16(buff, 60));

        // offset 64-69: 5 byte data of VariableFormatField1
        assertBufferSliceEqual(buff, data2, 64, 5);
    }

    [Test]
    public void testEncodeDecodeOperations()
    {
        Console.Out.WriteLine("");
        Console.Out.Write("[executing test (testEncodeDecodeOperations)]");

        MsgIn msg1 = new MsgIn();
        MsgIn msg2 = new MsgIn();

        byte[] data1 = new byte[10];
        byte[] data2 = new byte[5];

        for (int i = 0; i < 10; ++i) {
            data1[i] = (byte)(10+i);
        }
        for (int i = 0; i < 5; ++i) {
            data2[i] = (byte)(20+i);
        }

        msg1.getHeader().getHeaderRec().setMessageID(999);

        MsgIn.Body.Sequence.Record3 rec3_i = msg1.getBody().getSequence().getRecord3();

        rec3_i.setField1((sbyte)77);
        rec3_i.getVariableLengthField1().set(10, data1);
        rec3_i.getArray1().setElement1(1, (sbyte)10);
        rec3_i.getArray1().setElement1(3, (sbyte)20);
        rec3_i.getArray1().setElement1(5, (sbyte)30);
        rec3_i.getArray1().setElement1(7, (sbyte)40);
        rec3_i.getArray1().setElement1(9, (sbyte)50);
        rec3_i.getBitField1().setSubfield1((byte)1);
        rec3_i.setFixedLengthString1("ABCDE");
        rec3_i.getVariableField1().setMeterAsUnsignedByteAt1((byte)33);
        rec3_i.setVariableLengthString1("hello world");
        rec3_i.getVariableFormatField1().set((byte)0, 5, data2);

	    MsgIn.Body.Sequence.List.Record listrec_i = new MsgIn.Body.Sequence.List.Record();
	    msg1.getBody().getSequence().getList().addElement( listrec_i );

        listrec_i.setField1((sbyte)77);
        listrec_i.getVariableLengthField1().set(10, data1);
        listrec_i.getArray1().setElement1(1, (sbyte)60);
        listrec_i.getArray1().setElement1(3, (sbyte)70);
        listrec_i.getArray1().setElement1(5, (sbyte)80);
        listrec_i.getArray1().setElement1(7, (sbyte)90);
        listrec_i.getArray1().setElement1(9, (sbyte)100);
        listrec_i.getBitField1().setSubfield1((byte)1);
        listrec_i.setFixedLengthString1("FGHIJK");
        listrec_i.getVariableField1().setMeterAsUnsignedByteAt1((byte)44);
        listrec_i.setVariableLengthString1("aabbccddeeff");
        listrec_i.getVariableFormatField1().set((byte)0, 5, data2);

        // Variants
        msg1.getBody().getSequence().getVariant().setFieldValue((byte)1); // option 1: Record
        MsgIn.Body.Sequence.Variant.Record varrec_i = msg1.getBody().getSequence().getVariant().getRecord();

        varrec_i.setField1((sbyte)99);
        varrec_i.getVariableLengthField1().set(10, data1);
        varrec_i.getArray1().setElement1(1, (sbyte)65);
        varrec_i.getArray1().setElement1(3, (sbyte)75);
        varrec_i.getArray1().setElement1(5, (sbyte)85);
        varrec_i.getArray1().setElement1(7, (sbyte)95);
        varrec_i.getArray1().setElement1(9, (sbyte)105);
        varrec_i.getBitField1().setSubfield1((byte)1);
        varrec_i.setFixedLengthString1("LMNOPQR");
        varrec_i.getVariableField1().setMeterAsUnsignedByteAt1((byte)55);
        varrec_i.setVariableLengthString1("hello world");
        varrec_i.getVariableFormatField1().set((byte)0, 5, data2);

        byte[] buff = new byte[1000];
        byte[] fieldBuff_i = new byte[100];
        byte[] fieldBuff_o = new byte[100];

        msg1.encode(buff, 0);
        msg2.decode(buff, 0);

        MsgIn.Body.Sequence.Record3 rec3_o = msg2.getBody().getSequence().getRecord3();
	    MsgIn.Body.Sequence.List.Record listrec_o = msg2.getBody().getSequence().getList().getElement(0);
        MsgIn.Body.Sequence.Variant.Record varrec_o = msg2.getBody().getSequence().getVariant().getRecord();

        // *** ensure Record3 was decoded correctly

        // compare non bytefield data
        Assert.AreEqual(rec3_i.getField1(), rec3_o.getField1());
        for (int i = 0; i < 10; ++i) {
            Assert.AreEqual(rec3_i.getArray1().getElement1(i), rec3_o.getArray1().getElement1(i));
        }
        Assert.AreEqual(rec3_i.getBitField1().getSubfield1(), rec3_o.getBitField1().getSubfield1());
        Assert.AreEqual(rec3_i.getFixedLengthString1(), rec3_o.getFixedLengthString1());
        Assert.AreEqual(rec3_i.getVariableField1().getMeterAsUnsignedByteAt1(),
                rec3_o.getVariableField1().getMeterAsUnsignedByteAt1());
        Assert.AreEqual(rec3_i.getVariableLengthString1(), rec3_o.getVariableLengthString1());

        // compare bytefield data
        fieldBuff_i = rec3_i.getVariableLengthField1().getData();
        fieldBuff_o = rec3_o.getVariableLengthField1().getData();
        assertBuffersEqual(fieldBuff_i, fieldBuff_o, 10);

        fieldBuff_i = rec3_i.getVariableFormatField1().getData();
        fieldBuff_o = rec3_o.getVariableFormatField1().getData();
        Assert.AreEqual(rec3_i.getVariableFormatField1().getFormat(),
                rec3_o.getVariableFormatField1().getFormat());
        assertBuffersEqual(fieldBuff_i, fieldBuff_o, 5);

        // *** ensure Record under List was decoded correctly

        // compare non-bytefield data
        Assert.AreEqual(listrec_i.getField1(), listrec_o.getField1());
        for (int i = 0; i < 10; ++i) {
            Assert.AreEqual(listrec_i.getArray1().getElement1(i), listrec_o.getArray1().getElement1(i));
        }
        Assert.AreEqual(listrec_i.getBitField1().getSubfield1(), listrec_o.getBitField1().getSubfield1());
        Assert.AreEqual(listrec_i.getFixedLengthString1(), listrec_o.getFixedLengthString1());
        Assert.AreEqual(listrec_i.getVariableField1().getMeterAsUnsignedByteAt1(),
                listrec_o.getVariableField1().getMeterAsUnsignedByteAt1());
        Assert.AreEqual(listrec_i.getVariableLengthString1(), listrec_o.getVariableLengthString1());

        // compare bytefield data
        fieldBuff_i = listrec_i.getVariableLengthField1().getData();
        fieldBuff_o = listrec_o.getVariableLengthField1().getData();
        assertBuffersEqual(fieldBuff_i, fieldBuff_o, 10);

        fieldBuff_i = listrec_i.getVariableFormatField1().getData();
        fieldBuff_o = listrec_o.getVariableFormatField1().getData();
        Assert.AreEqual(listrec_i.getVariableFormatField1().getFormat(),
                listrec_o.getVariableFormatField1().getFormat());
        assertBuffersEqual(fieldBuff_i, fieldBuff_o, 5);

        // *** ensure Record under Variant was decoded correctly.

        // compare non-bytefield data
        Assert.AreEqual(varrec_i.getField1(), varrec_o.getField1());
        for (int i = 0; i < 10; ++i) {
            Assert.AreEqual(varrec_i.getArray1().getElement1(i), varrec_o.getArray1().getElement1(i));
        }
        Assert.AreEqual(varrec_i.getBitField1().getSubfield1(), varrec_o.getBitField1().getSubfield1());
        Assert.AreEqual(varrec_i.getFixedLengthString1(), varrec_o.getFixedLengthString1());
        Assert.AreEqual(varrec_i.getVariableField1().getMeterAsUnsignedByteAt1(),
                varrec_o.getVariableField1().getMeterAsUnsignedByteAt1());
        Assert.AreEqual(varrec_i.getVariableLengthString1(), varrec_o.getVariableLengthString1());

        // compare bytefield data
        fieldBuff_i = varrec_i.getVariableLengthField1().getData();
        fieldBuff_o = varrec_o.getVariableLengthField1().getData();
        assertBuffersEqual(fieldBuff_i, fieldBuff_o, 10);

        fieldBuff_i = varrec_i.getVariableFormatField1().getData();
        fieldBuff_o = varrec_o.getVariableFormatField1().getData();
        Assert.AreEqual(varrec_i.getVariableFormatField1().getFormat(),
                varrec_o.getVariableFormatField1().getFormat());
        assertBuffersEqual(fieldBuff_i, fieldBuff_o, 5);
    }

    [Test]
    public void testEquality()
    {
        Console.Out.WriteLine("");
        Console.Out.Write("[executing test (testEquality)]");

        MsgIn msg1 = new MsgIn();
        byte[] data1 = new byte[10];
        byte[] data2 = new byte[5];

        for (int i = 0; i < 10; ++i) {
            data1[i] = (byte)(10+i);
        }
        for (int i = 0; i < 5; ++i) {
            data2[i] = (byte)(20+i);
        }

        msg1.getHeader().getHeaderRec().setMessageID(999);

        msg1.getBody().getSequence().getRecord2().setField1((sbyte)77);
        msg1.getBody().getSequence().getRecord2().getVariableLengthField1().set(10, data1);
        msg1.getBody().getSequence().getRecord4().getArray1().setElement1(1, (sbyte)10);
        msg1.getBody().getSequence().getRecord4().getArray1().setElement1(3, (sbyte)20);
        msg1.getBody().getSequence().getRecord4().getArray1().setElement1(5, (sbyte)30);
        msg1.getBody().getSequence().getRecord4().getArray1().setElement1(7, (sbyte)40);
        msg1.getBody().getSequence().getRecord4().getArray1().setElement1(9, (sbyte)50);
        msg1.getBody().getSequence().getRecord4().getBitField1().setSubfield1((byte)1);
        msg1.getBody().getSequence().getRecord6().setFixedLengthString1("ABCDE");
        msg1.getBody().getSequence().getRecord6().getVariableField1().setMeterAsUnsignedByteAt1((byte)33);
        msg1.getBody().getSequence().getRecord8().setVariableLengthString1("hello world");
        msg1.getBody().getSequence().getRecord8().getVariableFormatField1().set((byte)0, 5, data2);

        MsgIn msg2 = new MsgIn();

        msg2.getHeader().getHeaderRec().setMessageID(999);

        msg2.getBody().getSequence().getRecord2().setField1((sbyte)77);
        msg2.getBody().getSequence().getRecord2().getVariableLengthField1().set(10, data1);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(1, (sbyte)10);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(3, (sbyte)20);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(5, (sbyte)30);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(7, (sbyte)40);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(9, (sbyte)50);
        msg2.getBody().getSequence().getRecord4().getBitField1().setSubfield1((byte)1);
        msg2.getBody().getSequence().getRecord6().setFixedLengthString1("ABCDE");
        msg2.getBody().getSequence().getRecord6().getVariableField1().setMeterAsUnsignedByteAt1((byte)33);
        msg2.getBody().getSequence().getRecord8().setVariableLengthString1("hello world");
        msg2.getBody().getSequence().getRecord8().getVariableFormatField1().set((byte)0, 5, data2);

        Assert.IsTrue(msg1.isEqual(msg2));
        Assert.IsTrue(msg2.isEqual(msg1));
        Assert.IsTrue(msg1.isEqual(msg1));

        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(9, (sbyte)120);

        Assert.IsFalse(msg1.isEqual(msg2));
        Assert.IsFalse(msg2.isEqual(msg1));

        // Also test for PV equivalence.  First put things back to equal,
        // then set the field of record 1...
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(9, (sbyte)50);
        Assert.IsTrue(msg1.isEqual(msg2));
        msg2.getBody().getSequence().getRecord1().setField1((sbyte)0);
        Assert.IsFalse(msg1.isEqual(msg2));
    }

    [Test]
    public void testInequality()
    {
        Console.Out.WriteLine("");
        Console.Out.Write("[executing test (testInequality)]");

        MsgIn msg1 = new MsgIn();
        byte[] data1 = new byte[10];
        byte[] data2 = new byte[5];

        for (int i = 0; i < 10; ++i)
        {
            data1[i] = (byte)(10 + i);
        }
        for (int i = 0; i < 5; ++i)
        {
            data2[i] = (byte)(20 + i);
        }

        msg1.getHeader().getHeaderRec().setMessageID(999);

        msg1.getBody().getSequence().getRecord2().setField1((sbyte)77);
        msg1.getBody().getSequence().getRecord2().getVariableLengthField1().set(10, data1);
        msg1.getBody().getSequence().getRecord4().getArray1().setElement1(1, (sbyte)10);
        msg1.getBody().getSequence().getRecord4().getArray1().setElement1(3, (sbyte)20);
        msg1.getBody().getSequence().getRecord4().getArray1().setElement1(5, (sbyte)30);
        msg1.getBody().getSequence().getRecord4().getArray1().setElement1(7, (sbyte)40);
        msg1.getBody().getSequence().getRecord4().getArray1().setElement1(9, (sbyte)50);
        msg1.getBody().getSequence().getRecord4().getBitField1().setSubfield1((byte)1);
        msg1.getBody().getSequence().getRecord6().setFixedLengthString1("ABCDE");
        msg1.getBody().getSequence().getRecord6().getVariableField1().setMeterAsUnsignedByteAt1((byte)33);
        msg1.getBody().getSequence().getRecord8().setVariableLengthString1("hello world");
        msg1.getBody().getSequence().getRecord8().getVariableFormatField1().set((byte)0, 5, data2);

        MsgIn msg2 = new MsgIn();

        msg2.getHeader().getHeaderRec().setMessageID(999);

        msg2.getBody().getSequence().getRecord2().setField1((sbyte)77);
        msg2.getBody().getSequence().getRecord2().getVariableLengthField1().set(10, data1);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(1, (sbyte)10);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(3, (sbyte)20);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(5, (sbyte)30);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(7, (sbyte)40);
        msg2.getBody().getSequence().getRecord4().getArray1().setElement1(9, (sbyte)50);
        msg2.getBody().getSequence().getRecord4().getBitField1().setSubfield1((byte)1);
        msg2.getBody().getSequence().getRecord6().setFixedLengthString1("ABCDE");
        msg2.getBody().getSequence().getRecord6().getVariableField1().setMeterAsUnsignedByteAt1((byte)33);
        msg2.getBody().getSequence().getRecord8().setVariableLengthString1("hello world");
        msg2.getBody().getSequence().getRecord8().getVariableFormatField1().set((byte)0, 5, data2);

        // messages are equal now - notEquals should be false
        Assert.IsFalse(msg1.notEquals(msg2));
        Assert.IsFalse(msg2.notEquals(msg1));
        Assert.IsFalse(msg1.notEquals(msg1));

        // alter msg2, notEquals should now be true
        msg2.getBody().getSequence().getRecord6().setFixedLengthString1("ABCDEFG");

        Assert.IsTrue(msg1.notEquals(msg2));
        Assert.IsTrue(msg2.notEquals(msg1));
    }

    public static void Main()
    {
    }
}
