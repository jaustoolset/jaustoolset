#include "urn_jaus_jss_core_Discovery_1_0/Messages/RegisterServices.h"

namespace urn_jaus_jss_core_Discovery_1_0
{

void RegisterServices::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void RegisterServices::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger RegisterServices::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int RegisterServices::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int RegisterServices::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void RegisterServices::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void RegisterServices::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

RegisterServices::AppHeader::HeaderRec &RegisterServices::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool RegisterServices::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool RegisterServices::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

RegisterServices::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x0b00;
}

RegisterServices::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x0b00;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

RegisterServices::AppHeader::HeaderRec::~HeaderRec()
{
}

RegisterServices::AppHeader::HeaderRec* const RegisterServices::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int RegisterServices::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void RegisterServices::AppHeader::setParentPresenceVector()
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
const unsigned int RegisterServices::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void RegisterServices::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void RegisterServices::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

RegisterServices::AppHeader &RegisterServices::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool RegisterServices::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool RegisterServices::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

RegisterServices::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

RegisterServices::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

RegisterServices::AppHeader::~AppHeader()
{
}

RegisterServices::AppHeader* const RegisterServices::getAppHeader()
{
	return &m_AppHeader;
}

int RegisterServices::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void RegisterServices::RegisterServicesBody::ServiceList::setParent(RegisterServicesBody* parent)
{
	m_parent = parent;
}

void RegisterServices::RegisterServicesBody::ServiceList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::setParent(ServiceList* parent)
{
	m_parent = parent;
}

void RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jVariableLengthString RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::getURI()
{
	return m_URI;
}

int RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::setURI(jVariableLengthString value)
{
	if ( value.length() > 255)
	{
		return 1;
	}
	
	m_URI = value;
	if (m_URI.length() < 0)
	{
		m_URI.resize(0);
	}
	setParentPresenceVector();
	return 0;
}

jUnsignedByte RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::getMajorVersionNumber()
{
	return m_MajorVersionNumber;
}

int RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::setMajorVersionNumber(jUnsignedByte value)
{
	m_MajorVersionNumber = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::getMinorVersionNumber()
{
	return m_MinorVersionNumber;
}

int RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::setMinorVersionNumber(jUnsignedByte value)
{
	m_MinorVersionNumber = value;
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
const unsigned int RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += m_URI.length();
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	
	jUnsignedByte m_URILength = 0;
	m_URILength = JSIDL_v_1_0::correctEndianness(m_URI.length());
	memcpy(bytes+pos, (unsigned char*)&m_URILength, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	
	memcpy(bytes+pos, m_URI.c_str(), m_URI.length());
	pos += m_URI.length();
	jUnsignedByte m_MajorVersionNumberTemp;
	
	m_MajorVersionNumberTemp = JSIDL_v_1_0::correctEndianness(m_MajorVersionNumber);
	memcpy(bytes + pos, &m_MajorVersionNumberTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_MinorVersionNumberTemp;
	
	m_MinorVersionNumberTemp = JSIDL_v_1_0::correctEndianness(m_MinorVersionNumber);
	memcpy(bytes + pos, &m_MinorVersionNumberTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	
	jUnsignedByte m_URILength = 0;
	memcpy((unsigned char*)&m_URILength, bytes+pos, sizeof( m_URILength ));
	m_URILength = JSIDL_v_1_0::correctEndianness(m_URILength);
	pos += sizeof( m_URILength );
	
	char* m_URITemp = new char[m_URILength+1];
	memcpy(m_URITemp, bytes+pos, m_URILength );
	m_URITemp[m_URILength] = '\0';
	m_URI = m_URITemp;
	pos += m_URILength ;
	delete m_URITemp;
	jUnsignedByte m_MajorVersionNumberTemp;
	
	memcpy(&m_MajorVersionNumberTemp, bytes + pos, sizeof(jUnsignedByte));
	m_MajorVersionNumber = JSIDL_v_1_0::correctEndianness(m_MajorVersionNumberTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_MinorVersionNumberTemp;
	
	memcpy(&m_MinorVersionNumberTemp, bytes + pos, sizeof(jUnsignedByte));
	m_MinorVersionNumber = JSIDL_v_1_0::correctEndianness(m_MinorVersionNumberTemp);
	pos += sizeof(jUnsignedByte);
}

RegisterServices::RegisterServicesBody::ServiceList::ServiceRec &RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::operator=(const ServiceRec &value)
{
	m_URI = value.m_URI;
	m_MajorVersionNumber = value.m_MajorVersionNumber;
	m_MinorVersionNumber = value.m_MinorVersionNumber;
	
	return *this;
}

bool RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::operator==(const ServiceRec &value) const
{
	if ((m_URI.length() != value.m_URI.length()) || (m_URI.compare(value.m_URI) != 0))
	{
		return false;
	}
	if (m_MajorVersionNumber != value.m_MajorVersionNumber)
	{
		return false;
	}
	if (m_MinorVersionNumber != value.m_MinorVersionNumber)
	{
		return false;
	}
	
	return true;
}

bool RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::operator!=(const ServiceRec &value) const
{
	return !((*this) == value);
}

RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::ServiceRec()
{
	m_parent = NULL;
	m_MajorVersionNumber = 0;
	m_MinorVersionNumber = 0;
}

RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::ServiceRec(const ServiceRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MajorVersionNumber = 0;
	m_MinorVersionNumber = 0;
	
	/// Copy the values
	m_URI = value.m_URI;
	m_MajorVersionNumber = value.m_MajorVersionNumber;
	m_MinorVersionNumber = value.m_MinorVersionNumber;
}

RegisterServices::RegisterServicesBody::ServiceList::ServiceRec::~ServiceRec()
{
}

unsigned int RegisterServices::RegisterServicesBody::ServiceList::getNumberOfElements() const
{
	return (unsigned int) m_ServiceRec.size();
}

RegisterServices::RegisterServicesBody::ServiceList::ServiceRec* const RegisterServices::RegisterServicesBody::ServiceList::getElement(unsigned int index)
{
	return &m_ServiceRec.at(index);
}

int RegisterServices::RegisterServicesBody::ServiceList::setElement(unsigned int index, const ServiceRec &value)
{
	if(m_ServiceRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ServiceRec.at(index) = value;
	m_ServiceRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int RegisterServices::RegisterServicesBody::ServiceList::addElement(const ServiceRec &value)
{
	m_ServiceRec.push_back(value);
	m_ServiceRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int RegisterServices::RegisterServicesBody::ServiceList::deleteElement(unsigned int index)
{
	if(m_ServiceRec.size()-1 < index)
	{
		return 1;
	}
	
	m_ServiceRec.erase(m_ServiceRec.begin()+index);
	return 0;
}

int RegisterServices::RegisterServicesBody::ServiceList::deleteLastElement()
{
	m_ServiceRec.pop_back();
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
const unsigned int RegisterServices::RegisterServicesBody::ServiceList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		size += m_ServiceRec[i].getSize();
	}
	
	return size;
}

void RegisterServices::RegisterServicesBody::ServiceList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_ServiceRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		m_ServiceRec[i].encode(bytes + pos);
		pos += m_ServiceRec[i].getSize();
	}
}

void RegisterServices::RegisterServicesBody::ServiceList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_ServiceRec.resize(size);
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		m_ServiceRec[i].decode(bytes + pos);
		pos += m_ServiceRec[i].getSize();
	}
}

RegisterServices::RegisterServicesBody::ServiceList &RegisterServices::RegisterServicesBody::ServiceList::operator=(const ServiceList &value)
{
	m_ServiceRec.clear();
	
	for (int i = 0; i < value.m_ServiceRec.size(); i++)
	{
		m_ServiceRec.push_back(value.m_ServiceRec[i]);
		m_ServiceRec[i].setParent(this);
	}
	
	return *this;
}

bool RegisterServices::RegisterServicesBody::ServiceList::operator==(const ServiceList &value) const
{
	for (int i = 0; i < m_ServiceRec.size(); i++)
	{
		if (m_ServiceRec[i] !=  value.m_ServiceRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool RegisterServices::RegisterServicesBody::ServiceList::operator!=(const ServiceList &value) const
{
	return !((*this) == value);
}

RegisterServices::RegisterServicesBody::ServiceList::ServiceList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ServiceRec.size(); i++)
	{
		m_ServiceRec[i].setParent(this);
	}
	/// No Initialization of m_ServiceRec necessary.
}

RegisterServices::RegisterServicesBody::ServiceList::ServiceList(const ServiceList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_ServiceRec.size(); i++)
	{
		m_ServiceRec[i].setParent(this);
	}
	/// No Initialization of m_ServiceRec necessary.
	
	/// Copy the values
	m_ServiceRec.clear();
	
	for (int i = 0; i < value.m_ServiceRec.size(); i++)
	{
		m_ServiceRec.push_back(value.m_ServiceRec[i]);
		m_ServiceRec[i].setParent(this);
	}
}

RegisterServices::RegisterServicesBody::ServiceList::~ServiceList()
{
}

RegisterServices::RegisterServicesBody::ServiceList* const RegisterServices::RegisterServicesBody::getServiceList()
{
	return &m_ServiceList;
}

int RegisterServices::RegisterServicesBody::setServiceList(const ServiceList &value)
{
	m_ServiceList = value;
	setParentPresenceVector();
	return 0;
}

void RegisterServices::RegisterServicesBody::setParentPresenceVector()
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
const unsigned int RegisterServices::RegisterServicesBody::getSize()
{
	unsigned int size = 0;
	
	size += m_ServiceList.getSize();
	
	return size;
}

void RegisterServices::RegisterServicesBody::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ServiceList.encode(bytes + pos);
	pos += m_ServiceList.getSize();
}

void RegisterServices::RegisterServicesBody::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ServiceList.decode(bytes + pos);
	pos += m_ServiceList.getSize();
}

RegisterServices::RegisterServicesBody &RegisterServices::RegisterServicesBody::operator=(const RegisterServicesBody &value)
{
	m_ServiceList = value.m_ServiceList;
	m_ServiceList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool RegisterServices::RegisterServicesBody::operator==(const RegisterServicesBody &value) const
{
	if (m_ServiceList != value.m_ServiceList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool RegisterServices::RegisterServicesBody::operator!=(const RegisterServicesBody &value) const
{
	return !((*this) == value);
}

RegisterServices::RegisterServicesBody::RegisterServicesBody()
{
	m_ServiceList.setParent(this);
	/// No Initialization of m_ServiceList necessary.
}

RegisterServices::RegisterServicesBody::RegisterServicesBody(const RegisterServicesBody &value)
{
	/// Initiliaze the protected variables
	m_ServiceList.setParent(this);
	/// No Initialization of m_ServiceList necessary.
	
	/// Copy the values
	m_ServiceList = value.m_ServiceList;
	m_ServiceList.setParent(this);
	/// This code is currently not supported
}

RegisterServices::RegisterServicesBody::~RegisterServicesBody()
{
}

RegisterServices::RegisterServicesBody* const RegisterServices::getRegisterServicesBody()
{
	return &m_RegisterServicesBody;
}

int RegisterServices::setRegisterServicesBody(const RegisterServicesBody &value)
{
	m_RegisterServicesBody = value;
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
const unsigned int RegisterServices::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_RegisterServicesBody.getSize();
	
	return size;
}

void RegisterServices::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AppHeader.encode(bytes + pos);
	pos += m_AppHeader.getSize();
	m_RegisterServicesBody.encode(bytes + pos);
	pos += m_RegisterServicesBody.getSize();
}

void RegisterServices::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AppHeader.decode(bytes + pos);
	pos += m_AppHeader.getSize();
	m_RegisterServicesBody.decode(bytes + pos);
	pos += m_RegisterServicesBody.getSize();
}

RegisterServices &RegisterServices::operator=(const RegisterServices &value)
{
	m_AppHeader = value.m_AppHeader;
	m_RegisterServicesBody = value.m_RegisterServicesBody;
	
	return *this;
}

bool RegisterServices::operator==(const RegisterServices &value) const
{
	if (m_AppHeader != value.m_AppHeader)
	{
		return false;
	}
	if (m_RegisterServicesBody != value.m_RegisterServicesBody)
	{
		return false;
	}
	
	return true;
}

bool RegisterServices::operator!=(const RegisterServices &value) const
{
	return !((*this) == value);
}

RegisterServices::RegisterServices()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_RegisterServicesBody necessary.
	m_IsCommand = true;
}

RegisterServices::RegisterServices(const RegisterServices &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_RegisterServicesBody necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_RegisterServicesBody = value.m_RegisterServicesBody;
}

RegisterServices::~RegisterServices()
{
}


}
