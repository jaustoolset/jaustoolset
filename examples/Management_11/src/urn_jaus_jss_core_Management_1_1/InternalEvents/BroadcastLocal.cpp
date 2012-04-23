#include "urn_jaus_jss_core_Management_1_1/InternalEvents/BroadcastLocal.h"

namespace urn_jaus_jss_core_Management_1_1
{

void BroadcastLocal::Body::BroadcastRec::setParent(Body* parent)
{
	m_parent = parent;
}

void BroadcastLocal::Body::BroadcastRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedByte BroadcastLocal::Body::BroadcastRec::getPresenceVector()
{
	return m_PresenceVector;
}

int BroadcastLocal::Body::BroadcastRec::setPresenceVector(unsigned int index)
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	pvBitSet[index] = 1;
	m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
	return 0;
}

bool BroadcastLocal::Body::BroadcastRec::checkPresenceVector(unsigned int index) const
{
	std::bitset<sizeof(jUnsignedByte) * 8> pvBitSet((int)(m_PresenceVector));
	
	return (pvBitSet[index] == 1);
}

void BroadcastLocal::Body::BroadcastRec::DestinationID::setParent(BroadcastRec* parent)
{
	m_parent = parent;
}

void BroadcastLocal::Body::BroadcastRec::DestinationID::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger BroadcastLocal::Body::BroadcastRec::DestinationID::getComponentID()
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

int BroadcastLocal::Body::BroadcastRec::DestinationID::setComponentID(jUnsignedInteger value)
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

jUnsignedInteger BroadcastLocal::Body::BroadcastRec::DestinationID::getNodeID()
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

int BroadcastLocal::Body::BroadcastRec::DestinationID::setNodeID(jUnsignedInteger value)
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

jUnsignedInteger BroadcastLocal::Body::BroadcastRec::DestinationID::getSubsystemID()
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

int BroadcastLocal::Body::BroadcastRec::DestinationID::setSubsystemID(jUnsignedInteger value)
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
const unsigned int BroadcastLocal::Body::BroadcastRec::DestinationID::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void BroadcastLocal::Body::BroadcastRec::DestinationID::encode(unsigned char *bytes)
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

void BroadcastLocal::Body::BroadcastRec::DestinationID::decode(const unsigned char *bytes)
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

BroadcastLocal::Body::BroadcastRec::DestinationID &BroadcastLocal::Body::BroadcastRec::DestinationID::operator=(const DestinationID &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool BroadcastLocal::Body::BroadcastRec::DestinationID::operator==(const DestinationID &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool BroadcastLocal::Body::BroadcastRec::DestinationID::operator!=(const DestinationID &value) const
{
	return !((*this) == value);
}

BroadcastLocal::Body::BroadcastRec::DestinationID::DestinationID()
{
	m_parent = NULL;
	m_SubFields = 0;
}

BroadcastLocal::Body::BroadcastRec::DestinationID::DestinationID(const DestinationID &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

BroadcastLocal::Body::BroadcastRec::DestinationID::~DestinationID()
{
}

BroadcastLocal::Body::BroadcastRec::DestinationID* const BroadcastLocal::Body::BroadcastRec::getDestinationID()
{
	return &m_DestinationID;
}

int BroadcastLocal::Body::BroadcastRec::setDestinationID(const DestinationID &value)
{
	m_DestinationID = value;
	setParentPresenceVector();
	return 0;
}

void BroadcastLocal::Body::BroadcastRec::SourceID::setParent(BroadcastRec* parent)
{
	m_parent = parent;
}

void BroadcastLocal::Body::BroadcastRec::SourceID::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setPresenceVector(0);
		m_parent->setParentPresenceVector();
	}
}

jUnsignedInteger BroadcastLocal::Body::BroadcastRec::SourceID::getComponentID()
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

int BroadcastLocal::Body::BroadcastRec::SourceID::setComponentID(jUnsignedInteger value)
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

jUnsignedInteger BroadcastLocal::Body::BroadcastRec::SourceID::getNodeID()
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

int BroadcastLocal::Body::BroadcastRec::SourceID::setNodeID(jUnsignedInteger value)
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

jUnsignedInteger BroadcastLocal::Body::BroadcastRec::SourceID::getSubsystemID()
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

int BroadcastLocal::Body::BroadcastRec::SourceID::setSubsystemID(jUnsignedInteger value)
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
const unsigned int BroadcastLocal::Body::BroadcastRec::SourceID::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	
	return size;
}

