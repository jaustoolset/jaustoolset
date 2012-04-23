using System;
using System.Diagnostics;

using JTS;
namespace urn_jaus_jss_core_AccessControl_1_1
{
    public sealed class AccessControl_ReceiveFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public AccessControl_ReceiveFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((AccessControl_ReceiveFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public AccessControl_ReceiveFSM Owner
        {
            get
            {
                return (_owner);
            }
            set
            {
                _owner = value;
            }
        }

    //---------------------------------------------------------------
    // Member methods.
    //

        public AccessControl_ReceiveFSMContext(AccessControl_ReceiveFSM owner) :
            base (AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available)
        {
        _owner = owner;
        }

        public override void EnterStartState()
        {
            State.Entry(this);
            return;
        }

        public void BroadcastGlobalTransition()
        {
            transition_ = "BroadcastGlobalTransition";
            State.BroadcastGlobalTransition(this);
            transition_ = "";
            return;
        }

        public void BroadcastLocalTransition()
        {
            transition_ = "BroadcastLocalTransition";
            State.BroadcastLocalTransition(this);
            transition_ = "";
            return;
        }

        public void CancelEventTransition()
        {
            transition_ = "CancelEventTransition";
            State.CancelEventTransition(this);
            transition_ = "";
            return;
        }

        public void CommandCompletedTransition()
        {
            transition_ = "CommandCompletedTransition";
            State.CommandCompletedTransition(this);
            transition_ = "";
            return;
        }

        public void CommandExpiredTransition()
        {
            transition_ = "CommandExpiredTransition";
            State.CommandExpiredTransition(this);
            transition_ = "";
            return;
        }

        public void CreateCommandEventTransition()
        {
            transition_ = "CreateCommandEventTransition";
            State.CreateCommandEventTransition(this);
            transition_ = "";
            return;
        }

        public void CreateEventTransition()
        {
            transition_ = "CreateEventTransition";
            State.CreateEventTransition(this);
            transition_ = "";
            return;
        }

        public void EventErrorTransition()
        {
            transition_ = "EventErrorTransition";
            State.EventErrorTransition(this);
            transition_ = "";
            return;
        }

        public void EventOccurredTransition()
        {
            transition_ = "EventOccurredTransition";
            State.EventOccurredTransition(this);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition";
            State.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition";
            State.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition";
            State.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition";
            State.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition";
            State.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition";
            State.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition";
            State.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition";
            State.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(this, ie);
            transition_ = "";
            return;
        }

        public void QueryAuthorityTransition()
        {
            transition_ = "QueryAuthorityTransition";
            State.QueryAuthorityTransition(this);
            transition_ = "";
            return;
        }

        public void QueryControlTransition()
        {
            transition_ = "QueryControlTransition";
            State.QueryControlTransition(this);
            transition_ = "";
            return;
        }

        public void QueryEventTimeoutTransition()
        {
            transition_ = "QueryEventTimeoutTransition";
            State.QueryEventTimeoutTransition(this);
            transition_ = "";
            return;
        }

        public void QueryEventsTransition()
        {
            transition_ = "QueryEventsTransition";
            State.QueryEventsTransition(this);
            transition_ = "";
            return;
        }

        public void QueryTimeoutTransition()
        {
            transition_ = "QueryTimeoutTransition";
            State.QueryTimeoutTransition(this);
            transition_ = "";
            return;
        }

        public void ReceiveTransition()
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(QueryAuthority msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(QueryControl msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(QueryTimeout msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(ReleaseControl msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(RequestControl msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(SetAuthority msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReleaseControlTransition()
        {
            transition_ = "ReleaseControlTransition";
            State.ReleaseControlTransition(this);
            transition_ = "";
            return;
        }

        public void RequestControlTransition()
        {
            transition_ = "RequestControlTransition";
            State.RequestControlTransition(this);
            transition_ = "";
            return;
        }

        public void SendTransition()
        {
            transition_ = "SendTransition";
            State.SendTransition(this);
            transition_ = "";
            return;
        }

        public void SetAuthorityTransition()
        {
            transition_ = "SetAuthorityTransition";
            State.SetAuthorityTransition(this);
            transition_ = "";
            return;
        }

        public void TimeoutTransition()
        {
            transition_ = "TimeoutTransition";
            State.TimeoutTransition(this);
            transition_ = "";
            return;
        }

        public void UpdateEventTransition()
        {
            transition_ = "UpdateEventTransition";
            State.UpdateEventTransition(this);
            transition_ = "";
            return;
        }

    //---------------------------------------------------------------
    // Member data.
    //

        [NonSerialized]
        private AccessControl_ReceiveFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class AccessControl_ReceiveFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal AccessControl_ReceiveFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(AccessControl_ReceiveFSMContext context)
            {}

            protected internal virtual void Exit(AccessControl_ReceiveFSMContext context)
            {}

            protected internal virtual void BroadcastGlobalTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void BroadcastLocalTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CancelEventTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandCompletedTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandExpiredTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateCommandEventTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateEventTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventErrorTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventOccurredTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void QueryAuthorityTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryControlTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventTimeoutTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventsTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryTimeoutTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryAuthority msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryControl msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryTimeout msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(AccessControl_ReceiveFSMContext context, ReleaseControl msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(AccessControl_ReceiveFSMContext context, RequestControl msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(AccessControl_ReceiveFSMContext context, SetAuthority msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReleaseControlTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void RequestControlTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SendTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SetAuthorityTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void TimeoutTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void UpdateEventTransition(AccessControl_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(AccessControl_ReceiveFSMContext context)
            {
#if TRACE
                Trace.WriteLine(
                    "TRANSITION : Default");
#endif
                throw (
                    new statemap.TransitionUndefinedException(
                        "State: " +
                        context.State.Name +
                        ", Transition: " +
                        context.GetTransition()));
            }
        }

        internal abstract class AccessControl_ReceiveFSM_SM
        {
        //-----------------------------------------------------------
        // Member methods.
        //

        //-----------------------------------------------------------
        // Member data.
        //

            //-------------------------------------------------------
            // Statics.
            //
            [NonSerialized]
            internal static readonly AccessControl_ReceiveFSM_SM_Default.AccessControl_ReceiveFSM_SM_Receiving_Ready_NotControlled_Available Receiving_Ready_NotControlled_Available =
                new AccessControl_ReceiveFSM_SM_Default.AccessControl_ReceiveFSM_SM_Receiving_Ready_NotControlled_Available("AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available", 0);
            [NonSerialized]
            internal static readonly AccessControl_ReceiveFSM_SM_Default.AccessControl_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable Receiving_Ready_NotControlled_NotAvailable =
                new AccessControl_ReceiveFSM_SM_Default.AccessControl_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable("AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable", 1);
            [NonSerialized]
            internal static readonly AccessControl_ReceiveFSM_SM_Default.AccessControl_ReceiveFSM_SM_Receiving_Ready_Controlled_Available Receiving_Ready_Controlled_Available =
                new AccessControl_ReceiveFSM_SM_Default.AccessControl_ReceiveFSM_SM_Receiving_Ready_Controlled_Available("AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available", 2);
            [NonSerialized]
            internal static readonly AccessControl_ReceiveFSM_SM_Default.AccessControl_ReceiveFSM_SM_Receiving_Ready_Controlled_NotAvailable Receiving_Ready_Controlled_NotAvailable =
                new AccessControl_ReceiveFSM_SM_Default.AccessControl_ReceiveFSM_SM_Receiving_Ready_Controlled_NotAvailable("AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable", 3);
            [NonSerialized]
            internal static readonly AccessControl_ReceiveFSM_SM_Default.AccessControl_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new AccessControl_ReceiveFSM_SM_Default.AccessControl_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE("AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE", 4);
            [NonSerialized]
            private static readonly AccessControl_ReceiveFSM_SM_Default Default =
                new AccessControl_ReceiveFSM_SM_Default("AccessControl_ReceiveFSM_SM.Default", -1);

        }

        internal class AccessControl_ReceiveFSM_SM_Default :
            AccessControl_ReceiveFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal AccessControl_ReceiveFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class AccessControl_ReceiveFSM_SM_Receiving_Ready_NotControlled_Available :
                AccessControl_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AccessControl_ReceiveFSM_SM_Receiving_Ready_NotControlled_Available(string name, int id) :
                    base (name, id)
                {}

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryAuthority msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available.ReceiveTransition(, QueryAuthority msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportAuthority", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available.ReceiveTransition(, QueryControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportControl", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryTimeout msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available.ReceiveTransition(, QueryTimeout msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportTimeout", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, ReleaseControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available.ReceiveTransition(, ReleaseControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("RejectControl", "CONTROL_RELEASED", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, RequestControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available.ReceiveTransition(, RequestControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isDefaultAuthorityGreater(msg))
                    {
                        AccessControl_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("ConfirmControl", "INSUFFICIENT_AUTHORITY", transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    }
                    else if (! ctxt.isDefaultAuthorityGreater(msg))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.StoreAddressAction(transportData);
                            ctxt.SetAuthorityAction(msg);
                            ctxt.ResetTimerAction();
                            ctxt.SendAction("ConfirmControl", "CONTROL_ACCEPTED", transportData);
                            ctxt.processNotifications("Receiving_Ready_Controlled_Available", null);
                        }
                        finally
                        {
                            context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available;
                            context.State.Entry(context);
                        }

                    }                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }
            }

            internal class AccessControl_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable :
                AccessControl_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AccessControl_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable(string name, int id) :
                    base (name, id)
                {}

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryAuthority msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.ReceiveTransition(, QueryAuthority msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportAuthority", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.ReceiveTransition(, QueryControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportControl", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryTimeout msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.ReceiveTransition(, QueryTimeout msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportTimeout", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, ReleaseControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.ReceiveTransition(, ReleaseControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("RejectControl", "CONTROL_RELEASED", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, RequestControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable.ReceiveTransition(, RequestControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ConfirmControl", "NOT_AVAILABLE", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }
            }

            internal class AccessControl_ReceiveFSM_SM_Receiving_Ready_Controlled_Available :
                AccessControl_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AccessControl_ReceiveFSM_SM_Receiving_Ready_Controlled_Available(string name, int id) :
                    base (name, id)
                {}

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryAuthority msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.ReceiveTransition(, QueryAuthority msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportAuthority", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.ReceiveTransition(, QueryControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportControl", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryTimeout msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.ReceiveTransition(, QueryTimeout msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportTimeout", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, ReleaseControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.ReceiveTransition(, ReleaseControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isControllingClient( transportData ))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectControl", "CONTROL_RELEASED", transportData);
                            ctxt.initAction();
                            ctxt.processNotifications("Receiving_Ready_NotControlled_Available", null);
                        }
                        finally
                        {
                            context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                            context.State.Entry(context);
                        }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, RequestControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.ReceiveTransition(, RequestControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isCurrentAuthorityLess(msg) && ! ctxt.isControllingClient( transportData ))
                    {
                        AccessControl_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectControl", "CONTROL_RELEASED", transportData);
                            ctxt.StoreAddressAction(transportData);
                            ctxt.SetAuthorityAction(msg);
                            ctxt.ResetTimerAction();
                            ctxt.SendAction("ConfirmControl", "CONTROL_ACCEPTED", transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    }
                    else if (! ctxt.isCurrentAuthorityLess(msg) && ! ctxt.isControllingClient( transportData ))
                    {
                        AccessControl_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("ConfirmControl", "INSUFFICIENT_AUTHORITY", transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    }
                    else if (! ctxt.isDefaultAuthorityGreater(msg) && ctxt.isControllingClient( transportData ))
                    {
                        AccessControl_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.ResetTimerAction();
                            ctxt.SetAuthorityAction(msg);
                            ctxt.SendAction("ConfirmControl", "CONTROL_ACCEPTED", transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    }
                    else if (ctxt.isDefaultAuthorityGreater(msg) && ctxt.isControllingClient(transportData))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectControl", "CONTROL_RELEASED", transportData);
                            ctxt.initAction();
                            ctxt.processNotifications("Receiving_Ready_NotControlled_Available", null);
                        }
                        finally
                        {
                            context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                            context.State.Entry(context);
                        }

                    }                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, SetAuthority msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.ReceiveTransition(, SetAuthority msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isAuthorityValid(msg) && ctxt.isControllingClient( transportData ))
                    {
                        AccessControl_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.SetAuthorityAction(msg);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void TimeoutTransition(AccessControl_ReceiveFSMContext context)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available.TimeoutTransition()");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("RejectControl", "CONTROL_RELEASED");
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", null);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }
            }

            internal class AccessControl_ReceiveFSM_SM_Receiving_Ready_Controlled_NotAvailable :
                AccessControl_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AccessControl_ReceiveFSM_SM_Receiving_Ready_Controlled_NotAvailable(string name, int id) :
                    base (name, id)
                {}

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(AccessControl_ReceiveFSMContext context, InternalEvent ie)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable", ie);
                    }
                    finally
                    {
                        context.State = AccessControl_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryAuthority msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.ReceiveTransition(, QueryAuthority msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportAuthority", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.ReceiveTransition(, QueryControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportControl", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, QueryTimeout msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.ReceiveTransition(, QueryTimeout msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportTimeout", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, ReleaseControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.ReceiveTransition(, ReleaseControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("RejectControl", "NOT_AVAILABLE", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, RequestControl msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.ReceiveTransition(, RequestControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ConfirmControl", "NOT_AVAILABLE", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context, SetAuthority msg, Receive.Body.ReceiveRec transportData)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.ReceiveTransition(, SetAuthority msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isAuthorityValid(msg) && ctxt.isControllingClient( transportData ))
                    {
                        AccessControl_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.SetAuthorityAction(msg);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void TimeoutTransition(AccessControl_ReceiveFSMContext context)
                {

                    AccessControl_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable.TimeoutTransition()");
#endif

                    AccessControl_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.resetTimerAction();
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }
            }

            internal class AccessControl_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE :
                AccessControl_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AccessControl_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void BroadcastGlobalTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif


                    return;
                }

                protected internal override void BroadcastLocalTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif


                    return;
                }

                protected internal override void CancelEventTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CancelEventTransition()");
#endif


                    return;
                }

                protected internal override void CommandCompletedTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandCompletedTransition()");
#endif


                    return;
                }

                protected internal override void CommandExpiredTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandExpiredTransition()");
#endif


                    return;
                }

                protected internal override void CreateCommandEventTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateCommandEventTransition()");
#endif


                    return;
                }

                protected internal override void CreateEventTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateEventTransition()");
#endif


                    return;
                }

                protected internal override void EventErrorTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.EventErrorTransition()");
#endif


                    return;
                }

                protected internal override void EventOccurredTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.EventOccurredTransition()");
#endif


                    return;
                }

                protected internal override void QueryAuthorityTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryAuthorityTransition()");
#endif


                    return;
                }

                protected internal override void QueryControlTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryControlTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventTimeoutTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventTimeoutTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventsTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventsTransition()");
#endif


                    return;
                }

                protected internal override void QueryTimeoutTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryTimeoutTransition()");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif


                    return;
                }

                protected internal override void ReleaseControlTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReleaseControlTransition()");
#endif


                    return;
                }

                protected internal override void RequestControlTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.RequestControlTransition()");
#endif


                    return;
                }

                protected internal override void SendTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif


                    return;
                }

                protected internal override void SetAuthorityTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.SetAuthorityTransition()");
#endif


                    return;
                }

                protected internal override void TimeoutTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.TimeoutTransition()");
#endif


                    return;
                }

                protected internal override void UpdateEventTransition(AccessControl_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.UpdateEventTransition()");
#endif


                    return;
                }
            }
        }
    }

}
