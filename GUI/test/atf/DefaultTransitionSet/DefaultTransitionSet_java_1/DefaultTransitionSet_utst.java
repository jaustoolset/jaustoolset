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

package src.urn_DeVivo_jaus_services_DefaultTransDef_1_0;

import junit.textui.TestRunner;
import junit.framework.TestCase;
import statemap.TransitionUndefinedException;


public class DefaultTransitionSet_utst extends TestCase {

    class TestFlagsClass {

        public byte LastState;
        public byte[] StateHistory = new byte[255];
    };

    class DefaultTransitionStateMachineImpl extends DefaultTransDef_DefaultTransitionStateMachine {

        DefaultTransitionStateMachineImpl() {
            TFC = new TestFlagsClass();
        }

        /// Action Methods
        public void EnteredReadyStateAction() {
            TFC.StateHistory[TFC.LastState++] = 0;
        }

        public void DefaultTransitionAction() {
            TFC.StateHistory[TFC.LastState++] = 1;
        }

        /// Guard Methods
        public boolean isReady() {
            TFC.StateHistory[TFC.LastState++] = 2;
            return true;
        }

        // user-variables
        public TestFlagsClass TFC;
    };

    DefaultTransitionStateMachineImpl fsm;

    public DefaultTransitionSet_utst(String name) {
        super(name);

    }
    protected void setUp() {
        // Create the FSM and call the entry action manually
        fsm = new DefaultTransitionStateMachineImpl();

        // reset state history
        for (int i = 0; i < 255; i++)
            fsm.TFC.StateHistory[i] = (byte) 255;
        fsm.TFC.LastState = 0;

        // Manually tickle the entry function
        fsm.EnteredReadyStateAction();

        // Run through the desired transition sequence
        try {
            fsm.context.InputMessage1Transition();
        } catch (TransitionUndefinedException e) {
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
        try {
            fsm.context.InputMessage2Transition();
        } catch (TransitionUndefinedException e) {
            fsm.TFC.StateHistory[fsm.TFC.LastState++] = 12;
        }
    }

    public void testProtocol() {
        int i;
        System.out.println(String.format("fsm.TFC.LastState, %d\n", fsm.TFC.LastState));
        for (i = 0; i < fsm.TFC.LastState + 1; i++) {
            System.out.println(String.format("fsm.TFC.StateHistory[%d], %d\n", i, fsm.TFC.StateHistory[i]));
        }
        assertEquals(0, fsm.TFC.StateHistory[0]);
        assertEquals(2, fsm.TFC.StateHistory[1]);
        assertEquals(1, fsm.TFC.StateHistory[2]);
        assertEquals(2, fsm.TFC.StateHistory[3]);
        assertEquals(1, fsm.TFC.StateHistory[4]);
    }

    public static void main(String[] args) {
        System.out.println("Executing suite JTSDefaultTransitionTest");
        TestRunner.run(DefaultTransitionSet_utst.class);
        System.out.println("Completed suite JTSDefaultTransitionTest");
        System.out.println("------------------------------------------------");
    }
}