void BroadcastLocal::Body::BroadcastRec::SourceID::encode(unsigned char *bytes)
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

void BroadcastLocal::Body::BroadcastRec::SourceID::decode(const unsigned char *bytes)
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

BroadcastLocal::Body::BroadcastRec::SourceID &BroadcastLocal::Body::BroadcastRec::SourceID::operator=(const SourceID &value)
{
	this->m_SubFields = value.m_SubFields;
	
	return *this;
}

bool BroadcastLocal::Body::BroadcastRec::SourceID::operator==(const SourceID &value) const
{
	return (this->m_SubFields == value.m_SubFields);
}

bool BroadcastLocal::Body::BroadcastRec::SourceID::operator!=(const SourceID &value) const
{
	return !((*this) == value);
}

BroadcastLocal::Body::BroadcastRec::SourceID::SourceID()
{
	m_parent = NULL;
	m_SubFields = 0;
}

BroadcastLocal::Body::BroadcastRec::SourceID::SourceID(const SourceID &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubFields = 0;
	
	/// Copy the values
	this->m_SubFields = value.m_SubFields;
}

BroadcastLocal::Body::BroadcastRec::SourceID::~SourceID()
{
}

bool BroadcastLocal::Body::BroadcastRec::isSourceIDValid() const
{
	if (checkPresenceVector(0))
	{
		return true;
	}
	return false;
}

BroadcastLocal::Body::BroadcastRec::SourceID* const BroadcastLocal::Body::BroadcastRec::getSourceID()
{
	return &m_SourceID;
}

int BroadcastLocal::Body::BroadcastRec::setSourceID(const SourceID &value)
{
	m_SourceID = value;
	setPresenceVector(0);
	setParentPresenceVector();
	return 0;
}

bool BroadcastLocal::Body::BroadcastRec::isPriorityValid() const
{
	if (checkPresenceVector(1))
	{
		return true;
	}
	return false;
}

jUnsignedByte BroadcastLocal::Body::BroadcastRec::getPriority()
{
	return m_Priority;
}

int BroadcastLocal::Body::BroadcastRec::setPriority(jUnsignedByte value)
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

void BroadcastLocal::Body::BroadcastRec::MessagePayload::setParent(BroadcastRec* parent)
{
	m_parent = parent;
}

void BroadcastLocal::Body::BroadcastRec::MessagePayload::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const jUnsignedInteger BroadcastLocal::Body::BroadcastRec::MessagePayload::getLength() const
{
	return m_Length;
}

const unsigned char *BroadcastLocal::Body::BroadcastRec::MessagePayload::getData() const
{
	return m_Data;
}

int BroadcastLocal::Body::BroadcastRec::MessagePayload::set(const jUnsignedInteger &length, const unsigned char *data)
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
const unsigned int BroadcastLocal::Body::BroadcastRec::MessagePayload::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedInteger);
	size += m_Length;
	
	return size;
}

void BroadcastLocal::Body::BroadcastRec::MessagePayload::encode(unsigned char *bytes)
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

void BroadcastLocal::Body::BroadcastRec::MessagePayload::decode(const unsigned char *bytes)
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

BroadcastLocal::Body::BroadcastRec::MessagePayload &BroadcastLocal::Body::BroadcastRec::MessagePayload::operator=(const MessagePayload &value)
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

bool BroadcastLocal::Body::BroadcastRec::MessagePayload::operator==(const MessagePayload &value) const
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

bool BroadcastLocal::Body::BroadcastRec::MessagePayload::operator!=(const MessagePayload &value) const
{
	return !((*this) == value);
}

BroadcastLocal::Body::BroadcastRec::MessagePayload::MessagePayload()
{
	m_parent = NULL;
	m_Length = 0;
	m_Data = NULL;
}

BroadcastLocal::Body::BroadcastRec::MessagePayload::MessagePayload(const MessagePayload &value)
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

BroadcastLocal::Body::BroadcastRec::MessagePayload::~MessagePayload()
{
	delete[] m_Data;
}

BroadcastLocal::Body::BroadcastRec::MessagePayload* const BroadcastLocal::Body::BroadcastRec::getMessagePayload()
{
	return &m_MessagePayload;
}

int BroadcastLocal::Body::BroadcastRec::setMessagePayload(const MessagePayload &value)
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
const unsigned int BroadcastLocal::Body::BroadcastRec::getSize()
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

