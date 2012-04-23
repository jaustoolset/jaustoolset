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

#include "InternalEvents/Receive_1_0.h"

namespace JTS
{

jUnsignedShortInteger Receive_1_0::Body::ReceiveRec::getSrcSubsystemID()
{
	return m_SrcSubsystemID;
}

int Receive_1_0::Body::ReceiveRec::setSrcSubsystemID(jUnsignedShortInteger value)
{
	m_SrcSubsystemID = value;
	return 0;
}

jUnsignedByte Receive_1_0::Body::ReceiveRec::getSrcNodeID()
{
	return m_SrcNodeID;
}

int Receive_1_0::Body::ReceiveRec::setSrcNodeID(jUnsignedByte value)
{
	m_SrcNodeID = value;
	return 0;
}

jUnsignedByte Receive_1_0::Body::ReceiveRec::getSrcComponentID()
{
	return m_SrcComponentID;
}

int Receive_1_0::Body::ReceiveRec::setSrcComponentID(jUnsignedByte value)
{
	m_SrcComponentID = value;
	return 0;
}

const jUnsignedInteger Receive_1_0::Body::ReceiveRec::MessagePayload::getLength() const
{
	return m_Length;
}

const unsigned char *Receive_1_0::Body::ReceiveRec::MessagePayload::getData() const
{
	return m_Data;
}

int Receive_1_0::Body::ReceiveRec::MessagePayload::set(const jUnsignedInteger &length, const unsigned char *data)
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
const unsigned int Receive_1_0::Body::ReceiveRec::MessagePayload::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void Receive_1_0::Body::ReceiveRec::MessagePayload::encode(unsigned char *bytes)
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

void Receive_1_0::Body::ReceiveRec::MessagePayload::decode(const unsigned char *bytes)
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

Receive_1_0::Body::ReceiveRec::MessagePayload &Receive_1_0::Body::ReceiveRec::MessagePayload::operator=(const MessagePayload &value)
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

bool Receive_1_0::Body::ReceiveRec::MessagePayload::operator==(const MessagePayload &value) const
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

bool Receive_1_0::Body::ReceiveRec::MessagePayload::operator!=(const MessagePayload &value) const
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

Receive_1_0::Body::ReceiveRec::MessagePayload::MessagePayload()
{
	m_Length = 0;
	m_Data = NULL;
}

Receive_1_0::Body::ReceiveRec::MessagePayload::MessagePayload(const MessagePayload &value)
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

Receive_1_0::Body::ReceiveRec::MessagePayload::~MessagePayload()
{
	delete[] m_Data;
}

Receive_1_0::Body::ReceiveRec::MessagePayload* const Receive_1_0::Body::ReceiveRec::getMessagePayload()
{
	return &m_MessagePayload;
}

int Receive_1_0::Body::ReceiveRec::setMessagePayload(const MessagePayload &value)
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
const unsigned int Receive_1_0::Body::ReceiveRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += m_MessagePayload.getSize();
	
	return size;
}

