#include "urn_jaus_example_addition_server_1_0/Messages/ReportAddition.h"

namespace urn_jaus_example_addition_server_1_0
{

void ReportAddition::Header::HeaderRecord::setParent(Header* parent)
{
	m_parent = parent;
}

void ReportAddition::Header::HeaderRecord::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportAddition::Header::HeaderRecord::getMessageIDHeader()
{
	return m_MessageIDHeader;
}

int ReportAddition::Header::HeaderRecord::setMessageIDHeader(jUnsignedShortInteger value)
{
	m_MessageIDHeader = value;
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
const unsigned int ReportAddition::Header::HeaderRecord::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportAddition::Header::HeaderRecord::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_MessageIDHeaderTemp;
	
	m_MessageIDHeaderTemp = JSIDL_v_1_0::correctEndianness(m_MessageIDHeader);
	memcpy(bytes + pos, &m_MessageIDHeaderTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void ReportAddition::Header::HeaderRecord::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_MessageIDHeaderTemp;
	
	memcpy(&m_MessageIDHeaderTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_MessageIDHeader = JSIDL_v_1_0::correctEndianness(m_MessageIDHeaderTemp);
	pos += sizeof(jUnsignedShortInteger);
}

ReportAddition::Header::HeaderRecord &ReportAddition::Header::HeaderRecord::operator=(const HeaderRecord &value)
{
	m_MessageIDHeader = value.m_MessageIDHeader;
	
	return *this;
}

bool ReportAddition::Header::HeaderRecord::operator==(const HeaderRecord &value) const
{
	if (m_MessageIDHeader != value.m_MessageIDHeader)
	{
		return false;
	}
	
	return true;
}

bool ReportAddition::Header::HeaderRecord::operator!=(const HeaderRecord &value) const
{
	return !((*this) == value);
}

ReportAddition::Header::HeaderRecord::HeaderRecord()
{
	m_parent = NULL;
	m_MessageIDHeader = 0xf011;
}

ReportAddition::Header::HeaderRecord::HeaderRecord(const HeaderRecord &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageIDHeader = 0xf011;
	
	/// Copy the values
	m_MessageIDHeader = value.m_MessageIDHeader;
}

ReportAddition::Header::HeaderRecord::~HeaderRecord()
{
}

ReportAddition::Header::HeaderRecord* const ReportAddition::Header::getHeaderRecord()
{
	return &m_HeaderRecord;
}

int ReportAddition::Header::setHeaderRecord(const HeaderRecord &value)
{
	m_HeaderRecord = value;
	setParentPresenceVector();
	return 0;
}

void ReportAddition::Header::setParentPresenceVector()
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
const unsigned int ReportAddition::Header::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRecord.getSize();
	
	return size;
}

void ReportAddition::Header::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRecord.encode(bytes + pos);
	pos += m_HeaderRecord.getSize();
}

void ReportAddition::Header::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRecord.decode(bytes + pos);
	pos += m_HeaderRecord.getSize();
}

ReportAddition::Header &ReportAddition::Header::operator=(const Header &value)
{
	m_HeaderRecord = value.m_HeaderRecord;
	m_HeaderRecord.setParent(this);
	
	return *this;
}

bool ReportAddition::Header::operator==(const Header &value) const
{
	if (m_HeaderRecord != value.m_HeaderRecord)
	{
		return false;
	}
	return true;
}

bool ReportAddition::Header::operator!=(const Header &value) const
{
	return !((*this) == value);
}

ReportAddition::Header::Header()
{
	m_HeaderRecord.setParent(this);
	/// No Initialization of m_HeaderRecord necessary.
}

ReportAddition::Header::Header(const Header &value)
{
	/// Initiliaze the protected variables
	m_HeaderRecord.setParent(this);
	/// No Initialization of m_HeaderRecord necessary.
	
	/// Copy the values
	m_HeaderRecord = value.m_HeaderRecord;
	m_HeaderRecord.setParent(this);
}

ReportAddition::Header::~Header()
{
}

ReportAddition::Header* const ReportAddition::getHeader()
{
	return &m_Header;
}

int ReportAddition::setHeader(const Header &value)
{
	m_Header = value;
	return 0;
}

void ReportAddition::AdditionOutputBody::AdditionOutput::setParent(AdditionOutputBody* parent)
{
	m_parent = parent;
}

void ReportAddition::AdditionOutputBody::AdditionOutput::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger ReportAddition::AdditionOutputBody::AdditionOutput::getAdditionResult()
{
	return m_AdditionResult;
}

int ReportAddition::AdditionOutputBody::AdditionOutput::setAdditionResult(jUnsignedInteger value)
{
	m_AdditionResult = value;
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
const unsigned int ReportAddition::AdditionOutputBody::AdditionOutput::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void ReportAddition::AdditionOutputBody::AdditionOutput::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_AdditionResultTemp;
	
	m_AdditionResultTemp = JSIDL_v_1_0::correctEndianness(m_AdditionResult);
	memcpy(bytes + pos, &m_AdditionResultTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
}

void ReportAddition::AdditionOutputBody::AdditionOutput::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_AdditionResultTemp;
	
	memcpy(&m_AdditionResultTemp, bytes + pos, sizeof(jUnsignedInteger));
	m_AdditionResult = JSIDL_v_1_0::correctEndianness(m_AdditionResultTemp);
	pos += sizeof(jUnsignedInteger);
}

