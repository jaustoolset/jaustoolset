/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.jts.eclipse.cjsidl.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CjsidlFactoryImpl extends EFactoryImpl implements CjsidlFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CjsidlFactory init()
  {
    try
    {
      CjsidlFactory theCjsidlFactory = (CjsidlFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.jts.org/Cjsidl"); 
      if (theCjsidlFactory != null)
      {
        return theCjsidlFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CjsidlFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CjsidlFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case CjsidlPackage.JAUS: return createjaus();
      case CjsidlPackage.SERVICE_DEF: return createserviceDef();
      case CjsidlPackage.DESCRIPTION: return createdescription();
      case CjsidlPackage.REFERENCES: return createreferences();
      case CjsidlPackage.REF_ATTR: return createrefAttr();
      case CjsidlPackage.DECLARED_CONST_SET: return createdeclaredConstSet();
      case CjsidlPackage.DECLARED_CONST_SET_REF: return createdeclaredConstSetRef();
      case CjsidlPackage.DECLARED_TYPE_SET: return createdeclaredTypeSet();
      case CjsidlPackage.MESSAGE_SET: return createmessageSet();
      case CjsidlPackage.MESSAGES: return createmessages();
      case CjsidlPackage.INTERNAL_EVENT_SET: return createinternalEventSet();
      case CjsidlPackage.EVENT_DEF: return createeventDef();
      case CjsidlPackage.MESSAGE_REF: return createmessageRef();
      case CjsidlPackage.PROTOCOL_BEHAVIOR: return createprotocolBehavior();
      case CjsidlPackage.START_STATE: return createstartState();
      case CjsidlPackage.STATE_MACHINE: return createstateMachine();
      case CjsidlPackage.STATE: return createstate();
      case CjsidlPackage.DEFAULT_STATE: return createdefaultState();
      case CjsidlPackage.ENTRY: return createentry();
      case CjsidlPackage.EXIT: return createexit();
      case CjsidlPackage.TRANS_PARAMS: return createtransParams();
      case CjsidlPackage.TRANS_PARAM: return createtransParam();
      case CjsidlPackage.TRANSITION: return createtransition();
      case CjsidlPackage.DEFAULT_TRANSITION: return createdefaultTransition();
      case CjsidlPackage.INTERNAL_TRANSITION: return createinternalTransition();
      case CjsidlPackage.SIMPLE_TRANSITION: return createsimpleTransition();
      case CjsidlPackage.PUSH_TRANSITION: return createpushTransition();
      case CjsidlPackage.POP_TRANSITION: return createpopTransition();
      case CjsidlPackage.NEXT_STATE: return createnextState();
      case CjsidlPackage.GUARD: return createguard();
      case CjsidlPackage.GUARD_ACTION: return createguardAction();
      case CjsidlPackage.GUARD_PARAM: return createguardParam();
      case CjsidlPackage.ACTION_LIST: return createactionList();
      case CjsidlPackage.SEND_ACTION_LIST: return createsendActionList();
      case CjsidlPackage.ACTION: return createaction();
      case CjsidlPackage.CONST_DEF: return createconstDef();
      case CjsidlPackage.DECLARED_TYPE_SET_REF: return createdeclaredTypeSetRef();
      case CjsidlPackage.TYPE_DEF: return createtypeDef();
      case CjsidlPackage.MESSAGE_DEF: return createmessageDef();
      case CjsidlPackage.HEADER_DEF: return createheaderDef();
      case CjsidlPackage.BODY_DEF: return createbodyDef();
      case CjsidlPackage.FOOTER_DEF: return createfooterDef();
      case CjsidlPackage.HEADER_REF: return createheaderRef();
      case CjsidlPackage.BODY_REF: return createbodyRef();
      case CjsidlPackage.FOOTER_REF: return createfooterRef();
      case CjsidlPackage.HEADER_SCOPED_REF: return createheaderScopedRef();
      case CjsidlPackage.BODY_SCOPED_REF: return createbodyScopedRef();
      case CjsidlPackage.FOOTER_SCOPED_REF: return createfooterScopedRef();
      case CjsidlPackage.CONTAINER_DEF: return createcontainerDef();
      case CjsidlPackage.CONTAINER_REF: return createcontainerRef();
      case CjsidlPackage.DECLARED_EVENT_DEF: return createdeclaredEventDef();
      case CjsidlPackage.SIMPLE_NUMERIC_TYPE: return createsimpleNumericType();
      case CjsidlPackage.FIXED_LEN_STRING: return createfixedLenString();
      case CjsidlPackage.VAR_LEN_STRING: return createvarLenString();
      case CjsidlPackage.FIXED_FIELD_DEF: return createfixedFieldDef();
      case CjsidlPackage.VAR_FIELD: return createvarField();
      case CjsidlPackage.VAR_LEN_FIELD: return createvarLenField();
      case CjsidlPackage.TAGGED_UNITS_ENUM: return createtaggedUnitsEnum();
      case CjsidlPackage.VAR_FORMAT_FIELD: return createvarFormatField();
      case CjsidlPackage.FORMAT_ENUM_DEF: return createformatEnumDef();
      case CjsidlPackage.VALUE_SET_DEF: return createvalueSetDef();
      case CjsidlPackage.BITFIELD_DEF: return createbitfieldDef();
      case CjsidlPackage.VALUE_RANGE: return createvalueRange();
      case CjsidlPackage.VALUE_SPEC: return createvalueSpec();
      case CjsidlPackage.SCALED_RANGE_DEF: return createscaledRangeDef();
      case CjsidlPackage.SUB_FIELD: return createsubField();
      case CjsidlPackage.LIST_DEF: return createlistDef();
      case CjsidlPackage.VARIANT_DEF: return createvariantDef();
      case CjsidlPackage.TAGGED_ITEM_DEF: return createtaggedItemDef();
      case CjsidlPackage.SEQUENCE_DEF: return createsequenceDef();
      case CjsidlPackage.RECORD_DEF: return createrecordDef();
      case CjsidlPackage.CONST_REFERENCE: return createconstReference();
      case CjsidlPackage.TYPE_REFERENCE: return createtypeReference();
      case CjsidlPackage.ARRAY_DEF: return createarrayDef();
      case CjsidlPackage.MESSAGE_SCOPED_REF: return createmessageScopedRef();
      case CjsidlPackage.SCOPED_TYPE: return createscopedType();
      case CjsidlPackage.SCOPED_EVENT_TYPE: return createscopedEventType();
      case CjsidlPackage.SCOPED_TYPE_ID: return createscopedTypeId();
      case CjsidlPackage.SCOPED_CONST_ID: return createscopedConstId();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case CjsidlPackage.FIELD_FORMAT:
        return createFIELD_FORMATFromString(eDataType, initialValue);
      case CjsidlPackage.UNIT:
        return createUNITFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case CjsidlPackage.FIELD_FORMAT:
        return convertFIELD_FORMATToString(eDataType, instanceValue);
      case CjsidlPackage.UNIT:
        return convertUNITToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public jaus createjaus()
  {
    jausImpl jaus = new jausImpl();
    return jaus;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public serviceDef createserviceDef()
  {
    serviceDefImpl serviceDef = new serviceDefImpl();
    return serviceDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public description createdescription()
  {
    descriptionImpl description = new descriptionImpl();
    return description;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public references createreferences()
  {
    referencesImpl references = new referencesImpl();
    return references;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public refAttr createrefAttr()
  {
    refAttrImpl refAttr = new refAttrImpl();
    return refAttr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public declaredConstSet createdeclaredConstSet()
  {
    declaredConstSetImpl declaredConstSet = new declaredConstSetImpl();
    return declaredConstSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public declaredConstSetRef createdeclaredConstSetRef()
  {
    declaredConstSetRefImpl declaredConstSetRef = new declaredConstSetRefImpl();
    return declaredConstSetRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public declaredTypeSet createdeclaredTypeSet()
  {
    declaredTypeSetImpl declaredTypeSet = new declaredTypeSetImpl();
    return declaredTypeSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public messageSet createmessageSet()
  {
    messageSetImpl messageSet = new messageSetImpl();
    return messageSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public messages createmessages()
  {
    messagesImpl messages = new messagesImpl();
    return messages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public internalEventSet createinternalEventSet()
  {
    internalEventSetImpl internalEventSet = new internalEventSetImpl();
    return internalEventSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public eventDef createeventDef()
  {
    eventDefImpl eventDef = new eventDefImpl();
    return eventDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public messageRef createmessageRef()
  {
    messageRefImpl messageRef = new messageRefImpl();
    return messageRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public protocolBehavior createprotocolBehavior()
  {
    protocolBehaviorImpl protocolBehavior = new protocolBehaviorImpl();
    return protocolBehavior;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public startState createstartState()
  {
    startStateImpl startState = new startStateImpl();
    return startState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public stateMachine createstateMachine()
  {
    stateMachineImpl stateMachine = new stateMachineImpl();
    return stateMachine;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public state createstate()
  {
    stateImpl state = new stateImpl();
    return state;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public defaultState createdefaultState()
  {
    defaultStateImpl defaultState = new defaultStateImpl();
    return defaultState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public entry createentry()
  {
    entryImpl entry = new entryImpl();
    return entry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public exit createexit()
  {
    exitImpl exit = new exitImpl();
    return exit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public transParams createtransParams()
  {
    transParamsImpl transParams = new transParamsImpl();
    return transParams;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public transParam createtransParam()
  {
    transParamImpl transParam = new transParamImpl();
    return transParam;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public transition createtransition()
  {
    transitionImpl transition = new transitionImpl();
    return transition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public defaultTransition createdefaultTransition()
  {
    defaultTransitionImpl defaultTransition = new defaultTransitionImpl();
    return defaultTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public internalTransition createinternalTransition()
  {
    internalTransitionImpl internalTransition = new internalTransitionImpl();
    return internalTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public simpleTransition createsimpleTransition()
  {
    simpleTransitionImpl simpleTransition = new simpleTransitionImpl();
    return simpleTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public pushTransition createpushTransition()
  {
    pushTransitionImpl pushTransition = new pushTransitionImpl();
    return pushTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public popTransition createpopTransition()
  {
    popTransitionImpl popTransition = new popTransitionImpl();
    return popTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public nextState createnextState()
  {
    nextStateImpl nextState = new nextStateImpl();
    return nextState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public guard createguard()
  {
    guardImpl guard = new guardImpl();
    return guard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public guardAction createguardAction()
  {
    guardActionImpl guardAction = new guardActionImpl();
    return guardAction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public guardParam createguardParam()
  {
    guardParamImpl guardParam = new guardParamImpl();
    return guardParam;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public actionList createactionList()
  {
    actionListImpl actionList = new actionListImpl();
    return actionList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public sendActionList createsendActionList()
  {
    sendActionListImpl sendActionList = new sendActionListImpl();
    return sendActionList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public action createaction()
  {
    actionImpl action = new actionImpl();
    return action;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public constDef createconstDef()
  {
    constDefImpl constDef = new constDefImpl();
    return constDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public declaredTypeSetRef createdeclaredTypeSetRef()
  {
    declaredTypeSetRefImpl declaredTypeSetRef = new declaredTypeSetRefImpl();
    return declaredTypeSetRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public typeDef createtypeDef()
  {
    typeDefImpl typeDef = new typeDefImpl();
    return typeDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public messageDef createmessageDef()
  {
    messageDefImpl messageDef = new messageDefImpl();
    return messageDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public headerDef createheaderDef()
  {
    headerDefImpl headerDef = new headerDefImpl();
    return headerDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public bodyDef createbodyDef()
  {
    bodyDefImpl bodyDef = new bodyDefImpl();
    return bodyDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public footerDef createfooterDef()
  {
    footerDefImpl footerDef = new footerDefImpl();
    return footerDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public headerRef createheaderRef()
  {
    headerRefImpl headerRef = new headerRefImpl();
    return headerRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public bodyRef createbodyRef()
  {
    bodyRefImpl bodyRef = new bodyRefImpl();
    return bodyRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public footerRef createfooterRef()
  {
    footerRefImpl footerRef = new footerRefImpl();
    return footerRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public headerScopedRef createheaderScopedRef()
  {
    headerScopedRefImpl headerScopedRef = new headerScopedRefImpl();
    return headerScopedRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public bodyScopedRef createbodyScopedRef()
  {
    bodyScopedRefImpl bodyScopedRef = new bodyScopedRefImpl();
    return bodyScopedRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public footerScopedRef createfooterScopedRef()
  {
    footerScopedRefImpl footerScopedRef = new footerScopedRefImpl();
    return footerScopedRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public containerDef createcontainerDef()
  {
    containerDefImpl containerDef = new containerDefImpl();
    return containerDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public containerRef createcontainerRef()
  {
    containerRefImpl containerRef = new containerRefImpl();
    return containerRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public declaredEventDef createdeclaredEventDef()
  {
    declaredEventDefImpl declaredEventDef = new declaredEventDefImpl();
    return declaredEventDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public simpleNumericType createsimpleNumericType()
  {
    simpleNumericTypeImpl simpleNumericType = new simpleNumericTypeImpl();
    return simpleNumericType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public fixedLenString createfixedLenString()
  {
    fixedLenStringImpl fixedLenString = new fixedLenStringImpl();
    return fixedLenString;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public varLenString createvarLenString()
  {
    varLenStringImpl varLenString = new varLenStringImpl();
    return varLenString;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public fixedFieldDef createfixedFieldDef()
  {
    fixedFieldDefImpl fixedFieldDef = new fixedFieldDefImpl();
    return fixedFieldDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public varField createvarField()
  {
    varFieldImpl varField = new varFieldImpl();
    return varField;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public varLenField createvarLenField()
  {
    varLenFieldImpl varLenField = new varLenFieldImpl();
    return varLenField;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public taggedUnitsEnum createtaggedUnitsEnum()
  {
    taggedUnitsEnumImpl taggedUnitsEnum = new taggedUnitsEnumImpl();
    return taggedUnitsEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public varFormatField createvarFormatField()
  {
    varFormatFieldImpl varFormatField = new varFormatFieldImpl();
    return varFormatField;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public formatEnumDef createformatEnumDef()
  {
    formatEnumDefImpl formatEnumDef = new formatEnumDefImpl();
    return formatEnumDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public valueSetDef createvalueSetDef()
  {
    valueSetDefImpl valueSetDef = new valueSetDefImpl();
    return valueSetDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public bitfieldDef createbitfieldDef()
  {
    bitfieldDefImpl bitfieldDef = new bitfieldDefImpl();
    return bitfieldDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public valueRange createvalueRange()
  {
    valueRangeImpl valueRange = new valueRangeImpl();
    return valueRange;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public valueSpec createvalueSpec()
  {
    valueSpecImpl valueSpec = new valueSpecImpl();
    return valueSpec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scaledRangeDef createscaledRangeDef()
  {
    scaledRangeDefImpl scaledRangeDef = new scaledRangeDefImpl();
    return scaledRangeDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public subField createsubField()
  {
    subFieldImpl subField = new subFieldImpl();
    return subField;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public listDef createlistDef()
  {
    listDefImpl listDef = new listDefImpl();
    return listDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variantDef createvariantDef()
  {
    variantDefImpl variantDef = new variantDefImpl();
    return variantDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public taggedItemDef createtaggedItemDef()
  {
    taggedItemDefImpl taggedItemDef = new taggedItemDefImpl();
    return taggedItemDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public sequenceDef createsequenceDef()
  {
    sequenceDefImpl sequenceDef = new sequenceDefImpl();
    return sequenceDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public recordDef createrecordDef()
  {
    recordDefImpl recordDef = new recordDefImpl();
    return recordDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public constReference createconstReference()
  {
    constReferenceImpl constReference = new constReferenceImpl();
    return constReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public typeReference createtypeReference()
  {
    typeReferenceImpl typeReference = new typeReferenceImpl();
    return typeReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public arrayDef createarrayDef()
  {
    arrayDefImpl arrayDef = new arrayDefImpl();
    return arrayDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public messageScopedRef createmessageScopedRef()
  {
    messageScopedRefImpl messageScopedRef = new messageScopedRefImpl();
    return messageScopedRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scopedType createscopedType()
  {
    scopedTypeImpl scopedType = new scopedTypeImpl();
    return scopedType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scopedEventType createscopedEventType()
  {
    scopedEventTypeImpl scopedEventType = new scopedEventTypeImpl();
    return scopedEventType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scopedTypeId createscopedTypeId()
  {
    scopedTypeIdImpl scopedTypeId = new scopedTypeIdImpl();
    return scopedTypeId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scopedConstId createscopedConstId()
  {
    scopedConstIdImpl scopedConstId = new scopedConstIdImpl();
    return scopedConstId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FIELD_FORMAT createFIELD_FORMATFromString(EDataType eDataType, String initialValue)
  {
    FIELD_FORMAT result = FIELD_FORMAT.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertFIELD_FORMATToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UNIT createUNITFromString(EDataType eDataType, String initialValue)
  {
    UNIT result = UNIT.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertUNITToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CjsidlPackage getCjsidlPackage()
  {
    return (CjsidlPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static CjsidlPackage getPackage()
  {
    return CjsidlPackage.eINSTANCE;
  }

} //CjsidlFactoryImpl
