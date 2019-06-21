#ifndef URN_JAUS_NEYA_SUBSYSTEMIDALLOCATORCLIENT_1_4_GRANTSUBSYSTEMID_H
#define URN_JAUS_NEYA_SUBSYSTEMIDALLOCATORCLIENT_1_4_GRANTSUBSYSTEMID_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_neya_SubsystemIDAllocatorClient_1_4
{

class DllExport GrantSubsystemID: public JTS::Message
{
public:
	static const int ID = 0xfb01;
	
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
		class DllExport SubsystemRec
		{
		public:
			class DllExport MACaddr
			{
			public:
				void setParent(SubsystemRec* parent);
				void setParentPresenceVector();
				const unsigned int getMAC_DimensionSize() const;
				jUnsignedByte getMac(unsigned int MAC_Dimension);
				int setMac(unsigned int MAC_Dimension, jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				MACaddr &operator=(const MACaddr &value);
				bool operator==(const MACaddr &value) const;
				bool operator!=(const MACaddr &value) const;
				MACaddr();
				MACaddr(const MACaddr &value);
				virtual ~MACaddr();
			
			protected:
				SubsystemRec* m_parent;
				unsigned int m_MAC_DimensionSize;
				jUnsignedByte m_Mac[6];
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getSubsystemID();
			int setSubsystemID(jUnsignedShortInteger value);
			jUnsignedShortInteger getType();
			int setType(jUnsignedShortInteger value);
			MACaddr* const getMACaddr();
			int setMACaddr(const MACaddr &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			SubsystemRec &operator=(const SubsystemRec &value);
			bool operator==(const SubsystemRec &value) const;
			bool operator!=(const SubsystemRec &value) const;
			SubsystemRec();
			SubsystemRec(const SubsystemRec &value);
			virtual ~SubsystemRec();
		
		protected:
			Body* m_parent;
			jUnsignedShortInteger m_SubsystemID;
			jUnsignedShortInteger m_Type;
			MACaddr m_MACaddr;
		};
	
		SubsystemRec* const getSubsystemRec();
		int setSubsystemRec(const SubsystemRec &value);
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
		SubsystemRec m_SubsystemRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	GrantSubsystemID &operator=(const GrantSubsystemID &value);
	bool operator==(const GrantSubsystemID &value) const;
	bool operator!=(const GrantSubsystemID &value) const;
	GrantSubsystemID();
	GrantSubsystemID(const GrantSubsystemID &value);
	virtual ~GrantSubsystemID();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_NEYA_SUBSYSTEMIDALLOCATORCLIENT_1_4_GRANTSUBSYSTEMID_H
