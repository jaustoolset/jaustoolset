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
import org.jts.jsidl.binding.DefaultTransition;
import org.jts.jsidl.binding.Parameter;

/**
 * Used to store information about a transition and generate Promela code
 * representing the transition.
 * @author cmessmer
 */
public class Transition {

    // parameters for the transition
    private List<Parameter> parameters;
    // guard associated with the transition
    private Guard guard;
    // actions to take if the guard is satisfied
    private List<Action> actions;

    // Type of transitions
    public enum TransitionType {

        INTERNAL,
        SIMPLE,
        PUSH,
        POP
    }
    // type of this transition
    private TransitionType transitionType;
    // state to goto if the transition occurs
    private String endState;
    // the ID for the end state
    private String endStateID = "";
    // name of this state
    private String name;
    // comments extracted from the JSIDL
    private String interp;
    // name of the FSM containing this transition
    private String fsmName;

    /**
     * Constructor for transitions
     * @param trans - the JSIDL Transition this object will be based on.
     */
    public Transition(org.jts.jsidl.binding.Transition trans, String fsmName, String parentName) {
        this.fsmName = fsmName;
        interp = trans.getInterpretation();

        loadName(trans);
        loadActions(trans.getActionOrSendAction());
        loadGuard(trans.getGuard());
        loadParams(trans.getParameter());
        loadType(trans);
        loadEndState(trans);
    }

    /**
     * Constructor for default transitions
     * @param trans - the JSIDL default transition this object will be based on.
     */
    public Transition(DefaultTransition trans, String fsmName, String parentName) {
        this.fsmName = fsmName;
        loadActions(trans.getActionOrSendAction());
        loadGuard(trans.getGuard());
        parameters = new ArrayList<Parameter>();
        loadType(trans);
        loadEndState(trans);
    }

    /**
     * Set the name for this transition
     * @param trans - the JTS transition to pull data from for this name.
     */
    private void loadName(org.jts.jsidl.binding.Transition trans) {
        name = trans.getName();
    }

    /**
     * Retrieve the data String for the transition
     * @return typically an incoming message that contains data used to evaluate
     * guards and support actions.
     */
    public String getTransString(Map<String, String> messageDataMap) {
        String tmpname = Util.getNameFromRef(name);
        String receive = "";

        if (tmpname.equals("Receive")) {
            // we are receiving a message, so check the parameters to see what
            boolean first = true;
            for (Parameter param : parameters) {

                if (first) {
                    String data = messageDataMap.get(param.getType());
                    receive = param.getType() + " ? incoming_pid, " + data + "_inst";
                    first = false;
                    break;
                }
            }
        } else //if(!tmpname.equals("Send"))
        {
            String data = messageDataMap.get(tmpname);
            receive = tmpname + " ? incoming_pid";
            if (data != null) {
                receive += ", " + data + "_inst";
            }
        }

        if (tmpname.isEmpty() && parameters.isEmpty()) {
            receive = "true";
        }
        return receive;
    }

    /**
     * Retrieve an identifying string for this transition, so we can compare against other Transitions
     * @return typically an incoming message that contains data used to show if 2 transitions are alike.
     */
    public String getTransString() {
        String tmpname = Util.getNameFromRef(name);
        String receive = "";

        if (tmpname.equals("Receive")) {
            // we are receiving a message, so check the parameters to see what
            boolean first = true;
            for (Parameter param : parameters) {

                if (first) {
                    receive = param.getType() + " ? incoming_pid";
                    first = false;
                    break;
                }
            }
        } else //if(!tmpname.equals("Send"))
        {
            receive = tmpname + " ? incoming_pid";
        }
        return receive;
    }

    /**
     * Retrieve the main Promela code definition for a guard.
     * @param messageDataMap - mapping of message names to data type
     * @return - a string containing the Promela guard
     */
    String getGuardString(Map<String, String> messageDataMap) {
        String result = "";
        String data = "";
        if (!parameters.isEmpty() && parameters.get(0).getType() != null) {
            if (messageDataMap.get(parameters.get(0).getType()) != null) {
                data = messageDataMap.get(parameters.get(0).getType()) + "_inst";
            }
        }
        if (guard != null) {
            result = guard.getGuardString();
        }
        return result;
    }

    /**
     * Retrieve a list of message data instances that must be in place prior to
     * receiving the message data so the data can be stored and used.
     * @param messageDataMap - map of message data types to message name
     * @return Code lines declaring instances of message data types.
     */
    public List<String> getMessageDataInstance(Map<String, String> messageDataMap) {
        List<String> output = new ArrayList<String>();
        String tmpname = Util.getNameFromRef(name);
        String data = "";

        if (tmpname.equals("Receive")) {
            // we are receiving a message, so check the parameters to see what
            if (!parameters.isEmpty()) {
                // this is really to handle the Receive defined in the Transport service
                // the first param is always the message type being received
                data = messageDataMap.get(parameters.get(0).getType());
                output.addAll(Util.formatCommentString(parameters.get(0).getInterpretation(), false));
                output.add(data + " " + data + "_inst;");
            }
        } else {
            data = messageDataMap.get(tmpname);
            if (data != null) {
                output.add(data + " " + data + "_inst;");
            }
        }

        return output;
    }

