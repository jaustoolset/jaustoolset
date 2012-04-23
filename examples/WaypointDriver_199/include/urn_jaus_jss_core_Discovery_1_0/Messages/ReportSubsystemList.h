#ifndef URN_JAUS_JSS_CORE_DISCOVERY_1_0_REPORTSUBSYSTEMLIST_H
#define URN_JAUS_JSS_CORE_DISCOVERY_1_0_REPORTSUBSYSTEMLIST_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Discovery_1_0
{

class DllExport ReportSubsystemList: public JTS::Message
{
public:
	static const int ID = 0x4b02;
	
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
		class DllExport SubsystemList
		{
		public:
			class DllExport SubsystemRec
			{
			public:
				void setParent(SubsystemList* parent);
				void setParentPresenceVector();
				jUnsignedShortInteger getSubsystemID();
				int setSubsystemID(jUnsignedShortInteger value);
				jUnsignedByte getNodeID();
				int setNodeID(jUnsignedByte value);
				jUnsignedByte getComponentID();
				int setComponentID(jUnsignedByte value);
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
				SubsystemList* m_parent;
				jUnsignedShortInteger m_SubsystemID;
				jUnsignedByte m_NodeID;
				jUnsignedByte m_ComponentID;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			SubsystemRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const SubsystemRec &value);
			int addElement(const SubsystemRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			SubsystemList &operator=(const SubsystemList &value);
			bool operator==(const SubsystemList &value) const;
			bool operator!=(const SubsystemList &value) const;
			SubsystemList();
			SubsystemList(const SubsystemList &value);
			virtual ~SubsystemList();
		
		protected:
			Body* m_parent;
			std::vector<SubsystemRec> m_SubsystemRec;
		};
	
		SubsystemList* const getSubsystemList();
		int setSubsystemList(const SubsystemList &value);
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
		SubsystemList m_SubsystemList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportSubsystemList &operator=(const ReportSubsystemList &value);
	bool operator==(const ReportSubsystemList &value) const;
	bool operator!=(const ReportSubsystemList &value) const;
	ReportSubsystemList();
	ReportSubsystemList(const ReportSubsystemList &value);
	virtual ~ReportSubsystemList();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_DISCOVERY_1_0_REPORTSUBSYSTEMLIST_H
