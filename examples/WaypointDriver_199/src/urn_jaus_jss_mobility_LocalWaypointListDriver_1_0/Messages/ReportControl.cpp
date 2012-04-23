#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/ReportControl.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

void ReportControl::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportControl::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportControl::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportControl::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportControl::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportControl::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportControl::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportControl::AppHeader::HeaderRec &ReportControl::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportControl::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportControl::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportControl::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x400d;
}

ReportControl::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x400d;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportControl::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportControl::AppHeader::HeaderRec* const ReportControl::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportControl::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportControl::AppHeader::setParentPresenceVector()
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
const unsigned int ReportControl::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportControl::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportControl::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportControl::AppHeader &ReportControl::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportControl::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportControl::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportControl::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportControl::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportControl::AppHeader::~AppHeader()
{
}

ReportControl::AppHeader* const ReportControl::getAppHeader()
{
	return &m_AppHeader;
}

int ReportControl::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportControl::Body::ReportControlRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportControl::Body::ReportControlRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportControl::Body::ReportControlRec::getSubsystemID()
{
	return m_SubsystemID;
}

int ReportControl::Body::ReportControlRec::setSubsystemID(jUnsignedShortInteger value)
{
	m_SubsystemID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportControl::Body::ReportControlRec::getNodeID()
{
	return m_NodeID;
}

int ReportControl::Body::ReportControlRec::setNodeID(jUnsignedByte value)
{
	m_NodeID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportControl::Body::ReportControlRec::getComponentID()
{
	return m_ComponentID;
}

int ReportControl::Body::ReportControlRec::setComponentID(jUnsignedByte value)
{
	m_ComponentID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportControl::Body::ReportControlRec::getAuthorityCode()
{
	return m_AuthorityCode;
}

int ReportControl::Body::ReportControlRec::setAuthorityCode(jUnsignedByte value)
{
	if (((value >= 0) && (value <= 255)))
	{
		m_AuthorityCode = value;
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
const unsigned int ReportControl::Body::ReportControlRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportControl::Body::ReportControlRec::encode(unsigned char *bytes)
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
	jUnsignedByte m_AuthorityCodeTemp;
	
	m_AuthorityCodeTemp = JSIDL_v_1_0::correctEndianness(m_AuthorityCode);
	memcpy(bytes + pos, &m_AuthorityCodeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportControl::Body::ReportControlRec::decode(const unsigned char *bytes)
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
	jUnsignedByte m_AuthorityCodeTemp;
	
	memcpy(&m_AuthorityCodeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_AuthorityCode = JSIDL_v_1_0::correctEndianness(m_AuthorityCodeTemp);
	pos += sizeof(jUnsignedByte);
}

ReportControl::Body::ReportControlRec &ReportControl::Body::ReportControlRec::operator=(const ReportControlRec &value)
{
	m_SubsystemID = value.m_SubsystemID;
	m_NodeID = value.m_NodeID;
	m_ComponentID = value.m_ComponentID;
	m_AuthorityCode = value.m_AuthorityCode;
	
	return *this;
}

bool ReportControl::Body::ReportControlRec::operator==(const ReportControlRec &value) const
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
	if (m_AuthorityCode != value.m_AuthorityCode)
	{
		return false;
	}
	
	return true;
}

bool ReportControl::Body::ReportControlRec::operator!=(const ReportControlRec &value) const
{
	return !((*this) == value);
}

ReportControl::Body::ReportControlRec::ReportControlRec()
{
	m_parent = NULL;
	m_SubsystemID = 0;
	m_NodeID = 0;
	m_ComponentID = 0;
	m_AuthorityCode = 0;
}

ReportControl::Body::ReportControlRec::ReportControlRec(const ReportControlRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubsystemID = 0;
	m_NodeID = 0;
	m_ComponentID = 0;
	m_AuthorityCode = 0;
	
	/// Copy the values
	m_SubsystemID = value.m_SubsystemID;
	m_NodeID = value.m_NodeID;
	m_ComponentID = value.m_ComponentID;
	m_AuthorityCode = value.m_AuthorityCode;
}

ReportControl::Body::ReportControlRec::~ReportControlRec()
{
}

ReportControl::Body::ReportControlRec* const ReportControl::Body::getReportControlRec()
{
	return &m_ReportControlRec;
}

int ReportControl::Body::setReportControlRec(const ReportControlRec &value)
{
	m_ReportControlRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportControl::Body::setParentPresenceVector()
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
const unsigned int ReportControl::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ReportControlRec.getSize();
	
	return size;
}

void ReportControl::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportControlRec.encode(bytes + pos);
	pos += m_ReportControlRec.getSize();
}

void ReportControl::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportControlRec.decode(bytes + pos);
	pos += m_ReportControlRec.getSize();
}

ReportControl::Body &ReportControl::Body::operator=(const Body &value)
{
	m_ReportControlRec = value.m_ReportControlRec;
	m_ReportControlRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportControl::Body::operator==(const Body &value) const
{
	if (m_ReportControlRec != value.m_ReportControlRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportControl::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportControl::Body::Body()
{
	m_ReportControlRec.setParent(this);
	/// No Initialization of m_ReportControlRec necessary.
}

ReportControl::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ReportControlRec.setParent(this);
	/// No Initialization of m_ReportControlRec necessary.
	
	/// Copy the values
	m_ReportControlRec = value.m_ReportControlRec;
	m_ReportControlRec.setParent(this);
	/// This code is currently not supported
}

ReportControl::Body::~Body()
{
}

ReportControl::Body* const ReportControl::getBody()
{
	return &m_Body;
}

int ReportControl::setBody(const Body &value)
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
const unsigned int ReportControl::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportControl::encode(unsigned char *bytes)
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

void ReportControl::decode(const unsigned char *bytes)
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

ReportControl &ReportControl::operator=(const ReportControl &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportControl::operator==(const ReportControl &value) const
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

bool ReportControl::operator!=(const ReportControl &value) const
{
	return !((*this) == value);
}

ReportControl::ReportControl()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportControl::ReportControl(const ReportControl &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportControl::~ReportControl()
{
}


}
