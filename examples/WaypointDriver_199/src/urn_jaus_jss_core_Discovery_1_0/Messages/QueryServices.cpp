#include "urn_jaus_jss_core_Discovery_1_0/Messages/QueryServices.h"

namespace urn_jaus_jss_core_Discovery_1_0
{

void QueryServices::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryServices::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryServices::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryServices::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
{
	m_MessageID = value;
	setParentPresenceVector();
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
const unsigned int QueryServices::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryServices::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryServices::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryServices::AppHeader::HeaderRec &QueryServices::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryServices::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryServices::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryServices::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2b03;
}

QueryServices::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2b03;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryServices::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryServices::AppHeader::HeaderRec* const QueryServices::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryServices::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryServices::AppHeader::setParentPresenceVector()
{
	// Nothing needed here, placeholder function
}

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int QueryServices::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryServices::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryServices::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryServices::AppHeader &QueryServices::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryServices::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryServices::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryServices::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryServices::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryServices::AppHeader::~AppHeader()
{
}

QueryServices::AppHeader* const QueryServices::getAppHeader()
{
	return &m_AppHeader;
}

int QueryServices::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryServices::Body::NodeList::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryServices::Body::NodeList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryServices::Body::NodeList::NodeSeq::setParent(NodeList* parent)
{
	m_parent = parent;
}

void QueryServices::Body::NodeList::NodeSeq::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryServices::Body::NodeList::NodeSeq::NodeRec::setParent(NodeSeq* parent)
{
	m_parent = parent;
}

void QueryServices::Body::NodeList::NodeSeq::NodeRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte QueryServices::Body::NodeList::NodeSeq::NodeRec::getNodeID()
{
	return m_NodeID;
}

int QueryServices::Body::NodeList::NodeSeq::NodeRec::setNodeID(jUnsignedByte value)
{
	if (((value >= 1) && (value <= 254)) || (value == 0) || (value == 255))
	{
		m_NodeID = value;
		setParentPresenceVector();
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
	return !((*this) == value);
}

QueryServices::Body::NodeList::NodeSeq::NodeRec::NodeRec()
{
	m_parent = NULL;
	m_NodeID = 0;
}

QueryServices::Body::NodeList::NodeSeq::NodeRec::NodeRec(const NodeRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
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
	setParentPresenceVector();
	return 0;
}

void QueryServices::Body::NodeList::NodeSeq::ComponentList::setParent(NodeSeq* parent)
{
	m_parent = parent;
}

void QueryServices::Body::NodeList::NodeSeq::ComponentList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::setParent(ComponentList* parent)
{
	m_parent = parent;
}

void QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::getComponentID()
{
	return m_ComponentID;
}

int QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::setComponentID(jUnsignedByte value)
{
	if (((value >= 1) && (value <= 254)) || (value == 0) || (value == 255))
	{
		m_ComponentID = value;
		setParentPresenceVector();
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
	return !((*this) == value);
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::ComponentRec()
{
	m_parent = NULL;
	m_ComponentID = 0;
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec::ComponentRec(const ComponentRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
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
	m_ComponentRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryServices::Body::NodeList::NodeSeq::ComponentList::addElement(const ComponentRec &value)
{
	m_ComponentRec.push_back(value);
	m_ComponentRec.back().setParent(this);
	setParentPresenceVector();
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
		m_ComponentRec[i].setParent(this);
	}
	
	return *this;
}

bool QueryServices::Body::NodeList::NodeSeq::ComponentList::operator==(const ComponentList &value) const
{
	for (int i = 0; i < m_ComponentRec.size(); i++)
	{
		if (m_ComponentRec[i] !=  value.m_ComponentRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QueryServices::Body::NodeList::NodeSeq::ComponentList::operator!=(const ComponentList &value) const
{
	return !((*this) == value);
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ComponentRec.size(); i++)
	{
		m_ComponentRec[i].setParent(this);
	}
	/// No Initialization of m_ComponentRec necessary.
}

QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentList(const ComponentList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ComponentRec.size(); i++)
	{
		m_ComponentRec[i].setParent(this);
	}
	/// No Initialization of m_ComponentRec necessary.
	
	/// Copy the values
	m_ComponentRec.clear();
	
	for (int i = 0; i < value.m_ComponentRec.size(); i++)
	{
		m_ComponentRec.push_back(value.m_ComponentRec[i]);
		m_ComponentRec[i].setParent(this);
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
	setParentPresenceVector();
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
	m_NodeRec.setParent(this);
	m_NodeRec = value.m_NodeRec;
	m_ComponentList = value.m_ComponentList;
	m_ComponentList.setParent(this);
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
	return !((*this) == value);
}

QueryServices::Body::NodeList::NodeSeq::NodeSeq()
{
	m_parent = NULL;
	m_NodeRec.setParent(this);
	/// No Initialization of m_NodeRec necessary.
	m_ComponentList.setParent(this);
	/// No Initialization of m_ComponentList necessary.
}

QueryServices::Body::NodeList::NodeSeq::NodeSeq(const NodeSeq &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_NodeRec.setParent(this);
	/// No Initialization of m_NodeRec necessary.
	m_ComponentList.setParent(this);
	/// No Initialization of m_ComponentList necessary.
	
	/// Copy the values
	m_NodeRec = value.m_NodeRec;
	m_NodeRec.setParent(this);
	m_NodeRec = value.m_NodeRec;
	m_ComponentList = value.m_ComponentList;
	m_ComponentList.setParent(this);
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
	m_NodeSeq.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryServices::Body::NodeList::addElement(const NodeSeq &value)
{
	m_NodeSeq.push_back(value);
	m_NodeSeq.back().setParent(this);
	setParentPresenceVector();
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
		m_NodeSeq[i].setParent(this);
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
	return !((*this) == value);
}

QueryServices::Body::NodeList::NodeList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_NodeSeq.size(); i++)
	{
		m_NodeSeq[i].setParent(this);
	}
	/// No Initialization of m_NodeSeq necessary.
}

QueryServices::Body::NodeList::NodeList(const NodeList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_NodeSeq.size(); i++)
	{
		m_NodeSeq[i].setParent(this);
	}
	/// No Initialization of m_NodeSeq necessary.
	
	/// Copy the values
	m_NodeSeq.clear();
	
	for (int i = 0; i < value.m_NodeSeq.size(); i++)
	{
		m_NodeSeq.push_back(value.m_NodeSeq[i]);
		m_NodeSeq[i].setParent(this);
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
	setParentPresenceVector();
	return 0;
}

void QueryServices::Body::setParentPresenceVector()
{
	// Nothing needed here, placeholder function
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
	m_NodeList.setParent(this);
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
	return !((*this) == value);
}

QueryServices::Body::Body()
{
	m_NodeList.setParent(this);
	/// No Initialization of m_NodeList necessary.
}

QueryServices::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_NodeList.setParent(this);
	/// No Initialization of m_NodeList necessary.
	
	/// Copy the values
	m_NodeList = value.m_NodeList;
	m_NodeList.setParent(this);
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
	
	size += m_AppHeader.getSize();
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
	m_AppHeader.encode(bytes + pos);
	pos += m_AppHeader.getSize();
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
	m_AppHeader.decode(bytes + pos);
	pos += m_AppHeader.getSize();
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

QueryServices &QueryServices::operator=(const QueryServices &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryServices::operator==(const QueryServices &value) const
{
	if (m_AppHeader != value.m_AppHeader)
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
	return !((*this) == value);
}

QueryServices::QueryServices()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryServices::QueryServices(const QueryServices &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryServices::~QueryServices()
{
}


}
