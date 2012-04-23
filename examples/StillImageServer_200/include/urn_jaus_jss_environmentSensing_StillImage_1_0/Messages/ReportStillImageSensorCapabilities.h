#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTSTILLIMAGESENSORCAPABILITIES_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTSTILLIMAGESENSORCAPABILITIES_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

class DllExport ReportStillImageSensorCapabilities: public JTS::Message
{
public:
	static const int ID = 0x4812;
	
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
		class DllExport StillImageSensorList
		{
		public:
			class DllExport StillImageSensorCapabilitiesRec
			{
			public:
				class DllExport SupportedFrameSizes
				{
				public:
					void setParent(StillImageSensorCapabilitiesRec* parent);
					void setParentPresenceVector();
					jUnsignedInteger getSqcif_128x96();
					int setSqcif_128x96(jUnsignedInteger value);
					jUnsignedInteger getQcif_176x144();
					int setQcif_176x144(jUnsignedInteger value);
					jUnsignedInteger getCif_352x288();
					int setCif_352x288(jUnsignedInteger value);
					jUnsignedInteger getCif4_704x576();
					int setCif4_704x576(jUnsignedInteger value);
					jUnsignedInteger getCif16_1408x1152();
					int setCif16_1408x1152(jUnsignedInteger value);
					jUnsignedInteger getQqvga_160x120();
					int setQqvga_160x120(jUnsignedInteger value);
					jUnsignedInteger getQvga_320x240();
					int setQvga_320x240(jUnsignedInteger value);
					jUnsignedInteger getVga_640x480();
					int setVga_640x480(jUnsignedInteger value);
					jUnsignedInteger getSvga_800x600();
					int setSvga_800x600(jUnsignedInteger value);
					jUnsignedInteger getXga_1024x768();
					int setXga_1024x768(jUnsignedInteger value);
					jUnsignedInteger getUxga_1600x1200();
					int setUxga_1600x1200(jUnsignedInteger value);
					jUnsignedInteger getQxga_2048x1536();
					int setQxga_2048x1536(jUnsignedInteger value);
					jUnsignedInteger getSxga_1280x1024();
					int setSxga_1280x1024(jUnsignedInteger value);
					jUnsignedInteger getQsxga_2560x2048();
					int setQsxga_2560x2048(jUnsignedInteger value);
					jUnsignedInteger getHsxga_5120x4096();
					int setHsxga_5120x4096(jUnsignedInteger value);
					jUnsignedInteger getWvga_852x480();
					int setWvga_852x480(jUnsignedInteger value);
					jUnsignedInteger getWxga_1366x768();
					int setWxga_1366x768(jUnsignedInteger value);
					jUnsignedInteger getWsxga_1600x1024();
					int setWsxga_1600x1024(jUnsignedInteger value);
					jUnsignedInteger getWuxga_1920x1200();
					int setWuxga_1920x1200(jUnsignedInteger value);
					jUnsignedInteger getWoxga_2560x1600();
					int setWoxga_2560x1600(jUnsignedInteger value);
					jUnsignedInteger getWqsxga_3200x2048();
					int setWqsxga_3200x2048(jUnsignedInteger value);
					jUnsignedInteger getWquxga_3840x2400();
					int setWquxga_3840x2400(jUnsignedInteger value);
					jUnsignedInteger getWhsxga_6400x4096();
					int setWhsxga_6400x4096(jUnsignedInteger value);
					jUnsignedInteger getWhuxga_7680x4800();
					int setWhuxga_7680x4800(jUnsignedInteger value);
					jUnsignedInteger getCga_320x200();
					int setCga_320x200(jUnsignedInteger value);
					jUnsignedInteger getEga_640x350();
					int setEga_640x350(jUnsignedInteger value);
					jUnsignedInteger getHd480_852x480();
					int setHd480_852x480(jUnsignedInteger value);
					jUnsignedInteger getHd720_1280x720();
					int setHd720_1280x720(jUnsignedInteger value);
					jUnsignedInteger getHd1080_1920x1080();
					int setHd1080_1920x1080(jUnsignedInteger value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					SupportedFrameSizes &operator=(const SupportedFrameSizes &value);
					bool operator==(const SupportedFrameSizes &value) const;
					bool operator!=(const SupportedFrameSizes &value) const;
					SupportedFrameSizes();
					SupportedFrameSizes(const SupportedFrameSizes &value);
					virtual ~SupportedFrameSizes();
				
				protected:
					StillImageSensorCapabilitiesRec* m_parent;
					jUnsignedInteger m_SubFields;
				};
				class DllExport SupportedImageFormats
				{
				public:
					void setParent(StillImageSensorCapabilitiesRec* parent);
					void setParentPresenceVector();
					jUnsignedShortInteger getJPEG();
					int setJPEG(jUnsignedShortInteger value);
					jUnsignedShortInteger getGIF();
					int setGIF(jUnsignedShortInteger value);
					jUnsignedShortInteger getPNG();
					int setPNG(jUnsignedShortInteger value);
					jUnsignedShortInteger getBMP();
					int setBMP(jUnsignedShortInteger value);
					jUnsignedShortInteger getTIFF();
					int setTIFF(jUnsignedShortInteger value);
					jUnsignedShortInteger getPPM();
					int setPPM(jUnsignedShortInteger value);
					jUnsignedShortInteger getPGM();
					int setPGM(jUnsignedShortInteger value);
					jUnsignedShortInteger getPNM();
					int setPNM(jUnsignedShortInteger value);
					jUnsignedShortInteger getNEF_Nikon_RAW();
					int setNEF_Nikon_RAW(jUnsignedShortInteger value);
					jUnsignedShortInteger getCR2_Canon_RAW();
					int setCR2_Canon_RAW(jUnsignedShortInteger value);
					jUnsignedShortInteger getDNG_Adobe_RAW();
					int setDNG_Adobe_RAW(jUnsignedShortInteger value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					SupportedImageFormats &operator=(const SupportedImageFormats &value);
					bool operator==(const SupportedImageFormats &value) const;
					bool operator!=(const SupportedImageFormats &value) const;
					SupportedImageFormats();
					SupportedImageFormats(const SupportedImageFormats &value);
					virtual ~SupportedImageFormats();
				
				protected:
					StillImageSensorCapabilitiesRec* m_parent;
					jUnsignedShortInteger m_SubFields;
				};
			
