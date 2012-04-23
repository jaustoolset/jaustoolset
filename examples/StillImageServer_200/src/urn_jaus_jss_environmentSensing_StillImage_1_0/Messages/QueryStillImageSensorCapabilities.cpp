#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/QueryStillImageSensorCapabilities.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void QueryStillImageSensorCapabilities::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryStillImageSensorCapabilities::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryStillImageSensorCapabilities::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryStillImageSensorCapabilities::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryStillImageSensorCapabilities::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryStillImageSensorCapabilities::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryStillImageSensorCapabilities::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryStillImageSensorCapabilities::AppHeader::HeaderRec &QueryStillImageSensorCapabilities::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryStillImageSensorCapabilities::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryStillImageSensorCapabilities::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorCapabilities::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2812;
}

QueryStillImageSensorCapabilities::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2812;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryStillImageSensorCapabilities::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryStillImageSensorCapabilities::AppHeader::HeaderRec* const QueryStillImageSensorCapabilities::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryStillImageSensorCapabilities::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryStillImageSensorCapabilities::AppHeader::setParentPresenceVector()
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
const unsigned int QueryStillImageSensorCapabilities::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryStillImageSensorCapabilities::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryStillImageSensorCapabilities::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryStillImageSensorCapabilities::AppHeader &QueryStillImageSensorCapabilities::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryStillImageSensorCapabilities::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryStillImageSensorCapabilities::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorCapabilities::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryStillImageSensorCapabilities::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryStillImageSensorCapabilities::AppHeader::~AppHeader()
{
}

QueryStillImageSensorCapabilities::AppHeader* const QueryStillImageSensorCapabilities::getAppHeader()
{
	return &m_AppHeader;
}

int QueryStillImageSensorCapabilities::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::setParent(QueryStillImageSensorCapabilitiesList* parent)
{
	m_parent = parent;
}

void QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::getSensorID()
{
	return m_SensorID;
}

int QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::getQueryPresenceVector()
{
	return m_QueryPresenceVector;
}

int QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::setQueryPresenceVector(jUnsignedByte value)
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
const unsigned int QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::encode(unsigned char *bytes)
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

void QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::decode(const unsigned char *bytes)
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

QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec &QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::operator=(const QueryStillImageSensorCapabilitiesRec &value)
{
	m_SensorID = value.m_SensorID;
	m_QueryPresenceVector = value.m_QueryPresenceVector;
	
	return *this;
}

bool QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::operator==(const QueryStillImageSensorCapabilitiesRec &value) const
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

bool QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::operator!=(const QueryStillImageSensorCapabilitiesRec &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::QueryStillImageSensorCapabilitiesRec()
{
	m_parent = NULL;
	m_SensorID = 0;
	m_QueryPresenceVector = 0;
}

QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::QueryStillImageSensorCapabilitiesRec(const QueryStillImageSensorCapabilitiesRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	m_QueryPresenceVector = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
	m_QueryPresenceVector = value.m_QueryPresenceVector;
}

QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec::~QueryStillImageSensorCapabilitiesRec()
{
}

unsigned int QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::getNumberOfElements() const
{
	return (unsigned int) m_QueryStillImageSensorCapabilitiesRec.size();
}

QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesRec* const QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::getElement(unsigned int index)
{
	return &m_QueryStillImageSensorCapabilitiesRec.at(index);
}

int QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::setElement(unsigned int index, const QueryStillImageSensorCapabilitiesRec &value)
{
	if(m_QueryStillImageSensorCapabilitiesRec.size()-1 < index)
	{
		return 1;
	}
	
	m_QueryStillImageSensorCapabilitiesRec.at(index) = value;
	m_QueryStillImageSensorCapabilitiesRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::addElement(const QueryStillImageSensorCapabilitiesRec &value)
{
	m_QueryStillImageSensorCapabilitiesRec.push_back(value);
	m_QueryStillImageSensorCapabilitiesRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::deleteElement(unsigned int index)
{
	if(m_QueryStillImageSensorCapabilitiesRec.size()-1 < index)
	{
		return 1;
	}
	
	m_QueryStillImageSensorCapabilitiesRec.erase(m_QueryStillImageSensorCapabilitiesRec.begin()+index);
	return 0;
}

int QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::deleteLastElement()
{
	m_QueryStillImageSensorCapabilitiesRec.pop_back();
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
const unsigned int QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_QueryStillImageSensorCapabilitiesRec.size(); i++)
	{
		size += m_QueryStillImageSensorCapabilitiesRec[i].getSize();
	}
	
	return size;
}

void QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_QueryStillImageSensorCapabilitiesRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_QueryStillImageSensorCapabilitiesRec.size(); i++)
	{
		m_QueryStillImageSensorCapabilitiesRec[i].encode(bytes + pos);
		pos += m_QueryStillImageSensorCapabilitiesRec[i].getSize();
	}
}

void QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_QueryStillImageSensorCapabilitiesRec.resize(size);
	for (int i = 0; i < m_QueryStillImageSensorCapabilitiesRec.size(); i++)
	{
		m_QueryStillImageSensorCapabilitiesRec[i].decode(bytes + pos);
		pos += m_QueryStillImageSensorCapabilitiesRec[i].getSize();
	}
}

QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList &QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::operator=(const QueryStillImageSensorCapabilitiesList &value)
{
	m_QueryStillImageSensorCapabilitiesRec.clear();
	
	for (int i = 0; i < value.m_QueryStillImageSensorCapabilitiesRec.size(); i++)
	{
		m_QueryStillImageSensorCapabilitiesRec.push_back(value.m_QueryStillImageSensorCapabilitiesRec[i]);
		m_QueryStillImageSensorCapabilitiesRec[i].setParent(this);
	}
	
	return *this;
}

bool QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::operator==(const QueryStillImageSensorCapabilitiesList &value) const
{
	for (int i = 0; i < m_QueryStillImageSensorCapabilitiesRec.size(); i++)
	{
		if (m_QueryStillImageSensorCapabilitiesRec[i] !=  value.m_QueryStillImageSensorCapabilitiesRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::operator!=(const QueryStillImageSensorCapabilitiesList &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_QueryStillImageSensorCapabilitiesRec.size(); i++)
	{
		m_QueryStillImageSensorCapabilitiesRec[i].setParent(this);
	}
	/// No Initialization of m_QueryStillImageSensorCapabilitiesRec necessary.
}

QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::QueryStillImageSensorCapabilitiesList(const QueryStillImageSensorCapabilitiesList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_QueryStillImageSensorCapabilitiesRec.size(); i++)
	{
		m_QueryStillImageSensorCapabilitiesRec[i].setParent(this);
	}
	/// No Initialization of m_QueryStillImageSensorCapabilitiesRec necessary.
	
	/// Copy the values
	m_QueryStillImageSensorCapabilitiesRec.clear();
	
	for (int i = 0; i < value.m_QueryStillImageSensorCapabilitiesRec.size(); i++)
	{
		m_QueryStillImageSensorCapabilitiesRec.push_back(value.m_QueryStillImageSensorCapabilitiesRec[i]);
		m_QueryStillImageSensorCapabilitiesRec[i].setParent(this);
	}
}

QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList::~QueryStillImageSensorCapabilitiesList()
{
}

QueryStillImageSensorCapabilities::Body::QueryStillImageSensorCapabilitiesList* const QueryStillImageSensorCapabilities::Body::getQueryStillImageSensorCapabilitiesList()
{
	return &m_QueryStillImageSensorCapabilitiesList;
}

int QueryStillImageSensorCapabilities::Body::setQueryStillImageSensorCapabilitiesList(const QueryStillImageSensorCapabilitiesList &value)
{
	m_QueryStillImageSensorCapabilitiesList = value;
	setParentPresenceVector();
	return 0;
}

void QueryStillImageSensorCapabilities::Body::setParentPresenceVector()
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
const unsigned int QueryStillImageSensorCapabilities::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryStillImageSensorCapabilitiesList.getSize();
	
	return size;
}

void QueryStillImageSensorCapabilities::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryStillImageSensorCapabilitiesList.encode(bytes + pos);
	pos += m_QueryStillImageSensorCapabilitiesList.getSize();
}

void QueryStillImageSensorCapabilities::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryStillImageSensorCapabilitiesList.decode(bytes + pos);
	pos += m_QueryStillImageSensorCapabilitiesList.getSize();
}

QueryStillImageSensorCapabilities::Body &QueryStillImageSensorCapabilities::Body::operator=(const Body &value)
{
	m_QueryStillImageSensorCapabilitiesList = value.m_QueryStillImageSensorCapabilitiesList;
	m_QueryStillImageSensorCapabilitiesList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryStillImageSensorCapabilities::Body::operator==(const Body &value) const
{
	if (m_QueryStillImageSensorCapabilitiesList != value.m_QueryStillImageSensorCapabilitiesList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryStillImageSensorCapabilities::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorCapabilities::Body::Body()
{
	m_QueryStillImageSensorCapabilitiesList.setParent(this);
	/// No Initialization of m_QueryStillImageSensorCapabilitiesList necessary.
}

QueryStillImageSensorCapabilities::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryStillImageSensorCapabilitiesList.setParent(this);
	/// No Initialization of m_QueryStillImageSensorCapabilitiesList necessary.
	
	/// Copy the values
	m_QueryStillImageSensorCapabilitiesList = value.m_QueryStillImageSensorCapabilitiesList;
	m_QueryStillImageSensorCapabilitiesList.setParent(this);
	/// This code is currently not supported
}

QueryStillImageSensorCapabilities::Body::~Body()
{
}

QueryStillImageSensorCapabilities::Body* const QueryStillImageSensorCapabilities::getBody()
{
	return &m_Body;
}

int QueryStillImageSensorCapabilities::setBody(const Body &value)
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
const unsigned int QueryStillImageSensorCapabilities::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryStillImageSensorCapabilities::encode(unsigned char *bytes)
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

void QueryStillImageSensorCapabilities::decode(const unsigned char *bytes)
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

QueryStillImageSensorCapabilities &QueryStillImageSensorCapabilities::operator=(const QueryStillImageSensorCapabilities &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryStillImageSensorCapabilities::operator==(const QueryStillImageSensorCapabilities &value) const
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

bool QueryStillImageSensorCapabilities::operator!=(const QueryStillImageSensorCapabilities &value) const
{
	return !((*this) == value);
}

QueryStillImageSensorCapabilities::QueryStillImageSensorCapabilities()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryStillImageSensorCapabilities::QueryStillImageSensorCapabilities(const QueryStillImageSensorCapabilities &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryStillImageSensorCapabilities::~QueryStillImageSensorCapabilities()
{
}


}
