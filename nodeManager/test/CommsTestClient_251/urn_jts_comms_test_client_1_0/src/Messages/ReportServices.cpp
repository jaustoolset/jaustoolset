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

#include "ReportServices.h"

namespace urn_jts_comms_test_client_1_0
{

jUnsignedShortInteger ReportServices::JAUSApplicationLayerHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportServices::JAUSApplicationLayerHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportServices::JAUSApplicationLayerHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportServices::JAUSApplicationLayerHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportServices::JAUSApplicationLayerHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportServices::JAUSApplicationLayerHeader::HeaderRec &ReportServices::JAUSApplicationLayerHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportServices::JAUSApplicationLayerHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportServices::JAUSApplicationLayerHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	if (m_MessageID == value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

ReportServices::JAUSApplicationLayerHeader::HeaderRec::HeaderRec()
{
	m_MessageID = 0x4b03;
}

ReportServices::JAUSApplicationLayerHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_MessageID = 0x4b03;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportServices::JAUSApplicationLayerHeader::HeaderRec::~HeaderRec()
{
}

ReportServices::JAUSApplicationLayerHeader::HeaderRec* const ReportServices::JAUSApplicationLayerHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportServices::JAUSApplicationLayerHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
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
const unsigned int ReportServices::JAUSApplicationLayerHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportServices::JAUSApplicationLayerHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportServices::JAUSApplicationLayerHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportServices::JAUSApplicationLayerHeader &ReportServices::JAUSApplicationLayerHeader::operator=(const JAUSApplicationLayerHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	
	return *this;
}

bool ReportServices::JAUSApplicationLayerHeader::operator==(const JAUSApplicationLayerHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportServices::JAUSApplicationLayerHeader::operator!=(const JAUSApplicationLayerHeader &value) const
{
	if (m_HeaderRec == value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

ReportServices::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader()
{
	/// No Initialization of m_HeaderRec necessary.
}

ReportServices::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
}

ReportServices::JAUSApplicationLayerHeader::~JAUSApplicationLayerHeader()
{
}

ReportServices::JAUSApplicationLayerHeader* const ReportServices::getJAUSApplicationLayerHeader()
{
	return &m_JAUSApplicationLayerHeader;
}

int ReportServices::setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	m_JAUSApplicationLayerHeader = value;
	return 0;
}

jUnsignedByte ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse::getNodeID()
{
	return m_NodeID;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse::setNodeID(jUnsignedByte value)
{
	m_NodeID = value;
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
const unsigned int ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_NodeIDTemp;
	
	m_NodeIDTemp = JSIDL_v_1_0::correctEndianness(m_NodeID);
	memcpy(bytes + pos, &m_NodeIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_NodeIDTemp;
	
	memcpy(&m_NodeIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_NodeID = JSIDL_v_1_0::correctEndianness(m_NodeIDTemp);
	pos += sizeof(jUnsignedByte);
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse &ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse::operator=(const NodeRecResponse &value)
{
	m_NodeID = value.m_NodeID;
	
	return *this;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse::operator==(const NodeRecResponse &value) const
{
	if (m_NodeID != value.m_NodeID)
	{
		return false;
	}
	
	return true;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse::operator!=(const NodeRecResponse &value) const
{
	if (m_NodeID == value.m_NodeID)
	{
		return false;
	}
	
	return true;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse::NodeRecResponse()
{
	m_NodeID = 0;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse::NodeRecResponse(const NodeRecResponse &value)
{
	/// Initiliaze the protected variables
	m_NodeID = 0;
	
	/// Copy the values
	m_NodeID = value.m_NodeID;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse::~NodeRecResponse()
{
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeRecResponse* const ReportServices::Body::NodeListResponse::NodeSeqResponse::getNodeRecResponse()
{
	return &m_NodeRecResponse;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::setNodeRecResponse(const NodeRecResponse &value)
{
	m_NodeRecResponse = value;
	return 0;
}

jUnsignedByte ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::getComponentID()
{
	return m_ComponentID;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::setComponentID(jUnsignedByte value)
{
	m_ComponentID = value;
	return 0;
}

jUnsignedByte ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::getInstanceID()
{
	return m_InstanceID;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::setInstanceID(jUnsignedByte value)
{
	m_InstanceID = value;
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
const unsigned int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_ComponentIDTemp;
	
	m_ComponentIDTemp = JSIDL_v_1_0::correctEndianness(m_ComponentID);
	memcpy(bytes + pos, &m_ComponentIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_InstanceIDTemp;
	
	m_InstanceIDTemp = JSIDL_v_1_0::correctEndianness(m_InstanceID);
	memcpy(bytes + pos, &m_InstanceIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_ComponentIDTemp;
	
	memcpy(&m_ComponentIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_ComponentID = JSIDL_v_1_0::correctEndianness(m_ComponentIDTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_InstanceIDTemp;
	
	memcpy(&m_InstanceIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_InstanceID = JSIDL_v_1_0::correctEndianness(m_InstanceIDTemp);
	pos += sizeof(jUnsignedByte);
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse &ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::operator=(const ComponentRecResponse &value)
{
	m_ComponentID = value.m_ComponentID;
	m_InstanceID = value.m_InstanceID;
	
	return *this;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::operator==(const ComponentRecResponse &value) const
{
	if (m_ComponentID != value.m_ComponentID)
	{
		return false;
	}
	if (m_InstanceID != value.m_InstanceID)
	{
		return false;
	}
	
	return true;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::operator!=(const ComponentRecResponse &value) const
{
	if (m_ComponentID == value.m_ComponentID)
	{
		return false;
	}
	if (m_InstanceID == value.m_InstanceID)
	{
		return false;
	}
	
	return true;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::ComponentRecResponse()
{
	m_ComponentID = 0;
	m_InstanceID = 0;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::ComponentRecResponse(const ComponentRecResponse &value)
{
	/// Initiliaze the protected variables
	m_ComponentID = 0;
	m_InstanceID = 0;
	
	/// Copy the values
	m_ComponentID = value.m_ComponentID;
	m_InstanceID = value.m_InstanceID;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse::~ComponentRecResponse()
{
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentRecResponse* const ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::getComponentRecResponse()
{
	return &m_ComponentRecResponse;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::setComponentRecResponse(const ComponentRecResponse &value)
{
	m_ComponentRecResponse = value;
	return 0;
}

jVariableLengthString ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::getURI()
{
	return m_URI;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::setURI(jVariableLengthString value)
{
	if ( value.length() > 255)
	{
		return 1;
	}
	
	m_URI = value;
	if (m_URI.length() < 0)
	{
		m_URI.resize(0);
	}
	return 0;
}

jUnsignedByte ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::getMajorVersionNumber()
{
	return m_MajorVersionNumber;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::setMajorVersionNumber(jUnsignedByte value)
{
	m_MajorVersionNumber = value;
	return 0;
}

jUnsignedByte ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::getMinorVersionNumber()
{
	return m_MinorVersionNumber;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::setMinorVersionNumber(jUnsignedByte value)
{
	m_MinorVersionNumber = value;
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
const unsigned int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += m_URI.length();
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte length = 0;
	
	length = JSIDL_v_1_0::correctEndianness(m_URI.length());
	memcpy(bytes+pos, (unsigned char*)&length, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	memcpy(bytes+pos, m_URI.c_str(), m_URI.length());
	pos += m_URI.length();
	jUnsignedByte m_MajorVersionNumberTemp;
	
	m_MajorVersionNumberTemp = JSIDL_v_1_0::correctEndianness(m_MajorVersionNumber);
	memcpy(bytes + pos, &m_MajorVersionNumberTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_MinorVersionNumberTemp;
	
	m_MinorVersionNumberTemp = JSIDL_v_1_0::correctEndianness(m_MinorVersionNumber);
	memcpy(bytes + pos, &m_MinorVersionNumberTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte length = 0;
	
	memcpy((unsigned char*)&length, bytes+pos, sizeof( length ));
	length = JSIDL_v_1_0::correctEndianness(length);
	pos += sizeof( length );
	
	char* m_URITemp = new char[length+1];
	memcpy(m_URITemp, bytes+pos, length );
	m_URITemp[length] = '\0';
	m_URI = m_URITemp;
	pos += length ;
	delete m_URITemp;
	jUnsignedByte m_MajorVersionNumberTemp;
	
	memcpy(&m_MajorVersionNumberTemp, bytes + pos, sizeof(jUnsignedByte));
	m_MajorVersionNumber = JSIDL_v_1_0::correctEndianness(m_MajorVersionNumberTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_MinorVersionNumberTemp;
	
	memcpy(&m_MinorVersionNumberTemp, bytes + pos, sizeof(jUnsignedByte));
	m_MinorVersionNumber = JSIDL_v_1_0::correctEndianness(m_MinorVersionNumberTemp);
	pos += sizeof(jUnsignedByte);
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec &ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::operator=(const ServiceRec &value)
{
	m_URI = value.m_URI;
	m_MajorVersionNumber = value.m_MajorVersionNumber;
	m_MinorVersionNumber = value.m_MinorVersionNumber;
	
	return *this;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::operator==(const ServiceRec &value) const
{
	if ((m_URI.length() != value.m_URI.length()) || (m_URI.compare(value.m_URI) != 0))
	{
		return false;
	}
	if (m_MajorVersionNumber != value.m_MajorVersionNumber)
	{
		return false;
	}
	if (m_MinorVersionNumber != value.m_MinorVersionNumber)
	{
		return false;
	}
	
	return true;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::operator!=(const ServiceRec &value) const
{
	if ((m_URI.length() == value.m_URI.length()) && (m_URI.compare(value.m_URI) == 0))
	{
		return false;
	}
	if (m_MajorVersionNumber == value.m_MajorVersionNumber)
	{
		return false;
	}
	if (m_MinorVersionNumber == value.m_MinorVersionNumber)
	{
		return false;
	}
	
	return true;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::ServiceRec()
{
	m_MajorVersionNumber = 0;
	m_MinorVersionNumber = 0;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::ServiceRec(const ServiceRec &value)
{
	/// Initiliaze the protected variables
	m_MajorVersionNumber = 0;
	m_MinorVersionNumber = 0;
	
	/// Copy the values
	m_URI = value.m_URI;
	m_MajorVersionNumber = value.m_MajorVersionNumber;
	m_MinorVersionNumber = value.m_MinorVersionNumber;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec::~ServiceRec()
{
}

unsigned int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::getNumberOfElements() const
{
	return (unsigned int) m_ServiceRec.size();
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceRec* const ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::getElement(unsigned int index)
{
	return &m_ServiceRec.at(index);
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::setElement(unsigned int index, const ServiceRec &value)
{
	if(m_ServiceRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ServiceRec.at(index) = value;
	return 0;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::addElement(const ServiceRec &value)
{
	m_ServiceRec.push_back(value);
	return 0;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::deleteElement(unsigned int index)
{
	if(m_ServiceRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ServiceRec.erase(m_ServiceRec.begin()+index);
	return 0;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::deleteLastElement()
{
	m_ServiceRec.pop_back();
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
const unsigned int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		size += m_ServiceRec[i].getSize();
	}
	
	return size;
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_ServiceRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		m_ServiceRec[i].encode(bytes + pos);
		pos += m_ServiceRec[i].getSize();
	}
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_ServiceRec.resize(size);
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		m_ServiceRec[i].decode(bytes + pos);
		pos += m_ServiceRec[i].getSize();
	}
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList &ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::operator=(const ServiceList &value)
{
	m_ServiceRec.clear();
	
	for (int i = 0; i < value.m_ServiceRec.size(); i++)
	{
		m_ServiceRec.push_back(value.m_ServiceRec[i]);
	}
	
	return *this;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::operator==(const ServiceList &value) const
{
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		if (m_ServiceRec[i] != value.m_ServiceRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::operator!=(const ServiceList &value) const
{
	if (m_ServiceRec == value.m_ServiceRec)
	{
		return false;
	}
	
	return true;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceList()
{
	/// No Initialization of m_ServiceRec necessary.
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::ServiceList(const ServiceList &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_ServiceRec necessary.
	
	/// Copy the values
	m_ServiceRec.clear();
	
	for (int i = 0; i < value.m_ServiceRec.size(); i++)
	{
		m_ServiceRec.push_back(value.m_ServiceRec[i]);
	}
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList::~ServiceList()
{
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ServiceList* const ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::getServiceList()
{
	return &m_ServiceList;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::setServiceList(const ServiceList &value)
{
	m_ServiceList = value;
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
const unsigned int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::getSize()
{
	unsigned int size = 0;
	
	size += m_ComponentRecResponse.getSize();
	size += m_ServiceList.getSize();
	
	return size;
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ComponentRecResponse.encode(bytes + pos);
	pos += m_ComponentRecResponse.getSize();
	m_ServiceList.encode(bytes + pos);
	pos += m_ServiceList.getSize();
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ComponentRecResponse.decode(bytes + pos);
	pos += m_ComponentRecResponse.getSize();
	m_ServiceList.decode(bytes + pos);
	pos += m_ServiceList.getSize();
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse &ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::operator=(const ComponentSeqResponse &value)
{
	m_ComponentRecResponse = value.m_ComponentRecResponse;
	m_ComponentRecResponse = value.m_ComponentRecResponse;
	m_ServiceList = value.m_ServiceList;
	m_ServiceList = value.m_ServiceList;
	
	return *this;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::operator==(const ComponentSeqResponse &value) const
{
	if (m_ComponentRecResponse != value.m_ComponentRecResponse)
	{
		return false;
	}
	
	if (m_ComponentRecResponse != value.m_ComponentRecResponse)
	{
		return false;
	}
	if (m_ServiceList != value.m_ServiceList)
	{
		return false;
	}
	
	if (m_ServiceList != value.m_ServiceList)
	{
		return false;
	}
	
	return true;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::operator!=(const ComponentSeqResponse &value) const
{
	if (m_ComponentRecResponse == value.m_ComponentRecResponse)
	{
		return false;
	}
	
	if (m_ComponentRecResponse == value.m_ComponentRecResponse)
	{
		return false;
	}
	if (m_ServiceList == value.m_ServiceList)
	{
		return false;
	}
	
	if (m_ServiceList == value.m_ServiceList)
	{
		return false;
	}
	
	return true;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentSeqResponse()
{
	/// No Initialization of m_ComponentRecResponse necessary.
	/// No Initialization of m_ServiceList necessary.
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::ComponentSeqResponse(const ComponentSeqResponse &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_ComponentRecResponse necessary.
	/// No Initialization of m_ServiceList necessary.
	
	/// Copy the values
	m_ComponentRecResponse = value.m_ComponentRecResponse;
	m_ComponentRecResponse = value.m_ComponentRecResponse;
	m_ServiceList = value.m_ServiceList;
	m_ServiceList = value.m_ServiceList;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse::~ComponentSeqResponse()
{
}

unsigned int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::getNumberOfElements() const
{
	return (unsigned int) m_ComponentSeqResponse.size();
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentSeqResponse* const ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::getElement(unsigned int index)
{
	return &m_ComponentSeqResponse.at(index);
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::setElement(unsigned int index, const ComponentSeqResponse &value)
{
	if(m_ComponentSeqResponse.size()-1 < index)
	{
		return 1;
	}
	
	m_ComponentSeqResponse.at(index) = value;
	return 0;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::addElement(const ComponentSeqResponse &value)
{
	m_ComponentSeqResponse.push_back(value);
	return 0;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::deleteElement(unsigned int index)
{
	if(m_ComponentSeqResponse.size()-1 < index)
	{
		return 1;
	}
	
	m_ComponentSeqResponse.erase(m_ComponentSeqResponse.begin()+index);
	return 0;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::deleteLastElement()
{
	m_ComponentSeqResponse.pop_back();
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
const unsigned int ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_ComponentSeqResponse.size(); i++)
	{
		size += m_ComponentSeqResponse[i].getSize();
	}
	
	return size;
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_ComponentSeqResponse.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_ComponentSeqResponse.size(); i++)
	{
		m_ComponentSeqResponse[i].encode(bytes + pos);
		pos += m_ComponentSeqResponse[i].getSize();
	}
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_ComponentSeqResponse.resize(size);
	for (int i = 0; i < m_ComponentSeqResponse.size(); i++)
	{
		m_ComponentSeqResponse[i].decode(bytes + pos);
		pos += m_ComponentSeqResponse[i].getSize();
	}
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse &ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::operator=(const ComponentListResponse &value)
{
	m_ComponentSeqResponse.clear();
	
	for (int i = 0; i < value.m_ComponentSeqResponse.size(); i++)
	{
		m_ComponentSeqResponse.push_back(value.m_ComponentSeqResponse[i]);
	}
	
	return *this;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::operator==(const ComponentListResponse &value) const
{
	for (int i = 0; i < m_ComponentSeqResponse.size(); i++)
	{
		if (m_ComponentSeqResponse[i] != value.m_ComponentSeqResponse[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::operator!=(const ComponentListResponse &value) const
{
	for (int i = 0; i < m_ComponentSeqResponse.size(); i++)
	{
		if (m_ComponentSeqResponse[i] == value.m_ComponentSeqResponse[i])
		{
			return false;
		}
	}
	
	return true;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentListResponse()
{
	/// No Initialization of m_ComponentSeqResponse necessary.
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::ComponentListResponse(const ComponentListResponse &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_ComponentSeqResponse necessary.
	
	/// Copy the values
	m_ComponentSeqResponse.clear();
	
	for (int i = 0; i < value.m_ComponentSeqResponse.size(); i++)
	{
		m_ComponentSeqResponse.push_back(value.m_ComponentSeqResponse[i]);
	}
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse::~ComponentListResponse()
{
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::ComponentListResponse* const ReportServices::Body::NodeListResponse::NodeSeqResponse::getComponentListResponse()
{
	return &m_ComponentListResponse;
}

int ReportServices::Body::NodeListResponse::NodeSeqResponse::setComponentListResponse(const ComponentListResponse &value)
{
	m_ComponentListResponse = value;
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
const unsigned int ReportServices::Body::NodeListResponse::NodeSeqResponse::getSize()
{
	unsigned int size = 0;
	
	size += m_NodeRecResponse.getSize();
	size += m_ComponentListResponse.getSize();
	
	return size;
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_NodeRecResponse.encode(bytes + pos);
	pos += m_NodeRecResponse.getSize();
	m_ComponentListResponse.encode(bytes + pos);
	pos += m_ComponentListResponse.getSize();
}

void ReportServices::Body::NodeListResponse::NodeSeqResponse::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_NodeRecResponse.decode(bytes + pos);
	pos += m_NodeRecResponse.getSize();
	m_ComponentListResponse.decode(bytes + pos);
	pos += m_ComponentListResponse.getSize();
}

ReportServices::Body::NodeListResponse::NodeSeqResponse &ReportServices::Body::NodeListResponse::NodeSeqResponse::operator=(const NodeSeqResponse &value)
{
	m_NodeRecResponse = value.m_NodeRecResponse;
	m_NodeRecResponse = value.m_NodeRecResponse;
	m_ComponentListResponse = value.m_ComponentListResponse;
	m_ComponentListResponse = value.m_ComponentListResponse;
	
	return *this;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::operator==(const NodeSeqResponse &value) const
{
	if (m_NodeRecResponse != value.m_NodeRecResponse)
	{
		return false;
	}
	
	if (m_NodeRecResponse != value.m_NodeRecResponse)
	{
		return false;
	}
	if (m_ComponentListResponse != value.m_ComponentListResponse)
	{
		return false;
	}
	
	if (m_ComponentListResponse != value.m_ComponentListResponse)
	{
		return false;
	}
	
	return true;
}

bool ReportServices::Body::NodeListResponse::NodeSeqResponse::operator!=(const NodeSeqResponse &value) const
{
	if (m_NodeRecResponse == value.m_NodeRecResponse)
	{
		return false;
	}
	
	if (m_NodeRecResponse == value.m_NodeRecResponse)
	{
		return false;
	}
	if (m_ComponentListResponse == value.m_ComponentListResponse)
	{
		return false;
	}
	
	if (m_ComponentListResponse == value.m_ComponentListResponse)
	{
		return false;
	}
	
	return true;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeSeqResponse()
{
	/// No Initialization of m_NodeRecResponse necessary.
	/// No Initialization of m_ComponentListResponse necessary.
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::NodeSeqResponse(const NodeSeqResponse &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_NodeRecResponse necessary.
	/// No Initialization of m_ComponentListResponse necessary.
	
	/// Copy the values
	m_NodeRecResponse = value.m_NodeRecResponse;
	m_NodeRecResponse = value.m_NodeRecResponse;
	m_ComponentListResponse = value.m_ComponentListResponse;
	m_ComponentListResponse = value.m_ComponentListResponse;
}

ReportServices::Body::NodeListResponse::NodeSeqResponse::~NodeSeqResponse()
{
}

unsigned int ReportServices::Body::NodeListResponse::getNumberOfElements() const
{
	return (unsigned int) m_NodeSeqResponse.size();
}

ReportServices::Body::NodeListResponse::NodeSeqResponse* const ReportServices::Body::NodeListResponse::getElement(unsigned int index)
{
	return &m_NodeSeqResponse.at(index);
}

int ReportServices::Body::NodeListResponse::setElement(unsigned int index, const NodeSeqResponse &value)
{
	if(m_NodeSeqResponse.size()-1 < index)
	{
		return 1;
	}
	
	m_NodeSeqResponse.at(index) = value;
	return 0;
}

int ReportServices::Body::NodeListResponse::addElement(const NodeSeqResponse &value)
{
	m_NodeSeqResponse.push_back(value);
	return 0;
}

int ReportServices::Body::NodeListResponse::deleteElement(unsigned int index)
{
	if(m_NodeSeqResponse.size()-1 < index)
	{
		return 1;
	}
	
	m_NodeSeqResponse.erase(m_NodeSeqResponse.begin()+index);
	return 0;
}

int ReportServices::Body::NodeListResponse::deleteLastElement()
{
	m_NodeSeqResponse.pop_back();
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
const unsigned int ReportServices::Body::NodeListResponse::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_NodeSeqResponse.size(); i++)
	{
		size += m_NodeSeqResponse[i].getSize();
	}
	
	return size;
}

void ReportServices::Body::NodeListResponse::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_NodeSeqResponse.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_NodeSeqResponse.size(); i++)
	{
		m_NodeSeqResponse[i].encode(bytes + pos);
		pos += m_NodeSeqResponse[i].getSize();
	}
}

void ReportServices::Body::NodeListResponse::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_NodeSeqResponse.resize(size);
	for (int i = 0; i < m_NodeSeqResponse.size(); i++)
	{
		m_NodeSeqResponse[i].decode(bytes + pos);
		pos += m_NodeSeqResponse[i].getSize();
	}
}

ReportServices::Body::NodeListResponse &ReportServices::Body::NodeListResponse::operator=(const NodeListResponse &value)
{
	m_NodeSeqResponse.clear();
	
	for (int i = 0; i < value.m_NodeSeqResponse.size(); i++)
	{
		m_NodeSeqResponse.push_back(value.m_NodeSeqResponse[i]);
	}
	
	return *this;
}

bool ReportServices::Body::NodeListResponse::operator==(const NodeListResponse &value) const
{
	for (int i = 0; i < m_NodeSeqResponse.size(); i++)
	{
		if (m_NodeSeqResponse[i] != value.m_NodeSeqResponse[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportServices::Body::NodeListResponse::operator!=(const NodeListResponse &value) const
{
	for (int i = 0; i < m_NodeSeqResponse.size(); i++)
	{
		if (m_NodeSeqResponse[i] == value.m_NodeSeqResponse[i])
		{
			return false;
		}
	}
	
	return true;
}

ReportServices::Body::NodeListResponse::NodeListResponse()
{
	/// No Initialization of m_NodeSeqResponse necessary.
}

ReportServices::Body::NodeListResponse::NodeListResponse(const NodeListResponse &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_NodeSeqResponse necessary.
	
	/// Copy the values
	m_NodeSeqResponse.clear();
	
	for (int i = 0; i < value.m_NodeSeqResponse.size(); i++)
	{
		m_NodeSeqResponse.push_back(value.m_NodeSeqResponse[i]);
	}
}

ReportServices::Body::NodeListResponse::~NodeListResponse()
{
}

ReportServices::Body::NodeListResponse* const ReportServices::Body::getNodeListResponse()
{
	return &m_NodeListResponse;
}

int ReportServices::Body::setNodeListResponse(const NodeListResponse &value)
{
	m_NodeListResponse = value;
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
const unsigned int ReportServices::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_NodeListResponse.getSize();
	
	return size;
}

void ReportServices::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_NodeListResponse.encode(bytes + pos);
	pos += m_NodeListResponse.getSize();
}

void ReportServices::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_NodeListResponse.decode(bytes + pos);
	pos += m_NodeListResponse.getSize();
}

ReportServices::Body &ReportServices::Body::operator=(const Body &value)
{
	m_NodeListResponse = value.m_NodeListResponse;
	/// This code is currently not supported
	
	return *this;
}

bool ReportServices::Body::operator==(const Body &value) const
{
	if (m_NodeListResponse != value.m_NodeListResponse)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportServices::Body::operator!=(const Body &value) const
{
	if (m_NodeListResponse == value.m_NodeListResponse)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

ReportServices::Body::Body()
{
	/// No Initialization of m_NodeListResponse necessary.
}

ReportServices::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_NodeListResponse necessary.
	
	/// Copy the values
	m_NodeListResponse = value.m_NodeListResponse;
	/// This code is currently not supported
}

ReportServices::Body::~Body()
{
}

ReportServices::Body* const ReportServices::getBody()
{
	return &m_Body;
}

int ReportServices::setBody(const Body &value)
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
const unsigned int ReportServices::getSize()
{
	unsigned int size = 0;
	
	size += m_JAUSApplicationLayerHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportServices::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_JAUSApplicationLayerHeader.encode(bytes + pos);
	pos += m_JAUSApplicationLayerHeader.getSize();
	m_Body.encode(bytes + pos);
	pos += m_Body.getSize();
}

void ReportServices::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_JAUSApplicationLayerHeader.decode(bytes + pos);
	pos += m_JAUSApplicationLayerHeader.getSize();
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

ReportServices &ReportServices::operator=(const ReportServices &value)
{
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportServices::operator==(const ReportServices &value) const
{
	if (m_JAUSApplicationLayerHeader != value.m_JAUSApplicationLayerHeader)
	{
		return false;
	}
	if (m_Body != value.m_Body)
	{
		return false;
	}
	
	return true;
}

bool ReportServices::operator!=(const ReportServices &value) const
{
	if (m_JAUSApplicationLayerHeader == value.m_JAUSApplicationLayerHeader)
	{
		return false;
	}
	if (m_Body == value.m_Body)
	{
		return false;
	}
	
	return true;
}

ReportServices::ReportServices()
{
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportServices::ReportServices(const ReportServices &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
}

ReportServices::~ReportServices()
{
}


}
