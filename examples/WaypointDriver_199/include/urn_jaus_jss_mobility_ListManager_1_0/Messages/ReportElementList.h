#ifndef URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_REPORTELEMENTLIST_H
#define URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_REPORTELEMENTLIST_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_ListManager_1_0
{

class DllExport ReportElementList: public JTS::Message
{
public:
	static const int ID = 0x441b;
	
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
		class DllExport ElementList
		{
		public:
			class DllExport ElementListRec
			{
			public:
				void setParent(ElementList* parent);
				void setParentPresenceVector();
				jUnsignedShortInteger getElementUID();
				int setElementUID(jUnsignedShortInteger value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				ElementListRec &operator=(const ElementListRec &value);
				bool operator==(const ElementListRec &value) const;
				bool operator!=(const ElementListRec &value) const;
				ElementListRec();
				ElementListRec(const ElementListRec &value);
				virtual ~ElementListRec();
			
			protected:
				ElementList* m_parent;
				jUnsignedShortInteger m_ElementUID;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			unsigned int getNumberOfElements() const;
			ElementListRec* const getElement(unsigned int index);
			int setElement(unsigned int index, const ElementListRec &value);
			int addElement(const ElementListRec &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ElementList &operator=(const ElementList &value);
			bool operator==(const ElementList &value) const;
			bool operator!=(const ElementList &value) const;
			ElementList();
			ElementList(const ElementList &value);
			virtual ~ElementList();
		
		protected:
			Body* m_parent;
			std::vector<ElementListRec> m_ElementListRec;
		};
	
		ElementList* const getElementList();
		int setElementList(const ElementList &value);
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
		ElementList m_ElementList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportElementList &operator=(const ReportElementList &value);
	bool operator==(const ReportElementList &value) const;
	bool operator!=(const ReportElementList &value) const;
	ReportElementList();
	ReportElementList(const ReportElementList &value);
	virtual ~ReportElementList();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_REPORTELEMENTLIST_H
