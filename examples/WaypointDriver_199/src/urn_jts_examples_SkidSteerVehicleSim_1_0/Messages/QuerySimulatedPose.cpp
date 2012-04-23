#include "urn_jts_examples_SkidSteerVehicleSim_1_0/Messages/QuerySimulatedPose.h"

namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

void QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::setParent(JAUSApplicationLayerHeader* parent)
{
	m_parent = parent;
}

void QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::encode(unsigned char *bytes)
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

void QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::decode(const unsigned char *bytes)
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

QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec &QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2d03;
}

QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2d03;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec::~HeaderRec()
{
}

QuerySimulatedPose::JAUSApplicationLayerHeader::HeaderRec* const QuerySimulatedPose::JAUSApplicationLayerHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QuerySimulatedPose::JAUSApplicationLayerHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QuerySimulatedPose::JAUSApplicationLayerHeader::setParentPresenceVector()
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
const unsigned int QuerySimulatedPose::JAUSApplicationLayerHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QuerySimulatedPose::JAUSApplicationLayerHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QuerySimulatedPose::JAUSApplicationLayerHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QuerySimulatedPose::JAUSApplicationLayerHeader &QuerySimulatedPose::JAUSApplicationLayerHeader::operator=(const JAUSApplicationLayerHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QuerySimulatedPose::JAUSApplicationLayerHeader::operator==(const JAUSApplicationLayerHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QuerySimulatedPose::JAUSApplicationLayerHeader::operator!=(const JAUSApplicationLayerHeader &value) const
{
	return !((*this) == value);
}

QuerySimulatedPose::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QuerySimulatedPose::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QuerySimulatedPose::JAUSApplicationLayerHeader::~JAUSApplicationLayerHeader()
{
}

QuerySimulatedPose::JAUSApplicationLayerHeader* const QuerySimulatedPose::getJAUSApplicationLayerHeader()
{
	return &m_JAUSApplicationLayerHeader;
}

int QuerySimulatedPose::setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	m_JAUSApplicationLayerHeader = value;
	return 0;
}

void QuerySimulatedPose::Body::QuerySimulatedPoseRec::setParent(Body* parent)
{
	m_parent = parent;
}

void QuerySimulatedPose::Body::QuerySimulatedPoseRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QuerySimulatedPose::Body::QuerySimulatedPoseRec::getPresenceVector()
{
	return m_PresenceVector;
}

int QuerySimulatedPose::Body::QuerySimulatedPoseRec::setPresenceVector(jUnsignedShortInteger value)
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
const unsigned int QuerySimulatedPose::Body::QuerySimulatedPoseRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QuerySimulatedPose::Body::QuerySimulatedPoseRec::encode(unsigned char *bytes)
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

void QuerySimulatedPose::Body::QuerySimulatedPoseRec::decode(const unsigned char *bytes)
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

QuerySimulatedPose::Body::QuerySimulatedPoseRec &QuerySimulatedPose::Body::QuerySimulatedPoseRec::operator=(const QuerySimulatedPoseRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	
	return *this;
}

bool QuerySimulatedPose::Body::QuerySimulatedPoseRec::operator==(const QuerySimulatedPoseRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	
	return true;
}

bool QuerySimulatedPose::Body::QuerySimulatedPoseRec::operator!=(const QuerySimulatedPoseRec &value) const
{
	return !((*this) == value);
}

QuerySimulatedPose::Body::QuerySimulatedPoseRec::QuerySimulatedPoseRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
}

QuerySimulatedPose::Body::QuerySimulatedPoseRec::QuerySimulatedPoseRec(const QuerySimulatedPoseRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
}

QuerySimulatedPose::Body::QuerySimulatedPoseRec::~QuerySimulatedPoseRec()
{
}

QuerySimulatedPose::Body::QuerySimulatedPoseRec* const QuerySimulatedPose::Body::getQuerySimulatedPoseRec()
{
	return &m_QuerySimulatedPoseRec;
}

int QuerySimulatedPose::Body::setQuerySimulatedPoseRec(const QuerySimulatedPoseRec &value)
{
	m_QuerySimulatedPoseRec = value;
	setParentPresenceVector();
	return 0;
}

void QuerySimulatedPose::Body::setParentPresenceVector()
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
const unsigned int QuerySimulatedPose::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QuerySimulatedPoseRec.getSize();
	
	return size;
}

void QuerySimulatedPose::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QuerySimulatedPoseRec.encode(bytes + pos);
	pos += m_QuerySimulatedPoseRec.getSize();
}

void QuerySimulatedPose::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QuerySimulatedPoseRec.decode(bytes + pos);
	pos += m_QuerySimulatedPoseRec.getSize();
}

QuerySimulatedPose::Body &QuerySimulatedPose::Body::operator=(const Body &value)
{
	m_QuerySimulatedPoseRec = value.m_QuerySimulatedPoseRec;
	m_QuerySimulatedPoseRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QuerySimulatedPose::Body::operator==(const Body &value) const
{
	if (m_QuerySimulatedPoseRec != value.m_QuerySimulatedPoseRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QuerySimulatedPose::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QuerySimulatedPose::Body::Body()
{
	m_QuerySimulatedPoseRec.setParent(this);
	/// No Initialization of m_QuerySimulatedPoseRec necessary.
}

QuerySimulatedPose::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QuerySimulatedPoseRec.setParent(this);
	/// No Initialization of m_QuerySimulatedPoseRec necessary.
	
	/// Copy the values
	m_QuerySimulatedPoseRec = value.m_QuerySimulatedPoseRec;
	m_QuerySimulatedPoseRec.setParent(this);
	/// This code is currently not supported
}

QuerySimulatedPose::Body::~Body()
{
}

QuerySimulatedPose::Body* const QuerySimulatedPose::getBody()
{
	return &m_Body;
}

int QuerySimulatedPose::setBody(const Body &value)
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
const unsigned int QuerySimulatedPose::getSize()
{
	unsigned int size = 0;
	
	size += m_JAUSApplicationLayerHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QuerySimulatedPose::encode(unsigned char *bytes)
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

void QuerySimulatedPose::decode(const unsigned char *bytes)
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

QuerySimulatedPose &QuerySimulatedPose::operator=(const QuerySimulatedPose &value)
{
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QuerySimulatedPose::operator==(const QuerySimulatedPose &value) const
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

bool QuerySimulatedPose::operator!=(const QuerySimulatedPose &value) const
{
	return !((*this) == value);
}

QuerySimulatedPose::QuerySimulatedPose()
{
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QuerySimulatedPose::QuerySimulatedPose(const QuerySimulatedPose &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
}

QuerySimulatedPose::~QuerySimulatedPose()
{
}


}
