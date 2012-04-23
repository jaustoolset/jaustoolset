#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/ReportStillImageSensorConfiguration.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void ReportStillImageSensorConfiguration::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportStillImageSensorConfiguration::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportStillImageSensorConfiguration::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportStillImageSensorConfiguration::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportStillImageSensorConfiguration::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportStillImageSensorConfiguration::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportStillImageSensorConfiguration::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportStillImageSensorConfiguration::AppHeader::HeaderRec &ReportStillImageSensorConfiguration::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportStillImageSensorConfiguration::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportStillImageSensorConfiguration::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorConfiguration::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4813;
}

ReportStillImageSensorConfiguration::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4813;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportStillImageSensorConfiguration::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportStillImageSensorConfiguration::AppHeader::HeaderRec* const ReportStillImageSensorConfiguration::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportStillImageSensorConfiguration::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportStillImageSensorConfiguration::AppHeader::setParentPresenceVector()
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
const unsigned int ReportStillImageSensorConfiguration::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportStillImageSensorConfiguration::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportStillImageSensorConfiguration::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportStillImageSensorConfiguration::AppHeader &ReportStillImageSensorConfiguration::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportStillImageSensorConfiguration::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportStillImageSensorConfiguration::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorConfiguration::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportStillImageSensorConfiguration::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportStillImageSensorConfiguration::AppHeader::~AppHeader()
{
}

ReportStillImageSensorConfiguration::AppHeader* const ReportStillImageSensorConfiguration::getAppHeader()
{
	return &m_AppHeader;
}

int ReportStillImageSensorConfiguration::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::setParent(StillImageSensorConfigurationList* parent)
{
	m_parent = parent;
}

void ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::getPresenceVector()
{
	return m_PresenceVector;
}

int ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
	return 0;
}

bool ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

jUnsignedShortInteger ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::getSensorID()
{
	return m_SensorID;
}

int ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

bool ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::isFrameSizeValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::getFrameSize()
{
	return m_FrameSize;
}

int ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::setFrameSize(jUnsignedByte value)
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

bool ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::isStillImageFormatValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::getStillImageFormat()
{
	return m_StillImageFormat;
}

int ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::setStillImageFormat(jUnsignedByte value)
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
const unsigned int ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::getSize()
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

void ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::encode(unsigned char *bytes)
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

void ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::decode(const unsigned char *bytes)
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

ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec &ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::operator=(const StillImageSensorConfigurationRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_SensorID = value.m_SensorID;
	m_FrameSize = value.m_FrameSize;
	m_StillImageFormat = value.m_StillImageFormat;
	
	return *this;
}

bool ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::operator==(const StillImageSensorConfigurationRec &value) const
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

bool ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::operator!=(const StillImageSensorConfigurationRec &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::StillImageSensorConfigurationRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_SensorID = 0;
	m_FrameSize = 0;
	m_StillImageFormat = 0;
}

ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::StillImageSensorConfigurationRec(const StillImageSensorConfigurationRec &value)
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

ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec::~StillImageSensorConfigurationRec()
{
}

unsigned int ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::getNumberOfElements() const
{
	return (unsigned int) m_StillImageSensorConfigurationRec.size();
}

ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationRec* const ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::getElement(unsigned int index)
{
	return &m_StillImageSensorConfigurationRec.at(index);
}

int ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::setElement(unsigned int index, const StillImageSensorConfigurationRec &value)
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

int ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::addElement(const StillImageSensorConfigurationRec &value)
{
	m_StillImageSensorConfigurationRec.push_back(value);
	m_StillImageSensorConfigurationRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::deleteElement(unsigned int index)
{
	if(m_StillImageSensorConfigurationRec.size()-1 < index)
	{
		return 1;
	}
	
	m_StillImageSensorConfigurationRec.erase(m_StillImageSensorConfigurationRec.begin()+index);
	return 0;
}

int ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::deleteLastElement()
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
const unsigned int ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
	{
		size += m_StillImageSensorConfigurationRec[i].getSize();
	}
	
	return size;
}

void ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::encode(unsigned char *bytes)
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

void ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::decode(const unsigned char *bytes)
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

ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList &ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::operator=(const StillImageSensorConfigurationList &value)
{
	m_StillImageSensorConfigurationRec.clear();
	
	for (int i = 0; i < value.m_StillImageSensorConfigurationRec.size(); i++)
	{
		m_StillImageSensorConfigurationRec.push_back(value.m_StillImageSensorConfigurationRec[i]);
		m_StillImageSensorConfigurationRec[i].setParent(this);
	}
	
	return *this;
}

bool ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::operator==(const StillImageSensorConfigurationList &value) const
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

bool ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::operator!=(const StillImageSensorConfigurationList &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
	{
		m_StillImageSensorConfigurationRec[i].setParent(this);
	}
	/// No Initialization of m_StillImageSensorConfigurationRec necessary.
}

ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::StillImageSensorConfigurationList(const StillImageSensorConfigurationList &value)
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

ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList::~StillImageSensorConfigurationList()
{
}

ReportStillImageSensorConfiguration::Body::StillImageSensorConfigurationList* const ReportStillImageSensorConfiguration::Body::getStillImageSensorConfigurationList()
{
	return &m_StillImageSensorConfigurationList;
}

int ReportStillImageSensorConfiguration::Body::setStillImageSensorConfigurationList(const StillImageSensorConfigurationList &value)
{
	m_StillImageSensorConfigurationList = value;
	setParentPresenceVector();
	return 0;
}

void ReportStillImageSensorConfiguration::Body::setParentPresenceVector()
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
const unsigned int ReportStillImageSensorConfiguration::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_StillImageSensorConfigurationList.getSize();
	
	return size;
}

void ReportStillImageSensorConfiguration::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_StillImageSensorConfigurationList.encode(bytes + pos);
	pos += m_StillImageSensorConfigurationList.getSize();
}

void ReportStillImageSensorConfiguration::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_StillImageSensorConfigurationList.decode(bytes + pos);
	pos += m_StillImageSensorConfigurationList.getSize();
}

ReportStillImageSensorConfiguration::Body &ReportStillImageSensorConfiguration::Body::operator=(const Body &value)
{
	m_StillImageSensorConfigurationList = value.m_StillImageSensorConfigurationList;
	m_StillImageSensorConfigurationList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportStillImageSensorConfiguration::Body::operator==(const Body &value) const
{
	if (m_StillImageSensorConfigurationList != value.m_StillImageSensorConfigurationList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportStillImageSensorConfiguration::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorConfiguration::Body::Body()
{
	m_StillImageSensorConfigurationList.setParent(this);
	/// No Initialization of m_StillImageSensorConfigurationList necessary.
}

ReportStillImageSensorConfiguration::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_StillImageSensorConfigurationList.setParent(this);
	/// No Initialization of m_StillImageSensorConfigurationList necessary.
	
	/// Copy the values
	m_StillImageSensorConfigurationList = value.m_StillImageSensorConfigurationList;
	m_StillImageSensorConfigurationList.setParent(this);
	/// This code is currently not supported
}

ReportStillImageSensorConfiguration::Body::~Body()
{
}

ReportStillImageSensorConfiguration::Body* const ReportStillImageSensorConfiguration::getBody()
{
	return &m_Body;
}

int ReportStillImageSensorConfiguration::setBody(const Body &value)
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
const unsigned int ReportStillImageSensorConfiguration::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportStillImageSensorConfiguration::encode(unsigned char *bytes)
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

void ReportStillImageSensorConfiguration::decode(const unsigned char *bytes)
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

ReportStillImageSensorConfiguration &ReportStillImageSensorConfiguration::operator=(const ReportStillImageSensorConfiguration &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportStillImageSensorConfiguration::operator==(const ReportStillImageSensorConfiguration &value) const
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

bool ReportStillImageSensorConfiguration::operator!=(const ReportStillImageSensorConfiguration &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorConfiguration::ReportStillImageSensorConfiguration()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportStillImageSensorConfiguration::ReportStillImageSensorConfiguration(const ReportStillImageSensorConfiguration &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportStillImageSensorConfiguration::~ReportStillImageSensorConfiguration()
{
}


}
