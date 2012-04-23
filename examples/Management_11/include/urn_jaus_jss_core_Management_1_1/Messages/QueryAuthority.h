#ifndef URN_JAUS_JSS_CORE_MANAGEMENT_1_1_QUERYAUTHORITY_H
#define URN_JAUS_JSS_CORE_MANAGEMENT_1_1_QUERYAUTHORITY_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Management_1_1
{

class DllExport QueryAuthority: public JTS::Message
{
public:
	static const int ID = 0x2001;
	
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
	QueryAuthority &operator=(const QueryAuthority &value);
	bool operator==(const QueryAuthority &value) const;
	bool operator!=(const QueryAuthority &value) const;
	QueryAuthority();
	QueryAuthority(const QueryAuthority &value);
	virtual ~QueryAuthority();

protected:
	MsgHeader m_MsgHeader;
};

}

#endif // URN_JAUS_JSS_CORE_MANAGEMENT_1_1_QUERYAUTHORITY_H
