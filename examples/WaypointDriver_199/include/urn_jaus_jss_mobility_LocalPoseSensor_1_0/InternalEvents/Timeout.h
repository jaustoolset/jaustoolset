#ifndef URN_JAUS_JSS_MOBILITY_LOCALPOSESENSOR_1_0_TIMEOUT_H
#define URN_JAUS_JSS_MOBILITY_LOCALPOSESENSOR_1_0_TIMEOUT_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jaus_jss_mobility_LocalPoseSensor_1_0
{

class DllExport Timeout: public JTS::InternalEvent
{
public:
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	Timeout();
	virtual ~Timeout();
};

}

#endif // URN_JAUS_JSS_MOBILITY_LOCALPOSESENSOR_1_0_TIMEOUT_H
