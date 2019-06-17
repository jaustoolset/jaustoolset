#ifndef URN_JAUS_JSS_EXP_AEODRS_SUBSYSTEMIDALLOCATOR_1_4_QUERYSUBSYSTEMIDS_H
#define URN_JAUS_JSS_EXP_AEODRS_SUBSYSTEMIDALLOCATOR_1_4_QUERYSUBSYSTEMIDS_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4
{

class DllExport QuerySubsystemIDs: public JTS::Message
{
public:
	static const int ID = 0xeb02;
	
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
		class DllExport QuerySubsystemIDsRec
		{
		public:
			class DllExport MACaddr
			{
			public:
				void setParent(QuerySubsystemIDsRec* parent);
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
				QuerySubsystemIDsRec* m_parent;
				unsigned int m_MAC_DimensionSize;
				jUnsignedByte m_Mac[6];
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			jByte getSubsystemIDType();
			int setSubsystemIDType(jByte value);
			MACaddr* const getMACaddr();
			int setMACaddr(const MACaddr &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			QuerySubsystemIDsRec &operator=(const QuerySubsystemIDsRec &value);
			bool operator==(const QuerySubsystemIDsRec &value) const;
			bool operator!=(const QuerySubsystemIDsRec &value) const;
			QuerySubsystemIDsRec();
			QuerySubsystemIDsRec(const QuerySubsystemIDsRec &value);
			virtual ~QuerySubsystemIDsRec();
		
		protected:
			Body* m_parent;
			jByte m_SubsystemIDType;
			MACaddr m_MACaddr;
		};
	
		QuerySubsystemIDsRec* const getQuerySubsystemIDsRec();
		int setQuerySubsystemIDsRec(const QuerySubsystemIDsRec &value);
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
		QuerySubsystemIDsRec m_QuerySubsystemIDsRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QuerySubsystemIDs &operator=(const QuerySubsystemIDs &value);
	bool operator==(const QuerySubsystemIDs &value) const;
	bool operator!=(const QuerySubsystemIDs &value) const;
	QuerySubsystemIDs();
	QuerySubsystemIDs(const QuerySubsystemIDs &value);
	virtual ~QuerySubsystemIDs();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_EXP_AEODRS_SUBSYSTEMIDALLOCATOR_1_4_QUERYSUBSYSTEMIDS_H
