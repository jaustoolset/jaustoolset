#include "urn_jaus_jss_mobility_ListManager_1_0/Messages/DeleteElement.h"

namespace urn_jaus_jss_mobility_ListManager_1_0
{

void DeleteElement::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void DeleteElement::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger DeleteElement::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int DeleteElement::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int DeleteElement::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void DeleteElement::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void DeleteElement::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

DeleteElement::AppHeader::HeaderRec &DeleteElement::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool DeleteElement::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool DeleteElement::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

DeleteElement::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x041b;
}

DeleteElement::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x041b;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

DeleteElement::AppHeader::HeaderRec::~HeaderRec()
{
}

DeleteElement::AppHeader::HeaderRec* const DeleteElement::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int DeleteElement::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void DeleteElement::AppHeader::setParentPresenceVector()
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
const unsigned int DeleteElement::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void DeleteElement::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void DeleteElement::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

DeleteElement::AppHeader &DeleteElement::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool DeleteElement::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool DeleteElement::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

DeleteElement::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

DeleteElement::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

DeleteElement::AppHeader::~AppHeader()
{
}

DeleteElement::AppHeader* const DeleteElement::getAppHeader()
{
	return &m_AppHeader;
}

int DeleteElement::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void DeleteElement::Body::DeleteElementSeq::setParent(Body* parent)
{
	m_parent = parent;
}

void DeleteElement::Body::DeleteElementSeq::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void DeleteElement::Body::DeleteElementSeq::RequestIDRec::setParent(DeleteElementSeq* parent)
{
	m_parent = parent;
}

void DeleteElement::Body::DeleteElementSeq::RequestIDRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte DeleteElement::Body::DeleteElementSeq::RequestIDRec::getRequestID()
{
	return m_RequestID;
}

int DeleteElement::Body::DeleteElementSeq::RequestIDRec::setRequestID(jUnsignedByte value)
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
const unsigned int DeleteElement::Body::DeleteElementSeq::RequestIDRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	
	return size;
}

void DeleteElement::Body::DeleteElementSeq::RequestIDRec::encode(unsigned char *bytes)
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

void DeleteElement::Body::DeleteElementSeq::RequestIDRec::decode(const unsigned char *bytes)
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

DeleteElement::Body::DeleteElementSeq::RequestIDRec &DeleteElement::Body::DeleteElementSeq::RequestIDRec::operator=(const RequestIDRec &value)
{
	m_RequestID = value.m_RequestID;
	
	return *this;
}

bool DeleteElement::Body::DeleteElementSeq::RequestIDRec::operator==(const RequestIDRec &value) const
{
	if (m_RequestID != value.m_RequestID)
	{
		return false;
	}
	
	return true;
}

bool DeleteElement::Body::DeleteElementSeq::RequestIDRec::operator!=(const RequestIDRec &value) const
{
	return !((*this) == value);
}

DeleteElement::Body::DeleteElementSeq::RequestIDRec::RequestIDRec()
{
	m_parent = NULL;
	m_RequestID = 0;
}

DeleteElement::Body::DeleteElementSeq::RequestIDRec::RequestIDRec(const RequestIDRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestID = 0;
	
	/// Copy the values
	m_RequestID = value.m_RequestID;
}

DeleteElement::Body::DeleteElementSeq::RequestIDRec::~RequestIDRec()
{
}

DeleteElement::Body::DeleteElementSeq::RequestIDRec* const DeleteElement::Body::DeleteElementSeq::getRequestIDRec()
{
	return &m_RequestIDRec;
}

int DeleteElement::Body::DeleteElementSeq::setRequestIDRec(const RequestIDRec &value)
{
	m_RequestIDRec = value;
	setParentPresenceVector();
	return 0;
}

void DeleteElement::Body::DeleteElementSeq::DeleteElementList::setParent(DeleteElementSeq* parent)
{
	m_parent = parent;
}

void DeleteElement::Body::DeleteElementSeq::DeleteElementList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::setParent(DeleteElementList* parent)
{
	m_parent = parent;
}

void DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::getElementUID()
{
	return m_ElementUID;
}

int DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::setElementUID(jUnsignedShortInteger value)
{
	m_ElementUID = value;
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
const unsigned int DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::encode(unsigned char *bytes)
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
}

void DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::decode(const unsigned char *bytes)
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
}

DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec &DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::operator=(const DeleteElementRec &value)
{
	m_ElementUID = value.m_ElementUID;
	
	return *this;
}

bool DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::operator==(const DeleteElementRec &value) const
{
	if (m_ElementUID != value.m_ElementUID)
	{
		return false;
	}
	
	return true;
}

bool DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::operator!=(const DeleteElementRec &value) const
{
	return !((*this) == value);
}

DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::DeleteElementRec()
{
	m_parent = NULL;
	m_ElementUID = 0;
}

DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::DeleteElementRec(const DeleteElementRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_ElementUID = 0;
	
	/// Copy the values
	m_ElementUID = value.m_ElementUID;
}

DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec::~DeleteElementRec()
{
}

unsigned int DeleteElement::Body::DeleteElementSeq::DeleteElementList::getNumberOfElements() const
{
	return (unsigned int) m_DeleteElementRec.size();
}

DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementRec* const DeleteElement::Body::DeleteElementSeq::DeleteElementList::getElement(unsigned int index)
{
	return &m_DeleteElementRec.at(index);
}

int DeleteElement::Body::DeleteElementSeq::DeleteElementList::setElement(unsigned int index, const DeleteElementRec &value)
{
	if(m_DeleteElementRec.size()-1 < index)
	{
		return 1;
	}
	
	m_DeleteElementRec.at(index) = value;
	m_DeleteElementRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int DeleteElement::Body::DeleteElementSeq::DeleteElementList::addElement(const DeleteElementRec &value)
{
	m_DeleteElementRec.push_back(value);
	m_DeleteElementRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int DeleteElement::Body::DeleteElementSeq::DeleteElementList::deleteElement(unsigned int index)
{
	if(m_DeleteElementRec.size()-1 < index)
	{
		return 1;
	}
	
	m_DeleteElementRec.erase(m_DeleteElementRec.begin()+index);
	return 0;
}

int DeleteElement::Body::DeleteElementSeq::DeleteElementList::deleteLastElement()
{
	m_DeleteElementRec.pop_back();
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
const unsigned int DeleteElement::Body::DeleteElementSeq::DeleteElementList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_DeleteElementRec.size(); i++)
	{
		size += m_DeleteElementRec[i].getSize();
	}
	
	return size;
}

void DeleteElement::Body::DeleteElementSeq::DeleteElementList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_DeleteElementRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_DeleteElementRec.size(); i++)
	{
		m_DeleteElementRec[i].encode(bytes + pos);
		pos += m_DeleteElementRec[i].getSize();
	}
}

void DeleteElement::Body::DeleteElementSeq::DeleteElementList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_DeleteElementRec.resize(size);
	for (int i = 0; i < m_DeleteElementRec.size(); i++)
	{
		m_DeleteElementRec[i].decode(bytes + pos);
		pos += m_DeleteElementRec[i].getSize();
	}
}

DeleteElement::Body::DeleteElementSeq::DeleteElementList &DeleteElement::Body::DeleteElementSeq::DeleteElementList::operator=(const DeleteElementList &value)
{
	m_DeleteElementRec.clear();
	
	for (int i = 0; i < value.m_DeleteElementRec.size(); i++)
	{
		m_DeleteElementRec.push_back(value.m_DeleteElementRec[i]);
		m_DeleteElementRec[i].setParent(this);
	}
	
	return *this;
}

bool DeleteElement::Body::DeleteElementSeq::DeleteElementList::operator==(const DeleteElementList &value) const
{
	for (int i = 0; i < m_DeleteElementRec.size(); i++)
	{
		if (m_DeleteElementRec[i] !=  value.m_DeleteElementRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool DeleteElement::Body::DeleteElementSeq::DeleteElementList::operator!=(const DeleteElementList &value) const
{
	return !((*this) == value);
}

DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_DeleteElementRec.size(); i++)
	{
		m_DeleteElementRec[i].setParent(this);
	}
	/// No Initialization of m_DeleteElementRec necessary.
}

DeleteElement::Body::DeleteElementSeq::DeleteElementList::DeleteElementList(const DeleteElementList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_DeleteElementRec.size(); i++)
	{
		m_DeleteElementRec[i].setParent(this);
	}
	/// No Initialization of m_DeleteElementRec necessary.
	
	/// Copy the values
	m_DeleteElementRec.clear();
	
	for (int i = 0; i < value.m_DeleteElementRec.size(); i++)
	{
		m_DeleteElementRec.push_back(value.m_DeleteElementRec[i]);
		m_DeleteElementRec[i].setParent(this);
	}
}

DeleteElement::Body::DeleteElementSeq::DeleteElementList::~DeleteElementList()
{
}

DeleteElement::Body::DeleteElementSeq::DeleteElementList* const DeleteElement::Body::DeleteElementSeq::getDeleteElementList()
{
	return &m_DeleteElementList;
}

int DeleteElement::Body::DeleteElementSeq::setDeleteElementList(const DeleteElementList &value)
{
	m_DeleteElementList = value;
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
const unsigned int DeleteElement::Body::DeleteElementSeq::getSize()
{
	unsigned int size = 0;
	
	size += m_RequestIDRec.getSize();
	size += m_DeleteElementList.getSize();
	
	return size;
}

void DeleteElement::Body::DeleteElementSeq::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIDRec.encode(bytes + pos);
	pos += m_RequestIDRec.getSize();
	m_DeleteElementList.encode(bytes + pos);
	pos += m_DeleteElementList.getSize();
}

