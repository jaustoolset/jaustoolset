#include "urn_jaus_jss_environmentSensing_VisualSensor_1_0/Messages/ReportTimeout.h"

namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0
{

void ReportTimeout::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportTimeout::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportTimeout::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportTimeout::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportTimeout::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportTimeout::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportTimeout::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportTimeout::AppHeader::HeaderRec &ReportTimeout::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportTimeout::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportTimeout::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportTimeout::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4003;
}

ReportTimeout::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4003;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportTimeout::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportTimeout::AppHeader::HeaderRec* const ReportTimeout::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportTimeout::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportTimeout::AppHeader::setParentPresenceVector()
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
const unsigned int ReportTimeout::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportTimeout::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportTimeout::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportTimeout::AppHeader &ReportTimeout::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportTimeout::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportTimeout::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportTimeout::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportTimeout::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportTimeout::AppHeader::~AppHeader()
{
}

ReportTimeout::AppHeader* const ReportTimeout::getAppHeader()
{
	return &m_AppHeader;
}

int ReportTimeout::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportTimeout::Body::ReportTimoutRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportTimeout::Body::ReportTimoutRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportTimeout::Body::ReportTimoutRec::getTimeout()
{
	return m_Timeout;
}

int ReportTimeout::Body::ReportTimoutRec::setTimeout(jUnsignedByte value)
{
	if (((value >= 1) && (value <= 255)) || (value == 0))
	{
		m_Timeout = value;
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
const unsigned int ReportTimeout::Body::ReportTimoutRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportTimeout::Body::ReportTimoutRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_TimeoutTemp;
	
	m_TimeoutTemp = JSIDL_v_1_0::correctEndianness(m_Timeout);
	memcpy(bytes + pos, &m_TimeoutTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportTimeout::Body::ReportTimoutRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_TimeoutTemp;
	
	memcpy(&m_TimeoutTemp, bytes + pos, sizeof(jUnsignedByte));
	m_Timeout = JSIDL_v_1_0::correctEndianness(m_TimeoutTemp);
	pos += sizeof(jUnsignedByte);
}

ReportTimeout::Body::ReportTimoutRec &ReportTimeout::Body::ReportTimoutRec::operator=(const ReportTimoutRec &value)
{
	m_Timeout = value.m_Timeout;
	
	return *this;
}

bool ReportTimeout::Body::ReportTimoutRec::operator==(const ReportTimoutRec &value) const
{
	if (m_Timeout != value.m_Timeout)
	{
		return false;
	}
	
	return true;
}

bool ReportTimeout::Body::ReportTimoutRec::operator!=(const ReportTimoutRec &value) const
{
	return !((*this) == value);
}

ReportTimeout::Body::ReportTimoutRec::ReportTimoutRec()
{
	m_parent = NULL;
	m_Timeout = 0;
}

ReportTimeout::Body::ReportTimoutRec::ReportTimoutRec(const ReportTimoutRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_Timeout = 0;
	
	/// Copy the values
	m_Timeout = value.m_Timeout;
}

ReportTimeout::Body::ReportTimoutRec::~ReportTimoutRec()
{
}

ReportTimeout::Body::ReportTimoutRec* const ReportTimeout::Body::getReportTimoutRec()
{
	return &m_ReportTimoutRec;
}

int ReportTimeout::Body::setReportTimoutRec(const ReportTimoutRec &value)
{
	m_ReportTimoutRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportTimeout::Body::setParentPresenceVector()
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
const unsigned int ReportTimeout::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ReportTimoutRec.getSize();
	
	return size;
}

void ReportTimeout::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportTimoutRec.encode(bytes + pos);
	pos += m_ReportTimoutRec.getSize();
}

void ReportTimeout::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportTimoutRec.decode(bytes + pos);
	pos += m_ReportTimoutRec.getSize();
}

ReportTimeout::Body &ReportTimeout::Body::operator=(const Body &value)
{
	m_ReportTimoutRec = value.m_ReportTimoutRec;
	m_ReportTimoutRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportTimeout::Body::operator==(const Body &value) const
{
	if (m_ReportTimoutRec != value.m_ReportTimoutRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportTimeout::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportTimeout::Body::Body()
{
	m_ReportTimoutRec.setParent(this);
	/// No Initialization of m_ReportTimoutRec necessary.
}

ReportTimeout::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ReportTimoutRec.setParent(this);
	/// No Initialization of m_ReportTimoutRec necessary.
	
	/// Copy the values
	m_ReportTimoutRec = value.m_ReportTimoutRec;
	m_ReportTimoutRec.setParent(this);
	/// This code is currently not supported
}

ReportTimeout::Body::~Body()
{
}

ReportTimeout::Body* const ReportTimeout::getBody()
{
	return &m_Body;
}

int ReportTimeout::setBody(const Body &value)
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
const unsigned int ReportTimeout::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportTimeout::encode(unsigned char *bytes)
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

void ReportTimeout::decode(const unsigned char *bytes)
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

ReportTimeout &ReportTimeout::operator=(const ReportTimeout &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportTimeout::operator==(const ReportTimeout &value) const
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

bool ReportTimeout::operator!=(const ReportTimeout &value) const
{
	return !((*this) == value);
}

ReportTimeout::ReportTimeout()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportTimeout::ReportTimeout(const ReportTimeout &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportTimeout::~ReportTimeout()
{
}


}
