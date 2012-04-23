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

#ifndef REPORTSERVICES_H
#define REPORTSERVICES_H

#include "JausUtils.h"
#include "Message.h"

using namespace JTS;

namespace urn_jts_comms_test_client_1_0
{

class ReportServices: public Message
{
public:
	static const int ID = 0x4b03;
	
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
		class NodeListResponse
		{
		public:
			class NodeSeqResponse
			{
			public:
				class NodeRecResponse
				{
				public:
					jUnsignedByte getNodeID();
					int setNodeID(jUnsignedByte value);
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					NodeRecResponse &operator=(const NodeRecResponse &value);
					bool operator==(const NodeRecResponse &value) const;
					bool operator!=(const NodeRecResponse &value) const;
					NodeRecResponse();
					NodeRecResponse(const NodeRecResponse &value);
					virtual ~NodeRecResponse();
				
				protected:
					jUnsignedByte m_NodeID;
				};
				class ComponentListResponse
				{
				public:
					class ComponentSeqResponse
					{
					public:
						class ComponentRecResponse
						{
						public:
							jUnsignedByte getComponentID();
							int setComponentID(jUnsignedByte value);
							jUnsignedByte getInstanceID();
							int setInstanceID(jUnsignedByte value);
							const unsigned int getSize();
							virtual void encode(unsigned char *bytes);
							virtual void decode(const unsigned char *bytes);
							ComponentRecResponse &operator=(const ComponentRecResponse &value);
							bool operator==(const ComponentRecResponse &value) const;
							bool operator!=(const ComponentRecResponse &value) const;
							ComponentRecResponse();
							ComponentRecResponse(const ComponentRecResponse &value);
							virtual ~ComponentRecResponse();
						
						protected:
							jUnsignedByte m_ComponentID;
							jUnsignedByte m_InstanceID;
						};
						class ServiceList
						{
						public:
							class ServiceRec
							{
							public:
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
								jVariableLengthString m_URI;
								jUnsignedByte m_MajorVersionNumber;
								jUnsignedByte m_MinorVersionNumber;
							};
						
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
							std::vector<ServiceRec> m_ServiceRec;
						};
					
						ComponentRecResponse* const getComponentRecResponse();
						int setComponentRecResponse(const ComponentRecResponse &value);
						ServiceList* const getServiceList();
						int setServiceList(const ServiceList &value);
						const unsigned int getSize();
						virtual void encode(unsigned char *bytes);
						virtual void decode(const unsigned char *bytes);
						ComponentSeqResponse &operator=(const ComponentSeqResponse &value);
						bool operator==(const ComponentSeqResponse &value) const;
						bool operator!=(const ComponentSeqResponse &value) const;
						ComponentSeqResponse();
						ComponentSeqResponse(const ComponentSeqResponse &value);
						virtual ~ComponentSeqResponse();
					
					protected:
						ComponentRecResponse m_ComponentRecResponse;
						ServiceList m_ServiceList;
					};
				
					unsigned int getNumberOfElements() const;
					ComponentSeqResponse* const getElement(unsigned int index);
					int setElement(unsigned int index, const ComponentSeqResponse &value);
					int addElement(const ComponentSeqResponse &value);
					int deleteElement(unsigned int index);
					int deleteLastElement();
					const unsigned int getSize();
					virtual void encode(unsigned char *bytes);
					virtual void decode(const unsigned char *bytes);
					ComponentListResponse &operator=(const ComponentListResponse &value);
					bool operator==(const ComponentListResponse &value) const;
					bool operator!=(const ComponentListResponse &value) const;
					ComponentListResponse();
					ComponentListResponse(const ComponentListResponse &value);
					virtual ~ComponentListResponse();
				
				protected:
					std::vector<ComponentSeqResponse> m_ComponentSeqResponse;
				};
			
				NodeRecResponse* const getNodeRecResponse();
				int setNodeRecResponse(const NodeRecResponse &value);
				ComponentListResponse* const getComponentListResponse();
				int setComponentListResponse(const ComponentListResponse &value);
				const unsigned int getSize();
				virtual void encode(unsigned char *bytes);
				virtual void decode(const unsigned char *bytes);
				NodeSeqResponse &operator=(const NodeSeqResponse &value);
				bool operator==(const NodeSeqResponse &value) const;
				bool operator!=(const NodeSeqResponse &value) const;
				NodeSeqResponse();
				NodeSeqResponse(const NodeSeqResponse &value);
				virtual ~NodeSeqResponse();
			
			protected:
				NodeRecResponse m_NodeRecResponse;
				ComponentListResponse m_ComponentListResponse;
			};
		
			unsigned int getNumberOfElements() const;
			NodeSeqResponse* const getElement(unsigned int index);
			int setElement(unsigned int index, const NodeSeqResponse &value);
			int addElement(const NodeSeqResponse &value);
			int deleteElement(unsigned int index);
			int deleteLastElement();
			const unsigned int getSize();
			virtual void encode(unsigned char *bytes);
			virtual void decode(const unsigned char *bytes);
			NodeListResponse &operator=(const NodeListResponse &value);
			bool operator==(const NodeListResponse &value) const;
			bool operator!=(const NodeListResponse &value) const;
			NodeListResponse();
			NodeListResponse(const NodeListResponse &value);
			virtual ~NodeListResponse();
		
		protected:
			std::vector<NodeSeqResponse> m_NodeSeqResponse;
		};
	
		NodeListResponse* const getNodeListResponse();
		int setNodeListResponse(const NodeListResponse &value);
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
		NodeListResponse m_NodeListResponse;
	};

	unsigned int getID() { return ID; };
	JAUSApplicationLayerHeader* const getJAUSApplicationLayerHeader();
	int setJAUSApplicationLayerHeader(const JAUSApplicationLayerHeader &value);
	Body* const getBody();
	int setBody(const Body &value);
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	ReportServices &operator=(const ReportServices &value);
	bool operator==(const ReportServices &value) const;
	bool operator!=(const ReportServices &value) const;
	ReportServices();
	ReportServices(const ReportServices &value);
	virtual ~ReportServices();

protected:
	JAUSApplicationLayerHeader m_JAUSApplicationLayerHeader;
	Body m_Body;
};

}

#endif // REPORTSERVICES_H
