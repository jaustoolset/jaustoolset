package org.jts.eclipse.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.validation.Check;
import org.jts.eclipse.cjsidl.arrayDef;
import org.jts.eclipse.cjsidl.bitfieldDef;
import org.jts.eclipse.cjsidl.bodyDef;
import org.jts.eclipse.cjsidl.declaredConstSet;
import org.jts.eclipse.cjsidl.declaredConstSetRef;
import org.jts.eclipse.cjsidl.declaredEventDef;
import org.jts.eclipse.cjsidl.declaredTypeSet;
import org.jts.eclipse.cjsidl.declaredTypeSetRef;
import org.jts.eclipse.cjsidl.defaultTransition;
import org.jts.eclipse.cjsidl.eventDef;
import org.jts.eclipse.cjsidl.fixedFieldDef;
import org.jts.eclipse.cjsidl.fixedLenString;
import org.jts.eclipse.cjsidl.footerDef;
import org.jts.eclipse.cjsidl.guard;
import org.jts.eclipse.cjsidl.headerDef;
import org.jts.eclipse.cjsidl.jaus;
import org.jts.eclipse.cjsidl.listDef;
import org.jts.eclipse.cjsidl.messageDef;
import org.jts.eclipse.cjsidl.messageRef;
import org.jts.eclipse.cjsidl.messageScopedRef;
import org.jts.eclipse.cjsidl.messageSet;
import org.jts.eclipse.cjsidl.pushTransition;
import org.jts.eclipse.cjsidl.recordDef;
import org.jts.eclipse.cjsidl.refAttr;
import org.jts.eclipse.cjsidl.scopedConstId;
import org.jts.eclipse.cjsidl.scopedEventType;
import org.jts.eclipse.cjsidl.scopedType;
import org.jts.eclipse.cjsidl.scopedTypeId;
import org.jts.eclipse.cjsidl.sequenceDef;
import org.jts.eclipse.cjsidl.serviceDef;
import org.jts.eclipse.cjsidl.startState;
import org.jts.eclipse.cjsidl.state;
import org.jts.eclipse.cjsidl.stateMachine;
import org.jts.eclipse.cjsidl.transParam;
import org.jts.eclipse.cjsidl.transition;
import org.jts.eclipse.cjsidl.typeReference;
import org.jts.eclipse.cjsidl.varField;
import org.jts.eclipse.cjsidl.varFormatField;
import org.jts.eclipse.cjsidl.varLenField;
import org.jts.eclipse.cjsidl.varLenString;
import org.jts.eclipse.cjsidl.variantDef;
 

public class CjsidlJavaValidator extends AbstractCjsidlJavaValidator {

//	@Check
//	public void checkGreetingStartsWithCapital(Greeting greeting) {
//		if (!Character.isUpperCase(greeting.getName().charAt(0))) {
//			warning("Name should start with a capital", MyDslPackage.GREETING__NAME);
//		}
//	}
	
    /**
     * Ensure that a uri contains at least one '.' - otherwise show a helpful error.
     * @param file - the jaus object to check
     */
	@Check
    public void checkValidURI(jaus file)
    {
    	if(file.getSet() != null)
    	{
    		if(file.getSet() instanceof serviceDef)
    		{
    			if(!((serviceDef)file.getSet()).getName().contains("."))
    			{
    				error(((serviceDef)file.getSet()).getServiceName() + " must have a URI with at least one '.'", XtextPackage.GRAMMAR__RULES);
    			}
    		}
    		else if(file.getSet() instanceof declaredTypeSet)
    		{
    			if(!((declaredTypeSet)file.getSet()).getName().contains("."))
    			{
    				error(((declaredTypeSet)file.getSet()).getTypeName() + " must have a URI with at least one '.'", XtextPackage.GRAMMAR__RULES);
    			}
    		}
    		else if(file.getSet() instanceof declaredConstSet)
    		{
    			if(!((declaredConstSet)file.getSet()).getName().contains("."))
    			{
    				error(((declaredConstSet)file.getSet()).getConstName() + " must have a URI with at least one '.'", XtextPackage.GRAMMAR__RULES);
    			}
    		}
    	}
    }
    /**
     * Ensure no duplicat messages defined.
     * @param msg
     */
    @Check
    public void checkDuplicateInheritedMsgs(messageDef msg)
    {
        int found = 0;
        boolean run = true;
        EObject parent = msg;
        do
        {
            if(parent instanceof serviceDef)
            {
                for(messageDef d:((serviceDef)parent).getMessageSet().getInputSet().getMessageDefs())
                {
                    if(d.equals(msg))
                    {
                        found++;
                    }
                }
                run = false;
            }
            parent = parent.eContainer();
            if(parent == null)
            {
                run = false;
            }
        }while(run);
        
        if(parent instanceof serviceDef)
        {
            if(((serviceDef)parent).getRefs()!= null && ((serviceDef)parent).getRefs().getRefInherit()!= null && ((serviceDef)parent).getRefs().getRefInherit().getImportedNamespace()!= null)
            {
                found = findMsg(((serviceDef)parent).getRefs().getRefInherit().getImportedNamespace(), msg, found);
            }
        }
        
        if(found>1)
        {
            error(msg.getName() + " is defined " + found + " times - check inherited services.", XtextPackage.GRAMMAR__RULES);
        }
        
    }
    
