#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/SetStillImageSensorConfiguration.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void SetStillImageSensorConfiguration::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void SetStillImageSensorConfiguration::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger SetStillImageSensorConfiguration::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int SetStillImageSensorConfiguration::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int SetStillImageSensorConfiguration::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void SetStillImageSensorConfiguration::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void SetStillImageSensorConfiguration::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

SetStillImageSensorConfiguration::AppHeader::HeaderRec &SetStillImageSensorConfiguration::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool SetStillImageSensorConfiguration::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool SetStillImageSensorConfiguration::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

SetStillImageSensorConfiguration::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x0807;
}

SetStillImageSensorConfiguration::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x0807;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

SetStillImageSensorConfiguration::AppHeader::HeaderRec::~HeaderRec()
{
}

SetStillImageSensorConfiguration::AppHeader::HeaderRec* const SetStillImageSensorConfiguration::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int SetStillImageSensorConfiguration::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void SetStillImageSensorConfiguration::AppHeader::setParentPresenceVector()
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
const unsigned int SetStillImageSensorConfiguration::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void SetStillImageSensorConfiguration::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void SetStillImageSensorConfiguration::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

SetStillImageSensorConfiguration::AppHeader &SetStillImageSensorConfiguration::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool SetStillImageSensorConfiguration::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool SetStillImageSensorConfiguration::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

SetStillImageSensorConfiguration::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

SetStillImageSensorConfiguration::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

SetStillImageSensorConfiguration::AppHeader::~AppHeader()
{
}

SetStillImageSensorConfiguration::AppHeader* const SetStillImageSensorConfiguration::getAppHeader()
{
	return &m_AppHeader;
}

