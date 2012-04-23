#ifndef URN_JAUS_JSS_CORE_EVENTS_1_1_COMMANDCOMPLETED_H
#define URN_JAUS_JSS_CORE_EVENTS_1_1_COMMANDCOMPLETED_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jaus_jss_core_Events_1_1
{

class DllExport CommandCompleted: public JTS::InternalEvent
{
public:
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	CommandCompleted();
	virtual ~CommandCompleted();
};

}

#endif // URN_JAUS_JSS_CORE_EVENTS_1_1_COMMANDCOMPLETED_H
