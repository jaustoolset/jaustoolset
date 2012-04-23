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

#ifndef JAUS_UTILS_H
#define JAUS_UTILS_H


#include <bitset>
#include <string>
#include <vector>
#include <cstring>

#include "Transport/OS.h"
#include "jFixedLengthString.h"

typedef uint8_t jUnsignedByte;
typedef uint16_t jUnsignedShortInteger;
typedef uint32_t jUnsignedInteger;
typedef uint64_t jUnsignedLongInteger;
typedef int8_t jByte;
typedef int16_t jShortInteger;
typedef int32_t jInteger;
typedef int64_t jLongInteger; 
typedef float jFloat;
typedef double jLongFloat;
typedef std::string jVariableLengthString;

namespace JSIDL_v_1_0
{

template <class T>
T correctEndianness(T hVal)
{
    int i, size=sizeof(T);
    T jVal;
    short a = 0x0100;
    bool bigEndian = ( *((unsigned char*) (&a)) != 0);
    if (bigEndian) {
        /* swap bytes */
        for (i=0; i<size; i++)
            memcpy(((unsigned char*)(&jVal)+i),((unsigned char*)(&hVal)+size-i-1),1);
    }
    else {
        jVal = hVal;
    }
    return jVal;
};


/// Needed by Component Message
template <class T>
T getValueFromBits(T data, int start, int length)
{
	std::bitset<sizeof(T)*8> b_data (data);
	std::bitset<sizeof(T)*8> b_value;
	for(int i=0; i<length; i++)
	{
		b_value[i] = b_data[start+i];
	}
	return (T)b_value.to_ulong();
};
	
};

#endif	// JAUS_UTILS_H

