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

#include "CommsTestMsg.h"

namespace urn_jts_comms_test_client_1_0
{

jUnsignedShortInteger CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec::getMessageID()
{
	return m_MessageID;
}

int CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec::setMessageID(jUnsignedShortInteger value)
{
	m_MessageID = value;
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
const unsigned int CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_MessageIDTemp;
	
	m_MessageIDTemp = JSIDL_v_1_0::correctEndianness(m_MessageID);
	memcpy(bytes + pos, &m_MessageIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_MessageIDTemp;
	
	memcpy(&m_MessageIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_MessageID = JSIDL_v_1_0::correctEndianness(m_MessageIDTemp);
	pos += sizeof(jUnsignedShortInteger);
}

CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec &CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec::operator=(const DefaultHeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec::operator==(const DefaultHeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec::operator!=(const DefaultHeaderRec &value) const
{
	if (m_MessageID == value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec::DefaultHeaderRec()
{
	m_MessageID = 0xeeee;
}

CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec::DefaultHeaderRec(const DefaultHeaderRec &value)
{
	/// Initiliaze the protected variables
	m_MessageID = 0xeeee;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec::~DefaultHeaderRec()
{
}

CommsTestMsg::JTS_DefaultHeader::DefaultHeaderRec* const CommsTestMsg::JTS_DefaultHeader::getDefaultHeaderRec()
{
	return &m_DefaultHeaderRec;
}

int CommsTestMsg::JTS_DefaultHeader::setDefaultHeaderRec(const DefaultHeaderRec &value)
{
	m_DefaultHeaderRec = value;
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
const unsigned int CommsTestMsg::JTS_DefaultHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_DefaultHeaderRec.getSize();
	
	return size;
}

void CommsTestMsg::JTS_DefaultHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_DefaultHeaderRec.encode(bytes + pos);
	pos += m_DefaultHeaderRec.getSize();
}

void CommsTestMsg::JTS_DefaultHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_DefaultHeaderRec.decode(bytes + pos);
	pos += m_DefaultHeaderRec.getSize();
}

CommsTestMsg::JTS_DefaultHeader &CommsTestMsg::JTS_DefaultHeader::operator=(const JTS_DefaultHeader &value)
{
	m_DefaultHeaderRec = value.m_DefaultHeaderRec;
	
	return *this;
}

bool CommsTestMsg::JTS_DefaultHeader::operator==(const JTS_DefaultHeader &value) const
{
	if (m_DefaultHeaderRec != value.m_DefaultHeaderRec)
	{
		return false;
	}
	return true;
}

bool CommsTestMsg::JTS_DefaultHeader::operator!=(const JTS_DefaultHeader &value) const
{
	if (m_DefaultHeaderRec == value.m_DefaultHeaderRec)
	{
		return false;
	}
	return true;
}

CommsTestMsg::JTS_DefaultHeader::JTS_DefaultHeader()
{
	/// No Initialization of m_DefaultHeaderRec necessary.
}

CommsTestMsg::JTS_DefaultHeader::JTS_DefaultHeader(const JTS_DefaultHeader &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_DefaultHeaderRec necessary.
	
	/// Copy the values
	m_DefaultHeaderRec = value.m_DefaultHeaderRec;
}

CommsTestMsg::JTS_DefaultHeader::~JTS_DefaultHeader()
{
}

CommsTestMsg::JTS_DefaultHeader* const CommsTestMsg::getJTS_DefaultHeader()
{
	return &m_JTS_DefaultHeader;
}

int CommsTestMsg::setJTS_DefaultHeader(const JTS_DefaultHeader &value)
{
	m_JTS_DefaultHeader = value;
	return 0;
}

jUnsignedInteger CommsTestMsg::CommsTestBody::CommsTestRec::getTimeStamp()
{
	return m_TimeStamp;
}

int CommsTestMsg::CommsTestBody::CommsTestRec::setTimeStamp(jUnsignedInteger value)
{
	m_TimeStamp = value;
	return 0;
}

jUnsignedInteger CommsTestMsg::CommsTestBody::CommsTestRec::getSequenceNumber()
{
	return m_SequenceNumber;
}

int CommsTestMsg::CommsTestBody::CommsTestRec::setSequenceNumber(jUnsignedInteger value)
{
	m_SequenceNumber = value;
	return 0;
}

const jUnsignedInteger CommsTestMsg::CommsTestBody::CommsTestRec::Payload::getLength() const
{
	return m_Length;
}

const unsigned char *CommsTestMsg::CommsTestBody::CommsTestRec::Payload::getData() const
{
	return m_Data;
}

int CommsTestMsg::CommsTestBody::CommsTestRec::Payload::set(const jUnsignedInteger &length, const unsigned char *data)
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
const unsigned int CommsTestMsg::CommsTestBody::CommsTestRec::Payload::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void CommsTestMsg::CommsTestBody::CommsTestRec::Payload::encode(unsigned char *bytes)
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

void CommsTestMsg::CommsTestBody::CommsTestRec::Payload::decode(const unsigned char *bytes)
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

CommsTestMsg::CommsTestBody::CommsTestRec::Payload &CommsTestMsg::CommsTestBody::CommsTestRec::Payload::operator=(const Payload &value)
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

bool CommsTestMsg::CommsTestBody::CommsTestRec::Payload::operator==(const Payload &value) const
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

bool CommsTestMsg::CommsTestBody::CommsTestRec::Payload::operator!=(const Payload &value) const
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

CommsTestMsg::CommsTestBody::CommsTestRec::Payload::Payload()
{
	m_Length = 0;
	m_Data = NULL;
}

CommsTestMsg::CommsTestBody::CommsTestRec::Payload::Payload(const Payload &value)
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

CommsTestMsg::CommsTestBody::CommsTestRec::Payload::~Payload()
{
	delete[] m_Data;
}

CommsTestMsg::CommsTestBody::CommsTestRec::Payload* const CommsTestMsg::CommsTestBody::CommsTestRec::getPayload()
{
	return &m_Payload;
}

int CommsTestMsg::CommsTestBody::CommsTestRec::setPayload(const Payload &value)
{
	m_Payload = value;
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
const unsigned int CommsTestMsg::CommsTestBody::CommsTestRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += sizeof(jUnsignedInteger);
	size += m_Payload.getSize();
	
	return size;
}

void CommsTestMsg::CommsTestBody::CommsTestRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_TimeStampTemp;
	
	m_TimeStampTemp = JSIDL_v_1_0::correctEndianness(m_TimeStamp);
	memcpy(bytes + pos, &m_TimeStampTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
	jUnsignedInteger m_SequenceNumberTemp;
	
	m_SequenceNumberTemp = JSIDL_v_1_0::correctEndianness(m_SequenceNumber);
	memcpy(bytes + pos, &m_SequenceNumberTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
	m_Payload.encode(bytes + pos);
	pos += m_Payload.getSize();
}

void CommsTestMsg::CommsTestBody::CommsTestRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_TimeStampTemp;
	
	memcpy(&m_TimeStampTemp, bytes + pos, sizeof(jUnsignedInteger));
	m_TimeStamp = JSIDL_v_1_0::correctEndianness(m_TimeStampTemp);
	pos += sizeof(jUnsignedInteger);
	jUnsignedInteger m_SequenceNumberTemp;
	
	memcpy(&m_SequenceNumberTemp, bytes + pos, sizeof(jUnsignedInteger));
	m_SequenceNumber = JSIDL_v_1_0::correctEndianness(m_SequenceNumberTemp);
	pos += sizeof(jUnsignedInteger);
	m_Payload.decode(bytes + pos);
	pos += m_Payload.getSize();
}

CommsTestMsg::CommsTestBody::CommsTestRec &CommsTestMsg::CommsTestBody::CommsTestRec::operator=(const CommsTestRec &value)
{
	m_TimeStamp = value.m_TimeStamp;
	m_SequenceNumber = value.m_SequenceNumber;
	m_Payload = value.m_Payload;
	
	return *this;
}

bool CommsTestMsg::CommsTestBody::CommsTestRec::operator==(const CommsTestRec &value) const
{
	if (m_TimeStamp != value.m_TimeStamp)
	{
		return false;
	}
	if (m_SequenceNumber != value.m_SequenceNumber)
	{
		return false;
	}
	
	if (m_Payload != value.m_Payload)
	{
		return false;
	}
	
	return true;
}

bool CommsTestMsg::CommsTestBody::CommsTestRec::operator!=(const CommsTestRec &value) const
{
	if (m_TimeStamp == value.m_TimeStamp)
	{
		return false;
	}
	if (m_SequenceNumber == value.m_SequenceNumber)
	{
		return false;
	}
	
	if (m_Payload == value.m_Payload)
	{
		return false;
	}
	
	return true;
}

CommsTestMsg::CommsTestBody::CommsTestRec::CommsTestRec()
{
	m_TimeStamp = 0;
	m_SequenceNumber = 0;
	/// No Initialization of m_Payload necessary.
}

CommsTestMsg::CommsTestBody::CommsTestRec::CommsTestRec(const CommsTestRec &value)
{
	/// Initiliaze the protected variables
	m_TimeStamp = 0;
	m_SequenceNumber = 0;
	/// No Initialization of m_Payload necessary.
	
	/// Copy the values
	m_TimeStamp = value.m_TimeStamp;
	m_SequenceNumber = value.m_SequenceNumber;
	m_Payload = value.m_Payload;
}

CommsTestMsg::CommsTestBody::CommsTestRec::~CommsTestRec()
{
}

CommsTestMsg::CommsTestBody::CommsTestRec* const CommsTestMsg::CommsTestBody::getCommsTestRec()
{
	return &m_CommsTestRec;
}

int CommsTestMsg::CommsTestBody::setCommsTestRec(const CommsTestRec &value)
{
	m_CommsTestRec = value;
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
const unsigned int CommsTestMsg::CommsTestBody::getSize()
{
	unsigned int size = 0;
	
	size += m_CommsTestRec.getSize();
	
	return size;
}

void CommsTestMsg::CommsTestBody::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_CommsTestRec.encode(bytes + pos);
	pos += m_CommsTestRec.getSize();
}

void CommsTestMsg::CommsTestBody::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_CommsTestRec.decode(bytes + pos);
	pos += m_CommsTestRec.getSize();
}

CommsTestMsg::CommsTestBody &CommsTestMsg::CommsTestBody::operator=(const CommsTestBody &value)
{
	m_CommsTestRec = value.m_CommsTestRec;
	/// This code is currently not supported
	
	return *this;
}

bool CommsTestMsg::CommsTestBody::operator==(const CommsTestBody &value) const
{
	if (m_CommsTestRec != value.m_CommsTestRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool CommsTestMsg::CommsTestBody::operator!=(const CommsTestBody &value) const
{
	if (m_CommsTestRec == value.m_CommsTestRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

CommsTestMsg::CommsTestBody::CommsTestBody()
{
	/// No Initialization of m_CommsTestRec necessary.
}

CommsTestMsg::CommsTestBody::CommsTestBody(const CommsTestBody &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_CommsTestRec necessary.
	
	/// Copy the values
	m_CommsTestRec = value.m_CommsTestRec;
	/// This code is currently not supported
}

CommsTestMsg::CommsTestBody::~CommsTestBody()
{
}

CommsTestMsg::CommsTestBody* const CommsTestMsg::getCommsTestBody()
{
	return &m_CommsTestBody;
}

int CommsTestMsg::setCommsTestBody(const CommsTestBody &value)
{
	m_CommsTestBody = value;
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
const unsigned int CommsTestMsg::getSize()
{
	unsigned int size = 0;
	
	size += m_JTS_DefaultHeader.getSize();
	size += m_CommsTestBody.getSize();
	
	return size;
}

void CommsTestMsg::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_JTS_DefaultHeader.encode(bytes + pos);
	pos += m_JTS_DefaultHeader.getSize();
	m_CommsTestBody.encode(bytes + pos);
	pos += m_CommsTestBody.getSize();
}

void CommsTestMsg::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_JTS_DefaultHeader.decode(bytes + pos);
	pos += m_JTS_DefaultHeader.getSize();
	m_CommsTestBody.decode(bytes + pos);
	pos += m_CommsTestBody.getSize();
}

CommsTestMsg &CommsTestMsg::operator=(const CommsTestMsg &value)
{
	m_JTS_DefaultHeader = value.m_JTS_DefaultHeader;
	m_CommsTestBody = value.m_CommsTestBody;
	
	return *this;
}

bool CommsTestMsg::operator==(const CommsTestMsg &value) const
{
	if (m_JTS_DefaultHeader != value.m_JTS_DefaultHeader)
	{
		return false;
	}
	if (m_CommsTestBody != value.m_CommsTestBody)
	{
		return false;
	}
	
	return true;
}

bool CommsTestMsg::operator!=(const CommsTestMsg &value) const
{
	if (m_JTS_DefaultHeader == value.m_JTS_DefaultHeader)
	{
		return false;
	}
	if (m_CommsTestBody == value.m_CommsTestBody)
	{
		return false;
	}
	
	return true;
}

CommsTestMsg::CommsTestMsg()
{
	/// No Initialization of m_JTS_DefaultHeader necessary.
	/// No Initialization of m_CommsTestBody necessary.
	m_IsCommand = false;
}

CommsTestMsg::CommsTestMsg(const CommsTestMsg &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_JTS_DefaultHeader necessary.
	/// No Initialization of m_CommsTestBody necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_JTS_DefaultHeader = value.m_JTS_DefaultHeader;
	m_CommsTestBody = value.m_CommsTestBody;
}

CommsTestMsg::~CommsTestMsg()
{
}


}
