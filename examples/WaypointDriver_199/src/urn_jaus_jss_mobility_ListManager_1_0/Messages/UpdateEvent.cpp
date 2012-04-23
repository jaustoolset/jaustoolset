#include "urn_jaus_jss_mobility_ListManager_1_0/Messages/UpdateEvent.h"

namespace urn_jaus_jss_mobility_ListManager_1_0
{

void UpdateEvent::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void UpdateEvent::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger UpdateEvent::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int UpdateEvent::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int UpdateEvent::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void UpdateEvent::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void UpdateEvent::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

UpdateEvent::AppHeader::HeaderRec &UpdateEvent::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool UpdateEvent::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool UpdateEvent::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

UpdateEvent::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x01f1;
}

UpdateEvent::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x01f1;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

UpdateEvent::AppHeader::HeaderRec::~HeaderRec()
{
}

UpdateEvent::AppHeader::HeaderRec* const UpdateEvent::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int UpdateEvent::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void UpdateEvent::AppHeader::setParentPresenceVector()
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
const unsigned int UpdateEvent::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void UpdateEvent::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void UpdateEvent::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

UpdateEvent::AppHeader &UpdateEvent::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool UpdateEvent::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool UpdateEvent::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

UpdateEvent::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

UpdateEvent::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

UpdateEvent::AppHeader::~AppHeader()
{
}

UpdateEvent::AppHeader* const UpdateEvent::getAppHeader()
{
	return &m_AppHeader;
}

int UpdateEvent::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void UpdateEvent::Body::UpdateEventRec::setParent(Body* parent)
{
	m_parent = parent;
}

void UpdateEvent::Body::UpdateEventRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte UpdateEvent::Body::UpdateEventRec::getRequestID()
{
	return m_RequestID;
}

int UpdateEvent::Body::UpdateEventRec::setRequestID(jUnsignedByte value)
{
	m_RequestID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte UpdateEvent::Body::UpdateEventRec::getEventType()
{
	return m_EventType;
}

int UpdateEvent::Body::UpdateEventRec::setEventType(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		m_EventType = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

double UpdateEvent::Body::UpdateEventRec::getRequestedPeriodicRate()
{
	double value;
	
	double scaleFactor = ( 1092 - 0 ) / 65535.0;
	double bias = 0;
	
	value = m_RequestedPeriodicRate * scaleFactor + bias;
	
	return value;
}

int UpdateEvent::Body::UpdateEventRec::setRequestedPeriodicRate(double value)
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

jUnsignedByte UpdateEvent::Body::UpdateEventRec::getEventID()
{
	return m_EventID;
}

int UpdateEvent::Body::UpdateEventRec::setEventID(jUnsignedByte value)
{
	m_EventID = value;
	setParentPresenceVector();
	return 0;
}

void UpdateEvent::Body::UpdateEventRec::QueryMessage::setParent(UpdateEventRec* parent)
{
	m_parent = parent;
}

void UpdateEvent::Body::UpdateEventRec::QueryMessage::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const jUnsignedInteger UpdateEvent::Body::UpdateEventRec::QueryMessage::getLength() const
{
	return m_Length;
}

const unsigned char *UpdateEvent::Body::UpdateEventRec::QueryMessage::getData() const
{
	return m_Data;
}

int UpdateEvent::Body::UpdateEventRec::QueryMessage::set(const jUnsignedInteger &length, const unsigned char *data)
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
const unsigned int UpdateEvent::Body::UpdateEventRec::QueryMessage::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void UpdateEvent::Body::UpdateEventRec::QueryMessage::encode(unsigned char *bytes)
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

void UpdateEvent::Body::UpdateEventRec::QueryMessage::decode(const unsigned char *bytes)
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

UpdateEvent::Body::UpdateEventRec::QueryMessage &UpdateEvent::Body::UpdateEventRec::QueryMessage::operator=(const QueryMessage &value)
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

bool UpdateEvent::Body::UpdateEventRec::QueryMessage::operator==(const QueryMessage &value) const
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

bool UpdateEvent::Body::UpdateEventRec::QueryMessage::operator!=(const QueryMessage &value) const
{
	return !((*this) == value);
}

UpdateEvent::Body::UpdateEventRec::QueryMessage::QueryMessage()
{
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
}

UpdateEvent::Body::UpdateEventRec::QueryMessage::QueryMessage(const QueryMessage &value)
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

UpdateEvent::Body::UpdateEventRec::QueryMessage::~QueryMessage()
{
	delete[] m_Data;
}

UpdateEvent::Body::UpdateEventRec::QueryMessage* const UpdateEvent::Body::UpdateEventRec::getQueryMessage()
{
	return &m_QueryMessage;
}

int UpdateEvent::Body::UpdateEventRec::setQueryMessage(const QueryMessage &value)
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
const unsigned int UpdateEvent::Body::UpdateEventRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += m_QueryMessage.getSize();
	
	return size;
}

void UpdateEvent::Body::UpdateEventRec::encode(unsigned char *bytes)
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
	jUnsignedByte m_EventIDTemp;
	
	m_EventIDTemp = JSIDL_v_1_0::correctEndianness(m_EventID);
	memcpy(bytes + pos, &m_EventIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	m_QueryMessage.encode(bytes + pos);
	pos += m_QueryMessage.getSize();
}

void UpdateEvent::Body::UpdateEventRec::decode(const unsigned char *bytes)
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
	jUnsignedByte m_EventIDTemp;
	
	memcpy(&m_EventIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_EventID = JSIDL_v_1_0::correctEndianness(m_EventIDTemp);
	pos += sizeof(jUnsignedByte);
	m_QueryMessage.decode(bytes + pos);
	pos += m_QueryMessage.getSize();
}

UpdateEvent::Body::UpdateEventRec &UpdateEvent::Body::UpdateEventRec::operator=(const UpdateEventRec &value)
{
	m_RequestID = value.m_RequestID;
	m_EventType = value.m_EventType;
	m_RequestedPeriodicRate = value.m_RequestedPeriodicRate;
	m_EventID = value.m_EventID;
	m_QueryMessage = value.m_QueryMessage;
	
	return *this;
}

bool UpdateEvent::Body::UpdateEventRec::operator==(const UpdateEventRec &value) const
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
	if (m_EventID != value.m_EventID)
	{
		return false;
	}
	
	if (m_QueryMessage != value.m_QueryMessage)
	{
		return false;
	}
	
	return true;
}