    /**
     * Retrieve the message data instance name
     * @param messageDataMap - mapping of message and data names
     * @return - message data instance name
     */
    public String getMessageDataInstanceName(Map<String, String> messageDataMap) {
        String tmpname = Util.getNameFromRef(name);
        String data = "";

        if (tmpname.equals("Receive")) {
            // we are receiving a message, so check the parameters to see what
            if (!parameters.isEmpty()) {
                // this is really to handle the Receive defined in the Transport service
                // the first param is always the message type being received
                data = messageDataMap.get(parameters.get(0).getType());
                data = data + " " + data + "_inst;";
            }
        }

        return data;
    }

    /**
     * get the end state for the transition
     * @param trans - the original JSIDL transition
     */
    private void loadEndState(org.jts.jsidl.binding.Transition trans) {
        if (transitionType == TransitionType.SIMPLE) {
            endState = fsmName + "_" + trans.getSimple().getEndState().getState();
            endStateID = trans.getSimple().getEndState().getState();
        } else if (transitionType == TransitionType.PUSH) {
            endState = fsmName + "_" + trans.getPush().getEndState().getState();
            endStateID = trans.getPush().getEndState().getState();
        } else if (transitionType == TransitionType.POP) {
        }
    }

    /**
     * Retrieve the ID for the end state.
     * @return - ID for the end state, or empty if this is a pop or internal transition.
     */
    public String getEndStateID() {
        return endStateID;
    }

    /**
     * Set the transition type based on transition data.
     * @param trans - the transition type.
     */
    private void loadType(org.jts.jsidl.binding.Transition trans) {
        if (trans.getInternal() != null) {
            transitionType = TransitionType.INTERNAL;
        } else if (trans.getSimple() != null) {
            transitionType = TransitionType.SIMPLE;
        } else if (trans.getPush() != null) {
            transitionType = TransitionType.PUSH;
        } else if (trans.getPop() != null) {
            transitionType = TransitionType.POP;
        } else {
            throw new UnsupportedOperationException("No Transition type specified.");
        }
    }

    /**
     * Save a copy of the parameter list for this transition.
     * @param parameter - list of parameters for the transition.
     */
    private void loadParams(List<Parameter> parameter) {
        parameters = new ArrayList<Parameter>();
        if (!parameter.isEmpty()) {
            parameters.addAll(parameter);
        }
    }

    /**
     * Load the guard for this transition, if one has been defined.
     * @param inputguard - the guard data binding.
     */
    private void loadGuard(org.jts.jsidl.binding.Guard inputguard) {
        if (inputguard != null) {
            guard = new Guard(inputguard);
        }
    }

    /**
     * Load data for actions defined in the transition.
     * @param actionOrSendAction - list of Action or SendAction objects.
     */
    private void loadActions(List<Object> actionOrSendAction) {
        actions = new ArrayList<Action>();
        for (Object tmpobj : actionOrSendAction) {
            if (tmpobj instanceof org.jts.jsidl.binding.Action) {
                actions.add(new Action((org.jts.jsidl.binding.Action) tmpobj));
            } else if (tmpobj instanceof org.jts.jsidl.binding.SendAction) {
                actions.add(new Action((org.jts.jsidl.binding.SendAction) tmpobj));
            }
        }
    }

    /**
     * Check fields to determine what type of transition this is.
     * @param trans - the transition
     */
    private void loadType(DefaultTransition trans) {
        if (trans.getInternal() != null) {
            transitionType = TransitionType.INTERNAL;
        } else if (trans.getSimple() != null) {
            transitionType = TransitionType.SIMPLE;
        } else if (trans.getPush() != null) {
            transitionType = TransitionType.PUSH;
        } else if (trans.getPop() != null) {
            transitionType = TransitionType.POP;
        } else {
            throw new UnsupportedOperationException("No Transition type specified.");
        }
    }

    /**
     * Load data for the target end state
     * @param trans - the transition holding the end state data.
     */
    private void loadEndState(DefaultTransition trans) {
        if (transitionType == TransitionType.SIMPLE) {
            endState = fsmName + "_" + trans.getSimple().getEndState().getState();
            endStateID = trans.getSimple().getEndState().getState();
        } else if (transitionType == TransitionType.PUSH) {
            endState = fsmName + "_" + trans.getPush().getEndState().getState();
            endStateID = trans.getPush().getEndState().getState();
        } else if (transitionType == TransitionType.POP) {
            // POP transitions don't know the end state since
            // it is whatever state transitioned to this state
        }
    }

