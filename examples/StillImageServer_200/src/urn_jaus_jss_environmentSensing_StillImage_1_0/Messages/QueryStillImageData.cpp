#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/QueryStillImageData.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void QueryStillImageData::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void QueryStillImageData::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryStillImageData::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int QueryStillImageData::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int QueryStillImageData::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryStillImageData::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void QueryStillImageData::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

QueryStillImageData::AppHeader::HeaderRec &QueryStillImageData::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool QueryStillImageData::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool QueryStillImageData::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

QueryStillImageData::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x2814;
}

QueryStillImageData::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x2814;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

QueryStillImageData::AppHeader::HeaderRec::~HeaderRec()
{
}

QueryStillImageData::AppHeader::HeaderRec* const QueryStillImageData::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int QueryStillImageData::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void QueryStillImageData::AppHeader::setParentPresenceVector()
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
const unsigned int QueryStillImageData::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void QueryStillImageData::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void QueryStillImageData::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

QueryStillImageData::AppHeader &QueryStillImageData::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool QueryStillImageData::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool QueryStillImageData::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

QueryStillImageData::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

QueryStillImageData::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

QueryStillImageData::AppHeader::~AppHeader()
{
}

QueryStillImageData::AppHeader* const QueryStillImageData::getAppHeader()
{
	return &m_AppHeader;
}

int QueryStillImageData::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void QueryStillImageData::Body::QueryStillImageDataList::setParent(Body* parent)
{
	m_parent = parent;
}

void QueryStillImageData::Body::QueryStillImageDataList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::setParent(QueryStillImageDataList* parent)
{
	m_parent = parent;
}

void QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::getSensorID()
{
	return m_SensorID;
}

int QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::getReportCoordinateSystem()
{
	return m_ReportCoordinateSystem;
}

int QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::setReportCoordinateSystem(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		m_ReportCoordinateSystem = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedByte QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::getQueryPresenceVector()
{
	return m_QueryPresenceVector;
}

int QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::setQueryPresenceVector(jUnsignedByte value)
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
const unsigned int QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedByte);
	
	return size;
}

void QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::encode(unsigned char *bytes)
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
	jUnsignedByte m_ReportCoordinateSystemTemp;
	
	m_ReportCoordinateSystemTemp = JSIDL_v_1_0::correctEndianness(m_ReportCoordinateSystem);
	memcpy(bytes + pos, &m_ReportCoordinateSystemTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_QueryPresenceVectorTemp;
	
	m_QueryPresenceVectorTemp = JSIDL_v_1_0::correctEndianness(m_QueryPresenceVector);
	memcpy(bytes + pos, &m_QueryPresenceVectorTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
}

void QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::decode(const unsigned char *bytes)
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
	jUnsignedByte m_ReportCoordinateSystemTemp;
	
	memcpy(&m_ReportCoordinateSystemTemp, bytes + pos, sizeof(jUnsignedByte));
	m_ReportCoordinateSystem = JSIDL_v_1_0::correctEndianness(m_ReportCoordinateSystemTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedByte m_QueryPresenceVectorTemp;
	
	memcpy(&m_QueryPresenceVectorTemp, bytes + pos, sizeof(jUnsignedByte));
	m_QueryPresenceVector = JSIDL_v_1_0::correctEndianness(m_QueryPresenceVectorTemp);
	pos += sizeof(jUnsignedByte);
}

QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec &QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::operator=(const QueryStillImageDataRec &value)
{
	m_SensorID = value.m_SensorID;
	m_ReportCoordinateSystem = value.m_ReportCoordinateSystem;
	m_QueryPresenceVector = value.m_QueryPresenceVector;
	
	return *this;
}

bool QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::operator==(const QueryStillImageDataRec &value) const
{
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	if (m_ReportCoordinateSystem != value.m_ReportCoordinateSystem)
	{
		return false;
	}
	if (m_QueryPresenceVector != value.m_QueryPresenceVector)
	{
		return false;
	}
	
	return true;
}

bool QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::operator!=(const QueryStillImageDataRec &value) const
{
	return !((*this) == value);
}

QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::QueryStillImageDataRec()
{
	m_parent = NULL;
	m_SensorID = 0;
	m_ReportCoordinateSystem = 0;
	m_QueryPresenceVector = 0;
}

QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::QueryStillImageDataRec(const QueryStillImageDataRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SensorID = 0;
	m_ReportCoordinateSystem = 0;
	m_QueryPresenceVector = 0;
	
	/// Copy the values
	m_SensorID = value.m_SensorID;
	m_ReportCoordinateSystem = value.m_ReportCoordinateSystem;
	m_QueryPresenceVector = value.m_QueryPresenceVector;
}

QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec::~QueryStillImageDataRec()
{
}

unsigned int QueryStillImageData::Body::QueryStillImageDataList::getNumberOfElements() const
{
	return (unsigned int) m_QueryStillImageDataRec.size();
}

QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataRec* const QueryStillImageData::Body::QueryStillImageDataList::getElement(unsigned int index)
{
	return &m_QueryStillImageDataRec.at(index);
}

int QueryStillImageData::Body::QueryStillImageDataList::setElement(unsigned int index, const QueryStillImageDataRec &value)
{
	if(m_QueryStillImageDataRec.size()-1 < index)
	{
		return 1;
	}
	
	m_QueryStillImageDataRec.at(index) = value;
	m_QueryStillImageDataRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryStillImageData::Body::QueryStillImageDataList::addElement(const QueryStillImageDataRec &value)
{
	m_QueryStillImageDataRec.push_back(value);
	m_QueryStillImageDataRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int QueryStillImageData::Body::QueryStillImageDataList::deleteElement(unsigned int index)
{
	if(m_QueryStillImageDataRec.size()-1 < index)
	{
		return 1;
	}
	
	m_QueryStillImageDataRec.erase(m_QueryStillImageDataRec.begin()+index);
	return 0;
}

int QueryStillImageData::Body::QueryStillImageDataList::deleteLastElement()
{
	m_QueryStillImageDataRec.pop_back();
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
const unsigned int QueryStillImageData::Body::QueryStillImageDataList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_QueryStillImageDataRec.size(); i++)
	{
		size += m_QueryStillImageDataRec[i].getSize();
	}
	
	return size;
}

void QueryStillImageData::Body::QueryStillImageDataList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_QueryStillImageDataRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_QueryStillImageDataRec.size(); i++)
	{
		m_QueryStillImageDataRec[i].encode(bytes + pos);
		pos += m_QueryStillImageDataRec[i].getSize();
	}
}

