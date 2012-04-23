#ifndef URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_BROADCASTGLOBAL_H
#define URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_BROADCASTGLOBAL_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jaus_jss_core_AccessControl_1_1
{

class DllExport BroadcastGlobal: public JTS::InternalEvent
{
public:
	class DllExport Body
	{
	public:
		class DllExport BroadcastRec
		{
		public:
			class DllExport DestinationID
			{
			public:
				void setParent(BroadcastRec* parent);
				void setParentPresenceVector();
				jUnsignedInteger getComponentID();
				int setComponentID(jUnsignedInteger value);
				jUnsignedInteger getNodeID();
				int setNodeID(jUnsignedInteger value);
				jUnsignedInteger getSubsystemID();
				int setSubsystemID(jUnsignedInteger value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				DestinationID &operator=(const DestinationID &value);
				bool operator==(const DestinationID &value) const;
				bool operator!=(const DestinationID &value) const;
				DestinationID();
				DestinationID(const DestinationID &value);
				virtual ~DestinationID();
			
			protected:
				BroadcastRec* m_parent;
				jUnsignedInteger m_SubFields;
			};
			class DllExport SourceID
			{
			public:
				void setParent(BroadcastRec* parent);
				void setParentPresenceVector();
				jUnsignedInteger getComponentID();
				int setComponentID(jUnsignedInteger value);
				jUnsignedInteger getNodeID();
				int setNodeID(jUnsignedInteger value);
				jUnsignedInteger getSubsystemID();
				int setSubsystemID(jUnsignedInteger value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				SourceID &operator=(const SourceID &value);
				bool operator==(const SourceID &value) const;
				bool operator!=(const SourceID &value) const;
				SourceID();
				SourceID(const SourceID &value);
				virtual ~SourceID();
			
			protected:
				BroadcastRec* m_parent;
				jUnsignedInteger m_SubFields;
			};
			class DllExport MessagePayload
			{
			public:
				void setParent(BroadcastRec* parent);
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
				BroadcastRec* m_parent;
				jUnsignedInteger m_Length;
				unsigned char *m_Data;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getPresenceVector();
			bool checkPresenceVector(unsigned int index) const;
			DestinationID* const getDestinationID();
			int setDestinationID(const DestinationID &value);
			bool isSourceIDValid() const;
			SourceID* const getSourceID();
			int setSourceID(const SourceID &value);
			bool isPriorityValid() const;
			jUnsignedByte getPriority();
			int setPriority(jUnsignedByte value);
			MessagePayload* const getMessagePayload();
			int setMessagePayload(const MessagePayload &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			BroadcastRec &operator=(const BroadcastRec &value);
			bool operator==(const BroadcastRec &value) const;
			bool operator!=(const BroadcastRec &value) const;
			BroadcastRec();
			BroadcastRec(const BroadcastRec &value);
			virtual ~BroadcastRec();
		
		protected:
			int setPresenceVector(unsigned int index);
		
			Body* m_parent;
			jUnsignedByte m_PresenceVector;
			DestinationID m_DestinationID;
			SourceID m_SourceID;
			jUnsignedByte m_Priority;
			MessagePayload m_MessagePayload;
		};
	
		BroadcastRec* const getBroadcastRec();
		int setBroadcastRec(const BroadcastRec &value);
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
		BroadcastRec m_BroadcastRec;
	};

	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	BroadcastGlobal &operator=(const BroadcastGlobal &value);
	bool operator==(const BroadcastGlobal &value) const;
	bool operator!=(const BroadcastGlobal &value) const;
	BroadcastGlobal();
	BroadcastGlobal(const BroadcastGlobal &value);
	virtual ~BroadcastGlobal();

protected:
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_ACCESSCONTROL_1_1_BROADCASTGLOBAL_H
