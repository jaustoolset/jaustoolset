/*! 
 ***********************************************************************
 * @file      JuniorMgr.h
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
#include "Transport.h"
#include "JuniorAPI.h"
#include "OS.h"
#include <stdio.h>
#include <stdlib.h>

namespace DeVivo {
namespace Junior {

typedef std::pair<JAUS_ID, unsigned short> MsgId;
typedef std::pair<unsigned long, MsgId> TimeStampedMsgId;
typedef std::list<TimeStampedMsgId> MsgIdList;
typedef std::list<TimeStampedMsgId>::iterator MsgIdListIter;
const int JrMaxPriority = 15;

class JuniorMgr
{
public:

    JuniorMgr();
    ~JuniorMgr();

    // The public functions mirror the API equivalents.
    JrErrorCode sendto( unsigned int destination, unsigned int size, 
                const char* buffer, int priority, int flags, MessageCode code = 0);

    JrErrorCode recvfrom( unsigned int* sender, unsigned int* bufsize,
                  char** buffer, int* priority, int* flags, MessageCode* code = NULL);
    
    JrErrorCode connect(unsigned int id, std::string config_file);

    unsigned char pending( );

private:
 
    // Define a couple of private helper functions
    unsigned int umin(unsigned int x, unsigned int y);
    void sendAckMsg(Message* source);
	void sendOrBroadcast(Message& msg);
    bool addMsgToBuffer(Message* msg);
    void checkLargeMsgBuffer();
    bool isDuplicateMsg(Message* msg);
    TimeStampedMsgListIter searchMsgList(TimeStampedMsgList& list, 
                                         JAUS_ID sender, 
                                         unsigned short seqnum);

    // Private data.
    MessageList        _buffers[JrMaxPriority+1];
    TimeStampedMsgList _largeMsgBuffer;
    JAUS_ID            _id;
    Transport*         _transport;
    unsigned short     _message_counter;
    MsgIdList          _recentMsgs;
    unsigned int       _max_retries;
    unsigned int       _ack_timeout;
    unsigned int       _msg_count;
    unsigned int       _max_msg_size;

    // Configuration data
    unsigned short _maxMsgHistory;      // as a message count
    unsigned short _oldMsgTimeout;      // in seconds
    unsigned char  _detectDuplicates; 
};

inline TimeStampedMsgListIter JuniorMgr::searchMsgList(
                                         TimeStampedMsgList& list, 
                                         JAUS_ID sender, 
                                         unsigned short seqnum)
{
    for (TimeStampedMsgListIter iter = list.begin();
         iter != list.end(); iter++)
    {
        if ((iter->second->getSourceId() == sender) &&
            (iter->second->getSequenceNumber() == seqnum))
            return iter;
    }
    return list.end();
}

}} // namespace DeVivo::Junior
