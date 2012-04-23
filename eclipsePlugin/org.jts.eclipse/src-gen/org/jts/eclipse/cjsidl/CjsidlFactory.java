/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.jts.eclipse.cjsidl.CjsidlPackage
 * @generated
 */
public interface CjsidlFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  CjsidlFactory eINSTANCE = org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl.init();

  /**
   * Returns a new object of class '<em>jaus</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>jaus</em>'.
   * @generated
   */
  jaus createjaus();

  /**
   * Returns a new object of class '<em>service Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>service Def</em>'.
   * @generated
   */
  serviceDef createserviceDef();

  /**
   * Returns a new object of class '<em>description</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>description</em>'.
   * @generated
   */
  description createdescription();

  /**
   * Returns a new object of class '<em>references</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>references</em>'.
   * @generated
   */
  references createreferences();

  /**
   * Returns a new object of class '<em>ref Attr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ref Attr</em>'.
   * @generated
   */
  refAttr createrefAttr();

  /**
   * Returns a new object of class '<em>declared Const Set</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>declared Const Set</em>'.
   * @generated
   */
  declaredConstSet createdeclaredConstSet();

  /**
   * Returns a new object of class '<em>declared Const Set Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>declared Const Set Ref</em>'.
   * @generated
   */
  declaredConstSetRef createdeclaredConstSetRef();

  /**
   * Returns a new object of class '<em>declared Type Set</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>declared Type Set</em>'.
   * @generated
   */
  declaredTypeSet createdeclaredTypeSet();

  /**
   * Returns a new object of class '<em>message Set</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>message Set</em>'.
   * @generated
   */
  messageSet createmessageSet();

  /**
   * Returns a new object of class '<em>messages</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>messages</em>'.
   * @generated
   */
  messages createmessages();

  /**
   * Returns a new object of class '<em>internal Event Set</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>internal Event Set</em>'.
   * @generated
   */
  internalEventSet createinternalEventSet();

  /**
   * Returns a new object of class '<em>event Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>event Def</em>'.
   * @generated
   */
  eventDef createeventDef();

  /**
   * Returns a new object of class '<em>message Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>message Ref</em>'.
   * @generated
   */
  messageRef createmessageRef();

  /**
   * Returns a new object of class '<em>protocol Behavior</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>protocol Behavior</em>'.
   * @generated
   */
  protocolBehavior createprotocolBehavior();

  /**
   * Returns a new object of class '<em>start State</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>start State</em>'.
   * @generated
   */
  startState createstartState();

  /**
   * Returns a new object of class '<em>state Machine</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>state Machine</em>'.
   * @generated
   */
  stateMachine createstateMachine();

  /**
   * Returns a new object of class '<em>state</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>state</em>'.
   * @generated
   */
  state createstate();

  /**
   * Returns a new object of class '<em>default State</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>default State</em>'.
   * @generated
   */
  defaultState createdefaultState();

  /**
   * Returns a new object of class '<em>entry</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>entry</em>'.
   * @generated
   */
  entry createentry();

  /**
   * Returns a new object of class '<em>exit</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>exit</em>'.
   * @generated
   */
  exit createexit();

  /**
   * Returns a new object of class '<em>trans Params</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>trans Params</em>'.
   * @generated
   */
  transParams createtransParams();

  /**
   * Returns a new object of class '<em>trans Param</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>trans Param</em>'.
   * @generated
   */
  transParam createtransParam();

  /**
   * Returns a new object of class '<em>transition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>transition</em>'.
   * @generated
   */
  transition createtransition();

  /**
   * Returns a new object of class '<em>default Transition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>default Transition</em>'.
   * @generated
   */
  defaultTransition createdefaultTransition();

  /**
   * Returns a new object of class '<em>internal Transition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>internal Transition</em>'.
   * @generated
   */
  internalTransition createinternalTransition();

  /**
   * Returns a new object of class '<em>simple Transition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>simple Transition</em>'.
   * @generated
   */
  simpleTransition createsimpleTransition();

  /**
   * Returns a new object of class '<em>push Transition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>push Transition</em>'.
   * @generated
   */
  pushTransition createpushTransition();

  /**
   * Returns a new object of class '<em>pop Transition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>pop Transition</em>'.
   * @generated
   */
  popTransition createpopTransition();

  /**
   * Returns a new object of class '<em>next State</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>next State</em>'.
   * @generated
   */
  nextState createnextState();

  /**
   * Returns a new object of class '<em>guard</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>guard</em>'.
   * @generated
   */
  guard createguard();

  /**
   * Returns a new object of class '<em>guard Action</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>guard Action</em>'.
   * @generated
   */
  guardAction createguardAction();

  /**
   * Returns a new object of class '<em>guard Param</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>guard Param</em>'.
   * @generated
   */
  guardParam createguardParam();

  /**
   * Returns a new object of class '<em>action List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>action List</em>'.
   * @generated
   */
  actionList createactionList();

  /**
   * Returns a new object of class '<em>send Action List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>send Action List</em>'.
   * @generated
   */
  sendActionList createsendActionList();

  /**
   * Returns a new object of class '<em>action</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>action</em>'.
   * @generated
   */
  action createaction();

  /**
   * Returns a new object of class '<em>const Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>const Def</em>'.
   * @generated
   */
  constDef createconstDef();

  /**
   * Returns a new object of class '<em>declared Type Set Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>declared Type Set Ref</em>'.
   * @generated
   */
  declaredTypeSetRef createdeclaredTypeSetRef();

  /**
   * Returns a new object of class '<em>type Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>type Def</em>'.
   * @generated
   */
  typeDef createtypeDef();

  /**
   * Returns a new object of class '<em>message Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>message Def</em>'.
   * @generated
   */
  messageDef createmessageDef();

  /**
   * Returns a new object of class '<em>header Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>header Def</em>'.
   * @generated
   */
  headerDef createheaderDef();

  /**
   * Returns a new object of class '<em>body Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>body Def</em>'.
   * @generated
   */
  bodyDef createbodyDef();

  /**
   * Returns a new object of class '<em>footer Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>footer Def</em>'.
   * @generated
   */
  footerDef createfooterDef();

  /**
   * Returns a new object of class '<em>header Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>header Ref</em>'.
   * @generated
   */
  headerRef createheaderRef();

  /**
   * Returns a new object of class '<em>body Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>body Ref</em>'.
   * @generated
   */
  bodyRef createbodyRef();

  /**
   * Returns a new object of class '<em>footer Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>footer Ref</em>'.
   * @generated
   */
  footerRef createfooterRef();

  /**
   * Returns a new object of class '<em>header Scoped Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>header Scoped Ref</em>'.
   * @generated
   */
  headerScopedRef createheaderScopedRef();

  /**
   * Returns a new object of class '<em>body Scoped Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>body Scoped Ref</em>'.
   * @generated
   */
  bodyScopedRef createbodyScopedRef();

  /**
   * Returns a new object of class '<em>footer Scoped Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>footer Scoped Ref</em>'.
   * @generated
   */
  footerScopedRef createfooterScopedRef();

  /**
   * Returns a new object of class '<em>container Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>container Def</em>'.
   * @generated
   */
  containerDef createcontainerDef();

  /**
   * Returns a new object of class '<em>container Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>container Ref</em>'.
   * @generated
   */
  containerRef createcontainerRef();

  /**
   * Returns a new object of class '<em>declared Event Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>declared Event Def</em>'.
   * @generated
   */
  declaredEventDef createdeclaredEventDef();

  /**
   * Returns a new object of class '<em>simple Numeric Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>simple Numeric Type</em>'.
   * @generated
   */
  simpleNumericType createsimpleNumericType();

  /**
   * Returns a new object of class '<em>fixed Len String</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>fixed Len String</em>'.
   * @generated
   */
  fixedLenString createfixedLenString();

  /**
   * Returns a new object of class '<em>var Len String</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>var Len String</em>'.
   * @generated
   */
  varLenString createvarLenString();

  /**
   * Returns a new object of class '<em>fixed Field Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>fixed Field Def</em>'.
   * @generated
   */
  fixedFieldDef createfixedFieldDef();

  /**
   * Returns a new object of class '<em>var Field</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>var Field</em>'.
   * @generated
   */
  varField createvarField();

  /**
   * Returns a new object of class '<em>var Len Field</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>var Len Field</em>'.
   * @generated
   */
  varLenField createvarLenField();

  /**
   * Returns a new object of class '<em>tagged Units Enum</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>tagged Units Enum</em>'.
   * @generated
   */
  taggedUnitsEnum createtaggedUnitsEnum();

  /**
   * Returns a new object of class '<em>var Format Field</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>var Format Field</em>'.
   * @generated
   */
  varFormatField createvarFormatField();

  /**
   * Returns a new object of class '<em>format Enum Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>format Enum Def</em>'.
   * @generated
   */
  formatEnumDef createformatEnumDef();

  /**
   * Returns a new object of class '<em>value Set Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>value Set Def</em>'.
   * @generated
   */
  valueSetDef createvalueSetDef();

  /**
   * Returns a new object of class '<em>bitfield Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>bitfield Def</em>'.
   * @generated
   */
  bitfieldDef createbitfieldDef();

  /**
   * Returns a new object of class '<em>value Range</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>value Range</em>'.
   * @generated
   */
  valueRange createvalueRange();

  /**
   * Returns a new object of class '<em>value Spec</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>value Spec</em>'.
   * @generated
   */
  valueSpec createvalueSpec();

  /**
   * Returns a new object of class '<em>scaled Range Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>scaled Range Def</em>'.
   * @generated
   */
  scaledRangeDef createscaledRangeDef();

  /**
   * Returns a new object of class '<em>sub Field</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>sub Field</em>'.
   * @generated
   */
  subField createsubField();

  /**
   * Returns a new object of class '<em>list Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>list Def</em>'.
   * @generated
   */
  listDef createlistDef();

  /**
   * Returns a new object of class '<em>variant Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>variant Def</em>'.
   * @generated
   */
  variantDef createvariantDef();

  /**
   * Returns a new object of class '<em>tagged Item Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>tagged Item Def</em>'.
   * @generated
   */
  taggedItemDef createtaggedItemDef();

  /**
   * Returns a new object of class '<em>sequence Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>sequence Def</em>'.
   * @generated
   */
  sequenceDef createsequenceDef();

  /**
   * Returns a new object of class '<em>record Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>record Def</em>'.
   * @generated
   */
  recordDef createrecordDef();

  /**
   * Returns a new object of class '<em>const Reference</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>const Reference</em>'.
   * @generated
   */
  constReference createconstReference();

  /**
   * Returns a new object of class '<em>type Reference</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>type Reference</em>'.
   * @generated
   */
  typeReference createtypeReference();

  /**
   * Returns a new object of class '<em>array Def</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>array Def</em>'.
   * @generated
   */
  arrayDef createarrayDef();

  /**
   * Returns a new object of class '<em>message Scoped Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>message Scoped Ref</em>'.
   * @generated
   */
  messageScopedRef createmessageScopedRef();

  /**
   * Returns a new object of class '<em>scoped Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>scoped Type</em>'.
   * @generated
   */
  scopedType createscopedType();

  /**
   * Returns a new object of class '<em>scoped Event Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>scoped Event Type</em>'.
   * @generated
   */
  scopedEventType createscopedEventType();

  /**
   * Returns a new object of class '<em>scoped Type Id</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>scoped Type Id</em>'.
   * @generated
   */
  scopedTypeId createscopedTypeId();

  /**
   * Returns a new object of class '<em>scoped Const Id</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>scoped Const Id</em>'.
   * @generated
   */
  scopedConstId createscopedConstId();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  CjsidlPackage getCjsidlPackage();

} //CjsidlFactory
