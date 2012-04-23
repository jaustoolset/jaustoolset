//
// The contents of this file are subject to the Mozilla Public
// License Version 1.1 (the "License"); you may not use this file
// except in compliance with the License. You may obtain a copy of
// the License at http://www.mozilla.org/MPL/
// 
// Software distributed under the License is distributed on an "AS
// IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
// implied. See the License for the specific language governing
// rights and limitations under the License.
// 
// The Original Code is State Machine Compiler (SMC).
// 
// The Initial Developer of the Original Code is Charles W. Rapp.
// Portions created by Charles W. Rapp are
// Copyright (C) 2000 - 2005 Charles W. Rapp.
// All Rights Reserved.
// 
// Contributor(s): 
//
// statemap.java --
//
//  This package defines the fsmContext class which must be inherited by
//  any Java class wanting to use an smc-generated state machine.
//
// RCS ID
// $Id: State.java,v 1.7 2009/03/27 09:41:07 cwrapp Exp $
//
// CHANGE LOG
// (See the bottom of this file.)
//

package statemap;

import java.io.Serializable;

/**
 * Base class for all SMC-generated context states. This class
 * stores the state name and unique integer identifier.
 *
 * @author <a href="mailto:rapp@acm.org">Charles Rapp</a>
 */

public abstract class State
    implements Serializable
{
//---------------------------------------------------------------
// Member functions
//

    //-----------------------------------------------------------
    // Constructors.
    //

    /**
     * Creates a state instance with the given name and unique
     * integer identifier.
     * @param name The state name.
     * @param id The state unique identifier.
     */
    protected State(String name, int id)
    {
        _name = name;
        _id = id;
    } // end of State(String, int)

    //
    // end of Constructors.
    //-----------------------------------------------------------

    //-----------------------------------------------------------
    // Get methods.
    //

    /**
     * Returns the state name.
     * @return the state name.
     */
    public String getName()
    {
        return (_name);
    } // end of getName()

    /**
     * Returns the unique integer identifier.
     * @return the unique integer identifier.
     */
    public int getId()
    {
        return (_id);
    } // end of getId()

    //
    // end of Get methods.
    //-----------------------------------------------------------

    /**
     * Returns the state name.
     * @return the state name.
     */
    @Override
    public String toString()
    {
        return (_name);
    } // end of toString()

//---------------------------------------------------------------
// Member data
//

    private final String _name;
    private final int _id;

    //-----------------------------------------------------------
    // Constants.
    //

    // Transition reflection values.

    /**
     * Zero (0) means the transition is undefined.
     */
    public static final Integer TRANSITION_UNDEFINED =
        new Integer(0);

    /**
     * One (1) means the transition is defined in the current
     * state.
     */
    public static final Integer TRANSITION_DEFINED_LOCALLY =
        new Integer(1);
    /**
     * Two (2) means the transition is defined in the default
     * state.
     */
    public static final Integer TRANSITION_DEFINED_DEFAULT =
        new Integer(2);

    private static final long serialVersionUID = 0x060000L;
} // end of class State

//
// CHANGE LOG
// $Log: State.java,v $
// Revision 1.7  2009/03/27 09:41:07  cwrapp
// Added F. Perrad changes back in.
//
// Revision 1.6  2005/06/03 19:58:29  cwrapp
// Further updates for release 4.0.0
//
// Revision 1.5  2005/05/28 18:44:13  cwrapp
// Updated C++, Java and Tcl libraries, added CSharp, Python and VB.
//
// Revision 1.0  2003/12/14 20:39:41  charlesr
// Initial revision
//
