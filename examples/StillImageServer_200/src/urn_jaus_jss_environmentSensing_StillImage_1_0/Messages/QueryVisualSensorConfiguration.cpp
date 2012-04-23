#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/QueryVisualSensorConfiguration.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void QueryVisualSensorConfiguration::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryVisualSensorConfiguration::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryVisualSensorConfiguration::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryVisualSensorConfiguration::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryVisualSensorConfiguration::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryVisualSensorConfiguration::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryVisualSensorConfiguration::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryVisualSensorConfiguration::AppHeader::HeaderRec &QueryVisualSensorConfiguration::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryVisualSensorConfiguration::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryVisualSensorConfiguration::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryVisualSensorConfiguration::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2807;
}

QueryVisualSensorConfiguration::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2807;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryVisualSensorConfiguration::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryVisualSensorConfiguration::AppHeader::HeaderRec* const QueryVisualSensorConfiguration::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryVisualSensorConfiguration::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryVisualSensorConfiguration::AppHeader::setParentPresenceVector()
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
const unsigned int QueryVisualSensorConfiguration::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryVisualSensorConfiguration::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryVisualSensorConfiguration::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryVisualSensorConfiguration::AppHeader &QueryVisualSensorConfiguration::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryVisualSensorConfiguration::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryVisualSensorConfiguration::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryVisualSensorConfiguration::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryVisualSensorConfiguration::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryVisualSensorConfiguration::AppHeader::~AppHeader()
{
}

QueryVisualSensorConfiguration::AppHeader* const QueryVisualSensorConfiguration::getAppHeader()
{
	return &m_AppHeader;
}

int QueryVisualSensorConfiguration::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::setParent(QueryVisualSensorConfigurationList* parent)
{
	m_parent = parent;
}

void QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::getSensorID()
{
	return m_SensorID;
}

int QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedShortInteger QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::getQueryPresenceVector()
{
	return m_QueryPresenceVector;
}

int QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::setQueryPresenceVector(jUnsignedShortInteger value)
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
const unsigned int QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::encode(unsigned char *bytes)
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
	jUnsignedShortInteger m_QueryPresenceVectorTemp;
	
	m_QueryPresenceVectorTemp = JSIDL_v_1_0::correctEndianness(m_QueryPresenceVector);
	memcpy(bytes + pos, &m_QueryPresenceVectorTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::decode(const unsigned char *bytes)
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
	jUnsignedShortInteger m_QueryPresenceVectorTemp;
	
	memcpy(&m_QueryPresenceVectorTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_QueryPresenceVector = JSIDL_v_1_0::correctEndianness(m_QueryPresenceVectorTemp);
	pos += sizeof(jUnsignedShortInteger);
}

QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec &QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::operator=(const QueryVisualSensorConfigurationRec &value)
{
	m_SensorID = value.m_SensorID;
	m_QueryPresenceVector = value.m_QueryPresenceVector;
	
	return *this;
}

bool QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::operator==(const QueryVisualSensorConfigurationRec &value) const
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

bool QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::operator!=(const QueryVisualSensorConfigurationRec &value) const
{
	return !((*this) == value);
}

QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::QueryVisualSensorConfigurationRec()
{
	m_parent = NULL;
	m_SensorID = 0;
	m_QueryPresenceVector = 0;
}

QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::QueryVisualSensorConfigurationRec(const QueryVisualSensorConfigurationRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	m_QueryPresenceVector = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
	m_QueryPresenceVector = value.m_QueryPresenceVector;
}

QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec::~QueryVisualSensorConfigurationRec()
{
}

unsigned int QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::getNumberOfElements() const
{
	return (unsigned int) m_QueryVisualSensorConfigurationRec.size();
}

QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationRec* const QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::getElement(unsigned int index)
{
	return &m_QueryVisualSensorConfigurationRec.at(index);
}

int QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::setElement(unsigned int index, const QueryVisualSensorConfigurationRec &value)
{
	if(m_QueryVisualSensorConfigurationRec.size()-1 < index)
	{
		return 1;
	}
	
	m_QueryVisualSensorConfigurationRec.at(index) = value;
	m_QueryVisualSensorConfigurationRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::addElement(const QueryVisualSensorConfigurationRec &value)
{
	m_QueryVisualSensorConfigurationRec.push_back(value);
	m_QueryVisualSensorConfigurationRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::deleteElement(unsigned int index)
{
	if(m_QueryVisualSensorConfigurationRec.size()-1 < index)
	{
		return 1;
	}
	
	m_QueryVisualSensorConfigurationRec.erase(m_QueryVisualSensorConfigurationRec.begin()+index);
	return 0;
}

int QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::deleteLastElement()
{
	m_QueryVisualSensorConfigurationRec.pop_back();
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
const unsigned int QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_QueryVisualSensorConfigurationRec.size(); i++)
	{
		size += m_QueryVisualSensorConfigurationRec[i].getSize();
	}
	
	return size;
}

void QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_QueryVisualSensorConfigurationRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_QueryVisualSensorConfigurationRec.size(); i++)
	{
		m_QueryVisualSensorConfigurationRec[i].encode(bytes + pos);
		pos += m_QueryVisualSensorConfigurationRec[i].getSize();
	}
}

void QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_QueryVisualSensorConfigurationRec.resize(size);
	for (int i = 0; i < m_QueryVisualSensorConfigurationRec.size(); i++)
	{
		m_QueryVisualSensorConfigurationRec[i].decode(bytes + pos);
		pos += m_QueryVisualSensorConfigurationRec[i].getSize();
	}
}

QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList &QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::operator=(const QueryVisualSensorConfigurationList &value)
{
	m_QueryVisualSensorConfigurationRec.clear();
	
	for (int i = 0; i < value.m_QueryVisualSensorConfigurationRec.size(); i++)
	{
		m_QueryVisualSensorConfigurationRec.push_back(value.m_QueryVisualSensorConfigurationRec[i]);
		m_QueryVisualSensorConfigurationRec[i].setParent(this);
	}
	
	return *this;
}

bool QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::operator==(const QueryVisualSensorConfigurationList &value) const
{
	for (int i = 0; i < m_QueryVisualSensorConfigurationRec.size(); i++)
	{
		if (m_QueryVisualSensorConfigurationRec[i] !=  value.m_QueryVisualSensorConfigurationRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::operator!=(const QueryVisualSensorConfigurationList &value) const
{
	return !((*this) == value);
}

QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_QueryVisualSensorConfigurationRec.size(); i++)
	{
		m_QueryVisualSensorConfigurationRec[i].setParent(this);
	}
	/// No Initialization of m_QueryVisualSensorConfigurationRec necessary.
}

QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::QueryVisualSensorConfigurationList(const QueryVisualSensorConfigurationList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_QueryVisualSensorConfigurationRec.size(); i++)
	{
		m_QueryVisualSensorConfigurationRec[i].setParent(this);
	}
	/// No Initialization of m_QueryVisualSensorConfigurationRec necessary.
	
	/// Copy the values
	m_QueryVisualSensorConfigurationRec.clear();
	
	for (int i = 0; i < value.m_QueryVisualSensorConfigurationRec.size(); i++)
	{
		m_QueryVisualSensorConfigurationRec.push_back(value.m_QueryVisualSensorConfigurationRec[i]);
		m_QueryVisualSensorConfigurationRec[i].setParent(this);
	}
}

QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList::~QueryVisualSensorConfigurationList()
{
}

QueryVisualSensorConfiguration::Body::QueryVisualSensorConfigurationList* const QueryVisualSensorConfiguration::Body::getQueryVisualSensorConfigurationList()
{
	return &m_QueryVisualSensorConfigurationList;
}

int QueryVisualSensorConfiguration::Body::setQueryVisualSensorConfigurationList(const QueryVisualSensorConfigurationList &value)
{
	m_QueryVisualSensorConfigurationList = value;
	setParentPresenceVector();
	return 0;
}

void QueryVisualSensorConfiguration::Body::setParentPresenceVector()
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
const unsigned int QueryVisualSensorConfiguration::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryVisualSensorConfigurationList.getSize();
	
	return size;
}

void QueryVisualSensorConfiguration::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryVisualSensorConfigurationList.encode(bytes + pos);
	pos += m_QueryVisualSensorConfigurationList.getSize();
}

void QueryVisualSensorConfiguration::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryVisualSensorConfigurationList.decode(bytes + pos);
	pos += m_QueryVisualSensorConfigurationList.getSize();
}

QueryVisualSensorConfiguration::Body &QueryVisualSensorConfiguration::Body::operator=(const Body &value)
{
	m_QueryVisualSensorConfigurationList = value.m_QueryVisualSensorConfigurationList;
	m_QueryVisualSensorConfigurationList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryVisualSensorConfiguration::Body::operator==(const Body &value) const
{
	if (m_QueryVisualSensorConfigurationList != value.m_QueryVisualSensorConfigurationList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryVisualSensorConfiguration::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryVisualSensorConfiguration::Body::Body()
{
	m_QueryVisualSensorConfigurationList.setParent(this);
	/// No Initialization of m_QueryVisualSensorConfigurationList necessary.
}

QueryVisualSensorConfiguration::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryVisualSensorConfigurationList.setParent(this);
	/// No Initialization of m_QueryVisualSensorConfigurationList necessary.
	
	/// Copy the values
	m_QueryVisualSensorConfigurationList = value.m_QueryVisualSensorConfigurationList;
	m_QueryVisualSensorConfigurationList.setParent(this);
	/// This code is currently not supported
}

QueryVisualSensorConfiguration::Body::~Body()
{
}

QueryVisualSensorConfiguration::Body* const QueryVisualSensorConfiguration::getBody()
{
	return &m_Body;
}

int QueryVisualSensorConfiguration::setBody(const Body &value)
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
const unsigned int QueryVisualSensorConfiguration::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryVisualSensorConfiguration::encode(unsigned char *bytes)
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

void QueryVisualSensorConfiguration::decode(const unsigned char *bytes)
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

QueryVisualSensorConfiguration &QueryVisualSensorConfiguration::operator=(const QueryVisualSensorConfiguration &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryVisualSensorConfiguration::operator==(const QueryVisualSensorConfiguration &value) const
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

bool QueryVisualSensorConfiguration::operator!=(const QueryVisualSensorConfiguration &value) const
{
	return !((*this) == value);
}

QueryVisualSensorConfiguration::QueryVisualSensorConfiguration()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryVisualSensorConfiguration::QueryVisualSensorConfiguration(const QueryVisualSensorConfiguration &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryVisualSensorConfiguration::~QueryVisualSensorConfiguration()
{
}


}
