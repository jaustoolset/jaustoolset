#include "urn_jaus_jss_core_Management_1_1/Messages/ConfirmEventRequest.h"

namespace urn_jaus_jss_core_Management_1_1
{

void ConfirmEventRequest::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void ConfirmEventRequest::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ConfirmEventRequest::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ConfirmEventRequest::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ConfirmEventRequest::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ConfirmEventRequest::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void ConfirmEventRequest::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

ConfirmEventRequest::MsgHeader::HeaderRec &ConfirmEventRequest::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ConfirmEventRequest::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ConfirmEventRequest::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ConfirmEventRequest::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x01f3;
}

ConfirmEventRequest::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x01f3;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ConfirmEventRequest::MsgHeader::HeaderRec::~HeaderRec()
{
}

ConfirmEventRequest::MsgHeader::HeaderRec* const ConfirmEventRequest::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ConfirmEventRequest::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmEventRequest::MsgHeader::setParentPresenceVector()
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
const unsigned int ConfirmEventRequest::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ConfirmEventRequest::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ConfirmEventRequest::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ConfirmEventRequest::MsgHeader &ConfirmEventRequest::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ConfirmEventRequest::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ConfirmEventRequest::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

ConfirmEventRequest::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ConfirmEventRequest::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ConfirmEventRequest::MsgHeader::~MsgHeader()
{
}

ConfirmEventRequest::MsgHeader* const ConfirmEventRequest::getMsgHeader()
{
	return &m_MsgHeader;
}

int ConfirmEventRequest::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
	return 0;
}

void ConfirmEventRequest::Body::ConfirmEventRequestRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ConfirmEventRequest::Body::ConfirmEventRequestRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ConfirmEventRequest::Body::ConfirmEventRequestRec::getRequestID()
{
	return m_RequestID;
}