void BroadcastLocal::Body::BroadcastRec::encode(unsigned char *bytes)
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

void BroadcastLocal::Body::BroadcastRec::decode(const unsigned char *bytes)
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

BroadcastLocal::Body::BroadcastRec &BroadcastLocal::Body::BroadcastRec::operator=(const BroadcastRec &value)
{
	m_PresenceVector = value.m_PresenceVector;
	m_DestinationID = value.m_DestinationID;
	m_SourceID = value.m_SourceID;
	m_Priority = value.m_Priority;
	m_MessagePayload = value.m_MessagePayload;
	
	return *this;
}

bool BroadcastLocal::Body::BroadcastRec::operator==(const BroadcastRec &value) const
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

bool BroadcastLocal::Body::BroadcastRec::operator!=(const BroadcastRec &value) const
{
	return !((*this) == value);
}

BroadcastLocal::Body::BroadcastRec::BroadcastRec()
{
	m_parent = NULL;
	m_PresenceVector = 0;
	m_DestinationID.setParent(this);
	m_SourceID.setParent(this);
	m_Priority = 0;
	m_MessagePayload.setParent(this);
}

BroadcastLocal::Body::BroadcastRec::BroadcastRec(const BroadcastRec &value)
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

BroadcastLocal::Body::BroadcastRec::~BroadcastRec()
{
}

BroadcastLocal::Body::BroadcastRec* const BroadcastLocal::Body::getBroadcastRec()
{
	return &m_BroadcastRec;
}

int BroadcastLocal::Body::setBroadcastRec(const BroadcastRec &value)
{
	m_BroadcastRec = value;
	setParentPresenceVector();
	return 0;
}

void BroadcastLocal::Body::setParentPresenceVector()
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
const unsigned int BroadcastLocal::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_BroadcastRec.getSize();
	
	return size;
}

void BroadcastLocal::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_BroadcastRec.encode(bytes + pos);
	pos += m_BroadcastRec.getSize();
}

void BroadcastLocal::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_BroadcastRec.decode(bytes + pos);
	pos += m_BroadcastRec.getSize();
}

BroadcastLocal::Body &BroadcastLocal::Body::operator=(const Body &value)
{
	m_BroadcastRec = value.m_BroadcastRec;
	m_BroadcastRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool BroadcastLocal::Body::operator==(const Body &value) const
{
	if (m_BroadcastRec != value.m_BroadcastRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool BroadcastLocal::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

BroadcastLocal::Body::Body()
{
	m_BroadcastRec.setParent(this);
	/// No Initialization of m_BroadcastRec necessary.
}

BroadcastLocal::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_BroadcastRec.setParent(this);
	/// No Initialization of m_BroadcastRec necessary.
	
	/// Copy the values
	m_BroadcastRec = value.m_BroadcastRec;
	m_BroadcastRec.setParent(this);
	/// This code is currently not supported
}

BroadcastLocal::Body::~Body()
{
}

BroadcastLocal::Body* const BroadcastLocal::getBody()
{
	return &m_Body;
}

int BroadcastLocal::setBody(const Body &value)
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
const unsigned int BroadcastLocal::getSize()
{
	unsigned int size = 0;
	
	size += m_Body.getSize();
	
	return size;
}

void BroadcastLocal::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.encode(bytes + pos);
	pos += m_Body.getSize();
}

void BroadcastLocal::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_Body.decode(bytes + pos);
	pos += m_Body.getSize();
}

BroadcastLocal &BroadcastLocal::operator=(const BroadcastLocal &value)
{
	m_Body = value.m_Body;
	
	return *this;
}

bool BroadcastLocal::operator==(const BroadcastLocal &value) const
{
	if (m_Body != value.m_Body)
	{
		return false;
	}
	
	return true;
}

bool BroadcastLocal::operator!=(const BroadcastLocal &value) const
{
	return !((*this) == value);
}

BroadcastLocal::BroadcastLocal()
{
	/// No Initialization of m_Body necessary.
	m_Name = "BroadcastLocal";
}

BroadcastLocal::BroadcastLocal(const BroadcastLocal &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_Body necessary.
	m_Name = "BroadcastLocal";
	
	/// Copy the values
	m_Body = value.m_Body;
}

BroadcastLocal::~BroadcastLocal()
{
}


}
