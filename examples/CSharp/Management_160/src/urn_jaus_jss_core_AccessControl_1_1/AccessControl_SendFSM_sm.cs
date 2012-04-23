using System;
using System.Diagnostics;

using JTS;
namespace urn_jaus_jss_core_AccessControl_1_1
{
    public sealed class AccessControl_SendFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public AccessControl_SendFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((AccessControl_SendFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public AccessControl_SendFSM Owner
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

        public AccessControl_SendFSMContext(AccessControl_SendFSM owner) :
            base (AccessControl_SendFSM_SM.Sending)
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
        private AccessControl_SendFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class AccessControl_SendFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal AccessControl_SendFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(AccessControl_SendFSMContext context)
            {}

            protected internal virtual void Exit(AccessControl_SendFSMContext context)
            {}

            protected internal virtual void BroadcastGlobalTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void BroadcastLocalTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CancelEventTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandCompletedTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandExpiredTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateCommandEventTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateEventTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventErrorTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventOccurredTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryAuthorityTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryControlTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventTimeoutTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventsTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryTimeoutTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReleaseControlTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void RequestControlTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SendTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SetAuthorityTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void TimeoutTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void UpdateEventTransition(AccessControl_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(AccessControl_SendFSMContext context)
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

        internal abstract class AccessControl_SendFSM_SM
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
            internal static readonly AccessControl_SendFSM_SM_Default.AccessControl_SendFSM_SM_Sending Sending =
                new AccessControl_SendFSM_SM_Default.AccessControl_SendFSM_SM_Sending("AccessControl_SendFSM_SM.Sending", 0);
            [NonSerialized]
            internal static readonly AccessControl_SendFSM_SM_Default.AccessControl_SendFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new AccessControl_SendFSM_SM_Default.AccessControl_SendFSM_SM_Internally_Generated_State_DO_NOT_USE("AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly AccessControl_SendFSM_SM_Default Default =
                new AccessControl_SendFSM_SM_Default("AccessControl_SendFSM_SM.Default", -1);

        }

        internal class AccessControl_SendFSM_SM_Default :
            AccessControl_SendFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal AccessControl_SendFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class AccessControl_SendFSM_SM_Sending :
                AccessControl_SendFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AccessControl_SendFSM_SM_Sending(string name, int id) :
                    base (name, id)
                {}
            }

            internal class AccessControl_SendFSM_SM_Internally_Generated_State_DO_NOT_USE :
                AccessControl_SendFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AccessControl_SendFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void BroadcastGlobalTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif


                    return;
                }

                protected internal override void BroadcastLocalTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif


                    return;
                }

                protected internal override void CancelEventTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CancelEventTransition()");
#endif


                    return;
                }

                protected internal override void CommandCompletedTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandCompletedTransition()");
#endif


                    return;
                }

                protected internal override void CommandExpiredTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandExpiredTransition()");
#endif


                    return;
                }

                protected internal override void CreateCommandEventTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateCommandEventTransition()");
#endif


                    return;
                }

                protected internal override void CreateEventTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateEventTransition()");
#endif


                    return;
                }

                protected internal override void EventErrorTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.EventErrorTransition()");
#endif


                    return;
                }

                protected internal override void EventOccurredTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.EventOccurredTransition()");
#endif


                    return;
                }

                protected internal override void QueryAuthorityTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryAuthorityTransition()");
#endif


                    return;
                }

                protected internal override void QueryControlTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryControlTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventTimeoutTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventTimeoutTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventsTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventsTransition()");
#endif


                    return;
                }

                protected internal override void QueryTimeoutTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryTimeoutTransition()");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif


                    return;
                }

                protected internal override void ReleaseControlTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReleaseControlTransition()");
#endif


                    return;
                }

                protected internal override void RequestControlTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.RequestControlTransition()");
#endif


                    return;
                }

                protected internal override void SendTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif


                    return;
                }

                protected internal override void SetAuthorityTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.SetAuthorityTransition()");
#endif


                    return;
                }

                protected internal override void TimeoutTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.TimeoutTransition()");
#endif


                    return;
                }

                protected internal override void UpdateEventTransition(AccessControl_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AccessControl_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.UpdateEventTransition()");
#endif


                    return;
                }
            }
        }
    }

}
