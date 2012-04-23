#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/ReportStillImageData.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

void ReportStillImageData::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportStillImageData::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportStillImageData::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportStillImageData::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportStillImageData::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportStillImageData::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportStillImageData::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportStillImageData::AppHeader::HeaderRec &ReportStillImageData::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportStillImageData::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportStillImageData::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportStillImageData::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x4814;
}

ReportStillImageData::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x4814;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportStillImageData::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportStillImageData::AppHeader::HeaderRec* const ReportStillImageData::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportStillImageData::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportStillImageData::AppHeader::setParentPresenceVector()
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
const unsigned int ReportStillImageData::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportStillImageData::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportStillImageData::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportStillImageData::AppHeader &ReportStillImageData::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportStillImageData::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportStillImageData::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportStillImageData::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportStillImageData::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportStillImageData::AppHeader::~AppHeader()
{
}

ReportStillImageData::AppHeader* const ReportStillImageData::getAppHeader()
{
	return &m_AppHeader;
}

int ReportStillImageData::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportStillImageData::Body::StillImageDataList::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportStillImageData::Body::StillImageDataList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::setParent(StillImageDataList* parent)
{
	m_parent = parent;
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte ReportStillImageData::Body::StillImageDataList::StillImageDataRec::getPresenceVector()
{
	return m_PresenceVector;
}

int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
	return 0;
}

bool ReportStillImageData::Body::StillImageDataList::StillImageDataRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

jUnsignedShortInteger ReportStillImageData::Body::StillImageDataList::StillImageDataRec::getSensorID()
{
	return m_SensorID;
}

