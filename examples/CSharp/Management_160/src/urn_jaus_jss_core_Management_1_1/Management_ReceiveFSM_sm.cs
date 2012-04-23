using System;
using System.Diagnostics;

using JTS;
namespace urn_jaus_jss_core_Management_1_1
{
    public sealed class Management_ReceiveFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public Management_ReceiveFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((Management_ReceiveFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public Management_ReceiveFSM Owner
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

        public Management_ReceiveFSMContext(Management_ReceiveFSM owner) :
            base (Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby)
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

        public void InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition";
            State.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(this, ie);
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

        public void ReceiveTransition(Reset msg)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(SetAuthority msg)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(Shutdown msg)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(ClearEmergency msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(QueryStatus msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(ReleaseControl msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(RequestControl msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(Reset msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(Resume msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(SetEmergency msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(Shutdown msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
            transition_ = "";
            return;
        }

        public void ReceiveTransition(Standby msg, Receive.Body.ReceiveRec transportData)
        {
            transition_ = "ReceiveTransition";
            State.ReceiveTransition(this, msg, transportData);
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
        private Management_ReceiveFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class Management_ReceiveFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Management_ReceiveFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(Management_ReceiveFSMContext context)
            {}

            protected internal virtual void Exit(Management_ReceiveFSMContext context)
            {}

            protected internal virtual void BroadcastGlobalTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void BroadcastLocalTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CancelEventTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ClearEmergencyTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandCompletedTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CommandExpiredTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateCommandEventTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void CreateEventTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventErrorTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void EventOccurredTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void FailureTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void InitializedTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void QueryAuthorityTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryControlTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventTimeoutTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryEventsTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryStatusTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryTimeoutTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, Reset msg)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, SetAuthority msg)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, Shutdown msg)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, ClearEmergency msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, QueryStatus msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, ReleaseControl msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, RequestControl msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, Reset msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, Resume msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, SetEmergency msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, Shutdown msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReceiveTransition(Management_ReceiveFSMContext context, Standby msg, Receive.Body.ReceiveRec transportData)
            {
                Default(context);
            }

            protected internal virtual void ReleaseControlTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void RequestControlTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ResetTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ResumeTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SendTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SetAuthorityTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void SetEmergencyTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ShutdownTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void StandbyTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void TimeoutTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void UpdateEventTransition(Management_ReceiveFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void Default(Management_ReceiveFSMContext context)
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

        internal abstract class Management_ReceiveFSM_SM
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
            internal static readonly Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_Available_Standby Receiving_Ready_NotControlled_Available_Standby =
                new Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_Available_Standby("Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby", 0);
            [NonSerialized]
            internal static readonly Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Init Receiving_Ready_NotControlled_NotAvailable_Init =
                new Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Init("Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init", 1);
            [NonSerialized]
            internal static readonly Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Failure Receiving_Ready_NotControlled_NotAvailable_Failure =
                new Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Failure("Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure", 2);
            [NonSerialized]
            internal static readonly Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Shutdown Receiving_Ready_NotControlled_NotAvailable_Shutdown =
                new Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Shutdown("Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown", 3);
            [NonSerialized]
            internal static readonly Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Emergency Receiving_Ready_NotControlled_NotAvailable_Emergency =
                new Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Emergency("Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency", 4);
            [NonSerialized]
            internal static readonly Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_Controlled_Available_Standby Receiving_Ready_Controlled_Available_Standby =
                new Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_Controlled_Available_Standby("Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby", 5);
            [NonSerialized]
            internal static readonly Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_Controlled_Available_Ready Receiving_Ready_Controlled_Available_Ready =
                new Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_Controlled_Available_Ready("Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready", 6);
            [NonSerialized]
            internal static readonly Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_Controlled_NotAvailable_Emergency Receiving_Ready_Controlled_NotAvailable_Emergency =
                new Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Receiving_Ready_Controlled_NotAvailable_Emergency("Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency", 7);
            [NonSerialized]
            internal static readonly Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new Management_ReceiveFSM_SM_Default.Management_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE("Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE", 8);
            [NonSerialized]
            private static readonly Management_ReceiveFSM_SM_Default Default =
                new Management_ReceiveFSM_SM_Default("Management_ReceiveFSM_SM.Default", -1);

        }

        internal class Management_ReceiveFSM_SM_Default :
            Management_ReceiveFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal Management_ReceiveFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_Available_Standby :
                Management_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_Available_Standby(string name, int id) :
                    base (name, id)
                {}

                protected internal override void FailureTransition(Management_ReceiveFSMContext context)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.FailureTransition()");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", null);
                        }
                        finally
                        {
                    context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                    context.State.Entry(context);
                }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Ready", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(, InternalEvent ie)");
#endif


                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                            context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Shutdown", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, QueryStatus msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.ReceiveTransition(, QueryStatus msg, Receive.Body.ReceiveRec transportData)");
#endif

                        Management_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                        ctxt.SendAction("ReportStatus", transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, SetEmergency msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby.ReceiveTransition(, SetEmergency msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.StoreIDAction(transportData);
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", null);
                    }
                    finally
                    {
                        context.State = endState;
                        context.PushState(Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency);
                        context.State.Entry(context);
                    }

                    return;
                }
            }

            internal class Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Init :
                Management_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Init(string name, int id) :
                    base (name, id)
                {}

                protected internal override void FailureTransition(Management_ReceiveFSMContext context)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.FailureTransition()");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", null);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InitializedTransition(Management_ReceiveFSMContext context)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InitializedTransition()");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", null);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                    context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                    context.State.Entry(context);
                }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Ready", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Shutdown", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, QueryStatus msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.ReceiveTransition(, QueryStatus msg, Receive.Body.ReceiveRec transportData)");