				void setParent(StillImageSensorList* parent);
				void setParentPresenceVector();
				jUnsignedByte getPresenceVector();
				bool checkPresenceVector(unsigned int index) const;
				jUnsignedShortInteger getSensorID();
				int setSensorID(jUnsignedShortInteger value);
				bool isSupportedFrameSizesValid() const;
				SupportedFrameSizes* const getSupportedFrameSizes();
				int setSupportedFrameSizes(const SupportedFrameSizes &value);
				bool isSupportedImageFormatsValid() const;
				SupportedImageFormats* const getSupportedImageFormats();
				int setSupportedImageFormats(const SupportedImageFormats &value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				StillImageSensorCapabilitiesRec &operator=(const StillImageSensorCapabilitiesRec &value);
				bool operator==(const StillImageSensorCapabilitiesRec &value) const;
				bool operator!=(const StillImageSensorCapabilitiesRec &value) const;
				StillImageSensorCapabilitiesRec();
				StillImageSensorCapabilitiesRec(const StillImageSensorCapabilitiesRec &value);
				virtual ~StillImageSensorCapabilitiesRec();
			
			protected:
				int setPresenceVector(unsigned int index);
			
				StillImageSensorList* m_parent;
				jUnsignedByte m_PresenceVector;
				jUnsignedShortInteger m_SensorID;
				SupportedFrameSizes m_SupportedFrameSizes;
				SupportedImageFormats m_SupportedImageFormats;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			StillImageSensorCapabilitiesRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const StillImageSensorCapabilitiesRec &value);
			int addElement(const StillImageSensorCapabilitiesRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			StillImageSensorList &operator=(const StillImageSensorList &value);
			bool operator==(const StillImageSensorList &value) const;
			bool operator!=(const StillImageSensorList &value) const;
			StillImageSensorList();
			StillImageSensorList(const StillImageSensorList &value);
			virtual ~StillImageSensorList();
		
		protected:
			Body* m_parent;
			std::vector<StillImageSensorCapabilitiesRec> m_StillImageSensorCapabilitiesRec;
		};
	
		StillImageSensorList* const getStillImageSensorList();
		int setStillImageSensorList(const StillImageSensorList &value);
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
		StillImageSensorList m_StillImageSensorList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportStillImageSensorCapabilities &operator=(const ReportStillImageSensorCapabilities &value);
	bool operator==(const ReportStillImageSensorCapabilities &value) const;
	bool operator!=(const ReportStillImageSensorCapabilities &value) const;
	ReportStillImageSensorCapabilities();
	ReportStillImageSensorCapabilities(const ReportStillImageSensorCapabilities &value);
	virtual ~ReportStillImageSensorCapabilities();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTSTILLIMAGESENSORCAPABILITIES_H
