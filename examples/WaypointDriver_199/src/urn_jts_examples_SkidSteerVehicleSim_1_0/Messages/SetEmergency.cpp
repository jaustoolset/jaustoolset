#include "urn_jts_examples_SkidSteerVehicleSim_1_0/Messages/SetEmergency.h"

namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

void SetEmergency::AppHeader::HeaderRec::setParent(AppHeader* parent)
{
	m_parent = parent;
}

void SetEmergency::AppHeader::HeaderRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger SetEmergency::AppHeader::HeaderRec::getMessageID()
{
	return m_MessageID;
}

int SetEmergency::AppHeader::HeaderRec::setMessageID(jUnsignedShortInteger value)
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
const unsigned int SetEmergency::AppHeader::HeaderRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void SetEmergency::AppHeader::HeaderRec::encode(unsigned char *bytes)
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

void SetEmergency::AppHeader::HeaderRec::decode(const unsigned char *bytes)
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

SetEmergency::AppHeader::HeaderRec &SetEmergency::AppHeader::HeaderRec::operator=(const HeaderRec &value)
{
	m_MessageID = value.m_MessageID;
	
	return *this;
}

bool SetEmergency::AppHeader::HeaderRec::operator==(const HeaderRec &value) const
{
	if (m_MessageID != value.m_MessageID)
	{
		return false;
	}
	
	return true;
}

bool SetEmergency::AppHeader::HeaderRec::operator!=(const HeaderRec &value) const
{
	return !((*this) == value);
}

SetEmergency::AppHeader::HeaderRec::HeaderRec()
{
	m_parent = NULL;
	m_MessageID = 0x0006;
}

SetEmergency::AppHeader::HeaderRec::HeaderRec(const HeaderRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_MessageID = 0x0006;
	
	/// Copy the values
	m_MessageID = value.m_MessageID;
}

SetEmergency::AppHeader::HeaderRec::~HeaderRec()
{
}

SetEmergency::AppHeader::HeaderRec* const SetEmergency::AppHeader::getHeaderRec()
{
	return &m_HeaderRec;
}

int SetEmergency::AppHeader::setHeaderRec(const HeaderRec &value)
{
	m_HeaderRec = value;
	setParentPresenceVector();
	return 0;
}

void SetEmergency::AppHeader::setParentPresenceVector()
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
const unsigned int SetEmergency::AppHeader::getSize()
{
	unsigned int size = 0;
	
	size += m_HeaderRec.getSize();
	
	return size;
}

void SetEmergency::AppHeader::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.encode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

void SetEmergency::AppHeader::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_HeaderRec.decode(bytes + pos);
	pos += m_HeaderRec.getSize();
}

SetEmergency::AppHeader &SetEmergency::AppHeader::operator=(const AppHeader &value)
{
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
	
	return *this;
}

bool SetEmergency::AppHeader::operator==(const AppHeader &value) const
{
	if (m_HeaderRec != value.m_HeaderRec)
	{
		return false;
	}
	return true;
}

bool SetEmergency::AppHeader::operator!=(const AppHeader &value) const
{
	return !((*this) == value);
}

SetEmergency::AppHeader::AppHeader()
{
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
}

SetEmergency::AppHeader::AppHeader(const AppHeader &value)
{
	/// Initiliaze the protected variables
	m_HeaderRec.setParent(this);
	/// No Initialization of m_HeaderRec necessary.
	
	/// Copy the values
	m_HeaderRec = value.m_HeaderRec;
	m_HeaderRec.setParent(this);
}

SetEmergency::AppHeader::~AppHeader()
{
}

SetEmergency::AppHeader* const SetEmergency::getAppHeader()
{
	return &m_AppHeader;
}

int SetEmergency::setAppHeader(const AppHeader &value)
{
	m_AppHeader = value;
	return 0;
}

void SetEmergency::Body::SetEmergencyRec::setParent(Body* parent)
{
	m_parent = parent;
}

void SetEmergency::Body::SetEmergencyRec::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jUnsignedShortInteger SetEmergency::Body::SetEmergencyRec::getEmergencyCode()
{
	return m_EmergencyCode;
}

int SetEmergency::Body::SetEmergencyRec::setEmergencyCode(jUnsignedShortInteger value)
{
	if ((value == 1))
	{
		m_EmergencyCode = value;
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
const unsigned int SetEmergency::Body::SetEmergencyRec::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jUnsignedShortInteger);
	
	return size;
}