    /**
     * Helper function to find all instances of a message  in the service def.
     * @param serviceDef - the serviceDef to search.
     * @param msg - The message to find.
     * @param found - expected result is 1 - the definition of msg.
     * @return
     */
    private int findMsg(serviceDef serviceDef, messageDef msg, int found) {
        for(messageDef def: serviceDef.getMessageSet().getInputSet().getMessageDefs())
        {
            if(def.equals(msg))
            {
                found++;
            }
        }
    return found;
}

    @Check
    public void checkDuplicateInheritedEvents(eventDef event)
    {
        int found = 0;
        boolean run = true;
        EObject parent = event;
        do
        {
            if(parent instanceof serviceDef)
            {
                if(((serviceDef)parent).getInternalEventSet() != null)
                {
                    for (EObject def : ((serviceDef) parent).getInternalEventSet().getDefs()) 
                    {
                        if (def instanceof eventDef)
                        {
                            if(((eventDef)def).getName().equals(event.getName()))
                            {
                                found++;
                            }
                        }
                    }
                }
                run = false;
            }
            parent = parent.eContainer();
            if(parent == null)
            {
                run = false;
            }
        }while(run);
        
        if(parent instanceof serviceDef)
        {
            if(((serviceDef)parent).getRefs()!= null && ((serviceDef)parent).getRefs().getRefInherit()!= null && ((serviceDef)parent).getRefs().getRefInherit().getImportedNamespace()!= null)
            {
                found = recursiveFindEvent(((serviceDef)parent).getRefs().getRefInherit().getImportedNamespace(), event, found);
            }
        }
        
        // We expect to find one instance of the event - itself.
        if(found!=1)
        {
            error(event.getName() + " is defined more than once - check inherited services.", XtextPackage.GRAMMAR__RULES);
        }
    }
    
    private int recursiveFindEvent(serviceDef importedNamespace, eventDef event, int found) {
        for(EObject def: importedNamespace.getInternalEventSet().getDefs())
        {
            if(def instanceof eventDef)
            {
                if(((eventDef)def).getName().equals(event.getName()))
                {
                    found++;
                }
            }
        }
        if(importedNamespace.getRefs()!= null && importedNamespace.getRefs().getRefInherit()!= null &&importedNamespace.getRefs().getRefInherit().getImportedNamespace()!= null)
        {
            found = recursiveFindEvent(importedNamespace.getRefs().getRefInherit().getImportedNamespace(), event, found);
        }
        return found;
    }

    /**
     * Check to see if the the transition parameter is a message,
     *  and if is a legal reference to a message (only a message
     *  defined in an input set should be allowed)
     * @param param - the transition parameter to be checked.
     */
    @Check
    public void checkTransParamMsgType(transParam param)
    {
        boolean found = false;
        boolean runLoop = true;
        
        if(param.getType() instanceof messageDef)
        {
            
            messageDef toFind = ((messageDef)param.getType());
            EObject parent = param.eContainer();
            do
            {
                if(parent instanceof serviceDef)
                {
                    messageSet ms = ((serviceDef)parent).getMessageSet();
                    if(ms!=null)
                    {
                    for(messageRef ref:ms.getInputSet().getTypeRefs())
                    {
                        if(ref.getRef().equals(toFind))
                        {
                            found = true;
                        }
                    }
                    for(messageDef def:ms.getInputSet().getMessageDefs())
                    {
                        if(def.equals(toFind))
                        {
                            found = true;
                        }
                    }
                    for(messageScopedRef ref: ms.getInputSet().getScopedRefs())
                    {
                        if(ref.getRef().equals(toFind))
                        {
                            found = true;
                        }
                    }
                    }
                    runLoop = false;
                    break;
                }
                parent = parent.eContainer();
            }while(runLoop);
            if(!found && parent instanceof serviceDef)
            {
                found = recursiveTransParamMsgCheck(toFind, (serviceDef)parent);
                
            }
        }else
        {
            // If it's not a message type, we don't need to worry.
            found = true;
        }
        
        if(!found)
        {
            error(param.getName() + " is a message defined outside of the input set.", XtextPackage.GRAMMAR__RULES);
        }
    }
    
