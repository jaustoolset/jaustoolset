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

/*! 
 ***********************************************************************
 * @file      ChecksumCRC.cpp
 * @author    Dave Martin, DeVivo AST, Inc.  
 * @date      2008/07/03
 *
 * This implementation is directly from SAE AS-5669, with slight
 * style modifications.
 ************************************************************************
 */

#include "Transport/ChecksumCRC.h"


void DeVivo::Junior::crc_accumulate (unsigned char data, unsigned short *crcAccum) 
{
    //  Accumulate one byte of data into the CRC.
    unsigned char tmp;
        
    tmp = data ^ (unsigned char)(*crcAccum & 0xff);
    tmp ^= (tmp << 4);
    *crcAccum = (*crcAccum >> 8) ^ (tmp << 8)
              ^ (tmp << 3) ^ (tmp >> 4);
}

unsigned short DeVivo::Junior::crc_calculate (unsigned char *pBuffer, 
                                              unsigned short init_value,
                                              int length)
{
    unsigned short crcTmp = init_value;
    unsigned char *pTmp = pBuffer;

    //  For a "message" of length bytes contained in the unsigned character
    //  array pointed to by pBuffer, calculate the CRC.
    for (int i=0; i<length; i++)
        crc_accumulate(*pTmp++, &crcTmp);
    return crcTmp;      
}
