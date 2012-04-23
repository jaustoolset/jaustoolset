#include "urn_jaus_jss_mobility_ListManager_1_0/Messages/SetElement.h"

namespace urn_jaus_jss_mobility_ListManager_1_0
{

void SetElement::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void SetElement::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger SetElement::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int SetElement::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int SetElement::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void SetElement::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void SetElement::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

SetElement::AppHeader::HeaderRec &SetElement::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool SetElement::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool SetElement::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

SetElement::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x041a;
}

SetElement::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x041a;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

SetElement::AppHeader::HeaderRec::~HeaderRec()
{
}

SetElement::AppHeader::HeaderRec* const SetElement::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int SetElement::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void SetElement::AppHeader::setParentPresenceVector()
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
const unsigned int SetElement::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void SetElement::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void SetElement::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

SetElement::AppHeader &SetElement::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool SetElement::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool SetElement::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

SetElement::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

SetElement::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

SetElement::AppHeader::~AppHeader()
{
}

SetElement::AppHeader* const SetElement::getAppHeader()
{
	return &m_AppHeader;
}

int SetElement::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void SetElement::Body::SetElementSeq::setParent(Body* parent)
{
	m_parent = parent;
}

void SetElement::Body::SetElementSeq::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void SetElement::Body::SetElementSeq::RequestIDRec::setParent(SetElementSeq* parent)
{
	m_parent = parent;
}

void SetElement::Body::SetElementSeq::RequestIDRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte SetElement::Body::SetElementSeq::RequestIDRec::getRequestID()
{
	return m_RequestID;
}

int SetElement::Body::SetElementSeq::RequestIDRec::setRequestID(jUnsignedByte value)
{
	m_RequestID = value;
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
const unsigned int SetElement::Body::SetElementSeq::RequestIDRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void SetElement::Body::SetElementSeq::RequestIDRec::encode(unsigned char *bytes)
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
}

void SetElement::Body::SetElementSeq::RequestIDRec::decode(const unsigned char *bytes)
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
}

SetElement::Body::SetElementSeq::RequestIDRec &SetElement::Body::SetElementSeq::RequestIDRec::operator=(const RequestIDRec &value)
{
	m_RequestID = value.m_RequestID;
	
	return *this;
}

bool SetElement::Body::SetElementSeq::RequestIDRec::operator==(const RequestIDRec &value) const
{
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	
	return true;
}

bool SetElement::Body::SetElementSeq::RequestIDRec::operator!=(const RequestIDRec &value) const
{
	return !((*this) == value);
}

SetElement::Body::SetElementSeq::RequestIDRec::RequestIDRec()
{
	m_parent = NULL;
	m_RequestID = 0;
}

SetElement::Body::SetElementSeq::RequestIDRec::RequestIDRec(const RequestIDRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
}

SetElement::Body::SetElementSeq::RequestIDRec::~RequestIDRec()
{
}

SetElement::Body::SetElementSeq::RequestIDRec* const SetElement::Body::SetElementSeq::getRequestIDRec()
{
	return &m_RequestIDRec;
}

int SetElement::Body::SetElementSeq::setRequestIDRec(const RequestIDRec &value)
{
	m_RequestIDRec = value;
	setParentPresenceVector();
	return 0;
}

void SetElement::Body::SetElementSeq::ElementList::setParent(SetElementSeq* parent)
{
	m_parent = parent;
}

void SetElement::Body::SetElementSeq::ElementList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void SetElement::Body::SetElementSeq::ElementList::ElementRec::setParent(ElementList* parent)
{
	m_parent = parent;
}

void SetElement::Body::SetElementSeq::ElementList::ElementRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger SetElement::Body::SetElementSeq::ElementList::ElementRec::getElementUID()
{
	return m_ElementUID;
}

