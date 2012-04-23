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
using urn_DeVivo_jaus_services_DefaultTransDef_1_0;

[TestFixture]
public class DefaultTransitionSet_utst
{

    public class TestFlagsClass
    {

        public byte LastState;
        public byte[] StateHistory = new byte[255];
    };

    public class DefaultTransitionStateMachineImpl : DefaultTransDef_DefaultTransitionStateMachine
    {

        public DefaultTransitionStateMachineImpl()
        {
            TFC = new TestFlagsClass();
        }

        /// Action Methods
        public override void EnteredReadyStateAction()
        {
            TFC.StateHistory[TFC.LastState++] = 0;
        }

        public override void DefaultTransitionAction()
        {
            TFC.StateHistory[TFC.LastState++] = 1;
        }

        /// Guard Methods
        public override bool isReady()
        {
            TFC.StateHistory[TFC.LastState++] = 2;
            return true;
        }

        // user-variables
        public TestFlagsClass TFC;
    };

    DefaultTransitionStateMachineImpl fsm;

    public DefaultTransitionSet_utst()
    {
    }

    [SetUp]
    protected void setUp()
    {
        // Create the FSM and call the entry action manually
        fsm = new DefaultTransitionStateMachineImpl();

        // reset state history
        for (int i = 0; i < 255; i++)
            fsm.TFC.StateHistory[i] = (byte)255;
        fsm.TFC.LastState = 0;

        // Manually tickle the entry function
        fsm.EnteredReadyStateAction();

        // Run through the desired transition sequence
        try
        {
            fsm.context.InputMessage1Transition();
        }
        catch (statemap.TransitionUndefinedException e)
        {
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
        try
        {
            fsm.context.InputMessage2Transition();
        }
        catch (statemap.TransitionUndefinedException e)
        {
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
    }

    [Test]
    public void testProtocol()
    {
        int i;
        Console.Out.WriteLine("fsm.TFC.LastState == " + fsm.TFC.LastState);
        for (i = 0; i < fsm.TFC.LastState + 1; i++)
        {
            Console.Out.WriteLine("fsm.TFC.StateHistory[" + i + "] == " + fsm.TFC.StateHistory[i]);
        }
        Assert.AreEqual(0, fsm.TFC.StateHistory[0]);
        Assert.AreEqual(2, fsm.TFC.StateHistory[1]);
        Assert.AreEqual(1, fsm.TFC.StateHistory[2]);
        Assert.AreEqual(2, fsm.TFC.StateHistory[3]);
        Assert.AreEqual(1, fsm.TFC.StateHistory[4]);
    }

    public static void Main()
    {
    }
}

