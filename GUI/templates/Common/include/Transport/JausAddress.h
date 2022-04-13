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

#ifndef JAUSADDRESS_H
#define JAUSADDRESS_H

#include "Address.h"
#include "Transport/OS.h"
#include <ostream>

class DllExport JausAddress : public Address
{
public:
	JausAddress();
	JausAddress(jUnsignedShortInteger subsystemID, jUnsignedByte nodeID, jUnsignedByte componentID);
	JausAddress(jUnsignedInteger value);
	virtual ~JausAddress();
	
	virtual jUnsignedShortInteger getSubsystemID() const;
	virtual int setSubsystemID(jUnsignedShortInteger value);
	virtual jUnsignedByte getNodeID() const;
	virtual int setNodeID(jUnsignedByte value);
	virtual jUnsignedByte getComponentID() const;
	virtual int setComponentID(jUnsignedByte value);
	virtual jUnsignedInteger get() const;

	virtual bool isLocalSubsystem(jUnsignedShortInteger sID) const;
	virtual bool isLocalSubsystem(const JausAddress& address) const;
	virtual bool isLocalNode(jUnsignedShortInteger sID, jUnsignedByte nID) const;
	virtual bool isLocalNode(const JausAddress& address) const; 
	virtual bool isLocalComponent(jUnsignedShortInteger sID, jUnsignedByte nID, jUnsignedByte cID) const;
	virtual bool isLocalComponent(const JausAddress& address) const;

	bool operator==(const JausAddress &value) const;
	bool operator!=(const JausAddress &value) const;
  
	/**
	 * Less than comparison.
	 * @param rhs
	 * @return Compare subsystems first, then nodes, then components
	 */
  	bool operator<(const JausAddress& rhs) const;
  
	/**
	 * Greater than comparison.
	 * @param rhs
	 * @return Compare subsystems first, then nodes, then components
	 */
	inline bool operator>(const JausAddress& rhs) const
	{
		return rhs < *this;
	}
  
	friend std::ostream& operator<<(std::ostream& os, const JausAddress& obj)
	{
		os << obj.getSubsystemID() << "."
		   << (uint16_t) obj.getNodeID() << "."
		   << (uint16_t) obj.getComponentID();
		return os;
	}
};


#endif // JAUSADDRESS_H

