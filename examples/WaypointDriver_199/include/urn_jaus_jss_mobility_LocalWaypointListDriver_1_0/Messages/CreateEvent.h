#ifndef URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_CREATEEVENT_H
#define URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_CREATEEVENT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

class DllExport CreateEvent: public JTS::Message
{
public:
	static const int ID = 0x01f0;
	
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
		class DllExport CreateEventRec
		{
		public:
			class DllExport QueryMessage
			{
			public:
				void setParent(CreateEventRec* parent);
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
				CreateEventRec* m_parent;
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
			QueryMessage* const getQueryMessage();
			int setQueryMessage(const QueryMessage &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			CreateEventRec &operator=(const CreateEventRec &value);
			bool operator==(const CreateEventRec &value) const;
			bool operator!=(const CreateEventRec &value) const;
			CreateEventRec();
			CreateEventRec(const CreateEventRec &value);
			virtual ~CreateEventRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_RequestID;
			jUnsignedByte m_EventType;
			jUnsignedShortInteger m_RequestedPeriodicRate;
			QueryMessage m_QueryMessage;
		};
	
		CreateEventRec* const getCreateEventRec();
		int setCreateEventRec(const CreateEventRec &value);
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
		CreateEventRec m_CreateEventRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	CreateEvent &operator=(const CreateEvent &value);
	bool operator==(const CreateEvent &value) const;
	bool operator!=(const CreateEvent &value) const;
	CreateEvent();
	CreateEvent(const CreateEvent &value);
	virtual ~CreateEvent();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_CREATEEVENT_H
