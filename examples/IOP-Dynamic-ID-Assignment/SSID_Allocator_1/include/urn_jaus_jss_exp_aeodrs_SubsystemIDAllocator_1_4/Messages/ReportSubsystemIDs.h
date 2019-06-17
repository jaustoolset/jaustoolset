#ifndef URN_JAUS_JSS_EXP_AEODRS_SUBSYSTEMIDALLOCATOR_1_4_REPORTSUBSYSTEMIDS_H
#define URN_JAUS_JSS_EXP_AEODRS_SUBSYSTEMIDALLOCATOR_1_4_REPORTSUBSYSTEMIDS_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4
{

class DllExport ReportSubsystemIDs: public JTS::Message
{
public:
	static const int ID = 0xfb02;
	
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
		class DllExport ReportSubsystemIDsSeq
		{
		public:
			class DllExport SubsystemIDTypeRec
			{
			public:
				void setParent(ReportSubsystemIDsSeq* parent);
				void setParentPresenceVector();
				jByte getSubsystemIDType();
				int setSubsystemIDType(jByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				SubsystemIDTypeRec &operator=(const SubsystemIDTypeRec &value);
				bool operator==(const SubsystemIDTypeRec &value) const;
				bool operator!=(const SubsystemIDTypeRec &value) const;
				SubsystemIDTypeRec();
				SubsystemIDTypeRec(const SubsystemIDTypeRec &value);
				virtual ~SubsystemIDTypeRec();
			
			protected:
				ReportSubsystemIDsSeq* m_parent;
				jByte m_SubsystemIDType;
			};
			class DllExport SubsystemIDsList
			{
			public:
				class DllExport SubsystemIDRec
				{
				public:
					class DllExport MACaddr
					{
					public:
						void setParent(SubsystemIDRec* parent);
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
						SubsystemIDRec* m_parent;
						unsigned int m_MAC_DimensionSize;
						jUnsignedByte m_Mac[6];
					};
				
					void setParent(SubsystemIDsList* parent);
					void setParentPresenceVector();
					jUnsignedShortInteger getSubsystemID();
					int setSubsystemID(jUnsignedShortInteger value);
					jUnsignedShortInteger getSubsystemType();
					int setSubsystemType(jUnsignedShortInteger value);
					MACaddr* const getMACaddr();
					int setMACaddr(const MACaddr &value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					SubsystemIDRec &operator=(const SubsystemIDRec &value);
					bool operator==(const SubsystemIDRec &value) const;
					bool operator!=(const SubsystemIDRec &value) const;
					SubsystemIDRec();
					SubsystemIDRec(const SubsystemIDRec &value);
					virtual ~SubsystemIDRec();
				
				protected:
					SubsystemIDsList* m_parent;
					jUnsignedShortInteger m_SubsystemID;
					jUnsignedShortInteger m_SubsystemType;
					MACaddr m_MACaddr;
				};
			
				void setParent(ReportSubsystemIDsSeq* parent);
				void setParentPresenceVector();
				unsigned int getNumberOfElements() const;
				SubsystemIDRec* const getElement(unsigned int index);
				int setElement(unsigned int index, const SubsystemIDRec &value);
				int addElement(const SubsystemIDRec &value);
				int deleteElement(unsigned int index);
				int deleteLastElement();
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				SubsystemIDsList &operator=(const SubsystemIDsList &value);
				bool operator==(const SubsystemIDsList &value) const;
				bool operator!=(const SubsystemIDsList &value) const;
				SubsystemIDsList();
				SubsystemIDsList(const SubsystemIDsList &value);
				virtual ~SubsystemIDsList();
			
			protected:
				ReportSubsystemIDsSeq* m_parent;
				std::vector<SubsystemIDRec> m_SubsystemIDRec;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			SubsystemIDTypeRec* const getSubsystemIDTypeRec();
			int setSubsystemIDTypeRec(const SubsystemIDTypeRec &value);
			SubsystemIDsList* const getSubsystemIDsList();
			int setSubsystemIDsList(const SubsystemIDsList &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ReportSubsystemIDsSeq &operator=(const ReportSubsystemIDsSeq &value);
			bool operator==(const ReportSubsystemIDsSeq &value) const;
			bool operator!=(const ReportSubsystemIDsSeq &value) const;
			ReportSubsystemIDsSeq();
			ReportSubsystemIDsSeq(const ReportSubsystemIDsSeq &value);
			virtual ~ReportSubsystemIDsSeq();
		
		protected:
			Body* m_parent;
			SubsystemIDTypeRec m_SubsystemIDTypeRec;
			SubsystemIDsList m_SubsystemIDsList;
		};
	
		ReportSubsystemIDsSeq* const getReportSubsystemIDsSeq();
		int setReportSubsystemIDsSeq(const ReportSubsystemIDsSeq &value);
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
		ReportSubsystemIDsSeq m_ReportSubsystemIDsSeq;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportSubsystemIDs &operator=(const ReportSubsystemIDs &value);
	bool operator==(const ReportSubsystemIDs &value) const;
	bool operator!=(const ReportSubsystemIDs &value) const;
	ReportSubsystemIDs();
	ReportSubsystemIDs(const ReportSubsystemIDs &value);
	virtual ~ReportSubsystemIDs();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_EXP_AEODRS_SUBSYSTEMIDALLOCATOR_1_4_REPORTSUBSYSTEMIDS_H
