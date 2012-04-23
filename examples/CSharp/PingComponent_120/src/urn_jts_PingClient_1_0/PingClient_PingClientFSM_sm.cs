using System;
using System.Diagnostics;

using JTS;
namespace urn_jts_PingClient_1_0
{
    public sealed class PingClient_PingClientFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public PingClient_PingClientFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((PingClient_PingClientFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public PingClient_PingClientFSM Owner
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

        public PingClient_PingClientFSMContext(PingClient_PingClientFSM owner) :
            base (PingClient_PingClientFSM_SM.Ready)
        {
        _owner = owner;
        }

        public override void EnterStartState()
        {
            State.Entry(this);
            return;
        }

        public void ReportHeartbeatPulseTransition()
        {
            transition_ = "ReportHeartbeatPulseTransition";
            State.ReportHeartbeatPulseTransition(this);
            transition_ = "";
            return;
        }

    //---------------------------------------------------------------
    // Member data.
    //

        [NonSerialized]
        private PingClient_PingClientFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class PingClient_PingClientFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal PingClient_PingClientFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(PingClient_PingClientFSMContext context)
            {}

            protected internal virtual void Exit(PingClient_PingClientFSMContext context)
            {}

            protected internal virtual void ReportHeartbeatPulseTransition(PingClient_PingClientFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(PingClient_PingClientFSMContext context)
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

        internal abstract class PingClient_PingClientFSM_SM
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
            internal static readonly PingClient_PingClientFSM_SM_Default.PingClient_PingClientFSM_SM_Ready Ready =
                new PingClient_PingClientFSM_SM_Default.PingClient_PingClientFSM_SM_Ready("PingClient_PingClientFSM_SM.Ready", 0);
            [NonSerialized]
            internal static readonly PingClient_PingClientFSM_SM_Default.PingClient_PingClientFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new PingClient_PingClientFSM_SM_Default.PingClient_PingClientFSM_SM_Internally_Generated_State_DO_NOT_USE("PingClient_PingClientFSM_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly PingClient_PingClientFSM_SM_Default Default =
                new PingClient_PingClientFSM_SM_Default("PingClient_PingClientFSM_SM.Default", -1);

        }

        internal class PingClient_PingClientFSM_SM_Default :
            PingClient_PingClientFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal PingClient_PingClientFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class PingClient_PingClientFSM_SM_Ready :
                PingClient_PingClientFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal PingClient_PingClientFSM_SM_Ready(string name, int id) :
                    base (name, id)
                {}

                protected internal override void ReportHeartbeatPulseTransition(PingClient_PingClientFSMContext context)
                {

                    PingClient_PingClientFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : PingClient_PingClientFSM_SM.Ready.ReportHeartbeatPulseTransition()");
#endif

                    PingClient_PingClientFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.printToScreenAction();
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }
            }

            internal class PingClient_PingClientFSM_SM_Internally_Generated_State_DO_NOT_USE :
                PingClient_PingClientFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal PingClient_PingClientFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void ReportHeartbeatPulseTransition(PingClient_PingClientFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : PingClient_PingClientFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportHeartbeatPulseTransition()");
#endif


                    return;
                }
            }
        }
    }

}
