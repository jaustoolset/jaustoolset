#include "urn_jts_examples_SkidSteerVehicleSim_1_0/Messages/ReportSimulatedPose.h"

namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

void ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::setParent(JAUSApplicationLayerHeader* parent)
{
	m_parent = parent;
}

void ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec &ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4d03;
}

ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4d03;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec::~HeaderRec()
{
}

ReportSimulatedPose::JAUSApplicationLayerHeader::HeaderRec* const ReportSimulatedPose::JAUSApplicationLayerHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportSimulatedPose::JAUSApplicationLayerHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportSimulatedPose::JAUSApplicationLayerHeader::setParentPresenceVector()
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
const unsigned int ReportSimulatedPose::JAUSApplicationLayerHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportSimulatedPose::JAUSApplicationLayerHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportSimulatedPose::JAUSApplicationLayerHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportSimulatedPose::JAUSApplicationLayerHeader &ReportSimulatedPose::JAUSApplicationLayerHeader::operator=(const JAUSApplicationLayerHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportSimulatedPose::JAUSApplicationLayerHeader::operator==(const JAUSApplicationLayerHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportSimulatedPose::JAUSApplicationLayerHeader::operator!=(const JAUSApplicationLayerHeader &value) const
{
	return !((*this) == value);
}

ReportSimulatedPose::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportSimulatedPose::JAUSApplicationLayerHeader::JAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportSimulatedPose::JAUSApplicationLayerHeader::~JAUSApplicationLayerHeader()
{
}

ReportSimulatedPose::JAUSApplicationLayerHeader* const ReportSimulatedPose::getJAUSApplicationLayerHeader()
{
	return &m_JAUSApplicationLayerHeader;
}

int ReportSimulatedPose::setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value)
{
	m_JAUSApplicationLayerHeader = value;
	return 0;
}

void ReportSimulatedPose::Body::LocalPoseRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportSimulatedPose::Body::LocalPoseRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportSimulatedPose::Body::LocalPoseRec::getPresenceVector()
{
	return m_PresenceVector;
}

int ReportSimulatedPose::Body::LocalPoseRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedShortInteger)pvBitSet.to_ulong();
	return 0;
}

bool ReportSimulatedPose::Body::LocalPoseRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

bool ReportSimulatedPose::Body::LocalPoseRec::isXValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

double ReportSimulatedPose::Body::LocalPoseRec::getX()
{
	double value;
	
	double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
	double bias = -100000;
	
	value = m_X * scaleFactor + bias;
	
	return value;
}

