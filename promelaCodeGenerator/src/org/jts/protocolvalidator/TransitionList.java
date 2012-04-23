/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.

Neither the name of the United States Government nor the names of
its contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
 *********************  END OF LICENSE ***********************************/

package org.jts.protocolvalidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to eliminate duplicate transition entries
 * @author cmessmer
 */
public class TransitionList  extends ArrayList<Transition> {

    /**
     * Adds a transition to our list, unless it's a duplicate
     * @param trans - Transition to add to this list
     * @return - true if add is successful
     */
    @Override
    public boolean add(Transition trans)
    {
        boolean result = false;
        boolean found = false;
        for(Transition existing: this)
        {
            if((existing.getTransString().equals(trans.getTransString()) &&
                    (existing.getGuard()!= null && trans.getGuard()!= null && 
                    existing.getGuard().getCondition().equals(trans.getGuard().getCondition())))
                    ||
                    ((existing.getTransString().equals(trans.getTransString()) &&
                    existing.getGuard()== null && trans.getGuard()== null)))
            {
                found = true;
            }
        }
        if(!found){
            result &= super.add(trans);
        }
        return result;
    }

    /**
     * Combines content of another list while removing duplicates.
     * @param transitions - List of Transition objects to add
     * @return - true if all additions were successful.  This does not give false for duplicates.
     */
    public boolean addAll(List<Transition> transitions)
    {
        boolean result = true;
        for(Transition tmptrans: transitions)
        {
            result &= this.add(tmptrans);
        }

        return result;
    }

}
