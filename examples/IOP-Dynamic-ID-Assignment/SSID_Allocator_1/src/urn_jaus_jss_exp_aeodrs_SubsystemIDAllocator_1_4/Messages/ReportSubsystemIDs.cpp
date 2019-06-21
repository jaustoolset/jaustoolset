#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/Messages/ReportSubsystemIDs.h"

namespace urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4
{

void ReportSubsystemIDs::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void ReportSubsystemIDs::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportSubsystemIDs::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int ReportSubsystemIDs::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int ReportSubsystemIDs::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void ReportSubsystemIDs::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void ReportSubsystemIDs::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

ReportSubsystemIDs::AppHeader::HeaderRec &ReportSubsystemIDs::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool ReportSubsystemIDs::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool ReportSubsystemIDs::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

ReportSubsystemIDs::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0xfb02;
}

ReportSubsystemIDs::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0xfb02;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

ReportSubsystemIDs::AppHeader::HeaderRec::~HeaderRec()
{
}

ReportSubsystemIDs::AppHeader::HeaderRec* const ReportSubsystemIDs::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int ReportSubsystemIDs::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportSubsystemIDs::AppHeader::setParentPresenceVector()
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
const unsigned int ReportSubsystemIDs::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void ReportSubsystemIDs::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void ReportSubsystemIDs::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

ReportSubsystemIDs::AppHeader &ReportSubsystemIDs::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool ReportSubsystemIDs::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool ReportSubsystemIDs::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

ReportSubsystemIDs::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

ReportSubsystemIDs::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

ReportSubsystemIDs::AppHeader::~AppHeader()
{
}

ReportSubsystemIDs::AppHeader* const ReportSubsystemIDs::getAppHeader()
{
	return &m_AppHeader;
}

