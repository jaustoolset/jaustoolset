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
using System.IO;
using NUnit.Framework;
using urn_org_jts_test_Client1_1_0;

[TestFixture]
public class References1_utst
{

    ServerInputMessage1 m_MsgIn1;
    ServerOutputMessage1 m_MsgOut1;
    ServerInputMessage2 m_MsgIn2;
    ServerOutputMessage2 m_MsgOut2;
    ParentInputMessage1 m_MsgIn3;
    ParentOutputMessage1 m_MsgOut3;
    IntermediaryInputMessage1 m_MsgIn4;
    IntermediaryOutputMessage1 m_MsgOut4;
    ServerParentInputMessage1 m_MsgIn5;
    ServerParentOutputMessage1 m_MsgOut5;
    ParentServerInputMessage1 m_MsgIn6;
    ParentServerOutputMessage1 m_MsgOut6;

    ParentEvent1 m_Event1;

    public References1_utst()
    {
    }

    [SetUp]
    public void setUp()
    {
        m_MsgIn1 = new ServerInputMessage1();
        m_MsgOut1 = new ServerOutputMessage1();
        m_MsgIn2 = new ServerInputMessage2();
        m_MsgOut2 = new ServerOutputMessage2();
        m_MsgIn3 = new ParentInputMessage1();
        m_MsgOut3 = new ParentOutputMessage1();
        m_MsgIn4 = new IntermediaryInputMessage1();
        m_MsgOut4 = new IntermediaryOutputMessage1();
        m_MsgIn5 = new ServerParentInputMessage1();
        m_MsgOut5 = new ServerParentOutputMessage1();
        m_MsgIn6 = new ParentServerInputMessage1();
        m_MsgOut6 = new ParentServerOutputMessage1();
        m_Event1 = new ParentEvent1();
    }


    [Test]
    public void testSimpleClientOf()
    {
        Console.WriteLine("[executing test (testSimpleClientOf)]");

        // verifying MsgIn1 constructs with correct default MessageID"
        Assert.AreEqual(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying MsgOut1 constructs with correct default MessageID"
        Assert.AreEqual(2, m_MsgOut1.getHeader().getHeaderRec().getMessageID());

        // verifying MsgIn2 constructs with correct default MessageID"
        Assert.AreEqual(3, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying MsgOut2 constructs with correct default MessageID"
        Assert.AreEqual(4, m_MsgOut2.getHeader().getHeaderRec().getMessageID());
    }

    [Test]
    public void testSimpleInheritence()
    {
        Console.WriteLine("[executing test (testSimpleInheritence)]");

        // verifying MsgIn3 constructs with correct default MessageID"
        Assert.AreEqual(5, m_MsgIn3.getHeader().getHeaderRec().getMessageID());

        // verifying MsgOut3 constructs with correct default MessageID"
        Assert.AreEqual(6, m_MsgOut3.getHeader().getHeaderRec().getMessageID());

        // verifying MsgIn4 constructs with correct default MessageID"
        Assert.AreEqual(7, m_MsgIn4.getHeader().getHeaderRec().getMessageID());

        // verifying MsgOut4 constructs with correct default MessageID"
        Assert.AreEqual(8, m_MsgOut4.getHeader().getHeaderRec().getMessageID());

        // verifying m_Event1 constructs with correct size"
        Assert.AreEqual(0, m_Event1.getSize());
    }

    [Test]
    public void testComplexCases()
    {
        Console.WriteLine("[executing test (testComplexCases)]");

        // verifying MsgIn5 constructs with correct default MessageID"
        Assert.AreEqual(16, m_MsgIn5.getHeader().getHeaderRec().getMessageID());

        // verifying MsgOut5 constructs with correct default MessageID"
        Assert.AreEqual(17, m_MsgOut5.getHeader().getHeaderRec().getMessageID());

        // verifying MsgIn6 constructs with correct default MessageID"
        Assert.AreEqual(18, m_MsgIn6.getHeader().getHeaderRec().getMessageID());

        // verifying MsgOut6 constructs with correct default MessageID"
        Assert.AreEqual(19, m_MsgOut6.getHeader().getHeaderRec().getMessageID());
    }

    //
    // Helper function returns TRUE is the <filename> is found anywhere in <path>
    //
    bool checkDirForFile(string path, string filename)
    {
        return File.Exists(Path.Combine(path, filename));
    }

    [Test]
    public void testNegativeCases()
    {
        Console.WriteLine("[executing test (testNegativeCases)]");

        // This is a little tricky.  We need to confirm that various "hidden" messages and
        // events were not generated (to ensure that when A is a client-of B and B is a client-of C
        // that the vocabulary of C is not automatically added to A).  Note that we therefore
        // need to verify that header files are missing or absent from the service without
        // generating compiler errors that would prevent the whole test from failing.  I'm going
        // to parse the directory for the hidden header files, but I'm open to alternatives
        // if anyone has ideas...

        // verifying HiddenInputMessage1 not resolved "
        Assert.AreEqual(false, checkDirForFile("src/urn_org_jts_test_Client1_1_0/Messages", "HiddenInputMessage1.cs"));

        // verifying HiddenOutputMessage1 not resolved "
        Assert.AreEqual(false, checkDirForFile("src/urn_org_jts_test_Client1_1_0/Messages", "HiddenOutputMessage1.cs"));

        // verifying HiddenEvent1 not resolved "
        Assert.AreEqual(false, checkDirForFile("src/urn_org_jts_test_Client1_1_0/InternalEvents", "HiddenEvent1.cs"));

        // verifying HiddenParentServerEvent1 not resolved "
        Assert.AreEqual(false, checkDirForFile("src/urn_org_jts_test_Client1_1_0/InternalEvents", "HiddenParentServerEvent1.cs"));

    }

    public static void Main()
    {
    }
}
