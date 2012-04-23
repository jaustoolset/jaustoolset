#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_SETSTILLIMAGESENSORCONFIGURATION_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_SETSTILLIMAGESENSORCONFIGURATION_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

class DllExport SetStillImageSensorConfiguration: public JTS::Message
{
public:
	static const int ID = 0x0807;
	
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
		class DllExport StillImageSensorConfigurationSequence
		{
		public:
			class DllExport RequestIdRec
			{
			public:
				void setParent(StillImageSensorConfigurationSequence* parent);
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
				StillImageSensorConfigurationSequence* m_parent;
				jUnsignedByte m_RequestID;
			};
			class DllExport StillImageSensorList
			{
			public:
				class DllExport StillImageSensorConfigurationRec
				{
				public:
					void setParent(StillImageSensorList* parent);
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
				
					StillImageSensorList* m_parent;
					jUnsignedByte m_PresenceVector;
					jUnsignedShortInteger m_SensorID;
					jUnsignedByte m_FrameSize;
					jUnsignedByte m_StillImageFormat;
				};
			
				void setParent(StillImageSensorConfigurationSequence* parent);
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
				StillImageSensorList &operator=(const StillImageSensorList &value);
				bool operator==(const StillImageSensorList &value) const;
				bool operator!=(const StillImageSensorList &value) const;
				StillImageSensorList();
				StillImageSensorList(const StillImageSensorList &value);
				virtual ~StillImageSensorList();
			
			protected:
				StillImageSensorConfigurationSequence* m_parent;
				std::vector<StillImageSensorConfigurationRec> m_StillImageSensorConfigurationRec;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			RequestIdRec* const getRequestIdRec();
			int setRequestIdRec(const RequestIdRec &value);
			StillImageSensorList* const getStillImageSensorList();
			int setStillImageSensorList(const StillImageSensorList &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			StillImageSensorConfigurationSequence &operator=(const StillImageSensorConfigurationSequence &value);
			bool operator==(const StillImageSensorConfigurationSequence &value) const;
			bool operator!=(const StillImageSensorConfigurationSequence &value) const;
			StillImageSensorConfigurationSequence();
			StillImageSensorConfigurationSequence(const StillImageSensorConfigurationSequence &value);
			virtual ~StillImageSensorConfigurationSequence();
		
		protected:
			Body* m_parent;
			RequestIdRec m_RequestIdRec;
			StillImageSensorList m_StillImageSensorList;
		};
	
		StillImageSensorConfigurationSequence* const getStillImageSensorConfigurationSequence();
		int setStillImageSensorConfigurationSequence(const StillImageSensorConfigurationSequence &value);
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
		StillImageSensorConfigurationSequence m_StillImageSensorConfigurationSequence;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	SetStillImageSensorConfiguration &operator=(const SetStillImageSensorConfiguration &value);
	bool operator==(const SetStillImageSensorConfiguration &value) const;
	bool operator!=(const SetStillImageSensorConfiguration &value) const;
	SetStillImageSensorConfiguration();
	SetStillImageSensorConfiguration(const SetStillImageSensorConfiguration &value);
	virtual ~SetStillImageSensorConfiguration();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_SETSTILLIMAGESENSORCONFIGURATION_H
