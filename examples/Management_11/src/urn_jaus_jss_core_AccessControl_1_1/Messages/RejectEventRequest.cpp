#include "urn_jaus_jss_core_AccessControl_1_1/Messages/RejectEventRequest.h"

namespace urn_jaus_jss_core_AccessControl_1_1
{

void RejectEventRequest::MsgHeader::HeaderRec::setParent(MsgHeader* parent)
{
	m_parent = parent;
}

void RejectEventRequest::MsgHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger RejectEventRequest::MsgHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int RejectEventRequest::MsgHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int RejectEventRequest::MsgHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void RejectEventRequest::MsgHeader::HeaderRec::encode(unsigned char *bytes)
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

void RejectEventRequest::MsgHeader::HeaderRec::decode(const unsigned char *bytes)
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

RejectEventRequest::MsgHeader::HeaderRec &RejectEventRequest::MsgHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool RejectEventRequest::MsgHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool RejectEventRequest::MsgHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

RejectEventRequest::MsgHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x01f4;
}

RejectEventRequest::MsgHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x01f4;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

RejectEventRequest::MsgHeader::HeaderRec::~HeaderRec()
{
}

RejectEventRequest::MsgHeader::HeaderRec* const RejectEventRequest::MsgHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int RejectEventRequest::MsgHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void RejectEventRequest::MsgHeader::setParentPresenceVector()
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
const unsigned int RejectEventRequest::MsgHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void RejectEventRequest::MsgHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void RejectEventRequest::MsgHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

RejectEventRequest::MsgHeader &RejectEventRequest::MsgHeader::operator=(const MsgHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool RejectEventRequest::MsgHeader::operator==(const MsgHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool RejectEventRequest::MsgHeader::operator!=(const MsgHeader &value) const
{
	return !((*this) == value);
}

RejectEventRequest::MsgHeader::MsgHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

RejectEventRequest::MsgHeader::MsgHeader(const MsgHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

RejectEventRequest::MsgHeader::~MsgHeader()
{
}

RejectEventRequest::MsgHeader* const RejectEventRequest::getMsgHeader()
{
	return &m_MsgHeader;
}

int RejectEventRequest::setMsgHeader(const MsgHeader &value)
{
	m_MsgHeader = value;
	return 0;
}

void RejectEventRequest::Body::RejectEventRequestRec::setParent(Body* parent)
{
	m_parent = parent;
}

void RejectEventRequest::Body::RejectEventRequestRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte RejectEventRequest::Body::RejectEventRequestRec::getPresenceVector()
{
	return m_PresenceVector;
}

int RejectEventRequest::Body::RejectEventRequestRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
	return 0;
}

bool RejectEventRequest::Body::RejectEventRequestRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

jUnsignedByte RejectEventRequest::Body::RejectEventRequestRec::getRequestID()
{
	return m_RequestID;
}

int RejectEventRequest::Body::RejectEventRequestRec::setRequestID(jUnsignedByte value)
{
	m_RequestID = value;
	setParentPresenceVector();
	return 0;
}

bool RejectEventRequest::Body::RejectEventRequestRec::isResponseCodeValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

jUnsignedByte RejectEventRequest::Body::RejectEventRequestRec::getResponseCode()
{
	return m_ResponseCode;
}

int RejectEventRequest::Body::RejectEventRequestRec::setResponseCode(jUnsignedByte value)
{
	if ((value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6))
	{
		m_ResponseCode = value;
		setPresenceVector(0);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

bool RejectEventRequest::Body::RejectEventRequestRec::isErrorMessageValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

jFixedLengthString RejectEventRequest::Body::RejectEventRequestRec::getErrorMessage()
{
	return m_ErrorMessage;
}

int RejectEventRequest::Body::RejectEventRequestRec::setErrorMessage(std::string value)
{
	m_ErrorMessage = value;
	setPresenceVector(1);
	setParentPresenceVector();
	return 0;
}

int RejectEventRequest::Body::RejectEventRequestRec::setErrorMessage(jFixedLengthString value)
{
	m_ErrorMessage = value;
	setPresenceVector(1);
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
const unsigned int RejectEventRequest::Body::RejectEventRequestRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		size += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(1))
	{
		size += 80;
	}
	
	return size;
}

void RejectEventRequest::Body::RejectEventRequestRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_PresenceVectorTemp;
	
	m_PresenceVectorTemp = JSIDL_v_1_0::correctEndianness(m_PresenceVector);
	memcpy(bytes + pos, &m_PresenceVectorTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_RequestIDTemp;
	
	m_RequestIDTemp = JSIDL_v_1_0::correctEndianness(m_RequestID);
	memcpy(bytes + pos, &m_RequestIDTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		jUnsignedByte m_ResponseCodeTemp;
		
		m_ResponseCodeTemp = JSIDL_v_1_0::correctEndianness(m_ResponseCode);
		memcpy(bytes + pos, &m_ResponseCodeTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(1))
	{
		jFixedLengthString m_ErrorMessageTemp(80);
		
		m_ErrorMessageTemp = JSIDL_v_1_0::correctEndianness(m_ErrorMessage.c_str());
		memcpy(bytes+pos, m_ErrorMessageTemp.c_str(), 80);
		pos += 80;
	}
}

void RejectEventRequest::Body::RejectEventRequestRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_PresenceVectorTemp;
	
	memcpy(&m_PresenceVectorTemp, bytes + pos, sizeof(jUnsignedByte));
	m_PresenceVector = JSIDL_v_1_0::correctEndianness(m_PresenceVectorTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_RequestIDTemp;
	
	memcpy(&m_RequestIDTemp, bytes + pos, sizeof(jUnsignedByte));
	m_RequestID = JSIDL_v_1_0::correctEndianness(m_RequestIDTemp);
	pos += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		jUnsignedByte m_ResponseCodeTemp;
		
		memcpy(&m_ResponseCodeTemp, bytes + pos, sizeof(jUnsignedByte));
		m_ResponseCode = JSIDL_v_1_0::correctEndianness(m_ResponseCodeTemp);
		pos += sizeof(jUnsignedByte);
	}
	if (checkPresenceVector(1))
	{
		char m_ErrorMessageTemp[80];
		
		memcpy(m_ErrorMessageTemp, bytes+pos, 80);
		m_ErrorMessage = JSIDL_v_1_0::correctEndianness(m_ErrorMessageTemp);
		pos += 80;
	}
}

RejectEventRequest::Body::RejectEventRequestRec &RejectEventRequest::Body::RejectEventRequestRec::operator=(const RejectEventRequestRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_RequestID = value.m_RequestID;
	m_ResponseCode = value.m_ResponseCode;
	m_ErrorMessage = value.m_ErrorMessage;
	
	return *this;
}

bool RejectEventRequest::Body::RejectEventRequestRec::operator==(const RejectEventRequestRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	if (m_ResponseCode != value.m_ResponseCode)
	{
		return false;
	}
	if (m_ErrorMessage != value.m_ErrorMessage)
	{
		return false;
	}
	
	return true;
}

bool RejectEventRequest::Body::RejectEventRequestRec::operator!=(const RejectEventRequestRec &value) const
{
	return !((*this) == value);
}

RejectEventRequest::Body::RejectEventRequestRec::RejectEventRequestRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_RequestID = 0;
	m_ResponseCode = 0;
	m_ErrorMessage.setSize(80);
}

RejectEventRequest::Body::RejectEventRequestRec::RejectEventRequestRec(const RejectEventRequestRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	m_RequestID = 0;
	m_ResponseCode = 0;
	m_ErrorMessage.setSize(80);
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_RequestID = value.m_RequestID;
	m_ResponseCode = value.m_ResponseCode;
	m_ErrorMessage = value.m_ErrorMessage;
}

RejectEventRequest::Body::RejectEventRequestRec::~RejectEventRequestRec()
{
}

RejectEventRequest::Body::RejectEventRequestRec* const RejectEventRequest::Body::getRejectEventRequestRec()
{
	return &m_RejectEventRequestRec;
}

int RejectEventRequest::Body::setRejectEventRequestRec(const RejectEventRequestRec &value)
{
	m_RejectEventRequestRec = value;
	setParentPresenceVector();
	return 0;
}

void RejectEventRequest::Body::setParentPresenceVector()
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
const unsigned int RejectEventRequest::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_RejectEventRequestRec.getSize();
	
	return size;
}

void RejectEventRequest::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RejectEventRequestRec.encode(bytes + pos);
	pos += m_RejectEventRequestRec.getSize();
}