    /**
     * Generated code for a Transition
     * @return - ordered list of code lines
     */
    public List<String> getStartCode(Map<String, String> messageDataMap) {
        List<String> output = new ArrayList<String>();
        output.addAll(Util.formatCommentString(interp, false));
        String receive = "";
        receive = getTransString(messageDataMap);
        receive = ":: " + receive + " ->";
        output.add(receive);

        return output;
    }

    /**
     * Generated code for a Transition
     * @return - ordered list of code lines
     */
    public List<String> getEndCode(Map<String, String> messageDataMap, int count) {
        List<String> output = new ArrayList<String>();
        // multiple guards for the same message received require
        // the use of "unless" in Promela to guarantee evaluation order.
        // this ends all of the "unless" blocks.
        String endUnless = "\t";
        // start at 1 because the first guard doesn't have an "unless"
        for (int i = 1; i < count; i++) {
            endUnless += "}";
        }
        output.add(endUnless);
        return output;
    }

    /**
     * Generated code for a Transition
     * @return - ordered list of code lines
     */
    public List<String> getCode(Map<String, String> messageDataMap, int count, List<Action> exitActions) {

        return getCode(endState, messageDataMap, count, exitActions);
    }

    /**
     * Generated code for a Transition
     * @param endStateID
     * @param messageDataMap
     * @param count
     * @return - ordered list of code lines
     */
    private List<String> getCode(String endStateID, Map<String, String> messageDataMap, int count, List<Action> exitActions) {
        List<String> output = new ArrayList<String>();
        String data = "";
        if (!parameters.isEmpty() && parameters.get(0).getType() != null) {
            if (messageDataMap.get(parameters.get(0).getType()) != null) {
                data = messageDataMap.get(parameters.get(0).getType()) + "_inst";
            }
        }
        if (count > 0) {
            output.add("unless {");
        }
        output.add("if");
        // output guard start
        List<String> guardstart = new ArrayList<String>();
        if (guard == null) {
            guardstart.add("// unguarded transition ");
            guardstart.add(":: true ->");
        } else {
            guardstart.addAll(guard.getCode(data));
        }
        output.addAll(guardstart);

        // output each action
        List<String> actionoutput = new ArrayList<String>();
        for (Action action : actions) {
            actionoutput.addAll(action.getCode(data));
        }
        if (transitionType != TransitionType.INTERNAL) {
            // We are about to exit this state, so perform the exit actions
            if (exitActions != null && exitActions.size() > 0) {
                actionoutput.add("// Start Exit Actions");
                for (Action action : exitActions) {
                    actionoutput.addAll(action.getCode(data));
                }
                actionoutput.add("// End Exit Actions");
            }
            // now go to the next state
            if (transitionType == TransitionType.PUSH) {
                actionoutput.add("goto " + Util.formatRefName(endStateID + "_" + endState) + ";");
            } else {
                actionoutput.add("goto " + Util.formatRefName(endStateID) + ";");
            }
        }
        Util.indent(2, actionoutput);
        output.addAll(actionoutput);
        output.add("fi");
        return output;
    }

    /**
     * Generated code for a Transition
     * @return - ordered list of code lines
     */
    public List<String> getPOPCode(String pushID, Map<String, String> messageDataMap, int count, List<Action> exitActions) {
        String newID = fsmName + "." + pushID;
        return getCode(newID, messageDataMap, count, exitActions);
    }

    /**
     * Generated code for a Transition
     * @return - ordered list of code lines
     */
    public List<String> getPUSHCode(String pushID, Map<String, String> messageDataMap, int count, List<Action> exitActions) {
        String newID = fsmName + "." + pushID;
        return getCode(newID, messageDataMap, count, exitActions);
    }

    /**
     * Retrieve the name of this transition
     * @return - the transition name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieve the guard
     * @return - the actual guard
     */
    public Guard getGuard() {
        return guard;
    }

    /**
     * Retrieve action list
     * @return - the actual action list
     */
    public List<Action> getActions() {
        return actions;
    }

    /**
     * Shows if this transition is a POP transition
     * @return - true if this is a POP transition
     */
    boolean isPOPTransition() {
        boolean result = false;
        if (transitionType == TransitionType.POP) {
            result = true;
        }
        return result;
    }

    /**
     * Shows if this transition is a POP transition
     * @return - true if this is a POP transition
     */
    boolean isPUSHTransition() {
        boolean result = false;
        if (transitionType == TransitionType.PUSH) {
            result = true;
        }
        return result;
    }
}
