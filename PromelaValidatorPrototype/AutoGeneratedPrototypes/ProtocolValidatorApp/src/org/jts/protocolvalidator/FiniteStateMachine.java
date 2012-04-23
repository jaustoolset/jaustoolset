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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jts.jsidl.binding.DefaultState;
import org.jts.jsidl.binding.PseudoStartState;
import org.jts.jsidl.binding.Start;
import org.jts.jsidl.binding.StateMachine;

/**
 * Object containing the definition of a finite state machine, that is used
 * to generate Promela code for validation.
 * @author cmessmer
 */
public class FiniteStateMachine {

    // list of code gen states
    private List<State> states;
    // name of the FSM
    private String name;
    // description of the FSM
    private String interp;
    // default state defined for the FSM
    private DefaultState defaultState;
    // starting point for the state machine
    private PseudoStartState startState;
    // list of input JTS states
    private List<org.jts.jsidl.binding.State> jtsstates;
    // name of the start state.
    private String startStateName;
    // push pop transition map.  The push/pop implementation requires
    // that every state with a pop transition be implemented as a unique
    // state for every matching push transition
    private StatePairList pushPopMap;

    /**
     * Constructs a new object.
     * @param fsm - input fsm data binding from JTS.
     * @param startstate - Start state for the fsm.
     */
    FiniteStateMachine(StateMachine fsm, Start startstate) {
        // set private data from input data
        interp = fsm.getInterpretation();
        defaultState = fsm.getDefaultState();
        startState = fsm.getPseudoStartState();
        name = Util.getNameFromRef(fsm.getName());
        jtsstates = fsm.getState();
        startStateName = startstate.getStateName();

        // initialize objects
        pushPopMap = new StatePairList();
        states = new ArrayList<State>();

        loadStates();
        
    }

    /**
     * Generates Promela code for the fsm.
     * @return Ordered list of code lines.
     */
    public List<String> getCode(Map<String, String> messageDataMap) {
        loadPushPopMap();

        List<String> output = new ArrayList<String>();
        Set<String> dataInst = new HashSet<String>();

        List<String> stateoutput = new ArrayList<String>();
        for (State state : states) {
            stateoutput.addAll(state.getCode(messageDataMap));
            dataInst.addAll(state.getDataInstances(messageDataMap));
            dataInst.addAll(state.getTransitionDataInstances(messageDataMap));
        }
        // indent the content and add it to out output
        Util.indent(1, stateoutput);

        output.addAll(Util.formatCommentString(interp, false));
        output.addAll(startStateMachine());

        output.addAll(dataInst);
        // go to the start state that was defined for this state machine
        output.add("goto " + name + "_" + Util.formatRefName(startStateName) + ";");

        output.addAll(stateoutput);

        output.addAll(getPOPStateCode(messageDataMap));

        output.addAll(endStateMachine());
        return output;
    }

    /**
     * Retrieve a list of unique guards from the fsm
     * @return - List of unique guards from states
     */
    public List<Guard> getGuards() {
        List<Guard> output = new ArrayList<Guard>();
        for (State tmpstate : states) {
            output.addAll(tmpstate.getGuards());
        }
        return output;
    }

    /**
     * Retrieve a list of unique actions from the fsm
     * @return - List of unique actions from states
     */
    public List<Action> getActions() {
        List<Action> output = new ArrayList<Action>();
        for (State tmpstate : states) {
            output.addAll(tmpstate.getActions());
        }
        return output;
    }

    /**
     * Generates the starting code for the state machine.
     * @return - ordered list of code lines.
     */
    private List<String> startStateMachine() {
        List<String> output = new ArrayList<String>();
        output.add("// starting state machine definition : " + name);
        output.add("active proctype " + name + "(){");
        output.add("\tpid incoming_pid = 0;");
        output.add("\tpid current_client_pid = 0;");

        return output;
    }

    /**
     * Generates the ending code for a state machine.
     * @return - ordered list of code lines.
     */
    private List<String> endStateMachine() {
        List<String> output = new ArrayList<String>();
        output.add("}");
        output.add("// ending state machine definition : " + name);
        return output;
    }

    /**
     * Processes an incoming state for the fsm.
     * @param fsm - the input state machine.
     * @param start - the start state for the fsm.
     */
    void addStates(StateMachine fsm, Start start) {
        startStateName = start.getStateName();
        DefaultState tmpDState = fsm.getDefaultState();
        List<DefaultState> defaultstates = new ArrayList<DefaultState>();

        if (tmpDState != null) {
            defaultstates.add(tmpDState);
        }
        List<org.jts.jsidl.binding.State> tmpstates = fsm.getState();
        for (org.jts.jsidl.binding.State jtsstate : tmpstates) {
            String statename = name + "_" + jtsstate.getName();

            boolean found = false;
            // add new states for our fsm
            for (State existingstate : states) {
                if (statename.equals(existingstate.getName())) {
                    found = true;
                    existingstate.combine(jtsstate);
                }
            }
            if (!found) {
                states.add(new State(jtsstate, name, defaultState));
            }
        }
    }

    /**
     * Creates a new code generation State for each incoming JTS State.
     */
    private void loadStates() {

        // add new states for our fsm
        for (org.jts.jsidl.binding.State jtsstate : jtsstates) {
            states.add(new State(jtsstate, name, defaultState));
        }
    }

    /**
     * Loads data into the push/pop map, so we can generate the pop states
     */
    public void loadPushPopMap() {
        Set<State> pushStates = new HashSet<State>();
        List<State> popStates = new ArrayList<State>();

        Set<String> processedIDs = new HashSet<String>();

        // get all the push and pop states
        for (State state : states) {
            pushStates.addAll(state.getPUSHStates());
            popStates.addAll(state.getPOPStates());
        }

        // figure out which push states match with which pop states
        for(State push: pushStates)
        {
            // it's possible to have more than one push transition in a state
            // so this  retrieves all of the end state ids for push transitions
            List<String> endStateIDs = push.getPUSHEndStateIDs();
            for(String endID: endStateIDs){
                for(State pop: popStates)
                {
                    if(endID.equals(pop.getStateID()) && !processedIDs.contains(endID))
                    {
                        processedIDs.add(endID);
                        pushPopMap.put(push, pop);
                    }
                }
            }
        }
        //pushPopMap.putAll(state.getPushPopMap());

    }

    /**
     * Retrieve the code for POP states.  This is different from other states in that
     * we don't know where to pop to.  This problem is solved by generating a unique
     * state containing pop transitions for each push transition to that state.  These unique
     * pop states are hard coded to pop back to the pushing state.
     * @param messageDataMap - data map between messages and message data types
     * @return - Promela code lines for all states containing pop transitions
     */
    private List<String> getPOPStateCode(Map<String, String> messageDataMap)
    {
        List<String> output = new ArrayList<String>();
        for(StatePair pair: pushPopMap)
        {
            String id = pair.getFirstState().getStateID();
            output.addAll(pair.getSecondState().getPOPStateCode(id, messageDataMap));
        }

        return output;
    }
}
