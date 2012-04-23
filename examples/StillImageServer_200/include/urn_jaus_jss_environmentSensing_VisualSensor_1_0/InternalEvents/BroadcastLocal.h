#ifndef URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_BROADCASTLOCAL_H
#define URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_BROADCASTLOCAL_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0
{

class DllExport BroadcastLocal: public JTS::InternalEvent
{
public:
	class DllExport Body
	{
	public:
		class DllExport SendRec
		{
		public:
			class DllExport MessagePayload
			{
			public:
				void setParent(SendRec* parent);
				void setParentPresenceVector();
				const jUnsignedInteger getLength() const;
				const unsigned char *getData() const;
				int set(const jUnsignedInteger &length, const unsigned char *data);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				MessagePayload &operator=(const MessagePayload &value);
				bool operator==(const MessagePayload &value) const;
				bool operator!=(const MessagePayload &value) const;
				MessagePayload();
				MessagePayload(const MessagePayload &value);
				virtual ~MessagePayload();
			
			protected:
				SendRec* m_parent;
				jUnsignedInteger m_Length;
				unsigned char *m_Data;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getPresenceVector();
			bool checkPresenceVector(unsigned int index) const;
			jUnsignedShortInteger getDestSubsystemID();
			int setDestSubsystemID(jUnsignedShortInteger value);
			jUnsignedByte getDestNodeID();
			int setDestNodeID(jUnsignedByte value);
			jUnsignedByte getDestComponentID();
			int setDestComponentID(jUnsignedByte value);
			bool isSrcSubsystemIDValid() const;
			jUnsignedShortInteger getSrcSubsystemID();
			int setSrcSubsystemID(jUnsignedShortInteger value);
			bool isSrcNodeIDValid() const;
			jUnsignedByte getSrcNodeID();
			int setSrcNodeID(jUnsignedByte value);
			jUnsignedByte getSrcComponentID();
			int setSrcComponentID(jUnsignedByte value);
			bool isPriorityValid() const;
			jUnsignedByte getPriority();
			int setPriority(jUnsignedByte value);
			MessagePayload* const getMessagePayload();
			int setMessagePayload(const MessagePayload &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			SendRec &operator=(const SendRec &value);
			bool operator==(const SendRec &value) const;
			bool operator!=(const SendRec &value) const;
			SendRec();
			SendRec(const SendRec &value);
			virtual ~SendRec();
		
		protected:
			int setPresenceVector(unsigned int index);
		
			Body* m_parent;
			jUnsignedByte m_PresenceVector;
			jUnsignedShortInteger m_DestSubsystemID;
			jUnsignedByte m_DestNodeID;
			jUnsignedByte m_DestComponentID;
			jUnsignedShortInteger m_SrcSubsystemID;
			jUnsignedByte m_SrcNodeID;
			jUnsignedByte m_SrcComponentID;
			jUnsignedByte m_Priority;
			MessagePayload m_MessagePayload;
		};
	
		SendRec* const getSendRec();
		int setSendRec(const SendRec &value);
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
		SendRec m_SendRec;
	};

	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	BroadcastLocal &operator=(const BroadcastLocal &value);
	bool operator==(const BroadcastLocal &value) const;
	bool operator!=(const BroadcastLocal &value) const;
	BroadcastLocal();
	BroadcastLocal(const BroadcastLocal &value);
	virtual ~BroadcastLocal();

protected:
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_ENVIRONMENTSENSING_VISUALSENSOR_1_0_BROADCASTLOCAL_H
