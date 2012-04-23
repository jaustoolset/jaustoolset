#include "urn_jaus_jss_mobility_LocalPoseSensor_1_0/Messages/SetLocalPose.h"

namespace urn_jaus_jss_mobility_LocalPoseSensor_1_0
{

void SetLocalPose::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void SetLocalPose::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger SetLocalPose::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int SetLocalPose::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int SetLocalPose::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void SetLocalPose::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void SetLocalPose::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

SetLocalPose::AppHeader::HeaderRec &SetLocalPose::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool SetLocalPose::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool SetLocalPose::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

SetLocalPose::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x0403;
}

SetLocalPose::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x0403;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

SetLocalPose::AppHeader::HeaderRec::~HeaderRec()
{
}

SetLocalPose::AppHeader::HeaderRec* const SetLocalPose::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int SetLocalPose::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void SetLocalPose::AppHeader::setParentPresenceVector()
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
const unsigned int SetLocalPose::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void SetLocalPose::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void SetLocalPose::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

SetLocalPose::AppHeader &SetLocalPose::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool SetLocalPose::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool SetLocalPose::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

SetLocalPose::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

SetLocalPose::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

SetLocalPose::AppHeader::~AppHeader()
{
}

SetLocalPose::AppHeader* const SetLocalPose::getAppHeader()
{
	return &m_AppHeader;
}

int SetLocalPose::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void SetLocalPose::Body::LocalPoseRec::setParent(Body* parent)
{
	m_parent = parent;
}

void SetLocalPose::Body::LocalPoseRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger SetLocalPose::Body::LocalPoseRec::getPresenceVector()
{
	return m_PresenceVector;
}

int SetLocalPose::Body::LocalPoseRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedShortInteger)pvBitSet.to_ulong();
	return 0;
}

bool SetLocalPose::Body::LocalPoseRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

bool SetLocalPose::Body::LocalPoseRec::isXValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

double SetLocalPose::Body::LocalPoseRec::getX()
{
	double value;
	
	double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
	double bias = -100000;
	
	value = m_X * scaleFactor + bias;
	
	return value;
}

int SetLocalPose::Body::LocalPoseRec::setX(double value)
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

bool SetLocalPose::Body::LocalPoseRec::isYValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

double SetLocalPose::Body::LocalPoseRec::getY()
{
	double value;
	
	double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
	double bias = -100000;
	
	value = m_Y * scaleFactor + bias;
	
	return value;
}

int SetLocalPose::Body::LocalPoseRec::setY(double value)
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

bool SetLocalPose::Body::LocalPoseRec::isZValid() const
{
	if (checkPresenceVector(2))
	{
		return true;
	}
	return false;
}

double SetLocalPose::Body::LocalPoseRec::getZ()
{
	double value;
	
	double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
	double bias = -100000;
	
	value = m_Z * scaleFactor + bias;
	
	return value;
}

int SetLocalPose::Body::LocalPoseRec::setZ(double value)
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

bool SetLocalPose::Body::LocalPoseRec::isPosition_RMSValid() const
{
	if (checkPresenceVector(3))
	{
		return true;
	}
	return false;
}

double SetLocalPose::Body::LocalPoseRec::getPosition_RMS()
{
	double value;
	
	double scaleFactor = ( 100 - 0 ) / 4.294967295E9;
	double bias = 0;
	
	value = m_Position_RMS * scaleFactor + bias;
	
	return value;
}

int SetLocalPose::Body::LocalPoseRec::setPosition_RMS(double value)
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

bool SetLocalPose::Body::LocalPoseRec::isRollValid() const
{
	if (checkPresenceVector(4))
	{
		return true;
	}
	return false;
}

double SetLocalPose::Body::LocalPoseRec::getRoll()
{
	double value;
	
	double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
	double bias = -3.14159265358979323846;
	
	value = m_Roll * scaleFactor + bias;
	
	return value;
}

int SetLocalPose::Body::LocalPoseRec::setRoll(double value)
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

bool SetLocalPose::Body::LocalPoseRec::isPitchValid() const
{
	if (checkPresenceVector(5))
	{
		return true;
	}
	return false;
}

