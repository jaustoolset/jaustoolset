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

#include "Transport/Address.h"


Address::Address()
{
	address = NULL; 
	size = 0;
}

Address::~Address()
{
}

Address::Address(Address const& from)
{
	address = malloc(from.size);
	if (address != NULL)
	{
		memcpy(address, from.address, from.size);
		size = from.size;
	}
}

const Address& Address::operator=(const Address& from)
{
	if (this != &from) 
	{
		if ((address != NULL) && (size == from.size))
			memcpy(address, from.address, from.size);
		else
		{
			if (address != NULL) free(address);
			address = malloc(from.size);
			memcpy(address, from.address, from.size);
			size = from.size;
		}
	}
	return *this;
}

bool Address::operator==(const Address &value) const
{
	if (size != value.size) return false;
	return (memcmp(address, value.address, size) == 0);
}

bool Address::operator!=(const Address &value) const
{
	if (size != value.size) return true;
	return (memcmp(address, value.address, size) != 0);
}