    /**
     * Recursively search all inherited services to find the msg ref in an input set.
     * Assumes parent has already been searched.
     * @param toFind - the message def that needs to be found.
     * @param sd - the service def to pull a reference from.
     * @return
     */
    private boolean recursiveTransParamMsgCheck(messageDef toFind, serviceDef sd) {
        if(sd.getRefs() != null && sd.getRefs().getRefInherit() != null)
        {
            serviceDef search = sd.getRefs().getRefInherit().getImportedNamespace();
            for(messageRef ref: search.getMessageSet().getInputSet().getTypeRefs())
            {
                if(ref.getRef().equals(toFind))
                {
                    return true;
                }
            }
            for(messageDef def:search.getMessageSet().getInputSet().getMessageDefs())
            {
                if(def.equals(toFind))
                {
                    return true;
                }
            }
            for(messageScopedRef ref: search.getMessageSet().getInputSet().getScopedRefs())
            {
                if(ref.getRef().equals(toFind))
                {
                    return true;
                }
            }
            return recursiveTransParamMsgCheck(toFind, search);
        }
    return false;
}

    /**
     * Make sure that a push transition points to a state with a
     *  corresponding pop transition.
     * @param push - the transition being checked.
     */
    @Check
    public void checkPushTransition(pushTransition push)
    {
        boolean valid = false;
        for(transition trans:push.getNextState().getNextState().getTransitions())
        {
            if(trans.getType().equalsIgnoreCase("pop"))
            {
                valid = true;
                break;
            }
        }
        
        if(!valid)
        {
            String name = "";
            if(push.eContainer() instanceof transition)
            {
                name = ((transition)push.eContainer()).getName();
            }
            else if(push.eContainer() instanceof defaultTransition)
            {
                name = "The default transition";
            }
            error(name + " must point to a state that contains a pop transition.", XtextPackage.GRAMMAR__RULES);
        }
    }
    
    /**
     * If a state has more than one substate, it must have a start state.
     * @param st - the state being checked
     */
    @Check
    public void checkNeedStartState(state st)
    {
        if(st.getSubState().size() >1 )
        {
            int initial = 0;
            for(state s: st.getSubState())
            {
                if(s.getInitial() != null)
                {
                    initial++;
                }
            }
           
            if(initial == 0)
            {
                error(st.getName() + " is missing an initial start state.",XtextPackage.GRAMMAR__RULES);
            }else if (initial >1)
            {
                error(st.getName() + " has multiple initial start states.", XtextPackage.GRAMMAR__RULES);
            }
        }
        
    }
    /**
     * lower case state names cause a problem in the JTS validator.
     * @param st - the state being checked
     */
    @Check
    public void checkStateNameCapitalization(state st)
    {
        if(st.getName().charAt(0) != st.getName().toUpperCase().charAt(0))
        {
            error(st.getName() + " must be capitalized.", XtextPackage.GRAMMAR__RULES);
        }
    }
    
