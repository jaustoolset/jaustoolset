#ifndef URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_REPORTELEMENTCOUNT_H
#define URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_REPORTELEMENTCOUNT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

class DllExport ReportElementCount: public JTS::Message
{
public:
	static const int ID = 0x441c;
	
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
		class DllExport ElementCountRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getElementCount();
			int setElementCount(jUnsignedShortInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ElementCountRec &operator=(const ElementCountRec &value);
			bool operator==(const ElementCountRec &value) const;
			bool operator!=(const ElementCountRec &value) const;
			ElementCountRec();
			ElementCountRec(const ElementCountRec &value);
			virtual ~ElementCountRec();
		
		protected:
			Body* m_parent;
			jUnsignedShortInteger m_ElementCount;
		};
	
		ElementCountRec* const getElementCountRec();
		int setElementCountRec(const ElementCountRec &value);
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
		ElementCountRec m_ElementCountRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportElementCount &operator=(const ReportElementCount &value);
	bool operator==(const ReportElementCount &value) const;
	bool operator!=(const ReportElementCount &value) const;
	ReportElementCount();
	ReportElementCount(const ReportElementCount &value);
	virtual ~ReportElementCount();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_REPORTELEMENTCOUNT_H
