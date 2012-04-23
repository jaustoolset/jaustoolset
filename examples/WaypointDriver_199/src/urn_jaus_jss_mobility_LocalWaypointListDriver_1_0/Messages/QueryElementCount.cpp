#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/QueryElementCount.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

void QueryElementCount::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryElementCount::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryElementCount::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryElementCount::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryElementCount::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryElementCount::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryElementCount::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryElementCount::AppHeader::HeaderRec &QueryElementCount::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryElementCount::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryElementCount::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryElementCount::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x241c;
}

QueryElementCount::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x241c;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryElementCount::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryElementCount::AppHeader::HeaderRec* const QueryElementCount::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryElementCount::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryElementCount::AppHeader::setParentPresenceVector()
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
const unsigned int QueryElementCount::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryElementCount::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryElementCount::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryElementCount::AppHeader &QueryElementCount::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryElementCount::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryElementCount::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryElementCount::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryElementCount::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryElementCount::AppHeader::~AppHeader()
{
}

QueryElementCount::AppHeader* const QueryElementCount::getAppHeader()
{
	return &m_AppHeader;
}

int QueryElementCount::setAppHeader(const AppHeader &value)
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
const unsigned int QueryElementCount::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	
	return size;
}

void QueryElementCount::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AppHeader.encode(bytes + pos);
	pos += m_AppHeader.getSize();
}

void QueryElementCount::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AppHeader.decode(bytes + pos);
	pos += m_AppHeader.getSize();
}

QueryElementCount &QueryElementCount::operator=(const QueryElementCount &value)
{
	m_AppHeader = value.m_AppHeader;
	
	return *this;
}

bool QueryElementCount::operator==(const QueryElementCount &value) const
{
	if (m_AppHeader != value.m_AppHeader)
	{
		return false;
	}
	
	return true;
}

bool QueryElementCount::operator!=(const QueryElementCount &value) const
{
	return !((*this) == value);
}

QueryElementCount::QueryElementCount()
{
	/// No Initialization of m_AppHeader necessary.
	m_IsCommand = false;
}

QueryElementCount::QueryElementCount(const QueryElementCount &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
}

QueryElementCount::~QueryElementCount()
{
}


}
