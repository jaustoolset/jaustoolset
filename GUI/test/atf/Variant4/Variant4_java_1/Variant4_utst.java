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

// test for message structure with <variant> containing <sequence>s

import java.nio.ByteBuffer;
import src.urn_org_jts_test_Variant4_1_0.Messages.MsgIn;
import junit.textui.TestRunner;
import junit.framework.TestCase;

public class Variant4_utst extends TestCase {
    // need to repeatedly reset the contents of msgIn1 in during testEncodeDecodeOperations.
    public void resetMessage(MsgIn for_reset)
    {
        for_reset.getHeader().getHeaderRec().setMessageID(1000);
        for_reset.getBody().getVariantTop().setFieldValue((short)1);
        for_reset.getBody().getVariantTop().getSeqB().getRecB().setFieldB1((short)9000);

        for (int i = 0; i < 10; ++i)
        {
            MsgIn.Body.VariantTop.SeqB.ListB.RecBInner temprec =
                    new MsgIn.Body.VariantTop.SeqB.ListB.RecBInner();
            temprec.setFieldB2((short)(20+i));
            for_reset.getBody().getVariantTop().getSeqB().getListB().addElement(temprec);
        }
    }
             
  public Variant4_utst(String name) {
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

    // check vtag fields are assigned 0 initially
    assertEquals(0 , msgIn1.getBody().getVariantTop().getFieldValue());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getFieldValue());
    
    // check fixed fields are zeroed and lists are empty initially.
    assertEquals(0 , msgIn1.getBody().getVariantTop().getSeqA().getRecA().getFieldA1());
    assertEquals(0 , msgIn1.getBody().getVariantTop().getSeqB().getRecB().getFieldB1());
    assertEquals(0 , msgIn1.getBody().getVariantTop().getSeqA().getListA().getNumberOfElements());
    assertEquals(0 , msgIn1.getBody().getVariantTop().getSeqB().getListB().getNumberOfElements());

