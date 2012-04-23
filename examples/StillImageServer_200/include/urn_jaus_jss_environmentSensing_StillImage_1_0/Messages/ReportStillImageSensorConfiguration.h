#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTSTILLIMAGESENSORCONFIGURATION_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTSTILLIMAGESENSORCONFIGURATION_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

class DllExport ReportStillImageSensorConfiguration: public JTS::Message
{
public:
	static const int ID = 0x4813;
	
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
		class DllExport StillImageSensorConfigurationList
		{
		public:
			class DllExport StillImageSensorConfigurationRec
			{
			public:
				void setParent(StillImageSensorConfigurationList* parent);
				void setParentPresenceVector();
				jUnsignedByte getPresenceVector();
				bool checkPresenceVector(unsigned int index) const;
				jUnsignedShortInteger getSensorID();
				int setSensorID(jUnsignedShortInteger value);
				bool isFrameSizeValid() const;
				jUnsignedByte getFrameSize();
				int setFrameSize(jUnsignedByte value);
				bool isStillImageFormatValid() const;
				jUnsignedByte getStillImageFormat();
				int setStillImageFormat(jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				StillImageSensorConfigurationRec &operator=(const StillImageSensorConfigurationRec &value);
				bool operator==(const StillImageSensorConfigurationRec &value) const;
				bool operator!=(const StillImageSensorConfigurationRec &value) const;
				StillImageSensorConfigurationRec();
				StillImageSensorConfigurationRec(const StillImageSensorConfigurationRec &value);
				virtual ~StillImageSensorConfigurationRec();
			
			protected:
				int setPresenceVector(unsigned int index);
			
				StillImageSensorConfigurationList* m_parent;
				jUnsignedByte m_PresenceVector;
				jUnsignedShortInteger m_SensorID;
				jUnsignedByte m_FrameSize;
				jUnsignedByte m_StillImageFormat;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			StillImageSensorConfigurationRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const StillImageSensorConfigurationRec &value);
			int addElement(const StillImageSensorConfigurationRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			StillImageSensorConfigurationList &operator=(const StillImageSensorConfigurationList &value);
			bool operator==(const StillImageSensorConfigurationList &value) const;
			bool operator!=(const StillImageSensorConfigurationList &value) const;
			StillImageSensorConfigurationList();
			StillImageSensorConfigurationList(const StillImageSensorConfigurationList &value);
			virtual ~StillImageSensorConfigurationList();
		
		protected:
			Body* m_parent;
			std::vector<StillImageSensorConfigurationRec> m_StillImageSensorConfigurationRec;
		};
	
		StillImageSensorConfigurationList* const getStillImageSensorConfigurationList();
		int setStillImageSensorConfigurationList(const StillImageSensorConfigurationList &value);
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
		StillImageSensorConfigurationList m_StillImageSensorConfigurationList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportStillImageSensorConfiguration &operator=(const ReportStillImageSensorConfiguration &value);
	bool operator==(const ReportStillImageSensorConfiguration &value) const;
	bool operator!=(const ReportStillImageSensorConfiguration &value) const;
	ReportStillImageSensorConfiguration();
	ReportStillImageSensorConfiguration(const ReportStillImageSensorConfiguration &value);
	virtual ~ReportStillImageSensorConfiguration();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTSTILLIMAGESENSORCONFIGURATION_H
