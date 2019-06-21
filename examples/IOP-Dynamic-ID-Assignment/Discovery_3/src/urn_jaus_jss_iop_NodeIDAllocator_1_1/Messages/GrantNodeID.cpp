#include "urn_jaus_jss_iop_NodeIDAllocator_1_1/Messages/GrantNodeID.h"

namespace urn_jaus_jss_iop_NodeIDAllocator_1_1
{

void GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::setParent(JAUSApplicationLayerHeader* parent)
{
	m_parent = parent;
}

void GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::encode(unsigned char *bytes)
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

void GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::decode(const unsigned char *bytes)
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

GrantNodeID::JAUSApplicationLayerHeader::HeaderRec &GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0xfb03;
}

GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0xfb03;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

GrantNodeID::JAUSApplicationLayerHeader::HeaderRec::~HeaderRec()
{
}

GrantNodeID::JAUSApplicationLayerHeader::HeaderRec* const GrantNodeID::JAUSApplicationLayerHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int GrantNodeID::JAUSApplicationLayerHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void GrantNodeID::JAUSApplicationLayerHeader::setParentPresenceVector()
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
const unsigned int GrantNodeID::JAUSApplicationLayerHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void GrantNodeID::JAUSApplicationLayerHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void GrantNodeID::JAUSApplicationLayerHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

GrantNodeID::JAUSApplicationLayerHeader &GrantNodeID::JAUSApplicationLayerHeader::operator=(const JAUSApplicationLayerHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool GrantNodeID::JAUSApplicationLayerHeader::operator==(const JAUSApplicationLayerHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool GrantNodeID::JAUSApplicationLayerHeader::operator!=(const JAUSApplicationLayerHeader &value) const
{
	return !((*this) == value);
}

GrantNodeID::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

GrantNodeID::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

GrantNodeID::JAUSApplicationLayerHeader::~JAUSApplicationLayerHeader()
{
}

GrantNodeID::JAUSApplicationLayerHeader* const GrantNodeID::getJAUSApplicationLayerHeader()
{
	return &m_JAUSApplicationLayerHeader;
}

int GrantNodeID::setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	m_JAUSApplicationLayerHeader = value;
	return 0;
}

void GrantNodeID::Body::GrantNodeIDRec::setParent(Body* parent)
{
	m_parent = parent;
}

void GrantNodeID::Body::GrantNodeIDRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte GrantNodeID::Body::GrantNodeIDRec::getNodeID()
{
	return m_NodeID;
}

int GrantNodeID::Body::GrantNodeIDRec::setNodeID(jUnsignedByte value)
{
	m_NodeID = value;
	setParentPresenceVector();
	return 0;
}

void GrantNodeID::Body::GrantNodeIDRec::RequesterID::setParent(GrantNodeIDRec* parent)
{
	m_parent = parent;
}

void GrantNodeID::Body::GrantNodeIDRec::RequesterID::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const unsigned int GrantNodeID::Body::GrantNodeIDRec::RequesterID::getOneDimensionArraySize() const
{
	return m_OneDimensionArraySize;
}

jUnsignedByte GrantNodeID::Body::GrantNodeIDRec::RequesterID::getRequesterIDArrayField(unsigned int OneDimensionArray)
{
	unsigned int index = OneDimensionArray;
	
	return m_RequesterIDArrayField[index];
}

int GrantNodeID::Body::GrantNodeIDRec::RequesterID::setRequesterIDArrayField(unsigned int OneDimensionArray, jUnsignedByte value)
{
	unsigned int index = OneDimensionArray;
	
	m_RequesterIDArrayField[index] = value;
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
const unsigned int GrantNodeID::Body::GrantNodeIDRec::RequesterID::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte) * 7;
	
	return size;
}

