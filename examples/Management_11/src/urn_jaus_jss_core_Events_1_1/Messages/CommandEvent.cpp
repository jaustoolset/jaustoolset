#include "urn_jaus_jss_core_Events_1_1/Messages/CommandEvent.h"

namespace urn_jaus_jss_core_Events_1_1
{

void CommandEvent::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void CommandEvent::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger CommandEvent::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int CommandEvent::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int CommandEvent::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void CommandEvent::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void CommandEvent::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

CommandEvent::MsgHeader::HeaderRec &CommandEvent::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool CommandEvent::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool CommandEvent::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

CommandEvent::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x41f6;
}

CommandEvent::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x41f6;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

CommandEvent::MsgHeader::HeaderRec::~HeaderRec()
{
}

CommandEvent::MsgHeader::HeaderRec* const CommandEvent::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int CommandEvent::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void CommandEvent::MsgHeader::setParentPresenceVector()
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
const unsigned int CommandEvent::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void CommandEvent::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void CommandEvent::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

CommandEvent::MsgHeader &CommandEvent::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool CommandEvent::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool CommandEvent::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

CommandEvent::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

CommandEvent::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

CommandEvent::MsgHeader::~MsgHeader()
{
}

CommandEvent::MsgHeader* const CommandEvent::getMsgHeader()
{
	return &m_MsgHeader;
}

int CommandEvent::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
	return 0;
}

void CommandEvent::Body::EventRec::setParent(Body* parent)
{
	m_parent = parent;
}

void CommandEvent::Body::EventRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte CommandEvent::Body::EventRec::getEventID()
{
	return m_EventID;
}

int CommandEvent::Body::EventRec::setEventID(jUnsignedByte value)
{
	m_EventID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte CommandEvent::Body::EventRec::getCommandResult()
{
	return m_CommandResult;
}

int CommandEvent::Body::EventRec::setCommandResult(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		m_CommandResult = value;
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
const unsigned int CommandEvent::Body::EventRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void CommandEvent::Body::EventRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_EventIDTemp;
	
	m_EventIDTemp = JSIDL_v_1_0::correctEndianness(m_EventID);
	memcpy(bytes + pos, &m_EventIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_CommandResultTemp;
	
	m_CommandResultTemp = JSIDL_v_1_0::correctEndianness(m_CommandResult);
	memcpy(bytes + pos, &m_CommandResultTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void CommandEvent::Body::EventRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_EventIDTemp;
	
	memcpy(&m_EventIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_EventID = JSIDL_v_1_0::correctEndianness(m_EventIDTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_CommandResultTemp;
	
	memcpy(&m_CommandResultTemp, bytes + pos, sizeof(jUnsignedByte));
	m_CommandResult = JSIDL_v_1_0::correctEndianness(m_CommandResultTemp);
	pos += sizeof(jUnsignedByte);
}

CommandEvent::Body::EventRec &CommandEvent::Body::EventRec::operator=(const EventRec &value)
{
	m_EventID = value.m_EventID;
	m_CommandResult = value.m_CommandResult;
	
	return *this;
}

bool CommandEvent::Body::EventRec::operator==(const EventRec &value) const
{
	if (m_EventID != value.m_EventID)
	{
		return false;
	}
	if (m_CommandResult != value.m_CommandResult)
	{
		return false;
	}
	
	return true;
}

bool CommandEvent::Body::EventRec::operator!=(const EventRec &value) const
{
	return !((*this) == value);
}

CommandEvent::Body::EventRec::EventRec()
{
	m_parent = NULL;
	m_EventID = 0;
	m_CommandResult = 0;
}

CommandEvent::Body::EventRec::EventRec(const EventRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_EventID = 0;
	m_CommandResult = 0;
	
	/// Copy the values
	m_EventID = value.m_EventID;
	m_CommandResult = value.m_CommandResult;
}

CommandEvent::Body::EventRec::~EventRec()
{
}

CommandEvent::Body::EventRec* const CommandEvent::Body::getEventRec()
{
	return &m_EventRec;
}

int CommandEvent::Body::setEventRec(const EventRec &value)
{
	m_EventRec = value;
	setParentPresenceVector();
	return 0;
}

void CommandEvent::Body::setParentPresenceVector()
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
const unsigned int CommandEvent::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_EventRec.getSize();
	
	return size;
}

void CommandEvent::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_EventRec.encode(bytes + pos);
	pos += m_EventRec.getSize();
}

void CommandEvent::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_EventRec.decode(bytes + pos);
	pos += m_EventRec.getSize();
}

CommandEvent::Body &CommandEvent::Body::operator=(const Body &value)
{
	m_EventRec = value.m_EventRec;
	m_EventRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool CommandEvent::Body::operator==(const Body &value) const
{
	if (m_EventRec != value.m_EventRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool CommandEvent::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

CommandEvent::Body::Body()
{
	m_EventRec.setParent(this);
	/// No Initialization of m_EventRec necessary.
}

CommandEvent::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_EventRec.setParent(this);
	/// No Initialization of m_EventRec necessary.
	
	/// Copy the values
	m_EventRec = value.m_EventRec;
	m_EventRec.setParent(this);
	/// This code is currently not supported
}

CommandEvent::Body::~Body()
{
}

CommandEvent::Body* const CommandEvent::getBody()
{
	return &m_Body;
}

int CommandEvent::setBody(const Body &value)
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
const unsigned int CommandEvent::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void CommandEvent::encode(unsigned char *bytes)
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

void CommandEvent::decode(const unsigned char *bytes)
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

CommandEvent &CommandEvent::operator=(const CommandEvent &value)
{
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool CommandEvent::operator==(const CommandEvent &value) const
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

bool CommandEvent::operator!=(const CommandEvent &value) const
{
	return !((*this) == value);
}

CommandEvent::CommandEvent()
{
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

CommandEvent::CommandEvent(const CommandEvent &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
}

CommandEvent::~CommandEvent()
{
}


}