int SetStillImageSensorConfiguration::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::setParent(Body* parent)
{
	m_parent = parent;
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::setParent(StillImageSensorConfigurationSequence* parent)
{
	m_parent = parent;
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::getRequestID()
{
	return m_RequestID;
}

int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::setRequestID(jUnsignedByte value)
{
	m_RequestID = value;
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
const unsigned int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_RequestIDTemp;
	
	m_RequestIDTemp = JSIDL_v_1_0::correctEndianness(m_RequestID);
	memcpy(bytes + pos, &m_RequestIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_RequestIDTemp;
	
	memcpy(&m_RequestIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_RequestID = JSIDL_v_1_0::correctEndianness(m_RequestIDTemp);
	pos += sizeof(jUnsignedByte);
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec &SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::operator=(const RequestIdRec &value)
{
	m_RequestID = value.m_RequestID;
	
	return *this;
}

bool SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::operator==(const RequestIdRec &value) const
{
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	
	return true;
}

bool SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::operator!=(const RequestIdRec &value) const
{
	return !((*this) == value);
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::RequestIdRec()
{
	m_parent = NULL;
	m_RequestID = 0;
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::RequestIdRec(const RequestIdRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec::~RequestIdRec()
{
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::RequestIdRec* const SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::getRequestIdRec()
{
	return &m_RequestIdRec;
}

int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::setRequestIdRec(const RequestIdRec &value)
{
	m_RequestIdRec = value;
	setParentPresenceVector();
	return 0;
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::setParent(StillImageSensorConfigurationSequence* parent)
{
	m_parent = parent;
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::setParent(StillImageSensorList* parent)
{
	m_parent = parent;
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::getPresenceVector()
{
	return m_PresenceVector;
}

int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
	return 0;
}

bool SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

jUnsignedShortInteger SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::getSensorID()
{
	return m_SensorID;
}

int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

bool SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::isFrameSizeValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

jUnsignedByte SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::getFrameSize()
{
	return m_FrameSize;
}

int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::setFrameSize(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6) || (value == 7) || (value == 8) || (value == 9) || (value == 10) || (value == 11) || (value == 12) || (value == 13) || (value == 14) || (value == 15) || (value == 16) || (value == 17) || (value == 18) || (value == 19) || (value == 20) || (value == 21) || (value == 22) || (value == 23) || (value == 24) || (value == 25) || (value == 26) || (value == 27) || (value == 28))
	{
		m_FrameSize = value;
		setPresenceVector(0);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::isStillImageFormatValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

jUnsignedByte SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::getStillImageFormat()
{
	return m_StillImageFormat;
}

int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::setStillImageFormat(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6) || (value == 7) || (value == 8) || (value == 9) || (value == 10))
	{
		m_StillImageFormat = value;
		setPresenceVector(1);
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
const unsigned int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	if (checkPresenceVector(0))
	{
		size += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(1))
	{
		size += sizeof(jUnsignedByte);
	}
	
	return size;
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::encode(unsigned char *bytes)
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
	jUnsignedShortInteger m_SensorIDTemp;
	
	m_SensorIDTemp = JSIDL_v_1_0::correctEndianness(m_SensorID);
	memcpy(bytes + pos, &m_SensorIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	if (checkPresenceVector(0))
	{
		jUnsignedByte m_FrameSizeTemp;
		
		m_FrameSizeTemp = JSIDL_v_1_0::correctEndianness(m_FrameSize);
		memcpy(bytes + pos, &m_FrameSizeTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(1))
	{
		jUnsignedByte m_StillImageFormatTemp;
		
		m_StillImageFormatTemp = JSIDL_v_1_0::correctEndianness(m_StillImageFormat);
		memcpy(bytes + pos, &m_StillImageFormatTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::decode(const unsigned char *bytes)
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
	jUnsignedShortInteger m_SensorIDTemp;
	
	memcpy(&m_SensorIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SensorID = JSIDL_v_1_0::correctEndianness(m_SensorIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	if (checkPresenceVector(0))
	{
		jUnsignedByte m_FrameSizeTemp;
		
		memcpy(&m_FrameSizeTemp, bytes + pos, sizeof(jUnsignedByte));
		m_FrameSize = JSIDL_v_1_0::correctEndianness(m_FrameSizeTemp);
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(1))
	{
		jUnsignedByte m_StillImageFormatTemp;
		
		memcpy(&m_StillImageFormatTemp, bytes + pos, sizeof(jUnsignedByte));
		m_StillImageFormat = JSIDL_v_1_0::correctEndianness(m_StillImageFormatTemp);
		pos += sizeof(jUnsignedByte);
	}
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec &SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::operator=(const StillImageSensorConfigurationRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_SensorID = value.m_SensorID;
	m_FrameSize = value.m_FrameSize;
	m_StillImageFormat = value.m_StillImageFormat;
	
	return *this;
}

bool SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::operator==(const StillImageSensorConfigurationRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	if (m_FrameSize != value.m_FrameSize)
	{
		return false;
	}
	if (m_StillImageFormat != value.m_StillImageFormat)
	{
		return false;
	}
	
	return true;
}

bool SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::operator!=(const StillImageSensorConfigurationRec &value) const
{
	return !((*this) == value);
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::StillImageSensorConfigurationRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_SensorID = 0;
	m_FrameSize = 0;
	m_StillImageFormat = 0;
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::StillImageSensorConfigurationRec(const StillImageSensorConfigurationRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	m_SensorID = 0;
	m_FrameSize = 0;
	m_StillImageFormat = 0;
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_SensorID = value.m_SensorID;
	m_FrameSize = value.m_FrameSize;
	m_StillImageFormat = value.m_StillImageFormat;
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec::~StillImageSensorConfigurationRec()
{
}

unsigned int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::getNumberOfElements() const
{
	return (unsigned int) m_StillImageSensorConfigurationRec.size();
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorConfigurationRec* const SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::getElement(unsigned int index)
{
	return &m_StillImageSensorConfigurationRec.at(index);
}

int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::setElement(unsigned int index, const StillImageSensorConfigurationRec &value)
{
	if(m_StillImageSensorConfigurationRec.size()-1 < index)
	{
		return 1;
	}
	
	m_StillImageSensorConfigurationRec.at(index) = value;
	m_StillImageSensorConfigurationRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::addElement(const StillImageSensorConfigurationRec &value)
{
	m_StillImageSensorConfigurationRec.push_back(value);
	m_StillImageSensorConfigurationRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::deleteElement(unsigned int index)
{
	if(m_StillImageSensorConfigurationRec.size()-1 < index)
	{
		return 1;
	}
	
	m_StillImageSensorConfigurationRec.erase(m_StillImageSensorConfigurationRec.begin()+index);
	return 0;
}

int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::deleteLastElement()
{
	m_StillImageSensorConfigurationRec.pop_back();
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
const unsigned int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
	{
		size += m_StillImageSensorConfigurationRec[i].getSize();
	}
	
	return size;
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_StillImageSensorConfigurationRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
	{
		m_StillImageSensorConfigurationRec[i].encode(bytes + pos);
		pos += m_StillImageSensorConfigurationRec[i].getSize();
	}
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_StillImageSensorConfigurationRec.resize(size);
	for (int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
	{
		m_StillImageSensorConfigurationRec[i].decode(bytes + pos);
		pos += m_StillImageSensorConfigurationRec[i].getSize();
	}
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList &SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::operator=(const StillImageSensorList &value)
{
	m_StillImageSensorConfigurationRec.clear();
	
	for (int i = 0; i < value.m_StillImageSensorConfigurationRec.size(); i++)
	{
		m_StillImageSensorConfigurationRec.push_back(value.m_StillImageSensorConfigurationRec[i]);
		m_StillImageSensorConfigurationRec[i].setParent(this);
	}
	
	return *this;
}

bool SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::operator==(const StillImageSensorList &value) const
{
	for (int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
	{
		if (m_StillImageSensorConfigurationRec[i] !=  value.m_StillImageSensorConfigurationRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::operator!=(const StillImageSensorList &value) const
{
	return !((*this) == value);
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
	{
		m_StillImageSensorConfigurationRec[i].setParent(this);
	}
	/// No Initialization of m_StillImageSensorConfigurationRec necessary.
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::StillImageSensorList(const StillImageSensorList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
	{
		m_StillImageSensorConfigurationRec[i].setParent(this);
	}
	/// No Initialization of m_StillImageSensorConfigurationRec necessary.
	
	/// Copy the values
	m_StillImageSensorConfigurationRec.clear();
	
	for (int i = 0; i < value.m_StillImageSensorConfigurationRec.size(); i++)
	{
		m_StillImageSensorConfigurationRec.push_back(value.m_StillImageSensorConfigurationRec[i]);
		m_StillImageSensorConfigurationRec[i].setParent(this);
	}
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList::~StillImageSensorList()
{
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorList* const SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::getStillImageSensorList()
{
	return &m_StillImageSensorList;
}

int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::setStillImageSensorList(const StillImageSensorList &value)
{
	m_StillImageSensorList = value;
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
const unsigned int SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::getSize()
{
	unsigned int size = 0;
	
	size += m_RequestIdRec.getSize();
	size += m_StillImageSensorList.getSize();
	
	return size;
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIdRec.encode(bytes + pos);
	pos += m_RequestIdRec.getSize();
	m_StillImageSensorList.encode(bytes + pos);
	pos += m_StillImageSensorList.getSize();
}

void SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIdRec.decode(bytes + pos);
	pos += m_RequestIdRec.getSize();
	m_StillImageSensorList.decode(bytes + pos);
	pos += m_StillImageSensorList.getSize();
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence &SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::operator=(const StillImageSensorConfigurationSequence &value)
{
	m_RequestIdRec = value.m_RequestIdRec;
	m_RequestIdRec.setParent(this);
	m_RequestIdRec = value.m_RequestIdRec;
	m_StillImageSensorList = value.m_StillImageSensorList;
	m_StillImageSensorList.setParent(this);
	m_StillImageSensorList = value.m_StillImageSensorList;
	
	return *this;
}

bool SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::operator==(const StillImageSensorConfigurationSequence &value) const
{
	if (m_RequestIdRec != value.m_RequestIdRec)
	{
		return false;
	}
	
	if (m_RequestIdRec != value.m_RequestIdRec)
	{
		return false;
	}
	if (m_StillImageSensorList != value.m_StillImageSensorList)
	{
		return false;
	}
	
	if (m_StillImageSensorList != value.m_StillImageSensorList)
	{
		return false;
	}
	
	return true;
}

bool SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::operator!=(const StillImageSensorConfigurationSequence &value) const
{
	return !((*this) == value);
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorConfigurationSequence()
{
	m_parent = NULL;
	m_RequestIdRec.setParent(this);
	/// No Initialization of m_RequestIdRec necessary.
	m_StillImageSensorList.setParent(this);
	/// No Initialization of m_StillImageSensorList necessary.
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::StillImageSensorConfigurationSequence(const StillImageSensorConfigurationSequence &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestIdRec.setParent(this);
	/// No Initialization of m_RequestIdRec necessary.
	m_StillImageSensorList.setParent(this);
	/// No Initialization of m_StillImageSensorList necessary.
	
	/// Copy the values
	m_RequestIdRec = value.m_RequestIdRec;
	m_RequestIdRec.setParent(this);
	m_RequestIdRec = value.m_RequestIdRec;
	m_StillImageSensorList = value.m_StillImageSensorList;
	m_StillImageSensorList.setParent(this);
	m_StillImageSensorList = value.m_StillImageSensorList;
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence::~StillImageSensorConfigurationSequence()
{
}

SetStillImageSensorConfiguration::Body::StillImageSensorConfigurationSequence* const SetStillImageSensorConfiguration::Body::getStillImageSensorConfigurationSequence()
{
	return &m_StillImageSensorConfigurationSequence;
}

int SetStillImageSensorConfiguration::Body::setStillImageSensorConfigurationSequence(const StillImageSensorConfigurationSequence &value)
{
	m_StillImageSensorConfigurationSequence = value;
	setParentPresenceVector();
	return 0;
}

void SetStillImageSensorConfiguration::Body::setParentPresenceVector()
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
const unsigned int SetStillImageSensorConfiguration::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_StillImageSensorConfigurationSequence.getSize();
	
	return size;
}

void SetStillImageSensorConfiguration::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_StillImageSensorConfigurationSequence.encode(bytes + pos);
	pos += m_StillImageSensorConfigurationSequence.getSize();
}

void SetStillImageSensorConfiguration::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_StillImageSensorConfigurationSequence.decode(bytes + pos);
	pos += m_StillImageSensorConfigurationSequence.getSize();
}

SetStillImageSensorConfiguration::Body &SetStillImageSensorConfiguration::Body::operator=(const Body &value)
{
	m_StillImageSensorConfigurationSequence = value.m_StillImageSensorConfigurationSequence;
	m_StillImageSensorConfigurationSequence.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool SetStillImageSensorConfiguration::Body::operator==(const Body &value) const
{
	if (m_StillImageSensorConfigurationSequence != value.m_StillImageSensorConfigurationSequence)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool SetStillImageSensorConfiguration::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

SetStillImageSensorConfiguration::Body::Body()
{
	m_StillImageSensorConfigurationSequence.setParent(this);
	/// No Initialization of m_StillImageSensorConfigurationSequence necessary.
}

SetStillImageSensorConfiguration::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_StillImageSensorConfigurationSequence.setParent(this);
	/// No Initialization of m_StillImageSensorConfigurationSequence necessary.
	
	/// Copy the values
	m_StillImageSensorConfigurationSequence = value.m_StillImageSensorConfigurationSequence;
	m_StillImageSensorConfigurationSequence.setParent(this);
	/// This code is currently not supported
}

SetStillImageSensorConfiguration::Body::~Body()
{
}

SetStillImageSensorConfiguration::Body* const SetStillImageSensorConfiguration::getBody()
{
	return &m_Body;
}

int SetStillImageSensorConfiguration::setBody(const Body &value)
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
const unsigned int SetStillImageSensorConfiguration::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void SetStillImageSensorConfiguration::encode(unsigned char *bytes)
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

void SetStillImageSensorConfiguration::decode(const unsigned char *bytes)
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

SetStillImageSensorConfiguration &SetStillImageSensorConfiguration::operator=(const SetStillImageSensorConfiguration &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool SetStillImageSensorConfiguration::operator==(const SetStillImageSensorConfiguration &value) const
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

bool SetStillImageSensorConfiguration::operator!=(const SetStillImageSensorConfiguration &value) const
{
	return !((*this) == value);
}

SetStillImageSensorConfiguration::SetStillImageSensorConfiguration()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

SetStillImageSensorConfiguration::SetStillImageSensorConfiguration(const SetStillImageSensorConfiguration &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

SetStillImageSensorConfiguration::~SetStillImageSensorConfiguration()
{
}


}
