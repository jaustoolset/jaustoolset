#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/Messages/RequestSubsystemID.h"

namespace urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4
{

void RequestSubsystemID::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void RequestSubsystemID::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger RequestSubsystemID::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int RequestSubsystemID::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int RequestSubsystemID::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void RequestSubsystemID::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void RequestSubsystemID::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

RequestSubsystemID::AppHeader::HeaderRec &RequestSubsystemID::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool RequestSubsystemID::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool RequestSubsystemID::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

RequestSubsystemID::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0xdb01;
}

RequestSubsystemID::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0xdb01;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

RequestSubsystemID::AppHeader::HeaderRec::~HeaderRec()
{
}

RequestSubsystemID::AppHeader::HeaderRec* const RequestSubsystemID::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int RequestSubsystemID::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void RequestSubsystemID::AppHeader::setParentPresenceVector()
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
const unsigned int RequestSubsystemID::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void RequestSubsystemID::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void RequestSubsystemID::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

RequestSubsystemID::AppHeader &RequestSubsystemID::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool RequestSubsystemID::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool RequestSubsystemID::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

RequestSubsystemID::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

RequestSubsystemID::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

RequestSubsystemID::AppHeader::~AppHeader()
{
}

RequestSubsystemID::AppHeader* const RequestSubsystemID::getAppHeader()
{
	return &m_AppHeader;
}

int RequestSubsystemID::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void RequestSubsystemID::Body::RequestSubsystemIDRec::setParent(Body* parent)
{
	m_parent = parent;
}

void RequestSubsystemID::Body::RequestSubsystemIDRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::setParent(RequestSubsystemIDRec* parent)
{
	m_parent = parent;
}

void RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const unsigned int RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::getMAC_DimensionSize() const
{
	return m_MAC_DimensionSize;
}

jUnsignedByte RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::getMac(unsigned int MAC_Dimension)
{
	unsigned int index = MAC_Dimension;
	
	return m_Mac[index];
}

int RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::setMac(unsigned int MAC_Dimension, jUnsignedByte value)
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
const unsigned int RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte) * 6;
	
	return size;
}

void RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::encode(unsigned char *bytes)
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

void RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::decode(const unsigned char *bytes)
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

RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr &RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::operator=(const MACaddr &value)
{
	// not yet implemented
	memcpy(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6);
	
	return *this;
}

bool RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::operator==(const MACaddr &value) const
{
	// not yet implemented
	if (memcmp(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6) != 0)
	{
		return false;
	}
	
	return true;
}

bool RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::operator!=(const MACaddr &value) const
{
	return !((*this) == value);
}

RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::MACaddr()
{
	m_parent = NULL;
	m_MAC_DimensionSize = 6;
	memset( m_Mac, 0, sizeof(jUnsignedByte) * 6);
}

RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::MACaddr(const MACaddr &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MAC_DimensionSize = 6;
	memset( m_Mac, 0, sizeof(jUnsignedByte) * 6);
	
	/// Copy the values
	// not yet implemented
	memcpy(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6);
}

RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr::~MACaddr()
{
}

RequestSubsystemID::Body::RequestSubsystemIDRec::MACaddr* const RequestSubsystemID::Body::RequestSubsystemIDRec::getMACaddr()
{
	return &m_MACaddr;
}

