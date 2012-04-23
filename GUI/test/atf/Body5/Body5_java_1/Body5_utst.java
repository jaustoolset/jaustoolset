
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
import java.nio.ByteBuffer;
import src.urn_org_jts_test_VariableLengthField1_1_0.Messages.MsgIn;
import junit.textui.TestRunner;
import junit.framework.TestCase;

public class Body5_utst extends TestCase {

    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Body5_utst(String name) {
        super(name);
    }

    protected void setUp() {
        m_MsgIn1 = new MsgIn();
        m_MsgIn2 = new MsgIn();
    }

    protected void tearDown() {
    }

    public void testConstructionDefaults() {
        System.out.println("");
        System.out.print("[executing test (testConstructionDefaults)]: ");

        // verifying MsgIn constructs with correct default MessageID"
        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        assertEquals(1, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

    }

    public void testSetGetOperations() {
        ByteBuffer buff1 = ByteBuffer.allocate(4096);
        ByteBuffer buff2 = ByteBuffer.allocate(4096);

        byte t;
        int i;

        System.out.println("");
        System.out.print("[executing test (testSetGetOperations)(TP_3.3.5.1)]: ");

        // verifying setMessageID/getMessageID reciprocity"
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(5);
        assertEquals(5, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(1);
        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying setField/getField reciprocity"    
        m_MsgIn1.getBody5().getRecord5().setField1((byte) 126);
        m_MsgIn2.getBody5().getRecord5().setField1((byte) -127);
        m_MsgIn1.getBody5().getRecord5().setField2(-40000);
        m_MsgIn2.getBody5().getRecord5().setField2(43210);
        m_MsgIn1.getBody5().getRecord5().setField3((float) 0.5);
        m_MsgIn2.getBody5().getRecord5().setField3((float) -32.125);
        m_MsgIn1.getBody5().getRecord5().setField4((short) 255);
        m_MsgIn2.getBody5().getRecord5().setField4((short) 0);

        assertEquals(126, m_MsgIn1.getBody5().getRecord5().getField1());
        assertEquals(-127, m_MsgIn2.getBody5().getRecord5().getField1());
        assertEquals(-40000, m_MsgIn1.getBody5().getRecord5().getField2());
        assertEquals(43210, m_MsgIn2.getBody5().getRecord5().getField2());
        assertEquals(0.5f, m_MsgIn1.getBody5().getRecord5().getField3(), 0.0001f);
        assertEquals(-32.125f, m_MsgIn2.getBody5().getRecord5().getField3(), 0.0001f);
        assertEquals(255, m_MsgIn1.getBody5().getRecord5().getField4());
        assertEquals(0, m_MsgIn2.getBody5().getRecord5().getField4());


        // verifying set/getData reciprocity and getLength consistency"   
        for (i = 0, t = 51; i < 4000; i++) {
            t = (byte) ((t * 71) + 13);
            buff1.put(i, t);
            buff2.put(i, t);
        }

        m_MsgIn1.getBody5().getRecord5().getVariableLengthField1().set(4000, buff1);
        m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().set(4000, buff2);

        assertEquals(4000, m_MsgIn1.getBody5().getRecord5().getVariableLengthField1().getLength());
        assertEquals(4000, m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().getLength());

        assertEquals(126, m_MsgIn1.getBody5().getRecord5().getField1());
        assertEquals(-127, m_MsgIn2.getBody5().getRecord5().getField1());
        assertEquals(-40000, m_MsgIn1.getBody5().getRecord5().getField2());
        assertEquals(43210, m_MsgIn2.getBody5().getRecord5().getField2());
        assertEquals(0.5f, m_MsgIn1.getBody5().getRecord5().getField3(), 0.0001f);
        assertEquals(-32.125f, m_MsgIn2.getBody5().getRecord5().getField3(), 0.0001f);
        assertEquals(255, m_MsgIn1.getBody5().getRecord5().getField4());
        assertEquals(0, m_MsgIn2.getBody5().getRecord5().getField4());


        for (i = 0; i < 4000; i++) {
            assertEquals(buff1.get(i), m_MsgIn1.getBody5().getRecord5().getVariableLengthField1().getData().get(i));
            assertEquals(buff2.get(i), m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().getData().get(i));
        }
    }

    public void testEncodeToSpec() {
        System.out.println("");
        System.out.print("[executing test (testEncodeToSpec)(TP_3.3.5.2)]: ");

        ByteBuffer buff = ByteBuffer.allocate(70000);
        int i;

        for (i = 0; i < 65786; i++) {
            buff.put(i, (byte) ((i + 6) % 256));
        }

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);
        m_MsgIn1.getBody5().getRecord5().getVariableLengthField1().set(65786, buff);
        m_MsgIn1.getBody5().getRecord5().setField1((byte) 0);
        m_MsgIn1.getBody5().getRecord5().setField2(67305985);  //4.06321607e-34
        m_MsgIn1.getBody5().getRecord5().setField3((float) .0000000000000000000000000000000004063216068939722949825023716001);
        m_MsgIn1.getBody5().getRecord5().setField4((short) 9);


        // verifying message level encode is AS-5684 compliant"
        m_MsgIn1.encode(buff, 0);

        for (i = 0; i < m_MsgIn1.getSize(); i++) { //FA 00 01 00
            if (i == 2) {
                assertEquals(250, (short)(buff.get(i)&0xff));
            } else if (i == 3) {
                assertEquals(0, (short)(buff.get(i) &0xff));
            } else if (i == 4) {
                assertEquals(1, (short) (buff.get(i) &0xff));
            } else if (i == 5) {
                assertEquals(0, (short) (buff.get(i) &0xff));
            } else {
                assertEquals((i % 256), (short)(buff.get(i)&0xff));
            }

        }
    }

    public void testEncodeDecodeOperations() {
        ByteBuffer buff = ByteBuffer.allocate(1024);
        ByteBuffer data = ByteBuffer.allocate(1000);
        int i;

        for (i = 0; i < 1024; i++) {
            buff.put(i, (byte) (((4101 * i) + 65543) % 256));
        }

        for (i = 0; i < 1000; i++) {
            data.put(i, (byte) (255 - i));
        }

        System.out.println("");
        System.out.print("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]: ");

        // verifying message level encode/decode reciprocity"

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
        m_MsgIn1.getBody5().getRecord5().getVariableLengthField1().set(1000, data);

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(127);
        m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().set(1011, buff);

        m_MsgIn1.getBody5().getRecord5().setField1((byte) 24);
        m_MsgIn1.getBody5().getRecord5().setField2(25);
        m_MsgIn1.getBody5().getRecord5().setField3((float) -16.75);
        m_MsgIn1.getBody5().getRecord5().setField4((short) 27);

        m_MsgIn2.getBody5().getRecord5().setField1((byte) 127);
        m_MsgIn2.getBody5().getRecord5().setField2(127);
        m_MsgIn2.getBody5().getRecord5().setField3((float) 127);
        m_MsgIn2.getBody5().getRecord5().setField4((short) 127);

        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);


        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        assertEquals(1000, m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().getLength());
        for (i = 0; i < 1000; i++) {
            assertEquals(data.get(i), m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().getData().get(i));
        }

        assertEquals(24, m_MsgIn2.getBody5().getRecord5().getField1());
        assertEquals(25, m_MsgIn2.getBody5().getRecord5().getField2());
        assertEquals(-16.75f, m_MsgIn2.getBody5().getRecord5().getField3(), 0.0001f);
        assertEquals(27, m_MsgIn2.getBody5().getRecord5().getField4());


        // verifying header level encode/decode reciprocity"

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().encode(buff, 0);
        m_MsgIn2.getHeader().decode(buff, 0);
        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().getHeaderRec().encode(buff, 0);
        m_MsgIn2.getHeader().getHeaderRec().decode(buff, 0);
        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying record level encode/decode reciprocity"

        for (i = 0; i < 1011; i++) {
            buff.put(i, (byte) 0);
        }
        m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().set(1011, buff);

        m_MsgIn2.getBody5().getRecord5().setField1((byte) 0);
        m_MsgIn2.getBody5().getRecord5().setField2(0);
        m_MsgIn2.getBody5().getRecord5().setField3((float) 0);
        m_MsgIn2.getBody5().getRecord5().setField4((short) 0);


        m_MsgIn1.getBody5().encode(buff, 0);
        m_MsgIn2.getBody5().decode(buff, 0);

        assertEquals(1000, m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().getLength());
        for (i = 0; i < 1000; i++) {
            assertEquals(data.get(i), m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().getData().get(i));
        }
        assertEquals(24, m_MsgIn2.getBody5().getRecord5().getField1());
        assertEquals(25, m_MsgIn2.getBody5().getRecord5().getField2());
        assertEquals(-16.75f, m_MsgIn2.getBody5().getRecord5().getField3(), 0.0001f);
        assertEquals(27, m_MsgIn2.getBody5().getRecord5().getField4());


        for (i = 0; i < 1011; i++) {
            buff.put(i, (byte) 0);
        }
        m_MsgIn2.getHeader().getHeaderRec().setMessageID(127);
        m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().set(1011, buff);
        m_MsgIn2.getBody5().getRecord5().setField1((byte) 0);
        m_MsgIn2.getBody5().getRecord5().setField2(0);
        m_MsgIn2.getBody5().getRecord5().setField3((float) 0);
        m_MsgIn2.getBody5().getRecord5().setField4((short) 0);

        m_MsgIn1.getBody5().getRecord5().encode(buff, 0);
        m_MsgIn2.getBody5().getRecord5().decode(buff, 0);

        assertEquals(m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().getLength(), 1000);
        for (i = 0; i < 1000; i++) {
            assertEquals(data.get(i), m_MsgIn2.getBody5().getRecord5().getVariableLengthField1().getData().get(i));
        }

        assertEquals(24, m_MsgIn2.getBody5().getRecord5().getField1());
        assertEquals(25, m_MsgIn2.getBody5().getRecord5().getField2());
        assertEquals(-16.75f, m_MsgIn2.getBody5().getRecord5().getField3(), 0.0001f);
        assertEquals(27, m_MsgIn2.getBody5().getRecord5().getField4());
    }

    public void testEquality() {
        System.out.println("");
        System.out.print("[executing test (testEquality)]: ");
        // verifying message equality operator"    
        assertTrue(m_MsgIn1.isEqual(m_MsgIn1));
        assertTrue(m_MsgIn1.isEqual(m_MsgIn2));
        assertTrue(m_MsgIn2.isEqual(m_MsgIn1));
    }

    public void testInequality() {
        System.out.println("");
        System.out.print("[executing test (testInequality)]: ");
        // verifying message inequality operator"    

        assertFalse(m_MsgIn1.notEquals(m_MsgIn2));
        assertFalse(m_MsgIn2.notEquals(m_MsgIn1));
    }

    public static void main(String[] args) {
        System.out.println("Executing suite JTSBody5MessageTest");
        TestRunner.run(Body5_utst.class);
        System.out.println("Completed suite JTSBody5MessageTest");
        System.out.println("------------------------------------------------");
    }
}