ReportAddition::AdditionOutputBody::AdditionOutput &ReportAddition::AdditionOutputBody::AdditionOutput::operator=(const AdditionOutput &value)
{
	m_AdditionResult = value.m_AdditionResult;
	
	return *this;
}

bool ReportAddition::AdditionOutputBody::AdditionOutput::operator==(const AdditionOutput &value) const
{
	if (m_AdditionResult != value.m_AdditionResult)
	{
		return false;
	}
	
	return true;
}

bool ReportAddition::AdditionOutputBody::AdditionOutput::operator!=(const AdditionOutput &value) const
{
	return !((*this) == value);
}

ReportAddition::AdditionOutputBody::AdditionOutput::AdditionOutput()
{
	m_parent = NULL;
	m_AdditionResult = 0;
}

ReportAddition::AdditionOutputBody::AdditionOutput::AdditionOutput(const AdditionOutput &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_AdditionResult = 0;
	
	/// Copy the values
	m_AdditionResult = value.m_AdditionResult;
}

ReportAddition::AdditionOutputBody::AdditionOutput::~AdditionOutput()
{
}

ReportAddition::AdditionOutputBody::AdditionOutput* const ReportAddition::AdditionOutputBody::getAdditionOutput()
{
	return &m_AdditionOutput;
}

int ReportAddition::AdditionOutputBody::setAdditionOutput(const AdditionOutput &value)
{
	m_AdditionOutput = value;
	setParentPresenceVector();
	return 0;
}

void ReportAddition::AdditionOutputBody::setParentPresenceVector()
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
const unsigned int ReportAddition::AdditionOutputBody::getSize()
{
	unsigned int size = 0;
	
	size += m_AdditionOutput.getSize();
	
	return size;
}

void ReportAddition::AdditionOutputBody::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AdditionOutput.encode(bytes + pos);
	pos += m_AdditionOutput.getSize();
}

void ReportAddition::AdditionOutputBody::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AdditionOutput.decode(bytes + pos);
	pos += m_AdditionOutput.getSize();
}

ReportAddition::AdditionOutputBody &ReportAddition::AdditionOutputBody::operator=(const AdditionOutputBody &value)
{
	m_AdditionOutput = value.m_AdditionOutput;
	m_AdditionOutput.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportAddition::AdditionOutputBody::operator==(const AdditionOutputBody &value) const
{
	if (m_AdditionOutput != value.m_AdditionOutput)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportAddition::AdditionOutputBody::operator!=(const AdditionOutputBody &value) const
{
	return !((*this) == value);
}

ReportAddition::AdditionOutputBody::AdditionOutputBody()
{
	m_AdditionOutput.setParent(this);
	/// No Initialization of m_AdditionOutput necessary.
}

ReportAddition::AdditionOutputBody::AdditionOutputBody(const AdditionOutputBody &value)
{
	/// Initiliaze the protected variables
	m_AdditionOutput.setParent(this);
	/// No Initialization of m_AdditionOutput necessary.
	
	/// Copy the values
	m_AdditionOutput = value.m_AdditionOutput;
	m_AdditionOutput.setParent(this);
	/// This code is currently not supported
}

ReportAddition::AdditionOutputBody::~AdditionOutputBody()
{
}

ReportAddition::AdditionOutputBody* const ReportAddition::getAdditionOutputBody()
{
	return &m_AdditionOutputBody;
}

int ReportAddition::setAdditionOutputBody(const AdditionOutputBody &value)
{
	m_AdditionOutputBody = value;
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
const unsigned int ReportAddition::getSize()
{
	unsigned int size = 0;
	
	size += m_Header.getSize();
	size += m_AdditionOutputBody.getSize();
	
	return size;
}

void ReportAddition::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Header.encode(bytes + pos);
	pos += m_Header.getSize();
	m_AdditionOutputBody.encode(bytes + pos);
	pos += m_AdditionOutputBody.getSize();
}

void ReportAddition::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Header.decode(bytes + pos);
	pos += m_Header.getSize();
	m_AdditionOutputBody.decode(bytes + pos);
	pos += m_AdditionOutputBody.getSize();
}

ReportAddition &ReportAddition::operator=(const ReportAddition &value)
{
	m_Header = value.m_Header;
	m_AdditionOutputBody = value.m_AdditionOutputBody;
	
	return *this;
}

bool ReportAddition::operator==(const ReportAddition &value) const
{
	if (m_Header != value.m_Header)
	{
		return false;
	}
	if (m_AdditionOutputBody != value.m_AdditionOutputBody)
	{
		return false;
	}
	
	return true;
}

bool ReportAddition::operator!=(const ReportAddition &value) const
{
	return !((*this) == value);
}

ReportAddition::ReportAddition()
{
	/// No Initialization of m_Header necessary.
	/// No Initialization of m_AdditionOutputBody necessary.
	m_IsCommand = false;
}

ReportAddition::ReportAddition(const ReportAddition &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_Header necessary.
	/// No Initialization of m_AdditionOutputBody necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_Header = value.m_Header;
	m_AdditionOutputBody = value.m_AdditionOutputBody;
}

ReportAddition::~ReportAddition()
{
}


}
