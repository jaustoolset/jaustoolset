#include "urn_jaus_jss_core_AccessControl_1_1/Messages/QueryControl.h"

namespace urn_jaus_jss_core_AccessControl_1_1
{

void QueryControl::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void QueryControl::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryControl::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryControl::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryControl::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryControl::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryControl::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryControl::MsgHeader::HeaderRec &QueryControl::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryControl::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryControl::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryControl::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x200d;
}

QueryControl::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x200d;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryControl::MsgHeader::HeaderRec::~HeaderRec()
{
}

QueryControl::MsgHeader::HeaderRec* const QueryControl::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryControl::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryControl::MsgHeader::setParentPresenceVector()
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
const unsigned int QueryControl::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryControl::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryControl::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryControl::MsgHeader &QueryControl::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryControl::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryControl::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

QueryControl::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryControl::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryControl::MsgHeader::~MsgHeader()
{
}

QueryControl::MsgHeader* const QueryControl::getMsgHeader()
{
	return &m_MsgHeader;
}

int QueryControl::setMsgHeader(const MsgHeader &value)
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
const unsigned int QueryControl::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	
	return size;
}

void QueryControl::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MsgHeader.encode(bytes + pos);
	pos += m_MsgHeader.getSize();
}

void QueryControl::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MsgHeader.decode(bytes + pos);
	pos += m_MsgHeader.getSize();
}

QueryControl &QueryControl::operator=(const QueryControl &value)
{
	m_MsgHeader = value.m_MsgHeader;
	
	return *this;
}

bool QueryControl::operator==(const QueryControl &value) const
{
	if (m_MsgHeader != value.m_MsgHeader)
	{
		return false;
	}
	
	return true;
}

bool QueryControl::operator!=(const QueryControl &value) const
{
	return !((*this) == value);
}

QueryControl::QueryControl()
{
	/// No Initialization of m_MsgHeader necessary.
	m_IsCommand = false;
}

QueryControl::QueryControl(const QueryControl &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
}

QueryControl::~QueryControl()
{
}


}
