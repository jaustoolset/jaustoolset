#include "urn_jaus_example_addition_server_1_0/Messages/QueryAddition.h"

namespace urn_jaus_example_addition_server_1_0
{

void QueryAddition::Header::HeaderRecord::setParent(Header* parent)
{
	m_parent = parent;
}

void QueryAddition::Header::HeaderRecord::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger QueryAddition::Header::HeaderRecord::getMessageIDHeader()
{
	return m_MessageIDHeader;
}

int QueryAddition::Header::HeaderRecord::setMessageIDHeader(jUnsignedShortInteger value)
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
const unsigned int QueryAddition::Header::HeaderRecord::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void QueryAddition::Header::HeaderRecord::encode(unsigned char *bytes)
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

void QueryAddition::Header::HeaderRecord::decode(const unsigned char *bytes)
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

QueryAddition::Header::HeaderRecord &QueryAddition::Header::HeaderRecord::operator=(const HeaderRecord &value)
{
	m_MessageIDHeader = value.m_MessageIDHeader;
	
	return *this;
}

bool QueryAddition::Header::HeaderRecord::operator==(const HeaderRecord &value) const
{
	if (m_MessageIDHeader != value.m_MessageIDHeader)
	{
		return false;
	}
	
	return true;
}

bool QueryAddition::Header::HeaderRecord::operator!=(const HeaderRecord &value) const
{
	return !((*this) == value);
}

QueryAddition::Header::HeaderRecord::HeaderRecord()
{
	m_parent = NULL;
	m_MessageIDHeader = 0xf010;
}

QueryAddition::Header::HeaderRecord::HeaderRecord(const HeaderRecord &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageIDHeader = 0xf010;
	
	/// Copy the values
	m_MessageIDHeader = value.m_MessageIDHeader;
}

QueryAddition::Header::HeaderRecord::~HeaderRecord()
{
}

QueryAddition::Header::HeaderRecord* const QueryAddition::Header::getHeaderRecord()
{
	return &m_HeaderRecord;
}

int QueryAddition::Header::setHeaderRecord(const HeaderRecord &value)
{
	m_HeaderRecord = value;
	setParentPresenceVector();
	return 0;
}

void QueryAddition::Header::setParentPresenceVector()
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
const unsigned int QueryAddition::Header::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRecord.getSize();
	
	return size;
}

void QueryAddition::Header::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRecord.encode(bytes + pos);
	pos += m_HeaderRecord.getSize();
}

void QueryAddition::Header::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRecord.decode(bytes + pos);
	pos += m_HeaderRecord.getSize();
}

QueryAddition::Header &QueryAddition::Header::operator=(const Header &value)
{
	m_HeaderRecord = value.m_HeaderRecord;
	m_HeaderRecord.setParent(this);
	
	return *this;
}

bool QueryAddition::Header::operator==(const Header &value) const
{
	if (m_HeaderRecord != value.m_HeaderRecord)
	{
		return false;
	}
	return true;
}

bool QueryAddition::Header::operator!=(const Header &value) const
{
	return !((*this) == value);
}

QueryAddition::Header::Header()
{
	m_HeaderRecord.setParent(this);
	/// No Initialization of m_HeaderRecord necessary.
}

QueryAddition::Header::Header(const Header &value)
{
	/// Initiliaze the protected variables
	m_HeaderRecord.setParent(this);
	/// No Initialization of m_HeaderRecord necessary.
	
	/// Copy the values
	m_HeaderRecord = value.m_HeaderRecord;
	m_HeaderRecord.setParent(this);
}

QueryAddition::Header::~Header()
{
}

QueryAddition::Header* const QueryAddition::getHeader()
{
	return &m_Header;
}

int QueryAddition::setHeader(const Header &value)
{
	m_Header = value;
	return 0;
}

void QueryAddition::AdditionInputBody::AdditionInput::setParent(AdditionInputBody* parent)
{
	m_parent = parent;
}

void QueryAddition::AdditionInputBody::AdditionInput::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger QueryAddition::AdditionInputBody::AdditionInput::getA1()
{
	return m_A1;
}

