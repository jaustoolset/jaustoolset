
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
import src.urn_org_jts_test_Array7_1_0.Messages.MsgIn;
import junit.textui.TestRunner;
import junit.framework.TestCase;

public class Array7_utst extends TestCase {

    private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

    public Array7_utst(String name) {
        super(name);
    }

    @Override
    protected void setUp() {
        m_MsgIn1 = new MsgIn();
        m_MsgIn2 = new MsgIn();
    }


    public void testConstructionDefaults() {
        System.out.println("executing test (testConstructionDefaults): ");

        // verifying MsgIn constructs with correct default MessageID
        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        assertEquals(1, m_MsgIn2.getHeader().getHeaderRec().getMessageID());

        // verifying MsgIn constructs with correct default DimensionSize
        assertEquals(9, m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size());
        assertEquals(9, m_MsgIn2.getBody().getRecord().getArray7().getDimension1Size());

        // verify strings in array field are initialized to a zero-length strings
        assertEquals("", m_MsgIn1.getBody().getRecord().getArray7().getVariableLengthString1(0));
        assertEquals("", m_MsgIn1.getBody().getRecord().getArray7().getVariableLengthString1(8));
        assertEquals("", m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(0));
        assertEquals("", m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(8));
    }

    public void testSetGetOperations() {
        System.out.println("");
        System.out.print("executing test testSetGetOperations(TP_3.3.5.1): ");

        // verifying setMessageID/getMessageID reciprocity
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(5);
        assertEquals(5, m_MsgIn1.getHeader().getHeaderRec().getMessageID());
        m_MsgIn1.getHeader().getHeaderRec().setMessageID(1);
        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());

