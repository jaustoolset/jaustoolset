#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTSTILLIMAGEDATA_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTSTILLIMAGEDATA_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

class DllExport ReportStillImageData: public JTS::Message
{
public:
	static const int ID = 0x4814;
	
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
		class DllExport StillImageDataList
		{
		public:
			class DllExport StillImageDataRec
			{
			public:
				class DllExport TimeStamp
				{
				public:
					void setParent(StillImageDataRec* parent);
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
					StillImageDataRec* m_parent;
					jUnsignedInteger m_SubFields;
				};
				class DllExport ImageFrame
				{
				public:
					void setParent(StillImageDataRec* parent);
					void setParentPresenceVector();
					const jUnsignedByte getFormat() const;
					const jUnsignedInteger getLength() const;
					const unsigned char *getData() const;
					int set(jUnsignedByte format, jUnsignedInteger length, unsigned char *data);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					ImageFrame &operator=(const ImageFrame &value);
					bool operator==(const ImageFrame &value) const;
					bool operator!=(const ImageFrame &value) const;
					ImageFrame();
					ImageFrame(const ImageFrame &value);
					virtual ~ImageFrame();
				
				protected:
					StillImageDataRec* m_parent;
					jUnsignedByte m_Format;
					jUnsignedInteger m_Length;
					unsigned char *m_Data;
				};
			
				void setParent(StillImageDataList* parent);
				void setParentPresenceVector();
				jUnsignedByte getPresenceVector();
				bool checkPresenceVector(unsigned int index) const;
				jUnsignedShortInteger getSensorID();
				int setSensorID(jUnsignedShortInteger value);
				jUnsignedByte getReportCoordinateSystem();
				int setReportCoordinateSystem(jUnsignedByte value);
				bool isTimeStampValid() const;
				TimeStamp* const getTimeStamp();
				int setTimeStamp(const TimeStamp &value);
				ImageFrame* const getImageFrame();
				int setImageFrame(const ImageFrame &value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				StillImageDataRec &operator=(const StillImageDataRec &value);
				bool operator==(const StillImageDataRec &value) const;
				bool operator!=(const StillImageDataRec &value) const;
				StillImageDataRec();
				StillImageDataRec(const StillImageDataRec &value);
				virtual ~StillImageDataRec();
			
			protected:
				int setPresenceVector(unsigned int index);
			
				StillImageDataList* m_parent;
				jUnsignedByte m_PresenceVector;
				jUnsignedShortInteger m_SensorID;
				jUnsignedByte m_ReportCoordinateSystem;
				TimeStamp m_TimeStamp;
				ImageFrame m_ImageFrame;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			StillImageDataRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const StillImageDataRec &value);
			int addElement(const StillImageDataRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			StillImageDataList &operator=(const StillImageDataList &value);
			bool operator==(const StillImageDataList &value) const;
			bool operator!=(const StillImageDataList &value) const;
			StillImageDataList();
			StillImageDataList(const StillImageDataList &value);
			virtual ~StillImageDataList();
		
		protected:
			Body* m_parent;
			std::vector<StillImageDataRec> m_StillImageDataRec;
		};
	
		StillImageDataList* const getStillImageDataList();
		int setStillImageDataList(const StillImageDataList &value);
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
		StillImageDataList m_StillImageDataList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportStillImageData &operator=(const ReportStillImageData &value);
	bool operator==(const ReportStillImageData &value) const;
	bool operator!=(const ReportStillImageData &value) const;
	ReportStillImageData();
	ReportStillImageData(const ReportStillImageData &value);
	virtual ~ReportStillImageData();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_REPORTSTILLIMAGEDATA_H
