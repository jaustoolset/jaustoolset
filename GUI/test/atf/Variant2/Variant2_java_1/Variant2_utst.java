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
import src.urn_org_jts_test_Variant2_1_0.Messages.MsgIn;
import junit.textui.TestRunner;
import junit.framework.TestCase;


public class Variant2_utst extends TestCase {

  public Variant2_utst(String name) {
     super(name);
  }

  private MsgIn msgIn1;
  private MsgIn msgIn2;

  @Override
  public void setUp()
  {
      msgIn1 = new MsgIn();
      msgIn2 = new MsgIn();
  }

  public void testConstructionDefaults()
  {
    System.out.println("\n  [executing test (testConstructionDefaults)] ");

    assertEquals(1 , msgIn1.getHeader().getHeaderRec().getMessageID());
    assertEquals(1 , msgIn2.getHeader().getHeaderRec().getMessageID());
    
    
    // check vtag fields are assigned 0 initially
    assertEquals(0 , msgIn1.getBody().getVariantTop().getFieldValue());
    assertEquals(0 , msgIn1.getBody().getVariantTop().getVariantA().getFieldValue());
    assertEquals(0 , msgIn1.getBody().getVariantTop().getVariantB().getFieldValue());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getFieldValue());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getVariantA().getFieldValue());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getVariantB().getFieldValue());
    
    // check all fields are 0 intially.
    assertEquals(0 , msgIn1.getBody().getVariantTop().getVariantA().getRecA1().getFieldA1());
    assertEquals(0 , msgIn1.getBody().getVariantTop().getVariantA().getRecA2().getFieldA2());
    assertEquals(0 , msgIn1.getBody().getVariantTop().getVariantB().getRecB1().getFieldB1());
    assertEquals(0 , msgIn1.getBody().getVariantTop().getVariantB().getRecB2().getFieldB2());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getVariantA().getRecA1().getFieldA1());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getVariantA().getRecA2().getFieldA2());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getVariantB().getRecB1().getFieldB1());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getVariantB().getRecB2().getFieldB2());

    System.out.println("  [completed test (testSetGetOperations)] ");
  }
  
  public void testSetGetOperations()
  {
    System.out.println("\n  [executing test (testSetGetOperations)(TP_3.3.5.1)] ");
    
    msgIn1.getHeader().getHeaderRec().setMessageID(5);
    assertEquals(msgIn1.getHeader().getHeaderRec().getMessageID() , 5 );
    msgIn1.getHeader().getHeaderRec().setMessageID(1);
    assertEquals(msgIn1.getHeader().getHeaderRec().getMessageID() , 1 );

    // check vtag field changes are recorded properly
    msgIn1.getBody().getVariantTop().setFieldValue((short)1);
    assertEquals(1 , msgIn1.getBody().getVariantTop().getFieldValue());
    msgIn1.getBody().getVariantTop().getVariantA().setFieldValue((short)1);
    assertEquals(1 , msgIn1.getBody().getVariantTop().getVariantA().getFieldValue());
    msgIn1.getBody().getVariantTop().getVariantB().setFieldValue((short)1);
    assertEquals(1 , msgIn1.getBody().getVariantTop().getVariantB().getFieldValue());

    // check field changes are recorded properly
    msgIn1.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1((short)99);
    assertEquals(99 , msgIn1.getBody().getVariantTop().getVariantA().getRecA1().getFieldA1());
    msgIn1.getBody().getVariantTop().getVariantA().getRecA2().setFieldA2((short)88);
    assertEquals(88 , msgIn1.getBody().getVariantTop().getVariantA().getRecA2().getFieldA2());
    msgIn1.getBody().getVariantTop().getVariantB().getRecB1().setFieldB1((short)77);
    assertEquals(77 , msgIn1.getBody().getVariantTop().getVariantB().getRecB1().getFieldB1());
    msgIn1.getBody().getVariantTop().getVariantB().getRecB2().setFieldB2((short)66);
    assertEquals(66 , msgIn1.getBody().getVariantTop().getVariantB().getRecB2().getFieldB2());
    
    // chech getsize() returns accurate value
    // 2-byte header, 2 1-byte vtag fields, 1 2-byte unsigned short
    assertEquals(2+1+1+2 , msgIn1.getSize());
        
    System.out.println("  [completed test (testSetGetOperations)] ");
  }

  public void testEncodeToSpec()
  {
    ByteBuffer buff = ByteBuffer.allocate(1024);
    int i;
    for (i=0;i<1024;i++)
    {
       buff.put(i, (byte)0);
    }
       
    System.out.println("\n  [executing test (testEncodeToSpec)(TP_3.3.5.2)] ");
    
    // fill msg1, select VariantA, RecA1 as active, encode.
    msgIn1.getHeader().getHeaderRec().setMessageID(500);

    msgIn1.getBody().getVariantTop().setFieldValue((short)0);
    msgIn1.getBody().getVariantTop().getVariantA().setFieldValue((short)0);
    msgIn1.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1((short)42);
    
    msgIn1.encode(buff, 0);
    
    // verify encoding matches expectations from spec document.
    
    // offset 0:   unsigned short w/ message ID
    assertEquals(500 , (int)(buff.getShort(0) & 0xFFFF));

    // offset 2:   unsigned char w/ VariantTop's vtag field
    assertEquals(0 , buff.get(2));
    
    // offset 3:   unsigned char w/ VariantA's vtag field
    assertEquals(0 , buff.get(3));
    
    // offset 4:   unsigned short w/ message ID
    assertEquals(42 , (int)(buff.getShort(4) & 0xFFFF));
    
    // fill msg1, select VariantB, RecB1 as active
    msgIn1.getHeader().getHeaderRec().setMessageID(600);

    msgIn1.getBody().getVariantTop().setFieldValue((short)1);
    msgIn1.getBody().getVariantTop().getVariantB().setFieldValue((short)0);
    msgIn1.getBody().getVariantTop().getVariantB().getRecB1().setFieldB1((short)3456);
    
    msgIn1.encode(buff, 0);
    
    // verify encoding matches expectations from spec document
    // offset 0:   unsigned short w/ message ID
    assertEquals(600 , (int)(buff.getShort(0) & 0xFFFF));

    // offset 2:   unsigned char w/ VariantTop's vtag field
    assertEquals(1 , buff.get(2));
    
    // offset 3:   unsigned char w/ VariantA's vtag field
    assertEquals(0 , buff.get(3));
    
    // offset 4:   unsigned short w/ message ID
    assertEquals(3456 , (int)(buff.getShort(4) & 0xFFFF));
    
    // offset 6: 0, past end of message data.
    

    System.out.println("\n  [completed test (testEncodeToSpec)] ");
       
  }
  
  public void testEncodeDecodeOperations()
  {
    ByteBuffer buff = ByteBuffer.allocate(1024);
    int i;
    for (i=0;i<1024;i++)
    {
       buff.put(i, (byte)0);
    }
    
    System.out.println("\n  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] ");
    
    // fill msg1, select VariantA, RecA1 as active.
    msgIn1.getHeader().getHeaderRec().setMessageID(500);

    msgIn1.getBody().getVariantTop().setFieldValue((short)0);
    msgIn1.getBody().getVariantTop().getVariantA().setFieldValue((short)0);
    msgIn1.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1((short)19);
    msgIn1.getBody().getVariantTop().getVariantB().getRecB2().setFieldB2((short)0);

    // fill msg2, select VariantB, RecB2 as active.
    msgIn2.getHeader().getHeaderRec().setMessageID(1000);

    msgIn2.getBody().getVariantTop().setFieldValue((short)1);
    msgIn2.getBody().getVariantTop().getVariantB().setFieldValue((short)1);
    msgIn2.getBody().getVariantTop().getVariantB().getRecB2().setFieldB2((short)29);
    
    // encode/decode msg2 into msg1.  msg1 should now show active VariantB, RecB2; verify field contents are
    // correct.
    msgIn2.encode(buff, 0);
    msgIn1.decode(buff, 0);
    
    assertEquals(1000 , msgIn1.getHeader().getHeaderRec().getMessageID());
    assertEquals(1 , msgIn1.getBody().getVariantTop().getFieldValue());
    assertEquals(1 , msgIn1.getBody().getVariantTop().getVariantB().getFieldValue());
    assertEquals(29 , msgIn1.getBody().getVariantTop().getVariantB().getRecB2().getFieldB2());
    
    // change the msg ID on msg1 and fill new values in msg1.
    // fill msg1, select VariantA, RecA1 as active.
    msgIn1.getHeader().getHeaderRec().setMessageID(1999);

    msgIn1.getBody().getVariantTop().setFieldValue((short)0);
    msgIn1.getBody().getVariantTop().getVariantA().setFieldValue((short)0);
    msgIn1.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1((short)0);
    msgIn1.getBody().getVariantTop().getVariantA().getRecA2().setFieldA2((short)0);
    msgIn1.getBody().getVariantTop().getVariantB().setFieldValue((short)0);
    msgIn1.getBody().getVariantTop().getVariantB().getRecB1().setFieldB1((short)0);
    msgIn1.getBody().getVariantTop().getVariantB().getRecB2().setFieldB2((short)0);
    
    // encode/decode msg2's VariantTop into msg1.  msg1 should now show changed variant, but keep the msg ID.
    msgIn2.getBody().getVariantTop().encode(buff, 0);
    msgIn1.getBody().getVariantTop().decode(buff, 0);

    assertEquals(1999 , msgIn1.getHeader().getHeaderRec().getMessageID());
    assertEquals(1 , msgIn1.getBody().getVariantTop().getFieldValue());
    assertEquals(0 , msgIn1.getBody().getVariantTop().getVariantA().getFieldValue());
    assertEquals(1 , msgIn1.getBody().getVariantTop().getVariantB().getFieldValue());
    assertEquals(29 , msgIn1.getBody().getVariantTop().getVariantB().getRecB2().getFieldB2());
        
    // set msg1's VariantTop so VariantA is active, also make RecA2 active inside.
    //  Also make VariantA active in msg2, but make RecA1 active.
    msgIn1.getBody().getVariantTop().setFieldValue((short)0);
    msgIn1.getBody().getVariantTop().getVariantA().setFieldValue((short)1);
    msgIn1.getBody().getVariantTop().getVariantA().getRecA2().setFieldA2((short)9000);
    msgIn1.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1((short)0);
    
    msgIn2.getBody().getVariantTop().setFieldValue((short)0);
    msgIn2.getBody().getVariantTop().getVariantA().setFieldValue((short)0);
    msgIn2.getBody().getVariantTop().getVariantA().getRecA1().setFieldA1((short)4500);
    
    // encode/decode msg2's VariantA into msg1.  verify msg1 shows contents from msgIn2.
    msgIn2.getBody().getVariantTop().getVariantA().encode(buff, 0);
    msgIn1.getBody().getVariantTop().getVariantA().decode(buff, 0);
    
    assertEquals(0 , msgIn1.getBody().getVariantTop().getFieldValue());
    assertEquals(0 , msgIn1.getBody().getVariantTop().getVariantA().getFieldValue());
    assertEquals(4500 , msgIn1.getBody().getVariantTop().getVariantA().getRecA1().getFieldA1());

    System.out.println("\n  [completed test (testEncodeDecodeOperations)] ");
       
  }

  public void testEquality()
  {
    System.out.println("\n  [executing test (testEquality)] ");
    assertTrue(msgIn1.isEqual(msgIn2));
    System.out.println("\n  [completed test (testEquality)] ");
    
  }
  
  public void testInequality()
  {
    System.out.println("\n  [executing test (testInequality)] ");
       
    assertFalse(msgIn1.notEquals(msgIn2));
    System.out.println("\n  [completed test (testInequality)] ");
    
  }

  public static void main(String[] args) {
        System.out.println("Executing suite JTSVariant2MessageTest");
        TestRunner.run(Variant2_utst.class);
        System.out.println("Completed suite JTSVariant2MessageTest");
        System.out.println("------------------------------------------------");
  }
}

