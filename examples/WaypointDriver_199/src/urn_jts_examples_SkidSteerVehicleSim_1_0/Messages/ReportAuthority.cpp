#include "urn_jts_examples_SkidSteerVehicleSim_1_0/Messages/ReportAuthority.h"

namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

void ReportAuthority::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportAuthority::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportAuthority::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportAuthority::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportAuthority::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportAuthority::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportAuthority::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportAuthority::AppHeader::HeaderRec &ReportAuthority::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportAuthority::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportAuthority::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportAuthority::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4001;
}

ReportAuthority::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4001;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportAuthority::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportAuthority::AppHeader::HeaderRec* const ReportAuthority::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportAuthority::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportAuthority::AppHeader::setParentPresenceVector()
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
const unsigned int ReportAuthority::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportAuthority::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportAuthority::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportAuthority::AppHeader &ReportAuthority::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportAuthority::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportAuthority::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportAuthority::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportAuthority::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportAuthority::AppHeader::~AppHeader()
{
}

ReportAuthority::AppHeader* const ReportAuthority::getAppHeader()
{
	return &m_AppHeader;
}

int ReportAuthority::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportAuthority::Body::ReportAuthorityRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportAuthority::Body::ReportAuthorityRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportAuthority::Body::ReportAuthorityRec::getAuthorityCode()
{
	return m_AuthorityCode;
}

int ReportAuthority::Body::ReportAuthorityRec::setAuthorityCode(jUnsignedByte value)
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
const unsigned int ReportAuthority::Body::ReportAuthorityRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportAuthority::Body::ReportAuthorityRec::encode(unsigned char *bytes)
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

void ReportAuthority::Body::ReportAuthorityRec::decode(const unsigned char *bytes)
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

ReportAuthority::Body::ReportAuthorityRec &ReportAuthority::Body::ReportAuthorityRec::operator=(const ReportAuthorityRec &value)
{
	m_AuthorityCode = value.m_AuthorityCode;
	
	return *this;
}

bool ReportAuthority::Body::ReportAuthorityRec::operator==(const ReportAuthorityRec &value) const
{
	if (m_AuthorityCode != value.m_AuthorityCode)
	{
		return false;
	}
	
	return true;
}

bool ReportAuthority::Body::ReportAuthorityRec::operator!=(const ReportAuthorityRec &value) const
{
	return !((*this) == value);
}

ReportAuthority::Body::ReportAuthorityRec::ReportAuthorityRec()
{
	m_parent = NULL;
	m_AuthorityCode = 0;
}

ReportAuthority::Body::ReportAuthorityRec::ReportAuthorityRec(const ReportAuthorityRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_AuthorityCode = 0;
	
	/// Copy the values
	m_AuthorityCode = value.m_AuthorityCode;
}

ReportAuthority::Body::ReportAuthorityRec::~ReportAuthorityRec()
{
}

ReportAuthority::Body::ReportAuthorityRec* const ReportAuthority::Body::getReportAuthorityRec()
{
	return &m_ReportAuthorityRec;
}

int ReportAuthority::Body::setReportAuthorityRec(const ReportAuthorityRec &value)
{
	m_ReportAuthorityRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportAuthority::Body::setParentPresenceVector()
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
const unsigned int ReportAuthority::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ReportAuthorityRec.getSize();
	
	return size;
}

void ReportAuthority::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportAuthorityRec.encode(bytes + pos);
	pos += m_ReportAuthorityRec.getSize();
}

void ReportAuthority::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportAuthorityRec.decode(bytes + pos);
	pos += m_ReportAuthorityRec.getSize();
}

ReportAuthority::Body &ReportAuthority::Body::operator=(const Body &value)
{
	m_ReportAuthorityRec = value.m_ReportAuthorityRec;
	m_ReportAuthorityRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportAuthority::Body::operator==(const Body &value) const
{
	if (m_ReportAuthorityRec != value.m_ReportAuthorityRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportAuthority::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportAuthority::Body::Body()
{
	m_ReportAuthorityRec.setParent(this);
	/// No Initialization of m_ReportAuthorityRec necessary.
}

ReportAuthority::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ReportAuthorityRec.setParent(this);
	/// No Initialization of m_ReportAuthorityRec necessary.
	
	/// Copy the values
	m_ReportAuthorityRec = value.m_ReportAuthorityRec;
	m_ReportAuthorityRec.setParent(this);
	/// This code is currently not supported
}

ReportAuthority::Body::~Body()
{
}

ReportAuthority::Body* const ReportAuthority::getBody()
{
	return &m_Body;
}

int ReportAuthority::setBody(const Body &value)
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
const unsigned int ReportAuthority::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportAuthority::encode(unsigned char *bytes)
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

void ReportAuthority::decode(const unsigned char *bytes)
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

ReportAuthority &ReportAuthority::operator=(const ReportAuthority &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportAuthority::operator==(const ReportAuthority &value) const
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

bool ReportAuthority::operator!=(const ReportAuthority &value) const
{
	return !((*this) == value);
}

ReportAuthority::ReportAuthority()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportAuthority::ReportAuthority(const ReportAuthority &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportAuthority::~ReportAuthority()
{
}


}
