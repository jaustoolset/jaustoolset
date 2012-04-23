#include "urn_jts_examples_SkidSteerVehicleSim_1_0/Messages/QueryWrenchEffort.h"

namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

void QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::setParent(JAUSApplicationLayerHeader* parent)
{
	m_parent = parent;
}

void QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec &QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2405;
}

QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2405;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec::~HeaderRec()
{
}

QueryWrenchEffort::JAUSApplicationLayerHeader::HeaderRec* const QueryWrenchEffort::JAUSApplicationLayerHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryWrenchEffort::JAUSApplicationLayerHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryWrenchEffort::JAUSApplicationLayerHeader::setParentPresenceVector()
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
const unsigned int QueryWrenchEffort::JAUSApplicationLayerHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryWrenchEffort::JAUSApplicationLayerHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryWrenchEffort::JAUSApplicationLayerHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryWrenchEffort::JAUSApplicationLayerHeader &QueryWrenchEffort::JAUSApplicationLayerHeader::operator=(const JAUSApplicationLayerHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryWrenchEffort::JAUSApplicationLayerHeader::operator==(const JAUSApplicationLayerHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryWrenchEffort::JAUSApplicationLayerHeader::operator!=(const JAUSApplicationLayerHeader &value) const
{
	return !((*this) == value);
}

QueryWrenchEffort::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryWrenchEffort::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryWrenchEffort::JAUSApplicationLayerHeader::~JAUSApplicationLayerHeader()
{
}

QueryWrenchEffort::JAUSApplicationLayerHeader* const QueryWrenchEffort::getJAUSApplicationLayerHeader()
{
	return &m_JAUSApplicationLayerHeader;
}

int QueryWrenchEffort::setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	m_JAUSApplicationLayerHeader = value;
	return 0;
}

void QueryWrenchEffort::Body::QueryWrenchEffortRec::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryWrenchEffort::Body::QueryWrenchEffortRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryWrenchEffort::Body::QueryWrenchEffortRec::getPresenceVector()
{
	return m_PresenceVector;
}

int QueryWrenchEffort::Body::QueryWrenchEffortRec::setPresenceVector(jUnsignedShortInteger value)
{
	m_PresenceVector = value;
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
const unsigned int QueryWrenchEffort::Body::QueryWrenchEffortRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryWrenchEffort::Body::QueryWrenchEffortRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_PresenceVectorTemp;
	
	m_PresenceVectorTemp = JSIDL_v_1_0::correctEndianness(m_PresenceVector);
	memcpy(bytes + pos, &m_PresenceVectorTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void QueryWrenchEffort::Body::QueryWrenchEffortRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_PresenceVectorTemp;
	
	memcpy(&m_PresenceVectorTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_PresenceVector = JSIDL_v_1_0::correctEndianness(m_PresenceVectorTemp);
	pos += sizeof(jUnsignedShortInteger);
}

QueryWrenchEffort::Body::QueryWrenchEffortRec &QueryWrenchEffort::Body::QueryWrenchEffortRec::operator=(const QueryWrenchEffortRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	
	return *this;
}

bool QueryWrenchEffort::Body::QueryWrenchEffortRec::operator==(const QueryWrenchEffortRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	
	return true;
}

bool QueryWrenchEffort::Body::QueryWrenchEffortRec::operator!=(const QueryWrenchEffortRec &value) const
{
	return !((*this) == value);
}

QueryWrenchEffort::Body::QueryWrenchEffortRec::QueryWrenchEffortRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
}

QueryWrenchEffort::Body::QueryWrenchEffortRec::QueryWrenchEffortRec(const QueryWrenchEffortRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
}

QueryWrenchEffort::Body::QueryWrenchEffortRec::~QueryWrenchEffortRec()
{
}

QueryWrenchEffort::Body::QueryWrenchEffortRec* const QueryWrenchEffort::Body::getQueryWrenchEffortRec()
{
	return &m_QueryWrenchEffortRec;
}

int QueryWrenchEffort::Body::setQueryWrenchEffortRec(const QueryWrenchEffortRec &value)
{
	m_QueryWrenchEffortRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryWrenchEffort::Body::setParentPresenceVector()
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
const unsigned int QueryWrenchEffort::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryWrenchEffortRec.getSize();
	
	return size;
}

void QueryWrenchEffort::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryWrenchEffortRec.encode(bytes + pos);
	pos += m_QueryWrenchEffortRec.getSize();
}

void QueryWrenchEffort::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryWrenchEffortRec.decode(bytes + pos);
	pos += m_QueryWrenchEffortRec.getSize();
}

QueryWrenchEffort::Body &QueryWrenchEffort::Body::operator=(const Body &value)
{
	m_QueryWrenchEffortRec = value.m_QueryWrenchEffortRec;
	m_QueryWrenchEffortRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryWrenchEffort::Body::operator==(const Body &value) const
{
	if (m_QueryWrenchEffortRec != value.m_QueryWrenchEffortRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryWrenchEffort::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryWrenchEffort::Body::Body()
{
	m_QueryWrenchEffortRec.setParent(this);
	/// No Initialization of m_QueryWrenchEffortRec necessary.
}

QueryWrenchEffort::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryWrenchEffortRec.setParent(this);
	/// No Initialization of m_QueryWrenchEffortRec necessary.
	
	/// Copy the values
	m_QueryWrenchEffortRec = value.m_QueryWrenchEffortRec;
	m_QueryWrenchEffortRec.setParent(this);
	/// This code is currently not supported
}

QueryWrenchEffort::Body::~Body()
{
}

QueryWrenchEffort::Body* const QueryWrenchEffort::getBody()
{
	return &m_Body;
}

int QueryWrenchEffort::setBody(const Body &value)
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
const unsigned int QueryWrenchEffort::getSize()
{
	unsigned int size = 0;
	
	size += m_JAUSApplicationLayerHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryWrenchEffort::encode(unsigned char *bytes)
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

void QueryWrenchEffort::decode(const unsigned char *bytes)
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

QueryWrenchEffort &QueryWrenchEffort::operator=(const QueryWrenchEffort &value)
{
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryWrenchEffort::operator==(const QueryWrenchEffort &value) const
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

bool QueryWrenchEffort::operator!=(const QueryWrenchEffort &value) const
{
	return !((*this) == value);
}

QueryWrenchEffort::QueryWrenchEffort()
{
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryWrenchEffort::QueryWrenchEffort(const QueryWrenchEffort &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
}

QueryWrenchEffort::~QueryWrenchEffort()
{
}


}
