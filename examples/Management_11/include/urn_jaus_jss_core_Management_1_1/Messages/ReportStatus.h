#ifndef URN_JAUS_JSS_CORE_MANAGEMENT_1_1_REPORTSTATUS_H
#define URN_JAUS_JSS_CORE_MANAGEMENT_1_1_REPORTSTATUS_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Management_1_1
{

class DllExport ReportStatus: public JTS::Message
{
public:
	static const int ID = 0x4002;
	
	class DllExport MsgHeader
	{
	public:
		class DllExport HeaderRec
		{
		public:
			void setParent(MsgHeader* parent);
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
			MsgHeader* m_parent;
			jUnsignedShortInteger m_MessageID;
		};
	
		HeaderRec* const getHeaderRec();
		int setHeaderRec(const HeaderRec &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		MsgHeader &operator=(const MsgHeader &value);
		bool operator==(const MsgHeader &value) const;
		bool operator!=(const MsgHeader &value) const;
		MsgHeader();
		MsgHeader(const MsgHeader &value);
		virtual ~MsgHeader();
	
	protected:
		HeaderRec m_HeaderRec;
	};
	class DllExport Body
	{
	public:
		class DllExport ReportStatusRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getStatus();
			int setStatus(jUnsignedByte value);
			jUnsignedInteger getReserved();
			int setReserved(jUnsignedInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ReportStatusRec &operator=(const ReportStatusRec &value);
			bool operator==(const ReportStatusRec &value) const;
			bool operator!=(const ReportStatusRec &value) const;
			ReportStatusRec();
			ReportStatusRec(const ReportStatusRec &value);
			virtual ~ReportStatusRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_Status;
			jUnsignedInteger m_Reserved;
		};
	
		ReportStatusRec* const getReportStatusRec();
		int setReportStatusRec(const ReportStatusRec &value);
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
		ReportStatusRec m_ReportStatusRec;
	};

	unsigned int getID() { return ID; };
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportStatus &operator=(const ReportStatus &value);
	bool operator==(const ReportStatus &value) const;
	bool operator!=(const ReportStatus &value) const;
	ReportStatus();
	ReportStatus(const ReportStatus &value);
	virtual ~ReportStatus();

protected:
	MsgHeader m_MsgHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_MANAGEMENT_1_1_REPORTSTATUS_H
