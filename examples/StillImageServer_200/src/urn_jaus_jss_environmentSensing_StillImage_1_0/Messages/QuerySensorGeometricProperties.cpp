#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/QuerySensorGeometricProperties.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void QuerySensorGeometricProperties::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QuerySensorGeometricProperties::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QuerySensorGeometricProperties::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QuerySensorGeometricProperties::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QuerySensorGeometricProperties::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QuerySensorGeometricProperties::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QuerySensorGeometricProperties::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QuerySensorGeometricProperties::AppHeader::HeaderRec &QuerySensorGeometricProperties::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QuerySensorGeometricProperties::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QuerySensorGeometricProperties::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QuerySensorGeometricProperties::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2805;
}

QuerySensorGeometricProperties::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2805;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QuerySensorGeometricProperties::AppHeader::HeaderRec::~HeaderRec()
{
}

QuerySensorGeometricProperties::AppHeader::HeaderRec* const QuerySensorGeometricProperties::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QuerySensorGeometricProperties::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QuerySensorGeometricProperties::AppHeader::setParentPresenceVector()
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
const unsigned int QuerySensorGeometricProperties::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QuerySensorGeometricProperties::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QuerySensorGeometricProperties::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QuerySensorGeometricProperties::AppHeader &QuerySensorGeometricProperties::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QuerySensorGeometricProperties::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QuerySensorGeometricProperties::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QuerySensorGeometricProperties::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QuerySensorGeometricProperties::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QuerySensorGeometricProperties::AppHeader::~AppHeader()
{
}

QuerySensorGeometricProperties::AppHeader* const QuerySensorGeometricProperties::getAppHeader()
{
	return &m_AppHeader;
}

int QuerySensorGeometricProperties::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QuerySensorGeometricProperties::Body::SensorIdList::setParent(Body* parent)
{
	m_parent = parent;
}

void QuerySensorGeometricProperties::Body::SensorIdList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::setParent(SensorIdList* parent)
{
	m_parent = parent;
}

void QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::getSensorID()
{
	return m_SensorID;
}

int QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
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
const unsigned int QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	m_SensorIDTemp = JSIDL_v_1_0::correctEndianness(m_SensorID);
	memcpy(bytes + pos, &m_SensorIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SensorIDTemp;
	
	memcpy(&m_SensorIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SensorID = JSIDL_v_1_0::correctEndianness(m_SensorIDTemp);
	pos += sizeof(jUnsignedShortInteger);
}

QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec &QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::operator=(const SensorIdRec &value)
{
	m_SensorID = value.m_SensorID;
	
	return *this;
}

bool QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::operator==(const SensorIdRec &value) const
{
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	
	return true;
}

bool QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::operator!=(const SensorIdRec &value) const
{
	return !((*this) == value);
}

QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::SensorIdRec()
{
	m_parent = NULL;
	m_SensorID = 0;
}

QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::SensorIdRec(const SensorIdRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
}

QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec::~SensorIdRec()
{
}

unsigned int QuerySensorGeometricProperties::Body::SensorIdList::getNumberOfElements() const
{
	return (unsigned int) m_SensorIdRec.size();
}

QuerySensorGeometricProperties::Body::SensorIdList::SensorIdRec* const QuerySensorGeometricProperties::Body::SensorIdList::getElement(unsigned int index)
{
	return &m_SensorIdRec.at(index);
}

int QuerySensorGeometricProperties::Body::SensorIdList::setElement(unsigned int index, const SensorIdRec &value)
{
	if(m_SensorIdRec.size()-1 < index)
	{
		return 1;
	}
	
	m_SensorIdRec.at(index) = value;
	m_SensorIdRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int QuerySensorGeometricProperties::Body::SensorIdList::addElement(const SensorIdRec &value)
{
	m_SensorIdRec.push_back(value);
	m_SensorIdRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int QuerySensorGeometricProperties::Body::SensorIdList::deleteElement(unsigned int index)
{
	if(m_SensorIdRec.size()-1 < index)
	{
		return 1;
	}
	
	m_SensorIdRec.erase(m_SensorIdRec.begin()+index);
	return 0;
}

int QuerySensorGeometricProperties::Body::SensorIdList::deleteLastElement()
{
	m_SensorIdRec.pop_back();
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
const unsigned int QuerySensorGeometricProperties::Body::SensorIdList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_SensorIdRec.size(); i++)
	{
		size += m_SensorIdRec[i].getSize();
	}
	
	return size;
}

void QuerySensorGeometricProperties::Body::SensorIdList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_SensorIdRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_SensorIdRec.size(); i++)
	{
		m_SensorIdRec[i].encode(bytes + pos);
		pos += m_SensorIdRec[i].getSize();
	}
}

