#ifndef URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_REPORTTRAVELSPEED_H
#define URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_REPORTTRAVELSPEED_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

class DllExport ReportTravelSpeed: public JTS::Message
{
public:
	static const int ID = 0x440a;
	
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
		class DllExport TravelSpeedRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			double getSpeed();
			int setSpeed(double value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			TravelSpeedRec &operator=(const TravelSpeedRec &value);
			bool operator==(const TravelSpeedRec &value) const;
			bool operator!=(const TravelSpeedRec &value) const;
			TravelSpeedRec();
			TravelSpeedRec(const TravelSpeedRec &value);
			virtual ~TravelSpeedRec();
		
		protected:
			Body* m_parent;
			jUnsignedShortInteger m_Speed;
		};
	
		TravelSpeedRec* const getTravelSpeedRec();
		int setTravelSpeedRec(const TravelSpeedRec &value);
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
		TravelSpeedRec m_TravelSpeedRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportTravelSpeed &operator=(const ReportTravelSpeed &value);
	bool operator==(const ReportTravelSpeed &value) const;
	bool operator!=(const ReportTravelSpeed &value) const;
	ReportTravelSpeed();
	ReportTravelSpeed(const ReportTravelSpeed &value);
	virtual ~ReportTravelSpeed();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_REPORTTRAVELSPEED_H
