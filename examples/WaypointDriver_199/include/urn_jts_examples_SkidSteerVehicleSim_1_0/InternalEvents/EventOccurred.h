#ifndef URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_EVENTOCCURRED_H
#define URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_EVENTOCCURRED_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

class DllExport EventOccurred: public JTS::InternalEvent
{
public:
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	EventOccurred();
	virtual ~EventOccurred();
};

}

#endif // URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_EVENTOCCURRED_H
