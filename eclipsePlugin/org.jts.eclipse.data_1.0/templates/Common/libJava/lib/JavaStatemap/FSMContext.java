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
// Copyright (C) 2000 - 2009. Charles W. Rapp.
// All Rights Reserved.
// 
// Contributor(s): 
//
// statemap.java --
//
//  This package defines the FSMContext class which must be
//  inherited by any Java class wanting to use an smc-generated
//  state machine.
//
// RCS ID
// $Id: FSMContext.java,v 1.14 2009/11/24 20:42:39 cwrapp Exp $
//
// CHANGE LOG
// (See the bottom of this file.)
//

package statemap;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.EmptyStackException;

/**
 * Base class for all SMC-generated application context classes.
 * This class stores the FSM name, current and previous states,
 * the state stack, debugging information and state change
 * listeners.
 *
 * @author <a href="mailto:rapp@acm.org">Charles Rapp</a>
 */

public abstract class FSMContext
    implements Serializable
{
//---------------------------------------------------------------
// Member functions
//

    //-----------------------------------------------------------
    // Constructors.
    //

    /**
     * Creates a finite state machine context for the given
     * initial state.
     * @param initState the finite state machine's start state.
     */
    protected FSMContext(State initState)
    {
        _name = "FSMContext";
        _state = initState;
        _transition = "";
        _previousState = null;
        _stateStack = null;
        _debugFlag = false;
        _debugStream = System.err;
        _listeners = new PropertyChangeSupport(this);
    } // end of FSMContext(State)

    //
    // end of Constructors.
    //-----------------------------------------------------------

    //-----------------------------------------------------------
    // Abstract method declarations.
    //

    /**
     * Starts the finite state machine running by executing the
     * initial state's entry actions.
     */
    public abstract void enterStartState();

    //
    // end of Abstract method declarations.
    //-----------------------------------------------------------

    //-----------------------------------------------------------
    // Serializable Interface Implementation.
    //

    private void readObject(ObjectInputStream istream)
        throws IOException,
               ClassNotFoundException
    {
        istream.defaultReadObject();

        // Create an empty listeners list.
        _listeners = new PropertyChangeSupport(this);

        return;
    } // end of readObject(ObjectInputStream)

    //
    // end of Serializable Interface Implementation.
    //-----------------------------------------------------------

    //-----------------------------------------------------------
    // Get methods.
    //

    /**
     * Returns the FSM name.
     * @return the FSM name.
     */
    public String getName()
    {
        return (_name);
    } // end of getName()

    /**
     * When debug is set to {@code true}, the state machine
     * will print messages to the console.
     * @return {@code true} if debug output is generated.
     */
    public boolean getDebugFlag()
    {
        return(_debugFlag && _debugStream != null);
    } // end of getDebugFlag()

    /**
     * Writes the debug output to this stream.
     * @return the debug output stream.
     */
    public PrintStream getDebugStream()
    {
        return (_debugStream == null ?
                System.err :
                _debugStream);
    } // end of getDebugStream()

    /**
     * Returns {@code true} if this FSM is in a transition and
     * {@code false} otherwise.
     * @return {@code true} if this FSM is in a transition and
     * {@code false} otherwise.
     */
    public boolean isInTransition()
    {
        return(_state == null ? true : false);
    } // end of isInTransition()

    // NOTE: getState() is defined in the SMC-generated
    // FSMContext subclass.
    
    /**
     * If this FSM is in transition, then returns the previous
     * state which the last transition left.
     * @return the previous state which the current transition
     * left. May return {@code null}.
     */
    public State getPreviousState()
        throws NullPointerException
    {
        return(_previousState);
    } // end of getPreviousState()

    /**
     * If this FSM is in transition, then returns the transition
     * name. If not in trnasition, then returns an empty string.
     * @return the current transition name.
     */
    public String getTransition()
    {
        return(_transition);
    } // end of getTransition()

    //
    // end of Get methods.
    //-----------------------------------------------------------

    //-----------------------------------------------------------
    // Set methods.
    //

    /**
     * Sets the FSM name.
     * @param name The finite state machine name.
     */
    public void setName(String name)
    {
        if (name != null &&
            name.length() > 0 &&
            name.equals(_name) == false)
        {
            _name = name;
        }

        return;
    } // end of setName(String)

    /**
     * Turns debug output on if {@code flag} is {@code true} and
     * off if {@code flag} is {@code false}.
     * @param flag {@code true} to turn debuggin on and
     * {@code false} to turn debugging off.
     */
    public void setDebugFlag(boolean flag)
    {
        _debugFlag = flag;
        return;
    } // end of setDebugFlag(boolean)

    /**
     * Sets the debug output stream to the given value.
     * @param stream The debug output stream.
     */
    public void setDebugStream(PrintStream stream)
    {
        _debugStream = stream;
        return;
    } // end of setDebugStream(PrintStream)

    /**
     * Sets the current state to the given value.
     * @param state The current state.
     */
    public void setState(State state)
    {
        if (getDebugFlag() == true)
        {
            getDebugStream().println("ENTER STATE     : " +
                                     state.getName());
        }

        _state = state;

        // Inform any and all listeners about this state
        // change.
        _listeners.firePropertyChange(
            STATE_PROPERTY, _previousState, _state);

        return;
    } // end of setState(State)

    /**
     * Places the current state into the previous state sets
     * the current state to {@code null}.
     */
    public void clearState()
    {
        _previousState = _state;
        _state = null;

        return;
    } // end of clearState()

    /**
     * Pushes the current state on top of the state stack and
     * sets the current state to {@code state}.
     * @param state The new current state.
     * @exception NullPointerException
     * if {@code state} is {@code null}.
     */
    public void pushState(State state)
    {
        State previousState = _state;

        if (_state == null)
        {
            throw (new NullPointerException());
        }

        if (getDebugFlag() == true)
        {
            getDebugStream().println("PUSH TO STATE   : " +
                                     state.getName());
        }

        if (_stateStack == null)
        {
            _stateStack = new java.util.Stack<State>();
        }

        _stateStack.push(_state);
        _state = state;

        // Inform any and all listeners about this state
        // change.
        _listeners.firePropertyChange(
            STATE_PROPERTY, previousState, _state);

        return;
    } // end of pushState(State)

    /**
     * Sets the previous state to the current state and pops
     * the top state off the stack and places it into the
     * current state.
     * @exception EmptyStackException
     * if the state stack is empty.
     */
    public void popState()
        throws EmptyStackException
    {
        if (_stateStack == null ||
            _stateStack.isEmpty() == true)
        {
            if (getDebugFlag() == true)
            {
                getDebugStream().println(
                    "POPPING ON EMPTY STATE STACK.");
            }

            throw (new EmptyStackException());
        }
        else
        {
            State previousState = _state;

            // The pop method removes the top element
            // from the stack and returns it.
            _state = _stateStack.pop();

            if (_stateStack.isEmpty() == true)
            {
                _stateStack = null;
            }

            if (getDebugFlag() == true)
            {
                getDebugStream().println("POP TO STATE    : " +
                                      _state.getName());
            }

            // Inform any and all listeners about this state
            // change.
            _listeners.firePropertyChange(
                STATE_PROPERTY, previousState, _state);
        }

        return;
    } // end of popState()

    /**
     * Empties the state stack.
     */
    public void emptyStateStack()
    {
        if (_stateStack != null)
        {
            _stateStack.clear();
            _stateStack = null;
        }

        return;
    } // end of emptyStateStack()
	
	/**
	* Returns the name of the state on the top of the stack.
	*
	*/
	public String peakTopStackState()
	{
		if(_stateStack != null)
		{
			return _stateStack.peek().getName();
		}
		return null;	
	}
	

    //
    // end of Set methods.
    //-----------------------------------------------------------

    // The following methods allow listeners to watch this
    // finite state machine for state changes.
    // Note: if a transition does not cause a state change,
    // then no state change event is fired.

    /**
     * Adds a PropertyChangeListener to the listener list. The
     * listener is registered for state property changes only.
     * The same listener may be added more than once. For each
     * state change, the listener will be invoked the number of
     * times it was added. If {@code listener} is {@code null},
     * no exception is thrown and no action is taken.
     * @param listener The PropertyChangeListener to be added.
     */
    public void
        addStateChangeListener(PropertyChangeListener listener)
    {
        _listeners.addPropertyChangeListener(
            STATE_PROPERTY, listener);
        return;
    }

    /**
     * Removes a PropertyChangeListener for the state change
     * property. If {@code listener} was added more than once
     * to the same event source, it will be notified one less
     * time after being removed. If {@code listener} is
     * {@code null} or was never added, no exception is thrown
     * and no action is taken.
     * @param listener The PropertyChangeListener to be removed.
     */
    public void
        removeStateChangeListener(
            PropertyChangeListener listener)
    {
        _listeners.removePropertyChangeListener(
            STATE_PROPERTY, listener);
        return;
    } // end of removeStateChangeListener(PropertyChangeListener)

//---------------------------------------------------------------
// Member data
//

    /**
     * The FSM name.
     */
    transient protected String _name;

    /**
     * The current state. Will be {@code null} while in
     * transition.
     */
    transient protected State _state;

    /**
     * The current transition name. Used for debugging
     * purposes. Will be en empty string when not in
     * transition.
     */
    transient protected String _transition;

    /**
     * Stores which state a transition left. May be {@code null}.
     */
    transient protected State _previousState;

    /**
     * This stack is used to store the current state when a push
     * transition is taken.
     */
    transient protected java.util.Stack<State> _stateStack;

    /**
     * When this flag is set to {@code true}, this class will
     * print out debug messages.
     */
    transient protected boolean _debugFlag;

    /**
     * Write debug output to this stream.
     */
    transient protected PrintStream _debugStream;

    // Stores the property change listeners here.
    transient private PropertyChangeSupport _listeners;

    //-----------------------------------------------------------
    // Constants.
    //
    private static final long serialVersionUID = 0x060000L;
    private static final String STATE_PROPERTY = "State";
} // end of class FSMContext

