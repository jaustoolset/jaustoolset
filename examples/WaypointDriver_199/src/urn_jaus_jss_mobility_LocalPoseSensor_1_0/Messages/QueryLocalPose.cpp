#include "urn_jaus_jss_mobility_LocalPoseSensor_1_0/Messages/QueryLocalPose.h"

namespace urn_jaus_jss_mobility_LocalPoseSensor_1_0
{

void QueryLocalPose::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryLocalPose::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryLocalPose::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryLocalPose::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryLocalPose::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryLocalPose::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryLocalPose::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryLocalPose::AppHeader::HeaderRec &QueryLocalPose::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryLocalPose::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryLocalPose::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryLocalPose::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2403;
}

QueryLocalPose::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2403;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryLocalPose::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryLocalPose::AppHeader::HeaderRec* const QueryLocalPose::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryLocalPose::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryLocalPose::AppHeader::setParentPresenceVector()
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
const unsigned int QueryLocalPose::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryLocalPose::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryLocalPose::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryLocalPose::AppHeader &QueryLocalPose::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryLocalPose::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryLocalPose::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryLocalPose::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryLocalPose::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryLocalPose::AppHeader::~AppHeader()
{
}

QueryLocalPose::AppHeader* const QueryLocalPose::getAppHeader()
{
	return &m_AppHeader;
}

int QueryLocalPose::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryLocalPose::Body::QueryLocalPoseRec::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryLocalPose::Body::QueryLocalPoseRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryLocalPose::Body::QueryLocalPoseRec::getPresenceVector()
{
	return m_PresenceVector;
}

int QueryLocalPose::Body::QueryLocalPoseRec::setPresenceVector(jUnsignedShortInteger value)
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
const unsigned int QueryLocalPose::Body::QueryLocalPoseRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryLocalPose::Body::QueryLocalPoseRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_PresenceVectorTemp;
	
	m_PresenceVectorTemp = JSIDL_v_1_0::correctEndianness(m_PresenceVector);
	memcpy(bytes + pos, &m_PresenceVectorTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void QueryLocalPose::Body::QueryLocalPoseRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_PresenceVectorTemp;
	
	memcpy(&m_PresenceVectorTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_PresenceVector = JSIDL_v_1_0::correctEndianness(m_PresenceVectorTemp);
	pos += sizeof(jUnsignedShortInteger);
}

QueryLocalPose::Body::QueryLocalPoseRec &QueryLocalPose::Body::QueryLocalPoseRec::operator=(const QueryLocalPoseRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	
	return *this;
}

bool QueryLocalPose::Body::QueryLocalPoseRec::operator==(const QueryLocalPoseRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	
	return true;
}

bool QueryLocalPose::Body::QueryLocalPoseRec::operator!=(const QueryLocalPoseRec &value) const
{
	return !((*this) == value);
}

QueryLocalPose::Body::QueryLocalPoseRec::QueryLocalPoseRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
}

QueryLocalPose::Body::QueryLocalPoseRec::QueryLocalPoseRec(const QueryLocalPoseRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
}

QueryLocalPose::Body::QueryLocalPoseRec::~QueryLocalPoseRec()
{
}

QueryLocalPose::Body::QueryLocalPoseRec* const QueryLocalPose::Body::getQueryLocalPoseRec()
{
	return &m_QueryLocalPoseRec;
}

int QueryLocalPose::Body::setQueryLocalPoseRec(const QueryLocalPoseRec &value)
{
	m_QueryLocalPoseRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryLocalPose::Body::setParentPresenceVector()
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
const unsigned int QueryLocalPose::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryLocalPoseRec.getSize();
	
	return size;
}

void QueryLocalPose::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryLocalPoseRec.encode(bytes + pos);
	pos += m_QueryLocalPoseRec.getSize();
}

void QueryLocalPose::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryLocalPoseRec.decode(bytes + pos);
	pos += m_QueryLocalPoseRec.getSize();
}

QueryLocalPose::Body &QueryLocalPose::Body::operator=(const Body &value)
{
	m_QueryLocalPoseRec = value.m_QueryLocalPoseRec;
	m_QueryLocalPoseRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryLocalPose::Body::operator==(const Body &value) const
{
	if (m_QueryLocalPoseRec != value.m_QueryLocalPoseRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryLocalPose::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryLocalPose::Body::Body()
{
	m_QueryLocalPoseRec.setParent(this);
	/// No Initialization of m_QueryLocalPoseRec necessary.
}

QueryLocalPose::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryLocalPoseRec.setParent(this);
	/// No Initialization of m_QueryLocalPoseRec necessary.
	
	/// Copy the values
	m_QueryLocalPoseRec = value.m_QueryLocalPoseRec;
	m_QueryLocalPoseRec.setParent(this);
	/// This code is currently not supported
}

QueryLocalPose::Body::~Body()
{
}

QueryLocalPose::Body* const QueryLocalPose::getBody()
{
	return &m_Body;
}

int QueryLocalPose::setBody(const Body &value)
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
const unsigned int QueryLocalPose::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryLocalPose::encode(unsigned char *bytes)
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

void QueryLocalPose::decode(const unsigned char *bytes)
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

QueryLocalPose &QueryLocalPose::operator=(const QueryLocalPose &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryLocalPose::operator==(const QueryLocalPose &value) const
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

bool QueryLocalPose::operator!=(const QueryLocalPose &value) const
{
	return !((*this) == value);
}

QueryLocalPose::QueryLocalPose()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryLocalPose::QueryLocalPose(const QueryLocalPose &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryLocalPose::~QueryLocalPose()
{
}


}