    /**
     * An input set cannot have a message reference that was defined in the output set
     *  and vice versa.
     * @param ms - the message set to be checked.
     */
    @Check
    public void checkMessageSet(messageSet ms)
    {
        for(messageRef ref:ms.getOutputSet().getTypeRefs())
        {
            if(ms.getInputSet().getMessageDefs().contains(ref.getRef()))
            {
                error(ref.getName() + " is defined in the input set.", XtextPackage.GRAMMAR__RULES);
            }
        }
        
        for(messageRef ref: ms.getInputSet().getTypeRefs())
        {
            if(ms.getOutputSet().getMessageDefs().contains(ref.getRef()))
            {
                error(ref.getName() + " is defined in the output set.", XtextPackage.GRAMMAR__RULES);
            }
        }
    }
    
    
    /**
     *  Confirm the type ref is using a valid reference.
     *  @param type - the typeRef being checked
     */
    @Check
    public void checkTypeRef(typeReference type)
    {
        if(type.getType()!= null &&
           !(type.getType() instanceof fixedFieldDef||
             type.getType() instanceof arrayDef ||
             type.getType() instanceof fixedLenString||
             type.getType() instanceof varLenString||
             type.getType() instanceof listDef||
             type.getType() instanceof varField||
             type.getType() instanceof varLenField||
             type.getType() instanceof sequenceDef||
             type.getType() instanceof recordDef||
             type.getType() instanceof varFormatField||
             type.getType() instanceof bitfieldDef||
             type.getType() instanceof messageDef||
             type.getType() instanceof variantDef))
        {
            error(type.getName() + " contains an illegal type", XtextPackage.GRAMMAR__RULES);
        }
    }
    
    /**
     * 
     * @param type
     */
    @Check
    public void checkScopedTypeRef(scopedType type)
    {
        if(type.getType()!= null &&
           !(type.getType() instanceof fixedFieldDef||
             type.getType() instanceof arrayDef ||
             type.getType() instanceof fixedLenString||
             type.getType() instanceof varLenString||
             type.getType() instanceof listDef||
             type.getType() instanceof varField||
             type.getType() instanceof varLenField||
             type.getType() instanceof sequenceDef||
             type.getType() instanceof recordDef||
             type.getType() instanceof varFormatField||
             type.getType() instanceof bitfieldDef||
             type.getType() instanceof messageDef||
             type.getType() instanceof variantDef))
        {
            //TODO: header, body, footer def as well? That's what was in the grammar, but I don't remember why.
            error(type.getName() + " contains an illegal type", XtextPackage.GRAMMAR__RULES);
        }
    }
    
    /**
     * Confirm the event being referenced is a valid type.
     * @param event
     */
    @Check
    public void checkScopedEventType(scopedEventType event)
    {
        if(event.getType() != null &&
           !(event.getType() instanceof eventDef ||
             event.getType() instanceof fixedFieldDef ||
             event.getType() instanceof arrayDef ||
             event.getType() instanceof fixedLenString ||
             event.getType() instanceof varLenString ||
             event.getType() instanceof listDef ||
             event.getType() instanceof varField ||
             event.getType() instanceof varLenField ||
             event.getType() instanceof sequenceDef ||
             event.getType() instanceof recordDef ||
             event.getType() instanceof varFormatField ||
             event.getType() instanceof bitfieldDef ||
             event.getType() instanceof messageDef ||
             event.getType() instanceof variantDef ||
             event.getType() instanceof headerDef ||
             event.getType() instanceof bodyDef ||
             event.getType() instanceof footerDef))
        {
            error(event.getType() + " contains an illegal type", XtextPackage.GRAMMAR__RULES);
        }
    }
    
    /**
     *  Confirm the transition parameter is using a valid reference.
     * @param param - the parameter 
     */
    @Check
    public void checkTransParamType(transParam param)
    {
        if(param.getType()!= null &&
           !(param.getType() instanceof messageDef ||
             param.getType() instanceof messageRef ||
             param.getType() instanceof messageScopedRef ||
             param.getType() instanceof eventDef))
        {
            error(param.getName() + " contains an illegal type", XtextPackage.GRAMMAR__RULES);
        }
    }
    
    /**
     * Confirm the array definition is using a valid reference.
     * @param arr
     */
    @Check
    public void checkArrays(arrayDef arr)
    {
        arr.getTypeRef();
        if(arr.getTypeRef() != null)
        {
        if(!(arr.getTypeRef() instanceof fixedFieldDef ||
             arr.getTypeRef() instanceof varLenString ||
             arr.getTypeRef() instanceof fixedLenString ||
             arr.getTypeRef() instanceof bitfieldDef ||
             arr.getTypeRef() instanceof varFormatField ||
             arr.getTypeRef() instanceof varField ||
             arr.getTypeRef() instanceof varLenField))
        {
            error(arr.getName() + " contains an illegal type", XtextPackage.GRAMMAR__RULES);
        }
        }
        if(arr.getScopedType() != null)
        {
            if(!(arr.getScopedType().getType() instanceof fixedFieldDef ||
             arr.getScopedType().getType() instanceof varLenString ||
             arr.getScopedType().getType() instanceof fixedLenString ||
             arr.getScopedType().getType() instanceof bitfieldDef ||
             arr.getScopedType().getType() instanceof varFormatField ||
             arr.getScopedType().getType() instanceof varField ||
             arr.getScopedType().getType() instanceof varLenField))
            {
                error(arr.getName() + " contains an illegal type", XtextPackage.GRAMMAR__RULES);
            }
            
        
        }
    }
    
