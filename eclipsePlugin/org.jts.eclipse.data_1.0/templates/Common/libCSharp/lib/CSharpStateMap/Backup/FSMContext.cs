//
// The contents of this file are subject to the Mozilla Public
// License Version 1.1 (the "License"); you may not use this file
// except in compliance with the License. You may obtain a copy
// of the License at http://www.mozilla.org/MPL/
// 
// Software distributed under the License is distributed on an
// "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
// implied. See the License for the specific language governing
// rights and limitations under the License.
// 
// The Original Code is  State Machine Compiler(SMC).
// 
// The Initial Developer of the Original Code is Charles W. Rapp.
// Portions created by Charles W. Rapp are
// Copyright (C) 2004. Charles W. Rapp.
// All Rights Reserved.
// 
// Contributor(s):
//   Eitan Suez contributed examples/Ant.
//   (Name withheld) contributed the C# code generation and
//   examples/C#.
//
// RCS ID
// $Id: FSMContext.cs,v 1.8 2009/12/17 19:51:43 cwrapp Exp $
//
// statemap.java --
//
//  This package defines the FSMContext class which is inherited
//  by the smc-generated application FSM context class.
//
// CHANGE LOG
// (See the bottom of this file.)
//

using System;
using System.IO;
using System.Diagnostics;

namespace statemap
{
    // State change event. Contains the previous and new state.
    public class StateChangeEventArgs :
        EventArgs
    {
    //-----------------------------------------------------------
    // Member functions.
    //

        // Constructor.
        public StateChangeEventArgs(string fsmName,
                                    string transitionType,
                                    State previousState,
                                    State newState)
        {
            _fsmName = fsmName;
            _transitionType = transitionType;
            _previousState = previousState;
            _newState = newState;
        } // end of StateChangeEventArgs(string, State, State)

        public string FSMName()
        {
            return (_fsmName);
        } // end of FSMName()

        public string TransitionType()
        {
            return (_transitionType);
        } // end of TransitionType()

        public State PreviousState()
        {
            return (_previousState);
        } // end of PreviousState()

        public State NewState()
        {
            return (_newState);
        } // end of NewState()

    //-----------------------------------------------------------
    // Member data.
    //

        private readonly string _fsmName;
        private readonly string _transitionType;
        private readonly State _previousState;
        private readonly State _newState;
    } // end of class StateChangeEventArgs

    // Delegate declaration.
    public delegate void StateChangeEventHandler(
         object sender, StateChangeEventArgs args);

    // statemap.FSMContext --
    //
    //  Base class for the smc-generated application FSM
    //  context class.

    [Serializable]
    public abstract class FSMContext
    {
    //-----------------------------------------------------------
    // Member functions.
    //

        public FSMContext(State state)
        {
            // There is no state until the application explicitly
            // sets the initial state.
            name_ = "FSMContext";
            state_ = state;
            transition_ = "";
            previousState_ = null;
            stateStack_ = new System.Collections.Stack();
            debugFlag_ = false;
            debugStream_ = null;
        } // end of FSMContext()

        // The state change event.
        public event StateChangeEventHandler StateChange;

        // Starts the finite state machine running by executing
        // the initial state's entry action.
        public abstract void EnterStartState();

        // The finite state machine name property.
        public string Name
        {
            get
            {
                return (name_);
            }
            set
            {
                name_ = value;
            }
        } // end of Name()

        // DEPRECATED
        // As of v. 4.3.3, System.Diagnostics.Trace is
        // used instead of the debugFlag_, debugStream_
        // pair.
        // Used to enable debugging output
        public bool Debug
        {
            get
            {
                return debugFlag_;
            }
            set
            {
                debugFlag_ = value;
            }
        }

        // DEPRECATED
        // As of v. 4.3.3, System.Diagnostics.Trace is
        // used instead of the debugFlag_, debugStream_
        // pair. debugStream_ will always be null.
        // Used to set the output text writer.
        public TextWriter DebugStream
        {
            get
            {
                return debugStream_;
            }
            set
            {
                // DEPRECATED.
                // debugStream_ = value;
            }
        }

        // Is this state machine in a transition? If state is
        // null, then true; otherwise, false.
        public bool InTransition
        {
            get 
            {
                return(state_ == null ? true : false);
            }
        }

