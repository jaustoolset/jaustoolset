#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/ReportTravelSpeed.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

void ReportTravelSpeed::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportTravelSpeed::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportTravelSpeed::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportTravelSpeed::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportTravelSpeed::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportTravelSpeed::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportTravelSpeed::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportTravelSpeed::AppHeader::HeaderRec &ReportTravelSpeed::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportTravelSpeed::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportTravelSpeed::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportTravelSpeed::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x440a;
}

ReportTravelSpeed::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x440a;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportTravelSpeed::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportTravelSpeed::AppHeader::HeaderRec* const ReportTravelSpeed::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportTravelSpeed::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportTravelSpeed::AppHeader::setParentPresenceVector()
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
const unsigned int ReportTravelSpeed::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportTravelSpeed::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportTravelSpeed::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportTravelSpeed::AppHeader &ReportTravelSpeed::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportTravelSpeed::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportTravelSpeed::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportTravelSpeed::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportTravelSpeed::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportTravelSpeed::AppHeader::~AppHeader()
{
}

ReportTravelSpeed::AppHeader* const ReportTravelSpeed::getAppHeader()
{
	return &m_AppHeader;
}

int ReportTravelSpeed::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportTravelSpeed::Body::TravelSpeedRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportTravelSpeed::Body::TravelSpeedRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

double ReportTravelSpeed::Body::TravelSpeedRec::getSpeed()
{
	double value;
	
	double scaleFactor = ( 327.67 - 0 ) / 65535.0;
	double bias = 0;
	
	value = m_Speed * scaleFactor + bias;
	
	return value;
}

int ReportTravelSpeed::Body::TravelSpeedRec::setSpeed(double value)
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

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int ReportTravelSpeed::Body::TravelSpeedRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportTravelSpeed::Body::TravelSpeedRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SpeedTemp;
	
	m_SpeedTemp = JSIDL_v_1_0::correctEndianness(m_Speed);
	memcpy(bytes + pos, &m_SpeedTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void ReportTravelSpeed::Body::TravelSpeedRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SpeedTemp;
	
	memcpy(&m_SpeedTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_Speed = JSIDL_v_1_0::correctEndianness(m_SpeedTemp);
	pos += sizeof(jUnsignedShortInteger);
}

ReportTravelSpeed::Body::TravelSpeedRec &ReportTravelSpeed::Body::TravelSpeedRec::operator=(const TravelSpeedRec &value)
{
	m_Speed = value.m_Speed;
	
	return *this;
}

bool ReportTravelSpeed::Body::TravelSpeedRec::operator==(const TravelSpeedRec &value) const
{
	if (m_Speed != value.m_Speed)
	{
		return false;
	}
	
	return true;
}

bool ReportTravelSpeed::Body::TravelSpeedRec::operator!=(const TravelSpeedRec &value) const
{
	return !((*this) == value);
}

ReportTravelSpeed::Body::TravelSpeedRec::TravelSpeedRec()
{
	m_parent = NULL;
	m_Speed = 0;
}

ReportTravelSpeed::Body::TravelSpeedRec::TravelSpeedRec(const TravelSpeedRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_Speed = 0;
	
	/// Copy the values
	m_Speed = value.m_Speed;
}

ReportTravelSpeed::Body::TravelSpeedRec::~TravelSpeedRec()
{
}

ReportTravelSpeed::Body::TravelSpeedRec* const ReportTravelSpeed::Body::getTravelSpeedRec()
{
	return &m_TravelSpeedRec;
}

int ReportTravelSpeed::Body::setTravelSpeedRec(const TravelSpeedRec &value)
{
	m_TravelSpeedRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportTravelSpeed::Body::setParentPresenceVector()
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
const unsigned int ReportTravelSpeed::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_TravelSpeedRec.getSize();
	
	return size;
}

void ReportTravelSpeed::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_TravelSpeedRec.encode(bytes + pos);
	pos += m_TravelSpeedRec.getSize();
}

void ReportTravelSpeed::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_TravelSpeedRec.decode(bytes + pos);
	pos += m_TravelSpeedRec.getSize();
}

ReportTravelSpeed::Body &ReportTravelSpeed::Body::operator=(const Body &value)
{
	m_TravelSpeedRec = value.m_TravelSpeedRec;
	m_TravelSpeedRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportTravelSpeed::Body::operator==(const Body &value) const
{
	if (m_TravelSpeedRec != value.m_TravelSpeedRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportTravelSpeed::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportTravelSpeed::Body::Body()
{
	m_TravelSpeedRec.setParent(this);
	/// No Initialization of m_TravelSpeedRec necessary.
}

ReportTravelSpeed::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_TravelSpeedRec.setParent(this);
	/// No Initialization of m_TravelSpeedRec necessary.
	
	/// Copy the values
	m_TravelSpeedRec = value.m_TravelSpeedRec;
	m_TravelSpeedRec.setParent(this);
	/// This code is currently not supported
}

ReportTravelSpeed::Body::~Body()
{
}

ReportTravelSpeed::Body* const ReportTravelSpeed::getBody()
{
	return &m_Body;
}

int ReportTravelSpeed::setBody(const Body &value)
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
const unsigned int ReportTravelSpeed::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportTravelSpeed::encode(unsigned char *bytes)
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

void ReportTravelSpeed::decode(const unsigned char *bytes)
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

ReportTravelSpeed &ReportTravelSpeed::operator=(const ReportTravelSpeed &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportTravelSpeed::operator==(const ReportTravelSpeed &value) const
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

bool ReportTravelSpeed::operator!=(const ReportTravelSpeed &value) const
{
	return !((*this) == value);
}

ReportTravelSpeed::ReportTravelSpeed()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportTravelSpeed::ReportTravelSpeed(const ReportTravelSpeed &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportTravelSpeed::~ReportTravelSpeed()
{
}


}
