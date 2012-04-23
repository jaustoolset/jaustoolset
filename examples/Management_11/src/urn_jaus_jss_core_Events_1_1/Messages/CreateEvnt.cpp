#include "urn_jaus_jss_core_Events_1_1/Messages/CreateEvnt.h"

namespace urn_jaus_jss_core_Events_1_1
{

void CreateEvnt::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void CreateEvnt::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger CreateEvnt::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int CreateEvnt::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int CreateEvnt::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void CreateEvnt::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void CreateEvnt::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

CreateEvnt::MsgHeader::HeaderRec &CreateEvnt::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool CreateEvnt::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool CreateEvnt::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

CreateEvnt::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x01f0;
}

CreateEvnt::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x01f0;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

CreateEvnt::MsgHeader::HeaderRec::~HeaderRec()
{
}

CreateEvnt::MsgHeader::HeaderRec* const CreateEvnt::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int CreateEvnt::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void CreateEvnt::MsgHeader::setParentPresenceVector()
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
const unsigned int CreateEvnt::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void CreateEvnt::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void CreateEvnt::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

CreateEvnt::MsgHeader &CreateEvnt::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool CreateEvnt::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool CreateEvnt::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

CreateEvnt::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

CreateEvnt::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

CreateEvnt::MsgHeader::~MsgHeader()
{
}

CreateEvnt::MsgHeader* const CreateEvnt::getMsgHeader()
{
	return &m_MsgHeader;
}

int CreateEvnt::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
	return 0;
}

void CreateEvnt::Body::CreateEventRec::setParent(Body* parent)
{
	m_parent = parent;
}

void CreateEvnt::Body::CreateEventRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte CreateEvnt::Body::CreateEventRec::getRequestID()
{
	return m_RequestID;
}

