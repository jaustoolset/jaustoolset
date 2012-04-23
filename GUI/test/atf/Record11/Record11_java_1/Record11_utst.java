
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
import src.urn_org_jts_test_VariableLengthString1_1_0.Messages.MsgIn;
import junit.textui.TestRunner;
import junit.framework.TestCase;

public class Record11_utst extends TestCase {

    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Record11_utst(String name) {
        super(name);
    }

    @Override
    protected void setUp() {
        m_MsgIn1 = new MsgIn();
        m_MsgIn2 = new MsgIn();
    }

    public void testConstructionDefaults() {
        System.out.println("[executing test (testConstructionDefaults)]");

        // verifying MsgIn constructs with correct default MessageID"
        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        assertEquals(1, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

    }

    public void testSetGetOperations() {
        String S1;
        String S2;
        String S3;
        S1 = S2 = S3 = "";

        int i;

        System.out.println("");
        System.out.print("[executing test (testSetGetOperations)(TP_3.3.5.1)]: ");

        // verifying setMessageID/getMessageID reciprocity"
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(5);
        assertEquals(5, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(1);
        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying set/getVarLenStr reciprocity"  

        for (i = 0; i < 500; i++) {
            S1 += "1234567890";
        }
        for (i = 0; i < 200; i++) {
            S2 += "ABCDEFGHIJKLMNOPQRSTUVWXY";
        }
        m_MsgIn1.getBody().getRecord11().setVariableLengthString1(S1);
        m_MsgIn2.getBody().getRecord11().setVariableLengthString1(S2);

        assertEquals(S1, m_MsgIn1.getBody().getRecord11().getVariableLengthString1());
        assertEquals(S2, m_MsgIn2.getBody().getRecord11().getVariableLengthString1());

        // verifying enforcement of maximum length"
        S3 = S1 + 'e';
        m_MsgIn1.getBody().getRecord11().setVariableLengthString1(S3);

        assertEquals(false, m_MsgIn1.getBody().getRecord11().getVariableLengthString1().compareTo(S3) == 0);
        assertEquals(S1, m_MsgIn1.getBody().getRecord11().getVariableLengthString1());

        m_MsgIn1.getBody().getRecord11().setVariableLengthString1(S1);
    }

    public void testEncodeToSpec() {
        System.out.println("");
        System.out.print("[executing test (testEncodeToSpec)(TP_3.3.5.1)]: ");

        ByteBuffer buff = ByteBuffer.allocate(8192);

        char c = 0;

        String S = "";

        int i;

        for (i = 0; i < 770; i++) {
            c = (char) ((i + 4) % 256);
            if (c != 0) {
                S += c;
            } else {
                S += (char) (1);
            }
        }

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);
        m_MsgIn1.getBody().getRecord11().setVariableLengthString1(S);

        // verifying message level encode is AS-5684 compliant"
        m_MsgIn1.encode(buff, 0);
        assertEquals(774, m_MsgIn1.getSize());

        for (i = 0; i < m_MsgIn1.getSize(); i++) {
            c = (char) (i % 256);

            if ((i > 10) && (c == 0)) {
                c = 1;
            }
            assertEquals(c, (char)(buff.get(i)&0xff));
        }
    }

    public void testEncodeDecodeOperations() {
        ByteBuffer buff = ByteBuffer.allocate(4096);

        String S1 = "This string belongs to m_MsgIn1";
        String S2 = "blah!  blah!   blah-blah-blah-BLAAAAH---blah-blah-----blah-blah!";

        System.out.println("");
        System.out.print("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]:");

        // verifying message level encode/decode reciprocity"

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
        m_MsgIn1.getBody().getRecord11().setVariableLengthString1(S1);

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(127);
        m_MsgIn2.getBody().getRecord11().setVariableLengthString1(S2);

        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        assertEquals(S1, m_MsgIn2.getBody().getRecord11().getVariableLengthString1());

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

        m_MsgIn2.getBody().getRecord11().setVariableLengthString1(S2);

        m_MsgIn1.getBody().encode(buff, 0);
        m_MsgIn2.getBody().decode(buff, 0);

        assertEquals(S1, m_MsgIn2.getBody().getRecord11().getVariableLengthString1());

        m_MsgIn2.getBody().getRecord11().setVariableLengthString1(S2);

        m_MsgIn1.getBody().getRecord11().encode(buff, 0);
        m_MsgIn2.getBody().getRecord11().decode(buff, 0);

        assertEquals(S1, m_MsgIn2.getBody().getRecord11().getVariableLengthString1());
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
        System.out.println("Executing suite JTSRecord11MessageTest");
        TestRunner.run(Record11_utst.class);
        System.out.println("Completed suite JTSRecord11MessageTest");
        System.out.println("------------------------------------------------");
    }
}
