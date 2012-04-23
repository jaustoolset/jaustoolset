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
using urn_org_jts_test_FixedLengthString1_1_0;

[TestFixture]
public class Record16_utst
{
    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Record16_utst()
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
        Assert.AreEqual(100, m_MsgIn1.getBody().getRecord16().getArray1().getXSize());
        Assert.AreEqual(10, m_MsgIn1.getBody().getRecord16().getArray1().getYSize());

        Assert.AreEqual(100, m_MsgIn2.getBody().getRecord16().getArray1().getXSize());
        Assert.AreEqual(10, m_MsgIn2.getBody().getRecord16().getArray1().getYSize());
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
        int i, j, r;

        r = 0;
        for (i = 0; i < m_MsgIn1.getBody().getRecord16().getArray1().getXSize(); i++)
            for (j = 0; j < m_MsgIn1.getBody().getRecord16().getArray1().getYSize(); j++)
            {
                if (r == 1)
                {
                    m_MsgIn1.getBody().getRecord16().getArray1().getVariableField1(i, j).setDegreeAsIntegerAt0((i * 10) + j);
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setKelvinAsUnsignedShortIntegerAt1((ushort)((i * 10) + j));
                    r = 0;
                }
                else
                {
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setDegreeAsIntegerAt0((i * 10) + j);
                    m_MsgIn1.getBody().getRecord16().getArray1().getVariableField1(i, j).setKelvinAsUnsignedShortIntegerAt1((ushort)((i * 10) + j));
                    r = 1;
                }
            }

        // Don't forget to account for index (unsigned byte) for each variable field
        Assert.AreEqual(4000, m_MsgIn1.getBody().getRecord16().getArray1().getSize());

