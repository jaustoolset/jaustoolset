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

#ifndef  __JTCP_ARCHIVE_H
#define  __JTCP_ARCHIVE_H

#include "TransportArchive.h"

namespace DeVivo {
namespace Junior {

//
// A Socket Archive is a specialty archive that includes
// on-the-wire header bits for IPC comms
//
class JTCPArchive : public TransportArchive
{
  public:
    JTCPArchive(){}
    ~JTCPArchive(){}

	// Primary function is to pack/unpack messages
	bool pack(Message& msg, MsgVersion version);
	bool unpack(Message& msg);

	// Helper functions to read data directly from archive
    bool isArchiveValid();
	void removeHeadMsg();
	MsgVersion getVersion();

	// TCP is special in that we only send
	// the version byte once.  Clients need to be
	// able to remove it from archives before sending.
	void removeVersion(){removeAt(0,1);}

  protected:
	int getHeaderSize();
	int getFooterSize();
    unsigned short getDataLength();
	char* getDataPtr();
};

inline MsgVersion JTCPArchive::getVersion()
{
	if (getArchiveLength() < 1) return UnknownVersion;
	char version; getValueAt(0,version);
	if (version == 1) return AS5669;
	if (version == 2) return AS5669A;

	// catch all...
	return UnknownVersion;
}

inline int JTCPArchive::getHeaderSize()
{
	if (getVersion() == UnknownVersion) return 0; //failure
	if (getVersion() == AS5669) return 19; // header size is fixed

	// For AS5669 revision A, the presence or absence of the
	// header compression fields changes the size of the header.
	// Extract the flags.  Return 2 if flags are non-zero.
	char hc_flags; getValueAt(1, hc_flags); hc_flags &= 0x03;
	return (13 + (hc_flags ? 2 : 0));
}

inline int JTCPArchive::getFooterSize()
{
	if (getVersion() == AS5669A) return 2;
	return 0;
}

inline unsigned short JTCPArchive::getDataLength()
{
	unsigned short length = 0; 

	// Make sure we've got enough to read
	if (getArchiveLength() < getHeaderSize()) return length;
	setPackMode( Archive::LittleEndian );

	// Location of the data size field depends on the header
	if (getVersion() == AS5669)
	{
		getValueAt(15, length);
		length &= 0x0FFF;
	}
	else if (getVersion() == AS5669A)
	{
		getValueAt(2, length);
		length -= (getHeaderSize()+1);
	}

	// return success
	return (length);
}

inline char* JTCPArchive::getDataPtr()
{
	// make sure the archive is valid
	if (!isArchiveValid()) return NULL;

	// Finding the payload depends on the header size
	return &data[getHeaderSize()];
}

inline bool JTCPArchive::isArchiveValid()
{
	// Make sure we have a proper version
	if (getVersion() == UnknownVersion) return false;

	// make sure we've got at least a complete header
	if (getArchiveLength() < getHeaderSize()) return false;

	// Pull data size from archive and make sure we exceed that
	if (getArchiveLength() < (getDataLength()+getHeaderSize()+getFooterSize())) 
		return false;

	// getting here implies success
	return true;
}

inline void JTCPArchive::removeHeadMsg()
{
	// keep the transport specific header
	int length = getHeaderSize()+getDataLength()+getFooterSize();
	if (length > getArchiveLength()) clear(); // error catching
	else removeAt(1, length - 1); // keep the version byte
}

inline bool JTCPArchive::pack(Message& msg, MsgVersion version)
{
	// Clear any existing data and resize for new message, with header.
	clear(); growBuffer( msg.getDataLength()+getHeaderSize()+getFooterSize());
	setPackMode( Archive::LittleEndian );

	// header depends on the version we're packing for
	if (version == AS5669)
	{
		*this << (char) 1; // version
		setPackMode( Archive::BigEndian );
		*this << (unsigned short) (msg.getDataLength() + 16);
		setPackMode( Archive::LittleEndian );
	}
	else
	{
		*this << (char) 2; //version
	}

	// pack common header, then message body, the common footer
	TransportArchive::packHdr(msg, version);
	append(msg.getPayload());
	TransportArchive::packFtr(msg, version);
	return true;
}

inline bool JTCPArchive::unpack(Message& msg)
{
	char temp8; unsigned short temp16;

	// check for a good archive before unpacking
	if (!isArchiveValid()) return false;

	// set the unpack mode and rewind to the start
	rewind(); setPackMode( Archive::LittleEndian );

	// pull JTCP header data depending on version
	Archive::offset = ((getVersion() == AS5669) ? 3 : 1);

	// unpack common header, message body, and common footer
	unpackHdr(msg, getVersion());
	msg.setPayload( getDataLength(), getDataPtr() );
	Archive::offset += getDataLength();
	unpackFtr(msg, getVersion());
	return true;
}

}} // namespace DeVivo::Junior

#endif
