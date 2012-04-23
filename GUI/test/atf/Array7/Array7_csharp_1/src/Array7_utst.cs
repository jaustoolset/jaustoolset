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
using urn_org_jts_test_Array7_1_0;

[TestFixture]
public class Array7_utst
{
    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Array7_utst()
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
        Console.Out.WriteLine("executing test (testConstructionDefaults): ");

        // verifying MsgIn constructs with correct default MessageID
        Assert.AreEqual(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(1, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying MsgIn constructs with correct default DimensionSize
        Assert.AreEqual(9, m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size());
        Assert.AreEqual(9, m_MsgIn2.getBody().getRecord().getArray7().getDimension1Size());

        // verify strings in array field are initialized to a zero-length strings
        Assert.AreEqual("", m_MsgIn1.getBody().getRecord().getArray7().getVariableLengthString1(0));
        Assert.AreEqual("", m_MsgIn1.getBody().getRecord().getArray7().getVariableLengthString1(8));
        Assert.AreEqual("", m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(0));
        Assert.AreEqual("", m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(8));
    }

    [Test]
    public void testSetGetOperations()
    {
        Console.WriteLine("[executing test (testSetGetOperations)(TP_3.3.5.1)]");

        // verifying setMessageID/getMessageID reciprocity
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(5);
        Assert.AreEqual(5, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(1);
        Assert.AreEqual(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying setField/getField reciprocity
        int i;
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++)
        {
            m_MsgIn1.getBody().getRecord().getArray7().setVariableLengthString1(i, "msg1 data");
            m_MsgIn2.getBody().getRecord().getArray7().setVariableLengthString1(i, "msg2 data");
        }

        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++)
        {
            Assert.AreEqual("msg1 data", m_MsgIn1.getBody().getRecord().getArray7().getVariableLengthString1(i));
            Assert.AreEqual("msg2 data", m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(i));
        }
    }

    /**
     * produce a string of length stringLength by concatenating stringLength instances of dataValue cast
     * to a char.
     * @param dataValue
     * @param stringLength
     * @return
     */
    private string buildEncodeTestString(int dataValue, int stringLength)
    {
        string resultString = "";

        for (int i = 0; i < stringLength; ++i)
        {
            resultString += (char)dataValue;
        }

        return resultString;
    }

    [Test]
    public void testEncodeToSpec()
    {
        Console.Out.WriteLine("");
        Console.Out.WriteLine("executing test testEncodeToSpec(TP_3.3.5.2): ");

        byte[] buff = new byte[1024];
        int i = 0;

        for (i = 0; i < 1024; i++)
            buff[i] = 0;

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);

        // array's length should be 9.
        // populate the array with 9 strings; first is 'A', second is 'BB', up to 'IIIIIIIII' (nine 'I's)
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++)
        {
            m_MsgIn1.getBody().getRecord().getArray7().setVariableLengthString1(i, buildEncodeTestString(65 + i, i + 1));
        }

        // verifying message level encode is AS-5684 compliant
        m_MsgIn1.encode(buff, 0);

        // offset 0 in buff is message ID, should be 256 (in an unsigned short)
        Assert.AreEqual(256, BitConverter.ToUInt16(buff, 0));

        // offset 2 in buff is length of first string, should be 1 (in an unsigned short)
        Assert.AreEqual(1, BitConverter.ToUInt16(buff, 2));

        // offset 4 in buff is single 'A' of first string, single byte UTF-8 character
        Assert.AreEqual(65, buff[4]);

        // offset 5 = length of 2nd string, 2 in unsigned short.
        Assert.AreEqual(2, BitConverter.ToUInt16(buff, 5));

        // offset 7 = 1st 'B' in 2nd string, single byte UTF-8 character
        Assert.AreEqual(66, buff[7]);

        // offset 8 = 2nd 'B' in 2nd string
        Assert.AreEqual(66,buff[8]);

        // offset 9 = length of 3rd string, 3 in unsigned short.
        Assert.AreEqual(3, BitConverter.ToUInt16(buff, 9));

        // offset 11 = 1st 'C' in 3rd string
        Assert.AreEqual(67, buff[11]);

        //        12 = 2nd 'C' in 3rd string
        Assert.AreEqual(67, buff[12]);

        //        13 = 3rd 'C' in 3rd string
        Assert.AreEqual(67, buff[13]);

        // skipping to 9th string, should be 9 'I's, length value at offset 54
        Assert.AreEqual(9, BitConverter.ToUInt16(buff, 54));

        //        56 = 1st 'I' in 9th string
        Assert.AreEqual(73, buff[56]);

        //        60 = 5th 'I' in 9th string
        Assert.AreEqual(73, buff[60]);

        //        64 = 9th 'I' in 9th string
        Assert.AreEqual(73, buff[64]);

        //        65 = past end of message encoding, should be 0.
        Assert.AreEqual(0, buff[65]);
    }

    [Test]
    public void testEncodeDecodeOperations()
    {
        byte[] buff = new byte[1024];
        int i;

        for (i = 0; i < 1024; i++)
            buff[i] = (byte)(((4101 * i) + 65543) % 256);

        Console.WriteLine("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]");

        // verifying message level encode/decode reciprocity"
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++)
        {
            m_MsgIn1.getBody().getRecord().getArray7().setVariableLengthString1(i, Convert.ToString(i));
            m_MsgIn2.getBody().getRecord().getArray7().setVariableLengthString1(i, Convert.ToString(42));
        }
        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        Assert.AreEqual(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++)
        {
            Assert.AreEqual(Convert.ToString(i), m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(i));
        }

        // verify encoded header decodes properly

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().encode(buff, 0);
        m_MsgIn2.getHeader().decode(buff, 0);
        Assert.AreEqual(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());


        // verify encoded body decodes properly

        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++)
        {
            m_MsgIn2.getBody().getRecord().getArray7().setVariableLengthString1(i, "foo");
        }

        m_MsgIn1.getBody().encode(buff, 0);
        m_MsgIn2.getBody().decode(buff, 0);
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++)
        {
            Assert.AreEqual(Convert.ToString(i),
                    m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(i));
        }

        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++)
        {
            m_MsgIn2.getBody().getRecord().getArray7().setVariableLengthString1(i, "foo");
        }

        m_MsgIn1.getBody().getRecord().encode(buff, 0);
        m_MsgIn2.getBody().getRecord().decode(buff, 0);
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++)
        {
            Assert.AreEqual(Convert.ToString(i), m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(i));
        }

        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++)
        {
            m_MsgIn2.getBody().getRecord().getArray7().setVariableLengthString1(i, "foo");
        }

        m_MsgIn1.getBody().getRecord().getArray7().encode(buff, 0);
        m_MsgIn2.getBody().getRecord().getArray7().decode(buff, 0);
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++)
        {
            Assert.AreEqual(Convert.ToString(i), m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(i));
        }
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
