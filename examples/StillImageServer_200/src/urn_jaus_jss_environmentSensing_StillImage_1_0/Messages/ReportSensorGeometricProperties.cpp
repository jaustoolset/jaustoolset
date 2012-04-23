#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/ReportSensorGeometricProperties.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void ReportSensorGeometricProperties::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportSensorGeometricProperties::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportSensorGeometricProperties::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportSensorGeometricProperties::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportSensorGeometricProperties::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportSensorGeometricProperties::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportSensorGeometricProperties::AppHeader::HeaderRec &ReportSensorGeometricProperties::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportSensorGeometricProperties::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportSensorGeometricProperties::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4805;
}

ReportSensorGeometricProperties::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4805;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportSensorGeometricProperties::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportSensorGeometricProperties::AppHeader::HeaderRec* const ReportSensorGeometricProperties::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportSensorGeometricProperties::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportSensorGeometricProperties::AppHeader::setParentPresenceVector()
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
const unsigned int ReportSensorGeometricProperties::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportSensorGeometricProperties::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportSensorGeometricProperties::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportSensorGeometricProperties::AppHeader &ReportSensorGeometricProperties::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportSensorGeometricProperties::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportSensorGeometricProperties::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportSensorGeometricProperties::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportSensorGeometricProperties::AppHeader::~AppHeader()
{
}

ReportSensorGeometricProperties::AppHeader* const ReportSensorGeometricProperties::getAppHeader()
{
	return &m_AppHeader;
}

