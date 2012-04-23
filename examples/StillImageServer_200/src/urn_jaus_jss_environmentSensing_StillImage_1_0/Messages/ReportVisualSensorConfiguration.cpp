#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/ReportVisualSensorConfiguration.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void ReportVisualSensorConfiguration::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportVisualSensorConfiguration::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportVisualSensorConfiguration::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportVisualSensorConfiguration::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportVisualSensorConfiguration::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportVisualSensorConfiguration::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportVisualSensorConfiguration::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportVisualSensorConfiguration::AppHeader::HeaderRec &ReportVisualSensorConfiguration::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportVisualSensorConfiguration::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportVisualSensorConfiguration::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportVisualSensorConfiguration::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4807;
}

ReportVisualSensorConfiguration::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4807;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportVisualSensorConfiguration::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportVisualSensorConfiguration::AppHeader::HeaderRec* const ReportVisualSensorConfiguration::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportVisualSensorConfiguration::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportVisualSensorConfiguration::AppHeader::setParentPresenceVector()
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
const unsigned int ReportVisualSensorConfiguration::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportVisualSensorConfiguration::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportVisualSensorConfiguration::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportVisualSensorConfiguration::AppHeader &ReportVisualSensorConfiguration::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportVisualSensorConfiguration::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportVisualSensorConfiguration::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportVisualSensorConfiguration::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportVisualSensorConfiguration::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportVisualSensorConfiguration::AppHeader::~AppHeader()
{
}

ReportVisualSensorConfiguration::AppHeader* const ReportVisualSensorConfiguration::getAppHeader()
{
	return &m_AppHeader;
}

int ReportVisualSensorConfiguration::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setParent(VisualSensorConfigurationList* parent)
{
	m_parent = parent;
}

void ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getPresenceVector()
{
	return m_PresenceVector;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedInteger) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedInteger)pvBitSet.to_ulong();
	return 0;
}

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedInteger) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

