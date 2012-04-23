/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of 
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

#include "InternalEvents/Send_1_1.h"

namespace JTS
{

jUnsignedByte Send_1_1::Body::SendRec::getPresenceVector()
{
	return m_PresenceVector;
}

int Send_1_1::Body::SendRec::setPresenceVector(unsigned int index)
{
	std::bitset<(int)(sizeof(jUnsignedByte) * 8)> pvBitSet(m_PresenceVector);
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
	return 0;
}

bool Send_1_1::Body::SendRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<(int)(sizeof(jUnsignedByte) * 8)> pvBitSet(m_PresenceVector);
	
	return (pvBitSet[index] == 1);
}

jUnsignedByte Send_1_1::Body::SendRec::getReliableDelivery()
{
	return m_ReliableDelivery;
}

int Send_1_1::Body::SendRec::setReliableDelivery(jUnsignedByte value)
{
	if (((value >= 0) && (value <= 1)) || (value == 0) || (value == 1))
	{
		m_ReliableDelivery = value;
		return 0;
	}
	return 1;
}

jUnsignedInteger Send_1_1::Body::SendRec::DestinationID::getComponentID()
{
	std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int)m_SubFields);
	std::bitset<(int)8> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 7; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int Send_1_1::Body::SendRec::DestinationID::setComponentID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 255)))
	{
		std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int) m_SubFields);
		std::bitset<(int)8> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 7; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		return 0;
	}
	return 1;
}

jUnsignedInteger Send_1_1::Body::SendRec::DestinationID::getNodeID()
{
	std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int) m_SubFields);
	std::bitset<(int)8> sfbs;
	int i = 0;
	
	for (int index = 8; index <= 15; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int Send_1_1::Body::SendRec::DestinationID::setNodeID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 255)))
	{
		std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int) m_SubFields);
		std::bitset<(int)8> sfbs((int) value);
		int i = 0;
		
		for (int index = 8; index <= 15; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		return 0;
	}
	return 1;
}

jUnsignedInteger Send_1_1::Body::SendRec::DestinationID::getSubsystemID()
{
	std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int) m_SubFields);
	std::bitset<(int)16> sfbs;
	int i = 0;
	
	for (int index = 16; index <= 31; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int Send_1_1::Body::SendRec::DestinationID::setSubsystemID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 65535)))
	{
		std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int) m_SubFields);
		std::bitset<(int)16> sfbs((int) value);
		int i = 0;
		
		for (int index = 16; index <= 31; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		return 0;
	}
	return 1;
}

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int Send_1_1::Body::SendRec::DestinationID::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void Send_1_1::Body::SendRec::DestinationID::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
}

void Send_1_1::Body::SendRec::DestinationID::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedInteger));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedInteger);
}

Send_1_1::Body::SendRec::DestinationID &Send_1_1::Body::SendRec::DestinationID::operator=(const DestinationID &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool Send_1_1::Body::SendRec::DestinationID::operator==(const DestinationID &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool Send_1_1::Body::SendRec::DestinationID::operator!=(const DestinationID &value) const
{
	return (this->m_SubFields != value.m_SubFields);
}

Send_1_1::Body::SendRec::DestinationID::DestinationID()
{
	m_SubFields = 0;
}

Send_1_1::Body::SendRec::DestinationID::DestinationID(const DestinationID &value)
{
	/// Initiliaze the protected variables
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

Send_1_1::Body::SendRec::DestinationID::~DestinationID()
{
}

Send_1_1::Body::SendRec::DestinationID* const Send_1_1::Body::SendRec::getDestinationID()
{
	return &m_DestinationID;
}

int Send_1_1::Body::SendRec::setDestinationID(const DestinationID &value)
{
	m_DestinationID = value;
	return 0;
}

jUnsignedInteger Send_1_1::Body::SendRec::SourceID::getComponentID()
{
	std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int) m_SubFields);
	std::bitset<(int)8> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 7; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int Send_1_1::Body::SendRec::SourceID::setComponentID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 255)))
	{
		std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int) m_SubFields);
		std::bitset<(int)8> sfbs((int) value);
		int i = 0;
		
		for (int index = 0; index <= 7; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		return 0;
	}
	return 1;
}

jUnsignedInteger Send_1_1::Body::SendRec::SourceID::getNodeID()
{
	std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int) m_SubFields);
	std::bitset<(int)8> sfbs;
	int i = 0;
	
	for (int index = 8; index <= 15; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int Send_1_1::Body::SendRec::SourceID::setNodeID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 255)))
	{
		std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int) m_SubFields);
		std::bitset<(int)8> sfbs((int) value);
		int i = 0;
		
		for (int index = 8; index <= 15; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		return 0;
	}
	return 1;
}

