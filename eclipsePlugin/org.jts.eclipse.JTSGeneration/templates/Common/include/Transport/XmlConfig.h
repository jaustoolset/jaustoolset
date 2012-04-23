/*! 
 ***********************************************************************
 * @file      XmlConfig.h
 * @author    Dave Martin, DeVivo AST, Inc.  
 * @date      2008/03/03
 *
 *  Copyright (C) 2008. DeVivo AST, Inc
 *
 *  This file is part of Jr Middleware.
 *
 *  Jr Middleware is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Jr Middleware is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Jr Middleware.  If not, see <http://www.gnu.org/licenses/>.
 *
 ************************************************************************
 */
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


