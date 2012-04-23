#include "urn_jaus_jss_core_Discovery_1_0/Messages/ReportSubsystemList.h"

namespace urn_jaus_jss_core_Discovery_1_0
{

void ReportSubsystemList::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportSubsystemList::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportSubsystemList::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportSubsystemList::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportSubsystemList::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportSubsystemList::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportSubsystemList::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportSubsystemList::AppHeader::HeaderRec &ReportSubsystemList::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportSubsystemList::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportSubsystemList::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportSubsystemList::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4b02;
}

ReportSubsystemList::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4b02;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportSubsystemList::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportSubsystemList::AppHeader::HeaderRec* const ReportSubsystemList::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportSubsystemList::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportSubsystemList::AppHeader::setParentPresenceVector()
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
const unsigned int ReportSubsystemList::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportSubsystemList::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportSubsystemList::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportSubsystemList::AppHeader &ReportSubsystemList::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportSubsystemList::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportSubsystemList::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportSubsystemList::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportSubsystemList::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportSubsystemList::AppHeader::~AppHeader()
{
}

ReportSubsystemList::AppHeader* const ReportSubsystemList::getAppHeader()
{
	return &m_AppHeader;
}

int ReportSubsystemList::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportSubsystemList::Body::SubsystemList::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportSubsystemList::Body::SubsystemList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportSubsystemList::Body::SubsystemList::SubsystemRec::setParent(SubsystemList* parent)
{
	m_parent = parent;
}

void ReportSubsystemList::Body::SubsystemList::SubsystemRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportSubsystemList::Body::SubsystemList::SubsystemRec::getSubsystemID()
{
	return m_SubsystemID;
}

