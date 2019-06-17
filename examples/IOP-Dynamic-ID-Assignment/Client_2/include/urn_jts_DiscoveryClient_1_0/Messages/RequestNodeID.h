#ifndef URN_JTS_DISCOVERYCLIENT_1_0_REQUESTNODEID_H
#define URN_JTS_DISCOVERYCLIENT_1_0_REQUESTNODEID_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jts_DiscoveryClient_1_0
{

class DllExport RequestNodeID: public JTS::Message
{
public:
	static const int ID = 0xdb03;
	
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
		class DllExport ReviewNodeIDRec
		{
		public:
			class DllExport RequesterID
			{
			public:
				void setParent(ReviewNodeIDRec* parent);
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
				ReviewNodeIDRec* m_parent;
				unsigned int m_OneDimensionArraySize;
				jUnsignedByte m_RequesterIDArrayField[7];
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			RequesterID* const getRequesterID();
			int setRequesterID(const RequesterID &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ReviewNodeIDRec &operator=(const ReviewNodeIDRec &value);
			bool operator==(const ReviewNodeIDRec &value) const;
			bool operator!=(const ReviewNodeIDRec &value) const;
			ReviewNodeIDRec();
			ReviewNodeIDRec(const ReviewNodeIDRec &value);
			virtual ~ReviewNodeIDRec();
		
		protected:
			Body* m_parent;
			RequesterID m_RequesterID;
		};
	
		ReviewNodeIDRec* const getReviewNodeIDRec();
		int setReviewNodeIDRec(const ReviewNodeIDRec &value);
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
		ReviewNodeIDRec m_ReviewNodeIDRec;
	};

	unsigned int getID() { return ID; };
	JAUSApplicationLayerHeader* const getJAUSApplicationLayerHeader();
	int setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	RequestNodeID &operator=(const RequestNodeID &value);
	bool operator==(const RequestNodeID &value) const;
	bool operator!=(const RequestNodeID &value) const;
	RequestNodeID();
	RequestNodeID(const RequestNodeID &value);
	virtual ~RequestNodeID();

protected:
	JAUSApplicationLayerHeader m_JAUSApplicationLayerHeader;
	Body m_Body;
};

}

#endif // URN_JTS_DISCOVERYCLIENT_1_0_REQUESTNODEID_H
