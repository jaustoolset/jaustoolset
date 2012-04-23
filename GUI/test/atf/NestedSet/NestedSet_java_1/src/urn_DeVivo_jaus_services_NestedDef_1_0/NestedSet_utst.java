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
package src.urn_DeVivo_jaus_services_NestedDef_1_0;

import junit.textui.TestRunner;
import junit.framework.TestCase;

public class NestedSet_utst extends TestCase {

    class TestFlagsClass {

        public byte LastState;
        public byte[] StateHistory = new byte[255];
    };

    class NestedStateMachnImpl extends NestedDef_NestedStateMachn {

        NestedStateMachnImpl() {
            TFC = new TestFlagsClass();
        }

        /// Action Methods
        public void EnteredStandbyStateAction() {
        }

        public void Entered_K1Action() {
            TFC.StateHistory[TFC.LastState++] = 1;
        }

        public void Entered_K2Action() {
            TFC.StateHistory[TFC.LastState++] = 2;
        }

        public void Entered_K3Action() {
            TFC.StateHistory[TFC.LastState++] = 3;
        }

        public void Entered_PAAction() {
        }

        public void Entered_PBAction() {
        }

        public void Entered_PCAction() {
            TFC.StateHistory[TFC.LastState++] = 4;
        }

        public void Exited_K1Action() {
            TFC.StateHistory[TFC.LastState++] = 5;
        }

        public void Exited_K2Action() {
            TFC.StateHistory[TFC.LastState++] = 6;
        }

        public void Exited_K3Action() {
            TFC.StateHistory[TFC.LastState++] = 7;
        }

        public void Exited_PAAction() {
        }

        public void Exited_PBAction() {
        }

        public void Exited_PCAction() {
            TFC.StateHistory[TFC.LastState++] = 8;
        }

        public void FromK1ToK2_SimpleAction() {
            TFC.StateHistory[TFC.LastState++] = 9;
        }

        public void FromK1ToPC_SimpleAction() {
            TFC.StateHistory[TFC.LastState++] = 10;
        }

        public void FromK1_PopAction() {
            TFC.StateHistory[TFC.LastState++] = 11;
        }

        public void FromK2ToK3_SimpleAction() {
            TFC.StateHistory[TFC.LastState++] = 12;
        }

        public void FromK2ToPA_PushAction() {
            TFC.StateHistory[TFC.LastState++] = 13;
        }

        public void FromK3ToK1_PushAction() {
            TFC.StateHistory[TFC.LastState++] = 14;
        }

        public void FromK3ToPC_SimpleAction() {
            TFC.StateHistory[TFC.LastState++] = 15;
        }

        public void FromPCToK1_PushAction() {
            TFC.StateHistory[TFC.LastState++] = 16;
        }

        public void FromPCToK1_SimpleAction() {
            TFC.StateHistory[TFC.LastState++] = 17;
        }

        public void FromPC_PopAction() {
            TFC.StateHistory[TFC.LastState++] = 18;
        }

        public void FromStandbyToPAAction() {
            TFC.StateHistory[TFC.LastState++] = 19;
        }
        // user-variables
        TestFlagsClass TFC;
    }

    private NestedStateMachnImpl fsm;

    public NestedSet_utst(String name) {
        super(name);
        // Create the FSM and call the entry action manually
        fsm = new NestedStateMachnImpl();
        fsm.EnteredStandbyStateAction();
    }

    protected void setUp() {
        fsm.TFC.LastState = 0;
        for (int i = 0; i < 255; i++) {
            fsm.TFC.StateHistory[i] = (byte) 255;
        }

        // trigger the test transitions
        fsm.context.StandbyToPA_SimpleTransition();
        fsm.context.K1ToK2_SimpleTransition();
        fsm.context.K2ToK1_PushTransition();
        fsm.context.K1_PopTransition();
        fsm.context.K2ToK3_SimpleTransition();
        fsm.context.K3ToK1_PushTransition();
        fsm.context.K1_PopTransition();

    }

    protected void tearDown() {
    }

    public void testProtocol() {
        int i;

        System.out.println("");
        System.out.print("Test message: ");

        System.out.println(String.format("[TFC.LastState, %d]\n", fsm.TFC.LastState));
        for (i = 0; i < fsm.TFC.LastState + 1; i++) {
            System.out.println(String.format("[TFC.StateHistory[%d], %d]\n", i, fsm.TFC.StateHistory[i]));
        }
        assertEquals((byte) 19, fsm.TFC.StateHistory[0]); //FromStandbyToPAAction
        assertEquals((byte) 1, fsm.TFC.StateHistory[1]);  //Entered_K1Action
        assertEquals((byte) 5, fsm.TFC.StateHistory[2]);  //Exited_K1Action
        assertEquals((byte) 9, fsm.TFC.StateHistory[3]);  //FromK1ToK2_SimpleAction
        assertEquals((byte) 2, fsm.TFC.StateHistory[4]);  //Entered_K2Action
        assertEquals((byte) 13, fsm.TFC.StateHistory[5]); //FromK2ToPA_PushAction
        assertEquals((byte) 1, fsm.TFC.StateHistory[6]);  //Entered_K1Action
        assertEquals((byte) 5, fsm.TFC.StateHistory[7]);  //Exited_K1Action
        assertEquals((byte) 11, fsm.TFC.StateHistory[8]); //FromK1_PopAction
        assertEquals((byte) 6, fsm.TFC.StateHistory[9]);  //Exited_K2Action
        assertEquals((byte) 12, fsm.TFC.StateHistory[10]); //FromK2ToK3_SimpleAction
        assertEquals((byte) 3, fsm.TFC.StateHistory[11]);  //Entered_K3Action
        assertEquals((byte) 14, fsm.TFC.StateHistory[12]); //FromK3ToK1_PushAction
        assertEquals((byte) 1, fsm.TFC.StateHistory[13]);  //Entered_K1Action
        assertEquals((byte) 5, fsm.TFC.StateHistory[14]);  //Exited_K1Action
        assertEquals((byte) 11, fsm.TFC.StateHistory[15]); //FromK1_PopAction
    }

    public static void main(String[] args) {
        System.out.println("Executing suite JTSNestedDefMessageTest 1");
        System.out.println("java.library.path: " + System.getProperty("java.library.path"));
        TestRunner.run(NestedSet_utst.class);
        System.out.println("Completed suite JTSNestedDefMessageTest 1");
        System.out.println("------------------------------------------------");
    }
}
