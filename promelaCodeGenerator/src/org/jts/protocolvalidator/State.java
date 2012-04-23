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
import java.util.Map;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jts.jsidl.binding.DefaultState;
import org.jts.jsidl.binding.DefaultTransition;

/**
 * Used to store information for a JSIDL State data binding and to generate
 * Promela code representing the state.
 * @author cmessmer
 */
public class State {

    // states within this state
    private List<State> subStates;
    // lists for each type of transition, which is necessary
    // to ensure that transition order is handled properly
    private TransitionList guardedTransitions;
    private TransitionList unguardedTransitions;
    private TransitionList defaultStateGuardedTransitions;
    private TransitionList defaultStateUnguardedTransitions;
    private TransitionList guardedDefaultTransitions;
    private TransitionList unguardedDefaultTransitions;
    private TransitionList defaultStateGuardedDefaultTransitions;
    private TransitionList defaultStateUnguardedDefaultTransitions;
    // entry and exit actions for the state
    private List<Action> entryActions;
    private List<Action> exitActions;
    // name of this state
    private String name;
    // comments taken from the JSIDL
    private String interp;
    // name of the FSM this state is part of
    private String fsmName;
    // name of the parent state
    private String parentName;
    // ID for this state
    private String stateID;

    /**
     * Constructor used to create substates, where transitions from
     * parent structures already exist and need to be integrated.
     * @param jtsstate - the JTS data binding for a state
     * @param parentName - the name of the parent object.  This is prepended to all child names
     * to keep names unique throughout the promela code.
     * @param guardedtrans - Guarded transitions from the parent
     * @param unguardedtrans - Unguarded transitions from the parent
     * @param defaultstateguardedtrans - Default state guarded transitions from the parent
     * @param defstateunguardedtrans - Default state unguarded transitions from the parent
     * @param guardeddefaulttrans - Guarded default transitions from the parent
     * @param unguardeddefaulttrans - Unguarded default transitions from the parent
     * @param defaultstateguardeddefaulttrans - Default state guarded default transitions from the parent
     * @param defaultstateunguardeddefaulttrans - Default state unguarded default transitions from the parent
     */
    public State(org.jts.jsidl.binding.State jtsstate, String parentName,
            List<Transition> guardedtrans, List<Transition> unguardedtrans,
            List<Transition> defaultstateguardedtrans, List<Transition> defstateunguardedtrans,
            List<Transition> guardeddefaulttrans, List<Transition> unguardeddefaulttrans,
            List<Transition> defaultstateguardeddefaulttrans, List<Transition> defaultstateunguardeddefaulttrans) {

        this.parentName = parentName;
        fsmName = getFSMName(parentName);
        entryActions = new ArrayList<Action>();
        exitActions = new ArrayList<Action>();
        setEntryExitActions(jtsstate);
        setState(jtsstate, parentName, guardedtrans, unguardedtrans, defaultstateguardedtrans, defstateunguardedtrans,
                guardeddefaulttrans, unguardeddefaulttrans, defaultstateguardeddefaulttrans,
                defaultstateunguardeddefaulttrans);
    }

    /**
     * Constructor used primarily for a top level state, where the parent has no
     * transitions or default transitions.  Used by the FiniteStateMachine to create states.
     * @param jtsstate - the JTS data binding for a state
     * @param parentName - the name of the parent object.  This is prepended to all child names
     * to keep names unique throughout the promela code.
     * @param defstate - the default state from the FiniteStateMachine
     */
    public State(org.jts.jsidl.binding.State jtsstate, String parentName, DefaultState defstate) {
        this.parentName = parentName;
        entryActions = new ArrayList<Action>();
        exitActions = new ArrayList<Action>();
        fsmName = getFSMName(parentName);
        // empty list we can use to call the other constructor
        List<Transition> emptyTransitions = new ArrayList<Transition>();

        List<Transition> defstateguarded = new ArrayList<Transition>();
        List<Transition> defstateunguarded = new ArrayList<Transition>();
        List<Transition> defstatedeftransguarded = new ArrayList<Transition>();
        List<Transition> defstatedeftransunguarded = new ArrayList<Transition>();

        // if we have a default state, then extract the data
        if (defstate != null) {
            List<org.jts.jsidl.binding.Transition> defstatetrans = defstate.getTransition();
            List<DefaultTransition> defstatedeftrans = defstate.getDefaultTransition();


            // default state guarded and unguarded transitions
            for (org.jts.jsidl.binding.Transition tmptrans : defstatetrans) {
                if (tmptrans.getGuard() == null) {
                    defstateunguarded.add(new Transition(tmptrans, fsmName, parentName));
                } else {
                    defstateguarded.add(new Transition(tmptrans, fsmName, parentName));
                }
            }
            // default state guarded and unguarded default transitions
            for (org.jts.jsidl.binding.DefaultTransition tmpdeftrans : defstatedeftrans) {
                if (tmpdeftrans.getGuard() == null) {
                    defstatedeftransunguarded.add(new Transition(tmpdeftrans, fsmName, parentName));
                } else {
                    defstatedeftransguarded.add(new Transition(tmpdeftrans, fsmName, parentName));
                }
            }
        }

        setEntryExitActions(jtsstate);
        setState(jtsstate, parentName, emptyTransitions, emptyTransitions, defstateguarded, defstateunguarded,
                emptyTransitions, emptyTransitions, defstatedeftransguarded, defstatedeftransunguarded);
    }

