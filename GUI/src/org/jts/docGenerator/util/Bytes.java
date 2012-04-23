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

/* jsidl.util.Bytes.java */
package org.jts.docGenerator.util;

/** A set of convenience functions that get the binary representation
* of Java primitives in String format. 
*
* @author Arfath Pasha 
* @author Drew Wood
* @version 2.1 11/2/03
*
*/
public class Bytes 
{

   /** Default constructor.
   *
   */
   public Bytes()
   {
   }
   
   /** Returns a String object representing the specified byte in base 2. 
   *
   * @param bb a byte to be converted 
   *
   * @return a string representation of the value of the specified byte in 
   *         base 2
   */
   public static String byteToBin(byte bb) 
   {
      String str = new String();
      
      for(int ii=7; ii>=0; ii--)
         if(((1<<ii) & bb) != 0)
            str = str.concat("1");
         else
            str = str.concat("0");
      
      return str;
   }
   
   
   /** Returns a String object representing the specified character in base 2.
   *
   * @param cc a character to be converted
   *
   * @return a string representation of the value of the specified character in 
   *         base 2
   */
   public static String charToBin(char cc) 
   {
      String str = new String();
      
      for(int ii=15; ii>=0; ii--)
         if(((1<<ii) & cc) != 0)
            str = str.concat("1");
         else
            str = str.concat("0");
      
      return str;
   }
   
   
   /** Returns a String object representing the specified short interger in 
   * base 2.
   *
   * @param ss a short integer to be converted
   *
   * @return a string representation of the value of the specified short integer  
   *         in base 2
   */
   public static String shortToBin(short ss) 
   {
      String str = new String();
      
      for(int ii=15; ii>=0; ii--)
         if(((1<<ii) & ss) != 0)
            str = str.concat("1");
         else
            str = str.concat("0");
      
      return str;
   }
   
   
   /** Returns a String object representing the specified integer in base 2.
   *
   * @param in an integer to be converted
   *
   * @return a string representation of the value of the specified integer in 
   *         base 2
   */
   public static String intToBin(int in) 
   {
      String str = new String();
      
      for(int ii=31; ii>=0; ii--)
         if(((1<<ii) & in) != 0)
            str = str.concat("1");
         else
            str = str.concat("0");
      
      return str;
   }
   
   /** Returns a String object representing the specified long integer in 
   * base 2.
   *
   * @param in a long integer to be converted
   *
   * @return a string representation of the value of the specified long integer
   *  in base 2
   */
   public static String longToBin(long ln) 
   {
      String str = new String();
      
      for(int ii=63; ii>=0; ii--)
         if(((1<<ii) & ln) != 0)
            str = str.concat("1");
         else
            str = str.concat("0");
      
      return str;
   }
   
   
   /** Returns a String object representing the specified char as a hexadecimal
   * number.
   *
   * @param cc a char to be converted
   *
   * @return a string representation of the value of the specified char in 
   *         base 16
   */
   public static String charToHex(char cc) 
   {
      StringBuffer str = new StringBuffer();
         
      for(int ii=0; ii<4; ii++) {
         int mod = (int)cc%16;
         cc = (char)((int)cc/(int)16);
         
         switch(mod) 
         {
            case 0:
               str = str.append("0");
               break;
            case 1:
               str = str.append("1");
               break;
            case 2:
               str = str.append("2");
               break;
            case 3:
               str = str.append("3");
               break;
            case 4:
               str = str.append("4");
               break;
            case 5:
               str = str.append("5");
               break;
            case 6:
               str = str.append("6");
               break;
            case 7:
               str = str.append("7");
               break;
            case 8:
               str = str.append("8");
               break;
            case 9:
               str = str.append("9");
               break;
            case 10:
               str = str.append("A");
               break;
            case 11:
               str = str.append("B");
               break;
            case 12:
               str = str.append("C");
               break;
            case 13:
               str = str.append("D");
               break;
            case 14:
               str = str.append("E");
               break;
            case 15: 
               str = str.append("F");
               break;
            default:
               System.out.println("error: illegal case in Bytes.charToHex()");
         }  
      }
      
      // reverse str
      str.append("x0");
      str.reverse();
         
      return str.toString();
   }
}
