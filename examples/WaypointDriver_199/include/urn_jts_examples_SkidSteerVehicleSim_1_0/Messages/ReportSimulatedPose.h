#ifndef URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_REPORTSIMULATEDPOSE_H
#define URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_REPORTSIMULATEDPOSE_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

class DllExport ReportSimulatedPose: public JTS::Message
{
public:
	static const int ID = 0x4d03;
	
	class DllExport JAUSApplicationLayerHeader
	{
	public:
		class DllExport HeaderRec
		{
		public:
			void setParent(JAUSApplicationLayerHeader* parent);
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
			JAUSApplicationLayerHeader* m_parent;
			jUnsignedShortInteger m_MessageID;
		};
	
		HeaderRec* const getHeaderRec();
		int setHeaderRec(const HeaderRec &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		JAUSApplicationLayerHeader &operator=(const JAUSApplicationLayerHeader &value);
		bool operator==(const JAUSApplicationLayerHeader &value) const;
		bool operator!=(const JAUSApplicationLayerHeader &value) const;
		JAUSApplicationLayerHeader();
		JAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value);
		virtual ~JAUSApplicationLayerHeader();
	
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
	JAUSApplicationLayerHeader* const getJAUSApplicationLayerHeader();
	int setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportSimulatedPose &operator=(const ReportSimulatedPose &value);
	bool operator==(const ReportSimulatedPose &value) const;
	bool operator!=(const ReportSimulatedPose &value) const;
	ReportSimulatedPose();
	ReportSimulatedPose(const ReportSimulatedPose &value);
	virtual ~ReportSimulatedPose();

protected:
	JAUSApplicationLayerHeader m_JAUSApplicationLayerHeader;
	Body m_Body;
};

}

#endif // URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_REPORTSIMULATEDPOSE_H