#endif

                        Management_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                        ctxt.SendAction("ReportStatus", transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, SetEmergency msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init.ReceiveTransition(, SetEmergency msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.StoreIDAction(transportData);
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", null);
                    }
                    finally
                    {
                        context.State = endState;
                        context.PushState(Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency);
                        context.State.Entry(context);
                    }

                    return;
                }
            }

            internal class Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Failure :
                Management_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Failure(string name, int id) :
                    base (name, id)
                {}

                protected internal override void FailureTransition(Management_ReceiveFSMContext context)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.FailureTransition()");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", null);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                    context.State.Entry(context);
                }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Ready", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Shutdown", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, QueryStatus msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.ReceiveTransition(, QueryStatus msg, Receive.Body.ReceiveRec transportData)");
#endif

                        Management_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                        ctxt.SendAction("ReportStatus", transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, SetEmergency msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure.ReceiveTransition(, SetEmergency msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.StoreIDAction(transportData);
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", null);
                    }
                    finally
                    {
                        context.State = endState;
                        context.PushState(Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency);
                        context.State.Entry(context);
                    }

                    return;
                }
            }

            internal class Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Shutdown :
                Management_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Shutdown(string name, int id) :
                    base (name, id)
                {}

                protected internal override void FailureTransition(Management_ReceiveFSMContext context)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.FailureTransition()");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", null);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                    context.State.Entry(context);
                }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Ready", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, QueryStatus msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.ReceiveTransition(, QueryStatus msg, Receive.Body.ReceiveRec transportData)");
#endif

                        Management_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                        ctxt.SendAction("ReportStatus", transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, SetEmergency msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown.ReceiveTransition(, SetEmergency msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.StoreIDAction(transportData);
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", null);
                    }
                    finally
                    {
                        context.State = endState;
                        context.PushState(Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency);
                        context.State.Entry(context);
                    }

                    return;
                }
            }

            internal class Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Emergency :
                Management_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Management_ReceiveFSM_SM_Receiving_Ready_NotControlled_NotAvailable_Emergency(string name, int id) :
                    base (name, id)
                {}

                protected internal override void FailureTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.FailureTransition()");
