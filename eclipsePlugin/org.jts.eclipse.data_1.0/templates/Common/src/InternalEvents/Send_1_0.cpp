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

#include "InternalEvents/Send_1_0.h"

namespace JTS
{

jUnsignedByte Send_1_0::Body::SendRec::getPresenceVector()
{
	return m_PresenceVector;
}

int Send_1_0::Body::SendRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet(m_PresenceVector);
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
	return 0;
}

bool Send_1_0::Body::SendRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet(m_PresenceVector);
	
	return (pvBitSet[index] == 1);
}

jUnsignedShortInteger Send_1_0::Body::SendRec::getDestSubsystemID()
{
	return m_DestSubsystemID;
}

int Send_1_0::Body::SendRec::setDestSubsystemID(jUnsignedShortInteger value)
{
	m_DestSubsystemID = value;
	return 0;
}

jUnsignedByte Send_1_0::Body::SendRec::getDestNodeID()
{
	return m_DestNodeID;
}

int Send_1_0::Body::SendRec::setDestNodeID(jUnsignedByte value)
{
	m_DestNodeID = value;
	return 0;
}

jUnsignedByte Send_1_0::Body::SendRec::getDestComponentID()
{
	return m_DestComponentID;
}

int Send_1_0::Body::SendRec::setDestComponentID(jUnsignedByte value)
{
	m_DestComponentID = value;
	return 0;
}

bool Send_1_0::Body::SendRec::isSrcSubsystemIDValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

jUnsignedShortInteger Send_1_0::Body::SendRec::getSrcSubsystemID()
{
	return m_SrcSubsystemID;
}

int Send_1_0::Body::SendRec::setSrcSubsystemID(jUnsignedShortInteger value)
{
	m_SrcSubsystemID = value;
	setPresenceVector(0);
	return 0;
}

bool Send_1_0::Body::SendRec::isSrcNodeIDValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

jUnsignedByte Send_1_0::Body::SendRec::getSrcNodeID()
{
	return m_SrcNodeID;
}

int Send_1_0::Body::SendRec::setSrcNodeID(jUnsignedByte value)
{
	m_SrcNodeID = value;
	setPresenceVector(1);
	return 0;
}

jUnsignedByte Send_1_0::Body::SendRec::getSrcComponentID()
{
	return m_SrcComponentID;
}

int Send_1_0::Body::SendRec::setSrcComponentID(jUnsignedByte value)
{
	m_SrcComponentID = value;
	return 0;
}

bool Send_1_0::Body::SendRec::isPriorityValid() const
{
	if (checkPresenceVector(2))
	{
		return true;
	}
	return false;
}

jUnsignedByte Send_1_0::Body::SendRec::getPriority()
{
	return m_Priority;
}

int Send_1_0::Body::SendRec::setPriority(jUnsignedByte value)
{
	if (((value >= 0) && (value <= 3)) || (value == 0) || (value == 1) || (value == 2) || (value == 3))
	{
		m_Priority = value;
		setPresenceVector(2);
		return 0;
	}
	return 1;
}

const jUnsignedInteger Send_1_0::Body::SendRec::MessagePayload::getLength() const
{
	return m_Length;
}

const unsigned char *Send_1_0::Body::SendRec::MessagePayload::getData() const
{
	return m_Data;
}

int Send_1_0::Body::SendRec::MessagePayload::set(const jUnsignedInteger &length, const unsigned char *data)
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
const unsigned int Send_1_0::Body::SendRec::MessagePayload::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void Send_1_0::Body::SendRec::MessagePayload::encode(unsigned char *bytes)
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

void Send_1_0::Body::SendRec::MessagePayload::decode(const unsigned char *bytes)
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

Send_1_0::Body::SendRec::MessagePayload &Send_1_0::Body::SendRec::MessagePayload::operator=(const MessagePayload &value)
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

bool Send_1_0::Body::SendRec::MessagePayload::operator==(const MessagePayload &value) const
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

bool Send_1_0::Body::SendRec::MessagePayload::operator!=(const MessagePayload &value) const
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

Send_1_0::Body::SendRec::MessagePayload::MessagePayload()
{
	m_Length = 0;
	m_Data = NULL;
}

Send_1_0::Body::SendRec::MessagePayload::MessagePayload(const MessagePayload &value)
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

Send_1_0::Body::SendRec::MessagePayload::~MessagePayload()
{
	delete[] m_Data;
}

Send_1_0::Body::SendRec::MessagePayload* const Send_1_0::Body::SendRec::getMessagePayload()
{
	return &m_MessagePayload;
}

int Send_1_0::Body::SendRec::setMessagePayload(const MessagePayload &value)
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
const unsigned int Send_1_0::Body::SendRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(1))
	{
		size += sizeof(jUnsignedByte);
	}
	size += sizeof(jUnsignedByte);
	if (checkPresenceVector(2))
	{
		size += sizeof(jUnsignedByte);
	}
	size += m_MessagePayload.getSize();
	
	return size;
}

