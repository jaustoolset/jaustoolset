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

using System;
using NUnit.Framework;
using urn_org_jts_test_Header5_1_0;

[TestFixture]
public class Header5_utst
{
  private MsgIn m_MsgIn1;
    private MsgIn m_MsgIn2;

  public Header5_utst()
  {
  }     
       
  [SetUp]
public void setUp()
  {
      m_MsgIn1 = new MsgIn();
      m_MsgIn2 = new MsgIn();
  }

  [Test]
public void testConstructionDefaults()
  {
        Console.WriteLine("[executing test (testConstructionDefaults)]");

  }
  
  [Test]
public void testSetGetOperations()
  {
        Console.WriteLine("[executing test (testSetGetOperations)(TP_3.3.5.1)]");
    
        // verifying setField1/getField1 reciprocity"    
    m_MsgIn1.getHeader().getVariant1().getMessageIDRec().setMessageCode(65535);
    m_MsgIn2.getHeader().getVariant1().getMessageIDRec().setMessageCode(0);
    Assert.AreEqual(65535, m_MsgIn1.getHeader().getVariant1().getMessageIDRec().getMessageCode());
    Assert.AreEqual(0, m_MsgIn2.getHeader().getVariant1().getMessageIDRec().getMessageCode());
        
    m_MsgIn1.getHeader().getVariant1().getEventTypeRec().setEventType(1);
    //this should fail as event type may only be 0 or 1
    m_MsgIn1.getHeader().getVariant1().getEventTypeRec().setEventType(129);
    m_MsgIn2.getHeader().getVariant1().getEventTypeRec().setEventType(0);
    //this should fail as event type may only be 0 or 1        
    m_MsgIn2.getHeader().getVariant1().getEventTypeRec().setEventType(251);
    Assert.AreEqual(1, m_MsgIn1.getHeader().getVariant1().getEventTypeRec().getEventType());
    Assert.AreEqual(0, m_MsgIn2.getHeader().getVariant1().getEventTypeRec().getEventType());
    
    m_MsgIn1.getHeader().getVariant1().getEventIDRec().setEventID(0);
    m_MsgIn2.getHeader().getVariant1().getEventIDRec().setEventID(1);
    Assert.AreEqual(0, m_MsgIn1.getHeader().getVariant1().getEventIDRec().getEventID());
    Assert.AreEqual(1, m_MsgIn2.getHeader().getVariant1().getEventIDRec().getEventID());

    m_MsgIn1.getHeader().getVariant1().getAllEventsRec().setAllEvents(0);
    //this should fail as allevents may only be 0    
    m_MsgIn1.getHeader().getVariant1().getAllEventsRec().setAllEvents(33);
    m_MsgIn2.getHeader().getVariant1().getAllEventsRec().setAllEvents(0);
    m_MsgIn2.getHeader().getVariant1().getAllEventsRec().setAllEvents(64);
    Assert.AreEqual(0, m_MsgIn1.getHeader().getVariant1().getAllEventsRec().getAllEvents());
    Assert.AreEqual(0, m_MsgIn2.getHeader().getVariant1().getAllEventsRec().getAllEvents());             
  }

  [Test]
public void testEncodeToSpec()
  {
    byte[] buff = new byte[1024];
    int i;
       
        Console.WriteLine("[executing test (testEncodeToSpec)(TP_3.3.5.2)]");
    
        // verifying message level encode is AS-5684 compliant"
        
    //case 1 - variant containing MessageIDRec 
    m_MsgIn1.getHeader().getVariant1().getMessageIDRec().setMessageCode(1027);
	m_MsgIn1.getHeader().getVariant1().setFieldValue(0);
    m_MsgIn1.encode(buff, 0);
    Assert.AreEqual(3, m_MsgIn1.getSize());
    Assert.AreEqual(0, buff[0]);  //vtag_field (run time record type)
    Assert.AreEqual(3, buff[1]);  //lsb of MessageCode
    Assert.AreEqual(4, buff[2]);  //msb of MessageCode

    //case 2 - variant containing EventTypeRec 
    m_MsgIn1.getHeader().getVariant1().getEventTypeRec().setEventType(1);
	m_MsgIn1.getHeader().getVariant1().setFieldValue(1);
	m_MsgIn1.encode(buff, 0);
    Assert.AreEqual(2, m_MsgIn1.getSize());
    Assert.AreEqual(1, buff[0]);  //vtag_field (run time record type)
    Assert.AreEqual(1, buff[1]);  //EventType

    //case 3 - variant containing EventIDRec 
    m_MsgIn1.getHeader().getVariant1().getEventIDRec().setEventID(4);
	m_MsgIn1.getHeader().getVariant1().setFieldValue(2);
	m_MsgIn1.encode(buff, 0);
    Assert.AreEqual(2, m_MsgIn1.getSize());
    Assert.AreEqual(2, buff[0]);  //vtag_field (run time record type)
    Assert.AreEqual(4, buff[1]);  //EventID

    //case 4 - variant containing AllEventsRec 
    m_MsgIn1.getHeader().getVariant1().getAllEventsRec().setAllEvents(0);
	m_MsgIn1.getHeader().getVariant1().setFieldValue(3);
	m_MsgIn1.encode(buff, 0);
    Assert.AreEqual(2, m_MsgIn1.getSize());
    Assert.AreEqual(3, buff[0]);  //vtag_field (run time record type)
    Assert.AreEqual(0, buff[1]);  //allEvents       
  }
  
