using System;
using System.Diagnostics;

using JTS;
namespace urn_jaus_jss_core_Transport_1_1
{
    public sealed class Transport_ReceiveFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public Transport_ReceiveFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((Transport_ReceiveFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public Transport_ReceiveFSM Owner
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

        public Transport_ReceiveFSMContext(Transport_ReceiveFSM owner) :
            base (Transport_ReceiveFSM_SM.Receiving)
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

    //---------------------------------------------------------------
    // Member data.
    //

        [NonSerialized]
        private Transport_ReceiveFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class Transport_ReceiveFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Transport_ReceiveFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(Transport_ReceiveFSMContext context)
            {}

            protected internal virtual void Exit(Transport_ReceiveFSMContext context)
            {}

            protected internal virtual void BroadcastGlobalTransition(Transport_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void BroadcastLocalTransition(Transport_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Transport_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SendTransition(Transport_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(Transport_ReceiveFSMContext context)
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

        internal abstract class Transport_ReceiveFSM_SM
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
            internal static readonly Transport_ReceiveFSM_SM_Default.Transport_ReceiveFSM_SM_Receiving Receiving =
                new Transport_ReceiveFSM_SM_Default.Transport_ReceiveFSM_SM_Receiving("Transport_ReceiveFSM_SM.Receiving", 0);
            [NonSerialized]
            internal static readonly Transport_ReceiveFSM_SM_Default.Transport_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new Transport_ReceiveFSM_SM_Default.Transport_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE("Transport_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly Transport_ReceiveFSM_SM_Default Default =
                new Transport_ReceiveFSM_SM_Default("Transport_ReceiveFSM_SM.Default", -1);

        }

        internal class Transport_ReceiveFSM_SM_Default :
            Transport_ReceiveFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Transport_ReceiveFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class Transport_ReceiveFSM_SM_Receiving :
                Transport_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Transport_ReceiveFSM_SM_Receiving(string name, int id) :
                    base (name, id)
                {}

                protected internal override void ReceiveTransition(Transport_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_ReceiveFSM_SM.Receiving.ReceiveTransition()");
#endif


                    return;
                }
            }

            internal class Transport_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE :
                Transport_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Transport_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void BroadcastGlobalTransition(Transport_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif


                    return;
                }

                protected internal override void BroadcastLocalTransition(Transport_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Transport_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif


                    return;
                }

                protected internal override void SendTransition(Transport_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif


                    return;
                }
            }
        }
    }

}
