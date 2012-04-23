#include "urn_jts_example_management_ocu_1_0/Messages/QueryStatus.h"

namespace urn_jts_example_management_ocu_1_0
{

void QueryStatus::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void QueryStatus::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryStatus::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryStatus::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryStatus::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryStatus::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryStatus::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryStatus::MsgHeader::HeaderRec &QueryStatus::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryStatus::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryStatus::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryStatus::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2002;
}

QueryStatus::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2002;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryStatus::MsgHeader::HeaderRec::~HeaderRec()
{
}

QueryStatus::MsgHeader::HeaderRec* const QueryStatus::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryStatus::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryStatus::MsgHeader::setParentPresenceVector()
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
const unsigned int QueryStatus::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryStatus::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryStatus::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryStatus::MsgHeader &QueryStatus::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryStatus::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryStatus::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

QueryStatus::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryStatus::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryStatus::MsgHeader::~MsgHeader()
{
}

QueryStatus::MsgHeader* const QueryStatus::getMsgHeader()
{
	return &m_MsgHeader;
}

int QueryStatus::setMsgHeader(const MsgHeader &value)
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
const unsigned int QueryStatus::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	
	return size;
}

void QueryStatus::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MsgHeader.encode(bytes + pos);
	pos += m_MsgHeader.getSize();
}

void QueryStatus::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MsgHeader.decode(bytes + pos);
	pos += m_MsgHeader.getSize();
}

QueryStatus &QueryStatus::operator=(const QueryStatus &value)
{
	m_MsgHeader = value.m_MsgHeader;
	
	return *this;
}

bool QueryStatus::operator==(const QueryStatus &value) const
{
	if (m_MsgHeader != value.m_MsgHeader)
	{
		return false;
	}
	
	return true;
}

bool QueryStatus::operator!=(const QueryStatus &value) const
{
	return !((*this) == value);
}

QueryStatus::QueryStatus()
{
	/// No Initialization of m_MsgHeader necessary.
	m_IsCommand = false;
}

QueryStatus::QueryStatus(const QueryStatus &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
}

QueryStatus::~QueryStatus()
{
}


}
