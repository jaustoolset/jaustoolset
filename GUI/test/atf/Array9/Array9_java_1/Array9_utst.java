
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
// test for message structure with <array> of <variable_length_field>s
import java.nio.ByteBuffer;
import src.urn_org_jts_test_Array9_1_0.Messages.MsgIn;
import junit.textui.TestRunner;
import junit.framework.TestCase;

public class Array9_utst extends TestCase {

    // assert if the contents of two byte buffers are equal, given a max length specifying the region to compare
    public void assertBufferContentsEqual(ByteBuffer data1, ByteBuffer data2, int length) {
        for (int i = 0; i < length; ++i) {
            assertEquals(data1.get(i), data2.get(i));
        }
    }

    // assert if the contents of two byte buffers are equal, given first offset into buff one and length following
    // that offset.  comparison is against contents of buff2 from offset zero to offset length-1.
    public void assertBufferSliceEqual(ByteBuffer data1, ByteBuffer data2, int first, int length) {
        for (int i = 0; i < length; ++i) {
            assertEquals(data1.get(first + i), data2.get(i));
        }
    }

    public Array9_utst(String name) {
        super(name);
    }
    private MsgIn msgIn1;
    private MsgIn msgIn2;

    @Override
    public void setUp() {
        msgIn1 = new MsgIn();
        msgIn2 = new MsgIn();
    }

    public void testConstructionDefaults() {
        System.out.println("\n  [executing test (testConstructionDefaults)] ");

        assertEquals(1, msgIn1.getHeader().getHeaderRec().getMessageID());
        assertEquals(1, msgIn2.getHeader().getHeaderRec().getMessageID());

        assertEquals(10, msgIn1.getBody().getRecord().getArray9().getIDXSize());
        assertEquals(10, msgIn2.getBody().getRecord().getArray9().getIDXSize());
    }

    public void testSetGetOperations() {
        System.out.println("\n  [executing test (testSetGetOperations)(TP_3.3.5.1)] ");

        msgIn1.getHeader().getHeaderRec().setMessageID(99);

        ByteBuffer[] data = new ByteBuffer[10];

        for (int i = 0; i < 10; ++i) {
            data[i] = ByteBuffer.allocate(1 + i);

            // after loop concludes, data_a[0] contains {10}, data_a[1] contains {10, 11}, data_a[2] {10, 11, 12} etc.
            data[i].put((byte) 10);

            for (int j = 1; j <= i; ++j) {
                data[i].put((byte) (10 + j));
            }
        }

        for (int i = 0; i < 10; ++i) {
            msgIn1.getBody().getRecord().getArray9().getVLF1(i).set(i+1, data[i]);
        }

        // check get results against what we set...
        assertEquals(99, msgIn1.getHeader().getHeaderRec().getMessageID());

        assertBufferContentsEqual(data[0], msgIn1.getBody().getRecord().getArray9().getVLF1(0).getData(), 1);
        assertBufferContentsEqual(data[1], msgIn1.getBody().getRecord().getArray9().getVLF1(1).getData(), 2);
        assertBufferContentsEqual(data[2], msgIn1.getBody().getRecord().getArray9().getVLF1(2).getData(), 3);
        assertBufferContentsEqual(data[3], msgIn1.getBody().getRecord().getArray9().getVLF1(3).getData(), 4);
        assertBufferContentsEqual(data[4], msgIn1.getBody().getRecord().getArray9().getVLF1(4).getData(), 5);
        assertBufferContentsEqual(data[5], msgIn1.getBody().getRecord().getArray9().getVLF1(5).getData(), 6);
        assertBufferContentsEqual(data[6], msgIn1.getBody().getRecord().getArray9().getVLF1(6).getData(), 7);
        assertBufferContentsEqual(data[7], msgIn1.getBody().getRecord().getArray9().getVLF1(7).getData(), 8);
        assertBufferContentsEqual(data[8], msgIn1.getBody().getRecord().getArray9().getVLF1(8).getData(), 9);
        assertBufferContentsEqual(data[9], msgIn1.getBody().getRecord().getArray9().getVLF1(9).getData(), 10);

        // check getSize() returns accurate value.
        assertEquals(2 + 10 * 4 + 55, msgIn1.getSize()); // 2-byte header, 10 4-byte count fields, 55 total data bytes.

        System.out.println("\n  [completed test (testSetGetOperations)] ");
    }