void QueryStillImageData::Body::QueryStillImageDataList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_QueryStillImageDataRec.resize(size);
	for (int i = 0; i < m_QueryStillImageDataRec.size(); i++)
	{
		m_QueryStillImageDataRec[i].decode(bytes + pos);
		pos += m_QueryStillImageDataRec[i].getSize();
	}
}

QueryStillImageData::Body::QueryStillImageDataList &QueryStillImageData::Body::QueryStillImageDataList::operator=(const QueryStillImageDataList &value)
{
	m_QueryStillImageDataRec.clear();
	
	for (int i = 0; i < value.m_QueryStillImageDataRec.size(); i++)
	{
		m_QueryStillImageDataRec.push_back(value.m_QueryStillImageDataRec[i]);
		m_QueryStillImageDataRec[i].setParent(this);
	}
	
	return *this;
}

bool QueryStillImageData::Body::QueryStillImageDataList::operator==(const QueryStillImageDataList &value) const
{
	for (int i = 0; i < m_QueryStillImageDataRec.size(); i++)
	{
		if (m_QueryStillImageDataRec[i] !=  value.m_QueryStillImageDataRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool QueryStillImageData::Body::QueryStillImageDataList::operator!=(const QueryStillImageDataList &value) const
{
	return !((*this) == value);
}

QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_QueryStillImageDataRec.size(); i++)
	{
		m_QueryStillImageDataRec[i].setParent(this);
	}
	/// No Initialization of m_QueryStillImageDataRec necessary.
}

QueryStillImageData::Body::QueryStillImageDataList::QueryStillImageDataList(const QueryStillImageDataList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_QueryStillImageDataRec.size(); i++)
	{
		m_QueryStillImageDataRec[i].setParent(this);
	}
	/// No Initialization of m_QueryStillImageDataRec necessary.
	
	/// Copy the values
	m_QueryStillImageDataRec.clear();
	
	for (int i = 0; i < value.m_QueryStillImageDataRec.size(); i++)
	{
		m_QueryStillImageDataRec.push_back(value.m_QueryStillImageDataRec[i]);
		m_QueryStillImageDataRec[i].setParent(this);
	}
}

QueryStillImageData::Body::QueryStillImageDataList::~QueryStillImageDataList()
{
}

QueryStillImageData::Body::QueryStillImageDataList* const QueryStillImageData::Body::getQueryStillImageDataList()
{
	return &m_QueryStillImageDataList;
}

int QueryStillImageData::Body::setQueryStillImageDataList(const QueryStillImageDataList &value)
{
	m_QueryStillImageDataList = value;
	setParentPresenceVector();
	return 0;
}

void QueryStillImageData::Body::setParentPresenceVector()
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
const unsigned int QueryStillImageData::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_QueryStillImageDataList.getSize();
	
	return size;
}

void QueryStillImageData::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryStillImageDataList.encode(bytes + pos);
	pos += m_QueryStillImageDataList.getSize();
}

void QueryStillImageData::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_QueryStillImageDataList.decode(bytes + pos);
	pos += m_QueryStillImageDataList.getSize();
}

QueryStillImageData::Body &QueryStillImageData::Body::operator=(const Body &value)
{
	m_QueryStillImageDataList = value.m_QueryStillImageDataList;
	m_QueryStillImageDataList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryStillImageData::Body::operator==(const Body &value) const
{
	if (m_QueryStillImageDataList != value.m_QueryStillImageDataList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryStillImageData::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

QueryStillImageData::Body::Body()
{
	m_QueryStillImageDataList.setParent(this);
	/// No Initialization of m_QueryStillImageDataList necessary.
}

QueryStillImageData::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_QueryStillImageDataList.setParent(this);
	/// No Initialization of m_QueryStillImageDataList necessary.
	
	/// Copy the values
	m_QueryStillImageDataList = value.m_QueryStillImageDataList;
	m_QueryStillImageDataList.setParent(this);
	/// This code is currently not supported
}

QueryStillImageData::Body::~Body()
{
}

QueryStillImageData::Body* const QueryStillImageData::getBody()
{
	return &m_Body;
}

int QueryStillImageData::setBody(const Body &value)
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
const unsigned int QueryStillImageData::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void QueryStillImageData::encode(unsigned char *bytes)
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

void QueryStillImageData::decode(const unsigned char *bytes)
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

QueryStillImageData &QueryStillImageData::operator=(const QueryStillImageData &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool QueryStillImageData::operator==(const QueryStillImageData &value) const
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

bool QueryStillImageData::operator!=(const QueryStillImageData &value) const
{
	return !((*this) == value);
}

QueryStillImageData::QueryStillImageData()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

QueryStillImageData::QueryStillImageData(const QueryStillImageData &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

QueryStillImageData::~QueryStillImageData()
{
}


}