        r = 0;
        for (i = 0; i < m_MsgIn1.getBody().getRecord16().getArray1().getXSize(); i++)
            for (j = 0; j < m_MsgIn1.getBody().getRecord16().getArray1().getYSize(); j++)
            {
                if (r == 1)
                {
                    Assert.AreEqual(0, m_MsgIn1.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn1.getBody().getRecord16().getArray1().getVariableField1(i, j).getDegreeAsIntegerAt0());
                    Assert.AreEqual(1, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getKelvinAsUnsignedShortIntegerAt1());
                    r = 0;
                }
                else
                {
                    Assert.AreEqual(0, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getDegreeAsIntegerAt0());
                    Assert.AreEqual(1, m_MsgIn1.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn1.getBody().getRecord16().getArray1().getVariableField1(i, j).getKelvinAsUnsignedShortIntegerAt1());
                    r = 1;
                }
            }
    }

    [Test]
    public void testEncodeToSpec()
    {
        byte[] buff = new byte[90000];
        int i, j, r, v;

        Console.WriteLine("[executing test (testEncodeToSpec)(TP_3.3.5.2)]");

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);
        r = 0;
        for (j = 0; j < m_MsgIn1.getBody().getRecord16().getArray1().getYSize(); j++)
            for (i = 0; i < m_MsgIn1.getBody().getRecord16().getArray1().getXSize(); i++)
                if (r == 1)
                {
                    m_MsgIn1.getBody().getRecord16().getArray1().getVariableField1(i, j).setDegreeAsIntegerAt0((j * 100) + i + 2);
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setKelvinAsUnsignedShortIntegerAt1((ushort)((j * 100) + i + 2));
                    r = 0;
                }
                else
                {
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setDegreeAsIntegerAt0((j * 100) + i + 2);
                    m_MsgIn1.getBody().getRecord16().getArray1().getVariableField1(i, j).setKelvinAsUnsignedShortIntegerAt1((ushort)((j * 100) + i + 2));
                    r = 1;
                }

        // verifying message level encode is AS-5684 compliant"
        m_MsgIn1.encode(buff, 0);
        Assert.AreEqual(0, buff[0]);
        Assert.AreEqual(1, buff[1]);
        i = 2;
        r = 0;
        v = 2;
        while (i < m_MsgIn1.getSize())
        {
            if (r == 1)
            {
                Assert.AreEqual(0, buff[i]);
                Assert.AreEqual((v % 256), buff[i + 1]);
                Assert.AreEqual(((v / 256) % 256), buff[i + 2]);
                Assert.AreEqual(0, buff[i + 3]);
                Assert.AreEqual(0, buff[i + 4]);
                i += 5;
                r = 0;
            }
            else
            {
                Assert.AreEqual(1, buff[i]);
                Assert.AreEqual((v % 256), buff[i + 1]);
                Assert.AreEqual(((v / 256) % 256), buff[i + 2]);
                i += 3;
                r = 1;
            }
            v++;
        }
    }

    [Test]
    public void testEncodeDecodeOperations()
    {
        byte[] buff = new byte[90000];
        int i, j, r;

        Console.WriteLine("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]");

        // verifying message level encode/decode reciprocity"

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);

        r = 0;
        for (i = 0; i < m_MsgIn1.getBody().getRecord16().getArray1().getXSize(); i++)
            for (j = 0; j < m_MsgIn1.getBody().getRecord16().getArray1().getYSize(); j++)
            {
                if (r == 1)
                {
                    m_MsgIn1.getBody().getRecord16().getArray1().getVariableField1(i, j).setDegreeAsIntegerAt0((i * 10) + j);
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setKelvinAsUnsignedShortIntegerAt1(127);
                    r = 0;
                }
                else
                {
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setDegreeAsIntegerAt0(127);
                    m_MsgIn1.getBody().getRecord16().getArray1().getVariableField1(i, j).setKelvinAsUnsignedShortIntegerAt1((ushort)((i * 10) + j));
                    r = 1;
                }
            }
        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        Assert.AreEqual(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        r = 0;
        for (i = 0; i < m_MsgIn2.getBody().getRecord16().getArray1().getXSize(); i++)
            for (j = 0; j < m_MsgIn2.getBody().getRecord16().getArray1().getYSize(); j++)
            {
                if (r == 1)
                {
                    Assert.AreEqual(0, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getDegreeAsIntegerAt0());
                    r = 0;
                }
                else
                {
                    Assert.AreEqual(1, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getKelvinAsUnsignedShortIntegerAt1());
                    r = 1;
                }
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

        r = 0;
        for (i = 0; i < m_MsgIn1.getBody().getRecord16().getArray1().getXSize(); i++)
            for (j = 0; j < m_MsgIn1.getBody().getRecord16().getArray1().getYSize(); j++)
            {
                if (r == 1)
                {
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setKelvinAsUnsignedShortIntegerAt1(127);
                    r = 0;
                }
                else
                {
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setDegreeAsIntegerAt0(127);
                    r = 1;
                }
            }

        m_MsgIn1.getBody().encode(buff, 0);
        m_MsgIn2.getBody().decode(buff, 0);

        r = 0;
        for (i = 0; i < m_MsgIn2.getBody().getRecord16().getArray1().getXSize(); i++)
            for (j = 0; j < m_MsgIn2.getBody().getRecord16().getArray1().getYSize(); j++)
            {
                if (r == 1)
                {
                    Assert.AreEqual(0, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getDegreeAsIntegerAt0());
                    r = 0;
                }
                else
                {
                    Assert.AreEqual(1, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getKelvinAsUnsignedShortIntegerAt1());
                    r = 1;
                }
            }

        r = 0;
        for (i = 0; i < m_MsgIn1.getBody().getRecord16().getArray1().getXSize(); i++)
            for (j = 0; j < m_MsgIn1.getBody().getRecord16().getArray1().getYSize(); j++)
            {
                if (r == 1)
                {
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setKelvinAsUnsignedShortIntegerAt1(127);
                    r = 0;
                }
                else
                {
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setDegreeAsIntegerAt0(127);
                    r = 1;
                }
            }

        m_MsgIn1.getBody().getRecord16().encode(buff, 0);
        m_MsgIn2.getBody().getRecord16().decode(buff, 0);

        r = 0;
        for (i = 0; i < m_MsgIn2.getBody().getRecord16().getArray1().getXSize(); i++)
            for (j = 0; j < m_MsgIn2.getBody().getRecord16().getArray1().getYSize(); j++)
            {
                if (r == 1)
                {
                    Assert.AreEqual(0, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getDegreeAsIntegerAt0());
                    r = 0;
                }
                else
                {
                    Assert.AreEqual(1, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getKelvinAsUnsignedShortIntegerAt1());
                    r = 1;
                }
            }

        r = 0;
        for (i = 0; i < m_MsgIn1.getBody().getRecord16().getArray1().getXSize(); i++)
            for (j = 0; j < m_MsgIn1.getBody().getRecord16().getArray1().getYSize(); j++)
            {
                if (r == 1)
                {
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setKelvinAsUnsignedShortIntegerAt1(127);
                    r = 0;
                }
                else
                {
                    m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).setDegreeAsIntegerAt0(127);
                    r = 1;
                }
            }

        m_MsgIn1.getBody().getRecord16().getArray1().encode(buff, 0);
        m_MsgIn2.getBody().getRecord16().getArray1().decode(buff, 0);

        r = 0;
        for (i = 0; i < m_MsgIn2.getBody().getRecord16().getArray1().getXSize(); i++)
            for (j = 0; j < m_MsgIn2.getBody().getRecord16().getArray1().getYSize(); j++)
            {
                if (r == 1)
                {
                    Assert.AreEqual(0, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getDegreeAsIntegerAt0());
                    r = 0;
                }
                else
                {
                    Assert.AreEqual(1, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getIndex());
                    Assert.AreEqual((i * 10) + j, m_MsgIn2.getBody().getRecord16().getArray1().getVariableField1(i, j).getKelvinAsUnsignedShortIntegerAt1());
                    r = 1;
                }
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