int ReportSubsystemList::Body::SubsystemList::SubsystemRec::setSubsystemID(jUnsignedShortInteger value)
{
	m_SubsystemID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportSubsystemList::Body::SubsystemList::SubsystemRec::getNodeID()
{
	return m_NodeID;
}

int ReportSubsystemList::Body::SubsystemList::SubsystemRec::setNodeID(jUnsignedByte value)
{
	m_NodeID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportSubsystemList::Body::SubsystemList::SubsystemRec::getComponentID()
{
	return m_ComponentID;
}

int ReportSubsystemList::Body::SubsystemList::SubsystemRec::setComponentID(jUnsignedByte value)
{
	m_ComponentID = value;
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
const unsigned int ReportSubsystemList::Body::SubsystemList::SubsystemRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportSubsystemList::Body::SubsystemList::SubsystemRec::encode(unsigned char *bytes)
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
	jUnsignedByte m_NodeIDTemp;
	
	m_NodeIDTemp = JSIDL_v_1_0::correctEndianness(m_NodeID);
	memcpy(bytes + pos, &m_NodeIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_ComponentIDTemp;
	
	m_ComponentIDTemp = JSIDL_v_1_0::correctEndianness(m_ComponentID);
	memcpy(bytes + pos, &m_ComponentIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportSubsystemList::Body::SubsystemList::SubsystemRec::decode(const unsigned char *bytes)
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
	jUnsignedByte m_NodeIDTemp;
	
	memcpy(&m_NodeIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_NodeID = JSIDL_v_1_0::correctEndianness(m_NodeIDTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_ComponentIDTemp;
	
	memcpy(&m_ComponentIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_ComponentID = JSIDL_v_1_0::correctEndianness(m_ComponentIDTemp);
	pos += sizeof(jUnsignedByte);
}

ReportSubsystemList::Body::SubsystemList::SubsystemRec &ReportSubsystemList::Body::SubsystemList::SubsystemRec::operator=(const SubsystemRec &value)
{
	m_SubsystemID = value.m_SubsystemID;
	m_NodeID = value.m_NodeID;
	m_ComponentID = value.m_ComponentID;
	
	return *this;
}

bool ReportSubsystemList::Body::SubsystemList::SubsystemRec::operator==(const SubsystemRec &value) const
{
	if (m_SubsystemID != value.m_SubsystemID)
	{
		return false;
	}
	if (m_NodeID != value.m_NodeID)
	{
		return false;
	}
	if (m_ComponentID != value.m_ComponentID)
	{
		return false;
	}
	
	return true;
}

bool ReportSubsystemList::Body::SubsystemList::SubsystemRec::operator!=(const SubsystemRec &value) const
{
	return !((*this) == value);
}

ReportSubsystemList::Body::SubsystemList::SubsystemRec::SubsystemRec()
{
	m_parent = NULL;
	m_SubsystemID = 0;
	m_NodeID = 0;
	m_ComponentID = 0;
}

ReportSubsystemList::Body::SubsystemList::SubsystemRec::SubsystemRec(const SubsystemRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubsystemID = 0;
	m_NodeID = 0;
	m_ComponentID = 0;
	
	/// Copy the values
	m_SubsystemID = value.m_SubsystemID;
	m_NodeID = value.m_NodeID;
	m_ComponentID = value.m_ComponentID;
}

ReportSubsystemList::Body::SubsystemList::SubsystemRec::~SubsystemRec()
{
}

unsigned int ReportSubsystemList::Body::SubsystemList::getNumberOfElements() const
{
	return (unsigned int) m_SubsystemRec.size();
}

ReportSubsystemList::Body::SubsystemList::SubsystemRec* const ReportSubsystemList::Body::SubsystemList::getElement(unsigned int index)
{
	return &m_SubsystemRec.at(index);
}

int ReportSubsystemList::Body::SubsystemList::setElement(unsigned int index, const SubsystemRec &value)
{
	if(m_SubsystemRec.size()-1 < index)
	{
		return 1;
	}
	
	m_SubsystemRec.at(index) = value;
	m_SubsystemRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportSubsystemList::Body::SubsystemList::addElement(const SubsystemRec &value)
{
	m_SubsystemRec.push_back(value);
	m_SubsystemRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportSubsystemList::Body::SubsystemList::deleteElement(unsigned int index)
{
	if(m_SubsystemRec.size()-1 < index)
	{
		return 1;
	}
	
	m_SubsystemRec.erase(m_SubsystemRec.begin()+index);
	return 0;
}

int ReportSubsystemList::Body::SubsystemList::deleteLastElement()
{
	m_SubsystemRec.pop_back();
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
const unsigned int ReportSubsystemList::Body::SubsystemList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_SubsystemRec.size(); i++)
	{
		size += m_SubsystemRec[i].getSize();
	}
	
	return size;
}

void ReportSubsystemList::Body::SubsystemList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_SubsystemRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_SubsystemRec.size(); i++)
	{
		m_SubsystemRec[i].encode(bytes + pos);
		pos += m_SubsystemRec[i].getSize();
	}
}

void ReportSubsystemList::Body::SubsystemList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_SubsystemRec.resize(size);
	for (int i = 0; i < m_SubsystemRec.size(); i++)
	{
		m_SubsystemRec[i].decode(bytes + pos);
		pos += m_SubsystemRec[i].getSize();
	}
}

ReportSubsystemList::Body::SubsystemList &ReportSubsystemList::Body::SubsystemList::operator=(const SubsystemList &value)
{
	m_SubsystemRec.clear();
	
	for (int i = 0; i < value.m_SubsystemRec.size(); i++)
	{
		m_SubsystemRec.push_back(value.m_SubsystemRec[i]);
		m_SubsystemRec[i].setParent(this);
	}
	
	return *this;
}

bool ReportSubsystemList::Body::SubsystemList::operator==(const SubsystemList &value) const
{
	for (int i = 0; i < m_SubsystemRec.size(); i++)
	{
		if (m_SubsystemRec[i] !=  value.m_SubsystemRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportSubsystemList::Body::SubsystemList::operator!=(const SubsystemList &value) const
{
	return !((*this) == value);
}

ReportSubsystemList::Body::SubsystemList::SubsystemList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_SubsystemRec.size(); i++)
	{
		m_SubsystemRec[i].setParent(this);
	}
	/// No Initialization of m_SubsystemRec necessary.
}

ReportSubsystemList::Body::SubsystemList::SubsystemList(const SubsystemList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_SubsystemRec.size(); i++)
	{
		m_SubsystemRec[i].setParent(this);
	}
	/// No Initialization of m_SubsystemRec necessary.
	
	/// Copy the values
	m_SubsystemRec.clear();
	
	for (int i = 0; i < value.m_SubsystemRec.size(); i++)
	{
		m_SubsystemRec.push_back(value.m_SubsystemRec[i]);
		m_SubsystemRec[i].setParent(this);
	}
}

ReportSubsystemList::Body::SubsystemList::~SubsystemList()
{
}

ReportSubsystemList::Body::SubsystemList* const ReportSubsystemList::Body::getSubsystemList()
{
	return &m_SubsystemList;
}

int ReportSubsystemList::Body::setSubsystemList(const SubsystemList &value)
{
	m_SubsystemList = value;
	setParentPresenceVector();
	return 0;
}

void ReportSubsystemList::Body::setParentPresenceVector()
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
const unsigned int ReportSubsystemList::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_SubsystemList.getSize();
	
	return size;
}

void ReportSubsystemList::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemList.encode(bytes + pos);
	pos += m_SubsystemList.getSize();
}

void ReportSubsystemList::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemList.decode(bytes + pos);
	pos += m_SubsystemList.getSize();
}

ReportSubsystemList::Body &ReportSubsystemList::Body::operator=(const Body &value)
{
	m_SubsystemList = value.m_SubsystemList;
	m_SubsystemList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportSubsystemList::Body::operator==(const Body &value) const
{
	if (m_SubsystemList != value.m_SubsystemList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportSubsystemList::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportSubsystemList::Body::Body()
{
	m_SubsystemList.setParent(this);
	/// No Initialization of m_SubsystemList necessary.
}

ReportSubsystemList::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_SubsystemList.setParent(this);
	/// No Initialization of m_SubsystemList necessary.
	
	/// Copy the values
	m_SubsystemList = value.m_SubsystemList;
	m_SubsystemList.setParent(this);
	/// This code is currently not supported
}

ReportSubsystemList::Body::~Body()
{
}

ReportSubsystemList::Body* const ReportSubsystemList::getBody()
{
	return &m_Body;
}

int ReportSubsystemList::setBody(const Body &value)
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
const unsigned int ReportSubsystemList::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportSubsystemList::encode(unsigned char *bytes)
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

void ReportSubsystemList::decode(const unsigned char *bytes)
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

ReportSubsystemList &ReportSubsystemList::operator=(const ReportSubsystemList &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportSubsystemList::operator==(const ReportSubsystemList &value) const
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

bool ReportSubsystemList::operator!=(const ReportSubsystemList &value) const
{
	return !((*this) == value);
}

ReportSubsystemList::ReportSubsystemList()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportSubsystemList::ReportSubsystemList(const ReportSubsystemList &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportSubsystemList::~ReportSubsystemList()
{
}


}
