#include "urn_jaus_jss_core_Events_1_1/Messages/CreateCommandEvent.h"

namespace urn_jaus_jss_core_Events_1_1
{

void CreateCommandEvent::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void CreateCommandEvent::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger CreateCommandEvent::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int CreateCommandEvent::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int CreateCommandEvent::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void CreateCommandEvent::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void CreateCommandEvent::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

CreateCommandEvent::MsgHeader::HeaderRec &CreateCommandEvent::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool CreateCommandEvent::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool CreateCommandEvent::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

CreateCommandEvent::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x01f6;
}

CreateCommandEvent::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x01f6;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

CreateCommandEvent::MsgHeader::HeaderRec::~HeaderRec()
{
}

CreateCommandEvent::MsgHeader::HeaderRec* const CreateCommandEvent::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int CreateCommandEvent::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void CreateCommandEvent::MsgHeader::setParentPresenceVector()
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
const unsigned int CreateCommandEvent::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void CreateCommandEvent::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void CreateCommandEvent::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

CreateCommandEvent::MsgHeader &CreateCommandEvent::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool CreateCommandEvent::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool CreateCommandEvent::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

CreateCommandEvent::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

CreateCommandEvent::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

CreateCommandEvent::MsgHeader::~MsgHeader()
{
}

CreateCommandEvent::MsgHeader* const CreateCommandEvent::getMsgHeader()
{
	return &m_MsgHeader;
}

int CreateCommandEvent::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
	return 0;
}

void CreateCommandEvent::Body::CreateEventRec::setParent(Body* parent)
{
	m_parent = parent;
}

void CreateCommandEvent::Body::CreateEventRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte CreateCommandEvent::Body::CreateEventRec::getRequestID()
{
	return m_RequestID;
}

int CreateCommandEvent::Body::CreateEventRec::setRequestID(jUnsignedByte value)
{
	m_RequestID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedInteger CreateCommandEvent::Body::CreateEventRec::getMaximumAllowedDuration()
{
	return m_MaximumAllowedDuration;
}

int CreateCommandEvent::Body::CreateEventRec::setMaximumAllowedDuration(jUnsignedInteger value)
{
	m_MaximumAllowedDuration = value;
	setParentPresenceVector();
	return 0;
}

void CreateCommandEvent::Body::CreateEventRec::CommandMessage::setParent(CreateEventRec* parent)
{
	m_parent = parent;
}

void CreateCommandEvent::Body::CreateEventRec::CommandMessage::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const jUnsignedInteger CreateCommandEvent::Body::CreateEventRec::CommandMessage::getLength() const
{
	return m_Length;
}

const unsigned char *CreateCommandEvent::Body::CreateEventRec::CommandMessage::getData() const
{
	return m_Data;
}

int CreateCommandEvent::Body::CreateEventRec::CommandMessage::set(const jUnsignedInteger &length, const unsigned char *data)
{
	m_Length = length;
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[length];
		memcpy(m_Data, data, length);
	}
	
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
const unsigned int CreateCommandEvent::Body::CreateEventRec::CommandMessage::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void CreateCommandEvent::Body::CreateEventRec::CommandMessage::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_LengthTemp;
	
	m_LengthTemp = JSIDL_v_1_0::correctEndianness(m_Length);
	memcpy(bytes+pos, &m_LengthTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
	
	memcpy(bytes+pos, m_Data, m_Length);
	pos += m_Length;
}

void CreateCommandEvent::Body::CreateEventRec::CommandMessage::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_LengthTemp;
	
	memcpy(&m_LengthTemp, bytes+pos, sizeof(jUnsignedInteger));
	m_Length = JSIDL_v_1_0::correctEndianness(m_LengthTemp);
	pos += sizeof(jUnsignedInteger);
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[m_Length];
		memcpy(m_Data, bytes+pos, m_Length);
		pos += m_Length;
	}
}

CreateCommandEvent::Body::CreateEventRec::CommandMessage &CreateCommandEvent::Body::CreateEventRec::CommandMessage::operator=(const CommandMessage &value)
{
	this->m_Length = value.m_Length;
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[this->m_Length];
		memcpy(this->m_Data, value.m_Data, this->m_Length);
	}
	
	return *this;
}

bool CreateCommandEvent::Body::CreateEventRec::CommandMessage::operator==(const CommandMessage &value) const
{
	if (this->m_Length != value.m_Length)
	{
		return false;
	}
	
	if ((this->m_Data != NULL) && (value.m_Data!= NULL))
	{
		if (memcmp(this->m_Data, value.m_Data, this->m_Length) != 0)
		{
			return false;
		}
	}
	// This case should never be true since it should not be possible
	// for the two variables to have equal lengths but one has no data.
	// This check is placed here as a secondary check.
	else if ((this->m_Data != NULL) || (value.m_Data != NULL))
	{
		return false;
	}
	
	return true;
}

bool CreateCommandEvent::Body::CreateEventRec::CommandMessage::operator!=(const CommandMessage &value) const
{
	return !((*this) == value);
}

CreateCommandEvent::Body::CreateEventRec::CommandMessage::CommandMessage()
{
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
}

CreateCommandEvent::Body::CreateEventRec::CommandMessage::CommandMessage(const CommandMessage &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
	
	/// Copy the values
	this->m_Length = value.m_Length;
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[this->m_Length];
		memcpy(this->m_Data, value.m_Data, this->m_Length);
	}
}

CreateCommandEvent::Body::CreateEventRec::CommandMessage::~CommandMessage()
{
	delete[] m_Data;
}