jUnsignedInteger Send_1_1::Body::SendRec::SourceID::getSubsystemID()
{
	std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int) m_SubFields);
	std::bitset<(int)16> sfbs;
	int i = 0;
	
	for (int index = 16; index <= 31; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int Send_1_1::Body::SendRec::SourceID::setSubsystemID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 65535)))
	{
		std::bitset<(int)(sizeof(jUnsignedInteger) * 8)> bfbs((int) m_SubFields);
		std::bitset<(int)16> sfbs((int) value);
		int i = 0;
		
		for (int index = 16; index <= 31; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		return 0;
	}
	return 1;
}

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int Send_1_1::Body::SendRec::SourceID::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void Send_1_1::Body::SendRec::SourceID::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
}

void Send_1_1::Body::SendRec::SourceID::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedInteger));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedInteger);
}

Send_1_1::Body::SendRec::SourceID &Send_1_1::Body::SendRec::SourceID::operator=(const SourceID &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool Send_1_1::Body::SendRec::SourceID::operator==(const SourceID &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool Send_1_1::Body::SendRec::SourceID::operator!=(const SourceID &value) const
{
	return (this->m_SubFields != value.m_SubFields);
}

Send_1_1::Body::SendRec::SourceID::SourceID()
{
	m_SubFields = 0;
}

Send_1_1::Body::SendRec::SourceID::SourceID(const SourceID &value)
{
	/// Initiliaze the protected variables
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

Send_1_1::Body::SendRec::SourceID::~SourceID()
{
}

bool Send_1_1::Body::SendRec::isSourceIDValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

Send_1_1::Body::SendRec::SourceID* const Send_1_1::Body::SendRec::getSourceID()
{
	return &m_SourceID;
}

int Send_1_1::Body::SendRec::setSourceID(const SourceID &value)
{
	m_SourceID = value;
	setPresenceVector(0);
	return 0;
}

bool Send_1_1::Body::SendRec::isPriorityValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

jUnsignedByte Send_1_1::Body::SendRec::getPriority()
{
	return m_Priority;
}

int Send_1_1::Body::SendRec::setPriority(jUnsignedByte value)
{
	if (((value >= 0) && (value <= 3)) || (value == 0) || (value == 1) || (value == 2) || (value == 3))
	{
		m_Priority = value;
		setPresenceVector(1);
		return 0;
	}
	return 1;
}

const jUnsignedInteger Send_1_1::Body::SendRec::MessagePayload::getLength() const
{
	return m_Length;
}

const unsigned char *Send_1_1::Body::SendRec::MessagePayload::getData() const
{
	return m_Data;
}

int Send_1_1::Body::SendRec::MessagePayload::set(const jUnsignedInteger &length, const unsigned char *data)
{
	m_Length = length;
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[length];
		memcpy(m_Data, data, length);
	}
	
	return 0;
}

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int Send_1_1::Body::SendRec::MessagePayload::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void Send_1_1::Body::SendRec::MessagePayload::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_LengthTemp;
	
	m_LengthTemp = JSIDL_v_1_0::correctEndianness(m_Length);
	memcpy(bytes+pos, &m_LengthTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
	
	memcpy(bytes+pos, m_Data, m_Length);
	pos += m_Length;
}

void Send_1_1::Body::SendRec::MessagePayload::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_LengthTemp;
	
	memcpy(&m_LengthTemp, bytes+pos, sizeof(jUnsignedInteger));
	m_Length = JSIDL_v_1_0::correctEndianness(m_LengthTemp);
	pos += sizeof(jUnsignedInteger);
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[m_Length];
		memcpy(m_Data, bytes+pos, m_Length);
		pos += m_Length;
	}
}

Send_1_1::Body::SendRec::MessagePayload &Send_1_1::Body::SendRec::MessagePayload::operator=(const MessagePayload &value)
{
	this->m_Length = value.m_Length;
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[this->m_Length];
		memcpy(this->m_Data, value.m_Data, this->m_Length);
	}
	
	return *this;
}

bool Send_1_1::Body::SendRec::MessagePayload::operator==(const MessagePayload &value) const
{
	if (this->m_Length != value.m_Length)
	{
		return false;
	}
	
	if ((this->m_Data != NULL) && (value.m_Data!= NULL))
	{
		if (memcmp(this->m_Data, value.m_Data, this->m_Length) != 0)
		{
			return false;
		}
	}
	// This case should never be true since it should not be possible
	// for the two variables to have equal lengths but one has no data.
	// This check is placed here as a secondary check.
	else if ((this->m_Data != NULL) || (value.m_Data != NULL))
	{
		return false;
	}
	
	return true;
}

