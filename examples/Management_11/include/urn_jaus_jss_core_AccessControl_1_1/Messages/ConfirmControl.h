#ifndef URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_CONFIRMCONTROL_H
#define URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_CONFIRMCONTROL_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_AccessControl_1_1
{

class DllExport ConfirmControl: public JTS::Message
{
public:
	static const int ID = 0x000f;
	
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
		class DllExport ConfirmControlRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getResponseCode();
			int setResponseCode(jUnsignedByte value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ConfirmControlRec &operator=(const ConfirmControlRec &value);
			bool operator==(const ConfirmControlRec &value) const;
			bool operator!=(const ConfirmControlRec &value) const;
			ConfirmControlRec();
			ConfirmControlRec(const ConfirmControlRec &value);
			virtual ~ConfirmControlRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_ResponseCode;
		};
	
		ConfirmControlRec* const getConfirmControlRec();
		int setConfirmControlRec(const ConfirmControlRec &value);
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
		ConfirmControlRec m_ConfirmControlRec;
	};

	unsigned int getID() { return ID; };
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ConfirmControl &operator=(const ConfirmControl &value);
	bool operator==(const ConfirmControl &value) const;
	bool operator!=(const ConfirmControl &value) const;
	ConfirmControl();
	ConfirmControl(const ConfirmControl &value);
	virtual ~ConfirmControl();

protected:
	MsgHeader m_MsgHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_CONFIRMCONTROL_H
