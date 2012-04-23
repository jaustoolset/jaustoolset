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

[TestFixture]
public class SimpleSet_utst
{
    public class TestFlagsClass
    {
        public byte LastState;
        public byte[] StateHistory = new byte[255];
    };

    public class SimpleStateMachnImpl : urn_DeVivo_jaus_services_SimpleDef_1_0.SimpleDef_SimpleStateMachn
    {
        public SimpleStateMachnImpl()
        {
            TFC = new TestFlagsClass();
        }

        /// Action Methods
        public override void EnteredErrorStateAction() { TFC.StateHistory[TFC.LastState++] = 5; }
        public override void EnteredPausedStateAction() { TFC.StateHistory[TFC.LastState++] = 6; }
        public override void EnteredReadyState2Action() { TFC.StateHistory[TFC.LastState++] = 8; }
        public override void EnteredReadyStateAction() { TFC.StateHistory[TFC.LastState++] = 7; }
        public override void EnteredStandbyStateAction() { }
        public override void FromReadyToErrorAction() { TFC.StateHistory[TFC.LastState++] = 0; }
        public override void FromStandbyToReadyAction() { TFC.StateHistory[TFC.LastState++] = 1; }
        public override void PauseAction(string arg0) { TFC.StateHistory[TFC.LastState++] = 2; }
        public override void ReportStateAction() { TFC.StateHistory[TFC.LastState++] = 3; }
        public override void ResumeAction() { TFC.StateHistory[TFC.LastState++] = 4; }

        /// Guard Methods
        public override bool ReadyToErrorGuard() { TFC.StateHistory[TFC.LastState++] = 9; return false; }
        public override bool StandbyToReadyGuard() { TFC.StateHistory[TFC.LastState++] = 10; return true; }

        // user-variables
        public TestFlagsClass TFC;

    };

    SimpleStateMachnImpl fsm;

    public SimpleSet_utst()
    {

    }


    [SetUp]
    public void setUp()
    {
        // Create the FSM and call the entry action manually
        fsm = new SimpleStateMachnImpl();
        fsm.EnteredStandbyStateAction();

        fsm.TFC.LastState = 0;
        int i;
        for (i = 0; i < 255; i++)
            fsm.TFC.StateHistory[i] = 255;

        // Run through the desired transition sequence
        try { fsm.context.ReadyMessageTransition(); }
        catch (statemap.TransitionUndefinedException e)
        {
            Console.Out.WriteLine("exception during transition #1!");
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
        try { fsm.context.PauseMessageTransition(); }
        catch (statemap.TransitionUndefinedException e)
        {
            Console.Out.WriteLine("exception during transition #2!");
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
        try { fsm.context.PauseMessageTransition(); }
        catch (statemap.TransitionUndefinedException e)
        {
            Console.Out.WriteLine("exception during transition #3");
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
        try { fsm.context.ResumeMessageTransition(); }
        catch (statemap.TransitionUndefinedException e)
        {
            Console.Out.WriteLine("exception during transition #4");
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
        try { fsm.context.PauseMessageTransition(); }
        catch (statemap.TransitionUndefinedException e)
        {
            Console.Out.WriteLine("exception during transition #5");
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
        try { fsm.context.ResumeMessageTransition(); }
        catch (statemap.TransitionUndefinedException e)
        {
            Console.Out.WriteLine("exception during transition #6");
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
        try { fsm.context.ResumeMessageTransition(); }
        catch (statemap.TransitionUndefinedException e)
        {
            Console.Out.WriteLine("exception during transition #7");
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
        try { fsm.context.ErrorMessageTransition(); }
        catch (statemap.TransitionUndefinedException e)
        {
            Console.Out.WriteLine("exception during transition #8!");
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
        try { fsm.context.QueryStateMessageTransition(); }
        catch (statemap.TransitionUndefinedException e)
        {
            Console.Out.WriteLine("exception during transition #9");
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
    }

    [Test]
    public void testProtocol()
    {
        int i;
        Console.WriteLine("fsm.TFC.LastState == " + fsm.TFC.LastState);
        for (i = 0; i < fsm.TFC.LastState + 1; i++)
        {
            Console.WriteLine("fsm.TFC.StateHistory[" + i + "] == " + fsm.TFC.StateHistory[i]);
        }
        Assert.AreEqual(10, fsm.TFC.StateHistory[0]);
        Assert.AreEqual(1, fsm.TFC.StateHistory[1]);
        Assert.AreEqual(7, fsm.TFC.StateHistory[2]);
        Assert.AreEqual(2, fsm.TFC.StateHistory[3]);
        Assert.AreEqual(6, fsm.TFC.StateHistory[4]);
        Assert.AreEqual(2, fsm.TFC.StateHistory[5]);
        Assert.AreEqual(6, fsm.TFC.StateHistory[6]);
        Assert.AreEqual(4, fsm.TFC.StateHistory[7]);
        Assert.AreEqual(12, fsm.TFC.StateHistory[8]);
        Assert.AreEqual(2, fsm.TFC.StateHistory[9]);
        Assert.AreEqual(6, fsm.TFC.StateHistory[10]);
        Assert.AreEqual(4, fsm.TFC.StateHistory[11]);
        Assert.AreEqual(12, fsm.TFC.StateHistory[12]);
        Assert.AreEqual(4, fsm.TFC.StateHistory[13]);
        Assert.AreEqual(12, fsm.TFC.StateHistory[14]);
        Assert.AreEqual(9, fsm.TFC.StateHistory[15]);
        Assert.AreEqual(12, fsm.TFC.StateHistory[16]);
        Assert.AreEqual(3, fsm.TFC.StateHistory[17]);
        Assert.AreEqual(8, fsm.TFC.StateHistory[18]);
    }

    public static void Main()
    {
    }
}
