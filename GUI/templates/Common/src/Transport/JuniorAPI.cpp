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
#include "Transport/JuniorAPI.h"
#include "Transport/JuniorMgr.h"
#include <vector>

using namespace DeVivo::Junior;
static std::vector<long> handles;


// This function checks all known handles for pending
// messages.  Any handle with 1 or more messages
// waiting is returned in the list.  This function does 
// not allocate any memory; therefore, the list must be
// allocated by the calling application, with a maximum
// size passed in 'size_of_list'.  This value will be modified
// to equal the total number of handles with messages waiting.
//
JrErrorCode DllExport JrCheckAllHandles(long* list, int* size_of_list)
{
    JrErrorCode ret = Ok;
    int count = 0;
    if (list == NULL) return InvalidParams;
	if (size_of_list == NULL) return InvalidParams;

    // Check each known handle for outstanding messages.
    for (int i=0; i < handles.size(); i++)
    {
        if (handles[i] == 0) continue;
        if (((JuniorMgr*) handles[i])->pending())
        {
            if (count < *size_of_list) list[count] = handles[i];
            count++;
        }
    }

    // If we actually have more handles with messages than 
    // the list allows us to return, mark as Overflow.
    if (count > *size_of_list) ret = Overflow;
    *size_of_list = count;
    return ret;
}


JrErrorCode DllExport JrSend(long handle,
           unsigned int destination, 
           unsigned int bufsize, 
           const char* buffer,
           int priority,
           int flags,
		   unsigned short msg_id )
{
    if (handle == 0) return NotInitialized;
    JuniorMgr* mgr = (JuniorMgr*) handle;
    return (mgr->sendto(destination, bufsize, buffer, priority, flags, msg_id));
}

JrErrorCode DllExport JrBroadcast(long handle,
              unsigned int bufsize,
              const char* buffer,
              int priority,
              unsigned short msg_id)
{
    return JrSend(handle, 0xFFFFFFFF, bufsize, buffer, priority, 0, msg_id);
}

JrErrorCode DllExport JrReceive(long handle,
             unsigned int* sender,
             unsigned int* bufsize,
             char** buffer,
             int* priority,
             int* flags,
			 unsigned short* msg_id)
{
    if (handle == 0) return NotInitialized;
    JuniorMgr* mgr = (JuniorMgr*) handle;
    return (mgr->recvfrom(sender, bufsize, buffer, priority, flags, msg_id));
}

JrErrorCode DllExport JrConnect(unsigned int id, const char* config_file, long* handle)
{
    if (handle == NULL) return InitFailed;

    // Create and initialize Junior Manager, to manage this 
    // connection to the RTE.  
    JuniorMgr* mgr = new JuniorMgr();
    JrErrorCode ret;
    if ((config_file == NULL) || strlen(config_file) == 0) 
        ret = mgr->connect(id, "");
    else 
        ret = mgr->connect(id, config_file);
    if (ret != Ok)
    {
        delete mgr;
        *handle = 0;
    }
    else
    {
        *handle = (long)mgr;
        handles.push_back((long) mgr);
    }
    return ret;
}

JrErrorCode DllExport JrDisconnect(long handle)
{    
    if (handle == 0) return NotInitialized;
    JuniorMgr* mgr = (JuniorMgr*) handle;
    delete(mgr);

    // find it in the static list
    for (int i=0; i < handles.size(); i++)
    {
        if (handles[i] == handle)
        {
            handles[i] = 0;
            break;
        }
    }
    return Ok;
}
