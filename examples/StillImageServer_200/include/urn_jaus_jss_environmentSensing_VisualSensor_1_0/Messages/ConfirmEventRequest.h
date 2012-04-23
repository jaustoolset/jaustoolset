#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_CONFIRMEVENTREQUEST_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_CONFIRMEVENTREQUEST_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0
{

class DllExport ConfirmEventRequest: public JTS::Message
{
public:
	static const int ID = 0x01f3;
	
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
		class DllExport ConfirmEventRequestRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getRequestID();
			int setRequestID(jUnsignedByte value);
			jUnsignedByte getEventID();
			int setEventID(jUnsignedByte value);
			double getConfirmedPeriodicRate();
			int setConfirmedPeriodicRate(double value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ConfirmEventRequestRec &operator=(const ConfirmEventRequestRec &value);
			bool operator==(const ConfirmEventRequestRec &value) const;
			bool operator!=(const ConfirmEventRequestRec &value) const;
			ConfirmEventRequestRec();
			ConfirmEventRequestRec(const ConfirmEventRequestRec &value);
			virtual ~ConfirmEventRequestRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_RequestID;
			jUnsignedByte m_EventID;
			jUnsignedShortInteger m_ConfirmedPeriodicRate;
		};
	
		ConfirmEventRequestRec* const getConfirmEventRequestRec();
		int setConfirmEventRequestRec(const ConfirmEventRequestRec &value);
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
		ConfirmEventRequestRec m_ConfirmEventRequestRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ConfirmEventRequest &operator=(const ConfirmEventRequest &value);
	bool operator==(const ConfirmEventRequest &value) const;
	bool operator!=(const ConfirmEventRequest &value) const;
	ConfirmEventRequest();
	ConfirmEventRequest(const ConfirmEventRequest &value);
	virtual ~ConfirmEventRequest();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_CONFIRMEVENTREQUEST_H
