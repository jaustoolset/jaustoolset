using System;
using System.Diagnostics;

using JTS;
namespace urn_jts_DiscoveryClient_1_0
{
    public sealed class DiscoveryClient_ReceiveFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public DiscoveryClient_ReceiveFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((DiscoveryClient_ReceiveFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public DiscoveryClient_ReceiveFSM Owner
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

        public DiscoveryClient_ReceiveFSMContext(DiscoveryClient_ReceiveFSM owner) :
            base (DiscoveryClient_ReceiveFSM_SM.Receiving_Ready)
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

        public void GrantNodeIDTransition()
        {
            transition_ = "GrantNodeIDTransition";
            State.GrantNodeIDTransition(this);
            transition_ = "";
            return;
        }

        public void RHP_TimeoutTransition()
        {
            transition_ = "RHP_TimeoutTransition";
            State.RHP_TimeoutTransition(this);
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

        public void ReceiveTransition(GrantNodeID msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(ReportHeartbeatPulse msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(ReportIdentification msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(ReportServices msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReportHeartbeatPulseTransition()
        {
            transition_ = "ReportHeartbeatPulseTransition";
            State.ReportHeartbeatPulseTransition(this);
            transition_ = "";
            return;
        }

        public void ReportIdentificationTransition()
        {
            transition_ = "ReportIdentificationTransition";
            State.ReportIdentificationTransition(this);
            transition_ = "";
            return;
        }

        public void ReportServicesTransition()
        {
            transition_ = "ReportServicesTransition";
            State.ReportServicesTransition(this);
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

    //---------------------------------------------------------------
    // Member data.
    //

        [NonSerialized]
        private DiscoveryClient_ReceiveFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class DiscoveryClient_ReceiveFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal DiscoveryClient_ReceiveFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(DiscoveryClient_ReceiveFSMContext context)
            {}

            protected internal virtual void Exit(DiscoveryClient_ReceiveFSMContext context)
            {}

            protected internal virtual void BroadcastGlobalTransition(DiscoveryClient_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void BroadcastLocalTransition(DiscoveryClient_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void GrantNodeIDTransition(DiscoveryClient_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void RHP_TimeoutTransition(DiscoveryClient_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(DiscoveryClient_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(DiscoveryClient_ReceiveFSMContext context, GrantNodeID msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(DiscoveryClient_ReceiveFSMContext context, ReportHeartbeatPulse msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(DiscoveryClient_ReceiveFSMContext context, ReportIdentification msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(DiscoveryClient_ReceiveFSMContext context, ReportServices msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReportHeartbeatPulseTransition(DiscoveryClient_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReportIdentificationTransition(DiscoveryClient_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReportServicesTransition(DiscoveryClient_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SendTransition(DiscoveryClient_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(DiscoveryClient_ReceiveFSMContext context)
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

        internal abstract class DiscoveryClient_ReceiveFSM_SM
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
            internal static readonly DiscoveryClient_ReceiveFSM_SM_Default.DiscoveryClient_ReceiveFSM_SM_Receiving_Ready Receiving_Ready =
                new DiscoveryClient_ReceiveFSM_SM_Default.DiscoveryClient_ReceiveFSM_SM_Receiving_Ready("DiscoveryClient_ReceiveFSM_SM.Receiving_Ready", 0);
            [NonSerialized]
            internal static readonly DiscoveryClient_ReceiveFSM_SM_Default.DiscoveryClient_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new DiscoveryClient_ReceiveFSM_SM_Default.DiscoveryClient_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE("DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly DiscoveryClient_ReceiveFSM_SM_Default Default =
                new DiscoveryClient_ReceiveFSM_SM_Default("DiscoveryClient_ReceiveFSM_SM.Default", -1);

        }

        internal class DiscoveryClient_ReceiveFSM_SM_Default :
            DiscoveryClient_ReceiveFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal DiscoveryClient_ReceiveFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class DiscoveryClient_ReceiveFSM_SM_Receiving_Ready :
                DiscoveryClient_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal DiscoveryClient_ReceiveFSM_SM_Receiving_Ready(string name, int id) :
                    base (name, id)
                {}

                protected internal override void RHP_TimeoutTransition(DiscoveryClient_ReceiveFSMContext context)
                {

                    DiscoveryClient_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Receiving_Ready");
#endif

                    DiscoveryClient_ReceiveFSMState endState = context.State;

#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Receiving_Ready.RHP_TimeoutTransition()");
#endif

                    context.ClearState();

                    try
                    {
                        ctxt.handleTimeoutAction();
                    }
                    finally
                    {
#if TRACE
                        Trace.WriteLine(
                            "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Receiving_Ready.RHP_TimeoutTransition()");
#endif

                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(DiscoveryClient_ReceiveFSMContext context, GrantNodeID msg, Receive.Body.ReceiveRec transportData)
                {

                    DiscoveryClient_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Receiving_Ready");
#endif

                    DiscoveryClient_ReceiveFSMState endState = context.State;

#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, GrantNodeID msg, Receive.Body.ReceiveRec transportData)");
#endif

                    context.ClearState();

                    try
                    {
                        ctxt.updateNodeIDAction(msg, transportData);
                    }
                    finally
                    {
#if TRACE
                        Trace.WriteLine(
                            "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, GrantNodeID msg, Receive.Body.ReceiveRec transportData)");
#endif

                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(DiscoveryClient_ReceiveFSMContext context, ReportHeartbeatPulse msg, Receive.Body.ReceiveRec transportData)
                {

                    DiscoveryClient_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Receiving_Ready");
#endif

                    if (ctxt.fromMasterModule( transportData ))
                    {
                        DiscoveryClient_ReceiveFSMState endState = context.State;

#if TRACE
                        Trace.WriteLine(
                            "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, ReportHeartbeatPulse msg, Receive.Body.ReceiveRec transportData)");
#endif

                        context.ClearState();

                        try
                        {
                            ctxt.updateSubsystemIDAction(transportData);
                            ctxt.registerServicesAction(transportData);
                        }
                        finally
                        {
#if TRACE
                            Trace.WriteLine(
                                "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, ReportHeartbeatPulse msg, Receive.Body.ReceiveRec transportData)");
#endif

                            context.State = endState;
                        }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(DiscoveryClient_ReceiveFSMContext context, ReportIdentification msg, Receive.Body.ReceiveRec transportData)
                {

                    DiscoveryClient_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Receiving_Ready");
#endif

                    DiscoveryClient_ReceiveFSMState endState = context.State;

#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, ReportIdentification msg, Receive.Body.ReceiveRec transportData)");
#endif

                    context.ClearState();

                    try
                    {
                        ctxt.handleMessageAction(msg, transportData);
                    }
                    finally
                    {
#if TRACE
                        Trace.WriteLine(
                            "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, ReportIdentification msg, Receive.Body.ReceiveRec transportData)");
#endif

                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(DiscoveryClient_ReceiveFSMContext context, ReportServices msg, Receive.Body.ReceiveRec transportData)
                {

                    DiscoveryClient_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Receiving_Ready");
#endif

                    DiscoveryClient_ReceiveFSMState endState = context.State;

#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, ReportServices msg, Receive.Body.ReceiveRec transportData)");
#endif

                    context.ClearState();

                    try
                    {
                        ctxt.parseServiceListAction(msg, transportData);
                    }
                    finally
                    {
#if TRACE
                        Trace.WriteLine(
                            "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Receiving_Ready.ReceiveTransition(, ReportServices msg, Receive.Body.ReceiveRec transportData)");
#endif

                        context.State = endState;
                    }

                    return;
                }
            }

            internal class DiscoveryClient_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE :
                DiscoveryClient_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal DiscoveryClient_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void BroadcastGlobalTransition(DiscoveryClient_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif

                    return;
                }

                protected internal override void BroadcastLocalTransition(DiscoveryClient_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif

                    return;
                }

                protected internal override void GrantNodeIDTransition(DiscoveryClient_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.GrantNodeIDTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.GrantNodeIDTransition()");
#endif

                    return;
                }

                protected internal override void RHP_TimeoutTransition(DiscoveryClient_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.RHP_TimeoutTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.RHP_TimeoutTransition()");
#endif

                    return;
                }

                protected internal override void ReceiveTransition(DiscoveryClient_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif

                    return;
                }

                protected internal override void ReportHeartbeatPulseTransition(DiscoveryClient_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportHeartbeatPulseTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportHeartbeatPulseTransition()");
#endif

                    return;
                }

                protected internal override void ReportIdentificationTransition(DiscoveryClient_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportIdentificationTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportIdentificationTransition()");
#endif

                    return;
                }

                protected internal override void ReportServicesTransition(DiscoveryClient_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportServicesTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportServicesTransition()");
#endif

                    return;
                }

                protected internal override void SendTransition(DiscoveryClient_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_ReceiveFSM_SM.DiscoveryClient_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif

                    return;
                }
            }
        }
    }

}
