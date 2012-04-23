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

#ifndef QUERYSERVICES_H
#define QUERYSERVICES_H

#include "JausUtils.h"
#include "Message.h"

using namespace JTS;

namespace urn_jts_comms_test_server_1_0
{

class QueryServices: public Message
{
public:
	static const int ID = 0x2b03;
	
	class JAUSApplicationLayerHeader
	{
	public:
		class HeaderRec
		{
		public:
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
			jUnsignedShortInteger m_MessageID;
		};
	
		HeaderRec* const getHeaderRec();
		int setHeaderRec(const HeaderRec &value);
		const unsigned int getSize();
		virtual void encode(unsigned char *bytes);
		virtual void decode(const unsigned char *bytes);
		JAUSApplicationLayerHeader &operator=(const JAUSApplicationLayerHeader &value);
		bool operator==(const JAUSApplicationLayerHeader &value) const;
		bool operator!=(const JAUSApplicationLayerHeader &value) const;
		JAUSApplicationLayerHeader();
		JAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value);
		virtual ~JAUSApplicationLayerHeader();
	
	protected:
		HeaderRec m_HeaderRec;
	};
	class Body
	{
	public:
		class NodeList
		{
		public:
			class NodeSeq
			{
			public:
				class NodeRec
				{
				public:
					jUnsignedByte getNodeID();
					int setNodeID(jUnsignedByte value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					NodeRec &operator=(const NodeRec &value);
					bool operator==(const NodeRec &value) const;
					bool operator!=(const NodeRec &value) const;
					NodeRec();
					NodeRec(const NodeRec &value);
					virtual ~NodeRec();
				
				protected:
					jUnsignedByte m_NodeID;
				};
				class ComponentList
				{
				public:
					class ComponentRec
					{
					public:
						jUnsignedByte getComponentID();
						int setComponentID(jUnsignedByte value);
						const unsigned int getSize();
						virtual void encode(unsigned char *bytes);
						virtual void decode(const unsigned char *bytes);
						ComponentRec &operator=(const ComponentRec &value);
						bool operator==(const ComponentRec &value) const;
						bool operator!=(const ComponentRec &value) const;
						ComponentRec();
						ComponentRec(const ComponentRec &value);
						virtual ~ComponentRec();
					
					protected:
						jUnsignedByte m_ComponentID;
					};
				
					unsigned int getNumberOfElements() const;
					ComponentRec* const getElement(unsigned int index);
					int setElement(unsigned int index, const ComponentRec &value);
					int addElement(const ComponentRec &value);
					int deleteElement(unsigned int index);
					int deleteLastElement();
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					ComponentList &operator=(const ComponentList &value);
					bool operator==(const ComponentList &value) const;
					bool operator!=(const ComponentList &value) const;
					ComponentList();
					ComponentList(const ComponentList &value);
					virtual ~ComponentList();
				
				protected:
					std::vector<ComponentRec> m_ComponentRec;
				};
			
				NodeRec* const getNodeRec();
				int setNodeRec(const NodeRec &value);
				ComponentList* const getComponentList();
				int setComponentList(const ComponentList &value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				NodeSeq &operator=(const NodeSeq &value);
				bool operator==(const NodeSeq &value) const;
				bool operator!=(const NodeSeq &value) const;
				NodeSeq();
				NodeSeq(const NodeSeq &value);
				virtual ~NodeSeq();
			
			protected:
				NodeRec m_NodeRec;
				ComponentList m_ComponentList;
			};
		
			unsigned int getNumberOfElements() const;
			NodeSeq* const getElement(unsigned int index);
			int setElement(unsigned int index, const NodeSeq &value);
			int addElement(const NodeSeq &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			NodeList &operator=(const NodeList &value);
			bool operator==(const NodeList &value) const;
			bool operator!=(const NodeList &value) const;
			NodeList();
			NodeList(const NodeList &value);
			virtual ~NodeList();
		
		protected:
			std::vector<NodeSeq> m_NodeSeq;
		};
	
		NodeList* const getNodeList();
		int setNodeList(const NodeList &value);
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
		NodeList m_NodeList;
	};

	unsigned int getID() { return ID; };
	JAUSApplicationLayerHeader* const getJAUSApplicationLayerHeader();
	int setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	QueryServices &operator=(const QueryServices &value);
	bool operator==(const QueryServices &value) const;
	bool operator!=(const QueryServices &value) const;
	QueryServices();
	QueryServices(const QueryServices &value);
	virtual ~QueryServices();

protected:
	JAUSApplicationLayerHeader m_JAUSApplicationLayerHeader;
	Body m_Body;
};

}

#endif // QUERYSERVICES_H
