/*! 
 ***********************************************************************
 * @file      JuniorAPI.cpp
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