int SetElement::Body::SetElementSeq::ElementList::ElementRec::setElementUID(jUnsignedShortInteger value)
{
	m_ElementUID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedShortInteger SetElement::Body::SetElementSeq::ElementList::ElementRec::getPreviousUID()
{
	return m_PreviousUID;
}

int SetElement::Body::SetElementSeq::ElementList::ElementRec::setPreviousUID(jUnsignedShortInteger value)
{
	m_PreviousUID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedShortInteger SetElement::Body::SetElementSeq::ElementList::ElementRec::getNextUID()
{
	return m_NextUID;
}

int SetElement::Body::SetElementSeq::ElementList::ElementRec::setNextUID(jUnsignedShortInteger value)
{
	m_NextUID = value;
	setParentPresenceVector();
	return 0;
}

void SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::setParent(ElementRec* parent)
{
	m_parent = parent;
}

void SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const jUnsignedByte SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::getFormat() const
{
	return m_Format;
}

const jUnsignedShortInteger SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::getLength() const
{
	return m_Length;
}

const unsigned char *SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::getData() const
{
	return m_Data;
}

int SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::set(jUnsignedByte format, jUnsignedShortInteger length, unsigned char *data)
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
const unsigned int SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	size += m_Length;
	
	return size;
}

void SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::encode(unsigned char *bytes)
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

void SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::decode(const unsigned char *bytes)
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

SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData &SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::operator=(const ElementData &value)
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

bool SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::operator==(const ElementData &value) const
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

bool SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::operator!=(const ElementData &value) const
{
	return !((*this) == value);
}

SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::ElementData()
{
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
	m_Format = 0;
}

SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::ElementData(const ElementData &value)
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

SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData::~ElementData()
{
	delete[] m_Data;
}

SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementData* const SetElement::Body::SetElementSeq::ElementList::ElementRec::getElementData()
{
	return &m_ElementData;
}

int SetElement::Body::SetElementSeq::ElementList::ElementRec::setElementData(const ElementData &value)
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
const unsigned int SetElement::Body::SetElementSeq::ElementList::ElementRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedShortInteger);
	size += m_ElementData.getSize();
	
	return size;
}

void SetElement::Body::SetElementSeq::ElementList::ElementRec::encode(unsigned char *bytes)
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

void SetElement::Body::SetElementSeq::ElementList::ElementRec::decode(const unsigned char *bytes)
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

SetElement::Body::SetElementSeq::ElementList::ElementRec &SetElement::Body::SetElementSeq::ElementList::ElementRec::operator=(const ElementRec &value)
{
	m_ElementUID = value.m_ElementUID;
	m_PreviousUID = value.m_PreviousUID;
	m_NextUID = value.m_NextUID;
	m_ElementData = value.m_ElementData;
	
	return *this;
}

bool SetElement::Body::SetElementSeq::ElementList::ElementRec::operator==(const ElementRec &value) const
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

bool SetElement::Body::SetElementSeq::ElementList::ElementRec::operator!=(const ElementRec &value) const
{
	return !((*this) == value);
}

SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementRec()
{
	m_parent = NULL;
	m_ElementUID = 0;
	m_PreviousUID = 0;
	m_NextUID = 0;
	m_ElementData.setParent(this);
}

SetElement::Body::SetElementSeq::ElementList::ElementRec::ElementRec(const ElementRec &value)
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

SetElement::Body::SetElementSeq::ElementList::ElementRec::~ElementRec()
{
}

unsigned int SetElement::Body::SetElementSeq::ElementList::getNumberOfElements() const
{
	return (unsigned int) m_ElementRec.size();
}

SetElement::Body::SetElementSeq::ElementList::ElementRec* const SetElement::Body::SetElementSeq::ElementList::getElement(unsigned int index)
{
	return &m_ElementRec.at(index);
}

