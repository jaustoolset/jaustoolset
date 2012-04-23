#include "urn_jaus_jss_mobility_ListManager_1_0/Messages/ReportStatus.h"

namespace urn_jaus_jss_mobility_ListManager_1_0
{

void ReportStatus::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportStatus::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportStatus::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportStatus::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportStatus::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportStatus::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportStatus::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportStatus::AppHeader::HeaderRec &ReportStatus::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportStatus::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportStatus::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportStatus::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4002;
}

ReportStatus::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4002;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportStatus::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportStatus::AppHeader::HeaderRec* const ReportStatus::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportStatus::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportStatus::AppHeader::setParentPresenceVector()
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
const unsigned int ReportStatus::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportStatus::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportStatus::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportStatus::AppHeader &ReportStatus::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportStatus::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportStatus::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportStatus::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportStatus::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportStatus::AppHeader::~AppHeader()
{
}

ReportStatus::AppHeader* const ReportStatus::getAppHeader()
{
	return &m_AppHeader;
}

int ReportStatus::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportStatus::Body::ReportStatusRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportStatus::Body::ReportStatusRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportStatus::Body::ReportStatusRec::getStatus()
{
	return m_Status;
}

int ReportStatus::Body::ReportStatusRec::setStatus(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5))
	{
		m_Status = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStatus::Body::ReportStatusRec::getReserved()
{
	return m_Reserved;
}

int ReportStatus::Body::ReportStatusRec::setReserved(jUnsignedInteger value)
{
	m_Reserved = value;
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
const unsigned int ReportStatus::Body::ReportStatusRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void ReportStatus::Body::ReportStatusRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_StatusTemp;
	
	m_StatusTemp = JSIDL_v_1_0::correctEndianness(m_Status);
	memcpy(bytes + pos, &m_StatusTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedInteger m_ReservedTemp;
	
	m_ReservedTemp = JSIDL_v_1_0::correctEndianness(m_Reserved);
	memcpy(bytes + pos, &m_ReservedTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
}

void ReportStatus::Body::ReportStatusRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_StatusTemp;
	
	memcpy(&m_StatusTemp, bytes + pos, sizeof(jUnsignedByte));
	m_Status = JSIDL_v_1_0::correctEndianness(m_StatusTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedInteger m_ReservedTemp;
	
	memcpy(&m_ReservedTemp, bytes + pos, sizeof(jUnsignedInteger));
	m_Reserved = JSIDL_v_1_0::correctEndianness(m_ReservedTemp);
	pos += sizeof(jUnsignedInteger);
}

ReportStatus::Body::ReportStatusRec &ReportStatus::Body::ReportStatusRec::operator=(const ReportStatusRec &value)
{
	m_Status = value.m_Status;
	m_Reserved = value.m_Reserved;
	
	return *this;
}

bool ReportStatus::Body::ReportStatusRec::operator==(const ReportStatusRec &value) const
{
	if (m_Status != value.m_Status)
	{
		return false;
	}
	if (m_Reserved != value.m_Reserved)
	{
		return false;
	}
	
	return true;
}

bool ReportStatus::Body::ReportStatusRec::operator!=(const ReportStatusRec &value) const
{
	return !((*this) == value);
}

ReportStatus::Body::ReportStatusRec::ReportStatusRec()
{
	m_parent = NULL;
	m_Status = 0;
	m_Reserved = 0;
}

ReportStatus::Body::ReportStatusRec::ReportStatusRec(const ReportStatusRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_Status = 0;
	m_Reserved = 0;
	
	/// Copy the values
	m_Status = value.m_Status;
	m_Reserved = value.m_Reserved;
}

ReportStatus::Body::ReportStatusRec::~ReportStatusRec()
{
}

ReportStatus::Body::ReportStatusRec* const ReportStatus::Body::getReportStatusRec()
{
	return &m_ReportStatusRec;
}

int ReportStatus::Body::setReportStatusRec(const ReportStatusRec &value)
{
	m_ReportStatusRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportStatus::Body::setParentPresenceVector()
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
const unsigned int ReportStatus::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ReportStatusRec.getSize();
	
	return size;
}

void ReportStatus::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportStatusRec.encode(bytes + pos);
	pos += m_ReportStatusRec.getSize();
}

void ReportStatus::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportStatusRec.decode(bytes + pos);
	pos += m_ReportStatusRec.getSize();
}

ReportStatus::Body &ReportStatus::Body::operator=(const Body &value)
{
	m_ReportStatusRec = value.m_ReportStatusRec;
	m_ReportStatusRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportStatus::Body::operator==(const Body &value) const
{
	if (m_ReportStatusRec != value.m_ReportStatusRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportStatus::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportStatus::Body::Body()
{
	m_ReportStatusRec.setParent(this);
	/// No Initialization of m_ReportStatusRec necessary.
}

ReportStatus::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ReportStatusRec.setParent(this);
	/// No Initialization of m_ReportStatusRec necessary.
	
	/// Copy the values
	m_ReportStatusRec = value.m_ReportStatusRec;
	m_ReportStatusRec.setParent(this);
	/// This code is currently not supported
}

ReportStatus::Body::~Body()
{
}

ReportStatus::Body* const ReportStatus::getBody()
{
	return &m_Body;
}

int ReportStatus::setBody(const Body &value)
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
const unsigned int ReportStatus::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportStatus::encode(unsigned char *bytes)
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

void ReportStatus::decode(const unsigned char *bytes)
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

ReportStatus &ReportStatus::operator=(const ReportStatus &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportStatus::operator==(const ReportStatus &value) const
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

bool ReportStatus::operator!=(const ReportStatus &value) const
{
	return !((*this) == value);
}

ReportStatus::ReportStatus()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportStatus::ReportStatus(const ReportStatus &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportStatus::~ReportStatus()
{
}


}
