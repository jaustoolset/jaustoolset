#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/Messages/QuerySubsystemIDs.h"

namespace urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4
{

void QuerySubsystemIDs::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QuerySubsystemIDs::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QuerySubsystemIDs::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QuerySubsystemIDs::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QuerySubsystemIDs::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QuerySubsystemIDs::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QuerySubsystemIDs::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QuerySubsystemIDs::AppHeader::HeaderRec &QuerySubsystemIDs::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QuerySubsystemIDs::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QuerySubsystemIDs::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QuerySubsystemIDs::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0xeb02;
}

QuerySubsystemIDs::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0xeb02;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QuerySubsystemIDs::AppHeader::HeaderRec::~HeaderRec()
{
}

QuerySubsystemIDs::AppHeader::HeaderRec* const QuerySubsystemIDs::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QuerySubsystemIDs::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QuerySubsystemIDs::AppHeader::setParentPresenceVector()
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
const unsigned int QuerySubsystemIDs::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QuerySubsystemIDs::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QuerySubsystemIDs::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QuerySubsystemIDs::AppHeader &QuerySubsystemIDs::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QuerySubsystemIDs::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QuerySubsystemIDs::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QuerySubsystemIDs::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QuerySubsystemIDs::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QuerySubsystemIDs::AppHeader::~AppHeader()
{
}

QuerySubsystemIDs::AppHeader* const QuerySubsystemIDs::getAppHeader()
{
	return &m_AppHeader;
}

int QuerySubsystemIDs::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QuerySubsystemIDs::Body::QuerySubsystemIDsRec::setParent(Body* parent)
{
	m_parent = parent;
}

void QuerySubsystemIDs::Body::QuerySubsystemIDsRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jByte QuerySubsystemIDs::Body::QuerySubsystemIDsRec::getSubsystemIDType()
{
	return m_SubsystemIDType;
}

int QuerySubsystemIDs::Body::QuerySubsystemIDsRec::setSubsystemIDType(jByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3))
	{
		m_SubsystemIDType = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

void QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::setParent(QuerySubsystemIDsRec* parent)
{
	m_parent = parent;
}

void QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const unsigned int QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::getMAC_DimensionSize() const
{
	return m_MAC_DimensionSize;
}

jUnsignedByte QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::getMac(unsigned int MAC_Dimension)
{
	unsigned int index = MAC_Dimension;
	
	return m_Mac[index];
}

int QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::setMac(unsigned int MAC_Dimension, jUnsignedByte value)
{
	unsigned int index = MAC_Dimension;
	
	m_Mac[index] = value;
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
const unsigned int QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte) * 6;
	
	return size;
}

void QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_MacTemp;
	
	for (unsigned int i = 0; i < 6; i++)
	{
		m_MacTemp = JSIDL_v_1_0::correctEndianness(m_Mac[i]);
		memcpy(bytes + pos, &m_MacTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
}

void QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_MacTemp;
	
	for (unsigned int i = 0; i < 6; i++)
	{
		memcpy(&m_MacTemp, bytes + pos, sizeof(jUnsignedByte));
		m_Mac[i] = JSIDL_v_1_0::correctEndianness(m_MacTemp);
		pos += sizeof(jUnsignedByte);
	}
}

QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr &QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::operator=(const MACaddr &value)
{
	// not yet implemented
	memcpy(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6);
	
	return *this;
}

bool QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::operator==(const MACaddr &value) const
{
	// not yet implemented
	if (memcmp(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6) != 0)
	{
		return false;
	}
	
	return true;
}

bool QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::operator!=(const MACaddr &value) const
{
	return !((*this) == value);
}

QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::MACaddr()
{
	m_parent = NULL;
	m_MAC_DimensionSize = 6;
	memset( m_Mac, 0, sizeof(jUnsignedByte) * 6);
}

QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::MACaddr(const MACaddr &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MAC_DimensionSize = 6;
	memset( m_Mac, 0, sizeof(jUnsignedByte) * 6);
	
	/// Copy the values
	// not yet implemented
	memcpy(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6);
}

QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr::~MACaddr()
{
}

QuerySubsystemIDs::Body::QuerySubsystemIDsRec::MACaddr* const QuerySubsystemIDs::Body::QuerySubsystemIDsRec::getMACaddr()
{
	return &m_MACaddr;
}

int QuerySubsystemIDs::Body::QuerySubsystemIDsRec::setMACaddr(const MACaddr &value)
{
	m_MACaddr = value;
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
const unsigned int QuerySubsystemIDs::Body::QuerySubsystemIDsRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jByte);
	size += m_MACaddr.getSize();
	
	return size;
}

void QuerySubsystemIDs::Body::QuerySubsystemIDsRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jByte m_SubsystemIDTypeTemp;
	
	m_SubsystemIDTypeTemp = JSIDL_v_1_0::correctEndianness(m_SubsystemIDType);
	memcpy(bytes + pos, &m_SubsystemIDTypeTemp, sizeof(jByte));
	pos += sizeof(jByte);
	m_MACaddr.encode(bytes + pos);
	pos += m_MACaddr.getSize();
}