double SetLocalPose::Body::LocalPoseRec::getPitch()
{
	double value;
	
	double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
	double bias = -3.14159265358979323846;
	
	value = m_Pitch * scaleFactor + bias;
	
	return value;
}

int SetLocalPose::Body::LocalPoseRec::setPitch(double value)
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

bool SetLocalPose::Body::LocalPoseRec::isYawValid() const
{
	if (checkPresenceVector(6))
	{
		return true;
	}
	return false;
}

double SetLocalPose::Body::LocalPoseRec::getYaw()
{
	double value;
	
	double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
	double bias = -3.14159265358979323846;
	
	value = m_Yaw * scaleFactor + bias;
	
	return value;
}

int SetLocalPose::Body::LocalPoseRec::setYaw(double value)
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

bool SetLocalPose::Body::LocalPoseRec::isAttitude_RMSValid() const
{
	if (checkPresenceVector(7))
	{
		return true;
	}
	return false;
}

double SetLocalPose::Body::LocalPoseRec::getAttitude_RMS()
{
	double value;
	
	double scaleFactor = ( 3.14159265358979323846 - 0 ) / 65535.0;
	double bias = 0;
	
	value = m_Attitude_RMS * scaleFactor + bias;
	
	return value;
}

int SetLocalPose::Body::LocalPoseRec::setAttitude_RMS(double value)
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

void SetLocalPose::Body::LocalPoseRec::TimeStamp::setParent(LocalPoseRec* parent)
{
	m_parent = parent;
}

void SetLocalPose::Body::LocalPoseRec::TimeStamp::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(8);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger SetLocalPose::Body::LocalPoseRec::TimeStamp::getMilliseconds()
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

int SetLocalPose::Body::LocalPoseRec::TimeStamp::setMilliseconds(jUnsignedInteger value)
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

jUnsignedInteger SetLocalPose::Body::LocalPoseRec::TimeStamp::getSeconds()
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

int SetLocalPose::Body::LocalPoseRec::TimeStamp::setSeconds(jUnsignedInteger value)
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

jUnsignedInteger SetLocalPose::Body::LocalPoseRec::TimeStamp::getMinutes()
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

int SetLocalPose::Body::LocalPoseRec::TimeStamp::setMinutes(jUnsignedInteger value)
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

jUnsignedInteger SetLocalPose::Body::LocalPoseRec::TimeStamp::getHour()
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

int SetLocalPose::Body::LocalPoseRec::TimeStamp::setHour(jUnsignedInteger value)
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

jUnsignedInteger SetLocalPose::Body::LocalPoseRec::TimeStamp::getDay()
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

int SetLocalPose::Body::LocalPoseRec::TimeStamp::setDay(jUnsignedInteger value)
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
const unsigned int SetLocalPose::Body::LocalPoseRec::TimeStamp::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void SetLocalPose::Body::LocalPoseRec::TimeStamp::encode(unsigned char *bytes)
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

void SetLocalPose::Body::LocalPoseRec::TimeStamp::decode(const unsigned char *bytes)
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

