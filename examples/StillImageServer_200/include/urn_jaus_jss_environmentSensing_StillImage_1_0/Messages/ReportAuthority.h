#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTAUTHORITY_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTAUTHORITY_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

class DllExport ReportAuthority: public JTS::Message
{
public:
	static const int ID = 0x4001;
	
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
		class DllExport ReportAuthorityRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getAuthorityCode();
			int setAuthorityCode(jUnsignedByte value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ReportAuthorityRec &operator=(const ReportAuthorityRec &value);
			bool operator==(const ReportAuthorityRec &value) const;
			bool operator!=(const ReportAuthorityRec &value) const;
			ReportAuthorityRec();
			ReportAuthorityRec(const ReportAuthorityRec &value);
			virtual ~ReportAuthorityRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_AuthorityCode;
		};
	
		ReportAuthorityRec* const getReportAuthorityRec();
		int setReportAuthorityRec(const ReportAuthorityRec &value);
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
		ReportAuthorityRec m_ReportAuthorityRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportAuthority &operator=(const ReportAuthority &value);
	bool operator==(const ReportAuthority &value) const;
	bool operator!=(const ReportAuthority &value) const;
	ReportAuthority();
	ReportAuthority(const ReportAuthority &value);
	virtual ~ReportAuthority();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTAUTHORITY_H
