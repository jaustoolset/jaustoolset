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
using urn_org_jts_test_Array5_1_0;

[TestFixture]
public class Array5_utst
{
    private MsgIn m_MsgIn1;
      private MsgIn m_MsgIn2;

  public Array5_utst()
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

        // verifying MsgIn constructs with correct default DimensionSize"
    Assert.AreEqual(9, m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size());
    Assert.AreEqual(9, m_MsgIn2.getBody().getRecord().getArray5().getDimension1Size());  }
  
  [Test]
public void testSetGetOperations()
  {
        Console.WriteLine("[executing test (testSetGetOperations)(TP_3.3.5.1)]");
    
        // verifying setField/getField reciprocity"    
    int i;
    for (i=0;i<m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size();i++) {
        m_MsgIn1.getBody().getRecord().getArray5().setField1(i,(sbyte)((i+1)*2));
        m_MsgIn2.getBody().getRecord().getArray5().setField1(i, (sbyte)((i + 3) * 2));
    }
    
    for (i=0;i<m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size();i++) {
        Assert.AreEqual(((i+1)*2), m_MsgIn1.getBody().getRecord().getArray5().getField1(i));
        Assert.AreEqual(((i+3)*2), m_MsgIn2.getBody().getRecord().getArray5().getField1(i));
    }             
  }

  [Test]
public void testEncodeToSpec()
  {
    byte[] buff = new byte[1024];
    int i;
       
        Console.WriteLine("[executing test (testEncodeToSpec)(TP_3.3.5.2)]");
    
    for (i=0;i<m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size();i++)
        m_MsgIn1.getBody().getRecord().getArray5().setField1(i, (sbyte)i);
        
        // verifying message level encode is AS-5684 compliant"
    m_MsgIn1.encode(buff, 0);
    for (i=0;i<m_MsgIn1.getSize();i++)
       Assert.AreEqual(i, buff[i]);       
  }
  
  [Test]
public void testEncodeDecodeOperations()
  {
    byte[] buff = new byte[1024];
    int i;
    
    for (i=0;i<1024;i++)
        buff[i] = (byte)(((4101 * i) + 65543) % 256);
    
        Console.WriteLine("[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]");
    
        // verifying message level encode/decode reciprocity"

    for (i=0;i<m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size();i++) {
        m_MsgIn1.getBody().getRecord().getArray5().setField1(i, (sbyte)((i + 1) * 7));
        m_MsgIn2.getBody().getRecord().getArray5().setField1(i,0);
    }
    m_MsgIn1.encode(buff, 0);
    m_MsgIn2.decode(buff, 0);
    
    for (i=0;i<m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size();i++) 
        Assert.AreEqual(((i+1)*7), m_MsgIn2.getBody().getRecord().getArray5().getField1(i));
    
        // verifying record level encode/decode reciprocity"

    for (i=0;i<m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size();i++) 
        m_MsgIn2.getBody().getRecord().getArray5().setField1(i,0);
        
    m_MsgIn1.getBody().encode(buff, 0);
    m_MsgIn2.getBody().decode(buff, 0);
    for (i=0;i<m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size();i++) 
        Assert.AreEqual(((i+1)*7), m_MsgIn2.getBody().getRecord().getArray5().getField1(i));

    for (i=0;i<m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size();i++) 
        m_MsgIn2.getBody().getRecord().getArray5().setField1(i,0);
        
    m_MsgIn1.getBody().getRecord().encode(buff, 0);
    m_MsgIn2.getBody().getRecord().decode(buff, 0);
    for (i=0;i<m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size();i++) 
        Assert.AreEqual(((i+1)*7), m_MsgIn2.getBody().getRecord().getArray5().getField1(i));
        
    for (i=0;i<m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size();i++) 
        m_MsgIn2.getBody().getRecord().getArray5().setField1(i,0);
        
    m_MsgIn1.getBody().getRecord().getArray5().encode(buff, 0);
    m_MsgIn2.getBody().getRecord().getArray5().decode(buff, 0);
    for (i=0;i<m_MsgIn1.getBody().getRecord().getArray5().getDimension1Size();i++) 
        Assert.AreEqual(((i+1)*7), m_MsgIn2.getBody().getRecord().getArray5().getField1(i));       
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