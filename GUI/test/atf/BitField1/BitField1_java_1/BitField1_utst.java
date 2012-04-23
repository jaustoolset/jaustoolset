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
import src.urn_org_jts_test_BitField1_1_0.Messages.MsgIn;
import junit.textui.TestRunner;
import junit.framework.TestCase;

public class BitField1_utst extends TestCase {

    private MsgIn m_MsgIn1 = null;
    private MsgIn m_MsgIn2 = null;

    public BitField1_utst(String name) {
        super(name);
    }

    @Override
    protected void setUp() {
        m_MsgIn1 = new MsgIn();
        m_MsgIn2 = new MsgIn();
    }

    @Override
    protected void tearDown() {
    }

    public void testConstructionDefaults() {
        System.out.println();
        System.out.print("Executing test testConstructionDefaults: ");

        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        assertEquals(1, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
    }

    public void testSetGetOperations() {
        System.out.println();
        System.out.print("Executing test testSetGetOperations (TP_3.3.5.1): ");

        // verifying setMessageID/getMessageID reciprocity
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(5);
        assertEquals(5, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(1);
        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying setMilliseconds/getMilliseconds reciprocity"
        m_MsgIn1.getBody().getRecord().getBitField1().setMilliseconds(715);
        m_MsgIn2.getBody().getRecord().getBitField1().setMilliseconds(3);
        assertEquals(715, m_MsgIn1.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(3, m_MsgIn2.getBody().getRecord().getBitField1().getMilliseconds());

        // verifying setSeconds/getSeconds reciprocity"
        m_MsgIn1.getBody().getRecord().getBitField1().setSeconds(41);
        m_MsgIn2.getBody().getRecord().getBitField1().setSeconds(0);
        assertEquals(715, m_MsgIn1.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(3, m_MsgIn2.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(41, m_MsgIn1.getBody().getRecord().getBitField1().getSeconds());
        assertEquals(0, m_MsgIn2.getBody().getRecord().getBitField1().getSeconds());

        // verifying setMinutes/getMinutes reciprocity"
        m_MsgIn1.getBody().getRecord().getBitField1().setMinutes(12);
        m_MsgIn2.getBody().getRecord().getBitField1().setMinutes(59);
        assertEquals(715, m_MsgIn1.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(3, m_MsgIn2.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(41, m_MsgIn1.getBody().getRecord().getBitField1().getSeconds());
        assertEquals(0, m_MsgIn2.getBody().getRecord().getBitField1().getSeconds());
        assertEquals(12, m_MsgIn1.getBody().getRecord().getBitField1().getMinutes());
        assertEquals(59, m_MsgIn2.getBody().getRecord().getBitField1().getMinutes());

        // verifying setHour/getHour reciprocity"
        m_MsgIn1.getBody().getRecord().getBitField1().setHour(12);
        m_MsgIn2.getBody().getRecord().getBitField1().setHour(19);
        assertEquals(715, m_MsgIn1.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(3, m_MsgIn2.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(41, m_MsgIn1.getBody().getRecord().getBitField1().getSeconds());
        assertEquals(0, m_MsgIn2.getBody().getRecord().getBitField1().getSeconds());
        assertEquals(12, m_MsgIn1.getBody().getRecord().getBitField1().getMinutes());
        assertEquals(59, m_MsgIn2.getBody().getRecord().getBitField1().getMinutes());
        assertEquals(12, m_MsgIn1.getBody().getRecord().getBitField1().getHour());
        assertEquals(19, m_MsgIn2.getBody().getRecord().getBitField1().getHour());

        // verifying setDay/getDay reciprocity"
        m_MsgIn1.getBody().getRecord().getBitField1().setDay(1);
        m_MsgIn2.getBody().getRecord().getBitField1().setDay(31);
        assertEquals(715, m_MsgIn1.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(3, m_MsgIn2.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(41, m_MsgIn1.getBody().getRecord().getBitField1().getSeconds());
        assertEquals(0, m_MsgIn2.getBody().getRecord().getBitField1().getSeconds());
        assertEquals(12, m_MsgIn1.getBody().getRecord().getBitField1().getMinutes());
        assertEquals(59, m_MsgIn2.getBody().getRecord().getBitField1().getMinutes());
        assertEquals(12, m_MsgIn1.getBody().getRecord().getBitField1().getHour());
        assertEquals(19, m_MsgIn2.getBody().getRecord().getBitField1().getHour());
        assertEquals(1, m_MsgIn1.getBody().getRecord().getBitField1().getDay());
        assertEquals(31, m_MsgIn2.getBody().getRecord().getBitField1().getDay());
    }

    public void testEncodeToSpec() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int i;

        System.out.println();
        System.out.print("executing test testEncodeToSpec (TP_3.3.5.2): ");

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);

        m_MsgIn1.getBody().getRecord().getBitField1().setMilliseconds(770);
        m_MsgIn1.getBody().getRecord().getBitField1().setSeconds(0);
        m_MsgIn1.getBody().getRecord().getBitField1().setMinutes(4);
        m_MsgIn1.getBody().getRecord().getBitField1().setHour(20);
        m_MsgIn1.getBody().getRecord().getBitField1().setDay(1);



        // verifying message level encode is AS-5684 compliant"
        m_MsgIn1.encode(buffer, 0);

        for (i = 0; i < m_MsgIn1.getSize() - 1; i++) {
            assertEquals(i, buffer.get(i));

        }
        assertEquals(13, buffer.get((int) (m_MsgIn1.getSize() - 1)));
    }

    public void testEncodeDecodeOperations() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int i;

        for (i = 0; i < 1024; i++) {
            buffer.put(i, (byte) (((4101 * i) + 65543) % 256));
        }

        System.out.println();
        System.out.print("executing test testEncodeDecodeOperations (TP_3.3.5.3): ");

        // verifying message level encode/decode reciprocity"
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
        m_MsgIn1.getBody().getRecord().getBitField1().setMilliseconds(333);
        m_MsgIn1.getBody().getRecord().getBitField1().setSeconds(15);
        m_MsgIn1.getBody().getRecord().getBitField1().setMinutes(49);
        m_MsgIn1.getBody().getRecord().getBitField1().setHour(3);
        m_MsgIn1.getBody().getRecord().getBitField1().setDay(21);

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setMilliseconds(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setSeconds(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setMinutes(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setHour(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setHour(0);

        m_MsgIn1.encode(buffer, 0);
        m_MsgIn2.decode(buffer, 0);

        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        assertEquals(333, m_MsgIn2.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(15, m_MsgIn2.getBody().getRecord().getBitField1().getSeconds());
        assertEquals(49, m_MsgIn2.getBody().getRecord().getBitField1().getMinutes());
        assertEquals(3, m_MsgIn2.getBody().getRecord().getBitField1().getHour());
        assertEquals(21, m_MsgIn2.getBody().getRecord().getBitField1().getDay());

        // verifying header level encode/decode reciprocity"

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().encode(buffer, 0);
        m_MsgIn2.getHeader().decode(buffer, 0);
        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().getHeaderRec().encode(buffer, 0);
        m_MsgIn2.getHeader().getHeaderRec().decode(buffer, 0);
        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying record level encode/decode reciprocity"

        m_MsgIn2.getBody().getRecord().getBitField1().setMilliseconds(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setSeconds(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setMinutes(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setHour(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setHour(0);

        m_MsgIn1.getBody().encode(buffer, 0);
        m_MsgIn2.getBody().decode(buffer, 0);

        assertEquals(333, m_MsgIn2.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(15, m_MsgIn2.getBody().getRecord().getBitField1().getSeconds());
        assertEquals(49, m_MsgIn2.getBody().getRecord().getBitField1().getMinutes());
        assertEquals(3, m_MsgIn2.getBody().getRecord().getBitField1().getHour());
        assertEquals(21, m_MsgIn2.getBody().getRecord().getBitField1().getDay());

        m_MsgIn2.getBody().getRecord().getBitField1().setMilliseconds(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setSeconds(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setMinutes(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setHour(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setHour(0);

        m_MsgIn1.getBody().getRecord().encode(buffer, 0);
        m_MsgIn2.getBody().getRecord().decode(buffer, 0);

        assertEquals(333, m_MsgIn2.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(15, m_MsgIn2.getBody().getRecord().getBitField1().getSeconds());
        assertEquals(49, m_MsgIn2.getBody().getRecord().getBitField1().getMinutes());
        assertEquals(3, m_MsgIn2.getBody().getRecord().getBitField1().getHour());
        assertEquals(21, m_MsgIn2.getBody().getRecord().getBitField1().getDay());

        m_MsgIn2.getBody().getRecord().getBitField1().setMilliseconds(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setSeconds(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setMinutes(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setHour(0);
        m_MsgIn2.getBody().getRecord().getBitField1().setHour(0);

        m_MsgIn1.getBody().getRecord().getBitField1().encode(buffer, 0);
        m_MsgIn2.getBody().getRecord().getBitField1().decode(buffer, 0);

        assertEquals(333, m_MsgIn2.getBody().getRecord().getBitField1().getMilliseconds());
        assertEquals(15, m_MsgIn2.getBody().getRecord().getBitField1().getSeconds());
        assertEquals(49, m_MsgIn2.getBody().getRecord().getBitField1().getMinutes());
        assertEquals(3, m_MsgIn2.getBody().getRecord().getBitField1().getHour());
        assertEquals(21, m_MsgIn2.getBody().getRecord().getBitField1().getDay());
    }

    public void testEquality() {
        System.out.println();
        System.out.print("executing test testEquality: ");
        // verifying message equality operator"
        assertTrue(m_MsgIn1.isEqual(m_MsgIn1));
        assertTrue(m_MsgIn1.isEqual(m_MsgIn2));
        assertTrue(m_MsgIn2.isEqual(m_MsgIn1));
    }

    public void testInequality() {
        System.out.println();
        System.out.print("executing test testInequality: ");
        // verifying message inequality operator"
        assertFalse(m_MsgIn1.notEquals(m_MsgIn2));
        assertFalse(m_MsgIn2.notEquals(m_MsgIn1));
    }

    public static void main(String[] args) {
        System.out.println("Executing suite JTSBitField1MessageTest");
        TestRunner.run(BitField1_utst.class);
        System.out.println("Completed suite JTSBitField1MessageTest");
        System.out.println("------------------------------------------------");
    }
}
