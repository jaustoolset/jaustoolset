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
using System.Collections;

/**
 * <summary>A helper class for managing encoding, decoding,
 * and BitSets for presence vectors and generated bit sets.</summary>
 * <author>Gina Nearing</author>
 * <date>4/1/11</date>
 */
namespace JTS
{
    public class JausUtils
    {
        public const int BYTE_BYTES = 1;
        public const int SHORT_BYTES = 2;
        public const int USHORT_BYTES = 2;
        public const int INT_BYTES = 4;
        public const int UINT_BYTES = 4;
        public const int FLOAT_BYTES = 4;
        public const int LONG_BYTES = 8;
        public const int ULONG_BYTES = 8;
        public const int DOUBLE_BYTES = 8;
        private const int SUFFICIENTLY_LARGE = 10000;

        /// <summary>
        /// Ensures that the encoded or decoded bytes
        /// are in the little endian order.
        /// </summary>
        /// <param name="source">The value to check.</param>
        /// <returns>The value with the correct endianness</returns>
        public static void correctEndianness(ref byte[] source)
        {
            if (BitConverter.IsLittleEndian)
            {
                // There is no need to do anything.
                return;
            }
            else
            {
                // Reverse the bytes from the given object.
                //Array.Reverse(ref source);

                byte[] tmp = new byte[source.Length];
                for (int i = 0; i < source.Length; i++)
                {
                    tmp[i] = source[source.Length - i];
                }
                source = tmp;
            }


        }


        /// <summary>
        /// Translate the given set of bits into
        /// an integer.
        /// </summary>
        /// <param name="pv">The bits to translate</param>
        /// <returns>the value as a uint.</returns>
        public static uint getPVint(BitArray pv)
        {
            uint val = 0;
            for (int i = 0; i < pv.Length; i++)
            {
                if (pv.Get(i))
                {
                    val += (uint)(1 << i);
                }
            }
            return val;
        }

        /// <summary>
        /// Translates a given ulong into a set of bits.
        /// </summary>
        /// <param name="value">the integer to be translated.</param>
        /// <returns>the equivelant bit set.</returns>
        public static BitArray setPV(uint value)
        {
            BitArray pv = new BitArray(SUFFICIENTLY_LARGE);
            int i = 0;
            while (value != 0)
            {
                if (value % 2 != 0)
                {
                    pv.Set(i, true);
                }
                value = value >> 1;
                i++;
            }
            // resize PV.            
            return pv;

        }

        /// <summary>
        /// A helper function to add a set of bytes to a given buffer.
        /// Functionally equivelant to Java.nio.ByteBuffer.put
        /// </summary>
        /// <param name="buffer">The byte buffer to add to.</param>
        /// <param name="obj">Th object to be added to the buffer in byte[] form.</param>
        /// <param name="pos">The position in the buffer to begin adding.</param>
        /// <param name="offset">The number of bytes to be added.</param>
        /// <param name="data">If the byte[] being added needs to check for endianness, or if it's raw data.</param>
        /// <returns>The source buffer with the new bytes.</returns>
        public static byte[] addToBuffer(byte[] buffer, byte[] obj, int pos, int offset, bool data)
        {
            int end = offset + pos;
            if (!data)
            {
                // Ensure that given data is encoded in the right order.
                correctEndianness(ref obj);
            }
            for (int i = 0; pos < end; pos++)
            {
                if (pos == buffer.Length)
                {
                    break;
                }
                buffer[pos] = obj[i];
                i++;
            }

            return buffer;
        }
        /// <summary>
        /// A helper function to add a set of chars to a given buffer.
        /// Functionally equivelant to Java.nio.ByteBuffer.put
        /// </summary>
        /// <param name="buffer">The byte buffer to add to.</param>
        /// <param name="obj">Th string to be added to the buffer in char[] form.</param>
        /// <param name="pos">The position in the buffer to begin adding.</param>
        /// <param name="offset">The number of bytes to be added.</param> 
        /// <returns>The source buffer with the new bytes.</returns>
        public static byte[] addToBuffer(byte[] buffer, char[] obj, int pos, int offset)
        {
            int end = offset + pos;
            int mid = obj.Length + pos;
            int i = 0;
            for (; pos < mid; pos++, i++)
            {
                if (pos == buffer.Length)
                {
                    break;
                }
                buffer[pos] = (byte)obj[i];
            }

            for (; pos < end; pos++)
            {
                buffer[pos] = 0;
            }

            return buffer;
        }

