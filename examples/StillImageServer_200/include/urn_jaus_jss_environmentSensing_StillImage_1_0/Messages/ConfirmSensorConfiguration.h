#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_CONFIRMSENSORCONFIGURATION_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_CONFIRMSENSORCONFIGURATION_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

class DllExport ConfirmSensorConfiguration: public JTS::Message
{
public:
	static const int ID = 0x0801;
	
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
		class DllExport ConfirmSensorConfigurationSequence
		{
		public:
			class DllExport RequestIdRec
			{
			public:
				void setParent(ConfirmSensorConfigurationSequence* parent);
				void setParentPresenceVector();
				jUnsignedByte getRequestID();
				int setRequestID(jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				RequestIdRec &operator=(const RequestIdRec &value);
				bool operator==(const RequestIdRec &value) const;
				bool operator!=(const RequestIdRec &value) const;
				RequestIdRec();
				RequestIdRec(const RequestIdRec &value);
				virtual ~RequestIdRec();
			
			protected:
				ConfirmSensorConfigurationSequence* m_parent;
				jUnsignedByte m_RequestID;
			};
			class DllExport ConfirmSensorList
			{
			public:
				class DllExport ConfirmSensorConfigurationVariant
				{
				public:
					class DllExport SensorIdRec
					{
					public:
						void setParent(ConfirmSensorConfigurationVariant* parent);
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
						ConfirmSensorConfigurationVariant* m_parent;
						jUnsignedShortInteger m_SensorID;
					};
					class DllExport RangeSensorErrorRec
					{
					public:
						void setParent(ConfirmSensorConfigurationVariant* parent);
						void setParentPresenceVector();
						jUnsignedShortInteger getSensorID();
						int setSensorID(jUnsignedShortInteger value);
						jUnsignedByte getRangeSensorErrorCode();
						int setRangeSensorErrorCode(jUnsignedByte value);
						jVariableLengthString getErrorMessage();
						int setErrorMessage(jVariableLengthString value);
						const unsigned int getSize();
						virtual void encode(unsigned char *bytes);
						virtual void decode(const unsigned char *bytes);
						RangeSensorErrorRec &operator=(const RangeSensorErrorRec &value);
						bool operator==(const RangeSensorErrorRec &value) const;
						bool operator!=(const RangeSensorErrorRec &value) const;
						RangeSensorErrorRec();
						RangeSensorErrorRec(const RangeSensorErrorRec &value);
						virtual ~RangeSensorErrorRec();
					
					protected:
						ConfirmSensorConfigurationVariant* m_parent;
						jUnsignedShortInteger m_SensorID;
						jUnsignedByte m_RangeSensorErrorCode;
						jVariableLengthString m_ErrorMessage;
					};
					class DllExport VisualSensorErrorRec
					{
					public:
						void setParent(ConfirmSensorConfigurationVariant* parent);
						void setParentPresenceVector();
						jUnsignedShortInteger getSensorID();
						int setSensorID(jUnsignedShortInteger value);
						jUnsignedByte getVisualSensorErrorCode();
						int setVisualSensorErrorCode(jUnsignedByte value);
						jVariableLengthString getErrorMessage();
						int setErrorMessage(jVariableLengthString value);
						const unsigned int getSize();
						virtual void encode(unsigned char *bytes);
						virtual void decode(const unsigned char *bytes);
						VisualSensorErrorRec &operator=(const VisualSensorErrorRec &value);
						bool operator==(const VisualSensorErrorRec &value) const;
						bool operator!=(const VisualSensorErrorRec &value) const;
						VisualSensorErrorRec();
						VisualSensorErrorRec(const VisualSensorErrorRec &value);
						virtual ~VisualSensorErrorRec();
					
