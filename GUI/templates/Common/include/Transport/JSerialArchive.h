/*! 
 ***********************************************************************
 * @file      JSerialArchive.h
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

#ifndef  __JSERIAL_ARCHIVE_H
#define  __JSERIAL_ARCHIVE_H

#include "TransportArchive.h"
#include "ChecksumCRC.h"

namespace DeVivo {
namespace Junior {

//
// A Socket Archive is a specialty archive that includes
// on-the-wire header bits for IPC comms
//
class JSerialArchive : public TransportArchive
{
  public:
    JSerialArchive(){}
    ~JSerialArchive(){}

	// Primary function is to pack/unpack messages
	bool pack(Message& msg, MsgVersion version);
	bool unpack(Message& msg);

	// Helper functions to read data directly from archive
    bool isArchiveValid();
	void removeHeadMsg();
	MsgVersion getVersion();

  protected:
	int getHeaderSize();
	int getFooterSize();
    unsigned short getDataLength();
	char* getDataPtr();
	bool usesExplicitAddress();
    bool usesImplicitAddress();
};


inline bool JSerialArchive::usesExplicitAddress()
{
	if (getArchiveLength() < 9) return false;
    if ((data[7] == 0x10) && (data[8] == 0x02))
        return true;
    return false;
}
inline bool JSerialArchive::usesImplicitAddress()
{
	if (getArchiveLength() < 7) return false;
    if ((data[5] == 0x10) && (data[6] == 0x02))
        return true;
    return false;
}

inline MsgVersion JSerialArchive::getVersion()
{
	if (getArchiveLength() < 3) return UnknownVersion;
	char version; getValueAt(2,version); version &=0x0F;
	if (version == 1) return AS5669;
	if (version == 2) return AS5669A;
	return UnknownVersion;
}

inline int JSerialArchive::getHeaderSize()
{
	char addressSize = usesExplicitAddress() ? 2 : 0;
	if (getVersion() == AS5669) return (29+addressSize);
	if (getVersion() == AS5669A)
	{
		// For AS5669 revision A, the presence or absence of the
		// header compression fields changes the size of the header.
		// Extract the flags.  Return 2 if flags are non-zero.
		char hc_flags; getValueAt(9+addressSize, hc_flags); hc_flags &= 0x03;
		return (21 + addressSize + (hc_flags ? 2 : 0));
	}
	return 0; // failure case
}

inline int JSerialArchive::getFooterSize()
{
	if (getVersion() == AS5669) return 4;
	if (getVersion() == AS5669A) return 6;
	return 0; // failure case
}

inline unsigned short JSerialArchive::getDataLength()
{
	unsigned short length = 0; 
	int offset, extra_bytes;

	// Make sure we've got enough to read
	if (getArchiveLength() < getHeaderSize()) return length;
	setPackMode( Archive::LittleEndian );
	char addressSize = usesExplicitAddress() ? 2 : 0;

	// The offset and extra_bytes is dependent on the version
	if (getVersion() == AS5669)
	{
		getValueAt( 11 + addressSize, length ); 
		return (length - 16); // remove the 16 byte header size
	}
	else
	{
		char hc_flags; getValueAt(9+addressSize, hc_flags); hc_flags &= 0x03;
		getValueAt( 10 + addressSize, length ); 
		return (length - 14 - (hc_flags ? 2 : 0)); // remove header size
	}

	// catch-all
	return (length);
}

inline char* JSerialArchive::getDataPtr()
{
	// make sure the archive is valid
	if (!isArchiveValid()) return NULL;

	// Finding the payload depends on the header size
	return &data[getHeaderSize()];
}

inline bool JSerialArchive::isArchiveValid()
{
    // Make sure we have enough bytes to bother reading
    if (getArchiveLength() < 5) return false;

	// Make sure we have a proper version
	if (getVersion() == UnknownVersion) return false;

	// make sure we've got at least a complete header
	if (getArchiveLength() < getHeaderSize()) return false;

    // Figure out where the header checksum is
    char addressSize = usesExplicitAddress() ? 2 : 0;
    if (!usesExplicitAddress() && !usesImplicitAddress())
    {
        JrDebug << "Invalid serial packet (no STX delineator)\n";
        return false;
    }

    // Verify that the packet matches the given length
	int extra_bytes = addressSize + 13;
    unsigned short packetLength = 0; getValueAt(3, packetLength);
    if (getArchiveLength() < (packetLength + extra_bytes))
    {
        JrDebug << "Invalid serial packet (invalid size: " << packetLength <<
            " versus " << getArchiveLength() << ")\n";
        return false;
    }

    // Check if the final 4-bytes include a DLE-ETX pair
    if (( data[data_length-4] != 0x10 ) ||
        ( data[data_length-3] != 0x03) )
    {
        JrDebug << "Invalid serial packet (no etx found)\n";
        return false;
    }

    // Check the header checksum.  Compare value
    // from the packet and the one computed locally.
    unsigned short header_checksum;
    getValueAt(7+addressSize, header_checksum);
    unsigned short local_header_checksum = crc_calculate(
            (unsigned char*)&data[2], 0xFFFF, 3+addressSize);
    if (header_checksum != local_header_checksum)
    {
        JrDebug << "Serial header checksum mismatch (" << header_checksum <<
            " versus " << local_header_checksum << ")\n";
        return false;
    }

    // Check the packet checksum
    unsigned short packet_checksum;
    getValueAt(data_length-2, packet_checksum);
    unsigned short local_packet_checksum = crc_calculate(
            (unsigned char*)&data[7+addressSize], 
            local_header_checksum, getArchiveLength()-4-7-addressSize);
    if (packet_checksum != local_packet_checksum)
    {
        JrDebug << "Serial packet checksum mismatch (" << packet_checksum <<
            " versus " << local_packet_checksum << ")\n";
		return false;
    }

    // getting to this point means we have a valid packet
    JrDebug << "Found valid serial packet\n";
    return true;
}


inline void JSerialArchive::removeHeadMsg()
{
	// need to fix this to support multiple messages in
	// a single serial packet.
	clear();
}

inline bool JSerialArchive::pack(Message& msg, MsgVersion version)
{
	// Clear any existing data and resize for new message, with header.
	clear(); growBuffer( msg.getDataLength()+getHeaderSize()+getFooterSize());
	setPackMode( Archive::LittleEndian );

	// most stuff is common between the header types
	*this << (char) 0x10; // DLE	
	*this << (char) 0x01; // SOH	
	*this << (char) ((version == AS5669) ? 1 : 2); //version
	*this << (unsigned short) (msg.getDataLength()+((version==AS5669)?20:14));
    *this << (char) 0x10; // <DLE>
    *this << (char) 0x02; // <STX>

	// compute the header checksum
	unsigned short header_checksum = crc_calculate(
		(unsigned char*)&data[2], 0xFFFF, 3);
	*this <<  header_checksum;

	// AS5669 has some additional bytes here....
	if (version == AS5669)
	{
		*this << (unsigned short) 0; // header compression
		*this << (unsigned short)(msg.getDataLength() + 16);
	}

	// pack common header, then message body, the common footer
	TransportArchive::packHdr(msg, version);
	append(msg.getPayload());
	TransportArchive::packFtr(msg, version);

	// Now finish up the serial footer
	*this << (char) 0x10; // DLE	
	*this << (char) 0x03; // ETX

	// Compute the packet checksum
    unsigned short packet_checksum = crc_calculate(
        (unsigned char*)&data[7], 
        header_checksum, getArchiveLength()-2-7);
	*this << packet_checksum;

    // insert DLE markers for data fields
    // that coincidentally have the same value as the DLE.
    int SOH_shift = 0;
    for (int i=0; i<data_length; i++)
    {
        // First make sure this is not an actual diagraph
        // (and therefore *should* be a DLE.
        if ((i==0) || (i==5+SOH_shift) || (i==data_length-4))
            continue;

        // now check for a DLE equivalent byte
        if (data[i] == 0x10)
        {
            // insert a DLE character at this location
            // If necessary, grow the buffer to accommodate the new data
            growBuffer(data_length + 20);

            // Shift the tail data backward to create an empty region in the buffer
            char* temp_data = (char*) malloc( buffer_size );
            int tail_size = data_length - i;
            memcpy( temp_data, &data[i], tail_size );
            memcpy( &data[i+1], temp_data, tail_size ); 
            free(temp_data);
            
            // now insert the DLE
            data[i] = 0x10;
            data_length += 1;
			JrDebug << "Padding extra DLE at byte " <<i<<std::endl;

            // If we inserted an element before the DLE-SOH pair,
            // we need to shift our expectations of where
            // to find the SOH
            if (i<5) SOH_shift++;

            // manually increment the loop counter, since we
            // inserted the new element.
            i++;
        }
    }

	return true;
}

inline bool JSerialArchive::unpack(Message& msg)
{
	// check for a good archive before unpacking
	if (!isArchiveValid()) return false;

	// set the unpack mode and rewind to the start
	rewind(); setPackMode( Archive::LittleEndian );

	// We don't care about any of the fields in the
	// serial portion of the header.  We only need
	// to properly set-up the offset used by Archive
	// to unparse the common header data.
	char addressSize = usesExplicitAddress() ? 2 : 0;
	Archive::offset = addressSize + ((getVersion()==AS5669) ? 13 : 9);

	// unpack common header, then message body, the common footer
	TransportArchive::unpackHdr(msg, getVersion());
	msg.setPayload( getDataLength(), getDataPtr() );
	Archive::offset += getDataLength();
	TransportArchive::unpackFtr(msg, getVersion());
	return true;
}

}} // namespace DeVivo::Junior

#endif
