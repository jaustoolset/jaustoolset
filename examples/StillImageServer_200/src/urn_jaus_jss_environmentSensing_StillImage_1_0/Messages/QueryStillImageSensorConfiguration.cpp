#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/QueryStillImageSensorConfiguration.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void QueryStillImageSensorConfiguration::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryStillImageSensorConfiguration::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryStillImageSensorConfiguration::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryStillImageSensorConfiguration::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryStillImageSensorConfiguration::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryStillImageSensorConfiguration::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryStillImageSensorConfiguration::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryStillImageSensorConfiguration::AppHeader::HeaderRec &QueryStillImageSensorConfiguration::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryStillImageSensorConfiguration::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryStillImageSensorConfiguration::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorConfiguration::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2813;
}

QueryStillImageSensorConfiguration::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2813;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryStillImageSensorConfiguration::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryStillImageSensorConfiguration::AppHeader::HeaderRec* const QueryStillImageSensorConfiguration::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryStillImageSensorConfiguration::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryStillImageSensorConfiguration::AppHeader::setParentPresenceVector()
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
const unsigned int QueryStillImageSensorConfiguration::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryStillImageSensorConfiguration::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryStillImageSensorConfiguration::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryStillImageSensorConfiguration::AppHeader &QueryStillImageSensorConfiguration::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryStillImageSensorConfiguration::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryStillImageSensorConfiguration::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorConfiguration::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryStillImageSensorConfiguration::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryStillImageSensorConfiguration::AppHeader::~AppHeader()
{
}

QueryStillImageSensorConfiguration::AppHeader* const QueryStillImageSensorConfiguration::getAppHeader()
{
	return &m_AppHeader;
}

int QueryStillImageSensorConfiguration::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::setParent(QueryStillImageSensorConfigurationList* parent)
{
	m_parent = parent;
}

void QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::getSensorID()
{
	return m_SensorID;
}

int QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::getQueryPresenceVector()
{
	return m_QueryPresenceVector;
}

int QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::setQueryPresenceVector(jUnsignedByte value)
{
	m_QueryPresenceVector = value;
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
const unsigned int QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::encode(unsigned char *bytes)
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
	jUnsignedByte m_QueryPresenceVectorTemp;
	
	m_QueryPresenceVectorTemp = JSIDL_v_1_0::correctEndianness(m_QueryPresenceVector);
	memcpy(bytes + pos, &m_QueryPresenceVectorTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::decode(const unsigned char *bytes)
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
	jUnsignedByte m_QueryPresenceVectorTemp;
	
	memcpy(&m_QueryPresenceVectorTemp, bytes + pos, sizeof(jUnsignedByte));
	m_QueryPresenceVector = JSIDL_v_1_0::correctEndianness(m_QueryPresenceVectorTemp);
	pos += sizeof(jUnsignedByte);
}

QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec &QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::operator=(const QueryStillImageSensorConfigurationRec &value)
{
	m_SensorID = value.m_SensorID;
	m_QueryPresenceVector = value.m_QueryPresenceVector;
	
	return *this;
}

bool QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::operator==(const QueryStillImageSensorConfigurationRec &value) const
{
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	if (m_QueryPresenceVector != value.m_QueryPresenceVector)
	{
		return false;
	}
	
	return true;
}

bool QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::operator!=(const QueryStillImageSensorConfigurationRec &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::QueryStillImageSensorConfigurationRec()
{
	m_parent = NULL;
	m_SensorID = 0;
	m_QueryPresenceVector = 0;
}

QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::QueryStillImageSensorConfigurationRec(const QueryStillImageSensorConfigurationRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	m_QueryPresenceVector = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
	m_QueryPresenceVector = value.m_QueryPresenceVector;
}

QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec::~QueryStillImageSensorConfigurationRec()
{
}

unsigned int QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::getNumberOfElements() const
{
	return (unsigned int) m_QueryStillImageSensorConfigurationRec.size();
}

QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationRec* const QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::getElement(unsigned int index)
{
	return &m_QueryStillImageSensorConfigurationRec.at(index);
}

int QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::setElement(unsigned int index, const QueryStillImageSensorConfigurationRec &value)
{
	if(m_QueryStillImageSensorConfigurationRec.size()-1 < index)
	{
		return 1;
	}
	
	m_QueryStillImageSensorConfigurationRec.at(index) = value;
	m_QueryStillImageSensorConfigurationRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::addElement(const QueryStillImageSensorConfigurationRec &value)
{
	m_QueryStillImageSensorConfigurationRec.push_back(value);
	m_QueryStillImageSensorConfigurationRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::deleteElement(unsigned int index)
{
	if(m_QueryStillImageSensorConfigurationRec.size()-1 < index)
	{
		return 1;
	}
	
	m_QueryStillImageSensorConfigurationRec.erase(m_QueryStillImageSensorConfigurationRec.begin()+index);
	return 0;
}

int QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::deleteLastElement()
{
	m_QueryStillImageSensorConfigurationRec.pop_back();
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
const unsigned int QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_QueryStillImageSensorConfigurationRec.size(); i++)
	{
		size += m_QueryStillImageSensorConfigurationRec[i].getSize();
	}
	
	return size;
}

void QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_QueryStillImageSensorConfigurationRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_QueryStillImageSensorConfigurationRec.size(); i++)
	{
		m_QueryStillImageSensorConfigurationRec[i].encode(bytes + pos);
		pos += m_QueryStillImageSensorConfigurationRec[i].getSize();
	}
}

void QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_QueryStillImageSensorConfigurationRec.resize(size);
	for (int i = 0; i < m_QueryStillImageSensorConfigurationRec.size(); i++)
	{
		m_QueryStillImageSensorConfigurationRec[i].decode(bytes + pos);
		pos += m_QueryStillImageSensorConfigurationRec[i].getSize();
	}
}

QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList &QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::operator=(const QueryStillImageSensorConfigurationList &value)
{
	m_QueryStillImageSensorConfigurationRec.clear();
	
	for (int i = 0; i < value.m_QueryStillImageSensorConfigurationRec.size(); i++)
	{
		m_QueryStillImageSensorConfigurationRec.push_back(value.m_QueryStillImageSensorConfigurationRec[i]);
		m_QueryStillImageSensorConfigurationRec[i].setParent(this);
	}
	
	return *this;
}

bool QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::operator==(const QueryStillImageSensorConfigurationList &value) const
{
	for (int i = 0; i < m_QueryStillImageSensorConfigurationRec.size(); i++)
	{
		if (m_QueryStillImageSensorConfigurationRec[i] !=  value.m_QueryStillImageSensorConfigurationRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::operator!=(const QueryStillImageSensorConfigurationList &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_QueryStillImageSensorConfigurationRec.size(); i++)
	{
		m_QueryStillImageSensorConfigurationRec[i].setParent(this);
	}
	/// No Initialization of m_QueryStillImageSensorConfigurationRec necessary.
}

QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::QueryStillImageSensorConfigurationList(const QueryStillImageSensorConfigurationList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_QueryStillImageSensorConfigurationRec.size(); i++)
	{
		m_QueryStillImageSensorConfigurationRec[i].setParent(this);
	}
	/// No Initialization of m_QueryStillImageSensorConfigurationRec necessary.
	
	/// Copy the values
	m_QueryStillImageSensorConfigurationRec.clear();
	
	for (int i = 0; i < value.m_QueryStillImageSensorConfigurationRec.size(); i++)
	{
		m_QueryStillImageSensorConfigurationRec.push_back(value.m_QueryStillImageSensorConfigurationRec[i]);
		m_QueryStillImageSensorConfigurationRec[i].setParent(this);
	}
}

QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList::~QueryStillImageSensorConfigurationList()
{
}

QueryStillImageSensorConfiguration::Body::QueryStillImageSensorConfigurationList* const QueryStillImageSensorConfiguration::Body::getQueryStillImageSensorConfigurationList()
{
	return &m_QueryStillImageSensorConfigurationList;
}

int QueryStillImageSensorConfiguration::Body::setQueryStillImageSensorConfigurationList(const QueryStillImageSensorConfigurationList &value)
{
	m_QueryStillImageSensorConfigurationList = value;
	setParentPresenceVector();
	return 0;
}

void QueryStillImageSensorConfiguration::Body::setParentPresenceVector()
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
const unsigned int QueryStillImageSensorConfiguration::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryStillImageSensorConfigurationList.getSize();
	
	return size;
}

void QueryStillImageSensorConfiguration::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryStillImageSensorConfigurationList.encode(bytes + pos);
	pos += m_QueryStillImageSensorConfigurationList.getSize();
}

void QueryStillImageSensorConfiguration::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryStillImageSensorConfigurationList.decode(bytes + pos);
	pos += m_QueryStillImageSensorConfigurationList.getSize();
}

QueryStillImageSensorConfiguration::Body &QueryStillImageSensorConfiguration::Body::operator=(const Body &value)
{
	m_QueryStillImageSensorConfigurationList = value.m_QueryStillImageSensorConfigurationList;
	m_QueryStillImageSensorConfigurationList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryStillImageSensorConfiguration::Body::operator==(const Body &value) const
{
	if (m_QueryStillImageSensorConfigurationList != value.m_QueryStillImageSensorConfigurationList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryStillImageSensorConfiguration::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorConfiguration::Body::Body()
{
	m_QueryStillImageSensorConfigurationList.setParent(this);
	/// No Initialization of m_QueryStillImageSensorConfigurationList necessary.
}

QueryStillImageSensorConfiguration::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryStillImageSensorConfigurationList.setParent(this);
	/// No Initialization of m_QueryStillImageSensorConfigurationList necessary.
	
	/// Copy the values
	m_QueryStillImageSensorConfigurationList = value.m_QueryStillImageSensorConfigurationList;
	m_QueryStillImageSensorConfigurationList.setParent(this);
	/// This code is currently not supported
}

QueryStillImageSensorConfiguration::Body::~Body()
{
}

QueryStillImageSensorConfiguration::Body* const QueryStillImageSensorConfiguration::getBody()
{
	return &m_Body;
}

int QueryStillImageSensorConfiguration::setBody(const Body &value)
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
const unsigned int QueryStillImageSensorConfiguration::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryStillImageSensorConfiguration::encode(unsigned char *bytes)
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

void QueryStillImageSensorConfiguration::decode(const unsigned char *bytes)
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

QueryStillImageSensorConfiguration &QueryStillImageSensorConfiguration::operator=(const QueryStillImageSensorConfiguration &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryStillImageSensorConfiguration::operator==(const QueryStillImageSensorConfiguration &value) const
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

bool QueryStillImageSensorConfiguration::operator!=(const QueryStillImageSensorConfiguration &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorConfiguration::QueryStillImageSensorConfiguration()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryStillImageSensorConfiguration::QueryStillImageSensorConfiguration(const QueryStillImageSensorConfiguration &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryStillImageSensorConfiguration::~QueryStillImageSensorConfiguration()
{
}


}
