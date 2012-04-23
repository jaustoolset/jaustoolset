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
public class Loopback1_utst
{
    public class TestFlagsClass
    {
        public byte LastState;
        public byte[] StateHistory = new byte[255];
    };

    public class LoopbackStateMachnImpl : urn_DeVivo_jaus_services_LoopbackDef_1_0.LoopbackDef_LoopbackStateMachn
    {
        public LoopbackStateMachnImpl()
        {
            TFC = new TestFlagsClass();
        }

        /// Action Methods
        public override void EnteredReadyStateAction() { TFC.StateHistory[TFC.LastState++] = 1; }
        public override void ExitedReadyStateAction() { TFC.StateHistory[TFC.LastState++] = 2; }
        public override void FromReadyToReadyInternalAction() { TFC.StateHistory[TFC.LastState++] = 3; }
        public override void FromReadyToReadySimpleAction() { TFC.StateHistory[TFC.LastState++] = 4; }
        public override void FromStandbyToReadyAction() { TFC.StateHistory[TFC.LastState++] = 5; }


        /// Guard Methods
        public TestFlagsClass TFC;
    };

    LoopbackStateMachnImpl fsm;

    public Loopback1_utst()
    {
    }


    [SetUp]
    public void setUp()
    {
        // Create the FSM and call the entry action manually
        fsm = new LoopbackStateMachnImpl();

        fsm.TFC.LastState = 0;
        int i;
        for (i = 0; i < 255; i++)
        {
            fsm.TFC.StateHistory[i] = 255;
        }

        fsm.context.FromStandbyToReadyMsgTransition();
        fsm.context.FromReadyToReadySimpleMsgTransition();
        fsm.context.FromReadyToReadyInternalMsgTransition();
        fsm.context.FromReadyToReadySimpleMsgTransition();
        fsm.context.FromReadyToReadyInternalMsgTransition();

    }

    [Test]
    public void testProtocol()
    {
        int i;
        Console.WriteLine("fsm.TFC.LastState == " + fsm.TFC.LastState);
        for (i = 0; i < fsm.TFC.LastState + 2; i++)
        {
            Console.WriteLine("fsm.TFC.StateHistory[" + i + "] == " + fsm.TFC.StateHistory[i]);
        }
        Assert.AreEqual(5, fsm.TFC.StateHistory[0]);
        Assert.AreEqual(1, fsm.TFC.StateHistory[1]);
        Assert.AreEqual(2, fsm.TFC.StateHistory[2]);
        Assert.AreEqual(4, fsm.TFC.StateHistory[3]);
        Assert.AreEqual(1, fsm.TFC.StateHistory[4]);
        Assert.AreEqual(3, fsm.TFC.StateHistory[5]);
        Assert.AreEqual(2, fsm.TFC.StateHistory[6]);
        Assert.AreEqual(4, fsm.TFC.StateHistory[7]);
        Assert.AreEqual(1, fsm.TFC.StateHistory[8]);
        Assert.AreEqual(3, fsm.TFC.StateHistory[9]);
    }

    public static void Main()
    {
    }
}
