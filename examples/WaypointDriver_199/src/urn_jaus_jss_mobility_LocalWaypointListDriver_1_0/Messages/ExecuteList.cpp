#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/ExecuteList.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

void ExecuteList::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ExecuteList::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ExecuteList::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ExecuteList::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ExecuteList::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ExecuteList::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ExecuteList::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ExecuteList::AppHeader::HeaderRec &ExecuteList::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ExecuteList::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ExecuteList::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ExecuteList::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x041e;
}

ExecuteList::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x041e;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ExecuteList::AppHeader::HeaderRec::~HeaderRec()
{
}

ExecuteList::AppHeader::HeaderRec* const ExecuteList::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ExecuteList::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ExecuteList::AppHeader::setParentPresenceVector()
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
const unsigned int ExecuteList::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ExecuteList::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ExecuteList::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ExecuteList::AppHeader &ExecuteList::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ExecuteList::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ExecuteList::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ExecuteList::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ExecuteList::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ExecuteList::AppHeader::~AppHeader()
{
}

ExecuteList::AppHeader* const ExecuteList::getAppHeader()
{
	return &m_AppHeader;
}

int ExecuteList::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ExecuteList::Body::ExecuteListRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ExecuteList::Body::ExecuteListRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ExecuteList::Body::ExecuteListRec::getPresenceVector()
{
	return m_PresenceVector;
}

int ExecuteList::Body::ExecuteListRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
	return 0;
}

bool ExecuteList::Body::ExecuteListRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

double ExecuteList::Body::ExecuteListRec::getSpeed()
{
	double value;
	
	double scaleFactor = ( 327.67 - 0 ) / 65535.0;
	double bias = 0;
	
	value = m_Speed * scaleFactor + bias;
	
	return value;
}

int ExecuteList::Body::ExecuteListRec::setSpeed(double value)
{
	if ((value >= 0) && (value <= 327.67))
	{
		double scaleFactor = ( 327.67 - 0 ) / 65535.0;
		double bias = 0;
		
		m_Speed= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ExecuteList::Body::ExecuteListRec::isElementUIDValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

jUnsignedShortInteger ExecuteList::Body::ExecuteListRec::getElementUID()
{
	return m_ElementUID;
}

int ExecuteList::Body::ExecuteListRec::setElementUID(jUnsignedShortInteger value)
{
	m_ElementUID = value;
	setPresenceVector(0);
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
const unsigned int ExecuteList::Body::ExecuteListRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	if (checkPresenceVector(0))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	
	return size;
}

void ExecuteList::Body::ExecuteListRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_PresenceVectorTemp;
	
	m_PresenceVectorTemp = JSIDL_v_1_0::correctEndianness(m_PresenceVector);
	memcpy(bytes + pos, &m_PresenceVectorTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedShortInteger m_SpeedTemp;
	
	m_SpeedTemp = JSIDL_v_1_0::correctEndianness(m_Speed);
	memcpy(bytes + pos, &m_SpeedTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	if (checkPresenceVector(0))
	{
		jUnsignedShortInteger m_ElementUIDTemp;
		
		m_ElementUIDTemp = JSIDL_v_1_0::correctEndianness(m_ElementUID);
		memcpy(bytes + pos, &m_ElementUIDTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
}

void ExecuteList::Body::ExecuteListRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_PresenceVectorTemp;
	
	memcpy(&m_PresenceVectorTemp, bytes + pos, sizeof(jUnsignedByte));
	m_PresenceVector = JSIDL_v_1_0::correctEndianness(m_PresenceVectorTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedShortInteger m_SpeedTemp;
	
	memcpy(&m_SpeedTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_Speed = JSIDL_v_1_0::correctEndianness(m_SpeedTemp);
	pos += sizeof(jUnsignedShortInteger);
	if (checkPresenceVector(0))
	{
		jUnsignedShortInteger m_ElementUIDTemp;
		
		memcpy(&m_ElementUIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_ElementUID = JSIDL_v_1_0::correctEndianness(m_ElementUIDTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
}

ExecuteList::Body::ExecuteListRec &ExecuteList::Body::ExecuteListRec::operator=(const ExecuteListRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_Speed = value.m_Speed;
	m_ElementUID = value.m_ElementUID;
	
	return *this;
}

bool ExecuteList::Body::ExecuteListRec::operator==(const ExecuteListRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	if (m_Speed != value.m_Speed)
	{
		return false;
	}
	if (m_ElementUID != value.m_ElementUID)
	{
		return false;
	}
	
	return true;
}

bool ExecuteList::Body::ExecuteListRec::operator!=(const ExecuteListRec &value) const
{
	return !((*this) == value);
}

ExecuteList::Body::ExecuteListRec::ExecuteListRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_Speed = 0;
	m_ElementUID = 0;
}

ExecuteList::Body::ExecuteListRec::ExecuteListRec(const ExecuteListRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	m_Speed = 0;
	m_ElementUID = 0;
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_Speed = value.m_Speed;
	m_ElementUID = value.m_ElementUID;
}

ExecuteList::Body::ExecuteListRec::~ExecuteListRec()
{
}

ExecuteList::Body::ExecuteListRec* const ExecuteList::Body::getExecuteListRec()
{
	return &m_ExecuteListRec;
}

int ExecuteList::Body::setExecuteListRec(const ExecuteListRec &value)
{
	m_ExecuteListRec = value;
	setParentPresenceVector();
	return 0;
}

void ExecuteList::Body::setParentPresenceVector()
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
const unsigned int ExecuteList::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ExecuteListRec.getSize();
	
	return size;
}

void ExecuteList::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ExecuteListRec.encode(bytes + pos);
	pos += m_ExecuteListRec.getSize();
}

void ExecuteList::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ExecuteListRec.decode(bytes + pos);
	pos += m_ExecuteListRec.getSize();
}

ExecuteList::Body &ExecuteList::Body::operator=(const Body &value)
{
	m_ExecuteListRec = value.m_ExecuteListRec;
	m_ExecuteListRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ExecuteList::Body::operator==(const Body &value) const
{
	if (m_ExecuteListRec != value.m_ExecuteListRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ExecuteList::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ExecuteList::Body::Body()
{
	m_ExecuteListRec.setParent(this);
	/// No Initialization of m_ExecuteListRec necessary.
}

ExecuteList::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ExecuteListRec.setParent(this);
	/// No Initialization of m_ExecuteListRec necessary.
	
	/// Copy the values
	m_ExecuteListRec = value.m_ExecuteListRec;
	m_ExecuteListRec.setParent(this);
	/// This code is currently not supported
}

ExecuteList::Body::~Body()
{
}

ExecuteList::Body* const ExecuteList::getBody()
{
	return &m_Body;
}

int ExecuteList::setBody(const Body &value)
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
const unsigned int ExecuteList::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ExecuteList::encode(unsigned char *bytes)
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

void ExecuteList::decode(const unsigned char *bytes)
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

ExecuteList &ExecuteList::operator=(const ExecuteList &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ExecuteList::operator==(const ExecuteList &value) const
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

bool ExecuteList::operator!=(const ExecuteList &value) const
{
	return !((*this) == value);
}

ExecuteList::ExecuteList()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ExecuteList::ExecuteList(const ExecuteList &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ExecuteList::~ExecuteList()
{
}


}
