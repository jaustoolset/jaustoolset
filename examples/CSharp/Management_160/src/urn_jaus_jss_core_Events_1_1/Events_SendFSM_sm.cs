using System;
using System.Diagnostics;

using JTS;
namespace urn_jaus_jss_core_Events_1_1
{
    public sealed class Events_SendFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public Events_SendFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((Events_SendFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public Events_SendFSM Owner
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

        public Events_SendFSMContext(Events_SendFSM owner) :
            base (Events_SendFSM_SM.Sending)
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

        public void ReceiveTransition()
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this);
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
        private Events_SendFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class Events_SendFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Events_SendFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(Events_SendFSMContext context)
            {}

            protected internal virtual void Exit(Events_SendFSMContext context)
            {}

            protected internal virtual void BroadcastGlobalTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void BroadcastLocalTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CancelEventTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandCompletedTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandExpiredTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateCommandEventTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateEventTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventErrorTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventOccurredTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventTimeoutTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventsTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SendTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void TimeoutTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void UpdateEventTransition(Events_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(Events_SendFSMContext context)
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

        internal abstract class Events_SendFSM_SM
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
            internal static readonly Events_SendFSM_SM_Default.Events_SendFSM_SM_Sending Sending =
                new Events_SendFSM_SM_Default.Events_SendFSM_SM_Sending("Events_SendFSM_SM.Sending", 0);
            [NonSerialized]
            internal static readonly Events_SendFSM_SM_Default.Events_SendFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new Events_SendFSM_SM_Default.Events_SendFSM_SM_Internally_Generated_State_DO_NOT_USE("Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly Events_SendFSM_SM_Default Default =
                new Events_SendFSM_SM_Default("Events_SendFSM_SM.Default", -1);

        }

        internal class Events_SendFSM_SM_Default :
            Events_SendFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Events_SendFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class Events_SendFSM_SM_Sending :
                Events_SendFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Events_SendFSM_SM_Sending(string name, int id) :
                    base (name, id)
                {}
            }

            internal class Events_SendFSM_SM_Internally_Generated_State_DO_NOT_USE :
                Events_SendFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Events_SendFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void BroadcastGlobalTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif


                    return;
                }

                protected internal override void BroadcastLocalTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif


                    return;
                }

                protected internal override void CancelEventTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CancelEventTransition()");
#endif


                    return;
                }

                protected internal override void CommandCompletedTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandCompletedTransition()");
#endif


                    return;
                }

                protected internal override void CommandExpiredTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandExpiredTransition()");
#endif


                    return;
                }

                protected internal override void CreateCommandEventTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateCommandEventTransition()");
#endif


                    return;
                }

                protected internal override void CreateEventTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateEventTransition()");
#endif


                    return;
                }

                protected internal override void EventErrorTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.EventErrorTransition()");
#endif


                    return;
                }

                protected internal override void EventOccurredTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.EventOccurredTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventTimeoutTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventTimeoutTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventsTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventsTransition()");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif


                    return;
                }

                protected internal override void SendTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif


                    return;
                }

                protected internal override void TimeoutTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.TimeoutTransition()");
#endif


                    return;
                }

                protected internal override void UpdateEventTransition(Events_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.UpdateEventTransition()");
#endif


                    return;
                }
            }
        }
    }

}
