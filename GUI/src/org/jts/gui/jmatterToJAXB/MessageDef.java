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

package org.jts.gui.jmatterToJAXB;

/* Converts JMatter MessageDef to a JAXB MessageDef.
*/
public class MessageDef {

  public static org.jts.jsidl.binding.MessageDef convert( com.u2d.generated.MessageDef messageDef ) {
    if( messageDef == null )
      return null;
    
    org.jts.jsidl.binding.MessageDef md = new org.jts.jsidl.binding.MessageDef();
    
    md.setName( messageDef.getName().toString() );
     
     md.setMessageId( getBytes(messageDef.getMessageId().toString()) );
     md.setIsCommand( messageDef.getIsCommand().booleanValue() );
     md.setDescription( Description.convert( messageDef) );
     
     // header
     md.setHeader( Header.convert( messageDef.getHeader() ) );
     
     // body
     md.setBody( Body.convert( messageDef.getBody() ) );
     
     // footer
     md.setFooter( Footer.convert( messageDef.getFooter() ) );
    
    return md;
  }


	/* Converts a string representation of the message id into a byte array of size 2. */
	private static byte[] getBytes(String mid)
	{
			byte[] midb = new byte[2];

			if (mid == null || mid.length() != 4)
					return null;

			char[] nibble = new char[4];

			for (int ii = 0; ii < 4; ii++)
			{
					nibble[ii] = mid.charAt(ii);

					if (nibble[ii] == 'a' || nibble[ii] == 'A')
							nibble[ii] = 10 + '0';
					else if (nibble[ii] == 'b' || nibble[ii] == 'B')
							nibble[ii] = 11 + '0';
					else if (nibble[ii] == 'c' || nibble[ii] == 'C')
							nibble[ii] = 12 + '0';
					else if (nibble[ii] == 'd' || nibble[ii] == 'D')
							nibble[ii] = 13 + '0';
					else if (nibble[ii] == 'e' || nibble[ii] == 'E')
							nibble[ii] = 14 + '0';
					else if (nibble[ii] == 'f' || nibble[ii] == 'F')
							nibble[ii] = 15 + '0';
					else if (nibble[ii] >= '0' && nibble[ii] <= '9') ;
					else 
							return null; // invalid nibble.  return prematurely.
			}

			midb[0] = (byte)((nibble[0] - '0') << 4 | (nibble[1] - '0'));
			midb[1] = (byte)((nibble[2] - '0') << 4 | (nibble[3] - '0'));

			return midb;
	}
}