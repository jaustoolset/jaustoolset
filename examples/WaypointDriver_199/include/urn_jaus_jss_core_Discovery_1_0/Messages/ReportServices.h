#ifndef URN_JAUS_JSS_CORE_DISCOVERY_1_0_REPORTSERVICES_H
#define URN_JAUS_JSS_CORE_DISCOVERY_1_0_REPORTSERVICES_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Discovery_1_0
{

class DllExport ReportServices: public JTS::Message
{
public:
	static const int ID = 0x4b03;
	
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
		class DllExport NodeList
		{
		public:
			class DllExport NodeSeq
			{
			public:
				class DllExport NodeRec
				{
				public:
					void setParent(NodeSeq* parent);
					void setParentPresenceVector();
					jUnsignedByte getNodeID();
					int setNodeID(jUnsignedByte value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					NodeRec &operator=(const NodeRec &value);
					bool operator==(const NodeRec &value) const;
					bool operator!=(const NodeRec &value) const;
					NodeRec();
					NodeRec(const NodeRec &value);
					virtual ~NodeRec();
				
				protected:
					NodeSeq* m_parent;
					jUnsignedByte m_NodeID;
				};
				class DllExport ComponentList
				{
				public:
					class DllExport ComponentSeq
					{
					public:
						class DllExport ComponentRec
						{
						public:
							void setParent(ComponentSeq* parent);
							void setParentPresenceVector();
							jUnsignedByte getComponentID();
							int setComponentID(jUnsignedByte value);
							jUnsignedByte getInstanceID();
							int setInstanceID(jUnsignedByte value);
							const unsigned int getSize();
							virtual void encode(unsigned char *bytes);
							virtual void decode(const unsigned char *bytes);
							ComponentRec &operator=(const ComponentRec &value);
							bool operator==(const ComponentRec &value) const;
							bool operator!=(const ComponentRec &value) const;
							ComponentRec();
							ComponentRec(const ComponentRec &value);
							virtual ~ComponentRec();
						
						protected:
							ComponentSeq* m_parent;
							jUnsignedByte m_ComponentID;
							jUnsignedByte m_InstanceID;
						};
						class DllExport ServiceList
						{
						public:
							class DllExport ServiceRec
							{
							public:
								void setParent(ServiceList* parent);
								void setParentPresenceVector();
								jVariableLengthString getURI();
								int setURI(jVariableLengthString value);
								jUnsignedByte getMajorVersionNumber();
								int setMajorVersionNumber(jUnsignedByte value);
								jUnsignedByte getMinorVersionNumber();
								int setMinorVersionNumber(jUnsignedByte value);
								const unsigned int getSize();
								virtual void encode(unsigned char *bytes);
								virtual void decode(const unsigned char *bytes);
								ServiceRec &operator=(const ServiceRec &value);
								bool operator==(const ServiceRec &value) const;
								bool operator!=(const ServiceRec &value) const;
								ServiceRec();
								ServiceRec(const ServiceRec &value);
								virtual ~ServiceRec();
							
							protected:
								ServiceList* m_parent;
								jVariableLengthString m_URI;
								jUnsignedByte m_MajorVersionNumber;
								jUnsignedByte m_MinorVersionNumber;
							};
						
							void setParent(ComponentSeq* parent);
							void setParentPresenceVector();
							unsigned int getNumberOfElements() const;
							ServiceRec* const getElement(unsigned int index);
							int setElement(unsigned int index, const ServiceRec &value);
							int addElement(const ServiceRec &value);
							int deleteElement(unsigned int index);
							int deleteLastElement();
							const unsigned int getSize();
							virtual void encode(unsigned char *bytes);
							virtual void decode(const unsigned char *bytes);
							ServiceList &operator=(const ServiceList &value);
							bool operator==(const ServiceList &value) const;
							bool operator!=(const ServiceList &value) const;
							ServiceList();
							ServiceList(const ServiceList &value);
							virtual ~ServiceList();
						
						protected:
							ComponentSeq* m_parent;
							std::vector<ServiceRec> m_ServiceRec;
						};
					
						void setParent(ComponentList* parent);
						void setParentPresenceVector();
						ComponentRec* const getComponentRec();
						int setComponentRec(const ComponentRec &value);
						ServiceList* const getServiceList();
						int setServiceList(const ServiceList &value);
						const unsigned int getSize();
						virtual void encode(unsigned char *bytes);
						virtual void decode(const unsigned char *bytes);
						ComponentSeq &operator=(const ComponentSeq &value);
						bool operator==(const ComponentSeq &value) const;
						bool operator!=(const ComponentSeq &value) const;
						ComponentSeq();
						ComponentSeq(const ComponentSeq &value);
						virtual ~ComponentSeq();
					
					protected:
						ComponentList* m_parent;
						ComponentRec m_ComponentRec;
						ServiceList m_ServiceList;
					};
				
					void setParent(NodeSeq* parent);
					void setParentPresenceVector();
					unsigned int getNumberOfElements() const;
					ComponentSeq* const getElement(unsigned int index);
					int setElement(unsigned int index, const ComponentSeq &value);
					int addElement(const ComponentSeq &value);
					int deleteElement(unsigned int index);
					int deleteLastElement();
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					ComponentList &operator=(const ComponentList &value);
					bool operator==(const ComponentList &value) const;
					bool operator!=(const ComponentList &value) const;
					ComponentList();
					ComponentList(const ComponentList &value);
					virtual ~ComponentList();
				
				protected:
					NodeSeq* m_parent;
					std::vector<ComponentSeq> m_ComponentSeq;
				};
			
				void setParent(NodeList* parent);
				void setParentPresenceVector();
				NodeRec* const getNodeRec();
				int setNodeRec(const NodeRec &value);
				ComponentList* const getComponentList();
				int setComponentList(const ComponentList &value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				NodeSeq &operator=(const NodeSeq &value);
				bool operator==(const NodeSeq &value) const;
				bool operator!=(const NodeSeq &value) const;
				NodeSeq();
				NodeSeq(const NodeSeq &value);
				virtual ~NodeSeq();
			
			protected:
				NodeList* m_parent;
				NodeRec m_NodeRec;
				ComponentList m_ComponentList;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			NodeSeq* const getElement(unsigned int index);
			int setElement(unsigned int index, const NodeSeq &value);
			int addElement(const NodeSeq &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			NodeList &operator=(const NodeList &value);
			bool operator==(const NodeList &value) const;
			bool operator!=(const NodeList &value) const;
			NodeList();
			NodeList(const NodeList &value);
			virtual ~NodeList();
		
		protected:
			Body* m_parent;
			std::vector<NodeSeq> m_NodeSeq;
		};
	
		NodeList* const getNodeList();
		int setNodeList(const NodeList &value);
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
		NodeList m_NodeList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportServices &operator=(const ReportServices &value);
	bool operator==(const ReportServices &value) const;
	bool operator!=(const ReportServices &value) const;
	ReportServices();
	ReportServices(const ReportServices &value);
	virtual ~ReportServices();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_DISCOVERY_1_0_REPORTSERVICES_H