#endif


                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Ready", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Shutdown", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Reset msg)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.ReceiveTransition(, Reset msg)");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, SetAuthority msg)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.ReceiveTransition(, SetAuthority msg)");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Shutdown msg)
                    {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.ReceiveTransition(, Shutdown msg)");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, ClearEmergency msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.ReceiveTransition(, ClearEmergency msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isIDStored( transportData ))
                    {

                        context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                            ctxt.popWrapper_367bc5868aa53b23a6c5a07701058fbf(msg, transportData);
                            ctxt.processNotifications(context.peakTopStateStack(), null);
                    }
                    finally
                    {
                            context.PopState();
                    }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, QueryStatus msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.ReceiveTransition(, QueryStatus msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportStatus", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, RequestControl msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.ReceiveTransition(, RequestControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                        Management_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                        ctxt.SendAction("ConfirmControl", "NOT_AVAILABLE", transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    return;
                    }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, SetEmergency msg, Receive.Body.ReceiveRec transportData)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.ReceiveTransition(, SetEmergency msg, Receive.Body.ReceiveRec transportData)");
#endif

                        Management_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                        ctxt.StoreIDAction(transportData);
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", null);
                        }
                        finally
                        {
                            context.State = endState;
                        context.PushState(Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency);
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void TimeoutTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency.TimeoutTransition()");
#endif


                    return;
                }
            }

            internal class Management_ReceiveFSM_SM_Receiving_Ready_Controlled_Available_Standby :
                Management_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Management_ReceiveFSM_SM_Receiving_Ready_Controlled_Available_Standby(string name, int id) :
                    base (name, id)
                {}

                protected internal override void FailureTransition(Management_ReceiveFSMContext context)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.FailureTransition()");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.SendAction("RejectControl", "CONTROL_RELEASED");
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", null);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Ready", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(, InternalEvent ie)");
#endif


                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                            context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Shutdown", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, QueryStatus msg, Receive.Body.ReceiveRec transportData)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.ReceiveTransition(, QueryStatus msg, Receive.Body.ReceiveRec transportData)");