    /**
     * Confirm that a record contains the correct types, and provide a
     *  useful error if they don't.
     */
    @Check
    public void checkRecordTypes(recordDef rec)
    {
        for(scopedTypeId id: rec.getScopedRef())
        {
            if(!(id.getRef().getType() instanceof fixedLenString ||
               id.getRef().getType() instanceof varLenString ||
               id.getRef().getType() instanceof bitfieldDef ||
               id.getRef().getType() instanceof varLenField ||
               id.getRef().getType() instanceof varFormatField ||
               id.getRef().getType() instanceof fixedFieldDef))
            {
                error( id.getScopedName() + " is an illegal type", XtextPackage.GRAMMAR__RULES);
            }                
        }
        
        for(typeReference ref: rec.getTypeReference())
        {
            if (!(ref.getType() instanceof fixedLenString || 
                  ref.getType() instanceof varLenString || 
                  ref.getType() instanceof bitfieldDef || 
                  ref.getType() instanceof varLenField || 
                  ref.getType() instanceof varFormatField ||
                  ref.getType() instanceof fixedFieldDef ))
            {
                error( ref.getName() + " is an illegal type", XtextPackage.GRAMMAR__RULES);
            }
        }
    }
    
    /**
     * Determine if a given transition has a valid ID - an existing event name.  
     * @param trans
     */
    @Check
    public void checkTransitionInternalEvent(transition trans)
    {        
        boolean found = false;
        if(trans.getScoped().size() == 0)
        {
            boolean done = false;
            EObject parent = trans.eContainer();
            do {
                if (parent instanceof serviceDef) {
                    if (eventExists(trans.getName(), ((serviceDef) parent).getInternalEventSet().getDefs()))                    
                    {
                        found = true;
                    }
                    if(messageExists(trans.getName(), ((serviceDef) parent).getMessageSet()))
                    {
                    	found = true;
                    }
                    done = true;
                }
                parent = parent.eContainer();
            } while (!done);
        }
        else{
            serviceDef sd = trans.getScoped().get(trans.getScoped().size() -1).getImportedNamespace();
            if(sd.getInternalEventSet().getDefs().size() > 0)
            {                
                if(eventExists(trans.getName(), sd.getInternalEventSet().getDefs()))
                {
                    found = true;
                }
                if(messageExists(trans.getName(), sd.getMessageSet()))
                {
                	found = true;
                }
            }
        }        
        if(!found)
        {
            warning("Internal transition name should be an event or message", XtextPackage.GRAMMAR__RULES);
        }
    }
    
    

