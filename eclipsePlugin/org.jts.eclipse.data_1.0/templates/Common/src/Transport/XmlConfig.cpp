/***********           LICENSE HEADER   *******************************
JR Middleware
Copyright (c)  2008-2019, DeVivo AST, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the copyright holder nor the names of 
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
#include "Transport/XmlConfig.h"
using namespace DeVivo::Junior;


ConfigData::ConfigError XmlConfig::parseFile(const  std::string& filename)
{
	doc.LoadFile(filename.c_str());
	if (doc.Error())
	{
		JrError << "Failed to parse config file: " << filename << std::endl;

		// Cast the TinyXML errors to our enum
		if (doc.ErrorId() == TiXmlBase::TIXML_ERROR_OPENING_FILE) 
			return FileNotFound;
		return InvalidFile;
	}
	return Ok;
}

// getValue implementations make use of templated accessor
ConfigData::ConfigError XmlConfig::getValue(std::string& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index)
{
	return lookupValue(value, attribute, element, index);
}

ConfigData::ConfigError XmlConfig::getValue(int& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index)
{
	return lookupValue(value, attribute, element, index);
}

ConfigData::ConfigError XmlConfig::getValue(unsigned int& value,
  					 const std::string& attribute,
					 const std::string& element,
					 int index)
{
	return lookupValue(value, attribute, element, index);
}

ConfigData::ConfigError XmlConfig::getValue(short& value,
  					 const std::string& attribute,
					 const std::string& element,
					 int index)
{
	return lookupValue(value, attribute, element, index);
}

ConfigData::ConfigError XmlConfig::getValue(unsigned short& value,
  					 const std::string& attribute,
					 const std::string& element,
					 int index)
{
	return lookupValue(value, attribute, element, index);
}

ConfigData::ConfigError XmlConfig::getValue(char& value,
		  					 const std::string& attribute,
							 const std::string& element,
							 int index)
{
	return lookupValue(value, attribute, element, index);
}

ConfigData::ConfigError XmlConfig::getValue(unsigned char& value,
		  					 const std::string& attribute,
							 const std::string& element,
							 int index)
{
	return lookupValue(value, attribute, element, index);
}

ConfigData::ConfigError XmlConfig::getValue(double& value,
		  					 const std::string& attribute,
							 const std::string& element,
							 int index)
{
	return lookupValue(value, attribute, element, index);
}

template <typename T> 
ConfigData::ConfigError XmlConfig::lookupValue(T& value,
											   const std::string& attribute,
											   const std::string& element,
											   int index)

{
	// Get the first occurrence of the requested element
	TiXmlHandle docHandle( &doc );
	TiXmlElement* ele = docHandle.FirstChild("JrXmlConfig").FirstChild(element.c_str()).ToElement();
	if (ele == NULL) 
	{
		JrWarn << "Failed to find configuration element: " << element << "\n";
		return ValueNotFound;
	}

	// Loop through the index values to find the requested element number
	while ( index > 0 )
	{
		ele = ele->NextSiblingElement(element.c_str());
		if (ele == NULL)
		{
			JrWarn<<"Failed to find configuration element: "<<element <<"\n";
			return ValueNotFound;
		}
		index--;
	}

	// Now that we have the right element, pull the requested attribute.
	if (ele->QueryValueAttribute(attribute.c_str(), &value) != TIXML_SUCCESS)
	{
		JrWarn << "Failed to find configuration attribute: " << attribute<<"\n";
		return ValueNotFound;
	}

	// Success!
	JrDebug << "Found config value: " << attribute << " = " << value << std::endl;
	return Ok;
}


StringList XmlConfig::getAttributes(std::string element)
{
	StringList ret;

	// Get the first occurrence of the requested element
	TiXmlHandle docHandle( &doc );
	TiXmlElement* ele = docHandle.FirstChild("JrXmlConfig").FirstChild(element.c_str()).ToElement();
	if (ele == NULL) return ret; 

	// Walk through the attributes, returning a string for each
	for (TiXmlAttribute* att = ele->FirstAttribute(); att != NULL; att = att->Next())
	{
		JrDebug << "Found attribute: " << att->Name() << "\n";
		ret.push_back(att->Name());
	}
	return ret;
}
