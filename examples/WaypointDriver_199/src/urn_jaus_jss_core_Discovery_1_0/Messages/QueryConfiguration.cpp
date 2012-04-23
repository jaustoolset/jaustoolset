#include "urn_jaus_jss_core_Discovery_1_0/Messages/QueryConfiguration.h"

namespace urn_jaus_jss_core_Discovery_1_0
{

void QueryConfiguration::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryConfiguration::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryConfiguration::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryConfiguration::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryConfiguration::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryConfiguration::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryConfiguration::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryConfiguration::AppHeader::HeaderRec &QueryConfiguration::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryConfiguration::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryConfiguration::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryConfiguration::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2b01;
}

QueryConfiguration::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2b01;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryConfiguration::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryConfiguration::AppHeader::HeaderRec* const QueryConfiguration::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryConfiguration::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryConfiguration::AppHeader::setParentPresenceVector()
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
const unsigned int QueryConfiguration::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryConfiguration::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryConfiguration::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryConfiguration::AppHeader &QueryConfiguration::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryConfiguration::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryConfiguration::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryConfiguration::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryConfiguration::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryConfiguration::AppHeader::~AppHeader()
{
}

QueryConfiguration::AppHeader* const QueryConfiguration::getAppHeader()
{
	return &m_AppHeader;
}

int QueryConfiguration::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryConfiguration::Body::QueryConfigurationRec::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryConfiguration::Body::QueryConfigurationRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte QueryConfiguration::Body::QueryConfigurationRec::getQueryType()
{
	return m_QueryType;
}

int QueryConfiguration::Body::QueryConfigurationRec::setQueryType(jUnsignedByte value)
{
	if (((value >= 4) && (value <= 255)) || (value == 0) || (value == 1) || (value == 2) || (value == 3))
	{
		m_QueryType = value;
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
const unsigned int QueryConfiguration::Body::QueryConfigurationRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryConfiguration::Body::QueryConfigurationRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_QueryTypeTemp;
	
	m_QueryTypeTemp = JSIDL_v_1_0::correctEndianness(m_QueryType);
	memcpy(bytes + pos, &m_QueryTypeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void QueryConfiguration::Body::QueryConfigurationRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_QueryTypeTemp;
	
	memcpy(&m_QueryTypeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_QueryType = JSIDL_v_1_0::correctEndianness(m_QueryTypeTemp);
	pos += sizeof(jUnsignedByte);
}

QueryConfiguration::Body::QueryConfigurationRec &QueryConfiguration::Body::QueryConfigurationRec::operator=(const QueryConfigurationRec &value)
{
	m_QueryType = value.m_QueryType;
	
	return *this;
}

bool QueryConfiguration::Body::QueryConfigurationRec::operator==(const QueryConfigurationRec &value) const
{
	if (m_QueryType != value.m_QueryType)
	{
		return false;
	}
	
	return true;
}

bool QueryConfiguration::Body::QueryConfigurationRec::operator!=(const QueryConfigurationRec &value) const
{
	return !((*this) == value);
}

QueryConfiguration::Body::QueryConfigurationRec::QueryConfigurationRec()
{
	m_parent = NULL;
	m_QueryType = 0;
}

QueryConfiguration::Body::QueryConfigurationRec::QueryConfigurationRec(const QueryConfigurationRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_QueryType = 0;
	
	/// Copy the values
	m_QueryType = value.m_QueryType;
}

QueryConfiguration::Body::QueryConfigurationRec::~QueryConfigurationRec()
{
}

QueryConfiguration::Body::QueryConfigurationRec* const QueryConfiguration::Body::getQueryConfigurationRec()
{
	return &m_QueryConfigurationRec;
}

int QueryConfiguration::Body::setQueryConfigurationRec(const QueryConfigurationRec &value)
{
	m_QueryConfigurationRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryConfiguration::Body::setParentPresenceVector()
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
const unsigned int QueryConfiguration::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryConfigurationRec.getSize();
	
	return size;
}

void QueryConfiguration::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryConfigurationRec.encode(bytes + pos);
	pos += m_QueryConfigurationRec.getSize();
}

void QueryConfiguration::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryConfigurationRec.decode(bytes + pos);
	pos += m_QueryConfigurationRec.getSize();
}

QueryConfiguration::Body &QueryConfiguration::Body::operator=(const Body &value)
{
	m_QueryConfigurationRec = value.m_QueryConfigurationRec;
	m_QueryConfigurationRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryConfiguration::Body::operator==(const Body &value) const
{
	if (m_QueryConfigurationRec != value.m_QueryConfigurationRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryConfiguration::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryConfiguration::Body::Body()
{
	m_QueryConfigurationRec.setParent(this);
	/// No Initialization of m_QueryConfigurationRec necessary.
}

QueryConfiguration::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryConfigurationRec.setParent(this);
	/// No Initialization of m_QueryConfigurationRec necessary.
	
	/// Copy the values
	m_QueryConfigurationRec = value.m_QueryConfigurationRec;
	m_QueryConfigurationRec.setParent(this);
	/// This code is currently not supported
}

QueryConfiguration::Body::~Body()
{
}

QueryConfiguration::Body* const QueryConfiguration::getBody()
{
	return &m_Body;
}

int QueryConfiguration::setBody(const Body &value)
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
const unsigned int QueryConfiguration::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryConfiguration::encode(unsigned char *bytes)
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

void QueryConfiguration::decode(const unsigned char *bytes)
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

QueryConfiguration &QueryConfiguration::operator=(const QueryConfiguration &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryConfiguration::operator==(const QueryConfiguration &value) const
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

bool QueryConfiguration::operator!=(const QueryConfiguration &value) const
{
	return !((*this) == value);
}

QueryConfiguration::QueryConfiguration()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryConfiguration::QueryConfiguration(const QueryConfiguration &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryConfiguration::~QueryConfiguration()
{
}


}
