#include "urn_jaus_jss_iop_NodeIDAllocator_1_1/Messages/RequestNodeID.h"

namespace urn_jaus_jss_iop_NodeIDAllocator_1_1
{

void RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::setParent(JAUSApplicationLayerHeader* parent)
{
	m_parent = parent;
}

void RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::encode(unsigned char *bytes)
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

void RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::decode(const unsigned char *bytes)
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

RequestNodeID::JAUSApplicationLayerHeader::HeaderRec &RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0xdb03;
}

RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0xdb03;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

RequestNodeID::JAUSApplicationLayerHeader::HeaderRec::~HeaderRec()
{
}

RequestNodeID::JAUSApplicationLayerHeader::HeaderRec* const RequestNodeID::JAUSApplicationLayerHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int RequestNodeID::JAUSApplicationLayerHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void RequestNodeID::JAUSApplicationLayerHeader::setParentPresenceVector()
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
const unsigned int RequestNodeID::JAUSApplicationLayerHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void RequestNodeID::JAUSApplicationLayerHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void RequestNodeID::JAUSApplicationLayerHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

RequestNodeID::JAUSApplicationLayerHeader &RequestNodeID::JAUSApplicationLayerHeader::operator=(const JAUSApplicationLayerHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool RequestNodeID::JAUSApplicationLayerHeader::operator==(const JAUSApplicationLayerHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool RequestNodeID::JAUSApplicationLayerHeader::operator!=(const JAUSApplicationLayerHeader &value) const
{
	return !((*this) == value);
}

RequestNodeID::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

RequestNodeID::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

RequestNodeID::JAUSApplicationLayerHeader::~JAUSApplicationLayerHeader()
{
}

RequestNodeID::JAUSApplicationLayerHeader* const RequestNodeID::getJAUSApplicationLayerHeader()
{
	return &m_JAUSApplicationLayerHeader;
}

int RequestNodeID::setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	m_JAUSApplicationLayerHeader = value;
	return 0;
}

void RequestNodeID::Body::ReviewNodeIDRec::setParent(Body* parent)
{
	m_parent = parent;
}

void RequestNodeID::Body::ReviewNodeIDRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void RequestNodeID::Body::ReviewNodeIDRec::RequesterID::setParent(ReviewNodeIDRec* parent)
{
	m_parent = parent;
}

void RequestNodeID::Body::ReviewNodeIDRec::RequesterID::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const unsigned int RequestNodeID::Body::ReviewNodeIDRec::RequesterID::getOneDimensionArraySize() const
{
	return m_OneDimensionArraySize;
}

jUnsignedByte RequestNodeID::Body::ReviewNodeIDRec::RequesterID::getRequesterIDArrayField(unsigned int OneDimensionArray)
{
	unsigned int index = OneDimensionArray;
	
	return m_RequesterIDArrayField[index];
}

int RequestNodeID::Body::ReviewNodeIDRec::RequesterID::setRequesterIDArrayField(unsigned int OneDimensionArray, jUnsignedByte value)
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
const unsigned int RequestNodeID::Body::ReviewNodeIDRec::RequesterID::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte) * 7;
	
	return size;
}

void RequestNodeID::Body::ReviewNodeIDRec::RequesterID::encode(unsigned char *bytes)
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

void RequestNodeID::Body::ReviewNodeIDRec::RequesterID::decode(const unsigned char *bytes)
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

RequestNodeID::Body::ReviewNodeIDRec::RequesterID &RequestNodeID::Body::ReviewNodeIDRec::RequesterID::operator=(const RequesterID &value)
{
	// not yet implemented
	memcpy(m_RequesterIDArrayField, value.m_RequesterIDArrayField, sizeof(jUnsignedByte) * 7);
	
	return *this;
}

bool RequestNodeID::Body::ReviewNodeIDRec::RequesterID::operator==(const RequesterID &value) const
{
	// not yet implemented
	if (memcmp(m_RequesterIDArrayField, value.m_RequesterIDArrayField, sizeof(jUnsignedByte) * 7) != 0)
	{
		return false;
	}
	
	return true;
}

bool RequestNodeID::Body::ReviewNodeIDRec::RequesterID::operator!=(const RequesterID &value) const
{
	return !((*this) == value);
}

RequestNodeID::Body::ReviewNodeIDRec::RequesterID::RequesterID()
{
	m_parent = NULL;
	m_OneDimensionArraySize = 7;
	memset( m_RequesterIDArrayField, 0, sizeof(jUnsignedByte) * 7);
}

RequestNodeID::Body::ReviewNodeIDRec::RequesterID::RequesterID(const RequesterID &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_OneDimensionArraySize = 7;
	memset( m_RequesterIDArrayField, 0, sizeof(jUnsignedByte) * 7);
	
	/// Copy the values
	// not yet implemented
	memcpy(m_RequesterIDArrayField, value.m_RequesterIDArrayField, sizeof(jUnsignedByte) * 7);
}

