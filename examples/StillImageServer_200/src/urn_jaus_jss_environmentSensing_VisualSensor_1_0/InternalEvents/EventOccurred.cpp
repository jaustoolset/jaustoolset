#include "urn_jaus_jss_environmentSensing_VisualSensor_1_0/InternalEvents/EventOccurred.h"

namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0
{

/**
 * Returns the size of memory the used data members of the class occupies.
 * This is not necessarily the same size of memory the class actually occupies.
 * Eg. A union of an int and a double may occupy 8 bytes. However, if the data
 *     stored is an int, this function will return a size of 4 bytes.
 * 
 * @return
 */
const unsigned int EventOccurred::getSize()
{
	unsigned int size = 0;
	
	
	return size;
}

void EventOccurred::encode(unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
}

void EventOccurred::decode(const unsigned char *bytes)
{
	
	if (bytes == NULL)
	{
		return;
	}
	
	int pos = 0;
}

EventOccurred::EventOccurred()
{
	m_Name = "EventOccurred";
}

EventOccurred::~EventOccurred()
{
}


}
