#ifndef URN_JAUS_JSS_CORE_MANAGEMENT_1_1_CLEAREMERGENCY_H
#define URN_JAUS_JSS_CORE_MANAGEMENT_1_1_CLEAREMERGENCY_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Management_1_1
{

class DllExport ClearEmergency: public JTS::Message
{
public:
	static const int ID = 0x0007;
	
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
		class DllExport ClearEmergencyRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getEmergencyCode();
			int setEmergencyCode(jUnsignedShortInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ClearEmergencyRec &operator=(const ClearEmergencyRec &value);
			bool operator==(const ClearEmergencyRec &value) const;
			bool operator!=(const ClearEmergencyRec &value) const;
			ClearEmergencyRec();
			ClearEmergencyRec(const ClearEmergencyRec &value);
			virtual ~ClearEmergencyRec();
		
		protected:
			Body* m_parent;
			jUnsignedShortInteger m_EmergencyCode;
		};
	
		ClearEmergencyRec* const getClearEmergencyRec();
		int setClearEmergencyRec(const ClearEmergencyRec &value);
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
		ClearEmergencyRec m_ClearEmergencyRec;
	};

	unsigned int getID() { return ID; };
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ClearEmergency &operator=(const ClearEmergency &value);
	bool operator==(const ClearEmergency &value) const;
	bool operator!=(const ClearEmergency &value) const;
	ClearEmergency();
	ClearEmergency(const ClearEmergency &value);
	virtual ~ClearEmergency();

protected:
	MsgHeader m_MsgHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_MANAGEMENT_1_1_CLEAREMERGENCY_H
