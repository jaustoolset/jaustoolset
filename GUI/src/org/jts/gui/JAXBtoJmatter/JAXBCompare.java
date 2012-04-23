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

package org.jts.gui.JAXBtoJmatter;

//
// This class compares the marshalled form of two objects.  This has only
// been validated against objects that are JAXB classes.
//
public class JAXBCompare
{
	public static boolean equals(Object obj1, Object obj2)
	{
	    boolean equal = false;
	    
	    // Make sure it's the same type before we go to far...
	    if (obj1.getClass().getPackage().getName() != obj2.getClass().getPackage().getName())
	        return false;
	    
	    //
	    // To test equivalence between the objects, we marshall
	    // both objects.  If their encoded forms are identical, it's the same
	    // object.
	    //
		try
		{	
		    // Marshall the objects according to the JAXB data bindings			
			javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(obj1.getClass().getPackage().getName());
			javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
			java.io.ByteArrayOutputStream stream1 = new java.io.ByteArrayOutputStream();
			java.io.ByteArrayOutputStream stream2 = new java.io.ByteArrayOutputStream();
			marshaller.marshal(obj1, stream1);
			marshaller.marshal(obj2, stream2);
			
			// Interpretations need not be equal for the objects to be equal.  Remove them
			// from the encoding.
			String string1 = stream1.toString();
			while (string1.contains("interpretation=\""))
			    string1 = string1.substring( 0, string1.indexOf("interpretation=\"")) +
		               string1.substring(string1.indexOf("\"", string1.indexOf("interpretation=\"")+ 16)+2, string1.length());
            String string2 = stream2.toString();
			while (string2.contains("interpretation=\""))
			    string2 = string2.substring( 0, string2.indexOf("interpretation=\"")) +
		               string2.substring(string2.indexOf("\"", string2.indexOf("interpretation=\"")+ 16)+2, string2.length());		               

            // Compare as strings
			if (string1.equals( string2 ))
				equal = true;
		}
		catch (javax.xml.bind.JAXBException ex) 
		{
			// Fail silently.  Worse case, we populate our database with a non-unique object.
			//System.out.println("Error marshalling\n");
		}
		
		return equal;	
	} 
}
