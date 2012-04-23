#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/ReportElement.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

void ReportElement::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportElement::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportElement::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportElement::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportElement::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportElement::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportElement::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportElement::AppHeader::HeaderRec &ReportElement::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportElement::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportElement::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportElement::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x441a;
}

ReportElement::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x441a;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportElement::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportElement::AppHeader::HeaderRec* const ReportElement::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportElement::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportElement::AppHeader::setParentPresenceVector()
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
const unsigned int ReportElement::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportElement::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportElement::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportElement::AppHeader &ReportElement::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportElement::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportElement::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportElement::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportElement::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportElement::AppHeader::~AppHeader()
{
}

ReportElement::AppHeader* const ReportElement::getAppHeader()
{
	return &m_AppHeader;
}

int ReportElement::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportElement::Body::ElementRec::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportElement::Body::ElementRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportElement::Body::ElementRec::getElementUID()
{
	return m_ElementUID;
}

int ReportElement::Body::ElementRec::setElementUID(jUnsignedShortInteger value)
{
	m_ElementUID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedShortInteger ReportElement::Body::ElementRec::getPreviousUID()
{
	return m_PreviousUID;
}

int ReportElement::Body::ElementRec::setPreviousUID(jUnsignedShortInteger value)
{
	m_PreviousUID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedShortInteger ReportElement::Body::ElementRec::getNextUID()
{
	return m_NextUID;
}

int ReportElement::Body::ElementRec::setNextUID(jUnsignedShortInteger value)
{
	m_NextUID = value;
	setParentPresenceVector();
	return 0;
}

void ReportElement::Body::ElementRec::ElementData::setParent(ElementRec* parent)
{
	m_parent = parent;
}

void ReportElement::Body::ElementRec::ElementData::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const jUnsignedByte ReportElement::Body::ElementRec::ElementData::getFormat() const
{
	return m_Format;
}

const jUnsignedShortInteger ReportElement::Body::ElementRec::ElementData::getLength() const
{
	return m_Length;
}

const unsigned char *ReportElement::Body::ElementRec::ElementData::getData() const
{
	return m_Data;
}

int ReportElement::Body::ElementRec::ElementData::set(jUnsignedByte format, jUnsignedShortInteger length, unsigned char *data)
{
	if ((format == 0)||(format == 1))
	{
		m_Format = format;
		m_Length = length;
	
		delete[] m_Data;
		m_Data = NULL;
	
		m_Data = new unsigned char[length];
		memcpy(m_Data, data, length);
	
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
const unsigned int ReportElement::Body::ElementRec::ElementData::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	size += m_Length;
	
	return size;
}

void ReportElement::Body::ElementRec::ElementData::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_FormatTemp;
	
	m_FormatTemp = JSIDL_v_1_0::correctEndianness(m_Format);
	memcpy(bytes+pos, &m_FormatTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	jUnsignedShortInteger m_LengthTemp;
	
	m_LengthTemp = JSIDL_v_1_0::correctEndianness(m_Length);
	memcpy(bytes+pos, &m_LengthTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	
	memcpy(bytes+pos, m_Data, m_Length);
	pos += m_Length;
}

void ReportElement::Body::ElementRec::ElementData::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_FormatTemp;
	
	memcpy(&m_FormatTemp, bytes+pos, sizeof(jUnsignedByte));
	m_Format = JSIDL_v_1_0::correctEndianness(m_FormatTemp);
	pos += sizeof(jUnsignedByte);
	
	jUnsignedShortInteger m_LengthTemp;
	
	memcpy(&m_LengthTemp, bytes+pos, sizeof(jUnsignedShortInteger));
	m_Length = JSIDL_v_1_0::correctEndianness(m_LengthTemp);
	pos += sizeof(jUnsignedShortInteger);
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[m_Length];
		memcpy(m_Data, bytes+pos, m_Length);
		pos += m_Length;
	}
}

ReportElement::Body::ElementRec::ElementData &ReportElement::Body::ElementRec::ElementData::operator=(const ElementData &value)
{
	this->m_Format = value.m_Format;
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

bool ReportElement::Body::ElementRec::ElementData::operator==(const ElementData &value) const
{
	if (this->m_Format != value.m_Format)
	{
		return false;
	}
	
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

bool ReportElement::Body::ElementRec::ElementData::operator!=(const ElementData &value) const
{
	return !((*this) == value);
}

ReportElement::Body::ElementRec::ElementData::ElementData()
{
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
	m_Format = 0;
}

ReportElement::Body::ElementRec::ElementData::ElementData(const ElementData &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
	m_Format = 0;
	
	/// Copy the values
	this->m_Format = value.m_Format;
	this->m_Length = value.m_Length;
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[this->m_Length];
		memcpy(this->m_Data, value.m_Data, this->m_Length);
	}
}

ReportElement::Body::ElementRec::ElementData::~ElementData()
{
	delete[] m_Data;
}

ReportElement::Body::ElementRec::ElementData* const ReportElement::Body::ElementRec::getElementData()
{
	return &m_ElementData;
}

int ReportElement::Body::ElementRec::setElementData(const ElementData &value)
{
	m_ElementData = value;
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
const unsigned int ReportElement::Body::ElementRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedShortInteger);
	size += m_ElementData.getSize();
	
	return size;
}

void ReportElement::Body::ElementRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_ElementUIDTemp;
	
	m_ElementUIDTemp = JSIDL_v_1_0::correctEndianness(m_ElementUID);
	memcpy(bytes + pos, &m_ElementUIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedShortInteger m_PreviousUIDTemp;
	
	m_PreviousUIDTemp = JSIDL_v_1_0::correctEndianness(m_PreviousUID);
	memcpy(bytes + pos, &m_PreviousUIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedShortInteger m_NextUIDTemp;
	
	m_NextUIDTemp = JSIDL_v_1_0::correctEndianness(m_NextUID);
	memcpy(bytes + pos, &m_NextUIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	m_ElementData.encode(bytes + pos);
	pos += m_ElementData.getSize();
}

void ReportElement::Body::ElementRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_ElementUIDTemp;
	
	memcpy(&m_ElementUIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_ElementUID = JSIDL_v_1_0::correctEndianness(m_ElementUIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedShortInteger m_PreviousUIDTemp;
	
	memcpy(&m_PreviousUIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_PreviousUID = JSIDL_v_1_0::correctEndianness(m_PreviousUIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedShortInteger m_NextUIDTemp;
	
	memcpy(&m_NextUIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_NextUID = JSIDL_v_1_0::correctEndianness(m_NextUIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	m_ElementData.decode(bytes + pos);
	pos += m_ElementData.getSize();
}

ReportElement::Body::ElementRec &ReportElement::Body::ElementRec::operator=(const ElementRec &value)
{
	m_ElementUID = value.m_ElementUID;
	m_PreviousUID = value.m_PreviousUID;
	m_NextUID = value.m_NextUID;
	m_ElementData = value.m_ElementData;
	
	return *this;
}

bool ReportElement::Body::ElementRec::operator==(const ElementRec &value) const
{
	if (m_ElementUID != value.m_ElementUID)
	{
		return false;
	}
	if (m_PreviousUID != value.m_PreviousUID)
	{
		return false;
	}
	if (m_NextUID != value.m_NextUID)
	{
		return false;
	}
	
	if (m_ElementData != value.m_ElementData)
	{
		return false;
	}
	
	return true;
}

bool ReportElement::Body::ElementRec::operator!=(const ElementRec &value) const
{
	return !((*this) == value);
}

ReportElement::Body::ElementRec::ElementRec()
{
	m_parent = NULL;
	m_ElementUID = 0;
	m_PreviousUID = 0;
	m_NextUID = 0;
	m_ElementData.setParent(this);
}

ReportElement::Body::ElementRec::ElementRec(const ElementRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_ElementUID = 0;
	m_PreviousUID = 0;
	m_NextUID = 0;
	m_ElementData.setParent(this);
	
	/// Copy the values
	m_ElementUID = value.m_ElementUID;
	m_PreviousUID = value.m_PreviousUID;
	m_NextUID = value.m_NextUID;
	m_ElementData = value.m_ElementData;
}

ReportElement::Body::ElementRec::~ElementRec()
{
}

ReportElement::Body::ElementRec* const ReportElement::Body::getElementRec()
{
	return &m_ElementRec;
}

int ReportElement::Body::setElementRec(const ElementRec &value)
{
	m_ElementRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportElement::Body::setParentPresenceVector()
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
const unsigned int ReportElement::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ElementRec.getSize();
	
	return size;
}

void ReportElement::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ElementRec.encode(bytes + pos);
	pos += m_ElementRec.getSize();
}

void ReportElement::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ElementRec.decode(bytes + pos);
	pos += m_ElementRec.getSize();
}

ReportElement::Body &ReportElement::Body::operator=(const Body &value)
{
	m_ElementRec = value.m_ElementRec;
	m_ElementRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportElement::Body::operator==(const Body &value) const
{
	if (m_ElementRec != value.m_ElementRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportElement::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportElement::Body::Body()
{
	m_ElementRec.setParent(this);
	/// No Initialization of m_ElementRec necessary.
}

ReportElement::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ElementRec.setParent(this);
	/// No Initialization of m_ElementRec necessary.
	
	/// Copy the values
	m_ElementRec = value.m_ElementRec;
	m_ElementRec.setParent(this);
	/// This code is currently not supported
}

ReportElement::Body::~Body()
{
}

ReportElement::Body* const ReportElement::getBody()
{
	return &m_Body;
}

int ReportElement::setBody(const Body &value)
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
const unsigned int ReportElement::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportElement::encode(unsigned char *bytes)
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

void ReportElement::decode(const unsigned char *bytes)
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

ReportElement &ReportElement::operator=(const ReportElement &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportElement::operator==(const ReportElement &value) const
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

bool ReportElement::operator!=(const ReportElement &value) const
{
	return !((*this) == value);
}

ReportElement::ReportElement()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportElement::ReportElement(const ReportElement &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportElement::~ReportElement()
{
}


}
