#ifndef URN_JAUS_JSS_CORE_DISCOVERY_1_0_REGISTERSERVICES_H
#define URN_JAUS_JSS_CORE_DISCOVERY_1_0_REGISTERSERVICES_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Discovery_1_0
{

class DllExport RegisterServices: public JTS::Message
{
public:
	static const int ID = 0x0b00;
	
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
	class DllExport RegisterServicesBody
	{
	public:
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
		
			void setParent(RegisterServicesBody* parent);
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
			RegisterServicesBody* m_parent;
			std::vector<ServiceRec> m_ServiceRec;
		};
	
		ServiceList* const getServiceList();
		int setServiceList(const ServiceList &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		RegisterServicesBody &operator=(const RegisterServicesBody &value);
		bool operator==(const RegisterServicesBody &value) const;
		bool operator!=(const RegisterServicesBody &value) const;
		RegisterServicesBody();
		RegisterServicesBody(const RegisterServicesBody &value);
		virtual ~RegisterServicesBody();
	
	protected:
		ServiceList m_ServiceList;
	};

	unsigned int getID() { return ID; };
	AppHeader* const getAppHeader();
	int setAppHeader(const AppHeader &value);
	RegisterServicesBody* const getRegisterServicesBody();
	int setRegisterServicesBody(const RegisterServicesBody &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	RegisterServices &operator=(const RegisterServices &value);
	bool operator==(const RegisterServices &value) const;
	bool operator!=(const RegisterServices &value) const;
	RegisterServices();
	RegisterServices(const RegisterServices &value);
	virtual ~RegisterServices();

protected:
	AppHeader m_AppHeader;
	RegisterServicesBody m_RegisterServicesBody;
};

}

#endif // URN_JAUS_JSS_CORE_DISCOVERY_1_0_REGISTERSERVICES_H
