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

/* jsidl.util.BinaryOp.java */

package org.jts.docGenerator.util;


/** A set of convenience functions that perform binary operations on 
* primitive Java data types
*
* @author Arfath Pasha 
* @author Drew Wood
* @version 2.1 11/2/03
*
*/
public class BinaryOp 
{

   /** Default constructor.
   *
   */
   protected BinaryOp()
   {
   }
   
   /** Returns the value of the consecutive set of numBit bits in the field 
   * starting at the specified offset.
   *
   */
   public static int getBitField(byte field, int offset, int numBits)
   {
      byte tmp = (byte)(field >> offset);
      byte fltr = (byte)(tmp >> numBits);
      fltr = (byte)(fltr << numBits);
      
      return (int)(tmp ^ fltr); 
   }

   /** Returns an int representing the specified byte. The argument is converted 
   * to an unsigned decimal representation and returned as an int.
   *
   * @param bb a byte to be converted
   *
   * @return a decimal representation of the unsigned value of the argument
   *
   */
   public static int getUnsigned(byte bb) 
   {
      int ii = (int)bb;
      
      if(ii < 0)
         return (int)(-256 ^ ii);
      else 
         return ii;
   }
   
   /** Returns an int representing the specified short. The argument is 
   * converted to an unsigned decimal representation and returned as an int.
   * 
   * @param ss a short to be converted
   *
   * @return a decimal representation of the unsigned value of the argument
   *
   */
   public static int getUnsigned(short ss) 
   {
      
      if(ss < 0)
         return (int)(-65536 ^ ss);
      else 
         return (int)ss;
   }
   
   /** Returns a long representing the specified int. The argument is 
   * converted to an unsigned decimal representation and returned as an long.
   * 
   * @param in an int to be converted
   *
   * @return a decimal representation of the unsigned value of the argument
   *
   */
   public static long getUnsigned(int in) 
   {
      
      if(in < 0)
         return (long)(-(int)Math.pow(2,32) ^ in);
      else 
         return (long)in;
   }
   
   /** returns an unsigned integer for one to three consecutive bytes in the 
   * array at the specified offset.
   *
   */
   public static int getUnsignedInt(byte [] array, int offset, int numBytes)
      throws IllegalArgumentException, IndexOutOfBoundsException 
   {
      if( array == null )
         throw new IllegalArgumentException("null array.");
         
      if( offset < 0 )
         throw new IllegalArgumentException("offset less than 0: "+offset);                                
      
      if( numBytes < 1 || numBytes > 3 )
         throw new IllegalArgumentException("numBytes not in range [1,3]: "
                                                               +numBytes);                                
            
      if( offset+numBytes >= array.length )
         throw new IndexOutOfBoundsException(" array size: "+array.length+
                                   "offset: "+offset+" numBytes: "+numBytes);
                                   
      int ctr = offset+numBytes-1;
      
      int val = getUnsigned( array[ctr--] );
      
      for(int ii=ctr; ii>=offset; ii--)
         val = (val << 8) | getUnsigned( array[ii] );
                                   
      return val;
   }
   