    /**
     * Retrieve code lines implementing this state in Promela
     * @param messageDataMap - a mapping of message data to reference
     * @return - code lines for this state
     */
    public List<String> getCode(Map<String, String> messageDataMap) {
        List<String> output = new ArrayList<String>();
        List<String> substateCode = new ArrayList<String>();
        for (State substate : subStates) {
            List<String> subcode = new ArrayList<String>();
            subcode.add("::");
            subcode.addAll(substate.getCode(messageDataMap));
            Util.indent(1, subcode);
            substateCode.addAll(subcode);
        }

        // state description
        output.addAll(Util.formatCommentString(interp, false));

        // add code for entry actions for this state
        output.addAll(getEntryActionCode(name));

        // state label
        output.add(name + "_IMPL:");
        output.add("do");
        output.addAll(substateCode);

        // only states with no children need to have transitions defined
        // this is due to JSIDL design and this simplified implementation
        // that pushes all transitions to the lowest level states.
        if (subStates.isEmpty()) {
            output.addAll(getTransitionCode(stateID, messageDataMap));
        }
        output.add("od;");
        return output;
    }

    /**
     * Retrieve code lines implementing this state in Promela
     * @param messageDataMap - a mapping of message data to reference
     * @return - code lines for this state
     */
    public List<String> getPOPStateCode(String pushStateID, Map<String, String> messageDataMap) {
        List<String> output = new ArrayList<String>();

        // state description
        output.addAll(Util.formatCommentString(interp, false));
        // add code for entry actions for this state
        String stateName = fsmName + "_" + Util.formatRefName(pushStateID) + "_" + name;
        output.addAll(getEntryActionCode(stateName));

        // state label
        output.add(stateName + "_IMPL:");
        output.add("do");
        output.addAll(getTransitionCode(pushStateID, messageDataMap));
        output.add("od;");
        return output;
    }

    /**
     * Retrieve a list of all transition data instances for this state.
     * @param messageDataMap - a mapping of message data to reference
     * @return - code lines for this state's data instances
     */
    public Set<String> getDataInstances(Map<String, String> messageDataMap) {
        Set<String> transDataInst = new HashSet<String>();
        for (State substate : subStates) {
            transDataInst.addAll(substate.getDataInstances(messageDataMap));
            transDataInst.addAll(substate.getTransitionDataInstances(messageDataMap));
        }
        // create data instances for substates
        return transDataInst;
    }

    /**
     * Retrieve code lines for entry actions.  Do to the limitations within
     * Promela, entry actions are implemented as a state that always transitions
     * to the original state's implementation.
     * @return - Promela code lines for entry actions
     */
    public List<String> getEntryActionCode(String stateName) {
        List<String> output = new ArrayList<String>();
        output.add("// Start Entry Actions");
        output.add(stateName + ":");
        output.add("do");
        List<String> tmpoutput = new ArrayList<String>();
        tmpoutput.add(":: true ->");
        for (Action action : entryActions) {
            // entry actions happen before there is data provided through a message
            tmpoutput.addAll(action.getCode(""));
        }
        // always go to the core implementation for this state
        tmpoutput.add("goto " + stateName + "_IMPL;");
        Util.indent(1, tmpoutput);
        output.addAll(tmpoutput);

        output.add("od;");
        output.add("// End Entry Actions");
        return output;
    }