int ConfirmEventRequest::Body::ConfirmEventRequestRec::setRequestID(jUnsignedByte value)
{
	m_RequestID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ConfirmEventRequest::Body::ConfirmEventRequestRec::getEventID()
{
	return m_EventID;
}

int ConfirmEventRequest::Body::ConfirmEventRequestRec::setEventID(jUnsignedByte value)
{
	m_EventID = value;
	setParentPresenceVector();
	return 0;
}

double ConfirmEventRequest::Body::ConfirmEventRequestRec::getConfirmedPeriodicRate()
{
	double value;
	
	double scaleFactor = ( 1092 - 0 ) / 65535.0;
	double bias = 0;
	
	value = m_ConfirmedPeriodicRate * scaleFactor + bias;
	
	return value;
}

int ConfirmEventRequest::Body::ConfirmEventRequestRec::setConfirmedPeriodicRate(double value)
{
	if ((value >= 0) && (value <= 1092))
	{
		double scaleFactor = ( 1092 - 0 ) / 65535.0;
		double bias = 0;
		
		m_ConfirmedPeriodicRate= (jUnsignedShortInteger)((value - bias) / scaleFactor);
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
const unsigned int ConfirmEventRequest::Body::ConfirmEventRequestRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ConfirmEventRequest::Body::ConfirmEventRequestRec::encode(unsigned char *bytes)
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
	jUnsignedByte m_EventIDTemp;
	
	m_EventIDTemp = JSIDL_v_1_0::correctEndianness(m_EventID);
	memcpy(bytes + pos, &m_EventIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedShortInteger m_ConfirmedPeriodicRateTemp;
	
	m_ConfirmedPeriodicRateTemp = JSIDL_v_1_0::correctEndianness(m_ConfirmedPeriodicRate);
	memcpy(bytes + pos, &m_ConfirmedPeriodicRateTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void ConfirmEventRequest::Body::ConfirmEventRequestRec::decode(const unsigned char *bytes)
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
	jUnsignedByte m_EventIDTemp;
	
	memcpy(&m_EventIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_EventID = JSIDL_v_1_0::correctEndianness(m_EventIDTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedShortInteger m_ConfirmedPeriodicRateTemp;
	
	memcpy(&m_ConfirmedPeriodicRateTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_ConfirmedPeriodicRate = JSIDL_v_1_0::correctEndianness(m_ConfirmedPeriodicRateTemp);
	pos += sizeof(jUnsignedShortInteger);
}

ConfirmEventRequest::Body::ConfirmEventRequestRec &ConfirmEventRequest::Body::ConfirmEventRequestRec::operator=(const ConfirmEventRequestRec &value)
{
	m_RequestID = value.m_RequestID;
	m_EventID = value.m_EventID;
	m_ConfirmedPeriodicRate = value.m_ConfirmedPeriodicRate;
	
	return *this;
}

bool ConfirmEventRequest::Body::ConfirmEventRequestRec::operator==(const ConfirmEventRequestRec &value) const
{
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	if (m_EventID != value.m_EventID)
	{
		return false;
	}
	if (m_ConfirmedPeriodicRate != value.m_ConfirmedPeriodicRate)
	{
		return false;
	}
	
	return true;
}

bool ConfirmEventRequest::Body::ConfirmEventRequestRec::operator!=(const ConfirmEventRequestRec &value) const
{
	return !((*this) == value);
}

ConfirmEventRequest::Body::ConfirmEventRequestRec::ConfirmEventRequestRec()
{
	m_parent = NULL;
	m_RequestID = 0;
	m_EventID = 0;
	m_ConfirmedPeriodicRate = 0;
}

ConfirmEventRequest::Body::ConfirmEventRequestRec::ConfirmEventRequestRec(const ConfirmEventRequestRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	m_EventID = 0;
	m_ConfirmedPeriodicRate = 0;
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
	m_EventID = value.m_EventID;
	m_ConfirmedPeriodicRate = value.m_ConfirmedPeriodicRate;
}

ConfirmEventRequest::Body::ConfirmEventRequestRec::~ConfirmEventRequestRec()
{
}

ConfirmEventRequest::Body::ConfirmEventRequestRec* const ConfirmEventRequest::Body::getConfirmEventRequestRec()
{
	return &m_ConfirmEventRequestRec;
}

int ConfirmEventRequest::Body::setConfirmEventRequestRec(const ConfirmEventRequestRec &value)
{
	m_ConfirmEventRequestRec = value;
	setParentPresenceVector();
	return 0;
}

void ConfirmEventRequest::Body::setParentPresenceVector()
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
const unsigned int ConfirmEventRequest::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ConfirmEventRequestRec.getSize();
	
	return size;
}

void ConfirmEventRequest::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ConfirmEventRequestRec.encode(bytes + pos);
	pos += m_ConfirmEventRequestRec.getSize();
}

void ConfirmEventRequest::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ConfirmEventRequestRec.decode(bytes + pos);
	pos += m_ConfirmEventRequestRec.getSize();
}

ConfirmEventRequest::Body &ConfirmEventRequest::Body::operator=(const Body &value)
{
	m_ConfirmEventRequestRec = value.m_ConfirmEventRequestRec;
	m_ConfirmEventRequestRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ConfirmEventRequest::Body::operator==(const Body &value) const
{
	if (m_ConfirmEventRequestRec != value.m_ConfirmEventRequestRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ConfirmEventRequest::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ConfirmEventRequest::Body::Body()
{
	m_ConfirmEventRequestRec.setParent(this);
	/// No Initialization of m_ConfirmEventRequestRec necessary.
}

ConfirmEventRequest::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ConfirmEventRequestRec.setParent(this);
	/// No Initialization of m_ConfirmEventRequestRec necessary.
	
	/// Copy the values
	m_ConfirmEventRequestRec = value.m_ConfirmEventRequestRec;
	m_ConfirmEventRequestRec.setParent(this);
	/// This code is currently not supported
}

ConfirmEventRequest::Body::~Body()
{
}

ConfirmEventRequest::Body* const ConfirmEventRequest::getBody()
{
	return &m_Body;
}

int ConfirmEventRequest::setBody(const Body &value)
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
const unsigned int ConfirmEventRequest::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ConfirmEventRequest::encode(unsigned char *bytes)
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

void ConfirmEventRequest::decode(const unsigned char *bytes)
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

ConfirmEventRequest &ConfirmEventRequest::operator=(const ConfirmEventRequest &value)
{
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ConfirmEventRequest::operator==(const ConfirmEventRequest &value) const
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

bool ConfirmEventRequest::operator!=(const ConfirmEventRequest &value) const
{
	return !((*this) == value);
}

ConfirmEventRequest::ConfirmEventRequest()
{
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ConfirmEventRequest::ConfirmEventRequest(const ConfirmEventRequest &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
}

ConfirmEventRequest::~ConfirmEventRequest()
{
}


}
