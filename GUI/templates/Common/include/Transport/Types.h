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
#ifndef __JR_COMMON_TYPES_H
#define __JR_COMMON_TYPES_H

#include <string>
#include <list>
#include <ostream>
#include <sstream>
#include "OS.h"

namespace DeVivo {
namespace Junior {

// Define convenient types.
typedef std::list<std::string> StringList;
typedef std::list<std::string>::iterator StringListIter;


static unsigned char getByte(unsigned long in, char num)
{
    return ((unsigned char)(in>>(num*8)));
}

// versions of the header/footer supported
typedef enum { UnknownVersion = 0, OPC, AS5669, AS5669A } MsgVersion;
static std::string VersionEnumToString(MsgVersion v)
{
	if (v == OPC) return "OPC";
	if (v == AS5669) return "AS5669";
	if (v == AS5669A) return "AS5669A";
	return "UnknownVersion";
}
static MsgVersion VersionStringToEnum(std::string v)
{
	if ((v == "OPC") || (v == "opc")) return OPC;
	if ((v == "AS5669") || (v == "as5669")) return AS5669;
	if ((v == "AS5669A") || (v == "as5669A")) return AS5669A;
	return UnknownVersion;
}

// Types for JAUS_ID.  The JAUS ID is simply an unsigned int
// but has to watch out for wildcard bytes (0xFF) during
// comparison operations.
class JAUS_ID
{
  public:
    JAUS_ID(){val=0;};
    JAUS_ID(unsigned int in){val=in;}
    JAUS_ID(std::string str)
    {
        std::stringstream ss; ss << str; ss >> val;
    }
    ~JAUS_ID(){}

    unsigned int val;
    bool operator==(const JAUS_ID& in) const
    {
		// If either is zero, return false.
		if ((val == 0) || (in.val == 0)) return false;

        // Check for the easy case for computation efficiency.
        if (val == in.val) return true;

        // Each byte may have a wildcard (0xFF), so we need to check bytewise
        // comparisons.
        for (char i=0; i<4; i++)
            if ((getByte(val, i) != 0xFF) &&
                (getByte(in.val, i) != 0xFF) &&
                (getByte(val, i) != getByte(in.val, i)))
            {
                return false;
            }

        // Getting to this point means each byte is equivalent or
        // a wildcard.
	    return true;
    }
    bool operator<(const JAUS_ID& in) const
    {
        if (val < in.val) return true;
        return false;
    }
    bool operator!=(const JAUS_ID& in) const
    {
        if (val != in.val) return true;
        return false;
    }
    bool containsWildcards()
    {
        // Each byte may have a wildcard (0xFF), so we need to check each
        for (char i=0; i<4; i++)
            if (getByte(val, i) == 0xFF) return true;
        return false;
    }
};

//
// Define a helper class for IP address (with port).
// Note that this is stored internally in NETWORK BYTE ORDER.
//
class IP_ADDRESS
{
  public:
    IP_ADDRESS():addr(0), port(0){};
    IP_ADDRESS(struct sockaddr_in in) :
                   addr(in.sin_addr.s_addr),port(in.sin_port){};
    ~IP_ADDRESS(){};

   bool operator==(IP_ADDRESS in)
   {
	   return (addr == in.addr) && (port == in.port);
   }
   bool operator!=(IP_ADDRESS in)
   {
	   return (addr != in.addr) || (port != in.port);
   }
   std::string toString() const
   {
       std::stringstream ss;
       ss << inet_ntoa(*(in_addr*) &addr) << ":" << ntohs(port);
       return ss.str();
   }
   bool fromString(std::string str)
   {
       // extract substrings for address and port (assume the
       // incoming line is of the form "dot-notation-address:port"
       std::string ip_addr_str = str.substr(0, str.find_first_of(":"));
       std::string port_str = str.substr(str.find_first_of(":")+1);
       if (ip_addr_str.empty() || port_str.empty()) return false;

       // populate the data members
       addr = inet_addr(ip_addr_str.c_str());
       port = htons((unsigned short)(strtod(port_str.c_str(), NULL)));
       return true;
   }
   unsigned int addr;
   unsigned short port;
};

}} // namespace DeVivo::Junior

template <class charT, class traits>
  std::basic_ostream<charT,traits>& operator<< (std::basic_ostream<charT,traits>& os, const DeVivo::Junior::IP_ADDRESS& xAddress )
{
   os << xAddress.toString();
   return os;
}

template <class charT, class traits>
  std::basic_ostream<charT,traits>& operator<< (std::basic_ostream<charT,traits>& os, const DeVivo::Junior::JAUS_ID& xID )
{
   os << xID.val;
   return os;
}

#endif //__JR_COMMON_TYPES_H



