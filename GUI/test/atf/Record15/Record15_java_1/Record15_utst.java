
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
import src.urn_org_jts_test_FixedLengthString1_1_0.Messages.MsgIn;
import junit.textui.TestRunner;
import junit.framework.TestCase;

public class Record15_utst extends TestCase {

    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Record15_utst(String name) {
        super(name);
    }

    @Override
    protected void setUp() {
        m_MsgIn1 = new MsgIn();
        m_MsgIn2 = new MsgIn();
    }

    public void testConstructionDefaults() {
        System.out.println("");
        System.out.print("[executing test (testConstructionDefaults)]:");

        // verifying MsgIn constructs with correct default MessageID"
        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        assertEquals(1, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying MsgIn constructs with correct default DimensionSize"
        assertEquals(100, m_MsgIn1.getBody().getRecord15().getNames().getXSize());
        assertEquals(10, m_MsgIn1.getBody().getRecord15().getNames().getYSize());

        assertEquals(100, m_MsgIn2.getBody().getRecord15().getNames().getXSize());
        assertEquals(10, m_MsgIn2.getBody().getRecord15().getNames().getYSize());

    }

    public void testSetGetOperations() {
        System.out.println("");
        System.out.print("[executing test (testSetGetOperations)(TP_3.3.5.1)]: ");

        // verifying setMessageID/getMessageID reciprocity"
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(5);
        assertEquals(5, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(1);
        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying set/getName reciprocity"    
        int i, j;

        for (i = 0; i < m_MsgIn1.getBody().getRecord15().getNames().getXSize(); i++) {
            for (j = 0; j < m_MsgIn1.getBody().getRecord15().getNames().getYSize(); j++) {
                m_MsgIn1.getBody().getRecord15().getNames().setName(i, j,
                        String.format("m_MsgIn1(%d,%d)", i, j));
                m_MsgIn2.getBody().getRecord15().getNames().setName(i, j,
                        String.format("m_MsgIn2(%d,%d)", i, j));
            }
        }

        int r;
        for (i = 0; i < m_MsgIn1.getBody().getRecord15().getNames().getXSize(); i++) {
            for (j = 0; j < m_MsgIn1.getBody().getRecord15().getNames().getYSize(); j++) {
                assertEquals(String.format("m_MsgIn1(%d,%d)", i, j),
                        m_MsgIn1.getBody().getRecord15().getNames().getName(i, j));

                assertEquals(String.format("m_MsgIn2(%d,%d)", i, j),
                        m_MsgIn2.getBody().getRecord15().getNames().getName(i, j));
            }
        }

    }

    private String buildEncodeTestString(short dataValue, int stringLength) {
        String resultString = "";

        for (int i = 0; i < stringLength; ++i) {
            resultString += (char) dataValue;
        }

        return resultString;
    }

    public void testEncodeToSpec() {
        ByteBuffer buff = ByteBuffer.allocate(90000);
        int i, j;
        short val = 0;
        String stringData = "";

        System.out.println("");
        System.out.print("[executing test (testEncodeToSpec)(TP_3.3.5.2)]: ");
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);
        val = 2;
        // set the first 200 strings in the array's encoding.
        for (j = 0; j < 2; ++j) {
            for (i = 0; i < 100; ++i) {
                stringData = buildEncodeTestString(val, 80);
                m_MsgIn1.getBody().getRecord15().getNames().setName(i, j, stringData);
                val++;
            }
        }

        // verifying message level encode is AS-5684 compliant"
        m_MsgIn1.encode(buff, 0);
        assertEquals(0, buff.get(0));
        assertEquals(1, buff.get(1));
        val = 2;
        i = 2;

        // loop through first 200 80-character strings in the array's encoding.
        while (i < (200 * 80) + 2) {
            for (j = i; j < i + 80; j++) {
                // treat value in the buffer as an unsigned byte.
                assertEquals(val, (short)(buff.get(j) & 0xff));
            }
            val++;
            i += 80;
        }
    }

    public void testEncodeDecodeOperations() {
        ByteBuffer buff = ByteBuffer.allocate(90000);
        int i, j;

        System.out.println("");
        System.out.print("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]: ");

        // verifying message level encode/decode reciprocity"

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);

        for (i = 0; i < m_MsgIn1.getBody().getRecord15().getNames().getXSize(); i++) {
            for (j = 0; j < m_MsgIn1.getBody().getRecord15().getNames().getYSize(); j++) {
                m_MsgIn1.getBody().getRecord15().getNames().setName(i, j,
                        String.format("m_MsgIn1(%d,%d)", i, j));
                m_MsgIn2.getBody().getRecord15().getNames().setName(i, j,
                        String.format("m_MsgIn2(%d,%d)", i, j));
            }
        }
        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++) {
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++) {
                assertEquals(String.format("m_MsgIn1(%d,%d)", i, j),
                        m_MsgIn2.getBody().getRecord15().getNames().getName(i, j));
            }
        }

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

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++) {
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++) {
                m_MsgIn2.getBody().getRecord15().getNames().setName(i, j,
                        String.format("m_MsgIn2(%d,%d)", i, j));
            }
        }

        m_MsgIn1.getBody().encode(buff, 0);
        m_MsgIn2.getBody().decode(buff, 0);

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++) {
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++) {
                assertEquals(String.format("m_MsgIn1(%d,%d)", i, j),
                        m_MsgIn2.getBody().getRecord15().getNames().getName(i, j));
            }
        }

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++) {
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++) {
                m_MsgIn2.getBody().getRecord15().getNames().setName(i, j,
                        String.format("m_MsgIn2(%d,%d)", i, j));
            }
        }

        m_MsgIn1.getBody().getRecord15().encode(buff, 0);
        m_MsgIn2.getBody().getRecord15().decode(buff, 0);

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++) {
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++) {
                assertEquals(String.format("m_MsgIn1(%d,%d)", i, j),
                        m_MsgIn2.getBody().getRecord15().getNames().getName(i, j));
            }
        }

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++) {
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++) {
                m_MsgIn2.getBody().getRecord15().getNames().setName(i, j,
                        String.format("m_MsgIn2(%d,%d)", i, j));
            }
        }

        m_MsgIn1.getBody().getRecord15().getNames().encode(buff, 0);
        m_MsgIn2.getBody().getRecord15().getNames().decode(buff, 0);

        for (i = 0; i < m_MsgIn2.getBody().getRecord15().getNames().getXSize(); i++) {
            for (j = 0; j < m_MsgIn2.getBody().getRecord15().getNames().getYSize(); j++) {
                assertEquals(String.format("m_MsgIn1(%d,%d)", i, j),
                        m_MsgIn2.getBody().getRecord15().getNames().getName(i, j));
            }
        }

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
        System.out.println("[executing test (testInequality)]: ");
        // verifying message inequality operator"    
        assertFalse(m_MsgIn1.notEquals(m_MsgIn2));
        assertFalse(m_MsgIn2.notEquals(m_MsgIn1));
    }

    public static void main(String[] args) {
        System.out.println("Executing suite JTSRecord15MessageTest");
        TestRunner.run(Record15_utst.class);
        System.out.println("Completed suite JTSRecord15MessageTest");
        System.out.println("------------------------------------------------");
    }
}
