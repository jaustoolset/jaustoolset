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

package org.jts.gui.util;

public class TypeConverter {

/**
	* Convert a byte[] array to readable string format. This makes the "hex" readable!
	* This code was obtained online at: http://www.devx.com/tips/Tip/13540
	* @return result String buffer in String format 
	* @param in byte[] buffer to convert to string format
	*/
	public static String byteArrayToHexString(byte in[]) 
	{

	    byte ch = 0x00;
	    int i = 0; 
	    if (in == null || in.length <= 0) return null;

	    String pseudo[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8",	"9", "A", "B", "C", "D", "E", "F"};

	    StringBuffer out = new StringBuffer(in.length * 2);
	    while (i < in.length) 
	    {
	        ch = (byte) (in[i] & 0xF0); 	// Strip off high nibble
	        ch = (byte) (ch >>> 4); 		// shift the bits down
	        ch = (byte) (ch & 0x0F); 		// must do this is high order bit is on!
	        out.append(pseudo[(int)ch]); 	// convert the nibble to a String Character
	        ch = (byte) (in[i] & 0x0F); 	// Strip off low nibble 
	        out.append(pseudo[(int)ch]); 	// convert the nibble to a String Character
	        i++;
	    }

	    String rslt = new String(out);
	    
	    // we always want our hex strings to be uppercase to resolve them consistently within the database
	    rslt = rslt.toUpperCase();

	    return rslt;
	}

        /**
         * Return an int representation of a byte array of length 2.
         * Results undefined if the byte array is not of length 2.
         * Assumes the bytes are unsigned data.
         * @param twoBytes Byte array, length 2
         * @return Integer representation of twoBytes.
         */
        public static int twoBytesToInt(byte[] twoBytes) {
            // bitwise and with mask to ensure no sign-extending behavior takes place
            return (twoBytes[0] << 8) | (twoBytes[1] & 0xff);
        }
}