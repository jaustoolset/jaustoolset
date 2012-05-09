#ifndef _H_LOCALPOSESENSOR_RECEIVEFSM_SM
#define _H_LOCALPOSESENSOR_RECEIVEFSM_SM

/*
 * ex: set ro:
 * DO NOT EDIT.
 * generated by smc (http://smc.sourceforge.net/)
 * from file : LocalPoseSensor_ReceiveFSM_sm.sm
 */


#define SMC_USES_IOSTREAMS

#include <statemap.h>

namespace urn_jaus_jss_mobility_LocalPoseSensor_1_0
{
    // Forward declarations.
    class LocalPoseSensor_ReceiveFSM_SM;
    class LocalPoseSensor_ReceiveFSM_SM_Receiving_Ready_NotControlled;
    class LocalPoseSensor_ReceiveFSM_SM_Receiving_Ready_Controlled;
    class LocalPoseSensor_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE;
    class LocalPoseSensor_ReceiveFSM_SM_Default;
    class LocalPoseSensor_ReceiveFSMState;
    class LocalPoseSensor_ReceiveFSMContext;
    class LocalPoseSensor_ReceiveFSM;

    class LocalPoseSensor_ReceiveFSMState :
        public statemap::State
    {
    public:

        LocalPoseSensor_ReceiveFSMState(const char *name, int stateId)
        : statemap::State(name, stateId)
        {};

        virtual void Entry(LocalPoseSensor_ReceiveFSMContext&) {};
        virtual void Exit(LocalPoseSensor_ReceiveFSMContext&) {};

        virtual void BroadcastGlobalTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void BroadcastLocalTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void CancelEventTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void CreateEventTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void EventErrorTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void EventOccurredTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_ReceivingTransition(LocalPoseSensor_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        virtual void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_ReadyTransition(LocalPoseSensor_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        virtual void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_Ready_ControlledTransition(LocalPoseSensor_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        virtual void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_Ready_NotControlledTransition(LocalPoseSensor_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        virtual void QueryAuthorityTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void QueryControlTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void QueryEventsTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void QueryLocalPoseTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void QueryTimeoutTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void ReceiveTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void ReceiveTransition(LocalPoseSensor_ReceiveFSMContext& context, QueryLocalPose msg, Receive::Body::ReceiveRec transportData);
        virtual void ReceiveTransition(LocalPoseSensor_ReceiveFSMContext& context, SetLocalPose msg, Receive::Body::ReceiveRec transportData);
        virtual void ReleaseControlTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void RequestControlTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void SendTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void SetAuthorityTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void SetLocalPoseTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void TimeoutTransition(LocalPoseSensor_ReceiveFSMContext& context);
        virtual void UpdateEventTransition(LocalPoseSensor_ReceiveFSMContext& context);

    protected:

        virtual void Default(LocalPoseSensor_ReceiveFSMContext& context);
    };

    class LocalPoseSensor_ReceiveFSM_SM
    {
    public:

        static LocalPoseSensor_ReceiveFSM_SM_Receiving_Ready_NotControlled Receiving_Ready_NotControlled;
        static LocalPoseSensor_ReceiveFSM_SM_Receiving_Ready_Controlled Receiving_Ready_Controlled;
        static LocalPoseSensor_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE;
    };

    class LocalPoseSensor_ReceiveFSM_SM_Default :
        public LocalPoseSensor_ReceiveFSMState
    {
    public:

        LocalPoseSensor_ReceiveFSM_SM_Default(const char *name, int stateId)
        : LocalPoseSensor_ReceiveFSMState(name, stateId)
        {};

    };

    class LocalPoseSensor_ReceiveFSM_SM_Receiving_Ready_NotControlled :
        public LocalPoseSensor_ReceiveFSM_SM_Default
    {
    public:
        LocalPoseSensor_ReceiveFSM_SM_Receiving_Ready_NotControlled(const char *name, int stateId)
        : LocalPoseSensor_ReceiveFSM_SM_Default(name, stateId)
        {};

        void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_Ready_ControlledTransition(LocalPoseSensor_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        void ReceiveTransition(LocalPoseSensor_ReceiveFSMContext& context, QueryLocalPose msg, Receive::Body::ReceiveRec transportData);
    };

    class LocalPoseSensor_ReceiveFSM_SM_Receiving_Ready_Controlled :
        public LocalPoseSensor_ReceiveFSM_SM_Default
    {
    public:
        LocalPoseSensor_ReceiveFSM_SM_Receiving_Ready_Controlled(const char *name, int stateId)
        : LocalPoseSensor_ReceiveFSM_SM_Default(name, stateId)
        {};

        void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_ReceivingTransition(LocalPoseSensor_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_ReadyTransition(LocalPoseSensor_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_Ready_NotControlledTransition(LocalPoseSensor_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        void ReceiveTransition(LocalPoseSensor_ReceiveFSMContext& context, QueryLocalPose msg, Receive::Body::ReceiveRec transportData);
        void ReceiveTransition(LocalPoseSensor_ReceiveFSMContext& context, SetLocalPose msg, Receive::Body::ReceiveRec transportData);
    };

    class LocalPoseSensor_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE :
        public LocalPoseSensor_ReceiveFSM_SM_Default
    {
    public:
        LocalPoseSensor_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE(const char *name, int stateId)
        : LocalPoseSensor_ReceiveFSM_SM_Default(name, stateId)
        {};

        void BroadcastGlobalTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void BroadcastLocalTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void CancelEventTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void CreateEventTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void EventErrorTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void EventOccurredTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void QueryAuthorityTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void QueryControlTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void QueryEventsTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void QueryLocalPoseTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void QueryTimeoutTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void ReceiveTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void ReleaseControlTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void RequestControlTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void SendTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void SetAuthorityTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void SetLocalPoseTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void TimeoutTransition(LocalPoseSensor_ReceiveFSMContext& context);
        void UpdateEventTransition(LocalPoseSensor_ReceiveFSMContext& context);
    };

    class LocalPoseSensor_ReceiveFSMContext :
        public statemap::FSMContext
    {
    public:

        LocalPoseSensor_ReceiveFSMContext(LocalPoseSensor_ReceiveFSM& owner)
        : FSMContext(LocalPoseSensor_ReceiveFSM_SM::Receiving_Ready_NotControlled),
          _owner(owner)
        {};

        LocalPoseSensor_ReceiveFSMContext(LocalPoseSensor_ReceiveFSM& owner, const statemap::State& state)
        : FSMContext(state),
          _owner(owner)
        {};

        virtual void enterStartState()
        {
            getState().Entry(*this);
            return;
        }

        LocalPoseSensor_ReceiveFSM& getOwner() const
        {
            return (_owner);
        };

        LocalPoseSensor_ReceiveFSMState& getState() const
        {
            if (_state == NULL)
            {
                throw statemap::StateUndefinedException();
            }

            return (dynamic_cast<LocalPoseSensor_ReceiveFSMState&>(*_state));
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

        void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_ReceivingTransition(JTS::InternalEvent* ie)
        {
            setTransition("InternalStateChange_To_LocalPoseSensor_ReceiveFSM_ReceivingTransition");
            (getState()).InternalStateChange_To_LocalPoseSensor_ReceiveFSM_ReceivingTransition(*this, ie);
            setTransition(NULL);
        };

        void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_ReadyTransition(JTS::InternalEvent* ie)
        {
            setTransition("InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_ReadyTransition");
            (getState()).InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_ReadyTransition(*this, ie);
            setTransition(NULL);
        };

        void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_Ready_ControlledTransition(JTS::InternalEvent* ie)
        {
            setTransition("InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_Ready_ControlledTransition");
            (getState()).InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_Ready_ControlledTransition(*this, ie);
            setTransition(NULL);
        };

        void InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_Ready_NotControlledTransition(JTS::InternalEvent* ie)
        {
            setTransition("InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_Ready_NotControlledTransition");
            (getState()).InternalStateChange_To_LocalPoseSensor_ReceiveFSM_Receiving_Ready_NotControlledTransition(*this, ie);
            setTransition(NULL);
        };

        void QueryAuthorityTransition()
        {
            setTransition("QueryAuthorityTransition");
            (getState()).QueryAuthorityTransition(*this);
            setTransition(NULL);
        };

        void QueryControlTransition()
        {
            setTransition("QueryControlTransition");
            (getState()).QueryControlTransition(*this);
            setTransition(NULL);
        };

        void QueryEventsTransition()
        {
            setTransition("QueryEventsTransition");
            (getState()).QueryEventsTransition(*this);
            setTransition(NULL);
        };

        void QueryLocalPoseTransition()
        {
            setTransition("QueryLocalPoseTransition");
            (getState()).QueryLocalPoseTransition(*this);
            setTransition(NULL);
        };

        void QueryTimeoutTransition()
        {
            setTransition("QueryTimeoutTransition");
            (getState()).QueryTimeoutTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition()
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition(QueryLocalPose msg, Receive::Body::ReceiveRec transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void ReceiveTransition(SetLocalPose msg, Receive::Body::ReceiveRec transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void ReleaseControlTransition()
        {
            setTransition("ReleaseControlTransition");
            (getState()).ReleaseControlTransition(*this);
            setTransition(NULL);
        };

        void RequestControlTransition()
        {
            setTransition("RequestControlTransition");
            (getState()).RequestControlTransition(*this);
            setTransition(NULL);
        };

        void SendTransition()
        {
            setTransition("SendTransition");
            (getState()).SendTransition(*this);
            setTransition(NULL);
        };

        void SetAuthorityTransition()
        {
            setTransition("SetAuthorityTransition");
            (getState()).SetAuthorityTransition(*this);
            setTransition(NULL);
        };

        void SetLocalPoseTransition()
        {
            setTransition("SetLocalPoseTransition");
            (getState()).SetLocalPoseTransition(*this);
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

        LocalPoseSensor_ReceiveFSM& _owner;
    };
}


/*
 * Local variables:
 *  buffer-read-only: t
 * End:
 */

#endif // _H_LOCALPOSESENSOR_RECEIVEFSM_SM