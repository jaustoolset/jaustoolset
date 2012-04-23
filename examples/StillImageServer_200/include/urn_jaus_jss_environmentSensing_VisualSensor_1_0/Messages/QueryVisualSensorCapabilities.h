#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_QUERYVISUALSENSORCAPABILITIES_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_QUERYVISUALSENSORCAPABILITIES_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0
{

class DllExport QueryVisualSensorCapabilities: public JTS::Message
{
public:
	static const int ID = 0x2806;
	
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
		class DllExport QueryVisualSensorCapabilitiesList
		{
		public:
			class DllExport QueryVisualSensorCapabilitiesRec
			{
			public:
				void setParent(QueryVisualSensorCapabilitiesList* parent);
				void setParentPresenceVector();
				jUnsignedShortInteger getSensorID();
				int setSensorID(jUnsignedShortInteger value);
				jUnsignedShortInteger getQueryPresenceVector();
				int setQueryPresenceVector(jUnsignedShortInteger value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				QueryVisualSensorCapabilitiesRec &operator=(const QueryVisualSensorCapabilitiesRec &value);
				bool operator==(const QueryVisualSensorCapabilitiesRec &value) const;
				bool operator!=(const QueryVisualSensorCapabilitiesRec &value) const;
				QueryVisualSensorCapabilitiesRec();
				QueryVisualSensorCapabilitiesRec(const QueryVisualSensorCapabilitiesRec &value);
				virtual ~QueryVisualSensorCapabilitiesRec();
			
			protected:
				QueryVisualSensorCapabilitiesList* m_parent;
				jUnsignedShortInteger m_SensorID;
				jUnsignedShortInteger m_QueryPresenceVector;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			QueryVisualSensorCapabilitiesRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const QueryVisualSensorCapabilitiesRec &value);
			int addElement(const QueryVisualSensorCapabilitiesRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			QueryVisualSensorCapabilitiesList &operator=(const QueryVisualSensorCapabilitiesList &value);
			bool operator==(const QueryVisualSensorCapabilitiesList &value) const;
			bool operator!=(const QueryVisualSensorCapabilitiesList &value) const;
			QueryVisualSensorCapabilitiesList();
			QueryVisualSensorCapabilitiesList(const QueryVisualSensorCapabilitiesList &value);
			virtual ~QueryVisualSensorCapabilitiesList();
		
		protected:
			Body* m_parent;
			std::vector<QueryVisualSensorCapabilitiesRec> m_QueryVisualSensorCapabilitiesRec;
		};
	
		QueryVisualSensorCapabilitiesList* const getQueryVisualSensorCapabilitiesList();
		int setQueryVisualSensorCapabilitiesList(const QueryVisualSensorCapabilitiesList &value);
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
		QueryVisualSensorCapabilitiesList m_QueryVisualSensorCapabilitiesList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryVisualSensorCapabilities &operator=(const QueryVisualSensorCapabilities &value);
	bool operator==(const QueryVisualSensorCapabilities &value) const;
	bool operator!=(const QueryVisualSensorCapabilities &value) const;
	QueryVisualSensorCapabilities();
	QueryVisualSensorCapabilities(const QueryVisualSensorCapabilities &value);
	virtual ~QueryVisualSensorCapabilities();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_QUERYVISUALSENSORCAPABILITIES_H