void RejectEventRequest::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RejectEventRequestRec.decode(bytes + pos);
	pos += m_RejectEventRequestRec.getSize();
}

RejectEventRequest::Body &RejectEventRequest::Body::operator=(const Body &value)
{
	m_RejectEventRequestRec = value.m_RejectEventRequestRec;
	m_RejectEventRequestRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool RejectEventRequest::Body::operator==(const Body &value) const
{
	if (m_RejectEventRequestRec != value.m_RejectEventRequestRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool RejectEventRequest::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

RejectEventRequest::Body::Body()
{
	m_RejectEventRequestRec.setParent(this);
	/// No Initialization of m_RejectEventRequestRec necessary.
}

RejectEventRequest::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_RejectEventRequestRec.setParent(this);
	/// No Initialization of m_RejectEventRequestRec necessary.
	
	/// Copy the values
	m_RejectEventRequestRec = value.m_RejectEventRequestRec;
	m_RejectEventRequestRec.setParent(this);
	/// This code is currently not supported
}

RejectEventRequest::Body::~Body()
{
}

RejectEventRequest::Body* const RejectEventRequest::getBody()
{
	return &m_Body;
}

int RejectEventRequest::setBody(const Body &value)
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
const unsigned int RejectEventRequest::getSize()
{
	unsigned int size = 0;
	
	size += m_MsgHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void RejectEventRequest::encode(unsigned char *bytes)
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

void RejectEventRequest::decode(const unsigned char *bytes)
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

RejectEventRequest &RejectEventRequest::operator=(const RejectEventRequest &value)
{
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool RejectEventRequest::operator==(const RejectEventRequest &value) const
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

bool RejectEventRequest::operator!=(const RejectEventRequest &value) const
{
	return !((*this) == value);
}

RejectEventRequest::RejectEventRequest()
{
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

RejectEventRequest::RejectEventRequest(const RejectEventRequest &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MsgHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_MsgHeader = value.m_MsgHeader;
	m_Body = value.m_Body;
}

RejectEventRequest::~RejectEventRequest()
{
}


}
