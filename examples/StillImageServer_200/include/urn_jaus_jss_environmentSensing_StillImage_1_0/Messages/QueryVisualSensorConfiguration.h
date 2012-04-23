#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYVISUALSENSORCONFIGURATION_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYVISUALSENSORCONFIGURATION_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

class DllExport QueryVisualSensorConfiguration: public JTS::Message
{
public:
	static const int ID = 0x2807;
	
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
		class DllExport QueryVisualSensorConfigurationList
		{
		public:
			class DllExport QueryVisualSensorConfigurationRec
			{
			public:
				void setParent(QueryVisualSensorConfigurationList* parent);
				void setParentPresenceVector();
				jUnsignedShortInteger getSensorID();
				int setSensorID(jUnsignedShortInteger value);
				jUnsignedShortInteger getQueryPresenceVector();
				int setQueryPresenceVector(jUnsignedShortInteger value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				QueryVisualSensorConfigurationRec &operator=(const QueryVisualSensorConfigurationRec &value);
				bool operator==(const QueryVisualSensorConfigurationRec &value) const;
				bool operator!=(const QueryVisualSensorConfigurationRec &value) const;
				QueryVisualSensorConfigurationRec();
				QueryVisualSensorConfigurationRec(const QueryVisualSensorConfigurationRec &value);
				virtual ~QueryVisualSensorConfigurationRec();
			
			protected:
				QueryVisualSensorConfigurationList* m_parent;
				jUnsignedShortInteger m_SensorID;
				jUnsignedShortInteger m_QueryPresenceVector;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			QueryVisualSensorConfigurationRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const QueryVisualSensorConfigurationRec &value);
			int addElement(const QueryVisualSensorConfigurationRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			QueryVisualSensorConfigurationList &operator=(const QueryVisualSensorConfigurationList &value);
			bool operator==(const QueryVisualSensorConfigurationList &value) const;
			bool operator!=(const QueryVisualSensorConfigurationList &value) const;
			QueryVisualSensorConfigurationList();
			QueryVisualSensorConfigurationList(const QueryVisualSensorConfigurationList &value);
			virtual ~QueryVisualSensorConfigurationList();
		
		protected:
			Body* m_parent;
			std::vector<QueryVisualSensorConfigurationRec> m_QueryVisualSensorConfigurationRec;
		};
	
		QueryVisualSensorConfigurationList* const getQueryVisualSensorConfigurationList();
		int setQueryVisualSensorConfigurationList(const QueryVisualSensorConfigurationList &value);
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
		QueryVisualSensorConfigurationList m_QueryVisualSensorConfigurationList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryVisualSensorConfiguration &operator=(const QueryVisualSensorConfiguration &value);
	bool operator==(const QueryVisualSensorConfiguration &value) const;
	bool operator!=(const QueryVisualSensorConfiguration &value) const;
	QueryVisualSensorConfiguration();
	QueryVisualSensorConfiguration(const QueryVisualSensorConfiguration &value);
	virtual ~QueryVisualSensorConfiguration();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_STILLIMAGE_1_0_QUERYVISUALSENSORCONFIGURATION_H