void QuerySensorGeometricProperties::Body::SensorIdList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_SensorIdRec.resize(size);
	for (int i = 0; i < m_SensorIdRec.size(); i++)
	{
		m_SensorIdRec[i].decode(bytes + pos);
		pos += m_SensorIdRec[i].getSize();
	}
}

QuerySensorGeometricProperties::Body::SensorIdList &QuerySensorGeometricProperties::Body::SensorIdList::operator=(const SensorIdList &value)
{
	m_SensorIdRec.clear();
	
	for (int i = 0; i < value.m_SensorIdRec.size(); i++)
	{
		m_SensorIdRec.push_back(value.m_SensorIdRec[i]);
		m_SensorIdRec[i].setParent(this);
	}
	
	return *this;
}

bool QuerySensorGeometricProperties::Body::SensorIdList::operator==(const SensorIdList &value) const
{
	for (int i = 0; i < m_SensorIdRec.size(); i++)
	{
		if (m_SensorIdRec[i] !=  value.m_SensorIdRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QuerySensorGeometricProperties::Body::SensorIdList::operator!=(const SensorIdList &value) const
{
	return !((*this) == value);
}

QuerySensorGeometricProperties::Body::SensorIdList::SensorIdList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_SensorIdRec.size(); i++)
	{
		m_SensorIdRec[i].setParent(this);
	}
	/// No Initialization of m_SensorIdRec necessary.
}

QuerySensorGeometricProperties::Body::SensorIdList::SensorIdList(const SensorIdList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_SensorIdRec.size(); i++)
	{
		m_SensorIdRec[i].setParent(this);
	}
	/// No Initialization of m_SensorIdRec necessary.
	
	/// Copy the values
	m_SensorIdRec.clear();
	
	for (int i = 0; i < value.m_SensorIdRec.size(); i++)
	{
		m_SensorIdRec.push_back(value.m_SensorIdRec[i]);
		m_SensorIdRec[i].setParent(this);
	}
}

QuerySensorGeometricProperties::Body::SensorIdList::~SensorIdList()
{
}

QuerySensorGeometricProperties::Body::SensorIdList* const QuerySensorGeometricProperties::Body::getSensorIdList()
{
	return &m_SensorIdList;
}

int QuerySensorGeometricProperties::Body::setSensorIdList(const SensorIdList &value)
{
	m_SensorIdList = value;
	setParentPresenceVector();
	return 0;
}

void QuerySensorGeometricProperties::Body::setParentPresenceVector()
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
const unsigned int QuerySensorGeometricProperties::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_SensorIdList.getSize();
	
	return size;
}

void QuerySensorGeometricProperties::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SensorIdList.encode(bytes + pos);
	pos += m_SensorIdList.getSize();
}

void QuerySensorGeometricProperties::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SensorIdList.decode(bytes + pos);
	pos += m_SensorIdList.getSize();
}

QuerySensorGeometricProperties::Body &QuerySensorGeometricProperties::Body::operator=(const Body &value)
{
	m_SensorIdList = value.m_SensorIdList;
	m_SensorIdList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QuerySensorGeometricProperties::Body::operator==(const Body &value) const
{
	if (m_SensorIdList != value.m_SensorIdList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QuerySensorGeometricProperties::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QuerySensorGeometricProperties::Body::Body()
{
	m_SensorIdList.setParent(this);
	/// No Initialization of m_SensorIdList necessary.
}

QuerySensorGeometricProperties::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_SensorIdList.setParent(this);
	/// No Initialization of m_SensorIdList necessary.
	
	/// Copy the values
	m_SensorIdList = value.m_SensorIdList;
	m_SensorIdList.setParent(this);
	/// This code is currently not supported
}

QuerySensorGeometricProperties::Body::~Body()
{
}

QuerySensorGeometricProperties::Body* const QuerySensorGeometricProperties::getBody()
{
	return &m_Body;
}

int QuerySensorGeometricProperties::setBody(const Body &value)
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
const unsigned int QuerySensorGeometricProperties::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QuerySensorGeometricProperties::encode(unsigned char *bytes)
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

void QuerySensorGeometricProperties::decode(const unsigned char *bytes)
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

QuerySensorGeometricProperties &QuerySensorGeometricProperties::operator=(const QuerySensorGeometricProperties &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QuerySensorGeometricProperties::operator==(const QuerySensorGeometricProperties &value) const
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

bool QuerySensorGeometricProperties::operator!=(const QuerySensorGeometricProperties &value) const
{
	return !((*this) == value);
}

QuerySensorGeometricProperties::QuerySensorGeometricProperties()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QuerySensorGeometricProperties::QuerySensorGeometricProperties(const QuerySensorGeometricProperties &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QuerySensorGeometricProperties::~QuerySensorGeometricProperties()
{
}


}
