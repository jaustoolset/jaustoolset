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
#ifndef __CONNECTION_MGR_H
#define __CONNECTION_MGR_H

#include <vector>
#include "Types.h"
#include "ConfigData.h"
#include "JrLogger.h"

namespace DeVivo {
namespace Junior {



template<class AddressType>
class Connection 
{
public:
    Connection(){_version = UnknownVersion;};
	Connection(JAUS_ID id, AddressType addr, MsgVersion version):
		_address(addr), _id(id), _version(version){};
   ~Connection(){};

    // Define accessors
    AddressType getAddress(){return _address;}
	void setAddress(AddressType addy){_address = addy;}

	JAUS_ID getId(){return _id;}
	void setId(JAUS_ID id){_id = id;}

	MsgVersion getVersion() {return _version;}
	void setVersion(MsgVersion v){_version = v;}

protected:
	AddressType _address;
	JAUS_ID     _id;
	MsgVersion  _version;
};

template<class AddressType>
class ConnectionList
{
public:
	ConnectionList(){};
	~ConnectionList(){};

	typedef std::vector<Connection <AddressType>* > ConnectionListType;

    // Functions to manage the list
    bool addElement(JAUS_ID id, AddressType addr, MsgVersion version, bool allowWildcards = false);
    bool removeElement(JAUS_ID id);

	// Functions to access the list
	bool getAddrFromId( JAUS_ID id, AddressType& addr );
	bool getMsgVersion( JAUS_ID id, MsgVersion& version );
	bool updateConnection( JAUS_ID id, AddressType& add, MsgVersion& version );
    ConnectionListType& getList(){return _list;};
   
protected:

	// This should really be a list, but iterators don't seem to like
	// lists of templated class pointers.  We use a vector as a 
	// work around.
	ConnectionListType _list;
};

// define inlines
template<class AddressType>
inline bool ConnectionList<AddressType>::addElement(JAUS_ID id, 
                                AddressType addr, 
                                MsgVersion version, 
                                bool allowWildcards)
{
    // Not permitted for zero id's
    if (id.val == 0) return false;

    // AEODRS SPECIFIC: Don't record anything with 0000 as subsystem ID
    // IOP Uses zeros as placeholders... not sure what if anything we
    // need to restrict here anymore
    if (id.containsWildcards() && !allowWildcards) return true;

   // check for duplicates
   AddressType prevAddr;
   if (getAddrFromId(id, prevAddr))
   {
      // Element with same ID.
      // Update the address and version
      // and return success.
      updateConnection(id, addr, version);
      return true;
   }

   // Existing element does not exist.  Create a new connection
   Connection<AddressType>* connection = new Connection<AddressType>(id, addr, version);
   if (connection == NULL) return false;

    // Add this connection to our list
    JrInfo
      << "Adding address book entry for id " << id.val
      << " for addr " << addr
      << " at position " << _list.size()
      << std::endl;
    _list.push_back(connection);
    return true;
}

template<class S>
inline bool ConnectionList<S>::removeElement(JAUS_ID id)
{
    // Wipe all entries with matching ids
    for (int i=0; i < _list.size(); i++)
        if (_list[i]->getId().val == id.val) _list[i]->setId(0);
    return true;
}

template<class AddressType>
inline bool ConnectionList<AddressType>::getAddrFromId( JAUS_ID id, AddressType& addr )
{
    if (id == 0) return false;

    // Check for a match based on the JAUS_ID
    for (int i=0; i < _list.size(); i++)
    {
        //printf("List size = %d\n", _list.size());
        if (_list[i] == NULL) continue;
        if (_list[i]->getId().val == id.val)
        {
            addr = _list[i]->getAddress();
            return true;
        }
    }
    return false;
}

template<class AddressType>
inline bool ConnectionList<AddressType>::getMsgVersion( JAUS_ID id, MsgVersion& version )
{
    if (id == 0) return false;

    // Check for a match based on the JAUS_ID
    for (int i=0; i < _list.size(); i++)
    {
        if (_list[i]->getId().val == id.val)
        {
            version = _list[i]->getVersion();
            return true;
        }
    }
    return false;
}

template<class AddressType>
inline bool ConnectionList<AddressType>::updateConnection( JAUS_ID id, AddressType& addr, MsgVersion& version )
{
    if (id == 0) return false;

    // Check for a match based on the JAUS_ID
    for (int i=0; i < _list.size(); i++)
    {
        if (_list[i]->getId().val == id.val)
        {
            _list[i]->setAddress(addr);
            _list[i]->setVersion(version);
            return true;
        }
    }
    return false;
}


// Specialize the ConnectionList for ip_address support.
// This also allows addresses to be loaded from a configuration file.
class IpAddressBook : public ConnectionList<IP_ADDRESS>
{
public:
    IpAddressBook(){};
   ~IpAddressBook(){};

   // Function to populate an address book from a file
   bool Load(ConfigData& addresses); 
};

//
// Load the address book from a given configuration file.
// All entries must be of the form:
//   L<id> = <ip_address_in_dot_notation>:<port>
// 
inline bool IpAddressBook::Load(ConfigData& addresses)
{
    // Each key in the address book should be a JAUS ID
    StringList ids = addresses.getAttributes("AddressBook");

    // For each key, extract the IP address
    StringListIter iter;
    for (iter = ids.begin(); iter != ids.end(); iter++)
    {
        // Pull the ip_address port string for the id
        std::string rhs;
        if (addresses.getValue(rhs, *iter, "AddressBook") != ConfigData::Ok) continue;

        // Make an IP_ADDRESS structure from the string
        IP_ADDRESS ip_struct;
		if (!ip_struct.fromString(rhs)) continue;

		// Pull the ID from the key (remove the leading character)
		std::string ID = (*iter).substr(1);

        // Add to the map
        addElement(JAUS_ID((unsigned int)strtod(ID.c_str(), NULL)), ip_struct, UnknownVersion);

        JrFull << "Adding entry to address map: " << ID << " -> " << 
            ip_struct.toString() << std::endl;
    }

    return true;
}

}} // namespace DeVivo::Junior
#endif


