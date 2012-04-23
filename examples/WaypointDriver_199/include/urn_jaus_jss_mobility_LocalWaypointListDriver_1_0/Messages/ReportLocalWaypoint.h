#ifndef URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_REPORTLOCALWAYPOINT_H
#define URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_REPORTLOCALWAYPOINT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

class DllExport ReportLocalWaypoint: public JTS::Message
{
public:
	static const int ID = 0x440d;
	
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
		class DllExport LocalWaypointRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getPresenceVector();
			bool checkPresenceVector(unsigned int index) const;
			double getX();
			int setX(double value);
			double getY();
			int setY(double value);
			bool isZValid() const;
			double getZ();
			int setZ(double value);
			bool isRollValid() const;
			double getRoll();
			int setRoll(double value);
			bool isPitchValid() const;
			double getPitch();
			int setPitch(double value);
			bool isYawValid() const;
			double getYaw();
			int setYaw(double value);
			bool isWaypointToleranceValid() const;
			double getWaypointTolerance();
			int setWaypointTolerance(double value);
			bool isPathToleranceValid() const;
			double getPathTolerance();
			int setPathTolerance(double value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			LocalWaypointRec &operator=(const LocalWaypointRec &value);
			bool operator==(const LocalWaypointRec &value) const;
			bool operator!=(const LocalWaypointRec &value) const;
			LocalWaypointRec();
			LocalWaypointRec(const LocalWaypointRec &value);
			virtual ~LocalWaypointRec();
		
		protected:
			int setPresenceVector(unsigned int index);
		
			Body* m_parent;
			jUnsignedByte m_PresenceVector;
			jUnsignedInteger m_X;
			jUnsignedInteger m_Y;
			jUnsignedInteger m_Z;
			jUnsignedShortInteger m_Roll;
			jUnsignedShortInteger m_Pitch;
			jUnsignedShortInteger m_Yaw;
			jUnsignedShortInteger m_WaypointTolerance;
			jUnsignedInteger m_PathTolerance;
		};
	
		LocalWaypointRec* const getLocalWaypointRec();
		int setLocalWaypointRec(const LocalWaypointRec &value);
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
		LocalWaypointRec m_LocalWaypointRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportLocalWaypoint &operator=(const ReportLocalWaypoint &value);
	bool operator==(const ReportLocalWaypoint &value) const;
	bool operator!=(const ReportLocalWaypoint &value) const;
	ReportLocalWaypoint();
	ReportLocalWaypoint(const ReportLocalWaypoint &value);
	virtual ~ReportLocalWaypoint();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LOCALWAYPOINTLISTDRIVER_1_0_REPORTLOCALWAYPOINT_H