bool UpdateEvent::Body::UpdateEventRec::operator!=(const UpdateEventRec &value) const
{
	return !((*this) == value);
}

UpdateEvent::Body::UpdateEventRec::UpdateEventRec()
{
	m_parent = NULL;
	m_RequestID = 0;
	m_EventType = 0;
	m_RequestedPeriodicRate = 0;
	m_EventID = 0;
	m_QueryMessage.setParent(this);
}

UpdateEvent::Body::UpdateEventRec::UpdateEventRec(const UpdateEventRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	m_EventType = 0;
	m_RequestedPeriodicRate = 0;
	m_EventID = 0;
	m_QueryMessage.setParent(this);
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
	m_EventType = value.m_EventType;
	m_RequestedPeriodicRate = value.m_RequestedPeriodicRate;
	m_EventID = value.m_EventID;
	m_QueryMessage = value.m_QueryMessage;
}

UpdateEvent::Body::UpdateEventRec::~UpdateEventRec()
{
}

UpdateEvent::Body::UpdateEventRec* const UpdateEvent::Body::getUpdateEventRec()
{
	return &m_UpdateEventRec;
}

int UpdateEvent::Body::setUpdateEventRec(const UpdateEventRec &value)
{
	m_UpdateEventRec = value;
	setParentPresenceVector();
	return 0;
}

void UpdateEvent::Body::setParentPresenceVector()
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
const unsigned int UpdateEvent::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_UpdateEventRec.getSize();
	
	return size;
}

void UpdateEvent::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_UpdateEventRec.encode(bytes + pos);
	pos += m_UpdateEventRec.getSize();
}

void UpdateEvent::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_UpdateEventRec.decode(bytes + pos);
	pos += m_UpdateEventRec.getSize();
}

UpdateEvent::Body &UpdateEvent::Body::operator=(const Body &value)
{
	m_UpdateEventRec = value.m_UpdateEventRec;
	m_UpdateEventRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool UpdateEvent::Body::operator==(const Body &value) const
{
	if (m_UpdateEventRec != value.m_UpdateEventRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool UpdateEvent::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

UpdateEvent::Body::Body()
{
	m_UpdateEventRec.setParent(this);
	/// No Initialization of m_UpdateEventRec necessary.
}

UpdateEvent::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_UpdateEventRec.setParent(this);
	/// No Initialization of m_UpdateEventRec necessary.
	
	/// Copy the values
	m_UpdateEventRec = value.m_UpdateEventRec;
	m_UpdateEventRec.setParent(this);
	/// This code is currently not supported
}

UpdateEvent::Body::~Body()
{
}

UpdateEvent::Body* const UpdateEvent::getBody()
{
	return &m_Body;
}

int UpdateEvent::setBody(const Body &value)
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
const unsigned int UpdateEvent::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void UpdateEvent::encode(unsigned char *bytes)
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

void UpdateEvent::decode(const unsigned char *bytes)
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

UpdateEvent &UpdateEvent::operator=(const UpdateEvent &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool UpdateEvent::operator==(const UpdateEvent &value) const
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

bool UpdateEvent::operator!=(const UpdateEvent &value) const
{
	return !((*this) == value);
}

UpdateEvent::UpdateEvent()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

UpdateEvent::UpdateEvent(const UpdateEvent &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

UpdateEvent::~UpdateEvent()
{
}


}
