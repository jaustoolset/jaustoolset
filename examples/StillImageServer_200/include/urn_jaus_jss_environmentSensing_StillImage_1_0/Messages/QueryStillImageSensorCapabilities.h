#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYSTILLIMAGESENSORCAPABILITIES_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYSTILLIMAGESENSORCAPABILITIES_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

class DllExport QueryStillImageSensorCapabilities: public JTS::Message
{
public:
	static const int ID = 0x2812;
	
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
		class DllExport QueryStillImageSensorCapabilitiesList
		{
		public:
			class DllExport QueryStillImageSensorCapabilitiesRec
			{
			public:
				void setParent(QueryStillImageSensorCapabilitiesList* parent);
				void setParentPresenceVector();
				jUnsignedShortInteger getSensorID();
				int setSensorID(jUnsignedShortInteger value);
				jUnsignedByte getQueryPresenceVector();
				int setQueryPresenceVector(jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				QueryStillImageSensorCapabilitiesRec &operator=(const QueryStillImageSensorCapabilitiesRec &value);
				bool operator==(const QueryStillImageSensorCapabilitiesRec &value) const;
				bool operator!=(const QueryStillImageSensorCapabilitiesRec &value) const;
				QueryStillImageSensorCapabilitiesRec();
				QueryStillImageSensorCapabilitiesRec(const QueryStillImageSensorCapabilitiesRec &value);
				virtual ~QueryStillImageSensorCapabilitiesRec();
			
			protected:
				QueryStillImageSensorCapabilitiesList* m_parent;
				jUnsignedShortInteger m_SensorID;
				jUnsignedByte m_QueryPresenceVector;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			QueryStillImageSensorCapabilitiesRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const QueryStillImageSensorCapabilitiesRec &value);
			int addElement(const QueryStillImageSensorCapabilitiesRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			QueryStillImageSensorCapabilitiesList &operator=(const QueryStillImageSensorCapabilitiesList &value);
			bool operator==(const QueryStillImageSensorCapabilitiesList &value) const;
			bool operator!=(const QueryStillImageSensorCapabilitiesList &value) const;
			QueryStillImageSensorCapabilitiesList();
			QueryStillImageSensorCapabilitiesList(const QueryStillImageSensorCapabilitiesList &value);
			virtual ~QueryStillImageSensorCapabilitiesList();
		
		protected:
			Body* m_parent;
			std::vector<QueryStillImageSensorCapabilitiesRec> m_QueryStillImageSensorCapabilitiesRec;
		};
	
		QueryStillImageSensorCapabilitiesList* const getQueryStillImageSensorCapabilitiesList();
		int setQueryStillImageSensorCapabilitiesList(const QueryStillImageSensorCapabilitiesList &value);
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
		QueryStillImageSensorCapabilitiesList m_QueryStillImageSensorCapabilitiesList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryStillImageSensorCapabilities &operator=(const QueryStillImageSensorCapabilities &value);
	bool operator==(const QueryStillImageSensorCapabilities &value) const;
	bool operator!=(const QueryStillImageSensorCapabilities &value) const;
	QueryStillImageSensorCapabilities();
	QueryStillImageSensorCapabilities(const QueryStillImageSensorCapabilities &value);
	virtual ~QueryStillImageSensorCapabilities();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYSTILLIMAGESENSORCAPABILITIES_H
