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
#ifndef  __JR_MESSAGE_H
#define  __JR_MESSAGE_H

#include "Archive.h"
#include "Types.h"

namespace DeVivo {
namespace Junior {

// Define a Message Code
typedef unsigned short MessageCode;
const unsigned short Connect = 1;
const unsigned short Accept  = 2;
const unsigned short Cancel  = 3;

// Define lists composed of Messages
class Message;
typedef std::list<Message*> MessageList;
typedef std::list<Message*>::iterator MessageListIter;
typedef std::pair<unsigned long, Message*> TimeStampedMsg;
typedef std::list<TimeStampedMsg> TimeStampedMsgList;
typedef std::list<TimeStampedMsg>::iterator TimeStampedMsgListIter;

class Message
{
public:
    Message():
            _code(0),_source(0),_destination(0), _priority(6), _acknak(0), 
			_control(None),_bcast(0), _service_connection(0), _experimental(0), _payload(){}
   ~Message(){}

    //
    // Functions to get/set the message code
    //
    MessageCode getMessageCode(){return _code;}
    void setMessageCode(MessageCode code){_code = code;}

    //
    // Functions to set the source and destination numeric id for the message
    //
    void setSourceId(JAUS_ID source){_source=source;}
    JAUS_ID getSourceId(){return _source;}

    void setDestinationId(JAUS_ID destination){_destination=destination;}
    JAUS_ID getDestinationId(){return _destination;}

    //
    // Functions for priority handling.  Note that AS5669A
	// uses a different meaning of priority.  Rather
	// than a 0-16 value, it uses a 0-3 range.  We need
	// to map one to the other.
    //
    void setPriority(unsigned char prio){_priority=prio;}
    unsigned char getPriority(){return _priority;}
	void setScaledPriority(unsigned char prio){_priority = (3*prio)+3;}
	unsigned char getScaledPriority()
	{
		if (_priority == 15) return 3;
		return ((_priority-3)/3);
	}
	
    //
    // Functions for ack/nak
    //
    void setAckNakFlag(char flag){_acknak=flag;}
    char getAckNakFlag(){return _acknak;}

    //
    // Functions for service connection
    //
    void setServiceConnection(char flag){_service_connection = flag;}
    char getServiceConnection(){return _service_connection;}

    //
    // Functions for experimental bit
    //
    void setExperimental(char flag){_experimental = flag;}
    char getExperimental(){return _experimental;}

    //
    // Functions for broadcast flag
    //
    void setBroadcast(char flag){_bcast = flag;}
    char getBroadcast(){return _bcast;}

    //
    // Functions for data control (large message handling)
    //
	typedef enum {None, FirstMsg, MiddleMsg, MiddleResentMsg, LastMsg} DataControlFlag;
    void setDataControlFlag(DataControlFlag flag){_control=flag;}
    DataControlFlag getDataControlFlag(){return _control;}
	char getDataControlFlagAsChar(MsgVersion version);
	void setDataControlFlagAsChar( char flag, MsgVersion version );

    //
    // Functions for sequence number
    //
    void setSequenceNumber(unsigned short seq){_sequence = seq;}
    unsigned short getSequenceNumber(){return _sequence;}

    //
    // Functions for handling the payload
    //
    void setPayload(unsigned int size, const char* data);
    void getPayload(unsigned int& size, char*& data);
    Archive& getPayload(){return _payload;}

    //
    // Returns the packed length of data (without headers)
    //
    unsigned short getDataLength();

protected:

    MessageCode     _code;
    JAUS_ID         _source;
    JAUS_ID         _destination;
    unsigned char   _priority;
    char            _acknak;
    DataControlFlag _control;
	char			_bcast;
    unsigned short  _sequence;
    Archive         _payload;
    char            _service_connection;
    char            _experimental;

};

inline unsigned short Message::getDataLength()
{
    // Return the length of the payload
    return _payload.getArchiveLength();
}

inline void Message::setPayload(unsigned int size, const char* data)
{
    // Copy the given data to the internal Archive
    _payload.setData( data, size );
}

inline void Message::getPayload(unsigned int& size, char*& data)
{
    // Return a pointer to the data buffer, and its size
    size = _payload.getArchiveLength();
    data = _payload.getArchive();
}

inline char Message::getDataControlFlagAsChar(MsgVersion version)
{
	// Note the change in values for 5669 versus 5669A
	if (_control == None) return 0;
	if (_control == FirstMsg) return 1;
	if (_control == MiddleMsg) return 2;
	if (_control == MiddleResentMsg)
		return ((version == AS5669A) ? 2 : 4);
	if (_control == LastMsg)
		return ((version == AS5669A) ? 3 : 8);
	return 0; // failure case
}

inline void Message::setDataControlFlagAsChar(char flag,
											  MsgVersion version)
{
	if (flag == 0) _control = None;
	else if (flag == 1) _control = FirstMsg;
	else if (flag == 2) _control = MiddleMsg;
	else if ((flag == 3) && (version == AS5669A)) _control = LastMsg;
	else if ((flag == 4) && (version != AS5669A)) _control = MiddleResentMsg;
	else if ((flag == 8) && (version != AS5669A)) _control = LastMsg;
	return;
}



}} // namespace DeVivo::Junior

#endif