   /** Checks if the bit at the specified index in the byte is set.
   * <p>
   * An exception of type <code>IndexOutOfBoundsException</code> is
   * thrown if the index is not in the range <code>[0..7]</code>
   *
   * @param bb byte to check
   * @param index position of bit to check
   *
   * @return true if the bit is set, else false
   *
   * @exception IndexOutOfBoundsException if the the specified index is out of 
   *            range
   *
   */
   public static boolean isBitSet(byte bb, int index) 
      throws IndexOutOfBoundsException
   {
      if( (index < 0) || (index > 7) )
         throw new IndexOutOfBoundsException(" index " + index +
                      " is out of range");
                      
      byte fltr = (byte)Math.pow(2,index);  
                    
      if ( (byte)(bb & fltr) == fltr ) 
         return true;
      else               
         return false;
   }   
   
   
   /** Checks if the bit at the specified index in the short integer is set.
   * <p>
   * An exception of type <code>IndexOutOfBoundsException</code> is
   * thrown if the index is not in the range <code>[0..15]</code>
   * 
   * @param sh short integer to check
   * @param index position of bit to check
   *
   * @return true if the bit is set, else false
   *
   * @exception IndexOutOfBoundsException if the the specified index is out of 
   *            range
   *
   */
   public static boolean isBitSet(short sh, int index)
      throws IndexOutOfBoundsException
   {
      if( (index < 0) || (index > 15) )
         throw new IndexOutOfBoundsException(" index " + index +
                      " is out of range");
                      
      short fltr = (short)Math.pow(2,index);  
                    
      if ( (short)(sh & fltr) == fltr ) 
         return true;
      else               
         return false;
   }
   
   
   /** Checks if the bit at the specified index in the integer is set.
   * <p>
   * An exception of type <code>IndexOutOfBoundsException</code> is
   * thrown if the index is not in the range <code>[0..31]</code>
   * 
   * @param in integer to check
   * @param index position of bit to check
   *
   * @return true if the bit is set, else false
   *
   * @exception IndexOutOfBoundsException if the the specified index is out of 
   *            range
   *
   */
   public static boolean isBitSet(int in, int index)
      throws IndexOutOfBoundsException
   {
      if( (index < 0) || (index > 31) )
         throw new IndexOutOfBoundsException(" index " + index +
                      " is out of range");
      
      int fltr = (int)Math.pow(2,index);  
                    
      if ( (int)(in & fltr) == fltr ) 
         return true;
      else               
         return false;
   }
   
   
   /** Modifies the specified byte by setting the bit at the given index.
   * <p>
   * An exception of type <code>IndexOutOfBoundsException</code> is
   * thrown if the index is not in the range <code>[0..7]</code>
   * 
   * @param bb byte to modify
   * @param index position of bit to set   
   *
   * @return the modified byte
   *
   * @exception IndexOutOfBoundsException if the the specified index is out of 
   *            range
   *
   */
   public static byte setBit(byte bb, int index)
      throws IndexOutOfBoundsException
   {
      if( (index < 0) || (index > 7) )
         throw new IndexOutOfBoundsException(" index " + index +
                      " is out of range");
      
      byte fltr = (byte)Math.pow(2,index);
      if(index == 7)
         fltr = -128;
      
      return (byte)(bb | fltr);
   }
   
   
   /** Modifies the specified short integer by setting the bit at the given 
   * index.
   * <p>
   * An exception of type <code>IndexOutOfBoundsException</code> is
   * thrown if the index is not in the range <code>[0..15]</code>
   * 
   * @param sh short integer to modify
   * @param index position of bit to set   
   *
   * @return the modified short integer
   *
   * @exception IndexOutOfBoundsException if the the specified index is out of 
   *            range
   *
   */
   public static short setBit(short sh, int index)
      throws IndexOutOfBoundsException
   {
      if( (index < 0) || (index > 15) )
         throw new IndexOutOfBoundsException(" index " + index +
                      " is out of range");
      
      short fltr = (short)Math.pow(2,index);
      if(index == 15)
         fltr = -32768;
      
      return (short)(sh | fltr);
   }
   
   
   /** Modifies the specified integer by setting the bit at the given index.
   * <p>
   * An exception of type <code>IndexOutOfBoundsException</code> is
   * thrown if the index is not in the range <code>[0..31]</code>
   * 
   * @param in integer to modify
   * @param index position of bit to set   
   *
   * @return the modified integer
   *
   * @exception IndexOutOfBoundsException if the the specified index is out of 
   *            range
   *
   */
   public static int setBit(int in, int index)
      throws IndexOutOfBoundsException
   {
      if( (index < 0) || (index > 31) )
         throw new IndexOutOfBoundsException(" index " + index +
                      " is out of range");
      
      int fltr = (int)Math.pow(2,index);
      if(index == 31)
         fltr = -2147483648;
      
      return (int)(in | fltr);
   }   
   
   /** sets the specified unsigned integer into the array at the specified 
   * offset for upto three bytes.
   *
   */
   public static void setUnsignedInt(byte [] array, int value, int offset,
                                                        int numBytes)
      throws IllegalArgumentException, IndexOutOfBoundsException 
   {
      if( array == null )
         throw new IllegalArgumentException("null array.");
         
      if( offset < 0 )
         throw new IllegalArgumentException("offset less than 0: "+offset);                                
      
      if( numBytes < 1 || numBytes > 3 )
         throw new IllegalArgumentException("numBytes not in range [1,3]: "
                                                               +numBytes);                                
            
      if( offset+numBytes >= array.length )
         throw new IndexOutOfBoundsException(" array size: "+array.length+
                                   "offset: "+offset+" numBytes: "+numBytes);
                                         
      int ctr = offset;
      array[ctr++] = (byte)value;
      
      for(int ii=ctr; ii<(offset+numBytes); ii++)
         array[ii] = (byte)(value >> 8);      
   }
   
   private static java.nio.ByteBuffer bbuf = java.nio.ByteBuffer.allocate(2);
   public static char getUnsigned(byte [] array, int offset)
   {
      bbuf.put( 0, array[offset] );
      bbuf.put( 1, array[offset+1] );
      
      return bbuf.getChar( 0 );
   }
}
