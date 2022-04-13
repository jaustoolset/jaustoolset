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

#include "Transport/JausAddress.h"

JausAddress::JausAddress()
{
	address = malloc(sizeof(jUnsignedInteger));
	if (address != NULL)
	{
		size = sizeof(jUnsignedInteger);
		*((jUnsignedInteger*) address) = 0;
	}
}

JausAddress::JausAddress(jUnsignedShortInteger subsystemID, jUnsignedByte nodeID, jUnsignedByte componentID) : Address()
{
	address = malloc(sizeof(jUnsignedInteger));
	if (address == NULL) return;
	else size = sizeof(jUnsignedInteger);

	jUnsignedInteger tempValue;
	tempValue = (subsystemID << 16) | (nodeID << 8) | componentID; 
	*((jUnsignedInteger*)address) = tempValue;
}

JausAddress::JausAddress(jUnsignedInteger value)
{
	address = malloc(sizeof(jUnsignedInteger));
	if (address == NULL) return;
	else size = sizeof(jUnsignedInteger);

	jUnsignedInteger tempValue;
	tempValue = value;
	*((jUnsignedInteger*)address) = tempValue;
}

JausAddress::~JausAddress()
{
	if (address != NULL) free(address);
	size = 0;
}

jUnsignedInteger JausAddress::get() const
{
	return *((jUnsignedInteger*)address);
}

jUnsignedShortInteger JausAddress::getSubsystemID() const
{
	jUnsignedInteger tempValue = *((jUnsignedInteger*)address);
	return (jUnsignedShortInteger)(tempValue >> 16);
}

int JausAddress::setSubsystemID(jUnsignedShortInteger value)
{
	jUnsignedInteger tempValue = *((jUnsignedInteger*)address);
	tempValue = (tempValue | ((jUnsignedInteger)value << 16));
	*((jUnsignedInteger*)address) = tempValue;
	
	return 0;
}

jUnsignedByte JausAddress::getNodeID() const
{
	jUnsignedInteger tempValue = *((jUnsignedInteger*)address);
	
	tempValue = (tempValue & 0x0000FF00);
	tempValue = tempValue >> 8;
	
	return (jUnsignedByte)tempValue ;
}

int JausAddress::setNodeID(jUnsignedByte value)
{
	jUnsignedInteger tempValue = *((jUnsignedInteger*)address);
	tempValue = (tempValue | ((jUnsignedInteger)value << 8));
	*((jUnsignedInteger*)address) = tempValue;
	
	return 0;
}

jUnsignedByte JausAddress::getComponentID() const
{
	jUnsignedInteger tempValue = *((jUnsignedInteger*)address);
	return (jUnsignedByte)(tempValue & 0x000000FF);
}

int JausAddress::setComponentID(jUnsignedByte value)
{
	jUnsignedInteger tempValue = *((jUnsignedInteger*)address);
	tempValue = (tempValue | (jUnsignedInteger)value);
	*((jUnsignedInteger*)address) = tempValue;
	
	return 0;
}

bool JausAddress::isLocalSubsystem(jUnsignedShortInteger sID) const
{
	return (getSubsystemID() == sID);
}

bool JausAddress::isLocalSubsystem(const JausAddress& address) const
{
	return (getSubsystemID() == address.getSubsystemID());
}

bool JausAddress::isLocalNode(jUnsignedShortInteger sID, jUnsignedByte nID) const
{
	return (isLocalSubsystem(sID) && (getNodeID() == nID));
}

bool JausAddress::isLocalNode(const JausAddress& address) const
{
	return (isLocalSubsystem(address) && (getNodeID() == address.getNodeID()));
}

bool JausAddress::isLocalComponent(jUnsignedShortInteger sID, jUnsignedByte nID, jUnsignedByte cID) const
{
	return (isLocalNode(sID, nID) && (getComponentID() == cID));
}

bool JausAddress::isLocalComponent(const JausAddress& address) const
{
	return (isLocalNode(address) && (getComponentID() == address.getComponentID()));
}

bool JausAddress::operator==(const JausAddress &value) const 
{
	return (get() == value.get());
}

bool JausAddress::operator!=(const JausAddress &value) const 
{
	return (get() != value.get());
}

bool JausAddress::operator<(const JausAddress& rhs) const
{
	if (getSubsystemID() < rhs.getSubsystemID())
	{
		return true;
	} else if (getSubsystemID() > rhs.getSubsystemID())
	{
		return false;
	} else if (getNodeID() < rhs.getNodeID())
	{
		return true;
	} else if (getNodeID() > rhs.getNodeID())
	{
		return false;
	} else
	{
		return (getComponentID() < rhs.getComponentID());
	}
}

