#include "urn_jaus_jss_mobility_ListManager_1_0/Messages/QueryTimeout.h"

namespace urn_jaus_jss_mobility_ListManager_1_0
{

void QueryTimeout::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryTimeout::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryTimeout::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryTimeout::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryTimeout::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryTimeout::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryTimeout::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryTimeout::AppHeader::HeaderRec &QueryTimeout::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryTimeout::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryTimeout::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryTimeout::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2003;
}

QueryTimeout::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2003;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryTimeout::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryTimeout::AppHeader::HeaderRec* const QueryTimeout::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryTimeout::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryTimeout::AppHeader::setParentPresenceVector()
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
const unsigned int QueryTimeout::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryTimeout::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryTimeout::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryTimeout::AppHeader &QueryTimeout::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryTimeout::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryTimeout::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryTimeout::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryTimeout::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryTimeout::AppHeader::~AppHeader()
{
}

QueryTimeout::AppHeader* const QueryTimeout::getAppHeader()
{
	return &m_AppHeader;
}

int QueryTimeout::setAppHeader(const AppHeader &value)
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
const unsigned int QueryTimeout::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	
	return size;
}

void QueryTimeout::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AppHeader.encode(bytes + pos);
	pos += m_AppHeader.getSize();
}

void QueryTimeout::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AppHeader.decode(bytes + pos);
	pos += m_AppHeader.getSize();
}

QueryTimeout &QueryTimeout::operator=(const QueryTimeout &value)
{
	m_AppHeader = value.m_AppHeader;
	
	return *this;
}

bool QueryTimeout::operator==(const QueryTimeout &value) const
{
	if (m_AppHeader != value.m_AppHeader)
	{
		return false;
	}
	
	return true;
}

bool QueryTimeout::operator!=(const QueryTimeout &value) const
{
	return !((*this) == value);
}

QueryTimeout::QueryTimeout()
{
	/// No Initialization of m_AppHeader necessary.
	m_IsCommand = false;
}

QueryTimeout::QueryTimeout(const QueryTimeout &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
}

QueryTimeout::~QueryTimeout()
{
}


}
