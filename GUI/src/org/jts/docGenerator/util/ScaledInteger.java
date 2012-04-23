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

/* jsidl.util.ScaledInteger.java */

package org.jts.docGenerator.util;


/** Contains a set of methods that convert 8 bit, 16 bit, 32 bit and 64 bit 
* integer values to real numbers, and real numbers back to integers values. This
* class uses the Scaled Integer concept defined in JAUS to perform these 
* conversions.
* <p>
* Since Java does not have unsigned primitives, widening conversions are 
* performed when unsigned values are either required as arguments to a function
* or returned from a function.
* <p>
* Conversions to and from an unsigned long is not possible since the Java long 
* primitive cannot be converted to a wider primitive. If the need for such a 
* conversion arises, this form of conversion must be handled by a special class
* that defines the new data type <code>unsigned long</code>.
*
* @author Arfath Pasha 
* @author Drew Wood
* @version 2.1 11/12/03
*
*/
public class ScaledInteger 
{
   /** signed byte range */
   private static final int BYTE_RANGE = 254;
   /** signed int range */
   private static final long INT_RANGE = (long)4.294967294e9;
   /** signed short range */
   private static final int SHORT_RANGE = 65534;
   /** unsigned byte range */
   private static final int U_BYTE_RANGE = 255;
   /** unsigned int range */
   private static final long U_INT_RANGE = (long)4.294967295e9;
   /** unsigned short range */
   private static final int U_SHORT_RANGE = 65535;
   
   /** maximum value of a signed byte 127 */
   public static final byte MAX_BYTE = 127;
   /** maximum value of a signed int 2147483647 */
   public static final int MAX_INT = (int)2.147483647e9;
   /** maximum value of a signed long 9223372036854775807 */
   public static final long MAX_LONG = (long)9.223372036854775807e18;
   /** maximum value of a signed short 32767 */
   public static final short MAX_SHORT = 32767;
   /** maximum value of an unsigned byte 255 */
   public static final short MAX_U_BYTE = 255;
   /** maximum value of an unsigned int 4294967295 */
   public static final long MAX_U_INT = (long)4.294967295e9;
   /** maximum value of an unsigned short 65535 */
   public static final int MAX_U_SHORT = 65535;
   /** minimum value of a signed byte -128 */
   public static final byte MIN_BYTE = -128;
   /** minimum value of a signed int -2147483648 */
   public static final int MIN_INT = (int)-2.147483648e9;
   /** minimum value of a signed long -9223372036854775808 */
   public static final long MIN_LONG = (long)-9.223372036854775808e18;
   /** minimum value of a signed short -32768 */
   public static final short MIN_SHORT = -32768;
   

   /** Default constructor.
   *
   */
   protected ScaledInteger()
   {
   }

   /** Returns a real number in the range [min..max] that corresponds to the 
   * specified signed byte integer. 
   * <p>
   * If the value of the specified signed byte integer is outside the range 
   * [0..{@link jsidl.util.ScaledInteger#MAX_U_BYTE}], it is set to the nearest 
   * limiting value of the range.
   *
   * @param bb signed byte
   * @param min lower limit of the real number
   * @param max upper limit of the real number
   *
   * @return a real number that corresponds to the first argument
   *
   */
   public static double byteToReal(byte bb, double min, double max)
   {
      if(bb < MIN_BYTE) bb = MIN_BYTE;
      if(bb > MAX_BYTE) bb = MAX_BYTE;
         
      double scaleFactor = (max-min)/BYTE_RANGE;
      double bias = (min+max)/2; 
      
      return bb*scaleFactor + bias;
   }

   /** Returns a real number in the range [min..max] that corresponds to the 
   * specified signed integer.  
   *
   * @param in a signed integer
   * @param min lower limit of the real number
   * @param max upper limit of the real number
   *
   * @return a real number that corresponds to the first argument
   *
   */
   public static double intToReal(int in, double min, double max)
   {   
      double scaleFactor = (max-min)/INT_RANGE;
      double bias = (max+min)/2; 
      
      return in*scaleFactor + bias;
   }