RequestNodeID::Body::ReviewNodeIDRec::RequesterID::~RequesterID()
{
}

RequestNodeID::Body::ReviewNodeIDRec::RequesterID* const RequestNodeID::Body::ReviewNodeIDRec::getRequesterID()
{
	return &m_RequesterID;
}

int RequestNodeID::Body::ReviewNodeIDRec::setRequesterID(const RequesterID &value)
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
const unsigned int RequestNodeID::Body::ReviewNodeIDRec::getSize()
{
	unsigned int size = 0;
	
	size += m_RequesterID.getSize();
	
	return size;
}

void RequestNodeID::Body::ReviewNodeIDRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequesterID.encode(bytes + pos);
	pos += m_RequesterID.getSize();
}

void RequestNodeID::Body::ReviewNodeIDRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequesterID.decode(bytes + pos);
	pos += m_RequesterID.getSize();
}

RequestNodeID::Body::ReviewNodeIDRec &RequestNodeID::Body::ReviewNodeIDRec::operator=(const ReviewNodeIDRec &value)
{
	m_RequesterID = value.m_RequesterID;
	
	return *this;
}

bool RequestNodeID::Body::ReviewNodeIDRec::operator==(const ReviewNodeIDRec &value) const
{
	if (m_RequesterID != value.m_RequesterID)
	{
		return false;
	}
	
	return true;
}

bool RequestNodeID::Body::ReviewNodeIDRec::operator!=(const ReviewNodeIDRec &value) const
{
	return !((*this) == value);
}

RequestNodeID::Body::ReviewNodeIDRec::ReviewNodeIDRec()
{
	m_parent = NULL;
	m_RequesterID.setParent(this);
	/// No Initialization of m_RequesterID necessary.
}

RequestNodeID::Body::ReviewNodeIDRec::ReviewNodeIDRec(const ReviewNodeIDRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequesterID.setParent(this);
	/// No Initialization of m_RequesterID necessary.
	
	/// Copy the values
	m_RequesterID = value.m_RequesterID;
}

RequestNodeID::Body::ReviewNodeIDRec::~ReviewNodeIDRec()
{
}

RequestNodeID::Body::ReviewNodeIDRec* const RequestNodeID::Body::getReviewNodeIDRec()
{
	return &m_ReviewNodeIDRec;
}

int RequestNodeID::Body::setReviewNodeIDRec(const ReviewNodeIDRec &value)
{
	m_ReviewNodeIDRec = value;
	setParentPresenceVector();
	return 0;
}

void RequestNodeID::Body::setParentPresenceVector()
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
const unsigned int RequestNodeID::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ReviewNodeIDRec.getSize();
	
	return size;
}

void RequestNodeID::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReviewNodeIDRec.encode(bytes + pos);
	pos += m_ReviewNodeIDRec.getSize();
}

void RequestNodeID::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReviewNodeIDRec.decode(bytes + pos);
	pos += m_ReviewNodeIDRec.getSize();
}

RequestNodeID::Body &RequestNodeID::Body::operator=(const Body &value)
{
	m_ReviewNodeIDRec = value.m_ReviewNodeIDRec;
	m_ReviewNodeIDRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool RequestNodeID::Body::operator==(const Body &value) const
{
	if (m_ReviewNodeIDRec != value.m_ReviewNodeIDRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool RequestNodeID::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

RequestNodeID::Body::Body()
{
	m_ReviewNodeIDRec.setParent(this);
	/// No Initialization of m_ReviewNodeIDRec necessary.
}

RequestNodeID::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ReviewNodeIDRec.setParent(this);
	/// No Initialization of m_ReviewNodeIDRec necessary.
	
	/// Copy the values
	m_ReviewNodeIDRec = value.m_ReviewNodeIDRec;
	m_ReviewNodeIDRec.setParent(this);
	/// This code is currently not supported
}

RequestNodeID::Body::~Body()
{
}

RequestNodeID::Body* const RequestNodeID::getBody()
{
	return &m_Body;
}

int RequestNodeID::setBody(const Body &value)
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
const unsigned int RequestNodeID::getSize()
{
	unsigned int size = 0;
	
	size += m_JAUSApplicationLayerHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void RequestNodeID::encode(unsigned char *bytes)
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

void RequestNodeID::decode(const unsigned char *bytes)
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

RequestNodeID &RequestNodeID::operator=(const RequestNodeID &value)
{
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool RequestNodeID::operator==(const RequestNodeID &value) const
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

bool RequestNodeID::operator!=(const RequestNodeID &value) const
{
	return !((*this) == value);
}

RequestNodeID::RequestNodeID()
{
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

RequestNodeID::RequestNodeID(const RequestNodeID &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
}

RequestNodeID::~RequestNodeID()
{
}


}
