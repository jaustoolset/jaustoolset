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
using urn_org_jts_test_VariableField1_1_0;

[TestFixture]
public class Record10_utst
{
    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Record10_utst()
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

        // verifying set/get reciprocity"    

        m_MsgIn1.getBody().getRecord10().getVariableField1().setDegreeAsIntegerAt0(999);
        m_MsgIn2.getBody().getRecord10().getVariableField1().setDegreeAsIntegerAt0(-100);
        Assert.AreEqual(999, m_MsgIn1.getBody().getRecord10().getVariableField1().getDegreeAsIntegerAt0());
        Assert.AreEqual(-100, m_MsgIn2.getBody().getRecord10().getVariableField1().getDegreeAsIntegerAt0());

        m_MsgIn1.getBody().getRecord10().getVariableField1().setKelvinAsUnsignedShortIntegerAt1(23);
        m_MsgIn2.getBody().getRecord10().getVariableField1().setKelvinAsUnsignedShortIntegerAt1(15);
        Assert.AreEqual(23, m_MsgIn1.getBody().getRecord10().getVariableField1().getKelvinAsUnsignedShortIntegerAt1());
        Assert.AreEqual(15, m_MsgIn2.getBody().getRecord10().getVariableField1().getKelvinAsUnsignedShortIntegerAt1());
    }

    [Test]
    public void testEncodeToSpec()
    {
        byte[] buff = new byte[1024];
        int i;

        Console.WriteLine("[executing test (testEncodeToSpec)(TP_3.3.5.2)]");

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);
        m_MsgIn1.getBody().getRecord10().getVariableField1().setDegreeAsIntegerAt0(258);


        // verifying message level encode is AS-5684 compliant"
        m_MsgIn1.encode(buff, 0);

        Assert.AreEqual(0, buff[0]);
        Assert.AreEqual(1, buff[1]);

        Assert.AreEqual(0, buff[2]);
        Assert.AreEqual(2, buff[3]);
        Assert.AreEqual(1, buff[4]);
        Assert.AreEqual(0, buff[5]);
        Assert.AreEqual(0, buff[6]);
        Assert.AreEqual(7, m_MsgIn1.getSize());

        m_MsgIn1.getBody().getRecord10().getVariableField1().setKelvinAsUnsignedShortIntegerAt1(770);
        m_MsgIn1.encode(buff, 0);

        Assert.AreEqual(0, buff[0]);
        Assert.AreEqual(1, buff[1]);

        Assert.AreEqual(1, buff[2]);
        Assert.AreEqual(2, buff[3]);
        Assert.AreEqual(3, buff[4]);
        Assert.AreEqual(5, m_MsgIn1.getSize());
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
        m_MsgIn1.getBody().getRecord10().getVariableField1().setDegreeAsIntegerAt0(35);

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(127);
        m_MsgIn2.getBody().getRecord10().getVariableField1().setKelvinAsUnsignedShortIntegerAt1(127);

        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        Assert.AreEqual(7, m_MsgIn2.getSize());
        Assert.AreEqual(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(0, m_MsgIn2.getBody().getRecord10().getVariableField1().getIndex());
        Assert.AreEqual(35, m_MsgIn2.getBody().getRecord10().getVariableField1().getDegreeAsIntegerAt0());

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(66);
        m_MsgIn1.getBody().getRecord10().getVariableField1().setKelvinAsUnsignedShortIntegerAt1(3500);

        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        Assert.AreEqual(5, m_MsgIn2.getSize());
        Assert.AreEqual(66, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(1, m_MsgIn2.getBody().getRecord10().getVariableField1().getIndex());
        Assert.AreEqual(3500, m_MsgIn2.getBody().getRecord10().getVariableField1().getKelvinAsUnsignedShortIntegerAt1());

        // verifying header level encode/decode reciprocity"

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().encode(buff, 0);
        m_MsgIn2.getHeader().decode(buff, 0);
        Assert.AreEqual(66, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().getHeaderRec().encode(buff, 0);
        m_MsgIn2.getHeader().getHeaderRec().decode(buff, 0);
        Assert.AreEqual(66, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying record level encode/decode reciprocity"

        m_MsgIn1.getBody().getRecord10().getVariableField1().setDegreeAsIntegerAt0(1111);
        m_MsgIn2.getBody().getRecord10().getVariableField1().setKelvinAsUnsignedShortIntegerAt1(0);

        m_MsgIn1.getBody().encode(buff, 0);
        m_MsgIn2.getBody().decode(buff, 0);

        Assert.AreEqual(0, m_MsgIn2.getBody().getRecord10().getVariableField1().getIndex());
        Assert.AreEqual(1111, m_MsgIn2.getBody().getRecord10().getVariableField1().getDegreeAsIntegerAt0());

        m_MsgIn1.getBody().getRecord10().getVariableField1().setKelvinAsUnsignedShortIntegerAt1(1234);

        m_MsgIn1.getBody().encode(buff, 0);
        m_MsgIn2.getBody().decode(buff, 0);

        Assert.AreEqual(1, m_MsgIn2.getBody().getRecord10().getVariableField1().getIndex());
        Assert.AreEqual(1234, m_MsgIn2.getBody().getRecord10().getVariableField1().getKelvinAsUnsignedShortIntegerAt1());

        m_MsgIn1.getBody().getRecord10().getVariableField1().setDegreeAsIntegerAt0(-100);
        m_MsgIn2.getBody().getRecord10().getVariableField1().setKelvinAsUnsignedShortIntegerAt1(7);

        m_MsgIn1.getBody().getRecord10().getVariableField1().encode(buff, 0);
        m_MsgIn2.getBody().getRecord10().getVariableField1().decode(buff, 0);

        Assert.AreEqual(0, m_MsgIn2.getBody().getRecord10().getVariableField1().getIndex());
        Assert.AreEqual(-100, m_MsgIn2.getBody().getRecord10().getVariableField1().getDegreeAsIntegerAt0());

        m_MsgIn1.getBody().getRecord10().getVariableField1().setKelvinAsUnsignedShortIntegerAt1(2323);

        m_MsgIn1.getBody().getRecord10().getVariableField1().encode(buff, 0);
        m_MsgIn2.getBody().getRecord10().getVariableField1().decode(buff, 0);

        Assert.AreEqual(1, m_MsgIn2.getBody().getRecord10().getVariableField1().getIndex());
        Assert.AreEqual(2323, m_MsgIn2.getBody().getRecord10().getVariableField1().getKelvinAsUnsignedShortIntegerAt1());
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
