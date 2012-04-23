#ifndef URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_QUERYELEMENT_H
#define URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_QUERYELEMENT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

class DllExport QueryElement: public JTS::Message
{
public:
	static const int ID = 0x241a;
	
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
	class DllExport Body
	{
	public:
		class DllExport QueryElementRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getElementUID();
			int setElementUID(jUnsignedShortInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			QueryElementRec &operator=(const QueryElementRec &value);
			bool operator==(const QueryElementRec &value) const;
			bool operator!=(const QueryElementRec &value) const;
			QueryElementRec();
			QueryElementRec(const QueryElementRec &value);
			virtual ~QueryElementRec();
		
		protected:
			Body* m_parent;
			jUnsignedShortInteger m_ElementUID;
		};
	
		QueryElementRec* const getQueryElementRec();
		int setQueryElementRec(const QueryElementRec &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		Body &operator=(const Body &value);
		bool operator==(const Body &value) const;
		bool operator!=(const Body &value) const;
		Body();
		Body(const Body &value);
		virtual ~Body();
	
	protected:
		QueryElementRec m_QueryElementRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryElement &operator=(const QueryElement &value);
	bool operator==(const QueryElement &value) const;
	bool operator!=(const QueryElement &value) const;
	QueryElement();
	QueryElement(const QueryElement &value);
	virtual ~QueryElement();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_QUERYELEMENT_H