void DeleteElement::Body::DeleteElementSeq::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_RequestIDRec.decode(bytes + pos);
	pos += m_RequestIDRec.getSize();
	m_DeleteElementList.decode(bytes + pos);
	pos += m_DeleteElementList.getSize();
}

DeleteElement::Body::DeleteElementSeq &DeleteElement::Body::DeleteElementSeq::operator=(const DeleteElementSeq &value)
{
	m_RequestIDRec = value.m_RequestIDRec;
	m_RequestIDRec.setParent(this);
	m_RequestIDRec = value.m_RequestIDRec;
	m_DeleteElementList = value.m_DeleteElementList;
	m_DeleteElementList.setParent(this);
	m_DeleteElementList = value.m_DeleteElementList;
	
	return *this;
}

bool DeleteElement::Body::DeleteElementSeq::operator==(const DeleteElementSeq &value) const
{
	if (m_RequestIDRec != value.m_RequestIDRec)
	{
		return false;
	}
	
	if (m_RequestIDRec != value.m_RequestIDRec)
	{
		return false;
	}
	if (m_DeleteElementList != value.m_DeleteElementList)
	{
		return false;
	}
	
	if (m_DeleteElementList != value.m_DeleteElementList)
	{
		return false;
	}
	
	return true;
}

bool DeleteElement::Body::DeleteElementSeq::operator!=(const DeleteElementSeq &value) const
{
	return !((*this) == value);
}

DeleteElement::Body::DeleteElementSeq::DeleteElementSeq()
{
	m_parent = NULL;
	m_RequestIDRec.setParent(this);
	/// No Initialization of m_RequestIDRec necessary.
	m_DeleteElementList.setParent(this);
	/// No Initialization of m_DeleteElementList necessary.
}

DeleteElement::Body::DeleteElementSeq::DeleteElementSeq(const DeleteElementSeq &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_RequestIDRec.setParent(this);
	/// No Initialization of m_RequestIDRec necessary.
	m_DeleteElementList.setParent(this);
	/// No Initialization of m_DeleteElementList necessary.
	
	/// Copy the values
	m_RequestIDRec = value.m_RequestIDRec;
	m_RequestIDRec.setParent(this);
	m_RequestIDRec = value.m_RequestIDRec;
	m_DeleteElementList = value.m_DeleteElementList;
	m_DeleteElementList.setParent(this);
	m_DeleteElementList = value.m_DeleteElementList;
}

DeleteElement::Body::DeleteElementSeq::~DeleteElementSeq()
{
}

DeleteElement::Body::DeleteElementSeq* const DeleteElement::Body::getDeleteElementSeq()
{
	return &m_DeleteElementSeq;
}

int DeleteElement::Body::setDeleteElementSeq(const DeleteElementSeq &value)
{
	m_DeleteElementSeq = value;
	setParentPresenceVector();
	return 0;
}

void DeleteElement::Body::setParentPresenceVector()
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
const unsigned int DeleteElement::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_DeleteElementSeq.getSize();
	
	return size;
}

void DeleteElement::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_DeleteElementSeq.encode(bytes + pos);
	pos += m_DeleteElementSeq.getSize();
}

void DeleteElement::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_DeleteElementSeq.decode(bytes + pos);
	pos += m_DeleteElementSeq.getSize();
}

DeleteElement::Body &DeleteElement::Body::operator=(const Body &value)
{
	m_DeleteElementSeq = value.m_DeleteElementSeq;
	m_DeleteElementSeq.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool DeleteElement::Body::operator==(const Body &value) const
{
	if (m_DeleteElementSeq != value.m_DeleteElementSeq)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool DeleteElement::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

DeleteElement::Body::Body()
{
	m_DeleteElementSeq.setParent(this);
	/// No Initialization of m_DeleteElementSeq necessary.
}

DeleteElement::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_DeleteElementSeq.setParent(this);
	/// No Initialization of m_DeleteElementSeq necessary.
	
	/// Copy the values
	m_DeleteElementSeq = value.m_DeleteElementSeq;
	m_DeleteElementSeq.setParent(this);
	/// This code is currently not supported
}

DeleteElement::Body::~Body()
{
}

DeleteElement::Body* const DeleteElement::getBody()
{
	return &m_Body;
}

int DeleteElement::setBody(const Body &value)
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
const unsigned int DeleteElement::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void DeleteElement::encode(unsigned char *bytes)
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

void DeleteElement::decode(const unsigned char *bytes)
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

DeleteElement &DeleteElement::operator=(const DeleteElement &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool DeleteElement::operator==(const DeleteElement &value) const
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

bool DeleteElement::operator!=(const DeleteElement &value) const
{
	return !((*this) == value);
}

DeleteElement::DeleteElement()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

DeleteElement::DeleteElement(const DeleteElement &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

DeleteElement::~DeleteElement()
{
}


}
