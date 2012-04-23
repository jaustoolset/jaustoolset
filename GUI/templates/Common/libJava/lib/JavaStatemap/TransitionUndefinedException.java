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
// The Original Code is  State Machine Compiler(SMC).
// 
// The Initial Developer of the Original Code is Charles W. Rapp.
// Portions created by Charles W. Rapp are
// Copyright (C) 2000 - 2003 Charles W. Rapp.
// All Rights Reserved.
// 
// Contributor(s): 
//
// statemap.java --
//
//  This package defines the FSMContext class which must be inherited by
//  any Java class wanting to use an smc-generated state machine.
//
// RCS ID
// $Id: TransitionUndefinedException.java,v 1.5 2009/03/27 09:41:07 cwrapp Exp $
//
// Change Log
// $Log: TransitionUndefinedException.java,v $
// Revision 1.5  2009/03/27 09:41:07  cwrapp
// Added F. Perrad changes back in.
//
// Revision 1.4  2005/05/28 18:44:13  cwrapp
// Updated C++, Java and Tcl libraries, added CSharp, Python and VB.
//
// Revision 1.0  2003/12/14 20:40:47  charlesr
// Initial revision
//

package statemap;

/**
 * A <code>TransitionUndefinedException</code> is thrown by
 * an SMC-generated state machine whenever a transition is taken
 * which:
 * <ol>
 *   <li>
 *     Is not explicitly defined in the current state <i>and</i>
 *   </li>
 *   <li>
 *     Is not explicitly defined in the current FSM's default
 *     state <i>and</i>
 *   </li>
 *   <li>
 *     There is no Default transition in the current state.
 *   </li>
 * </ol>
 *
 * @author <a href="mailto:rapp@acm.org">Charles Rapp</a>
 */

public final class TransitionUndefinedException
    extends RuntimeException
{
//---------------------------------------------------------------
// Member methods.
//

    //-----------------------------------------------------------
    // Constructors.
    //

    /**
     * Constructs a <code>TransitionUndefinedException</code>
     * with no detail message.
     */
    public TransitionUndefinedException()
    {
        super();
    } // end of TransitionUndefinedException()

    /**
     * Constructs a <code>TransitionUndefinedException</code>
     * with a detail message.
     * @param reason the detail message.
     */
    public TransitionUndefinedException(String reason)
    {
        super(reason);
    } // end of TransitionUndefinedException(String)

    //
    // end of Constructors.
    //-----------------------------------------------------------

//---------------------------------------------------------------
// Member data.
//

    //-----------------------------------------------------------
    // Constants.
    //
    private static final long serialVersionUID = 0x060000L;
} // end fo class TransitionUndefinedException