  [Test]
public void testEncodeDecodeOperations()
  {
    byte[] buff = new byte[1024];
    int i;

    for (i = 0; i < 1024; i++)
    {
        buff[i] = (byte)(((4101 * i) + 65543) % 256);
    }
    
        Console.WriteLine("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]");
    
        // verifying message level encode/decode reciprocity"

	m_MsgIn1.getHeader().getVariant1().setFieldValue(0);
    
    m_MsgIn1.encode(buff, 0);
    m_MsgIn2.decode(buff, 0);
    
    Assert.AreEqual(0, m_MsgIn2.getHeader().getVariant1().getFieldValue());

    m_MsgIn2.getHeader().getVariant1().getMessageIDRec().setMessageCode(127);   
    
    m_MsgIn1.encode(buff, 0);
    m_MsgIn2.decode(buff, 0);

    Assert.AreEqual(0, m_MsgIn2.getHeader().getVariant1().getFieldValue());

    m_MsgIn1.getHeader().getVariant1().getEventTypeRec().setEventType(1);
	m_MsgIn1.getHeader().getVariant1().setFieldValue(1);

    m_MsgIn2.getHeader().getVariant1().getMessageIDRec().setMessageCode(127);   

    m_MsgIn1.encode(buff, 0);
    m_MsgIn2.decode(buff, 0);
    
    Assert.AreEqual(1, m_MsgIn2.getHeader().getVariant1().getFieldValue());
    Assert.AreEqual(1, m_MsgIn2.getHeader().getVariant1().getEventTypeRec().getEventType());

    m_MsgIn1.getHeader().getVariant1().getEventIDRec().setEventID(101);
	m_MsgIn1.getHeader().getVariant1().setFieldValue(2);
    m_MsgIn2.getHeader().getVariant1().getMessageIDRec().setMessageCode(127);   

    m_MsgIn1.encode(buff, 0);
    m_MsgIn2.decode(buff, 0);
    
    Assert.AreEqual(2, m_MsgIn2.getHeader().getVariant1().getFieldValue());
    Assert.AreEqual(101, m_MsgIn2.getHeader().getVariant1().getEventIDRec().getEventID());
    
        // verifying record level encode/decode reciprocity"

    m_MsgIn2.getHeader().getVariant1().getMessageIDRec().setMessageCode(0);   
        
    m_MsgIn1.getHeader().encode(buff, 0);
    m_MsgIn2.getHeader().decode(buff, 0);

    Assert.AreEqual(101, m_MsgIn2.getHeader().getVariant1().getEventIDRec().getEventID());

    m_MsgIn2.getHeader().getVariant1().getMessageIDRec().setMessageCode(0);   
        
    m_MsgIn1.getHeader().getVariant1().encode(buff, 0);
    m_MsgIn2.getHeader().getVariant1().decode(buff, 0);

//    Assert.AreEqual(3, m_MsgIn2.getHeader().getVariant1().getFieldValue());
    Assert.AreEqual(101, m_MsgIn2.getHeader().getVariant1().getEventIDRec().getEventID());       
  }

  [Test]
public void testEquality()
  {
        Console.WriteLine("[executing test (testEquality)]");
        // verifying message equality operator"    
        Assert.IsTrue(m_MsgIn1.isEqual(m_MsgIn1));
        Assert.IsTrue(m_MsgIn2.isEqual(m_MsgIn1));
        Assert.IsTrue(m_MsgIn1.isEqual(m_MsgIn2));
  }
  
  [Test]
public void testInequality()
  {
        Console.WriteLine("[executing test (testInequality)]");
        // verifying message inequality operator"    

        Assert.IsFalse(m_MsgIn1.notEquals(m_MsgIn2));
        Assert.IsFalse(m_MsgIn2.notEquals(m_MsgIn1));
  }

  public static void Main()
  {
  }
}
