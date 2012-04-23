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

#include "InternalEvents/Receive_1_1.h"

namespace JTS
{

jUnsignedInteger Receive_1_1::Body::ReceiveRec::SourceID::getComponentID()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int) m_SubFields);
	std::bitset<8> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 7; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int Receive_1_1::Body::ReceiveRec::SourceID::setComponentID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 255)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int) m_SubFields);
		std::bitset<8> sfbs((int) value);
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

jUnsignedInteger Receive_1_1::Body::ReceiveRec::SourceID::getNodeID()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int) m_SubFields);
	std::bitset<8> sfbs;
	int i = 0;
	
	for (int index = 8; index <= 15; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int Receive_1_1::Body::ReceiveRec::SourceID::setNodeID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 255)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int) m_SubFields);
		std::bitset<8> sfbs((int) value);
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

jUnsignedInteger Receive_1_1::Body::ReceiveRec::SourceID::getSubsystemID()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int) m_SubFields);
	std::bitset<16> sfbs;
	int i = 0;
	
	for (int index = 16; index <= 31; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int Receive_1_1::Body::ReceiveRec::SourceID::setSubsystemID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 65535)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int) m_SubFields);
		std::bitset<16> sfbs((int) value);
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
const unsigned int Receive_1_1::Body::ReceiveRec::SourceID::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void Receive_1_1::Body::ReceiveRec::SourceID::encode(unsigned char *bytes)
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

void Receive_1_1::Body::ReceiveRec::SourceID::decode(const unsigned char *bytes)
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

Receive_1_1::Body::ReceiveRec::SourceID &Receive_1_1::Body::ReceiveRec::SourceID::operator=(const SourceID &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool Receive_1_1::Body::ReceiveRec::SourceID::operator==(const SourceID &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool Receive_1_1::Body::ReceiveRec::SourceID::operator!=(const SourceID &value) const
{
	return (this->m_SubFields != value.m_SubFields);
}

Receive_1_1::Body::ReceiveRec::SourceID::SourceID()
{
	m_SubFields = 0;
}

Receive_1_1::Body::ReceiveRec::SourceID::SourceID(const SourceID &value)
{
	/// Initiliaze the protected variables
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

Receive_1_1::Body::ReceiveRec::SourceID::~SourceID()
{
}

Receive_1_1::Body::ReceiveRec::SourceID* const Receive_1_1::Body::ReceiveRec::getSourceID()
{
	return &m_SourceID;
}

int Receive_1_1::Body::ReceiveRec::setSourceID(const SourceID &value)
{
	m_SourceID = value;
	return 0;
}

const jUnsignedInteger Receive_1_1::Body::ReceiveRec::MessagePayload::getLength() const
{
	return m_Length;
}

const unsigned char *Receive_1_1::Body::ReceiveRec::MessagePayload::getData() const
{
	return m_Data;
}

int Receive_1_1::Body::ReceiveRec::MessagePayload::set(const jUnsignedInteger &length, const unsigned char *data)
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
const unsigned int Receive_1_1::Body::ReceiveRec::MessagePayload::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void Receive_1_1::Body::ReceiveRec::MessagePayload::encode(unsigned char *bytes)
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

void Receive_1_1::Body::ReceiveRec::MessagePayload::decode(const unsigned char *bytes)
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

Receive_1_1::Body::ReceiveRec::MessagePayload &Receive_1_1::Body::ReceiveRec::MessagePayload::operator=(const MessagePayload &value)
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

bool Receive_1_1::Body::ReceiveRec::MessagePayload::operator==(const MessagePayload &value) const
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

bool Receive_1_1::Body::ReceiveRec::MessagePayload::operator!=(const MessagePayload &value) const
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

Receive_1_1::Body::ReceiveRec::MessagePayload::MessagePayload()
{
	m_Length = 0;
	m_Data = NULL;
}

Receive_1_1::Body::ReceiveRec::MessagePayload::MessagePayload(const MessagePayload &value)
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

Receive_1_1::Body::ReceiveRec::MessagePayload::~MessagePayload()
{
	delete[] m_Data;
}

Receive_1_1::Body::ReceiveRec::MessagePayload* const Receive_1_1::Body::ReceiveRec::getMessagePayload()
{
	return &m_MessagePayload;
}

int Receive_1_1::Body::ReceiveRec::setMessagePayload(const MessagePayload &value)
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
const unsigned int Receive_1_1::Body::ReceiveRec::getSize()
{
	unsigned int size = 0;
	
	size += m_SourceID.getSize();
	size += m_MessagePayload.getSize();
	
	return size;
}

void Receive_1_1::Body::ReceiveRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SourceID.encode(bytes + pos);
	pos += m_SourceID.getSize();
	m_MessagePayload.encode(bytes + pos);
	pos += m_MessagePayload.getSize();
}

void Receive_1_1::Body::ReceiveRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SourceID.decode(bytes + pos);
	pos += m_SourceID.getSize();
	m_MessagePayload.decode(bytes + pos);
	pos += m_MessagePayload.getSize();
}

Receive_1_1::Body::ReceiveRec &Receive_1_1::Body::ReceiveRec::operator=(const ReceiveRec &value)
{
	m_SourceID = value.m_SourceID;
	m_MessagePayload = value.m_MessagePayload;
	
	return *this;
}

bool Receive_1_1::Body::ReceiveRec::operator==(const ReceiveRec &value) const
{
	if (m_SourceID != value.m_SourceID)
	{
		return false;
	}
	
	if (m_MessagePayload != value.m_MessagePayload)
	{
		return false;
	}
	
	return true;
}

bool Receive_1_1::Body::ReceiveRec::operator!=(const ReceiveRec &value) const
{
	if (m_SourceID == value.m_SourceID)
	{
		return false;
	}
	
	if (m_MessagePayload == value.m_MessagePayload)
	{
		return false;
	}
	
	return true;
}

Receive_1_1::Body::ReceiveRec::ReceiveRec()
{
	/// No Initialization of m_SourceID necessary.
	/// No Initialization of m_MessagePayload necessary.
}

Receive_1_1::Body::ReceiveRec::ReceiveRec(const ReceiveRec &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_SourceID necessary.
	/// No Initialization of m_MessagePayload necessary.
	
	/// Copy the values
	m_SourceID = value.m_SourceID;
	m_MessagePayload = value.m_MessagePayload;
}

Receive_1_1::Body::ReceiveRec::~ReceiveRec()
{
}

Receive_1_1::Body::ReceiveRec* const Receive_1_1::Body::getReceiveRec()
{
	return &m_ReceiveRec;
}

int Receive_1_1::Body::setReceiveRec(const ReceiveRec &value)
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
const unsigned int Receive_1_1::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ReceiveRec.getSize();
	
	return size;
}

