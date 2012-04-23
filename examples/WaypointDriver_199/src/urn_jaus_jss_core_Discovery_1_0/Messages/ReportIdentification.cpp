#include "urn_jaus_jss_core_Discovery_1_0/Messages/ReportIdentification.h"

namespace urn_jaus_jss_core_Discovery_1_0
{

void ReportIdentification::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportIdentification::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportIdentification::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportIdentification::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportIdentification::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportIdentification::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportIdentification::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportIdentification::AppHeader::HeaderRec &ReportIdentification::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportIdentification::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportIdentification::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportIdentification::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4b00;
}

ReportIdentification::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4b00;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportIdentification::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportIdentification::AppHeader::HeaderRec* const ReportIdentification::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportIdentification::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportIdentification::AppHeader::setParentPresenceVector()
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
const unsigned int ReportIdentification::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportIdentification::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportIdentification::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportIdentification::AppHeader &ReportIdentification::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportIdentification::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportIdentification::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportIdentification::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportIdentification::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportIdentification::AppHeader::~AppHeader()
{
}

ReportIdentification::AppHeader* const ReportIdentification::getAppHeader()
{
	return &m_AppHeader;
}

int ReportIdentification::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportIdentification::Body::ReportIdentificationRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportIdentification::Body::ReportIdentificationRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportIdentification::Body::ReportIdentificationRec::getQueryType()
{
	return m_QueryType;
}

