#ifndef _H_SUBSYSTEMIDALLOCATOR_RECEIVEFSM_SM
#define _H_SUBSYSTEMIDALLOCATOR_RECEIVEFSM_SM

/*
 * ex: set ro:
 * DO NOT EDIT.
 * generated by smc (http://smc.sourceforge.net/)
 * from file : SubsystemIDAllocator_ReceiveFSM_sm.sm
 */


#define SMC_USES_IOSTREAMS

#include <statemap.h>

namespace urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4
{
    // Forward declarations.
    class SubsystemIDAllocator_ReceiveFSM_SM;
    class SubsystemIDAllocator_ReceiveFSM_SM_Receiving;
    class SubsystemIDAllocator_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE;
    class SubsystemIDAllocator_ReceiveFSM_SM_Default;
    class SubsystemIDAllocator_ReceiveFSMState;
    class SubsystemIDAllocator_ReceiveFSMContext;
    class SubsystemIDAllocator_ReceiveFSM;

    class SubsystemIDAllocator_ReceiveFSMState :
        public statemap::State
    {
    public:

        SubsystemIDAllocator_ReceiveFSMState(const char *name, int stateId)
        : statemap::State(name, stateId)
        {};

        virtual void Entry(SubsystemIDAllocator_ReceiveFSMContext&) {};
        virtual void Exit(SubsystemIDAllocator_ReceiveFSMContext&) {};

        virtual void BroadcastGlobalTransition(SubsystemIDAllocator_ReceiveFSMContext& context);
        virtual void BroadcastLocalTransition(SubsystemIDAllocator_ReceiveFSMContext& context);
        virtual void QuerySubsystemIDsTransition(SubsystemIDAllocator_ReceiveFSMContext& context);
        virtual void ReceiveTransition(SubsystemIDAllocator_ReceiveFSMContext& context);
        virtual void ReceiveTransition(SubsystemIDAllocator_ReceiveFSMContext& context, QuerySubsystemIDs& msg, Receive::Body::ReceiveRec& transportData);
        virtual void ReceiveTransition(SubsystemIDAllocator_ReceiveFSMContext& context, RequestSubsystemID& msg, Receive::Body::ReceiveRec& transportData);
        virtual void RequestSubsystemIDTransition(SubsystemIDAllocator_ReceiveFSMContext& context);
        virtual void SendTransition(SubsystemIDAllocator_ReceiveFSMContext& context);

    protected:

        virtual void Default(SubsystemIDAllocator_ReceiveFSMContext& context);
    };

    class SubsystemIDAllocator_ReceiveFSM_SM
    {
    public:

        static SubsystemIDAllocator_ReceiveFSM_SM_Receiving Receiving;
        static SubsystemIDAllocator_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE;
    };

    class SubsystemIDAllocator_ReceiveFSM_SM_Default :
        public SubsystemIDAllocator_ReceiveFSMState
    {
    public:

        SubsystemIDAllocator_ReceiveFSM_SM_Default(const char *name, int stateId)
        : SubsystemIDAllocator_ReceiveFSMState(name, stateId)
        {};

    };

    class SubsystemIDAllocator_ReceiveFSM_SM_Receiving :
        public SubsystemIDAllocator_ReceiveFSM_SM_Default
    {
    public:
        SubsystemIDAllocator_ReceiveFSM_SM_Receiving(const char *name, int stateId)
        : SubsystemIDAllocator_ReceiveFSM_SM_Default(name, stateId)
        {};

        void ReceiveTransition(SubsystemIDAllocator_ReceiveFSMContext& context, QuerySubsystemIDs& msg, Receive::Body::ReceiveRec& transportData);
        void ReceiveTransition(SubsystemIDAllocator_ReceiveFSMContext& context, RequestSubsystemID& msg, Receive::Body::ReceiveRec& transportData);
    };

    class SubsystemIDAllocator_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE :
        public SubsystemIDAllocator_ReceiveFSM_SM_Default
    {
    public:
        SubsystemIDAllocator_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE(const char *name, int stateId)
        : SubsystemIDAllocator_ReceiveFSM_SM_Default(name, stateId)
        {};

        void BroadcastGlobalTransition(SubsystemIDAllocator_ReceiveFSMContext& context);
        void BroadcastLocalTransition(SubsystemIDAllocator_ReceiveFSMContext& context);
        void QuerySubsystemIDsTransition(SubsystemIDAllocator_ReceiveFSMContext& context);
        void ReceiveTransition(SubsystemIDAllocator_ReceiveFSMContext& context);
        void RequestSubsystemIDTransition(SubsystemIDAllocator_ReceiveFSMContext& context);
        void SendTransition(SubsystemIDAllocator_ReceiveFSMContext& context);
    };

    class SubsystemIDAllocator_ReceiveFSMContext :
        public statemap::FSMContext
    {
    public:

        SubsystemIDAllocator_ReceiveFSMContext(SubsystemIDAllocator_ReceiveFSM& owner)
        : FSMContext(SubsystemIDAllocator_ReceiveFSM_SM::Receiving),
          _owner(owner)
        {};

        SubsystemIDAllocator_ReceiveFSMContext(SubsystemIDAllocator_ReceiveFSM& owner, const statemap::State& state)
        : FSMContext(state),
          _owner(owner)
        {};

        virtual void enterStartState()
        {
            getState().Entry(*this);
            return;
        }

        SubsystemIDAllocator_ReceiveFSM& getOwner() const
        {
            return (_owner);
        };

        SubsystemIDAllocator_ReceiveFSMState& getState() const
        {
            if (_state == NULL)
            {
                throw statemap::StateUndefinedException();
            }

            return (dynamic_cast<SubsystemIDAllocator_ReceiveFSMState&>(*_state));
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

        void QuerySubsystemIDsTransition()
        {
            setTransition("QuerySubsystemIDsTransition");
            (getState()).QuerySubsystemIDsTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition()
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this);
            setTransition(NULL);
        };

        void ReceiveTransition(QuerySubsystemIDs& msg, Receive::Body::ReceiveRec& transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void ReceiveTransition(RequestSubsystemID& msg, Receive::Body::ReceiveRec& transportData)
        {
            setTransition("ReceiveTransition");
            (getState()).ReceiveTransition(*this, msg, transportData);
            setTransition(NULL);
        };

        void RequestSubsystemIDTransition()
        {
            setTransition("RequestSubsystemIDTransition");
            (getState()).RequestSubsystemIDTransition(*this);
            setTransition(NULL);
        };

        void SendTransition()
        {
            setTransition("SendTransition");
            (getState()).SendTransition(*this);
            setTransition(NULL);
        };

    private:

        SubsystemIDAllocator_ReceiveFSM& _owner;
    };
}


/*
 * Local variables:
 *  buffer-read-only: t
 * End:
 */

#endif // _H_SUBSYSTEMIDALLOCATOR_RECEIVEFSM_SM
