#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_QUERYSENSORGEOMETRICPROPERTIES_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_QUERYSENSORGEOMETRICPROPERTIES_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0
{

class DllExport QuerySensorGeometricProperties: public JTS::Message
{
public:
	static const int ID = 0x2805;
	
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
		class DllExport SensorIdList
		{
		public:
			class DllExport SensorIdRec
			{
			public:
				void setParent(SensorIdList* parent);
				void setParentPresenceVector();
				jUnsignedShortInteger getSensorID();
				int setSensorID(jUnsignedShortInteger value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				SensorIdRec &operator=(const SensorIdRec &value);
				bool operator==(const SensorIdRec &value) const;
				bool operator!=(const SensorIdRec &value) const;
				SensorIdRec();
				SensorIdRec(const SensorIdRec &value);
				virtual ~SensorIdRec();
			
			protected:
				SensorIdList* m_parent;
				jUnsignedShortInteger m_SensorID;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			SensorIdRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const SensorIdRec &value);
			int addElement(const SensorIdRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			SensorIdList &operator=(const SensorIdList &value);
			bool operator==(const SensorIdList &value) const;
			bool operator!=(const SensorIdList &value) const;
			SensorIdList();
			SensorIdList(const SensorIdList &value);
			virtual ~SensorIdList();
		
		protected:
			Body* m_parent;
			std::vector<SensorIdRec> m_SensorIdRec;
		};
	
		SensorIdList* const getSensorIdList();
		int setSensorIdList(const SensorIdList &value);
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
		SensorIdList m_SensorIdList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QuerySensorGeometricProperties &operator=(const QuerySensorGeometricProperties &value);
	bool operator==(const QuerySensorGeometricProperties &value) const;
	bool operator!=(const QuerySensorGeometricProperties &value) const;
	QuerySensorGeometricProperties();
	QuerySensorGeometricProperties(const QuerySensorGeometricProperties &value);
	virtual ~QuerySensorGeometricProperties();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_QUERYSENSORGEOMETRICPROPERTIES_H
