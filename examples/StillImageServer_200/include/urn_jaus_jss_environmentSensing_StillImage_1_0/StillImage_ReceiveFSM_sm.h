#ifndef _H_STILLIMAGE_RECEIVEFSM_SM
#define _H_STILLIMAGE_RECEIVEFSM_SM

/*
 * ex: set ro:
 * DO NOT EDIT.
 * generated by smc (http://smc.sourceforge.net/)
 * from file : StillImage_ReceiveFSM_sm.sm
 */


#define SMC_USES_IOSTREAMS

#include <statemap.h>

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{
    // Forward declarations.
    class StillImage_ReceiveFSM_SM;
    class StillImage_ReceiveFSM_SM_Receiving_Ready_NotControlled;
    class StillImage_ReceiveFSM_SM_Receiving_Ready_Controlled;
    class StillImage_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE;
    class StillImage_ReceiveFSM_SM_Default;
    class StillImage_ReceiveFSMState;
    class StillImage_ReceiveFSMContext;
    class StillImage_ReceiveFSM;

    class StillImage_ReceiveFSMState :
        public statemap::State
    {
    public:

        StillImage_ReceiveFSMState(const char *name, int stateId)
        : statemap::State(name, stateId)
        {};

        virtual void Entry(StillImage_ReceiveFSMContext&) {};
        virtual void Exit(StillImage_ReceiveFSMContext&) {};

        virtual void BroadcastGlobalTransition(StillImage_ReceiveFSMContext& context);
        virtual void BroadcastLocalTransition(StillImage_ReceiveFSMContext& context);
        virtual void CancelEventTransition(StillImage_ReceiveFSMContext& context);
        virtual void CreateEventTransition(StillImage_ReceiveFSMContext& context);
        virtual void EventErrorTransition(StillImage_ReceiveFSMContext& context);
        virtual void EventOccurredTransition(StillImage_ReceiveFSMContext& context);
        virtual void InternalStateChange_To_StillImage_ReceiveFSM_ReceivingTransition(StillImage_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        virtual void InternalStateChange_To_StillImage_ReceiveFSM_Receiving_ReadyTransition(StillImage_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        virtual void InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_ControlledTransition(StillImage_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        virtual void InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_NotControlledTransition(StillImage_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        virtual void QueryAuthorityTransition(StillImage_ReceiveFSMContext& context);
        virtual void QueryControlTransition(StillImage_ReceiveFSMContext& context);
        virtual void QueryEventsTransition(StillImage_ReceiveFSMContext& context);
        virtual void QuerySensorGeometricPropertiesTransition(StillImage_ReceiveFSMContext& context);
        virtual void QueryStillImageDataTransition(StillImage_ReceiveFSMContext& context);
        virtual void QueryStillImageSensorCapabilitiesTransition(StillImage_ReceiveFSMContext& context);
        virtual void QueryStillImageSensorConfigurationTransition(StillImage_ReceiveFSMContext& context);
        virtual void QueryTimeoutTransition(StillImage_ReceiveFSMContext& context);
        virtual void QueryVisualSensorCapabilitiesTransition(StillImage_ReceiveFSMContext& context);
        virtual void QueryVisualSensorConfigurationTransition(StillImage_ReceiveFSMContext& context);
        virtual void ReceiveTransition(StillImage_ReceiveFSMContext& context);
        virtual void ReceiveTransition(StillImage_ReceiveFSMContext& context, QueryStillImageData msg, Receive::Body::ReceiveRec transportData);
        virtual void ReceiveTransition(StillImage_ReceiveFSMContext& context, QueryStillImageSensorCapabilities msg, Receive::Body::ReceiveRec transportData);
        virtual void ReceiveTransition(StillImage_ReceiveFSMContext& context, QueryStillImageSensorConfiguration msg, Receive::Body::ReceiveRec transportData);
        virtual void ReceiveTransition(StillImage_ReceiveFSMContext& context, SetStillImageSensorConfiguration msg, Receive::Body::ReceiveRec transportData);
        virtual void ReleaseControlTransition(StillImage_ReceiveFSMContext& context);
        virtual void RequestControlTransition(StillImage_ReceiveFSMContext& context);
        virtual void SendTransition(StillImage_ReceiveFSMContext& context);
        virtual void SetAuthorityTransition(StillImage_ReceiveFSMContext& context);
        virtual void SetStillImageSensorConfigurationTransition(StillImage_ReceiveFSMContext& context);
        virtual void SetVisualSensorConfigurationTransition(StillImage_ReceiveFSMContext& context);
        virtual void TimeoutTransition(StillImage_ReceiveFSMContext& context);
        virtual void UpdateEventTransition(StillImage_ReceiveFSMContext& context);

    protected:

        virtual void Default(StillImage_ReceiveFSMContext& context);
    };

    class StillImage_ReceiveFSM_SM
    {
    public:

        static StillImage_ReceiveFSM_SM_Receiving_Ready_NotControlled Receiving_Ready_NotControlled;
        static StillImage_ReceiveFSM_SM_Receiving_Ready_Controlled Receiving_Ready_Controlled;
        static StillImage_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE;
    };

    class StillImage_ReceiveFSM_SM_Default :
        public StillImage_ReceiveFSMState
    {
    public:

        StillImage_ReceiveFSM_SM_Default(const char *name, int stateId)
        : StillImage_ReceiveFSMState(name, stateId)
        {};

    };

    class StillImage_ReceiveFSM_SM_Receiving_Ready_NotControlled :
        public StillImage_ReceiveFSM_SM_Default
    {
    public:
        StillImage_ReceiveFSM_SM_Receiving_Ready_NotControlled(const char *name, int stateId)
        : StillImage_ReceiveFSM_SM_Default(name, stateId)
        {};

        void InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_ControlledTransition(StillImage_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        void ReceiveTransition(StillImage_ReceiveFSMContext& context, QueryStillImageData msg, Receive::Body::ReceiveRec transportData);
        void ReceiveTransition(StillImage_ReceiveFSMContext& context, QueryStillImageSensorCapabilities msg, Receive::Body::ReceiveRec transportData);
        void ReceiveTransition(StillImage_ReceiveFSMContext& context, QueryStillImageSensorConfiguration msg, Receive::Body::ReceiveRec transportData);
    };

    class StillImage_ReceiveFSM_SM_Receiving_Ready_Controlled :
        public StillImage_ReceiveFSM_SM_Default
    {
    public:
        StillImage_ReceiveFSM_SM_Receiving_Ready_Controlled(const char *name, int stateId)
        : StillImage_ReceiveFSM_SM_Default(name, stateId)
        {};

        void InternalStateChange_To_StillImage_ReceiveFSM_ReceivingTransition(StillImage_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        void InternalStateChange_To_StillImage_ReceiveFSM_Receiving_ReadyTransition(StillImage_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        void InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_NotControlledTransition(StillImage_ReceiveFSMContext& context, JTS::InternalEvent* ie);
        void ReceiveTransition(StillImage_ReceiveFSMContext& context, QueryStillImageData msg, Receive::Body::ReceiveRec transportData);
        void ReceiveTransition(StillImage_ReceiveFSMContext& context, QueryStillImageSensorCapabilities msg, Receive::Body::ReceiveRec transportData);
        void ReceiveTransition(StillImage_ReceiveFSMContext& context, QueryStillImageSensorConfiguration msg, Receive::Body::ReceiveRec transportData);
        void ReceiveTransition(StillImage_ReceiveFSMContext& context, SetStillImageSensorConfiguration msg, Receive::Body::ReceiveRec transportData);
    };

    class StillImage_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE :
        public StillImage_ReceiveFSM_SM_Default
    {
    public:
        StillImage_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE(const char *name, int stateId)
        : StillImage_ReceiveFSM_SM_Default(name, stateId)
        {};

        void BroadcastGlobalTransition(StillImage_ReceiveFSMContext& context);
        void BroadcastLocalTransition(StillImage_ReceiveFSMContext& context);
        void CancelEventTransition(StillImage_ReceiveFSMContext& context);
        void CreateEventTransition(StillImage_ReceiveFSMContext& context);
        void EventErrorTransition(StillImage_ReceiveFSMContext& context);
        void EventOccurredTransition(StillImage_ReceiveFSMContext& context);
        void QueryAuthorityTransition(StillImage_ReceiveFSMContext& context);
        void QueryControlTransition(StillImage_ReceiveFSMContext& context);
        void QueryEventsTransition(StillImage_ReceiveFSMContext& context);
        void QuerySensorGeometricPropertiesTransition(StillImage_ReceiveFSMContext& context);
        void QueryStillImageDataTransition(StillImage_ReceiveFSMContext& context);
        void QueryStillImageSensorCapabilitiesTransition(StillImage_ReceiveFSMContext& context);
        void QueryStillImageSensorConfigurationTransition(StillImage_ReceiveFSMContext& context);
        void QueryTimeoutTransition(StillImage_ReceiveFSMContext& context);
        void QueryVisualSensorCapabilitiesTransition(StillImage_ReceiveFSMContext& context);
        void QueryVisualSensorConfigurationTransition(StillImage_ReceiveFSMContext& context);
        void ReceiveTransition(StillImage_ReceiveFSMContext& context);
        void ReleaseControlTransition(StillImage_ReceiveFSMContext& context);
        void RequestControlTransition(StillImage_ReceiveFSMContext& context);
        void SendTransition(StillImage_ReceiveFSMContext& context);
        void SetAuthorityTransition(StillImage_ReceiveFSMContext& context);
        void SetStillImageSensorConfigurationTransition(StillImage_ReceiveFSMContext& context);
        void SetVisualSensorConfigurationTransition(StillImage_ReceiveFSMContext& context);
        void TimeoutTransition(StillImage_ReceiveFSMContext& context);
        void UpdateEventTransition(StillImage_ReceiveFSMContext& context);
    };

    class StillImage_ReceiveFSMContext :
        public statemap::FSMContext
    {
    public:

        StillImage_ReceiveFSMContext(StillImage_ReceiveFSM& owner)
        : FSMContext(StillImage_ReceiveFSM_SM::Receiving_Ready_NotControlled),
          _owner(owner)
        {};

        StillImage_ReceiveFSMContext(StillImage_ReceiveFSM& owner, const statemap::State& state)
        : FSMContext(state),
          _owner(owner)
        {};

        virtual void enterStartState()
        {
            getState().Entry(*this);
            return;
        }

        StillImage_ReceiveFSM& getOwner() const
        {
            return (_owner);
        };

        StillImage_ReceiveFSMState& getState() const
        {
            if (_state == NULL)
            {
                throw statemap::StateUndefinedException();
            }

            return (dynamic_cast<StillImage_ReceiveFSMState&>(*_state));
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

        void InternalStateChange_To_StillImage_ReceiveFSM_ReceivingTransition(JTS::InternalEvent* ie)
        {
            setTransition("InternalStateChange_To_StillImage_ReceiveFSM_ReceivingTransition");
            (getState()).InternalStateChange_To_StillImage_ReceiveFSM_ReceivingTransition(*this, ie);
            setTransition(NULL);
        };

        void InternalStateChange_To_StillImage_ReceiveFSM_Receiving_ReadyTransition(JTS::InternalEvent* ie)
        {
            setTransition("InternalStateChange_To_StillImage_ReceiveFSM_Receiving_ReadyTransition");
            (getState()).InternalStateChange_To_StillImage_ReceiveFSM_Receiving_ReadyTransition(*this, ie);
            setTransition(NULL);
        };

        void InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_ControlledTransition(JTS::InternalEvent* ie)
        {
            setTransition("InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_ControlledTransition");
            (getState()).InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_ControlledTransition(*this, ie);
            setTransition(NULL);
        };

        void InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_NotControlledTransition(JTS::InternalEvent* ie)
        {
            setTransition("InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_NotControlledTransition");
            (getState()).InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_NotControlledTransition(*this, ie);
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

        void QuerySensorGeometricPropertiesTransition()
        {
            setTransition("QuerySensorGeometricPropertiesTransition");
            (getState()).QuerySensorGeometricPropertiesTransition(*this);
            setTransition(NULL);
        };

        void QueryStillImageDataTransition()
        {
            setTransition("QueryStillImageDataTransition");
            (getState()).QueryStillImageDataTransition(*this);
            setTransition(NULL);
        };

        void QueryStillImageSensorCapabilitiesTransition()
        {
            setTransition("QueryStillImageSensorCapabilitiesTransition");
            (getState()).QueryStillImageSensorCapabilitiesTransition(*this);
            setTransition(NULL);
        };

        void QueryStillImageSensorConfigurationTransition()
        {
            setTransition("QueryStillImageSensorConfigurationTransition");
            (getState()).QueryStillImageSensorConfigurationTransition(*this);
            setTransition(NULL);
        };

        void QueryTimeoutTransition()
        {
            setTransition("QueryTimeoutTransition");
            (getState()).QueryTimeoutTransition(*this);
            setTransition(NULL);
        };

        void QueryVisualSensorCapabilitiesTransition()
        {
            setTransition("QueryVisualSensorCapabilitiesTransition");
            (getState()).QueryVisualSensorCapabilitiesTransition(*this);
            setTransition(NULL);
        };

        void QueryVisualSensorConfigurationTransition()
        {
            setTransition("QueryVisualSensorConfigurationTransition");
            (getState()).QueryVisualSensorConfigurationTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition()
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition(QueryStillImageData msg, Receive::Body::ReceiveRec transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void ReceiveTransition(QueryStillImageSensorCapabilities msg, Receive::Body::ReceiveRec transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void ReceiveTransition(QueryStillImageSensorConfiguration msg, Receive::Body::ReceiveRec transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void ReceiveTransition(SetStillImageSensorConfiguration msg, Receive::Body::ReceiveRec transportData)
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

        void SetStillImageSensorConfigurationTransition()
        {
            setTransition("SetStillImageSensorConfigurationTransition");
            (getState()).SetStillImageSensorConfigurationTransition(*this);
            setTransition(NULL);
        };

        void SetVisualSensorConfigurationTransition()
        {
            setTransition("SetVisualSensorConfigurationTransition");
            (getState()).SetVisualSensorConfigurationTransition(*this);
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

        StillImage_ReceiveFSM& _owner;
    };
}


/*
 * Local variables:
 *  buffer-read-only: t
 * End:
 */

#endif // _H_STILLIMAGE_RECEIVEFSM_SM