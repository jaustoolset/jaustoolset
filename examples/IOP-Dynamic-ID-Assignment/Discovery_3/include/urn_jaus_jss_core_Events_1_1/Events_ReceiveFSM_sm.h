#ifndef _H_EVENTS_RECEIVEFSM_SM
#define _H_EVENTS_RECEIVEFSM_SM

/*
 * ex: set ro:
 * DO NOT EDIT.
 * generated by smc (http://smc.sourceforge.net/)
 * from file : Events_ReceiveFSM_sm.sm
 */


#define SMC_USES_IOSTREAMS

#include <statemap.h>

namespace urn_jaus_jss_core_Events_1_1
{
    // Forward declarations.
    class Events_ReceiveFSM_SM;
    class Events_ReceiveFSM_SM_Receiving_Ready;
    class Events_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE;
    class Events_ReceiveFSM_SM_Default;
    class Events_ReceiveFSMState;
    class Events_ReceiveFSMContext;
    class Events_ReceiveFSM;

    class Events_ReceiveFSMState :
        public statemap::State
    {
    public:

        Events_ReceiveFSMState(const char *name, int stateId)
        : statemap::State(name, stateId)
        {};

        virtual void Entry(Events_ReceiveFSMContext&) {};
        virtual void Exit(Events_ReceiveFSMContext&) {};

        virtual void BroadcastGlobalTransition(Events_ReceiveFSMContext& context);
        virtual void BroadcastLocalTransition(Events_ReceiveFSMContext& context);
        virtual void CancelEventTransition(Events_ReceiveFSMContext& context);
        virtual void CommandCompletedTransition(Events_ReceiveFSMContext& context);
        virtual void CommandExpiredTransition(Events_ReceiveFSMContext& context);
        virtual void CreateCommandEventTransition(Events_ReceiveFSMContext& context);
        virtual void CreateEventTransition(Events_ReceiveFSMContext& context);
        virtual void EventErrorTransition(Events_ReceiveFSMContext& context);
        virtual void EventOccurredTransition(Events_ReceiveFSMContext& context);
        virtual void QueryEventTimeoutTransition(Events_ReceiveFSMContext& context);
        virtual void QueryEventsTransition(Events_ReceiveFSMContext& context);
        virtual void ReceiveTransition(Events_ReceiveFSMContext& context);
        virtual void ReceiveTransition(Events_ReceiveFSMContext& context, CancelEvent& msg, Receive::Body::ReceiveRec& transportData);
        virtual void ReceiveTransition(Events_ReceiveFSMContext& context, CreateEvent& msg, Receive::Body::ReceiveRec& transportData);
        virtual void ReceiveTransition(Events_ReceiveFSMContext& context, QueryEventTimeout& msg, Receive::Body::ReceiveRec& transportData);
        virtual void ReceiveTransition(Events_ReceiveFSMContext& context, QueryEvents& msg, Receive::Body::ReceiveRec& transportData);
        virtual void ReceiveTransition(Events_ReceiveFSMContext& context, UpdateEvent& msg, Receive::Body::ReceiveRec& transportData);
        virtual void SendTransition(Events_ReceiveFSMContext& context);
        virtual void TimeoutTransition(Events_ReceiveFSMContext& context);
        virtual void UpdateEventTransition(Events_ReceiveFSMContext& context);

    protected:

        virtual void Default(Events_ReceiveFSMContext& context);
    };

    class Events_ReceiveFSM_SM
    {
    public:

        static Events_ReceiveFSM_SM_Receiving_Ready Receiving_Ready;
        static Events_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE;
    };

    class Events_ReceiveFSM_SM_Default :
        public Events_ReceiveFSMState
    {
    public:

        Events_ReceiveFSM_SM_Default(const char *name, int stateId)
        : Events_ReceiveFSMState(name, stateId)
        {};

    };

    class Events_ReceiveFSM_SM_Receiving_Ready :
        public Events_ReceiveFSM_SM_Default
    {
    public:
        Events_ReceiveFSM_SM_Receiving_Ready(const char *name, int stateId)
        : Events_ReceiveFSM_SM_Default(name, stateId)
        {};

        void EventErrorTransition(Events_ReceiveFSMContext& context);
        void EventOccurredTransition(Events_ReceiveFSMContext& context);
        void ReceiveTransition(Events_ReceiveFSMContext& context, CancelEvent& msg, Receive::Body::ReceiveRec& transportData);
        void ReceiveTransition(Events_ReceiveFSMContext& context, CreateEvent& msg, Receive::Body::ReceiveRec& transportData);
        void ReceiveTransition(Events_ReceiveFSMContext& context, QueryEventTimeout& msg, Receive::Body::ReceiveRec& transportData);
        void ReceiveTransition(Events_ReceiveFSMContext& context, QueryEvents& msg, Receive::Body::ReceiveRec& transportData);
        void ReceiveTransition(Events_ReceiveFSMContext& context, UpdateEvent& msg, Receive::Body::ReceiveRec& transportData);
        void TimeoutTransition(Events_ReceiveFSMContext& context);
    };

