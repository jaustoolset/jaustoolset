#ifndef URN_JTS_DISCOVERYCLIENT_1_0_RHP_TIMEOUT_H
#define URN_JTS_DISCOVERYCLIENT_1_0_RHP_TIMEOUT_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jts_DiscoveryClient_1_0
{

class DllExport RHP_Timeout: public JTS::InternalEvent
{
public:
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	RHP_Timeout();
	virtual ~RHP_Timeout();
};

}

#endif // URN_JTS_DISCOVERYCLIENT_1_0_RHP_TIMEOUT_H
