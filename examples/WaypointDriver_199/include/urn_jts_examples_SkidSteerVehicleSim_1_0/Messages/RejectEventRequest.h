#ifndef URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_REJECTEVENTREQUEST_H
#define URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_REJECTEVENTREQUEST_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

class DllExport RejectEventRequest: public JTS::Message
{
public:
	static const int ID = 0x01f4;
	
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
		class DllExport RejectEventRequestRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getPresenceVector();
			bool checkPresenceVector(unsigned int index) const;
			jUnsignedByte getRequestID();
			int setRequestID(jUnsignedByte value);
			bool isResponseCodeValid() const;
			jUnsignedByte getResponseCode();
			int setResponseCode(jUnsignedByte value);
			bool isErrorMessageValid() const;
			jFixedLengthString getErrorMessage();
			int setErrorMessage(std::string value);
			int setErrorMessage(jFixedLengthString value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			RejectEventRequestRec &operator=(const RejectEventRequestRec &value);
			bool operator==(const RejectEventRequestRec &value) const;
			bool operator!=(const RejectEventRequestRec &value) const;
			RejectEventRequestRec();
			RejectEventRequestRec(const RejectEventRequestRec &value);
			virtual ~RejectEventRequestRec();
		
		protected:
			int setPresenceVector(unsigned int index);
		
			Body* m_parent;
			jUnsignedByte m_PresenceVector;
			jUnsignedByte m_RequestID;
			jUnsignedByte m_ResponseCode;
			jFixedLengthString m_ErrorMessage;
		};
	
		RejectEventRequestRec* const getRejectEventRequestRec();
		int setRejectEventRequestRec(const RejectEventRequestRec &value);
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
		RejectEventRequestRec m_RejectEventRequestRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	RejectEventRequest &operator=(const RejectEventRequest &value);
	bool operator==(const RejectEventRequest &value) const;
	bool operator!=(const RejectEventRequest &value) const;
	RejectEventRequest();
	RejectEventRequest(const RejectEventRequest &value);
	virtual ~RejectEventRequest();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_REJECTEVENTREQUEST_H
