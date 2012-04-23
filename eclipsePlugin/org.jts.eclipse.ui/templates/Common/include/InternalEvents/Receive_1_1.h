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

#ifndef JTS_RECEIVE_VERSION_1_1_H
#define JTS_RECEIVE_VERSION_1_1_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
#include "Transport/OS.h"
namespace JTS
{

class DllExport Receive_1_1: public JTS::InternalEvent
{
public:
	class DllExport Body
	{
	public:
		class DllExport ReceiveRec
		{
		public:
			class DllExport SourceID
			{
			public:
				jUnsignedInteger getComponentID();
				int setComponentID(jUnsignedInteger value);
				jUnsignedInteger getNodeID();
				int setNodeID(jUnsignedInteger value);
				jUnsignedInteger getSubsystemID();
				int setSubsystemID(jUnsignedInteger value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				SourceID &operator=(const SourceID &value);
				bool operator==(const SourceID &value) const;
				bool operator!=(const SourceID &value) const;
				SourceID();
				SourceID(const SourceID &value);
				virtual ~SourceID();
			
			protected:
				jUnsignedInteger m_SubFields;
			};
			class DllExport MessagePayload
			{
			public:
				const jUnsignedInteger getLength() const;
				const unsigned char *getData() const;
				int set(const jUnsignedInteger &length, const unsigned char *data);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				MessagePayload &operator=(const MessagePayload &value);
				bool operator==(const MessagePayload &value) const;
				bool operator!=(const MessagePayload &value) const;
				MessagePayload();
				MessagePayload(const MessagePayload &value);
				virtual ~MessagePayload();
			
			protected:
				jUnsignedInteger m_Length;
				unsigned char *m_Data;
			};
		
			SourceID* const getSourceID();
			int setSourceID(const SourceID &value);
			MessagePayload* const getMessagePayload();
			int setMessagePayload(const MessagePayload &value);
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			ReceiveRec &operator=(const ReceiveRec &value);
			bool operator==(const ReceiveRec &value) const;
			bool operator!=(const ReceiveRec &value) const;
			ReceiveRec();
			ReceiveRec(const ReceiveRec &value);
			virtual ~ReceiveRec();
		
		protected:
			SourceID m_SourceID;
			MessagePayload m_MessagePayload;
		};
	
		ReceiveRec* const getReceiveRec();
		int setReceiveRec(const ReceiveRec &value);
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
		ReceiveRec m_ReceiveRec;
	};

	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	Receive_1_1 &operator=(const Receive_1_1 &value);
	bool operator==(const Receive_1_1 &value) const;
	bool operator!=(const Receive_1_1 &value) const;
	Receive_1_1();
	Receive_1_1(const Receive_1_1 &value);
	virtual ~Receive_1_1();

protected:
	Body m_Body;
};

}

#endif
