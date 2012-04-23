#ifndef URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_REPORTELEMENT_H
#define URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_REPORTELEMENT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_ListManager_1_0
{

class DllExport ReportElement: public JTS::Message
{
public:
	static const int ID = 0x441a;
	
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
		class DllExport ElementRec
		{
		public:
			class DllExport ElementData
			{
			public:
				void setParent(ElementRec* parent);
				void setParentPresenceVector();
				const jUnsignedByte getFormat() const;
				const jUnsignedShortInteger getLength() const;
				const unsigned char *getData() const;
				int set(jUnsignedByte format, jUnsignedShortInteger length, unsigned char *data);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				ElementData &operator=(const ElementData &value);
				bool operator==(const ElementData &value) const;
				bool operator!=(const ElementData &value) const;
				ElementData();
				ElementData(const ElementData &value);
				virtual ~ElementData();
			
			protected:
				ElementRec* m_parent;
				jUnsignedByte m_Format;
				jUnsignedShortInteger m_Length;
				unsigned char *m_Data;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getElementUID();
			int setElementUID(jUnsignedShortInteger value);
			jUnsignedShortInteger getPreviousUID();
			int setPreviousUID(jUnsignedShortInteger value);
			jUnsignedShortInteger getNextUID();
			int setNextUID(jUnsignedShortInteger value);
			ElementData* const getElementData();
			int setElementData(const ElementData &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ElementRec &operator=(const ElementRec &value);
			bool operator==(const ElementRec &value) const;
			bool operator!=(const ElementRec &value) const;
			ElementRec();
			ElementRec(const ElementRec &value);
			virtual ~ElementRec();
		
		protected:
			Body* m_parent;
			jUnsignedShortInteger m_ElementUID;
			jUnsignedShortInteger m_PreviousUID;
			jUnsignedShortInteger m_NextUID;
			ElementData m_ElementData;
		};
	
		ElementRec* const getElementRec();
		int setElementRec(const ElementRec &value);
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
		ElementRec m_ElementRec;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportElement &operator=(const ReportElement &value);
	bool operator==(const ReportElement &value) const;
	bool operator!=(const ReportElement &value) const;
	ReportElement();
	ReportElement(const ReportElement &value);
	virtual ~ReportElement();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_REPORTELEMENT_H
