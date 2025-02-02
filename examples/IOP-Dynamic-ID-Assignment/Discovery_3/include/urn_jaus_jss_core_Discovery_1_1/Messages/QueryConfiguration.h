#ifndef URN_JAUS_JSS_CORE_DISCOVERY_1_1_QUERYCONFIGURATION_H
#define URN_JAUS_JSS_CORE_DISCOVERY_1_1_QUERYCONFIGURATION_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_jss_core_Discovery_1_1
{

class DllExport QueryConfiguration: public JTS::Message
{
public:
	static const int ID = 0x2b01;
	
	class DllExport MsgHeader
	{
	public:
		class DllExport HeaderRec
		{
		public:
			void setParent(MsgHeader* parent);
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
			MsgHeader* m_parent;
			jUnsignedShortInteger m_MessageID;
		};
	
		HeaderRec* const getHeaderRec();
		int setHeaderRec(const HeaderRec &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		MsgHeader &operator=(const MsgHeader &value);
		bool operator==(const MsgHeader &value) const;
		bool operator!=(const MsgHeader &value) const;
		MsgHeader();
		MsgHeader(const MsgHeader &value);
		virtual ~MsgHeader();
	
	protected:
		HeaderRec m_HeaderRec;
	};
	class DllExport Body
	{
	public:
		class DllExport QueryConfigurationRec
		{
		public:
			void setParent(Body* parent);
			void setParentPresenceVector();
			jUnsignedByte getQueryType();
			int setQueryType(jUnsignedByte value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			QueryConfigurationRec &operator=(const QueryConfigurationRec &value);
			bool operator==(const QueryConfigurationRec &value) const;
			bool operator!=(const QueryConfigurationRec &value) const;
			QueryConfigurationRec();
			QueryConfigurationRec(const QueryConfigurationRec &value);
			virtual ~QueryConfigurationRec();
		
		protected:
			Body* m_parent;
			jUnsignedByte m_QueryType;
		};
	
		QueryConfigurationRec* const getQueryConfigurationRec();
		int setQueryConfigurationRec(const QueryConfigurationRec &value);
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
		QueryConfigurationRec m_QueryConfigurationRec;
	};

	unsigned int getID() { return ID; };
	MsgHeader* const getMsgHeader();
	int setMsgHeader(const MsgHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryConfiguration &operator=(const QueryConfiguration &value);
	bool operator==(const QueryConfiguration &value) const;
	bool operator!=(const QueryConfiguration &value) const;
	QueryConfiguration();
	QueryConfiguration(const QueryConfiguration &value);
	virtual ~QueryConfiguration();

protected:
	MsgHeader m_MsgHeader;
	Body m_Body;
};

}

#endif // URN_JAUS_JSS_CORE_DISCOVERY_1_1_QUERYCONFIGURATION_H
