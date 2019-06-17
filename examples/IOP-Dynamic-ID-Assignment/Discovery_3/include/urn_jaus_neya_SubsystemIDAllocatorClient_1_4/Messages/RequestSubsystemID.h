#ifndef URN_JAUS_NEYA_SUBSYSTEMIDALLOCATORCLIENT_1_4_REQUESTSUBSYSTEMID_H
#define URN_JAUS_NEYA_SUBSYSTEMIDALLOCATORCLIENT_1_4_REQUESTSUBSYSTEMID_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_neya_SubsystemIDAllocatorClient_1_4
{

class DllExport RequestSubsystemID: public JTS::Message
{
public:
	static const int ID = 0xdb01;
	
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
		class DllExport RequestSubsystemIDRec
		{
		public:
			class DllExport MACaddr
			{
			public:
				void setParent(RequestSubsystemIDRec* parent);
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
				RequestSubsystemIDRec* m_parent;
				unsigned int m_MAC_DimensionSize;
				jUnsignedByte m_Mac[6];
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			MACaddr* const getMACaddr();
			int setMACaddr(const MACaddr &value);
			jUnsignedShortInteger getType();
			int setType(jUnsignedShortInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			RequestSubsystemIDRec &operator=(const RequestSubsystemIDRec &value);
			bool operator==(const RequestSubsystemIDRec &value) const;
			bool operator!=(const RequestSubsystemIDRec &value) const;
			RequestSubsystemIDRec();
			RequestSubsystemIDRec(const RequestSubsystemIDRec &value);
			virtual ~RequestSubsystemIDRec();
		
		protected:
			Body* m_parent;
			MACaddr m_MACaddr;
			jUnsignedShortInteger m_Type;
		};
	
		RequestSubsystemIDRec* const getRequestSubsystemIDRec();
		int setRequestSubsystemIDRec(const RequestSubsystemIDRec &value);
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
		RequestSubsystemIDRec m_RequestSubsystemIDRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	RequestSubsystemID &operator=(const RequestSubsystemID &value);
	bool operator==(const RequestSubsystemID &value) const;
	bool operator!=(const RequestSubsystemID &value) const;
	RequestSubsystemID();
	RequestSubsystemID(const RequestSubsystemID &value);
	virtual ~RequestSubsystemID();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_NEYA_SUBSYSTEMIDALLOCATORCLIENT_1_4_REQUESTSUBSYSTEMID_H
