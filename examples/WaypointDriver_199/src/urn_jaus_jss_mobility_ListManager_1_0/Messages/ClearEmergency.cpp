#include "urn_jaus_jss_mobility_ListManager_1_0/Messages/ClearEmergency.h"

namespace urn_jaus_jss_mobility_ListManager_1_0
{

void ClearEmergency::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ClearEmergency::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ClearEmergency::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ClearEmergency::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ClearEmergency::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ClearEmergency::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ClearEmergency::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ClearEmergency::AppHeader::HeaderRec &ClearEmergency::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ClearEmergency::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ClearEmergency::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ClearEmergency::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x0007;
}

ClearEmergency::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x0007;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ClearEmergency::AppHeader::HeaderRec::~HeaderRec()
{
}

ClearEmergency::AppHeader::HeaderRec* const ClearEmergency::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ClearEmergency::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ClearEmergency::AppHeader::setParentPresenceVector()
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
const unsigned int ClearEmergency::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ClearEmergency::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ClearEmergency::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ClearEmergency::AppHeader &ClearEmergency::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ClearEmergency::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ClearEmergency::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ClearEmergency::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ClearEmergency::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ClearEmergency::AppHeader::~AppHeader()
{
}

ClearEmergency::AppHeader* const ClearEmergency::getAppHeader()
{
	return &m_AppHeader;
}

int ClearEmergency::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ClearEmergency::Body::ClearEmergencyRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ClearEmergency::Body::ClearEmergencyRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ClearEmergency::Body::ClearEmergencyRec::getEmergencyCode()
{
	return m_EmergencyCode;
}

int ClearEmergency::Body::ClearEmergencyRec::setEmergencyCode(jUnsignedShortInteger value)
{
	if ((value == 1))
	{
		m_EmergencyCode = value;
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
const unsigned int ClearEmergency::Body::ClearEmergencyRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ClearEmergency::Body::ClearEmergencyRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_EmergencyCodeTemp;
	
	m_EmergencyCodeTemp = JSIDL_v_1_0::correctEndianness(m_EmergencyCode);
	memcpy(bytes + pos, &m_EmergencyCodeTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void ClearEmergency::Body::ClearEmergencyRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_EmergencyCodeTemp;
	
	memcpy(&m_EmergencyCodeTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_EmergencyCode = JSIDL_v_1_0::correctEndianness(m_EmergencyCodeTemp);
	pos += sizeof(jUnsignedShortInteger);
}

ClearEmergency::Body::ClearEmergencyRec &ClearEmergency::Body::ClearEmergencyRec::operator=(const ClearEmergencyRec &value)
{
	m_EmergencyCode = value.m_EmergencyCode;
	
	return *this;
}

bool ClearEmergency::Body::ClearEmergencyRec::operator==(const ClearEmergencyRec &value) const
{
	if (m_EmergencyCode != value.m_EmergencyCode)
	{
		return false;
	}
	
	return true;
}

bool ClearEmergency::Body::ClearEmergencyRec::operator!=(const ClearEmergencyRec &value) const
{
	return !((*this) == value);
}

ClearEmergency::Body::ClearEmergencyRec::ClearEmergencyRec()
{
	m_parent = NULL;
	m_EmergencyCode = 0;
}

ClearEmergency::Body::ClearEmergencyRec::ClearEmergencyRec(const ClearEmergencyRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_EmergencyCode = 0;
	
	/// Copy the values
	m_EmergencyCode = value.m_EmergencyCode;
}

ClearEmergency::Body::ClearEmergencyRec::~ClearEmergencyRec()
{
}

ClearEmergency::Body::ClearEmergencyRec* const ClearEmergency::Body::getClearEmergencyRec()
{
	return &m_ClearEmergencyRec;
}

int ClearEmergency::Body::setClearEmergencyRec(const ClearEmergencyRec &value)
{
	m_ClearEmergencyRec = value;
	setParentPresenceVector();
	return 0;
}

void ClearEmergency::Body::setParentPresenceVector()
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
const unsigned int ClearEmergency::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ClearEmergencyRec.getSize();
	
	return size;
}

void ClearEmergency::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ClearEmergencyRec.encode(bytes + pos);
	pos += m_ClearEmergencyRec.getSize();
}

void ClearEmergency::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ClearEmergencyRec.decode(bytes + pos);
	pos += m_ClearEmergencyRec.getSize();
}

ClearEmergency::Body &ClearEmergency::Body::operator=(const Body &value)
{
	m_ClearEmergencyRec = value.m_ClearEmergencyRec;
	m_ClearEmergencyRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ClearEmergency::Body::operator==(const Body &value) const
{
	if (m_ClearEmergencyRec != value.m_ClearEmergencyRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ClearEmergency::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ClearEmergency::Body::Body()
{
	m_ClearEmergencyRec.setParent(this);
	/// No Initialization of m_ClearEmergencyRec necessary.
}

ClearEmergency::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ClearEmergencyRec.setParent(this);
	/// No Initialization of m_ClearEmergencyRec necessary.
	
	/// Copy the values
	m_ClearEmergencyRec = value.m_ClearEmergencyRec;
	m_ClearEmergencyRec.setParent(this);
	/// This code is currently not supported
}

ClearEmergency::Body::~Body()
{
}

ClearEmergency::Body* const ClearEmergency::getBody()
{
	return &m_Body;
}

int ClearEmergency::setBody(const Body &value)
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
const unsigned int ClearEmergency::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ClearEmergency::encode(unsigned char *bytes)
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

void ClearEmergency::decode(const unsigned char *bytes)
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

ClearEmergency &ClearEmergency::operator=(const ClearEmergency &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ClearEmergency::operator==(const ClearEmergency &value) const
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

bool ClearEmergency::operator!=(const ClearEmergency &value) const
{
	return !((*this) == value);
}

ClearEmergency::ClearEmergency()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

ClearEmergency::ClearEmergency(const ClearEmergency &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ClearEmergency::~ClearEmergency()
{
}


}
