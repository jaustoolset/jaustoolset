#include "urn_jts_example_management_ocu_1_0/Messages/ConfirmControl.h"

namespace urn_jts_example_management_ocu_1_0
{

void ConfirmControl::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void ConfirmControl::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ConfirmControl::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ConfirmControl::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ConfirmControl::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ConfirmControl::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void ConfirmControl::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

ConfirmControl::MsgHeader::HeaderRec &ConfirmControl::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ConfirmControl::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ConfirmControl::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ConfirmControl::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x000f;
}

ConfirmControl::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x000f;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ConfirmControl::MsgHeader::HeaderRec::~HeaderRec()
{
}

ConfirmControl::MsgHeader::HeaderRec* const ConfirmControl::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ConfirmControl::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmControl::MsgHeader::setParentPresenceVector()
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
const unsigned int ConfirmControl::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ConfirmControl::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ConfirmControl::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ConfirmControl::MsgHeader &ConfirmControl::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ConfirmControl::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ConfirmControl::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

ConfirmControl::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ConfirmControl::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ConfirmControl::MsgHeader::~MsgHeader()
{
}

ConfirmControl::MsgHeader* const ConfirmControl::getMsgHeader()
{
	return &m_MsgHeader;
}

int ConfirmControl::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
	return 0;
}

void ConfirmControl::Body::ConfirmControlRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ConfirmControl::Body::ConfirmControlRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ConfirmControl::Body::ConfirmControlRec::getResponseCode()
{
	return m_ResponseCode;
}

int ConfirmControl::Body::ConfirmControlRec::setResponseCode(jUnsignedByte value)
{
	if ((value == 0) || (value == 1) || (value == 2))
	{
		m_ResponseCode = value;
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
const unsigned int ConfirmControl::Body::ConfirmControlRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ConfirmControl::Body::ConfirmControlRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_ResponseCodeTemp;
	
	m_ResponseCodeTemp = JSIDL_v_1_0::correctEndianness(m_ResponseCode);
	memcpy(bytes + pos, &m_ResponseCodeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ConfirmControl::Body::ConfirmControlRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_ResponseCodeTemp;
	
	memcpy(&m_ResponseCodeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_ResponseCode = JSIDL_v_1_0::correctEndianness(m_ResponseCodeTemp);
	pos += sizeof(jUnsignedByte);
}

ConfirmControl::Body::ConfirmControlRec &ConfirmControl::Body::ConfirmControlRec::operator=(const ConfirmControlRec &value)
{
	m_ResponseCode = value.m_ResponseCode;
	
	return *this;
}

bool ConfirmControl::Body::ConfirmControlRec::operator==(const ConfirmControlRec &value) const
{
	if (m_ResponseCode != value.m_ResponseCode)
	{
		return false;
	}
	
	return true;
}

bool ConfirmControl::Body::ConfirmControlRec::operator!=(const ConfirmControlRec &value) const
{
	return !((*this) == value);
}

ConfirmControl::Body::ConfirmControlRec::ConfirmControlRec()
{
	m_parent = NULL;
	m_ResponseCode = 0;
}

ConfirmControl::Body::ConfirmControlRec::ConfirmControlRec(const ConfirmControlRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_ResponseCode = 0;
	
	/// Copy the values
	m_ResponseCode = value.m_ResponseCode;
}

ConfirmControl::Body::ConfirmControlRec::~ConfirmControlRec()
{
}

ConfirmControl::Body::ConfirmControlRec* const ConfirmControl::Body::getConfirmControlRec()
{
	return &m_ConfirmControlRec;
}

int ConfirmControl::Body::setConfirmControlRec(const ConfirmControlRec &value)
{
	m_ConfirmControlRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmControl::Body::setParentPresenceVector()
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
const unsigned int ConfirmControl::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ConfirmControlRec.getSize();
	
	return size;
}

void ConfirmControl::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ConfirmControlRec.encode(bytes + pos);
	pos += m_ConfirmControlRec.getSize();
}

void ConfirmControl::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ConfirmControlRec.decode(bytes + pos);
	pos += m_ConfirmControlRec.getSize();
}

ConfirmControl::Body &ConfirmControl::Body::operator=(const Body &value)
{
	m_ConfirmControlRec = value.m_ConfirmControlRec;
	m_ConfirmControlRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ConfirmControl::Body::operator==(const Body &value) const
{
	if (m_ConfirmControlRec != value.m_ConfirmControlRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ConfirmControl::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ConfirmControl::Body::Body()
{
	m_ConfirmControlRec.setParent(this);
	/// No Initialization of m_ConfirmControlRec necessary.
}

ConfirmControl::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ConfirmControlRec.setParent(this);
	/// No Initialization of m_ConfirmControlRec necessary.
	
	/// Copy the values
	m_ConfirmControlRec = value.m_ConfirmControlRec;
	m_ConfirmControlRec.setParent(this);
	/// This code is currently not supported
}

ConfirmControl::Body::~Body()
{
}

ConfirmControl::Body* const ConfirmControl::getBody()
{
	return &m_Body;
}

int ConfirmControl::setBody(const Body &value)
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
const unsigned int ConfirmControl::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ConfirmControl::encode(unsigned char *bytes)
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

void ConfirmControl::decode(const unsigned char *bytes)
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

ConfirmControl &ConfirmControl::operator=(const ConfirmControl &value)
{
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ConfirmControl::operator==(const ConfirmControl &value) const
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

bool ConfirmControl::operator!=(const ConfirmControl &value) const
{
	return !((*this) == value);
}

ConfirmControl::ConfirmControl()
{
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ConfirmControl::ConfirmControl(const ConfirmControl &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
}

ConfirmControl::~ConfirmControl()
{
}


}
