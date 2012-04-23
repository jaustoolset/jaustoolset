#include "urn_jaus_jss_core_Management_1_1/InternalEvents/BroadcastGlobal.h"

namespace urn_jaus_jss_core_Management_1_1
{

void BroadcastGlobal::Body::BroadcastRec::setParent(Body* parent)
{
	m_parent = parent;
}

void BroadcastGlobal::Body::BroadcastRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte BroadcastGlobal::Body::BroadcastRec::getPresenceVector()
{
	return m_PresenceVector;
}

int BroadcastGlobal::Body::BroadcastRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
	return 0;
}

bool BroadcastGlobal::Body::BroadcastRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

void BroadcastGlobal::Body::BroadcastRec::DestinationID::setParent(BroadcastRec* parent)
{
	m_parent = parent;
}

void BroadcastGlobal::Body::BroadcastRec::DestinationID::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger BroadcastGlobal::Body::BroadcastRec::DestinationID::getComponentID()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<8> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 7; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int BroadcastGlobal::Body::BroadcastRec::DestinationID::setComponentID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 255)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<8> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 7; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger BroadcastGlobal::Body::BroadcastRec::DestinationID::getNodeID()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<8> sfbs;
	int i = 0;
	
	for (int index = 8; index <= 15; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int BroadcastGlobal::Body::BroadcastRec::DestinationID::setNodeID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 255)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<8> sfbs((int)value);
		int i = 0;
		
		for (int index = 8; index <= 15; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger BroadcastGlobal::Body::BroadcastRec::DestinationID::getSubsystemID()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<16> sfbs;
	int i = 0;
	
	for (int index = 16; index <= 31; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int BroadcastGlobal::Body::BroadcastRec::DestinationID::setSubsystemID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 65535)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<16> sfbs((int)value);
		int i = 0;
		
		for (int index = 16; index <= 31; index++)
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
const unsigned int BroadcastGlobal::Body::BroadcastRec::DestinationID::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void BroadcastGlobal::Body::BroadcastRec::DestinationID::encode(unsigned char *bytes)
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

void BroadcastGlobal::Body::BroadcastRec::DestinationID::decode(const unsigned char *bytes)
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

BroadcastGlobal::Body::BroadcastRec::DestinationID &BroadcastGlobal::Body::BroadcastRec::DestinationID::operator=(const DestinationID &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool BroadcastGlobal::Body::BroadcastRec::DestinationID::operator==(const DestinationID &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool BroadcastGlobal::Body::BroadcastRec::DestinationID::operator!=(const DestinationID &value) const
{
	return !((*this) == value);
}

BroadcastGlobal::Body::BroadcastRec::DestinationID::DestinationID()
{
	m_parent = NULL;
	m_SubFields = 0;
}

BroadcastGlobal::Body::BroadcastRec::DestinationID::DestinationID(const DestinationID &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

BroadcastGlobal::Body::BroadcastRec::DestinationID::~DestinationID()
{
}

BroadcastGlobal::Body::BroadcastRec::DestinationID* const BroadcastGlobal::Body::BroadcastRec::getDestinationID()
{
	return &m_DestinationID;
}

int BroadcastGlobal::Body::BroadcastRec::setDestinationID(const DestinationID &value)
{
	m_DestinationID = value;
	setParentPresenceVector();
	return 0;
}

void BroadcastGlobal::Body::BroadcastRec::SourceID::setParent(BroadcastRec* parent)
{
	m_parent = parent;
}

void BroadcastGlobal::Body::BroadcastRec::SourceID::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(0);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger BroadcastGlobal::Body::BroadcastRec::SourceID::getComponentID()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<8> sfbs;
	int i = 0;
	
	for (int index = 0; index <= 7; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int BroadcastGlobal::Body::BroadcastRec::SourceID::setComponentID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 255)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<8> sfbs((int)value);
		int i = 0;
		
		for (int index = 0; index <= 7; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger BroadcastGlobal::Body::BroadcastRec::SourceID::getNodeID()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<8> sfbs;
	int i = 0;
	
	for (int index = 8; index <= 15; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int BroadcastGlobal::Body::BroadcastRec::SourceID::setNodeID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 255)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<8> sfbs((int)value);
		int i = 0;
		
		for (int index = 8; index <= 15; index++)
		{
		    bfbs[index] = sfbs[i++];
		}
		
		m_SubFields = (jUnsignedInteger)bfbs.to_ulong();
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

jUnsignedInteger BroadcastGlobal::Body::BroadcastRec::SourceID::getSubsystemID()
{
	std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
	std::bitset<16> sfbs;
	int i = 0;
	
	for (int index = 16; index <= 31; index++)
	{
	    sfbs[i++] = bfbs[index];
	}
	
	return (jUnsignedInteger)(sfbs.to_ulong());
}

int BroadcastGlobal::Body::BroadcastRec::SourceID::setSubsystemID(jUnsignedInteger value)
{
	if (((value >= 1) && (value <= 65535)))
	{
		std::bitset<sizeof(jUnsignedInteger) * 8> bfbs((int)m_SubFields);
		std::bitset<16> sfbs((int)value);
		int i = 0;
		
		for (int index = 16; index <= 31; index++)
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
const unsigned int BroadcastGlobal::Body::BroadcastRec::SourceID::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void BroadcastGlobal::Body::BroadcastRec::SourceID::encode(unsigned char *bytes)
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

void BroadcastGlobal::Body::BroadcastRec::SourceID::decode(const unsigned char *bytes)
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

BroadcastGlobal::Body::BroadcastRec::SourceID &BroadcastGlobal::Body::BroadcastRec::SourceID::operator=(const SourceID &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool BroadcastGlobal::Body::BroadcastRec::SourceID::operator==(const SourceID &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool BroadcastGlobal::Body::BroadcastRec::SourceID::operator!=(const SourceID &value) const
{
	return !((*this) == value);
}

BroadcastGlobal::Body::BroadcastRec::SourceID::SourceID()
{
	m_parent = NULL;
	m_SubFields = 0;
}

BroadcastGlobal::Body::BroadcastRec::SourceID::SourceID(const SourceID &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

BroadcastGlobal::Body::BroadcastRec::SourceID::~SourceID()
{
}

bool BroadcastGlobal::Body::BroadcastRec::isSourceIDValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

BroadcastGlobal::Body::BroadcastRec::SourceID* const BroadcastGlobal::Body::BroadcastRec::getSourceID()
{
	return &m_SourceID;
}

int BroadcastGlobal::Body::BroadcastRec::setSourceID(const SourceID &value)
{
	m_SourceID = value;
	setPresenceVector(0);
	setParentPresenceVector();
	return 0;
}

bool BroadcastGlobal::Body::BroadcastRec::isPriorityValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

jUnsignedByte BroadcastGlobal::Body::BroadcastRec::getPriority()
{
	return m_Priority;
}

int BroadcastGlobal::Body::BroadcastRec::setPriority(jUnsignedByte value)
{
	if (((value >= 0) && (value <= 3)) || (value == 0) || (value == 1) || (value == 2) || (value == 3))
	{
		m_Priority = value;
		setPresenceVector(1);
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

void BroadcastGlobal::Body::BroadcastRec::MessagePayload::setParent(BroadcastRec* parent)
{
	m_parent = parent;
}

void BroadcastGlobal::Body::BroadcastRec::MessagePayload::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const jUnsignedInteger BroadcastGlobal::Body::BroadcastRec::MessagePayload::getLength() const
{
	return m_Length;
}

const unsigned char *BroadcastGlobal::Body::BroadcastRec::MessagePayload::getData() const
{
	return m_Data;
}

int BroadcastGlobal::Body::BroadcastRec::MessagePayload::set(const jUnsignedInteger &length, const unsigned char *data)
{
	m_Length = length;
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[length];
		memcpy(m_Data, data, length);
	}
	
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
const unsigned int BroadcastGlobal::Body::BroadcastRec::MessagePayload::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void BroadcastGlobal::Body::BroadcastRec::MessagePayload::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedInteger m_LengthTemp;
	
	m_LengthTemp = JSIDL_v_1_0::correctEndianness(m_Length);
	memcpy(bytes+pos, &m_LengthTemp, sizeof(jUnsignedInteger));
	pos += sizeof(jUnsignedInteger);
	
	memcpy(bytes+pos, m_Data, m_Length);
	pos += m_Length;
}

void BroadcastGlobal::Body::BroadcastRec::MessagePayload::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
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

BroadcastGlobal::Body::BroadcastRec::MessagePayload &BroadcastGlobal::Body::BroadcastRec::MessagePayload::operator=(const MessagePayload &value)
{
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

bool BroadcastGlobal::Body::BroadcastRec::MessagePayload::operator==(const MessagePayload &value) const
{
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

bool BroadcastGlobal::Body::BroadcastRec::MessagePayload::operator!=(const MessagePayload &value) const
{
	return !((*this) == value);
}

BroadcastGlobal::Body::BroadcastRec::MessagePayload::MessagePayload()
{
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
}

BroadcastGlobal::Body::BroadcastRec::MessagePayload::MessagePayload(const MessagePayload &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
	
	/// Copy the values
	this->m_Length = value.m_Length;
	
	delete[] m_Data;
	m_Data = NULL;
	
	if (m_Length > 0)
	{
		m_Data = new unsigned char[this->m_Length];
		memcpy(this->m_Data, value.m_Data, this->m_Length);
	}
}

BroadcastGlobal::Body::BroadcastRec::MessagePayload::~MessagePayload()
{
	delete[] m_Data;
}

BroadcastGlobal::Body::BroadcastRec::MessagePayload* const BroadcastGlobal::Body::BroadcastRec::getMessagePayload()
{
	return &m_MessagePayload;
}

int BroadcastGlobal::Body::BroadcastRec::setMessagePayload(const MessagePayload &value)
{
	m_MessagePayload = value;
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
const unsigned int BroadcastGlobal::Body::BroadcastRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	size += m_DestinationID.getSize();
	if (checkPresenceVector(0))
	{
		size += m_SourceID.getSize();
	}
	if (checkPresenceVector(1))
	{
		size += sizeof(jUnsignedByte);
	}
	size += m_MessagePayload.getSize();
	
	return size;
}

void BroadcastGlobal::Body::BroadcastRec::encode(unsigned char *bytes)
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
	m_DestinationID.encode(bytes + pos);
	pos += m_DestinationID.getSize();
	if (checkPresenceVector(0))
	{
		m_SourceID.encode(bytes + pos);
		pos += m_SourceID.getSize();
	}
	if (checkPresenceVector(1))
	{
		jUnsignedByte m_PriorityTemp;
		
		m_PriorityTemp = JSIDL_v_1_0::correctEndianness(m_Priority);
		memcpy(bytes + pos, &m_PriorityTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
	m_MessagePayload.encode(bytes + pos);
	pos += m_MessagePayload.getSize();
}

void BroadcastGlobal::Body::BroadcastRec::decode(const unsigned char *bytes)
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
	m_DestinationID.decode(bytes + pos);
	pos += m_DestinationID.getSize();
	if (checkPresenceVector(0))
	{
		m_SourceID.decode(bytes + pos);
		pos += m_SourceID.getSize();
	}
	if (checkPresenceVector(1))
	{
		jUnsignedByte m_PriorityTemp;
		
		memcpy(&m_PriorityTemp, bytes + pos, sizeof(jUnsignedByte));
		m_Priority = JSIDL_v_1_0::correctEndianness(m_PriorityTemp);
		pos += sizeof(jUnsignedByte);
	}
	m_MessagePayload.decode(bytes + pos);
	pos += m_MessagePayload.getSize();
}

BroadcastGlobal::Body::BroadcastRec &BroadcastGlobal::Body::BroadcastRec::operator=(const BroadcastRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_DestinationID = value.m_DestinationID;
	m_SourceID = value.m_SourceID;
	m_Priority = value.m_Priority;
	m_MessagePayload = value.m_MessagePayload;
	
	return *this;
}

bool BroadcastGlobal::Body::BroadcastRec::operator==(const BroadcastRec &value) const
{
	if (m_PresenceVector != value.m_PresenceVector)
	{
		return false;
	}
	
	if (m_DestinationID != value.m_DestinationID)
	{
		return false;
	}
	
	if (m_SourceID != value.m_SourceID)
	{
		return false;
	}
	if (m_Priority != value.m_Priority)
	{
		return false;
	}
	
	if (m_MessagePayload != value.m_MessagePayload)
	{
		return false;
	}
	
	return true;
}

bool BroadcastGlobal::Body::BroadcastRec::operator!=(const BroadcastRec &value) const
{
	return !((*this) == value);
}

BroadcastGlobal::Body::BroadcastRec::BroadcastRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_DestinationID.setParent(this);
	m_SourceID.setParent(this);
	m_Priority = 0;
	m_MessagePayload.setParent(this);
}

BroadcastGlobal::Body::BroadcastRec::BroadcastRec(const BroadcastRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_PresenceVector = 0;
	m_DestinationID.setParent(this);
	m_SourceID.setParent(this);
	m_Priority = 0;
	m_MessagePayload.setParent(this);
	
	/// Copy the values
	m_PresenceVector = value.m_PresenceVector;
	m_DestinationID = value.m_DestinationID;
	m_SourceID = value.m_SourceID;
	m_Priority = value.m_Priority;
	m_MessagePayload = value.m_MessagePayload;
}

BroadcastGlobal::Body::BroadcastRec::~BroadcastRec()
{
}

BroadcastGlobal::Body::BroadcastRec* const BroadcastGlobal::Body::getBroadcastRec()
{
	return &m_BroadcastRec;
}

int BroadcastGlobal::Body::setBroadcastRec(const BroadcastRec &value)
{
	m_BroadcastRec = value;
	setParentPresenceVector();
	return 0;
}

void BroadcastGlobal::Body::setParentPresenceVector()
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
const unsigned int BroadcastGlobal::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_BroadcastRec.getSize();
	
	return size;
}

void BroadcastGlobal::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_BroadcastRec.encode(bytes + pos);
	pos += m_BroadcastRec.getSize();
}

void BroadcastGlobal::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_BroadcastRec.decode(bytes + pos);
	pos += m_BroadcastRec.getSize();
}

BroadcastGlobal::Body &BroadcastGlobal::Body::operator=(const Body &value)
{
	m_BroadcastRec = value.m_BroadcastRec;
	m_BroadcastRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool BroadcastGlobal::Body::operator==(const Body &value) const
{
	if (m_BroadcastRec != value.m_BroadcastRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool BroadcastGlobal::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

BroadcastGlobal::Body::Body()
{
	m_BroadcastRec.setParent(this);
	/// No Initialization of m_BroadcastRec necessary.
}

BroadcastGlobal::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_BroadcastRec.setParent(this);
	/// No Initialization of m_BroadcastRec necessary.
	
	/// Copy the values
	m_BroadcastRec = value.m_BroadcastRec;
	m_BroadcastRec.setParent(this);
	/// This code is currently not supported
}

BroadcastGlobal::Body::~Body()
{
}

BroadcastGlobal::Body* const BroadcastGlobal::getBody()
{
	return &m_Body;
}

int BroadcastGlobal::setBody(const Body &value)
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
const unsigned int BroadcastGlobal::getSize()
{
	unsigned int size = 0;
	
	size += m_Body.getSize();
	
	return size;
}

void BroadcastGlobal::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.encode(bytes + pos);
	pos += m_Body.getSize();
}

void BroadcastGlobal::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

BroadcastGlobal &BroadcastGlobal::operator=(const BroadcastGlobal &value)
{
	m_Body = value.m_Body;
	
	return *this;
}

bool BroadcastGlobal::operator==(const BroadcastGlobal &value) const
{
	if (m_Body != value.m_Body)
	{
		return false;
	}
	
	return true;
}

bool BroadcastGlobal::operator!=(const BroadcastGlobal &value) const
{
	return !((*this) == value);
}

BroadcastGlobal::BroadcastGlobal()
{
	/// No Initialization of m_Body necessary.
	m_Name = "BroadcastGlobal";
}

BroadcastGlobal::BroadcastGlobal(const BroadcastGlobal &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_Body necessary.
	m_Name = "BroadcastGlobal";
	
	/// Copy the values
	m_Body = value.m_Body;
}

BroadcastGlobal::~BroadcastGlobal()
{
}


}
