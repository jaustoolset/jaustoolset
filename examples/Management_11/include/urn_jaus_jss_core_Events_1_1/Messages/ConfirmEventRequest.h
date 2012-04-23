#ifndef URN_JAUS_JSS_CORE_EVENTS_1_1_CONFIRMEVENTREQUEST_H
#define URN_JAUS_JSS_CORE_EVENTS_1_1_CONFIRMEVENTREQUEST_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Events_1_1
{

class DllExport ConfirmEventRequest: public JTS::Message
{
public:
	static const int ID = 0x01f3;
	
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
		class DllExport ConfirmEventRequestRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getRequestID();
			int setRequestID(jUnsignedByte value);
			jUnsignedByte getEventID();
			int setEventID(jUnsignedByte value);
			double getConfirmedPeriodicRate();
			int setConfirmedPeriodicRate(double value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ConfirmEventRequestRec &operator=(const ConfirmEventRequestRec &value);
			bool operator==(const ConfirmEventRequestRec &value) const;
			bool operator!=(const ConfirmEventRequestRec &value) const;
			ConfirmEventRequestRec();
			ConfirmEventRequestRec(const ConfirmEventRequestRec &value);
			virtual ~ConfirmEventRequestRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_RequestID;
			jUnsignedByte m_EventID;
			jUnsignedShortInteger m_ConfirmedPeriodicRate;
		};
	
		ConfirmEventRequestRec* const getConfirmEventRequestRec();
		int setConfirmEventRequestRec(const ConfirmEventRequestRec &value);
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
		ConfirmEventRequestRec m_ConfirmEventRequestRec;
	};

	unsigned int getID() { return ID; };
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ConfirmEventRequest &operator=(const ConfirmEventRequest &value);
	bool operator==(const ConfirmEventRequest &value) const;
	bool operator!=(const ConfirmEventRequest &value) const;
	ConfirmEventRequest();
	ConfirmEventRequest(const ConfirmEventRequest &value);
	virtual ~ConfirmEventRequest();

protected:
	MsgHeader m_MsgHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_EVENTS_1_1_CONFIRMEVENTREQUEST_H
