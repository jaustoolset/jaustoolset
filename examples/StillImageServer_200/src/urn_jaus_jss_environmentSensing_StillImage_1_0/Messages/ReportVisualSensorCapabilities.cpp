#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/ReportVisualSensorCapabilities.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void ReportVisualSensorCapabilities::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportVisualSensorCapabilities::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportVisualSensorCapabilities::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportVisualSensorCapabilities::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportVisualSensorCapabilities::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportVisualSensorCapabilities::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportVisualSensorCapabilities::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportVisualSensorCapabilities::AppHeader::HeaderRec &ReportVisualSensorCapabilities::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportVisualSensorCapabilities::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportVisualSensorCapabilities::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4806;
}

ReportVisualSensorCapabilities::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4806;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportVisualSensorCapabilities::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportVisualSensorCapabilities::AppHeader::HeaderRec* const ReportVisualSensorCapabilities::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportVisualSensorCapabilities::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportVisualSensorCapabilities::AppHeader::setParentPresenceVector()
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
const unsigned int ReportVisualSensorCapabilities::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportVisualSensorCapabilities::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportVisualSensorCapabilities::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportVisualSensorCapabilities::AppHeader &ReportVisualSensorCapabilities::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportVisualSensorCapabilities::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportVisualSensorCapabilities::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportVisualSensorCapabilities::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportVisualSensorCapabilities::AppHeader::~AppHeader()
{
}

ReportVisualSensorCapabilities::AppHeader* const ReportVisualSensorCapabilities::getAppHeader()
{
	return &m_AppHeader;
}

