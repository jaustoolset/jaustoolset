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