void Receive_1_0::Body::ReceiveRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SrcSubsystemIDTemp;
	
	m_SrcSubsystemIDTemp = JSIDL_v_1_0::correctEndianness(m_SrcSubsystemID);
	memcpy(bytes + pos, &m_SrcSubsystemIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_SrcNodeIDTemp;
	
	m_SrcNodeIDTemp = JSIDL_v_1_0::correctEndianness(m_SrcNodeID);
	memcpy(bytes + pos, &m_SrcNodeIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_SrcComponentIDTemp;
	
	m_SrcComponentIDTemp = JSIDL_v_1_0::correctEndianness(m_SrcComponentID);
	memcpy(bytes + pos, &m_SrcComponentIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	m_MessagePayload.encode(bytes + pos);
	pos += m_MessagePayload.getSize();
}

void Receive_1_0::Body::ReceiveRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SrcSubsystemIDTemp;
	
	memcpy(&m_SrcSubsystemIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SrcSubsystemID = JSIDL_v_1_0::correctEndianness(m_SrcSubsystemIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_SrcNodeIDTemp;
	
	memcpy(&m_SrcNodeIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SrcNodeID = JSIDL_v_1_0::correctEndianness(m_SrcNodeIDTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_SrcComponentIDTemp;
	
	memcpy(&m_SrcComponentIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SrcComponentID = JSIDL_v_1_0::correctEndianness(m_SrcComponentIDTemp);
	pos += sizeof(jUnsignedByte);
	m_MessagePayload.decode(bytes + pos);
	pos += m_MessagePayload.getSize();
}

Receive_1_0::Body::ReceiveRec &Receive_1_0::Body::ReceiveRec::operator=(const ReceiveRec &value)
{
	m_SrcSubsystemID = value.m_SrcSubsystemID;
	m_SrcNodeID = value.m_SrcNodeID;
	m_SrcComponentID = value.m_SrcComponentID;
	m_MessagePayload = value.m_MessagePayload;
	
	return *this;
}

bool Receive_1_0::Body::ReceiveRec::operator==(const ReceiveRec &value) const
{
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
	
	if (m_MessagePayload != value.m_MessagePayload)
	{
		return false;
	}
	
	return true;
}

bool Receive_1_0::Body::ReceiveRec::operator!=(const ReceiveRec &value) const
{
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
	
	if (m_MessagePayload == value.m_MessagePayload)
	{
		return false;
	}
	
	return true;
}

Receive_1_0::Body::ReceiveRec::ReceiveRec()
{
	m_SrcSubsystemID = 0;
	m_SrcNodeID = 0;
	m_SrcComponentID = 0;
	/// No Initialization of m_MessagePayload necessary.
}

Receive_1_0::Body::ReceiveRec::ReceiveRec(const ReceiveRec &value)
{
	/// Initiliaze the protected variables
	m_SrcSubsystemID = 0;
	m_SrcNodeID = 0;
	m_SrcComponentID = 0;
	/// No Initialization of m_MessagePayload necessary.
	
	/// Copy the values
	m_SrcSubsystemID = value.m_SrcSubsystemID;
	m_SrcNodeID = value.m_SrcNodeID;
	m_SrcComponentID = value.m_SrcComponentID;
	m_MessagePayload = value.m_MessagePayload;
}

Receive_1_0::Body::ReceiveRec::~ReceiveRec()
{
}

Receive_1_0::Body::ReceiveRec* const Receive_1_0::Body::getReceiveRec()
{
	return &m_ReceiveRec;
}

int Receive_1_0::Body::setReceiveRec(const ReceiveRec &value)
{
	m_ReceiveRec = value;
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
const unsigned int Receive_1_0::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ReceiveRec.getSize();
	
	return size;
}

void Receive_1_0::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReceiveRec.encode(bytes + pos);
	pos += m_ReceiveRec.getSize();
}

void Receive_1_0::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReceiveRec.decode(bytes + pos);
	pos += m_ReceiveRec.getSize();
}

Receive_1_0::Body &Receive_1_0::Body::operator=(const Body &value)
{
	m_ReceiveRec = value.m_ReceiveRec;
	/// This code is currently not supported
	
	return *this;
}

bool Receive_1_0::Body::operator==(const Body &value) const
{
	if (m_ReceiveRec != value.m_ReceiveRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool Receive_1_0::Body::operator!=(const Body &value) const
{
	if (m_ReceiveRec == value.m_ReceiveRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

Receive_1_0::Body::Body()
{
	/// No Initialization of m_ReceiveRec necessary.
}

Receive_1_0::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_ReceiveRec necessary.
	
	/// Copy the values
	m_ReceiveRec = value.m_ReceiveRec;
	/// This code is currently not supported
}

Receive_1_0::Body::~Body()
{
}

Receive_1_0::Body* const Receive_1_0::getBody()
{
	return &m_Body;
}

int Receive_1_0::setBody(const Body &value)
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
const unsigned int Receive_1_0::getSize()
{
	unsigned int size = 0;
	
	size += m_Body.getSize();
	
	return size;
}

void Receive_1_0::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.encode(bytes + pos);
	pos += m_Body.getSize();
}

void Receive_1_0::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

Receive_1_0 &Receive_1_0::operator=(const Receive_1_0 &value)
{
	m_Body = value.m_Body;
	
	return *this;
}

bool Receive_1_0::operator==(const Receive_1_0 &value) const
{
	if (m_Body != value.m_Body)
	{
		return false;
	}
	
	return true;
}

bool Receive_1_0::operator!=(const Receive_1_0 &value) const
{
	if (m_Body == value.m_Body)
	{
		return false;
	}
	
	return true;
}

Receive_1_0::Receive_1_0()
{
	/// No Initialization of m_Body necessary.
	m_Name = "Receive";
}

Receive_1_0::Receive_1_0(const Receive_1_0 &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_Body necessary.
	m_Name = "Receive";
	
	/// Copy the values
	m_Body = value.m_Body;
}

Receive_1_0::~Receive_1_0()
{
}

}
