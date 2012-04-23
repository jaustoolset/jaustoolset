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
// Copyright (C) 2004. Charles W. Rapp.
// All Rights Reserved.
// 
// Contributor(s):
//   Eitan Suez contributed examples/Ant.
//   (Name withheld) contributed the C# code generation and
//   examples/C#.
//
// RCS ID
// $Id: TransitionUndefinedException.cs,v 1.1 2005/05/28 18:44:13 cwrapp Exp $
//
// TransitionUndefinedException.cs --
//
//  This exception is thrown when the current state has no
//  definition for the transition, default or otherwise.
//
// Change Log
// $Log: TransitionUndefinedException.cs,v $
// Revision 1.1  2005/05/28 18:44:13  cwrapp
// Updated C++, Java and Tcl libraries, added CSharp, Python and VB.
//
// Revision 1.0  2004/09/06 16:33:19  charlesr
// Initial revision
//

using System;

namespace statemap
{
    // A TransitionUndefinedException is thrown by an
    // SMC-generated state machine whenever a transition is taken
    // which:
    //
    // 1. Is not explicitly defined in the current state.
    // 2. Is not explicitly defined in the current FSM's default
    //    state.
    // 3. There is no Default transition in the current state.
    //
    public sealed class TransitionUndefinedException :
        ApplicationException
    {
        // Constructs a <code>TransitionUndefinedException</code>
        // with no detail message.
        public TransitionUndefinedException() :
            base()
        {}

        // Constructs a TransitionUndefinedException
        // with a detail message.
        public TransitionUndefinedException(string message) :
            base(message)
        {}
    }
}