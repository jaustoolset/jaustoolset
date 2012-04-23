#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/ConfirmSensorConfiguration.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void ConfirmSensorConfiguration::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ConfirmSensorConfiguration::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ConfirmSensorConfiguration::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ConfirmSensorConfiguration::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ConfirmSensorConfiguration::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ConfirmSensorConfiguration::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ConfirmSensorConfiguration::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ConfirmSensorConfiguration::AppHeader::HeaderRec &ConfirmSensorConfiguration::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ConfirmSensorConfiguration::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ConfirmSensorConfiguration::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x0801;
}

ConfirmSensorConfiguration::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x0801;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ConfirmSensorConfiguration::AppHeader::HeaderRec::~HeaderRec()
{
}

ConfirmSensorConfiguration::AppHeader::HeaderRec* const ConfirmSensorConfiguration::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ConfirmSensorConfiguration::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmSensorConfiguration::AppHeader::setParentPresenceVector()
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
const unsigned int ConfirmSensorConfiguration::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ConfirmSensorConfiguration::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ConfirmSensorConfiguration::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ConfirmSensorConfiguration::AppHeader &ConfirmSensorConfiguration::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ConfirmSensorConfiguration::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ConfirmSensorConfiguration::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ConfirmSensorConfiguration::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ConfirmSensorConfiguration::AppHeader::~AppHeader()
{
}

ConfirmSensorConfiguration::AppHeader* const ConfirmSensorConfiguration::getAppHeader()
{
	return &m_AppHeader;
}

