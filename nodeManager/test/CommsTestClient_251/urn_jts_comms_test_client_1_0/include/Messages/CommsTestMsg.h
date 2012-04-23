/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of 
its contributors may be used to endorse or promote products derived from 
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/

#ifndef COMMSTESTMSG_H
#define COMMSTESTMSG_H

#include "JausUtils.h"
#include "Message.h"

using namespace JTS;

namespace urn_jts_comms_test_client_1_0
{

class CommsTestMsg: public Message
{
public:
	static const int ID = 0xeeee;
	
	class JTS_DefaultHeader
	{
	public:
		class DefaultHeaderRec
		{
		public:
			jUnsignedShortInteger getMessageID();
			int setMessageID(jUnsignedShortInteger value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			DefaultHeaderRec &operator=(const DefaultHeaderRec &value);
			bool operator==(const DefaultHeaderRec &value) const;
			bool operator!=(const DefaultHeaderRec &value) const;
			DefaultHeaderRec();
			DefaultHeaderRec(const DefaultHeaderRec &value);
			virtual ~DefaultHeaderRec();
		
		protected:
			jUnsignedShortInteger m_MessageID;
		};
	
		DefaultHeaderRec* const getDefaultHeaderRec();
		int setDefaultHeaderRec(const DefaultHeaderRec &value);
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		JTS_DefaultHeader &operator=(const JTS_DefaultHeader &value);
		bool operator==(const JTS_DefaultHeader &value) const;
		bool operator!=(const JTS_DefaultHeader &value) const;
		JTS_DefaultHeader();
		JTS_DefaultHeader(const JTS_DefaultHeader &value);
		virtual ~JTS_DefaultHeader();
	
	protected:
		DefaultHeaderRec m_DefaultHeaderRec;
	};
	class CommsTestBody
	{
	public:
		class CommsTestRec
		{
		public:
			class Payload
			{
			public:
				const jUnsignedInteger getLength() const;
				const unsigned char *getData() const;
				int set(const jUnsignedInteger &length, const unsigned char *data);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				Payload &operator=(const Payload &value);
				bool operator==(const Payload &value) const;
				bool operator!=(const Payload &value) const;
				Payload();
				Payload(const Payload &value);
				virtual ~Payload();
			
			protected:
				jUnsignedInteger m_Length;
				unsigned char *m_Data;
			};
		
			jUnsignedInteger getTimeStamp();
			int setTimeStamp(jUnsignedInteger value);
			jUnsignedInteger getSequenceNumber();
			int setSequenceNumber(jUnsignedInteger value);
			Payload* const getPayload();
			int setPayload(const Payload &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			CommsTestRec &operator=(const CommsTestRec &value);
			bool operator==(const CommsTestRec &value) const;
			bool operator!=(const CommsTestRec &value) const;
			CommsTestRec();
			CommsTestRec(const CommsTestRec &value);
			virtual ~CommsTestRec();
		
		protected:
			jUnsignedInteger m_TimeStamp;
			jUnsignedInteger m_SequenceNumber;
			Payload m_Payload;
		};
	
		CommsTestRec* const getCommsTestRec();
		int setCommsTestRec(const CommsTestRec &value);
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		CommsTestBody &operator=(const CommsTestBody &value);
		bool operator==(const CommsTestBody &value) const;
		bool operator!=(const CommsTestBody &value) const;
		CommsTestBody();
		CommsTestBody(const CommsTestBody &value);
		virtual ~CommsTestBody();
	
	protected:
		CommsTestRec m_CommsTestRec;
	};

	unsigned int getID() { return ID; };
	JTS_DefaultHeader* const getJTS_DefaultHeader();
	int setJTS_DefaultHeader(const JTS_DefaultHeader &value);
	CommsTestBody* const getCommsTestBody();
	int setCommsTestBody(const CommsTestBody &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	CommsTestMsg &operator=(const CommsTestMsg &value);
	bool operator==(const CommsTestMsg &value) const;
	bool operator!=(const CommsTestMsg &value) const;
	CommsTestMsg();
	CommsTestMsg(const CommsTestMsg &value);
	virtual ~CommsTestMsg();

protected:
	JTS_DefaultHeader m_JTS_DefaultHeader;
	CommsTestBody m_CommsTestBody;
};

}

#endif // COMMSTESTMSG_H
