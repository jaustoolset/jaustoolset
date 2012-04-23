#ifndef URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_REJECTELEMENTREQUEST_H
#define URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_REJECTELEMENTREQUEST_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_ListManager_1_0
{

class DllExport RejectElementRequest: public JTS::Message
{
public:
	static const int ID = 0x041d;
	
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
		class DllExport RejectElementRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getRequestID();
			int setRequestID(jUnsignedByte value);
			jUnsignedByte getResponseCode();
			int setResponseCode(jUnsignedByte value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			RejectElementRec &operator=(const RejectElementRec &value);
			bool operator==(const RejectElementRec &value) const;
			bool operator!=(const RejectElementRec &value) const;
			RejectElementRec();
			RejectElementRec(const RejectElementRec &value);
			virtual ~RejectElementRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_RequestID;
			jUnsignedByte m_ResponseCode;
		};
	
		RejectElementRec* const getRejectElementRec();
		int setRejectElementRec(const RejectElementRec &value);
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
		RejectElementRec m_RejectElementRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	RejectElementRequest &operator=(const RejectElementRequest &value);
	bool operator==(const RejectElementRequest &value) const;
	bool operator!=(const RejectElementRequest &value) const;
	RejectElementRequest();
	RejectElementRequest(const RejectElementRequest &value);
	virtual ~RejectElementRequest();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_REJECTELEMENTREQUEST_H
