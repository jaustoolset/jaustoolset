/*! 
 ***********************************************************************
 * @file      JuniorAPI.h
 * @author    Dave Martin, DeVivo AST, Inc.  
 * @date      2008/03/03
 *
 *  Copyright (C) 2008. DeVivo AST, Inc
 *
 *  This file is part of Jr Middleware.
 *
 *  Jr Middleware is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Jr Middleware is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Jr Middleware.  If not, see <http://www.gnu.org/licenses/>.
 *
 ************************************************************************
 */
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
                               long* handle);

JrErrorCode DllExport JrDisconnect(long handle);

} // end extern "C"
#endif
