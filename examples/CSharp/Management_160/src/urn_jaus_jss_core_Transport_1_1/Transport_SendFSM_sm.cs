using System;
using System.Diagnostics;

using JTS;
namespace urn_jaus_jss_core_Transport_1_1
{
    public sealed class Transport_SendFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public Transport_SendFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((Transport_SendFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public Transport_SendFSM Owner
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

        public Transport_SendFSMContext(Transport_SendFSM owner) :
            base (Transport_SendFSM_SM.Sending)
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

        public void BroadcastGlobalTransition(BroadcastGlobal msg)
        {
            transition_ = "BroadcastGlobalTransition";
            State.BroadcastGlobalTransition(this, msg);
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

        public void BroadcastLocalTransition(BroadcastLocal msg)
        {
            transition_ = "BroadcastLocalTransition";
            State.BroadcastLocalTransition(this, msg);
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

        public void SendTransition(Send msg)
        {
            transition_ = "SendTransition";
            State.SendTransition(this, msg);
            transition_ = "";
            return;
        }

    //---------------------------------------------------------------
    // Member data.
    //

        [NonSerialized]
        private Transport_SendFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class Transport_SendFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Transport_SendFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(Transport_SendFSMContext context)
            {}

            protected internal virtual void Exit(Transport_SendFSMContext context)
            {}

            protected internal virtual void BroadcastGlobalTransition(Transport_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void BroadcastGlobalTransition(Transport_SendFSMContext context, BroadcastGlobal msg)
            {
                Default(context);
            }

            protected internal virtual void BroadcastLocalTransition(Transport_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void BroadcastLocalTransition(Transport_SendFSMContext context, BroadcastLocal msg)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Transport_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SendTransition(Transport_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SendTransition(Transport_SendFSMContext context, Send msg)
            {
                Default(context);
            }

            protected internal virtual void Default(Transport_SendFSMContext context)
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

        internal abstract class Transport_SendFSM_SM
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
            internal static readonly Transport_SendFSM_SM_Default.Transport_SendFSM_SM_Sending Sending =
                new Transport_SendFSM_SM_Default.Transport_SendFSM_SM_Sending("Transport_SendFSM_SM.Sending", 0);
            [NonSerialized]
            internal static readonly Transport_SendFSM_SM_Default.Transport_SendFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new Transport_SendFSM_SM_Default.Transport_SendFSM_SM_Internally_Generated_State_DO_NOT_USE("Transport_SendFSM_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly Transport_SendFSM_SM_Default Default =
                new Transport_SendFSM_SM_Default("Transport_SendFSM_SM.Default", -1);

        }

        internal class Transport_SendFSM_SM_Default :
            Transport_SendFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Transport_SendFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class Transport_SendFSM_SM_Sending :
                Transport_SendFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Transport_SendFSM_SM_Sending(string name, int id) :
                    base (name, id)
                {}

                protected internal override void BroadcastGlobalTransition(Transport_SendFSMContext context, BroadcastGlobal msg)
                {

                    Transport_SendFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_SendFSM_SM.Sending.BroadcastGlobalTransition(, BroadcastGlobal msg)");
#endif

                    Transport_SendFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.BroadcastGlobalEnqueueAction(msg);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void BroadcastLocalTransition(Transport_SendFSMContext context, BroadcastLocal msg)
                {

                    Transport_SendFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_SendFSM_SM.Sending.BroadcastLocalTransition(, BroadcastLocal msg)");
#endif

                    Transport_SendFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.BroadcastLocalEnqueueAction(msg);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void SendTransition(Transport_SendFSMContext context, Send msg)
                {

                    Transport_SendFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_SendFSM_SM.Sending.SendTransition(, Send msg)");
#endif

                    Transport_SendFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.EnqueueAction(msg);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }
            }

            internal class Transport_SendFSM_SM_Internally_Generated_State_DO_NOT_USE :
                Transport_SendFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Transport_SendFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void BroadcastGlobalTransition(Transport_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif


                    return;
                }

                protected internal override void BroadcastLocalTransition(Transport_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Transport_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif


                    return;
                }

                protected internal override void SendTransition(Transport_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Transport_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif


                    return;
                }
            }
        }
    }

}
