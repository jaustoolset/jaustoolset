#include "urn_jaus_jss_core_Discovery_1_1/Messages/ReportServiceList.h"

namespace urn_jaus_jss_core_Discovery_1_1
{

void ReportServiceList::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void ReportServiceList::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportServiceList::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportServiceList::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportServiceList::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportServiceList::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportServiceList::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportServiceList::MsgHeader::HeaderRec &ReportServiceList::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportServiceList::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportServiceList::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportServiceList::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4b04;
}

ReportServiceList::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4b04;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportServiceList::MsgHeader::HeaderRec::~HeaderRec()
{
}

ReportServiceList::MsgHeader::HeaderRec* const ReportServiceList::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportServiceList::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportServiceList::MsgHeader::setParentPresenceVector()
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
const unsigned int ReportServiceList::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportServiceList::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportServiceList::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportServiceList::MsgHeader &ReportServiceList::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportServiceList::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportServiceList::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

ReportServiceList::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportServiceList::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportServiceList::MsgHeader::~MsgHeader()
{
}

ReportServiceList::MsgHeader* const ReportServiceList::getMsgHeader()
{
	return &m_MsgHeader;
}

int ReportServiceList::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
	return 0;
}

void ReportServiceList::Body::SubsystemList::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportServiceList::Body::SubsystemList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::setParent(SubsystemList* parent)
{
	m_parent = parent;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::setParent(SubsystemSeq* parent)
{
	m_parent = parent;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::getSubsystemID()
{
	return m_SubsystemID;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::setSubsystemID(jUnsignedShortInteger value)
{
	m_SubsystemID = value;
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
const unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::encode(unsigned char *bytes)
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

void ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::decode(const unsigned char *bytes)
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

ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec &ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::operator=(const SubsystemRec &value)
{
	m_SubsystemID = value.m_SubsystemID;
	
	return *this;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::operator==(const SubsystemRec &value) const
{
	if (m_SubsystemID != value.m_SubsystemID)
	{
		return false;
	}
	
	return true;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::operator!=(const SubsystemRec &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::SubsystemRec()
{
	m_parent = NULL;
	m_SubsystemID = 0;
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::SubsystemRec(const SubsystemRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubsystemID = 0;
	
	/// Copy the values
	m_SubsystemID = value.m_SubsystemID;
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec::~SubsystemRec()
{
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemRec* const ReportServiceList::Body::SubsystemList::SubsystemSeq::getSubsystemRec()
{
	return &m_SubsystemRec;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::setSubsystemRec(const SubsystemRec &value)
{
	m_SubsystemRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::setParent(SubsystemSeq* parent)
{
	m_parent = parent;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::setParent(NodeList* parent)
{
	m_parent = parent;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::setParent(NodeSeq* parent)
{
	m_parent = parent;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::getNodeID()
{
	return m_NodeID;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::setNodeID(jUnsignedByte value)
{
	m_NodeID = value;
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
const unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::encode(unsigned char *bytes)
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

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::decode(const unsigned char *bytes)
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

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec &ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::operator=(const NodeRec &value)
{
	m_NodeID = value.m_NodeID;
	
	return *this;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::operator==(const NodeRec &value) const
{
	if (m_NodeID != value.m_NodeID)
	{
		return false;
	}
	
	return true;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::operator!=(const NodeRec &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::NodeRec()
{
	m_parent = NULL;
	m_NodeID = 0;
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::NodeRec(const NodeRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_NodeID = 0;
	
	/// Copy the values
	m_NodeID = value.m_NodeID;
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec::~NodeRec()
{
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeRec* const ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::getNodeRec()
{
	return &m_NodeRec;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::setNodeRec(const NodeRec &value)
{
	m_NodeRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::setParent(NodeSeq* parent)
{
	m_parent = parent;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::setParent(ComponentList* parent)
{
	m_parent = parent;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::setParent(ComponentSeq* parent)
{
	m_parent = parent;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::getComponentID()
{
	return m_ComponentID;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::setComponentID(jUnsignedByte value)
{
	m_ComponentID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::getInstanceID()
{
	return m_InstanceID;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::setInstanceID(jUnsignedByte value)
{
	m_InstanceID = value;
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
const unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::encode(unsigned char *bytes)
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

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::decode(const unsigned char *bytes)
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

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec &ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::operator=(const ComponentRec &value)
{
	m_ComponentID = value.m_ComponentID;
	m_InstanceID = value.m_InstanceID;
	
	return *this;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::operator==(const ComponentRec &value) const
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

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::operator!=(const ComponentRec &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::ComponentRec()
{
	m_parent = NULL;
	m_ComponentID = 0;
	m_InstanceID = 0;
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::ComponentRec(const ComponentRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_ComponentID = 0;
	m_InstanceID = 0;
	
	/// Copy the values
	m_ComponentID = value.m_ComponentID;
	m_InstanceID = value.m_InstanceID;
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::~ComponentRec()
{
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec* const ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::getComponentRec()
{
	return &m_ComponentRec;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::setComponentRec(const ComponentRec &value)
{
	m_ComponentRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::setParent(ComponentSeq* parent)
{
	m_parent = parent;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::setParent(ServiceList* parent)
{
	m_parent = parent;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jVariableLengthString ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::getURI()
{
	return m_URI;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::setURI(jVariableLengthString value)
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
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::getMajorVersionNumber()
{
	return m_MajorVersionNumber;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::setMajorVersionNumber(jUnsignedByte value)
{
	m_MajorVersionNumber = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::getMinorVersionNumber()
{
	return m_MinorVersionNumber;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::setMinorVersionNumber(jUnsignedByte value)
{
	m_MinorVersionNumber = value;
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
const unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += m_URI.length();
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	
	jUnsignedByte m_URILength = m_URI.length();
	m_URILength = JSIDL_v_1_0::correctEndianness(m_URILength);
	memcpy(bytes+pos, (unsigned char*)&m_URILength, sizeof(jUnsignedByte));
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

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	
	jUnsignedByte m_URILength = 0;
	memcpy((unsigned char*)&m_URILength, bytes+pos, sizeof( m_URILength ));
	m_URILength = JSIDL_v_1_0::correctEndianness(m_URILength);
	pos += sizeof( m_URILength );
	
	char* m_URITemp = new char[m_URILength+1];
	memcpy(m_URITemp, bytes+pos, m_URILength );
	m_URITemp[m_URILength] = '\0';
	m_URI = m_URITemp;
	pos += m_URILength ;
	delete[] m_URITemp;
	jUnsignedByte m_MajorVersionNumberTemp;
	
	memcpy(&m_MajorVersionNumberTemp, bytes + pos, sizeof(jUnsignedByte));
	m_MajorVersionNumber = JSIDL_v_1_0::correctEndianness(m_MajorVersionNumberTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_MinorVersionNumberTemp;
	
	memcpy(&m_MinorVersionNumberTemp, bytes + pos, sizeof(jUnsignedByte));
	m_MinorVersionNumber = JSIDL_v_1_0::correctEndianness(m_MinorVersionNumberTemp);
	pos += sizeof(jUnsignedByte);
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec &ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::operator=(const ServiceRec &value)
{
	m_URI = value.m_URI;
	m_MajorVersionNumber = value.m_MajorVersionNumber;
	m_MinorVersionNumber = value.m_MinorVersionNumber;
	
	return *this;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::operator==(const ServiceRec &value) const
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

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::operator!=(const ServiceRec &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::ServiceRec()
{
	m_parent = NULL;
	m_MajorVersionNumber = 0;
	m_MinorVersionNumber = 0;
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::ServiceRec(const ServiceRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MajorVersionNumber = 0;
	m_MinorVersionNumber = 0;
	
	/// Copy the values
	m_URI = value.m_URI;
	m_MajorVersionNumber = value.m_MajorVersionNumber;
	m_MinorVersionNumber = value.m_MinorVersionNumber;
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::~ServiceRec()
{
}

unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::getNumberOfElements() const
{
	return (unsigned int) m_ServiceRec.size();
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec* const ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::getElement(unsigned int index)
{
	return &m_ServiceRec.at(index);
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::setElement(unsigned int index, const ServiceRec &value)
{
	if(m_ServiceRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ServiceRec.at(index) = value;
	m_ServiceRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::addElement(const ServiceRec &value)
{
	m_ServiceRec.push_back(value);
	m_ServiceRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::deleteElement(unsigned int index)
{
	if(m_ServiceRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ServiceRec.erase(m_ServiceRec.begin()+index);
	return 0;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::deleteLastElement()
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
const unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		size += m_ServiceRec[i].getSize();
	}
	
	return size;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::encode(unsigned char *bytes)
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

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::decode(const unsigned char *bytes)
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

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList &ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::operator=(const ServiceList &value)
{
	m_ServiceRec.clear();
	
	for (int i = 0; i < value.m_ServiceRec.size(); i++)
	{
		m_ServiceRec.push_back(value.m_ServiceRec[i]);
		m_ServiceRec[i].setParent(this);
	}
	
	return *this;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::operator==(const ServiceList &value) const
{
	if (m_ServiceRec.size() !=  value.m_ServiceRec.size())
	{
		return false;
	}
	
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		if (m_ServiceRec[i] !=  value.m_ServiceRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::operator!=(const ServiceList &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ServiceRec.size(); i++)
	{
		m_ServiceRec[i].setParent(this);
	}
	/// No Initialization of m_ServiceRec necessary.
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceList(const ServiceList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ServiceRec.size(); i++)
	{
		m_ServiceRec[i].setParent(this);
	}
	/// No Initialization of m_ServiceRec necessary.
	
	/// Copy the values
	m_ServiceRec.clear();
	
	for (int i = 0; i < value.m_ServiceRec.size(); i++)
	{
		m_ServiceRec.push_back(value.m_ServiceRec[i]);
		m_ServiceRec[i].setParent(this);
	}
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::~ServiceList()
{
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList* const ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::getServiceList()
{
	return &m_ServiceList;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::setServiceList(const ServiceList &value)
{
	m_ServiceList = value;
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
const unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::getSize()
{
	unsigned int size = 0;
	
	size += m_ComponentRec.getSize();
	size += m_ServiceList.getSize();
	
	return size;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ComponentRec.encode(bytes + pos);
	pos += m_ComponentRec.getSize();
	m_ServiceList.encode(bytes + pos);
	pos += m_ServiceList.getSize();
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ComponentRec.decode(bytes + pos);
	pos += m_ComponentRec.getSize();
	m_ServiceList.decode(bytes + pos);
	pos += m_ServiceList.getSize();
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq &ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::operator=(const ComponentSeq &value)
{
	m_ComponentRec = value.m_ComponentRec;
	m_ComponentRec.setParent(this);
	m_ComponentRec = value.m_ComponentRec;
	m_ServiceList = value.m_ServiceList;
	m_ServiceList.setParent(this);
	m_ServiceList = value.m_ServiceList;
	
	return *this;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::operator==(const ComponentSeq &value) const
{
	if (m_ComponentRec != value.m_ComponentRec)
	{
		return false;
	}
	
	if (m_ComponentRec != value.m_ComponentRec)
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

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::operator!=(const ComponentSeq &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentSeq()
{
	m_parent = NULL;
	m_ComponentRec.setParent(this);
	/// No Initialization of m_ComponentRec necessary.
	m_ServiceList.setParent(this);
	/// No Initialization of m_ServiceList necessary.
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentSeq(const ComponentSeq &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_ComponentRec.setParent(this);
	/// No Initialization of m_ComponentRec necessary.
	m_ServiceList.setParent(this);
	/// No Initialization of m_ServiceList necessary.
	
	/// Copy the values
	m_ComponentRec = value.m_ComponentRec;
	m_ComponentRec.setParent(this);
	m_ComponentRec = value.m_ComponentRec;
	m_ServiceList = value.m_ServiceList;
	m_ServiceList.setParent(this);
	m_ServiceList = value.m_ServiceList;
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::~ComponentSeq()
{
}

unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::getNumberOfElements() const
{
	return (unsigned int) m_ComponentSeq.size();
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq* const ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::getElement(unsigned int index)
{
	return &m_ComponentSeq.at(index);
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::setElement(unsigned int index, const ComponentSeq &value)
{
	if(m_ComponentSeq.size()-1 < index)
	{
		return 1;
	}
	
	m_ComponentSeq.at(index) = value;
	m_ComponentSeq.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::addElement(const ComponentSeq &value)
{
	m_ComponentSeq.push_back(value);
	m_ComponentSeq.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::deleteElement(unsigned int index)
{
	if(m_ComponentSeq.size()-1 < index)
	{
		return 1;
	}
	
	m_ComponentSeq.erase(m_ComponentSeq.begin()+index);
	return 0;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::deleteLastElement()
{
	m_ComponentSeq.pop_back();
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
const unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_ComponentSeq.size(); i++)
	{
		size += m_ComponentSeq[i].getSize();
	}
	
	return size;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_ComponentSeq.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_ComponentSeq.size(); i++)
	{
		m_ComponentSeq[i].encode(bytes + pos);
		pos += m_ComponentSeq[i].getSize();
	}
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_ComponentSeq.resize(size);
	for (int i = 0; i < m_ComponentSeq.size(); i++)
	{
		m_ComponentSeq[i].decode(bytes + pos);
		pos += m_ComponentSeq[i].getSize();
	}
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList &ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::operator=(const ComponentList &value)
{
	m_ComponentSeq.clear();
	
	for (int i = 0; i < value.m_ComponentSeq.size(); i++)
	{
		m_ComponentSeq.push_back(value.m_ComponentSeq[i]);
		m_ComponentSeq[i].setParent(this);
	}
	
	return *this;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::operator==(const ComponentList &value) const
{
	if (m_ComponentSeq.size() != value.m_ComponentSeq.size())
	{
		return false;
	}
	
	for (int i = 0; i < m_ComponentSeq.size(); i++)
	{
		if (m_ComponentSeq[i] != value.m_ComponentSeq[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::operator!=(const ComponentList &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ComponentSeq.size(); i++)
	{
		m_ComponentSeq[i].setParent(this);
	}
	/// No Initialization of m_ComponentSeq necessary.
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentList(const ComponentList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ComponentSeq.size(); i++)
	{
		m_ComponentSeq[i].setParent(this);
	}
	/// No Initialization of m_ComponentSeq necessary.
	
	/// Copy the values
	m_ComponentSeq.clear();
	
	for (int i = 0; i < value.m_ComponentSeq.size(); i++)
	{
		m_ComponentSeq.push_back(value.m_ComponentSeq[i]);
		m_ComponentSeq[i].setParent(this);
	}
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::~ComponentList()
{
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList* const ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::getComponentList()
{
	return &m_ComponentList;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::setComponentList(const ComponentList &value)
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
const unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::getSize()
{
	unsigned int size = 0;
	
	size += m_NodeRec.getSize();
	size += m_ComponentList.getSize();
	
	return size;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::encode(unsigned char *bytes)
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

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::decode(const unsigned char *bytes)
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

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq &ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::operator=(const NodeSeq &value)
{
	m_NodeRec = value.m_NodeRec;
	m_NodeRec.setParent(this);
	m_NodeRec = value.m_NodeRec;
	m_ComponentList = value.m_ComponentList;
	m_ComponentList.setParent(this);
	m_ComponentList = value.m_ComponentList;
	
	return *this;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::operator==(const NodeSeq &value) const
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

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::operator!=(const NodeSeq &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeSeq()
{
	m_parent = NULL;
	m_NodeRec.setParent(this);
	/// No Initialization of m_NodeRec necessary.
	m_ComponentList.setParent(this);
	/// No Initialization of m_ComponentList necessary.
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::NodeSeq(const NodeSeq &value)
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

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::~NodeSeq()
{
}

unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::getNumberOfElements() const
{
	return (unsigned int) m_NodeSeq.size();
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq* const ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::getElement(unsigned int index)
{
	return &m_NodeSeq.at(index);
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::setElement(unsigned int index, const NodeSeq &value)
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

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::addElement(const NodeSeq &value)
{
	m_NodeSeq.push_back(value);
	m_NodeSeq.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::deleteElement(unsigned int index)
{
	if(m_NodeSeq.size()-1 < index)
	{
		return 1;
	}
	
	m_NodeSeq.erase(m_NodeSeq.begin()+index);
	return 0;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::deleteLastElement()
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
const unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_NodeSeq.size(); i++)
	{
		size += m_NodeSeq[i].getSize();
	}
	
	return size;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::encode(unsigned char *bytes)
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

void ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::decode(const unsigned char *bytes)
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

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList &ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::operator=(const NodeList &value)
{
	m_NodeSeq.clear();
	
	for (int i = 0; i < value.m_NodeSeq.size(); i++)
	{
		m_NodeSeq.push_back(value.m_NodeSeq[i]);
		m_NodeSeq[i].setParent(this);
	}
	
	return *this;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::operator==(const NodeList &value) const
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

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::operator!=(const NodeList &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_NodeSeq.size(); i++)
	{
		m_NodeSeq[i].setParent(this);
	}
	/// No Initialization of m_NodeSeq necessary.
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeList(const NodeList &value)
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

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::~NodeList()
{
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList* const ReportServiceList::Body::SubsystemList::SubsystemSeq::getNodeList()
{
	return &m_NodeList;
}

int ReportServiceList::Body::SubsystemList::SubsystemSeq::setNodeList(const NodeList &value)
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
const unsigned int ReportServiceList::Body::SubsystemList::SubsystemSeq::getSize()
{
	unsigned int size = 0;
	
	size += m_SubsystemRec.getSize();
	size += m_NodeList.getSize();
	
	return size;
}

void ReportServiceList::Body::SubsystemList::SubsystemSeq::encode(unsigned char *bytes)
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

void ReportServiceList::Body::SubsystemList::SubsystemSeq::decode(const unsigned char *bytes)
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

ReportServiceList::Body::SubsystemList::SubsystemSeq &ReportServiceList::Body::SubsystemList::SubsystemSeq::operator=(const SubsystemSeq &value)
{
	m_SubsystemRec = value.m_SubsystemRec;
	m_SubsystemRec.setParent(this);
	m_SubsystemRec = value.m_SubsystemRec;
	m_NodeList = value.m_NodeList;
	m_NodeList.setParent(this);
	m_NodeList = value.m_NodeList;
	
	return *this;
}

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::operator==(const SubsystemSeq &value) const
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

bool ReportServiceList::Body::SubsystemList::SubsystemSeq::operator!=(const SubsystemSeq &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemSeq()
{
	m_parent = NULL;
	m_SubsystemRec.setParent(this);
	/// No Initialization of m_SubsystemRec necessary.
	m_NodeList.setParent(this);
	/// No Initialization of m_NodeList necessary.
}

ReportServiceList::Body::SubsystemList::SubsystemSeq::SubsystemSeq(const SubsystemSeq &value)
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

ReportServiceList::Body::SubsystemList::SubsystemSeq::~SubsystemSeq()
{
}

unsigned int ReportServiceList::Body::SubsystemList::getNumberOfElements() const
{
	return (unsigned int) m_SubsystemSeq.size();
}

ReportServiceList::Body::SubsystemList::SubsystemSeq* const ReportServiceList::Body::SubsystemList::getElement(unsigned int index)
{
	return &m_SubsystemSeq.at(index);
}

int ReportServiceList::Body::SubsystemList::setElement(unsigned int index, const SubsystemSeq &value)
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

int ReportServiceList::Body::SubsystemList::addElement(const SubsystemSeq &value)
{
	m_SubsystemSeq.push_back(value);
	m_SubsystemSeq.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportServiceList::Body::SubsystemList::deleteElement(unsigned int index)
{
	if(m_SubsystemSeq.size()-1 < index)
	{
		return 1;
	}
	
	m_SubsystemSeq.erase(m_SubsystemSeq.begin()+index);
	return 0;
}

int ReportServiceList::Body::SubsystemList::deleteLastElement()
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
const unsigned int ReportServiceList::Body::SubsystemList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_SubsystemSeq.size(); i++)
	{
		size += m_SubsystemSeq[i].getSize();
	}
	
	return size;
}

void ReportServiceList::Body::SubsystemList::encode(unsigned char *bytes)
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

void ReportServiceList::Body::SubsystemList::decode(const unsigned char *bytes)
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

ReportServiceList::Body::SubsystemList &ReportServiceList::Body::SubsystemList::operator=(const SubsystemList &value)
{
	m_SubsystemSeq.clear();
	
	for (int i = 0; i < value.m_SubsystemSeq.size(); i++)
	{
		m_SubsystemSeq.push_back(value.m_SubsystemSeq[i]);
		m_SubsystemSeq[i].setParent(this);
	}
	
	return *this;
}

bool ReportServiceList::Body::SubsystemList::operator==(const SubsystemList &value) const
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

bool ReportServiceList::Body::SubsystemList::operator!=(const SubsystemList &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::SubsystemList::SubsystemList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_SubsystemSeq.size(); i++)
	{
		m_SubsystemSeq[i].setParent(this);
	}
	/// No Initialization of m_SubsystemSeq necessary.
}

ReportServiceList::Body::SubsystemList::SubsystemList(const SubsystemList &value)
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

ReportServiceList::Body::SubsystemList::~SubsystemList()
{
}

ReportServiceList::Body::SubsystemList* const ReportServiceList::Body::getSubsystemList()
{
	return &m_SubsystemList;
}

int ReportServiceList::Body::setSubsystemList(const SubsystemList &value)
{
	m_SubsystemList = value;
	setParentPresenceVector();
	return 0;
}

void ReportServiceList::Body::setParentPresenceVector()
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
const unsigned int ReportServiceList::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_SubsystemList.getSize();
	
	return size;
}

void ReportServiceList::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemList.encode(bytes + pos);
	pos += m_SubsystemList.getSize();
}

void ReportServiceList::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemList.decode(bytes + pos);
	pos += m_SubsystemList.getSize();
}

ReportServiceList::Body &ReportServiceList::Body::operator=(const Body &value)
{
	m_SubsystemList = value.m_SubsystemList;
	m_SubsystemList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportServiceList::Body::operator==(const Body &value) const
{
	if (m_SubsystemList != value.m_SubsystemList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportServiceList::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportServiceList::Body::Body()
{
	m_SubsystemList.setParent(this);
	/// No Initialization of m_SubsystemList necessary.
}

ReportServiceList::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_SubsystemList.setParent(this);
	/// No Initialization of m_SubsystemList necessary.
	
	/// Copy the values
	m_SubsystemList = value.m_SubsystemList;
	m_SubsystemList.setParent(this);
	/// This code is currently not supported
}

ReportServiceList::Body::~Body()
{
}

ReportServiceList::Body* const ReportServiceList::getBody()
{
	return &m_Body;
}

int ReportServiceList::setBody(const Body &value)
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
const unsigned int ReportServiceList::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportServiceList::encode(unsigned char *bytes)
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

void ReportServiceList::decode(const unsigned char *bytes)
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

ReportServiceList &ReportServiceList::operator=(const ReportServiceList &value)
{
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportServiceList::operator==(const ReportServiceList &value) const
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

bool ReportServiceList::operator!=(const ReportServiceList &value) const
{
	return !((*this) == value);
}

ReportServiceList::ReportServiceList()
{
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportServiceList::ReportServiceList(const ReportServiceList &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
}

ReportServiceList::~ReportServiceList()
{
}


}
