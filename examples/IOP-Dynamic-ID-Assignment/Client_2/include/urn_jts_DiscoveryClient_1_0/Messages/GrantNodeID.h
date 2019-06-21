#ifndef URN_JTS_DISCOVERYCLIENT_1_0_GRANTNODEID_H
#define URN_JTS_DISCOVERYCLIENT_1_0_GRANTNODEID_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jts_DiscoveryClient_1_0
{

class DllExport GrantNodeID: public JTS::Message
{
public:
	static const int ID = 0xfb03;
	
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
		class DllExport GrantNodeIDRec
		{
		public:
			class DllExport RequesterID
			{
			public:
				void setParent(GrantNodeIDRec* parent);
				void setParentPresenceVector();
				const unsigned int getOneDimensionArraySize() const;
				jUnsignedByte getRequesterIDArrayField(unsigned int OneDimensionArray);
				int setRequesterIDArrayField(unsigned int OneDimensionArray, jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				RequesterID &operator=(const RequesterID &value);
				bool operator==(const RequesterID &value) const;
				bool operator!=(const RequesterID &value) const;
				RequesterID();
				RequesterID(const RequesterID &value);
				virtual ~RequesterID();
			
			protected:
				GrantNodeIDRec* m_parent;
				unsigned int m_OneDimensionArraySize;
				jUnsignedByte m_RequesterIDArrayField[7];
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getNodeID();
			int setNodeID(jUnsignedByte value);
			RequesterID* const getRequesterID();
			int setRequesterID(const RequesterID &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			GrantNodeIDRec &operator=(const GrantNodeIDRec &value);
			bool operator==(const GrantNodeIDRec &value) const;
			bool operator!=(const GrantNodeIDRec &value) const;
			GrantNodeIDRec();
			GrantNodeIDRec(const GrantNodeIDRec &value);
			virtual ~GrantNodeIDRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_NodeID;
			RequesterID m_RequesterID;
		};
	
		GrantNodeIDRec* const getGrantNodeIDRec();
		int setGrantNodeIDRec(const GrantNodeIDRec &value);
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
		GrantNodeIDRec m_GrantNodeIDRec;
	};

	unsigned int getID() { return ID; };
	JAUSApplicationLayerHeader* const getJAUSApplicationLayerHeader();
	int setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	GrantNodeID &operator=(const GrantNodeID &value);
	bool operator==(const GrantNodeID &value) const;
	bool operator!=(const GrantNodeID &value) const;
	GrantNodeID();
	GrantNodeID(const GrantNodeID &value);
	virtual ~GrantNodeID();

protected:
	JAUSApplicationLayerHeader m_JAUSApplicationLayerHeader;
	Body m_Body;
};

}

#endif // URN_JTS_DISCOVERYCLIENT_1_0_GRANTNODEID_H