					protected:
						ConfirmSensorConfigurationVariant* m_parent;
						jUnsignedShortInteger m_SensorID;
						jUnsignedByte m_VisualSensorErrorCode;
						jVariableLengthString m_ErrorMessage;
					};
					class DllExport DigitalVideoSensorErrorRec
					{
					public:
						void setParent(ConfirmSensorConfigurationVariant* parent);
						void setParentPresenceVector();
						jUnsignedShortInteger getSensorID();
						int setSensorID(jUnsignedShortInteger value);
						jUnsignedByte getDigitalVideoErrorCode();
						int setDigitalVideoErrorCode(jUnsignedByte value);
						jVariableLengthString getErrorMessage();
						int setErrorMessage(jVariableLengthString value);
						const unsigned int getSize();
						virtual void encode(unsigned char *bytes);
						virtual void decode(const unsigned char *bytes);
						DigitalVideoSensorErrorRec &operator=(const DigitalVideoSensorErrorRec &value);
						bool operator==(const DigitalVideoSensorErrorRec &value) const;
						bool operator!=(const DigitalVideoSensorErrorRec &value) const;
						DigitalVideoSensorErrorRec();
						DigitalVideoSensorErrorRec(const DigitalVideoSensorErrorRec &value);
						virtual ~DigitalVideoSensorErrorRec();
					
					protected:
						ConfirmSensorConfigurationVariant* m_parent;
						jUnsignedShortInteger m_SensorID;
						jUnsignedByte m_DigitalVideoErrorCode;
						jVariableLengthString m_ErrorMessage;
					};
					class DllExport AnalogVideoSensorErrorRec
					{
					public:
						void setParent(ConfirmSensorConfigurationVariant* parent);
						void setParentPresenceVector();
						jUnsignedShortInteger getSensorID();
						int setSensorID(jUnsignedShortInteger value);
						jUnsignedByte getAnalogVideoErrorCode();
						int setAnalogVideoErrorCode(jUnsignedByte value);
						jVariableLengthString getErrorMessage();
						int setErrorMessage(jVariableLengthString value);
						const unsigned int getSize();
						virtual void encode(unsigned char *bytes);
						virtual void decode(const unsigned char *bytes);
						AnalogVideoSensorErrorRec &operator=(const AnalogVideoSensorErrorRec &value);
						bool operator==(const AnalogVideoSensorErrorRec &value) const;
						bool operator!=(const AnalogVideoSensorErrorRec &value) const;
						AnalogVideoSensorErrorRec();
						AnalogVideoSensorErrorRec(const AnalogVideoSensorErrorRec &value);
						virtual ~AnalogVideoSensorErrorRec();
					
					protected:
						ConfirmSensorConfigurationVariant* m_parent;
						jUnsignedShortInteger m_SensorID;
						jUnsignedByte m_AnalogVideoErrorCode;
						jVariableLengthString m_ErrorMessage;
					};
					class DllExport StillImageSensorErrorRec
					{
					public:
						void setParent(ConfirmSensorConfigurationVariant* parent);
						void setParentPresenceVector();
						jUnsignedShortInteger getSensorID();
						int setSensorID(jUnsignedShortInteger value);
						jUnsignedByte getStillImageErrorCode();
						int setStillImageErrorCode(jUnsignedByte value);
						jVariableLengthString getErrorMessage();
						int setErrorMessage(jVariableLengthString value);
						const unsigned int getSize();
						virtual void encode(unsigned char *bytes);
						virtual void decode(const unsigned char *bytes);
						StillImageSensorErrorRec &operator=(const StillImageSensorErrorRec &value);
						bool operator==(const StillImageSensorErrorRec &value) const;
						bool operator!=(const StillImageSensorErrorRec &value) const;
						StillImageSensorErrorRec();
						StillImageSensorErrorRec(const StillImageSensorErrorRec &value);
						virtual ~StillImageSensorErrorRec();
					
					protected:
						ConfirmSensorConfigurationVariant* m_parent;
						jUnsignedShortInteger m_SensorID;
						jUnsignedByte m_StillImageErrorCode;
						jVariableLengthString m_ErrorMessage;
					};
				