int QueryAddition::AdditionInputBody::AdditionInput::setA1(jUnsignedInteger value)
{
	m_A1 = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedInteger QueryAddition::AdditionInputBody::AdditionInput::getA2()
{
	return m_A2;
}

int QueryAddition::AdditionInputBody::AdditionInput::setA2(jUnsignedInteger value)
{
	m_A2 = value;
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
const unsigned int QueryAddition::AdditionInputBody::AdditionInput::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void QueryAddition::AdditionInputBody::AdditionInput::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_A1Temp;
	
	m_A1Temp = JSIDL_v_1_0::correctEndianness(m_A1);
	memcpy(bytes + pos, &m_A1Temp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
	jUnsignedInteger m_A2Temp;
	
	m_A2Temp = JSIDL_v_1_0::correctEndianness(m_A2);
	memcpy(bytes + pos, &m_A2Temp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
}

void QueryAddition::AdditionInputBody::AdditionInput::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_A1Temp;
	
	memcpy(&m_A1Temp, bytes + pos, sizeof(jUnsignedInteger));
	m_A1 = JSIDL_v_1_0::correctEndianness(m_A1Temp);
	pos += sizeof(jUnsignedInteger);
	jUnsignedInteger m_A2Temp;
	
	memcpy(&m_A2Temp, bytes + pos, sizeof(jUnsignedInteger));
	m_A2 = JSIDL_v_1_0::correctEndianness(m_A2Temp);
	pos += sizeof(jUnsignedInteger);
}

QueryAddition::AdditionInputBody::AdditionInput &QueryAddition::AdditionInputBody::AdditionInput::operator=(const AdditionInput &value)
{
	m_A1 = value.m_A1;
	m_A2 = value.m_A2;
	
	return *this;
}

bool QueryAddition::AdditionInputBody::AdditionInput::operator==(const AdditionInput &value) const
{
	if (m_A1 != value.m_A1)
	{
		return false;
	}
	if (m_A2 != value.m_A2)
	{
		return false;
	}
	
	return true;
}

bool QueryAddition::AdditionInputBody::AdditionInput::operator!=(const AdditionInput &value) const
{
	return !((*this) == value);
}

QueryAddition::AdditionInputBody::AdditionInput::AdditionInput()
{
	m_parent = NULL;
	m_A1 = 0;
	m_A2 = 0;
}

QueryAddition::AdditionInputBody::AdditionInput::AdditionInput(const AdditionInput &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_A1 = 0;
	m_A2 = 0;
	
	/// Copy the values
	m_A1 = value.m_A1;
	m_A2 = value.m_A2;
}

QueryAddition::AdditionInputBody::AdditionInput::~AdditionInput()
{
}

QueryAddition::AdditionInputBody::AdditionInput* const QueryAddition::AdditionInputBody::getAdditionInput()
{
	return &m_AdditionInput;
}

int QueryAddition::AdditionInputBody::setAdditionInput(const AdditionInput &value)
{
	m_AdditionInput = value;
	setParentPresenceVector();
	return 0;
}

void QueryAddition::AdditionInputBody::setParentPresenceVector()
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
const unsigned int QueryAddition::AdditionInputBody::getSize()
{
	unsigned int size = 0;
	
	size += m_AdditionInput.getSize();
	
	return size;
}

void QueryAddition::AdditionInputBody::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AdditionInput.encode(bytes + pos);
	pos += m_AdditionInput.getSize();
}

void QueryAddition::AdditionInputBody::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_AdditionInput.decode(bytes + pos);
	pos += m_AdditionInput.getSize();
}

QueryAddition::AdditionInputBody &QueryAddition::AdditionInputBody::operator=(const AdditionInputBody &value)
{
	m_AdditionInput = value.m_AdditionInput;
	m_AdditionInput.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool QueryAddition::AdditionInputBody::operator==(const AdditionInputBody &value) const
{
	if (m_AdditionInput != value.m_AdditionInput)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool QueryAddition::AdditionInputBody::operator!=(const AdditionInputBody &value) const
{
	return !((*this) == value);
}

QueryAddition::AdditionInputBody::AdditionInputBody()
{
	m_AdditionInput.setParent(this);
	/// No Initialization of m_AdditionInput necessary.
}

QueryAddition::AdditionInputBody::AdditionInputBody(const AdditionInputBody &value)
{
	/// Initiliaze the protected variables
	m_AdditionInput.setParent(this);
	/// No Initialization of m_AdditionInput necessary.
	
	/// Copy the values
	m_AdditionInput = value.m_AdditionInput;
	m_AdditionInput.setParent(this);
	/// This code is currently not supported
}

QueryAddition::AdditionInputBody::~AdditionInputBody()
{
}

QueryAddition::AdditionInputBody* const QueryAddition::getAdditionInputBody()
{
	return &m_AdditionInputBody;
}

int QueryAddition::setAdditionInputBody(const AdditionInputBody &value)
{
	m_AdditionInputBody = value;
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
const unsigned int QueryAddition::getSize()
{
	unsigned int size = 0;
	
	size += m_Header.getSize();
	size += m_AdditionInputBody.getSize();
	
	return size;
}

void QueryAddition::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Header.encode(bytes + pos);
	pos += m_Header.getSize();
	m_AdditionInputBody.encode(bytes + pos);
	pos += m_AdditionInputBody.getSize();
}

void QueryAddition::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Header.decode(bytes + pos);
	pos += m_Header.getSize();
	m_AdditionInputBody.decode(bytes + pos);
	pos += m_AdditionInputBody.getSize();
}

QueryAddition &QueryAddition::operator=(const QueryAddition &value)
{
	m_Header = value.m_Header;
	m_AdditionInputBody = value.m_AdditionInputBody;
	
	return *this;
}

bool QueryAddition::operator==(const QueryAddition &value) const
{
	if (m_Header != value.m_Header)
	{
		return false;
	}
	if (m_AdditionInputBody != value.m_AdditionInputBody)
	{
		return false;
	}
	
	return true;
}

bool QueryAddition::operator!=(const QueryAddition &value) const
{
	return !((*this) == value);
}

QueryAddition::QueryAddition()
{
	/// No Initialization of m_Header necessary.
	/// No Initialization of m_AdditionInputBody necessary.
	m_IsCommand = false;
}

QueryAddition::QueryAddition(const QueryAddition &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_Header necessary.
	/// No Initialization of m_AdditionInputBody necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_Header = value.m_Header;
	m_AdditionInputBody = value.m_AdditionInputBody;
}

QueryAddition::~QueryAddition()
{
}


}
