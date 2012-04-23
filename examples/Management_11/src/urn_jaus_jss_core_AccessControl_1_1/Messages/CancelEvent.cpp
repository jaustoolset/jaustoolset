#include "urn_jaus_jss_core_AccessControl_1_1/Messages/CancelEvent.h"

namespace urn_jaus_jss_core_AccessControl_1_1
{

void CancelEvent::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void CancelEvent::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger CancelEvent::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int CancelEvent::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int CancelEvent::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void CancelEvent::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void CancelEvent::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

CancelEvent::MsgHeader::HeaderRec &CancelEvent::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool CancelEvent::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool CancelEvent::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

CancelEvent::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x01f2;
}

CancelEvent::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x01f2;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

CancelEvent::MsgHeader::HeaderRec::~HeaderRec()
{
}

CancelEvent::MsgHeader::HeaderRec* const CancelEvent::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int CancelEvent::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void CancelEvent::MsgHeader::setParentPresenceVector()
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
const unsigned int CancelEvent::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void CancelEvent::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void CancelEvent::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

CancelEvent::MsgHeader &CancelEvent::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool CancelEvent::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool CancelEvent::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

CancelEvent::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

CancelEvent::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

CancelEvent::MsgHeader::~MsgHeader()
{
}

CancelEvent::MsgHeader* const CancelEvent::getMsgHeader()
{
	return &m_MsgHeader;
}

int CancelEvent::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
	return 0;
}

void CancelEvent::Body::CancelEventRec::setParent(Body* parent)
{
	m_parent = parent;
}

void CancelEvent::Body::CancelEventRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte CancelEvent::Body::CancelEventRec::getRequestID()
{
	return m_RequestID;
}

int CancelEvent::Body::CancelEventRec::setRequestID(jUnsignedByte value)
{
	m_RequestID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte CancelEvent::Body::CancelEventRec::getEventID()
{
	return m_EventID;
}

int CancelEvent::Body::CancelEventRec::setEventID(jUnsignedByte value)
{
	m_EventID = value;
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
const unsigned int CancelEvent::Body::CancelEventRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void CancelEvent::Body::CancelEventRec::encode(unsigned char *bytes)
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
	jUnsignedByte m_EventIDTemp;
	
	m_EventIDTemp = JSIDL_v_1_0::correctEndianness(m_EventID);
	memcpy(bytes + pos, &m_EventIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void CancelEvent::Body::CancelEventRec::decode(const unsigned char *bytes)
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
	jUnsignedByte m_EventIDTemp;
	
	memcpy(&m_EventIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_EventID = JSIDL_v_1_0::correctEndianness(m_EventIDTemp);
	pos += sizeof(jUnsignedByte);
}

CancelEvent::Body::CancelEventRec &CancelEvent::Body::CancelEventRec::operator=(const CancelEventRec &value)
{
	m_RequestID = value.m_RequestID;
	m_EventID = value.m_EventID;
	
	return *this;
}

bool CancelEvent::Body::CancelEventRec::operator==(const CancelEventRec &value) const
{
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	if (m_EventID != value.m_EventID)
	{
		return false;
	}
	
	return true;
}

bool CancelEvent::Body::CancelEventRec::operator!=(const CancelEventRec &value) const
{
	return !((*this) == value);
}

CancelEvent::Body::CancelEventRec::CancelEventRec()
{
	m_parent = NULL;
	m_RequestID = 0;
	m_EventID = 0;
}

CancelEvent::Body::CancelEventRec::CancelEventRec(const CancelEventRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	m_EventID = 0;
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
	m_EventID = value.m_EventID;
}

CancelEvent::Body::CancelEventRec::~CancelEventRec()
{
}

CancelEvent::Body::CancelEventRec* const CancelEvent::Body::getCancelEventRec()
{
	return &m_CancelEventRec;
}

int CancelEvent::Body::setCancelEventRec(const CancelEventRec &value)
{
	m_CancelEventRec = value;
	setParentPresenceVector();
	return 0;
}

void CancelEvent::Body::setParentPresenceVector()
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
const unsigned int CancelEvent::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_CancelEventRec.getSize();
	
	return size;
}

void CancelEvent::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_CancelEventRec.encode(bytes + pos);
	pos += m_CancelEventRec.getSize();
}

void CancelEvent::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_CancelEventRec.decode(bytes + pos);
	pos += m_CancelEventRec.getSize();
}

CancelEvent::Body &CancelEvent::Body::operator=(const Body &value)
{
	m_CancelEventRec = value.m_CancelEventRec;
	m_CancelEventRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool CancelEvent::Body::operator==(const Body &value) const
{
	if (m_CancelEventRec != value.m_CancelEventRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool CancelEvent::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

CancelEvent::Body::Body()
{
	m_CancelEventRec.setParent(this);
	/// No Initialization of m_CancelEventRec necessary.
}

CancelEvent::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_CancelEventRec.setParent(this);
	/// No Initialization of m_CancelEventRec necessary.
	
	/// Copy the values
	m_CancelEventRec = value.m_CancelEventRec;
	m_CancelEventRec.setParent(this);
	/// This code is currently not supported
}

CancelEvent::Body::~Body()
{
}

CancelEvent::Body* const CancelEvent::getBody()
{
	return &m_Body;
}

int CancelEvent::setBody(const Body &value)
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
const unsigned int CancelEvent::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void CancelEvent::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MsgHeader.encode(bytes + pos);
	pos += m_MsgHeader.getSize();
	m_Body.encode(bytes + pos);
	pos += m_Body.getSize();
}

void CancelEvent::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MsgHeader.decode(bytes + pos);
	pos += m_MsgHeader.getSize();
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

CancelEvent &CancelEvent::operator=(const CancelEvent &value)
{
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool CancelEvent::operator==(const CancelEvent &value) const
{
	if (m_MsgHeader != value.m_MsgHeader)
	{
		return false;
	}
	if (m_Body != value.m_Body)
	{
		return false;
	}
	
	return true;
}

bool CancelEvent::operator!=(const CancelEvent &value) const
{
	return !((*this) == value);
}

CancelEvent::CancelEvent()
{
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

CancelEvent::CancelEvent(const CancelEvent &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
}

CancelEvent::~CancelEvent()
{
}


}
