#ifndef URN_JTS_EXAMPLE_MANAGEMENT_OCU_1_0_REQUESTCONTROL_H
#define URN_JTS_EXAMPLE_MANAGEMENT_OCU_1_0_REQUESTCONTROL_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jts_example_management_ocu_1_0
{

class DllExport RequestControl: public JTS::Message
{
public:
	static const int ID = 0x000d;
	
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
		class DllExport RequestControlRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getAuthorityCode();
			int setAuthorityCode(jUnsignedByte value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			RequestControlRec &operator=(const RequestControlRec &value);
			bool operator==(const RequestControlRec &value) const;
			bool operator!=(const RequestControlRec &value) const;
			RequestControlRec();
			RequestControlRec(const RequestControlRec &value);
			virtual ~RequestControlRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_AuthorityCode;
		};
	
		RequestControlRec* const getRequestControlRec();
		int setRequestControlRec(const RequestControlRec &value);
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
		RequestControlRec m_RequestControlRec;
	};

	unsigned int getID() { return ID; };
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	RequestControl &operator=(const RequestControl &value);
	bool operator==(const RequestControl &value) const;
	bool operator!=(const RequestControl &value) const;
	RequestControl();
	RequestControl(const RequestControl &value);
	virtual ~RequestControl();

protected:
	MsgHeader m_MsgHeader;
	Body m_Body;
};

}

#endif // URN_JTS_EXAMPLE_MANAGEMENT_OCU_1_0_REQUESTCONTROL_H