	/**
     * Ensures that all of the scoping within a transition is accurate.
     * @param trans
     */
    @Check
    public void checkTransitionParamScopes(transition trans)
    {        
        if(trans.getScoped().size()>1)
        {
            for(int pos = 1; pos<trans.getScoped().size(); pos++)
            {
                if(trans.getScoped().get(pos-1).getImportedNamespace().getRefs() == null)
                {
                    error(trans.getScoped().get(pos-1).getImportedNamespace().getServiceName() + " has no inherited services.", XtextPackage.GRAMMAR__RULES);
                }
                else if(!trans.getScoped().get(pos-1).getImportedNamespace().getRefs().getRefInherit().getImportedNamespace().equals(trans.getScoped().get(pos).getImportedNamespace()))
                {
                    error(trans.getScoped().get(pos).getName() + " is not a child of " + trans.getScoped().get(pos-1).getName(), XtextPackage.GRAMMAR__RULES);
                }
            }
        }
        
        for(transParam t: trans.getParams().getParams())
        {
            if(t.getScopedEventType() != null)
            {
                // Check that the first object is within scope of the transition name.
                if(trans.getScoped().size()>0)
                {
                    if(!trans.getScoped().get(trans.getScoped().size()-1).getImportedNamespace().getInternalEventSet().getDefs().contains(t.getScopedEventType().getName()))//|| !trans.getScoped().get(trans.getScoped().size()-1).getImportedNamespace().getTypeSet().getTypeDef().contains(t.getScopedEventType().getName()))
                    {
                        error(t.getName() + " is an illegal transition parameter. Check parameter type.", XtextPackage.GRAMMAR__RULES);
                    }
                }
            }            
        }
    }
    /**
     * Ensure state machine name exists in the given scope.
     * If there are no objects in the scoped list, the name can be new.
     * @param sm
     */
    @Check
    public void checkStateMachineName(stateMachine sm)
    {
        boolean found = false;
        if(sm.getScoped().size() > 0)
        {
            // Search for state machine name.
            for(stateMachine m:sm.getScoped().get(sm.getScoped().size() -1).getImportedNamespace().getProtocolBehavior().getStateMachine())
            {
                if(m.getName().equalsIgnoreCase(sm.getName()))
                {
                    found = true;
                }
            }            
            // Make sure list of scoped IDs is correct.
            for(int pos = 1; pos<sm.getScoped().size(); pos++)
            { 
                if(sm.getScoped().get(pos-1).getImportedNamespace().getRefs() == null)
                {
                    error(sm.getScoped().get(pos-1).getImportedNamespace().getServiceName() + " has no inherited services.",XtextPackage.GRAMMAR__RULES);
                }
                else if(!sm.getScoped().get(pos-1).getImportedNamespace().getRefs().getRefInherit().getImportedNamespace().equals(sm.getScoped().get(pos).getImportedNamespace()))
                {
                    error(sm.getScoped().get(pos).getName() + " is not a child of " + sm.getScoped().get(pos-1).getName(), XtextPackage.GRAMMAR__RULES);
                }
            }
        }
        if(!found)
        {
            error("State machine name not found in " + sm.getScoped().get(sm.getScoped().size() -1).getName(), XtextPackage.GRAMMAR__RULES);
        }
    }
    /**
     * Ensures correct scoped rule, as the scope provider will resolve each item in the list independently.
     * @param ss
     */
    @Check
    public void checkStartStateScope(startState ss)
    {
        if(ss.getScoped().size() >1)
        {
            for(int pos=1; pos<ss.getScoped().size(); pos++)
            {
                if(!ss.getScoped().get(pos-1).getSubState().contains(ss.getScoped().get(pos)))
                {
                    error(ss.getScoped().get(pos).getName() + " is not a child of " + ss.getScoped().get(pos-1).getName(),  XtextPackage.GRAMMAR__RULES);
                }
            }
        }
    }
    /**
     * Check a the scoped ID for the scopedConstId rule ensure the linking is legal.
     * @param ID
     */
    @Check
    public void checkScopedConst(scopedConstId ID)
    {
        if(ID.getNames().size() >1)
        {
            for(int pos=1; pos<ID.getNames().size(); pos++)
            {
                compareRefs(ID.getNames().get(pos-1), ID.getNames().get(pos));
            }
        }
    }
    /**
     * Check a the scoped ID for the scopedType rule ensure the linking is legal.
     * @param ID
     */
    @Check
    public void checkScopedType(scopedType ID)
    {
        if(ID.getNames().size() >1)
        {
            for(int pos=1; pos<ID.getNames().size(); pos++)
            {
                compareRefs(ID.getNames().get(pos-1), ID.getNames().get(pos));
            }
        }
    }
    
    /**
     * Check a the scoped ID for the messageScopedTypeRef rule ensure the linking is legal.
     * @param ID
     */
    @Check
    public void checkMessageScopedTypeRef(messageScopedRef ID)
    {
        if(ID.getScopes().size() >1)
        {
            for(int pos=1; pos<ID.getScopes().size(); pos++)
            {
                compareRefs(ID.getScopes().get(pos-1), ID.getScopes().get(pos));
            }
        }
    }
    