bool Send_1_1::Body::SendRec::MessagePayload::operator!=(const MessagePayload &value) const
{
	if (this->m_Length == value.m_Length)
	{
		return false;
	}
	
	if ((this->m_Data != NULL) && (value.m_Data != NULL))
	{
		if (memcmp(this->m_Data, value.m_Data, this->m_Length) == 0)
		{
			return false;
		}
	}
	// This case should never be true since length should be equal but is
	// placed here as a secondary check
	else if ((this->m_Data == NULL) && (value.m_Data == NULL))
	{
		return false;
	}
	
	return true;
}

Send_1_1::Body::SendRec::MessagePayload::MessagePayload()
{
	m_Length = 0;
	m_Data = NULL;
}

Send_1_1::Body::SendRec::MessagePayload::MessagePayload(const MessagePayload &value)
{
	/// Initiliaze the protected variables
	m_Length = 0;
	m_Data = NULL;
	
	/// Copy the values
	this->m_Length = value.m_Length;
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[this->m_Length];
		memcpy(this->m_Data, value.m_Data, this->m_Length);
	}
}

Send_1_1::Body::SendRec::MessagePayload::~MessagePayload()
{
	delete[] m_Data;
}

Send_1_1::Body::SendRec::MessagePayload* const Send_1_1::Body::SendRec::getMessagePayload()
{
	return &m_MessagePayload;
}

int Send_1_1::Body::SendRec::setMessagePayload(const MessagePayload &value)
{
	m_MessagePayload = value;
	return 0;
}

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int Send_1_1::Body::SendRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += m_DestinationID.getSize();
	if (checkPresenceVector(0))
	{
		size += m_SourceID.getSize();
	}
	if (checkPresenceVector(1))
	{
		size += sizeof(jUnsignedByte);
	}
	size += m_MessagePayload.getSize();
	
	return size;
}

void Send_1_1::Body::SendRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_PresenceVectorTemp;
	
	m_PresenceVectorTemp = JSIDL_v_1_0::correctEndianness(m_PresenceVector);
	memcpy(bytes + pos, &m_PresenceVectorTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_ReliableDeliveryTemp;
	
	m_ReliableDeliveryTemp = JSIDL_v_1_0::correctEndianness(m_ReliableDelivery);
	memcpy(bytes + pos, &m_ReliableDeliveryTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	m_DestinationID.encode(bytes + pos);
	pos += m_DestinationID.getSize();
	if (checkPresenceVector(0))
	{
		m_SourceID.encode(bytes + pos);
		pos += m_SourceID.getSize();
	}
	if (checkPresenceVector(1))
	{
		jUnsignedByte m_PriorityTemp;
		
		m_PriorityTemp = JSIDL_v_1_0::correctEndianness(m_Priority);
		memcpy(bytes + pos, &m_PriorityTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	m_MessagePayload.encode(bytes + pos);
	pos += m_MessagePayload.getSize();
}

void Send_1_1::Body::SendRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_PresenceVectorTemp;
	
	memcpy(&m_PresenceVectorTemp, bytes + pos, sizeof(jUnsignedByte));
	m_PresenceVector = JSIDL_v_1_0::correctEndianness(m_PresenceVectorTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_ReliableDeliveryTemp;
	
	memcpy(&m_ReliableDeliveryTemp, bytes + pos, sizeof(jUnsignedByte));
	m_ReliableDelivery = JSIDL_v_1_0::correctEndianness(m_ReliableDeliveryTemp);
	pos += sizeof(jUnsignedByte);
	m_DestinationID.decode(bytes + pos);
	pos += m_DestinationID.getSize();
	if (checkPresenceVector(0))
	{
		m_SourceID.decode(bytes + pos);
		pos += m_SourceID.getSize();
	}
	if (checkPresenceVector(1))
	{
		jUnsignedByte m_PriorityTemp;
		
		memcpy(&m_PriorityTemp, bytes + pos, sizeof(jUnsignedByte));
		m_Priority = JSIDL_v_1_0::correctEndianness(m_PriorityTemp);
		pos += sizeof(jUnsignedByte);
	}
	m_MessagePayload.decode(bytes + pos);
	pos += m_MessagePayload.getSize();
}

Send_1_1::Body::SendRec &Send_1_1::Body::SendRec::operator=(const SendRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_ReliableDelivery = value.m_ReliableDelivery;
	m_DestinationID = value.m_DestinationID;
	m_SourceID = value.m_SourceID;
	m_Priority = value.m_Priority;
	m_MessagePayload = value.m_MessagePayload;
	
	return *this;
}

bool Send_1_1::Body::SendRec::operator==(const SendRec &value) const
{
	if (m_ReliableDelivery != value.m_ReliableDelivery)
	{
		return false;
	}
	
	if (m_DestinationID != value.m_DestinationID)
	{
		return false;
	}
	
	if (m_SourceID != value.m_SourceID)
	{
		return false;
	}
	if (m_Priority != value.m_Priority)
	{
		return false;
	}
	
	if (m_MessagePayload != value.m_MessagePayload)
	{
		return false;
	}
	
	return true;
}

bool Send_1_1::Body::SendRec::operator!=(const SendRec &value) const
{
	if (m_ReliableDelivery == value.m_ReliableDelivery)
	{
		return false;
	}
	
	if (m_DestinationID == value.m_DestinationID)
	{
		return false;
	}
	
	if (m_SourceID == value.m_SourceID)
	{
		return false;
	}
	if (m_Priority == value.m_Priority)
	{
		return false;
	}
	
	if (m_MessagePayload == value.m_MessagePayload)
	{
		return false;
	}
	
	return true;
}

Send_1_1::Body::SendRec::SendRec()
{
	m_PresenceVector = 0;
	m_ReliableDelivery = 0;
	/// No Initialization of m_DestinationID necessary.
	/// No Initialization of m_SourceID necessary.
	m_Priority = 0;
	/// No Initialization of m_MessagePayload necessary.
}

Send_1_1::Body::SendRec::SendRec(const SendRec &value)
{
	/// Initiliaze the protected variables
	m_PresenceVector = 0;
	m_ReliableDelivery = 0;
	/// No Initialization of m_DestinationID necessary.
	/// No Initialization of m_SourceID necessary.
	m_Priority = 0;
	/// No Initialization of m_MessagePayload necessary.
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_ReliableDelivery = value.m_ReliableDelivery;
	m_DestinationID = value.m_DestinationID;
	m_SourceID = value.m_SourceID;
	m_Priority = value.m_Priority;
	m_MessagePayload = value.m_MessagePayload;
}

Send_1_1::Body::SendRec::~SendRec()
{
}

Send_1_1::Body::SendRec* const Send_1_1::Body::getSendRec()
{
	return &m_SendRec;
}

int Send_1_1::Body::setSendRec(const SendRec &value)
{
	m_SendRec = value;
	return 0;
}

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int Send_1_1::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_SendRec.getSize();
	
	return size;
}

void Send_1_1::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SendRec.encode(bytes + pos);
	pos += m_SendRec.getSize();
}

void Send_1_1::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SendRec.decode(bytes + pos);
	pos += m_SendRec.getSize();
}