int ReportSubsystemIDs::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::setParent(Body* parent)
{
	m_parent = parent;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::setParent(ReportSubsystemIDsSeq* parent)
{
	m_parent = parent;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jByte ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::getSubsystemIDType()
{
	return m_SubsystemIDType;
}

int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::setSubsystemIDType(jByte value)
{
	if ((value == 0) || (value == 1) || (value == 2) || (value == 3))
	{
		m_SubsystemIDType = value;
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
const unsigned int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jByte);
	
	return size;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jByte m_SubsystemIDTypeTemp;
	
	m_SubsystemIDTypeTemp = JSIDL_v_1_0::correctEndianness(m_SubsystemIDType);
	memcpy(bytes + pos, &m_SubsystemIDTypeTemp, sizeof(jByte));
	pos += sizeof(jByte);
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jByte m_SubsystemIDTypeTemp;
	
	memcpy(&m_SubsystemIDTypeTemp, bytes + pos, sizeof(jByte));
	m_SubsystemIDType = JSIDL_v_1_0::correctEndianness(m_SubsystemIDTypeTemp);
	pos += sizeof(jByte);
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec &ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::operator=(const SubsystemIDTypeRec &value)
{
	m_SubsystemIDType = value.m_SubsystemIDType;
	
	return *this;
}

bool ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::operator==(const SubsystemIDTypeRec &value) const
{
	if (m_SubsystemIDType != value.m_SubsystemIDType)
	{
		return false;
	}
	
	return true;
}

bool ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::operator!=(const SubsystemIDTypeRec &value) const
{
	return !((*this) == value);
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::SubsystemIDTypeRec()
{
	m_parent = NULL;
	m_SubsystemIDType = 0;
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::SubsystemIDTypeRec(const SubsystemIDTypeRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubsystemIDType = 0;
	
	/// Copy the values
	m_SubsystemIDType = value.m_SubsystemIDType;
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec::~SubsystemIDTypeRec()
{
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDTypeRec* const ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::getSubsystemIDTypeRec()
{
	return &m_SubsystemIDTypeRec;
}

int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::setSubsystemIDTypeRec(const SubsystemIDTypeRec &value)
{
	m_SubsystemIDTypeRec = value;
	setParentPresenceVector();
	return 0;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::setParent(ReportSubsystemIDsSeq* parent)
{
	m_parent = parent;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::setParent(SubsystemIDsList* parent)
{
	m_parent = parent;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::getSubsystemID()
{
	return m_SubsystemID;
}

int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::setSubsystemID(jUnsignedShortInteger value)
{
	m_SubsystemID = value;
	setParentPresenceVector();
	return 0;
}

jUnsignedShortInteger ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::getSubsystemType()
{
	return m_SubsystemType;
}

int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::setSubsystemType(jUnsignedShortInteger value)
{
	if ((value == 10001) || (value == 20001) || (value == 30001))
	{
		m_SubsystemType = value;
		setParentPresenceVector();
		return 0;
	}
	return 1;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::setParent(SubsystemIDRec* parent)
{
	m_parent = parent;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

const unsigned int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::getMAC_DimensionSize() const
{
	return m_MAC_DimensionSize;
}

jUnsignedByte ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::getMac(unsigned int MAC_Dimension)
{
	unsigned int index = MAC_Dimension;
	
	return m_Mac[index];
}

int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::setMac(unsigned int MAC_Dimension, jUnsignedByte value)
{
	unsigned int index = MAC_Dimension;
	
	m_Mac[index] = value;
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
const unsigned int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte) * 6;
	
	return size;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_MacTemp;
	
	for (unsigned int i = 0; i < 6; i++)
	{
		m_MacTemp = JSIDL_v_1_0::correctEndianness(m_Mac[i]);
		memcpy(bytes + pos, &m_MacTemp, sizeof(jUnsignedByte));
		pos += sizeof(jUnsignedByte);
	}
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte m_MacTemp;
	
	for (unsigned int i = 0; i < 6; i++)
	{
		memcpy(&m_MacTemp, bytes + pos, sizeof(jUnsignedByte));
		m_Mac[i] = JSIDL_v_1_0::correctEndianness(m_MacTemp);
		pos += sizeof(jUnsignedByte);
	}
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr &ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::operator=(const MACaddr &value)
{
	// not yet implemented
	memcpy(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6);
	
	return *this;
}

bool ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::operator==(const MACaddr &value) const
{
	// not yet implemented
	if (memcmp(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6) != 0)
	{
		return false;
	}
	
	return true;
}

bool ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::operator!=(const MACaddr &value) const
{
	return !((*this) == value);
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::MACaddr()
{
	m_parent = NULL;
	m_MAC_DimensionSize = 6;
	memset( m_Mac, 0, sizeof(jUnsignedByte) * 6);
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::MACaddr(const MACaddr &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MAC_DimensionSize = 6;
	memset( m_Mac, 0, sizeof(jUnsignedByte) * 6);
	
	/// Copy the values
	// not yet implemented
	memcpy(m_Mac, value.m_Mac, sizeof(jUnsignedByte) * 6);
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr::~MACaddr()
{
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::MACaddr* const ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::getMACaddr()
{
	return &m_MACaddr;
}

int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::setMACaddr(const MACaddr &value)
{
	m_MACaddr = value;
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
const unsigned int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	size += sizeof(jUnsignedShortInteger);
	size += m_MACaddr.getSize();
	
	return size;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SubsystemIDTemp;
	
	m_SubsystemIDTemp = JSIDL_v_1_0::correctEndianness(m_SubsystemID);
	memcpy(bytes + pos, &m_SubsystemIDTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedShortInteger m_SubsystemTypeTemp;
	
	m_SubsystemTypeTemp = JSIDL_v_1_0::correctEndianness(m_SubsystemType);
	memcpy(bytes + pos, &m_SubsystemTypeTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
	m_MACaddr.encode(bytes + pos);
	pos += m_MACaddr.getSize();
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_SubsystemIDTemp;
	
	memcpy(&m_SubsystemIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SubsystemID = JSIDL_v_1_0::correctEndianness(m_SubsystemIDTemp);
	pos += sizeof(jUnsignedShortInteger);
	jUnsignedShortInteger m_SubsystemTypeTemp;
	
	memcpy(&m_SubsystemTypeTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_SubsystemType = JSIDL_v_1_0::correctEndianness(m_SubsystemTypeTemp);
	pos += sizeof(jUnsignedShortInteger);
	m_MACaddr.decode(bytes + pos);
	pos += m_MACaddr.getSize();
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec &ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::operator=(const SubsystemIDRec &value)
{
	m_SubsystemID = value.m_SubsystemID;
	m_SubsystemType = value.m_SubsystemType;
	m_MACaddr = value.m_MACaddr;
	
	return *this;
}

bool ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::operator==(const SubsystemIDRec &value) const
{
	if (m_SubsystemID != value.m_SubsystemID)
	{
		return false;
	}
	if (m_SubsystemType != value.m_SubsystemType)
	{
		return false;
	}
	
	if (m_MACaddr != value.m_MACaddr)
	{
		return false;
	}
	
	return true;
}

bool ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::operator!=(const SubsystemIDRec &value) const
{
	return !((*this) == value);
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::SubsystemIDRec()
{
	m_parent = NULL;
	m_SubsystemID = 0;
	m_SubsystemType = 0;
	m_MACaddr.setParent(this);
	/// No Initialization of m_MACaddr necessary.
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::SubsystemIDRec(const SubsystemIDRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubsystemID = 0;
	m_SubsystemType = 0;
	m_MACaddr.setParent(this);
	/// No Initialization of m_MACaddr necessary.
	
	/// Copy the values
	m_SubsystemID = value.m_SubsystemID;
	m_SubsystemType = value.m_SubsystemType;
	m_MACaddr = value.m_MACaddr;
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec::~SubsystemIDRec()
{
}

unsigned int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::getNumberOfElements() const
{
	return (unsigned int) m_SubsystemIDRec.size();
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec* const ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::getElement(unsigned int index)
{
	return &m_SubsystemIDRec.at(index);
}

int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::setElement(unsigned int index, const SubsystemIDRec &value)
{
	if(m_SubsystemIDRec.size()-1 < index)
	{
		return 1;
	}
	
	m_SubsystemIDRec.at(index) = value;
	m_SubsystemIDRec.at(index).setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::addElement(const SubsystemIDRec &value)
{
	m_SubsystemIDRec.push_back(value);
	m_SubsystemIDRec.back().setParent(this);
	setParentPresenceVector();
	return 0;
}

int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::deleteElement(unsigned int index)
{
	if(m_SubsystemIDRec.size()-1 < index)
	{
		return 1;
	}
	
	m_SubsystemIDRec.erase(m_SubsystemIDRec.begin()+index);
	return 0;
}

int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::deleteLastElement()
{
	m_SubsystemIDRec.pop_back();
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
const unsigned int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedByte);
	for (int i = 0; i < m_SubsystemIDRec.size(); i++)
	{
		size += m_SubsystemIDRec[i].getSize();
	}
	
	return size;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size = (jUnsignedByte) m_SubsystemIDRec.size();
	memcpy( bytes, &size, sizeof(size));
	pos += sizeof(size);
	for (int i = 0; i < m_SubsystemIDRec.size(); i++)
	{
		m_SubsystemIDRec[i].encode(bytes + pos);
		pos += m_SubsystemIDRec[i].getSize();
	}
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedByte size;
	memcpy( &size, bytes, sizeof(size));
	pos += sizeof(size);
	m_SubsystemIDRec.resize(size);
	for (int i = 0; i < m_SubsystemIDRec.size(); i++)
	{
		m_SubsystemIDRec[i].decode(bytes + pos);
		pos += m_SubsystemIDRec[i].getSize();
	}
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList &ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::operator=(const SubsystemIDsList &value)
{
	m_SubsystemIDRec.clear();
	
	for (int i = 0; i < value.m_SubsystemIDRec.size(); i++)
	{
		m_SubsystemIDRec.push_back(value.m_SubsystemIDRec[i]);
		m_SubsystemIDRec[i].setParent(this);
	}
	
	return *this;
}

bool ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::operator==(const SubsystemIDsList &value) const
{
	if (m_SubsystemIDRec.size() !=  value.m_SubsystemIDRec.size())
	{
		return false;
	}
	
	for (int i = 0; i < m_SubsystemIDRec.size(); i++)
	{
		if (m_SubsystemIDRec[i] !=  value.m_SubsystemIDRec[i])
		{
			return false;
		}
	}
	
	return true;
}

bool ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::operator!=(const SubsystemIDsList &value) const
{
	return !((*this) == value);
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDsList()
{
	m_parent = NULL;
	for (unsigned int i = 0; i < m_SubsystemIDRec.size(); i++)
	{
		m_SubsystemIDRec[i].setParent(this);
	}
	/// No Initialization of m_SubsystemIDRec necessary.
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDsList(const SubsystemIDsList &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	for (unsigned int i = 0; i < m_SubsystemIDRec.size(); i++)
	{
		m_SubsystemIDRec[i].setParent(this);
	}
	/// No Initialization of m_SubsystemIDRec necessary.
	
	/// Copy the values
	m_SubsystemIDRec.clear();
	
	for (int i = 0; i < value.m_SubsystemIDRec.size(); i++)
	{
		m_SubsystemIDRec.push_back(value.m_SubsystemIDRec[i]);
		m_SubsystemIDRec[i].setParent(this);
	}
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::~SubsystemIDsList()
{
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList* const ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::getSubsystemIDsList()
{
	return &m_SubsystemIDsList;
}

int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::setSubsystemIDsList(const SubsystemIDsList &value)
{
	m_SubsystemIDsList = value;
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
const unsigned int ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::getSize()
{
	unsigned int size = 0;
	
	size += m_SubsystemIDTypeRec.getSize();
	size += m_SubsystemIDsList.getSize();
	
	return size;
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemIDTypeRec.encode(bytes + pos);
	pos += m_SubsystemIDTypeRec.getSize();
	m_SubsystemIDsList.encode(bytes + pos);
	pos += m_SubsystemIDsList.getSize();
}

void ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SubsystemIDTypeRec.decode(bytes + pos);
	pos += m_SubsystemIDTypeRec.getSize();
	m_SubsystemIDsList.decode(bytes + pos);
	pos += m_SubsystemIDsList.getSize();
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq &ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::operator=(const ReportSubsystemIDsSeq &value)
{
	m_SubsystemIDTypeRec = value.m_SubsystemIDTypeRec;
	m_SubsystemIDTypeRec.setParent(this);
	m_SubsystemIDTypeRec = value.m_SubsystemIDTypeRec;
	m_SubsystemIDsList = value.m_SubsystemIDsList;
	m_SubsystemIDsList.setParent(this);
	m_SubsystemIDsList = value.m_SubsystemIDsList;
	
	return *this;
}

bool ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::operator==(const ReportSubsystemIDsSeq &value) const
{
	if (m_SubsystemIDTypeRec != value.m_SubsystemIDTypeRec)
	{
		return false;
	}
	
	if (m_SubsystemIDTypeRec != value.m_SubsystemIDTypeRec)
	{
		return false;
	}
	if (m_SubsystemIDsList != value.m_SubsystemIDsList)
	{
		return false;
	}
	
	if (m_SubsystemIDsList != value.m_SubsystemIDsList)
	{
		return false;
	}
	
	return true;
}

bool ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::operator!=(const ReportSubsystemIDsSeq &value) const
{
	return !((*this) == value);
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::ReportSubsystemIDsSeq()
{
	m_parent = NULL;
	m_SubsystemIDTypeRec.setParent(this);
	/// No Initialization of m_SubsystemIDTypeRec necessary.
	m_SubsystemIDsList.setParent(this);
	/// No Initialization of m_SubsystemIDsList necessary.
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::ReportSubsystemIDsSeq(const ReportSubsystemIDsSeq &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_SubsystemIDTypeRec.setParent(this);
	/// No Initialization of m_SubsystemIDTypeRec necessary.
	m_SubsystemIDsList.setParent(this);
	/// No Initialization of m_SubsystemIDsList necessary.
	
	/// Copy the values
	m_SubsystemIDTypeRec = value.m_SubsystemIDTypeRec;
	m_SubsystemIDTypeRec.setParent(this);
	m_SubsystemIDTypeRec = value.m_SubsystemIDTypeRec;
	m_SubsystemIDsList = value.m_SubsystemIDsList;
	m_SubsystemIDsList.setParent(this);
	m_SubsystemIDsList = value.m_SubsystemIDsList;
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::~ReportSubsystemIDsSeq()
{
}

ReportSubsystemIDs::Body::ReportSubsystemIDsSeq* const ReportSubsystemIDs::Body::getReportSubsystemIDsSeq()
{
	return &m_ReportSubsystemIDsSeq;
}

int ReportSubsystemIDs::Body::setReportSubsystemIDsSeq(const ReportSubsystemIDsSeq &value)
{
	m_ReportSubsystemIDsSeq = value;
	setParentPresenceVector();
	return 0;
}

void ReportSubsystemIDs::Body::setParentPresenceVector()
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
const unsigned int ReportSubsystemIDs::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_ReportSubsystemIDsSeq.getSize();
	
	return size;
}

void ReportSubsystemIDs::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportSubsystemIDsSeq.encode(bytes + pos);
	pos += m_ReportSubsystemIDsSeq.getSize();
}

void ReportSubsystemIDs::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_ReportSubsystemIDsSeq.decode(bytes + pos);
	pos += m_ReportSubsystemIDsSeq.getSize();
}

ReportSubsystemIDs::Body &ReportSubsystemIDs::Body::operator=(const Body &value)
{
	m_ReportSubsystemIDsSeq = value.m_ReportSubsystemIDsSeq;
	m_ReportSubsystemIDsSeq.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool ReportSubsystemIDs::Body::operator==(const Body &value) const
{
	if (m_ReportSubsystemIDsSeq != value.m_ReportSubsystemIDsSeq)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool ReportSubsystemIDs::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

ReportSubsystemIDs::Body::Body()
{
	m_ReportSubsystemIDsSeq.setParent(this);
	/// No Initialization of m_ReportSubsystemIDsSeq necessary.
}

ReportSubsystemIDs::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_ReportSubsystemIDsSeq.setParent(this);
	/// No Initialization of m_ReportSubsystemIDsSeq necessary.
	
	/// Copy the values
	m_ReportSubsystemIDsSeq = value.m_ReportSubsystemIDsSeq;
	m_ReportSubsystemIDsSeq.setParent(this);
	/// This code is currently not supported
}

ReportSubsystemIDs::Body::~Body()
{
}

ReportSubsystemIDs::Body* const ReportSubsystemIDs::getBody()
{
	return &m_Body;
}

int ReportSubsystemIDs::setBody(const Body &value)
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
const unsigned int ReportSubsystemIDs::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void ReportSubsystemIDs::encode(unsigned char *bytes)
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

void ReportSubsystemIDs::decode(const unsigned char *bytes)
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

ReportSubsystemIDs &ReportSubsystemIDs::operator=(const ReportSubsystemIDs &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool ReportSubsystemIDs::operator==(const ReportSubsystemIDs &value) const
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

bool ReportSubsystemIDs::operator!=(const ReportSubsystemIDs &value) const
{
	return !((*this) == value);
}

ReportSubsystemIDs::ReportSubsystemIDs()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
}

ReportSubsystemIDs::ReportSubsystemIDs(const ReportSubsystemIDs &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = false;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

ReportSubsystemIDs::~ReportSubsystemIDs()
{
}


}
