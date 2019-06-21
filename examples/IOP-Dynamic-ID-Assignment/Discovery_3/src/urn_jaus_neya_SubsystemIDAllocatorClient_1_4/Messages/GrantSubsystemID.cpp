#include "urn_jaus_neya_SubsystemIDAllocatorClient_1_4/Messages/GrantSubsystemID.h"

namespace urn_jaus_neya_SubsystemIDAllocatorClient_1_4
{

void GrantSubsystemID::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void GrantSubsystemID::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger GrantSubsystemID::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int GrantSubsystemID::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int GrantSubsystemID::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void GrantSubsystemID::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void GrantSubsystemID::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

GrantSubsystemID::AppHeader::HeaderRec &GrantSubsystemID::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool GrantSubsystemID::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool GrantSubsystemID::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

GrantSubsystemID::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0xfb01;
}

GrantSubsystemID::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0xfb01;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

GrantSubsystemID::AppHeader::HeaderRec::~HeaderRec()
{
}

GrantSubsystemID::AppHeader::HeaderRec* const GrantSubsystemID::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int GrantSubsystemID::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void GrantSubsystemID::AppHeader::setParentPresenceVector()
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
const unsigned int GrantSubsystemID::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void GrantSubsystemID::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void GrantSubsystemID::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

GrantSubsystemID::AppHeader &GrantSubsystemID::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool GrantSubsystemID::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool GrantSubsystemID::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

GrantSubsystemID::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

GrantSubsystemID::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

GrantSubsystemID::AppHeader::~AppHeader()
{
}

GrantSubsystemID::AppHeader* const GrantSubsystemID::getAppHeader()
{
	return &m_AppHeader;
}

int GrantSubsystemID::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void GrantSubsystemID::Body::SubsystemRec::setParent(Body* parent)
{
	m_parent = parent;
}

void GrantSubsystemID::Body::SubsystemRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger GrantSubsystemID::Body::SubsystemRec::getSubsystemID()
{
	return m_SubsystemID;
}

int GrantSubsystemID::Body::SubsystemRec::setSubsystemID(jUnsignedShortInteger value)
{
	m_SubsystemID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedShortInteger GrantSubsystemID::Body::SubsystemRec::getType()
{
	return m_Type;
}

int GrantSubsystemID::Body::SubsystemRec::setType(jUnsignedShortInteger value)
{
	if ((value == 10001) || (value == 20001) || (value == 30001))
	{
		m_Type = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

void GrantSubsystemID::Body::SubsystemRec::MACaddr::setParent(SubsystemRec* parent)
{
	m_parent = parent;
}

void GrantSubsystemID::Body::SubsystemRec::MACaddr::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const unsigned int GrantSubsystemID::Body::SubsystemRec::MACaddr::getMAC_DimensionSize() const
{
	return m_MAC_DimensionSize;
}

jUnsignedByte GrantSubsystemID::Body::SubsystemRec::MACaddr::getMac(unsigned int MAC_Dimension)
{
	unsigned int index = MAC_Dimension;
	
	return m_Mac[index];
}

int GrantSubsystemID::Body::SubsystemRec::MACaddr::setMac(unsigned int MAC_Dimension, jUnsignedByte value)
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
const unsigned int GrantSubsystemID::Body::SubsystemRec::MACaddr::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte) * 6;
	
	return size;
}

void GrantSubsystemID::Body::SubsystemRec::MACaddr::encode(unsigned char *bytes)
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

void GrantSubsystemID::Body::SubsystemRec::MACaddr::decode(const unsigned char *bytes)
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

GrantSubsystemID::Body::SubsystemRec::MACaddr &GrantSubsystemID::Body::SubsystemRec::MACaddr::operator=(const MACaddr &value)
{
	// not yet implemented
	memcpy(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6);
	
	return *this;
}

bool GrantSubsystemID::Body::SubsystemRec::MACaddr::operator==(const MACaddr &value) const
{
	// not yet implemented
	if (memcmp(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6) != 0)
	{
		return false;
	}
	
	return true;
}

bool GrantSubsystemID::Body::SubsystemRec::MACaddr::operator!=(const MACaddr &value) const
{
	return !((*this) == value);
}

GrantSubsystemID::Body::SubsystemRec::MACaddr::MACaddr()
{
	m_parent = NULL;
	m_MAC_DimensionSize = 6;
	memset( m_Mac, 0, sizeof(jUnsignedByte) * 6);
}

GrantSubsystemID::Body::SubsystemRec::MACaddr::MACaddr(const MACaddr &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MAC_DimensionSize = 6;
	memset( m_Mac, 0, sizeof(jUnsignedByte) * 6);
	
	/// Copy the values
	// not yet implemented
	memcpy(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6);
}

GrantSubsystemID::Body::SubsystemRec::MACaddr::~MACaddr()
{
}

GrantSubsystemID::Body::SubsystemRec::MACaddr* const GrantSubsystemID::Body::SubsystemRec::getMACaddr()
{
	return &m_MACaddr;
}

int GrantSubsystemID::Body::SubsystemRec::setMACaddr(const MACaddr &value)
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
const unsigned int GrantSubsystemID::Body::SubsystemRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedShortInteger);
	size += m_MACaddr.getSize();
	
	return size;
}

