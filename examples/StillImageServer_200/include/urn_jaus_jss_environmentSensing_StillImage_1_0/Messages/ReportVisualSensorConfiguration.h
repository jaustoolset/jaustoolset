#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTVISUALSENSORCONFIGURATION_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTVISUALSENSORCONFIGURATION_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

class DllExport ReportVisualSensorConfiguration: public JTS::Message
{
public:
	static const int ID = 0x4807;
	
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
		class DllExport VisualSensorConfigurationList
		{
		public:
			class DllExport VisualSensorConfigurationRec
			{
			public:
				void setParent(VisualSensorConfigurationList* parent);
				void setParentPresenceVector();
				jUnsignedInteger getPresenceVector();
				bool checkPresenceVector(unsigned int index) const;
				jUnsignedShortInteger getSensorID();
				int setSensorID(jUnsignedShortInteger value);
				bool isSensorStateValid() const;
				jUnsignedByte getSensorState();
				int setSensorState(jUnsignedByte value);
				bool isZoomModeValid() const;
				jUnsignedByte getZoomMode();
				int setZoomMode(jUnsignedByte value);
				bool isZoomLevelValid() const;
				double getZoomLevel();
				int setZoomLevel(double value);
				bool isFocalLengthValid() const;
				double getFocalLength();
				int setFocalLength(double value);
				bool isHorizontalFieldOfViewValid() const;
				double getHorizontalFieldOfView();
				int setHorizontalFieldOfView(double value);
				bool isVerticalFieldOfViewValid() const;
				double getVerticalFieldOfView();
				int setVerticalFieldOfView(double value);
				bool isFocusModeValid() const;
				jUnsignedByte getFocusMode();
				int setFocusMode(jUnsignedByte value);
				bool isFocusValueValid() const;
				double getFocusValue();
				int setFocusValue(double value);
				bool isWhiteBalanceValid() const;
				jUnsignedByte getWhiteBalance();
				int setWhiteBalance(jUnsignedByte value);
				bool isImagingModeValid() const;
				jUnsignedByte getImagingMode();
				int setImagingMode(jUnsignedByte value);
				bool isExposureModeValid() const;
				jUnsignedByte getExposureMode();
				int setExposureMode(jUnsignedByte value);
				bool isMeteringModeValid() const;
				jUnsignedByte getMeteringMode();
				int setMeteringMode(jUnsignedByte value);
				bool isShutterSpeedValid() const;
				double getShutterSpeed();
				int setShutterSpeed(double value);
				bool isApertureValid() const;
				double getAperture();
				int setAperture(double value);
				bool isLightSensitivityValid() const;
				jUnsignedByte getLightSensitivity();
				int setLightSensitivity(jUnsignedByte value);
				bool isImageStablizationValid() const;
				jUnsignedByte getImageStablization();
				int setImageStablization(jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				VisualSensorConfigurationRec &operator=(const VisualSensorConfigurationRec &value);
				bool operator==(const VisualSensorConfigurationRec &value) const;
				bool operator!=(const VisualSensorConfigurationRec &value) const;
				VisualSensorConfigurationRec();
				VisualSensorConfigurationRec(const VisualSensorConfigurationRec &value);
				virtual ~VisualSensorConfigurationRec();
			
			protected:
				int setPresenceVector(unsigned int index);
			
				VisualSensorConfigurationList* m_parent;
				jUnsignedInteger m_PresenceVector;
				jUnsignedShortInteger m_SensorID;
				jUnsignedByte m_SensorState;
				jUnsignedByte m_ZoomMode;
				jUnsignedShortInteger m_ZoomLevel;
				jUnsignedInteger m_FocalLength;
				jUnsignedInteger m_HorizontalFieldOfView;
				jUnsignedInteger m_VerticalFieldOfView;
				jUnsignedByte m_FocusMode;
				jUnsignedShortInteger m_FocusValue;
				jUnsignedByte m_WhiteBalance;
				jUnsignedByte m_ImagingMode;
				jUnsignedByte m_ExposureMode;
				jUnsignedByte m_MeteringMode;
				jUnsignedShortInteger m_ShutterSpeed;
				jUnsignedShortInteger m_Aperture;
				jUnsignedByte m_LightSensitivity;
				jUnsignedByte m_ImageStablization;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			VisualSensorConfigurationRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const VisualSensorConfigurationRec &value);
			int addElement(const VisualSensorConfigurationRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			VisualSensorConfigurationList &operator=(const VisualSensorConfigurationList &value);
			bool operator==(const VisualSensorConfigurationList &value) const;
			bool operator!=(const VisualSensorConfigurationList &value) const;
			VisualSensorConfigurationList();
			VisualSensorConfigurationList(const VisualSensorConfigurationList &value);
			virtual ~VisualSensorConfigurationList();
		
		protected:
			Body* m_parent;
			std::vector<VisualSensorConfigurationRec> m_VisualSensorConfigurationRec;
		};
	
		VisualSensorConfigurationList* const getVisualSensorConfigurationList();
		int setVisualSensorConfigurationList(const VisualSensorConfigurationList &value);
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
		VisualSensorConfigurationList m_VisualSensorConfigurationList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportVisualSensorConfiguration &operator=(const ReportVisualSensorConfiguration &value);
	bool operator==(const ReportVisualSensorConfiguration &value) const;
	bool operator!=(const ReportVisualSensorConfiguration &value) const;
	ReportVisualSensorConfiguration();
	ReportVisualSensorConfiguration(const ReportVisualSensorConfiguration &value);
	virtual ~ReportVisualSensorConfiguration();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTVISUALSENSORCONFIGURATION_H
