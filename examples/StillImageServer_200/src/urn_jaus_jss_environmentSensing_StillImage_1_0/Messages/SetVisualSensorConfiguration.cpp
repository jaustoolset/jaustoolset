#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/SetVisualSensorConfiguration.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void SetVisualSensorConfiguration::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void SetVisualSensorConfiguration::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger SetVisualSensorConfiguration::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int SetVisualSensorConfiguration::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int SetVisualSensorConfiguration::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void SetVisualSensorConfiguration::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void SetVisualSensorConfiguration::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

SetVisualSensorConfiguration::AppHeader::HeaderRec &SetVisualSensorConfiguration::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool SetVisualSensorConfiguration::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool SetVisualSensorConfiguration::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

SetVisualSensorConfiguration::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x0803;
}

SetVisualSensorConfiguration::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x0803;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

SetVisualSensorConfiguration::AppHeader::HeaderRec::~HeaderRec()
{
}

SetVisualSensorConfiguration::AppHeader::HeaderRec* const SetVisualSensorConfiguration::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int SetVisualSensorConfiguration::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void SetVisualSensorConfiguration::AppHeader::setParentPresenceVector()
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
const unsigned int SetVisualSensorConfiguration::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void SetVisualSensorConfiguration::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void SetVisualSensorConfiguration::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

SetVisualSensorConfiguration::AppHeader &SetVisualSensorConfiguration::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool SetVisualSensorConfiguration::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool SetVisualSensorConfiguration::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

SetVisualSensorConfiguration::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

SetVisualSensorConfiguration::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

SetVisualSensorConfiguration::AppHeader::~AppHeader()
{
}

SetVisualSensorConfiguration::AppHeader* const SetVisualSensorConfiguration::getAppHeader()
{
	return &m_AppHeader;
}

