#include "urn_jaus_jss_core_Management_1_1/Messages/ReportEvents.h"

namespace urn_jaus_jss_core_Management_1_1
{

void ReportEvents::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void ReportEvents::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportEvents::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportEvents::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportEvents::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportEvents::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportEvents::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportEvents::MsgHeader::HeaderRec &ReportEvents::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportEvents::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportEvents::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportEvents::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x41f0;
}

ReportEvents::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x41f0;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportEvents::MsgHeader::HeaderRec::~HeaderRec()
{
}

ReportEvents::MsgHeader::HeaderRec* const ReportEvents::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportEvents::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportEvents::MsgHeader::setParentPresenceVector()
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
const unsigned int ReportEvents::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportEvents::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportEvents::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportEvents::MsgHeader &ReportEvents::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportEvents::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportEvents::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

ReportEvents::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportEvents::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportEvents::MsgHeader::~MsgHeader()
{
}

ReportEvents::MsgHeader* const ReportEvents::getMsgHeader()
{
	return &m_MsgHeader;
}

int ReportEvents::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
	return 0;
}

void ReportEvents::Body::EventList::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportEvents::Body::EventList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportEvents::Body::EventList::ReportEventRec::setParent(EventList* parent)
{
	m_parent = parent;
}

void ReportEvents::Body::EventList::ReportEventRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportEvents::Body::EventList::ReportEventRec::getEventType()
{
	return m_EventType;
}

