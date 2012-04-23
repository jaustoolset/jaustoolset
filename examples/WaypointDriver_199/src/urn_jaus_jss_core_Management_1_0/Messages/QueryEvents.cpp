#include "urn_jaus_jss_core_Management_1_0/Messages/QueryEvents.h"

namespace urn_jaus_jss_core_Management_1_0
{

void QueryEvents::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryEvents::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryEvents::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryEvents::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryEvents::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryEvents::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryEvents::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryEvents::AppHeader::HeaderRec &QueryEvents::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryEvents::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryEvents::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryEvents::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x21f0;
}

QueryEvents::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x21f0;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryEvents::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryEvents::AppHeader::HeaderRec* const QueryEvents::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryEvents::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryEvents::AppHeader::setParentPresenceVector()
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
const unsigned int QueryEvents::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryEvents::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryEvents::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryEvents::AppHeader &QueryEvents::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryEvents::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryEvents::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryEvents::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryEvents::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryEvents::AppHeader::~AppHeader()
{
}

QueryEvents::AppHeader* const QueryEvents::getAppHeader()
{
	return &m_AppHeader;
}

int QueryEvents::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryEvents::Body::QueryEventsVar::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryEvents::Body::QueryEventsVar::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryEvents::Body::QueryEventsVar::MessageIDRec::setParent(QueryEventsVar* parent)
{
	m_parent = parent;
}

void QueryEvents::Body::QueryEventsVar::MessageIDRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryEvents::Body::QueryEventsVar::MessageIDRec::getMessageCode()
{
	return m_MessageCode;
}

int QueryEvents::Body::QueryEventsVar::MessageIDRec::setMessageCode(jUnsignedShortInteger value)
{
	m_MessageCode = value;
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
const unsigned int QueryEvents::Body::QueryEventsVar::MessageIDRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryEvents::Body::QueryEventsVar::MessageIDRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_MessageCodeTemp;
	
	m_MessageCodeTemp = JSIDL_v_1_0::correctEndianness(m_MessageCode);
	memcpy(bytes + pos, &m_MessageCodeTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void QueryEvents::Body::QueryEventsVar::MessageIDRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_MessageCodeTemp;
	
	memcpy(&m_MessageCodeTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_MessageCode = JSIDL_v_1_0::correctEndianness(m_MessageCodeTemp);
	pos += sizeof(jUnsignedShortInteger);
}

QueryEvents::Body::QueryEventsVar::MessageIDRec &QueryEvents::Body::QueryEventsVar::MessageIDRec::operator=(const MessageIDRec &value)
{
	m_MessageCode = value.m_MessageCode;
	
	return *this;
}

bool QueryEvents::Body::QueryEventsVar::MessageIDRec::operator==(const MessageIDRec &value) const
{
	if (m_MessageCode != value.m_MessageCode)
	{
		return false;
	}
	
	return true;
}

bool QueryEvents::Body::QueryEventsVar::MessageIDRec::operator!=(const MessageIDRec &value) const
{
	return !((*this) == value);
}

QueryEvents::Body::QueryEventsVar::MessageIDRec::MessageIDRec()
{
	m_parent = NULL;
	m_MessageCode = 0;
}

QueryEvents::Body::QueryEventsVar::MessageIDRec::MessageIDRec(const MessageIDRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageCode = 0;
	
	/// Copy the values
	m_MessageCode = value.m_MessageCode;
}

QueryEvents::Body::QueryEventsVar::MessageIDRec::~MessageIDRec()
{
}

QueryEvents::Body::QueryEventsVar::MessageIDRec* const QueryEvents::Body::QueryEventsVar::getMessageIDRec()
{
	return &m_MessageIDRec;
}

int QueryEvents::Body::QueryEventsVar::setMessageIDRec(const MessageIDRec &value)
{
	m_MessageIDRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryEvents::Body::QueryEventsVar::EventTypeRec::setParent(QueryEventsVar* parent)
{
	m_parent = parent;
}

void QueryEvents::Body::QueryEventsVar::EventTypeRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte QueryEvents::Body::QueryEventsVar::EventTypeRec::getEventType()
{
	return m_EventType;
}

int QueryEvents::Body::QueryEventsVar::EventTypeRec::setEventType(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		m_EventType = value;
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
const unsigned int QueryEvents::Body::QueryEventsVar::EventTypeRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryEvents::Body::QueryEventsVar::EventTypeRec::encode(unsigned char *bytes)
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
}

void QueryEvents::Body::QueryEventsVar::EventTypeRec::decode(const unsigned char *bytes)
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
}

QueryEvents::Body::QueryEventsVar::EventTypeRec &QueryEvents::Body::QueryEventsVar::EventTypeRec::operator=(const EventTypeRec &value)
{
	m_EventType = value.m_EventType;
	
	return *this;
}

bool QueryEvents::Body::QueryEventsVar::EventTypeRec::operator==(const EventTypeRec &value) const
{
	if (m_EventType != value.m_EventType)
	{
		return false;
	}
	
	return true;
}

bool QueryEvents::Body::QueryEventsVar::EventTypeRec::operator!=(const EventTypeRec &value) const
{
	return !((*this) == value);
}

QueryEvents::Body::QueryEventsVar::EventTypeRec::EventTypeRec()
{
	m_parent = NULL;
	m_EventType = 0;
}

QueryEvents::Body::QueryEventsVar::EventTypeRec::EventTypeRec(const EventTypeRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_EventType = 0;
	
	/// Copy the values
	m_EventType = value.m_EventType;
}

QueryEvents::Body::QueryEventsVar::EventTypeRec::~EventTypeRec()
{
}

QueryEvents::Body::QueryEventsVar::EventTypeRec* const QueryEvents::Body::QueryEventsVar::getEventTypeRec()
{
	return &m_EventTypeRec;
}

int QueryEvents::Body::QueryEventsVar::setEventTypeRec(const EventTypeRec &value)
{
	m_EventTypeRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryEvents::Body::QueryEventsVar::EventIDRec::setParent(QueryEventsVar* parent)
{
	m_parent = parent;
}

void QueryEvents::Body::QueryEventsVar::EventIDRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte QueryEvents::Body::QueryEventsVar::EventIDRec::getEventID()
{
	return m_EventID;
}

int QueryEvents::Body::QueryEventsVar::EventIDRec::setEventID(jUnsignedByte value)
{
	m_EventID = value;
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
const unsigned int QueryEvents::Body::QueryEventsVar::EventIDRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryEvents::Body::QueryEventsVar::EventIDRec::encode(unsigned char *bytes)
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
}

void QueryEvents::Body::QueryEventsVar::EventIDRec::decode(const unsigned char *bytes)
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
}

QueryEvents::Body::QueryEventsVar::EventIDRec &QueryEvents::Body::QueryEventsVar::EventIDRec::operator=(const EventIDRec &value)
{
	m_EventID = value.m_EventID;
	
	return *this;
}

bool QueryEvents::Body::QueryEventsVar::EventIDRec::operator==(const EventIDRec &value) const
{
	if (m_EventID != value.m_EventID)
	{
		return false;
	}
	
	return true;
}

bool QueryEvents::Body::QueryEventsVar::EventIDRec::operator!=(const EventIDRec &value) const
{
	return !((*this) == value);
}

QueryEvents::Body::QueryEventsVar::EventIDRec::EventIDRec()
{
	m_parent = NULL;
	m_EventID = 0;
}

QueryEvents::Body::QueryEventsVar::EventIDRec::EventIDRec(const EventIDRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_EventID = 0;
	
	/// Copy the values
	m_EventID = value.m_EventID;
}

QueryEvents::Body::QueryEventsVar::EventIDRec::~EventIDRec()
{
}

QueryEvents::Body::QueryEventsVar::EventIDRec* const QueryEvents::Body::QueryEventsVar::getEventIDRec()
{
	return &m_EventIDRec;
}

int QueryEvents::Body::QueryEventsVar::setEventIDRec(const EventIDRec &value)
{
	m_EventIDRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryEvents::Body::QueryEventsVar::AllEventsRec::setParent(QueryEventsVar* parent)
{
	m_parent = parent;
}

void QueryEvents::Body::QueryEventsVar::AllEventsRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte QueryEvents::Body::QueryEventsVar::AllEventsRec::getAllEvents()
{
	return m_AllEvents;
}

int QueryEvents::Body::QueryEventsVar::AllEventsRec::setAllEvents(jUnsignedByte value)
{
	if (((value >= 0) && (value <= 0)))
	{
		m_AllEvents = value;
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
const unsigned int QueryEvents::Body::QueryEventsVar::AllEventsRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryEvents::Body::QueryEventsVar::AllEventsRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_AllEventsTemp;
	
	m_AllEventsTemp = JSIDL_v_1_0::correctEndianness(m_AllEvents);
	memcpy(bytes + pos, &m_AllEventsTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void QueryEvents::Body::QueryEventsVar::AllEventsRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_AllEventsTemp;
	
	memcpy(&m_AllEventsTemp, bytes + pos, sizeof(jUnsignedByte));
	m_AllEvents = JSIDL_v_1_0::correctEndianness(m_AllEventsTemp);
	pos += sizeof(jUnsignedByte);
}

QueryEvents::Body::QueryEventsVar::AllEventsRec &QueryEvents::Body::QueryEventsVar::AllEventsRec::operator=(const AllEventsRec &value)
{
	m_AllEvents = value.m_AllEvents;
	
	return *this;
}

bool QueryEvents::Body::QueryEventsVar::AllEventsRec::operator==(const AllEventsRec &value) const
{
	if (m_AllEvents != value.m_AllEvents)
	{
		return false;
	}
	
	return true;
}

bool QueryEvents::Body::QueryEventsVar::AllEventsRec::operator!=(const AllEventsRec &value) const
{
	return !((*this) == value);
}

QueryEvents::Body::QueryEventsVar::AllEventsRec::AllEventsRec()
{
	m_parent = NULL;
	m_AllEvents = 0;
}

QueryEvents::Body::QueryEventsVar::AllEventsRec::AllEventsRec(const AllEventsRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_AllEvents = 0;
	
	/// Copy the values
	m_AllEvents = value.m_AllEvents;
}

QueryEvents::Body::QueryEventsVar::AllEventsRec::~AllEventsRec()
{
}

QueryEvents::Body::QueryEventsVar::AllEventsRec* const QueryEvents::Body::QueryEventsVar::getAllEventsRec()
{
	return &m_AllEventsRec;
}

int QueryEvents::Body::QueryEventsVar::setAllEventsRec(const AllEventsRec &value)
{
	m_AllEventsRec = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte QueryEvents::Body::QueryEventsVar::getFieldValue() const
{
	return m_FieldValue;
}

int QueryEvents::Body::QueryEventsVar::setFieldValue(jUnsignedByte fieldValue)
{
	if(fieldValue > 3)
	{
		return 1;
	}
	
	m_FieldValue = fieldValue;
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
const unsigned int QueryEvents::Body::QueryEventsVar::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	switch(m_FieldValue)
	{
		case 0:
			size += m_MessageIDRec.getSize();
			break;
		case 1:
			size += m_EventTypeRec.getSize();
			break;
		case 2:
			size += m_EventIDRec.getSize();
			break;
		case 3:
			size += m_AllEventsRec.getSize();
			break;
	}
	
	return size;
}

void QueryEvents::Body::QueryEventsVar::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_FieldValueTemp;
	
	m_FieldValueTemp = JSIDL_v_1_0::correctEndianness(m_FieldValue);
	memcpy(bytes + pos, &m_FieldValueTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	switch(m_FieldValue)
	{
		case 0:
			m_MessageIDRec.encode(bytes + pos);
			pos += m_MessageIDRec.getSize();
			break;
		case 1:
			m_EventTypeRec.encode(bytes + pos);
			pos += m_EventTypeRec.getSize();
			break;
		case 2:
			m_EventIDRec.encode(bytes + pos);
			pos += m_EventIDRec.getSize();
			break;
		case 3:
			m_AllEventsRec.encode(bytes + pos);
			pos += m_AllEventsRec.getSize();
			break;
	}
}

void QueryEvents::Body::QueryEventsVar::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_FieldValueTemp;
	
	memcpy(&m_FieldValueTemp, bytes + pos, sizeof(jUnsignedByte));
	m_FieldValue = JSIDL_v_1_0::correctEndianness(m_FieldValueTemp);
	pos += sizeof(jUnsignedByte);
	
	switch(m_FieldValue)
	{
		case 0:
			m_MessageIDRec.decode(bytes + pos);
			pos += m_MessageIDRec.getSize();
			break;
		case 1:
			m_EventTypeRec.decode(bytes + pos);
			pos += m_EventTypeRec.getSize();
			break;
		case 2:
			m_EventIDRec.decode(bytes + pos);
			pos += m_EventIDRec.getSize();
			break;
		case 3:
			m_AllEventsRec.decode(bytes + pos);
			pos += m_AllEventsRec.getSize();
			break;
	}
}

QueryEvents::Body::QueryEventsVar &QueryEvents::Body::QueryEventsVar::operator=(const QueryEventsVar &value)
{
	m_FieldValue = value.m_FieldValue;
	m_MessageIDRec = value.m_MessageIDRec;
	m_MessageIDRec.setParent(this);
	m_EventTypeRec = value.m_EventTypeRec;
	m_EventTypeRec.setParent(this);
	m_EventIDRec = value.m_EventIDRec;
	m_EventIDRec.setParent(this);
	m_AllEventsRec = value.m_AllEventsRec;
	m_AllEventsRec.setParent(this);
	
	return *this;
}

bool QueryEvents::Body::QueryEventsVar::operator==(const QueryEventsVar &value) const
{
	if (m_FieldValue != value.m_FieldValue)
	{
		return false;
	}
	if (m_MessageIDRec != value.m_MessageIDRec)
	{
		return false;
	}
	if (m_EventTypeRec != value.m_EventTypeRec)
	{
		return false;
	}
	if (m_EventIDRec != value.m_EventIDRec)
	{
		return false;
	}
	if (m_AllEventsRec != value.m_AllEventsRec)
	{
		return false;
	}
	
	return true;
}

bool QueryEvents::Body::QueryEventsVar::operator!=(const QueryEventsVar &value) const
{
	return !((*this) == value);
}

QueryEvents::Body::QueryEventsVar::QueryEventsVar()
{
	m_parent = NULL;
	m_FieldValue = 0;
	m_MessageIDRec.setParent(this);
	/// No Initialization of m_MessageIDRec necessary.
	m_EventTypeRec.setParent(this);
	/// No Initialization of m_EventTypeRec necessary.
	m_EventIDRec.setParent(this);
	/// No Initialization of m_EventIDRec necessary.
	m_AllEventsRec.setParent(this);
	/// No Initialization of m_AllEventsRec necessary.
}

QueryEvents::Body::QueryEventsVar::QueryEventsVar(const QueryEventsVar &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_FieldValue = 0;
	m_MessageIDRec.setParent(this);
	/// No Initialization of m_MessageIDRec necessary.
	m_EventTypeRec.setParent(this);
	/// No Initialization of m_EventTypeRec necessary.
	m_EventIDRec.setParent(this);
	/// No Initialization of m_EventIDRec necessary.
	m_AllEventsRec.setParent(this);
	/// No Initialization of m_AllEventsRec necessary.
	
	/// Copy the values
	m_FieldValue = value.m_FieldValue;
	m_MessageIDRec = value.m_MessageIDRec;
	m_MessageIDRec.setParent(this);
	m_EventTypeRec = value.m_EventTypeRec;
	m_EventTypeRec.setParent(this);
	m_EventIDRec = value.m_EventIDRec;
	m_EventIDRec.setParent(this);
	m_AllEventsRec = value.m_AllEventsRec;
	m_AllEventsRec.setParent(this);
}

QueryEvents::Body::QueryEventsVar::~QueryEventsVar()
{
}

QueryEvents::Body::QueryEventsVar* const QueryEvents::Body::getQueryEventsVar()
{
	return &m_QueryEventsVar;
}

int QueryEvents::Body::setQueryEventsVar(const QueryEventsVar &value)
{
	m_QueryEventsVar = value;
	setParentPresenceVector();
	return 0;
}

void QueryEvents::Body::setParentPresenceVector()
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
const unsigned int QueryEvents::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryEventsVar.getSize();
	
	return size;
}

void QueryEvents::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryEventsVar.encode(bytes + pos);
	pos += m_QueryEventsVar.getSize();
}

void QueryEvents::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryEventsVar.decode(bytes + pos);
	pos += m_QueryEventsVar.getSize();
}

QueryEvents::Body &QueryEvents::Body::operator=(const Body &value)
{
	m_QueryEventsVar = value.m_QueryEventsVar;
	m_QueryEventsVar.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryEvents::Body::operator==(const Body &value) const
{
	if (m_QueryEventsVar != value.m_QueryEventsVar)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryEvents::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryEvents::Body::Body()
{
	m_QueryEventsVar.setParent(this);
	/// No Initialization of m_QueryEventsVar necessary.
}

QueryEvents::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryEventsVar.setParent(this);
	/// No Initialization of m_QueryEventsVar necessary.
	
	/// Copy the values
	m_QueryEventsVar = value.m_QueryEventsVar;
	m_QueryEventsVar.setParent(this);
	/// This code is currently not supported
}

QueryEvents::Body::~Body()
{
}

QueryEvents::Body* const QueryEvents::getBody()
{
	return &m_Body;
}

int QueryEvents::setBody(const Body &value)
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
const unsigned int QueryEvents::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryEvents::encode(unsigned char *bytes)
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

void QueryEvents::decode(const unsigned char *bytes)
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

QueryEvents &QueryEvents::operator=(const QueryEvents &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryEvents::operator==(const QueryEvents &value) const
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

bool QueryEvents::operator!=(const QueryEvents &value) const
{
	return !((*this) == value);
}

QueryEvents::QueryEvents()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryEvents::QueryEvents(const QueryEvents &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryEvents::~QueryEvents()
{
}


}
