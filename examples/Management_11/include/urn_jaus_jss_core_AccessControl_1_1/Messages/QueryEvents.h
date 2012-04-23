#ifndef URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_QUERYEVENTS_H
#define URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_QUERYEVENTS_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_AccessControl_1_1
{

class DllExport QueryEvents: public JTS::Message
{
public:
	static const int ID = 0x21f0;
	
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
		class DllExport QueryEventsVar
		{
		public:
			class DllExport MessageIDRec
			{
			public:
				void setParent(QueryEventsVar* parent);
				void setParentPresenceVector();
				jUnsignedShortInteger getMessageCode();
				int setMessageCode(jUnsignedShortInteger value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				MessageIDRec &operator=(const MessageIDRec &value);
				bool operator==(const MessageIDRec &value) const;
				bool operator!=(const MessageIDRec &value) const;
				MessageIDRec();
				MessageIDRec(const MessageIDRec &value);
				virtual ~MessageIDRec();
			
			protected:
				QueryEventsVar* m_parent;
				jUnsignedShortInteger m_MessageCode;
			};
			class DllExport EventTypeRec
			{
			public:
				void setParent(QueryEventsVar* parent);
				void setParentPresenceVector();
				jUnsignedByte getEventType();
				int setEventType(jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				EventTypeRec &operator=(const EventTypeRec &value);
				bool operator==(const EventTypeRec &value) const;
				bool operator!=(const EventTypeRec &value) const;
				EventTypeRec();
				EventTypeRec(const EventTypeRec &value);
				virtual ~EventTypeRec();
			
			protected:
				QueryEventsVar* m_parent;
				jUnsignedByte m_EventType;
			};
			class DllExport EventIDRec
			{
			public:
				void setParent(QueryEventsVar* parent);
				void setParentPresenceVector();
				jUnsignedByte getEventID();
				int setEventID(jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				EventIDRec &operator=(const EventIDRec &value);
				bool operator==(const EventIDRec &value) const;
				bool operator!=(const EventIDRec &value) const;
				EventIDRec();
				EventIDRec(const EventIDRec &value);
				virtual ~EventIDRec();
			
			protected:
				QueryEventsVar* m_parent;
				jUnsignedByte m_EventID;
			};
			class DllExport AllEventsRec
			{
			public:
				void setParent(QueryEventsVar* parent);
				void setParentPresenceVector();
				jUnsignedByte getAllEvents();
				int setAllEvents(jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				AllEventsRec &operator=(const AllEventsRec &value);
				bool operator==(const AllEventsRec &value) const;
				bool operator!=(const AllEventsRec &value) const;
				AllEventsRec();
				AllEventsRec(const AllEventsRec &value);
				virtual ~AllEventsRec();
			
			protected:
				QueryEventsVar* m_parent;
				jUnsignedByte m_AllEvents;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			MessageIDRec* const getMessageIDRec();
			int setMessageIDRec(const MessageIDRec &value);
			EventTypeRec* const getEventTypeRec();
			int setEventTypeRec(const EventTypeRec &value);
			EventIDRec* const getEventIDRec();
			int setEventIDRec(const EventIDRec &value);
			AllEventsRec* const getAllEventsRec();
			int setAllEventsRec(const AllEventsRec &value);
			jUnsignedByte getFieldValue() const;
			int setFieldValue(jUnsignedByte fieldValue);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			QueryEventsVar &operator=(const QueryEventsVar &value);
			bool operator==(const QueryEventsVar &value) const;
			bool operator!=(const QueryEventsVar &value) const;
			QueryEventsVar();
			QueryEventsVar(const QueryEventsVar &value);
			virtual ~QueryEventsVar();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_FieldValue;
			MessageIDRec m_MessageIDRec;
			EventTypeRec m_EventTypeRec;
			EventIDRec m_EventIDRec;
			AllEventsRec m_AllEventsRec;
		};
	
		QueryEventsVar* const getQueryEventsVar();
		int setQueryEventsVar(const QueryEventsVar &value);
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
		QueryEventsVar m_QueryEventsVar;
	};

	unsigned int getID() { return ID; };
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryEvents &operator=(const QueryEvents &value);
	bool operator==(const QueryEvents &value) const;
	bool operator!=(const QueryEvents &value) const;
	QueryEvents();
	QueryEvents(const QueryEvents &value);
	virtual ~QueryEvents();

protected:
	MsgHeader m_MsgHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_QUERYEVENTS_H
