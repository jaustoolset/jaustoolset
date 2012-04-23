#ifndef URN_JAUS_JSS_CORE_MANAGEMENT_1_0_EVENTERROR_H
#define URN_JAUS_JSS_CORE_MANAGEMENT_1_0_EVENTERROR_H

#include "JausUtils.h"
#include "InternalEvents/InternalEvent.h"
namespace urn_jaus_jss_core_Management_1_0
{

class DllExport EventError: public JTS::InternalEvent
{
public:
	const unsigned int getSize();
	virtual void encode(unsigned char *bytes);
	virtual void decode(const unsigned char *bytes);
	EventError();
	virtual ~EventError();
};

}

#endif // URN_JAUS_JSS_CORE_MANAGEMENT_1_0_EVENTERROR_H
