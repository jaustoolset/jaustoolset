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

#ifndef  __TRANSPORT_ARCHIVE_H
#define  __TRANSPORT_ARCHIVE_H

#include "Archive.h"
#include "JrLogger.h"

namespace DeVivo {
namespace Junior {

//
// A Transport Archive is a specialty archive that includes
// on-the-wire header bits.
//
class TransportArchive : public Archive
{
  public:
    TransportArchive(){}
    ~TransportArchive(){}

	// A transport archive converts a message into a byte stream
	// by serializing or "packing".  
	virtual bool pack(Message& msg, MsgVersion version) = 0;
	virtual bool unpack(Message& msg) = 0;

	// Helper functions for common operations
    virtual bool isArchiveValid() = 0;
	virtual void removeHeadMsg() = 0;

protected:

	// common functions to pack/unpack the headers/footers
	// across all transport types
	void packHdr(Message& msg, MsgVersion version = AS5669);
	void unpackHdr(Message& msg, MsgVersion version = AS5669);
	void packFtr(Message& msg, MsgVersion version = AS5669);
	void unpackFtr(Message& msg, MsgVersion version = AS5669);

};

inline void TransportArchive::packHdr(Message& msg, MsgVersion version)
{
    // Pack header as little endian
    setPackMode( Archive::LittleEndian );

	// Packed structure depends on the Version
	if (version == AS5669A)
	{
		*this << (char) 0; // Message type and header compression
		*this << (unsigned short) (msg.getDataLength()+14); // data & header
		*this << (char) (msg.getScaledPriority() | (msg.getBroadcast() << 2) | 
			(msg.getAckNakFlag() << 4) | (msg.getDataControlFlagAsChar(version)<<6));
		*this << (unsigned int) msg.getDestinationId().val;  // destination
		*this << (unsigned int) msg.getSourceId().val;		// source
	}
	else
	{
		// Priority and acknowledgement, service connection, experimental
		*this << ((char)(msg.getPriority() | (msg.getAckNakFlag() << 4) | 
			          (msg.getServiceConnection() << 6) | (msg.getExperimental() << 7)));
		*this << (char) 2; // version
		*this << (unsigned short) msg.getMessageCode(); // command code
		*this << (unsigned int) msg.getDestinationId().val; // destination
		*this << (unsigned int) msg.getSourceId().val;      // source
		*this << ((unsigned short)(msg.getDataLength() | 
					(msg.getDataControlFlagAsChar(version)<<12)));
		*this << (unsigned short) msg.getSequenceNumber();  // sequence number
	}
}

inline void TransportArchive::packFtr( Message& msg, MsgVersion version )
{
	// Pack as little endian
    setPackMode( Archive::LittleEndian );

	// Footers are only defined for 5669 rev A
	if (version == AS5669A) 
		*this << (unsigned short) msg.getSequenceNumber();
}

inline void TransportArchive::unpackHdr( Message& msg, MsgVersion version )
{
	char temp8; unsigned short temp16;
	unsigned int temp32;

    // Unpack header as little endian
    setPackMode( Archive::LittleEndian );

	// Structure depends on the Version
	if (version == AS5669A)
	{
		*this >> temp8; // message type
		bool hc_present = (temp8 & 0x3);
		*this >> temp16; // data size
		if (hc_present) *this >> temp16;
		*this >> temp8; // message properties
		msg.setScaledPriority(temp8 & 0x03);
		msg.setBroadcast((temp8 & 0x0C) >> 2);
		msg.setAckNakFlag((temp8 & 0x30) >> 4); 
		msg.setDataControlFlagAsChar((temp8 & 0xC0)>>6, version);
		*this >> temp32; msg.setDestinationId(JAUS_ID(temp32)); // destination
		*this >> temp32; msg.setSourceId(JAUS_ID(temp32)); // source
	}
	else
	{
		*this >> temp8; // message properties
		msg.setPriority(temp8 & 0x0F);
		msg.setAckNakFlag((temp8 & 0x30) >> 4); 
		msg.setServiceConnection((temp8 & 0x40) >> 6);
		msg.setExperimental((temp8 & 0x80) >> 7);
		*this >> temp8; // discard version
		*this >> temp16; msg.setMessageCode(temp16);// command code
		*this >> temp32; msg.setDestinationId(JAUS_ID(temp32)); // destination
		*this >> temp32; msg.setSourceId(JAUS_ID(temp32)); // source
		*this >> temp16; // data control flags
		msg.setDataControlFlagAsChar((temp16 >> 12) & 0xF, version);
		*this >> temp16; msg.setSequenceNumber(temp16);
	}
}

inline void TransportArchive::unpackFtr( Message& msg, MsgVersion version )
{
	// Pack as little endian
    setPackMode( Archive::LittleEndian );

	// Footers are only defined for 5669 rev A
	if (version == AS5669A) 
	{
		unsigned short temp16;
		*this >> temp16; msg.setSequenceNumber(temp16);
	}
}
}} // namespace DeVivo::Junior



#endif
