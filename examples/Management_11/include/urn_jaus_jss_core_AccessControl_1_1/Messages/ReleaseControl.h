#ifndef URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_RELEASECONTROL_H
#define URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_RELEASECONTROL_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_AccessControl_1_1
{

class DllExport ReleaseControl: public JTS::Message
{
public:
	static const int ID = 0x000e;
	
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
	ReleaseControl &operator=(const ReleaseControl &value);
	bool operator==(const ReleaseControl &value) const;
	bool operator!=(const ReleaseControl &value) const;
	ReleaseControl();
	ReleaseControl(const ReleaseControl &value);
	virtual ~ReleaseControl();

protected:
	MsgHeader m_MsgHeader;
};

}

#endif // URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_RELEASECONTROL_H
