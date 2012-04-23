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
using urn_org_jts_test_Record1_1_0;

[TestFixture]
public class Body9_utst
{
    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Body9_utst()
    {
    }

    [SetUp]
    public void setUp()
    {
        m_MsgIn1 = new MsgIn();
        m_MsgIn2 = new MsgIn();
    }

    void tearDown()
    {
    }

    [Test]
    public void testConstructionDefaults()
    {
    }

    [Test]
    public void testSetGetOperations()
    {
        byte[] data = new byte[10] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Console.WriteLine("[executing test (testSetGetOperations)(TP_3.3.5.1)]");

        Assert.AreEqual(0, m_MsgIn1.getBody9().getRecord9().getPresenceVector());

        // Set the first entry in the record.
        // verifying optional fixed field handling"
        Assert.AreEqual(false, m_MsgIn1.getBody9().getRecord9().isField1Valid());
        m_MsgIn1.getBody9().getRecord9().setField1(5);
        Assert.AreEqual(true, m_MsgIn1.getBody9().getRecord9().isField1Valid());
        Assert.AreEqual(1, m_MsgIn1.getBody9().getRecord9().getPresenceVector());

        // verifying optional variable length field handling"
        Assert.AreEqual(false, m_MsgIn1.getBody9().getRecord9().isVariableLengthField1Valid());
        MsgIn.Body9.Record9.VariableLengthField1 vl1 = new MsgIn.Body9.Record9.VariableLengthField1(); 
        vl1.set(10, data);
        m_MsgIn1.getBody9().getRecord9().setVariableLengthField1(vl1);
        Assert.AreEqual(true, m_MsgIn1.getBody9().getRecord9().isVariableLengthField1Valid());
        Assert.AreEqual(3, m_MsgIn1.getBody9().getRecord9().getPresenceVector());

        // verifying optional array handling"
        Assert.AreEqual(false, m_MsgIn1.getBody9().getRecord9().isArray1Valid());
        MsgIn.Body9.Record9.Array1 array1 = new MsgIn.Body9.Record9.Array1(); 
        array1.setElement1(0, 0);
        m_MsgIn1.getBody9().getRecord9().setArray1(array1);
        Assert.AreEqual(true, m_MsgIn1.getBody9().getRecord9().isArray1Valid());
        Assert.AreEqual(7, m_MsgIn1.getBody9().getRecord9().getPresenceVector());

        // verifying optional bitfield handling"
        Assert.AreEqual(false, m_MsgIn1.getBody9().getRecord9().isBitField1Valid());
        MsgIn.Body9.Record9.BitField1 bf1 = new MsgIn.Body9.Record9.BitField1(); 
        bf1.setSubfield1(1);
        m_MsgIn1.getBody9().getRecord9().setBitField1(bf1);
        Assert.AreEqual(true, m_MsgIn1.getBody9().getRecord9().isBitField1Valid());
        Assert.AreEqual(15, m_MsgIn1.getBody9().getRecord9().getPresenceVector());

        // verifying optional fixed length string handling"
        Assert.AreEqual(false, m_MsgIn1.getBody9().getRecord9().isFixedLengthString1Valid());
        m_MsgIn1.getBody9().getRecord9().setFixedLengthString1("wtf");
        Assert.AreEqual(true, m_MsgIn1.getBody9().getRecord9().isFixedLengthString1Valid());
        Assert.AreEqual(31, m_MsgIn1.getBody9().getRecord9().getPresenceVector());

        // verifying optional variable field handling"
        Assert.AreEqual(false, m_MsgIn1.getBody9().getRecord9().isVariableField1Valid());
        MsgIn.Body9.Record9.VariableField1 vf1 = new MsgIn.Body9.Record9.VariableField1(); 
        vf1.setOneAsUnsignedByteAt0(1);
        m_MsgIn1.getBody9().getRecord9().setVariableField1(vf1);
        Assert.AreEqual(true, m_MsgIn1.getBody9().getRecord9().isVariableField1Valid());
        Assert.AreEqual(63, m_MsgIn1.getBody9().getRecord9().getPresenceVector());

        // verifying optional variable length string handling"
        Assert.AreEqual(false, m_MsgIn1.getBody9().getRecord9().isVariableLengthString1Valid());
        m_MsgIn1.getBody9().getRecord9().setVariableLengthString1("jts");
        Assert.AreEqual(true, m_MsgIn1.getBody9().getRecord9().isVariableLengthString1Valid());
        Assert.AreEqual(127, m_MsgIn1.getBody9().getRecord9().getPresenceVector());

        // verifying optional variable format field handling"
        Assert.AreEqual(false, m_MsgIn1.getBody9().getRecord9().isVariableFormatField1Valid());
        MsgIn.Body9.Record9.VariableFormatField1 vff1 = new MsgIn.Body9.Record9.VariableFormatField1(); 
        vff1.set(0, 10, data);
        m_MsgIn1.getBody9().getRecord9().setVariableFormatField1(vff1);
        Assert.AreEqual(true, m_MsgIn1.getBody9().getRecord9().isVariableFormatField1Valid());
        Assert.AreEqual(255, m_MsgIn1.getBody9().getRecord9().getPresenceVector());
    }

    [Test]
    public void testEncodeToSpec()
    {
    }

    [Test]
    public void testEncodeDecodeOperations()
    {
    }

    [Test]
    public void testEquality()
    {
        Console.WriteLine("[executing test (testEquality)]");
        Assert.IsTrue(m_MsgIn1.isEqual(m_MsgIn1));
        Assert.IsTrue(m_MsgIn2.isEqual(m_MsgIn1));
        Assert.IsTrue(m_MsgIn1.isEqual(m_MsgIn2));
    }

    [Test]
    public void testInequality()
    {
        Console.WriteLine("[executing test (testInequality)]");
        Assert.IsFalse(m_MsgIn1.notEquals(m_MsgIn2));
        Assert.IsFalse(m_MsgIn2.notEquals(m_MsgIn1));
    }

    public static void Main()
    {
    }
}
