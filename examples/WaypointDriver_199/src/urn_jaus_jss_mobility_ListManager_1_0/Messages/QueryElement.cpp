#include "urn_jaus_jss_mobility_ListManager_1_0/Messages/QueryElement.h"

namespace urn_jaus_jss_mobility_ListManager_1_0
{

void QueryElement::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryElement::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryElement::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryElement::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryElement::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryElement::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryElement::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryElement::AppHeader::HeaderRec &QueryElement::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryElement::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryElement::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryElement::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x241a;
}

QueryElement::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x241a;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryElement::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryElement::AppHeader::HeaderRec* const QueryElement::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryElement::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryElement::AppHeader::setParentPresenceVector()
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
const unsigned int QueryElement::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryElement::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryElement::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryElement::AppHeader &QueryElement::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryElement::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryElement::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryElement::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryElement::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryElement::AppHeader::~AppHeader()
{
}

QueryElement::AppHeader* const QueryElement::getAppHeader()
{
	return &m_AppHeader;
}

int QueryElement::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryElement::Body::QueryElementRec::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryElement::Body::QueryElementRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryElement::Body::QueryElementRec::getElementUID()
{
	return m_ElementUID;
}

int QueryElement::Body::QueryElementRec::setElementUID(jUnsignedShortInteger value)
{
	m_ElementUID = value;
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
const unsigned int QueryElement::Body::QueryElementRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryElement::Body::QueryElementRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_ElementUIDTemp;
	
	m_ElementUIDTemp = JSIDL_v_1_0::correctEndianness(m_ElementUID);
	memcpy(bytes + pos, &m_ElementUIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void QueryElement::Body::QueryElementRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_ElementUIDTemp;
	
	memcpy(&m_ElementUIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_ElementUID = JSIDL_v_1_0::correctEndianness(m_ElementUIDTemp);
	pos += sizeof(jUnsignedShortInteger);
}

QueryElement::Body::QueryElementRec &QueryElement::Body::QueryElementRec::operator=(const QueryElementRec &value)
{
	m_ElementUID = value.m_ElementUID;
	
	return *this;
}

bool QueryElement::Body::QueryElementRec::operator==(const QueryElementRec &value) const
{
	if (m_ElementUID != value.m_ElementUID)
	{
		return false;
	}
	
	return true;
}

bool QueryElement::Body::QueryElementRec::operator!=(const QueryElementRec &value) const
{
	return !((*this) == value);
}

QueryElement::Body::QueryElementRec::QueryElementRec()
{
	m_parent = NULL;
	m_ElementUID = 0;
}

QueryElement::Body::QueryElementRec::QueryElementRec(const QueryElementRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_ElementUID = 0;
	
	/// Copy the values
	m_ElementUID = value.m_ElementUID;
}

QueryElement::Body::QueryElementRec::~QueryElementRec()
{
}

QueryElement::Body::QueryElementRec* const QueryElement::Body::getQueryElementRec()
{
	return &m_QueryElementRec;
}

int QueryElement::Body::setQueryElementRec(const QueryElementRec &value)
{
	m_QueryElementRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryElement::Body::setParentPresenceVector()
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
const unsigned int QueryElement::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryElementRec.getSize();
	
	return size;
}

void QueryElement::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryElementRec.encode(bytes + pos);
	pos += m_QueryElementRec.getSize();
}

void QueryElement::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryElementRec.decode(bytes + pos);
	pos += m_QueryElementRec.getSize();
}

QueryElement::Body &QueryElement::Body::operator=(const Body &value)
{
	m_QueryElementRec = value.m_QueryElementRec;
	m_QueryElementRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryElement::Body::operator==(const Body &value) const
{
	if (m_QueryElementRec != value.m_QueryElementRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryElement::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryElement::Body::Body()
{
	m_QueryElementRec.setParent(this);
	/// No Initialization of m_QueryElementRec necessary.
}

QueryElement::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryElementRec.setParent(this);
	/// No Initialization of m_QueryElementRec necessary.
	
	/// Copy the values
	m_QueryElementRec = value.m_QueryElementRec;
	m_QueryElementRec.setParent(this);
	/// This code is currently not supported
}

QueryElement::Body::~Body()
{
}

QueryElement::Body* const QueryElement::getBody()
{
	return &m_Body;
}

int QueryElement::setBody(const Body &value)
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
const unsigned int QueryElement::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryElement::encode(unsigned char *bytes)
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

void QueryElement::decode(const unsigned char *bytes)
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

QueryElement &QueryElement::operator=(const QueryElement &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryElement::operator==(const QueryElement &value) const
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

bool QueryElement::operator!=(const QueryElement &value) const
{
	return !((*this) == value);
}

QueryElement::QueryElement()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryElement::QueryElement(const QueryElement &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryElement::~QueryElement()
{
}


}
