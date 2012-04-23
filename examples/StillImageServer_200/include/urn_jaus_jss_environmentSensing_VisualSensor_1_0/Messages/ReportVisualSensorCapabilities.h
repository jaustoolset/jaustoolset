#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_REPORTVISUALSENSORCAPABILITIES_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_REPORTVISUALSENSORCAPABILITIES_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0
{

class DllExport ReportVisualSensorCapabilities: public JTS::Message
{
public:
	static const int ID = 0x4806;
	
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
		class DllExport VisualSensorCapabilitiesList
		{
		public:
			class DllExport VisualSensorCapabilitiesRec
			{
			public:
				class DllExport SupportedStates
				{
				public:
					void setParent(VisualSensorCapabilitiesRec* parent);
					void setParentPresenceVector();
					jUnsignedByte getActive();
					int setActive(jUnsignedByte value);
					jUnsignedByte getStandby();
					int setStandby(jUnsignedByte value);
					jUnsignedByte getOff();
					int setOff(jUnsignedByte value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					SupportedStates &operator=(const SupportedStates &value);
					bool operator==(const SupportedStates &value) const;
					bool operator!=(const SupportedStates &value) const;
					SupportedStates();
					SupportedStates(const SupportedStates &value);
					virtual ~SupportedStates();
				
				protected:
					VisualSensorCapabilitiesRec* m_parent;
					jUnsignedByte m_SubFields;
				};
				class DllExport ZoomModes
				{
				public:
					void setParent(VisualSensorCapabilitiesRec* parent);
					void setParentPresenceVector();
					jUnsignedByte getMixed();
					int setMixed(jUnsignedByte value);
					jUnsignedByte getAnalogOnly();
					int setAnalogOnly(jUnsignedByte value);
					jUnsignedByte getDigitalOnly();
					int setDigitalOnly(jUnsignedByte value);
					jUnsignedByte getNone();
					int setNone(jUnsignedByte value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					ZoomModes &operator=(const ZoomModes &value);
					bool operator==(const ZoomModes &value) const;
					bool operator!=(const ZoomModes &value) const;
					ZoomModes();
					ZoomModes(const ZoomModes &value);
					virtual ~ZoomModes();
				
				protected:
					VisualSensorCapabilitiesRec* m_parent;
					jUnsignedByte m_SubFields;
				};
				class DllExport FocusModes
				{
				public:
					void setParent(VisualSensorCapabilitiesRec* parent);
					void setParentPresenceVector();
					jUnsignedByte getAutoFocus();
					int setAutoFocus(jUnsignedByte value);
					jUnsignedByte getManualFocus();
					int setManualFocus(jUnsignedByte value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					FocusModes &operator=(const FocusModes &value);
					bool operator==(const FocusModes &value) const;
					bool operator!=(const FocusModes &value) const;
					FocusModes();
					FocusModes(const FocusModes &value);
					virtual ~FocusModes();
				
				protected:
					VisualSensorCapabilitiesRec* m_parent;
					jUnsignedByte m_SubFields;
				};
				class DllExport WhiteBalance
				{
				public:
					void setParent(VisualSensorCapabilitiesRec* parent);
					void setParentPresenceVector();
					jUnsignedByte getAuto();
					int setAuto(jUnsignedByte value);
					jUnsignedByte getDaylight();
					int setDaylight(jUnsignedByte value);
					jUnsignedByte getCloudy();
					int setCloudy(jUnsignedByte value);
					jUnsignedByte getShade();
					int setShade(jUnsignedByte value);
					jUnsignedByte getTungsten();
					int setTungsten(jUnsignedByte value);
					jUnsignedByte getFlurescent();
					int setFlurescent(jUnsignedByte value);
					jUnsignedByte getFlash();
					int setFlash(jUnsignedByte value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					WhiteBalance &operator=(const WhiteBalance &value);
					bool operator==(const WhiteBalance &value) const;
					bool operator!=(const WhiteBalance &value) const;
					WhiteBalance();
					WhiteBalance(const WhiteBalance &value);
					virtual ~WhiteBalance();
				
				protected:
					VisualSensorCapabilitiesRec* m_parent;
					jUnsignedByte m_SubFields;
				};
				class DllExport ImagingModes
				{
				public:
					void setParent(VisualSensorCapabilitiesRec* parent);
					void setParentPresenceVector();
					jUnsignedByte getColor();
					int setColor(jUnsignedByte value);
					jUnsignedByte getGreyscale();
					int setGreyscale(jUnsignedByte value);
					jUnsignedByte getInfrared();
					int setInfrared(jUnsignedByte value);
					jUnsignedByte getLowlight();
					int setLowlight(jUnsignedByte value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					ImagingModes &operator=(const ImagingModes &value);
					bool operator==(const ImagingModes &value) const;
					bool operator!=(const ImagingModes &value) const;
					ImagingModes();
					ImagingModes(const ImagingModes &value);
					virtual ~ImagingModes();
				
				protected:
					VisualSensorCapabilitiesRec* m_parent;
					jUnsignedByte m_SubFields;
				};
				class DllExport ExposureModes
				{
				public:
					void setParent(VisualSensorCapabilitiesRec* parent);
					void setParentPresenceVector();
					jUnsignedByte getAuto();
					int setAuto(jUnsignedByte value);
					jUnsignedByte getManual();
					int setManual(jUnsignedByte value);
					jUnsignedByte getShutterPriority();
					int setShutterPriority(jUnsignedByte value);
					jUnsignedByte getAperturePriority();
					int setAperturePriority(jUnsignedByte value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					ExposureModes &operator=(const ExposureModes &value);
					bool operator==(const ExposureModes &value) const;
					bool operator!=(const ExposureModes &value) const;
					ExposureModes();
					ExposureModes(const ExposureModes &value);
					virtual ~ExposureModes();
				
				protected:
					VisualSensorCapabilitiesRec* m_parent;
					jUnsignedByte m_SubFields;
				};
				class DllExport MeteringModes
				{
				public:
					void setParent(VisualSensorCapabilitiesRec* parent);
					void setParentPresenceVector();
					jUnsignedByte getMatrixOrAuto();
					int setMatrixOrAuto(jUnsignedByte value);
					jUnsignedByte getCenterWeighted();
					int setCenterWeighted(jUnsignedByte value);
					jUnsignedByte getSpot();
					int setSpot(jUnsignedByte value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					MeteringModes &operator=(const MeteringModes &value);
					bool operator==(const MeteringModes &value) const;
					bool operator!=(const MeteringModes &value) const;
					MeteringModes();
					MeteringModes(const MeteringModes &value);
					virtual ~MeteringModes();
				
				protected:
					VisualSensorCapabilitiesRec* m_parent;
					jUnsignedByte m_SubFields;
				};
				class DllExport LightSensitivityLevels
				{
				public:
					void setParent(VisualSensorCapabilitiesRec* parent);
					void setParentPresenceVector();
					jUnsignedByte getAuto();
					int setAuto(jUnsignedByte value);
					jUnsignedByte getISO100();
					int setISO100(jUnsignedByte value);
					jUnsignedByte getISO200();
					int setISO200(jUnsignedByte value);
					jUnsignedByte getISO400();
					int setISO400(jUnsignedByte value);
					jUnsignedByte getISO800();
					int setISO800(jUnsignedByte value);
					jUnsignedByte getISO1600();
					int setISO1600(jUnsignedByte value);
					jUnsignedByte getISO3200();
					int setISO3200(jUnsignedByte value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					LightSensitivityLevels &operator=(const LightSensitivityLevels &value);
					bool operator==(const LightSensitivityLevels &value) const;
					bool operator!=(const LightSensitivityLevels &value) const;
					LightSensitivityLevels();
					LightSensitivityLevels(const LightSensitivityLevels &value);
					virtual ~LightSensitivityLevels();
				
				protected:
					VisualSensorCapabilitiesRec* m_parent;
					jUnsignedByte m_SubFields;
				};
			
				void setParent(VisualSensorCapabilitiesList* parent);
				void setParentPresenceVector();
				jUnsignedShortInteger getPresenceVector();
				bool checkPresenceVector(unsigned int index) const;
				jUnsignedShortInteger getSensorID();
				int setSensorID(jUnsignedShortInteger value);
				jVariableLengthString getSensorName();
				int setSensorName(jVariableLengthString value);
				bool isSupportedStatesValid() const;
				SupportedStates* const getSupportedStates();
				int setSupportedStates(const SupportedStates &value);
				bool isZoomModesValid() const;
				ZoomModes* const getZoomModes();
				int setZoomModes(const ZoomModes &value);
				bool isFocusModesValid() const;
				FocusModes* const getFocusModes();
				int setFocusModes(const FocusModes &value);
				bool isWhiteBalanceValid() const;
				WhiteBalance* const getWhiteBalance();
				int setWhiteBalance(const WhiteBalance &value);
				bool isImagingModesValid() const;
				ImagingModes* const getImagingModes();
				int setImagingModes(const ImagingModes &value);
				bool isExposureModesValid() const;
				ExposureModes* const getExposureModes();
				int setExposureModes(const ExposureModes &value);
				bool isMeteringModesValid() const;
				MeteringModes* const getMeteringModes();
				int setMeteringModes(const MeteringModes &value);
				bool isMinimumShutterSpeedValid() const;
				double getMinimumShutterSpeed();
				int setMinimumShutterSpeed(double value);
				bool isMaximumShutterSpeedValid() const;
				double getMaximumShutterSpeed();
				int setMaximumShutterSpeed(double value);
				bool isMinimumApertureValid() const;
				double getMinimumAperture();
				int setMinimumAperture(double value);
				bool isMaximumApertureValid() const;
				double getMaximumAperture();
				int setMaximumAperture(double value);
				bool isMinimumFocalLengthValid() const;
				double getMinimumFocalLength();
				int setMinimumFocalLength(double value);
				bool isMaximumFocalLengthValid() const;
				double getMaximumFocalLength();
				int setMaximumFocalLength(double value);
				bool isLightSensitivityLevelsValid() const;
				LightSensitivityLevels* const getLightSensitivityLevels();
				int setLightSensitivityLevels(const LightSensitivityLevels &value);
				bool isImageStabilizationValid() const;
				jUnsignedByte getImageStabilization();
				int setImageStabilization(jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				VisualSensorCapabilitiesRec &operator=(const VisualSensorCapabilitiesRec &value);
				bool operator==(const VisualSensorCapabilitiesRec &value) const;
				bool operator!=(const VisualSensorCapabilitiesRec &value) const;
				VisualSensorCapabilitiesRec();
				VisualSensorCapabilitiesRec(const VisualSensorCapabilitiesRec &value);
				virtual ~VisualSensorCapabilitiesRec();
			
			protected:
				int setPresenceVector(unsigned int index);
			
				VisualSensorCapabilitiesList* m_parent;
				jUnsignedShortInteger m_PresenceVector;
				jUnsignedShortInteger m_SensorID;
				jVariableLengthString m_SensorName;
				SupportedStates m_SupportedStates;
				ZoomModes m_ZoomModes;
				FocusModes m_FocusModes;
				WhiteBalance m_WhiteBalance;
				ImagingModes m_ImagingModes;
				ExposureModes m_ExposureModes;
				MeteringModes m_MeteringModes;
				jUnsignedShortInteger m_MinimumShutterSpeed;
				jUnsignedShortInteger m_MaximumShutterSpeed;
				jUnsignedShortInteger m_MinimumAperture;
				jUnsignedShortInteger m_MaximumAperture;
				jUnsignedInteger m_MinimumFocalLength;
				jUnsignedInteger m_MaximumFocalLength;
				LightSensitivityLevels m_LightSensitivityLevels;
				jUnsignedByte m_ImageStabilization;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			VisualSensorCapabilitiesRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const VisualSensorCapabilitiesRec &value);
			int addElement(const VisualSensorCapabilitiesRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			VisualSensorCapabilitiesList &operator=(const VisualSensorCapabilitiesList &value);
			bool operator==(const VisualSensorCapabilitiesList &value) const;
			bool operator!=(const VisualSensorCapabilitiesList &value) const;
			VisualSensorCapabilitiesList();
			VisualSensorCapabilitiesList(const VisualSensorCapabilitiesList &value);
			virtual ~VisualSensorCapabilitiesList();
		
		protected:
			Body* m_parent;
			std::vector<VisualSensorCapabilitiesRec> m_VisualSensorCapabilitiesRec;
		};
	
		VisualSensorCapabilitiesList* const getVisualSensorCapabilitiesList();
		int setVisualSensorCapabilitiesList(const VisualSensorCapabilitiesList &value);
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
		VisualSensorCapabilitiesList m_VisualSensorCapabilitiesList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportVisualSensorCapabilities &operator=(const ReportVisualSensorCapabilities &value);
	bool operator==(const ReportVisualSensorCapabilities &value) const;
	bool operator!=(const ReportVisualSensorCapabilities &value) const;
	ReportVisualSensorCapabilities();
	ReportVisualSensorCapabilities(const ReportVisualSensorCapabilities &value);
	virtual ~ReportVisualSensorCapabilities();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_REPORTVISUALSENSORCAPABILITIES_H