    class Events_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE :
        public Events_ReceiveFSM_SM_Default
    {
    public:
        Events_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE(const char *name, int stateId)
        : Events_ReceiveFSM_SM_Default(name, stateId)
        {};

        void BroadcastGlobalTransition(Events_ReceiveFSMContext& context);
        void BroadcastLocalTransition(Events_ReceiveFSMContext& context);
        void CancelEventTransition(Events_ReceiveFSMContext& context);
        void CommandCompletedTransition(Events_ReceiveFSMContext& context);
        void CommandExpiredTransition(Events_ReceiveFSMContext& context);
        void CreateCommandEventTransition(Events_ReceiveFSMContext& context);
        void CreateEventTransition(Events_ReceiveFSMContext& context);
        void EventErrorTransition(Events_ReceiveFSMContext& context);
        void EventOccurredTransition(Events_ReceiveFSMContext& context);
        void QueryEventTimeoutTransition(Events_ReceiveFSMContext& context);
        void QueryEventsTransition(Events_ReceiveFSMContext& context);
        void ReceiveTransition(Events_ReceiveFSMContext& context);
        void SendTransition(Events_ReceiveFSMContext& context);
        void TimeoutTransition(Events_ReceiveFSMContext& context);
        void UpdateEventTransition(Events_ReceiveFSMContext& context);
    };

    class Events_ReceiveFSMContext :
        public statemap::FSMContext
    {
    public:

        Events_ReceiveFSMContext(Events_ReceiveFSM& owner)
        : FSMContext(Events_ReceiveFSM_SM::Receiving_Ready),
          _owner(owner)
        {};

        Events_ReceiveFSMContext(Events_ReceiveFSM& owner, const statemap::State& state)
        : FSMContext(state),
          _owner(owner)
        {};

        virtual void enterStartState()
        {
            getState().Entry(*this);
            return;
        }

        Events_ReceiveFSM& getOwner() const
        {
            return (_owner);
        };

        Events_ReceiveFSMState& getState() const
        {
            if (_state == NULL)
            {
                throw statemap::StateUndefinedException();
            }

            return (dynamic_cast<Events_ReceiveFSMState&>(*_state));
        };

        void BroadcastGlobalTransition()
        {
            setTransition("BroadcastGlobalTransition");
            (getState()).BroadcastGlobalTransition(*this);
            setTransition(NULL);
        };

        void BroadcastLocalTransition()
        {
            setTransition("BroadcastLocalTransition");
            (getState()).BroadcastLocalTransition(*this);
            setTransition(NULL);
        };

        void CancelEventTransition()
        {
            setTransition("CancelEventTransition");
            (getState()).CancelEventTransition(*this);
            setTransition(NULL);
        };

        void CommandCompletedTransition()
        {
            setTransition("CommandCompletedTransition");
            (getState()).CommandCompletedTransition(*this);
            setTransition(NULL);
        };

        void CommandExpiredTransition()
        {
            setTransition("CommandExpiredTransition");
            (getState()).CommandExpiredTransition(*this);
            setTransition(NULL);
        };

        void CreateCommandEventTransition()
        {
            setTransition("CreateCommandEventTransition");
            (getState()).CreateCommandEventTransition(*this);
            setTransition(NULL);
        };

        void CreateEventTransition()
        {
            setTransition("CreateEventTransition");
            (getState()).CreateEventTransition(*this);
            setTransition(NULL);
        };

        void EventErrorTransition()
        {
            setTransition("EventErrorTransition");
            (getState()).EventErrorTransition(*this);
            setTransition(NULL);
        };

        void EventOccurredTransition()
        {
            setTransition("EventOccurredTransition");
            (getState()).EventOccurredTransition(*this);
            setTransition(NULL);
        };

        void QueryEventTimeoutTransition()
        {
            setTransition("QueryEventTimeoutTransition");
            (getState()).QueryEventTimeoutTransition(*this);
            setTransition(NULL);
        };

        void QueryEventsTransition()
        {
            setTransition("QueryEventsTransition");
            (getState()).QueryEventsTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition()
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition(CancelEvent& msg, Receive::Body::ReceiveRec& transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void ReceiveTransition(CreateEvent& msg, Receive::Body::ReceiveRec& transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void ReceiveTransition(QueryEventTimeout& msg, Receive::Body::ReceiveRec& transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void ReceiveTransition(QueryEvents& msg, Receive::Body::ReceiveRec& transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void ReceiveTransition(UpdateEvent& msg, Receive::Body::ReceiveRec& transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void SendTransition()
        {
            setTransition("SendTransition");
            (getState()).SendTransition(*this);
            setTransition(NULL);
        };

        void TimeoutTransition()
        {
            setTransition("TimeoutTransition");
            (getState()).TimeoutTransition(*this);
            setTransition(NULL);
        };

        void UpdateEventTransition()
        {
            setTransition("UpdateEventTransition");
            (getState()).UpdateEventTransition(*this);
            setTransition(NULL);
        };

    private:

        Events_ReceiveFSM& _owner;
    };
}


/*
 * Local variables:
 *  buffer-read-only: t
 * End:
 */

#endif // _H_EVENTS_RECEIVEFSM_SM
