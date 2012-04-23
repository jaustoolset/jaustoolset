#ifndef URN_JAUS_JSS_CORE_DISCOVERY_1_0_QUERYSUBSYSTEMLIST_H
#define URN_JAUS_JSS_CORE_DISCOVERY_1_0_QUERYSUBSYSTEMLIST_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Discovery_1_0
{

class DllExport QuerySubsystemList: public JTS::Message
{
public:
	static const int ID = 0x2b02;
	
	class DllExport AppHeader
	{
	public:
		class DllExport HeaderRec
		{
		public:
			void setParent(AppHeader* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getMessageID();
			int setMessageID(jUnsignedShortInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			HeaderRec &operator=(const HeaderRec &value);
			bool operator==(const HeaderRec &value) const;
			bool operator!=(const HeaderRec &value) const;
			HeaderRec();
			HeaderRec(const HeaderRec &value);
			virtual ~HeaderRec();
		
		protected:
			AppHeader* m_parent;
			jUnsignedShortInteger m_MessageID;
		};
	
		HeaderRec* const getHeaderRec();
		int setHeaderRec(const HeaderRec &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		AppHeader &operator=(const AppHeader &value);
		bool operator==(const AppHeader &value) const;
		bool operator!=(const AppHeader &value) const;
		AppHeader();
		AppHeader(const AppHeader &value);
		virtual ~AppHeader();
	
	protected:
		HeaderRec m_HeaderRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QuerySubsystemList &operator=(const QuerySubsystemList &value);
	bool operator==(const QuerySubsystemList &value) const;
	bool operator!=(const QuerySubsystemList &value) const;
	QuerySubsystemList();
	QuerySubsystemList(const QuerySubsystemList &value);
	virtual ~QuerySubsystemList();

protected:
	AppHeader m_AppHeader;
};

}

#endif // URN_JAUS_JSS_CORE_DISCOVERY_1_0_QUERYSUBSYSTEMLIST_H
