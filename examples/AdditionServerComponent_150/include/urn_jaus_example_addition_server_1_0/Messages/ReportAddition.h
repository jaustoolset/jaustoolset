#ifndef URN_JAUS_EXAMPLE_ADDITION_SERVER_1_0_REPORTADDITION_H
#define URN_JAUS_EXAMPLE_ADDITION_SERVER_1_0_REPORTADDITION_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_example_addition_server_1_0
{

class DllExport ReportAddition: public JTS::Message
{
public:
	static const int ID = 0xf011;
	
	class DllExport Header
	{
	public:
		class DllExport HeaderRecord
		{
		public:
			void setParent(Header* parent);
			void setParentPresenceVector();
			jUnsignedShortInteger getMessageIDHeader();
			int setMessageIDHeader(jUnsignedShortInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			HeaderRecord &operator=(const HeaderRecord &value);
			bool operator==(const HeaderRecord &value) const;
			bool operator!=(const HeaderRecord &value) const;
			HeaderRecord();
			HeaderRecord(const HeaderRecord &value);
			virtual ~HeaderRecord();
		
		protected:
			Header* m_parent;
			jUnsignedShortInteger m_MessageIDHeader;
		};
	
		HeaderRecord* const getHeaderRecord();
		int setHeaderRecord(const HeaderRecord &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		Header &operator=(const Header &value);
		bool operator==(const Header &value) const;
		bool operator!=(const Header &value) const;
		Header();
		Header(const Header &value);
		virtual ~Header();
	
	protected:
		HeaderRecord m_HeaderRecord;
	};
	class DllExport AdditionOutputBody
	{
	public:
		class DllExport AdditionOutput
		{
		public:
			void setParent(AdditionOutputBody* parent);
			void setParentPresenceVector();
			jUnsignedInteger getAdditionResult();
			int setAdditionResult(jUnsignedInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			AdditionOutput &operator=(const AdditionOutput &value);
			bool operator==(const AdditionOutput &value) const;
			bool operator!=(const AdditionOutput &value) const;
			AdditionOutput();
			AdditionOutput(const AdditionOutput &value);
			virtual ~AdditionOutput();
		
		protected:
			AdditionOutputBody* m_parent;
			jUnsignedInteger m_AdditionResult;
		};
	
		AdditionOutput* const getAdditionOutput();
		int setAdditionOutput(const AdditionOutput &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		AdditionOutputBody &operator=(const AdditionOutputBody &value);
		bool operator==(const AdditionOutputBody &value) const;
		bool operator!=(const AdditionOutputBody &value) const;
		AdditionOutputBody();
		AdditionOutputBody(const AdditionOutputBody &value);
		virtual ~AdditionOutputBody();
	
	protected:
		AdditionOutput m_AdditionOutput;
	};

	unsigned int getID() { return ID; };
	Header* const getHeader();
	int setHeader(const Header &value);
	AdditionOutputBody* const getAdditionOutputBody();
	int setAdditionOutputBody(const AdditionOutputBody &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportAddition &operator=(const ReportAddition &value);
	bool operator==(const ReportAddition &value) const;
	bool operator!=(const ReportAddition &value) const;
	ReportAddition();
	ReportAddition(const ReportAddition &value);
	virtual ~ReportAddition();

protected:
	Header m_Header;
	AdditionOutputBody m_AdditionOutputBody;
};

}

#endif // URN_JAUS_EXAMPLE_ADDITION_SERVER_1_0_REPORTADDITION_H