    /**
     * Loads a list of states as substates to the current state.
     * @param jtsstates - incoming list of states.
     */
    private void loadStates(List<org.jts.jsidl.binding.State> jtsstates) {
        // load substates with default state and default transiiton data included
        for (org.jts.jsidl.binding.State tmpstate : jtsstates) {
            State state = new State(tmpstate, name, guardedTransitions,
                    unguardedTransitions, defaultStateGuardedTransitions,
                    defaultStateUnguardedTransitions, guardedDefaultTransitions,
                    unguardedDefaultTransitions, defaultStateGuardedDefaultTransitions,
                    defaultStateUnguardedDefaultTransitions);
            subStates.add(state);
        }

    }

    /**
     * Loads all the transition types into the appropriate list.  These lists are necessary
     * to keep the 8 categories separate, due to the required ordering of transition evaluation
     * (required by the JSIDL spec).
     * @param inputstate - the state that contains all the transitions which need to be processed.
     */
    private void loadTransitions(org.jts.jsidl.binding.State inputstate) {

        // load the transitions and default transitions and default state transitions for this state
        // guarded and unguarded transitions
        List<org.jts.jsidl.binding.Transition> trans = inputstate.getTransition();
        if (!trans.isEmpty()) {
            for (org.jts.jsidl.binding.Transition tmptrans : trans) {
                if (tmptrans.getGuard() == null) {
                    unguardedTransitions.add(new Transition(tmptrans, fsmName, parentName));
                } else {
                    guardedTransitions.add(new Transition(tmptrans, fsmName, parentName));
                }
            }
        }
        // guarded and unguarded default transitions
        List<DefaultTransition> deftrans = inputstate.getDefaultTransition();
        if (!deftrans.isEmpty()) {
            for (org.jts.jsidl.binding.DefaultTransition tmpdeftrans : deftrans) {
                if (tmpdeftrans.getGuard() == null) {
                    unguardedDefaultTransitions.add(new Transition(tmpdeftrans, fsmName, parentName));
                } else {
                    guardedDefaultTransitions.add(new Transition(tmpdeftrans, fsmName, parentName));
                }
            }
        }

        // default state guarded and unguarded default transitions
        DefaultState defstate = inputstate.getDefaultState();
        if (defstate != null) {
            // default state guarded and unguarded transitions
            List<org.jts.jsidl.binding.Transition> defstatetrans = defstate.getTransition();
            if (!defstatetrans.isEmpty()) {
                for (org.jts.jsidl.binding.Transition tmptrans : defstatetrans) {
                    if (tmptrans.getGuard() == null) {
                        defaultStateUnguardedTransitions.add(new Transition(tmptrans, fsmName, parentName));
                    } else {
                        defaultStateGuardedTransitions.add(new Transition(tmptrans, fsmName, parentName));
                    }
                }
            }

            List<DefaultTransition> defstatedeftrans = defstate.getDefaultTransition();
            if (!defstatedeftrans.isEmpty()) {
                for (org.jts.jsidl.binding.DefaultTransition tmpdeftrans : defstatedeftrans) {
                    if (tmpdeftrans.getGuard() == null) {
                        defaultStateUnguardedDefaultTransitions.add(new Transition(tmpdeftrans, fsmName, parentName));
                    } else {
                        defaultStateGuardedDefaultTransitions.add(new Transition(tmpdeftrans, fsmName, parentName));
                    }
                }
            }
        }
    }

    /**
     * Loads all entry actions for this state.
     * @param actionOrSendAction - list of entry actions or send-actions(not used in core services)
     */
    private void loadEntryActions(List<Object> actionOrSendAction) {
        for (Object tmpobj : actionOrSendAction) {
        }
    }

    /**
     * Loads all exit actions for this state.
     * @param actionOrSendAction - list of exit actions or send-actions(not used in core services)
     */
    private void loadExitActions(List<Object> actionOrSendAction) {
        for (Object tmpobj : actionOrSendAction) {
        }
    }

    /**
     * Retrieve the name of this state.
     * @return - the name of the state.
     */
    public String getName() {
        return name;
    }

