#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYSTILLIMAGEDATA_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYSTILLIMAGEDATA_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

class DllExport QueryStillImageData: public JTS::Message
{
public:
	static const int ID = 0x2814;
	
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
		class DllExport QueryStillImageDataList
		{
		public:
			class DllExport QueryStillImageDataRec
			{
			public:
				void setParent(QueryStillImageDataList* parent);
				void setParentPresenceVector();
				jUnsignedShortInteger getSensorID();
				int setSensorID(jUnsignedShortInteger value);
				jUnsignedByte getReportCoordinateSystem();
				int setReportCoordinateSystem(jUnsignedByte value);
				jUnsignedByte getQueryPresenceVector();
				int setQueryPresenceVector(jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				QueryStillImageDataRec &operator=(const QueryStillImageDataRec &value);
				bool operator==(const QueryStillImageDataRec &value) const;
				bool operator!=(const QueryStillImageDataRec &value) const;
				QueryStillImageDataRec();
				QueryStillImageDataRec(const QueryStillImageDataRec &value);
				virtual ~QueryStillImageDataRec();
			
			protected:
				QueryStillImageDataList* m_parent;
				jUnsignedShortInteger m_SensorID;
				jUnsignedByte m_ReportCoordinateSystem;
				jUnsignedByte m_QueryPresenceVector;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			QueryStillImageDataRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const QueryStillImageDataRec &value);
			int addElement(const QueryStillImageDataRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			QueryStillImageDataList &operator=(const QueryStillImageDataList &value);
			bool operator==(const QueryStillImageDataList &value) const;
			bool operator!=(const QueryStillImageDataList &value) const;
			QueryStillImageDataList();
			QueryStillImageDataList(const QueryStillImageDataList &value);
			virtual ~QueryStillImageDataList();
		
		protected:
			Body* m_parent;
			std::vector<QueryStillImageDataRec> m_QueryStillImageDataRec;
		};
	
		QueryStillImageDataList* const getQueryStillImageDataList();
		int setQueryStillImageDataList(const QueryStillImageDataList &value);
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
		QueryStillImageDataList m_QueryStillImageDataList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryStillImageData &operator=(const QueryStillImageData &value);
	bool operator==(const QueryStillImageData &value) const;
	bool operator!=(const QueryStillImageData &value) const;
	QueryStillImageData();
	QueryStillImageData(const QueryStillImageData &value);
	virtual ~QueryStillImageData();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYSTILLIMAGEDATA_H
