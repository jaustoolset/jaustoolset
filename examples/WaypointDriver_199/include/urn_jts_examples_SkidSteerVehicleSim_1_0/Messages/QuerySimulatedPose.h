#ifndef URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_QUERYSIMULATEDPOSE_H
#define URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_QUERYSIMULATEDPOSE_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

class DllExport QuerySimulatedPose: public JTS::Message
{
public:
	static const int ID = 0x2d03;
	
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
		class DllExport QuerySimulatedPoseRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getPresenceVector();
			int setPresenceVector(jUnsignedShortInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			QuerySimulatedPoseRec &operator=(const QuerySimulatedPoseRec &value);
			bool operator==(const QuerySimulatedPoseRec &value) const;
			bool operator!=(const QuerySimulatedPoseRec &value) const;
			QuerySimulatedPoseRec();
			QuerySimulatedPoseRec(const QuerySimulatedPoseRec &value);
			virtual ~QuerySimulatedPoseRec();
		
		protected:
			Body* m_parent;
			jUnsignedShortInteger m_PresenceVector;
		};
	
		QuerySimulatedPoseRec* const getQuerySimulatedPoseRec();
		int setQuerySimulatedPoseRec(const QuerySimulatedPoseRec &value);
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
		QuerySimulatedPoseRec m_QuerySimulatedPoseRec;
	};

	unsigned int getID() { return ID; };
	JAUSApplicationLayerHeader* const getJAUSApplicationLayerHeader();
	int setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QuerySimulatedPose &operator=(const QuerySimulatedPose &value);
	bool operator==(const QuerySimulatedPose &value) const;
	bool operator!=(const QuerySimulatedPose &value) const;
	QuerySimulatedPose();
	QuerySimulatedPose(const QuerySimulatedPose &value);
	virtual ~QuerySimulatedPose();

protected:
	JAUSApplicationLayerHeader m_JAUSApplicationLayerHeader;
	Body m_Body;
};

}

#endif // URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_QUERYSIMULATEDPOSE_H
