using System;
using System.Diagnostics;

using JTS;
namespace urn_jts_PingServer_1_0
{
    public sealed class PingServer_PingFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public PingServer_PingFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((PingServer_PingFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public PingServer_PingFSM Owner
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

        public PingServer_PingFSMContext(PingServer_PingFSM owner) :
            base (PingServer_PingFSM_SM.Ready)
        {
        _owner = owner;
        }

        public override void EnterStartState()
        {
            State.Entry(this);
            return;
        }

        public void QueryHeartbeatPulseTransition()
        {
            transition_ = "QueryHeartbeatPulseTransition";
            State.QueryHeartbeatPulseTransition(this);
            transition_ = "";
            return;
        }

    //---------------------------------------------------------------
    // Member data.
    //

        [NonSerialized]
        private PingServer_PingFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class PingServer_PingFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal PingServer_PingFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(PingServer_PingFSMContext context)
            {}

            protected internal virtual void Exit(PingServer_PingFSMContext context)
            {}

            protected internal virtual void QueryHeartbeatPulseTransition(PingServer_PingFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(PingServer_PingFSMContext context)
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

        internal abstract class PingServer_PingFSM_SM
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
            internal static readonly PingServer_PingFSM_SM_Default.PingServer_PingFSM_SM_Ready Ready =
                new PingServer_PingFSM_SM_Default.PingServer_PingFSM_SM_Ready("PingServer_PingFSM_SM.Ready", 0);
            [NonSerialized]
            internal static readonly PingServer_PingFSM_SM_Default.PingServer_PingFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new PingServer_PingFSM_SM_Default.PingServer_PingFSM_SM_Internally_Generated_State_DO_NOT_USE("PingServer_PingFSM_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly PingServer_PingFSM_SM_Default Default =
                new PingServer_PingFSM_SM_Default("PingServer_PingFSM_SM.Default", -1);

        }

        internal class PingServer_PingFSM_SM_Default :
            PingServer_PingFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal PingServer_PingFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class PingServer_PingFSM_SM_Ready :
                PingServer_PingFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal PingServer_PingFSM_SM_Ready(string name, int id) :
                    base (name, id)
                {}

                protected internal override void QueryHeartbeatPulseTransition(PingServer_PingFSMContext context)
                {

                    PingServer_PingFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : PingServer_PingFSM_SM.Ready.QueryHeartbeatPulseTransition()");
#endif

                    PingServer_PingFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.ReportHeartbeatPulseAction();
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }
            }

            internal class PingServer_PingFSM_SM_Internally_Generated_State_DO_NOT_USE :
                PingServer_PingFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal PingServer_PingFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void QueryHeartbeatPulseTransition(PingServer_PingFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : PingServer_PingFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryHeartbeatPulseTransition()");
#endif


                    return;
                }
            }
        }
    }

}
