#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_REPORTSENSORGEOMETRICPROPERTIES_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_REPORTSENSORGEOMETRICPROPERTIES_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0
{

class DllExport ReportSensorGeometricProperties: public JTS::Message
{
public:
	static const int ID = 0x4805;
	
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
		class DllExport GeometricPropertiesList
		{
		public:
			class DllExport GeometricPropertiesSequence
			{
			public:
				class DllExport SensorIdRec
				{
				public:
					void setParent(GeometricPropertiesSequence* parent);
					void setParentPresenceVector();
					jUnsignedShortInteger getSensorID();
					int setSensorID(jUnsignedShortInteger value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					SensorIdRec &operator=(const SensorIdRec &value);
					bool operator==(const SensorIdRec &value) const;
					bool operator!=(const SensorIdRec &value) const;
					SensorIdRec();
					SensorIdRec(const SensorIdRec &value);
					virtual ~SensorIdRec();
				
				protected:
					GeometricPropertiesSequence* m_parent;
					jUnsignedShortInteger m_SensorID;
				};
				class DllExport GeometricPropertiesVariant
				{
				public:
					class DllExport NoGeometricPropertiesVariant
					{
					public:
						void setParent(GeometricPropertiesVariant* parent);
						void setParentPresenceVector();
						jUnsignedByte getFieldValue() const;
						int setFieldValue(jUnsignedByte fieldValue);
						const unsigned int getSize();
						virtual void encode(unsigned char *bytes);
						virtual void decode(const unsigned char *bytes);
						NoGeometricPropertiesVariant &operator=(const NoGeometricPropertiesVariant &value);
						bool operator==(const NoGeometricPropertiesVariant &value) const;
						bool operator!=(const NoGeometricPropertiesVariant &value) const;
						NoGeometricPropertiesVariant();
						NoGeometricPropertiesVariant(const NoGeometricPropertiesVariant &value);
						virtual ~NoGeometricPropertiesVariant();
					
					protected:
						GeometricPropertiesVariant* m_parent;
						jUnsignedByte m_FieldValue;
					};
					class DllExport StaticGeometricPropertiesRec
					{
					public:
						class DllExport SensorPosition
						{
						public:
							void setParent(StaticGeometricPropertiesRec* parent);
							void setParentPresenceVector();
							const unsigned int getPositionVectorDimensionSize() const;
							double getPositionVectorElement(unsigned int PositionVectorDimension);
							int setPositionVectorElement(unsigned int PositionVectorDimension, double value);
							const unsigned int getSize();
							virtual void encode(unsigned char *bytes);
							virtual void decode(const unsigned char *bytes);
							SensorPosition &operator=(const SensorPosition &value);
							bool operator==(const SensorPosition &value) const;
							bool operator!=(const SensorPosition &value) const;
							SensorPosition();
							SensorPosition(const SensorPosition &value);
							virtual ~SensorPosition();
						
						protected:
							StaticGeometricPropertiesRec* m_parent;
							unsigned int m_PositionVectorDimensionSize;
							jUnsignedInteger m_PositionVectorElement[3];
						};
						class DllExport UnitQuaternion
						{
						public:
							void setParent(StaticGeometricPropertiesRec* parent);
							void setParentPresenceVector();
							const unsigned int getUnitQuaternionDimensionSize() const;
							double getUnitQuaternionElement(unsigned int UnitQuaternionDimension);
							int setUnitQuaternionElement(unsigned int UnitQuaternionDimension, double value);
							const unsigned int getSize();
							virtual void encode(unsigned char *bytes);
							virtual void decode(const unsigned char *bytes);
							UnitQuaternion &operator=(const UnitQuaternion &value);
							bool operator==(const UnitQuaternion &value) const;
							bool operator!=(const UnitQuaternion &value) const;
							UnitQuaternion();
							UnitQuaternion(const UnitQuaternion &value);
							virtual ~UnitQuaternion();
						
						protected:
							StaticGeometricPropertiesRec* m_parent;
							unsigned int m_UnitQuaternionDimensionSize;
							jUnsignedInteger m_UnitQuaternionElement[4];
						};
					
						void setParent(GeometricPropertiesVariant* parent);
						void setParentPresenceVector();
						SensorPosition* const getSensorPosition();
						int setSensorPosition(const SensorPosition &value);
						UnitQuaternion* const getUnitQuaternion();
						int setUnitQuaternion(const UnitQuaternion &value);
						const unsigned int getSize();
						virtual void encode(unsigned char *bytes);
						virtual void decode(const unsigned char *bytes);
						StaticGeometricPropertiesRec &operator=(const StaticGeometricPropertiesRec &value);
						bool operator==(const StaticGeometricPropertiesRec &value) const;
						bool operator!=(const StaticGeometricPropertiesRec &value) const;
						StaticGeometricPropertiesRec();
						StaticGeometricPropertiesRec(const StaticGeometricPropertiesRec &value);
						virtual ~StaticGeometricPropertiesRec();
					
					protected:
						GeometricPropertiesVariant* m_parent;
						SensorPosition m_SensorPosition;
						UnitQuaternion m_UnitQuaternion;
					};
					class DllExport ManipulatorGeometricPropertiesRec
					{
					public:
						class DllExport SensorPosition
						{
						public:
							void setParent(ManipulatorGeometricPropertiesRec* parent);
							void setParentPresenceVector();
							const unsigned int getPositionVectorDimensionSize() const;
							double getPositionVectorElement(unsigned int PositionVectorDimension);
							int setPositionVectorElement(unsigned int PositionVectorDimension, double value);
							const unsigned int getSize();
							virtual void encode(unsigned char *bytes);
							virtual void decode(const unsigned char *bytes);
							SensorPosition &operator=(const SensorPosition &value);
							bool operator==(const SensorPosition &value) const;
							bool operator!=(const SensorPosition &value) const;
							SensorPosition();
							SensorPosition(const SensorPosition &value);
							virtual ~SensorPosition();
						
						protected:
							ManipulatorGeometricPropertiesRec* m_parent;
							unsigned int m_PositionVectorDimensionSize;
							jUnsignedInteger m_PositionVectorElement[3];
						};
						class DllExport UnitQuaternion
						{
						public:
							void setParent(ManipulatorGeometricPropertiesRec* parent);
							void setParentPresenceVector();
							const unsigned int getUnitQuaternionDimensionSize() const;
							double getUnitQuaternionElement(unsigned int UnitQuaternionDimension);
							int setUnitQuaternionElement(unsigned int UnitQuaternionDimension, double value);
							const unsigned int getSize();
							virtual void encode(unsigned char *bytes);
							virtual void decode(const unsigned char *bytes);
							UnitQuaternion &operator=(const UnitQuaternion &value);
							bool operator==(const UnitQuaternion &value) const;
							bool operator!=(const UnitQuaternion &value) const;
							UnitQuaternion();
							UnitQuaternion(const UnitQuaternion &value);
							virtual ~UnitQuaternion();
						
						protected:
							ManipulatorGeometricPropertiesRec* m_parent;
							unsigned int m_UnitQuaternionDimensionSize;
							jUnsignedInteger m_UnitQuaternionElement[4];
						};
					
						void setParent(GeometricPropertiesVariant* parent);
						void setParentPresenceVector();
						jUnsignedShortInteger getSubsystemID();
						int setSubsystemID(jUnsignedShortInteger value);
						jUnsignedByte getNodeID();
						int setNodeID(jUnsignedByte value);
						jUnsignedByte getComponentID();
						int setComponentID(jUnsignedByte value);
						jUnsignedByte getJointNumber();
						int setJointNumber(jUnsignedByte value);
						SensorPosition* const getSensorPosition();
						int setSensorPosition(const SensorPosition &value);
						UnitQuaternion* const getUnitQuaternion();
						int setUnitQuaternion(const UnitQuaternion &value);
						const unsigned int getSize();
						virtual void encode(unsigned char *bytes);
						virtual void decode(const unsigned char *bytes);
						ManipulatorGeometricPropertiesRec &operator=(const ManipulatorGeometricPropertiesRec &value);
						bool operator==(const ManipulatorGeometricPropertiesRec &value) const;
						bool operator!=(const ManipulatorGeometricPropertiesRec &value) const;
						ManipulatorGeometricPropertiesRec();
						ManipulatorGeometricPropertiesRec(const ManipulatorGeometricPropertiesRec &value);
						virtual ~ManipulatorGeometricPropertiesRec();
					
					protected:
						GeometricPropertiesVariant* m_parent;
						jUnsignedShortInteger m_SubsystemID;
						jUnsignedByte m_NodeID;
						jUnsignedByte m_ComponentID;
						jUnsignedByte m_JointNumber;
						SensorPosition m_SensorPosition;
						UnitQuaternion m_UnitQuaternion;
					};
				
					void setParent(GeometricPropertiesSequence* parent);
					void setParentPresenceVector();
					NoGeometricPropertiesVariant* const getNoGeometricPropertiesVariant();
					int setNoGeometricPropertiesVariant(const NoGeometricPropertiesVariant &value);
					StaticGeometricPropertiesRec* const getStaticGeometricPropertiesRec();
					int setStaticGeometricPropertiesRec(const StaticGeometricPropertiesRec &value);
					ManipulatorGeometricPropertiesRec* const getManipulatorGeometricPropertiesRec();
					int setManipulatorGeometricPropertiesRec(const ManipulatorGeometricPropertiesRec &value);
					jUnsignedByte getFieldValue() const;
					int setFieldValue(jUnsignedByte fieldValue);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					GeometricPropertiesVariant &operator=(const GeometricPropertiesVariant &value);
					bool operator==(const GeometricPropertiesVariant &value) const;
					bool operator!=(const GeometricPropertiesVariant &value) const;
					GeometricPropertiesVariant();
					GeometricPropertiesVariant(const GeometricPropertiesVariant &value);
					virtual ~GeometricPropertiesVariant();
				
				protected:
					GeometricPropertiesSequence* m_parent;
					jUnsignedByte m_FieldValue;
					NoGeometricPropertiesVariant m_NoGeometricPropertiesVariant;
					StaticGeometricPropertiesRec m_StaticGeometricPropertiesRec;
					ManipulatorGeometricPropertiesRec m_ManipulatorGeometricPropertiesRec;
				};
			
				void setParent(GeometricPropertiesList* parent);
				void setParentPresenceVector();
				SensorIdRec* const getSensorIdRec();
				int setSensorIdRec(const SensorIdRec &value);
				GeometricPropertiesVariant* const getGeometricPropertiesVariant();
				int setGeometricPropertiesVariant(const GeometricPropertiesVariant &value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				GeometricPropertiesSequence &operator=(const GeometricPropertiesSequence &value);
				bool operator==(const GeometricPropertiesSequence &value) const;
				bool operator!=(const GeometricPropertiesSequence &value) const;
				GeometricPropertiesSequence();
				GeometricPropertiesSequence(const GeometricPropertiesSequence &value);
				virtual ~GeometricPropertiesSequence();
			
			protected:
				GeometricPropertiesList* m_parent;
				SensorIdRec m_SensorIdRec;
				GeometricPropertiesVariant m_GeometricPropertiesVariant;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			GeometricPropertiesSequence* const getElement(unsigned int index);
			int setElement(unsigned int index, const GeometricPropertiesSequence &value);
			int addElement(const GeometricPropertiesSequence &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			GeometricPropertiesList &operator=(const GeometricPropertiesList &value);
			bool operator==(const GeometricPropertiesList &value) const;
			bool operator!=(const GeometricPropertiesList &value) const;
			GeometricPropertiesList();
			GeometricPropertiesList(const GeometricPropertiesList &value);
			virtual ~GeometricPropertiesList();
		
		protected:
			Body* m_parent;
			std::vector<GeometricPropertiesSequence> m_GeometricPropertiesSequence;
		};
	
		GeometricPropertiesList* const getGeometricPropertiesList();
		int setGeometricPropertiesList(const GeometricPropertiesList &value);
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
		GeometricPropertiesList m_GeometricPropertiesList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportSensorGeometricProperties &operator=(const ReportSensorGeometricProperties &value);
	bool operator==(const ReportSensorGeometricProperties &value) const;
	bool operator!=(const ReportSensorGeometricProperties &value) const;
	ReportSensorGeometricProperties();
	ReportSensorGeometricProperties(const ReportSensorGeometricProperties &value);
	virtual ~ReportSensorGeometricProperties();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_REPORTSENSORGEOMETRICPROPERTIES_H