    /**
     * Check a the scoped ID for the scopedArrayType rule ensure the linking is legal.
     * @param ID
     */
//    @Check
//    public void checkScopedArrayType(scopedArrayType ID)
//    {
//        if(ID.getNames().size() >1)
//        {
//            for(int pos=1; pos<ID.getNames().size(); pos++)
//            {
//                compareRefs(ID.getNames().get(pos-1), ID.getNames().get(pos));
//            }
//        }
//    }
    
    
    /**
     * Check a the scoped ID for the scopedContainerRef rule ensure the linking is legal.
     * @param ID
     */
//    @Check
//    public void checkScopedContainer(scopedContainerRef ID)
//    {
//        if(ID.getNames().size() >1)
//        {
//            for(int pos=1; pos<ID.getNames().size(); pos++)
//            {
//                compareRefs(ID.getNames().get(pos-1), ID.getNames().get(pos));
//            }
//        }
//    }
    /**
     * Checks a transition to make sure that it has an end state only if needed.
     * @param trans the transition EObject to be checked.
     */
    @Check
    public void checkTransitionNextState(transition trans)
    {
        if(trans.getType().equalsIgnoreCase("simple") )
        {
            if(((org.jts.eclipse.cjsidl.simpleTransition)trans.getDestination()).getNextState() == null)
            {
                error("Simple transition must contain an end state.", XtextPackage.TYPE_REF);
            }
        }
        else if( trans.getType().equalsIgnoreCase("push"))
        {
            if(((org.jts.eclipse.cjsidl.pushTransition)trans.getDestination()).getNextState() == null)
            {
                error("Push transition must contain an end state.", XtextPackage.TYPE_REF);
            }
        }
    }
    
    /**
     * Checks the given transition to make sure no duplicates exist in the state machine.
     * @param trans
     */
    @Check
    public void checkTransitionDuplicate(transition trans)
    {
        // TODO: Compare name, params, and guards.
   
//        if(transitionEquality(trans))
//        {
//            error("You may not have duplicate transitions within a state.", null, XtextPackage.GRAMMAR__RULES);
//        }
    }
        
