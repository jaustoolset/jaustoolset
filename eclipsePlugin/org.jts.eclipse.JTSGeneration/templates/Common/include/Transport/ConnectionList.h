/*! 
 ***********************************************************************
 * @file      ConnectionMgr.h
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
    bool addElement(JAUS_ID id, AddressType addr, MsgVersion version);
    bool removeElement(JAUS_ID id);

	// Functions to access the list
	bool getAddrFromId( JAUS_ID id, AddressType& addr );
	bool getMsgVersion( JAUS_ID id, MsgVersion& version );
	bool updateMsgVersion( JAUS_ID id, MsgVersion version );
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
										  MsgVersion version)
{
    // Not permitted for zero id's
    if (id.val == 0) return false;

	// check for duplicates
	AddressType prevAddr; MsgVersion prevVersion;
	if (getAddrFromId(id, prevAddr) && (addr == prevAddr))
	{
		// Element with same ID and address found.  Update the version
		// and return success.
		updateMsgVersion(id, version);
		return true;
	}

	// Existing element does not exist.  Create a new connection
	Connection<AddressType>* connection = new Connection<AddressType>(id, addr, version);
	if (connection == NULL) return false;

    // Add this connection to our list
    JrFull << "Adding address book entry for id " << id.val << std::endl;
    _list.push_back(connection);
    return true;
}

template<class S>
inline bool ConnectionList<S>::removeElement(JAUS_ID id)
{
    // Wipe all entries with matching ids
    for (int i=0; i < _list.size(); i++)
        if (_list[i]->getId() == id) _list[i]->setId(0);
    return true;
}

template<class S>
inline bool ConnectionList<S>::getAddrFromId( JAUS_ID id, S& addr )
{
    if (id == 0) return false;

    // Check for a match based on the JAUS_ID
    for (int i=0; i < _list.size(); i++)
    {
        if (_list[i]->getId() == id)
        {
            addr = _list[i]->getAddress();
            return true;
        }
    }
    return false;
}

template<class S>
inline bool ConnectionList<S>::getMsgVersion( JAUS_ID id, MsgVersion& version )
{
    if (id == 0) return false;

    // Check for a match based on the JAUS_ID
    for (int i=0; i < _list.size(); i++)
    {
        if (_list[i]->getId() == id)
        {
            version = _list[i]->getVersion();
            return true;
        }
    }
    return false;
}

template<class S>
inline bool ConnectionList<S>::updateMsgVersion( JAUS_ID id, MsgVersion version )
{
    if (id == 0) return false;

    // Check for a match based on the JAUS_ID
    for (int i=0; i < _list.size(); i++)
    {
        if (_list[i]->getId() == id)
        {
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


