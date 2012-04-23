#include "urn_jaus_jss_core_Events_1_0/Messages/CreateEvent.h"

namespace urn_jaus_jss_core_Events_1_0
{

void CreateEvent::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void CreateEvent::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger CreateEvent::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int CreateEvent::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int CreateEvent::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void CreateEvent::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void CreateEvent::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

CreateEvent::AppHeader::HeaderRec &CreateEvent::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool CreateEvent::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool CreateEvent::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

CreateEvent::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x01f0;
}

CreateEvent::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x01f0;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

CreateEvent::AppHeader::HeaderRec::~HeaderRec()
{
}

CreateEvent::AppHeader::HeaderRec* const CreateEvent::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int CreateEvent::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void CreateEvent::AppHeader::setParentPresenceVector()
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
const unsigned int CreateEvent::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void CreateEvent::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void CreateEvent::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

CreateEvent::AppHeader &CreateEvent::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool CreateEvent::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool CreateEvent::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

CreateEvent::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

CreateEvent::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

CreateEvent::AppHeader::~AppHeader()
{
}

CreateEvent::AppHeader* const CreateEvent::getAppHeader()
{
	return &m_AppHeader;
}

int CreateEvent::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void CreateEvent::Body::CreateEventRec::setParent(Body* parent)
{
	m_parent = parent;
}

void CreateEvent::Body::CreateEventRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte CreateEvent::Body::CreateEventRec::getRequestID()
{
	return m_RequestID;
}

int CreateEvent::Body::CreateEventRec::setRequestID(jUnsignedByte value)
{
	m_RequestID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte CreateEvent::Body::CreateEventRec::getEventType()
{
	return m_EventType;
}

int CreateEvent::Body::CreateEventRec::setEventType(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		m_EventType = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

double CreateEvent::Body::CreateEventRec::getRequestedPeriodicRate()
{
	double value;
	
	double scaleFactor = ( 1092 - 0 ) / 65535.0;
	double bias = 0;
	
	value = m_RequestedPeriodicRate * scaleFactor + bias;
	
	return value;
}

int CreateEvent::Body::CreateEventRec::setRequestedPeriodicRate(double value)
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

void CreateEvent::Body::CreateEventRec::QueryMessage::setParent(CreateEventRec* parent)
{
	m_parent = parent;
}

void CreateEvent::Body::CreateEventRec::QueryMessage::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const jUnsignedInteger CreateEvent::Body::CreateEventRec::QueryMessage::getLength() const
{
	return m_Length;
}

const unsigned char *CreateEvent::Body::CreateEventRec::QueryMessage::getData() const
{
	return m_Data;
}

int CreateEvent::Body::CreateEventRec::QueryMessage::set(const jUnsignedInteger &length, const unsigned char *data)
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
const unsigned int CreateEvent::Body::CreateEventRec::QueryMessage::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void CreateEvent::Body::CreateEventRec::QueryMessage::encode(unsigned char *bytes)
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

void CreateEvent::Body::CreateEventRec::QueryMessage::decode(const unsigned char *bytes)
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

CreateEvent::Body::CreateEventRec::QueryMessage &CreateEvent::Body::CreateEventRec::QueryMessage::operator=(const QueryMessage &value)
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

bool CreateEvent::Body::CreateEventRec::QueryMessage::operator==(const QueryMessage &value) const
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

bool CreateEvent::Body::CreateEventRec::QueryMessage::operator!=(const QueryMessage &value) const
{
	return !((*this) == value);
}

CreateEvent::Body::CreateEventRec::QueryMessage::QueryMessage()
{
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
}

CreateEvent::Body::CreateEventRec::QueryMessage::QueryMessage(const QueryMessage &value)
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

CreateEvent::Body::CreateEventRec::QueryMessage::~QueryMessage()
{
	delete[] m_Data;
}

CreateEvent::Body::CreateEventRec::QueryMessage* const CreateEvent::Body::CreateEventRec::getQueryMessage()
{
	return &m_QueryMessage;
}

int CreateEvent::Body::CreateEventRec::setQueryMessage(const QueryMessage &value)
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
const unsigned int CreateEvent::Body::CreateEventRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	size += m_QueryMessage.getSize();
	
	return size;
}

void CreateEvent::Body::CreateEventRec::encode(unsigned char *bytes)
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

void CreateEvent::Body::CreateEventRec::decode(const unsigned char *bytes)
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

CreateEvent::Body::CreateEventRec &CreateEvent::Body::CreateEventRec::operator=(const CreateEventRec &value)
{
	m_RequestID = value.m_RequestID;
	m_EventType = value.m_EventType;
	m_RequestedPeriodicRate = value.m_RequestedPeriodicRate;
	m_QueryMessage = value.m_QueryMessage;
	
	return *this;
}

bool CreateEvent::Body::CreateEventRec::operator==(const CreateEventRec &value) const
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

bool CreateEvent::Body::CreateEventRec::operator!=(const CreateEventRec &value) const
{
	return !((*this) == value);
}

CreateEvent::Body::CreateEventRec::CreateEventRec()
{
	m_parent = NULL;
	m_RequestID = 0;
	m_EventType = 0;
	m_RequestedPeriodicRate = 0;
	m_QueryMessage.setParent(this);
}

CreateEvent::Body::CreateEventRec::CreateEventRec(const CreateEventRec &value)
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

CreateEvent::Body::CreateEventRec::~CreateEventRec()
{
}

CreateEvent::Body::CreateEventRec* const CreateEvent::Body::getCreateEventRec()
{
	return &m_CreateEventRec;
}

int CreateEvent::Body::setCreateEventRec(const CreateEventRec &value)
{
	m_CreateEventRec = value;
	setParentPresenceVector();
	return 0;
}

void CreateEvent::Body::setParentPresenceVector()
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
const unsigned int CreateEvent::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_CreateEventRec.getSize();
	
	return size;
}

void CreateEvent::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_CreateEventRec.encode(bytes + pos);
	pos += m_CreateEventRec.getSize();
}

void CreateEvent::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_CreateEventRec.decode(bytes + pos);
	pos += m_CreateEventRec.getSize();
}

CreateEvent::Body &CreateEvent::Body::operator=(const Body &value)
{
	m_CreateEventRec = value.m_CreateEventRec;
	m_CreateEventRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool CreateEvent::Body::operator==(const Body &value) const
{
	if (m_CreateEventRec != value.m_CreateEventRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool CreateEvent::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

CreateEvent::Body::Body()
{
	m_CreateEventRec.setParent(this);
	/// No Initialization of m_CreateEventRec necessary.
}

CreateEvent::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_CreateEventRec.setParent(this);
	/// No Initialization of m_CreateEventRec necessary.
	
	/// Copy the values
	m_CreateEventRec = value.m_CreateEventRec;
	m_CreateEventRec.setParent(this);
	/// This code is currently not supported
}

CreateEvent::Body::~Body()
{
}

CreateEvent::Body* const CreateEvent::getBody()
{
	return &m_Body;
}

int CreateEvent::setBody(const Body &value)
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
const unsigned int CreateEvent::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void CreateEvent::encode(unsigned char *bytes)
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

void CreateEvent::decode(const unsigned char *bytes)
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

CreateEvent &CreateEvent::operator=(const CreateEvent &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool CreateEvent::operator==(const CreateEvent &value) const
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

bool CreateEvent::operator!=(const CreateEvent &value) const
{
	return !((*this) == value);
}

CreateEvent::CreateEvent()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

CreateEvent::CreateEvent(const CreateEvent &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

CreateEvent::~CreateEvent()
{
}


}