void QuerySubsystemIDs::Body::QuerySubsystemIDsRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jByte m_SubsystemIDTypeTemp;
	
	memcpy(&m_SubsystemIDTypeTemp, bytes + pos, sizeof(jByte));
	m_SubsystemIDType = JSIDL_v_1_0::correctEndianness(m_SubsystemIDTypeTemp);
	pos += sizeof(jByte);
	m_MACaddr.decode(bytes + pos);
	pos += m_MACaddr.getSize();
}

QuerySubsystemIDs::Body::QuerySubsystemIDsRec &QuerySubsystemIDs::Body::QuerySubsystemIDsRec::operator=(const QuerySubsystemIDsRec &value)
{
	m_SubsystemIDType = value.m_SubsystemIDType;
	m_MACaddr = value.m_MACaddr;
	
	return *this;
}

bool QuerySubsystemIDs::Body::QuerySubsystemIDsRec::operator==(const QuerySubsystemIDsRec &value) const
{
	if (m_SubsystemIDType != value.m_SubsystemIDType)
	{
		return false;
	}
	
	if (m_MACaddr != value.m_MACaddr)
	{
		return false;
	}
	
	return true;
}

bool QuerySubsystemIDs::Body::QuerySubsystemIDsRec::operator!=(const QuerySubsystemIDsRec &value) const
{
	return !((*this) == value);
}

QuerySubsystemIDs::Body::QuerySubsystemIDsRec::QuerySubsystemIDsRec()
{
	m_parent = NULL;
	m_SubsystemIDType = 0;
	m_MACaddr.setParent(this);
	/// No Initialization of m_MACaddr necessary.
}

QuerySubsystemIDs::Body::QuerySubsystemIDsRec::QuerySubsystemIDsRec(const QuerySubsystemIDsRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubsystemIDType = 0;
	m_MACaddr.setParent(this);
	/// No Initialization of m_MACaddr necessary.
	
	/// Copy the values
	m_SubsystemIDType = value.m_SubsystemIDType;
	m_MACaddr = value.m_MACaddr;
}

QuerySubsystemIDs::Body::QuerySubsystemIDsRec::~QuerySubsystemIDsRec()
{
}

QuerySubsystemIDs::Body::QuerySubsystemIDsRec* const QuerySubsystemIDs::Body::getQuerySubsystemIDsRec()
{
	return &m_QuerySubsystemIDsRec;
}

int QuerySubsystemIDs::Body::setQuerySubsystemIDsRec(const QuerySubsystemIDsRec &value)
{
	m_QuerySubsystemIDsRec = value;
	setParentPresenceVector();
	return 0;
}

void QuerySubsystemIDs::Body::setParentPresenceVector()
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
const unsigned int QuerySubsystemIDs::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QuerySubsystemIDsRec.getSize();
	
	return size;
}

void QuerySubsystemIDs::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QuerySubsystemIDsRec.encode(bytes + pos);
	pos += m_QuerySubsystemIDsRec.getSize();
}

void QuerySubsystemIDs::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QuerySubsystemIDsRec.decode(bytes + pos);
	pos += m_QuerySubsystemIDsRec.getSize();
}

QuerySubsystemIDs::Body &QuerySubsystemIDs::Body::operator=(const Body &value)
{
	m_QuerySubsystemIDsRec = value.m_QuerySubsystemIDsRec;
	m_QuerySubsystemIDsRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QuerySubsystemIDs::Body::operator==(const Body &value) const
{
	if (m_QuerySubsystemIDsRec != value.m_QuerySubsystemIDsRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QuerySubsystemIDs::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QuerySubsystemIDs::Body::Body()
{
	m_QuerySubsystemIDsRec.setParent(this);
	/// No Initialization of m_QuerySubsystemIDsRec necessary.
}

QuerySubsystemIDs::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QuerySubsystemIDsRec.setParent(this);
	/// No Initialization of m_QuerySubsystemIDsRec necessary.
	
	/// Copy the values
	m_QuerySubsystemIDsRec = value.m_QuerySubsystemIDsRec;
	m_QuerySubsystemIDsRec.setParent(this);
	/// This code is currently not supported
}

QuerySubsystemIDs::Body::~Body()
{
}

QuerySubsystemIDs::Body* const QuerySubsystemIDs::getBody()
{
	return &m_Body;
}

int QuerySubsystemIDs::setBody(const Body &value)
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
const unsigned int QuerySubsystemIDs::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QuerySubsystemIDs::encode(unsigned char *bytes)
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

void QuerySubsystemIDs::decode(const unsigned char *bytes)
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

QuerySubsystemIDs &QuerySubsystemIDs::operator=(const QuerySubsystemIDs &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QuerySubsystemIDs::operator==(const QuerySubsystemIDs &value) const
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

bool QuerySubsystemIDs::operator!=(const QuerySubsystemIDs &value) const
{
	return !((*this) == value);
}

QuerySubsystemIDs::QuerySubsystemIDs()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QuerySubsystemIDs::QuerySubsystemIDs(const QuerySubsystemIDs &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QuerySubsystemIDs::~QuerySubsystemIDs()
{
}


}