        public void SetState(State state)
        {
            StateChangeEventArgs e =
                new StateChangeEventArgs(
                    name_, "SET", state_, state);

#if TRACE
            Trace.WriteLine("ENTER STATE     : " +    state.Name);
#endif

            state_ = state;

            OnStateChange(e);

            return;
        }

        public void ClearState()
        {
            previousState_ = state_;
            state_ = null;

            return;
        }

        public State PreviousState
        {
            get
            {
                if (previousState_ != null)
                {
                    return(previousState_);
                }

                throw
                    new System.NullReferenceException(
                        "Previous state not set.");
            }
        }

        public void PushState(State state)
        {
            StateChangeEventArgs e =
                new StateChangeEventArgs(
                    name_, "PUSH", state_, state);

#if TRACE
            Trace.WriteLine("PUSH TO STATE   : " +    state.Name);
#endif

            if (state_ != null)
            {

                stateStack_.Push(state_);
            }

            state_ = state;

            OnStateChange(e);

            return;
        }

        public void PopState()
        {
            if (stateStack_.Count == 0)
            {
#if TRACE
                Trace.WriteLine("POPPING ON EMPTY STATE STACK.");
#endif

                throw new
                    System.InvalidOperationException(
                        "popping an empty state stack");
            }
            else
            {
                State nextState = (State) stateStack_.Pop();
                StateChangeEventArgs e =
                    new StateChangeEventArgs(
                        name_, "POP", state_, nextState);

                // The pop method removes the top element
                // from the stack and returns it.
                state_ = nextState;

#if TRACE
                Trace.WriteLine("POP TO STATE    : " + state_.Name);
#endif

                OnStateChange(e);
            }

            return;
        }

        public void EmptyStateStack()
        {
            stateStack_.Clear();
        }

        public string GetTransition()
        {
            return transition_;
        }

        // Release all acquired resources.
        ~FSMContext()  //TODO: Add disposable
        {
            name_ = null;
            state_ = null;
            transition_ = null;
            previousState_ = null;
            stateStack_ = null;
        }

        protected virtual void OnStateChange(StateChangeEventArgs e)
        {
            if (StateChange != null)
            {
                StateChange(this, e);
            }

            return;;
        } // end of OnStateChange(StateChangeEventArgs)

    //-----------------------------------------------------------
    // Member data
    //

        // The finite state machine's unique name.
        [NonSerialized]
        protected string name_;

        // The current state.
        [NonSerialized]
        protected State state_;

        // The current transition *name*. Used for debugging
        // purposes.
        [NonSerialized]
        protected string transition_;

        // Remember what state a transition left.
        // Do no persist the previous state because an FSM should
        // be serialized while in transition.
        [NonSerialized]
        protected State previousState_;

        // This stack is used when a push transition is taken.
        [NonSerialized]
        protected System.Collections.Stack stateStack_;

        // DEPRECATED
        // As of v. 4.3.3, System.Diagnostics.Trace is
        // used instead of the debugFlag_, debugStream_
        // pair. debugStream_ will always be null.
        // When this flag is set to true, this class will print
        // out debug messages.
        [NonSerialized]
        protected bool debugFlag_;

        // Write debug output to this stream.
        [NonSerialized]
        protected TextWriter debugStream_;
    } // end of class FSMContext
} // end of namespace statemap

//
// CHANGE LOG
// $Log: FSMContext.cs,v $
// Revision 1.8  2009/12/17 19:51:43  cwrapp
// Testing complete.
//
// Revision 1.7  2009/03/30 19:05:44  kgreg99
// 1. Patch for bug  #2722356. Variable stateStack_ is initialized in the constructor and not by the first "push". This prevents an unhandled exception in EmptyStateStack() without pushing a state.
//
// Revision 1.6  2009/03/01 18:20:40  cwrapp
// Preliminary v. 6.0.0 commit.
//
// Revision 1.5  2008/01/14 19:59:23  cwrapp
// Release 5.0.2 check-in.
//
// Revision 1.4  2006/09/16 15:04:28  cwrapp
// Initial v. 4.3.3 check-in.
//
// Revision 1.3  2006/06/03 19:39:25  cwrapp
// Final v. 4.3.1 check in.
//
// Revision 1.2  2006/04/22 12:45:25  cwrapp
// Version 4.3.1
//
// Revision 1.1  2005/05/28 18:44:13  cwrapp
// Updated C++, Java and Tcl libraries, added CSharp, Python and VB.
//
// Revision 1.0  2004/09/06 16:32:15  charlesr
// Initial revision
//
