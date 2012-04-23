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


#include "jFixedLengthString.h"


jFixedLengthString::jFixedLengthString()
{
	this->setSize(0);
}

jFixedLengthString::jFixedLengthString(unsigned int size)
{
	this->setSize(size);
}

jFixedLengthString::jFixedLengthString(unsigned int size, const std::string& str)
{
	this->size = size;
	this->data = str;
	this->data.resize(this->size);
}

jFixedLengthString::jFixedLengthString(unsigned int size, const char* str)
{
	this->size = size;
	this->data = str;
	this->data.resize(this->size);
}

jFixedLengthString::jFixedLengthString(unsigned int size, const jFixedLengthString &str)
{
	this->size = size;
	this->data = str.data;
	this->data.resize(this->size);
}

jFixedLengthString::jFixedLengthString(const jFixedLengthString &str)
{
	this->size = str.size;
	this->data = str.data;
	this->data.resize(this->size);	
}

jFixedLengthString::~jFixedLengthString()
{
}

void jFixedLengthString::setSize(unsigned int size)
{
	this->size = size;
	this->data.resize(this->size);
}

unsigned int jFixedLengthString::length()
{
	return this->size;
}

const char* jFixedLengthString::c_str() const
{
	return this->data.c_str();
}

jFixedLengthString &jFixedLengthString::operator=(const char* value)
{
	this->data = value;
	this->data.resize(this->size);
	return *this;
}

jFixedLengthString &jFixedLengthString::operator=(const std::string &value)
{
	this->data = value;
	this->data.resize(this->size);
	return *this;
}

jFixedLengthString &jFixedLengthString::operator=(const jFixedLengthString &value)
{
	this->data = value.data;
	this->data.resize(this->size);
	return *this;
}

// If the provided const char* is longer than the jFixedLengthString
// they are NOT equal
bool jFixedLengthString::operator==(const char* value) const
{
	std::string temp = value;

	if (temp.size() < this->size)
	{
		temp.resize(this->size);
	}
	else if (temp.size() > this->size)
	{
		return false;
	}
	
	return (this->data == temp);
}

// If the provided string is longer than the jFixedLengthString
// they are NOT equal
bool jFixedLengthString::operator==(const std::string &value) const
{
	std::string temp = value;
	
	if (temp.size() < this->size)
	{
		temp.resize(this->size);
	}
	else if (temp.size() > this->size)
	{
		return false;
	}
	
	return (this->data == temp);
}

// 2 jFixedLengthStrings need to be identical (same size and contents) for them
// to be equal.
bool jFixedLengthString::operator==(const jFixedLengthString &value) const
{
	return (this->size == value.size) && (this->data == value.data);
}


bool jFixedLengthString::operator!=(const char* value) const
{
	std::string temp = value;

	if (temp.size() < this->size)
	{
		temp.resize(this->size);
	}
	else if (temp.size() > this->size)
	{
		return true;
	}

	return (this->data != temp);
}


// If the provided string is longer than then FixedLengthString this function
// will return true.
bool jFixedLengthString::operator!=(const std::string &value) const
{
	std::string temp = value;
	
	if (temp.size() < this->size)
	{
		temp.resize(this->size);
	}
	else if (temp.size() > this->size)
	{
		return true;
	}

	return (this->data != temp);
}

// If the sizes of the 2 jFixedLengthStrings are not the same then they are not equal.
bool jFixedLengthString::operator!=(const jFixedLengthString &value) const
{
	return ((this->size != value.size) || (this->data != value.data));
}
