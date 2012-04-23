#ifndef URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_REPORTWRENCHEFFORT_H
#define URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_REPORTWRENCHEFFORT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

class DllExport ReportWrenchEffort: public JTS::Message
{
public:
	static const int ID = 0x4405;
	
	class DllExport JAUSApplicationLayerHeader
	{
	public:
		class DllExport HeaderRec
		{
		public:
			void setParent(JAUSApplicationLayerHeader* parent);
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
			JAUSApplicationLayerHeader* m_parent;
			jUnsignedShortInteger m_MessageID;
		};
	
		HeaderRec* const getHeaderRec();
		int setHeaderRec(const HeaderRec &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		JAUSApplicationLayerHeader &operator=(const JAUSApplicationLayerHeader &value);
		bool operator==(const JAUSApplicationLayerHeader &value) const;
		bool operator!=(const JAUSApplicationLayerHeader &value) const;
		JAUSApplicationLayerHeader();
		JAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value);
		virtual ~JAUSApplicationLayerHeader();
	
	protected:
		HeaderRec m_HeaderRec;
	};
	class DllExport Body
	{
	public:
		class DllExport WrenchEffortRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getPresenceVector();
			bool checkPresenceVector(unsigned int index) const;
			bool isPropulsiveLinearEffortXValid() const;
			double getPropulsiveLinearEffortX();
			int setPropulsiveLinearEffortX(double value);
			bool isPropulsiveLinearEffortYValid() const;
			double getPropulsiveLinearEffortY();
			int setPropulsiveLinearEffortY(double value);
			bool isPropulsiveLinearEffortZValid() const;
			double getPropulsiveLinearEffortZ();
			int setPropulsiveLinearEffortZ(double value);
			bool isPropulsiveRotationalEffortXValid() const;
			double getPropulsiveRotationalEffortX();
			int setPropulsiveRotationalEffortX(double value);
			bool isPropulsiveRotationalEffortYValid() const;
			double getPropulsiveRotationalEffortY();
			int setPropulsiveRotationalEffortY(double value);
			bool isPropulsiveRotationalEffortZValid() const;
			double getPropulsiveRotationalEffortZ();
			int setPropulsiveRotationalEffortZ(double value);
			bool isResistiveLinearEffortXValid() const;
			double getResistiveLinearEffortX();
			int setResistiveLinearEffortX(double value);
			bool isResistiveLinearEffortYValid() const;
			double getResistiveLinearEffortY();
			int setResistiveLinearEffortY(double value);
			bool isResistiveLinearEffortZValid() const;
			double getResistiveLinearEffortZ();
			int setResistiveLinearEffortZ(double value);
			bool isResistiveRotationalEffortXValid() const;
			double getResistiveRotationalEffortX();
			int setResistiveRotationalEffortX(double value);
			bool isResistiveRotationalEffortYValid() const;
			double getResistiveRotationalEffortY();
			int setResistiveRotationalEffortY(double value);
			bool isResistiveRotationalEffortZValid() const;
			double getResistiveRotationalEffortZ();
			int setResistiveRotationalEffortZ(double value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			WrenchEffortRec &operator=(const WrenchEffortRec &value);
			bool operator==(const WrenchEffortRec &value) const;
			bool operator!=(const WrenchEffortRec &value) const;
			WrenchEffortRec();
			WrenchEffortRec(const WrenchEffortRec &value);
			virtual ~WrenchEffortRec();
		
		protected:
			int setPresenceVector(unsigned int index);
		
			Body* m_parent;
			jUnsignedShortInteger m_PresenceVector;
			jUnsignedShortInteger m_PropulsiveLinearEffortX;
			jUnsignedShortInteger m_PropulsiveLinearEffortY;
			jUnsignedShortInteger m_PropulsiveLinearEffortZ;
			jUnsignedShortInteger m_PropulsiveRotationalEffortX;
			jUnsignedShortInteger m_PropulsiveRotationalEffortY;
			jUnsignedShortInteger m_PropulsiveRotationalEffortZ;
			jUnsignedByte m_ResistiveLinearEffortX;
			jUnsignedByte m_ResistiveLinearEffortY;
			jUnsignedByte m_ResistiveLinearEffortZ;
			jUnsignedByte m_ResistiveRotationalEffortX;
			jUnsignedByte m_ResistiveRotationalEffortY;
			jUnsignedByte m_ResistiveRotationalEffortZ;
		};
	
		WrenchEffortRec* const getWrenchEffortRec();
		int setWrenchEffortRec(const WrenchEffortRec &value);
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
		WrenchEffortRec m_WrenchEffortRec;
	};

	unsigned int getID() { return ID; };
	JAUSApplicationLayerHeader* const getJAUSApplicationLayerHeader();
	int setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportWrenchEffort &operator=(const ReportWrenchEffort &value);
	bool operator==(const ReportWrenchEffort &value) const;
	bool operator!=(const ReportWrenchEffort &value) const;
	ReportWrenchEffort();
	ReportWrenchEffort(const ReportWrenchEffort &value);
	virtual ~ReportWrenchEffort();

protected:
	JAUSApplicationLayerHeader m_JAUSApplicationLayerHeader;
	Body m_Body;
};

}

#endif // URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_REPORTWRENCHEFFORT_H