int CreateEvnt::Body::CreateEventRec::setRequestID(jUnsignedByte value)
{
	m_RequestID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte CreateEvnt::Body::CreateEventRec::getEventType()
{
	return m_EventType;
}

int CreateEvnt::Body::CreateEventRec::setEventType(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		m_EventType = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

double CreateEvnt::Body::CreateEventRec::getRequestedPeriodicRate()
{
	double value;
	
	double scaleFactor = ( 1092 - 0 ) / 65535.0;
	double bias = 0;
	
	value = m_RequestedPeriodicRate * scaleFactor + bias;
	
	return value;
}

int CreateEvnt::Body::CreateEventRec::setRequestedPeriodicRate(double value)
{
	if ((value >= 0) && (value <= 1092))
	{
		double scaleFactor = ( 1092 - 0 ) / 65535.0;
		double bias = 0;
		
		m_RequestedPeriodicRate= (jUnsignedShortInteger)((value - bias) / scaleFactor);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

void CreateEvnt::Body::CreateEventRec::QueryMessage::setParent(CreateEventRec* parent)
{
	m_parent = parent;
}

void CreateEvnt::Body::CreateEventRec::QueryMessage::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const jUnsignedInteger CreateEvnt::Body::CreateEventRec::QueryMessage::getLength() const
{
	return m_Length;
}

const unsigned char *CreateEvnt::Body::CreateEventRec::QueryMessage::getData() const
{
	return m_Data;
}

int CreateEvnt::Body::CreateEventRec::QueryMessage::set(const jUnsignedInteger &length, const unsigned char *data)
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
const unsigned int CreateEvnt::Body::CreateEventRec::QueryMessage::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void CreateEvnt::Body::CreateEventRec::QueryMessage::encode(unsigned char *bytes)
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

void CreateEvnt::Body::CreateEventRec::QueryMessage::decode(const unsigned char *bytes)
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

CreateEvnt::Body::CreateEventRec::QueryMessage &CreateEvnt::Body::CreateEventRec::QueryMessage::operator=(const QueryMessage &value)
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

bool CreateEvnt::Body::CreateEventRec::QueryMessage::operator==(const QueryMessage &value) const
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

bool CreateEvnt::Body::CreateEventRec::QueryMessage::operator!=(const QueryMessage &value) const
{
	return !((*this) == value);
}

CreateEvnt::Body::CreateEventRec::QueryMessage::QueryMessage()
{
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
}

CreateEvnt::Body::CreateEventRec::QueryMessage::QueryMessage(const QueryMessage &value)
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

CreateEvnt::Body::CreateEventRec::QueryMessage::~QueryMessage()
{
	delete[] m_Data;
}

CreateEvnt::Body::CreateEventRec::QueryMessage* const CreateEvnt::Body::CreateEventRec::getQueryMessage()
{
	return &m_QueryMessage;
}

int CreateEvnt::Body::CreateEventRec::setQueryMessage(const QueryMessage &value)
{
	m_QueryMessage = value;
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
const unsigned int CreateEvnt::Body::CreateEventRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	size += m_QueryMessage.getSize();
	
	return size;
}

void CreateEvnt::Body::CreateEventRec::encode(unsigned char *bytes)
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
	jUnsignedByte m_EventTypeTemp;
	
	m_EventTypeTemp = JSIDL_v_1_0::correctEndianness(m_EventType);
	memcpy(bytes + pos, &m_EventTypeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedShortInteger m_RequestedPeriodicRateTemp;
	
	m_RequestedPeriodicRateTemp = JSIDL_v_1_0::correctEndianness(m_RequestedPeriodicRate);
	memcpy(bytes + pos, &m_RequestedPeriodicRateTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	m_QueryMessage.encode(bytes + pos);
	pos += m_QueryMessage.getSize();
}

void CreateEvnt::Body::CreateEventRec::decode(const unsigned char *bytes)
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
	jUnsignedByte m_EventTypeTemp;
	
	memcpy(&m_EventTypeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_EventType = JSIDL_v_1_0::correctEndianness(m_EventTypeTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedShortInteger m_RequestedPeriodicRateTemp;
	
	memcpy(&m_RequestedPeriodicRateTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_RequestedPeriodicRate = JSIDL_v_1_0::correctEndianness(m_RequestedPeriodicRateTemp);
	pos += sizeof(jUnsignedShortInteger);
	m_QueryMessage.decode(bytes + pos);
	pos += m_QueryMessage.getSize();
}

CreateEvnt::Body::CreateEventRec &CreateEvnt::Body::CreateEventRec::operator=(const CreateEventRec &value)
{
	m_RequestID = value.m_RequestID;
	m_EventType = value.m_EventType;
	m_RequestedPeriodicRate = value.m_RequestedPeriodicRate;
	m_QueryMessage = value.m_QueryMessage;
	
	return *this;
}

bool CreateEvnt::Body::CreateEventRec::operator==(const CreateEventRec &value) const
{
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	if (m_EventType != value.m_EventType)
	{
		return false;
	}
	if (m_RequestedPeriodicRate != value.m_RequestedPeriodicRate)
	{
		return false;
	}
	
	if (m_QueryMessage != value.m_QueryMessage)
	{
		return false;
	}
	
	return true;
}

bool CreateEvnt::Body::CreateEventRec::operator!=(const CreateEventRec &value) const
{
	return !((*this) == value);
}

CreateEvnt::Body::CreateEventRec::CreateEventRec()
{
	m_parent = NULL;
	m_RequestID = 0;
	m_EventType = 0;
	m_RequestedPeriodicRate = 0;
	m_QueryMessage.setParent(this);
}

CreateEvnt::Body::CreateEventRec::CreateEventRec(const CreateEventRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	m_EventType = 0;
	m_RequestedPeriodicRate = 0;
	m_QueryMessage.setParent(this);
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
	m_EventType = value.m_EventType;
	m_RequestedPeriodicRate = value.m_RequestedPeriodicRate;
	m_QueryMessage = value.m_QueryMessage;
}

CreateEvnt::Body::CreateEventRec::~CreateEventRec()
{
}

CreateEvnt::Body::CreateEventRec* const CreateEvnt::Body::getCreateEventRec()
{
	return &m_CreateEventRec;
}

int CreateEvnt::Body::setCreateEventRec(const CreateEventRec &value)
{
	m_CreateEventRec = value;
	setParentPresenceVector();
	return 0;
}

void CreateEvnt::Body::setParentPresenceVector()
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
const unsigned int CreateEvnt::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_CreateEventRec.getSize();
	
	return size;
}

void CreateEvnt::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_CreateEventRec.encode(bytes + pos);
	pos += m_CreateEventRec.getSize();
}

void CreateEvnt::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_CreateEventRec.decode(bytes + pos);
	pos += m_CreateEventRec.getSize();
}

CreateEvnt::Body &CreateEvnt::Body::operator=(const Body &value)
{
	m_CreateEventRec = value.m_CreateEventRec;
	m_CreateEventRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool CreateEvnt::Body::operator==(const Body &value) const
{
	if (m_CreateEventRec != value.m_CreateEventRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool CreateEvnt::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

CreateEvnt::Body::Body()
{
	m_CreateEventRec.setParent(this);
	/// No Initialization of m_CreateEventRec necessary.
}

CreateEvnt::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_CreateEventRec.setParent(this);
	/// No Initialization of m_CreateEventRec necessary.
	
	/// Copy the values
	m_CreateEventRec = value.m_CreateEventRec;
	m_CreateEventRec.setParent(this);
	/// This code is currently not supported
}

CreateEvnt::Body::~Body()
{
}

CreateEvnt::Body* const CreateEvnt::getBody()
{
	return &m_Body;
}

int CreateEvnt::setBody(const Body &value)
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
const unsigned int CreateEvnt::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void CreateEvnt::encode(unsigned char *bytes)
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

void CreateEvnt::decode(const unsigned char *bytes)
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

CreateEvnt &CreateEvnt::operator=(const CreateEvnt &value)
{
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool CreateEvnt::operator==(const CreateEvnt &value) const
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

bool CreateEvnt::operator!=(const CreateEvnt &value) const
{
	return !((*this) == value);
}

CreateEvnt::CreateEvnt()
{
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

CreateEvnt::CreateEvnt(const CreateEvnt &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
}

CreateEvnt::~CreateEvnt()
{
}


}
