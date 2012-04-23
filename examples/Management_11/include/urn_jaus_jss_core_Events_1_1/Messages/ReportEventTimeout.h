#ifndef URN_JAUS_JSS_CORE_EVENTS_1_1_REPORTEVENTTIMEOUT_H
#define URN_JAUS_JSS_CORE_EVENTS_1_1_REPORTEVENTTIMEOUT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Events_1_1
{

class DllExport ReportEventTimeout: public JTS::Message
{
public:
	static const int ID = 0x41f2;
	
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
		class DllExport ReportTimoutRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getTimeout();
			int setTimeout(jUnsignedByte value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ReportTimoutRec &operator=(const ReportTimoutRec &value);
			bool operator==(const ReportTimoutRec &value) const;
			bool operator!=(const ReportTimoutRec &value) const;
			ReportTimoutRec();
			ReportTimoutRec(const ReportTimoutRec &value);
			virtual ~ReportTimoutRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_Timeout;
		};
	
		ReportTimoutRec* const getReportTimoutRec();
		int setReportTimoutRec(const ReportTimoutRec &value);
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
		ReportTimoutRec m_ReportTimoutRec;
	};

	unsigned int getID() { return ID; };
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportEventTimeout &operator=(const ReportEventTimeout &value);
	bool operator==(const ReportEventTimeout &value) const;
	bool operator!=(const ReportEventTimeout &value) const;
	ReportEventTimeout();
	ReportEventTimeout(const ReportEventTimeout &value);
	virtual ~ReportEventTimeout();

protected:
	MsgHeader m_MsgHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_EVENTS_1_1_REPORTEVENTTIMEOUT_H