    /**
     * Called on an existing state match, to combine substates and transitions into this state.
     * @param inputstate - the input state that matches this state.
     */
    public void combine(org.jts.jsidl.binding.State inputstate) {

        DefaultState tmpDState = inputstate.getDefaultState();
        List<DefaultState> defaultstates = new ArrayList<DefaultState>();

        if (tmpDState != null) {
            defaultstates.add(tmpDState);
        }
        loadTransitions(inputstate);

        boolean found = false;

        List<org.jts.jsidl.binding.State> tmpstates = inputstate.getState();
        for (org.jts.jsidl.binding.State jtsstate : tmpstates) {
            String statename = jtsstate.getName();

            // add new states for our fsm
            for (State existingstate : subStates) {
                String tmpname = getBaseName();
                String tmpname2 = existingstate.getBaseName();
                if (statename.equals(existingstate.getBaseName())) {
                    found = true;
                    // if a state exists already, then this is an extension
                    // of the functionality and should be combined with the original state
                    existingstate.combine(jtsstate, guardedTransitions,
                            unguardedTransitions, defaultStateGuardedTransitions,
                            defaultStateUnguardedTransitions, guardedDefaultTransitions,
                            unguardedDefaultTransitions, defaultStateGuardedDefaultTransitions,
                            defaultStateUnguardedDefaultTransitions);
                }
            }
            // it's not an existing state, so add a new state
            if (!found) {
                subStates.add(new State(jtsstate, name, guardedTransitions,
                        unguardedTransitions, defaultStateGuardedTransitions,
                        defaultStateUnguardedTransitions, guardedDefaultTransitions,
                        unguardedDefaultTransitions, defaultStateGuardedDefaultTransitions,
                        defaultStateUnguardedDefaultTransitions));
            }
        }
    }

    /**
     * Called on an existing state match, to combine substates and transitions into this state.
     * @param inputstate - the input state that matches this state.
     */
    public void combine(org.jts.jsidl.binding.State inputstate, List<Transition> guardedtrans, List<Transition> unguardedtrans,
            List<Transition> defaultstateguardedtrans, List<Transition> defstateunguardedtrans,
            List<Transition> guardeddefaulttrans, List<Transition> unguardeddefaulttrans,
            List<Transition> defaultstateguardeddefaulttrans, List<Transition> defaultstateunguardeddefaulttrans) {

        this.guardedTransitions.addAll(guardedtrans);
        this.unguardedTransitions.addAll(unguardedtrans);

        this.defaultStateGuardedTransitions.addAll(defaultstateguardedtrans);
        this.defaultStateUnguardedTransitions.addAll(defstateunguardedtrans);

        this.defaultStateGuardedDefaultTransitions.addAll(defaultstateguardeddefaulttrans);
        this.defaultStateUnguardedDefaultTransitions.addAll(defaultstateunguardeddefaulttrans);

        this.guardedDefaultTransitions.addAll(guardeddefaulttrans);
        this.unguardedDefaultTransitions.addAll(unguardeddefaulttrans);

        DefaultState tmpDState = inputstate.getDefaultState();
        List<DefaultState> defaultstates = new ArrayList<DefaultState>();


        if (tmpDState != null) {
            defaultstates.add(tmpDState);
        }
        setEntryExitActions(inputstate);
        loadTransitions(inputstate);

        boolean found = false;

        List<org.jts.jsidl.binding.State> tmpstates = inputstate.getState();
        for (org.jts.jsidl.binding.State jtsstate : tmpstates) {
            String statename = jtsstate.getName();

            // add new states for our fsm
            for (State existingstate : subStates) {
                String tmpname = getBaseName();
                String tmpname2 = existingstate.getBaseName();
                if (statename.equals(existingstate.getBaseName())) {
                    found = true;
                    // if a state exists already, then this is an extension
                    // of the functionality and should be combined with the original state
                    existingstate.combine(jtsstate, guardedTransitions,
                            unguardedTransitions, defaultStateGuardedTransitions,
                            defaultStateUnguardedTransitions, guardedDefaultTransitions,
                            unguardedDefaultTransitions, defaultStateGuardedDefaultTransitions,
                            defaultStateUnguardedDefaultTransitions);
                }
            }
            // it's not an existing state, so add a new state
            if (!found) {
                subStates.add(new State(jtsstate, name, guardedTransitions,
                        unguardedTransitions, defaultStateGuardedTransitions,
                        defaultStateUnguardedTransitions, guardedDefaultTransitions,
                        unguardedDefaultTransitions, defaultStateGuardedDefaultTransitions,
                        defaultStateUnguardedDefaultTransitions));
            }
        }
    }

