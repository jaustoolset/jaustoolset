using System;
using System.Diagnostics;

using JTS;
namespace urn_jts_PingClient_1_0
{
    public sealed class PingClientProtocolContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public PingClientProtocolState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((PingClientProtocolState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public PingClientProtocol Owner
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

        public PingClientProtocolContext(PingClientProtocol owner) :
            base (PingClientProtocol_SM.Ready)
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
        private PingClientProtocol _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class PingClientProtocolState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal PingClientProtocolState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(PingClientProtocolContext context)
            {}

            protected internal virtual void Exit(PingClientProtocolContext context)
            {}

            protected internal virtual void ReportHeartbeatPulseTransition(PingClientProtocolContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(PingClientProtocolContext context)
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

        internal abstract class PingClientProtocol_SM
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
            internal static readonly PingClientProtocol_SM_Default.PingClientProtocol_SM_Ready Ready =
                new PingClientProtocol_SM_Default.PingClientProtocol_SM_Ready("PingClientProtocol_SM.Ready", 0);
            [NonSerialized]
            internal static readonly PingClientProtocol_SM_Default.PingClientProtocol_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new PingClientProtocol_SM_Default.PingClientProtocol_SM_Internally_Generated_State_DO_NOT_USE("PingClientProtocol_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly PingClientProtocol_SM_Default Default =
                new PingClientProtocol_SM_Default("PingClientProtocol_SM.Default", -1);

        }

        internal class PingClientProtocol_SM_Default :
            PingClientProtocolState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal PingClientProtocol_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class PingClientProtocol_SM_Ready :
                PingClientProtocol_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal PingClientProtocol_SM_Ready(string name, int id) :
                    base (name, id)
                {}

                protected internal override void ReportHeartbeatPulseTransition(PingClientProtocolContext context)
                {

                    PingClientProtocol ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : PingClientProtocol_SM.Ready.ReportHeartbeatPulseTransition()");
#endif

                    PingClientProtocolState endState = context.State;

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

            internal class PingClientProtocol_SM_Internally_Generated_State_DO_NOT_USE :
                PingClientProtocol_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal PingClientProtocol_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void ReportHeartbeatPulseTransition(PingClientProtocolContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : PingClientProtocol_SM.Internally_Generated_State_DO_NOT_USE.ReportHeartbeatPulseTransition()");
#endif


                    return;
                }
            }
        }
    }

}