//
// CHANGE LOG
// $Log: FSMContext.java,v $
// Revision 1.14  2009/11/24 20:42:39  cwrapp
// v. 6.0.1 update
//
// Revision 1.13  2009/09/05 15:39:20  cwrapp
// Checking in fixes for 1944542, 1983929, 2731415, 2803547 and feature 2797126.
//
// Revision 1.12  2009/03/27 09:41:07  cwrapp
// Added F. Perrad changes back in.
//
// Revision 1.11  2009/03/01 18:20:40  cwrapp
// Preliminary v. 6.0.0 commit.
//
// Revision 1.10  2008/01/14 19:59:23  cwrapp
// Release 5.0.2 check-in.
//
// Revision 1.9  2007/08/05 13:00:34  cwrapp
// Version 5.0.1 check-in. See net/sf/smc/CODE_README.txt for more information.
//
// Revision 1.8  2007/02/21 13:50:59  cwrapp
// Moved Java code to release 1.5.0
//
// Revision 1.7  2005/05/28 18:44:13  cwrapp
// Updated C++, Java and Tcl libraries, added CSharp, Python
// and VB.
//
// Revision 1.1  2005/02/21 19:03:38  charlesr
// Variable name clean up.
//
// Revision 1.0  2003/12/14 20:38:40  charlesr
// Initial revision
//