    /**
     * Retrieves the base name for this state.  State names typically contain a history of all
     * parent states leading to this state, separated by underscores.
     * @return - the last portion of the name, which matches the jts state name.
     */
    public String getBaseName() {
        String basename = "";
        if (name == null || name.isEmpty()) {
            return "";
        }
        String[] tmpstr = name.split("[_]");
        if (tmpstr.length > 0) {
            basename = tmpstr[tmpstr.length - 1];
        }
        return basename;
    }


    /**
     * Retrieve all Transition data for this state.
     * @param pushStateID - id for the push state that got us into this state, if this is a pop state
     * @param messageDataMap - mapping of message to message data type
     * @return - an ordered list of transition code.  Order: Guarded, Unguarded,
     * DefaultState Guarded, DefaultState Unguarded, Guarded DefaultTransition,
     * Unguarded DefaultTransition, DefaultState Guarded DefaultTransition,
     * DefaultState Unguarded DefaultTransition.
     *
     */
    private List<String> getTransitionCode(String pushStateID, Map<String, String> messageDataMap) {
        List<String> output = new ArrayList<String>();
        List<String> processedTransionStrings = new ArrayList<String>();

        // Since all transitions are pushed to child states, then
        // only the child states need to have output transition code.
        if (subStates.isEmpty()) {
            // this list will contain all transitions in priority order
            // which is determined by the spec
            List<Transition> priorityTransitions = getIntegratedPriorityOrderTransitionList();

            // Due to the Promela implementation, which uses "unless" the transition
            // order needs to be backward. (transition1 unless transition2, causes transition2 to be
            // evaluated first)
            List<Transition> reversePriorityTransitions = new ArrayList<Transition>();
            reversePriorityTransitions.addAll(priorityTransitions);
            Collections.reverse(reversePriorityTransitions);
            // add transitions to the output in the proper order
            // Guarded
            for (Transition trans : priorityTransitions) {
                List<String> transCode = new ArrayList<String>();
                String transStr = trans.getTransString(messageDataMap);
                if (transStr.isEmpty() || transStr.equals("")) {
                    continue;
                }
                if (processedTransionStrings.contains(transStr)) {
                    // we've already processed this transition string
                    continue;
                }
                processedTransionStrings.add(transStr);
                output.addAll(trans.getStartCode(messageDataMap));

                // Promela implementation requires that message data only be read once.
                // due to this, we need to process guards from all messages of the same type
                // at the same time.
                // In addition, "unless" forces Transitions to be evaluated in reverse order.
                int count = 0;
                for (Transition searchTrans : reversePriorityTransitions) {
                    String tmpstr = searchTrans.getTransString(messageDataMap);
                    if (searchTrans.getTransString(messageDataMap).equals(transStr)) {
                        // need to indent this code
                        List<String> tmparray = new ArrayList<String>();
                        if (searchTrans.isPOPTransition()) {
                            tmparray.addAll(searchTrans.getPOPCode(pushStateID, messageDataMap, count, exitActions));
                        } else if (searchTrans.isPUSHTransition()) {
                            tmparray.addAll(searchTrans.getPUSHCode(pushStateID, messageDataMap, count, exitActions));
                        } else {
                            tmparray.addAll(searchTrans.getCode(messageDataMap, count, exitActions));
                        }

                        Util.indent(1, tmparray);

                        if (!transCode.contains(searchTrans.getGuardString(messageDataMap))) {
                            transCode.addAll(tmparray);
                            count++;
                        }
                    }
                }
                output.addAll(transCode);
                output.addAll(trans.getEndCode(messageDataMap, count));
            }

            if (output.isEmpty()) {
                output.add(":: printf(\"Empty state expected here, based on JSIDL definition.\")");
            }
        }
        Util.indent(1, output);
        return output;
    }

