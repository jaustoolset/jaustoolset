#include "urn_jaus_jss_core_Discovery_1_0/Messages/QueryIdentification.h"

namespace urn_jaus_jss_core_Discovery_1_0
{

void QueryIdentification::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryIdentification::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryIdentification::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryIdentification::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryIdentification::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryIdentification::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryIdentification::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryIdentification::AppHeader::HeaderRec &QueryIdentification::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryIdentification::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryIdentification::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryIdentification::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2b00;
}

QueryIdentification::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2b00;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryIdentification::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryIdentification::AppHeader::HeaderRec* const QueryIdentification::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryIdentification::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryIdentification::AppHeader::setParentPresenceVector()
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
const unsigned int QueryIdentification::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryIdentification::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryIdentification::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryIdentification::AppHeader &QueryIdentification::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryIdentification::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryIdentification::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryIdentification::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryIdentification::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryIdentification::AppHeader::~AppHeader()
{
}

QueryIdentification::AppHeader* const QueryIdentification::getAppHeader()
{
	return &m_AppHeader;
}

int QueryIdentification::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryIdentification::Body::QueryIdentificationRec::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryIdentification::Body::QueryIdentificationRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte QueryIdentification::Body::QueryIdentificationRec::getQueryType()
{
	return m_QueryType;
}

int QueryIdentification::Body::QueryIdentificationRec::setQueryType(jUnsignedByte value)
{
	if (((value >= 5) && (value <= 255)) || (value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4))
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
const unsigned int QueryIdentification::Body::QueryIdentificationRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryIdentification::Body::QueryIdentificationRec::encode(unsigned char *bytes)
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

void QueryIdentification::Body::QueryIdentificationRec::decode(const unsigned char *bytes)
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

QueryIdentification::Body::QueryIdentificationRec &QueryIdentification::Body::QueryIdentificationRec::operator=(const QueryIdentificationRec &value)
{
	m_QueryType = value.m_QueryType;
	
	return *this;
}

bool QueryIdentification::Body::QueryIdentificationRec::operator==(const QueryIdentificationRec &value) const
{
	if (m_QueryType != value.m_QueryType)
	{
		return false;
	}
	
	return true;
}

bool QueryIdentification::Body::QueryIdentificationRec::operator!=(const QueryIdentificationRec &value) const
{
	return !((*this) == value);
}

QueryIdentification::Body::QueryIdentificationRec::QueryIdentificationRec()
{
	m_parent = NULL;
	m_QueryType = 0;
}

QueryIdentification::Body::QueryIdentificationRec::QueryIdentificationRec(const QueryIdentificationRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_QueryType = 0;
	
	/// Copy the values
	m_QueryType = value.m_QueryType;
}

QueryIdentification::Body::QueryIdentificationRec::~QueryIdentificationRec()
{
}

QueryIdentification::Body::QueryIdentificationRec* const QueryIdentification::Body::getQueryIdentificationRec()
{
	return &m_QueryIdentificationRec;
}

int QueryIdentification::Body::setQueryIdentificationRec(const QueryIdentificationRec &value)
{
	m_QueryIdentificationRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryIdentification::Body::setParentPresenceVector()
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
const unsigned int QueryIdentification::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryIdentificationRec.getSize();
	
	return size;
}

void QueryIdentification::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryIdentificationRec.encode(bytes + pos);
	pos += m_QueryIdentificationRec.getSize();
}

void QueryIdentification::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryIdentificationRec.decode(bytes + pos);
	pos += m_QueryIdentificationRec.getSize();
}

QueryIdentification::Body &QueryIdentification::Body::operator=(const Body &value)
{
	m_QueryIdentificationRec = value.m_QueryIdentificationRec;
	m_QueryIdentificationRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryIdentification::Body::operator==(const Body &value) const
{
	if (m_QueryIdentificationRec != value.m_QueryIdentificationRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryIdentification::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryIdentification::Body::Body()
{
	m_QueryIdentificationRec.setParent(this);
	/// No Initialization of m_QueryIdentificationRec necessary.
}

QueryIdentification::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryIdentificationRec.setParent(this);
	/// No Initialization of m_QueryIdentificationRec necessary.
	
	/// Copy the values
	m_QueryIdentificationRec = value.m_QueryIdentificationRec;
	m_QueryIdentificationRec.setParent(this);
	/// This code is currently not supported
}

QueryIdentification::Body::~Body()
{
}

QueryIdentification::Body* const QueryIdentification::getBody()
{
	return &m_Body;
}

int QueryIdentification::setBody(const Body &value)
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
const unsigned int QueryIdentification::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryIdentification::encode(unsigned char *bytes)
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

void QueryIdentification::decode(const unsigned char *bytes)
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

QueryIdentification &QueryIdentification::operator=(const QueryIdentification &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryIdentification::operator==(const QueryIdentification &value) const
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

bool QueryIdentification::operator!=(const QueryIdentification &value) const
{
	return !((*this) == value);
}

QueryIdentification::QueryIdentification()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryIdentification::QueryIdentification(const QueryIdentification &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryIdentification::~QueryIdentification()
{
}


}