int ReportVisualSensorCapabilities::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setParent(VisualSensorCapabilitiesList* parent)
{
	m_parent = parent;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getPresenceVector()
{
	return m_PresenceVector;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedShortInteger)pvBitSet.to_ulong();
	return 0;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

jUnsignedShortInteger ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getSensorID()
{
	return m_SensorID;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jVariableLengthString ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getSensorName()
{
	return m_SensorName;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setSensorName(jVariableLengthString value)
{
	if ( value.length() > 255)
	{
		return 1;
	}
	
	m_SensorName = value;
	if (m_SensorName.length() < 0)
	{
		m_SensorName.resize(0);
	}
	setParentPresenceVector();
	return 0;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::setParent(VisualSensorCapabilitiesRec* parent)
{
	m_parent = parent;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(0);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::getActive()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 0; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::setActive(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 0; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::getStandby()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 1; index <= 1; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::setStandby(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 1; index <= 1; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::getOff()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 2; index <= 2; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::setOff(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 2; index <= 2; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
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
const unsigned int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedByte);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates &ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::operator=(const SupportedStates &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::operator==(const SupportedStates &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::operator!=(const SupportedStates &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::SupportedStates()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::SupportedStates(const SupportedStates &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates::~SupportedStates()
{
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isSupportedStatesValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::SupportedStates* const ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getSupportedStates()
{
	return &m_SupportedStates;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setSupportedStates(const SupportedStates &value)
{
	m_SupportedStates = value;
	setPresenceVector(0);
	setParentPresenceVector();
	return 0;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::setParent(VisualSensorCapabilitiesRec* parent)
{
	m_parent = parent;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(1);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::getMixed()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 0; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::setMixed(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 0; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::getAnalogOnly()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 1; index <= 1; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::setAnalogOnly(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 1; index <= 1; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::getDigitalOnly()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 2; index <= 2; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::setDigitalOnly(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 2; index <= 2; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::getNone()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 3; index <= 3; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::setNone(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 3; index <= 3; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
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
const unsigned int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedByte);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes &ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::operator=(const ZoomModes &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::operator==(const ZoomModes &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::operator!=(const ZoomModes &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::ZoomModes()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::ZoomModes(const ZoomModes &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes::~ZoomModes()
{
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isZoomModesValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ZoomModes* const ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getZoomModes()
{
	return &m_ZoomModes;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setZoomModes(const ZoomModes &value)
{
	m_ZoomModes = value;
	setPresenceVector(1);
	setParentPresenceVector();
	return 0;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::setParent(VisualSensorCapabilitiesRec* parent)
{
	m_parent = parent;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(2);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::getAutoFocus()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 0; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::setAutoFocus(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 0; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::getManualFocus()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 1; index <= 1; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::setManualFocus(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 1; index <= 1; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
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
const unsigned int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedByte);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes &ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::operator=(const FocusModes &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::operator==(const FocusModes &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::operator!=(const FocusModes &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::FocusModes()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::FocusModes(const FocusModes &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes::~FocusModes()
{
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isFocusModesValid() const
{
	if (checkPresenceVector(2))
	{
		return true;
	}
	return false;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::FocusModes* const ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getFocusModes()
{
	return &m_FocusModes;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setFocusModes(const FocusModes &value)
{
	m_FocusModes = value;
	setPresenceVector(2);
	setParentPresenceVector();
	return 0;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::setParent(VisualSensorCapabilitiesRec* parent)
{
	m_parent = parent;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(3);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::getAuto()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 0; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::setAuto(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 0; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::getDaylight()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 1; index <= 1; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::setDaylight(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 1; index <= 1; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::getCloudy()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 2; index <= 2; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::setCloudy(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 2; index <= 2; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::getShade()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 3; index <= 3; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::setShade(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 3; index <= 3; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::getTungsten()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 4; index <= 4; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::setTungsten(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 4; index <= 4; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::getFlurescent()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 5; index <= 5; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::setFlurescent(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 5; index <= 5; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::getFlash()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 6; index <= 6; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::setFlash(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 6; index <= 6; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
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
const unsigned int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedByte);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance &ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::operator=(const WhiteBalance &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::operator==(const WhiteBalance &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::operator!=(const WhiteBalance &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::WhiteBalance()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::WhiteBalance(const WhiteBalance &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance::~WhiteBalance()
{
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isWhiteBalanceValid() const
{
	if (checkPresenceVector(3))
	{
		return true;
	}
	return false;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::WhiteBalance* const ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getWhiteBalance()
{
	return &m_WhiteBalance;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setWhiteBalance(const WhiteBalance &value)
{
	m_WhiteBalance = value;
	setPresenceVector(3);
	setParentPresenceVector();
	return 0;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::setParent(VisualSensorCapabilitiesRec* parent)
{
	m_parent = parent;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(4);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::getColor()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 0; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::setColor(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 0; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::getGreyscale()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 1; index <= 1; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::setGreyscale(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 1; index <= 1; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::getInfrared()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 2; index <= 2; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::setInfrared(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 2; index <= 2; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::getLowlight()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 3; index <= 3; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::setLowlight(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 3; index <= 3; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
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
const unsigned int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedByte);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes &ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::operator=(const ImagingModes &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::operator==(const ImagingModes &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::operator!=(const ImagingModes &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::ImagingModes()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::ImagingModes(const ImagingModes &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes::~ImagingModes()
{
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isImagingModesValid() const
{
	if (checkPresenceVector(4))
	{
		return true;
	}
	return false;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ImagingModes* const ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getImagingModes()
{
	return &m_ImagingModes;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setImagingModes(const ImagingModes &value)
{
	m_ImagingModes = value;
	setPresenceVector(4);
	setParentPresenceVector();
	return 0;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::setParent(VisualSensorCapabilitiesRec* parent)
{
	m_parent = parent;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(5);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::getAuto()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 0; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::setAuto(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 0; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::getManual()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 1; index <= 1; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::setManual(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 1; index <= 1; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::getShutterPriority()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 2; index <= 2; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::setShutterPriority(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 2; index <= 2; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::getAperturePriority()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 3; index <= 3; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::setAperturePriority(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 3; index <= 3; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
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
const unsigned int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedByte);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes &ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::operator=(const ExposureModes &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::operator==(const ExposureModes &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::operator!=(const ExposureModes &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::ExposureModes()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::ExposureModes(const ExposureModes &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes::~ExposureModes()
{
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isExposureModesValid() const
{
	if (checkPresenceVector(5))
	{
		return true;
	}
	return false;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::ExposureModes* const ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getExposureModes()
{
	return &m_ExposureModes;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setExposureModes(const ExposureModes &value)
{
	m_ExposureModes = value;
	setPresenceVector(5);
	setParentPresenceVector();
	return 0;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::setParent(VisualSensorCapabilitiesRec* parent)
{
	m_parent = parent;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(6);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::getMatrixOrAuto()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 0; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::setMatrixOrAuto(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 0; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::getCenterWeighted()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 1; index <= 1; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::setCenterWeighted(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 1; index <= 1; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::getSpot()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 2; index <= 2; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::setSpot(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 2; index <= 2; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
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
const unsigned int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedByte);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes &ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::operator=(const MeteringModes &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::operator==(const MeteringModes &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::operator!=(const MeteringModes &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::MeteringModes()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::MeteringModes(const MeteringModes &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes::~MeteringModes()
{
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isMeteringModesValid() const
{
	if (checkPresenceVector(6))
	{
		return true;
	}
	return false;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::MeteringModes* const ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getMeteringModes()
{
	return &m_MeteringModes;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setMeteringModes(const MeteringModes &value)
{
	m_MeteringModes = value;
	setPresenceVector(6);
	setParentPresenceVector();
	return 0;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isMinimumShutterSpeedValid() const
{
	if (checkPresenceVector(7))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getMinimumShutterSpeed()
{
	double value;
	
	double scaleFactor = ( 60.0 - 0.0 ) / 65535.0;
	double bias = 0.0;
	
	value = m_MinimumShutterSpeed * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setMinimumShutterSpeed(double value)
{
	if ((value >= 0.0) && (value <= 60.0))
	{
		double scaleFactor = ( 60.0 - 0.0 ) / 65535.0;
		double bias = 0.0;
		
		m_MinimumShutterSpeed= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(7);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isMaximumShutterSpeedValid() const
{
	if (checkPresenceVector(8))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getMaximumShutterSpeed()
{
	double value;
	
	double scaleFactor = ( 60.0 - 0.0 ) / 65535.0;
	double bias = 0.0;
	
	value = m_MaximumShutterSpeed * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setMaximumShutterSpeed(double value)
{
	if ((value >= 0.0) && (value <= 60.0))
	{
		double scaleFactor = ( 60.0 - 0.0 ) / 65535.0;
		double bias = 0.0;
		
		m_MaximumShutterSpeed= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(8);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isMinimumApertureValid() const
{
	if (checkPresenceVector(9))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getMinimumAperture()
{
	double value;
	
	double scaleFactor = ( 128.0 - 0.1 ) / 65535.0;
	double bias = 0.1;
	
	value = m_MinimumAperture * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setMinimumAperture(double value)
{
	if ((value >= 0.1) && (value <= 128.0))
	{
		double scaleFactor = ( 128.0 - 0.1 ) / 65535.0;
		double bias = 0.1;
		
		m_MinimumAperture= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(9);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isMaximumApertureValid() const
{
	if (checkPresenceVector(10))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getMaximumAperture()
{
	double value;
	
	double scaleFactor = ( 128.0 - 0.1 ) / 65535.0;
	double bias = 0.1;
	
	value = m_MaximumAperture * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setMaximumAperture(double value)
{
	if ((value >= 0.1) && (value <= 128.0))
	{
		double scaleFactor = ( 128.0 - 0.1 ) / 65535.0;
		double bias = 0.1;
		
		m_MaximumAperture= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setPresenceVector(10);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isMinimumFocalLengthValid() const
{
	if (checkPresenceVector(11))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getMinimumFocalLength()
{
	double value;
	
	double scaleFactor = ( 2.0 - 0.0 ) / 4.294967295E9;
	double bias = 0.0;
	
	value = m_MinimumFocalLength * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setMinimumFocalLength(double value)
{
	if ((value >= 0.0) && (value <= 2.0))
	{
		double scaleFactor = ( 2.0 - 0.0 ) / 4.294967295E9;
		double bias = 0.0;
		
		m_MinimumFocalLength= (jUnsignedInteger)((value - bias) / scaleFactor);
		setPresenceVector(11);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isMaximumFocalLengthValid() const
{
	if (checkPresenceVector(12))
	{
		return true;
	}
	return false;
}

double ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getMaximumFocalLength()
{
	double value;
	
	double scaleFactor = ( 2.0 - 0.0 ) / 4.294967295E9;
	double bias = 0.0;
	
	value = m_MaximumFocalLength * scaleFactor + bias;
	
	return value;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setMaximumFocalLength(double value)
{
	if ((value >= 0.0) && (value <= 2.0))
	{
		double scaleFactor = ( 2.0 - 0.0 ) / 4.294967295E9;
		double bias = 0.0;
		
		m_MaximumFocalLength= (jUnsignedInteger)((value - bias) / scaleFactor);
		setPresenceVector(12);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::setParent(VisualSensorCapabilitiesRec* parent)
{
	m_parent = parent;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(13);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::getAuto()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 0; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::setAuto(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 0; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::getISO100()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 1; index <= 1; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::setISO100(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 1; index <= 1; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::getISO200()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 2; index <= 2; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::setISO200(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 2; index <= 2; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::getISO400()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 3; index <= 3; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::setISO400(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 3; index <= 3; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::getISO800()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 4; index <= 4; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::setISO800(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 4; index <= 4; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::getISO1600()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 5; index <= 5; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::setISO1600(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 5; index <= 5; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::getISO3200()
{
	std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 6; index <= 6; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedByte)(sfbs.to_ulong());
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::setISO3200(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedByte) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 6; index <= 6; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedByte)bfbs.to_ulong();
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
const unsigned int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedByte);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels &ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::operator=(const LightSensitivityLevels &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::operator==(const LightSensitivityLevels &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::operator!=(const LightSensitivityLevels &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::LightSensitivityLevels()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::LightSensitivityLevels(const LightSensitivityLevels &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels::~LightSensitivityLevels()
{
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isLightSensitivityLevelsValid() const
{
	if (checkPresenceVector(13))
	{
		return true;
	}
	return false;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::LightSensitivityLevels* const ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getLightSensitivityLevels()
{
	return &m_LightSensitivityLevels;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setLightSensitivityLevels(const LightSensitivityLevels &value)
{
	m_LightSensitivityLevels = value;
	setPresenceVector(13);
	setParentPresenceVector();
	return 0;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::isImageStabilizationValid() const
{
	if (checkPresenceVector(14))
	{
		return true;
	}
	return false;
}

jUnsignedByte ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getImageStabilization()
{
	return m_ImageStabilization;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::setImageStabilization(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		m_ImageStabilization = value;
		setPresenceVector(14);
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
const unsigned int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += m_SensorName.length();
	if (checkPresenceVector(0))
	{
		size += m_SupportedStates.getSize();
	}
	if (checkPresenceVector(1))
	{
		size += m_ZoomModes.getSize();
	}
	if (checkPresenceVector(2))
	{
		size += m_FocusModes.getSize();
	}
	if (checkPresenceVector(3))
	{
		size += m_WhiteBalance.getSize();
	}
	if (checkPresenceVector(4))
	{
		size += m_ImagingModes.getSize();
	}
	if (checkPresenceVector(5))
	{
		size += m_ExposureModes.getSize();
	}
	if (checkPresenceVector(6))
	{
		size += m_MeteringModes.getSize();
	}
	if (checkPresenceVector(7))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(8))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(9))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(10))
	{
		size += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(11))
	{
		size += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(12))
	{
		size += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(13))
	{
		size += m_LightSensitivityLevels.getSize();
	}
	if (checkPresenceVector(14))
	{
		size += sizeof(jUnsignedByte);
	}
	
	return size;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::encode(unsigned char *bytes)
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
	jUnsignedShortInteger m_SensorIDTemp;
	
	m_SensorIDTemp = JSIDL_v_1_0::correctEndianness(m_SensorID);
	memcpy(bytes + pos, &m_SensorIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	
	jUnsignedByte m_SensorNameLength = 0;
	m_SensorNameLength = JSIDL_v_1_0::correctEndianness(m_SensorName.length());
	memcpy(bytes+pos, (unsigned char*)&m_SensorNameLength, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	memcpy(bytes+pos, m_SensorName.c_str(), m_SensorName.length());
	pos += m_SensorName.length();
	if (checkPresenceVector(0))
	{
		m_SupportedStates.encode(bytes + pos);
		pos += m_SupportedStates.getSize();
	}
	if (checkPresenceVector(1))
	{
		m_ZoomModes.encode(bytes + pos);
		pos += m_ZoomModes.getSize();
	}
	if (checkPresenceVector(2))
	{
		m_FocusModes.encode(bytes + pos);
		pos += m_FocusModes.getSize();
	}
	if (checkPresenceVector(3))
	{
		m_WhiteBalance.encode(bytes + pos);
		pos += m_WhiteBalance.getSize();
	}
	if (checkPresenceVector(4))
	{
		m_ImagingModes.encode(bytes + pos);
		pos += m_ImagingModes.getSize();
	}
	if (checkPresenceVector(5))
	{
		m_ExposureModes.encode(bytes + pos);
		pos += m_ExposureModes.getSize();
	}
	if (checkPresenceVector(6))
	{
		m_MeteringModes.encode(bytes + pos);
		pos += m_MeteringModes.getSize();
	}
	if (checkPresenceVector(7))
	{
		jUnsignedShortInteger m_MinimumShutterSpeedTemp;
		
		m_MinimumShutterSpeedTemp = JSIDL_v_1_0::correctEndianness(m_MinimumShutterSpeed);
		memcpy(bytes + pos, &m_MinimumShutterSpeedTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(8))
	{
		jUnsignedShortInteger m_MaximumShutterSpeedTemp;
		
		m_MaximumShutterSpeedTemp = JSIDL_v_1_0::correctEndianness(m_MaximumShutterSpeed);
		memcpy(bytes + pos, &m_MaximumShutterSpeedTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(9))
	{
		jUnsignedShortInteger m_MinimumApertureTemp;
		
		m_MinimumApertureTemp = JSIDL_v_1_0::correctEndianness(m_MinimumAperture);
		memcpy(bytes + pos, &m_MinimumApertureTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(10))
	{
		jUnsignedShortInteger m_MaximumApertureTemp;
		
		m_MaximumApertureTemp = JSIDL_v_1_0::correctEndianness(m_MaximumAperture);
		memcpy(bytes + pos, &m_MaximumApertureTemp, sizeof(jUnsignedShortInteger));
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(11))
	{
		jUnsignedInteger m_MinimumFocalLengthTemp;
		
		m_MinimumFocalLengthTemp = JSIDL_v_1_0::correctEndianness(m_MinimumFocalLength);
		memcpy(bytes + pos, &m_MinimumFocalLengthTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(12))
	{
		jUnsignedInteger m_MaximumFocalLengthTemp;
		
		m_MaximumFocalLengthTemp = JSIDL_v_1_0::correctEndianness(m_MaximumFocalLength);
		memcpy(bytes + pos, &m_MaximumFocalLengthTemp, sizeof(jUnsignedInteger));
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(13))
	{
		m_LightSensitivityLevels.encode(bytes + pos);
		pos += m_LightSensitivityLevels.getSize();
	}
	if (checkPresenceVector(14))
	{
		jUnsignedByte m_ImageStabilizationTemp;
		
		m_ImageStabilizationTemp = JSIDL_v_1_0::correctEndianness(m_ImageStabilization);
		memcpy(bytes + pos, &m_ImageStabilizationTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::decode(const unsigned char *bytes)
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
	jUnsignedShortInteger m_SensorIDTemp;
	
	memcpy(&m_SensorIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SensorID = JSIDL_v_1_0::correctEndianness(m_SensorIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	
	jUnsignedByte m_SensorNameLength = 0;
	memcpy((unsigned char*)&m_SensorNameLength, bytes+pos, sizeof( m_SensorNameLength ));
	m_SensorNameLength = JSIDL_v_1_0::correctEndianness(m_SensorNameLength);
	pos += sizeof( m_SensorNameLength );
	
	char* m_SensorNameTemp = new char[m_SensorNameLength+1];
	memcpy(m_SensorNameTemp, bytes+pos, m_SensorNameLength );
	m_SensorNameTemp[m_SensorNameLength] = '\0';
	m_SensorName = m_SensorNameTemp;
	pos += m_SensorNameLength ;
	delete m_SensorNameTemp;
	if (checkPresenceVector(0))
	{
		m_SupportedStates.decode(bytes + pos);
		pos += m_SupportedStates.getSize();
	}
	if (checkPresenceVector(1))
	{
		m_ZoomModes.decode(bytes + pos);
		pos += m_ZoomModes.getSize();
	}
	if (checkPresenceVector(2))
	{
		m_FocusModes.decode(bytes + pos);
		pos += m_FocusModes.getSize();
	}
	if (checkPresenceVector(3))
	{
		m_WhiteBalance.decode(bytes + pos);
		pos += m_WhiteBalance.getSize();
	}
	if (checkPresenceVector(4))
	{
		m_ImagingModes.decode(bytes + pos);
		pos += m_ImagingModes.getSize();
	}
	if (checkPresenceVector(5))
	{
		m_ExposureModes.decode(bytes + pos);
		pos += m_ExposureModes.getSize();
	}
	if (checkPresenceVector(6))
	{
		m_MeteringModes.decode(bytes + pos);
		pos += m_MeteringModes.getSize();
	}
	if (checkPresenceVector(7))
	{
		jUnsignedShortInteger m_MinimumShutterSpeedTemp;
		
		memcpy(&m_MinimumShutterSpeedTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_MinimumShutterSpeed = JSIDL_v_1_0::correctEndianness(m_MinimumShutterSpeedTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(8))
	{
		jUnsignedShortInteger m_MaximumShutterSpeedTemp;
		
		memcpy(&m_MaximumShutterSpeedTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_MaximumShutterSpeed = JSIDL_v_1_0::correctEndianness(m_MaximumShutterSpeedTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(9))
	{
		jUnsignedShortInteger m_MinimumApertureTemp;
		
		memcpy(&m_MinimumApertureTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_MinimumAperture = JSIDL_v_1_0::correctEndianness(m_MinimumApertureTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(10))
	{
		jUnsignedShortInteger m_MaximumApertureTemp;
		
		memcpy(&m_MaximumApertureTemp, bytes + pos, sizeof(jUnsignedShortInteger));
		m_MaximumAperture = JSIDL_v_1_0::correctEndianness(m_MaximumApertureTemp);
		pos += sizeof(jUnsignedShortInteger);
	}
	if (checkPresenceVector(11))
	{
		jUnsignedInteger m_MinimumFocalLengthTemp;
		
		memcpy(&m_MinimumFocalLengthTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_MinimumFocalLength = JSIDL_v_1_0::correctEndianness(m_MinimumFocalLengthTemp);
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(12))
	{
		jUnsignedInteger m_MaximumFocalLengthTemp;
		
		memcpy(&m_MaximumFocalLengthTemp, bytes + pos, sizeof(jUnsignedInteger));
		m_MaximumFocalLength = JSIDL_v_1_0::correctEndianness(m_MaximumFocalLengthTemp);
		pos += sizeof(jUnsignedInteger);
	}
	if (checkPresenceVector(13))
	{
		m_LightSensitivityLevels.decode(bytes + pos);
		pos += m_LightSensitivityLevels.getSize();
	}
	if (checkPresenceVector(14))
	{
		jUnsignedByte m_ImageStabilizationTemp;
		
		memcpy(&m_ImageStabilizationTemp, bytes + pos, sizeof(jUnsignedByte));
		m_ImageStabilization = JSIDL_v_1_0::correctEndianness(m_ImageStabilizationTemp);
		pos += sizeof(jUnsignedByte);
	}
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec &ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::operator=(const VisualSensorCapabilitiesRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_SensorID = value.m_SensorID;
	m_SensorName = value.m_SensorName;
	m_SupportedStates = value.m_SupportedStates;
	m_ZoomModes = value.m_ZoomModes;
	m_FocusModes = value.m_FocusModes;
	m_WhiteBalance = value.m_WhiteBalance;
	m_ImagingModes = value.m_ImagingModes;
	m_ExposureModes = value.m_ExposureModes;
	m_MeteringModes = value.m_MeteringModes;
	m_MinimumShutterSpeed = value.m_MinimumShutterSpeed;
	m_MaximumShutterSpeed = value.m_MaximumShutterSpeed;
	m_MinimumAperture = value.m_MinimumAperture;
	m_MaximumAperture = value.m_MaximumAperture;
	m_MinimumFocalLength = value.m_MinimumFocalLength;
	m_MaximumFocalLength = value.m_MaximumFocalLength;
	m_LightSensitivityLevels = value.m_LightSensitivityLevels;
	m_ImageStabilization = value.m_ImageStabilization;
	
	return *this;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::operator==(const VisualSensorCapabilitiesRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	if ((m_SensorName.length() != value.m_SensorName.length()) || (m_SensorName.compare(value.m_SensorName) != 0))
	{
		return false;
	}
	
	if (m_SupportedStates != value.m_SupportedStates)
	{
		return false;
	}
	
	if (m_ZoomModes != value.m_ZoomModes)
	{
		return false;
	}
	
	if (m_FocusModes != value.m_FocusModes)
	{
		return false;
	}
	
	if (m_WhiteBalance != value.m_WhiteBalance)
	{
		return false;
	}
	
	if (m_ImagingModes != value.m_ImagingModes)
	{
		return false;
	}
	
	if (m_ExposureModes != value.m_ExposureModes)
	{
		return false;
	}
	
	if (m_MeteringModes != value.m_MeteringModes)
	{
		return false;
	}
	if (m_MinimumShutterSpeed != value.m_MinimumShutterSpeed)
	{
		return false;
	}
	if (m_MaximumShutterSpeed != value.m_MaximumShutterSpeed)
	{
		return false;
	}
	if (m_MinimumAperture != value.m_MinimumAperture)
	{
		return false;
	}
	if (m_MaximumAperture != value.m_MaximumAperture)
	{
		return false;
	}
	if (m_MinimumFocalLength != value.m_MinimumFocalLength)
	{
		return false;
	}
	if (m_MaximumFocalLength != value.m_MaximumFocalLength)
	{
		return false;
	}
	
	if (m_LightSensitivityLevels != value.m_LightSensitivityLevels)
	{
		return false;
	}
	if (m_ImageStabilization != value.m_ImageStabilization)
	{
		return false;
	}
	
	return true;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::operator!=(const VisualSensorCapabilitiesRec &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::VisualSensorCapabilitiesRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_SensorID = 0;
	m_SupportedStates.setParent(this);
	m_ZoomModes.setParent(this);
	m_FocusModes.setParent(this);
	m_WhiteBalance.setParent(this);
	m_ImagingModes.setParent(this);
	m_ExposureModes.setParent(this);
	m_MeteringModes.setParent(this);
	m_MinimumShutterSpeed = 0;
	m_MaximumShutterSpeed = 0;
	m_MinimumAperture = 0;
	m_MaximumAperture = 0;
	m_MinimumFocalLength = 0;
	m_MaximumFocalLength = 0;
	m_LightSensitivityLevels.setParent(this);
	m_ImageStabilization = 0;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::VisualSensorCapabilitiesRec(const VisualSensorCapabilitiesRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	m_SensorID = 0;
	m_SupportedStates.setParent(this);
	m_ZoomModes.setParent(this);
	m_FocusModes.setParent(this);
	m_WhiteBalance.setParent(this);
	m_ImagingModes.setParent(this);
	m_ExposureModes.setParent(this);
	m_MeteringModes.setParent(this);
	m_MinimumShutterSpeed = 0;
	m_MaximumShutterSpeed = 0;
	m_MinimumAperture = 0;
	m_MaximumAperture = 0;
	m_MinimumFocalLength = 0;
	m_MaximumFocalLength = 0;
	m_LightSensitivityLevels.setParent(this);
	m_ImageStabilization = 0;
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_SensorID = value.m_SensorID;
	m_SensorName = value.m_SensorName;
	m_SupportedStates = value.m_SupportedStates;
	m_ZoomModes = value.m_ZoomModes;
	m_FocusModes = value.m_FocusModes;
	m_WhiteBalance = value.m_WhiteBalance;
	m_ImagingModes = value.m_ImagingModes;
	m_ExposureModes = value.m_ExposureModes;
	m_MeteringModes = value.m_MeteringModes;
	m_MinimumShutterSpeed = value.m_MinimumShutterSpeed;
	m_MaximumShutterSpeed = value.m_MaximumShutterSpeed;
	m_MinimumAperture = value.m_MinimumAperture;
	m_MaximumAperture = value.m_MaximumAperture;
	m_MinimumFocalLength = value.m_MinimumFocalLength;
	m_MaximumFocalLength = value.m_MaximumFocalLength;
	m_LightSensitivityLevels = value.m_LightSensitivityLevels;
	m_ImageStabilization = value.m_ImageStabilization;
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec::~VisualSensorCapabilitiesRec()
{
}

unsigned int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::getNumberOfElements() const
{
	return (unsigned int) m_VisualSensorCapabilitiesRec.size();
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesRec* const ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::getElement(unsigned int index)
{
	return &m_VisualSensorCapabilitiesRec.at(index);
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::setElement(unsigned int index, const VisualSensorCapabilitiesRec &value)
{
	if(m_VisualSensorCapabilitiesRec.size()-1 < index)
	{
		return 1;
	}
	
	m_VisualSensorCapabilitiesRec.at(index) = value;
	m_VisualSensorCapabilitiesRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::addElement(const VisualSensorCapabilitiesRec &value)
{
	m_VisualSensorCapabilitiesRec.push_back(value);
	m_VisualSensorCapabilitiesRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::deleteElement(unsigned int index)
{
	if(m_VisualSensorCapabilitiesRec.size()-1 < index)
	{
		return 1;
	}
	
	m_VisualSensorCapabilitiesRec.erase(m_VisualSensorCapabilitiesRec.begin()+index);
	return 0;
}

int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::deleteLastElement()
{
	m_VisualSensorCapabilitiesRec.pop_back();
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
const unsigned int ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_VisualSensorCapabilitiesRec.size(); i++)
	{
		size += m_VisualSensorCapabilitiesRec[i].getSize();
	}
	
	return size;
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_VisualSensorCapabilitiesRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_VisualSensorCapabilitiesRec.size(); i++)
	{
		m_VisualSensorCapabilitiesRec[i].encode(bytes + pos);
		pos += m_VisualSensorCapabilitiesRec[i].getSize();
	}
}

void ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_VisualSensorCapabilitiesRec.resize(size);
	for (int i = 0; i < m_VisualSensorCapabilitiesRec.size(); i++)
	{
		m_VisualSensorCapabilitiesRec[i].decode(bytes + pos);
		pos += m_VisualSensorCapabilitiesRec[i].getSize();
	}
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList &ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::operator=(const VisualSensorCapabilitiesList &value)
{
	m_VisualSensorCapabilitiesRec.clear();
	
	for (int i = 0; i < value.m_VisualSensorCapabilitiesRec.size(); i++)
	{
		m_VisualSensorCapabilitiesRec.push_back(value.m_VisualSensorCapabilitiesRec[i]);
		m_VisualSensorCapabilitiesRec[i].setParent(this);
	}
	
	return *this;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::operator==(const VisualSensorCapabilitiesList &value) const
{
	for (int i = 0; i < m_VisualSensorCapabilitiesRec.size(); i++)
	{
		if (m_VisualSensorCapabilitiesRec[i] !=  value.m_VisualSensorCapabilitiesRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::operator!=(const VisualSensorCapabilitiesList &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_VisualSensorCapabilitiesRec.size(); i++)
	{
		m_VisualSensorCapabilitiesRec[i].setParent(this);
	}
	/// No Initialization of m_VisualSensorCapabilitiesRec necessary.
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::VisualSensorCapabilitiesList(const VisualSensorCapabilitiesList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_VisualSensorCapabilitiesRec.size(); i++)
	{
		m_VisualSensorCapabilitiesRec[i].setParent(this);
	}
	/// No Initialization of m_VisualSensorCapabilitiesRec necessary.
	
	/// Copy the values
	m_VisualSensorCapabilitiesRec.clear();
	
	for (int i = 0; i < value.m_VisualSensorCapabilitiesRec.size(); i++)
	{
		m_VisualSensorCapabilitiesRec.push_back(value.m_VisualSensorCapabilitiesRec[i]);
		m_VisualSensorCapabilitiesRec[i].setParent(this);
	}
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList::~VisualSensorCapabilitiesList()
{
}

ReportVisualSensorCapabilities::Body::VisualSensorCapabilitiesList* const ReportVisualSensorCapabilities::Body::getVisualSensorCapabilitiesList()
{
	return &m_VisualSensorCapabilitiesList;
}

int ReportVisualSensorCapabilities::Body::setVisualSensorCapabilitiesList(const VisualSensorCapabilitiesList &value)
{
	m_VisualSensorCapabilitiesList = value;
	setParentPresenceVector();
	return 0;
}

void ReportVisualSensorCapabilities::Body::setParentPresenceVector()
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
const unsigned int ReportVisualSensorCapabilities::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_VisualSensorCapabilitiesList.getSize();
	
	return size;
}

void ReportVisualSensorCapabilities::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_VisualSensorCapabilitiesList.encode(bytes + pos);
	pos += m_VisualSensorCapabilitiesList.getSize();
}

void ReportVisualSensorCapabilities::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_VisualSensorCapabilitiesList.decode(bytes + pos);
	pos += m_VisualSensorCapabilitiesList.getSize();
}

ReportVisualSensorCapabilities::Body &ReportVisualSensorCapabilities::Body::operator=(const Body &value)
{
	m_VisualSensorCapabilitiesList = value.m_VisualSensorCapabilitiesList;
	m_VisualSensorCapabilitiesList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportVisualSensorCapabilities::Body::operator==(const Body &value) const
{
	if (m_VisualSensorCapabilitiesList != value.m_VisualSensorCapabilitiesList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportVisualSensorCapabilities::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::Body::Body()
{
	m_VisualSensorCapabilitiesList.setParent(this);
	/// No Initialization of m_VisualSensorCapabilitiesList necessary.
}

ReportVisualSensorCapabilities::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_VisualSensorCapabilitiesList.setParent(this);
	/// No Initialization of m_VisualSensorCapabilitiesList necessary.
	
	/// Copy the values
	m_VisualSensorCapabilitiesList = value.m_VisualSensorCapabilitiesList;
	m_VisualSensorCapabilitiesList.setParent(this);
	/// This code is currently not supported
}

ReportVisualSensorCapabilities::Body::~Body()
{
}

ReportVisualSensorCapabilities::Body* const ReportVisualSensorCapabilities::getBody()
{
	return &m_Body;
}

int ReportVisualSensorCapabilities::setBody(const Body &value)
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
const unsigned int ReportVisualSensorCapabilities::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportVisualSensorCapabilities::encode(unsigned char *bytes)
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

void ReportVisualSensorCapabilities::decode(const unsigned char *bytes)
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

ReportVisualSensorCapabilities &ReportVisualSensorCapabilities::operator=(const ReportVisualSensorCapabilities &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportVisualSensorCapabilities::operator==(const ReportVisualSensorCapabilities &value) const
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

bool ReportVisualSensorCapabilities::operator!=(const ReportVisualSensorCapabilities &value) const
{
	return !((*this) == value);
}

ReportVisualSensorCapabilities::ReportVisualSensorCapabilities()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportVisualSensorCapabilities::ReportVisualSensorCapabilities(const ReportVisualSensorCapabilities &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportVisualSensorCapabilities::~ReportVisualSensorCapabilities()
{
}


}