					void setParent(ConfirmSensorList* parent);
					void setParentPresenceVector();
					SensorIdRec* const getSensorIdRec();
					int setSensorIdRec(const SensorIdRec &value);
					RangeSensorErrorRec* const getRangeSensorErrorRec();
					int setRangeSensorErrorRec(const RangeSensorErrorRec &value);
					VisualSensorErrorRec* const getVisualSensorErrorRec();
					int setVisualSensorErrorRec(const VisualSensorErrorRec &value);
					DigitalVideoSensorErrorRec* const getDigitalVideoSensorErrorRec();
					int setDigitalVideoSensorErrorRec(const DigitalVideoSensorErrorRec &value);
					AnalogVideoSensorErrorRec* const getAnalogVideoSensorErrorRec();
					int setAnalogVideoSensorErrorRec(const AnalogVideoSensorErrorRec &value);
					StillImageSensorErrorRec* const getStillImageSensorErrorRec();
					int setStillImageSensorErrorRec(const StillImageSensorErrorRec &value);
					jUnsignedByte getFieldValue() const;
					int setFieldValue(jUnsignedByte fieldValue);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					ConfirmSensorConfigurationVariant &operator=(const ConfirmSensorConfigurationVariant &value);
					bool operator==(const ConfirmSensorConfigurationVariant &value) const;
					bool operator!=(const ConfirmSensorConfigurationVariant &value) const;
					ConfirmSensorConfigurationVariant();
					ConfirmSensorConfigurationVariant(const ConfirmSensorConfigurationVariant &value);
					virtual ~ConfirmSensorConfigurationVariant();
				
				protected:
					ConfirmSensorList* m_parent;
					jUnsignedByte m_FieldValue;
					SensorIdRec m_SensorIdRec;
					RangeSensorErrorRec m_RangeSensorErrorRec;
					VisualSensorErrorRec m_VisualSensorErrorRec;
					DigitalVideoSensorErrorRec m_DigitalVideoSensorErrorRec;
					AnalogVideoSensorErrorRec m_AnalogVideoSensorErrorRec;
					StillImageSensorErrorRec m_StillImageSensorErrorRec;
				};
			
				void setParent(ConfirmSensorConfigurationSequence* parent);
				void setParentPresenceVector();
				unsigned int getNumberOfElements() const;
				ConfirmSensorConfigurationVariant* const getElement(unsigned int index);
				int setElement(unsigned int index, const ConfirmSensorConfigurationVariant &value);
				int addElement(const ConfirmSensorConfigurationVariant &value);
				int deleteElement(unsigned int index);
				int deleteLastElement();
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				ConfirmSensorList &operator=(const ConfirmSensorList &value);
				bool operator==(const ConfirmSensorList &value) const;
				bool operator!=(const ConfirmSensorList &value) const;
				ConfirmSensorList();
				ConfirmSensorList(const ConfirmSensorList &value);
				virtual ~ConfirmSensorList();
			
			protected:
				ConfirmSensorConfigurationSequence* m_parent;
				std::vector<ConfirmSensorConfigurationVariant> m_ConfirmSensorConfigurationVariant;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			RequestIdRec* const getRequestIdRec();
			int setRequestIdRec(const RequestIdRec &value);
			ConfirmSensorList* const getConfirmSensorList();
			int setConfirmSensorList(const ConfirmSensorList &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ConfirmSensorConfigurationSequence &operator=(const ConfirmSensorConfigurationSequence &value);
			bool operator==(const ConfirmSensorConfigurationSequence &value) const;
			bool operator!=(const ConfirmSensorConfigurationSequence &value) const;
			ConfirmSensorConfigurationSequence();
			ConfirmSensorConfigurationSequence(const ConfirmSensorConfigurationSequence &value);
			virtual ~ConfirmSensorConfigurationSequence();
		
		protected:
			Body* m_parent;
			RequestIdRec m_RequestIdRec;
			ConfirmSensorList m_ConfirmSensorList;
		};
	
		ConfirmSensorConfigurationSequence* const getConfirmSensorConfigurationSequence();
		int setConfirmSensorConfigurationSequence(const ConfirmSensorConfigurationSequence &value);
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
		ConfirmSensorConfigurationSequence m_ConfirmSensorConfigurationSequence;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ConfirmSensorConfiguration &operator=(const ConfirmSensorConfiguration &value);
	bool operator==(const ConfirmSensorConfiguration &value) const;
	bool operator!=(const ConfirmSensorConfiguration &value) const;
	ConfirmSensorConfiguration();
	ConfirmSensorConfiguration(const ConfirmSensorConfiguration &value);
	virtual ~ConfirmSensorConfiguration();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_CONFIRMSENSORCONFIGURATION_H
