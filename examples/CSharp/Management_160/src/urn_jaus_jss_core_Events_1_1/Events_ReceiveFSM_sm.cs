using System;
using System.Diagnostics;

using JTS;
namespace urn_jaus_jss_core_Events_1_1
{
    public sealed class Events_ReceiveFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public Events_ReceiveFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((Events_ReceiveFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public Events_ReceiveFSM Owner
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

        public Events_ReceiveFSMContext(Events_ReceiveFSM owner) :
            base (Events_ReceiveFSM_SM.Receiving_Ready)
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

        public void ReceiveTransition(CancelEvent msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(CreateEvent msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(QueryEventTimeout msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(QueryEvents msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(UpdateEvent msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
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
        private Events_ReceiveFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class Events_ReceiveFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Events_ReceiveFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(Events_ReceiveFSMContext context)
            {}

            protected internal virtual void Exit(Events_ReceiveFSMContext context)
            {}

            protected internal virtual void BroadcastGlobalTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void BroadcastLocalTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CancelEventTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandCompletedTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandExpiredTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateCommandEventTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateEventTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventErrorTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventOccurredTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventTimeoutTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventsTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Events_ReceiveFSMContext context, CancelEvent msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Events_ReceiveFSMContext context, CreateEvent msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Events_ReceiveFSMContext context, QueryEventTimeout msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Events_ReceiveFSMContext context, QueryEvents msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Events_ReceiveFSMContext context, UpdateEvent msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void SendTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void TimeoutTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void UpdateEventTransition(Events_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(Events_ReceiveFSMContext context)
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

        internal abstract class Events_ReceiveFSM_SM
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
            internal static readonly Events_ReceiveFSM_SM_Default.Events_ReceiveFSM_SM_Receiving_Ready Receiving_Ready =
                new Events_ReceiveFSM_SM_Default.Events_ReceiveFSM_SM_Receiving_Ready("Events_ReceiveFSM_SM.Receiving_Ready", 0);
            [NonSerialized]
            internal static readonly Events_ReceiveFSM_SM_Default.Events_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new Events_ReceiveFSM_SM_Default.Events_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE("Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly Events_ReceiveFSM_SM_Default Default =
                new Events_ReceiveFSM_SM_Default("Events_ReceiveFSM_SM.Default", -1);

        }

        internal class Events_ReceiveFSM_SM_Default :
            Events_ReceiveFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Events_ReceiveFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class Events_ReceiveFSM_SM_Receiving_Ready :
                Events_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Events_ReceiveFSM_SM_Receiving_Ready(string name, int id) :
                    base (name, id)
                {}

                protected internal override void EventErrorTransition(Events_ReceiveFSMContext context)
                {

                    Events_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Receiving_Ready.EventErrorTransition()");
#endif

                    if (ctxt.eventExists())
                    {
                        Events_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.sendRejectEventRequestAction();
                        }
                        finally
                        {
                            context.State = endState;
                        }

                }
                    else                    {
                        base.EventErrorTransition(context);
                    }

                    return;
                }

                protected internal override void EventOccurredTransition(Events_ReceiveFSMContext context)
                {

                    Events_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Receiving_Ready.EventOccurredTransition()");
#endif

                    if (ctxt.eventExists())
                    {
                        Events_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.sendEventAction();
                        }
                        finally
                        {
                            context.State = endState;
                        }

                }
                    else                    {
                        base.EventOccurredTransition(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Events_ReceiveFSMContext context, CancelEvent msg, Receive.Body.ReceiveRec transportData)
                {

                    Events_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, CancelEvent msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (! ctxt.eventExists(msg))
                    {
                        Events_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectEventRequest", msg, transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    }
                    else if (ctxt.eventExists(msg))
                    {
                        Events_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.cancelEventAction(msg);
                            ctxt.SendAction("ConfirmEventRequest", msg, transportData);
                            ctxt.stopEventTimerAction();
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    }                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Events_ReceiveFSMContext context, CreateEvent msg, Receive.Body.ReceiveRec transportData)
                {

                    Events_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, CreateEvent msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isSupported(msg) && ! ctxt.eventExists(msg))
                    {
                        Events_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.createEventAction(msg);
                            ctxt.SendAction("ConfirmEventRequest", msg, transportData);
                            ctxt.resetEventTimerAction();
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    }
                    else if (ctxt.isSupported(msg) && ctxt.eventExists(msg))
                    {
                        Events_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.updateEventAction(msg);
                            ctxt.SendAction("ConfirmEventRequest", msg, transportData);
                            ctxt.resetEventTimerAction();
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    }
                    else if (! ctxt.isSupported(msg))
                    {
                        Events_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectEventRequest", msg, transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    }                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Events_ReceiveFSMContext context, QueryEventTimeout msg, Receive.Body.ReceiveRec transportData)
                {

                    Events_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, QueryEventTimeout msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Events_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportEventTimeout", msg, transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Events_ReceiveFSMContext context, QueryEvents msg, Receive.Body.ReceiveRec transportData)
                {

                    Events_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, QueryEvents msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Events_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportEvents", msg, transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Events_ReceiveFSMContext context, UpdateEvent msg, Receive.Body.ReceiveRec transportData)
                {

                    Events_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, UpdateEvent msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isSupported(msg) && ctxt.eventExists(msg))
                    {
                        Events_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.updateEventAction(msg);
                            ctxt.SendAction("ConfirmEventRequest", msg, transportData);
                            ctxt.resetEventTimerAction();
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    }
                    else if (! ctxt.isSupported(msg) || ! ctxt.eventExists(msg))
                    {
                        Events_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectEventRequest", msg, transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    }                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void TimeoutTransition(Events_ReceiveFSMContext context)
                {

                    Events_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Receiving_Ready.TimeoutTransition()");
#endif

                    Events_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.cancelEventAction();
                        ctxt.SendAction("ConfirmEventRequest");
                        ctxt.stopEventTimerAction();
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }
            }

            internal class Events_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE :
                Events_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Events_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void BroadcastGlobalTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif


                    return;
                }

                protected internal override void BroadcastLocalTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif


                    return;
                }

                protected internal override void CancelEventTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CancelEventTransition()");
#endif


                    return;
                }

                protected internal override void CommandCompletedTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandCompletedTransition()");
#endif


                    return;
                }

                protected internal override void CommandExpiredTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandExpiredTransition()");
#endif


                    return;
                }

                protected internal override void CreateCommandEventTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateCommandEventTransition()");
#endif


                    return;
                }

                protected internal override void CreateEventTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateEventTransition()");
#endif


                    return;
                }

                protected internal override void EventErrorTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.EventErrorTransition()");
#endif


                    return;
                }

                protected internal override void EventOccurredTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.EventOccurredTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventTimeoutTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventTimeoutTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventsTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventsTransition()");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif


                    return;
                }

                protected internal override void SendTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif


                    return;
                }

                protected internal override void TimeoutTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.TimeoutTransition()");
#endif


                    return;
                }

                protected internal override void UpdateEventTransition(Events_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Events_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.UpdateEventTransition()");
#endif


                    return;
                }
            }
        }
    }

}