int SetElement::Body::SetElementSeq::ElementList::setElement(unsigned int index, const ElementRec &value)
{
	if(m_ElementRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ElementRec.at(index) = value;
	m_ElementRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int SetElement::Body::SetElementSeq::ElementList::addElement(const ElementRec &value)
{
	m_ElementRec.push_back(value);
	m_ElementRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int SetElement::Body::SetElementSeq::ElementList::deleteElement(unsigned int index)
{
	if(m_ElementRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ElementRec.erase(m_ElementRec.begin()+index);
	return 0;
}

int SetElement::Body::SetElementSeq::ElementList::deleteLastElement()
{
	m_ElementRec.pop_back();
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
const unsigned int SetElement::Body::SetElementSeq::ElementList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_ElementRec.size(); i++)
	{
		size += m_ElementRec[i].getSize();
	}
	
	return size;
}

void SetElement::Body::SetElementSeq::ElementList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_ElementRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_ElementRec.size(); i++)
	{
		m_ElementRec[i].encode(bytes + pos);
		pos += m_ElementRec[i].getSize();
	}
}

void SetElement::Body::SetElementSeq::ElementList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_ElementRec.resize(size);
	for (int i = 0; i < m_ElementRec.size(); i++)
	{
		m_ElementRec[i].decode(bytes + pos);
		pos += m_ElementRec[i].getSize();
	}
}

SetElement::Body::SetElementSeq::ElementList &SetElement::Body::SetElementSeq::ElementList::operator=(const ElementList &value)
{
	m_ElementRec.clear();
	
	for (int i = 0; i < value.m_ElementRec.size(); i++)
	{
		m_ElementRec.push_back(value.m_ElementRec[i]);
		m_ElementRec[i].setParent(this);
	}
	
	return *this;
}

bool SetElement::Body::SetElementSeq::ElementList::operator==(const ElementList &value) const
{
	for (int i = 0; i < m_ElementRec.size(); i++)
	{
		if (m_ElementRec[i] !=  value.m_ElementRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool SetElement::Body::SetElementSeq::ElementList::operator!=(const ElementList &value) const
{
	return !((*this) == value);
}

SetElement::Body::SetElementSeq::ElementList::ElementList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ElementRec.size(); i++)
	{
		m_ElementRec[i].setParent(this);
	}
	/// No Initialization of m_ElementRec necessary.
}

SetElement::Body::SetElementSeq::ElementList::ElementList(const ElementList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ElementRec.size(); i++)
	{
		m_ElementRec[i].setParent(this);
	}
	/// No Initialization of m_ElementRec necessary.
	
	/// Copy the values
	m_ElementRec.clear();
	
	for (int i = 0; i < value.m_ElementRec.size(); i++)
	{
		m_ElementRec.push_back(value.m_ElementRec[i]);
		m_ElementRec[i].setParent(this);
	}
}

SetElement::Body::SetElementSeq::ElementList::~ElementList()
{
}

SetElement::Body::SetElementSeq::ElementList* const SetElement::Body::SetElementSeq::getElementList()
{
	return &m_ElementList;
}

int SetElement::Body::SetElementSeq::setElementList(const ElementList &value)
{
	m_ElementList = value;
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
const unsigned int SetElement::Body::SetElementSeq::getSize()
{
	unsigned int size = 0;
	
	size += m_RequestIDRec.getSize();
	size += m_ElementList.getSize();
	
	return size;
}

void SetElement::Body::SetElementSeq::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIDRec.encode(bytes + pos);
	pos += m_RequestIDRec.getSize();
	m_ElementList.encode(bytes + pos);
	pos += m_ElementList.getSize();
}

void SetElement::Body::SetElementSeq::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIDRec.decode(bytes + pos);
	pos += m_RequestIDRec.getSize();
	m_ElementList.decode(bytes + pos);
	pos += m_ElementList.getSize();
}

