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
using System.Threading;
using NUnit.Framework;
using urn_org_jts_test_Child_1_0;

[TestFixture]
public class Inheritence1_utst
{
    public class TestComponent : Inheritence1_csharp
    {
        public TestComponent(ushort subsystem, byte node, byte component):
			base(subsystem, node, component)
		{
		}
		public String getState(int layer)		{		    if (layer == 0) return ((urn_org_jts_test_Parent_1_0.ParentService) serviceList[layer]).pParent_Parent1FSM.context.State.Name;			if (layer == 1) return ((urn_org_jts_test_Intermediary_1_0.IntermediaryService) serviceList[layer]).pIntermediary_Parent1FSM.context.State.Name;			if (layer == 2) return ((urn_org_jts_test_Child_1_0.ChildService) serviceList[layer]).pChild_Parent1FSM.context.State.Name;			return "";		}

    }

	protected void sendMessage( JTS.Message msg, Inheritence1_csharp dest )	{		// First encode the message		uint bufsize = (uint) msg.getSize();
		byte[] buffer = new byte[bufsize];
		int pos = 0;
		msg.encode(buffer, pos);
	     // To inject a message directly into the component, we need		 // to wrap it in a receive event and spoof the source.		 JTS.Receive ie = new JTS.Receive();		 ie.getBody().getReceiveRec().getMessagePayload().set( bufsize, buffer );		 ie.getBody().getReceiveRec().setSrcSubsystemID(160);		 ie.getBody().getReceiveRec().setSrcNodeID(1);		 ie.getBody().getReceiveRec().setSrcComponentID(1);		 // Now wedge the event into the component...		 dest.processInternalEvent( ie );		 // Sleep for a bit to let the event percolate through...		Thread.Sleep(500);    }

    public Inheritence1_utst()
    {
    }

    [SetUp]
    public void setUp()
    {
    }

    [Test]
    public void testInitState()
    {
        Console.WriteLine("Checking initial state...");

        TestComponent cmpt = new TestComponent(160, 1, 1);		cmpt.startComponent();

        Assert.AreEqual("Parent_Parent1FSM_SM.Top1", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top1_Intermediary1", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top1_Intermediary1", cmpt.getState(2));

		// and stop the component		cmpt.shutdownComponent();
    }

    [Test]
    public void parentTransitions()
    {
        Console.WriteLine("Checking transitions between parent states...");

        TestComponent cmpt = new TestComponent(160, 1, 1);		cmpt.startComponent();

        // Send it a message ParentInputMessage1 to transition
        sendMessage( new ParentInputMessage1(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top2", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top2_Intermediary1", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top2_Intermediary1", cmpt.getState(2));

        // Put it back using ParentInputMessage2
        sendMessage( new ParentInputMessage2(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top1", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top1_Intermediary1", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top1_Intermediary1", cmpt.getState(2));

		// and stop the component		cmpt.shutdownComponent();
    }

    [Test]
    public void intermediaryTransitions()
    {
        Console.WriteLine("Checking transitions between intermediary states...");

        TestComponent cmpt = new TestComponent(160, 1, 1);		cmpt.startComponent();

        // Send it a message ParentInputMessage1 to transition to Top2_Intermediary1
        sendMessage( new ParentInputMessage1(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top2", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top2_Intermediary1", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top2_Intermediary1", cmpt.getState(2));

        // Put it into Top2_Intermediary2
        sendMessage( new IntermediaryInputMessage1(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top2", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(2));

        // Put it back to the initial Top1_Intermediary1 state
        sendMessage( new ParentInputMessage2(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top1", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top1_Intermediary1", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top1_Intermediary1", cmpt.getState(2));

		// and stop the component		cmpt.shutdownComponent();
    }

    [Test]
    public void childTransitions()
    {
        Console.WriteLine("Checking transitions using default state...");

        TestComponent cmpt = new TestComponent(160, 1, 1);		cmpt.startComponent();

        // Send it a message ParentInputMessage1 to transition to Top2_Intermediary1
        sendMessage( new ParentInputMessage1(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top2", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top2_Intermediary1", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top2_Intermediary1", cmpt.getState(2));

        // Top2_Intemediary1 does not support pim1 message.  Verify that this tickles the default transition
        sendMessage( new ParentInputMessage1(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top2", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top2_Intermediary1", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top2_Intermediary1", cmpt.getState(2));

        // Lastly, the CIM3 message should transition back to the Top1_Intermediary1 state
        sendMessage( new ChildInputMessage3(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top1", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top1_Intermediary1", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top1_Intermediary1", cmpt.getState(2));

        // Send it a message ParentInputMessage1 to transition to Top2_Intermediary1
        sendMessage( new ParentInputMessage1(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top2", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top2_Intermediary1", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top2_Intermediary1", cmpt.getState(2));

        // Put it into Top2_Intermediary2
        sendMessage( new IntermediaryInputMessage1(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top2", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(2));

        // From Top2_Intermediary2, pim1 and cim3 are not supported.  both following
        // transitions should cause default transition to happen instead.
        sendMessage( new ParentInputMessage1(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top2", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(2));

        sendMessage( new ChildInputMessage3(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top2", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(2));

        // From Top2_Intermediary2, a default internal transition causes us to stay in the same state
        sendMessage( new ChildInputMessage2(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top2", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(2));

        // As of Drew's update Dec 4, a simple default transition returns to the original nested state 
        sendMessage( new ChildInputMessage1(), cmpt );
        Assert.AreEqual("Parent_Parent1FSM_SM.Top2", cmpt.getState(0));
		Assert.AreEqual("Intermediary_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(1));
		Assert.AreEqual("Child_Parent1FSM_SM.Top2_Intermediary2", cmpt.getState(2));

		// and stop the component		cmpt.shutdownComponent();
    }

    public static void Main()
    {
    }
}
