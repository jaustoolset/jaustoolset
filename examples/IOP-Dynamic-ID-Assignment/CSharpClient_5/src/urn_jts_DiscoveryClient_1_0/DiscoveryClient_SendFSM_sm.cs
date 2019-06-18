using System;
using System.Diagnostics;

using JTS;
namespace urn_jts_DiscoveryClient_1_0
{
    public sealed class DiscoveryClient_SendFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public DiscoveryClient_SendFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((DiscoveryClient_SendFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public DiscoveryClient_SendFSM Owner
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

        public DiscoveryClient_SendFSMContext(DiscoveryClient_SendFSM owner) :
            base (DiscoveryClient_SendFSM_SM.Sending)
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
        private DiscoveryClient_SendFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class DiscoveryClient_SendFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal DiscoveryClient_SendFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(DiscoveryClient_SendFSMContext context)
            {}

            protected internal virtual void Exit(DiscoveryClient_SendFSMContext context)
            {}

            protected internal virtual void BroadcastGlobalTransition(DiscoveryClient_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void BroadcastLocalTransition(DiscoveryClient_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void GrantNodeIDTransition(DiscoveryClient_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void RHP_TimeoutTransition(DiscoveryClient_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(DiscoveryClient_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReportHeartbeatPulseTransition(DiscoveryClient_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReportIdentificationTransition(DiscoveryClient_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReportServicesTransition(DiscoveryClient_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SendTransition(DiscoveryClient_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(DiscoveryClient_SendFSMContext context)
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

        internal abstract class DiscoveryClient_SendFSM_SM
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
            internal static readonly DiscoveryClient_SendFSM_SM_Default.DiscoveryClient_SendFSM_SM_Sending Sending =
                new DiscoveryClient_SendFSM_SM_Default.DiscoveryClient_SendFSM_SM_Sending("DiscoveryClient_SendFSM_SM.Sending", 0);
            [NonSerialized]
            internal static readonly DiscoveryClient_SendFSM_SM_Default.DiscoveryClient_SendFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new DiscoveryClient_SendFSM_SM_Default.DiscoveryClient_SendFSM_SM_Internally_Generated_State_DO_NOT_USE("DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly DiscoveryClient_SendFSM_SM_Default Default =
                new DiscoveryClient_SendFSM_SM_Default("DiscoveryClient_SendFSM_SM.Default", -1);

        }

        internal class DiscoveryClient_SendFSM_SM_Default :
            DiscoveryClient_SendFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal DiscoveryClient_SendFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class DiscoveryClient_SendFSM_SM_Sending :
                DiscoveryClient_SendFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal DiscoveryClient_SendFSM_SM_Sending(string name, int id) :
                    base (name, id)
                {}
            }

            internal class DiscoveryClient_SendFSM_SM_Internally_Generated_State_DO_NOT_USE :
                DiscoveryClient_SendFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal DiscoveryClient_SendFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void BroadcastGlobalTransition(DiscoveryClient_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif

                    return;
                }

                protected internal override void BroadcastLocalTransition(DiscoveryClient_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif

                    return;
                }

                protected internal override void GrantNodeIDTransition(DiscoveryClient_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.GrantNodeIDTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.GrantNodeIDTransition()");
#endif

                    return;
                }

                protected internal override void RHP_TimeoutTransition(DiscoveryClient_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.RHP_TimeoutTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.RHP_TimeoutTransition()");
#endif

                    return;
                }

                protected internal override void ReceiveTransition(DiscoveryClient_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif

                    return;
                }

                protected internal override void ReportHeartbeatPulseTransition(DiscoveryClient_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportHeartbeatPulseTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportHeartbeatPulseTransition()");
#endif

                    return;
                }

                protected internal override void ReportIdentificationTransition(DiscoveryClient_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportIdentificationTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportIdentificationTransition()");
#endif

                    return;
                }

                protected internal override void ReportServicesTransition(DiscoveryClient_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportServicesTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportServicesTransition()");
#endif

                    return;
                }

                protected internal override void SendTransition(DiscoveryClient_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "LEAVING STATE   : DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE");
#endif


#if TRACE
                    Trace.WriteLine(
                        "ENTER TRANSITION: DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif

#if TRACE
                    Trace.WriteLine(
                        "EXIT TRANSITION : DiscoveryClient_SendFSM_SM.DiscoveryClient_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif

                    return;
                }
            }
        }
    }

}
