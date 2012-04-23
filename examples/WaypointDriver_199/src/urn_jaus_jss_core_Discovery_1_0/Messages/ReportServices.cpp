#include "urn_jaus_jss_core_Discovery_1_0/Messages/ReportServices.h"

namespace urn_jaus_jss_core_Discovery_1_0
{

void ReportServices::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportServices::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportServices::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportServices::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportServices::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportServices::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportServices::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportServices::AppHeader::HeaderRec &ReportServices::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportServices::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportServices::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportServices::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4b03;
}

ReportServices::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4b03;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportServices::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportServices::AppHeader::HeaderRec* const ReportServices::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportServices::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportServices::AppHeader::setParentPresenceVector()
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
const unsigned int ReportServices::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportServices::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportServices::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportServices::AppHeader &ReportServices::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportServices::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportServices::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportServices::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportServices::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportServices::AppHeader::~AppHeader()
{
}

ReportServices::AppHeader* const ReportServices::getAppHeader()
{
	return &m_AppHeader;
}

int ReportServices::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportServices::Body::NodeList::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportServices::Body::NodeList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServices::Body::NodeList::NodeSeq::setParent(NodeList* parent)
{
	m_parent = parent;
}

void ReportServices::Body::NodeList::NodeSeq::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServices::Body::NodeList::NodeSeq::NodeRec::setParent(NodeSeq* parent)
{
	m_parent = parent;
}

void ReportServices::Body::NodeList::NodeSeq::NodeRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportServices::Body::NodeList::NodeSeq::NodeRec::getNodeID()
{
	return m_NodeID;
}

int ReportServices::Body::NodeList::NodeSeq::NodeRec::setNodeID(jUnsignedByte value)
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
const unsigned int ReportServices::Body::NodeList::NodeSeq::NodeRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportServices::Body::NodeList::NodeSeq::NodeRec::encode(unsigned char *bytes)
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

void ReportServices::Body::NodeList::NodeSeq::NodeRec::decode(const unsigned char *bytes)
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

ReportServices::Body::NodeList::NodeSeq::NodeRec &ReportServices::Body::NodeList::NodeSeq::NodeRec::operator=(const NodeRec &value)
{
	m_NodeID = value.m_NodeID;
	
	return *this;
}

bool ReportServices::Body::NodeList::NodeSeq::NodeRec::operator==(const NodeRec &value) const
{
	if (m_NodeID != value.m_NodeID)
	{
		return false;
	}
	
	return true;
}

bool ReportServices::Body::NodeList::NodeSeq::NodeRec::operator!=(const NodeRec &value) const
{
	return !((*this) == value);
}

ReportServices::Body::NodeList::NodeSeq::NodeRec::NodeRec()
{
	m_parent = NULL;
	m_NodeID = 0;
}

ReportServices::Body::NodeList::NodeSeq::NodeRec::NodeRec(const NodeRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_NodeID = 0;
	
	/// Copy the values
	m_NodeID = value.m_NodeID;
}

ReportServices::Body::NodeList::NodeSeq::NodeRec::~NodeRec()
{
}

ReportServices::Body::NodeList::NodeSeq::NodeRec* const ReportServices::Body::NodeList::NodeSeq::getNodeRec()
{
	return &m_NodeRec;
}

