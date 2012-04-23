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
public class Record12_utst
{
    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Record12_utst()
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
        string S1 = "", S2 = "", S3 = "";
        int i = 0;

        Console.WriteLine("[executing test (testSetGetOperations)(TP_3.3.5.1)]");

        // verifying setMessageID/getMessageID reciprocity"
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(5);
        Assert.AreEqual(5, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(1);
        Assert.AreEqual(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying set/getVarLenStr reciprocity"  

        for (i = 0; i < 500; i++)
            S1 += "1234567890";
        for (i = 0; i < 200; i++)
            S2 += "ABCDEFGHIJKLMNOPQRSTUVWXY";
        m_MsgIn1.getBody().getRecord12().setVariableLengthString1(S1);
        m_MsgIn2.getBody().getRecord12().setVariableLengthString1(S2);

        Assert.AreEqual(S1, m_MsgIn1.getBody().getRecord12().getVariableLengthString1());
        Assert.AreEqual(S2, m_MsgIn2.getBody().getRecord12().getVariableLengthString1());

        // verifying enforcement of maximum length"
        S3 = S1;
        S3 += 'e';
        m_MsgIn1.getBody().getRecord12().setVariableLengthString1(S3);

        Assert.AreNotEqual(S3, m_MsgIn1.getBody().getRecord12().getVariableLengthString1());
        Assert.AreEqual(S1, m_MsgIn1.getBody().getRecord12().getVariableLengthString1());

        // verifying set/getField reciprocity"    

        m_MsgIn1.getBody().getRecord12().setField1(25);
        m_MsgIn2.getBody().getRecord12().setField1(-15);
        Assert.AreEqual(25, m_MsgIn1.getBody().getRecord12().getField1());
        Assert.AreEqual(-15, m_MsgIn2.getBody().getRecord12().getField1());

        // verifying set/getStrFld reciprocity"    
        m_MsgIn1.getBody().getRecord12().setStrFld("Hello");
        m_MsgIn2.getBody().getRecord12().setStrFld("World!");

        Assert.AreEqual("Hello", m_MsgIn1.getBody().getRecord12().getStrFld());
        Assert.AreEqual("World!", m_MsgIn2.getBody().getRecord12().getStrFld());
    }

    [Test]
    public void testEncodeToSpec()
    {
        byte[] buff = new byte[8192];
        byte c = 0;
        string S1 = "", S2 = "";
        int i = 0;

        for (i = 85; i < 168; i++)
        {
            c = (byte)i;
            S1 += (((char)c));
        }



        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);
        m_MsgIn1.getBody().getRecord12().setField1(2);

        for (i = 3; i < 83; i++)
            S2 += (char)i;
        m_MsgIn1.getBody().getRecord12().setStrFld(S2);

        m_MsgIn1.getBody().getRecord12().setVariableLengthString1(S1);

        // verifying message level encode is AS-5684 compliant"
        m_MsgIn1.encode(buff, 0);

        Assert.AreEqual(168, m_MsgIn1.getSize());


        for (i = 0; i < m_MsgIn1.getSize(); i++)
        {
            c = (byte)(i % 256);
            if (i == 84)
                c = (byte)0;
            Assert.AreEqual(c, (char)(buff[i] & 0xff));
        }
    }

    [Test]
    public void testEncodeDecodeOperations()
    {
        int i;
        byte[] buff = new byte[4096];

        string S1 = "This string belongs to m_MsgIn1";
        string S2 = "blah!  blah!   blah-blah-blah-BLAAAAH---blah-blah-----blah-blah!";

        Console.WriteLine("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]");

        // verifying message level encode/decode reciprocity"

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
        m_MsgIn1.getBody().getRecord12().setField1(77);
        m_MsgIn1.getBody().getRecord12().setStrFld("This is the value of m_MsgIn1 StrFld");
        m_MsgIn1.getBody().getRecord12().setVariableLengthString1(S1);

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(127);
        m_MsgIn2.getBody().getRecord12().setField1(127);
        m_MsgIn2.getBody().getRecord12().setStrFld("This is ***NOT*** the value of m_MsgIn1 StrFld");
        m_MsgIn2.getBody().getRecord12().setVariableLengthString1(S2);

        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        Assert.AreEqual(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        Assert.AreEqual(77, m_MsgIn2.getBody().getRecord12().getField1());
        Assert.AreEqual("This is the value of m_MsgIn1 StrFld", m_MsgIn2.getBody().getRecord12().getStrFld());
        Assert.AreEqual(S1, m_MsgIn2.getBody().getRecord12().getVariableLengthString1());

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

        m_MsgIn2.getBody().getRecord12().setField1(127);
        m_MsgIn2.getBody().getRecord12().setStrFld("This is ***NOT*** the value of m_MsgIn1 StrFld");
        m_MsgIn2.getBody().getRecord12().setVariableLengthString1(S2);

        m_MsgIn1.getBody().encode(buff, 0);
        m_MsgIn2.getBody().decode(buff, 0);

        Assert.AreEqual(77, m_MsgIn2.getBody().getRecord12().getField1());
        Assert.AreEqual("This is the value of m_MsgIn1 StrFld", m_MsgIn2.getBody().getRecord12().getStrFld());
        Assert.AreEqual(S1, m_MsgIn2.getBody().getRecord12().getVariableLengthString1());

        m_MsgIn2.getBody().getRecord12().setField1(127);
        m_MsgIn2.getBody().getRecord12().setStrFld("This is ***NOT*** the value of m_MsgIn1 StrFld");
        m_MsgIn2.getBody().getRecord12().setVariableLengthString1(S2);

        m_MsgIn1.getBody().getRecord12().encode(buff, 0);
        m_MsgIn2.getBody().getRecord12().decode(buff, 0);

        Assert.AreEqual(77, m_MsgIn2.getBody().getRecord12().getField1());
        Assert.AreEqual("This is the value of m_MsgIn1 StrFld", m_MsgIn2.getBody().getRecord12().getStrFld());
        Assert.AreEqual(S1, m_MsgIn2.getBody().getRecord12().getVariableLengthString1());
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
