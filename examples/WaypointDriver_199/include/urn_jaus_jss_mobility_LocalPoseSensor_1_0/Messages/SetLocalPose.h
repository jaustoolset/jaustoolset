#ifndef URN_JAUS_JSS_MOBILITY_LOCALPOSESENSOR_1_0_SETLOCALPOSE_H
#define URN_JAUS_JSS_MOBILITY_LOCALPOSESENSOR_1_0_SETLOCALPOSE_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_LocalPoseSensor_1_0
{

class DllExport SetLocalPose: public JTS::Message
{
public:
	static const int ID = 0x0403;
	
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
		class DllExport LocalPoseRec
		{
		public:
			class DllExport TimeStamp
			{
			public:
				void setParent(LocalPoseRec* parent);
				void setParentPresenceVector();
				jUnsignedInteger getMilliseconds();
				int setMilliseconds(jUnsignedInteger value);
				jUnsignedInteger getSeconds();
				int setSeconds(jUnsignedInteger value);
				jUnsignedInteger getMinutes();
				int setMinutes(jUnsignedInteger value);
				jUnsignedInteger getHour();
				int setHour(jUnsignedInteger value);
				jUnsignedInteger getDay();
				int setDay(jUnsignedInteger value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				TimeStamp &operator=(const TimeStamp &value);
				bool operator==(const TimeStamp &value) const;
				bool operator!=(const TimeStamp &value) const;
				TimeStamp();
				TimeStamp(const TimeStamp &value);
				virtual ~TimeStamp();
			
			protected:
				LocalPoseRec* m_parent;
				jUnsignedInteger m_SubFields;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getPresenceVector();
			bool checkPresenceVector(unsigned int index) const;
			bool isXValid() const;
			double getX();
			int setX(double value);
			bool isYValid() const;
			double getY();
			int setY(double value);
			bool isZValid() const;
			double getZ();
			int setZ(double value);
			bool isPosition_RMSValid() const;
			double getPosition_RMS();
			int setPosition_RMS(double value);
			bool isRollValid() const;
			double getRoll();
			int setRoll(double value);
			bool isPitchValid() const;
			double getPitch();
			int setPitch(double value);
			bool isYawValid() const;
			double getYaw();
			int setYaw(double value);
			bool isAttitude_RMSValid() const;
			double getAttitude_RMS();
			int setAttitude_RMS(double value);
			bool isTimeStampValid() const;
			TimeStamp* const getTimeStamp();
			int setTimeStamp(const TimeStamp &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			LocalPoseRec &operator=(const LocalPoseRec &value);
			bool operator==(const LocalPoseRec &value) const;
			bool operator!=(const LocalPoseRec &value) const;
			LocalPoseRec();
			LocalPoseRec(const LocalPoseRec &value);
			virtual ~LocalPoseRec();
		
		protected:
			int setPresenceVector(unsigned int index);
		
			Body* m_parent;
			jUnsignedShortInteger m_PresenceVector;
			jUnsignedInteger m_X;
			jUnsignedInteger m_Y;
			jUnsignedInteger m_Z;
			jUnsignedInteger m_Position_RMS;
			jUnsignedShortInteger m_Roll;
			jUnsignedShortInteger m_Pitch;
			jUnsignedShortInteger m_Yaw;
			jUnsignedShortInteger m_Attitude_RMS;
			TimeStamp m_TimeStamp;
		};
	
		LocalPoseRec* const getLocalPoseRec();
		int setLocalPoseRec(const LocalPoseRec &value);
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
		LocalPoseRec m_LocalPoseRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	SetLocalPose &operator=(const SetLocalPose &value);
	bool operator==(const SetLocalPose &value) const;
	bool operator!=(const SetLocalPose &value) const;
	SetLocalPose();
	SetLocalPose(const SetLocalPose &value);
	virtual ~SetLocalPose();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LOCALPOSESENSOR_1_0_SETLOCALPOSE_H