void Receive_1_1::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReceiveRec.encode(bytes + pos);
	pos += m_ReceiveRec.getSize();
}

void Receive_1_1::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReceiveRec.decode(bytes + pos);
	pos += m_ReceiveRec.getSize();
}

Receive_1_1::Body &Receive_1_1::Body::operator=(const Body &value)
{
	m_ReceiveRec = value.m_ReceiveRec;
	/// This code is currently not supported
	
	return *this;
}

bool Receive_1_1::Body::operator==(const Body &value) const
{
	if (m_ReceiveRec != value.m_ReceiveRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool Receive_1_1::Body::operator!=(const Body &value) const
{
	if (m_ReceiveRec == value.m_ReceiveRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

Receive_1_1::Body::Body()
{
	/// No Initialization of m_ReceiveRec necessary.
}

Receive_1_1::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_ReceiveRec necessary.
	
	/// Copy the values
	m_ReceiveRec = value.m_ReceiveRec;
	/// This code is currently not supported
}

Receive_1_1::Body::~Body()
{
}

Receive_1_1::Body* const Receive_1_1::getBody()
{
	return &m_Body;
}

int Receive_1_1::setBody(const Body &value)
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
const unsigned int Receive_1_1::getSize()
{
	unsigned int size = 0;
	
	size += m_Body.getSize();
	
	return size;
}

void Receive_1_1::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.encode(bytes + pos);
	pos += m_Body.getSize();
}

void Receive_1_1::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

Receive_1_1 &Receive_1_1::operator=(const Receive_1_1 &value)
{
	m_Body = value.m_Body;
	
	return *this;
}

bool Receive_1_1::operator==(const Receive_1_1 &value) const
{
	if (m_Body != value.m_Body)
	{
		return false;
	}
	
	return true;
}

bool Receive_1_1::operator!=(const Receive_1_1 &value) const
{
	if (m_Body == value.m_Body)
	{
		return false;
	}
	
	return true;
}

Receive_1_1::Receive_1_1()
{
	/// No Initialization of m_Body necessary.
	m_Name = "Receive";
}

Receive_1_1::Receive_1_1(const Receive_1_1 &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_Body necessary.
	m_Name = "Receive";
	
	/// Copy the values
	m_Body = value.m_Body;
}

Receive_1_1::~Receive_1_1()
{
}


}
