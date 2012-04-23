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

/* ByteArray.java */

package org.jts.docGenerator.util;

/** A convenience object that provides a reference to an array object whose size
* is automatically increased when required. When data size is reduced through 
* deletion or overwriting the physical size of this array is not reduced. This
* allows for the array to be reused for varying size data with minimal 
* array object creation.
*
* @author Arfath Pasha $Date: 2005/06/13 16:38:56 $ $Revision: 1.1.1.1 $
*
*/
public class ByteArray
{
   public byte [] array = null;
   private int dataLength = 0;
   
   public ByteArray()
   {
   }
   
   /** resizes the array if it's length is less than the specified size.
   *
   */
   private void checkSize(int size)
   {
      if( array != null && array.length >= size )
         return;
      
      // resize
      byte [] newArray = null;
      newArray = new byte[ size ];
      
      // copy existing data to new array
      if( array != null )
         for(int ii=0; ii<array.length; ii++)
            newArray[ii] = array[ii];      
      
      array = newArray;
   }
   
   /** sets the actual data length to 0.
   *
   */
   public void init()
   {
      dataLength = 0;
   }
   
   /** returns the actual data length. this length may be less than or equal to
   * the array length
   *
   */
   public int getDataLength()
   {
      return dataLength;
   }
   
   /** inserts the specified data at the specified location in this array.
   *
   * @return modified array
   *
   */
   public byte [] insert( byte [] from, int fromOffset, int toOffset, int len)
   {
      // resize array if necessary
      checkSize( dataLength+len );
      
      // make space for the insert
      int qtyToMove = dataLength-toOffset;
      for(int ii=0; ii<qtyToMove; ii++ )
         array[toOffset+len+qtyToMove-ii] = array[toOffset+qtyToMove-ii];
         
      // insert new data
      for(int ii=0; ii<len; ii++)
         array[toOffset+ii] = from[fromOffset+ii];
         
      // recompute data length
      dataLength += len;
      
      return array;
   }
   
   /** writes the specified data to the end of this array
   *
   * @return modified array
   *
   */
   public byte [] append( byte [] from, int fromOffset, int len)
   {
      // resize array if necessary
      checkSize( dataLength+len );

      // write new data at the end of the array
      for(int ii=0; ii<len; ii++)
         array[dataLength+ii] = from[ fromOffset+ii ];
      
      // recompute data length
      dataLength += len;
      
      return array;  
   }
   
   /** overwrites the contents of this array with the specified data. any 
   * existing data that exists from <code>toOffset</code> to 
   * <code>toOffset+len</code> is overwritten. 
   * 
   * @return returns the modified array
   *
   */
   public byte [] overwrite( byte [] from, int fromOffset, int toOffset, int len )
   {
      
      checkSize( toOffset+len );
         
      // copy new data
      for(int ii=0; ii<len; ii++)
         array[toOffset+ii] = from[fromOffset+ii];
      
      // recompute data length
      if( dataLength < (toOffset+len) )
         dataLength = toOffset+len;
         
      return array;
   }
   
   /** deletes the specified sections of this array and compacts the array.
   *
   * @return returns the modified array.
   *
   */
   public byte [] delete( int offset, int len )
   {
      // delete len bytes
      for(int ii=0; ii<len; ii++)
         array[offset+ii] = array[offset+len+ii];
         
      // recompute data length
      dataLength -= len;
       
      return array;
   }
}