void SetEmergency::Body::SetEmergencyRec::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_EmergencyCodeTemp;
	
	m_EmergencyCodeTemp = JSIDL_v_1_0::correctEndianness(m_EmergencyCode);
	memcpy(bytes + pos, &m_EmergencyCodeTemp, sizeof(jUnsignedShortInteger));
	pos += sizeof(jUnsignedShortInteger);
}

void SetEmergency::Body::SetEmergencyRec::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jUnsignedShortInteger m_EmergencyCodeTemp;
	
	memcpy(&m_EmergencyCodeTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	m_EmergencyCode = JSIDL_v_1_0::correctEndianness(m_EmergencyCodeTemp);
	pos += sizeof(jUnsignedShortInteger);
}

SetEmergency::Body::SetEmergencyRec &SetEmergency::Body::SetEmergencyRec::operator=(const SetEmergencyRec &value)
{
	m_EmergencyCode = value.m_EmergencyCode;
	
	return *this;
}

bool SetEmergency::Body::SetEmergencyRec::operator==(const SetEmergencyRec &value) const
{
	if (m_EmergencyCode != value.m_EmergencyCode)
	{
		return false;
	}
	
	return true;
}

bool SetEmergency::Body::SetEmergencyRec::operator!=(const SetEmergencyRec &value) const
{
	return !((*this) == value);
}

SetEmergency::Body::SetEmergencyRec::SetEmergencyRec()
{
	m_parent = NULL;
	m_EmergencyCode = 0;
}

SetEmergency::Body::SetEmergencyRec::SetEmergencyRec(const SetEmergencyRec &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_EmergencyCode = 0;
	
	/// Copy the values
	m_EmergencyCode = value.m_EmergencyCode;
}

SetEmergency::Body::SetEmergencyRec::~SetEmergencyRec()
{
}

SetEmergency::Body::SetEmergencyRec* const SetEmergency::Body::getSetEmergencyRec()
{
	return &m_SetEmergencyRec;
}

int SetEmergency::Body::setSetEmergencyRec(const SetEmergencyRec &value)
{
	m_SetEmergencyRec = value;
	setParentPresenceVector();
	return 0;
}

void SetEmergency::Body::setParentPresenceVector()
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
const unsigned int SetEmergency::Body::getSize()
{
	unsigned int size = 0;
	
	size += m_SetEmergencyRec.getSize();
	
	return size;
}

void SetEmergency::Body::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SetEmergencyRec.encode(bytes + pos);
	pos += m_SetEmergencyRec.getSize();
}

void SetEmergency::Body::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_SetEmergencyRec.decode(bytes + pos);
	pos += m_SetEmergencyRec.getSize();
}

SetEmergency::Body &SetEmergency::Body::operator=(const Body &value)
{
	m_SetEmergencyRec = value.m_SetEmergencyRec;
	m_SetEmergencyRec.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool SetEmergency::Body::operator==(const Body &value) const
{
	if (m_SetEmergencyRec != value.m_SetEmergencyRec)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool SetEmergency::Body::operator!=(const Body &value) const
{
	return !((*this) == value);
}

SetEmergency::Body::Body()
{
	m_SetEmergencyRec.setParent(this);
	/// No Initialization of m_SetEmergencyRec necessary.
}

SetEmergency::Body::Body(const Body &value)
{
	/// Initiliaze the protected variables
	m_SetEmergencyRec.setParent(this);
	/// No Initialization of m_SetEmergencyRec necessary.
	
	/// Copy the values
	m_SetEmergencyRec = value.m_SetEmergencyRec;
	m_SetEmergencyRec.setParent(this);
	/// This code is currently not supported
}

SetEmergency::Body::~Body()
{
}

SetEmergency::Body* const SetEmergency::getBody()
{
	return &m_Body;
}

int SetEmergency::setBody(const Body &value)
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
const unsigned int SetEmergency::getSize()
{
	unsigned int size = 0;
	
	size += m_AppHeader.getSize();
	size += m_Body.getSize();
	
	return size;
}

void SetEmergency::encode(unsigned char *bytes)
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

void SetEmergency::decode(const unsigned char *bytes)
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

SetEmergency &SetEmergency::operator=(const SetEmergency &value)
{
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
	
	return *this;
}

bool SetEmergency::operator==(const SetEmergency &value) const
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

bool SetEmergency::operator!=(const SetEmergency &value) const
{
	return !((*this) == value);
}

SetEmergency::SetEmergency()
{
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
}

SetEmergency::SetEmergency(const SetEmergency &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_AppHeader necessary.
	/// No Initialization of m_Body necessary.
	m_IsCommand = true;
	
	/// Copy the values
	m_AppHeader = value.m_AppHeader;
	m_Body = value.m_Body;
}

SetEmergency::~SetEmergency()
{
}


}
