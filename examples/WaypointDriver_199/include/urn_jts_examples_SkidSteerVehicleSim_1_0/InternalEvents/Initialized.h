#ifndef URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_INITIALIZED_H
#define URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_INITIALIZED_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

class DllExport Initialized: public JTS::InternalEvent
{
public:
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	Initialized();
	virtual ~Initialized();
};

}

#endif // URN_JTS_EXAMPLES_SKIDSTEERVEHICLESIM_1_0_INITIALIZED_H