CreateCommandEvent::Body::CreateEventRec::CommandMessage* const CreateCommandEvent::Body::CreateEventRec::getCommandMessage()
{
	return &m_CommandMessage;
}

int CreateCommandEvent::Body::CreateEventRec::setCommandMessage(const CommandMessage &value)
{
	m_CommandMessage = value;
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
const unsigned int CreateCommandEvent::Body::CreateEventRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedInteger);
	size += m_CommandMessage.getSize();
	
	return size;
}

void CreateCommandEvent::Body::CreateEventRec::encode(unsigned char *bytes)
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
	jUnsignedInteger m_MaximumAllowedDurationTemp;
	
	m_MaximumAllowedDurationTemp = JSIDL_v_1_0::correctEndianness(m_MaximumAllowedDuration);
	memcpy(bytes + pos, &m_MaximumAllowedDurationTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
	m_CommandMessage.encode(bytes + pos);
	pos += m_CommandMessage.getSize();
}

void CreateCommandEvent::Body::CreateEventRec::decode(const unsigned char *bytes)
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
	jUnsignedInteger m_MaximumAllowedDurationTemp;
	
	memcpy(&m_MaximumAllowedDurationTemp, bytes + pos, sizeof(jUnsignedInteger));
	m_MaximumAllowedDuration = JSIDL_v_1_0::correctEndianness(m_MaximumAllowedDurationTemp);
	pos += sizeof(jUnsignedInteger);
	m_CommandMessage.decode(bytes + pos);
	pos += m_CommandMessage.getSize();
}

CreateCommandEvent::Body::CreateEventRec &CreateCommandEvent::Body::CreateEventRec::operator=(const CreateEventRec &value)
{
	m_RequestID = value.m_RequestID;
	m_MaximumAllowedDuration = value.m_MaximumAllowedDuration;
	m_CommandMessage = value.m_CommandMessage;
	
	return *this;
}

bool CreateCommandEvent::Body::CreateEventRec::operator==(const CreateEventRec &value) const
{
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	if (m_MaximumAllowedDuration != value.m_MaximumAllowedDuration)
	{
		return false;
	}
	
	if (m_CommandMessage != value.m_CommandMessage)
	{
		return false;
	}
	
	return true;
}

bool CreateCommandEvent::Body::CreateEventRec::operator!=(const CreateEventRec &value) const
{
	return !((*this) == value);
}

CreateCommandEvent::Body::CreateEventRec::CreateEventRec()
{
	m_parent = NULL;
	m_RequestID = 0;
	m_MaximumAllowedDuration = 0;
	m_CommandMessage.setParent(this);
}

CreateCommandEvent::Body::CreateEventRec::CreateEventRec(const CreateEventRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	m_MaximumAllowedDuration = 0;
	m_CommandMessage.setParent(this);
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
	m_MaximumAllowedDuration = value.m_MaximumAllowedDuration;
	m_CommandMessage = value.m_CommandMessage;
}

CreateCommandEvent::Body::CreateEventRec::~CreateEventRec()
{
}

CreateCommandEvent::Body::CreateEventRec* const CreateCommandEvent::Body::getCreateEventRec()
{
	return &m_CreateEventRec;
}

int CreateCommandEvent::Body::setCreateEventRec(const CreateEventRec &value)
{
	m_CreateEventRec = value;
	setParentPresenceVector();
	return 0;
}

void CreateCommandEvent::Body::setParentPresenceVector()
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
const unsigned int CreateCommandEvent::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_CreateEventRec.getSize();
	
	return size;
}

void CreateCommandEvent::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_CreateEventRec.encode(bytes + pos);
	pos += m_CreateEventRec.getSize();
}

void CreateCommandEvent::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_CreateEventRec.decode(bytes + pos);
	pos += m_CreateEventRec.getSize();
}

CreateCommandEvent::Body &CreateCommandEvent::Body::operator=(const Body &value)
{
	m_CreateEventRec = value.m_CreateEventRec;
	m_CreateEventRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool CreateCommandEvent::Body::operator==(const Body &value) const
{
	if (m_CreateEventRec != value.m_CreateEventRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool CreateCommandEvent::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

CreateCommandEvent::Body::Body()
{
	m_CreateEventRec.setParent(this);
	/// No Initialization of m_CreateEventRec necessary.
}

CreateCommandEvent::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_CreateEventRec.setParent(this);
	/// No Initialization of m_CreateEventRec necessary.
	
	/// Copy the values
	m_CreateEventRec = value.m_CreateEventRec;
	m_CreateEventRec.setParent(this);
	/// This code is currently not supported
}

CreateCommandEvent::Body::~Body()
{
}

CreateCommandEvent::Body* const CreateCommandEvent::getBody()
{
	return &m_Body;
}

int CreateCommandEvent::setBody(const Body &value)
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
const unsigned int CreateCommandEvent::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void CreateCommandEvent::encode(unsigned char *bytes)
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

void CreateCommandEvent::decode(const unsigned char *bytes)
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

CreateCommandEvent &CreateCommandEvent::operator=(const CreateCommandEvent &value)
{
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool CreateCommandEvent::operator==(const CreateCommandEvent &value) const
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

bool CreateCommandEvent::operator!=(const CreateCommandEvent &value) const
{
	return !((*this) == value);
}

CreateCommandEvent::CreateCommandEvent()
{
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

CreateCommandEvent::CreateCommandEvent(const CreateCommandEvent &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
}

CreateCommandEvent::~CreateCommandEvent()
{
}


}
