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
using urn_org_jts_test_Header4_1_0;

[TestFixture]
public class Header4_utst
{
    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Header4_utst()
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

    }

    [Test]
    public void testSetGetOperations()
    {
        Console.WriteLine("[executing test (testSetGetOperations)(TP_3.3.5.1)]");

        // verifying setMilliseconds/getMilliseconds reciprocity"    
        m_MsgIn1.getHeader().getRecord().getBitField1().setMilliseconds(715);
        m_MsgIn2.getHeader().getRecord().getBitField1().setMilliseconds(3);
        Assert.AreEqual(715, m_MsgIn1.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(3, m_MsgIn2.getHeader().getRecord().getBitField1().getMilliseconds());

        // verifying setSeconds/getSeconds reciprocity"    
        m_MsgIn1.getHeader().getRecord().getBitField1().setSeconds(41);
        m_MsgIn2.getHeader().getRecord().getBitField1().setSeconds(0);
        Assert.AreEqual(715, m_MsgIn1.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(3, m_MsgIn2.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(41, m_MsgIn1.getHeader().getRecord().getBitField1().getSeconds());
        Assert.AreEqual(0, m_MsgIn2.getHeader().getRecord().getBitField1().getSeconds());

        // verifying setMinutes/getMinutes reciprocity"    
        m_MsgIn1.getHeader().getRecord().getBitField1().setMinutes(12);
        m_MsgIn2.getHeader().getRecord().getBitField1().setMinutes(59);
        Assert.AreEqual(715, m_MsgIn1.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(3, m_MsgIn2.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(41, m_MsgIn1.getHeader().getRecord().getBitField1().getSeconds());
        Assert.AreEqual(0, m_MsgIn2.getHeader().getRecord().getBitField1().getSeconds());
        Assert.AreEqual(12, m_MsgIn1.getHeader().getRecord().getBitField1().getMinutes());
        Assert.AreEqual(59, m_MsgIn2.getHeader().getRecord().getBitField1().getMinutes());

        // verifying setHour/getHour reciprocity"    
        m_MsgIn1.getHeader().getRecord().getBitField1().setHour(12);
        m_MsgIn2.getHeader().getRecord().getBitField1().setHour(19);
        Assert.AreEqual(715, m_MsgIn1.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(3, m_MsgIn2.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(41, m_MsgIn1.getHeader().getRecord().getBitField1().getSeconds());
        Assert.AreEqual(0, m_MsgIn2.getHeader().getRecord().getBitField1().getSeconds());
        Assert.AreEqual(12, m_MsgIn1.getHeader().getRecord().getBitField1().getMinutes());
        Assert.AreEqual(59, m_MsgIn2.getHeader().getRecord().getBitField1().getMinutes());
        Assert.AreEqual(12, m_MsgIn1.getHeader().getRecord().getBitField1().getHour());
        Assert.AreEqual(19, m_MsgIn2.getHeader().getRecord().getBitField1().getHour());

        // verifying setDay/getDay reciprocity"    
        m_MsgIn1.getHeader().getRecord().getBitField1().setDay(1);
        m_MsgIn2.getHeader().getRecord().getBitField1().setDay(31);
        Assert.AreEqual(715, m_MsgIn1.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(3, m_MsgIn2.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(41, m_MsgIn1.getHeader().getRecord().getBitField1().getSeconds());
        Assert.AreEqual(0, m_MsgIn2.getHeader().getRecord().getBitField1().getSeconds());
        Assert.AreEqual(12, m_MsgIn1.getHeader().getRecord().getBitField1().getMinutes());
        Assert.AreEqual(59, m_MsgIn2.getHeader().getRecord().getBitField1().getMinutes());
        Assert.AreEqual(12, m_MsgIn1.getHeader().getRecord().getBitField1().getHour());
        Assert.AreEqual(19, m_MsgIn2.getHeader().getRecord().getBitField1().getHour());
        Assert.AreEqual(1, m_MsgIn1.getHeader().getRecord().getBitField1().getDay());
        Assert.AreEqual(31, m_MsgIn2.getHeader().getRecord().getBitField1().getDay());
    }

    [Test]
    public void testEncodeToSpec()
    {
        byte[] buff = new byte[1024];
        int i;

        Console.WriteLine("[executing test (testEncodeToSpec)(TP_3.3.5.2)]");

        m_MsgIn1.getHeader().getRecord().getBitField1().setMilliseconds(770);
        m_MsgIn1.getHeader().getRecord().getBitField1().setSeconds(0);
        m_MsgIn1.getHeader().getRecord().getBitField1().setMinutes(4);
        m_MsgIn1.getHeader().getRecord().getBitField1().setHour(20);
        m_MsgIn1.getHeader().getRecord().getBitField1().setDay(1);



        // verifying message level encode is AS-5684 compliant"
        m_MsgIn1.encode(buff, 0);

        for (i = 0; i < m_MsgIn1.getSize() - 1; i++)
            Assert.AreEqual((i + 2), buff[i]);
        Assert.AreEqual(13, buff[m_MsgIn1.getSize() - 1]);
    }

    [Test]
    public void testEncodeDecodeOperations()
    {
        byte[] buff = new byte[1024];
        int i;

        for (i = 0; i < 1024; i++)
        {
            buff[i] = (byte)(((4101 * i) + 65543) % 256);
        }

        Console.WriteLine("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]");

        // verifying message level encode/decode reciprocity"

        m_MsgIn1.getHeader().getRecord().getBitField1().setMilliseconds(333);
        m_MsgIn1.getHeader().getRecord().getBitField1().setSeconds(15);
        m_MsgIn1.getHeader().getRecord().getBitField1().setMinutes(49);
        m_MsgIn1.getHeader().getRecord().getBitField1().setHour(3);
        m_MsgIn1.getHeader().getRecord().getBitField1().setDay(21);

        m_MsgIn2.getHeader().getRecord().getBitField1().setMilliseconds(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setSeconds(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setMinutes(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setHour(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setHour(0);

        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        Assert.AreEqual(333, m_MsgIn2.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(15, m_MsgIn2.getHeader().getRecord().getBitField1().getSeconds());
        Assert.AreEqual(49, m_MsgIn2.getHeader().getRecord().getBitField1().getMinutes());
        Assert.AreEqual(3, m_MsgIn2.getHeader().getRecord().getBitField1().getHour());
        Assert.AreEqual(21, m_MsgIn2.getHeader().getRecord().getBitField1().getDay());

        // verifying record level encode/decode reciprocity"

        m_MsgIn2.getHeader().getRecord().getBitField1().setMilliseconds(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setSeconds(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setMinutes(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setHour(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setHour(0);

        m_MsgIn1.getHeader().encode(buff, 0);
        m_MsgIn2.getHeader().decode(buff, 0);

        Assert.AreEqual(333, m_MsgIn2.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(15, m_MsgIn2.getHeader().getRecord().getBitField1().getSeconds());
        Assert.AreEqual(49, m_MsgIn2.getHeader().getRecord().getBitField1().getMinutes());
        Assert.AreEqual(3, m_MsgIn2.getHeader().getRecord().getBitField1().getHour());
        Assert.AreEqual(21, m_MsgIn2.getHeader().getRecord().getBitField1().getDay());

        m_MsgIn2.getHeader().getRecord().getBitField1().setMilliseconds(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setSeconds(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setMinutes(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setHour(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setHour(0);

        m_MsgIn1.getHeader().getRecord().encode(buff, 0);
        m_MsgIn2.getHeader().getRecord().decode(buff, 0);

        Assert.AreEqual(333, m_MsgIn2.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(15, m_MsgIn2.getHeader().getRecord().getBitField1().getSeconds());
        Assert.AreEqual(49, m_MsgIn2.getHeader().getRecord().getBitField1().getMinutes());
        Assert.AreEqual(3, m_MsgIn2.getHeader().getRecord().getBitField1().getHour());
        Assert.AreEqual(21, m_MsgIn2.getHeader().getRecord().getBitField1().getDay());

        m_MsgIn2.getHeader().getRecord().getBitField1().setMilliseconds(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setSeconds(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setMinutes(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setHour(0);
        m_MsgIn2.getHeader().getRecord().getBitField1().setHour(0);

        m_MsgIn1.getHeader().getRecord().getBitField1().encode(buff, 0);
        m_MsgIn2.getHeader().getRecord().getBitField1().decode(buff, 0);

        Assert.AreEqual(333, m_MsgIn2.getHeader().getRecord().getBitField1().getMilliseconds());
        Assert.AreEqual(15, m_MsgIn2.getHeader().getRecord().getBitField1().getSeconds());
        Assert.AreEqual(49, m_MsgIn2.getHeader().getRecord().getBitField1().getMinutes());
        Assert.AreEqual(3, m_MsgIn2.getHeader().getRecord().getBitField1().getHour());
        Assert.AreEqual(21, m_MsgIn2.getHeader().getRecord().getBitField1().getDay());
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