int SetVisualSensorConfiguration::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::setParent(Body* parent)
{
	m_parent = parent;
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::setParent(VisualSensorConfigurationSequence* parent)
{
	m_parent = parent;
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::getRequestID()
{
	return m_RequestID;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::setRequestID(jUnsignedByte value)
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
const unsigned int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::encode(unsigned char *bytes)
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

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::decode(const unsigned char *bytes)
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

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec &SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::operator=(const RequestIdRec &value)
{
	m_RequestID = value.m_RequestID;
	
	return *this;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::operator==(const RequestIdRec &value) const
{
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	
	return true;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::operator!=(const RequestIdRec &value) const
{
	return !((*this) == value);
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::RequestIdRec()
{
	m_parent = NULL;
	m_RequestID = 0;
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::RequestIdRec(const RequestIdRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec::~RequestIdRec()
{
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::RequestIdRec* const SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::getRequestIdRec()
{
	return &m_RequestIdRec;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::setRequestIdRec(const RequestIdRec &value)
{
	m_RequestIdRec = value;
	setParentPresenceVector();
	return 0;
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::setParent(VisualSensorConfigurationSequence* parent)
{
	m_parent = parent;
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setParent(VisualSensorConfigurationList* parent)
{
	m_parent = parent;
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getPresenceVector()
{
	return m_PresenceVector;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedInteger) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedInteger)pvBitSet.to_ulong();
	return 0;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedInteger) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

jUnsignedShortInteger SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getSensorID()
{
	return m_SensorID;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isSensorStateValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

jUnsignedByte SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getSensorState()
{
	return m_SensorState;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setSensorState(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2))
	{
		m_SensorState = value;
		setPresenceVector(0);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isZoomModeValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

jUnsignedByte SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getZoomMode()
{
	return m_ZoomMode;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setZoomMode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3))
	{
		m_ZoomMode = value;
		setPresenceVector(1);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isZoomLevelValid() const
{
	if (checkPresenceVector(2))
	{
		return true;
	}
	return false;
}

double SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getZoomLevel()
{
	double value;
	
	double scaleFactor = ( 100.0 - 0.0 ) / 65535.0;
	double bias = 0.0;
	
	value = m_ZoomLevel * scaleFactor + bias;
	
	return value;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setZoomLevel(double value)
{
	if ((value >= 0.0) && (value <= 100.0))
	{
		double scaleFactor = ( 100.0 - 0.0 ) / 65535.0;
		double bias = 0.0;
		
		m_ZoomLevel= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(2);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isFocalLengthValid() const
{
	if (checkPresenceVector(3))
	{
		return true;
	}
	return false;
}

double SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getFocalLength()
{
	double value;
	
	double scaleFactor = ( 2.0 - 0.0 ) / 4.294967295E9;
	double bias = 0.0;
	
	value = m_FocalLength * scaleFactor + bias;
	
	return value;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setFocalLength(double value)
{
	if ((value >= 0.0) && (value <= 2.0))
	{
		double scaleFactor = ( 2.0 - 0.0 ) / 4.294967295E9;
		double bias = 0.0;
		
		m_FocalLength= (jUnsignedInteger)((value - bias) / scaleFactor);
		setPresenceVector(3);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isHorizontalFieldOfViewValid() const
{
	if (checkPresenceVector(4))
	{
		return true;
	}
	return false;
}

double SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getHorizontalFieldOfView()
{
	double value;
	
	double scaleFactor = ( 3.141592653589793 - -3.141592653589793 ) / 4.294967295E9;
	double bias = -3.141592653589793;
	
	value = m_HorizontalFieldOfView * scaleFactor + bias;
	
	return value;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setHorizontalFieldOfView(double value)
{
	if ((value >= -3.141592653589793) && (value <= 3.141592653589793))
	{
		double scaleFactor = ( 3.141592653589793 - -3.141592653589793 ) / 4.294967295E9;
		double bias = -3.141592653589793;
		
		m_HorizontalFieldOfView= (jUnsignedInteger)((value - bias) / scaleFactor);
		setPresenceVector(4);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isVerticalFieldOfViewValid() const
{
	if (checkPresenceVector(5))
	{
		return true;
	}
	return false;
}

double SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getVerticalFieldOfView()
{
	double value;
	
	double scaleFactor = ( 3.141592653589793 - -3.141592653589793 ) / 4.294967295E9;
	double bias = -3.141592653589793;
	
	value = m_VerticalFieldOfView * scaleFactor + bias;
	
	return value;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setVerticalFieldOfView(double value)
{
	if ((value >= -3.141592653589793) && (value <= 3.141592653589793))
	{
		double scaleFactor = ( 3.141592653589793 - -3.141592653589793 ) / 4.294967295E9;
		double bias = -3.141592653589793;
		
		m_VerticalFieldOfView= (jUnsignedInteger)((value - bias) / scaleFactor);
		setPresenceVector(5);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isFocusModeValid() const
{
	if (checkPresenceVector(6))
	{
		return true;
	}
	return false;
}

jUnsignedByte SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getFocusMode()
{
	return m_FocusMode;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setFocusMode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		m_FocusMode = value;
		setPresenceVector(6);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isFocusValueValid() const
{
	if (checkPresenceVector(7))
	{
		return true;
	}
	return false;
}

double SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getFocusValue()
{
	double value;
	
	double scaleFactor = ( 100.0 - 0.0 ) / 65535.0;
	double bias = 0.0;
	
	value = m_FocusValue * scaleFactor + bias;
	
	return value;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setFocusValue(double value)
{
	if ((value >= 0.0) && (value <= 100.0))
	{
		double scaleFactor = ( 100.0 - 0.0 ) / 65535.0;
		double bias = 0.0;
		
		m_FocusValue= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(7);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isWhiteBalanceValid() const
{
	if (checkPresenceVector(8))
	{
		return true;
	}
	return false;
}

jUnsignedByte SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getWhiteBalance()
{
	return m_WhiteBalance;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setWhiteBalance(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6))
	{
		m_WhiteBalance = value;
		setPresenceVector(8);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isImagingModeValid() const
{
	if (checkPresenceVector(9))
	{
		return true;
	}
	return false;
}

jUnsignedByte SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getImagingMode()
{
	return m_ImagingMode;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setImagingMode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3))
	{
		m_ImagingMode = value;
		setPresenceVector(9);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isExposureModeValid() const
{
	if (checkPresenceVector(10))
	{
		return true;
	}
	return false;
}

jUnsignedByte SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getExposureMode()
{
	return m_ExposureMode;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setExposureMode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3))
	{
		m_ExposureMode = value;
		setPresenceVector(10);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isMeteringModeValid() const
{
	if (checkPresenceVector(11))
	{
		return true;
	}
	return false;
}

jUnsignedByte SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getMeteringMode()
{
	return m_MeteringMode;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setMeteringMode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2))
	{
		m_MeteringMode = value;
		setPresenceVector(11);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isShutterSpeedValid() const
{
	if (checkPresenceVector(12))
	{
		return true;
	}
	return false;
}

double SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getShutterSpeed()
{
	double value;
	
	double scaleFactor = ( 60.0 - 0.0 ) / 65535.0;
	double bias = 0.0;
	
	value = m_ShutterSpeed * scaleFactor + bias;
	
	return value;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setShutterSpeed(double value)
{
	if ((value >= 0.0) && (value <= 60.0))
	{
		double scaleFactor = ( 60.0 - 0.0 ) / 65535.0;
		double bias = 0.0;
		
		m_ShutterSpeed= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(12);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isApertureValid() const
{
	if (checkPresenceVector(13))
	{
		return true;
	}
	return false;
}

double SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getAperture()
{
	double value;
	
	double scaleFactor = ( 128.0 - 0.1 ) / 65535.0;
	double bias = 0.1;
	
	value = m_Aperture * scaleFactor + bias;
	
	return value;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setAperture(double value)
{
	if ((value >= 0.1) && (value <= 128.0))
	{
		double scaleFactor = ( 128.0 - 0.1 ) / 65535.0;
		double bias = 0.1;
		
		m_Aperture= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(13);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isLightSensitivityValid() const
{
	if (checkPresenceVector(14))
	{
		return true;
	}
	return false;
}

jUnsignedByte SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getLightSensitivity()
{
	return m_LightSensitivity;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setLightSensitivity(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6))
	{
		m_LightSensitivity = value;
		setPresenceVector(14);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::isImageStablizationValid() const
{
	if (checkPresenceVector(15))
	{
		return true;
	}
	return false;
}

jUnsignedByte SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getImageStablization()
{
	return m_ImageStablization;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::setImageStablization(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		m_ImageStablization = value;
		setPresenceVector(15);
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
const unsigned int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += sizeof(jUnsignedShortInteger);
	if (checkPresenceVector(0))
	{
		size += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(1))
	{
		size += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(2))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(3))
	{
		size += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(4))
	{
		size += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(5))
	{
		size += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(6))
	{
		size += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(7))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(8))
	{
		size += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(9))
	{
		size += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(10))
	{
		size += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(11))
	{
		size += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(12))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(13))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(14))
	{
		size += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(15))
	{
		size += sizeof(jUnsignedByte);
	}
	
	return size;
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_PresenceVectorTemp;
	
	m_PresenceVectorTemp = JSIDL_v_1_0::correctEndianness(m_PresenceVector);
	memcpy(bytes + pos, &m_PresenceVectorTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
	jUnsignedShortInteger m_SensorIDTemp;
	
	m_SensorIDTemp = JSIDL_v_1_0::correctEndianness(m_SensorID);
	memcpy(bytes + pos, &m_SensorIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	if (checkPresenceVector(0))
	{
		jUnsignedByte m_SensorStateTemp;
		
		m_SensorStateTemp = JSIDL_v_1_0::correctEndianness(m_SensorState);
		memcpy(bytes + pos, &m_SensorStateTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(1))
	{
		jUnsignedByte m_ZoomModeTemp;
		
		m_ZoomModeTemp = JSIDL_v_1_0::correctEndianness(m_ZoomMode);
		memcpy(bytes + pos, &m_ZoomModeTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(2))
	{
		jUnsignedShortInteger m_ZoomLevelTemp;
		
		m_ZoomLevelTemp = JSIDL_v_1_0::correctEndianness(m_ZoomLevel);
		memcpy(bytes + pos, &m_ZoomLevelTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(3))
	{
		jUnsignedInteger m_FocalLengthTemp;
		
		m_FocalLengthTemp = JSIDL_v_1_0::correctEndianness(m_FocalLength);
		memcpy(bytes + pos, &m_FocalLengthTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(4))
	{
		jUnsignedInteger m_HorizontalFieldOfViewTemp;
		
		m_HorizontalFieldOfViewTemp = JSIDL_v_1_0::correctEndianness(m_HorizontalFieldOfView);
		memcpy(bytes + pos, &m_HorizontalFieldOfViewTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(5))
	{
		jUnsignedInteger m_VerticalFieldOfViewTemp;
		
		m_VerticalFieldOfViewTemp = JSIDL_v_1_0::correctEndianness(m_VerticalFieldOfView);
		memcpy(bytes + pos, &m_VerticalFieldOfViewTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(6))
	{
		jUnsignedByte m_FocusModeTemp;
		
		m_FocusModeTemp = JSIDL_v_1_0::correctEndianness(m_FocusMode);
		memcpy(bytes + pos, &m_FocusModeTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(7))
	{
		jUnsignedShortInteger m_FocusValueTemp;
		
		m_FocusValueTemp = JSIDL_v_1_0::correctEndianness(m_FocusValue);
		memcpy(bytes + pos, &m_FocusValueTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(8))
	{
		jUnsignedByte m_WhiteBalanceTemp;
		
		m_WhiteBalanceTemp = JSIDL_v_1_0::correctEndianness(m_WhiteBalance);
		memcpy(bytes + pos, &m_WhiteBalanceTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(9))
	{
		jUnsignedByte m_ImagingModeTemp;
		
		m_ImagingModeTemp = JSIDL_v_1_0::correctEndianness(m_ImagingMode);
		memcpy(bytes + pos, &m_ImagingModeTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(10))
	{
		jUnsignedByte m_ExposureModeTemp;
		
		m_ExposureModeTemp = JSIDL_v_1_0::correctEndianness(m_ExposureMode);
		memcpy(bytes + pos, &m_ExposureModeTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(11))
	{
		jUnsignedByte m_MeteringModeTemp;
		
		m_MeteringModeTemp = JSIDL_v_1_0::correctEndianness(m_MeteringMode);
		memcpy(bytes + pos, &m_MeteringModeTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(12))
	{
		jUnsignedShortInteger m_ShutterSpeedTemp;
		
		m_ShutterSpeedTemp = JSIDL_v_1_0::correctEndianness(m_ShutterSpeed);
		memcpy(bytes + pos, &m_ShutterSpeedTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(13))
	{
		jUnsignedShortInteger m_ApertureTemp;
		
		m_ApertureTemp = JSIDL_v_1_0::correctEndianness(m_Aperture);
		memcpy(bytes + pos, &m_ApertureTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(14))
	{
		jUnsignedByte m_LightSensitivityTemp;
		
		m_LightSensitivityTemp = JSIDL_v_1_0::correctEndianness(m_LightSensitivity);
		memcpy(bytes + pos, &m_LightSensitivityTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(15))
	{
		jUnsignedByte m_ImageStablizationTemp;
		
		m_ImageStablizationTemp = JSIDL_v_1_0::correctEndianness(m_ImageStablization);
		memcpy(bytes + pos, &m_ImageStablizationTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_PresenceVectorTemp;
	
	memcpy(&m_PresenceVectorTemp, bytes + pos, sizeof(jUnsignedInteger));
	m_PresenceVector = JSIDL_v_1_0::correctEndianness(m_PresenceVectorTemp);
	pos += sizeof(jUnsignedInteger);
	jUnsignedShortInteger m_SensorIDTemp;
	
	memcpy(&m_SensorIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SensorID = JSIDL_v_1_0::correctEndianness(m_SensorIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	if (checkPresenceVector(0))
	{
		jUnsignedByte m_SensorStateTemp;
		
		memcpy(&m_SensorStateTemp, bytes + pos, sizeof(jUnsignedByte));
		m_SensorState = JSIDL_v_1_0::correctEndianness(m_SensorStateTemp);
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(1))
	{
		jUnsignedByte m_ZoomModeTemp;
		
		memcpy(&m_ZoomModeTemp, bytes + pos, sizeof(jUnsignedByte));
		m_ZoomMode = JSIDL_v_1_0::correctEndianness(m_ZoomModeTemp);
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(2))
	{
		jUnsignedShortInteger m_ZoomLevelTemp;
		
		memcpy(&m_ZoomLevelTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_ZoomLevel = JSIDL_v_1_0::correctEndianness(m_ZoomLevelTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(3))
	{
		jUnsignedInteger m_FocalLengthTemp;
		
		memcpy(&m_FocalLengthTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_FocalLength = JSIDL_v_1_0::correctEndianness(m_FocalLengthTemp);
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(4))
	{
		jUnsignedInteger m_HorizontalFieldOfViewTemp;
		
		memcpy(&m_HorizontalFieldOfViewTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_HorizontalFieldOfView = JSIDL_v_1_0::correctEndianness(m_HorizontalFieldOfViewTemp);
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(5))
	{
		jUnsignedInteger m_VerticalFieldOfViewTemp;
		
		memcpy(&m_VerticalFieldOfViewTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_VerticalFieldOfView = JSIDL_v_1_0::correctEndianness(m_VerticalFieldOfViewTemp);
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(6))
	{
		jUnsignedByte m_FocusModeTemp;
		
		memcpy(&m_FocusModeTemp, bytes + pos, sizeof(jUnsignedByte));
		m_FocusMode = JSIDL_v_1_0::correctEndianness(m_FocusModeTemp);
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(7))
	{
		jUnsignedShortInteger m_FocusValueTemp;
		
		memcpy(&m_FocusValueTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_FocusValue = JSIDL_v_1_0::correctEndianness(m_FocusValueTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(8))
	{
		jUnsignedByte m_WhiteBalanceTemp;
		
		memcpy(&m_WhiteBalanceTemp, bytes + pos, sizeof(jUnsignedByte));
		m_WhiteBalance = JSIDL_v_1_0::correctEndianness(m_WhiteBalanceTemp);
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(9))
	{
		jUnsignedByte m_ImagingModeTemp;
		
		memcpy(&m_ImagingModeTemp, bytes + pos, sizeof(jUnsignedByte));
		m_ImagingMode = JSIDL_v_1_0::correctEndianness(m_ImagingModeTemp);
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(10))
	{
		jUnsignedByte m_ExposureModeTemp;
		
		memcpy(&m_ExposureModeTemp, bytes + pos, sizeof(jUnsignedByte));
		m_ExposureMode = JSIDL_v_1_0::correctEndianness(m_ExposureModeTemp);
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(11))
	{
		jUnsignedByte m_MeteringModeTemp;
		
		memcpy(&m_MeteringModeTemp, bytes + pos, sizeof(jUnsignedByte));
		m_MeteringMode = JSIDL_v_1_0::correctEndianness(m_MeteringModeTemp);
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(12))
	{
		jUnsignedShortInteger m_ShutterSpeedTemp;
		
		memcpy(&m_ShutterSpeedTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_ShutterSpeed = JSIDL_v_1_0::correctEndianness(m_ShutterSpeedTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(13))
	{
		jUnsignedShortInteger m_ApertureTemp;
		
		memcpy(&m_ApertureTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_Aperture = JSIDL_v_1_0::correctEndianness(m_ApertureTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(14))
	{
		jUnsignedByte m_LightSensitivityTemp;
		
		memcpy(&m_LightSensitivityTemp, bytes + pos, sizeof(jUnsignedByte));
		m_LightSensitivity = JSIDL_v_1_0::correctEndianness(m_LightSensitivityTemp);
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(15))
	{
		jUnsignedByte m_ImageStablizationTemp;
		
		memcpy(&m_ImageStablizationTemp, bytes + pos, sizeof(jUnsignedByte));
		m_ImageStablization = JSIDL_v_1_0::correctEndianness(m_ImageStablizationTemp);
		pos += sizeof(jUnsignedByte);
	}
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec &SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::operator=(const VisualSensorConfigurationRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_SensorID = value.m_SensorID;
	m_SensorState = value.m_SensorState;
	m_ZoomMode = value.m_ZoomMode;
	m_ZoomLevel = value.m_ZoomLevel;
	m_FocalLength = value.m_FocalLength;
	m_HorizontalFieldOfView = value.m_HorizontalFieldOfView;
	m_VerticalFieldOfView = value.m_VerticalFieldOfView;
	m_FocusMode = value.m_FocusMode;
	m_FocusValue = value.m_FocusValue;
	m_WhiteBalance = value.m_WhiteBalance;
	m_ImagingMode = value.m_ImagingMode;
	m_ExposureMode = value.m_ExposureMode;
	m_MeteringMode = value.m_MeteringMode;
	m_ShutterSpeed = value.m_ShutterSpeed;
	m_Aperture = value.m_Aperture;
	m_LightSensitivity = value.m_LightSensitivity;
	m_ImageStablization = value.m_ImageStablization;
	
	return *this;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::operator==(const VisualSensorConfigurationRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	if (m_SensorState != value.m_SensorState)
	{
		return false;
	}
	if (m_ZoomMode != value.m_ZoomMode)
	{
		return false;
	}
	if (m_ZoomLevel != value.m_ZoomLevel)
	{
		return false;
	}
	if (m_FocalLength != value.m_FocalLength)
	{
		return false;
	}
	if (m_HorizontalFieldOfView != value.m_HorizontalFieldOfView)
	{
		return false;
	}
	if (m_VerticalFieldOfView != value.m_VerticalFieldOfView)
	{
		return false;
	}
	if (m_FocusMode != value.m_FocusMode)
	{
		return false;
	}
	if (m_FocusValue != value.m_FocusValue)
	{
		return false;
	}
	if (m_WhiteBalance != value.m_WhiteBalance)
	{
		return false;
	}
	if (m_ImagingMode != value.m_ImagingMode)
	{
		return false;
	}
	if (m_ExposureMode != value.m_ExposureMode)
	{
		return false;
	}
	if (m_MeteringMode != value.m_MeteringMode)
	{
		return false;
	}
	if (m_ShutterSpeed != value.m_ShutterSpeed)
	{
		return false;
	}
	if (m_Aperture != value.m_Aperture)
	{
		return false;
	}
	if (m_LightSensitivity != value.m_LightSensitivity)
	{
		return false;
	}
	if (m_ImageStablization != value.m_ImageStablization)
	{
		return false;
	}
	
	return true;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::operator!=(const VisualSensorConfigurationRec &value) const
{
	return !((*this) == value);
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::VisualSensorConfigurationRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_SensorID = 0;
	m_SensorState = 0;
	m_ZoomMode = 0;
	m_ZoomLevel = 0;
	m_FocalLength = 0;
	m_HorizontalFieldOfView = 0;
	m_VerticalFieldOfView = 0;
	m_FocusMode = 0;
	m_FocusValue = 0;
	m_WhiteBalance = 0;
	m_ImagingMode = 0;
	m_ExposureMode = 0;
	m_MeteringMode = 0;
	m_ShutterSpeed = 0;
	m_Aperture = 0;
	m_LightSensitivity = 0;
	m_ImageStablization = 0;
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::VisualSensorConfigurationRec(const VisualSensorConfigurationRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	m_SensorID = 0;
	m_SensorState = 0;
	m_ZoomMode = 0;
	m_ZoomLevel = 0;
	m_FocalLength = 0;
	m_HorizontalFieldOfView = 0;
	m_VerticalFieldOfView = 0;
	m_FocusMode = 0;
	m_FocusValue = 0;
	m_WhiteBalance = 0;
	m_ImagingMode = 0;
	m_ExposureMode = 0;
	m_MeteringMode = 0;
	m_ShutterSpeed = 0;
	m_Aperture = 0;
	m_LightSensitivity = 0;
	m_ImageStablization = 0;
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_SensorID = value.m_SensorID;
	m_SensorState = value.m_SensorState;
	m_ZoomMode = value.m_ZoomMode;
	m_ZoomLevel = value.m_ZoomLevel;
	m_FocalLength = value.m_FocalLength;
	m_HorizontalFieldOfView = value.m_HorizontalFieldOfView;
	m_VerticalFieldOfView = value.m_VerticalFieldOfView;
	m_FocusMode = value.m_FocusMode;
	m_FocusValue = value.m_FocusValue;
	m_WhiteBalance = value.m_WhiteBalance;
	m_ImagingMode = value.m_ImagingMode;
	m_ExposureMode = value.m_ExposureMode;
	m_MeteringMode = value.m_MeteringMode;
	m_ShutterSpeed = value.m_ShutterSpeed;
	m_Aperture = value.m_Aperture;
	m_LightSensitivity = value.m_LightSensitivity;
	m_ImageStablization = value.m_ImageStablization;
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec::~VisualSensorConfigurationRec()
{
}

unsigned int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::getNumberOfElements() const
{
	return (unsigned int) m_VisualSensorConfigurationRec.size();
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationRec* const SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::getElement(unsigned int index)
{
	return &m_VisualSensorConfigurationRec.at(index);
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::setElement(unsigned int index, const VisualSensorConfigurationRec &value)
{
	if(m_VisualSensorConfigurationRec.size()-1 < index)
	{
		return 1;
	}
	
	m_VisualSensorConfigurationRec.at(index) = value;
	m_VisualSensorConfigurationRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::addElement(const VisualSensorConfigurationRec &value)
{
	m_VisualSensorConfigurationRec.push_back(value);
	m_VisualSensorConfigurationRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::deleteElement(unsigned int index)
{
	if(m_VisualSensorConfigurationRec.size()-1 < index)
	{
		return 1;
	}
	
	m_VisualSensorConfigurationRec.erase(m_VisualSensorConfigurationRec.begin()+index);
	return 0;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::deleteLastElement()
{
	m_VisualSensorConfigurationRec.pop_back();
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
const unsigned int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_VisualSensorConfigurationRec.size(); i++)
	{
		size += m_VisualSensorConfigurationRec[i].getSize();
	}
	
	return size;
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_VisualSensorConfigurationRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_VisualSensorConfigurationRec.size(); i++)
	{
		m_VisualSensorConfigurationRec[i].encode(bytes + pos);
		pos += m_VisualSensorConfigurationRec[i].getSize();
	}
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_VisualSensorConfigurationRec.resize(size);
	for (int i = 0; i < m_VisualSensorConfigurationRec.size(); i++)
	{
		m_VisualSensorConfigurationRec[i].decode(bytes + pos);
		pos += m_VisualSensorConfigurationRec[i].getSize();
	}
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList &SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::operator=(const VisualSensorConfigurationList &value)
{
	m_VisualSensorConfigurationRec.clear();
	
	for (int i = 0; i < value.m_VisualSensorConfigurationRec.size(); i++)
	{
		m_VisualSensorConfigurationRec.push_back(value.m_VisualSensorConfigurationRec[i]);
		m_VisualSensorConfigurationRec[i].setParent(this);
	}
	
	return *this;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::operator==(const VisualSensorConfigurationList &value) const
{
	for (int i = 0; i < m_VisualSensorConfigurationRec.size(); i++)
	{
		if (m_VisualSensorConfigurationRec[i] !=  value.m_VisualSensorConfigurationRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::operator!=(const VisualSensorConfigurationList &value) const
{
	return !((*this) == value);
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_VisualSensorConfigurationRec.size(); i++)
	{
		m_VisualSensorConfigurationRec[i].setParent(this);
	}
	/// No Initialization of m_VisualSensorConfigurationRec necessary.
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::VisualSensorConfigurationList(const VisualSensorConfigurationList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_VisualSensorConfigurationRec.size(); i++)
	{
		m_VisualSensorConfigurationRec[i].setParent(this);
	}
	/// No Initialization of m_VisualSensorConfigurationRec necessary.
	
	/// Copy the values
	m_VisualSensorConfigurationRec.clear();
	
	for (int i = 0; i < value.m_VisualSensorConfigurationRec.size(); i++)
	{
		m_VisualSensorConfigurationRec.push_back(value.m_VisualSensorConfigurationRec[i]);
		m_VisualSensorConfigurationRec[i].setParent(this);
	}
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList::~VisualSensorConfigurationList()
{
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationList* const SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::getVisualSensorConfigurationList()
{
	return &m_VisualSensorConfigurationList;
}

int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::setVisualSensorConfigurationList(const VisualSensorConfigurationList &value)
{
	m_VisualSensorConfigurationList = value;
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
const unsigned int SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::getSize()
{
	unsigned int size = 0;
	
	size += m_RequestIdRec.getSize();
	size += m_VisualSensorConfigurationList.getSize();
	
	return size;
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIdRec.encode(bytes + pos);
	pos += m_RequestIdRec.getSize();
	m_VisualSensorConfigurationList.encode(bytes + pos);
	pos += m_VisualSensorConfigurationList.getSize();
}

void SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIdRec.decode(bytes + pos);
	pos += m_RequestIdRec.getSize();
	m_VisualSensorConfigurationList.decode(bytes + pos);
	pos += m_VisualSensorConfigurationList.getSize();
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence &SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::operator=(const VisualSensorConfigurationSequence &value)
{
	m_RequestIdRec = value.m_RequestIdRec;
	m_RequestIdRec.setParent(this);
	m_RequestIdRec = value.m_RequestIdRec;
	m_VisualSensorConfigurationList = value.m_VisualSensorConfigurationList;
	m_VisualSensorConfigurationList.setParent(this);
	m_VisualSensorConfigurationList = value.m_VisualSensorConfigurationList;
	
	return *this;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::operator==(const VisualSensorConfigurationSequence &value) const
{
	if (m_RequestIdRec != value.m_RequestIdRec)
	{
		return false;
	}
	
	if (m_RequestIdRec != value.m_RequestIdRec)
	{
		return false;
	}
	if (m_VisualSensorConfigurationList != value.m_VisualSensorConfigurationList)
	{
		return false;
	}
	
	if (m_VisualSensorConfigurationList != value.m_VisualSensorConfigurationList)
	{
		return false;
	}
	
	return true;
}

bool SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::operator!=(const VisualSensorConfigurationSequence &value) const
{
	return !((*this) == value);
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationSequence()
{
	m_parent = NULL;
	m_RequestIdRec.setParent(this);
	/// No Initialization of m_RequestIdRec necessary.
	m_VisualSensorConfigurationList.setParent(this);
	/// No Initialization of m_VisualSensorConfigurationList necessary.
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::VisualSensorConfigurationSequence(const VisualSensorConfigurationSequence &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestIdRec.setParent(this);
	/// No Initialization of m_RequestIdRec necessary.
	m_VisualSensorConfigurationList.setParent(this);
	/// No Initialization of m_VisualSensorConfigurationList necessary.
	
	/// Copy the values
	m_RequestIdRec = value.m_RequestIdRec;
	m_RequestIdRec.setParent(this);
	m_RequestIdRec = value.m_RequestIdRec;
	m_VisualSensorConfigurationList = value.m_VisualSensorConfigurationList;
	m_VisualSensorConfigurationList.setParent(this);
	m_VisualSensorConfigurationList = value.m_VisualSensorConfigurationList;
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence::~VisualSensorConfigurationSequence()
{
}

SetVisualSensorConfiguration::Body::VisualSensorConfigurationSequence* const SetVisualSensorConfiguration::Body::getVisualSensorConfigurationSequence()
{
	return &m_VisualSensorConfigurationSequence;
}

int SetVisualSensorConfiguration::Body::setVisualSensorConfigurationSequence(const VisualSensorConfigurationSequence &value)
{
	m_VisualSensorConfigurationSequence = value;
	setParentPresenceVector();
	return 0;
}

void SetVisualSensorConfiguration::Body::setParentPresenceVector()
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
const unsigned int SetVisualSensorConfiguration::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_VisualSensorConfigurationSequence.getSize();
	
	return size;
}

void SetVisualSensorConfiguration::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_VisualSensorConfigurationSequence.encode(bytes + pos);
	pos += m_VisualSensorConfigurationSequence.getSize();
}

void SetVisualSensorConfiguration::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_VisualSensorConfigurationSequence.decode(bytes + pos);
	pos += m_VisualSensorConfigurationSequence.getSize();
}

SetVisualSensorConfiguration::Body &SetVisualSensorConfiguration::Body::operator=(const Body &value)
{
	m_VisualSensorConfigurationSequence = value.m_VisualSensorConfigurationSequence;
	m_VisualSensorConfigurationSequence.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool SetVisualSensorConfiguration::Body::operator==(const Body &value) const
{
	if (m_VisualSensorConfigurationSequence != value.m_VisualSensorConfigurationSequence)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool SetVisualSensorConfiguration::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

SetVisualSensorConfiguration::Body::Body()
{
	m_VisualSensorConfigurationSequence.setParent(this);
	/// No Initialization of m_VisualSensorConfigurationSequence necessary.
}

SetVisualSensorConfiguration::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_VisualSensorConfigurationSequence.setParent(this);
	/// No Initialization of m_VisualSensorConfigurationSequence necessary.
	
	/// Copy the values
	m_VisualSensorConfigurationSequence = value.m_VisualSensorConfigurationSequence;
	m_VisualSensorConfigurationSequence.setParent(this);
	/// This code is currently not supported
}

SetVisualSensorConfiguration::Body::~Body()
{
}

SetVisualSensorConfiguration::Body* const SetVisualSensorConfiguration::getBody()
{
	return &m_Body;
}

int SetVisualSensorConfiguration::setBody(const Body &value)
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
const unsigned int SetVisualSensorConfiguration::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void SetVisualSensorConfiguration::encode(unsigned char *bytes)
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

void SetVisualSensorConfiguration::decode(const unsigned char *bytes)
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

SetVisualSensorConfiguration &SetVisualSensorConfiguration::operator=(const SetVisualSensorConfiguration &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool SetVisualSensorConfiguration::operator==(const SetVisualSensorConfiguration &value) const
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

bool SetVisualSensorConfiguration::operator!=(const SetVisualSensorConfiguration &value) const
{
	return !((*this) == value);
}

SetVisualSensorConfiguration::SetVisualSensorConfiguration()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

SetVisualSensorConfiguration::SetVisualSensorConfiguration(const SetVisualSensorConfiguration &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

SetVisualSensorConfiguration::~SetVisualSensorConfiguration()
{
}


}
