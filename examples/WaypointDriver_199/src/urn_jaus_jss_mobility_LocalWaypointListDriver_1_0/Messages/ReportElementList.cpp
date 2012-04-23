#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/ReportElementList.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

void ReportElementList::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportElementList::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportElementList::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportElementList::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportElementList::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportElementList::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportElementList::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportElementList::AppHeader::HeaderRec &ReportElementList::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportElementList::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportElementList::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportElementList::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x441b;
}

ReportElementList::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x441b;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportElementList::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportElementList::AppHeader::HeaderRec* const ReportElementList::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportElementList::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportElementList::AppHeader::setParentPresenceVector()
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
const unsigned int ReportElementList::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportElementList::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportElementList::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportElementList::AppHeader &ReportElementList::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportElementList::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportElementList::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportElementList::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportElementList::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportElementList::AppHeader::~AppHeader()
{
}

ReportElementList::AppHeader* const ReportElementList::getAppHeader()
{
	return &m_AppHeader;
}

int ReportElementList::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportElementList::Body::ElementList::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportElementList::Body::ElementList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportElementList::Body::ElementList::ElementListRec::setParent(ElementList* parent)
{
	m_parent = parent;
}

void ReportElementList::Body::ElementList::ElementListRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportElementList::Body::ElementList::ElementListRec::getElementUID()
{
	return m_ElementUID;
}

int ReportElementList::Body::ElementList::ElementListRec::setElementUID(jUnsignedShortInteger value)
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
const unsigned int ReportElementList::Body::ElementList::ElementListRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportElementList::Body::ElementList::ElementListRec::encode(unsigned char *bytes)
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

void ReportElementList::Body::ElementList::ElementListRec::decode(const unsigned char *bytes)
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

ReportElementList::Body::ElementList::ElementListRec &ReportElementList::Body::ElementList::ElementListRec::operator=(const ElementListRec &value)
{
	m_ElementUID = value.m_ElementUID;
	
	return *this;
}

bool ReportElementList::Body::ElementList::ElementListRec::operator==(const ElementListRec &value) const
{
	if (m_ElementUID != value.m_ElementUID)
	{
		return false;
	}
	
	return true;
}

bool ReportElementList::Body::ElementList::ElementListRec::operator!=(const ElementListRec &value) const
{
	return !((*this) == value);
}

ReportElementList::Body::ElementList::ElementListRec::ElementListRec()
{
	m_parent = NULL;
	m_ElementUID = 0;
}

ReportElementList::Body::ElementList::ElementListRec::ElementListRec(const ElementListRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_ElementUID = 0;
	
	/// Copy the values
	m_ElementUID = value.m_ElementUID;
}

ReportElementList::Body::ElementList::ElementListRec::~ElementListRec()
{
}

unsigned int ReportElementList::Body::ElementList::getNumberOfElements() const
{
	return (unsigned int) m_ElementListRec.size();
}

ReportElementList::Body::ElementList::ElementListRec* const ReportElementList::Body::ElementList::getElement(unsigned int index)
{
	return &m_ElementListRec.at(index);
}

int ReportElementList::Body::ElementList::setElement(unsigned int index, const ElementListRec &value)
{
	if(m_ElementListRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ElementListRec.at(index) = value;
	m_ElementListRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportElementList::Body::ElementList::addElement(const ElementListRec &value)
{
	m_ElementListRec.push_back(value);
	m_ElementListRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportElementList::Body::ElementList::deleteElement(unsigned int index)
{
	if(m_ElementListRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ElementListRec.erase(m_ElementListRec.begin()+index);
	return 0;
}

int ReportElementList::Body::ElementList::deleteLastElement()
{
	m_ElementListRec.pop_back();
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
const unsigned int ReportElementList::Body::ElementList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_ElementListRec.size(); i++)
	{
		size += m_ElementListRec[i].getSize();
	}
	
	return size;
}

void ReportElementList::Body::ElementList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_ElementListRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_ElementListRec.size(); i++)
	{
		m_ElementListRec[i].encode(bytes + pos);
		pos += m_ElementListRec[i].getSize();
	}
}

void ReportElementList::Body::ElementList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_ElementListRec.resize(size);
	for (int i = 0; i < m_ElementListRec.size(); i++)
	{
		m_ElementListRec[i].decode(bytes + pos);
		pos += m_ElementListRec[i].getSize();
	}
}

