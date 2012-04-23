using System;
using System.Diagnostics;

using JTS;
namespace urn_jts_PingServer_1_0
{
    public sealed class PingServerProtocolContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public PingServerProtocolState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((PingServerProtocolState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public PingServerProtocol Owner
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

        public PingServerProtocolContext(PingServerProtocol owner) :
            base (PingServerProtocol_SM.Ready)
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
        private PingServerProtocol _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class PingServerProtocolState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal PingServerProtocolState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(PingServerProtocolContext context)
            {}

            protected internal virtual void Exit(PingServerProtocolContext context)
            {}

            protected internal virtual void QueryHeartbeatPulseTransition(PingServerProtocolContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(PingServerProtocolContext context)
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

        internal abstract class PingServerProtocol_SM
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
            internal static readonly PingServerProtocol_SM_Default.PingServerProtocol_SM_Ready Ready =
                new PingServerProtocol_SM_Default.PingServerProtocol_SM_Ready("PingServerProtocol_SM.Ready", 0);
            [NonSerialized]
            internal static readonly PingServerProtocol_SM_Default.PingServerProtocol_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new PingServerProtocol_SM_Default.PingServerProtocol_SM_Internally_Generated_State_DO_NOT_USE("PingServerProtocol_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly PingServerProtocol_SM_Default Default =
                new PingServerProtocol_SM_Default("PingServerProtocol_SM.Default", -1);

        }

        internal class PingServerProtocol_SM_Default :
            PingServerProtocolState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal PingServerProtocol_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class PingServerProtocol_SM_Ready :
                PingServerProtocol_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal PingServerProtocol_SM_Ready(string name, int id) :
                    base (name, id)
                {}

                protected internal override void QueryHeartbeatPulseTransition(PingServerProtocolContext context)
                {

                    PingServerProtocol ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : PingServerProtocol_SM.Ready.QueryHeartbeatPulseTransition()");
#endif

                    PingServerProtocolState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.sendReportHeartbeatPulseAction();
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }
            }

            internal class PingServerProtocol_SM_Internally_Generated_State_DO_NOT_USE :
                PingServerProtocol_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal PingServerProtocol_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void QueryHeartbeatPulseTransition(PingServerProtocolContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : PingServerProtocol_SM.Internally_Generated_State_DO_NOT_USE.QueryHeartbeatPulseTransition()");
#endif


                    return;
                }
            }
        }
    }

}
