/***********           LICENSE HEADER   *******************************JAUS Tool SetCopyright (c)  2010, United States GovernmentAll rights reserved.Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.Neither the name of the United States Government nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. *********************  END OF LICENSE ***********************************/import java.nio.ByteBuffer;import src.urn_org_jts_test_List4_1_0.Messages.MsgIn;import junit.textui.TestRunner;import junit.framework.TestCase;public class List4_utst extends TestCase {    private MsgIn m_MsgIn1;    private MsgIn m_MsgIn2;    public List4_utst(String name) {        super(name);    }    	  protected void populate()
	  {
		MsgIn.Body.List4 List = new MsgIn.Body.List4();
		MsgIn.Body.List4.Variant1 V1 = new MsgIn.Body.List4.Variant1();
		MsgIn.Body.List4.Variant1 V2 = new MsgIn.Body.List4.Variant1();
		MsgIn.Body.List4.Variant1 V3 = new MsgIn.Body.List4.Variant1();
		MsgIn.Body.List4.Variant1.List1.Record2 R1 = new MsgIn.Body.List4.Variant1.List1.Record2();
		MsgIn.Body.List4.Variant1.List1.Record2 R2 = new MsgIn.Body.List4.Variant1.List1.Record2();

		m_MsgIn1.getBody().setList4( List );
		
		V1.setFieldValue((short) 0);
		V1.getRecord1().setField1((short) 1);
		V1.getRecord1().setField2((short) 770);

		V2.setFieldValue((short) 1);
		R1.setField1((short) 3); V2.getList1().addElement(R1);
		R2.setField1((short) 4); V2.getList1().addElement(R2);

		V3.setFieldValue((short) 2);
		V3.getSequence1().getRecord3().setField1((short) 3);
		V3.getSequence1().getRecord3().setField2((short) 1284);

		m_MsgIn1.getBody().getList4().addElement(V1);
		m_MsgIn1.getBody().getList4().addElement(V2);
		m_MsgIn1.getBody().getList4().addElement(V3);
	  }
         protected void setUp() {        m_MsgIn1 = new MsgIn();        m_MsgIn2 = new MsgIn();    }    protected void tearDown() {    }    public void testConstructionDefaults() {        System.out.println("");        System.out.print("[executing test (testConstructionDefaults)]: ");        // verifying MsgIn constructs with correct default MessageID"        assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());        assertEquals(1, m_MsgIn2.getHeader().getHeaderRec().getMessageID());        // verifying MsgIn constructs with default empty lists"        assertEquals(0, m_MsgIn1.getBody().getList4().getNumberOfElements());        assertEquals(0, m_MsgIn2.getBody().getList4().getNumberOfElements());    }


  
  public void testSetGetOperations()
  {
    System.out.println("");    System.out.print("[executing test (testSetGetOperations)(TP_3.3.5.1)]: ");    // verifying setMessageID/getMessageID reciprocity"    m_MsgIn1.getHeader().getHeaderRec().setMessageID(5);    assertEquals(5, m_MsgIn1.getHeader().getHeaderRec().getMessageID());    m_MsgIn1.getHeader().getHeaderRec().setMessageID(1);    assertEquals(1, m_MsgIn1.getHeader().getHeaderRec().getMessageID());    // verifying setField/getField reciprocity"
    MsgIn.Body.List4.Variant1 V1 = new MsgIn.Body.List4.Variant1();
    MsgIn.Body.List4.Variant1 V2 = new MsgIn.Body.List4.Variant1();
    
    V1.getRecord1().setField1((short) 5);
    V1.getRecord1().setField2((short) 500);
	V1.setFieldValue((short) 0);
    V2.getSequence1().getRecord3().setField1((short) 6);
    V2.getSequence1().getRecord3().setField2((short) 600);
	V2.setFieldValue((short) 2);
    m_MsgIn1.getBody().getList4().addElement(V1);
    m_MsgIn2.getBody().getList4().addElement(V2);
    assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getRecord1().getField1(), 5);
    assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getRecord1().getField2(), 500);
    assertEquals(m_MsgIn2.getBody().getList4().getElement(0).getSequence1().getRecord3().getField1(), 6);
    assertEquals(m_MsgIn2.getBody().getList4().getElement(0).getSequence1().getRecord3().getField2(), 600);

	// Test copy constructor
    m_MsgIn2 = m_MsgIn1;    assertEquals(m_MsgIn2, m_MsgIn1);    assertEquals(m_MsgIn2.getBody().getList4().getNumberOfElements(), m_MsgIn1.getBody().getList4().getNumberOfElements());             
  }
  
  public void testEquality() {        System.out.println("");        System.out.print("[executing test (testEquality)]: ");        // verifying message equality operator"            assertTrue(m_MsgIn1.isEqual(m_MsgIn1));        assertTrue(m_MsgIn1.isEqual(m_MsgIn2));        assertTrue(m_MsgIn2.isEqual(m_MsgIn1));    }    public void testInequality() {        System.out.println("");        System.out.print("[executing test (testInequality)]: ");        // verifying message inequality operator"            assertFalse(m_MsgIn1.notEquals(m_MsgIn2));        assertFalse(m_MsgIn2.notEquals(m_MsgIn1));    }

  public void testEncodeToSpec()
  {
    ByteBuffer buff = ByteBuffer.allocate(1024);
    int i, j;
       
    System.out.println("");    System.out.print("[executing test (testEncodeToSpec)(TP_3.3.5.2)]: ");

    populate();
    m_MsgIn1.getHeader().getHeaderRec().setMessageID(256);
    m_MsgIn1.encode(buff, 0);

    assertEquals( buff.get(0) , 0 ); assertEquals( buff.get(1) , 1 ); // msg_id
	assertEquals( buff.get(2) , 3 );                                // str length
	for (i=3, j=0; i<7; i++, j++) assertEquals( buff.get(i) , j); // record type
	for (i=7, j=1; i<11; i++, j++) assertEquals( buff.get(i) , j); // list type
	for (i=11, j=2; i<15; i++, j++) assertEquals( buff.get(i) , j); // sequence type
  }
  
  public void testEncodeDecodeOperations()
  {
    int i; 
    ByteBuffer buff = ByteBuffer.allocate(1024);
    MsgIn.Body.List4 List = new MsgIn.Body.List4();
	MsgIn.Body.List4.Variant1 V1 = new MsgIn.Body.List4.Variant1();
    MsgIn.Body.List4.Variant1 V2 = new MsgIn.Body.List4.Variant1();
    MsgIn.Body.List4.Variant1 V3 = new MsgIn.Body.List4.Variant1();
    
    for (i=0;i<1024;i++)
       buff.put(i, (byte)(((2101*i)+65543) % 256));
    
    System.out.println("");    System.out.print("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]: ");
    
    // verifying message level encode/decode reciprocity"
    m_MsgIn1.getHeader().getHeaderRec().setMessageID(41);
    m_MsgIn1.getBody().setList4( List );
    m_MsgIn1.getBody().getList4().addElement(V1);
    m_MsgIn1.getBody().getList4().addElement(V2);
	m_MsgIn1.getBody().getList4().getElement(0).setFieldValue((short) 0);
    m_MsgIn1.getBody().getList4().getElement(0).getRecord1().setField1((short) 15);
    m_MsgIn1.getBody().getList4().getElement(0).getRecord1().setField2((short) 300);
	m_MsgIn1.getBody().getList4().getElement(1).setFieldValue((short) 2);
    m_MsgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().setField1((short) 0);
    m_MsgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().setField2((short) 6525);
    
    m_MsgIn2.getHeader().getHeaderRec().setMessageID(127);
    m_MsgIn2.getBody().getList4().addElement(V3);
	m_MsgIn2.getBody().getList4().getElement(0).setFieldValue((short) 0);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField1((short) 127);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField2((short) 127);
    
    m_MsgIn1.encode(buff, 0);
    m_MsgIn2.decode(buff, 0);

    assertEquals( m_MsgIn2.getHeader().getHeaderRec().getMessageID() , 41);
    assertEquals( m_MsgIn2.getBody().getList4().getNumberOfElements(), 2);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getFieldValue() , 0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField1() , 15);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField2() , 300);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getFieldValue() , 2);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1() , 0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2() , 6525);
    
    
    // verifying header level encode/decode reciprocity
    
    m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
    m_MsgIn1.getHeader().encode(buff, 0);
    m_MsgIn2.getHeader().decode(buff, 0);
    assertEquals( m_MsgIn2.getHeader().getHeaderRec().getMessageID() , 41);

    m_MsgIn2.getHeader().getHeaderRec().setMessageID(0);
    m_MsgIn1.getHeader().getHeaderRec().encode(buff, 0);
    m_MsgIn2.getHeader().getHeaderRec().decode(buff, 0);
    assertEquals( m_MsgIn2.getHeader().getHeaderRec().getMessageID() , 41);
    
    //     --- verifying body level encode/decode reciprocity

	m_MsgIn2.getBody().getList4().getElement(0).setFieldValue((short) 0);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField1((short) 255);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField2((short) 255);
	m_MsgIn2.getBody().getList4().getElement(0).setFieldValue((short) 0);
    m_MsgIn2.getBody().getList4().getElement(1).getRecord1().setField1((short) 255);
    m_MsgIn2.getBody().getList4().getElement(1).getRecord1().setField2((short) 255);
    V3.getRecord1().setField1((short) 255); V3.getRecord1().setField2((short) 255);
	m_MsgIn2.getBody().getList4().addElement(V3);
    
    m_MsgIn1.getBody().encode(buff, 0);
    m_MsgIn2.getBody().decode(buff, 0);
    
    assertEquals( m_MsgIn2.getBody().getList4().getNumberOfElements(),2);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getFieldValue() , 0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField1() , 15);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField2() , 300);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getFieldValue() , 2);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1() , 0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2() , 6525);


    //     --- verifying list level encode/decode reciprocity
    
	m_MsgIn2.getBody().getList4().getElement(0).setFieldValue((short) 0);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField1((short) 255);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField2((short) 255);
	m_MsgIn2.getBody().getList4().getElement(0).setFieldValue((short) 1);
    m_MsgIn2.getBody().getList4().getElement(1).getRecord1().setField1((short) 255);
    m_MsgIn2.getBody().getList4().getElement(1).getRecord1().setField2((short) 255);
    V3.getRecord1().setField1((short) 255); V3.getRecord1().setField2((short) 255);
    m_MsgIn2.getBody().getList4().addElement(V3);
    
    m_MsgIn1.getBody().getList4().encode(buff, 0);
    m_MsgIn2.getBody().getList4().decode(buff, 0);
    
    assertEquals( m_MsgIn2.getBody().getList4().getNumberOfElements(),2);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getFieldValue(),0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField1() , 15);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField2() , 300);
	assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getFieldValue(),2);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1() , 0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2() , 6525);
    
    //     --- verifying record level encode/decode reciprocity
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField1((short) 255);
    m_MsgIn2.getBody().getList4().getElement(0).getRecord1().setField2((short) 255);
    
    m_MsgIn1.getBody().getList4().getElement(0).encode(buff, 0);
    m_MsgIn2.getBody().getList4().getElement(0).decode(buff, 0);
    
	assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getFieldValue(),0);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField1() , 15);
    assertEquals( m_MsgIn2.getBody().getList4().getElement(0).getRecord1().getField2() , 300);
       
  }



  public void testListOperations()
  {
	MsgIn.Body.List4 List = new MsgIn.Body.List4();
    MsgIn.Body.List4.Variant1 V1;
	m_MsgIn2.getBody().setList4( List );
    int i,j;
    
    System.out.println("");    System.out.print("[executing test (testListOperations)]: ");
    
    //     --- verifying MsgIn getNumberOfElements with Add and Delete operations    
    i=j=0;  
    for (i=0;i<4;i++) {
	   V1 = new MsgIn.Body.List4.Variant1();
       assertEquals(m_MsgIn2.getBody().getList4().getNumberOfElements(),i);
       m_MsgIn2.getBody().getList4().addElement(V1);
    }   
    for (j=0,i=4;j<4;j++,i--) {
       assertEquals(m_MsgIn2.getBody().getList4().getNumberOfElements() , i);
       m_MsgIn2.getBody().getList4().deleteLastElement();
    }

    //     --- verifying deleteElement preserves order & corrects count of remaining elements                          
    
	populate();

    m_MsgIn1.getBody().getList4().deleteElement(0);
    assertEquals(m_MsgIn1.getBody().getList4().getNumberOfElements() , 2);

	assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getFieldValue() , 1);
    assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getNumberOfElements() , 2);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getElement(0).getField1() , 3);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getElement(1).getField1() , 4);
    assertEquals(m_MsgIn1.getBody().getList4().getElement(1).getFieldValue() , 2);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().getField1() , 3);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(1).getSequence1().getRecord3().getField2() , 1284);
    
    m_MsgIn1.getBody().getList4().deleteElement(1);
    assertEquals(m_MsgIn1.getBody().getList4().getNumberOfElements() , 1);
    
    assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getFieldValue() , 1);
    assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getNumberOfElements() , 2);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getElement(0).getField1() , 3);
	assertEquals(m_MsgIn1.getBody().getList4().getElement(0).getList1().getElement(1).getField1() , 4);
    
    m_MsgIn1.getBody().getList4().deleteElement(0);
    assertEquals(m_MsgIn1.getBody().getList4().getNumberOfElements() , 0);
                
  }

     public static void main(String[] args) {        System.out.println("Executing suite JTSList4MessageTest");        TestRunner.run(List4_utst.class);        System.out.println("Completed suite JTSList4MessageTest");        System.out.println("------------------------------------------------");    }}