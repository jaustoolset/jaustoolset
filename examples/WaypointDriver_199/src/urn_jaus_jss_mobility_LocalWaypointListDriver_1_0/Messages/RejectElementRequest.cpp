#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/RejectElementRequest.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

void RejectElementRequest::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void RejectElementRequest::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger RejectElementRequest::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int RejectElementRequest::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int RejectElementRequest::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void RejectElementRequest::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void RejectElementRequest::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

RejectElementRequest::AppHeader::HeaderRec &RejectElementRequest::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool RejectElementRequest::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool RejectElementRequest::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

RejectElementRequest::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x041d;
}

RejectElementRequest::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x041d;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

RejectElementRequest::AppHeader::HeaderRec::~HeaderRec()
{
}

RejectElementRequest::AppHeader::HeaderRec* const RejectElementRequest::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int RejectElementRequest::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void RejectElementRequest::AppHeader::setParentPresenceVector()
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
const unsigned int RejectElementRequest::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void RejectElementRequest::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void RejectElementRequest::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

RejectElementRequest::AppHeader &RejectElementRequest::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool RejectElementRequest::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool RejectElementRequest::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

RejectElementRequest::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

RejectElementRequest::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

RejectElementRequest::AppHeader::~AppHeader()
{
}

RejectElementRequest::AppHeader* const RejectElementRequest::getAppHeader()
{
	return &m_AppHeader;
}

int RejectElementRequest::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void RejectElementRequest::Body::RejectElementRec::setParent(Body* parent)
{
	m_parent = parent;
}

void RejectElementRequest::Body::RejectElementRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte RejectElementRequest::Body::RejectElementRec::getRequestID()
{
	return m_RequestID;
}

int RejectElementRequest::Body::RejectElementRec::setRequestID(jUnsignedByte value)
{
	m_RequestID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte RejectElementRequest::Body::RejectElementRec::getResponseCode()
{
	return m_ResponseCode;
}

int RejectElementRequest::Body::RejectElementRec::setResponseCode(jUnsignedByte value)
{
	if ((value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6) || (value == 7))
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
const unsigned int RejectElementRequest::Body::RejectElementRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void RejectElementRequest::Body::RejectElementRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_RequestIDTemp;
	
	m_RequestIDTemp = JSIDL_v_1_0::correctEndianness(m_RequestID);
	memcpy(bytes + pos, &m_RequestIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_ResponseCodeTemp;
	
	m_ResponseCodeTemp = JSIDL_v_1_0::correctEndianness(m_ResponseCode);
	memcpy(bytes + pos, &m_ResponseCodeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void RejectElementRequest::Body::RejectElementRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_RequestIDTemp;
	
	memcpy(&m_RequestIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_RequestID = JSIDL_v_1_0::correctEndianness(m_RequestIDTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_ResponseCodeTemp;
	
	memcpy(&m_ResponseCodeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_ResponseCode = JSIDL_v_1_0::correctEndianness(m_ResponseCodeTemp);
	pos += sizeof(jUnsignedByte);
}

RejectElementRequest::Body::RejectElementRec &RejectElementRequest::Body::RejectElementRec::operator=(const RejectElementRec &value)
{
	m_RequestID = value.m_RequestID;
	m_ResponseCode = value.m_ResponseCode;
	
	return *this;
}

bool RejectElementRequest::Body::RejectElementRec::operator==(const RejectElementRec &value) const
{
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	if (m_ResponseCode != value.m_ResponseCode)
	{
		return false;
	}
	
	return true;
}

bool RejectElementRequest::Body::RejectElementRec::operator!=(const RejectElementRec &value) const
{
	return !((*this) == value);
}

RejectElementRequest::Body::RejectElementRec::RejectElementRec()
{
	m_parent = NULL;
	m_RequestID = 0;
	m_ResponseCode = 0;
}

RejectElementRequest::Body::RejectElementRec::RejectElementRec(const RejectElementRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	m_ResponseCode = 0;
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
	m_ResponseCode = value.m_ResponseCode;
}

RejectElementRequest::Body::RejectElementRec::~RejectElementRec()
{
}

RejectElementRequest::Body::RejectElementRec* const RejectElementRequest::Body::getRejectElementRec()
{
	return &m_RejectElementRec;
}

int RejectElementRequest::Body::setRejectElementRec(const RejectElementRec &value)
{
	m_RejectElementRec = value;
	setParentPresenceVector();
	return 0;
}

void RejectElementRequest::Body::setParentPresenceVector()
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
const unsigned int RejectElementRequest::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_RejectElementRec.getSize();
	
	return size;
}

void RejectElementRequest::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RejectElementRec.encode(bytes + pos);
	pos += m_RejectElementRec.getSize();
}

void RejectElementRequest::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RejectElementRec.decode(bytes + pos);
	pos += m_RejectElementRec.getSize();
}

RejectElementRequest::Body &RejectElementRequest::Body::operator=(const Body &value)
{
	m_RejectElementRec = value.m_RejectElementRec;
	m_RejectElementRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool RejectElementRequest::Body::operator==(const Body &value) const
{
	if (m_RejectElementRec != value.m_RejectElementRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool RejectElementRequest::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

RejectElementRequest::Body::Body()
{
	m_RejectElementRec.setParent(this);
	/// No Initialization of m_RejectElementRec necessary.
}

RejectElementRequest::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_RejectElementRec.setParent(this);
	/// No Initialization of m_RejectElementRec necessary.
	
	/// Copy the values
	m_RejectElementRec = value.m_RejectElementRec;
	m_RejectElementRec.setParent(this);
	/// This code is currently not supported
}

RejectElementRequest::Body::~Body()
{
}

RejectElementRequest::Body* const RejectElementRequest::getBody()
{
	return &m_Body;
}

int RejectElementRequest::setBody(const Body &value)
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
const unsigned int RejectElementRequest::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void RejectElementRequest::encode(unsigned char *bytes)
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

void RejectElementRequest::decode(const unsigned char *bytes)
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

RejectElementRequest &RejectElementRequest::operator=(const RejectElementRequest &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool RejectElementRequest::operator==(const RejectElementRequest &value) const
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

bool RejectElementRequest::operator!=(const RejectElementRequest &value) const
{
	return !((*this) == value);
}

RejectElementRequest::RejectElementRequest()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

RejectElementRequest::RejectElementRequest(const RejectElementRequest &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

RejectElementRequest::~RejectElementRequest()
{
}


}
