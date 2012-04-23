#include "urn_jaus_jss_core_AccessControl_1_1/Messages/QueryEventTimeout.h"

namespace urn_jaus_jss_core_AccessControl_1_1
{

void QueryEventTimeout::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void QueryEventTimeout::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryEventTimeout::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryEventTimeout::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryEventTimeout::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryEventTimeout::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryEventTimeout::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryEventTimeout::MsgHeader::HeaderRec &QueryEventTimeout::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryEventTimeout::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryEventTimeout::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryEventTimeout::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x21f2;
}

QueryEventTimeout::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x21f2;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryEventTimeout::MsgHeader::HeaderRec::~HeaderRec()
{
}

QueryEventTimeout::MsgHeader::HeaderRec* const QueryEventTimeout::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryEventTimeout::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryEventTimeout::MsgHeader::setParentPresenceVector()
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
const unsigned int QueryEventTimeout::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryEventTimeout::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryEventTimeout::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryEventTimeout::MsgHeader &QueryEventTimeout::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryEventTimeout::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryEventTimeout::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

QueryEventTimeout::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryEventTimeout::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryEventTimeout::MsgHeader::~MsgHeader()
{
}

QueryEventTimeout::MsgHeader* const QueryEventTimeout::getMsgHeader()
{
	return &m_MsgHeader;
}

int QueryEventTimeout::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
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
const unsigned int QueryEventTimeout::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	
	return size;
}

void QueryEventTimeout::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MsgHeader.encode(bytes + pos);
	pos += m_MsgHeader.getSize();
}

void QueryEventTimeout::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MsgHeader.decode(bytes + pos);
	pos += m_MsgHeader.getSize();
}

QueryEventTimeout &QueryEventTimeout::operator=(const QueryEventTimeout &value)
{
	m_MsgHeader = value.m_MsgHeader;
	
	return *this;
}

bool QueryEventTimeout::operator==(const QueryEventTimeout &value) const
{
	if (m_MsgHeader != value.m_MsgHeader)
	{
		return false;
	}
	
	return true;
}

bool QueryEventTimeout::operator!=(const QueryEventTimeout &value) const
{
	return !((*this) == value);
}

QueryEventTimeout::QueryEventTimeout()
{
	/// No Initialization of m_MsgHeader necessary.
	m_IsCommand = false;
}

QueryEventTimeout::QueryEventTimeout(const QueryEventTimeout &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
}

QueryEventTimeout::~QueryEventTimeout()
{
}


}