int ReportServices::Body::NodeList::NodeSeq::setNodeRec(const NodeRec &value)
{
	m_NodeRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::setParent(NodeSeq* parent)
{
	m_parent = parent;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::setParent(ComponentList* parent)
{
	m_parent = parent;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::setParent(ComponentSeq* parent)
{
	m_parent = parent;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::getComponentID()
{
	return m_ComponentID;
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::setComponentID(jUnsignedByte value)
{
	m_ComponentID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::getInstanceID()
{
	return m_InstanceID;
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::setInstanceID(jUnsignedByte value)
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
const unsigned int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::encode(unsigned char *bytes)
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

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::decode(const unsigned char *bytes)
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

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec &ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::operator=(const ComponentRec &value)
{
	m_ComponentID = value.m_ComponentID;
	m_InstanceID = value.m_InstanceID;
	
	return *this;
}

bool ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::operator==(const ComponentRec &value) const
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

bool ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::operator!=(const ComponentRec &value) const
{
	return !((*this) == value);
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::ComponentRec()
{
	m_parent = NULL;
	m_ComponentID = 0;
	m_InstanceID = 0;
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::ComponentRec(const ComponentRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_ComponentID = 0;
	m_InstanceID = 0;
	
	/// Copy the values
	m_ComponentID = value.m_ComponentID;
	m_InstanceID = value.m_InstanceID;
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec::~ComponentRec()
{
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentRec* const ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::getComponentRec()
{
	return &m_ComponentRec;
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::setComponentRec(const ComponentRec &value)
{
	m_ComponentRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::setParent(ComponentSeq* parent)
{
	m_parent = parent;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::setParent(ServiceList* parent)
{
	m_parent = parent;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jVariableLengthString ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::getURI()
{
	return m_URI;
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::setURI(jVariableLengthString value)
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

jUnsignedByte ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::getMajorVersionNumber()
{
	return m_MajorVersionNumber;
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::setMajorVersionNumber(jUnsignedByte value)
{
	m_MajorVersionNumber = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::getMinorVersionNumber()
{
	return m_MinorVersionNumber;
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::setMinorVersionNumber(jUnsignedByte value)
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
const unsigned int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += m_URI.length();
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	
	jUnsignedByte m_URILength = 0;
	m_URILength = JSIDL_v_1_0::correctEndianness(m_URI.length());
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

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::decode(const unsigned char *bytes)
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

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec &ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::operator=(const ServiceRec &value)
{
	m_URI = value.m_URI;
	m_MajorVersionNumber = value.m_MajorVersionNumber;
	m_MinorVersionNumber = value.m_MinorVersionNumber;
	
	return *this;
}

bool ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::operator==(const ServiceRec &value) const
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

bool ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::operator!=(const ServiceRec &value) const
{
	return !((*this) == value);
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::ServiceRec()
{
	m_parent = NULL;
	m_MajorVersionNumber = 0;
	m_MinorVersionNumber = 0;
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::ServiceRec(const ServiceRec &value)
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

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec::~ServiceRec()
{
}

unsigned int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::getNumberOfElements() const
{
	return (unsigned int) m_ServiceRec.size();
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec* const ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::getElement(unsigned int index)
{
	return &m_ServiceRec.at(index);
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::setElement(unsigned int index, const ServiceRec &value)
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

int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::addElement(const ServiceRec &value)
{
	m_ServiceRec.push_back(value);
	m_ServiceRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::deleteElement(unsigned int index)
{
	if(m_ServiceRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ServiceRec.erase(m_ServiceRec.begin()+index);
	return 0;
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::deleteLastElement()
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
const unsigned int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		size += m_ServiceRec[i].getSize();
	}
	
	return size;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::encode(unsigned char *bytes)
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

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::decode(const unsigned char *bytes)
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

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList &ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::operator=(const ServiceList &value)
{
	m_ServiceRec.clear();
	
	for (int i = 0; i < value.m_ServiceRec.size(); i++)
	{
		m_ServiceRec.push_back(value.m_ServiceRec[i]);
		m_ServiceRec[i].setParent(this);
	}
	
	return *this;
}

bool ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::operator==(const ServiceList &value) const
{
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		if (m_ServiceRec[i] !=  value.m_ServiceRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::operator!=(const ServiceList &value) const
{
	return !((*this) == value);
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ServiceRec.size(); i++)
	{
		m_ServiceRec[i].setParent(this);
	}
	/// No Initialization of m_ServiceRec necessary.
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceList(const ServiceList &value)
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

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::~ServiceList()
{
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList* const ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::getServiceList()
{
	return &m_ServiceList;
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::setServiceList(const ServiceList &value)
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
const unsigned int ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::getSize()
{
	unsigned int size = 0;
	
	size += m_ComponentRec.getSize();
	size += m_ServiceList.getSize();
	
	return size;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::encode(unsigned char *bytes)
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

void ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::decode(const unsigned char *bytes)
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

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq &ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::operator=(const ComponentSeq &value)
{
	m_ComponentRec = value.m_ComponentRec;
	m_ComponentRec.setParent(this);
	m_ComponentRec = value.m_ComponentRec;
	m_ServiceList = value.m_ServiceList;
	m_ServiceList.setParent(this);
	m_ServiceList = value.m_ServiceList;
	
	return *this;
}

bool ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::operator==(const ComponentSeq &value) const
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

bool ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::operator!=(const ComponentSeq &value) const
{
	return !((*this) == value);
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentSeq()
{
	m_parent = NULL;
	m_ComponentRec.setParent(this);
	/// No Initialization of m_ComponentRec necessary.
	m_ServiceList.setParent(this);
	/// No Initialization of m_ServiceList necessary.
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ComponentSeq(const ComponentSeq &value)
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

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::~ComponentSeq()
{
}

unsigned int ReportServices::Body::NodeList::NodeSeq::ComponentList::getNumberOfElements() const
{
	return (unsigned int) m_ComponentSeq.size();
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq* const ReportServices::Body::NodeList::NodeSeq::ComponentList::getElement(unsigned int index)
{
	return &m_ComponentSeq.at(index);
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::setElement(unsigned int index, const ComponentSeq &value)
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

int ReportServices::Body::NodeList::NodeSeq::ComponentList::addElement(const ComponentSeq &value)
{
	m_ComponentSeq.push_back(value);
	m_ComponentSeq.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::deleteElement(unsigned int index)
{
	if(m_ComponentSeq.size()-1 < index)
	{
		return 1;
	}
	
	m_ComponentSeq.erase(m_ComponentSeq.begin()+index);
	return 0;
}

int ReportServices::Body::NodeList::NodeSeq::ComponentList::deleteLastElement()
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
const unsigned int ReportServices::Body::NodeList::NodeSeq::ComponentList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_ComponentSeq.size(); i++)
	{
		size += m_ComponentSeq[i].getSize();
	}
	
	return size;
}

void ReportServices::Body::NodeList::NodeSeq::ComponentList::encode(unsigned char *bytes)
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

void ReportServices::Body::NodeList::NodeSeq::ComponentList::decode(const unsigned char *bytes)
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

ReportServices::Body::NodeList::NodeSeq::ComponentList &ReportServices::Body::NodeList::NodeSeq::ComponentList::operator=(const ComponentList &value)
{
	m_ComponentSeq.clear();
	
	for (int i = 0; i < value.m_ComponentSeq.size(); i++)
	{
		m_ComponentSeq.push_back(value.m_ComponentSeq[i]);
		m_ComponentSeq[i].setParent(this);
	}
	
	return *this;
}

bool ReportServices::Body::NodeList::NodeSeq::ComponentList::operator==(const ComponentList &value) const
{
	for (int i = 0; i < m_ComponentSeq.size(); i++)
	{
		if (m_ComponentSeq[i] != value.m_ComponentSeq[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportServices::Body::NodeList::NodeSeq::ComponentList::operator!=(const ComponentList &value) const
{
	return !((*this) == value);
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ComponentSeq.size(); i++)
	{
		m_ComponentSeq[i].setParent(this);
	}
	/// No Initialization of m_ComponentSeq necessary.
}

ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentList(const ComponentList &value)
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

ReportServices::Body::NodeList::NodeSeq::ComponentList::~ComponentList()
{
}

ReportServices::Body::NodeList::NodeSeq::ComponentList* const ReportServices::Body::NodeList::NodeSeq::getComponentList()
{
	return &m_ComponentList;
}

int ReportServices::Body::NodeList::NodeSeq::setComponentList(const ComponentList &value)
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
const unsigned int ReportServices::Body::NodeList::NodeSeq::getSize()
{
	unsigned int size = 0;
	
	size += m_NodeRec.getSize();
	size += m_ComponentList.getSize();
	
	return size;
}

void ReportServices::Body::NodeList::NodeSeq::encode(unsigned char *bytes)
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

void ReportServices::Body::NodeList::NodeSeq::decode(const unsigned char *bytes)
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

ReportServices::Body::NodeList::NodeSeq &ReportServices::Body::NodeList::NodeSeq::operator=(const NodeSeq &value)
{
	m_NodeRec = value.m_NodeRec;
	m_NodeRec.setParent(this);
	m_NodeRec = value.m_NodeRec;
	m_ComponentList = value.m_ComponentList;
	m_ComponentList.setParent(this);
	m_ComponentList = value.m_ComponentList;
	
	return *this;
}

bool ReportServices::Body::NodeList::NodeSeq::operator==(const NodeSeq &value) const
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

bool ReportServices::Body::NodeList::NodeSeq::operator!=(const NodeSeq &value) const
{
	return !((*this) == value);
}

ReportServices::Body::NodeList::NodeSeq::NodeSeq()
{
	m_parent = NULL;
	m_NodeRec.setParent(this);
	/// No Initialization of m_NodeRec necessary.
	m_ComponentList.setParent(this);
	/// No Initialization of m_ComponentList necessary.
}

ReportServices::Body::NodeList::NodeSeq::NodeSeq(const NodeSeq &value)
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

ReportServices::Body::NodeList::NodeSeq::~NodeSeq()
{
}

unsigned int ReportServices::Body::NodeList::getNumberOfElements() const
{
	return (unsigned int) m_NodeSeq.size();
}

ReportServices::Body::NodeList::NodeSeq* const ReportServices::Body::NodeList::getElement(unsigned int index)
{
	return &m_NodeSeq.at(index);
}

int ReportServices::Body::NodeList::setElement(unsigned int index, const NodeSeq &value)
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

int ReportServices::Body::NodeList::addElement(const NodeSeq &value)
{
	m_NodeSeq.push_back(value);
	m_NodeSeq.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportServices::Body::NodeList::deleteElement(unsigned int index)
{
	if(m_NodeSeq.size()-1 < index)
	{
		return 1;
	}
	
	m_NodeSeq.erase(m_NodeSeq.begin()+index);
	return 0;
}

int ReportServices::Body::NodeList::deleteLastElement()
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
const unsigned int ReportServices::Body::NodeList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_NodeSeq.size(); i++)
	{
		size += m_NodeSeq[i].getSize();
	}
	
	return size;
}

void ReportServices::Body::NodeList::encode(unsigned char *bytes)
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

void ReportServices::Body::NodeList::decode(const unsigned char *bytes)
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

ReportServices::Body::NodeList &ReportServices::Body::NodeList::operator=(const NodeList &value)
{
	m_NodeSeq.clear();
	
	for (int i = 0; i < value.m_NodeSeq.size(); i++)
	{
		m_NodeSeq.push_back(value.m_NodeSeq[i]);
		m_NodeSeq[i].setParent(this);
	}
	
	return *this;
}

bool ReportServices::Body::NodeList::operator==(const NodeList &value) const
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

bool ReportServices::Body::NodeList::operator!=(const NodeList &value) const
{
	return !((*this) == value);
}

ReportServices::Body::NodeList::NodeList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_NodeSeq.size(); i++)
	{
		m_NodeSeq[i].setParent(this);
	}
	/// No Initialization of m_NodeSeq necessary.
}

ReportServices::Body::NodeList::NodeList(const NodeList &value)
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

ReportServices::Body::NodeList::~NodeList()
{
}

ReportServices::Body::NodeList* const ReportServices::Body::getNodeList()
{
	return &m_NodeList;
}

int ReportServices::Body::setNodeList(const NodeList &value)
{
	m_NodeList = value;
	setParentPresenceVector();
	return 0;
}

void ReportServices::Body::setParentPresenceVector()
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
const unsigned int ReportServices::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_NodeList.getSize();
	
	return size;
}

void ReportServices::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_NodeList.encode(bytes + pos);
	pos += m_NodeList.getSize();
}

void ReportServices::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_NodeList.decode(bytes + pos);
	pos += m_NodeList.getSize();
}

ReportServices::Body &ReportServices::Body::operator=(const Body &value)
{
	m_NodeList = value.m_NodeList;
	m_NodeList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportServices::Body::operator==(const Body &value) const
{
	if (m_NodeList != value.m_NodeList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportServices::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportServices::Body::Body()
{
	m_NodeList.setParent(this);
	/// No Initialization of m_NodeList necessary.
}

ReportServices::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_NodeList.setParent(this);
	/// No Initialization of m_NodeList necessary.
	
	/// Copy the values
	m_NodeList = value.m_NodeList;
	m_NodeList.setParent(this);
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
	
	size += m_AppHeader.getSize();
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
	m_AppHeader.encode(bytes + pos);
	pos += m_AppHeader.getSize();
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
	m_AppHeader.decode(bytes + pos);
	pos += m_AppHeader.getSize();
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

ReportServices &ReportServices::operator=(const ReportServices &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportServices::operator==(const ReportServices &value) const
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

bool ReportServices::operator!=(const ReportServices &value) const
{
	return !((*this) == value);
}

ReportServices::ReportServices()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportServices::ReportServices(const ReportServices &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportServices::~ReportServices()
{
}


}