int ConfirmSensorConfiguration::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::setParent(Body* parent)
{
	m_parent = parent;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::setParent(ConfirmSensorConfigurationSequence* parent)
{
	m_parent = parent;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::getRequestID()
{
	return m_RequestID;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::setRequestID(jUnsignedByte value)
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
const unsigned int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::encode(unsigned char *bytes)
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

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::decode(const unsigned char *bytes)
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

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec &ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::operator=(const RequestIdRec &value)
{
	m_RequestID = value.m_RequestID;
	
	return *this;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::operator==(const RequestIdRec &value) const
{
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	
	return true;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::operator!=(const RequestIdRec &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::RequestIdRec()
{
	m_parent = NULL;
	m_RequestID = 0;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::RequestIdRec(const RequestIdRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec::~RequestIdRec()
{
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::RequestIdRec* const ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::getRequestIdRec()
{
	return &m_RequestIdRec;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::setRequestIdRec(const RequestIdRec &value)
{
	m_RequestIdRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::setParent(ConfirmSensorConfigurationSequence* parent)
{
	m_parent = parent;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::setParent(ConfirmSensorList* parent)
{
	m_parent = parent;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::setParent(ConfirmSensorConfigurationVariant* parent)
{
	m_parent = parent;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::getSensorID()
{
	return m_SensorID;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
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
const unsigned int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	m_SensorIDTemp = JSIDL_v_1_0::correctEndianness(m_SensorID);
	memcpy(bytes + pos, &m_SensorIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	memcpy(&m_SensorIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SensorID = JSIDL_v_1_0::correctEndianness(m_SensorIDTemp);
	pos += sizeof(jUnsignedShortInteger);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec &ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::operator=(const SensorIdRec &value)
{
	m_SensorID = value.m_SensorID;
	
	return *this;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::operator==(const SensorIdRec &value) const
{
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	
	return true;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::operator!=(const SensorIdRec &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::SensorIdRec()
{
	m_parent = NULL;
	m_SensorID = 0;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::SensorIdRec(const SensorIdRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec::~SensorIdRec()
{
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::SensorIdRec* const ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::getSensorIdRec()
{
	return &m_SensorIdRec;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::setSensorIdRec(const SensorIdRec &value)
{
	m_SensorIdRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::setParent(ConfirmSensorConfigurationVariant* parent)
{
	m_parent = parent;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::getSensorID()
{
	return m_SensorID;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::getRangeSensorErrorCode()
{
	return m_RangeSensorErrorCode;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::setRangeSensorErrorCode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6) || (value == 255))
	{
		m_RangeSensorErrorCode = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jVariableLengthString ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::getErrorMessage()
{
	return m_ErrorMessage;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::setErrorMessage(jVariableLengthString value)
{
	if ( value.length() > 255)
	{
		return 1;
	}
	
	m_ErrorMessage = value;
	if (m_ErrorMessage.length() < 0)
	{
		m_ErrorMessage.resize(0);
	}
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
const unsigned int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += m_ErrorMessage.length();
	
	return size;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	m_SensorIDTemp = JSIDL_v_1_0::correctEndianness(m_SensorID);
	memcpy(bytes + pos, &m_SensorIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_RangeSensorErrorCodeTemp;
	
	m_RangeSensorErrorCodeTemp = JSIDL_v_1_0::correctEndianness(m_RangeSensorErrorCode);
	memcpy(bytes + pos, &m_RangeSensorErrorCodeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	jUnsignedByte m_ErrorMessageLength = 0;
	m_ErrorMessageLength = JSIDL_v_1_0::correctEndianness(m_ErrorMessage.length());
	memcpy(bytes+pos, (unsigned char*)&m_ErrorMessageLength, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	memcpy(bytes+pos, m_ErrorMessage.c_str(), m_ErrorMessage.length());
	pos += m_ErrorMessage.length();
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	memcpy(&m_SensorIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SensorID = JSIDL_v_1_0::correctEndianness(m_SensorIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_RangeSensorErrorCodeTemp;
	
	memcpy(&m_RangeSensorErrorCodeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_RangeSensorErrorCode = JSIDL_v_1_0::correctEndianness(m_RangeSensorErrorCodeTemp);
	pos += sizeof(jUnsignedByte);
	
	jUnsignedByte m_ErrorMessageLength = 0;
	memcpy((unsigned char*)&m_ErrorMessageLength, bytes+pos, sizeof( m_ErrorMessageLength ));
	m_ErrorMessageLength = JSIDL_v_1_0::correctEndianness(m_ErrorMessageLength);
	pos += sizeof( m_ErrorMessageLength );
	
	char* m_ErrorMessageTemp = new char[m_ErrorMessageLength+1];
	memcpy(m_ErrorMessageTemp, bytes+pos, m_ErrorMessageLength );
	m_ErrorMessageTemp[m_ErrorMessageLength] = '\0';
	m_ErrorMessage = m_ErrorMessageTemp;
	pos += m_ErrorMessageLength ;
	delete m_ErrorMessageTemp;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec &ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::operator=(const RangeSensorErrorRec &value)
{
	m_SensorID = value.m_SensorID;
	m_RangeSensorErrorCode = value.m_RangeSensorErrorCode;
	m_ErrorMessage = value.m_ErrorMessage;
	
	return *this;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::operator==(const RangeSensorErrorRec &value) const
{
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	if (m_RangeSensorErrorCode != value.m_RangeSensorErrorCode)
	{
		return false;
	}
	if ((m_ErrorMessage.length() != value.m_ErrorMessage.length()) || (m_ErrorMessage.compare(value.m_ErrorMessage) != 0))
	{
		return false;
	}
	
	return true;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::operator!=(const RangeSensorErrorRec &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::RangeSensorErrorRec()
{
	m_parent = NULL;
	m_SensorID = 0;
	m_RangeSensorErrorCode = 0;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::RangeSensorErrorRec(const RangeSensorErrorRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	m_RangeSensorErrorCode = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
	m_RangeSensorErrorCode = value.m_RangeSensorErrorCode;
	m_ErrorMessage = value.m_ErrorMessage;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec::~RangeSensorErrorRec()
{
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::RangeSensorErrorRec* const ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::getRangeSensorErrorRec()
{
	return &m_RangeSensorErrorRec;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::setRangeSensorErrorRec(const RangeSensorErrorRec &value)
{
	m_RangeSensorErrorRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::setParent(ConfirmSensorConfigurationVariant* parent)
{
	m_parent = parent;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::getSensorID()
{
	return m_SensorID;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::getVisualSensorErrorCode()
{
	return m_VisualSensorErrorCode;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::setVisualSensorErrorCode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6) || (value == 7) || (value == 8) || (value == 9) || (value == 10) || (value == 11) || (value == 12) || (value == 13) || (value == 14) || (value == 15) || (value == 16) || (value == 255))
	{
		m_VisualSensorErrorCode = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jVariableLengthString ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::getErrorMessage()
{
	return m_ErrorMessage;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::setErrorMessage(jVariableLengthString value)
{
	if ( value.length() > 255)
	{
		return 1;
	}
	
	m_ErrorMessage = value;
	if (m_ErrorMessage.length() < 0)
	{
		m_ErrorMessage.resize(0);
	}
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
const unsigned int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += m_ErrorMessage.length();
	
	return size;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	m_SensorIDTemp = JSIDL_v_1_0::correctEndianness(m_SensorID);
	memcpy(bytes + pos, &m_SensorIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_VisualSensorErrorCodeTemp;
	
	m_VisualSensorErrorCodeTemp = JSIDL_v_1_0::correctEndianness(m_VisualSensorErrorCode);
	memcpy(bytes + pos, &m_VisualSensorErrorCodeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	jUnsignedByte m_ErrorMessageLength = 0;
	m_ErrorMessageLength = JSIDL_v_1_0::correctEndianness(m_ErrorMessage.length());
	memcpy(bytes+pos, (unsigned char*)&m_ErrorMessageLength, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	memcpy(bytes+pos, m_ErrorMessage.c_str(), m_ErrorMessage.length());
	pos += m_ErrorMessage.length();
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	memcpy(&m_SensorIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SensorID = JSIDL_v_1_0::correctEndianness(m_SensorIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_VisualSensorErrorCodeTemp;
	
	memcpy(&m_VisualSensorErrorCodeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_VisualSensorErrorCode = JSIDL_v_1_0::correctEndianness(m_VisualSensorErrorCodeTemp);
	pos += sizeof(jUnsignedByte);
	
	jUnsignedByte m_ErrorMessageLength = 0;
	memcpy((unsigned char*)&m_ErrorMessageLength, bytes+pos, sizeof( m_ErrorMessageLength ));
	m_ErrorMessageLength = JSIDL_v_1_0::correctEndianness(m_ErrorMessageLength);
	pos += sizeof( m_ErrorMessageLength );
	
	char* m_ErrorMessageTemp = new char[m_ErrorMessageLength+1];
	memcpy(m_ErrorMessageTemp, bytes+pos, m_ErrorMessageLength );
	m_ErrorMessageTemp[m_ErrorMessageLength] = '\0';
	m_ErrorMessage = m_ErrorMessageTemp;
	pos += m_ErrorMessageLength ;
	delete m_ErrorMessageTemp;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec &ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::operator=(const VisualSensorErrorRec &value)
{
	m_SensorID = value.m_SensorID;
	m_VisualSensorErrorCode = value.m_VisualSensorErrorCode;
	m_ErrorMessage = value.m_ErrorMessage;
	
	return *this;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::operator==(const VisualSensorErrorRec &value) const
{
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	if (m_VisualSensorErrorCode != value.m_VisualSensorErrorCode)
	{
		return false;
	}
	if ((m_ErrorMessage.length() != value.m_ErrorMessage.length()) || (m_ErrorMessage.compare(value.m_ErrorMessage) != 0))
	{
		return false;
	}
	
	return true;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::operator!=(const VisualSensorErrorRec &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::VisualSensorErrorRec()
{
	m_parent = NULL;
	m_SensorID = 0;
	m_VisualSensorErrorCode = 0;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::VisualSensorErrorRec(const VisualSensorErrorRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	m_VisualSensorErrorCode = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
	m_VisualSensorErrorCode = value.m_VisualSensorErrorCode;
	m_ErrorMessage = value.m_ErrorMessage;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec::~VisualSensorErrorRec()
{
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::VisualSensorErrorRec* const ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::getVisualSensorErrorRec()
{
	return &m_VisualSensorErrorRec;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::setVisualSensorErrorRec(const VisualSensorErrorRec &value)
{
	m_VisualSensorErrorRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::setParent(ConfirmSensorConfigurationVariant* parent)
{
	m_parent = parent;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::getSensorID()
{
	return m_SensorID;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::getDigitalVideoErrorCode()
{
	return m_DigitalVideoErrorCode;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::setDigitalVideoErrorCode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6) || (value == 7) || (value == 255))
	{
		m_DigitalVideoErrorCode = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jVariableLengthString ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::getErrorMessage()
{
	return m_ErrorMessage;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::setErrorMessage(jVariableLengthString value)
{
	if ( value.length() > 255)
	{
		return 1;
	}
	
	m_ErrorMessage = value;
	if (m_ErrorMessage.length() < 0)
	{
		m_ErrorMessage.resize(0);
	}
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
const unsigned int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += m_ErrorMessage.length();
	
	return size;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	m_SensorIDTemp = JSIDL_v_1_0::correctEndianness(m_SensorID);
	memcpy(bytes + pos, &m_SensorIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_DigitalVideoErrorCodeTemp;
	
	m_DigitalVideoErrorCodeTemp = JSIDL_v_1_0::correctEndianness(m_DigitalVideoErrorCode);
	memcpy(bytes + pos, &m_DigitalVideoErrorCodeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	jUnsignedByte m_ErrorMessageLength = 0;
	m_ErrorMessageLength = JSIDL_v_1_0::correctEndianness(m_ErrorMessage.length());
	memcpy(bytes+pos, (unsigned char*)&m_ErrorMessageLength, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	memcpy(bytes+pos, m_ErrorMessage.c_str(), m_ErrorMessage.length());
	pos += m_ErrorMessage.length();
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	memcpy(&m_SensorIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SensorID = JSIDL_v_1_0::correctEndianness(m_SensorIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_DigitalVideoErrorCodeTemp;
	
	memcpy(&m_DigitalVideoErrorCodeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_DigitalVideoErrorCode = JSIDL_v_1_0::correctEndianness(m_DigitalVideoErrorCodeTemp);
	pos += sizeof(jUnsignedByte);
	
	jUnsignedByte m_ErrorMessageLength = 0;
	memcpy((unsigned char*)&m_ErrorMessageLength, bytes+pos, sizeof( m_ErrorMessageLength ));
	m_ErrorMessageLength = JSIDL_v_1_0::correctEndianness(m_ErrorMessageLength);
	pos += sizeof( m_ErrorMessageLength );
	
	char* m_ErrorMessageTemp = new char[m_ErrorMessageLength+1];
	memcpy(m_ErrorMessageTemp, bytes+pos, m_ErrorMessageLength );
	m_ErrorMessageTemp[m_ErrorMessageLength] = '\0';
	m_ErrorMessage = m_ErrorMessageTemp;
	pos += m_ErrorMessageLength ;
	delete m_ErrorMessageTemp;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec &ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::operator=(const DigitalVideoSensorErrorRec &value)
{
	m_SensorID = value.m_SensorID;
	m_DigitalVideoErrorCode = value.m_DigitalVideoErrorCode;
	m_ErrorMessage = value.m_ErrorMessage;
	
	return *this;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::operator==(const DigitalVideoSensorErrorRec &value) const
{
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	if (m_DigitalVideoErrorCode != value.m_DigitalVideoErrorCode)
	{
		return false;
	}
	if ((m_ErrorMessage.length() != value.m_ErrorMessage.length()) || (m_ErrorMessage.compare(value.m_ErrorMessage) != 0))
	{
		return false;
	}
	
	return true;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::operator!=(const DigitalVideoSensorErrorRec &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::DigitalVideoSensorErrorRec()
{
	m_parent = NULL;
	m_SensorID = 0;
	m_DigitalVideoErrorCode = 0;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::DigitalVideoSensorErrorRec(const DigitalVideoSensorErrorRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	m_DigitalVideoErrorCode = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
	m_DigitalVideoErrorCode = value.m_DigitalVideoErrorCode;
	m_ErrorMessage = value.m_ErrorMessage;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec::~DigitalVideoSensorErrorRec()
{
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::DigitalVideoSensorErrorRec* const ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::getDigitalVideoSensorErrorRec()
{
	return &m_DigitalVideoSensorErrorRec;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::setDigitalVideoSensorErrorRec(const DigitalVideoSensorErrorRec &value)
{
	m_DigitalVideoSensorErrorRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::setParent(ConfirmSensorConfigurationVariant* parent)
{
	m_parent = parent;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::getSensorID()
{
	return m_SensorID;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::getAnalogVideoErrorCode()
{
	return m_AnalogVideoErrorCode;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::setAnalogVideoErrorCode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 255))
	{
		m_AnalogVideoErrorCode = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jVariableLengthString ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::getErrorMessage()
{
	return m_ErrorMessage;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::setErrorMessage(jVariableLengthString value)
{
	if ( value.length() > 255)
	{
		return 1;
	}
	
	m_ErrorMessage = value;
	if (m_ErrorMessage.length() < 0)
	{
		m_ErrorMessage.resize(0);
	}
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
const unsigned int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += m_ErrorMessage.length();
	
	return size;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	m_SensorIDTemp = JSIDL_v_1_0::correctEndianness(m_SensorID);
	memcpy(bytes + pos, &m_SensorIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_AnalogVideoErrorCodeTemp;
	
	m_AnalogVideoErrorCodeTemp = JSIDL_v_1_0::correctEndianness(m_AnalogVideoErrorCode);
	memcpy(bytes + pos, &m_AnalogVideoErrorCodeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	jUnsignedByte m_ErrorMessageLength = 0;
	m_ErrorMessageLength = JSIDL_v_1_0::correctEndianness(m_ErrorMessage.length());
	memcpy(bytes+pos, (unsigned char*)&m_ErrorMessageLength, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	memcpy(bytes+pos, m_ErrorMessage.c_str(), m_ErrorMessage.length());
	pos += m_ErrorMessage.length();
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	memcpy(&m_SensorIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SensorID = JSIDL_v_1_0::correctEndianness(m_SensorIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_AnalogVideoErrorCodeTemp;
	
	memcpy(&m_AnalogVideoErrorCodeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_AnalogVideoErrorCode = JSIDL_v_1_0::correctEndianness(m_AnalogVideoErrorCodeTemp);
	pos += sizeof(jUnsignedByte);
	
	jUnsignedByte m_ErrorMessageLength = 0;
	memcpy((unsigned char*)&m_ErrorMessageLength, bytes+pos, sizeof( m_ErrorMessageLength ));
	m_ErrorMessageLength = JSIDL_v_1_0::correctEndianness(m_ErrorMessageLength);
	pos += sizeof( m_ErrorMessageLength );
	
	char* m_ErrorMessageTemp = new char[m_ErrorMessageLength+1];
	memcpy(m_ErrorMessageTemp, bytes+pos, m_ErrorMessageLength );
	m_ErrorMessageTemp[m_ErrorMessageLength] = '\0';
	m_ErrorMessage = m_ErrorMessageTemp;
	pos += m_ErrorMessageLength ;
	delete m_ErrorMessageTemp;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec &ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::operator=(const AnalogVideoSensorErrorRec &value)
{
	m_SensorID = value.m_SensorID;
	m_AnalogVideoErrorCode = value.m_AnalogVideoErrorCode;
	m_ErrorMessage = value.m_ErrorMessage;
	
	return *this;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::operator==(const AnalogVideoSensorErrorRec &value) const
{
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	if (m_AnalogVideoErrorCode != value.m_AnalogVideoErrorCode)
	{
		return false;
	}
	if ((m_ErrorMessage.length() != value.m_ErrorMessage.length()) || (m_ErrorMessage.compare(value.m_ErrorMessage) != 0))
	{
		return false;
	}
	
	return true;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::operator!=(const AnalogVideoSensorErrorRec &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::AnalogVideoSensorErrorRec()
{
	m_parent = NULL;
	m_SensorID = 0;
	m_AnalogVideoErrorCode = 0;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::AnalogVideoSensorErrorRec(const AnalogVideoSensorErrorRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	m_AnalogVideoErrorCode = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
	m_AnalogVideoErrorCode = value.m_AnalogVideoErrorCode;
	m_ErrorMessage = value.m_ErrorMessage;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec::~AnalogVideoSensorErrorRec()
{
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::AnalogVideoSensorErrorRec* const ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::getAnalogVideoSensorErrorRec()
{
	return &m_AnalogVideoSensorErrorRec;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::setAnalogVideoSensorErrorRec(const AnalogVideoSensorErrorRec &value)
{
	m_AnalogVideoSensorErrorRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::setParent(ConfirmSensorConfigurationVariant* parent)
{
	m_parent = parent;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::getSensorID()
{
	return m_SensorID;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::getStillImageErrorCode()
{
	return m_StillImageErrorCode;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::setStillImageErrorCode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 255))
	{
		m_StillImageErrorCode = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jVariableLengthString ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::getErrorMessage()
{
	return m_ErrorMessage;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::setErrorMessage(jVariableLengthString value)
{
	if ( value.length() > 255)
	{
		return 1;
	}
	
	m_ErrorMessage = value;
	if (m_ErrorMessage.length() < 0)
	{
		m_ErrorMessage.resize(0);
	}
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
const unsigned int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += m_ErrorMessage.length();
	
	return size;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	m_SensorIDTemp = JSIDL_v_1_0::correctEndianness(m_SensorID);
	memcpy(bytes + pos, &m_SensorIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_StillImageErrorCodeTemp;
	
	m_StillImageErrorCodeTemp = JSIDL_v_1_0::correctEndianness(m_StillImageErrorCode);
	memcpy(bytes + pos, &m_StillImageErrorCodeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	jUnsignedByte m_ErrorMessageLength = 0;
	m_ErrorMessageLength = JSIDL_v_1_0::correctEndianness(m_ErrorMessage.length());
	memcpy(bytes+pos, (unsigned char*)&m_ErrorMessageLength, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	memcpy(bytes+pos, m_ErrorMessage.c_str(), m_ErrorMessage.length());
	pos += m_ErrorMessage.length();
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	memcpy(&m_SensorIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SensorID = JSIDL_v_1_0::correctEndianness(m_SensorIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_StillImageErrorCodeTemp;
	
	memcpy(&m_StillImageErrorCodeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_StillImageErrorCode = JSIDL_v_1_0::correctEndianness(m_StillImageErrorCodeTemp);
	pos += sizeof(jUnsignedByte);
	
	jUnsignedByte m_ErrorMessageLength = 0;
	memcpy((unsigned char*)&m_ErrorMessageLength, bytes+pos, sizeof( m_ErrorMessageLength ));
	m_ErrorMessageLength = JSIDL_v_1_0::correctEndianness(m_ErrorMessageLength);
	pos += sizeof( m_ErrorMessageLength );
	
	char* m_ErrorMessageTemp = new char[m_ErrorMessageLength+1];
	memcpy(m_ErrorMessageTemp, bytes+pos, m_ErrorMessageLength );
	m_ErrorMessageTemp[m_ErrorMessageLength] = '\0';
	m_ErrorMessage = m_ErrorMessageTemp;
	pos += m_ErrorMessageLength ;
	delete m_ErrorMessageTemp;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec &ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::operator=(const StillImageSensorErrorRec &value)
{
	m_SensorID = value.m_SensorID;
	m_StillImageErrorCode = value.m_StillImageErrorCode;
	m_ErrorMessage = value.m_ErrorMessage;
	
	return *this;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::operator==(const StillImageSensorErrorRec &value) const
{
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	if (m_StillImageErrorCode != value.m_StillImageErrorCode)
	{
		return false;
	}
	if ((m_ErrorMessage.length() != value.m_ErrorMessage.length()) || (m_ErrorMessage.compare(value.m_ErrorMessage) != 0))
	{
		return false;
	}
	
	return true;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::operator!=(const StillImageSensorErrorRec &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::StillImageSensorErrorRec()
{
	m_parent = NULL;
	m_SensorID = 0;
	m_StillImageErrorCode = 0;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::StillImageSensorErrorRec(const StillImageSensorErrorRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	m_StillImageErrorCode = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
	m_StillImageErrorCode = value.m_StillImageErrorCode;
	m_ErrorMessage = value.m_ErrorMessage;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec::~StillImageSensorErrorRec()
{
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::StillImageSensorErrorRec* const ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::getStillImageSensorErrorRec()
{
	return &m_StillImageSensorErrorRec;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::setStillImageSensorErrorRec(const StillImageSensorErrorRec &value)
{
	m_StillImageSensorErrorRec = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::getFieldValue() const
{
	return m_FieldValue;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::setFieldValue(jUnsignedByte fieldValue)
{
	if(fieldValue > 5)
	{
		return 1;
	}
	
	m_FieldValue = fieldValue;
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
const unsigned int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	switch(m_FieldValue)
	{
		case 0:
			size += m_SensorIdRec.getSize();
			break;
		case 1:
			size += m_RangeSensorErrorRec.getSize();
			break;
		case 2:
			size += m_VisualSensorErrorRec.getSize();
			break;
		case 3:
			size += m_DigitalVideoSensorErrorRec.getSize();
			break;
		case 4:
			size += m_AnalogVideoSensorErrorRec.getSize();
			break;
		case 5:
			size += m_StillImageSensorErrorRec.getSize();
			break;
	}
	
	return size;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_FieldValueTemp;
	
	m_FieldValueTemp = JSIDL_v_1_0::correctEndianness(m_FieldValue);
	memcpy(bytes + pos, &m_FieldValueTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	switch(m_FieldValue)
	{
		case 0:
			m_SensorIdRec.encode(bytes + pos);
			pos += m_SensorIdRec.getSize();
			break;
		case 1:
			m_RangeSensorErrorRec.encode(bytes + pos);
			pos += m_RangeSensorErrorRec.getSize();
			break;
		case 2:
			m_VisualSensorErrorRec.encode(bytes + pos);
			pos += m_VisualSensorErrorRec.getSize();
			break;
		case 3:
			m_DigitalVideoSensorErrorRec.encode(bytes + pos);
			pos += m_DigitalVideoSensorErrorRec.getSize();
			break;
		case 4:
			m_AnalogVideoSensorErrorRec.encode(bytes + pos);
			pos += m_AnalogVideoSensorErrorRec.getSize();
			break;
		case 5:
			m_StillImageSensorErrorRec.encode(bytes + pos);
			pos += m_StillImageSensorErrorRec.getSize();
			break;
	}
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_FieldValueTemp;
	
	memcpy(&m_FieldValueTemp, bytes + pos, sizeof(jUnsignedByte));
	m_FieldValue = JSIDL_v_1_0::correctEndianness(m_FieldValueTemp);
	pos += sizeof(jUnsignedByte);
	
	switch(m_FieldValue)
	{
		case 0:
			m_SensorIdRec.decode(bytes + pos);
			pos += m_SensorIdRec.getSize();
			break;
		case 1:
			m_RangeSensorErrorRec.decode(bytes + pos);
			pos += m_RangeSensorErrorRec.getSize();
			break;
		case 2:
			m_VisualSensorErrorRec.decode(bytes + pos);
			pos += m_VisualSensorErrorRec.getSize();
			break;
		case 3:
			m_DigitalVideoSensorErrorRec.decode(bytes + pos);
			pos += m_DigitalVideoSensorErrorRec.getSize();
			break;
		case 4:
			m_AnalogVideoSensorErrorRec.decode(bytes + pos);
			pos += m_AnalogVideoSensorErrorRec.getSize();
			break;
		case 5:
			m_StillImageSensorErrorRec.decode(bytes + pos);
			pos += m_StillImageSensorErrorRec.getSize();
			break;
	}
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant &ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::operator=(const ConfirmSensorConfigurationVariant &value)
{
	m_FieldValue = value.m_FieldValue;
	m_SensorIdRec = value.m_SensorIdRec;
	m_SensorIdRec.setParent(this);
	m_RangeSensorErrorRec = value.m_RangeSensorErrorRec;
	m_RangeSensorErrorRec.setParent(this);
	m_VisualSensorErrorRec = value.m_VisualSensorErrorRec;
	m_VisualSensorErrorRec.setParent(this);
	m_DigitalVideoSensorErrorRec = value.m_DigitalVideoSensorErrorRec;
	m_DigitalVideoSensorErrorRec.setParent(this);
	m_AnalogVideoSensorErrorRec = value.m_AnalogVideoSensorErrorRec;
	m_AnalogVideoSensorErrorRec.setParent(this);
	m_StillImageSensorErrorRec = value.m_StillImageSensorErrorRec;
	m_StillImageSensorErrorRec.setParent(this);
	
	return *this;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::operator==(const ConfirmSensorConfigurationVariant &value) const
{
	if (m_FieldValue != value.m_FieldValue)
	{
		return false;
	}
	if (m_SensorIdRec != value.m_SensorIdRec)
	{
		return false;
	}
	if (m_RangeSensorErrorRec != value.m_RangeSensorErrorRec)
	{
		return false;
	}
	if (m_VisualSensorErrorRec != value.m_VisualSensorErrorRec)
	{
		return false;
	}
	if (m_DigitalVideoSensorErrorRec != value.m_DigitalVideoSensorErrorRec)
	{
		return false;
	}
	if (m_AnalogVideoSensorErrorRec != value.m_AnalogVideoSensorErrorRec)
	{
		return false;
	}
	if (m_StillImageSensorErrorRec != value.m_StillImageSensorErrorRec)
	{
		return false;
	}
	
	return true;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::operator!=(const ConfirmSensorConfigurationVariant &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::ConfirmSensorConfigurationVariant()
{
	m_parent = NULL;
	m_FieldValue = 0;
	m_SensorIdRec.setParent(this);
	/// No Initialization of m_SensorIdRec necessary.
	m_RangeSensorErrorRec.setParent(this);
	/// No Initialization of m_RangeSensorErrorRec necessary.
	m_VisualSensorErrorRec.setParent(this);
	/// No Initialization of m_VisualSensorErrorRec necessary.
	m_DigitalVideoSensorErrorRec.setParent(this);
	/// No Initialization of m_DigitalVideoSensorErrorRec necessary.
	m_AnalogVideoSensorErrorRec.setParent(this);
	/// No Initialization of m_AnalogVideoSensorErrorRec necessary.
	m_StillImageSensorErrorRec.setParent(this);
	/// No Initialization of m_StillImageSensorErrorRec necessary.
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::ConfirmSensorConfigurationVariant(const ConfirmSensorConfigurationVariant &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_FieldValue = 0;
	m_SensorIdRec.setParent(this);
	/// No Initialization of m_SensorIdRec necessary.
	m_RangeSensorErrorRec.setParent(this);
	/// No Initialization of m_RangeSensorErrorRec necessary.
	m_VisualSensorErrorRec.setParent(this);
	/// No Initialization of m_VisualSensorErrorRec necessary.
	m_DigitalVideoSensorErrorRec.setParent(this);
	/// No Initialization of m_DigitalVideoSensorErrorRec necessary.
	m_AnalogVideoSensorErrorRec.setParent(this);
	/// No Initialization of m_AnalogVideoSensorErrorRec necessary.
	m_StillImageSensorErrorRec.setParent(this);
	/// No Initialization of m_StillImageSensorErrorRec necessary.
	
	/// Copy the values
	m_FieldValue = value.m_FieldValue;
	m_SensorIdRec = value.m_SensorIdRec;
	m_SensorIdRec.setParent(this);
	m_RangeSensorErrorRec = value.m_RangeSensorErrorRec;
	m_RangeSensorErrorRec.setParent(this);
	m_VisualSensorErrorRec = value.m_VisualSensorErrorRec;
	m_VisualSensorErrorRec.setParent(this);
	m_DigitalVideoSensorErrorRec = value.m_DigitalVideoSensorErrorRec;
	m_DigitalVideoSensorErrorRec.setParent(this);
	m_AnalogVideoSensorErrorRec = value.m_AnalogVideoSensorErrorRec;
	m_AnalogVideoSensorErrorRec.setParent(this);
	m_StillImageSensorErrorRec = value.m_StillImageSensorErrorRec;
	m_StillImageSensorErrorRec.setParent(this);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant::~ConfirmSensorConfigurationVariant()
{
}

unsigned int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::getNumberOfElements() const
{
	return (unsigned int) m_ConfirmSensorConfigurationVariant.size();
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorConfigurationVariant* const ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::getElement(unsigned int index)
{
	return &m_ConfirmSensorConfigurationVariant.at(index);
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::setElement(unsigned int index, const ConfirmSensorConfigurationVariant &value)
{
	if(m_ConfirmSensorConfigurationVariant.size()-1 < index)
	{
		return 1;
	}
	
	m_ConfirmSensorConfigurationVariant.at(index) = value;
	m_ConfirmSensorConfigurationVariant.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::addElement(const ConfirmSensorConfigurationVariant &value)
{
	m_ConfirmSensorConfigurationVariant.push_back(value);
	m_ConfirmSensorConfigurationVariant.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::deleteElement(unsigned int index)
{
	if(m_ConfirmSensorConfigurationVariant.size()-1 < index)
	{
		return 1;
	}
	
	m_ConfirmSensorConfigurationVariant.erase(m_ConfirmSensorConfigurationVariant.begin()+index);
	return 0;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::deleteLastElement()
{
	m_ConfirmSensorConfigurationVariant.pop_back();
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
const unsigned int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_ConfirmSensorConfigurationVariant.size(); i++)
	{
		size += m_ConfirmSensorConfigurationVariant[i].getSize();
	}
	
	return size;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_ConfirmSensorConfigurationVariant.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_ConfirmSensorConfigurationVariant.size(); i++)
	{
		m_ConfirmSensorConfigurationVariant[i].encode(bytes + pos);
		pos += m_ConfirmSensorConfigurationVariant[i].getSize();
	}
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_ConfirmSensorConfigurationVariant.resize(size);
	for (int i = 0; i < m_ConfirmSensorConfigurationVariant.size(); i++)
	{
		m_ConfirmSensorConfigurationVariant[i].decode(bytes + pos);
		pos += m_ConfirmSensorConfigurationVariant[i].getSize();
	}
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList &ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::operator=(const ConfirmSensorList &value)
{
	m_ConfirmSensorConfigurationVariant.clear();
	
	for (int i = 0; i < value.m_ConfirmSensorConfigurationVariant.size(); i++)
	{
		m_ConfirmSensorConfigurationVariant.push_back(value.m_ConfirmSensorConfigurationVariant[i]);
		m_ConfirmSensorConfigurationVariant[i].setParent(this);
	}
	
	return *this;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::operator==(const ConfirmSensorList &value) const
{
	for (int i = 0; i < m_ConfirmSensorConfigurationVariant.size(); i++)
	{
		if (m_ConfirmSensorConfigurationVariant[i] != value.m_ConfirmSensorConfigurationVariant[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::operator!=(const ConfirmSensorList &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ConfirmSensorConfigurationVariant.size(); i++)
	{
		m_ConfirmSensorConfigurationVariant[i].setParent(this);
	}
	/// No Initialization of m_ConfirmSensorConfigurationVariant necessary.
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::ConfirmSensorList(const ConfirmSensorList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ConfirmSensorConfigurationVariant.size(); i++)
	{
		m_ConfirmSensorConfigurationVariant[i].setParent(this);
	}
	/// No Initialization of m_ConfirmSensorConfigurationVariant necessary.
	
	/// Copy the values
	m_ConfirmSensorConfigurationVariant.clear();
	
	for (int i = 0; i < value.m_ConfirmSensorConfigurationVariant.size(); i++)
	{
		m_ConfirmSensorConfigurationVariant.push_back(value.m_ConfirmSensorConfigurationVariant[i]);
		m_ConfirmSensorConfigurationVariant[i].setParent(this);
	}
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList::~ConfirmSensorList()
{
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorList* const ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::getConfirmSensorList()
{
	return &m_ConfirmSensorList;
}

int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::setConfirmSensorList(const ConfirmSensorList &value)
{
	m_ConfirmSensorList = value;
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
const unsigned int ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::getSize()
{
	unsigned int size = 0;
	
	size += m_RequestIdRec.getSize();
	size += m_ConfirmSensorList.getSize();
	
	return size;
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIdRec.encode(bytes + pos);
	pos += m_RequestIdRec.getSize();
	m_ConfirmSensorList.encode(bytes + pos);
	pos += m_ConfirmSensorList.getSize();
}

void ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIdRec.decode(bytes + pos);
	pos += m_RequestIdRec.getSize();
	m_ConfirmSensorList.decode(bytes + pos);
	pos += m_ConfirmSensorList.getSize();
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence &ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::operator=(const ConfirmSensorConfigurationSequence &value)
{
	m_RequestIdRec = value.m_RequestIdRec;
	m_RequestIdRec.setParent(this);
	m_RequestIdRec = value.m_RequestIdRec;
	m_ConfirmSensorList = value.m_ConfirmSensorList;
	m_ConfirmSensorList.setParent(this);
	m_ConfirmSensorList = value.m_ConfirmSensorList;
	
	return *this;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::operator==(const ConfirmSensorConfigurationSequence &value) const
{
	if (m_RequestIdRec != value.m_RequestIdRec)
	{
		return false;
	}
	
	if (m_RequestIdRec != value.m_RequestIdRec)
	{
		return false;
	}
	if (m_ConfirmSensorList != value.m_ConfirmSensorList)
	{
		return false;
	}
	
	if (m_ConfirmSensorList != value.m_ConfirmSensorList)
	{
		return false;
	}
	
	return true;
}

bool ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::operator!=(const ConfirmSensorConfigurationSequence &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorConfigurationSequence()
{
	m_parent = NULL;
	m_RequestIdRec.setParent(this);
	/// No Initialization of m_RequestIdRec necessary.
	m_ConfirmSensorList.setParent(this);
	/// No Initialization of m_ConfirmSensorList necessary.
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::ConfirmSensorConfigurationSequence(const ConfirmSensorConfigurationSequence &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestIdRec.setParent(this);
	/// No Initialization of m_RequestIdRec necessary.
	m_ConfirmSensorList.setParent(this);
	/// No Initialization of m_ConfirmSensorList necessary.
	
	/// Copy the values
	m_RequestIdRec = value.m_RequestIdRec;
	m_RequestIdRec.setParent(this);
	m_RequestIdRec = value.m_RequestIdRec;
	m_ConfirmSensorList = value.m_ConfirmSensorList;
	m_ConfirmSensorList.setParent(this);
	m_ConfirmSensorList = value.m_ConfirmSensorList;
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence::~ConfirmSensorConfigurationSequence()
{
}

ConfirmSensorConfiguration::Body::ConfirmSensorConfigurationSequence* const ConfirmSensorConfiguration::Body::getConfirmSensorConfigurationSequence()
{
	return &m_ConfirmSensorConfigurationSequence;
}

int ConfirmSensorConfiguration::Body::setConfirmSensorConfigurationSequence(const ConfirmSensorConfigurationSequence &value)
{
	m_ConfirmSensorConfigurationSequence = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmSensorConfiguration::Body::setParentPresenceVector()
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
const unsigned int ConfirmSensorConfiguration::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ConfirmSensorConfigurationSequence.getSize();
	
	return size;
}

void ConfirmSensorConfiguration::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ConfirmSensorConfigurationSequence.encode(bytes + pos);
	pos += m_ConfirmSensorConfigurationSequence.getSize();
}

void ConfirmSensorConfiguration::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ConfirmSensorConfigurationSequence.decode(bytes + pos);
	pos += m_ConfirmSensorConfigurationSequence.getSize();
}

ConfirmSensorConfiguration::Body &ConfirmSensorConfiguration::Body::operator=(const Body &value)
{
	m_ConfirmSensorConfigurationSequence = value.m_ConfirmSensorConfigurationSequence;
	m_ConfirmSensorConfigurationSequence.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ConfirmSensorConfiguration::Body::operator==(const Body &value) const
{
	if (m_ConfirmSensorConfigurationSequence != value.m_ConfirmSensorConfigurationSequence)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ConfirmSensorConfiguration::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::Body::Body()
{
	m_ConfirmSensorConfigurationSequence.setParent(this);
	/// No Initialization of m_ConfirmSensorConfigurationSequence necessary.
}

ConfirmSensorConfiguration::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ConfirmSensorConfigurationSequence.setParent(this);
	/// No Initialization of m_ConfirmSensorConfigurationSequence necessary.
	
	/// Copy the values
	m_ConfirmSensorConfigurationSequence = value.m_ConfirmSensorConfigurationSequence;
	m_ConfirmSensorConfigurationSequence.setParent(this);
	/// This code is currently not supported
}

ConfirmSensorConfiguration::Body::~Body()
{
}

ConfirmSensorConfiguration::Body* const ConfirmSensorConfiguration::getBody()
{
	return &m_Body;
}

int ConfirmSensorConfiguration::setBody(const Body &value)
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
const unsigned int ConfirmSensorConfiguration::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ConfirmSensorConfiguration::encode(unsigned char *bytes)
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

void ConfirmSensorConfiguration::decode(const unsigned char *bytes)
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

ConfirmSensorConfiguration &ConfirmSensorConfiguration::operator=(const ConfirmSensorConfiguration &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ConfirmSensorConfiguration::operator==(const ConfirmSensorConfiguration &value) const
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

bool ConfirmSensorConfiguration::operator!=(const ConfirmSensorConfiguration &value) const
{
	return !((*this) == value);
}

ConfirmSensorConfiguration::ConfirmSensorConfiguration()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ConfirmSensorConfiguration::ConfirmSensorConfiguration(const ConfirmSensorConfiguration &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ConfirmSensorConfiguration::~ConfirmSensorConfiguration()
{
}


}