int ReportSimulatedPose::Body::LocalPoseRec::setX(double value)
{
	if ((value >= -100000) && (value <= 100000))
	{
		double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
		double bias = -100000;
		
		m_X= (jUnsignedInteger)((value - bias) / scaleFactor);
		setPresenceVector(0);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportSimulatedPose::Body::LocalPoseRec::isYValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

double ReportSimulatedPose::Body::LocalPoseRec::getY()
{
	double value;
	
	double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
	double bias = -100000;
	
	value = m_Y * scaleFactor + bias;
	
	return value;
}

int ReportSimulatedPose::Body::LocalPoseRec::setY(double value)
{
	if ((value >= -100000) && (value <= 100000))
	{
		double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
		double bias = -100000;
		
		m_Y= (jUnsignedInteger)((value - bias) / scaleFactor);
		setPresenceVector(1);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportSimulatedPose::Body::LocalPoseRec::isZValid() const
{
	if (checkPresenceVector(2))
	{
		return true;
	}
	return false;
}

double ReportSimulatedPose::Body::LocalPoseRec::getZ()
{
	double value;
	
	double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
	double bias = -100000;
	
	value = m_Z * scaleFactor + bias;
	
	return value;
}

int ReportSimulatedPose::Body::LocalPoseRec::setZ(double value)
{
	if ((value >= -100000) && (value <= 100000))
	{
		double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
		double bias = -100000;
		
		m_Z= (jUnsignedInteger)((value - bias) / scaleFactor);
		setPresenceVector(2);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportSimulatedPose::Body::LocalPoseRec::isPosition_RMSValid() const
{
	if (checkPresenceVector(3))
	{
		return true;
	}
	return false;
}

double ReportSimulatedPose::Body::LocalPoseRec::getPosition_RMS()
{
	double value;
	
	double scaleFactor = ( 100 - 0 ) / 4.294967295E9;
	double bias = 0;
	
	value = m_Position_RMS * scaleFactor + bias;
	
	return value;
}

int ReportSimulatedPose::Body::LocalPoseRec::setPosition_RMS(double value)
{
	if ((value >= 0) && (value <= 100))
	{
		double scaleFactor = ( 100 - 0 ) / 4.294967295E9;
		double bias = 0;
		
		m_Position_RMS= (jUnsignedInteger)((value - bias) / scaleFactor);
		setPresenceVector(3);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportSimulatedPose::Body::LocalPoseRec::isRollValid() const
{
	if (checkPresenceVector(4))
	{
		return true;
	}
	return false;
}

double ReportSimulatedPose::Body::LocalPoseRec::getRoll()
{
	double value;
	
	double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
	double bias = -3.14159265358979323846;
	
	value = m_Roll * scaleFactor + bias;
	
	return value;
}

int ReportSimulatedPose::Body::LocalPoseRec::setRoll(double value)
{
	if ((value >= -3.14159265358979323846) && (value <= 3.14159265358979323846))
	{
		double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
		double bias = -3.14159265358979323846;
		
		m_Roll= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(4);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportSimulatedPose::Body::LocalPoseRec::isPitchValid() const
{
	if (checkPresenceVector(5))
	{
		return true;
	}
	return false;
}

double ReportSimulatedPose::Body::LocalPoseRec::getPitch()
{
	double value;
	
	double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
	double bias = -3.14159265358979323846;
	
	value = m_Pitch * scaleFactor + bias;
	
	return value;
}

int ReportSimulatedPose::Body::LocalPoseRec::setPitch(double value)
{
	if ((value >= -3.14159265358979323846) && (value <= 3.14159265358979323846))
	{
		double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
		double bias = -3.14159265358979323846;
		
		m_Pitch= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(5);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportSimulatedPose::Body::LocalPoseRec::isYawValid() const
{
	if (checkPresenceVector(6))
	{
		return true;
	}
	return false;
}

double ReportSimulatedPose::Body::LocalPoseRec::getYaw()
{
	double value;
	
	double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
	double bias = -3.14159265358979323846;
	
	value = m_Yaw * scaleFactor + bias;
	
	return value;
}

int ReportSimulatedPose::Body::LocalPoseRec::setYaw(double value)
{
	if ((value >= -3.14159265358979323846) && (value <= 3.14159265358979323846))
	{
		double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
		double bias = -3.14159265358979323846;
		
		m_Yaw= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(6);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportSimulatedPose::Body::LocalPoseRec::isAttitude_RMSValid() const
{
	if (checkPresenceVector(7))
	{
		return true;
	}
	return false;
}

double ReportSimulatedPose::Body::LocalPoseRec::getAttitude_RMS()
{
	double value;
	
	double scaleFactor = ( 3.14159265358979323846 - 0 ) / 65535.0;
	double bias = 0;
	
	value = m_Attitude_RMS * scaleFactor + bias;
	
	return value;
}

int ReportSimulatedPose::Body::LocalPoseRec::setAttitude_RMS(double value)
{
	if ((value >= 0) && (value <= 3.14159265358979323846))
	{
		double scaleFactor = ( 3.14159265358979323846 - 0 ) / 65535.0;
		double bias = 0;
		
		m_Attitude_RMS= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(7);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

void ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::setParent(LocalPoseRec* parent)
{
	m_parent = parent;
}

void ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::getMilliseconds()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<10> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 9; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::setMilliseconds(jUnsignedInteger value)
{
	if (((value >= 0) && (value <= 999)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<10> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 9; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::getSeconds()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<6> sfbs;
	int i = 0;
	
	for (int index = 10; index <= 15; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::setSeconds(jUnsignedInteger value)
{
	if (((value >= 0) && (value <= 59)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<6> sfbs((int)value);
		int i = 0;
		
		for (int index = 10; index <= 15; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::getMinutes()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<6> sfbs;
	int i = 0;
	
	for (int index = 16; index <= 21; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::setMinutes(jUnsignedInteger value)
{
	if (((value >= 0) && (value <= 59)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<6> sfbs((int)value);
		int i = 0;
		
		for (int index = 16; index <= 21; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::getHour()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<5> sfbs;
	int i = 0;
	
	for (int index = 22; index <= 26; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::setHour(jUnsignedInteger value)
{
	if (((value >= 0) && (value <= 23)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<5> sfbs((int)value);
		int i = 0;
		
		for (int index = 22; index <= 26; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::getDay()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<5> sfbs;
	int i = 0;
	
	for (int index = 27; index <= 31; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::setDay(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 31)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<5> sfbs((int)value);
		int i = 0;
		
		for (int index = 27; index <= 31; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
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
const unsigned int ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
}

void ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedInteger));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedInteger);
}

ReportSimulatedPose::Body::LocalPoseRec::TimeStamp &ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::operator=(const TimeStamp &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::operator==(const TimeStamp &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::operator!=(const TimeStamp &value) const
{
	return !((*this) == value);
}

ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::TimeStamp()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::TimeStamp(const TimeStamp &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportSimulatedPose::Body::LocalPoseRec::TimeStamp::~TimeStamp()
{
}

ReportSimulatedPose::Body::LocalPoseRec::TimeStamp* const ReportSimulatedPose::Body::LocalPoseRec::getTimeStamp()
{
	return &m_TimeStamp;
}

int ReportSimulatedPose::Body::LocalPoseRec::setTimeStamp(const TimeStamp &value)
{
	m_TimeStamp = value;
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
const unsigned int ReportSimulatedPose::Body::LocalPoseRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	if (checkPresenceVector(0))
	{
		size += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(1))
	{
		size += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(2))
	{
		size += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(3))
	{
		size += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(4))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(5))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(6))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(7))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	size += m_TimeStamp.getSize();
	
	return size;
}

void ReportSimulatedPose::Body::LocalPoseRec::encode(unsigned char *bytes)
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
	if (checkPresenceVector(0))
	{
		jUnsignedInteger m_XTemp;
		
		m_XTemp = JSIDL_v_1_0::correctEndianness(m_X);
		memcpy(bytes + pos, &m_XTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(1))
	{
		jUnsignedInteger m_YTemp;
		
		m_YTemp = JSIDL_v_1_0::correctEndianness(m_Y);
		memcpy(bytes + pos, &m_YTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(2))
	{
		jUnsignedInteger m_ZTemp;
		
		m_ZTemp = JSIDL_v_1_0::correctEndianness(m_Z);
		memcpy(bytes + pos, &m_ZTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(3))
	{
		jUnsignedInteger m_Position_RMSTemp;
		
		m_Position_RMSTemp = JSIDL_v_1_0::correctEndianness(m_Position_RMS);
		memcpy(bytes + pos, &m_Position_RMSTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(4))
	{
		jUnsignedShortInteger m_RollTemp;
		
		m_RollTemp = JSIDL_v_1_0::correctEndianness(m_Roll);
		memcpy(bytes + pos, &m_RollTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(5))
	{
		jUnsignedShortInteger m_PitchTemp;
		
		m_PitchTemp = JSIDL_v_1_0::correctEndianness(m_Pitch);
		memcpy(bytes + pos, &m_PitchTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(6))
	{
		jUnsignedShortInteger m_YawTemp;
		
		m_YawTemp = JSIDL_v_1_0::correctEndianness(m_Yaw);
		memcpy(bytes + pos, &m_YawTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(7))
	{
		jUnsignedShortInteger m_Attitude_RMSTemp;
		
		m_Attitude_RMSTemp = JSIDL_v_1_0::correctEndianness(m_Attitude_RMS);
		memcpy(bytes + pos, &m_Attitude_RMSTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	m_TimeStamp.encode(bytes + pos);
	pos += m_TimeStamp.getSize();
}

void ReportSimulatedPose::Body::LocalPoseRec::decode(const unsigned char *bytes)
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
	if (checkPresenceVector(0))
	{
		jUnsignedInteger m_XTemp;
		
		memcpy(&m_XTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_X = JSIDL_v_1_0::correctEndianness(m_XTemp);
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(1))
	{
		jUnsignedInteger m_YTemp;
		
		memcpy(&m_YTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_Y = JSIDL_v_1_0::correctEndianness(m_YTemp);
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(2))
	{
		jUnsignedInteger m_ZTemp;
		
		memcpy(&m_ZTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_Z = JSIDL_v_1_0::correctEndianness(m_ZTemp);
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(3))
	{
		jUnsignedInteger m_Position_RMSTemp;
		
		memcpy(&m_Position_RMSTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_Position_RMS = JSIDL_v_1_0::correctEndianness(m_Position_RMSTemp);
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(4))
	{
		jUnsignedShortInteger m_RollTemp;
		
		memcpy(&m_RollTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_Roll = JSIDL_v_1_0::correctEndianness(m_RollTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(5))
	{
		jUnsignedShortInteger m_PitchTemp;
		
		memcpy(&m_PitchTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_Pitch = JSIDL_v_1_0::correctEndianness(m_PitchTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(6))
	{
		jUnsignedShortInteger m_YawTemp;
		
		memcpy(&m_YawTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_Yaw = JSIDL_v_1_0::correctEndianness(m_YawTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(7))
	{
		jUnsignedShortInteger m_Attitude_RMSTemp;
		
		memcpy(&m_Attitude_RMSTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_Attitude_RMS = JSIDL_v_1_0::correctEndianness(m_Attitude_RMSTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	m_TimeStamp.decode(bytes + pos);
	pos += m_TimeStamp.getSize();
}

ReportSimulatedPose::Body::LocalPoseRec &ReportSimulatedPose::Body::LocalPoseRec::operator=(const LocalPoseRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_X = value.m_X;
	m_Y = value.m_Y;
	m_Z = value.m_Z;
	m_Position_RMS = value.m_Position_RMS;
	m_Roll = value.m_Roll;
	m_Pitch = value.m_Pitch;
	m_Yaw = value.m_Yaw;
	m_Attitude_RMS = value.m_Attitude_RMS;
	m_TimeStamp = value.m_TimeStamp;
	
	return *this;
}

bool ReportSimulatedPose::Body::LocalPoseRec::operator==(const LocalPoseRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	if (m_X != value.m_X)
	{
		return false;
	}
	if (m_Y != value.m_Y)
	{
		return false;
	}
	if (m_Z != value.m_Z)
	{
		return false;
	}
	if (m_Position_RMS != value.m_Position_RMS)
	{
		return false;
	}
	if (m_Roll != value.m_Roll)
	{
		return false;
	}
	if (m_Pitch != value.m_Pitch)
	{
		return false;
	}
	if (m_Yaw != value.m_Yaw)
	{
		return false;
	}
	if (m_Attitude_RMS != value.m_Attitude_RMS)
	{
		return false;
	}
	
	if (m_TimeStamp != value.m_TimeStamp)
	{
		return false;
	}
	
	return true;
}

bool ReportSimulatedPose::Body::LocalPoseRec::operator!=(const LocalPoseRec &value) const
{
	return !((*this) == value);
}

ReportSimulatedPose::Body::LocalPoseRec::LocalPoseRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_X = 0;
	m_Y = 0;
	m_Z = 0;
	m_Position_RMS = 0;
	m_Roll = 0;
	m_Pitch = 0;
	m_Yaw = 0;
	m_Attitude_RMS = 0;
	m_TimeStamp.setParent(this);
}

ReportSimulatedPose::Body::LocalPoseRec::LocalPoseRec(const LocalPoseRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	m_X = 0;
	m_Y = 0;
	m_Z = 0;
	m_Position_RMS = 0;
	m_Roll = 0;
	m_Pitch = 0;
	m_Yaw = 0;
	m_Attitude_RMS = 0;
	m_TimeStamp.setParent(this);
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_X = value.m_X;
	m_Y = value.m_Y;
	m_Z = value.m_Z;
	m_Position_RMS = value.m_Position_RMS;
	m_Roll = value.m_Roll;
	m_Pitch = value.m_Pitch;
	m_Yaw = value.m_Yaw;
	m_Attitude_RMS = value.m_Attitude_RMS;
	m_TimeStamp = value.m_TimeStamp;
}

ReportSimulatedPose::Body::LocalPoseRec::~LocalPoseRec()
{
}

ReportSimulatedPose::Body::LocalPoseRec* const ReportSimulatedPose::Body::getLocalPoseRec()
{
	return &m_LocalPoseRec;
}

int ReportSimulatedPose::Body::setLocalPoseRec(const LocalPoseRec &value)
{
	m_LocalPoseRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportSimulatedPose::Body::setParentPresenceVector()
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
const unsigned int ReportSimulatedPose::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_LocalPoseRec.getSize();
	
	return size;
}

void ReportSimulatedPose::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_LocalPoseRec.encode(bytes + pos);
	pos += m_LocalPoseRec.getSize();
}

void ReportSimulatedPose::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_LocalPoseRec.decode(bytes + pos);
	pos += m_LocalPoseRec.getSize();
}

ReportSimulatedPose::Body &ReportSimulatedPose::Body::operator=(const Body &value)
{
	m_LocalPoseRec = value.m_LocalPoseRec;
	m_LocalPoseRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportSimulatedPose::Body::operator==(const Body &value) const
{
	if (m_LocalPoseRec != value.m_LocalPoseRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportSimulatedPose::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportSimulatedPose::Body::Body()
{
	m_LocalPoseRec.setParent(this);
	/// No Initialization of m_LocalPoseRec necessary.
}

ReportSimulatedPose::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_LocalPoseRec.setParent(this);
	/// No Initialization of m_LocalPoseRec necessary.
	
	/// Copy the values
	m_LocalPoseRec = value.m_LocalPoseRec;
	m_LocalPoseRec.setParent(this);
	/// This code is currently not supported
}

ReportSimulatedPose::Body::~Body()
{
}

ReportSimulatedPose::Body* const ReportSimulatedPose::getBody()
{
	return &m_Body;
}

int ReportSimulatedPose::setBody(const Body &value)
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
const unsigned int ReportSimulatedPose::getSize()
{
	unsigned int size = 0;
	
	size += m_JAUSApplicationLayerHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportSimulatedPose::encode(unsigned char *bytes)
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

void ReportSimulatedPose::decode(const unsigned char *bytes)
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

ReportSimulatedPose &ReportSimulatedPose::operator=(const ReportSimulatedPose &value)
{
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportSimulatedPose::operator==(const ReportSimulatedPose &value) const
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

bool ReportSimulatedPose::operator!=(const ReportSimulatedPose &value) const
{
	return !((*this) == value);
}

ReportSimulatedPose::ReportSimulatedPose()
{
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportSimulatedPose::ReportSimulatedPose(const ReportSimulatedPose &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_JAUSApplicationLayerHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
	m_Body = value.m_Body;
}

ReportSimulatedPose::~ReportSimulatedPose()
{
}


}
