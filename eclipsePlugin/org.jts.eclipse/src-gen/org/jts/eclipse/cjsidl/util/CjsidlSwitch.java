/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.jts.eclipse.cjsidl.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.jts.eclipse.cjsidl.CjsidlPackage
 * @generated
 */
public class CjsidlSwitch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static CjsidlPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CjsidlSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = CjsidlPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public T doSwitch(EObject theEObject)
  {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(EClass theEClass, EObject theEObject)
  {
    if (theEClass.eContainer() == modelPackage)
    {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    }
    else
    {
      List<EClass> eSuperTypes = theEClass.getESuperTypes();
      return
        eSuperTypes.isEmpty() ?
          defaultCase(theEObject) :
          doSwitch(eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case CjsidlPackage.JAUS:
      {
        jaus jaus = (jaus)theEObject;
        T result = casejaus(jaus);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.SERVICE_DEF:
      {
        serviceDef serviceDef = (serviceDef)theEObject;
        T result = caseserviceDef(serviceDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.DESCRIPTION:
      {
        description description = (description)theEObject;
        T result = casedescription(description);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.REFERENCES:
      {
        references references = (references)theEObject;
        T result = casereferences(references);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.REF_ATTR:
      {
        refAttr refAttr = (refAttr)theEObject;
        T result = caserefAttr(refAttr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.DECLARED_CONST_SET:
      {
        declaredConstSet declaredConstSet = (declaredConstSet)theEObject;
        T result = casedeclaredConstSet(declaredConstSet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.DECLARED_CONST_SET_REF:
      {
        declaredConstSetRef declaredConstSetRef = (declaredConstSetRef)theEObject;
        T result = casedeclaredConstSetRef(declaredConstSetRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.DECLARED_TYPE_SET:
      {
        declaredTypeSet declaredTypeSet = (declaredTypeSet)theEObject;
        T result = casedeclaredTypeSet(declaredTypeSet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.MESSAGE_SET:
      {
        messageSet messageSet = (messageSet)theEObject;
        T result = casemessageSet(messageSet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.MESSAGES:
      {
        messages messages = (messages)theEObject;
        T result = casemessages(messages);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.INTERNAL_EVENT_SET:
      {
        internalEventSet internalEventSet = (internalEventSet)theEObject;
        T result = caseinternalEventSet(internalEventSet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.EVENT_DEF:
      {
        eventDef eventDef = (eventDef)theEObject;
        T result = caseeventDef(eventDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.MESSAGE_REF:
      {
        messageRef messageRef = (messageRef)theEObject;
        T result = casemessageRef(messageRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.PROTOCOL_BEHAVIOR:
      {
        protocolBehavior protocolBehavior = (protocolBehavior)theEObject;
        T result = caseprotocolBehavior(protocolBehavior);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.START_STATE:
      {
        startState startState = (startState)theEObject;
        T result = casestartState(startState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.STATE_MACHINE:
      {
        stateMachine stateMachine = (stateMachine)theEObject;
        T result = casestateMachine(stateMachine);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.STATE:
      {
        state state = (state)theEObject;
        T result = casestate(state);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.DEFAULT_STATE:
      {
        defaultState defaultState = (defaultState)theEObject;
        T result = casedefaultState(defaultState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.ENTRY:
      {
        entry entry = (entry)theEObject;
        T result = caseentry(entry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.EXIT:
      {
        exit exit = (exit)theEObject;
        T result = caseexit(exit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.TRANS_PARAMS:
      {
        transParams transParams = (transParams)theEObject;
        T result = casetransParams(transParams);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.TRANS_PARAM:
      {
        transParam transParam = (transParam)theEObject;
        T result = casetransParam(transParam);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.TRANSITION:
      {
        transition transition = (transition)theEObject;
        T result = casetransition(transition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.DEFAULT_TRANSITION:
      {
        defaultTransition defaultTransition = (defaultTransition)theEObject;
        T result = casedefaultTransition(defaultTransition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.INTERNAL_TRANSITION:
      {
        internalTransition internalTransition = (internalTransition)theEObject;
        T result = caseinternalTransition(internalTransition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.SIMPLE_TRANSITION:
      {
        simpleTransition simpleTransition = (simpleTransition)theEObject;
        T result = casesimpleTransition(simpleTransition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.PUSH_TRANSITION:
      {
        pushTransition pushTransition = (pushTransition)theEObject;
        T result = casepushTransition(pushTransition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.POP_TRANSITION:
      {
        popTransition popTransition = (popTransition)theEObject;
        T result = casepopTransition(popTransition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.NEXT_STATE:
      {
        nextState nextState = (nextState)theEObject;
        T result = casenextState(nextState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.GUARD:
      {
        guard guard = (guard)theEObject;
        T result = caseguard(guard);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.GUARD_ACTION:
      {
        guardAction guardAction = (guardAction)theEObject;
        T result = caseguardAction(guardAction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.GUARD_PARAM:
      {
        guardParam guardParam = (guardParam)theEObject;
        T result = caseguardParam(guardParam);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.ACTION_LIST:
      {
        actionList actionList = (actionList)theEObject;
        T result = caseactionList(actionList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.SEND_ACTION_LIST:
      {
        sendActionList sendActionList = (sendActionList)theEObject;
        T result = casesendActionList(sendActionList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.ACTION:
      {
        action action = (action)theEObject;
        T result = caseaction(action);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.CONST_DEF:
      {
        constDef constDef = (constDef)theEObject;
        T result = caseconstDef(constDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.DECLARED_TYPE_SET_REF:
      {
        declaredTypeSetRef declaredTypeSetRef = (declaredTypeSetRef)theEObject;
        T result = casedeclaredTypeSetRef(declaredTypeSetRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.TYPE_DEF:
      {
        typeDef typeDef = (typeDef)theEObject;
        T result = casetypeDef(typeDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.MESSAGE_DEF:
      {
        messageDef messageDef = (messageDef)theEObject;
        T result = casemessageDef(messageDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.HEADER_DEF:
      {
        headerDef headerDef = (headerDef)theEObject;
        T result = caseheaderDef(headerDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.BODY_DEF:
      {
        bodyDef bodyDef = (bodyDef)theEObject;
        T result = casebodyDef(bodyDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.FOOTER_DEF:
      {
        footerDef footerDef = (footerDef)theEObject;
        T result = casefooterDef(footerDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.HEADER_REF:
      {
        headerRef headerRef = (headerRef)theEObject;
        T result = caseheaderRef(headerRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.BODY_REF:
      {
        bodyRef bodyRef = (bodyRef)theEObject;
        T result = casebodyRef(bodyRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.FOOTER_REF:
      {
        footerRef footerRef = (footerRef)theEObject;
        T result = casefooterRef(footerRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.HEADER_SCOPED_REF:
      {
        headerScopedRef headerScopedRef = (headerScopedRef)theEObject;
        T result = caseheaderScopedRef(headerScopedRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.BODY_SCOPED_REF:
      {
        bodyScopedRef bodyScopedRef = (bodyScopedRef)theEObject;
        T result = casebodyScopedRef(bodyScopedRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.FOOTER_SCOPED_REF:
      {
        footerScopedRef footerScopedRef = (footerScopedRef)theEObject;
        T result = casefooterScopedRef(footerScopedRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.CONTAINER_DEF:
      {
        containerDef containerDef = (containerDef)theEObject;
        T result = casecontainerDef(containerDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.CONTAINER_REF:
      {
        containerRef containerRef = (containerRef)theEObject;
        T result = casecontainerRef(containerRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.DECLARED_EVENT_DEF:
      {
        declaredEventDef declaredEventDef = (declaredEventDef)theEObject;
        T result = casedeclaredEventDef(declaredEventDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.SIMPLE_NUMERIC_TYPE:
      {
        simpleNumericType simpleNumericType = (simpleNumericType)theEObject;
        T result = casesimpleNumericType(simpleNumericType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.FIXED_LEN_STRING:
      {
        fixedLenString fixedLenString = (fixedLenString)theEObject;
        T result = casefixedLenString(fixedLenString);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.VAR_LEN_STRING:
      {
        varLenString varLenString = (varLenString)theEObject;
        T result = casevarLenString(varLenString);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.FIXED_FIELD_DEF:
      {
        fixedFieldDef fixedFieldDef = (fixedFieldDef)theEObject;
        T result = casefixedFieldDef(fixedFieldDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.VAR_FIELD:
      {
        varField varField = (varField)theEObject;
        T result = casevarField(varField);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.VAR_LEN_FIELD:
      {
        varLenField varLenField = (varLenField)theEObject;
        T result = casevarLenField(varLenField);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.TAGGED_UNITS_ENUM:
      {
        taggedUnitsEnum taggedUnitsEnum = (taggedUnitsEnum)theEObject;
        T result = casetaggedUnitsEnum(taggedUnitsEnum);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.VAR_FORMAT_FIELD:
      {
        varFormatField varFormatField = (varFormatField)theEObject;
        T result = casevarFormatField(varFormatField);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.FORMAT_ENUM_DEF:
      {
        formatEnumDef formatEnumDef = (formatEnumDef)theEObject;
        T result = caseformatEnumDef(formatEnumDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.VALUE_SET_DEF:
      {
        valueSetDef valueSetDef = (valueSetDef)theEObject;
        T result = casevalueSetDef(valueSetDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.BITFIELD_DEF:
      {
        bitfieldDef bitfieldDef = (bitfieldDef)theEObject;
        T result = casebitfieldDef(bitfieldDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.VALUE_RANGE:
      {
        valueRange valueRange = (valueRange)theEObject;
        T result = casevalueRange(valueRange);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.VALUE_SPEC:
      {
        valueSpec valueSpec = (valueSpec)theEObject;
        T result = casevalueSpec(valueSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.SCALED_RANGE_DEF:
      {
        scaledRangeDef scaledRangeDef = (scaledRangeDef)theEObject;
        T result = casescaledRangeDef(scaledRangeDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.SUB_FIELD:
      {
        subField subField = (subField)theEObject;
        T result = casesubField(subField);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.LIST_DEF:
      {
        listDef listDef = (listDef)theEObject;
        T result = caselistDef(listDef);
        if (result == null) result = casecontainerDef(listDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.VARIANT_DEF:
      {
        variantDef variantDef = (variantDef)theEObject;
        T result = casevariantDef(variantDef);
        if (result == null) result = casecontainerDef(variantDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.TAGGED_ITEM_DEF:
      {
        taggedItemDef taggedItemDef = (taggedItemDef)theEObject;
        T result = casetaggedItemDef(taggedItemDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.SEQUENCE_DEF:
      {
        sequenceDef sequenceDef = (sequenceDef)theEObject;
        T result = casesequenceDef(sequenceDef);
        if (result == null) result = casecontainerDef(sequenceDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.RECORD_DEF:
      {
        recordDef recordDef = (recordDef)theEObject;
        T result = caserecordDef(recordDef);
        if (result == null) result = casecontainerDef(recordDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.CONST_REFERENCE:
      {
        constReference constReference = (constReference)theEObject;
        T result = caseconstReference(constReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.TYPE_REFERENCE:
      {
        typeReference typeReference = (typeReference)theEObject;
        T result = casetypeReference(typeReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.ARRAY_DEF:
      {
        arrayDef arrayDef = (arrayDef)theEObject;
        T result = casearrayDef(arrayDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.MESSAGE_SCOPED_REF:
      {
        messageScopedRef messageScopedRef = (messageScopedRef)theEObject;
        T result = casemessageScopedRef(messageScopedRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.SCOPED_TYPE:
      {
        scopedType scopedType = (scopedType)theEObject;
        T result = casescopedType(scopedType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.SCOPED_EVENT_TYPE:
      {
        scopedEventType scopedEventType = (scopedEventType)theEObject;
        T result = casescopedEventType(scopedEventType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.SCOPED_TYPE_ID:
      {
        scopedTypeId scopedTypeId = (scopedTypeId)theEObject;
        T result = casescopedTypeId(scopedTypeId);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CjsidlPackage.SCOPED_CONST_ID:
      {
        scopedConstId scopedConstId = (scopedConstId)theEObject;
        T result = casescopedConstId(scopedConstId);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>jaus</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>jaus</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casejaus(jaus object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>service Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>service Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseserviceDef(serviceDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>description</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>description</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedescription(description object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>references</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>references</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casereferences(references object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ref Attr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ref Attr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caserefAttr(refAttr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>declared Const Set</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>declared Const Set</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedeclaredConstSet(declaredConstSet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>declared Const Set Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>declared Const Set Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedeclaredConstSetRef(declaredConstSetRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>declared Type Set</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>declared Type Set</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedeclaredTypeSet(declaredTypeSet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>message Set</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>message Set</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemessageSet(messageSet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>messages</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>messages</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemessages(messages object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>internal Event Set</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>internal Event Set</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseinternalEventSet(internalEventSet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>event Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>event Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseeventDef(eventDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>message Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>message Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemessageRef(messageRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>protocol Behavior</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>protocol Behavior</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseprotocolBehavior(protocolBehavior object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>start State</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>start State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casestartState(startState object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>state Machine</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>state Machine</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casestateMachine(stateMachine object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>state</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>state</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casestate(state object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>default State</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>default State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedefaultState(defaultState object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseentry(entry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>exit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>exit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseexit(exit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>trans Params</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>trans Params</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetransParams(transParams object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>trans Param</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>trans Param</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetransParam(transParam object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>transition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>transition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetransition(transition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>default Transition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>default Transition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedefaultTransition(defaultTransition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>internal Transition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>internal Transition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseinternalTransition(internalTransition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>simple Transition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>simple Transition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casesimpleTransition(simpleTransition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>push Transition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>push Transition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casepushTransition(pushTransition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>pop Transition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>pop Transition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casepopTransition(popTransition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>next State</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>next State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casenextState(nextState object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>guard</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>guard</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseguard(guard object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>guard Action</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>guard Action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseguardAction(guardAction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>guard Param</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>guard Param</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseguardParam(guardParam object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>action List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>action List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseactionList(actionList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>send Action List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>send Action List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casesendActionList(sendActionList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>action</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseaction(action object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>const Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>const Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconstDef(constDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>declared Type Set Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>declared Type Set Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedeclaredTypeSetRef(declaredTypeSetRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>type Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>type Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetypeDef(typeDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>message Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>message Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemessageDef(messageDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>header Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>header Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseheaderDef(headerDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>body Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>body Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casebodyDef(bodyDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>footer Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>footer Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefooterDef(footerDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>header Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>header Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseheaderRef(headerRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>body Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>body Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casebodyRef(bodyRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>footer Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>footer Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefooterRef(footerRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>header Scoped Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>header Scoped Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseheaderScopedRef(headerScopedRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>body Scoped Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>body Scoped Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casebodyScopedRef(bodyScopedRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>footer Scoped Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>footer Scoped Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefooterScopedRef(footerScopedRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>container Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>container Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casecontainerDef(containerDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>container Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>container Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casecontainerRef(containerRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>declared Event Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>declared Event Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedeclaredEventDef(declaredEventDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>simple Numeric Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>simple Numeric Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casesimpleNumericType(simpleNumericType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>fixed Len String</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>fixed Len String</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefixedLenString(fixedLenString object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>var Len String</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>var Len String</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevarLenString(varLenString object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>fixed Field Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>fixed Field Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefixedFieldDef(fixedFieldDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>var Field</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>var Field</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevarField(varField object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>var Len Field</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>var Len Field</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevarLenField(varLenField object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>tagged Units Enum</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>tagged Units Enum</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetaggedUnitsEnum(taggedUnitsEnum object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>var Format Field</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>var Format Field</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevarFormatField(varFormatField object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>format Enum Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>format Enum Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseformatEnumDef(formatEnumDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>value Set Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>value Set Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevalueSetDef(valueSetDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>bitfield Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>bitfield Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casebitfieldDef(bitfieldDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>value Range</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>value Range</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevalueRange(valueRange object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>value Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>value Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevalueSpec(valueSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>scaled Range Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>scaled Range Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casescaledRangeDef(scaledRangeDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>sub Field</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>sub Field</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casesubField(subField object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>list Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>list Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caselistDef(listDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>variant Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>variant Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevariantDef(variantDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>tagged Item Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>tagged Item Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetaggedItemDef(taggedItemDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>sequence Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>sequence Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casesequenceDef(sequenceDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>record Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>record Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caserecordDef(recordDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>const Reference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>const Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconstReference(constReference object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>type Reference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>type Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetypeReference(typeReference object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>array Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>array Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casearrayDef(arrayDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>message Scoped Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>message Scoped Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemessageScopedRef(messageScopedRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>scoped Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>scoped Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casescopedType(scopedType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>scoped Event Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>scoped Event Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casescopedEventType(scopedEventType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>scoped Type Id</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>scoped Type Id</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casescopedTypeId(scopedTypeId object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>scoped Const Id</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>scoped Const Id</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casescopedConstId(scopedConstId object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public T defaultCase(EObject object)
  {
    return null;
  }

} //CjsidlSwitch