    assertEquals(0 , msgIn2.getBody().getVariantTop().getSeqA().getRecA().getFieldA1());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getSeqB().getRecB().getFieldB1());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getSeqA().getListA().getNumberOfElements());
    assertEquals(0 , msgIn2.getBody().getVariantTop().getSeqB().getListB().getNumberOfElements());

    System.out.println("\n  [completed test (testSetGetOperations)] ");
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
    
    // set/add elements to active sequence, SeqA, verify changes are recorded.
    msgIn1.getBody().getVariantTop().getSeqA().getRecA().setFieldA1((short)45);
    for (int i = 0; i < 10; ++i) 
    {
       MsgIn.Body.VariantTop.SeqA.ListA.RecAInner temprec = new MsgIn.Body.VariantTop.SeqA.ListA.RecAInner();
       temprec.setFieldA2((short)(10+i));
       msgIn1.getBody().getVariantTop().getSeqA().getListA().addElement(temprec);
    }
    
    assertEquals(45 , msgIn1.getBody().getVariantTop().getSeqA().getRecA().getFieldA1());
    for (int i = 0; i < 10; ++i) 
    {
       assertEquals(10+i , msgIn1.getBody().getVariantTop().getSeqA().getListA().getElement(i).getFieldA2());
    }
    
    
    // set/add elements to active sequence, SeqB, verify changes are recorded.
    msgIn1.getBody().getVariantTop().setFieldValue((short)1);
    msgIn1.getBody().getVariantTop().getSeqB().getRecB().setFieldB1((short)90);
    for (int i = 0; i < 5; ++i) 
    {
       MsgIn.Body.VariantTop.SeqB.ListB.RecBInner temprec = new MsgIn.Body.VariantTop.SeqB.ListB.RecBInner();
       temprec.setFieldB2((short)(20+i));
       msgIn1.getBody().getVariantTop().getSeqB().getListB().addElement(temprec);
    }
    
    assertEquals(90 , msgIn1.getBody().getVariantTop().getSeqB().getRecB().getFieldB1());
    for (int i = 0; i < 5; ++i) 
    {
       assertEquals(20+i , msgIn1.getBody().getVariantTop().getSeqB().getListB().getElement(i).getFieldB2());
    }
    
    // check message size is correct
    // 2-byte ushort msg ID header, 1-byte vtag field, 2-byte ushort in RecB, 1-byte count field for ListB, 
    // 5 2-byte unsigned shorts in ListB
    assertEquals(2 + 1 + 2 + 1 + 5*2 , msgIn1.getSize());
    
    System.out.println("\n  [completed test (testSetGetOperations)] ");
             
  }

  public void testEncodeToSpec()
  {
     ByteBuffer buff = ByteBuffer.allocate(1024);
     int i = 0;
    
    for (i=0;i<1024;i++) 
    {
       buff.put(i, (byte)0);
    }

    System.out.println("\n  [executing test (testEncodeToSpec)(TP_3.3.5.2)] ");

    // fill the message in and copy to buffer
    resetMessage(msgIn1);
    msgIn1.encode(buff, 0);
    
    // verify encoding matches expectations from spec document

    // offset 0:   unsigned short w/ message ID
    assertEquals(1000 , (int)(buff.getShort(0) & 0xFFFF));

    // offset 2:   unsigned char w/ VariantTop's vtag field
    assertEquals(1, buff.get(2));
    
    // offset 3:   unsigned short, FieldB1
    assertEquals(9000 , (int)(buff.getShort(3) & 0xFFFF));
    
    // offset 5:   unsigned char w/ ListB count field
    assertEquals(10 , buff.get(5));
    
    // offsets 6-24: 10 ushort values in list
    int offset = 6;
    for (i = 0; i < 10; ++i) 
    {
        assertEquals(20+i , (int)(buff.getShort(offset) & 0xFFFF));
        offset += 2;
    }
    
    System.out.println("\n  [completed test (testEncodeToSpec)] ");
       
  }
  
  public void testEncodeDecodeOperations()
  {
     ByteBuffer buff = ByteBuffer.allocate(1024);
     int i = 0;

    for (i=0;i<1024;i++)
    {
       buff.put(i, (byte)0);
    }
    
    // setup msg1
    resetMessage(msgIn1);
    
    // setup msg2
    msgIn2.getHeader().getHeaderRec().setMessageID(500);
    msgIn2.getBody().getVariantTop().setFieldValue((short)1);
    msgIn2.getBody().getVariantTop().getSeqB().getRecB().setFieldB1((short)4500);

    for (i = 0; i < 15; ++i)
    {
      MsgIn.Body.VariantTop.SeqB.ListB.RecBInner temprec = new MsgIn.Body.VariantTop.SeqB.ListB.RecBInner();
      temprec.setFieldB2((short)(5+i));
      msgIn2.getBody().getVariantTop().getSeqB().getListB().addElement(temprec);
    }
    
    // copy entire msg2 into msg1, verify new contents are correct
    msgIn2.encode(buff, 0);
    msgIn1.decode(buff, 0);
    
    assertEquals(500 , msgIn1.getHeader().getHeaderRec().getMessageID());
    assertEquals(1 , msgIn1.getBody().getVariantTop().getFieldValue());
    assertEquals(4500 , msgIn1.getBody().getVariantTop().getSeqB().getRecB().getFieldB1());
    
    for (i = 0; i < 15; ++i)
    {
        assertEquals(5+i , msgIn1.getBody().getVariantTop().getSeqB().getListB().getElement(i).getFieldB2());
    }
    
    // reset msg1.
    resetMessage(msgIn1);
    
    // copy only variant from msg2 into msg1, verify contents
    msgIn2.getBody().getVariantTop().encode(buff, 0);
    msgIn1.getBody().getVariantTop().decode(buff, 0);

    assertEquals(1000 , msgIn1.getHeader().getHeaderRec().getMessageID()); // unchanged from reset values
    assertEquals(1 , msgIn1.getBody().getVariantTop().getFieldValue());
    assertEquals(4500 , msgIn1.getBody().getVariantTop().getSeqB().getRecB().getFieldB1());
    
    for (i = 0; i < 15; ++i)
    {
        assertEquals(5+i , msgIn1.getBody().getVariantTop().getSeqB().getListB().getElement(i).getFieldB2());
    }
    
    // reset msg1.
    resetMessage(msgIn1);    

    // copy only seqB's listB from msg2 into msg1, verify contents.
    msgIn2.getBody().getVariantTop().getSeqB().getListB().encode(buff, 0);
    msgIn1.getBody().getVariantTop().getSeqB().getListB().decode(buff, 0);

    assertEquals(1000 , msgIn1.getHeader().getHeaderRec().getMessageID()); // unchanged from reset values
    assertEquals(1 , msgIn1.getBody().getVariantTop().getFieldValue());
    assertEquals(9000 , msgIn1.getBody().getVariantTop().getSeqB().getRecB().getFieldB1()); // should also be unchanged.
    
    for (i = 0; i < 15; ++i)
    {
        assertEquals(5+i , msgIn1.getBody().getVariantTop().getSeqB().getListB().getElement(i).getFieldB2());
    }
    
    System.out.println("\n  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] ");
    
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
        System.out.println("Executing suite JTSVariant4MessageTest");
        TestRunner.run(Variant4_utst.class);
        System.out.println("Completed suite JTSVariant4MessageTest");
        System.out.println("------------------------------------------------");
  }
};