jUnsignedShortInteger ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getSensorID()
{
	return m_SensorID;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isSensorStateValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getSensorState()
{
	return m_SensorState;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setSensorState(jUnsignedByte value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isZoomModeValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getZoomMode()
{
	return m_ZoomMode;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setZoomMode(jUnsignedByte value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isZoomLevelValid() const
{
	if (checkPresenceVector(2))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getZoomLevel()
{
	double value;
	
	double scaleFactor = ( 100.0 - 0.0 ) / 65535.0;
	double bias = 0.0;
	
	value = m_ZoomLevel * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setZoomLevel(double value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isFocalLengthValid() const
{
	if (checkPresenceVector(3))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getFocalLength()
{
	double value;
	
	double scaleFactor = ( 2.0 - 0.0 ) / 4.294967295E9;
	double bias = 0.0;
	
	value = m_FocalLength * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setFocalLength(double value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isHorizontalFieldOfViewValid() const
{
	if (checkPresenceVector(4))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getHorizontalFieldOfView()
{
	double value;
	
	double scaleFactor = ( 3.141592653589793 - -3.141592653589793 ) / 4.294967295E9;
	double bias = -3.141592653589793;
	
	value = m_HorizontalFieldOfView * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setHorizontalFieldOfView(double value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isVerticalFieldOfViewValid() const
{
	if (checkPresenceVector(5))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getVerticalFieldOfView()
{
	double value;
	
	double scaleFactor = ( 3.141592653589793 - -3.141592653589793 ) / 4.294967295E9;
	double bias = -3.141592653589793;
	
	value = m_VerticalFieldOfView * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setVerticalFieldOfView(double value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isFocusModeValid() const
{
	if (checkPresenceVector(6))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getFocusMode()
{
	return m_FocusMode;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setFocusMode(jUnsignedByte value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isFocusValueValid() const
{
	if (checkPresenceVector(7))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getFocusValue()
{
	double value;
	
	double scaleFactor = ( 100.0 - 0.0 ) / 65535.0;
	double bias = 0.0;
	
	value = m_FocusValue * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setFocusValue(double value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isWhiteBalanceValid() const
{
	if (checkPresenceVector(8))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getWhiteBalance()
{
	return m_WhiteBalance;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setWhiteBalance(jUnsignedByte value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isImagingModeValid() const
{
	if (checkPresenceVector(9))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getImagingMode()
{
	return m_ImagingMode;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setImagingMode(jUnsignedByte value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isExposureModeValid() const
{
	if (checkPresenceVector(10))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getExposureMode()
{
	return m_ExposureMode;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setExposureMode(jUnsignedByte value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isMeteringModeValid() const
{
	if (checkPresenceVector(11))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getMeteringMode()
{
	return m_MeteringMode;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setMeteringMode(jUnsignedByte value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isShutterSpeedValid() const
{
	if (checkPresenceVector(12))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getShutterSpeed()
{
	double value;
	
	double scaleFactor = ( 60.0 - 0.0 ) / 65535.0;
	double bias = 0.0;
	
	value = m_ShutterSpeed * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setShutterSpeed(double value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isApertureValid() const
{
	if (checkPresenceVector(13))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getAperture()
{
	double value;
	
	double scaleFactor = ( 128.0 - 0.1 ) / 65535.0;
	double bias = 0.1;
	
	value = m_Aperture * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setAperture(double value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isLightSensitivityValid() const
{
	if (checkPresenceVector(14))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getLightSensitivity()
{
	return m_LightSensitivity;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setLightSensitivity(jUnsignedByte value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::isImageStablizationValid() const
{
	if (checkPresenceVector(15))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getImageStablization()
{
	return m_ImageStablization;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::setImageStablization(jUnsignedByte value)
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
const unsigned int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::getSize()
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

void ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::encode(unsigned char *bytes)
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

void ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::decode(const unsigned char *bytes)
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

ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec &ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::operator=(const VisualSensorConfigurationRec &value)
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::operator==(const VisualSensorConfigurationRec &value) const
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::operator!=(const VisualSensorConfigurationRec &value) const
{
	return !((*this) == value);
}

ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::VisualSensorConfigurationRec()
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

ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::VisualSensorConfigurationRec(const VisualSensorConfigurationRec &value)
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

ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec::~VisualSensorConfigurationRec()
{
}

unsigned int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::getNumberOfElements() const
{
	return (unsigned int) m_VisualSensorConfigurationRec.size();
}

ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationRec* const ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::getElement(unsigned int index)
{
	return &m_VisualSensorConfigurationRec.at(index);
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::setElement(unsigned int index, const VisualSensorConfigurationRec &value)
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

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::addElement(const VisualSensorConfigurationRec &value)
{
	m_VisualSensorConfigurationRec.push_back(value);
	m_VisualSensorConfigurationRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::deleteElement(unsigned int index)
{
	if(m_VisualSensorConfigurationRec.size()-1 < index)
	{
		return 1;
	}
	
	m_VisualSensorConfigurationRec.erase(m_VisualSensorConfigurationRec.begin()+index);
	return 0;
}

int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::deleteLastElement()
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
const unsigned int ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_VisualSensorConfigurationRec.size(); i++)
	{
		size += m_VisualSensorConfigurationRec[i].getSize();
	}
	
	return size;
}

void ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::encode(unsigned char *bytes)
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

void ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::decode(const unsigned char *bytes)
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

ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList &ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::operator=(const VisualSensorConfigurationList &value)
{
	m_VisualSensorConfigurationRec.clear();
	
	for (int i = 0; i < value.m_VisualSensorConfigurationRec.size(); i++)
	{
		m_VisualSensorConfigurationRec.push_back(value.m_VisualSensorConfigurationRec[i]);
		m_VisualSensorConfigurationRec[i].setParent(this);
	}
	
	return *this;
}

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::operator==(const VisualSensorConfigurationList &value) const
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

bool ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::operator!=(const VisualSensorConfigurationList &value) const
{
	return !((*this) == value);
}

ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_VisualSensorConfigurationRec.size(); i++)
	{
		m_VisualSensorConfigurationRec[i].setParent(this);
	}
	/// No Initialization of m_VisualSensorConfigurationRec necessary.
}

ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::VisualSensorConfigurationList(const VisualSensorConfigurationList &value)
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

ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList::~VisualSensorConfigurationList()
{
}

ReportVisualSensorConfiguration::Body::VisualSensorConfigurationList* const ReportVisualSensorConfiguration::Body::getVisualSensorConfigurationList()
{
	return &m_VisualSensorConfigurationList;
}

int ReportVisualSensorConfiguration::Body::setVisualSensorConfigurationList(const VisualSensorConfigurationList &value)
{
	m_VisualSensorConfigurationList = value;
	setParentPresenceVector();
	return 0;
}

void ReportVisualSensorConfiguration::Body::setParentPresenceVector()
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
const unsigned int ReportVisualSensorConfiguration::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_VisualSensorConfigurationList.getSize();
	
	return size;
}

void ReportVisualSensorConfiguration::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_VisualSensorConfigurationList.encode(bytes + pos);
	pos += m_VisualSensorConfigurationList.getSize();
}

void ReportVisualSensorConfiguration::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_VisualSensorConfigurationList.decode(bytes + pos);
	pos += m_VisualSensorConfigurationList.getSize();
}

ReportVisualSensorConfiguration::Body &ReportVisualSensorConfiguration::Body::operator=(const Body &value)
{
	m_VisualSensorConfigurationList = value.m_VisualSensorConfigurationList;
	m_VisualSensorConfigurationList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportVisualSensorConfiguration::Body::operator==(const Body &value) const
{
	if (m_VisualSensorConfigurationList != value.m_VisualSensorConfigurationList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportVisualSensorConfiguration::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportVisualSensorConfiguration::Body::Body()
{
	m_VisualSensorConfigurationList.setParent(this);
	/// No Initialization of m_VisualSensorConfigurationList necessary.
}

ReportVisualSensorConfiguration::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_VisualSensorConfigurationList.setParent(this);
	/// No Initialization of m_VisualSensorConfigurationList necessary.
	
	/// Copy the values
	m_VisualSensorConfigurationList = value.m_VisualSensorConfigurationList;
	m_VisualSensorConfigurationList.setParent(this);
	/// This code is currently not supported
}

ReportVisualSensorConfiguration::Body::~Body()
{
}

ReportVisualSensorConfiguration::Body* const ReportVisualSensorConfiguration::getBody()
{
	return &m_Body;
}

int ReportVisualSensorConfiguration::setBody(const Body &value)
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
const unsigned int ReportVisualSensorConfiguration::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportVisualSensorConfiguration::encode(unsigned char *bytes)
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

void ReportVisualSensorConfiguration::decode(const unsigned char *bytes)
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

ReportVisualSensorConfiguration &ReportVisualSensorConfiguration::operator=(const ReportVisualSensorConfiguration &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportVisualSensorConfiguration::operator==(const ReportVisualSensorConfiguration &value) const
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

bool ReportVisualSensorConfiguration::operator!=(const ReportVisualSensorConfiguration &value) const
{
	return !((*this) == value);
}

ReportVisualSensorConfiguration::ReportVisualSensorConfiguration()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportVisualSensorConfiguration::ReportVisualSensorConfiguration(const ReportVisualSensorConfiguration &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportVisualSensorConfiguration::~ReportVisualSensorConfiguration()
{
}


}
