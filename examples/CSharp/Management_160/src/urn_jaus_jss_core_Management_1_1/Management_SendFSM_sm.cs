using System;
using System.Diagnostics;

using JTS;
namespace urn_jaus_jss_core_Management_1_1
{
    public sealed class Management_SendFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public Management_SendFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((Management_SendFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public Management_SendFSM Owner
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

        public Management_SendFSMContext(Management_SendFSM owner) :
            base (Management_SendFSM_SM.Sending)
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

        public void ClearEmergencyTransition()
        {
            transition_ = "ClearEmergencyTransition";
            State.ClearEmergencyTransition(this);
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

        public void FailureTransition()
        {
            transition_ = "FailureTransition";
            State.FailureTransition(this);
            transition_ = "";
            return;
        }

        public void InitializedTransition()
        {
            transition_ = "InitializedTransition";
            State.InitializedTransition(this);
            transition_ = "";
            return;
        }

        public void QueryAuthorityTransition()
        {
            transition_ = "QueryAuthorityTransition";
            State.QueryAuthorityTransition(this);
            transition_ = "";
            return;
        }

        public void QueryControlTransition()
        {
            transition_ = "QueryControlTransition";
            State.QueryControlTransition(this);
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

        public void QueryStatusTransition()
        {
            transition_ = "QueryStatusTransition";
            State.QueryStatusTransition(this);
            transition_ = "";
            return;
        }

        public void QueryTimeoutTransition()
        {
            transition_ = "QueryTimeoutTransition";
            State.QueryTimeoutTransition(this);
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

        public void ReleaseControlTransition()
        {
            transition_ = "ReleaseControlTransition";
            State.ReleaseControlTransition(this);
            transition_ = "";
            return;
        }

        public void RequestControlTransition()
        {
            transition_ = "RequestControlTransition";
            State.RequestControlTransition(this);
            transition_ = "";
            return;
        }

        public void ResetTransition()
        {
            transition_ = "ResetTransition";
            State.ResetTransition(this);
            transition_ = "";
            return;
        }

        public void ResumeTransition()
        {
            transition_ = "ResumeTransition";
            State.ResumeTransition(this);
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

        public void SetAuthorityTransition()
        {
            transition_ = "SetAuthorityTransition";
            State.SetAuthorityTransition(this);
            transition_ = "";
            return;
        }

        public void SetEmergencyTransition()
        {
            transition_ = "SetEmergencyTransition";
            State.SetEmergencyTransition(this);
            transition_ = "";
            return;
        }

        public void ShutdownTransition()
        {
            transition_ = "ShutdownTransition";
            State.ShutdownTransition(this);
            transition_ = "";
            return;
        }

        public void StandbyTransition()
        {
            transition_ = "StandbyTransition";
            State.StandbyTransition(this);
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
        private Management_SendFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class Management_SendFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Management_SendFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(Management_SendFSMContext context)
            {}

            protected internal virtual void Exit(Management_SendFSMContext context)
            {}

            protected internal virtual void BroadcastGlobalTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void BroadcastLocalTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CancelEventTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ClearEmergencyTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandCompletedTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandExpiredTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateCommandEventTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateEventTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventErrorTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventOccurredTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void FailureTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void InitializedTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryAuthorityTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryControlTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventTimeoutTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventsTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryStatusTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryTimeoutTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReleaseControlTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void RequestControlTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ResetTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ResumeTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SendTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SetAuthorityTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SetEmergencyTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ShutdownTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void StandbyTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void TimeoutTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void UpdateEventTransition(Management_SendFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(Management_SendFSMContext context)
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

        internal abstract class Management_SendFSM_SM
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
            internal static readonly Management_SendFSM_SM_Default.Management_SendFSM_SM_Sending Sending =
                new Management_SendFSM_SM_Default.Management_SendFSM_SM_Sending("Management_SendFSM_SM.Sending", 0);
            [NonSerialized]
            internal static readonly Management_SendFSM_SM_Default.Management_SendFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new Management_SendFSM_SM_Default.Management_SendFSM_SM_Internally_Generated_State_DO_NOT_USE("Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE", 1);
            [NonSerialized]
            private static readonly Management_SendFSM_SM_Default Default =
                new Management_SendFSM_SM_Default("Management_SendFSM_SM.Default", -1);

        }

        internal class Management_SendFSM_SM_Default :
            Management_SendFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Management_SendFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class Management_SendFSM_SM_Sending :
                Management_SendFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Management_SendFSM_SM_Sending(string name, int id) :
                    base (name, id)
                {}
                    }

            internal class Management_SendFSM_SM_Internally_Generated_State_DO_NOT_USE :
                Management_SendFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Management_SendFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void BroadcastGlobalTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif


                    return;
                }

                protected internal override void BroadcastLocalTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif


                    return;
                }

                protected internal override void CancelEventTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CancelEventTransition()");
#endif


                    return;
                }

                protected internal override void ClearEmergencyTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ClearEmergencyTransition()");
#endif


                    return;
                }

                protected internal override void CommandCompletedTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandCompletedTransition()");
#endif


                    return;
                }

                protected internal override void CommandExpiredTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandExpiredTransition()");
#endif


                    return;
                }

                protected internal override void CreateCommandEventTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateCommandEventTransition()");
#endif


                    return;
                }

                protected internal override void CreateEventTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateEventTransition()");
#endif


                    return;
                }

                protected internal override void EventErrorTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.EventErrorTransition()");
#endif


                    return;
                }

                protected internal override void EventOccurredTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.EventOccurredTransition()");
#endif


                    return;
                }

                protected internal override void FailureTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.FailureTransition()");
#endif


                    return;
                }

                protected internal override void InitializedTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.InitializedTransition()");
#endif


                    return;
                }

                protected internal override void QueryAuthorityTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryAuthorityTransition()");
#endif


                    return;
                }

                protected internal override void QueryControlTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryControlTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventTimeoutTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventTimeoutTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventsTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventsTransition()");
#endif


                    return;
                }

                protected internal override void QueryStatusTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryStatusTransition()");
#endif


                    return;
                }

                protected internal override void QueryTimeoutTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryTimeoutTransition()");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif


                    return;
                }

                protected internal override void ReleaseControlTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ReleaseControlTransition()");
#endif


                    return;
                }

                protected internal override void RequestControlTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.RequestControlTransition()");
#endif


                    return;
                }

                protected internal override void ResetTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ResetTransition()");
#endif


                    return;
                }

                protected internal override void ResumeTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ResumeTransition()");
#endif


                    return;
                }

                protected internal override void SendTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif


                    return;
                }

                protected internal override void SetAuthorityTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.SetAuthorityTransition()");
#endif


                    return;
                }

                protected internal override void SetEmergencyTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.SetEmergencyTransition()");
#endif


                    return;
                }

                protected internal override void ShutdownTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.ShutdownTransition()");
#endif


                    return;
                }

                protected internal override void StandbyTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.StandbyTransition()");
#endif


                    return;
                }

                protected internal override void TimeoutTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.TimeoutTransition()");
#endif


                    return;
                }

                protected internal override void UpdateEventTransition(Management_SendFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_SendFSM_SM.Internally_Generated_State_DO_NOT_USE.UpdateEventTransition()");
#endif


                    return;
                }
            }
        }
    }

}
