#ifndef URN_JAUS_JSS_CORE_MANAGEMENT_1_1_COMMANDEVENT_H
#define URN_JAUS_JSS_CORE_MANAGEMENT_1_1_COMMANDEVENT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Management_1_1
{

class DllExport CommandEvent: public JTS::Message
{
public:
	static const int ID = 0x41f6;
	
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
		class DllExport EventRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getEventID();
			int setEventID(jUnsignedByte value);
			jUnsignedByte getCommandResult();
			int setCommandResult(jUnsignedByte value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			EventRec &operator=(const EventRec &value);
			bool operator==(const EventRec &value) const;
			bool operator!=(const EventRec &value) const;
			EventRec();
			EventRec(const EventRec &value);
			virtual ~EventRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_EventID;
			jUnsignedByte m_CommandResult;
		};
	
		EventRec* const getEventRec();
		int setEventRec(const EventRec &value);
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
		EventRec m_EventRec;
	};

	unsigned int getID() { return ID; };
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	CommandEvent &operator=(const CommandEvent &value);
	bool operator==(const CommandEvent &value) const;
	bool operator!=(const CommandEvent &value) const;
	CommandEvent();
	CommandEvent(const CommandEvent &value);
	virtual ~CommandEvent();

protected:
	MsgHeader m_MsgHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_MANAGEMENT_1_1_COMMANDEVENT_H
