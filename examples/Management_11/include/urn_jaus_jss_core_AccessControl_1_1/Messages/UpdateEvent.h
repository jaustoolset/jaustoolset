#ifndef URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_UPDATEEVENT_H
#define URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_UPDATEEVENT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_AccessControl_1_1
{

class DllExport UpdateEvent: public JTS::Message
{
public:
	static const int ID = 0x01f1;
	
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
		class DllExport UpdateEventRec
		{
		public:
			class DllExport QueryMessage
			{
			public:
				void setParent(UpdateEventRec* parent);
				void setParentPresenceVector();
				const jUnsignedInteger getLength() const;
				const unsigned char *getData() const;
				int set(const jUnsignedInteger &length, const unsigned char *data);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				QueryMessage &operator=(const QueryMessage &value);
				bool operator==(const QueryMessage &value) const;
				bool operator!=(const QueryMessage &value) const;
				QueryMessage();
				QueryMessage(const QueryMessage &value);
				virtual ~QueryMessage();
			
			protected:
				UpdateEventRec* m_parent;
				jUnsignedInteger m_Length;
				unsigned char *m_Data;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getRequestID();
			int setRequestID(jUnsignedByte value);
			jUnsignedByte getEventType();
			int setEventType(jUnsignedByte value);
			double getRequestedPeriodicRate();
			int setRequestedPeriodicRate(double value);
			jUnsignedByte getEventID();
			int setEventID(jUnsignedByte value);
			QueryMessage* const getQueryMessage();
			int setQueryMessage(const QueryMessage &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			UpdateEventRec &operator=(const UpdateEventRec &value);
			bool operator==(const UpdateEventRec &value) const;
			bool operator!=(const UpdateEventRec &value) const;
			UpdateEventRec();
			UpdateEventRec(const UpdateEventRec &value);
			virtual ~UpdateEventRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_RequestID;
			jUnsignedByte m_EventType;
			jUnsignedShortInteger m_RequestedPeriodicRate;
			jUnsignedByte m_EventID;
			QueryMessage m_QueryMessage;
		};
	
		UpdateEventRec* const getUpdateEventRec();
		int setUpdateEventRec(const UpdateEventRec &value);
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
		UpdateEventRec m_UpdateEventRec;
	};

	unsigned int getID() { return ID; };
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	UpdateEvent &operator=(const UpdateEvent &value);
	bool operator==(const UpdateEvent &value) const;
	bool operator!=(const UpdateEvent &value) const;
	UpdateEvent();
	UpdateEvent(const UpdateEvent &value);
	virtual ~UpdateEvent();

protected:
	MsgHeader m_MsgHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_UPDATEEVENT_H