void GrantNodeID::Body::GrantNodeIDRec::RequesterID::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_RequesterIDArrayFieldTemp;
	
	for (unsigned int i = 0; i < 7; i++)
	{
		m_RequesterIDArrayFieldTemp = JSIDL_v_1_0::correctEndianness(m_RequesterIDArrayField[i]);
		memcpy(bytes + pos, &m_RequesterIDArrayFieldTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
}

void GrantNodeID::Body::GrantNodeIDRec::RequesterID::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_RequesterIDArrayFieldTemp;
	
	for (unsigned int i = 0; i < 7; i++)
	{
		memcpy(&m_RequesterIDArrayFieldTemp, bytes + pos, sizeof(jUnsignedByte));
		m_RequesterIDArrayField[i] = JSIDL_v_1_0::correctEndianness(m_RequesterIDArrayFieldTemp);
		pos += sizeof(jUnsignedByte);
	}
}

GrantNodeID::Body::GrantNodeIDRec::RequesterID &GrantNodeID::Body::GrantNodeIDRec::RequesterID::operator=(const RequesterID &value)
{
	// not yet implemented
	memcpy(m_RequesterIDArrayField, value.m_RequesterIDArrayField, sizeof(jUnsignedByte) * 7);
	
	return *this;
}

bool GrantNodeID::Body::GrantNodeIDRec::RequesterID::operator==(const RequesterID &value) const
{
	// not yet implemented
	if (memcmp(m_RequesterIDArrayField, value.m_RequesterIDArrayField, sizeof(jUnsignedByte) * 7) != 0)
	{
		return false;
	}
	
	return true;
}

bool GrantNodeID::Body::GrantNodeIDRec::RequesterID::operator!=(const RequesterID &value) const
{
	return !((*this) == value);
}

GrantNodeID::Body::GrantNodeIDRec::RequesterID::RequesterID()
{
	m_parent = NULL;
	m_OneDimensionArraySize = 7;
	memset( m_RequesterIDArrayField, 0, sizeof(jUnsignedByte) * 7);
}

GrantNodeID::Body::GrantNodeIDRec::RequesterID::RequesterID(const RequesterID &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_OneDimensionArraySize = 7;
	memset( m_RequesterIDArrayField, 0, sizeof(jUnsignedByte) * 7);
	
	/// Copy the values
	// not yet implemented
	memcpy(m_RequesterIDArrayField, value.m_RequesterIDArrayField, sizeof(jUnsignedByte) * 7);
}

GrantNodeID::Body::GrantNodeIDRec::RequesterID::~RequesterID()
{
}

GrantNodeID::Body::GrantNodeIDRec::RequesterID* const GrantNodeID::Body::GrantNodeIDRec::getRequesterID()
{
	return &m_RequesterID;
}

int GrantNodeID::Body::GrantNodeIDRec::setRequesterID(const RequesterID &value)
{
	m_RequesterID = value;
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
const unsigned int GrantNodeID::Body::GrantNodeIDRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += m_RequesterID.getSize();
	
	return size;
}

void GrantNodeID::Body::GrantNodeIDRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_NodeIDTemp;
	
	m_NodeIDTemp = JSIDL_v_1_0::correctEndianness(m_NodeID);
	memcpy(bytes + pos, &m_NodeIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	m_RequesterID.encode(bytes + pos);
	pos += m_RequesterID.getSize();
}

void GrantNodeID::Body::GrantNodeIDRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_NodeIDTemp;
	
	memcpy(&m_NodeIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_NodeID = JSIDL_v_1_0::correctEndianness(m_NodeIDTemp);
	pos += sizeof(jUnsignedByte);
	m_RequesterID.decode(bytes + pos);
	pos += m_RequesterID.getSize();
}

GrantNodeID::Body::GrantNodeIDRec &GrantNodeID::Body::GrantNodeIDRec::operator=(const GrantNodeIDRec &value)
{
	m_NodeID = value.m_NodeID;
	m_RequesterID = value.m_RequesterID;
	
	return *this;
}

bool GrantNodeID::Body::GrantNodeIDRec::operator==(const GrantNodeIDRec &value) const
{
	if (m_NodeID != value.m_NodeID)
	{
		return false;
	}
	
	if (m_RequesterID != value.m_RequesterID)
	{
		return false;
	}
	
	return true;
}

