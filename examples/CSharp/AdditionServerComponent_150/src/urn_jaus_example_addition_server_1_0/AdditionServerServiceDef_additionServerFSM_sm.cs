using System;
using System.Diagnostics;

using JTS;
namespace urn_jaus_example_addition_server_1_0
{
    public sealed class AdditionServerServiceDef_additionServerFSMContext :
        statemap.FSMContext
    {
    //---------------------------------------------------------------
    // Properties.
    //

        public AdditionServerServiceDef_additionServerFSMState State
        {
            get
            {
                if (state_ == null)
                {
                    throw(
                        new statemap.StateUndefinedException());
                }

                return ((AdditionServerServiceDef_additionServerFSMState) state_);
            }
            set
            {
                SetState(value);
            }
        }

        public AdditionServerServiceDef_additionServerFSM Owner
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

        public AdditionServerServiceDef_additionServerFSMContext(AdditionServerServiceDef_additionServerFSM owner) :
            base (AdditionServerServiceDef_additionServerFSM_SM.Init)
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

        public void InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_InitTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_InitTransition";
            State.InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_InitTransition(this, ie);
            transition_ = "";
            return;
        }

        public void InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_ReadyTransition(InternalEvent ie)
        {
            transition_ = "InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_ReadyTransition";
            State.InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_ReadyTransition(this, ie);
            transition_ = "";
            return;
        }

        public void QueryAdditionTransition()
        {
            transition_ = "QueryAdditionTransition";
            State.QueryAdditionTransition(this);
            transition_ = "";
            return;
        }

        public void QueryAdditionTransition(QueryAddition msg, uint sender)
        {
            transition_ = "QueryAdditionTransition";
            State.QueryAdditionTransition(this, msg, sender);
            transition_ = "";
            return;
        }

    //---------------------------------------------------------------
    // Member data.
    //

        [NonSerialized]
        private AdditionServerServiceDef_additionServerFSM _owner;

    //---------------------------------------------------------------
    // Inner classes.
    //

        public abstract class AdditionServerServiceDef_additionServerFSMState :
            statemap.State
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal AdditionServerServiceDef_additionServerFSMState(string name, int id) :
                base (name, id)
            {}

            protected internal virtual void Entry(AdditionServerServiceDef_additionServerFSMContext context)
            {}

            protected internal virtual void Exit(AdditionServerServiceDef_additionServerFSMContext context)
            {}

