#ifndef _H_LIVENESS_RECEIVEFSM_SM
#define _H_LIVENESS_RECEIVEFSM_SM

/*
 * ex: set ro:
 * DO NOT EDIT.
 * generated by smc (http://smc.sourceforge.net/)
 * from file : Liveness_ReceiveFSM_sm.sm
 */


#define SMC_USES_IOSTREAMS

#include <statemap.h>

namespace urn_jaus_jss_core_Liveness_1_1
{
    // Forward declarations.
    class Liveness_ReceiveFSM_SM;
    class Liveness_ReceiveFSM_SM_Receiving_Ready;
    class Liveness_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE;
    class Liveness_ReceiveFSM_SM_Default;
    class Liveness_ReceiveFSMState;
    class Liveness_ReceiveFSMContext;
    class Liveness_ReceiveFSM;

    class Liveness_ReceiveFSMState :
        public statemap::State
    {
    public:

        Liveness_ReceiveFSMState(const char *name, int stateId)
        : statemap::State(name, stateId)
        {};

        virtual void Entry(Liveness_ReceiveFSMContext&) {};
        virtual void Exit(Liveness_ReceiveFSMContext&) {};

        virtual void BroadcastGlobalTransition(Liveness_ReceiveFSMContext& context);
        virtual void BroadcastLocalTransition(Liveness_ReceiveFSMContext& context);
        virtual void CancelEventTransition(Liveness_ReceiveFSMContext& context);
        virtual void CommandCompletedTransition(Liveness_ReceiveFSMContext& context);
        virtual void CommandExpiredTransition(Liveness_ReceiveFSMContext& context);
        virtual void CreateCommandEventTransition(Liveness_ReceiveFSMContext& context);
        virtual void CreateEventTransition(Liveness_ReceiveFSMContext& context);
        virtual void EventErrorTransition(Liveness_ReceiveFSMContext& context);
        virtual void EventOccurredTransition(Liveness_ReceiveFSMContext& context);
        virtual void QueryEventTimeoutTransition(Liveness_ReceiveFSMContext& context);
        virtual void QueryEventsTransition(Liveness_ReceiveFSMContext& context);
        virtual void QueryHeartbeatPulseTransition(Liveness_ReceiveFSMContext& context);
        virtual void ReceiveTransition(Liveness_ReceiveFSMContext& context);
        virtual void ReceiveTransition(Liveness_ReceiveFSMContext& context, QueryHeartbeatPulse& msg, Receive::Body::ReceiveRec& transportData);
        virtual void SendTransition(Liveness_ReceiveFSMContext& context);
        virtual void TimeoutTransition(Liveness_ReceiveFSMContext& context);
        virtual void UpdateEventTransition(Liveness_ReceiveFSMContext& context);

    protected:

        virtual void Default(Liveness_ReceiveFSMContext& context);
    };

    class Liveness_ReceiveFSM_SM
    {
    public:

        static Liveness_ReceiveFSM_SM_Receiving_Ready Receiving_Ready;
        static Liveness_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE;
    };

    class Liveness_ReceiveFSM_SM_Default :
        public Liveness_ReceiveFSMState
    {
    public:

        Liveness_ReceiveFSM_SM_Default(const char *name, int stateId)
        : Liveness_ReceiveFSMState(name, stateId)
        {};

    };

    class Liveness_ReceiveFSM_SM_Receiving_Ready :
        public Liveness_ReceiveFSM_SM_Default
    {
    public:
        Liveness_ReceiveFSM_SM_Receiving_Ready(const char *name, int stateId)
        : Liveness_ReceiveFSM_SM_Default(name, stateId)
        {};

        void ReceiveTransition(Liveness_ReceiveFSMContext& context, QueryHeartbeatPulse& msg, Receive::Body::ReceiveRec& transportData);
    };

    class Liveness_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE :
        public Liveness_ReceiveFSM_SM_Default
    {
    public:
        Liveness_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE(const char *name, int stateId)
        : Liveness_ReceiveFSM_SM_Default(name, stateId)
        {};

        void BroadcastGlobalTransition(Liveness_ReceiveFSMContext& context);
        void BroadcastLocalTransition(Liveness_ReceiveFSMContext& context);
        void CancelEventTransition(Liveness_ReceiveFSMContext& context);
        void CommandCompletedTransition(Liveness_ReceiveFSMContext& context);
        void CommandExpiredTransition(Liveness_ReceiveFSMContext& context);
        void CreateCommandEventTransition(Liveness_ReceiveFSMContext& context);
        void CreateEventTransition(Liveness_ReceiveFSMContext& context);
        void EventErrorTransition(Liveness_ReceiveFSMContext& context);
        void EventOccurredTransition(Liveness_ReceiveFSMContext& context);
        void QueryEventTimeoutTransition(Liveness_ReceiveFSMContext& context);
        void QueryEventsTransition(Liveness_ReceiveFSMContext& context);
        void QueryHeartbeatPulseTransition(Liveness_ReceiveFSMContext& context);
        void ReceiveTransition(Liveness_ReceiveFSMContext& context);
        void SendTransition(Liveness_ReceiveFSMContext& context);
        void TimeoutTransition(Liveness_ReceiveFSMContext& context);
        void UpdateEventTransition(Liveness_ReceiveFSMContext& context);
    };

    class Liveness_ReceiveFSMContext :
        public statemap::FSMContext
    {
    public:

        Liveness_ReceiveFSMContext(Liveness_ReceiveFSM& owner)
        : FSMContext(Liveness_ReceiveFSM_SM::Receiving_Ready),
          _owner(owner)
        {};

        Liveness_ReceiveFSMContext(Liveness_ReceiveFSM& owner, const statemap::State& state)
        : FSMContext(state),
          _owner(owner)
        {};

        virtual void enterStartState()
        {
            getState().Entry(*this);
            return;
        }

        Liveness_ReceiveFSM& getOwner() const
        {
            return (_owner);
        };

        Liveness_ReceiveFSMState& getState() const
        {
            if (_state == NULL)
            {
                throw statemap::StateUndefinedException();
            }

            return (dynamic_cast<Liveness_ReceiveFSMState&>(*_state));
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

        void QueryHeartbeatPulseTransition()
        {
            setTransition("QueryHeartbeatPulseTransition");
            (getState()).QueryHeartbeatPulseTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition()
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition(QueryHeartbeatPulse& msg, Receive::Body::ReceiveRec& transportData)
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

        Liveness_ReceiveFSM& _owner;
    };
}


/*
 * Local variables:
 *  buffer-read-only: t
 * End:
 */

#endif // _H_LIVENESS_RECEIVEFSM_SM