    /**
     * Retrieve a list of message data instance declarations.
     * @param messageDataMap - map of message names to message data names.
     * @return - a list of message data instance declarations that should be in place prior to receiving a message.
     */
    public List<String> getTransitionDataInstances(Map<String, String> messageDataMap) {
        List<String> output = new ArrayList<String>();

        // Since all transitions are pushed to child states, then
        // only the child states need to have output transition code.
        if (subStates.isEmpty()) {
            // this list will contain all transitions in priority order
            List<Transition> priorityTransitions = getIntegratedPriorityOrderTransitionList();


            // add transitions to the output in the proper order
            // Guarded
            for (Transition trans : priorityTransitions) {
                String transStr = trans.getTransString(messageDataMap);
                if (transStr.isEmpty() || transStr.equals("")) {
                    continue;
                }

                if (!output.contains(trans.getMessageDataInstanceName(messageDataMap))) {
                    output.addAll(trans.getMessageDataInstance(messageDataMap));
                }
            }


        }
        Util.indent(1, output);
        return output;
    }

    /**
     * Initialize all transition lists for this state.
     * @param guardedtrans - input list of guarded transitions
     * @param unguardedtrans - input list of unguarded transitions
     * @param defaultstateguardedtrans - input list of default state guarded transitions
     * @param defstateunguardedtrans - input list of default state unguarded transitions
     * @param guardeddefaulttrans - input list of guarded default transitions
     * @param unguardeddefaulttrans - input list of unguarded default transitions
     * @param defaultstateguardeddefaulttrans - input list of default state guarded default transitions
     * @param defaultstateunguardeddefaulttrans - input list of default state unguarded default transitions
     */
    private void initTransitions(List<Transition> guardedtrans, List<Transition> unguardedtrans,
            List<Transition> defaultstateguardedtrans, List<Transition> defstateunguardedtrans,
            List<Transition> guardeddefaulttrans, List<Transition> unguardeddefaulttrans,
            List<Transition> defaultstateguardeddefaulttrans, List<Transition> defaultstateunguardeddefaulttrans) {
        // initialize Lists
        guardedTransitions = new TransitionList();
        unguardedTransitions = new TransitionList();
        defaultStateGuardedTransitions = new TransitionList();
        defaultStateUnguardedTransitions = new TransitionList();
        guardedDefaultTransitions = new TransitionList();
        unguardedDefaultTransitions = new TransitionList();
        defaultStateGuardedDefaultTransitions = new TransitionList();
        defaultStateUnguardedDefaultTransitions = new TransitionList();

        // get copies of the default transitions
        // regular transitions
        if (guardedtrans != null) {
            guardedTransitions.addAll(guardedtrans);
        }
        if (unguardedtrans != null) {
            unguardedTransitions.addAll(unguardedtrans);
        }

        // default state transitions
        if (defaultstateguardedtrans != null) {
            defaultStateGuardedTransitions.addAll(defaultstateguardedtrans);
        }
        if (defstateunguardedtrans != null) {
            defaultStateUnguardedTransitions.addAll(defstateunguardedtrans);
        }
        // default transitions
        if (guardeddefaulttrans != null) {
            guardedDefaultTransitions.addAll(guardeddefaulttrans);
        }
        if (unguardeddefaulttrans != null) {
            unguardedDefaultTransitions.addAll(unguardeddefaulttrans);
        }
        // default state default transitions
        if (defaultstateguardeddefaulttrans != null) {
            defaultStateGuardedDefaultTransitions.addAll(defaultstateguardeddefaulttrans);
        }
        if (defaultstateunguardeddefaulttrans != null) {
            defaultStateUnguardedDefaultTransitions.addAll(defaultstateunguardeddefaulttrans);
        }

    }

    /**
     * Sets the content of this state based on input data.
     * @param jtsstate - the input JTS state that this state should be based on
     * @param parentName - name of the parent state
     * @param guardedtrans - input list of guarded transitions
     * @param unguardedtrans - input list of unguarded transitions
     * @param defaultstateguardedtrans - input list of default state guarded transitions
     * @param defstateunguardedtrans - input list of default state unguarded transitions
     * @param guardeddefaulttrans - input list of guarded default transitions
     * @param unguardeddefaulttrans - input list of unguarded default transitions
     * @param defaultstateguardeddefaulttrans - input list of default state guarded default transitions
     * @param defaultstateunguardeddefaulttrans - input list of default state unguarded default transitions
     */
    private void setState(org.jts.jsidl.binding.State jtsstate, String parentName,
            List<Transition> guardedtrans, List<Transition> unguardedtrans,
            List<Transition> defaultstateguardedtrans, List<Transition> defstateunguardedtrans,
            List<Transition> guardeddefaulttrans, List<Transition> unguardeddefaulttrans,
            List<Transition> defaultstateguardeddefaulttrans, List<Transition> defaultstateunguardeddefaulttrans) {

        subStates = new ArrayList<State>();

        initTransitions(guardedtrans, unguardedtrans, defaultstateguardedtrans, defstateunguardedtrans,
                guardeddefaulttrans, unguardeddefaulttrans, defaultstateguardeddefaulttrans,
                defaultstateunguardeddefaulttrans);


        // state names include the parent state's name
        name = parentName + "_" + jtsstate.getName();
        stateID = getReferenceFromName(name);
        interp = jtsstate.getInterpretation();


        // entry actions
        if (jtsstate.getEntry() != null) {
            loadEntryActions(jtsstate.getEntry().getActionOrSendAction());
        }
        // exit actions
        if (jtsstate.getExit() != null) {
            loadExitActions(jtsstate.getExit().getActionOrSendAction());
        }
        // get all the transitions into the same list
        loadTransitions(jtsstate);
        // get all child states and create objects
        loadStates(jtsstate.getState());
    }

