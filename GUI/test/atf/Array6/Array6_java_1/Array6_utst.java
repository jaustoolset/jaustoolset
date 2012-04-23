
/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
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
import src.urn_org_jts_test_Array6_1_0.Messages.MsgIn;
import junit.textui.TestRunner;
import junit.framework.TestCase;

public class Array6_utst extends TestCase {

    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Array6_utst(String name) {
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
        System.out.println("executing test (testConstructionDefaults): ");

        // verifying MsgIn constructs with correct default MessageID" << std::endl;
        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        assertEquals(1, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying MsgIn constructs with correct default DimensionSize" << std::endl;
        assertEquals(9, m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size());
        assertEquals(9, m_MsgIn2.getFooter().getRecord().getArray6().getDimension1Size());
    }

    public void testSetGetOperations() {
        System.out.println("");
        System.out.print("executing test testSetGetOperations(TP_3.3.5.1): ");

        // verifying setMessageID/getMessageID reciprocity" << std::endl;
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(5);
        assertEquals(5, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(1);
        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying setField/getField reciprocity" << std::endl;
        int i;
        for (i = 0; i < m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size(); i++) {
            m_MsgIn1.getFooter().getRecord().getArray6().setField1(i, (byte)((i + 1) * 2));
            m_MsgIn2.getFooter().getRecord().getArray6().setField1(i, (byte)((i + 3) * 2));
        }

        for (i = 0; i < m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size(); i++) {
            assertEquals(((i + 1) * 2), m_MsgIn1.getFooter().getRecord().getArray6().getField1(i));
            assertEquals(((i + 3) * 2), m_MsgIn2.getFooter().getRecord().getArray6().getField1(i));
        }

    }

    public void testEncodeToSpec() {
        ByteBuffer buff = ByteBuffer.allocate(1024);
        int i;

        System.out.println("");
        System.out.print("executing test testEncodeToSpec(TP_3.3.5.2): ");

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);
        for (i = 0; i < m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size(); i++) {
            m_MsgIn1.getFooter().getRecord().getArray6().setField1(i, (byte)(i + 2));
        }

        // verifying message level encode is AS-5684 compliant" << std::endl;
        m_MsgIn1.encode(buff, 0);
        for (i = 0; i < m_MsgIn1.getSize(); i++) {
            assertEquals(i, buff.get(i));
        }

    }

    public void testEncodeDecodeOperations() {
        ByteBuffer buff = ByteBuffer.allocate(1024);
        int i;

        for (i = 0; i < 1024; i++) {
            buff.put(i, (byte) (((4101 * i) + 65543) % 256));
        }

        System.out.println();
        System.out.print("executing test testEncodeDecodeOperations (TP_3.3.5.3): ");

        // verifying message level encode/decode reciprocity" << std::endl;

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        for (i = 0; i < m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size(); i++) {
            m_MsgIn1.getFooter().getRecord().getArray6().setField1(i, (byte)((i + 1) * 7));
            m_MsgIn2.getFooter().getRecord().getArray6().setField1(i, (byte)0);
        }
        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        for (i = 0; i < m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size(); i++) {
            assertEquals(((i + 1) * 7), m_MsgIn2.getFooter().getRecord().getArray6().getField1(i));
        }

        // verifying header level encode/decode reciprocity" << std::endl;

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().encode(buff, 0);
        m_MsgIn2.getHeader().decode(buff, 0);
        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().getHeaderRec().encode(buff, 0);
        m_MsgIn2.getHeader().getHeaderRec().decode(buff, 0);
        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying record level encode/decode reciprocity" << std::endl;

        for (i = 0; i < m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size(); i++) {
            m_MsgIn2.getFooter().getRecord().getArray6().setField1(i, (byte)0);
        }

        m_MsgIn1.getFooter().encode(buff, 0);
        m_MsgIn2.getFooter().decode(buff, 0);
        for (i = 0; i < m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size(); i++) {
            assertEquals(((i + 1) * 7), m_MsgIn2.getFooter().getRecord().getArray6().getField1(i));
        }

        for (i = 0; i < m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size(); i++) {
            m_MsgIn2.getFooter().getRecord().getArray6().setField1(i, (byte)0);
        }

        m_MsgIn1.getFooter().getRecord().encode(buff, 0);
        m_MsgIn2.getFooter().getRecord().decode(buff, 0);
        for (i = 0; i < m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size(); i++) {
            assertEquals(((i + 1) * 7), m_MsgIn2.getFooter().getRecord().getArray6().getField1(i));
        }

        for (i = 0; i < m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size(); i++) {
            m_MsgIn2.getFooter().getRecord().getArray6().setField1(i, (byte)0);
        }

        m_MsgIn1.getFooter().getRecord().getArray6().encode(buff, 0);
        m_MsgIn2.getFooter().getRecord().getArray6().decode(buff, 0);
        for (i = 0; i < m_MsgIn1.getFooter().getRecord().getArray6().getDimension1Size(); i++) {
            assertEquals(((i + 1) * 7), m_MsgIn2.getFooter().getRecord().getArray6().getField1(i));
        }



    }

    public void testEquality() {
        System.out.println();
        System.out.print("executing test testEquality: ");
        // verifying message equality operator" << std::endl;
        assertTrue(m_MsgIn1.isEqual(m_MsgIn1));
        assertTrue(m_MsgIn1.isEqual(m_MsgIn2));
        assertTrue(m_MsgIn2.isEqual(m_MsgIn1));

    }

    public void testInequality() {
        System.out.println();
        System.out.print("executing test testInequality: ");
        // verifying message inequality operator" << std::endl;

        assertFalse(m_MsgIn1.notEquals(m_MsgIn2));
        assertFalse(m_MsgIn2.notEquals(m_MsgIn1));
    }

    public static void main(String[] args) {
        System.out.println("Executing suite JTSArray6MessageTest");
        TestRunner.run(Array6_utst.class);
        System.out.println("Completed suite JTSArray6MessageTest");
        System.out.println("------------------------------------------------");
    }
}
