#include "urn_jts_DiscoveryClient_1_0/InternalEvents/RHP_Timeout.h"

namespace urn_jts_DiscoveryClient_1_0
{

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int RHP_Timeout::getSize()
{
	unsigned int size = 0;
	
	
	return size;
}

void RHP_Timeout::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
}

void RHP_Timeout::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
}

RHP_Timeout::RHP_Timeout()
{
	m_Name = "RHP_Timeout";
}

RHP_Timeout::~RHP_Timeout()
{
}


}
