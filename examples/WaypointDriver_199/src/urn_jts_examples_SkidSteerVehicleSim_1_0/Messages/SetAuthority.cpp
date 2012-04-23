#include "urn_jts_examples_SkidSteerVehicleSim_1_0/Messages/SetAuthority.h"

namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

void SetAuthority::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void SetAuthority::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger SetAuthority::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int SetAuthority::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int SetAuthority::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void SetAuthority::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void SetAuthority::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

SetAuthority::AppHeader::HeaderRec &SetAuthority::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool SetAuthority::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool SetAuthority::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

SetAuthority::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x0001;
}

SetAuthority::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x0001;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

SetAuthority::AppHeader::HeaderRec::~HeaderRec()
{
}

SetAuthority::AppHeader::HeaderRec* const SetAuthority::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int SetAuthority::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void SetAuthority::AppHeader::setParentPresenceVector()
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
const unsigned int SetAuthority::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void SetAuthority::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void SetAuthority::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

SetAuthority::AppHeader &SetAuthority::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool SetAuthority::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool SetAuthority::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

SetAuthority::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

SetAuthority::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

SetAuthority::AppHeader::~AppHeader()
{
}

SetAuthority::AppHeader* const SetAuthority::getAppHeader()
{
	return &m_AppHeader;
}

int SetAuthority::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void SetAuthority::Body::authorityRec::setParent(Body* parent)
{
	m_parent = parent;
}

void SetAuthority::Body::authorityRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte SetAuthority::Body::authorityRec::getAuthorityCode()
{
	return m_AuthorityCode;
}

int SetAuthority::Body::authorityRec::setAuthorityCode(jUnsignedByte value)
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
const unsigned int SetAuthority::Body::authorityRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void SetAuthority::Body::authorityRec::encode(unsigned char *bytes)
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

void SetAuthority::Body::authorityRec::decode(const unsigned char *bytes)
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

SetAuthority::Body::authorityRec &SetAuthority::Body::authorityRec::operator=(const authorityRec &value)
{
	m_AuthorityCode = value.m_AuthorityCode;
	
	return *this;
}

bool SetAuthority::Body::authorityRec::operator==(const authorityRec &value) const
{
	if (m_AuthorityCode != value.m_AuthorityCode)
	{
		return false;
	}
	
	return true;
}

bool SetAuthority::Body::authorityRec::operator!=(const authorityRec &value) const
{
	return !((*this) == value);
}

SetAuthority::Body::authorityRec::authorityRec()
{
	m_parent = NULL;
	m_AuthorityCode = 0;
}

SetAuthority::Body::authorityRec::authorityRec(const authorityRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_AuthorityCode = 0;
	
	/// Copy the values
	m_AuthorityCode = value.m_AuthorityCode;
}

SetAuthority::Body::authorityRec::~authorityRec()
{
}

SetAuthority::Body::authorityRec* const SetAuthority::Body::getAuthorityRec()
{
	return &m_AuthorityRec;
}

int SetAuthority::Body::setAuthorityRec(const authorityRec &value)
{
	m_AuthorityRec = value;
	setParentPresenceVector();
	return 0;
}

void SetAuthority::Body::setParentPresenceVector()
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
const unsigned int SetAuthority::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_AuthorityRec.getSize();
	
	return size;
}

void SetAuthority::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AuthorityRec.encode(bytes + pos);
	pos += m_AuthorityRec.getSize();
}

void SetAuthority::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AuthorityRec.decode(bytes + pos);
	pos += m_AuthorityRec.getSize();
}

SetAuthority::Body &SetAuthority::Body::operator=(const Body &value)
{
	m_AuthorityRec = value.m_AuthorityRec;
	m_AuthorityRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool SetAuthority::Body::operator==(const Body &value) const
{
	if (m_AuthorityRec != value.m_AuthorityRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool SetAuthority::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

SetAuthority::Body::Body()
{
	m_AuthorityRec.setParent(this);
	/// No Initialization of m_AuthorityRec necessary.
}

SetAuthority::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_AuthorityRec.setParent(this);
	/// No Initialization of m_AuthorityRec necessary.
	
	/// Copy the values
	m_AuthorityRec = value.m_AuthorityRec;
	m_AuthorityRec.setParent(this);
	/// This code is currently not supported
}

SetAuthority::Body::~Body()
{
}

SetAuthority::Body* const SetAuthority::getBody()
{
	return &m_Body;
}

int SetAuthority::setBody(const Body &value)
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
const unsigned int SetAuthority::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void SetAuthority::encode(unsigned char *bytes)
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

void SetAuthority::decode(const unsigned char *bytes)
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

SetAuthority &SetAuthority::operator=(const SetAuthority &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool SetAuthority::operator==(const SetAuthority &value) const
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

bool SetAuthority::operator!=(const SetAuthority &value) const
{
	return !((*this) == value);
}

SetAuthority::SetAuthority()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

SetAuthority::SetAuthority(const SetAuthority &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

SetAuthority::~SetAuthority()
{
}


}
