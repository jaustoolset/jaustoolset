/*! 
 ***********************************************************************
 * @file      ConfigData.h
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
#ifndef  __CONFIG_DATA_H
#define  __CONFIG_DATA_H

#include "Types.h"

namespace DeVivo {
namespace Junior {

//
// This is a default implementation that does nothing.  It is 
// not abstract, however, since we can use the default implementation
// to implicitly use pre-compile defaults.
//
class ConfigData
{
public:
	ConfigData(){};
    ~ConfigData(){};

    //
    // Define an error enum
    //
    enum ConfigError {Ok, FileNotFound, InvalidFile, ValueNotFound};

    //
    // Functions to parse a config file
    //
	virtual ConfigError parseFile( std::string filename ){return Ok;};

	//
	// Access an atribute of an element.  An optional
	// index can be supplied to manage duplicate elements.
	//
	virtual ConfigError getValue(std::string& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0){return ValueNotFound;};
	virtual ConfigError getValue(int& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0){return ValueNotFound;};
	virtual ConfigError getValue(unsigned int& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0){return ValueNotFound;};
	virtual ConfigError getValue(short& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0){return ValueNotFound;};
	virtual ConfigError getValue(unsigned short& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0){return ValueNotFound;};
	virtual ConfigError getValue(char& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0){return ValueNotFound;};
	virtual ConfigError getValue(unsigned char& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0){return ValueNotFound;};
	virtual ConfigError getValue(double& value,
			  					 const std::string& attribute,
								 const std::string& element,
								 int index = 0){return ValueNotFound;};

	// Get a list of attributes for a given element
	virtual StringList getAttributes(std::string element){return StringList();};

protected:

};

}} // namespace DeVivo::Junior
#endif


