using System;
using System.Diagnostics;

using JTS;
namespace urn_jaus_example_addition_client_1_0
{
    public sealed class AdditionClientServiceDef_additionClientFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public AdditionClientServiceDef_additionClientFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((AdditionClientServiceDef_additionClientFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public AdditionClientServiceDef_additionClientFSM Owner
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

        public AdditionClientServiceDef_additionClientFSMContext(AdditionClientServiceDef_additionClientFSM owner) :
            base (AdditionClientServiceDef_additionClientFSM_SM.Init)
        {
        _owner = owner;
        }

        public override void EnterStartState()
        {
            State.Entry(this);
            return;
        }

        public void InitToReadyEventDefTransition()
        {
            transition_ = "InitToReadyEventDefTransition";
            State.InitToReadyEventDefTransition(this);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_InitTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_InitTransition";
            State.InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_InitTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_ReadyTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_ReadyTransition";
            State.InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_ReadyTransition(this, ie);
            transition_ = "";
            return;
        }

        public void ReportAdditionTransition()
        {
            transition_ = "ReportAdditionTransition";
            State.ReportAdditionTransition(this);
            transition_ = "";
            return;
        }

        public void ReportAdditionTransition(ReportAddition msg)
        {
            transition_ = "ReportAdditionTransition";
            State.ReportAdditionTransition(this, msg);
            transition_ = "";
            return;
        }

    //---------------------------------------------------------------
    // Member data.
    //

        [NonSerialized]
        private AdditionClientServiceDef_additionClientFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class AdditionClientServiceDef_additionClientFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal AdditionClientServiceDef_additionClientFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(AdditionClientServiceDef_additionClientFSMContext context)
            {}

            protected internal virtual void Exit(AdditionClientServiceDef_additionClientFSMContext context)
            {}

            protected internal virtual void InitToReadyEventDefTransition(AdditionClientServiceDef_additionClientFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_InitTransition(AdditionClientServiceDef_additionClientFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_ReadyTransition(AdditionClientServiceDef_additionClientFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void ReportAdditionTransition(AdditionClientServiceDef_additionClientFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void ReportAdditionTransition(AdditionClientServiceDef_additionClientFSMContext context, ReportAddition msg)
            {
                Default(context);
            }

            protected internal virtual void Default(AdditionClientServiceDef_additionClientFSMContext context)
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

        internal abstract class AdditionClientServiceDef_additionClientFSM_SM
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
            internal static readonly AdditionClientServiceDef_additionClientFSM_SM_Default.AdditionClientServiceDef_additionClientFSM_SM_Init Init =
                new AdditionClientServiceDef_additionClientFSM_SM_Default.AdditionClientServiceDef_additionClientFSM_SM_Init("AdditionClientServiceDef_additionClientFSM_SM.Init", 0);
            [NonSerialized]
            internal static readonly AdditionClientServiceDef_additionClientFSM_SM_Default.AdditionClientServiceDef_additionClientFSM_SM_Ready Ready =
                new AdditionClientServiceDef_additionClientFSM_SM_Default.AdditionClientServiceDef_additionClientFSM_SM_Ready("AdditionClientServiceDef_additionClientFSM_SM.Ready", 1);
            [NonSerialized]
            internal static readonly AdditionClientServiceDef_additionClientFSM_SM_Default.AdditionClientServiceDef_additionClientFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new AdditionClientServiceDef_additionClientFSM_SM_Default.AdditionClientServiceDef_additionClientFSM_SM_Internally_Generated_State_DO_NOT_USE("AdditionClientServiceDef_additionClientFSM_SM.Internally_Generated_State_DO_NOT_USE", 2);
            [NonSerialized]
            private static readonly AdditionClientServiceDef_additionClientFSM_SM_Default Default =
                new AdditionClientServiceDef_additionClientFSM_SM_Default("AdditionClientServiceDef_additionClientFSM_SM.Default", -1);

        }

        internal class AdditionClientServiceDef_additionClientFSM_SM_Default :
            AdditionClientServiceDef_additionClientFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal AdditionClientServiceDef_additionClientFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class AdditionClientServiceDef_additionClientFSM_SM_Init :
                AdditionClientServiceDef_additionClientFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AdditionClientServiceDef_additionClientFSM_SM_Init(string name, int id) :
                    base (name, id)
                {}

                protected internal override void InitToReadyEventDefTransition(AdditionClientServiceDef_additionClientFSMContext context)
                {

                    AdditionClientServiceDef_additionClientFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionClientServiceDef_additionClientFSM_SM.Init.InitToReadyEventDefTransition()");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.serviceInitializedAction();
                        ctxt.processNotifications("Ready", null);
                    }
                    finally
                    {
                        context.State = AdditionClientServiceDef_additionClientFSM_SM.Ready;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_ReadyTransition(AdditionClientServiceDef_additionClientFSMContext context, InternalEvent ie)
                {

                    AdditionClientServiceDef_additionClientFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionClientServiceDef_additionClientFSM_SM.Init.InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Ready", ie);
            }
                    finally
                    {
                        context.State = AdditionClientServiceDef_additionClientFSM_SM.Ready;
                        context.State.Entry(context);
                    }

                    return;
                }
            }

            internal class AdditionClientServiceDef_additionClientFSM_SM_Ready :
                AdditionClientServiceDef_additionClientFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AdditionClientServiceDef_additionClientFSM_SM_Ready(string name, int id) :
                    base (name, id)
                {}

                protected internal override void InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_InitTransition(AdditionClientServiceDef_additionClientFSMContext context, InternalEvent ie)
                {

                    AdditionClientServiceDef_additionClientFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionClientServiceDef_additionClientFSM_SM.Ready.InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_InitTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.serviceStartedAction();
                        ctxt.processNotifications("Init", ie);
                    }
                    finally
                    {
                        context.State = AdditionClientServiceDef_additionClientFSM_SM.Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void ReportAdditionTransition(AdditionClientServiceDef_additionClientFSMContext context, ReportAddition msg)
                {

                    AdditionClientServiceDef_additionClientFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionClientServiceDef_additionClientFSM_SM.Ready.ReportAdditionTransition(, ReportAddition msg)");
#endif

                    AdditionClientServiceDef_additionClientFSMState endState = context.State;

                    context.ClearState();

                    try
                    {
                        ctxt.printAnswerToScreenAction(msg);
                    }
                    finally
                    {
                        context.State = endState;
                    }

                    return;
                }
            }

            internal class AdditionClientServiceDef_additionClientFSM_SM_Internally_Generated_State_DO_NOT_USE :
                AdditionClientServiceDef_additionClientFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AdditionClientServiceDef_additionClientFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void InitToReadyEventDefTransition(AdditionClientServiceDef_additionClientFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionClientServiceDef_additionClientFSM_SM.Internally_Generated_State_DO_NOT_USE.InitToReadyEventDefTransition()");
#endif


                    return;
                }

                protected internal override void ReportAdditionTransition(AdditionClientServiceDef_additionClientFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionClientServiceDef_additionClientFSM_SM.Internally_Generated_State_DO_NOT_USE.ReportAdditionTransition()");
#endif


                    return;
                }
            }
        }
    }

}
