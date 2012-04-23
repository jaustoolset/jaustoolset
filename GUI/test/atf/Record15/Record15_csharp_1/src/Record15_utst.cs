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

using urn_org_jts_test_FixedLengthString1_1_0;
using System;
using NUnit.Framework;

[TestFixture]
public class Record15_utst
{
    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Record15_utst()
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

        // verifying MsgIn constructs with correct default DimensionSize"
        Assert.AreEqual(100, m_MsgIn1.getBody().getRecord15().getNames().getXSize());
        Assert.AreEqual(10, m_MsgIn1.getBody().getRecord15().getNames().getYSize());

        Assert.AreEqual(100, m_MsgIn2.getBody().getRecord15().getNames().getXSize());
        Assert.AreEqual(10, m_MsgIn2.getBody().getRecord15().getNames().getYSize());
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

        // verifying set/getName reciprocity"    
        int i, j;
        string formattedStr = "";

        for (i = 0; i < m_MsgIn1.getBody().getRecord15().getNames().getXSize(); i++)
            for (j = 0; j < m_MsgIn1.getBody().getRecord15().getNames().getYSize(); j++)
            {
                formattedStr = String.Format("m_MsgIn1({0},{1})", i, j);
                m_MsgIn1.getBody().getRecord15().getNames().setName(i, j, formattedStr);
                formattedStr = String.Format("m_MsgIn2({0},{1})", i, j);
                m_MsgIn2.getBody().getRecord15().getNames().setName(i, j, formattedStr);
            }

        for (i = 0; i < m_MsgIn1.getBody().getRecord15().getNames().getXSize(); i++)
            for (j = 0; j < m_MsgIn1.getBody().getRecord15().getNames().getYSize(); j++)
            {
                formattedStr = String.Format("m_MsgIn1({0},{1})", i, j);
                Assert.AreEqual(formattedStr, m_MsgIn1.getBody().getRecord15().getNames().getName(i, j));

                formattedStr = String.Format("m_MsgIn2({0},{1})", i, j);
                Assert.AreEqual(formattedStr, m_MsgIn2.getBody().getRecord15().getNames().getName(i, j));
            }
    }

    private string buildEncodeTestString(short dataValue, int stringLength)
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
        byte[] buff = new byte[90000];
        int i,j;
        string stringData = "";

        Console.WriteLine("[executing test (testEncodeToSpec)(TP_3.3.5.2)]");

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);
        short val = 2;

        // set the first 200 strings in the array's encoding.
        for (j = 0; j < 2; ++j) {
            for (i = 0; i < 100; ++i) {
                stringData = buildEncodeTestString(val, 80);
                m_MsgIn1.getBody().getRecord15().getNames().setName(i, j, stringData);
                val++;
            }
        }

        // verifying message level encode is AS-5684 compliant"
        m_MsgIn1.encode(buff, 0);
        Assert.AreEqual(0, buff[0]);
        Assert.AreEqual(1, buff[1]);
        val = 2;
        i=2;

        // loop through first 200 80-character strings in the array's encoding.
        while (i < (200 * 80) + 2)
        {
            for (j = i; j < i + 80; j++)
            {
                // treat value in the buffer as an unsigned byte.
                Assert.AreEqual(val, (short)(buff[j] & 0xff));
            }
            val++;
            i += 80;
        }
    }

    [Test]
    public void testEncodeDecodeOperations()
    {
        byte[] buff = new byte[90000];
        int i, j;
        string formattedString = "";


        Console.WriteLine("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]");

        // verifying message level encode/decode reciprocity"

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);

        for (i = 0; i < m_MsgIn1.getBody().getRecord15().getNames().getXSize(); i++)
            for (j = 0; j < m_MsgIn1.getBody().getRecord15().getNames().getYSize(); j++)
            {
                formattedString = String.Format("m_MsgIn1({0},{1})", i, j);
                m_MsgIn1.getBody().getRecord15().getNames().setName(i, j, formattedString);
                formattedString = String.Format("m_MsgIn2({0},{1})", i, j);
                m_MsgIn2.getBody().getRecord15().getNames().setName(i, j, formattedString);
            }
        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        Assert.AreEqual(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++)
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++)
            {
                formattedString = String.Format("m_MsgIn1({0},{1})", i, j);
                Assert.AreEqual(formattedString, m_MsgIn2.getBody().getRecord15().getNames().getName(i, j));
            }

        // verifying header level encode/decode reciprocity"

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().encode(buff, 0);
        m_MsgIn2.getHeader().decode(buff, 0);
        Assert.AreEqual(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().getHeaderRec().encode(buff, 0);
        m_MsgIn2.getHeader().getHeaderRec().decode(buff, 0);
        Assert.AreEqual(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying record level encode/decode reciprocity"

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++)
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++)
            {
                formattedString = String.Format("m_MsgIn2({0},{1})", i, j);
                m_MsgIn2.getBody().getRecord15().getNames().setName(i, j, formattedString);
            }

        m_MsgIn1.getBody().encode(buff, 0);
        m_MsgIn2.getBody().decode(buff, 0);

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++)
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++)
            {
                formattedString = String.Format("m_MsgIn1({0},{1})", i, j);
                Assert.AreEqual(formattedString, m_MsgIn2.getBody().getRecord15().getNames().getName(i, j));
            }

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++)
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++)
            {
                formattedString = String.Format("m_MsgIn2({0},{1})", i, j);
                m_MsgIn2.getBody().getRecord15().getNames().setName(i, j, formattedString);
            }

        m_MsgIn1.getBody().getRecord15().encode(buff, 0);
        m_MsgIn2.getBody().getRecord15().decode(buff, 0);

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++)
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++)
            {
                formattedString = String.Format("m_MsgIn1({0},{1})", i, j);
                Assert.AreEqual(formattedString, m_MsgIn2.getBody().getRecord15().getNames().getName(i, j));
            }

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++)
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++)
            {
                formattedString = String.Format("m_MsgIn2({0},{1})", i, j);
                m_MsgIn2.getBody().getRecord15().getNames().setName(i, j, formattedString);
            }

        m_MsgIn1.getBody().getRecord15().getNames().encode(buff, 0);
        m_MsgIn2.getBody().getRecord15().getNames().decode(buff, 0);

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++)
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++)
            {
                formattedString = String.Format("m_MsgIn1({0},{1})", i, j);
                Assert.AreEqual(formattedString, m_MsgIn2.getBody().getRecord15().getNames().getName(i, j));
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