        // verifying setField/getField reciprocity
        int i;
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++) {
            m_MsgIn1.getBody().getRecord().getArray7().setVariableLengthString1(i, "msg1 data");
            m_MsgIn2.getBody().getRecord().getArray7().setVariableLengthString1(i, "msg2 data");
        }

        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++) {
            assertEquals("msg1 data", m_MsgIn1.getBody().getRecord().getArray7().getVariableLengthString1(i));
            assertEquals("msg2 data", m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(i));
        }

    }

    /**
     * produce a string of length stringLength by concatenating stringLength instances of dataValue cast
     * to a char.
     * @param dataValue
     * @param stringLength
     * @return
     */
    private String buildEncodeTestString(int dataValue, int stringLength) {
        String resultString = "";

        for (int i = 0; i < stringLength; ++i) {
            resultString += (char) dataValue;
        }

        return resultString;
    }

    public void testEncodeToSpec() {
        ByteBuffer buff = ByteBuffer.allocate(1024);
        int i;


        System.out.println("");
        System.out.print("executing test testEncodeToSpec(TP_3.3.5.2): ");

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);

        // array's length should be 9.
        // populate the array with 9 strings; first is 'A', second is 'BB', up to 'IIIIIIIII' (nine 'I's)
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++) {
            m_MsgIn1.getBody().getRecord().getArray7().setVariableLengthString1(i, buildEncodeTestString(65+i, i+1));
        }

        // verifying message level encode is AS-5684 compliant
        m_MsgIn1.encode(buff, 0);

		System.out.println("");
        // offset 0 in buff is message ID, should be 256 (in an unsigned short)
        assertEquals(256, (int)(buff.getShort(0) & 0xffff));
		
        // offset 2 in buff is length of first string, should be 1 (in an unsigned short)
        //System.out.println("*** data @ 2: " + (int)(buff.getShort(2) & 0xffff));
        assertEquals(1, (int)(buff.getShort(2) & 0xffff));

        // offset 4 in buff is single 'A' of first string, single byte UTF-8 character
        assertEquals(65, (short)(buff.get(4) & 0xff));

        // offset 5 = length of 2nd string, 2 in unsigned short.
        assertEquals(2, (int)(buff.getShort(5) & 0xffff));

        // offset 7 = 1st 'B' in 2nd string, single byte UTF-8 character
        assertEquals(66, (short)(buff.get(7) & 0xff));

        // offset 8 = 2nd 'B' in 2nd string
        assertEquals(66, (short)(buff.get(8) & 0xff));

        // offset 9 = length of 3rd string, 3 in unsigned short.
        assertEquals(3, (int)(buff.getShort(9) & 0xffff));

        // offset 11 = 1st 'C' in 3rd string
        assertEquals(67, (short)(buff.get(11) & 0xff));

        //        12 = 2nd 'C' in 3rd string
        assertEquals(67, (short)(buff.get(12) & 0xff));

        //        13 = 3rd 'C' in 3rd string
        assertEquals(67, (short)(buff.get(13) & 0xff));

        // skipping to 9th string, should be 9 'I's, length value at offset 54
        assertEquals(9, (int)(buff.getShort(54) & 0xffff));

        //        56 = 1st 'I' in 9th string
        assertEquals(73, (short)(buff.get(56) & 0xff));

        //        57 = 5th 'I' in 9th string
        assertEquals(73, (short)(buff.get(57) & 0xff));

        //        64 = 9th 'I' in 9th string
        assertEquals(73, (short)(buff.get(64) & 0xff));

        //        65 = past end of message data, should be 0.
        assertEquals(0, (short)(buff.get(65) & 0xff));
    }

    public void testEncodeDecodeOperations() {
        ByteBuffer buff = ByteBuffer.allocate(4000);
        int i;

        System.out.println();
        System.out.print("executing test testEncodeDecodeOperations (TP_3.3.5.3): ");

        // verifying message level encode/decode reciprocity" << std::endl;

        m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++) {
            m_MsgIn1.getBody().getRecord().getArray7().setVariableLengthString1(i, Integer.toString(i));
            m_MsgIn2.getBody().getRecord().getArray7().setVariableLengthString1(i, Integer.toString(42));
        }
        m_MsgIn1.encode(buff, 0);
        m_MsgIn2.decode(buff, 0);

        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++) {
            assertEquals(Integer.toString(i), m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(i));
        }

        // verify encoded header decodes properly

        m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
        m_MsgIn1.getHeader().encode(buff, 0);
        m_MsgIn2.getHeader().decode(buff, 0);
        assertEquals(41, m_MsgIn2.getHeader().getHeaderRec().getMessageID());


        // verify encoded body decodes properly

        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++) {
            m_MsgIn2.getBody().getRecord().getArray7().setVariableLengthString1(i, "foo");
        }

        m_MsgIn1.getBody().encode(buff, 0);
        m_MsgIn2.getBody().decode(buff, 0);
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++) {
            assertEquals(Integer.toString(i),
                    m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(i));
        }

        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++) {
            m_MsgIn2.getBody().getRecord().getArray7().setVariableLengthString1(i, "foo");
        }

        m_MsgIn1.getBody().getRecord().encode(buff, 0);
        m_MsgIn2.getBody().getRecord().decode(buff, 0);
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++) {
            assertEquals(Integer.toString(i), m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(i));
        }

        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++) {
            m_MsgIn2.getBody().getRecord().getArray7().setVariableLengthString1(i, "foo");
        }

        m_MsgIn1.getBody().getRecord().getArray7().encode(buff, 0);
        m_MsgIn2.getBody().getRecord().getArray7().decode(buff, 0);
        for (i = 0; i < m_MsgIn1.getBody().getRecord().getArray7().getDimension1Size(); i++) {
            assertEquals(Integer.toString(i), m_MsgIn2.getBody().getRecord().getArray7().getVariableLengthString1(i));
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
        System.out.println("Executing suite JTSArray7MessageTest");
        TestRunner.run(Array7_utst.class);
        System.out.println("Completed suite JTSArray7MessageTest");
        System.out.println("------------------------------------------------");
    }
}