    /**
     * Retrieve a list of unique guards used within this state.
     * @return - list of unique guards used in this state
     */
    public List<Guard> getGuards() {
        List<Guard> output = new ArrayList<Guard>();

        // Since all transitions are pushed to child states, then
        // only the child states need to have output transition code.
        if (subStates.isEmpty()) {
            // this list will contain all transitions in priority order
            List<Transition> priorityTransitions = new ArrayList<Transition>();
            priorityTransitions.addAll(guardedTransitions);
            priorityTransitions.addAll(defaultStateGuardedTransitions);
            priorityTransitions.addAll(guardedDefaultTransitions);
            priorityTransitions.addAll(defaultStateGuardedDefaultTransitions);

            // retrieve definitions from each transition, making sure we don't
            // have overlap
            for (Transition trans : priorityTransitions) {

                // add guard defs to our output if it isn't an overlap
                if (trans.getGuard() != null) {
                    output.add(trans.getGuard());
                } else {
                    Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                            "Found unguarded transition when it should be guarded. \n " + trans.getGuard());
                }
            }
        } else {
            // gather results from substates and add unique items to our list
            for (State tmpState : subStates) {
                output.addAll(tmpState.getGuards());
            }
        }

        return output;
    }

    /**
     * Retrieve guard and action definitions.  User editing required.
     * @return - code lines containing the definitions for actions and guards.
     */
    public List<Action> getActions() {
        List<Action> output = new ArrayList<Action>();

        // Since all transitions are pushed to child states, then
        // only the child states need to have output transition code.
        if (subStates.isEmpty()) {
            // this list will contain all transitions in priority order
            List<Transition> priorityTransitions = getIntegratedPriorityOrderTransitionList();

            // retrieve definitions from each transition, making sure we don't
            // have overlap
            for (Transition trans : priorityTransitions) {

                // add action defs to our outptu if it isn't an overlap
                if (!trans.getActions().isEmpty()) {
                    output.addAll(trans.getActions());
                }
            }
        } else {
            // gather results from substates and add unique items to our list
            for (State tmpState : subStates) {
                output.addAll(tmpState.getActions());
            }
        }
        if (entryActions != null && !entryActions.isEmpty()) {
            output.addAll(entryActions);
        }
        if (exitActions != null && !exitActions.isEmpty()) {
            output.addAll(exitActions);
        }
        return output;
    }

    /**
     * Retrieve the FSM name that contains this state
     * @param parentName - name of the parent
     * @return - Name of the FSM
     */
    private String getFSMName(String parentName) {
        String fsmname = "";
        if (parentName == null || parentName.isEmpty()) {
            return "";
        }
        String[] tmpstr = parentName.split("[_]");
        for (String part : tmpstr) {
            fsmname = part;
            break;
        }

        return fsmname;
    }

    /**
     * Shows if this State contains a POP transition
     * @return - true if this state contains a POP transition
     */
    private boolean hasPOPTransition() {
        boolean result = false;
        List<Transition> transitions = getIntegratedPriorityOrderTransitionList();

        for (Transition tmptrans : transitions) {
            if (tmptrans.isPOPTransition()) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Shows if this State contains a PUSH transition
     * @return - true if this state contains a PUSH transition
     */
    private boolean hasPUSHTransition() {
        boolean result = false;
        List<Transition> transitions = getIntegratedPriorityOrderTransitionList();

        for (Transition tmptrans : transitions) {
            if (tmptrans.isPUSHTransition()) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Retrieves a list of the substates that contain POP transitions
     * @return - List of States with POP transitions
     */
    public List<State> getPOPStates() {
        List<State> popstates = new ArrayList<State>();
        if (subStates.isEmpty() && hasPOPTransition()) {
            popstates.add(this);
        } else {
            for (State state : subStates) {
                popstates.addAll(state.getPOPStates());
            }
        }
        return popstates;
    }

    /**
     * Retrieves a list of the substates that contain PUSH transitions
     * @return - List of States with PUSH transitions
     */
    public Set<State> getPUSHStates() {
        Set<State> pushstates = new HashSet<State>();
        if (subStates.isEmpty() && hasPUSHTransition()) {
            pushstates.add(this);
        } else {
            for (State state : subStates) {
                pushstates.addAll(state.getPUSHStates());
            }
        }
        return pushstates;
    }

    /**
     * Retrieves the name from the end of an id string.
     * @param id - the id to be processed
     * @return - the last segment of an id string.
     */
    private String getReferenceFromName(String name) {
        String ref = "";
        if (name == null || name.isEmpty()) {
            return "";
        }
        // get tokens separated by :
        String[] tmpstr = name.split("[_]");
        int count = 0;
        for (String part : tmpstr) {
            if (count == 0) {
                // we are ignoring the FSM Name for this ID
                // this could be rewritten, but makes the intention obvious.
            } else if (count == 1) {
                ref = part;
            } else {
                ref += "." + part;
            }
            count++;
        }

        return ref;
    }

    /**
     * Retrieves the ID for this state
     * @return - ID for this state.
     */
    public String getStateID() {
        return this.stateID;
    }

    /**
     * It is possible for a state to have multiple push transitions, so this
     * function returns all end state IDs associated with a PUSH transition.
     * @return - List of end state IDS associated with PUSH transitions in this state.
     */
    public List<String> getPUSHEndStateIDs() {
        List<String> ids = new ArrayList<String>();
        List<Transition> transitions = getIntegratedPriorityOrderTransitionList();
        for (Transition tmptrans : transitions) {
            if (tmptrans.isPUSHTransition()) {
                ids.add(tmptrans.getEndStateID());
            }
        }
        return ids;
    }

    /**
     * Retrieve a list of all the transitions in priority order according to the spec.
     * @return - integrated list of all transitions in priority order.
     */
    private List<Transition> getIntegratedPriorityOrderTransitionList() {
        List<Transition> transitions = new ArrayList<Transition>();
        transitions.addAll(guardedTransitions);
        transitions.addAll(unguardedTransitions);
        transitions.addAll(defaultStateGuardedTransitions);
        transitions.addAll(defaultStateUnguardedTransitions);
        transitions.addAll(guardedDefaultTransitions);
        transitions.addAll(unguardedDefaultTransitions);
        transitions.addAll(defaultStateGuardedDefaultTransitions);
        transitions.addAll(defaultStateUnguardedDefaultTransitions);

        return transitions;
    }

    /**
     * Sets the entry and exit actions for this state
     * @param jtsstate - the JSIDL data binding for this state.
     */
    private void setEntryExitActions(org.jts.jsidl.binding.State jtsstate) {

        if (jtsstate.getEntry() != null) {
            List<Object> entryObjs = jtsstate.getEntry().getActionOrSendAction();
            for (Object obj : entryObjs) {
                if (obj instanceof org.jts.jsidl.binding.Action) {
                    entryActions.add(new Action((org.jts.jsidl.binding.Action) obj));
                } else if (obj instanceof org.jts.jsidl.binding.SendAction) {
                    entryActions.add(new Action((org.jts.jsidl.binding.SendAction) obj));
                }
            }
        }

        if (jtsstate.getExit() != null) {
            List<Object> exitObjs = jtsstate.getExit().getActionOrSendAction();

            for (Object obj : exitObjs) {
                if (obj instanceof org.jts.jsidl.binding.Action) {
                    exitActions.add(new Action((org.jts.jsidl.binding.Action) obj));
                } else if (obj instanceof org.jts.jsidl.binding.SendAction) {
                    exitActions.add(new Action((org.jts.jsidl.binding.SendAction) obj));
                }
            }
        }
    }
}