int ReportSensorGeometricProperties::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::setParent(GeometricPropertiesList* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::setParent(GeometricPropertiesSequence* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::getSensorID()
{
	return m_SensorID;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::setSensorID(jUnsignedShortInteger value)
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
const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::encode(unsigned char *bytes)
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

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::decode(const unsigned char *bytes)
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

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec &ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::operator=(const SensorIdRec &value)
{
	m_SensorID = value.m_SensorID;
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::operator==(const SensorIdRec &value) const
{
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	
	return true;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::operator!=(const SensorIdRec &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::SensorIdRec()
{
	m_parent = NULL;
	m_SensorID = 0;
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::SensorIdRec(const SensorIdRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec::~SensorIdRec()
{
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::SensorIdRec* const ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::getSensorIdRec()
{
	return &m_SensorIdRec;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::setSensorIdRec(const SensorIdRec &value)
{
	m_SensorIdRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::setParent(GeometricPropertiesSequence* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::setParent(GeometricPropertiesVariant* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::getFieldValue() const
{
	return m_FieldValue;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::setFieldValue(jUnsignedByte fieldValue)
{
	if(fieldValue > 0)
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
const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	switch(m_FieldValue)
	{
	}
	
	return size;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::encode(unsigned char *bytes)
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
	}
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::decode(const unsigned char *bytes)
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
	}
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant &ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::operator=(const NoGeometricPropertiesVariant &value)
{
	m_FieldValue = value.m_FieldValue;
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::operator==(const NoGeometricPropertiesVariant &value) const
{
	if (m_FieldValue != value.m_FieldValue)
	{
		return false;
	}
	
	return true;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::operator!=(const NoGeometricPropertiesVariant &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::NoGeometricPropertiesVariant()
{
	m_parent = NULL;
	m_FieldValue = 0;
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::NoGeometricPropertiesVariant(const NoGeometricPropertiesVariant &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_FieldValue = 0;
	
	/// Copy the values
	m_FieldValue = value.m_FieldValue;
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant::~NoGeometricPropertiesVariant()
{
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::NoGeometricPropertiesVariant* const ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::getNoGeometricPropertiesVariant()
{
	return &m_NoGeometricPropertiesVariant;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::setNoGeometricPropertiesVariant(const NoGeometricPropertiesVariant &value)
{
	m_NoGeometricPropertiesVariant = value;
	setParentPresenceVector();
	return 0;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::setParent(GeometricPropertiesVariant* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::setParent(StaticGeometricPropertiesRec* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::getPositionVectorDimensionSize() const
{
	return m_PositionVectorDimensionSize;
}

double ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::getPositionVectorElement(unsigned int PositionVectorDimension)
{
	double value;
	unsigned int index = PositionVectorDimension;
	
	double scaleFactor = ( 30.0 - -30.0 ) / 4.294967295E9;
	double bias = -30.0;
	
	value = m_PositionVectorElement[index] * scaleFactor + bias;
	
	return value;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::setPositionVectorElement(unsigned int PositionVectorDimension, double value)
{
	if ((value >= -30.0) && (value <= 30.0))
	{
		unsigned int index = PositionVectorDimension;
		
		double scaleFactor = ( 30.0 - -30.0 ) / 4.294967295E9;
		double bias = -30.0;
		
		m_PositionVectorElement[index]= (jUnsignedInteger)((value - bias) / scaleFactor);
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
const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger) * 3;
	
	return size;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_PositionVectorElementTemp;
	
	for (unsigned int i = 0; i < 3; i++)
	{
		m_PositionVectorElementTemp = JSIDL_v_1_0::correctEndianness(m_PositionVectorElement[i]);
		memcpy(bytes + pos, &m_PositionVectorElementTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_PositionVectorElementTemp;
	
	for (unsigned int i = 0; i < 3; i++)
	{
		memcpy(&m_PositionVectorElementTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_PositionVectorElement[i] = JSIDL_v_1_0::correctEndianness(m_PositionVectorElementTemp);
		pos += sizeof(jUnsignedInteger);
	}
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition &ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::operator=(const SensorPosition &value)
{
	// not yet implemented
	memcpy(m_PositionVectorElement, value.m_PositionVectorElement, sizeof(jUnsignedInteger) * 3);
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::operator==(const SensorPosition &value) const
{
	// not yet implemented
	if (memcmp(m_PositionVectorElement, value.m_PositionVectorElement, sizeof(jUnsignedInteger) * 3) != 0)
	{
		return false;
	}
	
	return true;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::operator!=(const SensorPosition &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::SensorPosition()
{
	m_parent = NULL;
	m_PositionVectorDimensionSize = 3;
	memset( m_PositionVectorElement, 0, sizeof(jUnsignedInteger) * 3);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::SensorPosition(const SensorPosition &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PositionVectorDimensionSize = 3;
	memset( m_PositionVectorElement, 0, sizeof(jUnsignedInteger) * 3);
	
	/// Copy the values
	// not yet implemented
	memcpy(m_PositionVectorElement, value.m_PositionVectorElement, sizeof(jUnsignedInteger) * 3);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition::~SensorPosition()
{
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::SensorPosition* const ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::getSensorPosition()
{
	return &m_SensorPosition;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::setSensorPosition(const SensorPosition &value)
{
	m_SensorPosition = value;
	setParentPresenceVector();
	return 0;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::setParent(StaticGeometricPropertiesRec* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::getUnitQuaternionDimensionSize() const
{
	return m_UnitQuaternionDimensionSize;
}

double ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::getUnitQuaternionElement(unsigned int UnitQuaternionDimension)
{
	double value;
	unsigned int index = UnitQuaternionDimension;
	
	double scaleFactor = ( 1.0 - -1.0 ) / 4.294967295E9;
	double bias = -1.0;
	
	value = m_UnitQuaternionElement[index] * scaleFactor + bias;
	
	return value;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::setUnitQuaternionElement(unsigned int UnitQuaternionDimension, double value)
{
	if ((value >= -1.0) && (value <= 1.0))
	{
		unsigned int index = UnitQuaternionDimension;
		
		double scaleFactor = ( 1.0 - -1.0 ) / 4.294967295E9;
		double bias = -1.0;
		
		m_UnitQuaternionElement[index]= (jUnsignedInteger)((value - bias) / scaleFactor);
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
const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger) * 4;
	
	return size;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_UnitQuaternionElementTemp;
	
	for (unsigned int i = 0; i < 4; i++)
	{
		m_UnitQuaternionElementTemp = JSIDL_v_1_0::correctEndianness(m_UnitQuaternionElement[i]);
		memcpy(bytes + pos, &m_UnitQuaternionElementTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_UnitQuaternionElementTemp;
	
	for (unsigned int i = 0; i < 4; i++)
	{
		memcpy(&m_UnitQuaternionElementTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_UnitQuaternionElement[i] = JSIDL_v_1_0::correctEndianness(m_UnitQuaternionElementTemp);
		pos += sizeof(jUnsignedInteger);
	}
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion &ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::operator=(const UnitQuaternion &value)
{
	// not yet implemented
	memcpy(m_UnitQuaternionElement, value.m_UnitQuaternionElement, sizeof(jUnsignedInteger) * 4);
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::operator==(const UnitQuaternion &value) const
{
	// not yet implemented
	if (memcmp(m_UnitQuaternionElement, value.m_UnitQuaternionElement, sizeof(jUnsignedInteger) * 4) != 0)
	{
		return false;
	}
	
	return true;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::operator!=(const UnitQuaternion &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::UnitQuaternion()
{
	m_parent = NULL;
	m_UnitQuaternionDimensionSize = 4;
	memset( m_UnitQuaternionElement, 0, sizeof(jUnsignedInteger) * 4);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::UnitQuaternion(const UnitQuaternion &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_UnitQuaternionDimensionSize = 4;
	memset( m_UnitQuaternionElement, 0, sizeof(jUnsignedInteger) * 4);
	
	/// Copy the values
	// not yet implemented
	memcpy(m_UnitQuaternionElement, value.m_UnitQuaternionElement, sizeof(jUnsignedInteger) * 4);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion::~UnitQuaternion()
{
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::UnitQuaternion* const ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::getUnitQuaternion()
{
	return &m_UnitQuaternion;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::setUnitQuaternion(const UnitQuaternion &value)
{
	m_UnitQuaternion = value;
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
const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::getSize()
{
	unsigned int size = 0;
	
	size += m_SensorPosition.getSize();
	size += m_UnitQuaternion.getSize();
	
	return size;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SensorPosition.encode(bytes + pos);
	pos += m_SensorPosition.getSize();
	m_UnitQuaternion.encode(bytes + pos);
	pos += m_UnitQuaternion.getSize();
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SensorPosition.decode(bytes + pos);
	pos += m_SensorPosition.getSize();
	m_UnitQuaternion.decode(bytes + pos);
	pos += m_UnitQuaternion.getSize();
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec &ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::operator=(const StaticGeometricPropertiesRec &value)
{
	m_SensorPosition = value.m_SensorPosition;
	m_UnitQuaternion = value.m_UnitQuaternion;
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::operator==(const StaticGeometricPropertiesRec &value) const
{
	if (m_SensorPosition != value.m_SensorPosition)
	{
		return false;
	}
	
	if (m_UnitQuaternion != value.m_UnitQuaternion)
	{
		return false;
	}
	
	return true;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::operator!=(const StaticGeometricPropertiesRec &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::StaticGeometricPropertiesRec()
{
	m_parent = NULL;
	m_SensorPosition.setParent(this);
	/// No Initialization of m_SensorPosition necessary.
	m_UnitQuaternion.setParent(this);
	/// No Initialization of m_UnitQuaternion necessary.
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::StaticGeometricPropertiesRec(const StaticGeometricPropertiesRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorPosition.setParent(this);
	/// No Initialization of m_SensorPosition necessary.
	m_UnitQuaternion.setParent(this);
	/// No Initialization of m_UnitQuaternion necessary.
	
	/// Copy the values
	m_SensorPosition = value.m_SensorPosition;
	m_UnitQuaternion = value.m_UnitQuaternion;
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec::~StaticGeometricPropertiesRec()
{
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::StaticGeometricPropertiesRec* const ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::getStaticGeometricPropertiesRec()
{
	return &m_StaticGeometricPropertiesRec;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::setStaticGeometricPropertiesRec(const StaticGeometricPropertiesRec &value)
{
	m_StaticGeometricPropertiesRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::setParent(GeometricPropertiesVariant* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::getSubsystemID()
{
	return m_SubsystemID;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::setSubsystemID(jUnsignedShortInteger value)
{
	m_SubsystemID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::getNodeID()
{
	return m_NodeID;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::setNodeID(jUnsignedByte value)
{
	m_NodeID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::getComponentID()
{
	return m_ComponentID;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::setComponentID(jUnsignedByte value)
{
	m_ComponentID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::getJointNumber()
{
	return m_JointNumber;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::setJointNumber(jUnsignedByte value)
{
	m_JointNumber = value;
	setParentPresenceVector();
	return 0;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::setParent(ManipulatorGeometricPropertiesRec* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::getPositionVectorDimensionSize() const
{
	return m_PositionVectorDimensionSize;
}

double ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::getPositionVectorElement(unsigned int PositionVectorDimension)
{
	double value;
	unsigned int index = PositionVectorDimension;
	
	double scaleFactor = ( 30.0 - -30.0 ) / 4.294967295E9;
	double bias = -30.0;
	
	value = m_PositionVectorElement[index] * scaleFactor + bias;
	
	return value;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::setPositionVectorElement(unsigned int PositionVectorDimension, double value)
{
	if ((value >= -30.0) && (value <= 30.0))
	{
		unsigned int index = PositionVectorDimension;
		
		double scaleFactor = ( 30.0 - -30.0 ) / 4.294967295E9;
		double bias = -30.0;
		
		m_PositionVectorElement[index]= (jUnsignedInteger)((value - bias) / scaleFactor);
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
const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger) * 3;
	
	return size;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_PositionVectorElementTemp;
	
	for (unsigned int i = 0; i < 3; i++)
	{
		m_PositionVectorElementTemp = JSIDL_v_1_0::correctEndianness(m_PositionVectorElement[i]);
		memcpy(bytes + pos, &m_PositionVectorElementTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_PositionVectorElementTemp;
	
	for (unsigned int i = 0; i < 3; i++)
	{
		memcpy(&m_PositionVectorElementTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_PositionVectorElement[i] = JSIDL_v_1_0::correctEndianness(m_PositionVectorElementTemp);
		pos += sizeof(jUnsignedInteger);
	}
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition &ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::operator=(const SensorPosition &value)
{
	// not yet implemented
	memcpy(m_PositionVectorElement, value.m_PositionVectorElement, sizeof(jUnsignedInteger) * 3);
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::operator==(const SensorPosition &value) const
{
	// not yet implemented
	if (memcmp(m_PositionVectorElement, value.m_PositionVectorElement, sizeof(jUnsignedInteger) * 3) != 0)
	{
		return false;
	}
	
	return true;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::operator!=(const SensorPosition &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::SensorPosition()
{
	m_parent = NULL;
	m_PositionVectorDimensionSize = 3;
	memset( m_PositionVectorElement, 0, sizeof(jUnsignedInteger) * 3);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::SensorPosition(const SensorPosition &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PositionVectorDimensionSize = 3;
	memset( m_PositionVectorElement, 0, sizeof(jUnsignedInteger) * 3);
	
	/// Copy the values
	// not yet implemented
	memcpy(m_PositionVectorElement, value.m_PositionVectorElement, sizeof(jUnsignedInteger) * 3);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition::~SensorPosition()
{
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::SensorPosition* const ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::getSensorPosition()
{
	return &m_SensorPosition;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::setSensorPosition(const SensorPosition &value)
{
	m_SensorPosition = value;
	setParentPresenceVector();
	return 0;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::setParent(ManipulatorGeometricPropertiesRec* parent)
{
	m_parent = parent;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::getUnitQuaternionDimensionSize() const
{
	return m_UnitQuaternionDimensionSize;
}

double ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::getUnitQuaternionElement(unsigned int UnitQuaternionDimension)
{
	double value;
	unsigned int index = UnitQuaternionDimension;
	
	double scaleFactor = ( 1.0 - -1.0 ) / 4.294967295E9;
	double bias = -1.0;
	
	value = m_UnitQuaternionElement[index] * scaleFactor + bias;
	
	return value;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::setUnitQuaternionElement(unsigned int UnitQuaternionDimension, double value)
{
	if ((value >= -1.0) && (value <= 1.0))
	{
		unsigned int index = UnitQuaternionDimension;
		
		double scaleFactor = ( 1.0 - -1.0 ) / 4.294967295E9;
		double bias = -1.0;
		
		m_UnitQuaternionElement[index]= (jUnsignedInteger)((value - bias) / scaleFactor);
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
const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger) * 4;
	
	return size;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_UnitQuaternionElementTemp;
	
	for (unsigned int i = 0; i < 4; i++)
	{
		m_UnitQuaternionElementTemp = JSIDL_v_1_0::correctEndianness(m_UnitQuaternionElement[i]);
		memcpy(bytes + pos, &m_UnitQuaternionElementTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_UnitQuaternionElementTemp;
	
	for (unsigned int i = 0; i < 4; i++)
	{
		memcpy(&m_UnitQuaternionElementTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_UnitQuaternionElement[i] = JSIDL_v_1_0::correctEndianness(m_UnitQuaternionElementTemp);
		pos += sizeof(jUnsignedInteger);
	}
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion &ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::operator=(const UnitQuaternion &value)
{
	// not yet implemented
	memcpy(m_UnitQuaternionElement, value.m_UnitQuaternionElement, sizeof(jUnsignedInteger) * 4);
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::operator==(const UnitQuaternion &value) const
{
	// not yet implemented
	if (memcmp(m_UnitQuaternionElement, value.m_UnitQuaternionElement, sizeof(jUnsignedInteger) * 4) != 0)
	{
		return false;
	}
	
	return true;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::operator!=(const UnitQuaternion &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::UnitQuaternion()
{
	m_parent = NULL;
	m_UnitQuaternionDimensionSize = 4;
	memset( m_UnitQuaternionElement, 0, sizeof(jUnsignedInteger) * 4);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::UnitQuaternion(const UnitQuaternion &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_UnitQuaternionDimensionSize = 4;
	memset( m_UnitQuaternionElement, 0, sizeof(jUnsignedInteger) * 4);
	
	/// Copy the values
	// not yet implemented
	memcpy(m_UnitQuaternionElement, value.m_UnitQuaternionElement, sizeof(jUnsignedInteger) * 4);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion::~UnitQuaternion()
{
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::UnitQuaternion* const ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::getUnitQuaternion()
{
	return &m_UnitQuaternion;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::setUnitQuaternion(const UnitQuaternion &value)
{
	m_UnitQuaternion = value;
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
const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += m_SensorPosition.getSize();
	size += m_UnitQuaternion.getSize();
	
	return size;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SubsystemIDTemp;
	
	m_SubsystemIDTemp = JSIDL_v_1_0::correctEndianness(m_SubsystemID);
	memcpy(bytes + pos, &m_SubsystemIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_NodeIDTemp;
	
	m_NodeIDTemp = JSIDL_v_1_0::correctEndianness(m_NodeID);
	memcpy(bytes + pos, &m_NodeIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_ComponentIDTemp;
	
	m_ComponentIDTemp = JSIDL_v_1_0::correctEndianness(m_ComponentID);
	memcpy(bytes + pos, &m_ComponentIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_JointNumberTemp;
	
	m_JointNumberTemp = JSIDL_v_1_0::correctEndianness(m_JointNumber);
	memcpy(bytes + pos, &m_JointNumberTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	m_SensorPosition.encode(bytes + pos);
	pos += m_SensorPosition.getSize();
	m_UnitQuaternion.encode(bytes + pos);
	pos += m_UnitQuaternion.getSize();
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SubsystemIDTemp;
	
	memcpy(&m_SubsystemIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SubsystemID = JSIDL_v_1_0::correctEndianness(m_SubsystemIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_NodeIDTemp;
	
	memcpy(&m_NodeIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_NodeID = JSIDL_v_1_0::correctEndianness(m_NodeIDTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_ComponentIDTemp;
	
	memcpy(&m_ComponentIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_ComponentID = JSIDL_v_1_0::correctEndianness(m_ComponentIDTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_JointNumberTemp;
	
	memcpy(&m_JointNumberTemp, bytes + pos, sizeof(jUnsignedByte));
	m_JointNumber = JSIDL_v_1_0::correctEndianness(m_JointNumberTemp);
	pos += sizeof(jUnsignedByte);
	m_SensorPosition.decode(bytes + pos);
	pos += m_SensorPosition.getSize();
	m_UnitQuaternion.decode(bytes + pos);
	pos += m_UnitQuaternion.getSize();
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec &ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::operator=(const ManipulatorGeometricPropertiesRec &value)
{
	m_SubsystemID = value.m_SubsystemID;
	m_NodeID = value.m_NodeID;
	m_ComponentID = value.m_ComponentID;
	m_JointNumber = value.m_JointNumber;
	m_SensorPosition = value.m_SensorPosition;
	m_UnitQuaternion = value.m_UnitQuaternion;
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::operator==(const ManipulatorGeometricPropertiesRec &value) const
{
	if (m_SubsystemID != value.m_SubsystemID)
	{
		return false;
	}
	if (m_NodeID != value.m_NodeID)
	{
		return false;
	}
	if (m_ComponentID != value.m_ComponentID)
	{
		return false;
	}
	if (m_JointNumber != value.m_JointNumber)
	{
		return false;
	}
	
	if (m_SensorPosition != value.m_SensorPosition)
	{
		return false;
	}
	
	if (m_UnitQuaternion != value.m_UnitQuaternion)
	{
		return false;
	}
	
	return true;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::operator!=(const ManipulatorGeometricPropertiesRec &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::ManipulatorGeometricPropertiesRec()
{
	m_parent = NULL;
	m_SubsystemID = 0;
	m_NodeID = 0;
	m_ComponentID = 0;
	m_JointNumber = 0;
	m_SensorPosition.setParent(this);
	/// No Initialization of m_SensorPosition necessary.
	m_UnitQuaternion.setParent(this);
	/// No Initialization of m_UnitQuaternion necessary.
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::ManipulatorGeometricPropertiesRec(const ManipulatorGeometricPropertiesRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubsystemID = 0;
	m_NodeID = 0;
	m_ComponentID = 0;
	m_JointNumber = 0;
	m_SensorPosition.setParent(this);
	/// No Initialization of m_SensorPosition necessary.
	m_UnitQuaternion.setParent(this);
	/// No Initialization of m_UnitQuaternion necessary.
	
	/// Copy the values
	m_SubsystemID = value.m_SubsystemID;
	m_NodeID = value.m_NodeID;
	m_ComponentID = value.m_ComponentID;
	m_JointNumber = value.m_JointNumber;
	m_SensorPosition = value.m_SensorPosition;
	m_UnitQuaternion = value.m_UnitQuaternion;
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec::~ManipulatorGeometricPropertiesRec()
{
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::ManipulatorGeometricPropertiesRec* const ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::getManipulatorGeometricPropertiesRec()
{
	return &m_ManipulatorGeometricPropertiesRec;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::setManipulatorGeometricPropertiesRec(const ManipulatorGeometricPropertiesRec &value)
{
	m_ManipulatorGeometricPropertiesRec = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::getFieldValue() const
{
	return m_FieldValue;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::setFieldValue(jUnsignedByte fieldValue)
{
	if(fieldValue > 2)
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
const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	switch(m_FieldValue)
	{
		case 0:
			size += m_NoGeometricPropertiesVariant.getSize();
			break;
		case 1:
			size += m_StaticGeometricPropertiesRec.getSize();
			break;
		case 2:
			size += m_ManipulatorGeometricPropertiesRec.getSize();
			break;
	}
	
	return size;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::encode(unsigned char *bytes)
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
			m_NoGeometricPropertiesVariant.encode(bytes + pos);
			pos += m_NoGeometricPropertiesVariant.getSize();
			break;
		case 1:
			m_StaticGeometricPropertiesRec.encode(bytes + pos);
			pos += m_StaticGeometricPropertiesRec.getSize();
			break;
		case 2:
			m_ManipulatorGeometricPropertiesRec.encode(bytes + pos);
			pos += m_ManipulatorGeometricPropertiesRec.getSize();
			break;
	}
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::decode(const unsigned char *bytes)
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
			m_NoGeometricPropertiesVariant.decode(bytes + pos);
			pos += m_NoGeometricPropertiesVariant.getSize();
			break;
		case 1:
			m_StaticGeometricPropertiesRec.decode(bytes + pos);
			pos += m_StaticGeometricPropertiesRec.getSize();
			break;
		case 2:
			m_ManipulatorGeometricPropertiesRec.decode(bytes + pos);
			pos += m_ManipulatorGeometricPropertiesRec.getSize();
			break;
	}
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant &ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::operator=(const GeometricPropertiesVariant &value)
{
	m_FieldValue = value.m_FieldValue;
	m_NoGeometricPropertiesVariant = value.m_NoGeometricPropertiesVariant;
	m_NoGeometricPropertiesVariant.setParent(this);
	m_StaticGeometricPropertiesRec = value.m_StaticGeometricPropertiesRec;
	m_StaticGeometricPropertiesRec.setParent(this);
	m_ManipulatorGeometricPropertiesRec = value.m_ManipulatorGeometricPropertiesRec;
	m_ManipulatorGeometricPropertiesRec.setParent(this);
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::operator==(const GeometricPropertiesVariant &value) const
{
	if (m_FieldValue != value.m_FieldValue)
	{
		return false;
	}
	if (m_NoGeometricPropertiesVariant != value.m_NoGeometricPropertiesVariant)
	{
		return false;
	}
	if (m_StaticGeometricPropertiesRec != value.m_StaticGeometricPropertiesRec)
	{
		return false;
	}
	if (m_ManipulatorGeometricPropertiesRec != value.m_ManipulatorGeometricPropertiesRec)
	{
		return false;
	}
	
	return true;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::operator!=(const GeometricPropertiesVariant &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::GeometricPropertiesVariant()
{
	m_parent = NULL;
	m_FieldValue = 0;
	m_NoGeometricPropertiesVariant.setParent(this);
	/// No Initialization of m_NoGeometricPropertiesVariant necessary.
	m_StaticGeometricPropertiesRec.setParent(this);
	/// No Initialization of m_StaticGeometricPropertiesRec necessary.
	m_ManipulatorGeometricPropertiesRec.setParent(this);
	/// No Initialization of m_ManipulatorGeometricPropertiesRec necessary.
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::GeometricPropertiesVariant(const GeometricPropertiesVariant &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_FieldValue = 0;
	m_NoGeometricPropertiesVariant.setParent(this);
	/// No Initialization of m_NoGeometricPropertiesVariant necessary.
	m_StaticGeometricPropertiesRec.setParent(this);
	/// No Initialization of m_StaticGeometricPropertiesRec necessary.
	m_ManipulatorGeometricPropertiesRec.setParent(this);
	/// No Initialization of m_ManipulatorGeometricPropertiesRec necessary.
	
	/// Copy the values
	m_FieldValue = value.m_FieldValue;
	m_NoGeometricPropertiesVariant = value.m_NoGeometricPropertiesVariant;
	m_NoGeometricPropertiesVariant.setParent(this);
	m_StaticGeometricPropertiesRec = value.m_StaticGeometricPropertiesRec;
	m_StaticGeometricPropertiesRec.setParent(this);
	m_ManipulatorGeometricPropertiesRec = value.m_ManipulatorGeometricPropertiesRec;
	m_ManipulatorGeometricPropertiesRec.setParent(this);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant::~GeometricPropertiesVariant()
{
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesVariant* const ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::getGeometricPropertiesVariant()
{
	return &m_GeometricPropertiesVariant;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::setGeometricPropertiesVariant(const GeometricPropertiesVariant &value)
{
	m_GeometricPropertiesVariant = value;
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
const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::getSize()
{
	unsigned int size = 0;
	
	size += m_SensorIdRec.getSize();
	size += m_GeometricPropertiesVariant.getSize();
	
	return size;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SensorIdRec.encode(bytes + pos);
	pos += m_SensorIdRec.getSize();
	m_GeometricPropertiesVariant.encode(bytes + pos);
	pos += m_GeometricPropertiesVariant.getSize();
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SensorIdRec.decode(bytes + pos);
	pos += m_SensorIdRec.getSize();
	m_GeometricPropertiesVariant.decode(bytes + pos);
	pos += m_GeometricPropertiesVariant.getSize();
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence &ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::operator=(const GeometricPropertiesSequence &value)
{
	m_SensorIdRec = value.m_SensorIdRec;
	m_SensorIdRec.setParent(this);
	m_SensorIdRec = value.m_SensorIdRec;
	m_GeometricPropertiesVariant = value.m_GeometricPropertiesVariant;
	m_GeometricPropertiesVariant.setParent(this);
	m_GeometricPropertiesVariant = value.m_GeometricPropertiesVariant;
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::operator==(const GeometricPropertiesSequence &value) const
{
	if (m_SensorIdRec != value.m_SensorIdRec)
	{
		return false;
	}
	
	if (m_SensorIdRec != value.m_SensorIdRec)
	{
		return false;
	}
	if (m_GeometricPropertiesVariant != value.m_GeometricPropertiesVariant)
	{
		return false;
	}
	
	if (m_GeometricPropertiesVariant != value.m_GeometricPropertiesVariant)
	{
		return false;
	}
	
	return true;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::operator!=(const GeometricPropertiesSequence &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesSequence()
{
	m_parent = NULL;
	m_SensorIdRec.setParent(this);
	/// No Initialization of m_SensorIdRec necessary.
	m_GeometricPropertiesVariant.setParent(this);
	/// No Initialization of m_GeometricPropertiesVariant necessary.
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::GeometricPropertiesSequence(const GeometricPropertiesSequence &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorIdRec.setParent(this);
	/// No Initialization of m_SensorIdRec necessary.
	m_GeometricPropertiesVariant.setParent(this);
	/// No Initialization of m_GeometricPropertiesVariant necessary.
	
	/// Copy the values
	m_SensorIdRec = value.m_SensorIdRec;
	m_SensorIdRec.setParent(this);
	m_SensorIdRec = value.m_SensorIdRec;
	m_GeometricPropertiesVariant = value.m_GeometricPropertiesVariant;
	m_GeometricPropertiesVariant.setParent(this);
	m_GeometricPropertiesVariant = value.m_GeometricPropertiesVariant;
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence::~GeometricPropertiesSequence()
{
}

unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::getNumberOfElements() const
{
	return (unsigned int) m_GeometricPropertiesSequence.size();
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesSequence* const ReportSensorGeometricProperties::Body::GeometricPropertiesList::getElement(unsigned int index)
{
	return &m_GeometricPropertiesSequence.at(index);
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::setElement(unsigned int index, const GeometricPropertiesSequence &value)
{
	if(m_GeometricPropertiesSequence.size()-1 < index)
	{
		return 1;
	}
	
	m_GeometricPropertiesSequence.at(index) = value;
	m_GeometricPropertiesSequence.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::addElement(const GeometricPropertiesSequence &value)
{
	m_GeometricPropertiesSequence.push_back(value);
	m_GeometricPropertiesSequence.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::deleteElement(unsigned int index)
{
	if(m_GeometricPropertiesSequence.size()-1 < index)
	{
		return 1;
	}
	
	m_GeometricPropertiesSequence.erase(m_GeometricPropertiesSequence.begin()+index);
	return 0;
}

int ReportSensorGeometricProperties::Body::GeometricPropertiesList::deleteLastElement()
{
	m_GeometricPropertiesSequence.pop_back();
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
const unsigned int ReportSensorGeometricProperties::Body::GeometricPropertiesList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_GeometricPropertiesSequence.size(); i++)
	{
		size += m_GeometricPropertiesSequence[i].getSize();
	}
	
	return size;
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_GeometricPropertiesSequence.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_GeometricPropertiesSequence.size(); i++)
	{
		m_GeometricPropertiesSequence[i].encode(bytes + pos);
		pos += m_GeometricPropertiesSequence[i].getSize();
	}
}

void ReportSensorGeometricProperties::Body::GeometricPropertiesList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_GeometricPropertiesSequence.resize(size);
	for (int i = 0; i < m_GeometricPropertiesSequence.size(); i++)
	{
		m_GeometricPropertiesSequence[i].decode(bytes + pos);
		pos += m_GeometricPropertiesSequence[i].getSize();
	}
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList &ReportSensorGeometricProperties::Body::GeometricPropertiesList::operator=(const GeometricPropertiesList &value)
{
	m_GeometricPropertiesSequence.clear();
	
	for (int i = 0; i < value.m_GeometricPropertiesSequence.size(); i++)
	{
		m_GeometricPropertiesSequence.push_back(value.m_GeometricPropertiesSequence[i]);
		m_GeometricPropertiesSequence[i].setParent(this);
	}
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::operator==(const GeometricPropertiesList &value) const
{
	for (int i = 0; i < m_GeometricPropertiesSequence.size(); i++)
	{
		if (m_GeometricPropertiesSequence[i] != value.m_GeometricPropertiesSequence[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportSensorGeometricProperties::Body::GeometricPropertiesList::operator!=(const GeometricPropertiesList &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_GeometricPropertiesSequence.size(); i++)
	{
		m_GeometricPropertiesSequence[i].setParent(this);
	}
	/// No Initialization of m_GeometricPropertiesSequence necessary.
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::GeometricPropertiesList(const GeometricPropertiesList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_GeometricPropertiesSequence.size(); i++)
	{
		m_GeometricPropertiesSequence[i].setParent(this);
	}
	/// No Initialization of m_GeometricPropertiesSequence necessary.
	
	/// Copy the values
	m_GeometricPropertiesSequence.clear();
	
	for (int i = 0; i < value.m_GeometricPropertiesSequence.size(); i++)
	{
		m_GeometricPropertiesSequence.push_back(value.m_GeometricPropertiesSequence[i]);
		m_GeometricPropertiesSequence[i].setParent(this);
	}
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList::~GeometricPropertiesList()
{
}

ReportSensorGeometricProperties::Body::GeometricPropertiesList* const ReportSensorGeometricProperties::Body::getGeometricPropertiesList()
{
	return &m_GeometricPropertiesList;
}

int ReportSensorGeometricProperties::Body::setGeometricPropertiesList(const GeometricPropertiesList &value)
{
	m_GeometricPropertiesList = value;
	setParentPresenceVector();
	return 0;
}

void ReportSensorGeometricProperties::Body::setParentPresenceVector()
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
const unsigned int ReportSensorGeometricProperties::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_GeometricPropertiesList.getSize();
	
	return size;
}

void ReportSensorGeometricProperties::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_GeometricPropertiesList.encode(bytes + pos);
	pos += m_GeometricPropertiesList.getSize();
}

void ReportSensorGeometricProperties::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_GeometricPropertiesList.decode(bytes + pos);
	pos += m_GeometricPropertiesList.getSize();
}

ReportSensorGeometricProperties::Body &ReportSensorGeometricProperties::Body::operator=(const Body &value)
{
	m_GeometricPropertiesList = value.m_GeometricPropertiesList;
	m_GeometricPropertiesList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportSensorGeometricProperties::Body::operator==(const Body &value) const
{
	if (m_GeometricPropertiesList != value.m_GeometricPropertiesList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportSensorGeometricProperties::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::Body::Body()
{
	m_GeometricPropertiesList.setParent(this);
	/// No Initialization of m_GeometricPropertiesList necessary.
}

ReportSensorGeometricProperties::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_GeometricPropertiesList.setParent(this);
	/// No Initialization of m_GeometricPropertiesList necessary.
	
	/// Copy the values
	m_GeometricPropertiesList = value.m_GeometricPropertiesList;
	m_GeometricPropertiesList.setParent(this);
	/// This code is currently not supported
}

ReportSensorGeometricProperties::Body::~Body()
{
}

ReportSensorGeometricProperties::Body* const ReportSensorGeometricProperties::getBody()
{
	return &m_Body;
}

int ReportSensorGeometricProperties::setBody(const Body &value)
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
const unsigned int ReportSensorGeometricProperties::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportSensorGeometricProperties::encode(unsigned char *bytes)
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

void ReportSensorGeometricProperties::decode(const unsigned char *bytes)
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

ReportSensorGeometricProperties &ReportSensorGeometricProperties::operator=(const ReportSensorGeometricProperties &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportSensorGeometricProperties::operator==(const ReportSensorGeometricProperties &value) const
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

bool ReportSensorGeometricProperties::operator!=(const ReportSensorGeometricProperties &value) const
{
	return !((*this) == value);
}

ReportSensorGeometricProperties::ReportSensorGeometricProperties()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportSensorGeometricProperties::ReportSensorGeometricProperties(const ReportSensorGeometricProperties &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportSensorGeometricProperties::~ReportSensorGeometricProperties()
{
}


}