   /** Returns a scaled signed byte integer value, that corresponds to the 
   * specified real number in the range [min..max]. 
   * <p>
   * If the value of the real number is outside the range [min..max], it is set 
   * to the nearest limiting value of the range.
   *
   * @param real a real number in the range [min..max]
   * @param min lower limit of the real number
   * @param max upper limit of the real number
   *
   * @return a scaled signed byte integer value held in a short
   *
   */
   public static byte realToByte(double real, double min, double max)
   {
      double scaleFactor = (max-min)/BYTE_RANGE;
      double bias = (min+max)/2;
      
      // limit real number between min and max
      if(real < min) real = min;
      if(real > max) real = max; 
      
      return (byte)((real - bias)/scaleFactor);
   }

   /** Returns a scaled signed integer that corresponds to the specified real 
   * number in the range [min..max]. 
   * <p>
   * If the value of the real number is outside the range [min..max], it is set 
   * to the nearest limiting value of the range.
   *
   * @param real a real number in the range [min..max]
   * @param min lower limit of the real number 
   * @param max upper limit of the real number
   *
   * @return a scaled signed integer
   *
   */
   public static int realToInt(double real, double min, double max)
   {
      int in = 0;
      double scaleFactor = (max-min)/INT_RANGE;
      double bias = (max+min)/2;
      
      // limit real number between min and max
      if(real < min) real = min;
      if(real > max) real = max;
      
      // calculate rounded integer value
      if(real < 0.0) 
         in = (int)((real - bias)/scaleFactor -0.5);
      else 
         in = (int)((real - bias)/scaleFactor +0.5);
      
      return in; 
   }

   
   /** Returns a scaled signed short integer that corresponds to the specified 
   * real number in the range [min..max]. 
   * <p>
   * If the value of the real number is outside the range [min..max], it is set 
   * to the nearest limiting value of the range.
   *
   * @param real a real number in the range [min..max]
   * @param min lower limit of the real number
   * @param max upper limit of the real number
   *
   * @return a scaled signed short integer
   *
   */
   public static short realToShort(double real, double min, double max)
   {
      short in = 0;
      double scaleFactor = (max-min)/SHORT_RANGE;
      double bias = (max+min)/2; 
      
      // limit real number between min and max
      if(real < min) real = min;
      if(real > max) real = max;
      
      // calculate rounded integer value
      if(real < 0.0) 
         in = (short)((real - bias)/scaleFactor -0.5);
      else 
         in = (short)((real - bias)/scaleFactor +0.5);
         
      return in;
   }

   
   /** Returns a scaled unsigned byte integer value, that corresponds to the 
   * specified real number in the range [min..max]. 
   * <p>
   * If the value of the real number is outside the range [min..max], it is set 
   * to the nearest limiting value of the range.
   *
   * @param real a real number in the range [min..max]
   * @param min lower limit of the real number
   * @param max upper limit of the real number
   *
   * @return a scaled unsigned byte integer value held in a short
   *
   */
   public static short realToUByte(double real, double min, double max)
   {
      double scaleFactor = (max-min)/U_BYTE_RANGE;
      double bias = min;
      
      // limit real number between min and max
      if(real < min) real = min;
      if(real > max) real = max; 
      
      return (short)((real - bias)/scaleFactor);
   }

   
   /** Returns a scaled unsigned integer value, that corresponds to the 
   * specified real number in the range [min..max]. 
   * <p>
   * If the value of the real number is outside the range [min..max], it is set 
   * to the nearest limiting value of the range.
   *
   * @param real a real number in the range [min..max]
   * @param min lower limit of the real number
   * @param max upper limit of the real number
   *
   * @return a scaled unsigned integer value held in a long
   *
   */
   public static long realToUInt(double real, double min, double max)
   {  
      double scaleFactor = (max-min)/U_INT_RANGE;
      double bias = min;
      
      // limit real number between min and max
      if(real < min) real = min;
      if(real > max) real = max; 
      
      return (long)((real - bias)/scaleFactor);
   }

   
   /** Returns a scaled unsigned short integer value, that corresponds to the 
   * specified real number in the range [min..max]. 
   * <p>
   * If the value of the real number is outside the range [min..max], it is set 
   * to the nearest limiting value of the range.
   *
   * @param real a real number in the range [min..max]
   * @param min lower limit of the real number
   * @param max upper limit of the real number
   *
   * @return a scaled unsigned short integer value held in an int
   *
   */
   public static int realToUShort(double real, double min, double max)
   {
      double scaleFactor = (max-min)/U_SHORT_RANGE;
      double bias = min;
      
      // limit real number between min and max
      if(real < min) real = min;
      if(real > max) real = max; 
      
      return (int)((real - bias)/scaleFactor);
   }

   
   /** Returns a real number in the range [min..max] that corresponds to the 
   * specified signed short integer.  
   *
   * @param in a signed short integer
   * @param min lower limit of the real number
   * @param max upper limit of the real number
   *
   * @return a real number that corresponds to the first argument
   *
   */
   public static double shortToReal(short sh, double min, double max)
   {
      double scaleFactor = (max-min)/SHORT_RANGE;
      double bias = (max+min)/2; 
      
      return sh*scaleFactor + bias;
   }

   
   /** Returns a real number in the range [min..max] that corresponds to the 
   * specified unsigned byte integer. 
   * <p>
   * If the value of the specified unsigned byte integer is outside the range 
   * [0..{@link jsidl.util.ScaledInteger#MAX_U_BYTE}], it is set to the nearest 
   * limiting value of the range.
   *
   * @param uByte unsigned byte integer value held in a short
   * @param min lower limit of the real number
   * @param max upper limit of the real number
   *
   * @return a real number that corresponds to the first argument
   *
   */
   public static double uByteToReal(short uByte, double min, double max)
   {
      if(uByte < 0) uByte = 0;
      if(uByte > MAX_U_BYTE) uByte = MAX_U_BYTE;
         
      double scaleFactor = (max-min)/U_BYTE_RANGE;
      double bias = min; 
      
      return uByte*scaleFactor + bias;
   }

   
   /** Returns a real number in the range [min..max] that corresponds to the 
   * specified unsigned integer. 
   * <p>
   * If the value of the specified unsigned integer is outside the range 
   * [0..{@link jsidl.util.ScaledInteger#MAX_U_INT}], it is set to the nearest 
   * limiting value of the range.
   *
   * @param uIn unsigned integer value held in a long
   * @param min lower limit of the real number
   * @param max upper limit of the real number
   *
   * @return a real number that corresponds to the first argument
   *
   */
   public static double uIntToReal(long uIn, double min, double max)
   {
      if(uIn < 0) uIn = 0;
      if(uIn > MAX_U_INT) uIn = MAX_U_INT;
   
      double scaleFactor = (max-min)/U_INT_RANGE;
      double bias = min; 
      
      return uIn*scaleFactor + bias;
   }

   
   /** Returns a real number in the range [min..max] that corresponds to the 
   * specified unsigned short integer. 
   * <p>
   * If the value of the specified unsigned short integer is outside the range 
   * [0..{@link jsidl.util.ScaledInteger#MAX_U_SHORT}], it is set to the nearest 
   * limiting value of the range.
   *
   * @param uShort unsigned short integer value held in an int
   * @param min lower limit of the real number
   * @param max upper limit of the real number
   *
   * @return a real number that corresponds to the first argument
   *
   */
   public static double uShortToReal(int uShort, double min, double max)
   {
      if(uShort < 0) uShort = 0;
      if(uShort > MAX_U_SHORT) uShort = MAX_U_SHORT;
   
      double scaleFactor = (double)(max-min)/U_SHORT_RANGE;
      double bias = min; 
      
      return uShort*scaleFactor + bias;
   }
}