            protected internal virtual void InitToReadyEventDefTransition(AdditionServerServiceDef_additionServerFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_InitTransition(AdditionServerServiceDef_additionServerFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_ReadyTransition(AdditionServerServiceDef_additionServerFSMContext context, InternalEvent ie)
            {
                Default(context);
            }

            protected internal virtual void QueryAdditionTransition(AdditionServerServiceDef_additionServerFSMContext context)
            {
                Default(context);
            }

            protected internal virtual void QueryAdditionTransition(AdditionServerServiceDef_additionServerFSMContext context, QueryAddition msg, uint sender)
            {
                Default(context);
            }

            protected internal virtual void Default(AdditionServerServiceDef_additionServerFSMContext context)
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

        internal abstract class AdditionServerServiceDef_additionServerFSM_SM
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
            internal static readonly AdditionServerServiceDef_additionServerFSM_SM_Default.AdditionServerServiceDef_additionServerFSM_SM_Init Init =
                new AdditionServerServiceDef_additionServerFSM_SM_Default.AdditionServerServiceDef_additionServerFSM_SM_Init("AdditionServerServiceDef_additionServerFSM_SM.Init", 0);
            [NonSerialized]
            internal static readonly AdditionServerServiceDef_additionServerFSM_SM_Default.AdditionServerServiceDef_additionServerFSM_SM_Ready Ready =
                new AdditionServerServiceDef_additionServerFSM_SM_Default.AdditionServerServiceDef_additionServerFSM_SM_Ready("AdditionServerServiceDef_additionServerFSM_SM.Ready", 1);
            [NonSerialized]
            internal static readonly AdditionServerServiceDef_additionServerFSM_SM_Default.AdditionServerServiceDef_additionServerFSM_SM_Internally_Generated_State_DO_NOT_USE Internally_Generated_State_DO_NOT_USE =
                new AdditionServerServiceDef_additionServerFSM_SM_Default.AdditionServerServiceDef_additionServerFSM_SM_Internally_Generated_State_DO_NOT_USE("AdditionServerServiceDef_additionServerFSM_SM.Internally_Generated_State_DO_NOT_USE", 2);
            [NonSerialized]
            private static readonly AdditionServerServiceDef_additionServerFSM_SM_Default Default =
                new AdditionServerServiceDef_additionServerFSM_SM_Default("AdditionServerServiceDef_additionServerFSM_SM.Default", -1);

        }

        internal class AdditionServerServiceDef_additionServerFSM_SM_Default :
            AdditionServerServiceDef_additionServerFSMState
        {
        //-----------------------------------------------------------
        // Member methods.
        //

            internal AdditionServerServiceDef_additionServerFSM_SM_Default(string name, int id) :
                base (name, id)
            {}

        //-----------------------------------------------------------
        // Inner classes.
        //

            internal class AdditionServerServiceDef_additionServerFSM_SM_Init :
                AdditionServerServiceDef_additionServerFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AdditionServerServiceDef_additionServerFSM_SM_Init(string name, int id) :
                    base (name, id)
                {}

                protected internal override void InitToReadyEventDefTransition(AdditionServerServiceDef_additionServerFSMContext context)
                {

                    AdditionServerServiceDef_additionServerFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionServerServiceDef_additionServerFSM_SM.Init.InitToReadyEventDefTransition()");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.serverInitializedAction();
                        ctxt.processNotifications("Ready", null);
                    }
                    finally
                    {
                        context.State = AdditionServerServiceDef_additionServerFSM_SM.Ready;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_ReadyTransition(AdditionServerServiceDef_additionServerFSMContext context, InternalEvent ie)
                {

                    AdditionServerServiceDef_additionServerFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionServerServiceDef_additionServerFSM_SM.Init.InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_ReadyTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.processNotifications("Ready", ie);
            }
                    finally
                    {
                        context.State = AdditionServerServiceDef_additionServerFSM_SM.Ready;
                        context.State.Entry(context);
                    }

                    return;
                }
            }

            internal class AdditionServerServiceDef_additionServerFSM_SM_Ready :
                AdditionServerServiceDef_additionServerFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AdditionServerServiceDef_additionServerFSM_SM_Ready(string name, int id) :
                    base (name, id)
                {}

                protected internal override void InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_InitTransition(AdditionServerServiceDef_additionServerFSMContext context, InternalEvent ie)
                {

                    AdditionServerServiceDef_additionServerFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionServerServiceDef_additionServerFSM_SM.Ready.InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_InitTransition(, InternalEvent ie)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.fsmStartedAction();
                        ctxt.processNotifications("Init", ie);
                    }
                    finally
                    {
                        context.State = AdditionServerServiceDef_additionServerFSM_SM.Init;
                        context.State.Entry(context);
                    }

                    return;
                }

                protected internal override void QueryAdditionTransition(AdditionServerServiceDef_additionServerFSMContext context, QueryAddition msg, uint sender)
                {

                    AdditionServerServiceDef_additionServerFSM ctxt = context.Owner;


#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionServerServiceDef_additionServerFSM_SM.Ready.QueryAdditionTransition(, QueryAddition msg, uint sender)");
#endif


                    context.State.Exit(context);
                    context.ClearState();

                    try
                    {
                        ctxt.sendReportAdditionAction(msg, sender);
                        ctxt.processNotifications("Ready", null);
                    }
                    finally
                    {
                        context.State = AdditionServerServiceDef_additionServerFSM_SM.Ready;
                        context.State.Entry(context);
                    }

                    return;
                }
            }

            internal class AdditionServerServiceDef_additionServerFSM_SM_Internally_Generated_State_DO_NOT_USE :
                AdditionServerServiceDef_additionServerFSM_SM_Default
            {
            //-------------------------------------------------------
            // Member methods.
            //

                internal AdditionServerServiceDef_additionServerFSM_SM_Internally_Generated_State_DO_NOT_USE(string name, int id) :
                    base (name, id)
                {}

                protected internal override void InitToReadyEventDefTransition(AdditionServerServiceDef_additionServerFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionServerServiceDef_additionServerFSM_SM.Internally_Generated_State_DO_NOT_USE.InitToReadyEventDefTransition()");
#endif


                    return;
                }

                protected internal override void QueryAdditionTransition(AdditionServerServiceDef_additionServerFSMContext context)
                {

#if TRACE
                    Trace.WriteLine(
                        "TRANSITION   : AdditionServerServiceDef_additionServerFSM_SM.Internally_Generated_State_DO_NOT_USE.QueryAdditionTransition()");
#endif


                    return;
                }
            }
        }
    }

}
