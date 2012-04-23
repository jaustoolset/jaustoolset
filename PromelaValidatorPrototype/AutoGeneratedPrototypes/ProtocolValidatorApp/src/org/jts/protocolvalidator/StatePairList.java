/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jts.protocolvalidator;

import java.util.ArrayList;




/**
 * Pair of related states
 * @author cmessmer
 */
class StatePair
{
    private State first;
    private State second;

    StatePair(State firstState, State secondState)
    {
        first = firstState;
        second = secondState;
    }
    public State getFirstState()
    {
        return first;
    }
    public State getSecondState()
    {
        return second;
    }
}

/**
 * Implements methods similar to a map without the unique key criteria
 * @author cmessmer
 */
public class StatePairList extends ArrayList<StatePair>{

    /**
     * Adds a new state pair to the list
     * @param firstState - first state
     * @param secondState - second state
     * @return - true if the pair was added successfully
     */
    public boolean put(State firstState, State secondState)
    {
        boolean success = true;

        success = add(new StatePair(firstState, secondState));

        return success;
    }
}