#endif

                        Management_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                        ctxt.SendAction("ReportStatus", transportData);
                        }
                        finally
                        {
                            context.State = endState;
                        }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Reset msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.ReceiveTransition(, Reset msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isControllingClient( transportData ))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectControl", "CONTROL_RELEASED", transportData);
                            ctxt.initializeAction();
                            ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", null);
                        }
                        finally
                        {
                            context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                            context.State.Entry(context);
                        }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Resume msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.ReceiveTransition(, Resume msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isControllingClient( transportData ))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.processNotifications("Receiving_Ready_Controlled_Available_Ready", null);
                        }
                        finally
                        {
                            context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready;
                            context.State.Entry(context);
                        }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, SetEmergency msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.ReceiveTransition(, SetEmergency msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.StoreIDAction(transportData);
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", null);
                    }
                    finally
                    {
                        context.State = endState;
                        context.PushState(Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency);
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Shutdown msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby.ReceiveTransition(, Shutdown msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isControllingClient( transportData ))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectControl", "CONTROL_RELEASED", transportData);
                            ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Shutdown", null);
                        }
                        finally
                        {
                            context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown;
                            context.State.Entry(context);
                        }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }
            }

            internal class Management_ReceiveFSM_SM_Receiving_Ready_Controlled_Available_Ready :
                Management_ReceiveFSM_SM_Default
                {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Management_ReceiveFSM_SM_Receiving_Ready_Controlled_Available_Ready(string name, int id) :
                    base (name, id)
                {}

                protected internal override void FailureTransition(Management_ReceiveFSMContext context)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.FailureTransition()");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.SendAction("RejectControl", "CONTROL_RELEASED");
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", null);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(, InternalEvent ie)");
#endif


                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                            context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                        {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(, InternalEvent ie)");
#endif


                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Shutdown", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown;
                            context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, QueryStatus msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.ReceiveTransition(, QueryStatus msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                        context.ClearState();

                        try
                        {
                        ctxt.SendAction("ReportStatus", transportData);
                        }
                        finally
                        {
                        context.State = endState;
                        }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Reset msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.ReceiveTransition(, Reset msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isControllingClient( transportData ))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectControl", "CONTROL_RELEASED", transportData);
                            ctxt.initializeAction();
                            ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", null);
                        }
                        finally
                        {
                            context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                            context.State.Entry(context);
                        }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, SetEmergency msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.ReceiveTransition(, SetEmergency msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.StoreIDAction(transportData);
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", null);
                    }
                    finally
                    {
                        context.State = endState;
                        context.PushState(Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency);
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Shutdown msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.ReceiveTransition(, Shutdown msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isControllingClient( transportData ))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectControl", "CONTROL_RELEASED", transportData);
                            ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Shutdown", null);
                        }
                        finally
                        {
                            context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown;
                            context.State.Entry(context);
                        }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Standby msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready.ReceiveTransition(, Standby msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isControllingClient( transportData ))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", null);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }
            }

            internal class Management_ReceiveFSM_SM_Receiving_Ready_Controlled_NotAvailable_Emergency :
                Management_ReceiveFSM_SM_Default
                {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Management_ReceiveFSM_SM_Receiving_Ready_Controlled_NotAvailable_Emergency(string name, int id) :
                    base (name, id)
                {}

                protected internal override void FailureTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.FailureTransition()");
#endif


                    return;
                        }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_ReceivingTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
            {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_ControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Ready", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Ready;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                {
                        ctxt.processNotifications("Receiving_Ready_Controlled_Available_Standby", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_Controlled_Available_Standby;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlledTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_StandbyTransition(, InternalEvent ie)");
#endif


                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_Available_Standby", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_Available_Standby;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                        }

                    return;
                    }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                    {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_EmergencyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Emergency", ie);
                        }
                        finally
                        {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Emergency;
                        context.State.Entry(context);
                        }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_FailureTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Failure", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Failure;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_InitTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.initializeAction();
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(Management_ReceiveFSMContext context, InternalEvent ie)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_ShutdownTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Shutdown", ie);
                    }
                    finally
                    {
                        context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Reset msg)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.ReceiveTransition(, Reset msg)");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, SetAuthority msg)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.ReceiveTransition(, SetAuthority msg)");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Shutdown msg)
                    {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.ReceiveTransition(, Shutdown msg)");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, ClearEmergency msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.ReceiveTransition(, ClearEmergency msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isIDStored( transportData ))
                    {

                        context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                            ctxt.popWrapper_1014a7ff54cb3a51af2f80235d77d9d1(msg, transportData);
                            ctxt.processNotifications(context.peakTopStateStack(), null);
                    }
                    finally
                    {
                            context.PopState();
                    }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, QueryStatus msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.ReceiveTransition(, QueryStatus msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ReportStatus", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, ReleaseControl msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.ReceiveTransition(, ReleaseControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("RejectControl", "NOT_AVAILABLE", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, RequestControl msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.ReceiveTransition(, RequestControl msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.SendAction("ConfirmControl", "NOT_AVAILABLE", transportData);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Reset msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.ReceiveTransition(, Reset msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isControllingClient( transportData ))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectControl", "CONTROL_RELEASED", transportData);
                            ctxt.initializeAction();
                            ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Init", null);
                        }
                        finally
                        {
                            context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Init;
                            context.State.Entry(context);
                        }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, SetEmergency msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.ReceiveTransition(, SetEmergency msg, Receive.Body.ReceiveRec transportData)");