bool GrantNodeID::Body::GrantNodeIDRec::operator!=(const GrantNodeIDRec &value) const
{
	return !((*this) == value);
}

GrantNodeID::Body::GrantNodeIDRec::GrantNodeIDRec()
{
	m_parent = NULL;
	m_NodeID = 0;
	m_RequesterID.setParent(this);
	/// No Initialization of m_RequesterID necessary.
}

GrantNodeID::Body::GrantNodeIDRec::GrantNodeIDRec(const GrantNodeIDRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_NodeID = 0;
	m_RequesterID.setParent(this);
	/// No Initialization of m_RequesterID necessary.
	
	/// Copy the values
	m_NodeID = value.m_NodeID;
	m_RequesterID = value.m_RequesterID;
}

GrantNodeID::Body::GrantNodeIDRec::~GrantNodeIDRec()
{
}

GrantNodeID::Body::GrantNodeIDRec* const GrantNodeID::Body::getGrantNodeIDRec()
{
	return &m_GrantNodeIDRec;
}

int GrantNodeID::Body::setGrantNodeIDRec(const GrantNodeIDRec &value)
{
	m_GrantNodeIDRec = value;
	setParentPresenceVector();
	return 0;
}

void GrantNodeID::Body::setParentPresenceVector()
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
const unsigned int GrantNodeID::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_GrantNodeIDRec.getSize();
	
	return size;
}

void GrantNodeID::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_GrantNodeIDRec.encode(bytes + pos);
	pos += m_GrantNodeIDRec.getSize();
}

void GrantNodeID::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_GrantNodeIDRec.decode(bytes + pos);
	pos += m_GrantNodeIDRec.getSize();
}

GrantNodeID::Body &GrantNodeID::Body::operator=(const Body &value)
{
	m_GrantNodeIDRec = value.m_GrantNodeIDRec;
	m_GrantNodeIDRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool GrantNodeID::Body::operator==(const Body &value) const
{
	if (m_GrantNodeIDRec != value.m_GrantNodeIDRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool GrantNodeID::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

GrantNodeID::Body::Body()
{
	m_GrantNodeIDRec.setParent(this);
	/// No Initialization of m_GrantNodeIDRec necessary.
}

GrantNodeID::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_GrantNodeIDRec.setParent(this);
	/// No Initialization of m_GrantNodeIDRec necessary.
	
	/// Copy the values
	m_GrantNodeIDRec = value.m_GrantNodeIDRec;
	m_GrantNodeIDRec.setParent(this);
	/// This code is currently not supported
}

GrantNodeID::Body::~Body()
{
}

GrantNodeID::Body* const GrantNodeID::getBody()
{
	return &m_Body;
}

int GrantNodeID::setBody(const Body &value)
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
const unsigned int GrantNodeID::getSize()
{
	unsigned int size = 0;
	
	size += m_JAUSApplicationLayerHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void GrantNodeID::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_JAUSApplicationLayerHeader.encode(bytes + pos);
	pos += m_JAUSApplicationLayerHeader.getSize();
	m_Body.encode(bytes + pos);
	pos += m_Body.getSize();
}

void GrantNodeID::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_JAUSApplicationLayerHeader.decode(bytes + pos);
	pos += m_JAUSApplicationLayerHeader.getSize();
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

GrantNodeID &GrantNodeID::operator=(const GrantNodeID &value)
{
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool GrantNodeID::operator==(const GrantNodeID &value) const
{
	if (m_JAUSApplicationLayerHeader != value.m_JAUSApplicationLayerHeader)
	{
		return false;
	}
	if (m_Body != value.m_Body)
	{
		return false;
	}
	
	return true;
}

bool GrantNodeID::operator!=(const GrantNodeID &value) const
{
	return !((*this) == value);
}

GrantNodeID::GrantNodeID()
{
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

GrantNodeID::GrantNodeID(const GrantNodeID &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
}

GrantNodeID::~GrantNodeID()
{
}


}
