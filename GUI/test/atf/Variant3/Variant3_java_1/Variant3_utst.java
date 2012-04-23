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
import src.urn_org_jts_test_Variant3_1_0.Messages.MsgIn;
import junit.textui.TestRunner;
import junit.framework.TestCase;

public class Variant3_utst extends TestCase {

  public Variant3_utst(String name) {
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

    assertEquals(msgIn1.getHeader().getHeaderRec().getMessageID() , 1 );
    assertEquals(msgIn2.getHeader().getHeaderRec().getMessageID() , 1 );

	// check vtags are assigned 0 initially
    assertEquals(0 , msgIn1.getBody().getVariantTop().getFieldValue());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getFieldValue());

    // check lists are empty initially
    assertEquals(0 , msgIn2.getBody().getVariantTop().getListA().getNumberOfElements());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getListB().getNumberOfElements());

    System.out.println("\n  [completed test testConstructionDefaults] ");
  }
  
  public void testSetGetOperations()
  {
    System.out.println("\n  [executing test (testSetGetOperations)(TP_3.3.5.1)] ");
    
    // check header field changes are recorded properly
    msgIn1.getHeader().getHeaderRec().setMessageID(5);
    assertEquals(5 , msgIn1.getHeader().getHeaderRec().getMessageID());
    msgIn1.getHeader().getHeaderRec().setMessageID(1);
    assertEquals(1 , msgIn1.getHeader().getHeaderRec().getMessageID());

    // check vtag field changes are recorded properly
    msgIn1.getBody().getVariantTop().setFieldValue((short)1);
    assertEquals(1 , msgIn1.getBody().getVariantTop().getFieldValue());
    msgIn1.getBody().getVariantTop().setFieldValue((short)0);
    assertEquals(0 , msgIn1.getBody().getVariantTop().getFieldValue());
    
    // add elements (records with unsigned shorts) to ListA.
    for (int i = 0; i < 10; ++i)
    {
       MsgIn.Body.VariantTop.ListA.RecA1 temprec = new MsgIn.Body.VariantTop.ListA.RecA1();
       temprec.setFieldA1((short)(10+i));
       msgIn1.getBody().getVariantTop().getListA().addElement(temprec);
    }
    
    // verify new contents were stored 
    assertEquals(10 , msgIn1.getBody().getVariantTop().getListA().getNumberOfElements());

    for (int i = 0; i < 10; ++i)
    {
       assertEquals(10+i , msgIn1.getBody().getVariantTop().getListA().getElement(i).getFieldA1());
    }
    
    // try changing and deleting some elements and ensure expected results occur.
    MsgIn.Body.VariantTop.ListA list_alias = msgIn1.getBody().getVariantTop().getListA(); // less typing
    
    list_alias.deleteElement(1);
    list_alias.deleteElement(1);
    list_alias.deleteLastElement();
    list_alias.deleteLastElement();
    
    assertEquals(6 , list_alias.getNumberOfElements());
    
    assertEquals(10 , list_alias.getElement(0).getFieldA1());
    assertEquals(13 , list_alias.getElement(1).getFieldA1());
    assertEquals(14 , list_alias.getElement(2).getFieldA1());
    assertEquals(15 , list_alias.getElement(3).getFieldA1());
    assertEquals(16 , list_alias.getElement(4).getFieldA1());
    assertEquals(17 , list_alias.getElement(5).getFieldA1());
    
    MsgIn.Body.VariantTop.ListA.RecA1 rec1 = new MsgIn.Body.VariantTop.ListA.RecA1();
    MsgIn.Body.VariantTop.ListA.RecA1 rec2 = new MsgIn.Body.VariantTop.ListA.RecA1();
    MsgIn.Body.VariantTop.ListA.RecA1 rec3 = new MsgIn.Body.VariantTop.ListA.RecA1();
    rec1.setFieldA1((short)99);
    rec2.setFieldA1((short)199);
    rec3.setFieldA1((short)299);
    
    list_alias.setElement(5, rec1);
    list_alias.addElement(rec2);
    list_alias.addElement(rec3);

    assertEquals(10 , list_alias.getElement(0).getFieldA1());
    assertEquals(13 , list_alias.getElement(1).getFieldA1());
    assertEquals(14 , list_alias.getElement(2).getFieldA1());
    assertEquals(15 , list_alias.getElement(3).getFieldA1());
    assertEquals(16 , list_alias.getElement(4).getFieldA1());
    assertEquals(99 , list_alias.getElement(5).getFieldA1());
    assertEquals(199 , list_alias.getElement(6).getFieldA1());
    assertEquals(299 , list_alias.getElement(7).getFieldA1());
        
    // check size is correct
    // 2-byte header, 1 1-byte vtag field, 1 1-byte count field, 8 2-byte store shorts
    assertEquals(2 + 1 + 1 + 2*8 , msgIn1.getSize());

    System.out.println("\n  [completed test (testSetGetOperations)] ");
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
    
    msgIn1.getHeader().getHeaderRec().setMessageID(500);
    msgIn1.getBody().getVariantTop().setFieldValue((short)1);
    
    // fill list and encode into buff
    for (i = 0; i < 10; ++i)
    {
       MsgIn.Body.VariantTop.ListB.RecB1 temprec = new MsgIn.Body.VariantTop.ListB.RecB1();
       temprec.setFieldB1((short)(20+i));
       msgIn1.getBody().getVariantTop().getListB().addElement(temprec);
    }
    
    msgIn1.encode(buff, 0);
    
    // verify encoding matches expectations from spec document.
    
    // offset 0:   unsigned short w/ message ID
    assertEquals(500 , (int)(buff.getShort(0) & 0xFFFF));

    // offset 2:   unsigned char w/ VariantTop's vtag field
    assertEquals(1 , buff.get(2));
    
    // offset 3:   unsigned char w/ ListB's length
    assertEquals(10 , buff.get(3));
    
    int offset = 4; // list data starts at offset 4, ends at offset 22.
    for (i = 0; i < 10; ++i)
    {
        assertEquals(20 + i , (int)(buff.getShort(offset) & 0xFFFF));
        offset += 2;
    }
    
    // byte 23 should be 0, past end of message data.
    assertEquals(0 , buff.get(23));
    
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

    // fill msg1, select ListA as active.
    msgIn1.getHeader().getHeaderRec().setMessageID(500);
    msgIn1.getBody().getVariantTop().setFieldValue((short)0);
    for (i = 0; i < 5; ++i)
    {
       MsgIn.Body.VariantTop.ListA.RecA1 temprec = new MsgIn.Body.VariantTop.ListA.RecA1();
       temprec.setFieldA1((short)(10+i));
       msgIn1.getBody().getVariantTop().getListA().addElement(temprec);
    }

    // fill msg2, select ListB as active.
    msgIn2.getHeader().getHeaderRec().setMessageID(1000);
    msgIn2.getBody().getVariantTop().setFieldValue((short)1);
    for (i = 0; i < 10; ++i)
    {
       MsgIn.Body.VariantTop.ListB.RecB1 temprec = new MsgIn.Body.VariantTop.ListB.RecB1();
       temprec.setFieldB1((short)(20+i));
       msgIn2.getBody().getVariantTop().getListB().addElement(temprec);
    }
    
    // encode/decode msg2 into msg1.  verify ListB now active in msg1 and verify list contents copied over
    msgIn2.encode(buff, 0);
    msgIn1.decode(buff, 0);
    
    assertEquals(1000 , msgIn1.getHeader().getHeaderRec().getMessageID());
    assertEquals(1 , msgIn1.getBody().getVariantTop().getFieldValue());
    for (i = 0; i < 10; ++i)
    {
       assertEquals(20+i , msgIn1.getBody().getVariantTop().getListB().getElement(i).getFieldB1());
    }
    
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
        System.out.println("Executing suite JTSVariant3MessageTest");
        TestRunner.run(Variant3_utst.class);
        System.out.println("Completed suite JTSVariant3MessageTest");
        System.out.println("------------------------------------------------");
  }
}