int RequestSubsystemID::Body::RequestSubsystemIDRec::setMACaddr(const MACaddr &value)
{
	m_MACaddr = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedShortInteger RequestSubsystemID::Body::RequestSubsystemIDRec::getType()
{
	return m_Type;
}

int RequestSubsystemID::Body::RequestSubsystemIDRec::setType(jUnsignedShortInteger value)
{
	if ((value == 10001) || (value == 20001) || (value == 30001))
	{
		m_Type = value;
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
const unsigned int RequestSubsystemID::Body::RequestSubsystemIDRec::getSize()
{
	unsigned int size = 0;
	
	size += m_MACaddr.getSize();
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void RequestSubsystemID::Body::RequestSubsystemIDRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MACaddr.encode(bytes + pos);
	pos += m_MACaddr.getSize();
	jUnsignedShortInteger m_TypeTemp;
	
	m_TypeTemp = JSIDL_v_1_0::correctEndianness(m_Type);
	memcpy(bytes + pos, &m_TypeTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void RequestSubsystemID::Body::RequestSubsystemIDRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MACaddr.decode(bytes + pos);
	pos += m_MACaddr.getSize();
	jUnsignedShortInteger m_TypeTemp;
	
	memcpy(&m_TypeTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_Type = JSIDL_v_1_0::correctEndianness(m_TypeTemp);
	pos += sizeof(jUnsignedShortInteger);
}

RequestSubsystemID::Body::RequestSubsystemIDRec &RequestSubsystemID::Body::RequestSubsystemIDRec::operator=(const RequestSubsystemIDRec &value)
{
	m_MACaddr = value.m_MACaddr;
	m_Type = value.m_Type;
	
	return *this;
}

bool RequestSubsystemID::Body::RequestSubsystemIDRec::operator==(const RequestSubsystemIDRec &value) const
{
	if (m_MACaddr != value.m_MACaddr)
	{
		return false;
	}
	if (m_Type != value.m_Type)
	{
		return false;
	}
	
	return true;
}

bool RequestSubsystemID::Body::RequestSubsystemIDRec::operator!=(const RequestSubsystemIDRec &value) const
{
	return !((*this) == value);
}

RequestSubsystemID::Body::RequestSubsystemIDRec::RequestSubsystemIDRec()
{
	m_parent = NULL;
	m_MACaddr.setParent(this);
	/// No Initialization of m_MACaddr necessary.
	m_Type = 0;
}

RequestSubsystemID::Body::RequestSubsystemIDRec::RequestSubsystemIDRec(const RequestSubsystemIDRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MACaddr.setParent(this);
	/// No Initialization of m_MACaddr necessary.
	m_Type = 0;
	
	/// Copy the values
	m_MACaddr = value.m_MACaddr;
	m_Type = value.m_Type;
}

RequestSubsystemID::Body::RequestSubsystemIDRec::~RequestSubsystemIDRec()
{
}

RequestSubsystemID::Body::RequestSubsystemIDRec* const RequestSubsystemID::Body::getRequestSubsystemIDRec()
{
	return &m_RequestSubsystemIDRec;
}

int RequestSubsystemID::Body::setRequestSubsystemIDRec(const RequestSubsystemIDRec &value)
{
	m_RequestSubsystemIDRec = value;
	setParentPresenceVector();
	return 0;
}

void RequestSubsystemID::Body::setParentPresenceVector()
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
const unsigned int RequestSubsystemID::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_RequestSubsystemIDRec.getSize();
	
	return size;
}

void RequestSubsystemID::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestSubsystemIDRec.encode(bytes + pos);
	pos += m_RequestSubsystemIDRec.getSize();
}

void RequestSubsystemID::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestSubsystemIDRec.decode(bytes + pos);
	pos += m_RequestSubsystemIDRec.getSize();
}

RequestSubsystemID::Body &RequestSubsystemID::Body::operator=(const Body &value)
{
	m_RequestSubsystemIDRec = value.m_RequestSubsystemIDRec;
	m_RequestSubsystemIDRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool RequestSubsystemID::Body::operator==(const Body &value) const
{
	if (m_RequestSubsystemIDRec != value.m_RequestSubsystemIDRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool RequestSubsystemID::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

RequestSubsystemID::Body::Body()
{
	m_RequestSubsystemIDRec.setParent(this);
	/// No Initialization of m_RequestSubsystemIDRec necessary.
}

RequestSubsystemID::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_RequestSubsystemIDRec.setParent(this);
	/// No Initialization of m_RequestSubsystemIDRec necessary.
	
	/// Copy the values
	m_RequestSubsystemIDRec = value.m_RequestSubsystemIDRec;
	m_RequestSubsystemIDRec.setParent(this);
	/// This code is currently not supported
}

RequestSubsystemID::Body::~Body()
{
}

RequestSubsystemID::Body* const RequestSubsystemID::getBody()
{
	return &m_Body;
}

int RequestSubsystemID::setBody(const Body &value)
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
const unsigned int RequestSubsystemID::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void RequestSubsystemID::encode(unsigned char *bytes)
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

void RequestSubsystemID::decode(const unsigned char *bytes)
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

RequestSubsystemID &RequestSubsystemID::operator=(const RequestSubsystemID &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool RequestSubsystemID::operator==(const RequestSubsystemID &value) const
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

bool RequestSubsystemID::operator!=(const RequestSubsystemID &value) const
{
	return !((*this) == value);
}

RequestSubsystemID::RequestSubsystemID()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

RequestSubsystemID::RequestSubsystemID(const RequestSubsystemID &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

RequestSubsystemID::~RequestSubsystemID()
{
}


}