void GrantSubsystemID::Body::SubsystemRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SubsystemIDTemp;
	
	m_SubsystemIDTemp = JSIDL_v_1_0::correctEndianness(m_SubsystemID);
	memcpy(bytes + pos, &m_SubsystemIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedShortInteger m_TypeTemp;
	
	m_TypeTemp = JSIDL_v_1_0::correctEndianness(m_Type);
	memcpy(bytes + pos, &m_TypeTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	m_MACaddr.encode(bytes + pos);
	pos += m_MACaddr.getSize();
}

void GrantSubsystemID::Body::SubsystemRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SubsystemIDTemp;
	
	memcpy(&m_SubsystemIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SubsystemID = JSIDL_v_1_0::correctEndianness(m_SubsystemIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedShortInteger m_TypeTemp;
	
	memcpy(&m_TypeTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_Type = JSIDL_v_1_0::correctEndianness(m_TypeTemp);
	pos += sizeof(jUnsignedShortInteger);
	m_MACaddr.decode(bytes + pos);
	pos += m_MACaddr.getSize();
}

GrantSubsystemID::Body::SubsystemRec &GrantSubsystemID::Body::SubsystemRec::operator=(const SubsystemRec &value)
{
	m_SubsystemID = value.m_SubsystemID;
	m_Type = value.m_Type;
	m_MACaddr = value.m_MACaddr;
	
	return *this;
}

bool GrantSubsystemID::Body::SubsystemRec::operator==(const SubsystemRec &value) const
{
	if (m_SubsystemID != value.m_SubsystemID)
	{
		return false;
	}
	if (m_Type != value.m_Type)
	{
		return false;
	}
	
	if (m_MACaddr != value.m_MACaddr)
	{
		return false;
	}
	
	return true;
}

bool GrantSubsystemID::Body::SubsystemRec::operator!=(const SubsystemRec &value) const
{
	return !((*this) == value);
}

GrantSubsystemID::Body::SubsystemRec::SubsystemRec()
{
	m_parent = NULL;
	m_SubsystemID = 0;
	m_Type = 0;
	m_MACaddr.setParent(this);
	/// No Initialization of m_MACaddr necessary.
}

GrantSubsystemID::Body::SubsystemRec::SubsystemRec(const SubsystemRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubsystemID = 0;
	m_Type = 0;
	m_MACaddr.setParent(this);
	/// No Initialization of m_MACaddr necessary.
	
	/// Copy the values
	m_SubsystemID = value.m_SubsystemID;
	m_Type = value.m_Type;
	m_MACaddr = value.m_MACaddr;
}

GrantSubsystemID::Body::SubsystemRec::~SubsystemRec()
{
}

GrantSubsystemID::Body::SubsystemRec* const GrantSubsystemID::Body::getSubsystemRec()
{
	return &m_SubsystemRec;
}

int GrantSubsystemID::Body::setSubsystemRec(const SubsystemRec &value)
{
	m_SubsystemRec = value;
	setParentPresenceVector();
	return 0;
}

void GrantSubsystemID::Body::setParentPresenceVector()
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
const unsigned int GrantSubsystemID::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_SubsystemRec.getSize();
	
	return size;
}

void GrantSubsystemID::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemRec.encode(bytes + pos);
	pos += m_SubsystemRec.getSize();
}

void GrantSubsystemID::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemRec.decode(bytes + pos);
	pos += m_SubsystemRec.getSize();
}

GrantSubsystemID::Body &GrantSubsystemID::Body::operator=(const Body &value)
{
	m_SubsystemRec = value.m_SubsystemRec;
	m_SubsystemRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool GrantSubsystemID::Body::operator==(const Body &value) const
{
	if (m_SubsystemRec != value.m_SubsystemRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool GrantSubsystemID::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

GrantSubsystemID::Body::Body()
{
	m_SubsystemRec.setParent(this);
	/// No Initialization of m_SubsystemRec necessary.
}

GrantSubsystemID::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_SubsystemRec.setParent(this);
	/// No Initialization of m_SubsystemRec necessary.
	
	/// Copy the values
	m_SubsystemRec = value.m_SubsystemRec;
	m_SubsystemRec.setParent(this);
	/// This code is currently not supported
}

GrantSubsystemID::Body::~Body()
{
}

GrantSubsystemID::Body* const GrantSubsystemID::getBody()
{
	return &m_Body;
}

int GrantSubsystemID::setBody(const Body &value)
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
const unsigned int GrantSubsystemID::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void GrantSubsystemID::encode(unsigned char *bytes)
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

void GrantSubsystemID::decode(const unsigned char *bytes)
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

GrantSubsystemID &GrantSubsystemID::operator=(const GrantSubsystemID &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool GrantSubsystemID::operator==(const GrantSubsystemID &value) const
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

bool GrantSubsystemID::operator!=(const GrantSubsystemID &value) const
{
	return !((*this) == value);
}

GrantSubsystemID::GrantSubsystemID()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

GrantSubsystemID::GrantSubsystemID(const GrantSubsystemID &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

GrantSubsystemID::~GrantSubsystemID()
{
}


}
