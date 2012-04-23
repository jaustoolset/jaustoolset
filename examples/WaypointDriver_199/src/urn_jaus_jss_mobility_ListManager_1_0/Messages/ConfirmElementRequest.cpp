#include "urn_jaus_jss_mobility_ListManager_1_0/Messages/ConfirmElementRequest.h"

namespace urn_jaus_jss_mobility_ListManager_1_0
{

void ConfirmElementRequest::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ConfirmElementRequest::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ConfirmElementRequest::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ConfirmElementRequest::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ConfirmElementRequest::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ConfirmElementRequest::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ConfirmElementRequest::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ConfirmElementRequest::AppHeader::HeaderRec &ConfirmElementRequest::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ConfirmElementRequest::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ConfirmElementRequest::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ConfirmElementRequest::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x041c;
}

ConfirmElementRequest::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x041c;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ConfirmElementRequest::AppHeader::HeaderRec::~HeaderRec()
{
}

ConfirmElementRequest::AppHeader::HeaderRec* const ConfirmElementRequest::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ConfirmElementRequest::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmElementRequest::AppHeader::setParentPresenceVector()
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
const unsigned int ConfirmElementRequest::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ConfirmElementRequest::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ConfirmElementRequest::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ConfirmElementRequest::AppHeader &ConfirmElementRequest::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ConfirmElementRequest::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ConfirmElementRequest::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ConfirmElementRequest::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ConfirmElementRequest::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ConfirmElementRequest::AppHeader::~AppHeader()
{
}

ConfirmElementRequest::AppHeader* const ConfirmElementRequest::getAppHeader()
{
	return &m_AppHeader;
}

int ConfirmElementRequest::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ConfirmElementRequest::Body::RequestIDRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ConfirmElementRequest::Body::RequestIDRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ConfirmElementRequest::Body::RequestIDRec::getRequestID()
{
	return m_RequestID;
}

int ConfirmElementRequest::Body::RequestIDRec::setRequestID(jUnsignedByte value)
{
	m_RequestID = value;
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
const unsigned int ConfirmElementRequest::Body::RequestIDRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ConfirmElementRequest::Body::RequestIDRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_RequestIDTemp;
	
	m_RequestIDTemp = JSIDL_v_1_0::correctEndianness(m_RequestID);
	memcpy(bytes + pos, &m_RequestIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ConfirmElementRequest::Body::RequestIDRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_RequestIDTemp;
	
	memcpy(&m_RequestIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_RequestID = JSIDL_v_1_0::correctEndianness(m_RequestIDTemp);
	pos += sizeof(jUnsignedByte);
}

ConfirmElementRequest::Body::RequestIDRec &ConfirmElementRequest::Body::RequestIDRec::operator=(const RequestIDRec &value)
{
	m_RequestID = value.m_RequestID;
	
	return *this;
}

bool ConfirmElementRequest::Body::RequestIDRec::operator==(const RequestIDRec &value) const
{
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	
	return true;
}

bool ConfirmElementRequest::Body::RequestIDRec::operator!=(const RequestIDRec &value) const
{
	return !((*this) == value);
}

ConfirmElementRequest::Body::RequestIDRec::RequestIDRec()
{
	m_parent = NULL;
	m_RequestID = 0;
}

ConfirmElementRequest::Body::RequestIDRec::RequestIDRec(const RequestIDRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
}

ConfirmElementRequest::Body::RequestIDRec::~RequestIDRec()
{
}

ConfirmElementRequest::Body::RequestIDRec* const ConfirmElementRequest::Body::getRequestIDRec()
{
	return &m_RequestIDRec;
}

int ConfirmElementRequest::Body::setRequestIDRec(const RequestIDRec &value)
{
	m_RequestIDRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmElementRequest::Body::setParentPresenceVector()
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
const unsigned int ConfirmElementRequest::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_RequestIDRec.getSize();
	
	return size;
}

void ConfirmElementRequest::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIDRec.encode(bytes + pos);
	pos += m_RequestIDRec.getSize();
}

void ConfirmElementRequest::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIDRec.decode(bytes + pos);
	pos += m_RequestIDRec.getSize();
}

ConfirmElementRequest::Body &ConfirmElementRequest::Body::operator=(const Body &value)
{
	m_RequestIDRec = value.m_RequestIDRec;
	m_RequestIDRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ConfirmElementRequest::Body::operator==(const Body &value) const
{
	if (m_RequestIDRec != value.m_RequestIDRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ConfirmElementRequest::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ConfirmElementRequest::Body::Body()
{
	m_RequestIDRec.setParent(this);
	/// No Initialization of m_RequestIDRec necessary.
}

ConfirmElementRequest::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_RequestIDRec.setParent(this);
	/// No Initialization of m_RequestIDRec necessary.
	
	/// Copy the values
	m_RequestIDRec = value.m_RequestIDRec;
	m_RequestIDRec.setParent(this);
	/// This code is currently not supported
}

ConfirmElementRequest::Body::~Body()
{
}

ConfirmElementRequest::Body* const ConfirmElementRequest::getBody()
{
	return &m_Body;
}

int ConfirmElementRequest::setBody(const Body &value)
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
const unsigned int ConfirmElementRequest::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ConfirmElementRequest::encode(unsigned char *bytes)
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

void ConfirmElementRequest::decode(const unsigned char *bytes)
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

ConfirmElementRequest &ConfirmElementRequest::operator=(const ConfirmElementRequest &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ConfirmElementRequest::operator==(const ConfirmElementRequest &value) const
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

bool ConfirmElementRequest::operator!=(const ConfirmElementRequest &value) const
{
	return !((*this) == value);
}

ConfirmElementRequest::ConfirmElementRequest()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ConfirmElementRequest::ConfirmElementRequest(const ConfirmElementRequest &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ConfirmElementRequest::~ConfirmElementRequest()
{
}


}
