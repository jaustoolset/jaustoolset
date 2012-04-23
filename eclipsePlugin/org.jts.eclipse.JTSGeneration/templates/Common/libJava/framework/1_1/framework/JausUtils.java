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

package framework;

/*
* This class provides tools needed by the generated code
* 
* @version 			01/04/2011
* @author			Gina Nearing
*
*/

import java.util.BitSet;

public class JausUtils
{
	public static final int DOUBLE_BYTES = 16;
	public static final int FLOAT_BYTES = 8;
	public static final int LONG_BYTES = 8;
	public static final int INT_BYTES = 4;
	public static final int CHAR_BYTES = 2;
	public static final int SHORT_BYTES = 2;
	public static final int BYTE_BYTES = 1;
	public static final int BOOLEAN_BYTES = 1;
	public static final int EMPTY_BYTES = 0;
	

	/*
	* A method that converts a BitSet into a long that can then be
	* encoded into the byte buffer.
  	*
	* @param pv the bit set
	* @return the long value of the bit set.
	*/
	public static long getPVLong(BitSet pv)
	{
		long value = 0;
		for(int i= 0; i<pv.size(); i++)
		{
			if(pv.get(i))
			{
				value += ((1<<i) & 0xffffffffL);
			}
		}
		return value;
	}

	/*
	* A method that converts a BitSet into a int that can then be
	* encoded into the byte buffer.
  	*
	* @param pv the bit set
	* @return the long value of the bit set
	*/
	public static int getPVInt(BitSet pv)
	{
		int value = 0;
		for(int i= 0; i<pv.size(); i++)
		{
			if(pv.get(i))
			{
				value += 1<<i;
			}
		}
		return value;
	}

	/*
	* A method that creates a BitSet from an int that was
	* encoded in the byte buffer.
  	*
	* @return pv the Presence Vector bit set
	* @param the long value of the Presence Vector.
	*/
	public static BitSet setPV(int value)
	{
		BitSet pv = new BitSet();
		int i = 0;
		while(value!=0)
		{
			// If the current value is odd, 
			if(value % 2 !=0)
			{
				//System.out.println("\tSetting " + i);
				pv.set(i);
			}			
			value = value >>1;
			i++;
		}

		return pv;
	}
	/*
	* A method that creates a BitSet from a long that was
	* encoded in the byte buffer.
  	*
	* @return pv the Presence Vector bit set
	* @param the long value of the Presence Vector.
	*/
	public static BitSet setPV(long value)
	{
		BitSet pv = new BitSet();
		int i = 0;
		while(value!=0)
		{
			// If the current value is odd, 
			if(value % 2 !=0)
			{
				pv.set(i);
			}			
			value = value >>1;
			i++;
		}

		return pv;
	}
	/*
	* @param type the data type whose number of bytes we need
	* @return the number of bytes that the object will take on the byte buffer in order to be compatable with the C++ framework
	*
	*/
	public static int getNumBytes(String type)
	{
		
		if (type.equals("double"))
		{
			return DOUBLE_BYTES;
		}
		else if (type.equals("float") || type.equals("long"))
		{
			return LONG_BYTES;
		}
		else if (type.equals("int"))
		{
			return INT_BYTES;
		}
		else if (type.equals("short") || type.equals("char"))
		{
			return SHORT_BYTES;
		}
		else if (type.equals("byte") || type.equals("boolean"))
		{
			return BOOLEAN_BYTES;
		}

		else
		{
			// it is a more complex object - an array or class.
			return EMPTY_BYTES;
		}
	}

}
