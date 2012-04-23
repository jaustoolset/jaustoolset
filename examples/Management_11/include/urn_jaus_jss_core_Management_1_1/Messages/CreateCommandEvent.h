#ifndef URN_JAUS_JSS_CORE_MANAGEMENT_1_1_CREATECOMMANDEVENT_H
#define URN_JAUS_JSS_CORE_MANAGEMENT_1_1_CREATECOMMANDEVENT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Management_1_1
{

class DllExport CreateCommandEvent: public JTS::Message
{
public:
	static const int ID = 0x01f6;
	
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
		class DllExport CreateEventRec
		{
		public:
			class DllExport CommandMessage
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
				CommandMessage &operator=(const CommandMessage &value);
				bool operator==(const CommandMessage &value) const;
				bool operator!=(const CommandMessage &value) const;
				CommandMessage();
				CommandMessage(const CommandMessage &value);
				virtual ~CommandMessage();
			
			protected:
				CreateEventRec* m_parent;
				jUnsignedInteger m_Length;
				unsigned char *m_Data;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getRequestID();
			int setRequestID(jUnsignedByte value);
			jUnsignedInteger getMaximumAllowedDuration();
			int setMaximumAllowedDuration(jUnsignedInteger value);
			CommandMessage* const getCommandMessage();
			int setCommandMessage(const CommandMessage &value);
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
			jUnsignedInteger m_MaximumAllowedDuration;
			CommandMessage m_CommandMessage;
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
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	CreateCommandEvent &operator=(const CreateCommandEvent &value);
	bool operator==(const CreateCommandEvent &value) const;
	bool operator!=(const CreateCommandEvent &value) const;
	CreateCommandEvent();
	CreateCommandEvent(const CreateCommandEvent &value);
	virtual ~CreateCommandEvent();

protected:
	MsgHeader m_MsgHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_MANAGEMENT_1_1_CREATECOMMANDEVENT_H
