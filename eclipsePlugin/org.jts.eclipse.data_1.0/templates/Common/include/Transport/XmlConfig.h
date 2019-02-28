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
#ifndef  __XML_CONFIG_DATA_H
#define  __XML_CONFIG_DATA_H

#ifndef TIXML_USE_STL
#define TIXML_USE_STL
#endif

#include "ConfigData.h"
#include "tinyxml.h"
#include "JrLogger.h"
#include "Types.h"

namespace DeVivo {
namespace Junior {


// We use TinyXML for parsing the configure file into a DOM.
// The XmlConfig class provides abstraction from the DOM
// and XML, in case we switch to a different solution later.
class XmlConfig : public ConfigData
{
public:
	XmlConfig(){};
    ~XmlConfig(){};

    //
    // Functions to parse a config file
    //
	virtual ConfigError parseFile(const std::string& filename );

	//
	// Access an atribute of an element.  An optional
	// index can be supplied to manage duplicate elements.
	//
	virtual ConfigError getValue(std::string& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0);
	virtual ConfigError getValue(int& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0);
	virtual ConfigError getValue(unsigned int& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0);
	virtual ConfigError getValue(short& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0);
	virtual ConfigError getValue(unsigned short& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0);
	virtual ConfigError getValue(char& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0);
	virtual ConfigError getValue(unsigned char& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0);
    virtual ConfigError getValue(double& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0);

	// Get a list of attributed for a given element
	virtual StringList getAttributes(std::string element);

protected:

    TiXmlDocument doc;

	// Templated function to access DOM
	template <typename T> ConfigData::ConfigError lookupValue(T& value,
											   const std::string& attribute,
											   const std::string& element,
											   int index = 0);

};

}} // namespace DeVivo::Junior
#endif


