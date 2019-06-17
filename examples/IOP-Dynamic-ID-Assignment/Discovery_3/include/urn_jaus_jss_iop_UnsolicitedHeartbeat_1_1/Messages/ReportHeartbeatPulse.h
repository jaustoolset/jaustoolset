#ifndef URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_REPORTHEARTBEATPULSE_H
#define URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_REPORTHEARTBEATPULSE_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1
{

class DllExport ReportHeartbeatPulse: public JTS::Message
{
public:
	static const int ID = 0x4202;
	
	class DllExport JTS_DefaultHeader
	{
	public:
		class DllExport DefaultHeaderRec
		{
		public:
			void setParent(JTS_DefaultHeader* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getMessageID();
			int setMessageID(jUnsignedShortInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			DefaultHeaderRec &operator=(const DefaultHeaderRec &value);
			bool operator==(const DefaultHeaderRec &value) const;
			bool operator!=(const DefaultHeaderRec &value) const;
			DefaultHeaderRec();
			DefaultHeaderRec(const DefaultHeaderRec &value);
			virtual ~DefaultHeaderRec();
		
		protected:
			JTS_DefaultHeader* m_parent;
			jUnsignedShortInteger m_MessageID;
		};
	
		DefaultHeaderRec* const getDefaultHeaderRec();
		int setDefaultHeaderRec(const DefaultHeaderRec &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		JTS_DefaultHeader &operator=(const JTS_DefaultHeader &value);
		bool operator==(const JTS_DefaultHeader &value) const;
		bool operator!=(const JTS_DefaultHeader &value) const;
		JTS_DefaultHeader();
		JTS_DefaultHeader(const JTS_DefaultHeader &value);
		virtual ~JTS_DefaultHeader();
	
	protected:
		DefaultHeaderRec m_DefaultHeaderRec;
	};

	unsigned int getID() { return ID; };
	JTS_DefaultHeader* const getJTS_DefaultHeader();
	int setJTS_DefaultHeader(const JTS_DefaultHeader &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportHeartbeatPulse &operator=(const ReportHeartbeatPulse &value);
	bool operator==(const ReportHeartbeatPulse &value) const;
	bool operator!=(const ReportHeartbeatPulse &value) const;
	ReportHeartbeatPulse();
	ReportHeartbeatPulse(const ReportHeartbeatPulse &value);
	virtual ~ReportHeartbeatPulse();

protected:
	JTS_DefaultHeader m_JTS_DefaultHeader;
};

}

#endif // URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_REPORTHEARTBEATPULSE_H
