#ifndef URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_SETELEMENT_H
#define URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_SETELEMENT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_ListManager_1_0
{

class DllExport SetElement: public JTS::Message
{
public:
	static const int ID = 0x041a;
	
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
		class DllExport SetElementSeq
		{
		public:
			class DllExport RequestIDRec
			{
			public:
				void setParent(SetElementSeq* parent);
				void setParentPresenceVector();
				jUnsignedByte getRequestID();
				int setRequestID(jUnsignedByte value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				RequestIDRec &operator=(const RequestIDRec &value);
				bool operator==(const RequestIDRec &value) const;
				bool operator!=(const RequestIDRec &value) const;
				RequestIDRec();
				RequestIDRec(const RequestIDRec &value);
				virtual ~RequestIDRec();
			
			protected:
				SetElementSeq* m_parent;
				jUnsignedByte m_RequestID;
			};
			class DllExport ElementList
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
				
					void setParent(ElementList* parent);
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
					ElementList* m_parent;
					jUnsignedShortInteger m_ElementUID;
					jUnsignedShortInteger m_PreviousUID;
					jUnsignedShortInteger m_NextUID;
					ElementData m_ElementData;
				};
			
				void setParent(SetElementSeq* parent);
				void setParentPresenceVector();
				unsigned int getNumberOfElements() const;
				ElementRec* const getElement(unsigned int index);
				int setElement(unsigned int index, const ElementRec &value);
				int addElement(const ElementRec &value);
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
				SetElementSeq* m_parent;
				std::vector<ElementRec> m_ElementRec;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			RequestIDRec* const getRequestIDRec();
			int setRequestIDRec(const RequestIDRec &value);
			ElementList* const getElementList();
			int setElementList(const ElementList &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			SetElementSeq &operator=(const SetElementSeq &value);
			bool operator==(const SetElementSeq &value) const;
			bool operator!=(const SetElementSeq &value) const;
			SetElementSeq();
			SetElementSeq(const SetElementSeq &value);
			virtual ~SetElementSeq();
		
		protected:
			Body* m_parent;
			RequestIDRec m_RequestIDRec;
			ElementList m_ElementList;
		};
	
		SetElementSeq* const getSetElementSeq();
		int setSetElementSeq(const SetElementSeq &value);
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
		SetElementSeq m_SetElementSeq;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	SetElement &operator=(const SetElement &value);
	bool operator==(const SetElement &value) const;
	bool operator!=(const SetElement &value) const;
	SetElement();
	SetElement(const SetElement &value);
	virtual ~SetElement();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_SETELEMENT_H
