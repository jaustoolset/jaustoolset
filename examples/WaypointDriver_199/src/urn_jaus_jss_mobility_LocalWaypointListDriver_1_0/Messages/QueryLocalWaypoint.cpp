#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/QueryLocalWaypoint.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

void QueryLocalWaypoint::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryLocalWaypoint::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryLocalWaypoint::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryLocalWaypoint::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryLocalWaypoint::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryLocalWaypoint::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryLocalWaypoint::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryLocalWaypoint::AppHeader::HeaderRec &QueryLocalWaypoint::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryLocalWaypoint::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryLocalWaypoint::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryLocalWaypoint::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x240d;
}

QueryLocalWaypoint::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x240d;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryLocalWaypoint::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryLocalWaypoint::AppHeader::HeaderRec* const QueryLocalWaypoint::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryLocalWaypoint::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryLocalWaypoint::AppHeader::setParentPresenceVector()
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
const unsigned int QueryLocalWaypoint::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryLocalWaypoint::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryLocalWaypoint::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryLocalWaypoint::AppHeader &QueryLocalWaypoint::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryLocalWaypoint::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryLocalWaypoint::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryLocalWaypoint::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryLocalWaypoint::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryLocalWaypoint::AppHeader::~AppHeader()
{
}

QueryLocalWaypoint::AppHeader* const QueryLocalWaypoint::getAppHeader()
{
	return &m_AppHeader;
}

int QueryLocalWaypoint::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryLocalWaypoint::Body::QueryLocalWaypointRec::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryLocalWaypoint::Body::QueryLocalWaypointRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte QueryLocalWaypoint::Body::QueryLocalWaypointRec::getPresenceVector()
{
	return m_PresenceVector;
}

int QueryLocalWaypoint::Body::QueryLocalWaypointRec::setPresenceVector(jUnsignedByte value)
{
	m_PresenceVector = value;
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
const unsigned int QueryLocalWaypoint::Body::QueryLocalWaypointRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryLocalWaypoint::Body::QueryLocalWaypointRec::encode(unsigned char *bytes)
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
}

void QueryLocalWaypoint::Body::QueryLocalWaypointRec::decode(const unsigned char *bytes)
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
}

QueryLocalWaypoint::Body::QueryLocalWaypointRec &QueryLocalWaypoint::Body::QueryLocalWaypointRec::operator=(const QueryLocalWaypointRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	
	return *this;
}

bool QueryLocalWaypoint::Body::QueryLocalWaypointRec::operator==(const QueryLocalWaypointRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	
	return true;
}

bool QueryLocalWaypoint::Body::QueryLocalWaypointRec::operator!=(const QueryLocalWaypointRec &value) const
{
	return !((*this) == value);
}

QueryLocalWaypoint::Body::QueryLocalWaypointRec::QueryLocalWaypointRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
}

QueryLocalWaypoint::Body::QueryLocalWaypointRec::QueryLocalWaypointRec(const QueryLocalWaypointRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
}

QueryLocalWaypoint::Body::QueryLocalWaypointRec::~QueryLocalWaypointRec()
{
}

QueryLocalWaypoint::Body::QueryLocalWaypointRec* const QueryLocalWaypoint::Body::getQueryLocalWaypointRec()
{
	return &m_QueryLocalWaypointRec;
}

int QueryLocalWaypoint::Body::setQueryLocalWaypointRec(const QueryLocalWaypointRec &value)
{
	m_QueryLocalWaypointRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryLocalWaypoint::Body::setParentPresenceVector()
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
const unsigned int QueryLocalWaypoint::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryLocalWaypointRec.getSize();
	
	return size;
}

void QueryLocalWaypoint::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryLocalWaypointRec.encode(bytes + pos);
	pos += m_QueryLocalWaypointRec.getSize();
}

void QueryLocalWaypoint::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryLocalWaypointRec.decode(bytes + pos);
	pos += m_QueryLocalWaypointRec.getSize();
}

QueryLocalWaypoint::Body &QueryLocalWaypoint::Body::operator=(const Body &value)
{
	m_QueryLocalWaypointRec = value.m_QueryLocalWaypointRec;
	m_QueryLocalWaypointRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryLocalWaypoint::Body::operator==(const Body &value) const
{
	if (m_QueryLocalWaypointRec != value.m_QueryLocalWaypointRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryLocalWaypoint::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryLocalWaypoint::Body::Body()
{
	m_QueryLocalWaypointRec.setParent(this);
	/// No Initialization of m_QueryLocalWaypointRec necessary.
}

QueryLocalWaypoint::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryLocalWaypointRec.setParent(this);
	/// No Initialization of m_QueryLocalWaypointRec necessary.
	
	/// Copy the values
	m_QueryLocalWaypointRec = value.m_QueryLocalWaypointRec;
	m_QueryLocalWaypointRec.setParent(this);
	/// This code is currently not supported
}

QueryLocalWaypoint::Body::~Body()
{
}

QueryLocalWaypoint::Body* const QueryLocalWaypoint::getBody()
{
	return &m_Body;
}

int QueryLocalWaypoint::setBody(const Body &value)
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
const unsigned int QueryLocalWaypoint::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryLocalWaypoint::encode(unsigned char *bytes)
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

void QueryLocalWaypoint::decode(const unsigned char *bytes)
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

QueryLocalWaypoint &QueryLocalWaypoint::operator=(const QueryLocalWaypoint &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryLocalWaypoint::operator==(const QueryLocalWaypoint &value) const
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

bool QueryLocalWaypoint::operator!=(const QueryLocalWaypoint &value) const
{
	return !((*this) == value);
}

QueryLocalWaypoint::QueryLocalWaypoint()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryLocalWaypoint::QueryLocalWaypoint(const QueryLocalWaypoint &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryLocalWaypoint::~QueryLocalWaypoint()
{
}


}
