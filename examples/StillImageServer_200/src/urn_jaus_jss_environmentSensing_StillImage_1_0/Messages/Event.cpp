#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/Event.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void Event::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void Event::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger Event::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int Event::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int Event::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void Event::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void Event::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

Event::AppHeader::HeaderRec &Event::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool Event::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool Event::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

Event::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x41f1;
}

Event::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x41f1;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

Event::AppHeader::HeaderRec::~HeaderRec()
{
}

Event::AppHeader::HeaderRec* const Event::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int Event::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void Event::AppHeader::setParentPresenceVector()
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
const unsigned int Event::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void Event::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void Event::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

Event::AppHeader &Event::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool Event::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool Event::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

Event::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

Event::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

Event::AppHeader::~AppHeader()
{
}

Event::AppHeader* const Event::getAppHeader()
{
	return &m_AppHeader;
}

int Event::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void Event::Body::EventRec::setParent(Body* parent)
{
	m_parent = parent;
}

void Event::Body::EventRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte Event::Body::EventRec::getEventID()
{
	return m_EventID;
}

int Event::Body::EventRec::setEventID(jUnsignedByte value)
{
	m_EventID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte Event::Body::EventRec::getSequenceNumber()
{
	return m_SequenceNumber;
}

int Event::Body::EventRec::setSequenceNumber(jUnsignedByte value)
{
	m_SequenceNumber = value;
	setParentPresenceVector();
	return 0;
}

void Event::Body::EventRec::ReportMessage::setParent(EventRec* parent)
{
	m_parent = parent;
}

void Event::Body::EventRec::ReportMessage::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const jUnsignedInteger Event::Body::EventRec::ReportMessage::getLength() const
{
	return m_Length;
}

const unsigned char *Event::Body::EventRec::ReportMessage::getData() const
{
	return m_Data;
}

int Event::Body::EventRec::ReportMessage::set(const jUnsignedInteger &length, const unsigned char *data)
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
const unsigned int Event::Body::EventRec::ReportMessage::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void Event::Body::EventRec::ReportMessage::encode(unsigned char *bytes)
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

void Event::Body::EventRec::ReportMessage::decode(const unsigned char *bytes)
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

Event::Body::EventRec::ReportMessage &Event::Body::EventRec::ReportMessage::operator=(const ReportMessage &value)
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

bool Event::Body::EventRec::ReportMessage::operator==(const ReportMessage &value) const
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

bool Event::Body::EventRec::ReportMessage::operator!=(const ReportMessage &value) const
{
	return !((*this) == value);
}

Event::Body::EventRec::ReportMessage::ReportMessage()
{
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
}

Event::Body::EventRec::ReportMessage::ReportMessage(const ReportMessage &value)
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

Event::Body::EventRec::ReportMessage::~ReportMessage()
{
	delete[] m_Data;
}

Event::Body::EventRec::ReportMessage* const Event::Body::EventRec::getReportMessage()
{
	return &m_ReportMessage;
}

int Event::Body::EventRec::setReportMessage(const ReportMessage &value)
{
	m_ReportMessage = value;
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
const unsigned int Event::Body::EventRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += m_ReportMessage.getSize();
	
	return size;
}

void Event::Body::EventRec::encode(unsigned char *bytes)
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
	jUnsignedByte m_SequenceNumberTemp;
	
	m_SequenceNumberTemp = JSIDL_v_1_0::correctEndianness(m_SequenceNumber);
	memcpy(bytes + pos, &m_SequenceNumberTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	m_ReportMessage.encode(bytes + pos);
	pos += m_ReportMessage.getSize();
}

void Event::Body::EventRec::decode(const unsigned char *bytes)
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
	jUnsignedByte m_SequenceNumberTemp;
	
	memcpy(&m_SequenceNumberTemp, bytes + pos, sizeof(jUnsignedByte));
	m_SequenceNumber = JSIDL_v_1_0::correctEndianness(m_SequenceNumberTemp);
	pos += sizeof(jUnsignedByte);
	m_ReportMessage.decode(bytes + pos);
	pos += m_ReportMessage.getSize();
}

Event::Body::EventRec &Event::Body::EventRec::operator=(const EventRec &value)
{
	m_EventID = value.m_EventID;
	m_SequenceNumber = value.m_SequenceNumber;
	m_ReportMessage = value.m_ReportMessage;
	
	return *this;
}

bool Event::Body::EventRec::operator==(const EventRec &value) const
{
	if (m_EventID != value.m_EventID)
	{
		return false;
	}
	if (m_SequenceNumber != value.m_SequenceNumber)
	{
		return false;
	}
	
	if (m_ReportMessage != value.m_ReportMessage)
	{
		return false;
	}
	
	return true;
}

bool Event::Body::EventRec::operator!=(const EventRec &value) const
{
	return !((*this) == value);
}

Event::Body::EventRec::EventRec()
{
	m_parent = NULL;
	m_EventID = 0;
	m_SequenceNumber = 0;
	m_ReportMessage.setParent(this);
}

Event::Body::EventRec::EventRec(const EventRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_EventID = 0;
	m_SequenceNumber = 0;
	m_ReportMessage.setParent(this);
	
	/// Copy the values
	m_EventID = value.m_EventID;
	m_SequenceNumber = value.m_SequenceNumber;
	m_ReportMessage = value.m_ReportMessage;
}

Event::Body::EventRec::~EventRec()
{
}

Event::Body::EventRec* const Event::Body::getEventRec()
{
	return &m_EventRec;
}

int Event::Body::setEventRec(const EventRec &value)
{
	m_EventRec = value;
	setParentPresenceVector();
	return 0;
}

void Event::Body::setParentPresenceVector()
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
const unsigned int Event::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_EventRec.getSize();
	
	return size;
}

void Event::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_EventRec.encode(bytes + pos);
	pos += m_EventRec.getSize();
}

void Event::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_EventRec.decode(bytes + pos);
	pos += m_EventRec.getSize();
}

Event::Body &Event::Body::operator=(const Body &value)
{
	m_EventRec = value.m_EventRec;
	m_EventRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool Event::Body::operator==(const Body &value) const
{
	if (m_EventRec != value.m_EventRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool Event::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

Event::Body::Body()
{
	m_EventRec.setParent(this);
	/// No Initialization of m_EventRec necessary.
}

Event::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_EventRec.setParent(this);
	/// No Initialization of m_EventRec necessary.
	
	/// Copy the values
	m_EventRec = value.m_EventRec;
	m_EventRec.setParent(this);
	/// This code is currently not supported
}

Event::Body::~Body()
{
}

Event::Body* const Event::getBody()
{
	return &m_Body;
}

int Event::setBody(const Body &value)
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
const unsigned int Event::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void Event::encode(unsigned char *bytes)
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

void Event::decode(const unsigned char *bytes)
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

Event &Event::operator=(const Event &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool Event::operator==(const Event &value) const
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

bool Event::operator!=(const Event &value) const
{
	return !((*this) == value);
}

Event::Event()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

Event::Event(const Event &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

Event::~Event()
{
}


}
