#ifndef URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_PERIODICTIMERTRIGGER_H
#define URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_PERIODICTIMERTRIGGER_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1
{

class DllExport PeriodicTimerTrigger: public JTS::InternalEvent
{
public:
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	PeriodicTimerTrigger();
	virtual ~PeriodicTimerTrigger();
};

}

#endif // URN_JAUS_JSS_IOP_UNSOLICITEDHEARTBEAT_1_1_PERIODICTIMERTRIGGER_H