SetLocalPose::Body::LocalPoseRec::TimeStamp &SetLocalPose::Body::LocalPoseRec::TimeStamp::operator=(const TimeStamp &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool SetLocalPose::Body::LocalPoseRec::TimeStamp::operator==(const TimeStamp &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool SetLocalPose::Body::LocalPoseRec::TimeStamp::operator!=(const TimeStamp &value) const
{
	return !((*this) == value);
}

SetLocalPose::Body::LocalPoseRec::TimeStamp::TimeStamp()
{
	m_parent = NULL;
	m_SubFields = 0;
}

SetLocalPose::Body::LocalPoseRec::TimeStamp::TimeStamp(const TimeStamp &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

SetLocalPose::Body::LocalPoseRec::TimeStamp::~TimeStamp()
{
}

bool SetLocalPose::Body::LocalPoseRec::isTimeStampValid() const
{
	if (checkPresenceVector(8))
	{
		return true;
	}
	return false;
}

SetLocalPose::Body::LocalPoseRec::TimeStamp* const SetLocalPose::Body::LocalPoseRec::getTimeStamp()
{
	return &m_TimeStamp;
}

int SetLocalPose::Body::LocalPoseRec::setTimeStamp(const TimeStamp &value)
{
	m_TimeStamp = value;
	setPresenceVector(8);
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
const unsigned int SetLocalPose::Body::LocalPoseRec::getSize()
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
	if (checkPresenceVector(8))
	{
		size += m_TimeStamp.getSize();
	}
	
	return size;
}

void SetLocalPose::Body::LocalPoseRec::encode(unsigned char *bytes)
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
	if (checkPresenceVector(8))
	{
		m_TimeStamp.encode(bytes + pos);
		pos += m_TimeStamp.getSize();
	}
}

void SetLocalPose::Body::LocalPoseRec::decode(const unsigned char *bytes)
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
	if (checkPresenceVector(8))
	{
		m_TimeStamp.decode(bytes + pos);
		pos += m_TimeStamp.getSize();
	}
}

SetLocalPose::Body::LocalPoseRec &SetLocalPose::Body::LocalPoseRec::operator=(const LocalPoseRec &value)
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

bool SetLocalPose::Body::LocalPoseRec::operator==(const LocalPoseRec &value) const
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

bool SetLocalPose::Body::LocalPoseRec::operator!=(const LocalPoseRec &value) const
{
	return !((*this) == value);
}

SetLocalPose::Body::LocalPoseRec::LocalPoseRec()
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

SetLocalPose::Body::LocalPoseRec::LocalPoseRec(const LocalPoseRec &value)
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

SetLocalPose::Body::LocalPoseRec::~LocalPoseRec()
{
}

SetLocalPose::Body::LocalPoseRec* const SetLocalPose::Body::getLocalPoseRec()
{
	return &m_LocalPoseRec;
}

int SetLocalPose::Body::setLocalPoseRec(const LocalPoseRec &value)
{
	m_LocalPoseRec = value;
	setParentPresenceVector();
	return 0;
}

void SetLocalPose::Body::setParentPresenceVector()
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
const unsigned int SetLocalPose::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_LocalPoseRec.getSize();
	
	return size;
}

void SetLocalPose::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_LocalPoseRec.encode(bytes + pos);
	pos += m_LocalPoseRec.getSize();
}

void SetLocalPose::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_LocalPoseRec.decode(bytes + pos);
	pos += m_LocalPoseRec.getSize();
}

SetLocalPose::Body &SetLocalPose::Body::operator=(const Body &value)
{
	m_LocalPoseRec = value.m_LocalPoseRec;
	m_LocalPoseRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool SetLocalPose::Body::operator==(const Body &value) const
{
	if (m_LocalPoseRec != value.m_LocalPoseRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool SetLocalPose::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

SetLocalPose::Body::Body()
{
	m_LocalPoseRec.setParent(this);
	/// No Initialization of m_LocalPoseRec necessary.
}

SetLocalPose::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_LocalPoseRec.setParent(this);
	/// No Initialization of m_LocalPoseRec necessary.
	
	/// Copy the values
	m_LocalPoseRec = value.m_LocalPoseRec;
	m_LocalPoseRec.setParent(this);
	/// This code is currently not supported
}

SetLocalPose::Body::~Body()
{
}

SetLocalPose::Body* const SetLocalPose::getBody()
{
	return &m_Body;
}

int SetLocalPose::setBody(const Body &value)
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
const unsigned int SetLocalPose::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void SetLocalPose::encode(unsigned char *bytes)
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

void SetLocalPose::decode(const unsigned char *bytes)
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

SetLocalPose &SetLocalPose::operator=(const SetLocalPose &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool SetLocalPose::operator==(const SetLocalPose &value) const
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

bool SetLocalPose::operator!=(const SetLocalPose &value) const
{
	return !((*this) == value);
}

SetLocalPose::SetLocalPose()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

SetLocalPose::SetLocalPose(const SetLocalPose &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

SetLocalPose::~SetLocalPose()
{
}


}
