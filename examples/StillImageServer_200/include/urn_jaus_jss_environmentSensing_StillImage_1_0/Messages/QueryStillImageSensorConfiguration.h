#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYSTILLIMAGESENSORCONFIGURATION_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYSTILLIMAGESENSORCONFIGURATION_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

class DllExport QueryStillImageSensorConfiguration: public JTS::Message
{
public:
	static const int ID = 0x2813;
	
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
		class DllExport QueryStillImageSensorConfigurationList
		{
		public:
			class DllExport QueryStillImageSensorConfigurationRec
			{
			public:
				void setParent(QueryStillImageSensorConfigurationList* parent);
				void setParentPresenceVector();
				jUnsignedShortInteger getSensorID();
				int setSensorID(jUnsignedShortInteger value);
				jUnsignedByte getQueryPresenceVector();
				int setQueryPresenceVector(jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				QueryStillImageSensorConfigurationRec &operator=(const QueryStillImageSensorConfigurationRec &value);
				bool operator==(const QueryStillImageSensorConfigurationRec &value) const;
				bool operator!=(const QueryStillImageSensorConfigurationRec &value) const;
				QueryStillImageSensorConfigurationRec();
				QueryStillImageSensorConfigurationRec(const QueryStillImageSensorConfigurationRec &value);
				virtual ~QueryStillImageSensorConfigurationRec();
			
			protected:
				QueryStillImageSensorConfigurationList* m_parent;
				jUnsignedShortInteger m_SensorID;
				jUnsignedByte m_QueryPresenceVector;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			QueryStillImageSensorConfigurationRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const QueryStillImageSensorConfigurationRec &value);
			int addElement(const QueryStillImageSensorConfigurationRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			QueryStillImageSensorConfigurationList &operator=(const QueryStillImageSensorConfigurationList &value);
			bool operator==(const QueryStillImageSensorConfigurationList &value) const;
			bool operator!=(const QueryStillImageSensorConfigurationList &value) const;
			QueryStillImageSensorConfigurationList();
			QueryStillImageSensorConfigurationList(const QueryStillImageSensorConfigurationList &value);
			virtual ~QueryStillImageSensorConfigurationList();
		
		protected:
			Body* m_parent;
			std::vector<QueryStillImageSensorConfigurationRec> m_QueryStillImageSensorConfigurationRec;
		};
	
		QueryStillImageSensorConfigurationList* const getQueryStillImageSensorConfigurationList();
		int setQueryStillImageSensorConfigurationList(const QueryStillImageSensorConfigurationList &value);
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
		QueryStillImageSensorConfigurationList m_QueryStillImageSensorConfigurationList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryStillImageSensorConfiguration &operator=(const QueryStillImageSensorConfiguration &value);
	bool operator==(const QueryStillImageSensorConfiguration &value) const;
	bool operator!=(const QueryStillImageSensorConfiguration &value) const;
	QueryStillImageSensorConfiguration();
	QueryStillImageSensorConfiguration(const QueryStillImageSensorConfiguration &value);
	virtual ~QueryStillImageSensorConfiguration();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYSTILLIMAGESENSORCONFIGURATION_H