    public void testEncodeToSpec() {
        ByteBuffer buff = ByteBuffer.allocate(1024);
        int i;
        for (i = 0; i < 1024; i++) {
            buff.put(i, (byte) 0);
        }

        System.out.println("\n  [executing test (testEncodeToSpec)(TP_3.3.5.2)] ");

        msgIn1.getHeader().getHeaderRec().setMessageID(999);

        ByteBuffer[] data = new ByteBuffer[10];

        for (i = 0; i < 10; ++i) {
            data[i] = ByteBuffer.allocate(1 + i);

            // after loop concludes, data_a[0] contains {10}, data_a[1] contains {10, 11}, data_a[2] {10, 11, 12} etc.
            data[i].put((byte) 10);

            for (int j = 1; j <= i; ++j) {
                data[i].put((byte) (10 + j));
            }
        }

        for (i = 0; i < 10; ++i) {
            msgIn1.getBody().getRecord().getArray9().getVLF1(i).set(i+1, data[i]);
        }

        msgIn1.encode(buff, 0);

//        for (int k = 0; k < 100; ++k) {
//            System.out.println("buff[" + k + "] == " + buff.get(k));
//        }

        // offset 0:   unsigned short w/ message ID
        assertEquals(999, (int) (buff.getShort(0) & 0xFFFF));

        // offset 2:  unsigned int w/ count field = 1
        assertEquals(1, (long) (buff.getInt(2) & 0xFFFFFFFFL));

        // offset 6:  unsigned char = 10
        assertEquals(10, buff.get(6));

        // offset 7:  count field, 2nd VLF.
        assertEquals(2, (long) (buff.getInt(7) & 0xFFFFFFFFL));

        // offset 11: unsigned char from 2nd VLF
        assertEquals(10, buff.get(11));

        // offset 12: unsigned char from 2nd VLF
        assertEquals(11, buff.get(12));

        // offset 13: count field, 3rd VLF
        assertEquals(3, (long) (buff.getInt(13) & 0xFFFFFFFFL));

        // array of uchar starting at offset 17: 3rd VLF data
        assertBufferSliceEqual(buff, data[3], 17, 3);

        // offset 20: count field, 4th VLF
        assertEquals(4, (long) (buff.getInt(20) & 0xFFFFFFFFL));

        // array of uchar starting at offset 24, 4th VLF data
        assertBufferSliceEqual(buff, data[4], 24, 4);

        // offset 83: count field, 10th VLF
        assertEquals(10, (long) (buff.getInt(83) & 0xFFFFFFFFL));

        // array of uchar starting at offset 87, 10th VLF data
        assertBufferSliceEqual(buff, data[9], 87, 10);

        System.out.println("\n  [completed test (testEncodeToSpec)] ");
    }

    public void testEncodeDecodeOperations() {
        ByteBuffer buff = ByteBuffer.allocate(1024);
        int i;
        for (i = 0; i < 1024; i++) {
            buff.put(i, (byte) 0);
        }

        System.out.println("\n  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] ");

        // set initial contents of messages to differ.

        msgIn1.getHeader().getHeaderRec().setMessageID(50);

        ByteBuffer[] data_a = new ByteBuffer[10];
        ByteBuffer[] data_b = new ByteBuffer[10];
        ByteBuffer[] data_c = new ByteBuffer[10];

        for (i = 0; i < 10; ++i) {
            data_a[i] = ByteBuffer.allocate(1 + i);
            data_b[i] = ByteBuffer.allocate(1 + i);
            data_c[i] = ByteBuffer.allocate(1 + i);

            // after loop concludes, data_a[0] contains {10}, data_a[1] contains {10, 11}, data_a[2] {10, 11, 12} etc.
            // similarly data_b[0] = {20}, data_b[1] = {20, 11}, etc.
            data_a[i].put((byte) 10);
            data_b[i].put((byte) 20);
            data_c[i].put((byte) 30);

            for (int j = 1; j <= i; ++j) {
                data_a[i].put((byte) (10 + j));
                data_b[i].put((byte) (10 + j));
                data_c[i].put((byte) (10 + j));
            }
        }

        for (i = 0; i < 10; ++i) {
            msgIn1.getBody().getRecord().getArray9().getVLF1(i).set(i+1, data_a[i]);
        }

        msgIn2.getHeader().getHeaderRec().setMessageID(100);

        for (i = 0; i < 10; ++i) {
            msgIn2.getBody().getRecord().getArray9().getVLF1(i).set(i+1, data_b[i]);
        }

        // decode contents of msg1 into msg2.  fields of msg2 should now match fields of msg1.
        msgIn1.encode(buff, 0);
        msgIn2.decode(buff, 0);

        assertEquals(msgIn1.getHeader().getHeaderRec().getMessageID(), msgIn2.getHeader().getHeaderRec().getMessageID());

        assertBufferContentsEqual(msgIn1.getBody().getRecord().getArray9().getVLF1(0).getData(),
                msgIn2.getBody().getRecord().getArray9().getVLF1(0).getData(), 1);
        assertBufferContentsEqual(msgIn1.getBody().getRecord().getArray9().getVLF1(1).getData(),
                msgIn2.getBody().getRecord().getArray9().getVLF1(1).getData(), 2);
        assertBufferContentsEqual(msgIn1.getBody().getRecord().getArray9().getVLF1(2).getData(),
                msgIn2.getBody().getRecord().getArray9().getVLF1(2).getData(), 3);
        assertBufferContentsEqual(msgIn1.getBody().getRecord().getArray9().getVLF1(3).getData(),
                msgIn2.getBody().getRecord().getArray9().getVLF1(3).getData(), 4);
        assertBufferContentsEqual(msgIn1.getBody().getRecord().getArray9().getVLF1(4).getData(),
                msgIn2.getBody().getRecord().getArray9().getVLF1(4).getData(), 5);
        assertBufferContentsEqual(msgIn1.getBody().getRecord().getArray9().getVLF1(5).getData(),
                msgIn2.getBody().getRecord().getArray9().getVLF1(5).getData(), 6);
        assertBufferContentsEqual(msgIn1.getBody().getRecord().getArray9().getVLF1(6).getData(),
                msgIn2.getBody().getRecord().getArray9().getVLF1(6).getData(), 7);
        assertBufferContentsEqual(msgIn1.getBody().getRecord().getArray9().getVLF1(7).getData(),
                msgIn2.getBody().getRecord().getArray9().getVLF1(7).getData(), 8);
        assertBufferContentsEqual(msgIn1.getBody().getRecord().getArray9().getVLF1(8).getData(),
                msgIn2.getBody().getRecord().getArray9().getVLF1(8).getData(), 9);
        assertBufferContentsEqual(msgIn1.getBody().getRecord().getArray9().getVLF1(9).getData(),
                msgIn2.getBody().getRecord().getArray9().getVLF1(9).getData(), 10);

        // change msg1's message ID..
        msgIn1.getHeader().getHeaderRec().setMessageID(9000);

        // change contents of msg2's array, encode the array *alone* into the buffer, then decode
        // the buffer into msg1's array.
        msgIn2.getHeader().getHeaderRec().setMessageID(18000);

        for (i = 0; i < 10; ++i) {
            msgIn2.getBody().getRecord().getArray9().getVLF1(i).set(i+1, data_c[i]);
        }

        msgIn2.getBody().getRecord().getArray9().encode(buff, 0);
        msgIn1.getBody().getRecord().getArray9().decode(buff, 0);

        // since the encode/decode only touched the array, msg1's message ID should still be 9000!
        assertEquals(9000, msgIn1.getHeader().getHeaderRec().getMessageID());

        // contents of msg1's array should be equal to the _c arrays now...
        assertBufferContentsEqual(data_c[0], msgIn1.getBody().getRecord().getArray9().getVLF1(0).getData(), 1);
        assertBufferContentsEqual(data_c[1], msgIn1.getBody().getRecord().getArray9().getVLF1(1).getData(), 2);
        assertBufferContentsEqual(data_c[2], msgIn1.getBody().getRecord().getArray9().getVLF1(2).getData(), 3);
        assertBufferContentsEqual(data_c[3], msgIn1.getBody().getRecord().getArray9().getVLF1(3).getData(), 4);
        assertBufferContentsEqual(data_c[4], msgIn1.getBody().getRecord().getArray9().getVLF1(4).getData(), 5);
        assertBufferContentsEqual(data_c[5], msgIn1.getBody().getRecord().getArray9().getVLF1(5).getData(), 6);
        assertBufferContentsEqual(data_c[6], msgIn1.getBody().getRecord().getArray9().getVLF1(6).getData(), 7);
        assertBufferContentsEqual(data_c[7], msgIn1.getBody().getRecord().getArray9().getVLF1(7).getData(), 8);
        assertBufferContentsEqual(data_c[8], msgIn1.getBody().getRecord().getArray9().getVLF1(8).getData(), 9);
        assertBufferContentsEqual(data_c[9], msgIn1.getBody().getRecord().getArray9().getVLF1(9).getData(), 10);

        System.out.println("\n  [completed test (testEncodeDecodeOperations)] ");

    }

    public void testEquality() {
        System.out.println("\n  [executing test (testEquality)] ");

        assertTrue(msgIn1.isEqual(msgIn2));
        System.out.println("\n  [completed test (testEquality)] ");

    }

    public void testInequality() {
        System.out.println("\n  [executing test (testInequality)] ");

        assertFalse(msgIn1.notEquals(msgIn2));
        System.out.println("\n  [completed test (testInequality)] ");

    }

    public static void main(String[] args) {
        System.out.println("Executing suite JTSArray9MessageTest");
        TestRunner.run(Array9_utst.class);
        System.out.println("Completed suite JTSArray9MessageTest");
        System.out.println("------------------------------------------------");
    }
}
