#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/QueryTravelSpeed.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

void QueryTravelSpeed::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryTravelSpeed::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryTravelSpeed::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryTravelSpeed::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryTravelSpeed::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryTravelSpeed::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryTravelSpeed::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryTravelSpeed::AppHeader::HeaderRec &QueryTravelSpeed::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryTravelSpeed::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryTravelSpeed::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryTravelSpeed::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x240a;
}

QueryTravelSpeed::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x240a;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryTravelSpeed::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryTravelSpeed::AppHeader::HeaderRec* const QueryTravelSpeed::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryTravelSpeed::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryTravelSpeed::AppHeader::setParentPresenceVector()
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
const unsigned int QueryTravelSpeed::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryTravelSpeed::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryTravelSpeed::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryTravelSpeed::AppHeader &QueryTravelSpeed::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryTravelSpeed::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryTravelSpeed::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryTravelSpeed::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryTravelSpeed::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryTravelSpeed::AppHeader::~AppHeader()
{
}

QueryTravelSpeed::AppHeader* const QueryTravelSpeed::getAppHeader()
{
	return &m_AppHeader;
}

int QueryTravelSpeed::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
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
const unsigned int QueryTravelSpeed::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	
	return size;
}

void QueryTravelSpeed::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AppHeader.encode(bytes + pos);
	pos += m_AppHeader.getSize();
}

void QueryTravelSpeed::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AppHeader.decode(bytes + pos);
	pos += m_AppHeader.getSize();
}

QueryTravelSpeed &QueryTravelSpeed::operator=(const QueryTravelSpeed &value)
{
	m_AppHeader = value.m_AppHeader;
	
	return *this;
}

bool QueryTravelSpeed::operator==(const QueryTravelSpeed &value) const
{
	if (m_AppHeader != value.m_AppHeader)
	{
		return false;
	}
	
	return true;
}

bool QueryTravelSpeed::operator!=(const QueryTravelSpeed &value) const
{
	return !((*this) == value);
}

QueryTravelSpeed::QueryTravelSpeed()
{
	/// No Initialization of m_AppHeader necessary.
	m_IsCommand = false;
}

QueryTravelSpeed::QueryTravelSpeed(const QueryTravelSpeed &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
}

QueryTravelSpeed::~QueryTravelSpeed()
{
}


}
