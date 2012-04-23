#include "urn_jaus_jss_core_AccessControl_1_1/Messages/ReportEventTimeout.h"

namespace urn_jaus_jss_core_AccessControl_1_1
{

void ReportEventTimeout::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void ReportEventTimeout::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportEventTimeout::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportEventTimeout::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportEventTimeout::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportEventTimeout::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportEventTimeout::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportEventTimeout::MsgHeader::HeaderRec &ReportEventTimeout::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportEventTimeout::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportEventTimeout::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportEventTimeout::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x41f2;
}

ReportEventTimeout::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x41f2;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportEventTimeout::MsgHeader::HeaderRec::~HeaderRec()
{
}

ReportEventTimeout::MsgHeader::HeaderRec* const ReportEventTimeout::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportEventTimeout::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportEventTimeout::MsgHeader::setParentPresenceVector()
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
const unsigned int ReportEventTimeout::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportEventTimeout::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportEventTimeout::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportEventTimeout::MsgHeader &ReportEventTimeout::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportEventTimeout::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportEventTimeout::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

ReportEventTimeout::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportEventTimeout::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportEventTimeout::MsgHeader::~MsgHeader()
{
}

ReportEventTimeout::MsgHeader* const ReportEventTimeout::getMsgHeader()
{
	return &m_MsgHeader;
}

int ReportEventTimeout::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
	return 0;
}

void ReportEventTimeout::Body::ReportTimoutRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportEventTimeout::Body::ReportTimoutRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportEventTimeout::Body::ReportTimoutRec::getTimeout()
{
	return m_Timeout;
}

int ReportEventTimeout::Body::ReportTimoutRec::setTimeout(jUnsignedByte value)
{
	if ((value == 0))
	{
		m_Timeout = value;
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
const unsigned int ReportEventTimeout::Body::ReportTimoutRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void ReportEventTimeout::Body::ReportTimoutRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_TimeoutTemp;
	
	m_TimeoutTemp = JSIDL_v_1_0::correctEndianness(m_Timeout);
	memcpy(bytes + pos, &m_TimeoutTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void ReportEventTimeout::Body::ReportTimoutRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_TimeoutTemp;
	
	memcpy(&m_TimeoutTemp, bytes + pos, sizeof(jUnsignedByte));
	m_Timeout = JSIDL_v_1_0::correctEndianness(m_TimeoutTemp);
	pos += sizeof(jUnsignedByte);
}

ReportEventTimeout::Body::ReportTimoutRec &ReportEventTimeout::Body::ReportTimoutRec::operator=(const ReportTimoutRec &value)
{
	m_Timeout = value.m_Timeout;
	
	return *this;
}

bool ReportEventTimeout::Body::ReportTimoutRec::operator==(const ReportTimoutRec &value) const
{
	if (m_Timeout != value.m_Timeout)
	{
		return false;
	}
	
	return true;
}

bool ReportEventTimeout::Body::ReportTimoutRec::operator!=(const ReportTimoutRec &value) const
{
	return !((*this) == value);
}

ReportEventTimeout::Body::ReportTimoutRec::ReportTimoutRec()
{
	m_parent = NULL;
	m_Timeout = 0;
}

ReportEventTimeout::Body::ReportTimoutRec::ReportTimoutRec(const ReportTimoutRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_Timeout = 0;
	
	/// Copy the values
	m_Timeout = value.m_Timeout;
}

ReportEventTimeout::Body::ReportTimoutRec::~ReportTimoutRec()
{
}

ReportEventTimeout::Body::ReportTimoutRec* const ReportEventTimeout::Body::getReportTimoutRec()
{
	return &m_ReportTimoutRec;
}

int ReportEventTimeout::Body::setReportTimoutRec(const ReportTimoutRec &value)
{
	m_ReportTimoutRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportEventTimeout::Body::setParentPresenceVector()
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
const unsigned int ReportEventTimeout::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ReportTimoutRec.getSize();
	
	return size;
}

void ReportEventTimeout::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportTimoutRec.encode(bytes + pos);
	pos += m_ReportTimoutRec.getSize();
}

void ReportEventTimeout::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportTimoutRec.decode(bytes + pos);
	pos += m_ReportTimoutRec.getSize();
}

ReportEventTimeout::Body &ReportEventTimeout::Body::operator=(const Body &value)
{
	m_ReportTimoutRec = value.m_ReportTimoutRec;
	m_ReportTimoutRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportEventTimeout::Body::operator==(const Body &value) const
{
	if (m_ReportTimoutRec != value.m_ReportTimoutRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportEventTimeout::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportEventTimeout::Body::Body()
{
	m_ReportTimoutRec.setParent(this);
	/// No Initialization of m_ReportTimoutRec necessary.
}

ReportEventTimeout::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ReportTimoutRec.setParent(this);
	/// No Initialization of m_ReportTimoutRec necessary.
	
	/// Copy the values
	m_ReportTimoutRec = value.m_ReportTimoutRec;
	m_ReportTimoutRec.setParent(this);
	/// This code is currently not supported
}

ReportEventTimeout::Body::~Body()
{
}

ReportEventTimeout::Body* const ReportEventTimeout::getBody()
{
	return &m_Body;
}

int ReportEventTimeout::setBody(const Body &value)
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
const unsigned int ReportEventTimeout::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportEventTimeout::encode(unsigned char *bytes)
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

void ReportEventTimeout::decode(const unsigned char *bytes)
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

ReportEventTimeout &ReportEventTimeout::operator=(const ReportEventTimeout &value)
{
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportEventTimeout::operator==(const ReportEventTimeout &value) const
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

bool ReportEventTimeout::operator!=(const ReportEventTimeout &value) const
{
	return !((*this) == value);
}

ReportEventTimeout::ReportEventTimeout()
{
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportEventTimeout::ReportEventTimeout(const ReportEventTimeout &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
}

ReportEventTimeout::~ReportEventTimeout()
{
}


}
