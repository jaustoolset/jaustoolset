#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/ReportActiveElement.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

void ReportActiveElement::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportActiveElement::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportActiveElement::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportActiveElement::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportActiveElement::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportActiveElement::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportActiveElement::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportActiveElement::AppHeader::HeaderRec &ReportActiveElement::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportActiveElement::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportActiveElement::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportActiveElement::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x441e;
}

ReportActiveElement::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x441e;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportActiveElement::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportActiveElement::AppHeader::HeaderRec* const ReportActiveElement::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportActiveElement::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportActiveElement::AppHeader::setParentPresenceVector()
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
const unsigned int ReportActiveElement::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportActiveElement::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportActiveElement::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportActiveElement::AppHeader &ReportActiveElement::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportActiveElement::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportActiveElement::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportActiveElement::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportActiveElement::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportActiveElement::AppHeader::~AppHeader()
{
}

ReportActiveElement::AppHeader* const ReportActiveElement::getAppHeader()
{
	return &m_AppHeader;
}

int ReportActiveElement::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportActiveElement::Body::ActiveElementRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportActiveElement::Body::ActiveElementRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportActiveElement::Body::ActiveElementRec::getElementUID()
{
	return m_ElementUID;
}

int ReportActiveElement::Body::ActiveElementRec::setElementUID(jUnsignedShortInteger value)
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
const unsigned int ReportActiveElement::Body::ActiveElementRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportActiveElement::Body::ActiveElementRec::encode(unsigned char *bytes)
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

void ReportActiveElement::Body::ActiveElementRec::decode(const unsigned char *bytes)
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

ReportActiveElement::Body::ActiveElementRec &ReportActiveElement::Body::ActiveElementRec::operator=(const ActiveElementRec &value)
{
	m_ElementUID = value.m_ElementUID;
	
	return *this;
}

bool ReportActiveElement::Body::ActiveElementRec::operator==(const ActiveElementRec &value) const
{
	if (m_ElementUID != value.m_ElementUID)
	{
		return false;
	}
	
	return true;
}

bool ReportActiveElement::Body::ActiveElementRec::operator!=(const ActiveElementRec &value) const
{
	return !((*this) == value);
}

ReportActiveElement::Body::ActiveElementRec::ActiveElementRec()
{
	m_parent = NULL;
	m_ElementUID = 0;
}

ReportActiveElement::Body::ActiveElementRec::ActiveElementRec(const ActiveElementRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_ElementUID = 0;
	
	/// Copy the values
	m_ElementUID = value.m_ElementUID;
}

ReportActiveElement::Body::ActiveElementRec::~ActiveElementRec()
{
}

ReportActiveElement::Body::ActiveElementRec* const ReportActiveElement::Body::getActiveElementRec()
{
	return &m_ActiveElementRec;
}

int ReportActiveElement::Body::setActiveElementRec(const ActiveElementRec &value)
{
	m_ActiveElementRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportActiveElement::Body::setParentPresenceVector()
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
const unsigned int ReportActiveElement::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ActiveElementRec.getSize();
	
	return size;
}

void ReportActiveElement::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ActiveElementRec.encode(bytes + pos);
	pos += m_ActiveElementRec.getSize();
}

void ReportActiveElement::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ActiveElementRec.decode(bytes + pos);
	pos += m_ActiveElementRec.getSize();
}

ReportActiveElement::Body &ReportActiveElement::Body::operator=(const Body &value)
{
	m_ActiveElementRec = value.m_ActiveElementRec;
	m_ActiveElementRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportActiveElement::Body::operator==(const Body &value) const
{
	if (m_ActiveElementRec != value.m_ActiveElementRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportActiveElement::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportActiveElement::Body::Body()
{
	m_ActiveElementRec.setParent(this);
	/// No Initialization of m_ActiveElementRec necessary.
}

ReportActiveElement::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ActiveElementRec.setParent(this);
	/// No Initialization of m_ActiveElementRec necessary.
	
	/// Copy the values
	m_ActiveElementRec = value.m_ActiveElementRec;
	m_ActiveElementRec.setParent(this);
	/// This code is currently not supported
}

ReportActiveElement::Body::~Body()
{
}

ReportActiveElement::Body* const ReportActiveElement::getBody()
{
	return &m_Body;
}

int ReportActiveElement::setBody(const Body &value)
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
const unsigned int ReportActiveElement::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportActiveElement::encode(unsigned char *bytes)
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

void ReportActiveElement::decode(const unsigned char *bytes)
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

ReportActiveElement &ReportActiveElement::operator=(const ReportActiveElement &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportActiveElement::operator==(const ReportActiveElement &value) const
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

bool ReportActiveElement::operator!=(const ReportActiveElement &value) const
{
	return !((*this) == value);
}

ReportActiveElement::ReportActiveElement()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportActiveElement::ReportActiveElement(const ReportActiveElement &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportActiveElement::~ReportActiveElement()
{
}


}
