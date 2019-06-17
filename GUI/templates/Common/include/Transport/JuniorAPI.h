/***********           LICENSE HEADER   *******************************
JR Middleware
Copyright (c)  2008-2019, DeVivo AST, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the copyright holder nor the names of 
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
#ifndef __JUNIOR_API_H
#define __JUNIOR_API_H

#include "Transport/OS.h"

// Extern the definitions to avoid name mangling
extern "C" {

// Define an enumerated list of error codes used by the Junior API.
typedef enum {Ok, NoMessages, InvalidID, Overflow, InitFailed, 
              InvalidParams, Timeout, UnknownError, NotInitialized, NoMemory} JrErrorCode;

// Define the list of valid flags.  These can be logically AND'ed into
// the "flags" field to allow for more than one flag per message.
const unsigned char GuaranteeDelivery = 0x01;
const unsigned char ServiceConnection = 0x02;
const unsigned char ExperimentalFlag  = 0x04;

// Functional interface.  
JrErrorCode DllExport JrSend(long handle,
           unsigned int destination, 
           unsigned int size, 
           const char* buffer,
           int priority = 6,
           int flags = 0,
		   unsigned short msg_id = 0);

JrErrorCode DllExport JrReceive(long handle,
             unsigned int* source,
             unsigned int* bufsize,
             char** buffer,
             int* priority = 0,
             int* flags = 0,
			 unsigned short* msg_id = 0);

JrErrorCode DllExport JrBroadcast(long handle,
              unsigned int size,
              const char* buffer,
              int priority = 6,
			  unsigned short msg_id = 0);

JrErrorCode DllExport JrCheckAllHandles(long* list, int* size_of_list);

JrErrorCode DllExport JrConnect(unsigned int id, 
                               const char* config_file, 
                               long* handle,
                               bool allowWildcards = false);

JrErrorCode DllExport JrDisconnect(long handle);

} // end extern "C"
#endif
