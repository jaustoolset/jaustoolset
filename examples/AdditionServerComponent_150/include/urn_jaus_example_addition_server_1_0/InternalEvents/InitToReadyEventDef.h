#ifndef URN_JAUS_EXAMPLE_ADDITION_SERVER_1_0_INITTOREADYEVENTDEF_H
#define URN_JAUS_EXAMPLE_ADDITION_SERVER_1_0_INITTOREADYEVENTDEF_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jaus_example_addition_server_1_0
{

class DllExport InitToReadyEventDef: public JTS::InternalEvent
{
public:
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	InitToReadyEventDef();
	virtual ~InitToReadyEventDef();
};

}

#endif // URN_JAUS_EXAMPLE_ADDITION_SERVER_1_0_INITTOREADYEVENTDEF_H
