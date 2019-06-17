#include "urn_jaus_jss_core_Discovery_1_1/Messages/QueryServiceList.h"

namespace urn_jaus_jss_core_Discovery_1_1
{

void QueryServiceList::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void QueryServiceList::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryServiceList::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryServiceList::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryServiceList::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryServiceList::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryServiceList::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryServiceList::MsgHeader::HeaderRec &QueryServiceList::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryServiceList::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryServiceList::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryServiceList::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2b04;
}

QueryServiceList::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2b04;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryServiceList::MsgHeader::HeaderRec::~HeaderRec()
{
}

QueryServiceList::MsgHeader::HeaderRec* const QueryServiceList::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryServiceList::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryServiceList::MsgHeader::setParentPresenceVector()
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
const unsigned int QueryServiceList::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryServiceList::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryServiceList::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryServiceList::MsgHeader &QueryServiceList::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryServiceList::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryServiceList::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

QueryServiceList::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryServiceList::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryServiceList::MsgHeader::~MsgHeader()
{
}

QueryServiceList::MsgHeader* const QueryServiceList::getMsgHeader()
{
	return &m_MsgHeader;
}

int QueryServiceList::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
	return 0;
}

void QueryServiceList::Body::SubsystemList::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryServiceList::Body::SubsystemList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::setParent(SubsystemList* parent)
{
	m_parent = parent;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::setParent(SubsystemSeq* parent)
{
	m_parent = parent;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::getSubsystemID()
{
	return m_SubsystemID;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::setSubsystemID(jUnsignedShortInteger value)
{
	if (((value >= 1) && (value <= 65534)) || (value == 0) || (value == 65535))
	{
		m_SubsystemID = value;
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
const unsigned int QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SubsystemIDTemp;
	
	m_SubsystemIDTemp = JSIDL_v_1_0::correctEndianness(m_SubsystemID);
	memcpy(bytes + pos, &m_SubsystemIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SubsystemIDTemp;
	
	memcpy(&m_SubsystemIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SubsystemID = JSIDL_v_1_0::correctEndianness(m_SubsystemIDTemp);
	pos += sizeof(jUnsignedShortInteger);
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec &QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::operator=(const SubsystemRec &value)
{
	m_SubsystemID = value.m_SubsystemID;
	
	return *this;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::operator==(const SubsystemRec &value) const
{
	if (m_SubsystemID != value.m_SubsystemID)
	{
		return false;
	}
	
	return true;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::operator!=(const SubsystemRec &value) const
{
	return !((*this) == value);
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::SubsystemRec()
{
	m_parent = NULL;
	m_SubsystemID = 0;
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::SubsystemRec(const SubsystemRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubsystemID = 0;
	
	/// Copy the values
	m_SubsystemID = value.m_SubsystemID;
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::~SubsystemRec()
{
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec* const QueryServiceList::Body::SubsystemList::SubsystemSeq::getSubsystemRec()
{
	return &m_SubsystemRec;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::setSubsystemRec(const SubsystemRec &value)
{
	m_SubsystemRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::setParent(SubsystemSeq* parent)
{
	m_parent = parent;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::setParent(NodeList* parent)
{
	m_parent = parent;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::setParent(NodeSeq* parent)
{
	m_parent = parent;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::getNodeID()
{
	return m_NodeID;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::setNodeID(jUnsignedByte value)
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
const unsigned int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::encode(unsigned char *bytes)
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

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::decode(const unsigned char *bytes)
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

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec &QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::operator=(const NodeRec &value)
{
	m_NodeID = value.m_NodeID;
	
	return *this;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::operator==(const NodeRec &value) const
{
	if (m_NodeID != value.m_NodeID)
	{
		return false;
	}
	
	return true;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::operator!=(const NodeRec &value) const
{
	return !((*this) == value);
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::NodeRec()
{
	m_parent = NULL;
	m_NodeID = 0;
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::NodeRec(const NodeRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_NodeID = 0;
	
	/// Copy the values
	m_NodeID = value.m_NodeID;
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::~NodeRec()
{
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec* const QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::getNodeRec()
{
	return &m_NodeRec;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::setNodeRec(const NodeRec &value)
{
	m_NodeRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::setParent(NodeSeq* parent)
{
	m_parent = parent;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::setParent(ComponentList* parent)
{
	m_parent = parent;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::getPresenceVector()
{
	return m_PresenceVector;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
	return 0;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

jUnsignedByte QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::getComponentID()
{
	return m_ComponentID;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::setComponentID(jUnsignedByte value)
{
	if (((value >= 1) && (value <= 254)) || (value == 0) || (value == 255))
	{
		m_ComponentID = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::isSearchFilterValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

jVariableLengthString QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::getSearchFilter()
{
	return m_SearchFilter;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::setSearchFilter(jVariableLengthString value)
{
	if ( value.length() > 255)
	{
		return 1;
	}
	
	m_SearchFilter = value;
	if (m_SearchFilter.length() < 0)
	{
		m_SearchFilter.resize(0);
	}
	setPresenceVector(0);
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
const unsigned int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		size += sizeof(jUnsignedByte);
		size += m_SearchFilter.length();
	}
	
	return size;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::encode(unsigned char *bytes)
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
	jUnsignedByte m_ComponentIDTemp;
	
	m_ComponentIDTemp = JSIDL_v_1_0::correctEndianness(m_ComponentID);
	memcpy(bytes + pos, &m_ComponentIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		
		jUnsignedByte m_SearchFilterLength = m_SearchFilter.length();
		m_SearchFilterLength = JSIDL_v_1_0::correctEndianness(m_SearchFilterLength);
		memcpy(bytes+pos, (unsigned char*)&m_SearchFilterLength, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
		
		memcpy(bytes+pos, m_SearchFilter.c_str(), m_SearchFilter.length());
		pos += m_SearchFilter.length();
	}
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::decode(const unsigned char *bytes)
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
	jUnsignedByte m_ComponentIDTemp;
	
	memcpy(&m_ComponentIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_ComponentID = JSIDL_v_1_0::correctEndianness(m_ComponentIDTemp);
	pos += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		
		jUnsignedByte m_SearchFilterLength = 0;
		memcpy((unsigned char*)&m_SearchFilterLength, bytes+pos, sizeof( m_SearchFilterLength ));
		m_SearchFilterLength = JSIDL_v_1_0::correctEndianness(m_SearchFilterLength);
		pos += sizeof( m_SearchFilterLength );
		
		char* m_SearchFilterTemp = new char[m_SearchFilterLength+1];
		memcpy(m_SearchFilterTemp, bytes+pos, m_SearchFilterLength );
		m_SearchFilterTemp[m_SearchFilterLength] = '\0';
		m_SearchFilter = m_SearchFilterTemp;
		pos += m_SearchFilterLength ;
		delete[] m_SearchFilterTemp;
	}
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec &QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::operator=(const ComponentRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_ComponentID = value.m_ComponentID;
	m_SearchFilter = value.m_SearchFilter;
	
	return *this;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::operator==(const ComponentRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	if (m_ComponentID != value.m_ComponentID)
	{
		return false;
	}
	if ((m_SearchFilter.length() != value.m_SearchFilter.length()) || (m_SearchFilter.compare(value.m_SearchFilter) != 0))
	{
		return false;
	}
	
	return true;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::operator!=(const ComponentRec &value) const
{
	return !((*this) == value);
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::ComponentRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_ComponentID = 0;
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::ComponentRec(const ComponentRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	m_ComponentID = 0;
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_ComponentID = value.m_ComponentID;
	m_SearchFilter = value.m_SearchFilter;
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec::~ComponentRec()
{
}

unsigned int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::getNumberOfElements() const
{
	return (unsigned int) m_ComponentRec.size();
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentRec* const QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::getElement(unsigned int index)
{
	return &m_ComponentRec.at(index);
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::setElement(unsigned int index, const ComponentRec &value)
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

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::addElement(const ComponentRec &value)
{
	m_ComponentRec.push_back(value);
	m_ComponentRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::deleteElement(unsigned int index)
{
	if(m_ComponentRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ComponentRec.erase(m_ComponentRec.begin()+index);
	return 0;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::deleteLastElement()
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
const unsigned int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_ComponentRec.size(); i++)
	{
		size += m_ComponentRec[i].getSize();
	}
	
	return size;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::encode(unsigned char *bytes)
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

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::decode(const unsigned char *bytes)
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

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList &QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::operator=(const ComponentList &value)
{
	m_ComponentRec.clear();
	
	for (int i = 0; i < value.m_ComponentRec.size(); i++)
	{
		m_ComponentRec.push_back(value.m_ComponentRec[i]);
		m_ComponentRec[i].setParent(this);
	}
	
	return *this;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::operator==(const ComponentList &value) const
{
	if (m_ComponentRec.size() !=  value.m_ComponentRec.size())
	{
		return false;
	}
	
	for (int i = 0; i < m_ComponentRec.size(); i++)
	{
		if (m_ComponentRec[i] !=  value.m_ComponentRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::operator!=(const ComponentList &value) const
{
	return !((*this) == value);
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ComponentRec.size(); i++)
	{
		m_ComponentRec[i].setParent(this);
	}
	/// No Initialization of m_ComponentRec necessary.
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentList(const ComponentList &value)
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

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::~ComponentList()
{
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList* const QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::getComponentList()
{
	return &m_ComponentList;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::setComponentList(const ComponentList &value)
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
const unsigned int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::getSize()
{
	unsigned int size = 0;
	
	size += m_NodeRec.getSize();
	size += m_ComponentList.getSize();
	
	return size;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::encode(unsigned char *bytes)
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

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::decode(const unsigned char *bytes)
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

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq &QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::operator=(const NodeSeq &value)
{
	m_NodeRec = value.m_NodeRec;
	m_NodeRec.setParent(this);
	m_NodeRec = value.m_NodeRec;
	m_ComponentList = value.m_ComponentList;
	m_ComponentList.setParent(this);
	m_ComponentList = value.m_ComponentList;
	
	return *this;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::operator==(const NodeSeq &value) const
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

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::operator!=(const NodeSeq &value) const
{
	return !((*this) == value);
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeSeq()
{
	m_parent = NULL;
	m_NodeRec.setParent(this);
	/// No Initialization of m_NodeRec necessary.
	m_ComponentList.setParent(this);
	/// No Initialization of m_ComponentList necessary.
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeSeq(const NodeSeq &value)
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

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::~NodeSeq()
{
}

unsigned int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::getNumberOfElements() const
{
	return (unsigned int) m_NodeSeq.size();
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq* const QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::getElement(unsigned int index)
{
	return &m_NodeSeq.at(index);
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::setElement(unsigned int index, const NodeSeq &value)
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

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::addElement(const NodeSeq &value)
{
	m_NodeSeq.push_back(value);
	m_NodeSeq.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::deleteElement(unsigned int index)
{
	if(m_NodeSeq.size()-1 < index)
	{
		return 1;
	}
	
	m_NodeSeq.erase(m_NodeSeq.begin()+index);
	return 0;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::deleteLastElement()
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
const unsigned int QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_NodeSeq.size(); i++)
	{
		size += m_NodeSeq[i].getSize();
	}
	
	return size;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::encode(unsigned char *bytes)
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

void QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::decode(const unsigned char *bytes)
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

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList &QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::operator=(const NodeList &value)
{
	m_NodeSeq.clear();
	
	for (int i = 0; i < value.m_NodeSeq.size(); i++)
	{
		m_NodeSeq.push_back(value.m_NodeSeq[i]);
		m_NodeSeq[i].setParent(this);
	}
	
	return *this;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::operator==(const NodeList &value) const
{
	if (m_NodeSeq.size() != value.m_NodeSeq.size())
	{
		return false;
	}
	
	for (int i = 0; i < m_NodeSeq.size(); i++)
	{
		if (m_NodeSeq[i] != value.m_NodeSeq[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::operator!=(const NodeList &value) const
{
	return !((*this) == value);
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_NodeSeq.size(); i++)
	{
		m_NodeSeq[i].setParent(this);
	}
	/// No Initialization of m_NodeSeq necessary.
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeList(const NodeList &value)
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

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList::~NodeList()
{
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::NodeList* const QueryServiceList::Body::SubsystemList::SubsystemSeq::getNodeList()
{
	return &m_NodeList;
}

int QueryServiceList::Body::SubsystemList::SubsystemSeq::setNodeList(const NodeList &value)
{
	m_NodeList = value;
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
const unsigned int QueryServiceList::Body::SubsystemList::SubsystemSeq::getSize()
{
	unsigned int size = 0;
	
	size += m_SubsystemRec.getSize();
	size += m_NodeList.getSize();
	
	return size;
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemRec.encode(bytes + pos);
	pos += m_SubsystemRec.getSize();
	m_NodeList.encode(bytes + pos);
	pos += m_NodeList.getSize();
}

void QueryServiceList::Body::SubsystemList::SubsystemSeq::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemRec.decode(bytes + pos);
	pos += m_SubsystemRec.getSize();
	m_NodeList.decode(bytes + pos);
	pos += m_NodeList.getSize();
}

QueryServiceList::Body::SubsystemList::SubsystemSeq &QueryServiceList::Body::SubsystemList::SubsystemSeq::operator=(const SubsystemSeq &value)
{
	m_SubsystemRec = value.m_SubsystemRec;
	m_SubsystemRec.setParent(this);
	m_SubsystemRec = value.m_SubsystemRec;
	m_NodeList = value.m_NodeList;
	m_NodeList.setParent(this);
	m_NodeList = value.m_NodeList;
	
	return *this;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::operator==(const SubsystemSeq &value) const
{
	if (m_SubsystemRec != value.m_SubsystemRec)
	{
		return false;
	}
	
	if (m_SubsystemRec != value.m_SubsystemRec)
	{
		return false;
	}
	if (m_NodeList != value.m_NodeList)
	{
		return false;
	}
	
	if (m_NodeList != value.m_NodeList)
	{
		return false;
	}
	
	return true;
}

bool QueryServiceList::Body::SubsystemList::SubsystemSeq::operator!=(const SubsystemSeq &value) const
{
	return !((*this) == value);
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemSeq()
{
	m_parent = NULL;
	m_SubsystemRec.setParent(this);
	/// No Initialization of m_SubsystemRec necessary.
	m_NodeList.setParent(this);
	/// No Initialization of m_NodeList necessary.
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::SubsystemSeq(const SubsystemSeq &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubsystemRec.setParent(this);
	/// No Initialization of m_SubsystemRec necessary.
	m_NodeList.setParent(this);
	/// No Initialization of m_NodeList necessary.
	
	/// Copy the values
	m_SubsystemRec = value.m_SubsystemRec;
	m_SubsystemRec.setParent(this);
	m_SubsystemRec = value.m_SubsystemRec;
	m_NodeList = value.m_NodeList;
	m_NodeList.setParent(this);
	m_NodeList = value.m_NodeList;
}

QueryServiceList::Body::SubsystemList::SubsystemSeq::~SubsystemSeq()
{
}

unsigned int QueryServiceList::Body::SubsystemList::getNumberOfElements() const
{
	return (unsigned int) m_SubsystemSeq.size();
}

QueryServiceList::Body::SubsystemList::SubsystemSeq* const QueryServiceList::Body::SubsystemList::getElement(unsigned int index)
{
	return &m_SubsystemSeq.at(index);
}

int QueryServiceList::Body::SubsystemList::setElement(unsigned int index, const SubsystemSeq &value)
{
	if(m_SubsystemSeq.size()-1 < index)
	{
		return 1;
	}
	
	m_SubsystemSeq.at(index) = value;
	m_SubsystemSeq.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryServiceList::Body::SubsystemList::addElement(const SubsystemSeq &value)
{
	m_SubsystemSeq.push_back(value);
	m_SubsystemSeq.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryServiceList::Body::SubsystemList::deleteElement(unsigned int index)
{
	if(m_SubsystemSeq.size()-1 < index)
	{
		return 1;
	}
	
	m_SubsystemSeq.erase(m_SubsystemSeq.begin()+index);
	return 0;
}

int QueryServiceList::Body::SubsystemList::deleteLastElement()
{
	m_SubsystemSeq.pop_back();
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
const unsigned int QueryServiceList::Body::SubsystemList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_SubsystemSeq.size(); i++)
	{
		size += m_SubsystemSeq[i].getSize();
	}
	
	return size;
}

void QueryServiceList::Body::SubsystemList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_SubsystemSeq.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_SubsystemSeq.size(); i++)
	{
		m_SubsystemSeq[i].encode(bytes + pos);
		pos += m_SubsystemSeq[i].getSize();
	}
}

void QueryServiceList::Body::SubsystemList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_SubsystemSeq.resize(size);
	for (int i = 0; i < m_SubsystemSeq.size(); i++)
	{
		m_SubsystemSeq[i].decode(bytes + pos);
		pos += m_SubsystemSeq[i].getSize();
	}
}

QueryServiceList::Body::SubsystemList &QueryServiceList::Body::SubsystemList::operator=(const SubsystemList &value)
{
	m_SubsystemSeq.clear();
	
	for (int i = 0; i < value.m_SubsystemSeq.size(); i++)
	{
		m_SubsystemSeq.push_back(value.m_SubsystemSeq[i]);
		m_SubsystemSeq[i].setParent(this);
	}
	
	return *this;
}

bool QueryServiceList::Body::SubsystemList::operator==(const SubsystemList &value) const
{
	if (m_SubsystemSeq.size() != value.m_SubsystemSeq.size())
	{
		return false;
	}
	
	for (int i = 0; i < m_SubsystemSeq.size(); i++)
	{
		if (m_SubsystemSeq[i] != value.m_SubsystemSeq[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QueryServiceList::Body::SubsystemList::operator!=(const SubsystemList &value) const
{
	return !((*this) == value);
}

QueryServiceList::Body::SubsystemList::SubsystemList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_SubsystemSeq.size(); i++)
	{
		m_SubsystemSeq[i].setParent(this);
	}
	/// No Initialization of m_SubsystemSeq necessary.
}

QueryServiceList::Body::SubsystemList::SubsystemList(const SubsystemList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_SubsystemSeq.size(); i++)
	{
		m_SubsystemSeq[i].setParent(this);
	}
	/// No Initialization of m_SubsystemSeq necessary.
	
	/// Copy the values
	m_SubsystemSeq.clear();
	
	for (int i = 0; i < value.m_SubsystemSeq.size(); i++)
	{
		m_SubsystemSeq.push_back(value.m_SubsystemSeq[i]);
		m_SubsystemSeq[i].setParent(this);
	}
}

QueryServiceList::Body::SubsystemList::~SubsystemList()
{
}

QueryServiceList::Body::SubsystemList* const QueryServiceList::Body::getSubsystemList()
{
	return &m_SubsystemList;
}

int QueryServiceList::Body::setSubsystemList(const SubsystemList &value)
{
	m_SubsystemList = value;
	setParentPresenceVector();
	return 0;
}

void QueryServiceList::Body::setParentPresenceVector()
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
const unsigned int QueryServiceList::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_SubsystemList.getSize();
	
	return size;
}

void QueryServiceList::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemList.encode(bytes + pos);
	pos += m_SubsystemList.getSize();
}

void QueryServiceList::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemList.decode(bytes + pos);
	pos += m_SubsystemList.getSize();
}

QueryServiceList::Body &QueryServiceList::Body::operator=(const Body &value)
{
	m_SubsystemList = value.m_SubsystemList;
	m_SubsystemList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryServiceList::Body::operator==(const Body &value) const
{
	if (m_SubsystemList != value.m_SubsystemList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryServiceList::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryServiceList::Body::Body()
{
	m_SubsystemList.setParent(this);
	/// No Initialization of m_SubsystemList necessary.
}

QueryServiceList::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_SubsystemList.setParent(this);
	/// No Initialization of m_SubsystemList necessary.
	
	/// Copy the values
	m_SubsystemList = value.m_SubsystemList;
	m_SubsystemList.setParent(this);
	/// This code is currently not supported
}

QueryServiceList::Body::~Body()
{
}

QueryServiceList::Body* const QueryServiceList::getBody()
{
	return &m_Body;
}

int QueryServiceList::setBody(const Body &value)
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
const unsigned int QueryServiceList::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryServiceList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MsgHeader.encode(bytes + pos);
	pos += m_MsgHeader.getSize();
	m_Body.encode(bytes + pos);
	pos += m_Body.getSize();
}

void QueryServiceList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MsgHeader.decode(bytes + pos);
	pos += m_MsgHeader.getSize();
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

QueryServiceList &QueryServiceList::operator=(const QueryServiceList &value)
{
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryServiceList::operator==(const QueryServiceList &value) const
{
	if (m_MsgHeader != value.m_MsgHeader)
	{
		return false;
	}
	if (m_Body != value.m_Body)
	{
		return false;
	}
	
	return true;
}

bool QueryServiceList::operator!=(const QueryServiceList &value) const
{
	return !((*this) == value);
}

QueryServiceList::QueryServiceList()
{
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryServiceList::QueryServiceList(const QueryServiceList &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
}

QueryServiceList::~QueryServiceList()
{
}


}
