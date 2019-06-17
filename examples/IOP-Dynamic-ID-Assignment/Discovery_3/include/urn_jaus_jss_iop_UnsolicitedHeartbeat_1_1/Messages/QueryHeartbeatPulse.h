#ifndef URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_QUERYHEARTBEATPULSE_H
#define URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_QUERYHEARTBEATPULSE_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1
{

class DllExport QueryHeartbeatPulse: public JTS::Message
{
public:
	static const int ID = 0x2202;
	
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

	unsigned int getID() { return ID; };
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryHeartbeatPulse &operator=(const QueryHeartbeatPulse &value);
	bool operator==(const QueryHeartbeatPulse &value) const;
	bool operator!=(const QueryHeartbeatPulse &value) const;
	QueryHeartbeatPulse();
	QueryHeartbeatPulse(const QueryHeartbeatPulse &value);
	virtual ~QueryHeartbeatPulse();

protected:
	MsgHeader m_MsgHeader;
};

}

#endif // URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_QUERYHEARTBEATPULSE_H
