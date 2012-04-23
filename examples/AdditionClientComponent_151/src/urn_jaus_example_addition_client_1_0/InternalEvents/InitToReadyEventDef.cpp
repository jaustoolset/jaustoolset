#include "urn_jaus_example_addition_client_1_0/InternalEvents/InitToReadyEventDef.h"

namespace urn_jaus_example_addition_client_1_0
{

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int InitToReadyEventDef::getSize()
{
	unsigned int size = 0;
	
	
	return size;
}

void InitToReadyEventDef::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
}

void InitToReadyEventDef::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
}

InitToReadyEventDef::InitToReadyEventDef()
{
	m_Name = "InitToReadyEventDef";
}

InitToReadyEventDef::~InitToReadyEventDef()
{
}


}
