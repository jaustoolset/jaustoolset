#ifndef URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_DELETEELEMENT_H
#define URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_DELETEELEMENT_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_mobility_ListManager_1_0
{

class DllExport DeleteElement: public JTS::Message
{
public:
	static const int ID = 0x041b;
	
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
		class DllExport DeleteElementSeq
		{
		public:
			class DllExport RequestIDRec
			{
			public:
				void setParent(DeleteElementSeq* parent);
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
				DeleteElementSeq* m_parent;
				jUnsignedByte m_RequestID;
			};
			class DllExport DeleteElementList
			{
			public:
				class DllExport DeleteElementRec
				{
				public:
					void setParent(DeleteElementList* parent);
					void setParentPresenceVector();
					jUnsignedShortInteger getElementUID();
					int setElementUID(jUnsignedShortInteger value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					DeleteElementRec &operator=(const DeleteElementRec &value);
					bool operator==(const DeleteElementRec &value) const;
					bool operator!=(const DeleteElementRec &value) const;
					DeleteElementRec();
					DeleteElementRec(const DeleteElementRec &value);
					virtual ~DeleteElementRec();
				
				protected:
					DeleteElementList* m_parent;
					jUnsignedShortInteger m_ElementUID;
				};
			
				void setParent(DeleteElementSeq* parent);
				void setParentPresenceVector();
				unsigned int getNumberOfElements() const;
				DeleteElementRec* const getElement(unsigned int index);
				int setElement(unsigned int index, const DeleteElementRec &value);
				int addElement(const DeleteElementRec &value);
				int deleteElement(unsigned int index);
				int deleteLastElement();
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				DeleteElementList &operator=(const DeleteElementList &value);
				bool operator==(const DeleteElementList &value) const;
				bool operator!=(const DeleteElementList &value) const;
				DeleteElementList();
				DeleteElementList(const DeleteElementList &value);
				virtual ~DeleteElementList();
			
			protected:
				DeleteElementSeq* m_parent;
				std::vector<DeleteElementRec> m_DeleteElementRec;
			};
		
			void setParent(Body* parent);
			void setParentPresenceVector();
			RequestIDRec* const getRequestIDRec();
			int setRequestIDRec(const RequestIDRec &value);
			DeleteElementList* const getDeleteElementList();
			int setDeleteElementList(const DeleteElementList &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			DeleteElementSeq &operator=(const DeleteElementSeq &value);
			bool operator==(const DeleteElementSeq &value) const;
			bool operator!=(const DeleteElementSeq &value) const;
			DeleteElementSeq();
			DeleteElementSeq(const DeleteElementSeq &value);
			virtual ~DeleteElementSeq();
		
		protected:
			Body* m_parent;
			RequestIDRec m_RequestIDRec;
			DeleteElementList m_DeleteElementList;
		};
	
		DeleteElementSeq* const getDeleteElementSeq();
		int setDeleteElementSeq(const DeleteElementSeq &value);
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
		DeleteElementSeq m_DeleteElementSeq;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	DeleteElement &operator=(const DeleteElement &value);
	bool operator==(const DeleteElement &value) const;
	bool operator!=(const DeleteElement &value) const;
	DeleteElement();
	DeleteElement(const DeleteElement &value);
	virtual ~DeleteElement();

protected:
	AppHeader m_AppHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_MOBILITY_LISTMANAGER_1_0_DELETEELEMENT_H