int ReportIdentification::Body::ReportIdentificationRec::setQueryType(jUnsignedByte value)
{
	if (((value >= 5) && (value <= 255)) || (value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4))
	{
		m_QueryType = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedShortInteger ReportIdentification::Body::ReportIdentificationRec::getType()
{
	return m_Type;
}

int ReportIdentification::Body::ReportIdentificationRec::setType(jUnsignedShortInteger value)
{
	if (((value >= 10002) && (value <= 20000)) || ((value >= 20002) && (value <= 30000)) || ((value >= 30002) && (value <= 40000)) || ((value >= 40002) && (value <= 50000)) || ((value >= 50002) && (value <= 60000)) || ((value >= 60002) && (value <= 65535)) || (value == 0) || (value == 10001) || (value == 20001) || (value == 30001) || (value == 40001) || (value == 50001) || (value == 60001))
	{
		m_Type = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jVariableLengthString ReportIdentification::Body::ReportIdentificationRec::getIdentification()
{
	return m_Identification;
}

int ReportIdentification::Body::ReportIdentificationRec::setIdentification(jVariableLengthString value)
{
	if ( value.length() > 255)
	{
		return 1;
	}
	
	m_Identification = value;
	if (m_Identification.length() < 0)
	{
		m_Identification.resize(0);
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
const unsigned int ReportIdentification::Body::ReportIdentificationRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += m_Identification.length();
	
	return size;
}

void ReportIdentification::Body::ReportIdentificationRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_QueryTypeTemp;
	
	m_QueryTypeTemp = JSIDL_v_1_0::correctEndianness(m_QueryType);
	memcpy(bytes + pos, &m_QueryTypeTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedShortInteger m_TypeTemp;
	
	m_TypeTemp = JSIDL_v_1_0::correctEndianness(m_Type);
	memcpy(bytes + pos, &m_TypeTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	
	jUnsignedByte m_IdentificationLength = 0;
	m_IdentificationLength = JSIDL_v_1_0::correctEndianness(m_Identification.length());
	memcpy(bytes+pos, (unsigned char*)&m_IdentificationLength, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	memcpy(bytes+pos, m_Identification.c_str(), m_Identification.length());
	pos += m_Identification.length();
}

void ReportIdentification::Body::ReportIdentificationRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_QueryTypeTemp;
	
	memcpy(&m_QueryTypeTemp, bytes + pos, sizeof(jUnsignedByte));
	m_QueryType = JSIDL_v_1_0::correctEndianness(m_QueryTypeTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedShortInteger m_TypeTemp;
	
	memcpy(&m_TypeTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_Type = JSIDL_v_1_0::correctEndianness(m_TypeTemp);
	pos += sizeof(jUnsignedShortInteger);
	
	jUnsignedByte m_IdentificationLength = 0;
	memcpy((unsigned char*)&m_IdentificationLength, bytes+pos, sizeof( m_IdentificationLength ));
	m_IdentificationLength = JSIDL_v_1_0::correctEndianness(m_IdentificationLength);
	pos += sizeof( m_IdentificationLength );
	
	char* m_IdentificationTemp = new char[m_IdentificationLength+1];
	memcpy(m_IdentificationTemp, bytes+pos, m_IdentificationLength );
	m_IdentificationTemp[m_IdentificationLength] = '\0';
	m_Identification = m_IdentificationTemp;
	pos += m_IdentificationLength ;
	delete m_IdentificationTemp;
}

ReportIdentification::Body::ReportIdentificationRec &ReportIdentification::Body::ReportIdentificationRec::operator=(const ReportIdentificationRec &value)
{
	m_QueryType = value.m_QueryType;
	m_Type = value.m_Type;
	m_Identification = value.m_Identification;
	
	return *this;
}

bool ReportIdentification::Body::ReportIdentificationRec::operator==(const ReportIdentificationRec &value) const
{
	if (m_QueryType != value.m_QueryType)
	{
		return false;
	}
	if (m_Type != value.m_Type)
	{
		return false;
	}
	if ((m_Identification.length() != value.m_Identification.length()) || (m_Identification.compare(value.m_Identification) != 0))
	{
		return false;
	}
	
	return true;
}

bool ReportIdentification::Body::ReportIdentificationRec::operator!=(const ReportIdentificationRec &value) const
{
	return !((*this) == value);
}

ReportIdentification::Body::ReportIdentificationRec::ReportIdentificationRec()
{
	m_parent = NULL;
	m_QueryType = 0;
	m_Type = 0;
}

ReportIdentification::Body::ReportIdentificationRec::ReportIdentificationRec(const ReportIdentificationRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_QueryType = 0;
	m_Type = 0;
	
	/// Copy the values
	m_QueryType = value.m_QueryType;
	m_Type = value.m_Type;
	m_Identification = value.m_Identification;
}

ReportIdentification::Body::ReportIdentificationRec::~ReportIdentificationRec()
{
}

ReportIdentification::Body::ReportIdentificationRec* const ReportIdentification::Body::getReportIdentificationRec()
{
	return &m_ReportIdentificationRec;
}

int ReportIdentification::Body::setReportIdentificationRec(const ReportIdentificationRec &value)
{
	m_ReportIdentificationRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportIdentification::Body::setParentPresenceVector()
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
const unsigned int ReportIdentification::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ReportIdentificationRec.getSize();
	
	return size;
}

void ReportIdentification::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportIdentificationRec.encode(bytes + pos);
	pos += m_ReportIdentificationRec.getSize();
}

void ReportIdentification::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportIdentificationRec.decode(bytes + pos);
	pos += m_ReportIdentificationRec.getSize();
}

ReportIdentification::Body &ReportIdentification::Body::operator=(const Body &value)
{
	m_ReportIdentificationRec = value.m_ReportIdentificationRec;
	m_ReportIdentificationRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportIdentification::Body::operator==(const Body &value) const
{
	if (m_ReportIdentificationRec != value.m_ReportIdentificationRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportIdentification::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportIdentification::Body::Body()
{
	m_ReportIdentificationRec.setParent(this);
	/// No Initialization of m_ReportIdentificationRec necessary.
}

ReportIdentification::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ReportIdentificationRec.setParent(this);
	/// No Initialization of m_ReportIdentificationRec necessary.
	
	/// Copy the values
	m_ReportIdentificationRec = value.m_ReportIdentificationRec;
	m_ReportIdentificationRec.setParent(this);
	/// This code is currently not supported
}

ReportIdentification::Body::~Body()
{
}

ReportIdentification::Body* const ReportIdentification::getBody()
{
	return &m_Body;
}

int ReportIdentification::setBody(const Body &value)
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
const unsigned int ReportIdentification::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportIdentification::encode(unsigned char *bytes)
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

void ReportIdentification::decode(const unsigned char *bytes)
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

ReportIdentification &ReportIdentification::operator=(const ReportIdentification &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportIdentification::operator==(const ReportIdentification &value) const
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

bool ReportIdentification::operator!=(const ReportIdentification &value) const
{
	return !((*this) == value);
}

ReportIdentification::ReportIdentification()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportIdentification::ReportIdentification(const ReportIdentification &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportIdentification::~ReportIdentification()
{
}


}
