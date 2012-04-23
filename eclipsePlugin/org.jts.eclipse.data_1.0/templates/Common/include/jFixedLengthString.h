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

#ifndef JFIXEDLENGTHSTRING_H
#define JFIXEDLENGTHSTRING_H

#include <string>
#include "Transport/OS.h"

class DllExport jFixedLengthString
{
public:
	jFixedLengthString();
	jFixedLengthString(unsigned int size);
	jFixedLengthString(unsigned int size, const char* str);
	jFixedLengthString(unsigned int size, const std::string &str);
	jFixedLengthString(unsigned int size, const jFixedLengthString &str);
	jFixedLengthString(const jFixedLengthString &str);
	virtual ~jFixedLengthString();
	void setSize(unsigned int size);
	unsigned int length();
	const char* c_str() const;
	jFixedLengthString &operator=(const char* value);
	jFixedLengthString &operator=(const std::string &value);
	jFixedLengthString &operator=(const jFixedLengthString &value);
	bool operator==(const char* value) const;
	bool operator==(const std::string &value) const;
	bool operator==(const jFixedLengthString &value) const;
	bool operator!=(const char* value) const;
	bool operator!=(const std::string &value) const;
	bool operator!=(const jFixedLengthString &value) const;

protected:
	unsigned int size;
	std::string data;
};

#endif // JFIXEDLENGTHSTRING_H
