%copyright%

%user_include_definitions%

%user_constants_definitions%

using namespace JTS;

namespace %service_namespace%
{

%user_definitions%

%statemachine_name%::%statemachine_name%(%parent_fsm_arguments%)
%sm_constuctor%


%statemachine_name%::~%statemachine_name%() 
{
	delete context;
}

void %statemachine_name%::setupNotifications()
{
%setup_notifications%
}

%action_method_definitions%

%guard_method_definitions%

};