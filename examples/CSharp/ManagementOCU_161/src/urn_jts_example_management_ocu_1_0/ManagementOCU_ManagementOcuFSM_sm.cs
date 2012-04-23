using System;
using System.Diagnostics;

using JTS;
namespace urn_jts_example_management_ocu_1_0
{
    public sealed class ManagementOCU_ManagementOcuFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public ManagementOCU_ManagementOcuFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((ManagementOCU_ManagementOcuFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public ManagementOCU_ManagementOcuFSM Owner
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

        public ManagementOCU_ManagementOcuFSMContext(ManagementOCU_ManagementOcuFSM owner) :
            base (ManagementOCU_ManagementOcuFSM_SM.WaitForControl)
        {
        _owner = owner;
        }

        public override void EnterStartState()
        {
            State.Entry(this);
            return;
        }

        public void ConfirmControlTransition()
        {
            transition_ = "ConfirmControlTransition";
            State.ConfirmControlTransition(this);
            transition_ = "";
            return;
        }

        public void ConfirmControlTransition(ConfirmControl msg)
        {
            transition_ = "ConfirmControlTransition";
            State.ConfirmControlTransition(this, msg);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenuTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenuTransition";
            State.InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenuTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControlTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControlTransition";
            State.InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControlTransition(this, ie);
            transition_ = "";
            return;
        }

        public void MenuItemEnteredTransition()
        {
            transition_ = "MenuItemEnteredTransition";
            State.MenuItemEnteredTransition(this);
            transition_ = "";
            return;
        }

        public void MenuItemEnteredTransition(MenuItemEntered msg)
        {
            transition_ = "MenuItemEnteredTransition";
            State.MenuItemEnteredTransition(this, msg);
            transition_ = "";
            return;
        }

        public void RejectControlTransition()
        {
            transition_ = "RejectControlTransition";
            State.RejectControlTransition(this);
            transition_ = "";
            return;
        }

        public void RejectControlTransition(RejectControl msg)
        {
            transition_ = "RejectControlTransition";
            State.RejectControlTransition(this, msg);
            transition_ = "";
            return;
        }

        public void ReportControlTransition()
        {
            transition_ = "ReportControlTransition";
            State.ReportControlTransition(this);
            transition_ = "";
            return;
        }

        public void ReportStatusTransition()
        {
            transition_ = "ReportStatusTransition";
            State.ReportStatusTransition(this);
            transition_ = "";
            return;
        }

        public void ReportStatusTransition(ReportStatus msg)
        {
            transition_ = "ReportStatusTransition";
            State.ReportStatusTransition(this, msg);
            transition_ = "";
            return;
        }

    //---------------------------------------------------------------
    // Member data.
    //

        [NonSerialized]
        private ManagementOCU_ManagementOcuFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class ManagementOCU_ManagementOcuFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal ManagementOCU_ManagementOcuFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(ManagementOCU_ManagementOcuFSMContext context)
            {}

            protected internal virtual void Exit(ManagementOCU_ManagementOcuFSMContext context)
            {}

            protected internal virtual void ConfirmControlTransition(ManagementOCU_ManagementOcuFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ConfirmControlTransition(ManagementOCU_ManagementOcuFSMContext context, ConfirmControl msg)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenuTransition(ManagementOCU_ManagementOcuFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControlTransition(ManagementOCU_ManagementOcuFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void MenuItemEnteredTransition(ManagementOCU_ManagementOcuFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void MenuItemEnteredTransition(ManagementOCU_ManagementOcuFSMContext context, MenuItemEntered msg)
            {
                Default(context);
            }

            protected internal virtual void RejectControlTransition(ManagementOCU_ManagementOcuFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void RejectControlTransition(ManagementOCU_ManagementOcuFSMContext context, RejectControl msg)
            {
                Default(context);
            }

            protected internal virtual void ReportControlTransition(ManagementOCU_ManagementOcuFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReportStatusTransition(ManagementOCU_ManagementOcuFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReportStatusTransition(ManagementOCU_ManagementOcuFSMContext context, ReportStatus msg)
            {
                Default(context);
            }

            protected internal virtual void Default(ManagementOCU_ManagementOcuFSMContext context)
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

        internal abstract class ManagementOCU_ManagementOcuFSM_SM
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
            internal static readonly ManagementOCU_ManagementOcuFSM_SM_Default.ManagementOCU_ManagementOcuFSM_SM_WaitForControl WaitForControl =
                new ManagementOCU_ManagementOcuFSM_SM_Default.ManagementOCU_ManagementOcuFSM_SM_WaitForControl("ManagementOCU_ManagementOcuFSM_SM.WaitForControl", 0);
            [NonSerialized]
            internal static readonly ManagementOCU_ManagementOcuFSM_SM_Default.ManagementOCU_ManagementOcuFSM_SM_HandleMenu HandleMenu =
                new ManagementOCU_ManagementOcuFSM_SM_Default.ManagementOCU_ManagementOcuFSM_SM_HandleMenu("ManagementOCU_ManagementOcuFSM_SM.HandleMenu", 1);
            [NonSerialized]
            internal static readonly ManagementOCU_ManagementOcuFSM_SM_Default.ManagementOCU_ManagementOcuFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new ManagementOCU_ManagementOcuFSM_SM_Default.ManagementOCU_ManagementOcuFSM_SM_Internally_Generated_State_DO_NOT_USE("ManagementOCU_ManagementOcuFSM_SM.Internally_Generated_State_DO_NOT_USE", 2);
            [NonSerialized]
            private static readonly ManagementOCU_ManagementOcuFSM_SM_Default Default =
                new ManagementOCU_ManagementOcuFSM_SM_Default("ManagementOCU_ManagementOcuFSM_SM.Default", -1);

        }

        internal class ManagementOCU_ManagementOcuFSM_SM_Default :
            ManagementOCU_ManagementOcuFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal ManagementOCU_ManagementOcuFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class ManagementOCU_ManagementOcuFSM_SM_WaitForControl :
                ManagementOCU_ManagementOcuFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal ManagementOCU_ManagementOcuFSM_SM_WaitForControl(string name, int id) :
                    base (name, id)
                {}

                protected internal override void ConfirmControlTransition(ManagementOCU_ManagementOcuFSMContext context, ConfirmControl msg)
                {

                    ManagementOCU_ManagementOcuFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.WaitForControl.ConfirmControlTransition(, ConfirmControl msg)");
#endif

                    if (! ctxt.isControlAccepted(msg))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.sendRequestControlAction();
                            ctxt.displayMenuAction();
                            ctxt.processNotifications("WaitForControl", null);
                        }
                        finally
                        {
                            context.State = ManagementOCU_ManagementOcuFSM_SM.WaitForControl;
                            context.State.Entry(context);
                        }

                    }
                    else if (ctxt.isControlAccepted(msg))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.displayMenuAction();
                            ctxt.processNotifications("HandleMenu", null);
                        }
                        finally
                        {
                            context.State = ManagementOCU_ManagementOcuFSM_SM.HandleMenu;
                            context.State.Entry(context);
                        }

                    }                    else                    {
                        base.ConfirmControlTransition(context, msg);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenuTransition(ManagementOCU_ManagementOcuFSMContext context, InternalEvent ie)
                {

                    ManagementOCU_ManagementOcuFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.WaitForControl.InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenuTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.displayMenuAction();
                        ctxt.processNotifications("HandleMenu", ie);
                    }
                    finally
                    {
                        context.State = ManagementOCU_ManagementOcuFSM_SM.HandleMenu;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void MenuItemEnteredTransition(ManagementOCU_ManagementOcuFSMContext context, MenuItemEntered msg)
                {

                    ManagementOCU_ManagementOcuFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.WaitForControl.MenuItemEnteredTransition(, MenuItemEntered msg)");
#endif

                    if (!ctxt.isSelectionToEnd(msg))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.printMessageAction("waiting for AccessControl process to end");
                            ctxt.sendRequestControlAction();
                            ctxt.displayMenuAction();
                            ctxt.processNotifications("WaitForControl", null);
                        }
                        finally
                        {
                            context.State = ManagementOCU_ManagementOcuFSM_SM.WaitForControl;
                            context.State.Entry(context);
                        }

                    }
                    else if (ctxt.isSelectionToEnd(msg))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.terminateServiceAction();
                            ctxt.sendRequestControlAction();
                            ctxt.displayMenuAction();
                            ctxt.processNotifications("WaitForControl", null);
                        }
                        finally
                        {
                            context.State = ManagementOCU_ManagementOcuFSM_SM.WaitForControl;
                            context.State.Entry(context);
                        }

                    }                    else                    {
                        base.MenuItemEnteredTransition(context, msg);
                    }

                    return;
                }
            }

            internal class ManagementOCU_ManagementOcuFSM_SM_HandleMenu :
                ManagementOCU_ManagementOcuFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal ManagementOCU_ManagementOcuFSM_SM_HandleMenu(string name, int id) :
                    base (name, id)
                {}

                protected internal override void InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControlTransition(ManagementOCU_ManagementOcuFSMContext context, InternalEvent ie)
                {

                    ManagementOCU_ManagementOcuFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.HandleMenu.InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControlTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.sendRequestControlAction();
                        ctxt.displayMenuAction();
                        ctxt.processNotifications("WaitForControl", ie);
                    }
                    finally
                    {
                        context.State = ManagementOCU_ManagementOcuFSM_SM.WaitForControl;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void MenuItemEnteredTransition(ManagementOCU_ManagementOcuFSMContext context, MenuItemEntered msg)
                {

                    ManagementOCU_ManagementOcuFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.HandleMenu.MenuItemEnteredTransition(, MenuItemEntered msg)");
#endif

                    if (!ctxt.isSelectionToEnd(msg))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.sendManagementMessageAction(msg);
                            ctxt.displayMenuAction();
                            ctxt.processNotifications("HandleMenu", null);
                        }
                        finally
                        {
                            context.State = ManagementOCU_ManagementOcuFSM_SM.HandleMenu;
                            context.State.Entry(context);
                        }

                    }
                    else if (ctxt.isSelectionToEnd(msg))
                    {

                        context.State.Exit(context);
                        context.ClearState();

                        try
                        {
                            ctxt.terminateServiceAction();
                            ctxt.displayMenuAction();
                            ctxt.processNotifications("HandleMenu", null);
                        }
                        finally
                        {
                            context.State = ManagementOCU_ManagementOcuFSM_SM.HandleMenu;
                            context.State.Entry(context);
                        }

                    }                    else                    {
                        base.MenuItemEnteredTransition(context, msg);
                    }

                    return;
                }

                protected internal override void RejectControlTransition(ManagementOCU_ManagementOcuFSMContext context, RejectControl msg)
                {

                    ManagementOCU_ManagementOcuFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.HandleMenu.RejectControlTransition(, RejectControl msg)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.sendRequestControlAction();
                        ctxt.displayMenuAction();
                        ctxt.processNotifications("WaitForControl", null);
                    }
                    finally
                    {
                        context.State = ManagementOCU_ManagementOcuFSM_SM.WaitForControl;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReportStatusTransition(ManagementOCU_ManagementOcuFSMContext context, ReportStatus msg)
                {

                    ManagementOCU_ManagementOcuFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.HandleMenu.ReportStatusTransition(, ReportStatus msg)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.displayStatusAction(msg);
                        ctxt.displayMenuAction();
                        ctxt.processNotifications("HandleMenu", null);
                    }
                    finally
                    {
                        context.State = ManagementOCU_ManagementOcuFSM_SM.HandleMenu;
                        context.State.Entry(context);
                    }

                    return;
                }
            }

            internal class ManagementOCU_ManagementOcuFSM_SM_Internally_Generated_State_DO_NOT_USE :
                ManagementOCU_ManagementOcuFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal ManagementOCU_ManagementOcuFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void ConfirmControlTransition(ManagementOCU_ManagementOcuFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.Internally_Generated_State_DO_NOT_USE.ConfirmControlTransition()");
#endif


                    return;
                }

                protected internal override void MenuItemEnteredTransition(ManagementOCU_ManagementOcuFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.Internally_Generated_State_DO_NOT_USE.MenuItemEnteredTransition()");
#endif


                    return;
                }

                protected internal override void RejectControlTransition(ManagementOCU_ManagementOcuFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.Internally_Generated_State_DO_NOT_USE.RejectControlTransition()");
#endif


                    return;
                }

                protected internal override void ReportControlTransition(ManagementOCU_ManagementOcuFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportControlTransition()");
#endif


                    return;
                }

                protected internal override void ReportStatusTransition(ManagementOCU_ManagementOcuFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : ManagementOCU_ManagementOcuFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportStatusTransition()");
#endif


                    return;
                }
            }
        }
    }

}