    /**
     * Helper function to compare two references and throw an error if the parent does not contain the child.
     * @param obj1 parent object - may be a refAttr, declaredTypeSetRef, or declaredConstSetRef
     * @param obj2 child object - may be a refAttr, declaredTypeSetRef, or declaredConstSetRef
     */
    private void compareRefs(EObject obj1, EObject obj2)
    {
        if(obj1 instanceof refAttr)
        {
            // must check for all three types.
            if(obj2 instanceof refAttr)
            {
                if(((refAttr)obj1).getImportedNamespace().getRefs() == null)
                {
                    error(((refAttr)obj1).getImportedNamespace().getServiceName() + " has no inherited services.", null, XtextPackage.GRAMMAR__RULES);
                }
                else if(!((refAttr)obj1).getImportedNamespace().getRefs().getRefInherit().getImportedNamespace().equals(((refAttr)obj2).getImportedNamespace()))
                {
                    error(((refAttr)obj2).getName() + " is not a child of " + ((refAttr)obj1).getName(), XtextPackage.GRAMMAR__RULES);
                }
            }
            else if(obj2 instanceof declaredTypeSetRef)
            {
                if(!((refAttr)obj1).getImportedNamespace().getTypeSet().getDeclaredTypeSetRef().contains((declaredTypeSetRef)obj2))
                {
                    error(((declaredTypeSetRef)obj2).getName() + " is not a child of " + ((refAttr)obj1).getName(), XtextPackage.GRAMMAR__RULES);
                }
            }
            else if(obj2 instanceof declaredConstSetRef)
            {
                if(!((refAttr)obj1).getImportedNamespace().getTypeSet().getDeclaredConstSetRef().contains((declaredConstSetRef) obj2) &&
                   !((refAttr)obj1).getImportedNamespace().getConstSet().getConstDef().contains((declaredConstSetRef) obj2))
                {
                    error(((declaredConstSetRef)obj2).getName() + " is not a child of " + ((refAttr)obj1).getName(), XtextPackage.GRAMMAR__RULES);
                }
            }
        }
        else if(obj1 instanceof declaredTypeSetRef)
        {
            // A declared type set can contain a declared constant set or another declared type set
            if(obj2 instanceof declaredTypeSetRef)
            {
                if(!((declaredTypeSetRef)obj1).getImportedNamespace().getDeclaredTypeSetRef().contains(((declaredTypeSetRef)obj2)))
                {
                    error(((declaredTypeSetRef)obj2).getName() + " is not a child of " + ((declaredTypeSetRef)obj1).getName(), XtextPackage.GRAMMAR__RULES);
                }
            }
            else if(obj2 instanceof declaredConstSetRef)
            {
                if(!((declaredTypeSetRef)obj1).getImportedNamespace().getDeclaredConstSetRef().contains(((declaredConstSetRef)obj2)))
                {
                    error(((declaredConstSetRef)obj2).getName() + " is not a child of " + ((declaredTypeSetRef)obj1).getName(), XtextPackage.GRAMMAR__RULES);
                }
            }
        }
        else if(obj1 instanceof declaredConstSetRef)
        {
            // A constant set can only reference another constant set.
            if(!((declaredConstSetRef)obj1).getImportedNamespace().getDeclaredConstSetRef().contains(((declaredConstSetRef)obj2)))
            {
                error(((declaredConstSetRef)obj2).getName() + " is not a child of " + ((declaredConstSetRef)obj1).getName(), XtextPackage.GRAMMAR__RULES);
            }
        }
    }
    /**
     *  Helper function to check an event object against a given ID.
     * @param id
     * @param list
     * @return
     */
    private boolean eventExists(String id, List<EObject> list)
    {
        for(EObject obj: list)
        {
            if(obj instanceof eventDef)
            {
                if(((eventDef) obj).getName().equalsIgnoreCase(id))
                {
                    return true;
                }
            }else if(obj instanceof declaredEventDef)
            {
                if(((declaredEventDef) obj).getName().equalsIgnoreCase(id))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean messageExists(String name, messageSet messageSet) {
    	for(messageDef m: messageSet.getInputSet().getMessageDefs())
    	{
    		if(m.getName().equals(name))
    		{
    			return true;
    		}
    	}
    	for(messageDef m: messageSet.getOutputSet().getMessageDefs())
    	{
    		if(m.getName().equals(name))
    		{
    			return true;
    		}
    	}
		return false;
	}
    
    /**
     * Returns true if there is another transition equal to the given transition.
     * @param trans
     * @return
     */
    private boolean transitionEquality(transition trans) {
        List<transition> toCheck = new ArrayList<transition>();
        getTransitions(toCheck, trans);        
        int counter = 0; // counter should equal 1 (trans will equal itself), 2 will throw an error.
        
        for(transition t: toCheck)
        {
            if(checkGuard(t.getTransGuard(), trans.getTransGuard()) && 
                    t.getName().equalsIgnoreCase(trans.getName()) &&
                    t.getScoped().size() == trans.getScoped().size() &&
                    sameScoped(t.getScoped(), trans.getScoped())){
               //checkTrigger(t.getName(), trans.getName())){
                counter++;
            }            
        }
        if(counter >1){
            return true;
        }
        return false;
    }
    
    /**
     * Checks that two lists of serviceDef references from a scoped ID are the same.
     * @param listA
     * @param listB
     * @return
     */
    private boolean sameScoped(List<refAttr> listA, List<refAttr> listB)
    {
        int size;
        if(listA.size()>listB.size())
        {
            size = listA.size();
        }else
        {
            size = listB.size();
        }
        
        for(int i = 0; i<size; i++){
            if(!listA.get(i).getName().equalsIgnoreCase(listB.get(i).getName()))
            {
                return false;
            }
        }
        return true;
    }
    

    /**
     * returns true if guards are equal.
     * @param g1
     * @param g2
     * @return
     */
    private boolean checkGuard(guard g1, guard g2)
    {
        if(g1 == null && g2 == null)
        {
            return true;
        }
        return checkExpression(g1, g2);
    }
    
    /**
     * Returns true if the expressions are equal.
     * @param expr1
     * @param expr2
     * @return
     */
    private boolean checkExpression(guard expr1, guard expr2)
    {
//        if((expr1.getExpr() == null && expr2.getExpr() == null) || 
//           (expr1.getExpr().getAndExpression() == null && expr2.getExpr().getAndExpression() == null))
//        {
//            return true;
//        }
//        // if the list of expressions are different lengths, they must be different.
//        if(expr1.getExpr().getAndExpression().size() != expr2.getExpr().getAndExpression().size())
//        {
//            return false;
//        }        
//        for(int i = 0; i<expr1.getExpr().getAndExpression().size(); i++)
//        {
//            if(! checkAndExpr(expr1.getExpr().getAndExpression().get(i), expr2.getExpr().getAndExpression().get(i)))
//            {               
//                return false;
//            }            
//        }
        return true;
    }


    /**
     * Helper function to retrieve all of the transitions in scope that need to be checked.
     * @param transitions list of transitions to compare.
     * @param trans current transition - starting point to find remainder of transitions.
     */
    private void getTransitions(List<transition> transitions, transition trans)
    {
        EObject parent = (EObject) trans;
        do{
            if (parent instanceof state) {
                transitions.addAll(((state)parent).getTransitions());
            }
            parent = parent.eContainer();
        }while(parent!=null);
    }
    


}
