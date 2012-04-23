#ifndef URN_JTS_EXAMPLE_MANAGEMENT_OCU_1_0_MENUITEMENTERED_H
#define URN_JTS_EXAMPLE_MANAGEMENT_OCU_1_0_MENUITEMENTERED_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jts_example_management_ocu_1_0
{

class DllExport MenuItemEntered: public JTS::InternalEvent
{
public:
	class DllExport MenuItemEnteredBody
	{
	public:
		class DllExport MenuItemEnteredRecord
		{
		public:
			void setParent(MenuItemEnteredBody* parent);
			void setParentPresenceVector();
			jInteger getSelection();
			int setSelection(jInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			MenuItemEnteredRecord &operator=(const MenuItemEnteredRecord &value);
			bool operator==(const MenuItemEnteredRecord &value) const;
			bool operator!=(const MenuItemEnteredRecord &value) const;
			MenuItemEnteredRecord();
			MenuItemEnteredRecord(const MenuItemEnteredRecord &value);
			virtual ~MenuItemEnteredRecord();
		
		protected:
			MenuItemEnteredBody* m_parent;
			jInteger m_Selection;
		};
	
		MenuItemEnteredRecord* const getMenuItemEnteredRecord();
		int setMenuItemEnteredRecord(const MenuItemEnteredRecord &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		MenuItemEnteredBody &operator=(const MenuItemEnteredBody &value);
		bool operator==(const MenuItemEnteredBody &value) const;
		bool operator!=(const MenuItemEnteredBody &value) const;
		MenuItemEnteredBody();
		MenuItemEnteredBody(const MenuItemEnteredBody &value);
		virtual ~MenuItemEnteredBody();
	
	protected:
		MenuItemEnteredRecord m_MenuItemEnteredRecord;
	};

	MenuItemEnteredBody* const getMenuItemEnteredBody();
	int setMenuItemEnteredBody(const MenuItemEnteredBody &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	MenuItemEntered &operator=(const MenuItemEntered &value);
	bool operator==(const MenuItemEntered &value) const;
	bool operator!=(const MenuItemEntered &value) const;
	MenuItemEntered();
	MenuItemEntered(const MenuItemEntered &value);
	virtual ~MenuItemEntered();

protected:
	MenuItemEnteredBody m_MenuItemEnteredBody;
};

}

#endif // URN_JTS_EXAMPLE_MANAGEMENT_OCU_1_0_MENUITEMENTERED_H
