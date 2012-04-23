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

#include "QueryServices.h"

namespace urn_jts_comms_test_client_1_0
{

jUnsignedShortInteger QueryServices::JAUSApplicationLayerHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryServices::JAUSApplicationLayerHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryServices::JAUSApplicationLayerHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryServices::JAUSApplicationLayerHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryServices::JAUSApplicationLayerHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryServices::JAUSApplicationLayerHeader::HeaderRec &QueryServices::JAUSApplicationLayerHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryServices::JAUSApplicationLayerHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryServices::JAUSApplicationLayerHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	if (m_MessageID == value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

QueryServices::JAUSApplicationLayerHeader::HeaderRec::HeaderRec()
{
	m_MessageID = 0x2b03;
}

QueryServices::JAUSApplicationLayerHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_MessageID = 0x2b03;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryServices::JAUSApplicationLayerHeader::HeaderRec::~HeaderRec()
{
}

QueryServices::JAUSApplicationLayerHeader::HeaderRec* const QueryServices::JAUSApplicationLayerHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryServices::JAUSApplicationLayerHeader::setHeaderRec(const HeaderRec &value)
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
const unsigned int QueryServices::JAUSApplicationLayerHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryServices::JAUSApplicationLayerHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryServices::JAUSApplicationLayerHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryServices::JAUSApplicationLayerHeader &QueryServices::JAUSApplicationLayerHeader::operator=(const JAUSApplicationLayerHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	
	return *this;
}

bool QueryServices::JAUSApplicationLayerHeader::operator==(const JAUSApplicationLayerHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryServices::JAUSApplicationLayerHeader::operator!=(const JAUSApplicationLayerHeader &value) const
{
	if (m_HeaderRec == value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

QueryServices::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader()
{
	/// No Initialization of m_HeaderRec necessary.
}

QueryServices::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
}

QueryServices::JAUSApplicationLayerHeader::~JAUSApplicationLayerHeader()
{
}

QueryServices::JAUSApplicationLayerHeader* const QueryServices::getJAUSApplicationLayerHeader()
{
	return &m_JAUSApplicationLayerHeader;
}

int QueryServices::setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	m_JAUSApplicationLayerHeader = value;
	return 0;
}

jUnsignedByte QueryServices::Body::NodeList::NodeSeq::NodeRec::getNodeID()
{
	return m_NodeID;
}

int QueryServices::Body::NodeList::NodeSeq::NodeRec::setNodeID(jUnsignedByte value)
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
const unsigned int QueryServices::Body::NodeList::NodeSeq::NodeRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryServices::Body::NodeList::NodeSeq::NodeRec::encode(unsigned char *bytes)
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

void QueryServices::Body::NodeList::NodeSeq::NodeRec::decode(const unsigned char *bytes)
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

QueryServices::Body::NodeList::NodeSeq::NodeRec &QueryServices::Body::NodeList::NodeSeq::NodeRec::operator=(const NodeRec &value)
{
	m_NodeID = value.m_NodeID;
	
	return *this;
}

bool QueryServices::Body::NodeList::NodeSeq::NodeRec::operator==(const NodeRec &value) const
{
	if (m_NodeID != value.m_NodeID)
	{
		return false;
	}
	
	return true;
}

bool QueryServices::Body::NodeList::NodeSeq::NodeRec::operator!=(const NodeRec &value) const
{
	if (m_NodeID == value.m_NodeID)
	{
		return false;
	}
	
	return true;
}

QueryServices::Body::NodeList::NodeSeq::NodeRec::NodeRec()
{
	m_NodeID = 0;
}

QueryServices::Body::NodeList::NodeSeq::NodeRec::NodeRec(const NodeRec &value)
{
	/// Initiliaze the protected variables
	m_NodeID = 0;
	
	/// Copy the values
	m_NodeID = value.m_NodeID;
}

QueryServices::Body::NodeList::NodeSeq::NodeRec::~NodeRec()
{
}

QueryServices::Body::NodeList::NodeSeq::NodeRec* const QueryServices::Body::NodeList::NodeSeq::getNodeRec()
{
	return &m_NodeRec;
}

int QueryServices::Body::NodeList::NodeSeq::setNodeRec(const NodeRec &value)
{
	m_NodeRec = value;
	return 0;
}

jUnsignedByte QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::getComponentID()
{
	return m_ComponentID;
}

int QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::setComponentID(jUnsignedByte value)
{
	m_ComponentID = value;
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
const unsigned int QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::encode(unsigned char *bytes)
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
}

void QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::decode(const unsigned char *bytes)
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
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec &QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::operator=(const ComponentRec &value)
{
	m_ComponentID = value.m_ComponentID;
	
	return *this;
}

bool QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::operator==(const ComponentRec &value) const
{
	if (m_ComponentID != value.m_ComponentID)
	{
		return false;
	}
	
	return true;
}

bool QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::operator!=(const ComponentRec &value) const
{
	if (m_ComponentID == value.m_ComponentID)
	{
		return false;
	}
	
	return true;
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::ComponentRec()
{
	m_ComponentID = 0;
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::ComponentRec(const ComponentRec &value)
{
	/// Initiliaze the protected variables
	m_ComponentID = 0;
	
	/// Copy the values
	m_ComponentID = value.m_ComponentID;
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::~ComponentRec()
{
}

unsigned int QueryServices::Body::NodeList::NodeSeq::ComponentList::getNumberOfElements() const
{
	return (unsigned int) m_ComponentRec.size();
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec* const QueryServices::Body::NodeList::NodeSeq::ComponentList::getElement(unsigned int index)
{
	return &m_ComponentRec.at(index);
}

int QueryServices::Body::NodeList::NodeSeq::ComponentList::setElement(unsigned int index, const ComponentRec &value)
{
	if(m_ComponentRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ComponentRec.at(index) = value;
	return 0;
}

int QueryServices::Body::NodeList::NodeSeq::ComponentList::addElement(const ComponentRec &value)
{
	m_ComponentRec.push_back(value);
	return 0;
}

int QueryServices::Body::NodeList::NodeSeq::ComponentList::deleteElement(unsigned int index)
{
	if(m_ComponentRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ComponentRec.erase(m_ComponentRec.begin()+index);
	return 0;
}

int QueryServices::Body::NodeList::NodeSeq::ComponentList::deleteLastElement()
{
	m_ComponentRec.pop_back();
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
const unsigned int QueryServices::Body::NodeList::NodeSeq::ComponentList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_ComponentRec.size(); i++)
	{
		size += m_ComponentRec[i].getSize();
	}
	
	return size;
}

void QueryServices::Body::NodeList::NodeSeq::ComponentList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_ComponentRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_ComponentRec.size(); i++)
	{
		m_ComponentRec[i].encode(bytes + pos);
		pos += m_ComponentRec[i].getSize();
	}
}

void QueryServices::Body::NodeList::NodeSeq::ComponentList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_ComponentRec.resize(size);
	for (int i = 0; i < m_ComponentRec.size(); i++)
	{
		m_ComponentRec[i].decode(bytes + pos);
		pos += m_ComponentRec[i].getSize();
	}
}

QueryServices::Body::NodeList::NodeSeq::ComponentList &QueryServices::Body::NodeList::NodeSeq::ComponentList::operator=(const ComponentList &value)
{
	m_ComponentRec.clear();
	
	for (int i = 0; i < value.m_ComponentRec.size(); i++)
	{
		m_ComponentRec.push_back(value.m_ComponentRec[i]);
	}
	
	return *this;
}

bool QueryServices::Body::NodeList::NodeSeq::ComponentList::operator==(const ComponentList &value) const
{
	for (int i = 0; i < m_ComponentRec.size(); i++)
	{
		if (m_ComponentRec[i] != value.m_ComponentRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QueryServices::Body::NodeList::NodeSeq::ComponentList::operator!=(const ComponentList &value) const
{
	if (m_ComponentRec == value.m_ComponentRec)
	{
		return false;
	}
	
	return true;
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentList()
{
	/// No Initialization of m_ComponentRec necessary.
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentList(const ComponentList &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_ComponentRec necessary.
	
	/// Copy the values
	m_ComponentRec.clear();
	
	for (int i = 0; i < value.m_ComponentRec.size(); i++)
	{
		m_ComponentRec.push_back(value.m_ComponentRec[i]);
	}
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::~ComponentList()
{
}

QueryServices::Body::NodeList::NodeSeq::ComponentList* const QueryServices::Body::NodeList::NodeSeq::getComponentList()
{
	return &m_ComponentList;
}

int QueryServices::Body::NodeList::NodeSeq::setComponentList(const ComponentList &value)
{
	m_ComponentList = value;
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
const unsigned int QueryServices::Body::NodeList::NodeSeq::getSize()
{
	unsigned int size = 0;
	
	size += m_NodeRec.getSize();
	size += m_ComponentList.getSize();
	
	return size;
}

void QueryServices::Body::NodeList::NodeSeq::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_NodeRec.encode(bytes + pos);
	pos += m_NodeRec.getSize();
	m_ComponentList.encode(bytes + pos);
	pos += m_ComponentList.getSize();
}

void QueryServices::Body::NodeList::NodeSeq::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_NodeRec.decode(bytes + pos);
	pos += m_NodeRec.getSize();
	m_ComponentList.decode(bytes + pos);
	pos += m_ComponentList.getSize();
}

QueryServices::Body::NodeList::NodeSeq &QueryServices::Body::NodeList::NodeSeq::operator=(const NodeSeq &value)
{
	m_NodeRec = value.m_NodeRec;
	m_NodeRec = value.m_NodeRec;
	m_ComponentList = value.m_ComponentList;
	m_ComponentList = value.m_ComponentList;
	
	return *this;
}

bool QueryServices::Body::NodeList::NodeSeq::operator==(const NodeSeq &value) const
{
	if (m_NodeRec != value.m_NodeRec)
	{
		return false;
	}
	
	if (m_NodeRec != value.m_NodeRec)
	{
		return false;
	}
	if (m_ComponentList != value.m_ComponentList)
	{
		return false;
	}
	
	if (m_ComponentList != value.m_ComponentList)
	{
		return false;
	}
	
	return true;
}

bool QueryServices::Body::NodeList::NodeSeq::operator!=(const NodeSeq &value) const
{
	if (m_NodeRec == value.m_NodeRec)
	{
		return false;
	}
	
	if (m_NodeRec == value.m_NodeRec)
	{
		return false;
	}
	if (m_ComponentList == value.m_ComponentList)
	{
		return false;
	}
	
	if (m_ComponentList == value.m_ComponentList)
	{
		return false;
	}
	
	return true;
}

QueryServices::Body::NodeList::NodeSeq::NodeSeq()
{
	/// No Initialization of m_NodeRec necessary.
	/// No Initialization of m_ComponentList necessary.
}

QueryServices::Body::NodeList::NodeSeq::NodeSeq(const NodeSeq &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_NodeRec necessary.
	/// No Initialization of m_ComponentList necessary.
	
	/// Copy the values
	m_NodeRec = value.m_NodeRec;
	m_NodeRec = value.m_NodeRec;
	m_ComponentList = value.m_ComponentList;
	m_ComponentList = value.m_ComponentList;
}

QueryServices::Body::NodeList::NodeSeq::~NodeSeq()
{
}

unsigned int QueryServices::Body::NodeList::getNumberOfElements() const
{
	return (unsigned int) m_NodeSeq.size();
}

QueryServices::Body::NodeList::NodeSeq* const QueryServices::Body::NodeList::getElement(unsigned int index)
{
	return &m_NodeSeq.at(index);
}

int QueryServices::Body::NodeList::setElement(unsigned int index, const NodeSeq &value)
{
	if(m_NodeSeq.size()-1 < index)
	{
		return 1;
	}
	
	m_NodeSeq.at(index) = value;
	return 0;
}

int QueryServices::Body::NodeList::addElement(const NodeSeq &value)
{
	m_NodeSeq.push_back(value);
	return 0;
}

int QueryServices::Body::NodeList::deleteElement(unsigned int index)
{
	if(m_NodeSeq.size()-1 < index)
	{
		return 1;
	}
	
	m_NodeSeq.erase(m_NodeSeq.begin()+index);
	return 0;
}

int QueryServices::Body::NodeList::deleteLastElement()
{
	m_NodeSeq.pop_back();
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
const unsigned int QueryServices::Body::NodeList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_NodeSeq.size(); i++)
	{
		size += m_NodeSeq[i].getSize();
	}
	
	return size;
}

void QueryServices::Body::NodeList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_NodeSeq.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_NodeSeq.size(); i++)
	{
		m_NodeSeq[i].encode(bytes + pos);
		pos += m_NodeSeq[i].getSize();
	}
}

void QueryServices::Body::NodeList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_NodeSeq.resize(size);
	for (int i = 0; i < m_NodeSeq.size(); i++)
	{
		m_NodeSeq[i].decode(bytes + pos);
		pos += m_NodeSeq[i].getSize();
	}
}

QueryServices::Body::NodeList &QueryServices::Body::NodeList::operator=(const NodeList &value)
{
	m_NodeSeq.clear();
	
	for (int i = 0; i < value.m_NodeSeq.size(); i++)
	{
		m_NodeSeq.push_back(value.m_NodeSeq[i]);
	}
	
	return *this;
}

bool QueryServices::Body::NodeList::operator==(const NodeList &value) const
{
	for (int i = 0; i < m_NodeSeq.size(); i++)
	{
		if (m_NodeSeq[i] != value.m_NodeSeq[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QueryServices::Body::NodeList::operator!=(const NodeList &value) const
{
	for (int i = 0; i < m_NodeSeq.size(); i++)
	{
		if (m_NodeSeq[i] == value.m_NodeSeq[i])
		{
			return false;
		}
	}
	
	return true;
}

QueryServices::Body::NodeList::NodeList()
{
	/// No Initialization of m_NodeSeq necessary.
}

QueryServices::Body::NodeList::NodeList(const NodeList &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_NodeSeq necessary.
	
	/// Copy the values
	m_NodeSeq.clear();
	
	for (int i = 0; i < value.m_NodeSeq.size(); i++)
	{
		m_NodeSeq.push_back(value.m_NodeSeq[i]);
	}
}

QueryServices::Body::NodeList::~NodeList()
{
}

QueryServices::Body::NodeList* const QueryServices::Body::getNodeList()
{
	return &m_NodeList;
}

int QueryServices::Body::setNodeList(const NodeList &value)
{
	m_NodeList = value;
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
const unsigned int QueryServices::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_NodeList.getSize();
	
	return size;
}

void QueryServices::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_NodeList.encode(bytes + pos);
	pos += m_NodeList.getSize();
}

void QueryServices::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_NodeList.decode(bytes + pos);
	pos += m_NodeList.getSize();
}

QueryServices::Body &QueryServices::Body::operator=(const Body &value)
{
	m_NodeList = value.m_NodeList;
	/// This code is currently not supported
	
	return *this;
}

bool QueryServices::Body::operator==(const Body &value) const
{
	if (m_NodeList != value.m_NodeList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryServices::Body::operator!=(const Body &value) const
{
	if (m_NodeList == value.m_NodeList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

QueryServices::Body::Body()
{
	/// No Initialization of m_NodeList necessary.
}

QueryServices::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_NodeList necessary.
	
	/// Copy the values
	m_NodeList = value.m_NodeList;
	/// This code is currently not supported
}

QueryServices::Body::~Body()
{
}

QueryServices::Body* const QueryServices::getBody()
{
	return &m_Body;
}

int QueryServices::setBody(const Body &value)
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
const unsigned int QueryServices::getSize()
{
	unsigned int size = 0;
	
	size += m_JAUSApplicationLayerHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryServices::encode(unsigned char *bytes)
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

void QueryServices::decode(const unsigned char *bytes)
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

QueryServices &QueryServices::operator=(const QueryServices &value)
{
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryServices::operator==(const QueryServices &value) const
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

bool QueryServices::operator!=(const QueryServices &value) const
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

QueryServices::QueryServices()
{
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryServices::QueryServices(const QueryServices &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
}

QueryServices::~QueryServices()
{
}


}
