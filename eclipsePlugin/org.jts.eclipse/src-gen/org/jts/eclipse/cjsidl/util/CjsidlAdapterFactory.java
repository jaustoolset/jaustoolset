/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.jts.eclipse.cjsidl.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.jts.eclipse.cjsidl.CjsidlPackage
 * @generated
 */
public class CjsidlAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static CjsidlPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CjsidlAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = CjsidlPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CjsidlSwitch<Adapter> modelSwitch =
    new CjsidlSwitch<Adapter>()
    {
      @Override
      public Adapter casejaus(jaus object)
      {
        return createjausAdapter();
      }
      @Override
      public Adapter caseserviceDef(serviceDef object)
      {
        return createserviceDefAdapter();
      }
      @Override
      public Adapter casedescription(description object)
      {
        return createdescriptionAdapter();
      }
      @Override
      public Adapter casereferences(references object)
      {
        return createreferencesAdapter();
      }
      @Override
      public Adapter caserefAttr(refAttr object)
      {
        return createrefAttrAdapter();
      }
      @Override
      public Adapter casedeclaredConstSet(declaredConstSet object)
      {
        return createdeclaredConstSetAdapter();
      }
      @Override
      public Adapter casedeclaredConstSetRef(declaredConstSetRef object)
      {
        return createdeclaredConstSetRefAdapter();
      }
      @Override
      public Adapter casedeclaredTypeSet(declaredTypeSet object)
      {
        return createdeclaredTypeSetAdapter();
      }
      @Override
      public Adapter casemessageSet(messageSet object)
      {
        return createmessageSetAdapter();
      }
      @Override
      public Adapter casemessages(messages object)
      {
        return createmessagesAdapter();
      }
      @Override
      public Adapter caseinternalEventSet(internalEventSet object)
      {
        return createinternalEventSetAdapter();
      }
      @Override
      public Adapter caseeventDef(eventDef object)
      {
        return createeventDefAdapter();
      }
      @Override
      public Adapter casemessageRef(messageRef object)
      {
        return createmessageRefAdapter();
      }
      @Override
      public Adapter caseprotocolBehavior(protocolBehavior object)
      {
        return createprotocolBehaviorAdapter();
      }
      @Override
      public Adapter casestartState(startState object)
      {
        return createstartStateAdapter();
      }
      @Override
      public Adapter casestateMachine(stateMachine object)
      {
        return createstateMachineAdapter();
      }
      @Override
      public Adapter casestate(state object)
      {
        return createstateAdapter();
      }
      @Override
      public Adapter casedefaultState(defaultState object)
      {
        return createdefaultStateAdapter();
      }
      @Override
      public Adapter caseentry(entry object)
      {
        return createentryAdapter();
      }
      @Override
      public Adapter caseexit(exit object)
      {
        return createexitAdapter();
      }
      @Override
      public Adapter casetransParams(transParams object)
      {
        return createtransParamsAdapter();
      }
      @Override
      public Adapter casetransParam(transParam object)
      {
        return createtransParamAdapter();
      }
      @Override
      public Adapter casetransition(transition object)
      {
        return createtransitionAdapter();
      }
      @Override
      public Adapter casedefaultTransition(defaultTransition object)
      {
        return createdefaultTransitionAdapter();
      }
      @Override
      public Adapter caseinternalTransition(internalTransition object)
      {
        return createinternalTransitionAdapter();
      }
      @Override
      public Adapter casesimpleTransition(simpleTransition object)
      {
        return createsimpleTransitionAdapter();
      }
      @Override
      public Adapter casepushTransition(pushTransition object)
      {
        return createpushTransitionAdapter();
      }
      @Override
      public Adapter casepopTransition(popTransition object)
      {
        return createpopTransitionAdapter();
      }
      @Override
      public Adapter casenextState(nextState object)
      {
        return createnextStateAdapter();
      }
      @Override
      public Adapter caseguard(guard object)
      {
        return createguardAdapter();
      }
      @Override
      public Adapter caseguardAction(guardAction object)
      {
        return createguardActionAdapter();
      }
      @Override
      public Adapter caseguardParam(guardParam object)
      {
        return createguardParamAdapter();
      }
      @Override
      public Adapter caseactionList(actionList object)
      {
        return createactionListAdapter();
      }
      @Override
      public Adapter casesendActionList(sendActionList object)
      {
        return createsendActionListAdapter();
      }
      @Override
      public Adapter caseaction(action object)
      {
        return createactionAdapter();
      }
      @Override
      public Adapter caseconstDef(constDef object)
      {
        return createconstDefAdapter();
      }
      @Override
      public Adapter casedeclaredTypeSetRef(declaredTypeSetRef object)
      {
        return createdeclaredTypeSetRefAdapter();
      }
      @Override
      public Adapter casetypeDef(typeDef object)
      {
        return createtypeDefAdapter();
      }
      @Override
      public Adapter casemessageDef(messageDef object)
      {
        return createmessageDefAdapter();
      }
      @Override
      public Adapter caseheaderDef(headerDef object)
      {
        return createheaderDefAdapter();
      }
      @Override
      public Adapter casebodyDef(bodyDef object)
      {
        return createbodyDefAdapter();
      }
      @Override
      public Adapter casefooterDef(footerDef object)
      {
        return createfooterDefAdapter();
      }
      @Override
      public Adapter caseheaderRef(headerRef object)
      {
        return createheaderRefAdapter();
      }
      @Override
      public Adapter casebodyRef(bodyRef object)
      {
        return createbodyRefAdapter();
      }
      @Override
      public Adapter casefooterRef(footerRef object)
      {
        return createfooterRefAdapter();
      }
      @Override
      public Adapter caseheaderScopedRef(headerScopedRef object)
      {
        return createheaderScopedRefAdapter();
      }
      @Override
      public Adapter casebodyScopedRef(bodyScopedRef object)
      {
        return createbodyScopedRefAdapter();
      }
      @Override
      public Adapter casefooterScopedRef(footerScopedRef object)
      {
        return createfooterScopedRefAdapter();
      }
      @Override
      public Adapter casecontainerDef(containerDef object)
      {
        return createcontainerDefAdapter();
      }
      @Override
      public Adapter casecontainerRef(containerRef object)
      {
        return createcontainerRefAdapter();
      }
      @Override
      public Adapter casedeclaredEventDef(declaredEventDef object)
      {
        return createdeclaredEventDefAdapter();
      }
      @Override
      public Adapter casesimpleNumericType(simpleNumericType object)
      {
        return createsimpleNumericTypeAdapter();
      }
      @Override
      public Adapter casefixedLenString(fixedLenString object)
      {
        return createfixedLenStringAdapter();
      }
      @Override
      public Adapter casevarLenString(varLenString object)
      {
        return createvarLenStringAdapter();
      }
      @Override
      public Adapter casefixedFieldDef(fixedFieldDef object)
      {
        return createfixedFieldDefAdapter();
      }
      @Override
      public Adapter casevarField(varField object)
      {
        return createvarFieldAdapter();
      }
      @Override
      public Adapter casevarLenField(varLenField object)
      {
        return createvarLenFieldAdapter();
      }
      @Override
      public Adapter casetaggedUnitsEnum(taggedUnitsEnum object)
      {
        return createtaggedUnitsEnumAdapter();
      }
      @Override
      public Adapter casevarFormatField(varFormatField object)
      {
        return createvarFormatFieldAdapter();
      }
      @Override
      public Adapter caseformatEnumDef(formatEnumDef object)
      {
        return createformatEnumDefAdapter();
      }
      @Override
      public Adapter casevalueSetDef(valueSetDef object)
      {
        return createvalueSetDefAdapter();
      }
      @Override
      public Adapter casebitfieldDef(bitfieldDef object)
      {
        return createbitfieldDefAdapter();
      }
      @Override
      public Adapter casevalueRange(valueRange object)
      {
        return createvalueRangeAdapter();
      }
      @Override
      public Adapter casevalueSpec(valueSpec object)
      {
        return createvalueSpecAdapter();
      }
      @Override
      public Adapter casescaledRangeDef(scaledRangeDef object)
      {
        return createscaledRangeDefAdapter();
      }
      @Override
      public Adapter casesubField(subField object)
      {
        return createsubFieldAdapter();
      }
      @Override
      public Adapter caselistDef(listDef object)
      {
        return createlistDefAdapter();
      }
      @Override
      public Adapter casevariantDef(variantDef object)
      {
        return createvariantDefAdapter();
      }
      @Override
      public Adapter casetaggedItemDef(taggedItemDef object)
      {
        return createtaggedItemDefAdapter();
      }
      @Override
      public Adapter casesequenceDef(sequenceDef object)
      {
        return createsequenceDefAdapter();
      }
      @Override
      public Adapter caserecordDef(recordDef object)
      {
        return createrecordDefAdapter();
      }
      @Override
      public Adapter caseconstReference(constReference object)
      {
        return createconstReferenceAdapter();
      }
      @Override
      public Adapter casetypeReference(typeReference object)
      {
        return createtypeReferenceAdapter();
      }
      @Override
      public Adapter casearrayDef(arrayDef object)
      {
        return createarrayDefAdapter();
      }
      @Override
      public Adapter casemessageScopedRef(messageScopedRef object)
      {
        return createmessageScopedRefAdapter();
      }
      @Override
      public Adapter casescopedType(scopedType object)
      {
        return createscopedTypeAdapter();
      }
      @Override
      public Adapter casescopedEventType(scopedEventType object)
      {
        return createscopedEventTypeAdapter();
      }
      @Override
      public Adapter casescopedTypeId(scopedTypeId object)
      {
        return createscopedTypeIdAdapter();
      }
      @Override
      public Adapter casescopedConstId(scopedConstId object)
      {
        return createscopedConstIdAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.jaus <em>jaus</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.jaus
   * @generated
   */
  public Adapter createjausAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.serviceDef <em>service Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.serviceDef
   * @generated
   */
  public Adapter createserviceDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.description <em>description</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.description
   * @generated
   */
  public Adapter createdescriptionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.references <em>references</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.references
   * @generated
   */
  public Adapter createreferencesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.refAttr <em>ref Attr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.refAttr
   * @generated
   */
  public Adapter createrefAttrAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.declaredConstSet <em>declared Const Set</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.declaredConstSet
   * @generated
   */
  public Adapter createdeclaredConstSetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.declaredConstSetRef <em>declared Const Set Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.declaredConstSetRef
   * @generated
   */
  public Adapter createdeclaredConstSetRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.declaredTypeSet <em>declared Type Set</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.declaredTypeSet
   * @generated
   */
  public Adapter createdeclaredTypeSetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.messageSet <em>message Set</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.messageSet
   * @generated
   */
  public Adapter createmessageSetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.messages <em>messages</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.messages
   * @generated
   */
  public Adapter createmessagesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.internalEventSet <em>internal Event Set</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.internalEventSet
   * @generated
   */
  public Adapter createinternalEventSetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.eventDef <em>event Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.eventDef
   * @generated
   */
  public Adapter createeventDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.messageRef <em>message Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.messageRef
   * @generated
   */
  public Adapter createmessageRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.protocolBehavior <em>protocol Behavior</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.protocolBehavior
   * @generated
   */
  public Adapter createprotocolBehaviorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.startState <em>start State</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.startState
   * @generated
   */
  public Adapter createstartStateAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.stateMachine <em>state Machine</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.stateMachine
   * @generated
   */
  public Adapter createstateMachineAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.state <em>state</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.state
   * @generated
   */
  public Adapter createstateAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.defaultState <em>default State</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.defaultState
   * @generated
   */
  public Adapter createdefaultStateAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.entry <em>entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.entry
   * @generated
   */
  public Adapter createentryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.exit <em>exit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.exit
   * @generated
   */
  public Adapter createexitAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.transParams <em>trans Params</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.transParams
   * @generated
   */
  public Adapter createtransParamsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.transParam <em>trans Param</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.transParam
   * @generated
   */
  public Adapter createtransParamAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.transition <em>transition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.transition
   * @generated
   */
  public Adapter createtransitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.defaultTransition <em>default Transition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.defaultTransition
   * @generated
   */
  public Adapter createdefaultTransitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.internalTransition <em>internal Transition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.internalTransition
   * @generated
   */
  public Adapter createinternalTransitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.simpleTransition <em>simple Transition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.simpleTransition
   * @generated
   */
  public Adapter createsimpleTransitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.pushTransition <em>push Transition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.pushTransition
   * @generated
   */
  public Adapter createpushTransitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.popTransition <em>pop Transition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.popTransition
   * @generated
   */
  public Adapter createpopTransitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.nextState <em>next State</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.nextState
   * @generated
   */
  public Adapter createnextStateAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.guard <em>guard</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.guard
   * @generated
   */
  public Adapter createguardAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.guardAction <em>guard Action</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.guardAction
   * @generated
   */
  public Adapter createguardActionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.guardParam <em>guard Param</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.guardParam
   * @generated
   */
  public Adapter createguardParamAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.actionList <em>action List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.actionList
   * @generated
   */
  public Adapter createactionListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.sendActionList <em>send Action List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.sendActionList
   * @generated
   */
  public Adapter createsendActionListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.action <em>action</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.action
   * @generated
   */
  public Adapter createactionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.constDef <em>const Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.constDef
   * @generated
   */
  public Adapter createconstDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.declaredTypeSetRef <em>declared Type Set Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.declaredTypeSetRef
   * @generated
   */
  public Adapter createdeclaredTypeSetRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.typeDef <em>type Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.typeDef
   * @generated
   */
  public Adapter createtypeDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.messageDef <em>message Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.messageDef
   * @generated
   */
  public Adapter createmessageDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.headerDef <em>header Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.headerDef
   * @generated
   */
  public Adapter createheaderDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.bodyDef <em>body Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.bodyDef
   * @generated
   */
  public Adapter createbodyDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.footerDef <em>footer Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.footerDef
   * @generated
   */
  public Adapter createfooterDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.headerRef <em>header Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.headerRef
   * @generated
   */
  public Adapter createheaderRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.bodyRef <em>body Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.bodyRef
   * @generated
   */
  public Adapter createbodyRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.footerRef <em>footer Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.footerRef
   * @generated
   */
  public Adapter createfooterRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.headerScopedRef <em>header Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.headerScopedRef
   * @generated
   */
  public Adapter createheaderScopedRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.bodyScopedRef <em>body Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.bodyScopedRef
   * @generated
   */
  public Adapter createbodyScopedRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.footerScopedRef <em>footer Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.footerScopedRef
   * @generated
   */
  public Adapter createfooterScopedRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.containerDef <em>container Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.containerDef
   * @generated
   */
  public Adapter createcontainerDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.containerRef <em>container Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.containerRef
   * @generated
   */
  public Adapter createcontainerRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.declaredEventDef <em>declared Event Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.declaredEventDef
   * @generated
   */
  public Adapter createdeclaredEventDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.simpleNumericType <em>simple Numeric Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.simpleNumericType
   * @generated
   */
  public Adapter createsimpleNumericTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.fixedLenString <em>fixed Len String</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.fixedLenString
   * @generated
   */
  public Adapter createfixedLenStringAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.varLenString <em>var Len String</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.varLenString
   * @generated
   */
  public Adapter createvarLenStringAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.fixedFieldDef <em>fixed Field Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.fixedFieldDef
   * @generated
   */
  public Adapter createfixedFieldDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.varField <em>var Field</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.varField
   * @generated
   */
  public Adapter createvarFieldAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.varLenField <em>var Len Field</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.varLenField
   * @generated
   */
  public Adapter createvarLenFieldAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum <em>tagged Units Enum</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.taggedUnitsEnum
   * @generated
   */
  public Adapter createtaggedUnitsEnumAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.varFormatField <em>var Format Field</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.varFormatField
   * @generated
   */
  public Adapter createvarFormatFieldAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.formatEnumDef <em>format Enum Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.formatEnumDef
   * @generated
   */
  public Adapter createformatEnumDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.valueSetDef <em>value Set Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.valueSetDef
   * @generated
   */
  public Adapter createvalueSetDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.bitfieldDef <em>bitfield Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.bitfieldDef
   * @generated
   */
  public Adapter createbitfieldDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.valueRange <em>value Range</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.valueRange
   * @generated
   */
  public Adapter createvalueRangeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.valueSpec <em>value Spec</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.valueSpec
   * @generated
   */
  public Adapter createvalueSpecAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.scaledRangeDef <em>scaled Range Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.scaledRangeDef
   * @generated
   */
  public Adapter createscaledRangeDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.subField <em>sub Field</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.subField
   * @generated
   */
  public Adapter createsubFieldAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.listDef <em>list Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.listDef
   * @generated
   */
  public Adapter createlistDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.variantDef <em>variant Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.variantDef
   * @generated
   */
  public Adapter createvariantDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.taggedItemDef <em>tagged Item Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.taggedItemDef
   * @generated
   */
  public Adapter createtaggedItemDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.sequenceDef <em>sequence Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.sequenceDef
   * @generated
   */
  public Adapter createsequenceDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.recordDef <em>record Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.recordDef
   * @generated
   */
  public Adapter createrecordDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.constReference <em>const Reference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.constReference
   * @generated
   */
  public Adapter createconstReferenceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.typeReference <em>type Reference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.typeReference
   * @generated
   */
  public Adapter createtypeReferenceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.arrayDef <em>array Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.arrayDef
   * @generated
   */
  public Adapter createarrayDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.messageScopedRef <em>message Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.messageScopedRef
   * @generated
   */
  public Adapter createmessageScopedRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.scopedType <em>scoped Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.scopedType
   * @generated
   */
  public Adapter createscopedTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.scopedEventType <em>scoped Event Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.scopedEventType
   * @generated
   */
  public Adapter createscopedEventTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.scopedTypeId <em>scoped Type Id</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.scopedTypeId
   * @generated
   */
  public Adapter createscopedTypeIdAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.jts.eclipse.cjsidl.scopedConstId <em>scoped Const Id</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.jts.eclipse.cjsidl.scopedConstId
   * @generated
   */
  public Adapter createscopedConstIdAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //CjsidlAdapterFactory