ReportElementList::Body::ElementList &ReportElementList::Body::ElementList::operator=(const ElementList &value)
{
	m_ElementListRec.clear();
	
	for (int i = 0; i < value.m_ElementListRec.size(); i++)
	{
		m_ElementListRec.push_back(value.m_ElementListRec[i]);
		m_ElementListRec[i].setParent(this);
	}
	
	return *this;
}

bool ReportElementList::Body::ElementList::operator==(const ElementList &value) const
{
	for (int i = 0; i < m_ElementListRec.size(); i++)
	{
		if (m_ElementListRec[i] !=  value.m_ElementListRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportElementList::Body::ElementList::operator!=(const ElementList &value) const
{
	return !((*this) == value);
}

ReportElementList::Body::ElementList::ElementList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ElementListRec.size(); i++)
	{
		m_ElementListRec[i].setParent(this);
	}
	/// No Initialization of m_ElementListRec necessary.
}

ReportElementList::Body::ElementList::ElementList(const ElementList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ElementListRec.size(); i++)
	{
		m_ElementListRec[i].setParent(this);
	}
	/// No Initialization of m_ElementListRec necessary.
	
	/// Copy the values
	m_ElementListRec.clear();
	
	for (int i = 0; i < value.m_ElementListRec.size(); i++)
	{
		m_ElementListRec.push_back(value.m_ElementListRec[i]);
		m_ElementListRec[i].setParent(this);
	}
}

ReportElementList::Body::ElementList::~ElementList()
{
}

ReportElementList::Body::ElementList* const ReportElementList::Body::getElementList()
{
	return &m_ElementList;
}

int ReportElementList::Body::setElementList(const ElementList &value)
{
	m_ElementList = value;
	setParentPresenceVector();
	return 0;
}

void ReportElementList::Body::setParentPresenceVector()
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
const unsigned int ReportElementList::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ElementList.getSize();
	
	return size;
}

void ReportElementList::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ElementList.encode(bytes + pos);
	pos += m_ElementList.getSize();
}

void ReportElementList::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ElementList.decode(bytes + pos);
	pos += m_ElementList.getSize();
}

ReportElementList::Body &ReportElementList::Body::operator=(const Body &value)
{
	m_ElementList = value.m_ElementList;
	m_ElementList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportElementList::Body::operator==(const Body &value) const
{
	if (m_ElementList != value.m_ElementList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportElementList::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportElementList::Body::Body()
{
	m_ElementList.setParent(this);
	/// No Initialization of m_ElementList necessary.
}

ReportElementList::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ElementList.setParent(this);
	/// No Initialization of m_ElementList necessary.
	
	/// Copy the values
	m_ElementList = value.m_ElementList;
	m_ElementList.setParent(this);
	/// This code is currently not supported
}

ReportElementList::Body::~Body()
{
}

ReportElementList::Body* const ReportElementList::getBody()
{
	return &m_Body;
}

int ReportElementList::setBody(const Body &value)
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
const unsigned int ReportElementList::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportElementList::encode(unsigned char *bytes)
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

void ReportElementList::decode(const unsigned char *bytes)
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

ReportElementList &ReportElementList::operator=(const ReportElementList &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportElementList::operator==(const ReportElementList &value) const
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

bool ReportElementList::operator!=(const ReportElementList &value) const
{
	return !((*this) == value);
}

ReportElementList::ReportElementList()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportElementList::ReportElementList(const ReportElementList &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportElementList::~ReportElementList()
{
}


}
