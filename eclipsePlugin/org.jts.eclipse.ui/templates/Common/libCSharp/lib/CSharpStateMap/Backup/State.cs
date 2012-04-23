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
// Copyright (C) 2004. Charles W. Rapp.
// All Rights Reserved.
// 
// Contributor(s):
//   Eitan Suez contributed examples/Ant.
//   (Name withheld) contributed the C# code generation and
//   examples/C#.
//
// RCS ID
// $Id: State.cs,v 1.2 2006/09/16 15:04:28 cwrapp Exp $
//
// State.cs --
//
//  Base class for all smc-generated state classes.
//
// CHANGE LOG
// (See the bottom of this file.)
//

using System;

namespace statemap
{
    [Serializable]
    public abstract class State
    {
    //-----------------------------------------------------------
    // Member functions.
    //

        protected State(String name, int id)
        {
            _name = name;
            _id = id;
        }

        public string Name
        {
            get
            {
                return _name;
            }
        }

        public int Id
        {
            get
            {
                return _id;
            }
        }

        public override string ToString()
        {
            return _name;
        }

    //-----------------------------------------------------------
    // Member data.
    //

        private string _name;
        private int _id;
    } // end of class State
} // end of namespace statemap

//
// CHANGE LOG
// $Log: State.cs,v $
// Revision 1.2  2006/09/16 15:04:28  cwrapp
// Initial v. 4.3.3 check-in.
//
// Revision 1.1  2005/05/28 18:44:13  cwrapp
// Updated C++, Java and Tcl libraries, added CSharp, Python and VB.
//
// Revision 1.0  2004/09/06 16:33:12  charlesr
// Initial revision
//
