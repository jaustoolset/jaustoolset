#include "urn_jaus_jss_environmentSensing_VisualSensor_1_0/Messages/QueryVisualSensorCapabilities.h"

namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0
{

void QueryVisualSensorCapabilities::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryVisualSensorCapabilities::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryVisualSensorCapabilities::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryVisualSensorCapabilities::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryVisualSensorCapabilities::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryVisualSensorCapabilities::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryVisualSensorCapabilities::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryVisualSensorCapabilities::AppHeader::HeaderRec &QueryVisualSensorCapabilities::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryVisualSensorCapabilities::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryVisualSensorCapabilities::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryVisualSensorCapabilities::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2806;
}

QueryVisualSensorCapabilities::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2806;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryVisualSensorCapabilities::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryVisualSensorCapabilities::AppHeader::HeaderRec* const QueryVisualSensorCapabilities::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryVisualSensorCapabilities::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryVisualSensorCapabilities::AppHeader::setParentPresenceVector()
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
const unsigned int QueryVisualSensorCapabilities::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryVisualSensorCapabilities::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryVisualSensorCapabilities::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryVisualSensorCapabilities::AppHeader &QueryVisualSensorCapabilities::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryVisualSensorCapabilities::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryVisualSensorCapabilities::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryVisualSensorCapabilities::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryVisualSensorCapabilities::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryVisualSensorCapabilities::AppHeader::~AppHeader()
{
}

QueryVisualSensorCapabilities::AppHeader* const QueryVisualSensorCapabilities::getAppHeader()
{
	return &m_AppHeader;
}

int QueryVisualSensorCapabilities::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::setParent(QueryVisualSensorCapabilitiesList* parent)
{
	m_parent = parent;
}

void QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::getSensorID()
{
	return m_SensorID;
}

int QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedShortInteger QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::getQueryPresenceVector()
{
	return m_QueryPresenceVector;
}

int QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::setQueryPresenceVector(jUnsignedShortInteger value)
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
const unsigned int QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::encode(unsigned char *bytes)
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

void QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::decode(const unsigned char *bytes)
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

QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec &QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::operator=(const QueryVisualSensorCapabilitiesRec &value)
{
	m_SensorID = value.m_SensorID;
	m_QueryPresenceVector = value.m_QueryPresenceVector;
	
	return *this;
}

bool QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::operator==(const QueryVisualSensorCapabilitiesRec &value) const
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

bool QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::operator!=(const QueryVisualSensorCapabilitiesRec &value) const
{
	return !((*this) == value);
}

QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::QueryVisualSensorCapabilitiesRec()
{
	m_parent = NULL;
	m_SensorID = 0;
	m_QueryPresenceVector = 0;
}

QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::QueryVisualSensorCapabilitiesRec(const QueryVisualSensorCapabilitiesRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	m_QueryPresenceVector = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
	m_QueryPresenceVector = value.m_QueryPresenceVector;
}

QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec::~QueryVisualSensorCapabilitiesRec()
{
}

unsigned int QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::getNumberOfElements() const
{
	return (unsigned int) m_QueryVisualSensorCapabilitiesRec.size();
}

QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesRec* const QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::getElement(unsigned int index)
{
	return &m_QueryVisualSensorCapabilitiesRec.at(index);
}

int QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::setElement(unsigned int index, const QueryVisualSensorCapabilitiesRec &value)
{
	if(m_QueryVisualSensorCapabilitiesRec.size()-1 < index)
	{
		return 1;
	}
	
	m_QueryVisualSensorCapabilitiesRec.at(index) = value;
	m_QueryVisualSensorCapabilitiesRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::addElement(const QueryVisualSensorCapabilitiesRec &value)
{
	m_QueryVisualSensorCapabilitiesRec.push_back(value);
	m_QueryVisualSensorCapabilitiesRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::deleteElement(unsigned int index)
{
	if(m_QueryVisualSensorCapabilitiesRec.size()-1 < index)
	{
		return 1;
	}
	
	m_QueryVisualSensorCapabilitiesRec.erase(m_QueryVisualSensorCapabilitiesRec.begin()+index);
	return 0;
}

int QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::deleteLastElement()
{
	m_QueryVisualSensorCapabilitiesRec.pop_back();
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
const unsigned int QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_QueryVisualSensorCapabilitiesRec.size(); i++)
	{
		size += m_QueryVisualSensorCapabilitiesRec[i].getSize();
	}
	
	return size;
}

void QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_QueryVisualSensorCapabilitiesRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_QueryVisualSensorCapabilitiesRec.size(); i++)
	{
		m_QueryVisualSensorCapabilitiesRec[i].encode(bytes + pos);
		pos += m_QueryVisualSensorCapabilitiesRec[i].getSize();
	}
}

void QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_QueryVisualSensorCapabilitiesRec.resize(size);
	for (int i = 0; i < m_QueryVisualSensorCapabilitiesRec.size(); i++)
	{
		m_QueryVisualSensorCapabilitiesRec[i].decode(bytes + pos);
		pos += m_QueryVisualSensorCapabilitiesRec[i].getSize();
	}
}

QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList &QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::operator=(const QueryVisualSensorCapabilitiesList &value)
{
	m_QueryVisualSensorCapabilitiesRec.clear();
	
	for (int i = 0; i < value.m_QueryVisualSensorCapabilitiesRec.size(); i++)
	{
		m_QueryVisualSensorCapabilitiesRec.push_back(value.m_QueryVisualSensorCapabilitiesRec[i]);
		m_QueryVisualSensorCapabilitiesRec[i].setParent(this);
	}
	
	return *this;
}

bool QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::operator==(const QueryVisualSensorCapabilitiesList &value) const
{
	for (int i = 0; i < m_QueryVisualSensorCapabilitiesRec.size(); i++)
	{
		if (m_QueryVisualSensorCapabilitiesRec[i] !=  value.m_QueryVisualSensorCapabilitiesRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::operator!=(const QueryVisualSensorCapabilitiesList &value) const
{
	return !((*this) == value);
}

QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_QueryVisualSensorCapabilitiesRec.size(); i++)
	{
		m_QueryVisualSensorCapabilitiesRec[i].setParent(this);
	}
	/// No Initialization of m_QueryVisualSensorCapabilitiesRec necessary.
}

QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::QueryVisualSensorCapabilitiesList(const QueryVisualSensorCapabilitiesList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_QueryVisualSensorCapabilitiesRec.size(); i++)
	{
		m_QueryVisualSensorCapabilitiesRec[i].setParent(this);
	}
	/// No Initialization of m_QueryVisualSensorCapabilitiesRec necessary.
	
	/// Copy the values
	m_QueryVisualSensorCapabilitiesRec.clear();
	
	for (int i = 0; i < value.m_QueryVisualSensorCapabilitiesRec.size(); i++)
	{
		m_QueryVisualSensorCapabilitiesRec.push_back(value.m_QueryVisualSensorCapabilitiesRec[i]);
		m_QueryVisualSensorCapabilitiesRec[i].setParent(this);
	}
}

QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList::~QueryVisualSensorCapabilitiesList()
{
}

QueryVisualSensorCapabilities::Body::QueryVisualSensorCapabilitiesList* const QueryVisualSensorCapabilities::Body::getQueryVisualSensorCapabilitiesList()
{
	return &m_QueryVisualSensorCapabilitiesList;
}

int QueryVisualSensorCapabilities::Body::setQueryVisualSensorCapabilitiesList(const QueryVisualSensorCapabilitiesList &value)
{
	m_QueryVisualSensorCapabilitiesList = value;
	setParentPresenceVector();
	return 0;
}

void QueryVisualSensorCapabilities::Body::setParentPresenceVector()
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
const unsigned int QueryVisualSensorCapabilities::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryVisualSensorCapabilitiesList.getSize();
	
	return size;
}

void QueryVisualSensorCapabilities::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryVisualSensorCapabilitiesList.encode(bytes + pos);
	pos += m_QueryVisualSensorCapabilitiesList.getSize();
}

void QueryVisualSensorCapabilities::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryVisualSensorCapabilitiesList.decode(bytes + pos);
	pos += m_QueryVisualSensorCapabilitiesList.getSize();
}

QueryVisualSensorCapabilities::Body &QueryVisualSensorCapabilities::Body::operator=(const Body &value)
{
	m_QueryVisualSensorCapabilitiesList = value.m_QueryVisualSensorCapabilitiesList;
	m_QueryVisualSensorCapabilitiesList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryVisualSensorCapabilities::Body::operator==(const Body &value) const
{
	if (m_QueryVisualSensorCapabilitiesList != value.m_QueryVisualSensorCapabilitiesList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryVisualSensorCapabilities::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryVisualSensorCapabilities::Body::Body()
{
	m_QueryVisualSensorCapabilitiesList.setParent(this);
	/// No Initialization of m_QueryVisualSensorCapabilitiesList necessary.
}

QueryVisualSensorCapabilities::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryVisualSensorCapabilitiesList.setParent(this);
	/// No Initialization of m_QueryVisualSensorCapabilitiesList necessary.
	
	/// Copy the values
	m_QueryVisualSensorCapabilitiesList = value.m_QueryVisualSensorCapabilitiesList;
	m_QueryVisualSensorCapabilitiesList.setParent(this);
	/// This code is currently not supported
}

QueryVisualSensorCapabilities::Body::~Body()
{
}

QueryVisualSensorCapabilities::Body* const QueryVisualSensorCapabilities::getBody()
{
	return &m_Body;
}

int QueryVisualSensorCapabilities::setBody(const Body &value)
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
const unsigned int QueryVisualSensorCapabilities::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryVisualSensorCapabilities::encode(unsigned char *bytes)
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

void QueryVisualSensorCapabilities::decode(const unsigned char *bytes)
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

QueryVisualSensorCapabilities &QueryVisualSensorCapabilities::operator=(const QueryVisualSensorCapabilities &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryVisualSensorCapabilities::operator==(const QueryVisualSensorCapabilities &value) const
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

bool QueryVisualSensorCapabilities::operator!=(const QueryVisualSensorCapabilities &value) const
{
	return !((*this) == value);
}

QueryVisualSensorCapabilities::QueryVisualSensorCapabilities()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryVisualSensorCapabilities::QueryVisualSensorCapabilities(const QueryVisualSensorCapabilities &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryVisualSensorCapabilities::~QueryVisualSensorCapabilities()
{
}


}
