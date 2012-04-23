#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/RejectControl.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

void RejectControl::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void RejectControl::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger RejectControl::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int RejectControl::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int RejectControl::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void RejectControl::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void RejectControl::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

RejectControl::AppHeader::HeaderRec &RejectControl::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool RejectControl::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool RejectControl::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

RejectControl::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x0010;
}

RejectControl::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x0010;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

RejectControl::AppHeader::HeaderRec::~HeaderRec()
{
}

RejectControl::AppHeader::HeaderRec* const RejectControl::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int RejectControl::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void RejectControl::AppHeader::setParentPresenceVector()
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
const unsigned int RejectControl::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void RejectControl::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void RejectControl::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

RejectControl::AppHeader &RejectControl::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool RejectControl::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool RejectControl::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

RejectControl::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

RejectControl::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

RejectControl::AppHeader::~AppHeader()
{
}

RejectControl::AppHeader* const RejectControl::getAppHeader()
{
	return &m_AppHeader;
}

int RejectControl::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void RejectControl::Body::RejectControlRec::setParent(Body* parent)
{
	m_parent = parent;
}

void RejectControl::Body::RejectControlRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte RejectControl::Body::RejectControlRec::getResponseCode()
{
	return m_ResponseCode;
}

int RejectControl::Body::RejectControlRec::setResponseCode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		m_ResponseCode = value;
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
const unsigned int RejectControl::Body::RejectControlRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void RejectControl::Body::RejectControlRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_ResponseCodeTemp;
	
	m_ResponseCodeTemp = JSIDL_v_1_0::correctEndianness(m_ResponseCode);
	memcpy(bytes + pos, &m_ResponseCodeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void RejectControl::Body::RejectControlRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_ResponseCodeTemp;
	
	memcpy(&m_ResponseCodeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_ResponseCode = JSIDL_v_1_0::correctEndianness(m_ResponseCodeTemp);
	pos += sizeof(jUnsignedByte);
}

RejectControl::Body::RejectControlRec &RejectControl::Body::RejectControlRec::operator=(const RejectControlRec &value)
{
	m_ResponseCode = value.m_ResponseCode;
	
	return *this;
}

bool RejectControl::Body::RejectControlRec::operator==(const RejectControlRec &value) const
{
	if (m_ResponseCode != value.m_ResponseCode)
	{
		return false;
	}
	
	return true;
}

bool RejectControl::Body::RejectControlRec::operator!=(const RejectControlRec &value) const
{
	return !((*this) == value);
}

RejectControl::Body::RejectControlRec::RejectControlRec()
{
	m_parent = NULL;
	m_ResponseCode = 0;
}

RejectControl::Body::RejectControlRec::RejectControlRec(const RejectControlRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_ResponseCode = 0;
	
	/// Copy the values
	m_ResponseCode = value.m_ResponseCode;
}

RejectControl::Body::RejectControlRec::~RejectControlRec()
{
}

RejectControl::Body::RejectControlRec* const RejectControl::Body::getRejectControlRec()
{
	return &m_RejectControlRec;
}

int RejectControl::Body::setRejectControlRec(const RejectControlRec &value)
{
	m_RejectControlRec = value;
	setParentPresenceVector();
	return 0;
}

void RejectControl::Body::setParentPresenceVector()
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
const unsigned int RejectControl::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_RejectControlRec.getSize();
	
	return size;
}

void RejectControl::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RejectControlRec.encode(bytes + pos);
	pos += m_RejectControlRec.getSize();
}

void RejectControl::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RejectControlRec.decode(bytes + pos);
	pos += m_RejectControlRec.getSize();
}

RejectControl::Body &RejectControl::Body::operator=(const Body &value)
{
	m_RejectControlRec = value.m_RejectControlRec;
	m_RejectControlRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool RejectControl::Body::operator==(const Body &value) const
{
	if (m_RejectControlRec != value.m_RejectControlRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool RejectControl::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

RejectControl::Body::Body()
{
	m_RejectControlRec.setParent(this);
	/// No Initialization of m_RejectControlRec necessary.
}

RejectControl::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_RejectControlRec.setParent(this);
	/// No Initialization of m_RejectControlRec necessary.
	
	/// Copy the values
	m_RejectControlRec = value.m_RejectControlRec;
	m_RejectControlRec.setParent(this);
	/// This code is currently not supported
}

RejectControl::Body::~Body()
{
}

RejectControl::Body* const RejectControl::getBody()
{
	return &m_Body;
}

int RejectControl::setBody(const Body &value)
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
const unsigned int RejectControl::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void RejectControl::encode(unsigned char *bytes)
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

void RejectControl::decode(const unsigned char *bytes)
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

RejectControl &RejectControl::operator=(const RejectControl &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool RejectControl::operator==(const RejectControl &value) const
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

bool RejectControl::operator!=(const RejectControl &value) const
{
	return !((*this) == value);
}

RejectControl::RejectControl()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

RejectControl::RejectControl(const RejectControl &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

RejectControl::~RejectControl()
{
}


}