#endif

                    Management_ReceiveFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.StoreIDAction(transportData);
                        ctxt.processNotifications("Receiving_Ready_Controlled_NotAvailable_Emergency", null);
                    }
                    finally
                    {
                        context.State = endState;
                        context.PushState(Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency);
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context, Shutdown msg, Receive.Body.ReceiveRec transportData)
                {

                    Management_ReceiveFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.ReceiveTransition(, Shutdown msg, Receive.Body.ReceiveRec transportData)");
#endif

                    if (ctxt.isControllingClient( transportData ))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.SendAction("RejectControl", "CONTROL_RELEASED", transportData);
                            ctxt.processNotifications("Receiving_Ready_NotControlled_NotAvailable_Shutdown", null);
                        }
                        finally
                        {
                            context.State = Management_ReceiveFSM_SM.Receiving_Ready_NotControlled_NotAvailable_Shutdown;
                            context.State.Entry(context);
                        }

                }
                    else                    {
                        base.ReceiveTransition(context, msg, transportData);
                    }

                    return;
                }

                protected internal override void TimeoutTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Receiving_Ready_Controlled_NotAvailable_Emergency.TimeoutTransition()");
#endif


                    return;
                }
            }

            internal class Management_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE :
                Management_ReceiveFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal Management_ReceiveFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void BroadcastGlobalTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastGlobalTransition()");
#endif


                    return;
                }

                protected internal override void BroadcastLocalTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.BroadcastLocalTransition()");
#endif


                    return;
                }

                protected internal override void CancelEventTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CancelEventTransition()");
#endif


                    return;
                }

                protected internal override void ClearEmergencyTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ClearEmergencyTransition()");
#endif


                    return;
                }

                protected internal override void CommandCompletedTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandCompletedTransition()");
#endif


                    return;
                }

                protected internal override void CommandExpiredTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CommandExpiredTransition()");
#endif


                    return;
                }

                protected internal override void CreateCommandEventTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateCommandEventTransition()");
#endif


                    return;
                }

                protected internal override void CreateEventTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.CreateEventTransition()");
#endif


                    return;
                }

                protected internal override void EventErrorTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.EventErrorTransition()");
#endif


                    return;
                }

                protected internal override void EventOccurredTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.EventOccurredTransition()");
#endif


                    return;
                }

                protected internal override void FailureTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.FailureTransition()");
#endif


                    return;
                }

                protected internal override void InitializedTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.InitializedTransition()");
#endif


                    return;
                }

                protected internal override void QueryAuthorityTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryAuthorityTransition()");
#endif


                    return;
                }

                protected internal override void QueryControlTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryControlTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventTimeoutTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventTimeoutTransition()");
#endif


                    return;
                }

                protected internal override void QueryEventsTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryEventsTransition()");
#endif


                    return;
                }

                protected internal override void QueryStatusTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryStatusTransition()");
#endif


                    return;
                }

                protected internal override void QueryTimeoutTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryTimeoutTransition()");
#endif


                    return;
                }

                protected internal override void ReceiveTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReceiveTransition()");
#endif


                    return;
                }

                protected internal override void ReleaseControlTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ReleaseControlTransition()");
#endif


                    return;
                }

                protected internal override void RequestControlTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.RequestControlTransition()");
#endif


                    return;
                }

                protected internal override void ResetTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ResetTransition()");
#endif


                    return;
                }

                protected internal override void ResumeTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ResumeTransition()");
#endif


                    return;
                }

                protected internal override void SendTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.SendTransition()");
#endif


                    return;
                }

                protected internal override void SetAuthorityTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.SetAuthorityTransition()");
#endif


                    return;
                }

                protected internal override void SetEmergencyTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.SetEmergencyTransition()");
#endif


                    return;
                }

                protected internal override void ShutdownTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.ShutdownTransition()");
#endif


                    return;
                }

                protected internal override void StandbyTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.StandbyTransition()");
#endif


                    return;
                }

                protected internal override void TimeoutTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.TimeoutTransition()");
#endif


                    return;
                }

                protected internal override void UpdateEventTransition(Management_ReceiveFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : Management_ReceiveFSM_SM.Internally_Generated_State_DO_NOT_USE.UpdateEventTransition()");
#endif


                    return;
                }
            }
        }
    }

}