        /// <summary>
        /// A helper function to remove a set of bytes from a given buffer.
        /// Functionally equivelant to Java.nio.ByteBuffer.get
        /// </summary>
        /// <param name="buffer">The byte buffer to remove from.</param>
        /// <param name="pos">The position in the buffer to begin removing.</param>
        /// <param name="offset">The number of bytes to be removed.</param>
        /// <param name="data">If the byte[] being returned needs to check for endianness, or if it's raw data.</param>
        /// <returns>the set of bytes containing the object.</returns>
        public static byte[] getFromBuffer(byte[] buffer, int pos, int offset, bool data)
        {
            byte[] ret = new byte[offset];
            int end = pos + offset;
            for (int i = 0; pos < end; pos++)
            {
                if (pos == buffer.Length)
                {
                    break;
                }
                ret[i] = buffer[pos];
                i++;
            }

            if (!data && pos + offset <= end)
            {
                // Ensure that retrieved data is in the correct order.
                correctEndianness(ref ret);
            }
            return ret;
        }


        /// <summary>
        /// A helper function to remove a set of bytes from a given buffer.
        /// Functionally equivelant to Java.nio.ByteBuffer.get for Strings
        /// </summary>
        /// <param name="buffer">The byte buffer to remove from.</param>
        /// <param name="pos">The position in the buffer to begin removing.</param>
        /// <param name="offset">The number of bytes to be removed.</param>
        /// <param name="str">The boolean flag to distinguish the overloaded function.</param>
        /// <returns>the set of chars containing the string.</returns>
        public static string getFromBuffer(byte[] buffer, int pos, int offset, bool str, bool data)
        {
            char[] ret = new char[offset];
            string ret_str = "";
            int end = pos + offset;
            int i = 0;
            for (; pos < end; pos++, i++)
            {
                if (pos == buffer.Length || buffer[pos] == 0)
                {
                    break;
                }
                ret[i] = (char)buffer[pos];
            }
            ret_str = new String(ret);
            if (offset > i)
            {
                ret_str = ret_str.Substring(0, i);
            }
            return ret_str;
        }

        /// <summary>
        /// Returns the number of bytes taken by a given type.
        /// Its purpose is to make generating code for an unknown
        /// type easier and contain fewer lines of code.
        /// </summary>
        /// <param name="type">The type of the object.</param>
        /// <returns>The number of bytes it will occupy on the buffer.</returns>
        public static int getNumBytes(string type)
        {
            // Types that contain 4 bytes.
            if (type.CompareTo("uint") == 0 || type.CompareTo("int") == 0)
            {
                return UINT_BYTES;
            }
            else if (type.CompareTo("float") == 0)
            {
                return FLOAT_BYTES;
            }
            // Types that contain 2 bytes.
            else if (type.CompareTo("short") == 0 || type.CompareTo("ushort") == 0)
            {
                return USHORT_BYTES;
            }
            // One byte.
            else if (type.CompareTo("byte") == 0 || type.CompareTo("sbyte") == 0)
            {
                return BYTE_BYTES;
            }
            // 8 bytes.
            else if (type.CompareTo("long") == 0 || type.CompareTo("ulong") == 0 || type.CompareTo("double") == 0)
            {
                return ULONG_BYTES;
            }
            else
            {
                // This function should only be called for basic types - 0 should not be returned.
                // Strings are encoded with an accompanying integer 
                //      containing the number of bytes.
                // Generated types are encoded by breaking them down into
                //      primative types and strings.              
                return 0;
            }
        }
    }


}

