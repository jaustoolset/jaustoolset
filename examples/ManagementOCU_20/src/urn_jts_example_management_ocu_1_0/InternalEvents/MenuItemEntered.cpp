#include "urn_jts_example_management_ocu_1_0/InternalEvents/MenuItemEntered.h"

namespace urn_jts_example_management_ocu_1_0
{

void MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::setParent(MenuItemEnteredBody* parent)
{
	m_parent = parent;
}

void MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::setParentPresenceVector()
{
	if(m_parent != NULL )
	{
		m_parent->setParentPresenceVector();
	}
}

jInteger MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::getSelection()
{
	return m_Selection;
}

int MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::setSelection(jInteger value)
{
	m_Selection = value;
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
const unsigned int MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::getSize()
{
	unsigned int size = 0;
	
	size += sizeof(jInteger);
	
	return size;
}

void MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jInteger m_SelectionTemp;
	
	m_SelectionTemp = JSIDL_v_1_0::correctEndianness(m_Selection);
	memcpy(bytes + pos, &m_SelectionTemp, sizeof(jInteger));
	pos += sizeof(jInteger);
}

void MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	jInteger m_SelectionTemp;
	
	memcpy(&m_SelectionTemp, bytes + pos, sizeof(jInteger));
	m_Selection = JSIDL_v_1_0::correctEndianness(m_SelectionTemp);
	pos += sizeof(jInteger);
}

MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord &MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::operator=(const MenuItemEnteredRecord &value)
{
	m_Selection = value.m_Selection;
	
	return *this;
}

bool MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::operator==(const MenuItemEnteredRecord &value) const
{
	if (m_Selection != value.m_Selection)
	{
		return false;
	}
	
	return true;
}

bool MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::operator!=(const MenuItemEnteredRecord &value) const
{
	return !((*this) == value);
}

MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::MenuItemEnteredRecord()
{
	m_parent = NULL;
	m_Selection = 0;
}

MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::MenuItemEnteredRecord(const MenuItemEnteredRecord &value)
{
	/// Initiliaze the protected variables
	m_parent = NULL;
	m_Selection = 0;
	
	/// Copy the values
	m_Selection = value.m_Selection;
}

MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord::~MenuItemEnteredRecord()
{
}

MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredRecord* const MenuItemEntered::MenuItemEnteredBody::getMenuItemEnteredRecord()
{
	return &m_MenuItemEnteredRecord;
}

int MenuItemEntered::MenuItemEnteredBody::setMenuItemEnteredRecord(const MenuItemEnteredRecord &value)
{
	m_MenuItemEnteredRecord = value;
	setParentPresenceVector();
	return 0;
}

void MenuItemEntered::MenuItemEnteredBody::setParentPresenceVector()
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
const unsigned int MenuItemEntered::MenuItemEnteredBody::getSize()
{
	unsigned int size = 0;
	
	size += m_MenuItemEnteredRecord.getSize();
	
	return size;
}

void MenuItemEntered::MenuItemEnteredBody::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MenuItemEnteredRecord.encode(bytes + pos);
	pos += m_MenuItemEnteredRecord.getSize();
}

void MenuItemEntered::MenuItemEnteredBody::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MenuItemEnteredRecord.decode(bytes + pos);
	pos += m_MenuItemEnteredRecord.getSize();
}

MenuItemEntered::MenuItemEnteredBody &MenuItemEntered::MenuItemEnteredBody::operator=(const MenuItemEnteredBody &value)
{
	m_MenuItemEnteredRecord = value.m_MenuItemEnteredRecord;
	m_MenuItemEnteredRecord.setParent(this);
	/// This code is currently not supported
	
	return *this;
}

bool MenuItemEntered::MenuItemEnteredBody::operator==(const MenuItemEnteredBody &value) const
{
	if (m_MenuItemEnteredRecord != value.m_MenuItemEnteredRecord)
	{
		return false;
	}
	/// This code is currently not supported
	return true;
}

bool MenuItemEntered::MenuItemEnteredBody::operator!=(const MenuItemEnteredBody &value) const
{
	return !((*this) == value);
}

MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredBody()
{
	m_MenuItemEnteredRecord.setParent(this);
	/// No Initialization of m_MenuItemEnteredRecord necessary.
}

MenuItemEntered::MenuItemEnteredBody::MenuItemEnteredBody(const MenuItemEnteredBody &value)
{
	/// Initiliaze the protected variables
	m_MenuItemEnteredRecord.setParent(this);
	/// No Initialization of m_MenuItemEnteredRecord necessary.
	
	/// Copy the values
	m_MenuItemEnteredRecord = value.m_MenuItemEnteredRecord;
	m_MenuItemEnteredRecord.setParent(this);
	/// This code is currently not supported
}

MenuItemEntered::MenuItemEnteredBody::~MenuItemEnteredBody()
{
}

MenuItemEntered::MenuItemEnteredBody* const MenuItemEntered::getMenuItemEnteredBody()
{
	return &m_MenuItemEnteredBody;
}

int MenuItemEntered::setMenuItemEnteredBody(const MenuItemEnteredBody &value)
{
	m_MenuItemEnteredBody = value;
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
const unsigned int MenuItemEntered::getSize()
{
	unsigned int size = 0;
	
	size += m_MenuItemEnteredBody.getSize();
	
	return size;
}

void MenuItemEntered::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MenuItemEnteredBody.encode(bytes + pos);
	pos += m_MenuItemEnteredBody.getSize();
}

void MenuItemEntered::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
	m_MenuItemEnteredBody.decode(bytes + pos);
	pos += m_MenuItemEnteredBody.getSize();
}

MenuItemEntered &MenuItemEntered::operator=(const MenuItemEntered &value)
{
	m_MenuItemEnteredBody = value.m_MenuItemEnteredBody;
	
	return *this;
}

bool MenuItemEntered::operator==(const MenuItemEntered &value) const
{
	if (m_MenuItemEnteredBody != value.m_MenuItemEnteredBody)
	{
		return false;
	}
	
	return true;
}

bool MenuItemEntered::operator!=(const MenuItemEntered &value) const
{
	return !((*this) == value);
}

MenuItemEntered::MenuItemEntered()
{
	/// No Initialization of m_MenuItemEnteredBody necessary.
	m_Name = "MenuItemEntered";
}

MenuItemEntered::MenuItemEntered(const MenuItemEntered &value)
{
	/// Initiliaze the protected variables
	/// No Initialization of m_MenuItemEnteredBody necessary.
	m_Name = "MenuItemEntered";
	
	/// Copy the values
	m_MenuItemEnteredBody = value.m_MenuItemEnteredBody;
}

MenuItemEntered::~MenuItemEntered()
{
}


}
