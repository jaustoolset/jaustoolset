#include "urn_jaus_jss_core_AccessControl_1_1/InternalEvents/CommandExpired.h"

namespace urn_jaus_jss_core_AccessControl_1_1
{

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int CommandExpired::getSize()
{
	unsigned int size = 0;
	
	
	return size;
}

void CommandExpired::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
}

void CommandExpired::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
}

CommandExpired::CommandExpired()
{
	m_Name = "CommandExpired";
}

CommandExpired::~CommandExpired()
{
}


}
