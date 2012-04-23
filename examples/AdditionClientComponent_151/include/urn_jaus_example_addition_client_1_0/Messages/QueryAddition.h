#ifndef URN_JAUS_EXAMPLE_ADDITION_CLIENT_1_0_QUERYADDITION_H
#define URN_JAUS_EXAMPLE_ADDITION_CLIENT_1_0_QUERYADDITION_H

#include "JausUtils.h"
#include "Messages/Message.h"
#include "JConstants.h"
namespace urn_jaus_example_addition_client_1_0
{

class DllExport QueryAddition: public JTS::Message
{
public:
	static const int ID = 0xf010;
	
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
	class DllExport AdditionInputBody
	{
	public:
		class DllExport AdditionInput
		{
		public:
			void setParent(AdditionInputBody* parent);
			void setParentPresenceVector();
			jUnsignedInteger getA1();
			int setA1(jUnsignedInteger value);
			jUnsignedInteger getA2();
			int setA2(jUnsignedInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			AdditionInput &operator=(const AdditionInput &value);
			bool operator==(const AdditionInput &value) const;
			bool operator!=(const AdditionInput &value) const;
			AdditionInput();
			AdditionInput(const AdditionInput &value);
			virtual ~AdditionInput();
		
		protected:
			AdditionInputBody* m_parent;
			jUnsignedInteger m_A1;
			jUnsignedInteger m_A2;
		};
	
		AdditionInput* const getAdditionInput();
		int setAdditionInput(const AdditionInput &value);
		void setParentPresenceVector();
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		AdditionInputBody &operator=(const AdditionInputBody &value);
		bool operator==(const AdditionInputBody &value) const;
		bool operator!=(const AdditionInputBody &value) const;
		AdditionInputBody();
		AdditionInputBody(const AdditionInputBody &value);
		virtual ~AdditionInputBody();
	
	protected:
		AdditionInput m_AdditionInput;
	};

	unsigned int getID() { return ID; };
	Header* const getHeader();
	int setHeader(const Header &value);
	AdditionInputBody* const getAdditionInputBody();
	int setAdditionInputBody(const AdditionInputBody &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryAddition &operator=(const QueryAddition &value);
	bool operator==(const QueryAddition &value) const;
	bool operator!=(const QueryAddition &value) const;
	QueryAddition();
	QueryAddition(const QueryAddition &value);
	virtual ~QueryAddition();

protected:
	Header m_Header;
	AdditionInputBody m_AdditionInputBody;
};

}

#endif // URN_JAUS_EXAMPLE_ADDITION_CLIENT_1_0_QUERYADDITION_H