int ReportEvents::Body::EventList::ReportEventRec::setEventType(jUnsignedByte value)
{
	if ((value == 1) || (value == 0))
	{
		m_EventType = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte ReportEvents::Body::EventList::ReportEventRec::getEventID()
{
	return m_EventID;
}

int ReportEvents::Body::EventList::ReportEventRec::setEventID(jUnsignedByte value)
{
	m_EventID = value;
	setParentPresenceVector();
	return 0;
}

void ReportEvents::Body::EventList::ReportEventRec::QueryMessage::setParent(ReportEventRec* parent)
{
	m_parent = parent;
}

void ReportEvents::Body::EventList::ReportEventRec::QueryMessage::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const jUnsignedInteger ReportEvents::Body::EventList::ReportEventRec::QueryMessage::getLength() const
{
	return m_Length;
}

const unsigned char *ReportEvents::Body::EventList::ReportEventRec::QueryMessage::getData() const
{
	return m_Data;
}

int ReportEvents::Body::EventList::ReportEventRec::QueryMessage::set(const jUnsignedInteger &length, const unsigned char *data)
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
const unsigned int ReportEvents::Body::EventList::ReportEventRec::QueryMessage::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void ReportEvents::Body::EventList::ReportEventRec::QueryMessage::encode(unsigned char *bytes)
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

void ReportEvents::Body::EventList::ReportEventRec::QueryMessage::decode(const unsigned char *bytes)
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

ReportEvents::Body::EventList::ReportEventRec::QueryMessage &ReportEvents::Body::EventList::ReportEventRec::QueryMessage::operator=(const QueryMessage &value)
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

bool ReportEvents::Body::EventList::ReportEventRec::QueryMessage::operator==(const QueryMessage &value) const
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

bool ReportEvents::Body::EventList::ReportEventRec::QueryMessage::operator!=(const QueryMessage &value) const
{
	return !((*this) == value);
}

ReportEvents::Body::EventList::ReportEventRec::QueryMessage::QueryMessage()
{
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
}

ReportEvents::Body::EventList::ReportEventRec::QueryMessage::QueryMessage(const QueryMessage &value)
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

ReportEvents::Body::EventList::ReportEventRec::QueryMessage::~QueryMessage()
{
	delete[] m_Data;
}

ReportEvents::Body::EventList::ReportEventRec::QueryMessage* const ReportEvents::Body::EventList::ReportEventRec::getQueryMessage()
{
	return &m_QueryMessage;
}

int ReportEvents::Body::EventList::ReportEventRec::setQueryMessage(const QueryMessage &value)
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
const unsigned int ReportEvents::Body::EventList::ReportEventRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += m_QueryMessage.getSize();
	
	return size;
}

void ReportEvents::Body::EventList::ReportEventRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_EventTypeTemp;
	
	m_EventTypeTemp = JSIDL_v_1_0::correctEndianness(m_EventType);
	memcpy(bytes + pos, &m_EventTypeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_EventIDTemp;
	
	m_EventIDTemp = JSIDL_v_1_0::correctEndianness(m_EventID);
	memcpy(bytes + pos, &m_EventIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	m_QueryMessage.encode(bytes + pos);
	pos += m_QueryMessage.getSize();
}

void ReportEvents::Body::EventList::ReportEventRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_EventTypeTemp;
	
	memcpy(&m_EventTypeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_EventType = JSIDL_v_1_0::correctEndianness(m_EventTypeTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_EventIDTemp;
	
	memcpy(&m_EventIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_EventID = JSIDL_v_1_0::correctEndianness(m_EventIDTemp);
	pos += sizeof(jUnsignedByte);
	m_QueryMessage.decode(bytes + pos);
	pos += m_QueryMessage.getSize();
}

ReportEvents::Body::EventList::ReportEventRec &ReportEvents::Body::EventList::ReportEventRec::operator=(const ReportEventRec &value)
{
	m_EventType = value.m_EventType;
	m_EventID = value.m_EventID;
	m_QueryMessage = value.m_QueryMessage;
	
	return *this;
}

bool ReportEvents::Body::EventList::ReportEventRec::operator==(const ReportEventRec &value) const
{
	if (m_EventType != value.m_EventType)
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

bool ReportEvents::Body::EventList::ReportEventRec::operator!=(const ReportEventRec &value) const
{
	return !((*this) == value);
}

ReportEvents::Body::EventList::ReportEventRec::ReportEventRec()
{
	m_parent = NULL;
	m_EventType = 0;
	m_EventID = 0;
	m_QueryMessage.setParent(this);
}

ReportEvents::Body::EventList::ReportEventRec::ReportEventRec(const ReportEventRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_EventType = 0;
	m_EventID = 0;
	m_QueryMessage.setParent(this);
	
	/// Copy the values
	m_EventType = value.m_EventType;
	m_EventID = value.m_EventID;
	m_QueryMessage = value.m_QueryMessage;
}

ReportEvents::Body::EventList::ReportEventRec::~ReportEventRec()
{
}

unsigned int ReportEvents::Body::EventList::getNumberOfElements() const
{
	return (unsigned int) m_ReportEventRec.size();
}

ReportEvents::Body::EventList::ReportEventRec* const ReportEvents::Body::EventList::getElement(unsigned int index)
{
	return &m_ReportEventRec.at(index);
}

int ReportEvents::Body::EventList::setElement(unsigned int index, const ReportEventRec &value)
{
	if(m_ReportEventRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ReportEventRec.at(index) = value;
	m_ReportEventRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportEvents::Body::EventList::addElement(const ReportEventRec &value)
{
	m_ReportEventRec.push_back(value);
	m_ReportEventRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportEvents::Body::EventList::deleteElement(unsigned int index)
{
	if(m_ReportEventRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ReportEventRec.erase(m_ReportEventRec.begin()+index);
	return 0;
}

int ReportEvents::Body::EventList::deleteLastElement()
{
	m_ReportEventRec.pop_back();
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
const unsigned int ReportEvents::Body::EventList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_ReportEventRec.size(); i++)
	{
		size += m_ReportEventRec[i].getSize();
	}
	
	return size;
}

void ReportEvents::Body::EventList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_ReportEventRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_ReportEventRec.size(); i++)
	{
		m_ReportEventRec[i].encode(bytes + pos);
		pos += m_ReportEventRec[i].getSize();
	}
}

void ReportEvents::Body::EventList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_ReportEventRec.resize(size);
	for (int i = 0; i < m_ReportEventRec.size(); i++)
	{
		m_ReportEventRec[i].decode(bytes + pos);
		pos += m_ReportEventRec[i].getSize();
	}
}

ReportEvents::Body::EventList &ReportEvents::Body::EventList::operator=(const EventList &value)
{
	m_ReportEventRec.clear();
	
	for (int i = 0; i < value.m_ReportEventRec.size(); i++)
	{
		m_ReportEventRec.push_back(value.m_ReportEventRec[i]);
		m_ReportEventRec[i].setParent(this);
	}
	
	return *this;
}

bool ReportEvents::Body::EventList::operator==(const EventList &value) const
{
	for (int i = 0; i < m_ReportEventRec.size(); i++)
	{
		if (m_ReportEventRec[i] != value.m_ReportEventRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportEvents::Body::EventList::operator!=(const EventList &value) const
{
	return !((*this) == value);
}

ReportEvents::Body::EventList::EventList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ReportEventRec.size(); i++)
	{
		m_ReportEventRec[i].setParent(this);
	}
	/// No Initialization of m_ReportEventRec necessary.
}

ReportEvents::Body::EventList::EventList(const EventList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ReportEventRec.size(); i++)
	{
		m_ReportEventRec[i].setParent(this);
	}
	/// No Initialization of m_ReportEventRec necessary.
	
	/// Copy the values
	m_ReportEventRec.clear();
	
	for (int i = 0; i < value.m_ReportEventRec.size(); i++)
	{
		m_ReportEventRec.push_back(value.m_ReportEventRec[i]);
		m_ReportEventRec[i].setParent(this);
	}
}

ReportEvents::Body::EventList::~EventList()
{
}

ReportEvents::Body::EventList* const ReportEvents::Body::getEventList()
{
	return &m_EventList;
}

int ReportEvents::Body::setEventList(const EventList &value)
{
	m_EventList = value;
	setParentPresenceVector();
	return 0;
}

void ReportEvents::Body::setParentPresenceVector()
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
const unsigned int ReportEvents::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_EventList.getSize();
	
	return size;
}

void ReportEvents::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_EventList.encode(bytes + pos);
	pos += m_EventList.getSize();
}

void ReportEvents::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_EventList.decode(bytes + pos);
	pos += m_EventList.getSize();
}

ReportEvents::Body &ReportEvents::Body::operator=(const Body &value)
{
	m_EventList = value.m_EventList;
	m_EventList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportEvents::Body::operator==(const Body &value) const
{
	if (m_EventList != value.m_EventList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportEvents::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportEvents::Body::Body()
{
	m_EventList.setParent(this);
	/// No Initialization of m_EventList necessary.
}

ReportEvents::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_EventList.setParent(this);
	/// No Initialization of m_EventList necessary.
	
	/// Copy the values
	m_EventList = value.m_EventList;
	m_EventList.setParent(this);
	/// This code is currently not supported
}

ReportEvents::Body::~Body()
{
}

ReportEvents::Body* const ReportEvents::getBody()
{
	return &m_Body;
}

int ReportEvents::setBody(const Body &value)
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
const unsigned int ReportEvents::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportEvents::encode(unsigned char *bytes)
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

void ReportEvents::decode(const unsigned char *bytes)
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

ReportEvents &ReportEvents::operator=(const ReportEvents &value)
{
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportEvents::operator==(const ReportEvents &value) const
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

bool ReportEvents::operator!=(const ReportEvents &value) const
{
	return !((*this) == value);
}

ReportEvents::ReportEvents()
{
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportEvents::ReportEvents(const ReportEvents &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
}

ReportEvents::~ReportEvents()
{
}


}