SetElement::Body::SetElementSeq &SetElement::Body::SetElementSeq::operator=(const SetElementSeq &value)
{
	m_RequestIDRec = value.m_RequestIDRec;
	m_RequestIDRec.setParent(this);
	m_RequestIDRec = value.m_RequestIDRec;
	m_ElementList = value.m_ElementList;
	m_ElementList.setParent(this);
	m_ElementList = value.m_ElementList;
	
	return *this;
}

bool SetElement::Body::SetElementSeq::operator==(const SetElementSeq &value) const
{
	if (m_RequestIDRec != value.m_RequestIDRec)
	{
		return false;
	}
	
	if (m_RequestIDRec != value.m_RequestIDRec)
	{
		return false;
	}
	if (m_ElementList != value.m_ElementList)
	{
		return false;
	}
	
	if (m_ElementList != value.m_ElementList)
	{
		return false;
	}
	
	return true;
}

bool SetElement::Body::SetElementSeq::operator!=(const SetElementSeq &value) const
{
	return !((*this) == value);
}

SetElement::Body::SetElementSeq::SetElementSeq()
{
	m_parent = NULL;
	m_RequestIDRec.setParent(this);
	/// No Initialization of m_RequestIDRec necessary.
	m_ElementList.setParent(this);
	/// No Initialization of m_ElementList necessary.
}

SetElement::Body::SetElementSeq::SetElementSeq(const SetElementSeq &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestIDRec.setParent(this);
	/// No Initialization of m_RequestIDRec necessary.
	m_ElementList.setParent(this);
	/// No Initialization of m_ElementList necessary.
	
	/// Copy the values
	m_RequestIDRec = value.m_RequestIDRec;
	m_RequestIDRec.setParent(this);
	m_RequestIDRec = value.m_RequestIDRec;
	m_ElementList = value.m_ElementList;
	m_ElementList.setParent(this);
	m_ElementList = value.m_ElementList;
}

SetElement::Body::SetElementSeq::~SetElementSeq()
{
}

SetElement::Body::SetElementSeq* const SetElement::Body::getSetElementSeq()
{
	return &m_SetElementSeq;
}

int SetElement::Body::setSetElementSeq(const SetElementSeq &value)
{
	m_SetElementSeq = value;
	setParentPresenceVector();
	return 0;
}

void SetElement::Body::setParentPresenceVector()
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
const unsigned int SetElement::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_SetElementSeq.getSize();
	
	return size;
}

void SetElement::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SetElementSeq.encode(bytes + pos);
	pos += m_SetElementSeq.getSize();
}

void SetElement::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SetElementSeq.decode(bytes + pos);
	pos += m_SetElementSeq.getSize();
}

SetElement::Body &SetElement::Body::operator=(const Body &value)
{
	m_SetElementSeq = value.m_SetElementSeq;
	m_SetElementSeq.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool SetElement::Body::operator==(const Body &value) const
{
	if (m_SetElementSeq != value.m_SetElementSeq)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool SetElement::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

SetElement::Body::Body()
{
	m_SetElementSeq.setParent(this);
	/// No Initialization of m_SetElementSeq necessary.
}

SetElement::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_SetElementSeq.setParent(this);
	/// No Initialization of m_SetElementSeq necessary.
	
	/// Copy the values
	m_SetElementSeq = value.m_SetElementSeq;
	m_SetElementSeq.setParent(this);
	/// This code is currently not supported
}

SetElement::Body::~Body()
{
}

SetElement::Body* const SetElement::getBody()
{
	return &m_Body;
}

int SetElement::setBody(const Body &value)
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
const unsigned int SetElement::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void SetElement::encode(unsigned char *bytes)
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

void SetElement::decode(const unsigned char *bytes)
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

SetElement &SetElement::operator=(const SetElement &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool SetElement::operator==(const SetElement &value) const
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

bool SetElement::operator!=(const SetElement &value) const
{
	return !((*this) == value);
}

SetElement::SetElement()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

SetElement::SetElement(const SetElement &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

SetElement::~SetElement()
{
}


}