int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::setSensorID(jUnsignedShortInteger value)
{
	m_SensorID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedByte ReportStillImageData::Body::StillImageDataList::StillImageDataRec::getReportCoordinateSystem()
{
	return m_ReportCoordinateSystem;
}

int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::setReportCoordinateSystem(jUnsignedByte value)
{
	if ((value == 0) || (value == 1))
	{
		m_ReportCoordinateSystem = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::setParent(StillImageDataRec* parent)
{
	m_parent = parent;
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(0);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::getMilliseconds()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<10> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 9; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::setMilliseconds(jUnsignedInteger value)
{
	if (((value >= 0) && (value <= 999)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<10> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 9; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::getSeconds()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<6> sfbs;
	int i = 0;
	
	for (int index = 10; index <= 15; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::setSeconds(jUnsignedInteger value)
{
	if (((value >= 0) && (value <= 59)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<6> sfbs((int)value);
		int i = 0;
		
		for (int index = 10; index <= 15; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::getMinutes()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<6> sfbs;
	int i = 0;
	
	for (int index = 16; index <= 21; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::setMinutes(jUnsignedInteger value)
{
	if (((value >= 0) && (value <= 59)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<6> sfbs((int)value);
		int i = 0;
		
		for (int index = 16; index <= 21; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::getHour()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<5> sfbs;
	int i = 0;
	
	for (int index = 22; index <= 26; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::setHour(jUnsignedInteger value)
{
	if (((value >= 0) && (value <= 23)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<5> sfbs((int)value);
		int i = 0;
		
		for (int index = 22; index <= 26; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::getDay()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<5> sfbs;
	int i = 0;
	
	for (int index = 27; index <= 31; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::setDay(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 31)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<5> sfbs((int)value);
		int i = 0;
		
		for (int index = 27; index <= 31; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
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
const unsigned int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_SubFieldsTemp;
	
	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianness(m_SubFields);
	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_SubFieldsTemp;
	
	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedInteger));
	m_SubFields = JSIDL_v_1_0::correctEndianness(m_SubFieldsTemp);
	pos += sizeof(jUnsignedInteger);
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp &ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::operator=(const TimeStamp &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::operator==(const TimeStamp &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::operator!=(const TimeStamp &value) const
{
	return !((*this) == value);
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::TimeStamp()
{
	m_parent = NULL;
	m_SubFields = 0;
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::TimeStamp(const TimeStamp &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp::~TimeStamp()
{
}

bool ReportStillImageData::Body::StillImageDataList::StillImageDataRec::isTimeStampValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::TimeStamp* const ReportStillImageData::Body::StillImageDataList::StillImageDataRec::getTimeStamp()
{
	return &m_TimeStamp;
}

int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::setTimeStamp(const TimeStamp &value)
{
	m_TimeStamp = value;
	setPresenceVector(0);
	setParentPresenceVector();
	return 0;
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::setParent(StillImageDataRec* parent)
{
	m_parent = parent;
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const jUnsignedByte ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::getFormat() const
{
	return m_Format;
}

const jUnsignedInteger ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::getLength() const
{
	return m_Length;
}

const unsigned char *ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::getData() const
{
	return m_Data;
}

int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::set(jUnsignedByte format, jUnsignedInteger length, unsigned char *data)
{
	if ((format == 0)||(format == 1)||(format == 2)||(format == 3)||(format == 4)||(format == 5)||(format == 6)||(format == 7)||(format == 8)||(format == 9)||(format == 10))
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
const unsigned int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::encode(unsigned char *bytes)
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
	
	jUnsignedInteger m_LengthTemp;
	
	m_LengthTemp = JSIDL_v_1_0::correctEndianness(m_Length);
	memcpy(bytes+pos, &m_LengthTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
	
	memcpy(bytes+pos, m_Data, m_Length);
	pos += m_Length;
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::decode(const unsigned char *bytes)
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
	
	jUnsignedInteger m_LengthTemp;
	
	memcpy(&m_LengthTemp, bytes+pos, sizeof(jUnsignedInteger));
	m_Length = JSIDL_v_1_0::correctEndianness(m_LengthTemp);
	pos += sizeof(jUnsignedInteger);
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[m_Length];
		memcpy(m_Data, bytes+pos, m_Length);
		pos += m_Length;
	}
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame &ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::operator=(const ImageFrame &value)
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

bool ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::operator==(const ImageFrame &value) const
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

bool ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::operator!=(const ImageFrame &value) const
{
	return !((*this) == value);
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::ImageFrame()
{
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
	m_Format = 0;
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::ImageFrame(const ImageFrame &value)
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

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame::~ImageFrame()
{
	delete[] m_Data;
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::ImageFrame* const ReportStillImageData::Body::StillImageDataList::StillImageDataRec::getImageFrame()
{
	return &m_ImageFrame;
}

int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::setImageFrame(const ImageFrame &value)
{
	m_ImageFrame = value;
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
const unsigned int ReportStillImageData::Body::StillImageDataList::StillImageDataRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		size += m_TimeStamp.getSize();
	}
	size += m_ImageFrame.getSize();
	
	return size;
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_PresenceVectorTemp;
	
	m_PresenceVectorTemp = JSIDL_v_1_0::correctEndianness(m_PresenceVector);
	memcpy(bytes + pos, &m_PresenceVectorTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	jUnsignedShortInteger m_SensorIDTemp;
	
	m_SensorIDTemp = JSIDL_v_1_0::correctEndianness(m_SensorID);
	memcpy(bytes + pos, &m_SensorIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_ReportCoordinateSystemTemp;
	
	m_ReportCoordinateSystemTemp = JSIDL_v_1_0::correctEndianness(m_ReportCoordinateSystem);
	memcpy(bytes + pos, &m_ReportCoordinateSystemTemp, sizeof(jUnsignedByte));
	pos += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		m_TimeStamp.encode(bytes + pos);
		pos += m_TimeStamp.getSize();
	}
	m_ImageFrame.encode(bytes + pos);
	pos += m_ImageFrame.getSize();
}

void ReportStillImageData::Body::StillImageDataList::StillImageDataRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_PresenceVectorTemp;
	
	memcpy(&m_PresenceVectorTemp, bytes + pos, sizeof(jUnsignedByte));
	m_PresenceVector = JSIDL_v_1_0::correctEndianness(m_PresenceVectorTemp);
	pos += sizeof(jUnsignedByte);
	jUnsignedShortInteger m_SensorIDTemp;
	
	memcpy(&m_SensorIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SensorID = JSIDL_v_1_0::correctEndianness(m_SensorIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedByte m_ReportCoordinateSystemTemp;
	
	memcpy(&m_ReportCoordinateSystemTemp, bytes + pos, sizeof(jUnsignedByte));
	m_ReportCoordinateSystem = JSIDL_v_1_0::correctEndianness(m_ReportCoordinateSystemTemp);
	pos += sizeof(jUnsignedByte);
	if (checkPresenceVector(0))
	{
		m_TimeStamp.decode(bytes + pos);
		pos += m_TimeStamp.getSize();
	}
	m_ImageFrame.decode(bytes + pos);
	pos += m_ImageFrame.getSize();
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec &ReportStillImageData::Body::StillImageDataList::StillImageDataRec::operator=(const StillImageDataRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_SensorID = value.m_SensorID;
	m_ReportCoordinateSystem = value.m_ReportCoordinateSystem;
	m_TimeStamp = value.m_TimeStamp;
	m_ImageFrame = value.m_ImageFrame;
	
	return *this;
}

bool ReportStillImageData::Body::StillImageDataList::StillImageDataRec::operator==(const StillImageDataRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	if (m_SensorID != value.m_SensorID)
	{
		return false;
	}
	if (m_ReportCoordinateSystem != value.m_ReportCoordinateSystem)
	{
		return false;
	}
	
	if (m_TimeStamp != value.m_TimeStamp)
	{
		return false;
	}
	
	if (m_ImageFrame != value.m_ImageFrame)
	{
		return false;
	}
	
	return true;
}

bool ReportStillImageData::Body::StillImageDataList::StillImageDataRec::operator!=(const StillImageDataRec &value) const
{
	return !((*this) == value);
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::StillImageDataRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_SensorID = 0;
	m_ReportCoordinateSystem = 0;
	m_TimeStamp.setParent(this);
	m_ImageFrame.setParent(this);
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::StillImageDataRec(const StillImageDataRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	m_SensorID = 0;
	m_ReportCoordinateSystem = 0;
	m_TimeStamp.setParent(this);
	m_ImageFrame.setParent(this);
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_SensorID = value.m_SensorID;
	m_ReportCoordinateSystem = value.m_ReportCoordinateSystem;
	m_TimeStamp = value.m_TimeStamp;
	m_ImageFrame = value.m_ImageFrame;
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec::~StillImageDataRec()
{
}

unsigned int ReportStillImageData::Body::StillImageDataList::getNumberOfElements() const
{
	return (unsigned int) m_StillImageDataRec.size();
}

ReportStillImageData::Body::StillImageDataList::StillImageDataRec* const ReportStillImageData::Body::StillImageDataList::getElement(unsigned int index)
{
	return &m_StillImageDataRec.at(index);
}

int ReportStillImageData::Body::StillImageDataList::setElement(unsigned int index, const StillImageDataRec &value)
{
	if(m_StillImageDataRec.size()-1 < index)
	{
		return 1;
	}
	
	m_StillImageDataRec.at(index) = value;
	m_StillImageDataRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportStillImageData::Body::StillImageDataList::addElement(const StillImageDataRec &value)
{
	m_StillImageDataRec.push_back(value);
	m_StillImageDataRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportStillImageData::Body::StillImageDataList::deleteElement(unsigned int index)
{
	if(m_StillImageDataRec.size()-1 < index)
	{
		return 1;
	}
	
	m_StillImageDataRec.erase(m_StillImageDataRec.begin()+index);
	return 0;
}

int ReportStillImageData::Body::StillImageDataList::deleteLastElement()
{
	m_StillImageDataRec.pop_back();
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
const unsigned int ReportStillImageData::Body::StillImageDataList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	for (int i = 0; i < m_StillImageDataRec.size(); i++)
	{
		size += m_StillImageDataRec[i].getSize();
	}
	
	return size;
}

void ReportStillImageData::Body::StillImageDataList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size = (jUnsignedShortInteger) m_StillImageDataRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_StillImageDataRec.size(); i++)
	{
		m_StillImageDataRec[i].encode(bytes + pos);
		pos += m_StillImageDataRec[i].getSize();
	}
}

void ReportStillImageData::Body::StillImageDataList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_StillImageDataRec.resize(size);
	for (int i = 0; i < m_StillImageDataRec.size(); i++)
	{
		m_StillImageDataRec[i].decode(bytes + pos);
		pos += m_StillImageDataRec[i].getSize();
	}
}

ReportStillImageData::Body::StillImageDataList &ReportStillImageData::Body::StillImageDataList::operator=(const StillImageDataList &value)
{
	m_StillImageDataRec.clear();
	
	for (int i = 0; i < value.m_StillImageDataRec.size(); i++)
	{
		m_StillImageDataRec.push_back(value.m_StillImageDataRec[i]);
		m_StillImageDataRec[i].setParent(this);
	}
	
	return *this;
}

bool ReportStillImageData::Body::StillImageDataList::operator==(const StillImageDataList &value) const
{
	for (int i = 0; i < m_StillImageDataRec.size(); i++)
	{
		if (m_StillImageDataRec[i] !=  value.m_StillImageDataRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportStillImageData::Body::StillImageDataList::operator!=(const StillImageDataList &value) const
{
	return !((*this) == value);
}

ReportStillImageData::Body::StillImageDataList::StillImageDataList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_StillImageDataRec.size(); i++)
	{
		m_StillImageDataRec[i].setParent(this);
	}
	/// No Initialization of m_StillImageDataRec necessary.
}

ReportStillImageData::Body::StillImageDataList::StillImageDataList(const StillImageDataList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_StillImageDataRec.size(); i++)
	{
		m_StillImageDataRec[i].setParent(this);
	}
	/// No Initialization of m_StillImageDataRec necessary.
	
	/// Copy the values
	m_StillImageDataRec.clear();
	
	for (int i = 0; i < value.m_StillImageDataRec.size(); i++)
	{
		m_StillImageDataRec.push_back(value.m_StillImageDataRec[i]);
		m_StillImageDataRec[i].setParent(this);
	}
}

ReportStillImageData::Body::StillImageDataList::~StillImageDataList()
{
}

ReportStillImageData::Body::StillImageDataList* const ReportStillImageData::Body::getStillImageDataList()
{
	return &m_StillImageDataList;
}

int ReportStillImageData::Body::setStillImageDataList(const StillImageDataList &value)
{
	m_StillImageDataList = value;
	setParentPresenceVector();
	return 0;
}

void ReportStillImageData::Body::setParentPresenceVector()
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
const unsigned int ReportStillImageData::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_StillImageDataList.getSize();
	
	return size;
}

void ReportStillImageData::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_StillImageDataList.encode(bytes + pos);
	pos += m_StillImageDataList.getSize();
}

void ReportStillImageData::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_StillImageDataList.decode(bytes + pos);
	pos += m_StillImageDataList.getSize();
}

ReportStillImageData::Body &ReportStillImageData::Body::operator=(const Body &value)
{
	m_StillImageDataList = value.m_StillImageDataList;
	m_StillImageDataList.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportStillImageData::Body::operator==(const Body &value) const
{
	if (m_StillImageDataList != value.m_StillImageDataList)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportStillImageData::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportStillImageData::Body::Body()
{
	m_StillImageDataList.setParent(this);
	/// No Initialization of m_StillImageDataList necessary.
}

ReportStillImageData::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_StillImageDataList.setParent(this);
	/// No Initialization of m_StillImageDataList necessary.
	
	/// Copy the values
	m_StillImageDataList = value.m_StillImageDataList;
	m_StillImageDataList.setParent(this);
	/// This code is currently not supported
}

ReportStillImageData::Body::~Body()
{
}

ReportStillImageData::Body* const ReportStillImageData::getBody()
{
	return &m_Body;
}

int ReportStillImageData::setBody(const Body &value)
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
const unsigned int ReportStillImageData::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportStillImageData::encode(unsigned char *bytes)
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

void ReportStillImageData::decode(const unsigned char *bytes)
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

ReportStillImageData &ReportStillImageData::operator=(const ReportStillImageData &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportStillImageData::operator==(const ReportStillImageData &value) const
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

bool ReportStillImageData::operator!=(const ReportStillImageData &value) const
{
	return !((*this) == value);
}

ReportStillImageData::ReportStillImageData()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportStillImageData::ReportStillImageData(const ReportStillImageData &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportStillImageData::~ReportStillImageData()
{
}


}
