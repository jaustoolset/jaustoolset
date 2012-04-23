#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/ReportStillImageSensorCapabilities.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void ReportStillImageSensorCapabilities::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportStillImageSensorCapabilities::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportStillImageSensorCapabilities::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportStillImageSensorCapabilities::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportStillImageSensorCapabilities::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportStillImageSensorCapabilities::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportStillImageSensorCapabilities::AppHeader::HeaderRec &ReportStillImageSensorCapabilities::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportStillImageSensorCapabilities::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportStillImageSensorCapabilities::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorCapabilities::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4812;
}

ReportStillImageSensorCapabilities::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4812;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportStillImageSensorCapabilities::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportStillImageSensorCapabilities::AppHeader::HeaderRec* const ReportStillImageSensorCapabilities::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportStillImageSensorCapabilities::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportStillImageSensorCapabilities::AppHeader::setParentPresenceVector()
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
const unsigned int ReportStillImageSensorCapabilities::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportStillImageSensorCapabilities::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportStillImageSensorCapabilities::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportStillImageSensorCapabilities::AppHeader &ReportStillImageSensorCapabilities::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportStillImageSensorCapabilities::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportStillImageSensorCapabilities::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorCapabilities::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportStillImageSensorCapabilities::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportStillImageSensorCapabilities::AppHeader::~AppHeader()
{
}

ReportStillImageSensorCapabilities::AppHeader* const ReportStillImageSensorCapabilities::getAppHeader()
{
	return &m_AppHeader;
}