Send_1_1::Body &Send_1_1::Body::operator=(const Body &value)
{
	m_SendRec = value.m_SendRec;
	/// This code is currently not supported
	
	return *this;
}

bool Send_1_1::Body::operator==(const Body &value) const
{
	if (m_SendRec != value.m_SendRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool Send_1_1::Body::operator!=(const Body &value) const
{
	if (m_SendRec == value.m_SendRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

Send_1_1::Body::Body()
{
	/// No Initialization of m_SendRec necessary.
}

Send_1_1::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_SendRec necessary.
	
	/// Copy the values
	m_SendRec = value.m_SendRec;
	/// This code is currently not supported
}

Send_1_1::Body::~Body()
{
}

Send_1_1::Body* const Send_1_1::getBody()
{
	return &m_Body;
}

int Send_1_1::setBody(const Body &value)
{
	m_Body = value;
	return 0;
}

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int Send_1_1::getSize()
{
	unsigned int size = 0;
	
	size += m_Body.getSize();
	
	return size;
}

void Send_1_1::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.encode(bytes + pos);
	pos += m_Body.getSize();
}

void Send_1_1::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

Send_1_1 &Send_1_1::operator=(const Send_1_1 &value)
{
	m_Body = value.m_Body;
	
	return *this;
}

bool Send_1_1::operator==(const Send_1_1 &value) const
{
	if (m_Body != value.m_Body)
	{
		return false;
	}
	
	return true;
}

bool Send_1_1::operator!=(const Send_1_1 &value) const
{
	if (m_Body == value.m_Body)
	{
		return false;
	}
	
	return true;
}

Send_1_1::Send_1_1()
{
	/// No Initialization of m_Body necessary.
	m_Name = "Send";
}

Send_1_1::Send_1_1(const Send_1_1 &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_Body necessary.
	m_Name = "Send";
	
	/// Copy the values
	m_Body = value.m_Body;
}

Send_1_1::~Send_1_1()
{
}


}
