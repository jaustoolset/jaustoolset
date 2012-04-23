#include "urn_jaus_jss_mobility_LocalPoseSensor_1_0/Messages/RequestControl.h"

namespace urn_jaus_jss_mobility_LocalPoseSensor_1_0
{

void RequestControl::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void RequestControl::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger RequestControl::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int RequestControl::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int RequestControl::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void RequestControl::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void RequestControl::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

RequestControl::AppHeader::HeaderRec &RequestControl::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool RequestControl::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool RequestControl::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

RequestControl::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x000d;
}

RequestControl::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x000d;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

RequestControl::AppHeader::HeaderRec::~HeaderRec()
{
}

RequestControl::AppHeader::HeaderRec* const RequestControl::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int RequestControl::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void RequestControl::AppHeader::setParentPresenceVector()
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
const unsigned int RequestControl::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void RequestControl::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void RequestControl::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

RequestControl::AppHeader &RequestControl::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool RequestControl::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool RequestControl::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

RequestControl::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

RequestControl::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

RequestControl::AppHeader::~AppHeader()
{
}

RequestControl::AppHeader* const RequestControl::getAppHeader()
{
	return &m_AppHeader;
}

int RequestControl::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void RequestControl::Body::RequestControlRec::setParent(Body* parent)
{
	m_parent = parent;
}

void RequestControl::Body::RequestControlRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte RequestControl::Body::RequestControlRec::getAuthorityCode()
{
	return m_AuthorityCode;
}

int RequestControl::Body::RequestControlRec::setAuthorityCode(jUnsignedByte value)
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
const unsigned int RequestControl::Body::RequestControlRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void RequestControl::Body::RequestControlRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_AuthorityCodeTemp;
	
	m_AuthorityCodeTemp = JSIDL_v_1_0::correctEndianness(m_AuthorityCode);
	memcpy(bytes + pos, &m_AuthorityCodeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void RequestControl::Body::RequestControlRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_AuthorityCodeTemp;
	
	memcpy(&m_AuthorityCodeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_AuthorityCode = JSIDL_v_1_0::correctEndianness(m_AuthorityCodeTemp);
	pos += sizeof(jUnsignedByte);
}

RequestControl::Body::RequestControlRec &RequestControl::Body::RequestControlRec::operator=(const RequestControlRec &value)
{
	m_AuthorityCode = value.m_AuthorityCode;
	
	return *this;
}

bool RequestControl::Body::RequestControlRec::operator==(const RequestControlRec &value) const
{
	if (m_AuthorityCode != value.m_AuthorityCode)
	{
		return false;
	}
	
	return true;
}

bool RequestControl::Body::RequestControlRec::operator!=(const RequestControlRec &value) const
{
	return !((*this) == value);
}

RequestControl::Body::RequestControlRec::RequestControlRec()
{
	m_parent = NULL;
	m_AuthorityCode = 0;
}

RequestControl::Body::RequestControlRec::RequestControlRec(const RequestControlRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_AuthorityCode = 0;
	
	/// Copy the values
	m_AuthorityCode = value.m_AuthorityCode;
}

RequestControl::Body::RequestControlRec::~RequestControlRec()
{
}

RequestControl::Body::RequestControlRec* const RequestControl::Body::getRequestControlRec()
{
	return &m_RequestControlRec;
}

int RequestControl::Body::setRequestControlRec(const RequestControlRec &value)
{
	m_RequestControlRec = value;
	setParentPresenceVector();
	return 0;
}

void RequestControl::Body::setParentPresenceVector()
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
const unsigned int RequestControl::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_RequestControlRec.getSize();
	
	return size;
}

void RequestControl::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestControlRec.encode(bytes + pos);
	pos += m_RequestControlRec.getSize();
}

void RequestControl::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestControlRec.decode(bytes + pos);
	pos += m_RequestControlRec.getSize();
}

RequestControl::Body &RequestControl::Body::operator=(const Body &value)
{
	m_RequestControlRec = value.m_RequestControlRec;
	m_RequestControlRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool RequestControl::Body::operator==(const Body &value) const
{
	if (m_RequestControlRec != value.m_RequestControlRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool RequestControl::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

RequestControl::Body::Body()
{
	m_RequestControlRec.setParent(this);
	/// No Initialization of m_RequestControlRec necessary.
}

RequestControl::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_RequestControlRec.setParent(this);
	/// No Initialization of m_RequestControlRec necessary.
	
	/// Copy the values
	m_RequestControlRec = value.m_RequestControlRec;
	m_RequestControlRec.setParent(this);
	/// This code is currently not supported
}

RequestControl::Body::~Body()
{
}

RequestControl::Body* const RequestControl::getBody()
{
	return &m_Body;
}

int RequestControl::setBody(const Body &value)
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
const unsigned int RequestControl::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void RequestControl::encode(unsigned char *bytes)
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

void RequestControl::decode(const unsigned char *bytes)
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

RequestControl &RequestControl::operator=(const RequestControl &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool RequestControl::operator==(const RequestControl &value) const
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

bool RequestControl::operator!=(const RequestControl &value) const
{
	return !((*this) == value);
}

RequestControl::RequestControl()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

RequestControl::RequestControl(const RequestControl &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

RequestControl::~RequestControl()
{
}


}