int ReportStillImageSensorCapabilities::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::setParent(StillImageSensorList* parent)
{
	m_parent = parent;
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::getPresenceVector()
{
	return m_PresenceVector;
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
	return 0;
}

bool ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::getSensorID()
{
	return m_SensorID;
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setParent(StillImageSensorCapabilitiesRec* parent)
{
	m_parent = parent;
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(0);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getSqcif_128x96()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 0; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setSqcif_128x96(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 0; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getQcif_176x144()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 1; index <= 1; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setQcif_176x144(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 1; index <= 1; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getCif_352x288()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 2; index <= 2; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setCif_352x288(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 2; index <= 2; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getCif4_704x576()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 3; index <= 3; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setCif4_704x576(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 3; index <= 3; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getCif16_1408x1152()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 4; index <= 4; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setCif16_1408x1152(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 4; index <= 4; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getQqvga_160x120()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 5; index <= 5; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setQqvga_160x120(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 5; index <= 5; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getQvga_320x240()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 6; index <= 6; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setQvga_320x240(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 6; index <= 6; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getVga_640x480()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 7; index <= 7; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setVga_640x480(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 7; index <= 7; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getSvga_800x600()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 8; index <= 8; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setSvga_800x600(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 8; index <= 8; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getXga_1024x768()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 9; index <= 9; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setXga_1024x768(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 9; index <= 9; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getUxga_1600x1200()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 10; index <= 10; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setUxga_1600x1200(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 10; index <= 10; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getQxga_2048x1536()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 11; index <= 11; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setQxga_2048x1536(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 11; index <= 11; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getSxga_1280x1024()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 12; index <= 12; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setSxga_1280x1024(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 12; index <= 12; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getQsxga_2560x2048()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 13; index <= 13; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setQsxga_2560x2048(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 13; index <= 13; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getHsxga_5120x4096()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 14; index <= 14; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setHsxga_5120x4096(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 14; index <= 14; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getWvga_852x480()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 15; index <= 15; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setWvga_852x480(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 15; index <= 15; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getWxga_1366x768()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 16; index <= 16; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setWxga_1366x768(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 16; index <= 16; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getWsxga_1600x1024()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 17; index <= 17; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setWsxga_1600x1024(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 17; index <= 17; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getWuxga_1920x1200()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 18; index <= 18; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setWuxga_1920x1200(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 18; index <= 18; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getWoxga_2560x1600()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 19; index <= 19; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setWoxga_2560x1600(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 19; index <= 19; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getWqsxga_3200x2048()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 20; index <= 20; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setWqsxga_3200x2048(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 20; index <= 20; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getWquxga_3840x2400()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 21; index <= 21; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setWquxga_3840x2400(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 21; index <= 21; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getWhsxga_6400x4096()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 22; index <= 22; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setWhsxga_6400x4096(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 22; index <= 22; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getWhuxga_7680x4800()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 23; index <= 23; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setWhuxga_7680x4800(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 23; index <= 23; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getCga_320x200()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 24; index <= 24; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setCga_320x200(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 24; index <= 24; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getEga_640x350()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 25; index <= 25; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setEga_640x350(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 25; index <= 25; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getHd480_852x480()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 26; index <= 26; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setHd480_852x480(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 26; index <= 26; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getHd720_1280x720()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 27; index <= 27; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setHd720_1280x720(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 27; index <= 27; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getHd1080_1920x1080()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 28; index <= 28; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::setHd1080_1920x1080(jUnsignedInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 28; index <= 28; index++)
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
const unsigned int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::encode(unsigned char *bytes)
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

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::decode(const unsigned char *bytes)
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

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes &ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::operator=(const SupportedFrameSizes &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::operator==(const SupportedFrameSizes &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::operator!=(const SupportedFrameSizes &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::SupportedFrameSizes()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::SupportedFrameSizes(const SupportedFrameSizes &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes::~SupportedFrameSizes()
{
}

bool ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::isSupportedFrameSizesValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedFrameSizes* const ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::getSupportedFrameSizes()
{
	return &m_SupportedFrameSizes;
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::setSupportedFrameSizes(const SupportedFrameSizes &value)
{
	m_SupportedFrameSizes = value;
	setPresenceVector(0);
	setParentPresenceVector();
	return 0;
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setParent(StillImageSensorCapabilitiesRec* parent)
{
	m_parent = parent;
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(1);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getJPEG()
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 0; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedShortInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setJPEG(jUnsignedShortInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 0; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedShortInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getGIF()
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 1; index <= 1; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedShortInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setGIF(jUnsignedShortInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 1; index <= 1; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedShortInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getPNG()
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 2; index <= 2; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedShortInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setPNG(jUnsignedShortInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 2; index <= 2; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedShortInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getBMP()
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 3; index <= 3; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedShortInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setBMP(jUnsignedShortInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 3; index <= 3; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedShortInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getTIFF()
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 4; index <= 4; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedShortInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setTIFF(jUnsignedShortInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 4; index <= 4; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedShortInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getPPM()
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 5; index <= 5; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedShortInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setPPM(jUnsignedShortInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 5; index <= 5; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedShortInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getPGM()
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 6; index <= 6; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedShortInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setPGM(jUnsignedShortInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 6; index <= 6; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedShortInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getPNM()
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 7; index <= 7; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedShortInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setPNM(jUnsignedShortInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 7; index <= 7; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedShortInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getNEF_Nikon_RAW()
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 8; index <= 8; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedShortInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setNEF_Nikon_RAW(jUnsignedShortInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 8; index <= 8; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedShortInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getCR2_Canon_RAW()
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 9; index <= 9; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedShortInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setCR2_Canon_RAW(jUnsignedShortInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 9; index <= 9; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedShortInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedShortInteger ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getDNG_Adobe_RAW()
{
	std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<1> sfbs;
	int i = 0;
	
	for (int index = 10; index <= 10; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedShortInteger)(sfbs.to_ulong());
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::setDNG_Adobe_RAW(jUnsignedShortInteger value)
{
	if ((value == 0) || (value == 1))
	{
		std::bitset<sizeof(jUnsignedShortInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<1> sfbs((int)value);
		int i = 0;
		
		for (int index = 10; index <= 10; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedShortInteger)bfbs.to_ulong();
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
const unsigned int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedShortInteger);
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats &ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::operator=(const SupportedImageFormats &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::operator==(const SupportedImageFormats &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::operator!=(const SupportedImageFormats &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::SupportedImageFormats()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::SupportedImageFormats(const SupportedImageFormats &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats::~SupportedImageFormats()
{
}

bool ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::isSupportedImageFormatsValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::SupportedImageFormats* const ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::getSupportedImageFormats()
{
	return &m_SupportedImageFormats;
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::setSupportedImageFormats(const SupportedImageFormats &value)
{
	m_SupportedImageFormats = value;
	setPresenceVector(1);
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
const unsigned int ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	if (checkPresenceVector(0))
	{
		size += m_SupportedFrameSizes.getSize();
	}
	if (checkPresenceVector(1))
	{
		size += m_SupportedImageFormats.getSize();
	}
	
	return size;
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::encode(unsigned char *bytes)
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
		m_SupportedFrameSizes.encode(bytes + pos);
		pos += m_SupportedFrameSizes.getSize();
	}
	if (checkPresenceVector(1))
	{
		m_SupportedImageFormats.encode(bytes + pos);
		pos += m_SupportedImageFormats.getSize();
	}
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::decode(const unsigned char *bytes)
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
		m_SupportedFrameSizes.decode(bytes + pos);
		pos += m_SupportedFrameSizes.getSize();
	}
	if (checkPresenceVector(1))
	{
		m_SupportedImageFormats.decode(bytes + pos);
		pos += m_SupportedImageFormats.getSize();
	}
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec &ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::operator=(const StillImageSensorCapabilitiesRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_SensorID = value.m_SensorID;
	m_SupportedFrameSizes = value.m_SupportedFrameSizes;
	m_SupportedImageFormats = value.m_SupportedImageFormats;
	
	return *this;
}

bool ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::operator==(const StillImageSensorCapabilitiesRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	
	if (m_SupportedFrameSizes != value.m_SupportedFrameSizes)
	{
		return false;
	}
	
	if (m_SupportedImageFormats != value.m_SupportedImageFormats)
	{
		return false;
	}
	
	return true;
}

bool ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::operator!=(const StillImageSensorCapabilitiesRec &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::StillImageSensorCapabilitiesRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_SensorID = 0;
	m_SupportedFrameSizes.setParent(this);
	m_SupportedImageFormats.setParent(this);
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::StillImageSensorCapabilitiesRec(const StillImageSensorCapabilitiesRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	m_SensorID = 0;
	m_SupportedFrameSizes.setParent(this);
	m_SupportedImageFormats.setParent(this);
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_SensorID = value.m_SensorID;
	m_SupportedFrameSizes = value.m_SupportedFrameSizes;
	m_SupportedImageFormats = value.m_SupportedImageFormats;
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec::~StillImageSensorCapabilitiesRec()
{
}

unsigned int ReportStillImageSensorCapabilities::Body::StillImageSensorList::getNumberOfElements() const
{
	return (unsigned int) m_StillImageSensorCapabilitiesRec.size();
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorCapabilitiesRec* const ReportStillImageSensorCapabilities::Body::StillImageSensorList::getElement(unsigned int index)
{
	return &m_StillImageSensorCapabilitiesRec.at(index);
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::setElement(unsigned int index, const StillImageSensorCapabilitiesRec &value)
{
	if(m_StillImageSensorCapabilitiesRec.size()-1 < index)
	{
		return 1;
	}
	
	m_StillImageSensorCapabilitiesRec.at(index) = value;
	m_StillImageSensorCapabilitiesRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::addElement(const StillImageSensorCapabilitiesRec &value)
{
	m_StillImageSensorCapabilitiesRec.push_back(value);
	m_StillImageSensorCapabilitiesRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::deleteElement(unsigned int index)
{
	if(m_StillImageSensorCapabilitiesRec.size()-1 < index)
	{
		return 1;
	}
	
	m_StillImageSensorCapabilitiesRec.erase(m_StillImageSensorCapabilitiesRec.begin()+index);
	return 0;
}

int ReportStillImageSensorCapabilities::Body::StillImageSensorList::deleteLastElement()
{
	m_StillImageSensorCapabilitiesRec.pop_back();
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
const unsigned int ReportStillImageSensorCapabilities::Body::StillImageSensorList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_StillImageSensorCapabilitiesRec.size(); i++)
	{
		size += m_StillImageSensorCapabilitiesRec[i].getSize();
	}
	
	return size;
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_StillImageSensorCapabilitiesRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_StillImageSensorCapabilitiesRec.size(); i++)
	{
		m_StillImageSensorCapabilitiesRec[i].encode(bytes + pos);
		pos += m_StillImageSensorCapabilitiesRec[i].getSize();
	}
}

void ReportStillImageSensorCapabilities::Body::StillImageSensorList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_StillImageSensorCapabilitiesRec.resize(size);
	for (int i = 0; i < m_StillImageSensorCapabilitiesRec.size(); i++)
	{
		m_StillImageSensorCapabilitiesRec[i].decode(bytes + pos);
		pos += m_StillImageSensorCapabilitiesRec[i].getSize();
	}
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList &ReportStillImageSensorCapabilities::Body::StillImageSensorList::operator=(const StillImageSensorList &value)
{
	m_StillImageSensorCapabilitiesRec.clear();
	
	for (int i = 0; i < value.m_StillImageSensorCapabilitiesRec.size(); i++)
	{
		m_StillImageSensorCapabilitiesRec.push_back(value.m_StillImageSensorCapabilitiesRec[i]);
		m_StillImageSensorCapabilitiesRec[i].setParent(this);
	}
	
	return *this;
}

bool ReportStillImageSensorCapabilities::Body::StillImageSensorList::operator==(const StillImageSensorList &value) const
{
	for (int i = 0; i < m_StillImageSensorCapabilitiesRec.size(); i++)
	{
		if (m_StillImageSensorCapabilitiesRec[i] !=  value.m_StillImageSensorCapabilitiesRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportStillImageSensorCapabilities::Body::StillImageSensorList::operator!=(const StillImageSensorList &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_StillImageSensorCapabilitiesRec.size(); i++)
	{
		m_StillImageSensorCapabilitiesRec[i].setParent(this);
	}
	/// No Initialization of m_StillImageSensorCapabilitiesRec necessary.
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::StillImageSensorList(const StillImageSensorList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_StillImageSensorCapabilitiesRec.size(); i++)
	{
		m_StillImageSensorCapabilitiesRec[i].setParent(this);
	}
	/// No Initialization of m_StillImageSensorCapabilitiesRec necessary.
	
	/// Copy the values
	m_StillImageSensorCapabilitiesRec.clear();
	
	for (int i = 0; i < value.m_StillImageSensorCapabilitiesRec.size(); i++)
	{
		m_StillImageSensorCapabilitiesRec.push_back(value.m_StillImageSensorCapabilitiesRec[i]);
		m_StillImageSensorCapabilitiesRec[i].setParent(this);
	}
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList::~StillImageSensorList()
{
}

ReportStillImageSensorCapabilities::Body::StillImageSensorList* const ReportStillImageSensorCapabilities::Body::getStillImageSensorList()
{
	return &m_StillImageSensorList;
}

int ReportStillImageSensorCapabilities::Body::setStillImageSensorList(const StillImageSensorList &value)
{
	m_StillImageSensorList = value;
	setParentPresenceVector();
	return 0;
}

void ReportStillImageSensorCapabilities::Body::setParentPresenceVector()
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
const unsigned int ReportStillImageSensorCapabilities::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_StillImageSensorList.getSize();
	
	return size;
}

void ReportStillImageSensorCapabilities::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_StillImageSensorList.encode(bytes + pos);
	pos += m_StillImageSensorList.getSize();
}

void ReportStillImageSensorCapabilities::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_StillImageSensorList.decode(bytes + pos);
	pos += m_StillImageSensorList.getSize();
}

ReportStillImageSensorCapabilities::Body &ReportStillImageSensorCapabilities::Body::operator=(const Body &value)
{
	m_StillImageSensorList = value.m_StillImageSensorList;
	m_StillImageSensorList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportStillImageSensorCapabilities::Body::operator==(const Body &value) const
{
	if (m_StillImageSensorList != value.m_StillImageSensorList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportStillImageSensorCapabilities::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorCapabilities::Body::Body()
{
	m_StillImageSensorList.setParent(this);
	/// No Initialization of m_StillImageSensorList necessary.
}

ReportStillImageSensorCapabilities::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_StillImageSensorList.setParent(this);
	/// No Initialization of m_StillImageSensorList necessary.
	
	/// Copy the values
	m_StillImageSensorList = value.m_StillImageSensorList;
	m_StillImageSensorList.setParent(this);
	/// This code is currently not supported
}

ReportStillImageSensorCapabilities::Body::~Body()
{
}

ReportStillImageSensorCapabilities::Body* const ReportStillImageSensorCapabilities::getBody()
{
	return &m_Body;
}

int ReportStillImageSensorCapabilities::setBody(const Body &value)
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
const unsigned int ReportStillImageSensorCapabilities::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportStillImageSensorCapabilities::encode(unsigned char *bytes)
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

void ReportStillImageSensorCapabilities::decode(const unsigned char *bytes)
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

ReportStillImageSensorCapabilities &ReportStillImageSensorCapabilities::operator=(const ReportStillImageSensorCapabilities &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportStillImageSensorCapabilities::operator==(const ReportStillImageSensorCapabilities &value) const
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

bool ReportStillImageSensorCapabilities::operator!=(const ReportStillImageSensorCapabilities &value) const
{
	return !((*this) == value);
}

ReportStillImageSensorCapabilities::ReportStillImageSensorCapabilities()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportStillImageSensorCapabilities::ReportStillImageSensorCapabilities(const ReportStillImageSensorCapabilities &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportStillImageSensorCapabilities::~ReportStillImageSensorCapabilities()
{
}


}