void Send_1_0::Body::SendRec::encode(unsigned char *bytes)
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
	jUnsignedShortInteger m_DestSubsystemIDTemp;
	
	m_DestSubsystemIDTemp = JSIDL_v_1_0::correctEndianness(m_DestSubsystemID);
	memcpy(bytes + pos, &m_DestSubsystemIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_DestNodeIDTemp;
	
	m_DestNodeIDTemp = JSIDL_v_1_0::correctEndianness(m_DestNodeID);
	memcpy(bytes + pos, &m_DestNodeIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_DestComponentIDTemp;
	
	m_DestComponentIDTemp = JSIDL_v_1_0::correctEndianness(m_DestComponentID);
	memcpy(bytes + pos, &m_DestComponentIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		jUnsignedShortInteger m_SrcSubsystemIDTemp;
		
		m_SrcSubsystemIDTemp = JSIDL_v_1_0::correctEndianness(m_SrcSubsystemID);
		memcpy(bytes + pos, &m_SrcSubsystemIDTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(1))
	{
		jUnsignedByte m_SrcNodeIDTemp;
		
		m_SrcNodeIDTemp = JSIDL_v_1_0::correctEndianness(m_SrcNodeID);
		memcpy(bytes + pos, &m_SrcNodeIDTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	jUnsignedByte m_SrcComponentIDTemp;
	
	m_SrcComponentIDTemp = JSIDL_v_1_0::correctEndianness(m_SrcComponentID);
	memcpy(bytes + pos, &m_SrcComponentIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	if (checkPresenceVector(2))
	{
		jUnsignedByte m_PriorityTemp;
		
		m_PriorityTemp = JSIDL_v_1_0::correctEndianness(m_Priority);
		memcpy(bytes + pos, &m_PriorityTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	m_MessagePayload.encode(bytes + pos);
	pos += m_MessagePayload.getSize();
}

void Send_1_0::Body::SendRec::decode(const unsigned char *bytes)
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
	jUnsignedShortInteger m_DestSubsystemIDTemp;
	
	memcpy(&m_DestSubsystemIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_DestSubsystemID = JSIDL_v_1_0::correctEndianness(m_DestSubsystemIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_DestNodeIDTemp;
	
	memcpy(&m_DestNodeIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_DestNodeID = JSIDL_v_1_0::correctEndianness(m_DestNodeIDTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_DestComponentIDTemp;
	
	memcpy(&m_DestComponentIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_DestComponentID = JSIDL_v_1_0::correctEndianness(m_DestComponentIDTemp);
	pos += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		jUnsignedShortInteger m_SrcSubsystemIDTemp;
		
		memcpy(&m_SrcSubsystemIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_SrcSubsystemID = JSIDL_v_1_0::correctEndianness(m_SrcSubsystemIDTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(1))
	{
		jUnsignedByte m_SrcNodeIDTemp;
		
		memcpy(&m_SrcNodeIDTemp, bytes + pos, sizeof(jUnsignedByte));
		m_SrcNodeID = JSIDL_v_1_0::correctEndianness(m_SrcNodeIDTemp);
		pos += sizeof(jUnsignedByte);
	}
	jUnsignedByte m_SrcComponentIDTemp;
	
	memcpy(&m_SrcComponentIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SrcComponentID = JSIDL_v_1_0::correctEndianness(m_SrcComponentIDTemp);
	pos += sizeof(jUnsignedByte);
	if (checkPresenceVector(2))
	{
		jUnsignedByte m_PriorityTemp;
		
		memcpy(&m_PriorityTemp, bytes + pos, sizeof(jUnsignedByte));
		m_Priority = JSIDL_v_1_0::correctEndianness(m_PriorityTemp);
		pos += sizeof(jUnsignedByte);
	}
	m_MessagePayload.decode(bytes + pos);
	pos += m_MessagePayload.getSize();
}

Send_1_0::Body::SendRec &Send_1_0::Body::SendRec::operator=(const SendRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_DestSubsystemID = value.m_DestSubsystemID;
	m_DestNodeID = value.m_DestNodeID;
	m_DestComponentID = value.m_DestComponentID;
	m_SrcSubsystemID = value.m_SrcSubsystemID;
	m_SrcNodeID = value.m_SrcNodeID;
	m_SrcComponentID = value.m_SrcComponentID;
	m_Priority = value.m_Priority;
	m_MessagePayload = value.m_MessagePayload;
	
	return *this;
}

bool Send_1_0::Body::SendRec::operator==(const SendRec &value) const
{
	if (m_DestSubsystemID != value.m_DestSubsystemID)
	{
		return false;
	}
	if (m_DestNodeID != value.m_DestNodeID)
	{
		return false;
	}
	if (m_DestComponentID != value.m_DestComponentID)
	{
		return false;
	}
	if (m_SrcSubsystemID != value.m_SrcSubsystemID)
	{
		return false;
	}
	if (m_SrcNodeID != value.m_SrcNodeID)
	{
		return false;
	}
	if (m_SrcComponentID != value.m_SrcComponentID)
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

bool Send_1_0::Body::SendRec::operator!=(const SendRec &value) const
{
	if (m_DestSubsystemID == value.m_DestSubsystemID)
	{
		return false;
	}
	if (m_DestNodeID == value.m_DestNodeID)
	{
		return false;
	}
	if (m_DestComponentID == value.m_DestComponentID)
	{
		return false;
	}
	if (m_SrcSubsystemID == value.m_SrcSubsystemID)
	{
		return false;
	}
	if (m_SrcNodeID == value.m_SrcNodeID)
	{
		return false;
	}
	if (m_SrcComponentID == value.m_SrcComponentID)
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

Send_1_0::Body::SendRec::SendRec()
{
	m_PresenceVector = 0;
	m_DestSubsystemID = 0;
	m_DestNodeID = 0;
	m_DestComponentID = 0;
	m_SrcSubsystemID = 0;
	m_SrcNodeID = 0;
	m_SrcComponentID = 0;
	m_Priority = 0;
	/// No Initialization of m_MessagePayload necessary.
}

Send_1_0::Body::SendRec::SendRec(const SendRec &value)
{
	/// Initiliaze the protected variables
	m_PresenceVector = 0;
	m_DestSubsystemID = 0;
	m_DestNodeID = 0;
	m_DestComponentID = 0;
	m_SrcSubsystemID = 0;
	m_SrcNodeID = 0;
	m_SrcComponentID = 0;
	m_Priority = 0;
	/// No Initialization of m_MessagePayload necessary.
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_DestSubsystemID = value.m_DestSubsystemID;
	m_DestNodeID = value.m_DestNodeID;
	m_DestComponentID = value.m_DestComponentID;
	m_SrcSubsystemID = value.m_SrcSubsystemID;
	m_SrcNodeID = value.m_SrcNodeID;
	m_SrcComponentID = value.m_SrcComponentID;
	m_Priority = value.m_Priority;
	m_MessagePayload = value.m_MessagePayload;
}

Send_1_0::Body::SendRec::~SendRec()
{
}

Send_1_0::Body::SendRec* const Send_1_0::Body::getSendRec()
{
	return &m_SendRec;
}

int Send_1_0::Body::setSendRec(const SendRec &value)
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
const unsigned int Send_1_0::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_SendRec.getSize();
	
	return size;
}

void Send_1_0::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SendRec.encode(bytes + pos);
	pos += m_SendRec.getSize();
}

void Send_1_0::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SendRec.decode(bytes + pos);
	pos += m_SendRec.getSize();
}

Send_1_0::Body &Send_1_0::Body::operator=(const Body &value)
{
	m_SendRec = value.m_SendRec;
	/// This code is currently not supported
	
	return *this;
}

bool Send_1_0::Body::operator==(const Body &value) const
{
	if (m_SendRec != value.m_SendRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool Send_1_0::Body::operator!=(const Body &value) const
{
	if (m_SendRec == value.m_SendRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

Send_1_0::Body::Body()
{
	/// No Initialization of m_SendRec necessary.
}

Send_1_0::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_SendRec necessary.
	
	/// Copy the values
	m_SendRec = value.m_SendRec;
	/// This code is currently not supported
}

Send_1_0::Body::~Body()
{
}

Send_1_0::Body* const Send_1_0::getBody()
{
	return &m_Body;
}

int Send_1_0::setBody(const Body &value)
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
const unsigned int Send_1_0::getSize()
{
	unsigned int size = 0;
	
	size += m_Body.getSize();
	
	return size;
}

void Send_1_0::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.encode(bytes + pos);
	pos += m_Body.getSize();
}

void Send_1_0::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

Send_1_0 &Send_1_0::operator=(const Send_1_0 &value)
{
	m_Body = value.m_Body;
	
	return *this;
}

bool Send_1_0::operator==(const Send_1_0 &value) const
{
	if (m_Body != value.m_Body)
	{
		return false;
	}
	
	return true;
}

bool Send_1_0::operator!=(const Send_1_0 &value) const
{
	if (m_Body == value.m_Body)
	{
		return false;
	}
	
	return true;
}

Send_1_0::Send_1_0()
{
	/// No Initialization of m_Body necessary.
	m_Name = "Send";
}

Send_1_0::Send_1_0(const Send_1_0 &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_Body necessary.
	m_Name = "Send";
	
	/// Copy the values
	m_Body = value.m_Body;
}

Send_1_0::~Send_1_0()
{
}

}