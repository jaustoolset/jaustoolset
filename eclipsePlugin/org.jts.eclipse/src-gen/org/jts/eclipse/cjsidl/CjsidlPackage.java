/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.jts.eclipse.cjsidl.CjsidlFactory
 * @model kind="package"
 * @generated
 */
public interface CjsidlPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "cjsidl";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.jts.org/Cjsidl";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "cjsidl";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  CjsidlPackage eINSTANCE = org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl.init();

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.jausImpl <em>jaus</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.jausImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getjaus()
   * @generated
   */
  int JAUS = 0;

  /**
   * The feature id for the '<em><b>Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAUS__SET = 0;

  /**
   * The number of structural features of the '<em>jaus</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAUS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl <em>service Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.serviceDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getserviceDef()
   * @generated
   */
  int SERVICE_DEF = 1;

  /**
   * The feature id for the '<em><b>Service Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF__SERVICE_NAME = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF__NAME = 1;

  /**
   * The feature id for the '<em><b>Service Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF__SERVICE_VERSION = 2;

  /**
   * The feature id for the '<em><b>Descr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF__DESCR = 3;

  /**
   * The feature id for the '<em><b>Assumpt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF__ASSUMPT = 4;

  /**
   * The feature id for the '<em><b>Refs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF__REFS = 5;

  /**
   * The feature id for the '<em><b>Const Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF__CONST_SET = 6;

  /**
   * The feature id for the '<em><b>Type Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF__TYPE_SET = 7;

  /**
   * The feature id for the '<em><b>Message Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF__MESSAGE_SET = 8;

  /**
   * The feature id for the '<em><b>Internal Event Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF__INTERNAL_EVENT_SET = 9;

  /**
   * The feature id for the '<em><b>Protocol Behavior</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF__PROTOCOL_BEHAVIOR = 10;

  /**
   * The number of structural features of the '<em>service Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_DEF_FEATURE_COUNT = 11;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.descriptionImpl <em>description</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.descriptionImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getdescription()
   * @generated
   */
  int DESCRIPTION = 2;

  /**
   * The feature id for the '<em><b>Content</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESCRIPTION__CONTENT = 0;

  /**
   * The number of structural features of the '<em>description</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESCRIPTION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.referencesImpl <em>references</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.referencesImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getreferences()
   * @generated
   */
  int REFERENCES = 3;

  /**
   * The feature id for the '<em><b>Ref Inherit</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCES__REF_INHERIT = 0;

  /**
   * The feature id for the '<em><b>Ref Client</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCES__REF_CLIENT = 1;

  /**
   * The number of structural features of the '<em>references</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCES_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.refAttrImpl <em>ref Attr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.refAttrImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getrefAttr()
   * @generated
   */
  int REF_ATTR = 4;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REF_ATTR__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Imported Namespace</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REF_ATTR__IMPORTED_NAMESPACE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REF_ATTR__NAME = 2;

  /**
   * The number of structural features of the '<em>ref Attr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REF_ATTR_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.declaredConstSetImpl <em>declared Const Set</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.declaredConstSetImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getdeclaredConstSet()
   * @generated
   */
  int DECLARED_CONST_SET = 5;

  /**
   * The feature id for the '<em><b>Const Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_CONST_SET__CONST_NAME = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_CONST_SET__NAME = 1;

  /**
   * The feature id for the '<em><b>Const Set Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_CONST_SET__CONST_SET_VERSION = 2;

  /**
   * The feature id for the '<em><b>Declared Const Set Ref</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_CONST_SET__DECLARED_CONST_SET_REF = 3;

  /**
   * The feature id for the '<em><b>Const Def</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_CONST_SET__CONST_DEF = 4;

  /**
   * The number of structural features of the '<em>declared Const Set</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_CONST_SET_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.declaredConstSetRefImpl <em>declared Const Set Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.declaredConstSetRefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getdeclaredConstSetRef()
   * @generated
   */
  int DECLARED_CONST_SET_REF = 6;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_CONST_SET_REF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Imported Namespace</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_CONST_SET_REF__IMPORTED_NAMESPACE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_CONST_SET_REF__NAME = 2;

  /**
   * The number of structural features of the '<em>declared Const Set Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_CONST_SET_REF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.declaredTypeSetImpl <em>declared Type Set</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.declaredTypeSetImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getdeclaredTypeSet()
   * @generated
   */
  int DECLARED_TYPE_SET = 7;

  /**
   * The feature id for the '<em><b>Type Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET__TYPE_NAME = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET__NAME = 1;

  /**
   * The feature id for the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET__VERSION = 2;

  /**
   * The feature id for the '<em><b>Declared Const Set Ref</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET__DECLARED_CONST_SET_REF = 3;

  /**
   * The feature id for the '<em><b>Declared Type Set Ref</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET__DECLARED_TYPE_SET_REF = 4;

  /**
   * The feature id for the '<em><b>Type Def</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET__TYPE_DEF = 5;

  /**
   * The feature id for the '<em><b>Type Ref</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET__TYPE_REF = 6;

  /**
   * The feature id for the '<em><b>Scoped Ref</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET__SCOPED_REF = 7;

  /**
   * The number of structural features of the '<em>declared Type Set</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.messageSetImpl <em>message Set</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.messageSetImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getmessageSet()
   * @generated
   */
  int MESSAGE_SET = 8;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SET__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Input Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SET__INPUT_COMMENT = 1;

  /**
   * The feature id for the '<em><b>Input Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SET__INPUT_SET = 2;

  /**
   * The feature id for the '<em><b>Output Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SET__OUTPUT_COMMENT = 3;

  /**
   * The feature id for the '<em><b>Output Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SET__OUTPUT_SET = 4;

  /**
   * The number of structural features of the '<em>message Set</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SET_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.messagesImpl <em>messages</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.messagesImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getmessages()
   * @generated
   */
  int MESSAGES = 9;

  /**
   * The feature id for the '<em><b>Message Defs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGES__MESSAGE_DEFS = 0;

  /**
   * The feature id for the '<em><b>Type Refs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGES__TYPE_REFS = 1;

  /**
   * The feature id for the '<em><b>Scoped Refs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGES__SCOPED_REFS = 2;

  /**
   * The number of structural features of the '<em>messages</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGES_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.internalEventSetImpl <em>internal Event Set</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.internalEventSetImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getinternalEventSet()
   * @generated
   */
  int INTERNAL_EVENT_SET = 10;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERNAL_EVENT_SET__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Defs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERNAL_EVENT_SET__DEFS = 1;

  /**
   * The number of structural features of the '<em>internal Event Set</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERNAL_EVENT_SET_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.eventDefImpl <em>event Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.eventDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#geteventDef()
   * @generated
   */
  int EVENT_DEF = 11;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT_DEF__NAME = 0;

  /**
   * The feature id for the '<em><b>Descr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT_DEF__DESCR = 1;

  /**
   * The feature id for the '<em><b>Header</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT_DEF__HEADER = 2;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT_DEF__BODY = 3;

  /**
   * The feature id for the '<em><b>Footer</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT_DEF__FOOTER = 4;

  /**
   * The number of structural features of the '<em>event Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT_DEF_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.messageRefImpl <em>message Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.messageRefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getmessageRef()
   * @generated
   */
  int MESSAGE_REF = 12;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_REF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_REF__REF = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_REF__NAME = 2;

  /**
   * The number of structural features of the '<em>message Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_REF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.protocolBehaviorImpl <em>protocol Behavior</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.protocolBehaviorImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getprotocolBehavior()
   * @generated
   */
  int PROTOCOL_BEHAVIOR = 13;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROTOCOL_BEHAVIOR__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Stateless</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROTOCOL_BEHAVIOR__STATELESS = 1;

  /**
   * The feature id for the '<em><b>State Machine</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROTOCOL_BEHAVIOR__STATE_MACHINE = 2;

  /**
   * The number of structural features of the '<em>protocol Behavior</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROTOCOL_BEHAVIOR_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.startStateImpl <em>start State</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.startStateImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getstartState()
   * @generated
   */
  int START_STATE = 14;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int START_STATE__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Scoped</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int START_STATE__SCOPED = 1;

  /**
   * The feature id for the '<em><b>State</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int START_STATE__STATE = 2;

  /**
   * The number of structural features of the '<em>start State</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int START_STATE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.stateMachineImpl <em>state Machine</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.stateMachineImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getstateMachine()
   * @generated
   */
  int STATE_MACHINE = 15;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_MACHINE__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Scoped</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_MACHINE__SCOPED = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_MACHINE__NAME = 2;

  /**
   * The feature id for the '<em><b>Start State</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_MACHINE__START_STATE = 3;

  /**
   * The feature id for the '<em><b>Default State</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_MACHINE__DEFAULT_STATE = 4;

  /**
   * The feature id for the '<em><b>States</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_MACHINE__STATES = 5;

  /**
   * The number of structural features of the '<em>state Machine</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_MACHINE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.stateImpl <em>state</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.stateImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getstate()
   * @generated
   */
  int STATE = 16;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Initial</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE__INITIAL = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE__NAME = 2;

  /**
   * The feature id for the '<em><b>Entry Action</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE__ENTRY_ACTION = 3;

  /**
   * The feature id for the '<em><b>Exit Action</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE__EXIT_ACTION = 4;

  /**
   * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE__TRANSITIONS = 5;

  /**
   * The feature id for the '<em><b>Default Transition</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE__DEFAULT_TRANSITION = 6;

  /**
   * The feature id for the '<em><b>Default State</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE__DEFAULT_STATE = 7;

  /**
   * The feature id for the '<em><b>Sub State</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE__SUB_STATE = 8;

  /**
   * The number of structural features of the '<em>state</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.defaultStateImpl <em>default State</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.defaultStateImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getdefaultState()
   * @generated
   */
  int DEFAULT_STATE = 17;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFAULT_STATE__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Transition</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFAULT_STATE__TRANSITION = 1;

  /**
   * The feature id for the '<em><b>Default Transition</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFAULT_STATE__DEFAULT_TRANSITION = 2;

  /**
   * The number of structural features of the '<em>default State</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFAULT_STATE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.entryImpl <em>entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.entryImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getentry()
   * @generated
   */
  int ENTRY = 18;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTRY__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Actions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTRY__ACTIONS = 1;

  /**
   * The feature id for the '<em><b>Send Actions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTRY__SEND_ACTIONS = 2;

  /**
   * The number of structural features of the '<em>entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTRY_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.exitImpl <em>exit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.exitImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getexit()
   * @generated
   */
  int EXIT = 19;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXIT__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Actions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXIT__ACTIONS = 1;

  /**
   * The feature id for the '<em><b>Send Actions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXIT__SEND_ACTIONS = 2;

  /**
   * The number of structural features of the '<em>exit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXIT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.transParamsImpl <em>trans Params</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.transParamsImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#gettransParams()
   * @generated
   */
  int TRANS_PARAMS = 20;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANS_PARAMS__PARAMS = 0;

  /**
   * The number of structural features of the '<em>trans Params</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANS_PARAMS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.transParamImpl <em>trans Param</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.transParamImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#gettransParam()
   * @generated
   */
  int TRANS_PARAM = 21;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANS_PARAM__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANS_PARAM__TYPE = 1;

  /**
   * The feature id for the '<em><b>Scoped Event Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANS_PARAM__SCOPED_EVENT_TYPE = 2;

  /**
   * The feature id for the '<em><b>Unsigned Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANS_PARAM__UNSIGNED_TYPE = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANS_PARAM__NAME = 4;

  /**
   * The number of structural features of the '<em>trans Param</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANS_PARAM_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.transitionImpl <em>transition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.transitionImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#gettransition()
   * @generated
   */
  int TRANSITION = 22;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSITION__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSITION__TYPE = 1;

  /**
   * The feature id for the '<em><b>Scoped</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSITION__SCOPED = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSITION__NAME = 3;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSITION__PARAMS = 4;

  /**
   * The feature id for the '<em><b>Trans Guard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSITION__TRANS_GUARD = 5;

  /**
   * The feature id for the '<em><b>Actions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSITION__ACTIONS = 6;

  /**
   * The feature id for the '<em><b>Send Actions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSITION__SEND_ACTIONS = 7;

  /**
   * The feature id for the '<em><b>Destination</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSITION__DESTINATION = 8;

  /**
   * The number of structural features of the '<em>transition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRANSITION_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.defaultTransitionImpl <em>default Transition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.defaultTransitionImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getdefaultTransition()
   * @generated
   */
  int DEFAULT_TRANSITION = 23;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFAULT_TRANSITION__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFAULT_TRANSITION__TYPE = 1;

  /**
   * The feature id for the '<em><b>Trans Guard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFAULT_TRANSITION__TRANS_GUARD = 2;

  /**
   * The feature id for the '<em><b>Actions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFAULT_TRANSITION__ACTIONS = 3;

  /**
   * The feature id for the '<em><b>Send Actions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFAULT_TRANSITION__SEND_ACTIONS = 4;

  /**
   * The feature id for the '<em><b>Destination</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFAULT_TRANSITION__DESTINATION = 5;

  /**
   * The number of structural features of the '<em>default Transition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFAULT_TRANSITION_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.internalTransitionImpl <em>internal Transition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.internalTransitionImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getinternalTransition()
   * @generated
   */
  int INTERNAL_TRANSITION = 24;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERNAL_TRANSITION__COMMENT = 0;

  /**
   * The number of structural features of the '<em>internal Transition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERNAL_TRANSITION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.simpleTransitionImpl <em>simple Transition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.simpleTransitionImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getsimpleTransition()
   * @generated
   */
  int SIMPLE_TRANSITION = 25;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_TRANSITION__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Next State</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_TRANSITION__NEXT_STATE = 1;

  /**
   * The number of structural features of the '<em>simple Transition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_TRANSITION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.pushTransitionImpl <em>push Transition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.pushTransitionImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getpushTransition()
   * @generated
   */
  int PUSH_TRANSITION = 26;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PUSH_TRANSITION__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Next State</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PUSH_TRANSITION__NEXT_STATE = 1;

  /**
   * The feature id for the '<em><b>Simple Transition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PUSH_TRANSITION__SIMPLE_TRANSITION = 2;

  /**
   * The number of structural features of the '<em>push Transition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PUSH_TRANSITION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.popTransitionImpl <em>pop Transition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.popTransitionImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getpopTransition()
   * @generated
   */
  int POP_TRANSITION = 27;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POP_TRANSITION__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Secondary Transition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POP_TRANSITION__SECONDARY_TRANSITION = 1;

  /**
   * The feature id for the '<em><b>Param</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POP_TRANSITION__PARAM = 2;

  /**
   * The number of structural features of the '<em>pop Transition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POP_TRANSITION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.nextStateImpl <em>next State</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.nextStateImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getnextState()
   * @generated
   */
  int NEXT_STATE = 28;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NEXT_STATE__COMMENT = 0;

  /**
   * The feature id for the '<em><b>First State</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NEXT_STATE__FIRST_STATE = 1;

  /**
   * The feature id for the '<em><b>Scoped</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NEXT_STATE__SCOPED = 2;

  /**
   * The feature id for the '<em><b>Next State</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NEXT_STATE__NEXT_STATE = 3;

  /**
   * The number of structural features of the '<em>next State</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NEXT_STATE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.guardImpl <em>guard</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.guardImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getguard()
   * @generated
   */
  int GUARD = 29;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Guard Action</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD__GUARD_ACTION = 1;

  /**
   * The feature id for the '<em><b>Equiv</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD__EQUIV = 2;

  /**
   * The feature id for the '<em><b>Logical Operator</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD__LOGICAL_OPERATOR = 3;

  /**
   * The number of structural features of the '<em>guard</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.guardActionImpl <em>guard Action</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.guardActionImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getguardAction()
   * @generated
   */
  int GUARD_ACTION = 30;

  /**
   * The feature id for the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD_ACTION__NOT = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD_ACTION__NAME = 1;

  /**
   * The feature id for the '<em><b>Param</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD_ACTION__PARAM = 2;

  /**
   * The number of structural features of the '<em>guard Action</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD_ACTION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.guardParamImpl <em>guard Param</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.guardParamImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getguardParam()
   * @generated
   */
  int GUARD_PARAM = 31;

  /**
   * The feature id for the '<em><b>Parameter</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD_PARAM__PARAMETER = 0;

  /**
   * The feature id for the '<em><b>Guard Const</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD_PARAM__GUARD_CONST = 1;

  /**
   * The number of structural features of the '<em>guard Param</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARD_PARAM_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.actionListImpl <em>action List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.actionListImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getactionList()
   * @generated
   */
  int ACTION_LIST = 32;

  /**
   * The feature id for the '<em><b>Actions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION_LIST__ACTIONS = 0;

  /**
   * The number of structural features of the '<em>action List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.sendActionListImpl <em>send Action List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.sendActionListImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getsendActionList()
   * @generated
   */
  int SEND_ACTION_LIST = 33;

  /**
   * The feature id for the '<em><b>Actions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEND_ACTION_LIST__ACTIONS = 0;

  /**
   * The number of structural features of the '<em>send Action List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEND_ACTION_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.actionImpl <em>action</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.actionImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getaction()
   * @generated
   */
  int ACTION = 34;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION__NAME = 1;

  /**
   * The feature id for the '<em><b>Param</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION__PARAM = 2;

  /**
   * The number of structural features of the '<em>action</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.constDefImpl <em>const Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.constDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getconstDef()
   * @generated
   */
  int CONST_DEF = 35;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_DEF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Const Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_DEF__CONST_TYPE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_DEF__NAME = 2;

  /**
   * The feature id for the '<em><b>Const Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_DEF__CONST_VALUE = 3;

  /**
   * The feature id for the '<em><b>Field Units</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_DEF__FIELD_UNITS = 4;

  /**
   * The number of structural features of the '<em>const Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_DEF_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.declaredTypeSetRefImpl <em>declared Type Set Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.declaredTypeSetRefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getdeclaredTypeSetRef()
   * @generated
   */
  int DECLARED_TYPE_SET_REF = 36;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET_REF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Imported Namespace</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET_REF__IMPORTED_NAMESPACE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET_REF__NAME = 2;

  /**
   * The number of structural features of the '<em>declared Type Set Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_TYPE_SET_REF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.typeDefImpl <em>type Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.typeDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#gettypeDef()
   * @generated
   */
  int TYPE_DEF = 37;

  /**
   * The feature id for the '<em><b>Message Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__MESSAGE_DEF = 0;

  /**
   * The feature id for the '<em><b>Array Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__ARRAY_DEF = 1;

  /**
   * The feature id for the '<em><b>Record Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__RECORD_DEF = 2;

  /**
   * The feature id for the '<em><b>List Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__LIST_DEF = 3;

  /**
   * The feature id for the '<em><b>Variant Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__VARIANT_DEF = 4;

  /**
   * The feature id for the '<em><b>Sequence Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__SEQUENCE_DEF = 5;

  /**
   * The feature id for the '<em><b>Fixed Field Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__FIXED_FIELD_DEF = 6;

  /**
   * The feature id for the '<em><b>Var Field</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__VAR_FIELD = 7;

  /**
   * The feature id for the '<em><b>Bitfield Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__BITFIELD_DEF = 8;

  /**
   * The feature id for the '<em><b>Fixed Len String</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__FIXED_LEN_STRING = 9;

  /**
   * The feature id for the '<em><b>Var Len String</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__VAR_LEN_STRING = 10;

  /**
   * The feature id for the '<em><b>Var Len Field</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__VAR_LEN_FIELD = 11;

  /**
   * The feature id for the '<em><b>Var Format Field</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__VAR_FORMAT_FIELD = 12;

  /**
   * The feature id for the '<em><b>Header Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__HEADER_DEF = 13;

  /**
   * The feature id for the '<em><b>Body Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__BODY_DEF = 14;

  /**
   * The feature id for the '<em><b>Footer Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__FOOTER_DEF = 15;

  /**
   * The number of structural features of the '<em>type Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF_FEATURE_COUNT = 16;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.messageDefImpl <em>message Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.messageDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getmessageDef()
   * @generated
   */
  int MESSAGE_DEF = 38;

  /**
   * The feature id for the '<em><b>Command</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_DEF__COMMAND = 0;

  /**
   * The feature id for the '<em><b>Message ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_DEF__MESSAGE_ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_DEF__NAME = 2;

  /**
   * The feature id for the '<em><b>Descr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_DEF__DESCR = 3;

  /**
   * The feature id for the '<em><b>Header</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_DEF__HEADER = 4;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_DEF__BODY = 5;

  /**
   * The feature id for the '<em><b>Footer</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_DEF__FOOTER = 6;

  /**
   * The number of structural features of the '<em>message Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_DEF_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.headerDefImpl <em>header Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.headerDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getheaderDef()
   * @generated
   */
  int HEADER_DEF = 39;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_DEF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_DEF__NAME = 1;

  /**
   * The feature id for the '<em><b>Record List Sequence Variant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_DEF__RECORD_LIST_SEQUENCE_VARIANT = 2;

  /**
   * The number of structural features of the '<em>header Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_DEF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.bodyDefImpl <em>body Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.bodyDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getbodyDef()
   * @generated
   */
  int BODY_DEF = 40;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_DEF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_DEF__NAME = 1;

  /**
   * The feature id for the '<em><b>Record List Sequence Variant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_DEF__RECORD_LIST_SEQUENCE_VARIANT = 2;

  /**
   * The number of structural features of the '<em>body Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_DEF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.footerDefImpl <em>footer Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.footerDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getfooterDef()
   * @generated
   */
  int FOOTER_DEF = 41;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_DEF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_DEF__NAME = 1;

  /**
   * The feature id for the '<em><b>Record List Sequence Variant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_DEF__RECORD_LIST_SEQUENCE_VARIANT = 2;

  /**
   * The number of structural features of the '<em>footer Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_DEF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.headerRefImpl <em>header Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.headerRefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getheaderRef()
   * @generated
   */
  int HEADER_REF = 42;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_REF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Type Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_REF__TYPE_REF = 1;

  /**
   * The feature id for the '<em><b>Scoped Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_REF__SCOPED_REF = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_REF__NAME = 3;

  /**
   * The number of structural features of the '<em>header Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_REF_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.bodyRefImpl <em>body Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.bodyRefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getbodyRef()
   * @generated
   */
  int BODY_REF = 43;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_REF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Type Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_REF__TYPE_REF = 1;

  /**
   * The feature id for the '<em><b>Scoped Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_REF__SCOPED_REF = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_REF__NAME = 3;

  /**
   * The number of structural features of the '<em>body Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_REF_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.footerRefImpl <em>footer Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.footerRefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getfooterRef()
   * @generated
   */
  int FOOTER_REF = 44;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_REF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Type Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_REF__TYPE_REF = 1;

  /**
   * The feature id for the '<em><b>Scoped Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_REF__SCOPED_REF = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_REF__NAME = 3;

  /**
   * The number of structural features of the '<em>footer Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_REF_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.headerScopedRefImpl <em>header Scoped Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.headerScopedRefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getheaderScopedRef()
   * @generated
   */
  int HEADER_SCOPED_REF = 45;

  /**
   * The feature id for the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_SCOPED_REF__NAME = 0;

  /**
   * The feature id for the '<em><b>Names</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_SCOPED_REF__NAMES = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_SCOPED_REF__TYPE = 2;

  /**
   * The number of structural features of the '<em>header Scoped Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_SCOPED_REF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.bodyScopedRefImpl <em>body Scoped Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.bodyScopedRefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getbodyScopedRef()
   * @generated
   */
  int BODY_SCOPED_REF = 46;

  /**
   * The feature id for the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_SCOPED_REF__NAME = 0;

  /**
   * The feature id for the '<em><b>Names</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_SCOPED_REF__NAMES = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_SCOPED_REF__TYPE = 2;

  /**
   * The number of structural features of the '<em>body Scoped Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BODY_SCOPED_REF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.footerScopedRefImpl <em>footer Scoped Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.footerScopedRefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getfooterScopedRef()
   * @generated
   */
  int FOOTER_SCOPED_REF = 47;

  /**
   * The feature id for the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_SCOPED_REF__NAME = 0;

  /**
   * The feature id for the '<em><b>Names</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_SCOPED_REF__NAMES = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_SCOPED_REF__TYPE = 2;

  /**
   * The number of structural features of the '<em>footer Scoped Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOOTER_SCOPED_REF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.containerDefImpl <em>container Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.containerDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getcontainerDef()
   * @generated
   */
  int CONTAINER_DEF = 48;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_DEF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_DEF__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_DEF__NAME = 2;

  /**
   * The number of structural features of the '<em>container Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_DEF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.containerRefImpl <em>container Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.containerRefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getcontainerRef()
   * @generated
   */
  int CONTAINER_REF = 49;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_REF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_REF__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_REF__TYPE = 2;

  /**
   * The feature id for the '<em><b>Type Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_REF__TYPE_SCOPED = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_REF__NAME = 4;

  /**
   * The number of structural features of the '<em>container Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_REF_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.declaredEventDefImpl <em>declared Event Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.declaredEventDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getdeclaredEventDef()
   * @generated
   */
  int DECLARED_EVENT_DEF = 50;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_EVENT_DEF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_EVENT_DEF__TYPE = 1;

  /**
   * The feature id for the '<em><b>Scoped Event Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_EVENT_DEF__SCOPED_EVENT_TYPE = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_EVENT_DEF__NAME = 3;

  /**
   * The number of structural features of the '<em>declared Event Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARED_EVENT_DEF_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.simpleNumericTypeImpl <em>simple Numeric Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.simpleNumericTypeImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getsimpleNumericType()
   * @generated
   */
  int SIMPLE_NUMERIC_TYPE = 51;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_NUMERIC_TYPE__TYPE = 0;

  /**
   * The number of structural features of the '<em>simple Numeric Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_NUMERIC_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.fixedLenStringImpl <em>fixed Len String</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.fixedLenStringImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getfixedLenString()
   * @generated
   */
  int FIXED_LEN_STRING = 52;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_LEN_STRING__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_LEN_STRING__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_LEN_STRING__NAME = 2;

  /**
   * The feature id for the '<em><b>Upper Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_LEN_STRING__UPPER_LIM = 3;

  /**
   * The feature id for the '<em><b>Upper Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_LEN_STRING__UPPER_LIM_REF = 4;

  /**
   * The feature id for the '<em><b>Upper Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_LEN_STRING__UPPER_LIM_SCOPED = 5;

  /**
   * The number of structural features of the '<em>fixed Len String</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_LEN_STRING_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.varLenStringImpl <em>var Len String</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.varLenStringImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getvarLenString()
   * @generated
   */
  int VAR_LEN_STRING = 53;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_STRING__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_STRING__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_STRING__NAME = 2;

  /**
   * The feature id for the '<em><b>Lower Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_STRING__LOWER_LIM = 3;

  /**
   * The feature id for the '<em><b>Lower Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_STRING__LOWER_LIM_REF = 4;

  /**
   * The feature id for the '<em><b>Lower Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_STRING__LOWER_LIM_SCOPED = 5;

  /**
   * The feature id for the '<em><b>Upper Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_STRING__UPPER_LIM = 6;

  /**
   * The feature id for the '<em><b>Upper Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_STRING__UPPER_LIM_REF = 7;

  /**
   * The feature id for the '<em><b>Upper Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_STRING__UPPER_LIM_SCOPED = 8;

  /**
   * The number of structural features of the '<em>var Len String</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_STRING_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.fixedFieldDefImpl <em>fixed Field Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.fixedFieldDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getfixedFieldDef()
   * @generated
   */
  int FIXED_FIELD_DEF = 54;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_FIELD_DEF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_FIELD_DEF__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_FIELD_DEF__TYPE = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_FIELD_DEF__NAME = 3;

  /**
   * The feature id for the '<em><b>Field Unit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_FIELD_DEF__FIELD_UNIT = 4;

  /**
   * The feature id for the '<em><b>Value Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_FIELD_DEF__VALUE_RANGE = 5;

  /**
   * The number of structural features of the '<em>fixed Field Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIXED_FIELD_DEF_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.varFieldImpl <em>var Field</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.varFieldImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getvarField()
   * @generated
   */
  int VAR_FIELD = 55;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FIELD__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FIELD__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FIELD__NAME = 2;

  /**
   * The feature id for the '<em><b>Vtag Field</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FIELD__VTAG_FIELD = 3;

  /**
   * The number of structural features of the '<em>var Field</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FIELD_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.varLenFieldImpl <em>var Len Field</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.varLenFieldImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getvarLenField()
   * @generated
   */
  int VAR_LEN_FIELD = 56;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Field Format</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD__FIELD_FORMAT = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD__NAME = 3;

  /**
   * The feature id for the '<em><b>Count Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD__COUNT_COMMENT = 4;

  /**
   * The feature id for the '<em><b>Lower Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD__LOWER_LIM = 5;

  /**
   * The feature id for the '<em><b>Lower Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD__LOWER_LIM_REF = 6;

  /**
   * The feature id for the '<em><b>Lower Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD__LOWER_LIM_SCOPED = 7;

  /**
   * The feature id for the '<em><b>Upper Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD__UPPER_LIM = 8;

  /**
   * The feature id for the '<em><b>Upper Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD__UPPER_LIM_REF = 9;

  /**
   * The feature id for the '<em><b>Upper Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD__UPPER_LIM_SCOPED = 10;

  /**
   * The number of structural features of the '<em>var Len Field</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_LEN_FIELD_FEATURE_COUNT = 11;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.taggedUnitsEnumImpl <em>tagged Units Enum</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.taggedUnitsEnumImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#gettaggedUnitsEnum()
   * @generated
   */
  int TAGGED_UNITS_ENUM = 57;

  /**
   * The feature id for the '<em><b>Const tag</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_UNITS_ENUM__CONST_TAG = 0;

  /**
   * The feature id for the '<em><b>Tag</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_UNITS_ENUM__TAG = 1;

  /**
   * The feature id for the '<em><b>Scoped Tag</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_UNITS_ENUM__SCOPED_TAG = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_UNITS_ENUM__NAME = 3;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_UNITS_ENUM__TYPE = 4;

  /**
   * The feature id for the '<em><b>Field Unit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_UNITS_ENUM__FIELD_UNIT = 5;

  /**
   * The feature id for the '<em><b>Value Set Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_UNITS_ENUM__VALUE_SET_DEF = 6;

  /**
   * The feature id for the '<em><b>Scaled Range Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_UNITS_ENUM__SCALED_RANGE_DEF = 7;

  /**
   * The number of structural features of the '<em>tagged Units Enum</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_UNITS_ENUM_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.varFormatFieldImpl <em>var Format Field</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.varFormatFieldImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getvarFormatField()
   * @generated
   */
  int VAR_FORMAT_FIELD = 58;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FORMAT_FIELD__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FORMAT_FIELD__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FORMAT_FIELD__NAME = 2;

  /**
   * The feature id for the '<em><b>Count Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FORMAT_FIELD__COUNT_COMMENT = 3;

  /**
   * The feature id for the '<em><b>Units</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FORMAT_FIELD__UNITS = 4;

  /**
   * The feature id for the '<em><b>Count Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FORMAT_FIELD__COUNT_RANGE = 5;

  /**
   * The feature id for the '<em><b>Format Field</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FORMAT_FIELD__FORMAT_FIELD = 6;

  /**
   * The number of structural features of the '<em>var Format Field</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_FORMAT_FIELD_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.formatEnumDefImpl <em>format Enum Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.formatEnumDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getformatEnumDef()
   * @generated
   */
  int FORMAT_ENUM_DEF = 59;

  /**
   * The feature id for the '<em><b>Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORMAT_ENUM_DEF__INDEX = 0;

  /**
   * The feature id for the '<em><b>Const Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORMAT_ENUM_DEF__CONST_REF = 1;

  /**
   * The feature id for the '<em><b>Const Scoped Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORMAT_ENUM_DEF__CONST_SCOPED_REF = 2;

  /**
   * The feature id for the '<em><b>Field Format</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORMAT_ENUM_DEF__FIELD_FORMAT = 3;

  /**
   * The feature id for the '<em><b>Field Format Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORMAT_ENUM_DEF__FIELD_FORMAT_STR = 4;

  /**
   * The number of structural features of the '<em>format Enum Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORMAT_ENUM_DEF_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.valueSetDefImpl <em>value Set Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.valueSetDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getvalueSetDef()
   * @generated
   */
  int VALUE_SET_DEF = 60;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_SET_DEF__VALUE = 0;

  /**
   * The feature id for the '<em><b>Offset</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_SET_DEF__OFFSET = 1;

  /**
   * The number of structural features of the '<em>value Set Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_SET_DEF_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.bitfieldDefImpl <em>bitfield Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.bitfieldDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getbitfieldDef()
   * @generated
   */
  int BITFIELD_DEF = 61;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BITFIELD_DEF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BITFIELD_DEF__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BITFIELD_DEF__TYPE = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BITFIELD_DEF__NAME = 3;

  /**
   * The feature id for the '<em><b>Sub Field</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BITFIELD_DEF__SUB_FIELD = 4;

  /**
   * The number of structural features of the '<em>bitfield Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BITFIELD_DEF_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.valueRangeImpl <em>value Range</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.valueRangeImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getvalueRange()
   * @generated
   */
  int VALUE_RANGE = 62;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_RANGE__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Lower Limit type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_RANGE__LOWER_LIMIT_TYPE = 1;

  /**
   * The feature id for the '<em><b>Lower Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_RANGE__LOWER_LIM = 2;

  /**
   * The feature id for the '<em><b>Lower Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_RANGE__LOWER_LIM_REF = 3;

  /**
   * The feature id for the '<em><b>Lower Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_RANGE__LOWER_LIM_SCOPED = 4;

  /**
   * The feature id for the '<em><b>Upper Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_RANGE__UPPER_LIM = 5;

  /**
   * The feature id for the '<em><b>Upper Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_RANGE__UPPER_LIM_REF = 6;

  /**
   * The feature id for the '<em><b>Upper Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_RANGE__UPPER_LIM_SCOPED = 7;

  /**
   * The feature id for the '<em><b>Upper Limit type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_RANGE__UPPER_LIMIT_TYPE = 8;

  /**
   * The number of structural features of the '<em>value Range</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_RANGE_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.valueSpecImpl <em>value Spec</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.valueSpecImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getvalueSpec()
   * @generated
   */
  int VALUE_SPEC = 63;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_SPEC__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_SPEC__NAME = 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_SPEC__VALUE = 2;

  /**
   * The number of structural features of the '<em>value Spec</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_SPEC_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.scaledRangeDefImpl <em>scaled Range Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.scaledRangeDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getscaledRangeDef()
   * @generated
   */
  int SCALED_RANGE_DEF = 64;

  /**
   * The feature id for the '<em><b>Interp</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCALED_RANGE_DEF__INTERP = 0;

  /**
   * The feature id for the '<em><b>Lower Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCALED_RANGE_DEF__LOWER_LIM = 1;

  /**
   * The feature id for the '<em><b>Lower Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCALED_RANGE_DEF__LOWER_LIM_REF = 2;

  /**
   * The feature id for the '<em><b>Lower Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCALED_RANGE_DEF__LOWER_LIM_SCOPED = 3;

  /**
   * The feature id for the '<em><b>Upper Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCALED_RANGE_DEF__UPPER_LIM = 4;

  /**
   * The feature id for the '<em><b>Upper Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCALED_RANGE_DEF__UPPER_LIM_REF = 5;

  /**
   * The feature id for the '<em><b>Upper Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCALED_RANGE_DEF__UPPER_LIM_SCOPED = 6;

  /**
   * The feature id for the '<em><b>Function</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCALED_RANGE_DEF__FUNCTION = 7;

  /**
   * The number of structural features of the '<em>scaled Range Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCALED_RANGE_DEF_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.subFieldImpl <em>sub Field</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.subFieldImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getsubField()
   * @generated
   */
  int SUB_FIELD = 65;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUB_FIELD__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUB_FIELD__NAME = 1;

  /**
   * The feature id for the '<em><b>From Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUB_FIELD__FROM_INDEX = 2;

  /**
   * The feature id for the '<em><b>To Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUB_FIELD__TO_INDEX = 3;

  /**
   * The feature id for the '<em><b>Value Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUB_FIELD__VALUE_SET = 4;

  /**
   * The number of structural features of the '<em>sub Field</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUB_FIELD_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.listDefImpl <em>list Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.listDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getlistDef()
   * @generated
   */
  int LIST_DEF = 66;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__COMMENT = CONTAINER_DEF__COMMENT;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__OPTIONAL = CONTAINER_DEF__OPTIONAL;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__NAME = CONTAINER_DEF__NAME;

  /**
   * The feature id for the '<em><b>Count Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__COUNT_COMMENT = CONTAINER_DEF_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Min Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__MIN_COUNT = CONTAINER_DEF_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Min Count Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__MIN_COUNT_REF = CONTAINER_DEF_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Min Count Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__MIN_COUNT_SCOPED = CONTAINER_DEF_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Max Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__MAX_COUNT = CONTAINER_DEF_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Max Count Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__MAX_COUNT_REF = CONTAINER_DEF_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Max Count Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__MAX_COUNT_SCOPED = CONTAINER_DEF_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Container Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__CONTAINER_REF = CONTAINER_DEF_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Container Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF__CONTAINER_DEF = CONTAINER_DEF_FEATURE_COUNT + 8;

  /**
   * The number of structural features of the '<em>list Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_DEF_FEATURE_COUNT = CONTAINER_DEF_FEATURE_COUNT + 9;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.variantDefImpl <em>variant Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.variantDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getvariantDef()
   * @generated
   */
  int VARIANT_DEF = 67;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF__COMMENT = CONTAINER_DEF__COMMENT;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF__OPTIONAL = CONTAINER_DEF__OPTIONAL;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF__NAME = CONTAINER_DEF__NAME;

  /**
   * The feature id for the '<em><b>Vtag Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF__VTAG_COMMENT = CONTAINER_DEF_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Min Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF__MIN_COUNT = CONTAINER_DEF_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Min Count Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF__MIN_COUNT_REF = CONTAINER_DEF_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Min Count Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF__MIN_COUNT_SCOPED = CONTAINER_DEF_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Max Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF__MAX_COUNT = CONTAINER_DEF_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Max Count Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF__MAX_COUNT_REF = CONTAINER_DEF_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Max Count Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF__MAX_COUNT_SCOPED = CONTAINER_DEF_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Item List</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF__ITEM_LIST = CONTAINER_DEF_FEATURE_COUNT + 7;

  /**
   * The number of structural features of the '<em>variant Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_DEF_FEATURE_COUNT = CONTAINER_DEF_FEATURE_COUNT + 8;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.taggedItemDefImpl <em>tagged Item Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.taggedItemDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#gettaggedItemDef()
   * @generated
   */
  int TAGGED_ITEM_DEF = 68;

  /**
   * The feature id for the '<em><b>Container Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_ITEM_DEF__CONTAINER_DEF = 0;

  /**
   * The feature id for the '<em><b>Container Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_ITEM_DEF__CONTAINER_REF = 1;

  /**
   * The number of structural features of the '<em>tagged Item Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAGGED_ITEM_DEF_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.sequenceDefImpl <em>sequence Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.sequenceDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getsequenceDef()
   * @generated
   */
  int SEQUENCE_DEF = 69;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DEF__COMMENT = CONTAINER_DEF__COMMENT;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DEF__OPTIONAL = CONTAINER_DEF__OPTIONAL;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DEF__NAME = CONTAINER_DEF__NAME;

  /**
   * The feature id for the '<em><b>Container Type List</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DEF__CONTAINER_TYPE_LIST = CONTAINER_DEF_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>sequence Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DEF_FEATURE_COUNT = CONTAINER_DEF_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.recordDefImpl <em>record Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.recordDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getrecordDef()
   * @generated
   */
  int RECORD_DEF = 70;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__COMMENT = CONTAINER_DEF__COMMENT;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__OPTIONAL = CONTAINER_DEF__OPTIONAL;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__NAME = CONTAINER_DEF__NAME;

  /**
   * The feature id for the '<em><b>Array Def</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__ARRAY_DEF = CONTAINER_DEF_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Field Def</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__FIELD_DEF = CONTAINER_DEF_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Variable Field Def</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__VARIABLE_FIELD_DEF = CONTAINER_DEF_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Bitfield Def</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__BITFIELD_DEF = CONTAINER_DEF_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Fixed Length String Def</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__FIXED_LENGTH_STRING_DEF = CONTAINER_DEF_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Variable Length String Def</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__VARIABLE_LENGTH_STRING_DEF = CONTAINER_DEF_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Variable Length Field Def</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__VARIABLE_LENGTH_FIELD_DEF = CONTAINER_DEF_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Var Format Field</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__VAR_FORMAT_FIELD = CONTAINER_DEF_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Type Reference</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__TYPE_REFERENCE = CONTAINER_DEF_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Scoped Ref</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__SCOPED_REF = CONTAINER_DEF_FEATURE_COUNT + 9;

  /**
   * The number of structural features of the '<em>record Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF_FEATURE_COUNT = CONTAINER_DEF_FEATURE_COUNT + 10;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.constReferenceImpl <em>const Reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.constReferenceImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getconstReference()
   * @generated
   */
  int CONST_REFERENCE = 71;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_REFERENCE__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Const Val</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_REFERENCE__CONST_VAL = 1;

  /**
   * The number of structural features of the '<em>const Reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_REFERENCE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.typeReferenceImpl <em>type Reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.typeReferenceImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#gettypeReference()
   * @generated
   */
  int TYPE_REFERENCE = 72;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_REFERENCE__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_REFERENCE__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_REFERENCE__TYPE = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_REFERENCE__NAME = 3;

  /**
   * The number of structural features of the '<em>type Reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_REFERENCE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.arrayDefImpl <em>array Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.arrayDefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getarrayDef()
   * @generated
   */
  int ARRAY_DEF = 73;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_DEF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_DEF__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Type Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_DEF__TYPE_REF = 2;

  /**
   * The feature id for the '<em><b>Scoped Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_DEF__SCOPED_TYPE = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_DEF__NAME = 4;

  /**
   * The feature id for the '<em><b>Array Size</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_DEF__ARRAY_SIZE = 5;

  /**
   * The number of structural features of the '<em>array Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_DEF_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.messageScopedRefImpl <em>message Scoped Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.messageScopedRefImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getmessageScopedRef()
   * @generated
   */
  int MESSAGE_SCOPED_REF = 74;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SCOPED_REF__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Scope</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SCOPED_REF__SCOPE = 1;

  /**
   * The feature id for the '<em><b>Scopes</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SCOPED_REF__SCOPES = 2;

  /**
   * The feature id for the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SCOPED_REF__REF = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SCOPED_REF__NAME = 4;

  /**
   * The number of structural features of the '<em>message Scoped Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MESSAGE_SCOPED_REF_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.scopedTypeImpl <em>scoped Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.scopedTypeImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getscopedType()
   * @generated
   */
  int SCOPED_TYPE = 75;

  /**
   * The feature id for the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_TYPE__NAME = 0;

  /**
   * The feature id for the '<em><b>Names</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_TYPE__NAMES = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_TYPE__TYPE = 2;

  /**
   * The number of structural features of the '<em>scoped Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.scopedEventTypeImpl <em>scoped Event Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.scopedEventTypeImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getscopedEventType()
   * @generated
   */
  int SCOPED_EVENT_TYPE = 76;

  /**
   * The feature id for the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_EVENT_TYPE__NAME = 0;

  /**
   * The feature id for the '<em><b>Names</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_EVENT_TYPE__NAMES = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_EVENT_TYPE__TYPE = 2;

  /**
   * The number of structural features of the '<em>scoped Event Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_EVENT_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.scopedTypeIdImpl <em>scoped Type Id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.scopedTypeIdImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getscopedTypeId()
   * @generated
   */
  int SCOPED_TYPE_ID = 77;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_TYPE_ID__COMMENT = 0;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_TYPE_ID__OPTIONAL = 1;

  /**
   * The feature id for the '<em><b>Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_TYPE_ID__REF = 2;

  /**
   * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_TYPE_ID__SCOPED_NAME = 3;

  /**
   * The number of structural features of the '<em>scoped Type Id</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_TYPE_ID_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.impl.scopedConstIdImpl <em>scoped Const Id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.impl.scopedConstIdImpl
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getscopedConstId()
   * @generated
   */
  int SCOPED_CONST_ID = 78;

  /**
   * The feature id for the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_CONST_ID__NAME = 0;

  /**
   * The feature id for the '<em><b>Names</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_CONST_ID__NAMES = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_CONST_ID__TYPE = 2;

  /**
   * The number of structural features of the '<em>scoped Const Id</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPED_CONST_ID_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.FIELD_FORMAT <em>FIELD FORMAT</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.FIELD_FORMAT
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getFIELD_FORMAT()
   * @generated
   */
  int FIELD_FORMAT = 79;

  /**
   * The meta object id for the '{@link org.jts.eclipse.cjsidl.UNIT <em>UNIT</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jts.eclipse.cjsidl.UNIT
   * @see org.jts.eclipse.cjsidl.impl.CjsidlPackageImpl#getUNIT()
   * @generated
   */
  int UNIT = 80;


  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.jaus <em>jaus</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>jaus</em>'.
   * @see org.jts.eclipse.cjsidl.jaus
   * @generated
   */
  EClass getjaus();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.jaus#getSet <em>Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Set</em>'.
   * @see org.jts.eclipse.cjsidl.jaus#getSet()
   * @see #getjaus()
   * @generated
   */
  EReference getjaus_Set();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.serviceDef <em>service Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>service Def</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef
   * @generated
   */
  EClass getserviceDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.serviceDef#getServiceName <em>Service Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Service Name</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef#getServiceName()
   * @see #getserviceDef()
   * @generated
   */
  EAttribute getserviceDef_ServiceName();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.serviceDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef#getName()
   * @see #getserviceDef()
   * @generated
   */
  EAttribute getserviceDef_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.serviceDef#getServiceVersion <em>Service Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Service Version</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef#getServiceVersion()
   * @see #getserviceDef()
   * @generated
   */
  EAttribute getserviceDef_ServiceVersion();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.serviceDef#getDescr <em>Descr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Descr</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef#getDescr()
   * @see #getserviceDef()
   * @generated
   */
  EReference getserviceDef_Descr();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.serviceDef#getAssumpt <em>Assumpt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Assumpt</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef#getAssumpt()
   * @see #getserviceDef()
   * @generated
   */
  EAttribute getserviceDef_Assumpt();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.serviceDef#getRefs <em>Refs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Refs</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef#getRefs()
   * @see #getserviceDef()
   * @generated
   */
  EReference getserviceDef_Refs();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.serviceDef#getConstSet <em>Const Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Const Set</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef#getConstSet()
   * @see #getserviceDef()
   * @generated
   */
  EReference getserviceDef_ConstSet();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.serviceDef#getTypeSet <em>Type Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Set</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef#getTypeSet()
   * @see #getserviceDef()
   * @generated
   */
  EReference getserviceDef_TypeSet();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.serviceDef#getMessageSet <em>Message Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Message Set</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef#getMessageSet()
   * @see #getserviceDef()
   * @generated
   */
  EReference getserviceDef_MessageSet();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.serviceDef#getInternalEventSet <em>Internal Event Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Internal Event Set</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef#getInternalEventSet()
   * @see #getserviceDef()
   * @generated
   */
  EReference getserviceDef_InternalEventSet();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.serviceDef#getProtocolBehavior <em>Protocol Behavior</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Protocol Behavior</em>'.
   * @see org.jts.eclipse.cjsidl.serviceDef#getProtocolBehavior()
   * @see #getserviceDef()
   * @generated
   */
  EReference getserviceDef_ProtocolBehavior();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.description <em>description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>description</em>'.
   * @see org.jts.eclipse.cjsidl.description
   * @generated
   */
  EClass getdescription();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.description#getContent <em>Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Content</em>'.
   * @see org.jts.eclipse.cjsidl.description#getContent()
   * @see #getdescription()
   * @generated
   */
  EAttribute getdescription_Content();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.references <em>references</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>references</em>'.
   * @see org.jts.eclipse.cjsidl.references
   * @generated
   */
  EClass getreferences();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.references#getRefInherit <em>Ref Inherit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ref Inherit</em>'.
   * @see org.jts.eclipse.cjsidl.references#getRefInherit()
   * @see #getreferences()
   * @generated
   */
  EReference getreferences_RefInherit();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.references#getRefClient <em>Ref Client</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Ref Client</em>'.
   * @see org.jts.eclipse.cjsidl.references#getRefClient()
   * @see #getreferences()
   * @generated
   */
  EReference getreferences_RefClient();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.refAttr <em>ref Attr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ref Attr</em>'.
   * @see org.jts.eclipse.cjsidl.refAttr
   * @generated
   */
  EClass getrefAttr();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.refAttr#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.refAttr#getComment()
   * @see #getrefAttr()
   * @generated
   */
  EAttribute getrefAttr_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.refAttr#getImportedNamespace <em>Imported Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Imported Namespace</em>'.
   * @see org.jts.eclipse.cjsidl.refAttr#getImportedNamespace()
   * @see #getrefAttr()
   * @generated
   */
  EReference getrefAttr_ImportedNamespace();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.refAttr#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.refAttr#getName()
   * @see #getrefAttr()
   * @generated
   */
  EAttribute getrefAttr_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.declaredConstSet <em>declared Const Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>declared Const Set</em>'.
   * @see org.jts.eclipse.cjsidl.declaredConstSet
   * @generated
   */
  EClass getdeclaredConstSet();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredConstSet#getConstName <em>Const Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Const Name</em>'.
   * @see org.jts.eclipse.cjsidl.declaredConstSet#getConstName()
   * @see #getdeclaredConstSet()
   * @generated
   */
  EAttribute getdeclaredConstSet_ConstName();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredConstSet#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.declaredConstSet#getName()
   * @see #getdeclaredConstSet()
   * @generated
   */
  EAttribute getdeclaredConstSet_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredConstSet#getConstSetVersion <em>Const Set Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Const Set Version</em>'.
   * @see org.jts.eclipse.cjsidl.declaredConstSet#getConstSetVersion()
   * @see #getdeclaredConstSet()
   * @generated
   */
  EAttribute getdeclaredConstSet_ConstSetVersion();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.declaredConstSet#getDeclaredConstSetRef <em>Declared Const Set Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Declared Const Set Ref</em>'.
   * @see org.jts.eclipse.cjsidl.declaredConstSet#getDeclaredConstSetRef()
   * @see #getdeclaredConstSet()
   * @generated
   */
  EReference getdeclaredConstSet_DeclaredConstSetRef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.declaredConstSet#getConstDef <em>Const Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Const Def</em>'.
   * @see org.jts.eclipse.cjsidl.declaredConstSet#getConstDef()
   * @see #getdeclaredConstSet()
   * @generated
   */
  EReference getdeclaredConstSet_ConstDef();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.declaredConstSetRef <em>declared Const Set Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>declared Const Set Ref</em>'.
   * @see org.jts.eclipse.cjsidl.declaredConstSetRef
   * @generated
   */
  EClass getdeclaredConstSetRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredConstSetRef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.declaredConstSetRef#getComment()
   * @see #getdeclaredConstSetRef()
   * @generated
   */
  EAttribute getdeclaredConstSetRef_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.declaredConstSetRef#getImportedNamespace <em>Imported Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Imported Namespace</em>'.
   * @see org.jts.eclipse.cjsidl.declaredConstSetRef#getImportedNamespace()
   * @see #getdeclaredConstSetRef()
   * @generated
   */
  EReference getdeclaredConstSetRef_ImportedNamespace();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredConstSetRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.declaredConstSetRef#getName()
   * @see #getdeclaredConstSetRef()
   * @generated
   */
  EAttribute getdeclaredConstSetRef_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.declaredTypeSet <em>declared Type Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>declared Type Set</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSet
   * @generated
   */
  EClass getdeclaredTypeSet();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredTypeSet#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type Name</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSet#getTypeName()
   * @see #getdeclaredTypeSet()
   * @generated
   */
  EAttribute getdeclaredTypeSet_TypeName();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredTypeSet#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSet#getName()
   * @see #getdeclaredTypeSet()
   * @generated
   */
  EAttribute getdeclaredTypeSet_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredTypeSet#getVersion <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Version</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSet#getVersion()
   * @see #getdeclaredTypeSet()
   * @generated
   */
  EAttribute getdeclaredTypeSet_Version();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.declaredTypeSet#getDeclaredConstSetRef <em>Declared Const Set Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Declared Const Set Ref</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSet#getDeclaredConstSetRef()
   * @see #getdeclaredTypeSet()
   * @generated
   */
  EReference getdeclaredTypeSet_DeclaredConstSetRef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.declaredTypeSet#getDeclaredTypeSetRef <em>Declared Type Set Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Declared Type Set Ref</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSet#getDeclaredTypeSetRef()
   * @see #getdeclaredTypeSet()
   * @generated
   */
  EReference getdeclaredTypeSet_DeclaredTypeSetRef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.declaredTypeSet#getTypeDef <em>Type Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Type Def</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSet#getTypeDef()
   * @see #getdeclaredTypeSet()
   * @generated
   */
  EReference getdeclaredTypeSet_TypeDef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.declaredTypeSet#getTypeRef <em>Type Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Type Ref</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSet#getTypeRef()
   * @see #getdeclaredTypeSet()
   * @generated
   */
  EReference getdeclaredTypeSet_TypeRef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.declaredTypeSet#getScopedRef <em>Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Scoped Ref</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSet#getScopedRef()
   * @see #getdeclaredTypeSet()
   * @generated
   */
  EReference getdeclaredTypeSet_ScopedRef();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.messageSet <em>message Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>message Set</em>'.
   * @see org.jts.eclipse.cjsidl.messageSet
   * @generated
   */
  EClass getmessageSet();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.messageSet#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.messageSet#getComment()
   * @see #getmessageSet()
   * @generated
   */
  EAttribute getmessageSet_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.messageSet#getInputComment <em>Input Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Input Comment</em>'.
   * @see org.jts.eclipse.cjsidl.messageSet#getInputComment()
   * @see #getmessageSet()
   * @generated
   */
  EAttribute getmessageSet_InputComment();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.messageSet#getInputSet <em>Input Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Input Set</em>'.
   * @see org.jts.eclipse.cjsidl.messageSet#getInputSet()
   * @see #getmessageSet()
   * @generated
   */
  EReference getmessageSet_InputSet();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.messageSet#getOutputComment <em>Output Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Output Comment</em>'.
   * @see org.jts.eclipse.cjsidl.messageSet#getOutputComment()
   * @see #getmessageSet()
   * @generated
   */
  EAttribute getmessageSet_OutputComment();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.messageSet#getOutputSet <em>Output Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Output Set</em>'.
   * @see org.jts.eclipse.cjsidl.messageSet#getOutputSet()
   * @see #getmessageSet()
   * @generated
   */
  EReference getmessageSet_OutputSet();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.messages <em>messages</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>messages</em>'.
   * @see org.jts.eclipse.cjsidl.messages
   * @generated
   */
  EClass getmessages();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.messages#getMessageDefs <em>Message Defs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Message Defs</em>'.
   * @see org.jts.eclipse.cjsidl.messages#getMessageDefs()
   * @see #getmessages()
   * @generated
   */
  EReference getmessages_MessageDefs();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.messages#getTypeRefs <em>Type Refs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Type Refs</em>'.
   * @see org.jts.eclipse.cjsidl.messages#getTypeRefs()
   * @see #getmessages()
   * @generated
   */
  EReference getmessages_TypeRefs();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.messages#getScopedRefs <em>Scoped Refs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Scoped Refs</em>'.
   * @see org.jts.eclipse.cjsidl.messages#getScopedRefs()
   * @see #getmessages()
   * @generated
   */
  EReference getmessages_ScopedRefs();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.internalEventSet <em>internal Event Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>internal Event Set</em>'.
   * @see org.jts.eclipse.cjsidl.internalEventSet
   * @generated
   */
  EClass getinternalEventSet();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.internalEventSet#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.internalEventSet#getComment()
   * @see #getinternalEventSet()
   * @generated
   */
  EAttribute getinternalEventSet_Comment();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.internalEventSet#getDefs <em>Defs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Defs</em>'.
   * @see org.jts.eclipse.cjsidl.internalEventSet#getDefs()
   * @see #getinternalEventSet()
   * @generated
   */
  EReference getinternalEventSet_Defs();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.eventDef <em>event Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>event Def</em>'.
   * @see org.jts.eclipse.cjsidl.eventDef
   * @generated
   */
  EClass geteventDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.eventDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.eventDef#getName()
   * @see #geteventDef()
   * @generated
   */
  EAttribute geteventDef_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.eventDef#getDescr <em>Descr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Descr</em>'.
   * @see org.jts.eclipse.cjsidl.eventDef#getDescr()
   * @see #geteventDef()
   * @generated
   */
  EReference geteventDef_Descr();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.eventDef#getHeader <em>Header</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Header</em>'.
   * @see org.jts.eclipse.cjsidl.eventDef#getHeader()
   * @see #geteventDef()
   * @generated
   */
  EReference geteventDef_Header();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.eventDef#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.jts.eclipse.cjsidl.eventDef#getBody()
   * @see #geteventDef()
   * @generated
   */
  EReference geteventDef_Body();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.eventDef#getFooter <em>Footer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Footer</em>'.
   * @see org.jts.eclipse.cjsidl.eventDef#getFooter()
   * @see #geteventDef()
   * @generated
   */
  EReference geteventDef_Footer();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.messageRef <em>message Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>message Ref</em>'.
   * @see org.jts.eclipse.cjsidl.messageRef
   * @generated
   */
  EClass getmessageRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.messageRef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.messageRef#getComment()
   * @see #getmessageRef()
   * @generated
   */
  EAttribute getmessageRef_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.messageRef#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ref</em>'.
   * @see org.jts.eclipse.cjsidl.messageRef#getRef()
   * @see #getmessageRef()
   * @generated
   */
  EReference getmessageRef_Ref();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.messageRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.messageRef#getName()
   * @see #getmessageRef()
   * @generated
   */
  EAttribute getmessageRef_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.protocolBehavior <em>protocol Behavior</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>protocol Behavior</em>'.
   * @see org.jts.eclipse.cjsidl.protocolBehavior
   * @generated
   */
  EClass getprotocolBehavior();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.protocolBehavior#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.protocolBehavior#getComment()
   * @see #getprotocolBehavior()
   * @generated
   */
  EAttribute getprotocolBehavior_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.protocolBehavior#getStateless <em>Stateless</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Stateless</em>'.
   * @see org.jts.eclipse.cjsidl.protocolBehavior#getStateless()
   * @see #getprotocolBehavior()
   * @generated
   */
  EAttribute getprotocolBehavior_Stateless();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.protocolBehavior#getStateMachine <em>State Machine</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>State Machine</em>'.
   * @see org.jts.eclipse.cjsidl.protocolBehavior#getStateMachine()
   * @see #getprotocolBehavior()
   * @generated
   */
  EReference getprotocolBehavior_StateMachine();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.startState <em>start State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>start State</em>'.
   * @see org.jts.eclipse.cjsidl.startState
   * @generated
   */
  EClass getstartState();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.startState#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.startState#getComment()
   * @see #getstartState()
   * @generated
   */
  EAttribute getstartState_Comment();

  /**
   * Returns the meta object for the reference list '{@link org.jts.eclipse.cjsidl.startState#getScoped <em>Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.startState#getScoped()
   * @see #getstartState()
   * @generated
   */
  EReference getstartState_Scoped();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.startState#getState <em>State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>State</em>'.
   * @see org.jts.eclipse.cjsidl.startState#getState()
   * @see #getstartState()
   * @generated
   */
  EReference getstartState_State();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.stateMachine <em>state Machine</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>state Machine</em>'.
   * @see org.jts.eclipse.cjsidl.stateMachine
   * @generated
   */
  EClass getstateMachine();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.stateMachine#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.stateMachine#getComment()
   * @see #getstateMachine()
   * @generated
   */
  EAttribute getstateMachine_Comment();

  /**
   * Returns the meta object for the reference list '{@link org.jts.eclipse.cjsidl.stateMachine#getScoped <em>Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.stateMachine#getScoped()
   * @see #getstateMachine()
   * @generated
   */
  EReference getstateMachine_Scoped();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.stateMachine#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.stateMachine#getName()
   * @see #getstateMachine()
   * @generated
   */
  EAttribute getstateMachine_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.stateMachine#getStartState <em>Start State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Start State</em>'.
   * @see org.jts.eclipse.cjsidl.stateMachine#getStartState()
   * @see #getstateMachine()
   * @generated
   */
  EReference getstateMachine_StartState();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.stateMachine#getDefaultState <em>Default State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Default State</em>'.
   * @see org.jts.eclipse.cjsidl.stateMachine#getDefaultState()
   * @see #getstateMachine()
   * @generated
   */
  EReference getstateMachine_DefaultState();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.stateMachine#getStates <em>States</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>States</em>'.
   * @see org.jts.eclipse.cjsidl.stateMachine#getStates()
   * @see #getstateMachine()
   * @generated
   */
  EReference getstateMachine_States();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.state <em>state</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>state</em>'.
   * @see org.jts.eclipse.cjsidl.state
   * @generated
   */
  EClass getstate();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.state#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.state#getComment()
   * @see #getstate()
   * @generated
   */
  EAttribute getstate_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.state#getInitial <em>Initial</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Initial</em>'.
   * @see org.jts.eclipse.cjsidl.state#getInitial()
   * @see #getstate()
   * @generated
   */
  EAttribute getstate_Initial();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.state#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.state#getName()
   * @see #getstate()
   * @generated
   */
  EAttribute getstate_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.state#getEntryAction <em>Entry Action</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Entry Action</em>'.
   * @see org.jts.eclipse.cjsidl.state#getEntryAction()
   * @see #getstate()
   * @generated
   */
  EReference getstate_EntryAction();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.state#getExitAction <em>Exit Action</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Exit Action</em>'.
   * @see org.jts.eclipse.cjsidl.state#getExitAction()
   * @see #getstate()
   * @generated
   */
  EReference getstate_ExitAction();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.state#getTransitions <em>Transitions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Transitions</em>'.
   * @see org.jts.eclipse.cjsidl.state#getTransitions()
   * @see #getstate()
   * @generated
   */
  EReference getstate_Transitions();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.state#getDefaultTransition <em>Default Transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Default Transition</em>'.
   * @see org.jts.eclipse.cjsidl.state#getDefaultTransition()
   * @see #getstate()
   * @generated
   */
  EReference getstate_DefaultTransition();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.state#getDefaultState <em>Default State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Default State</em>'.
   * @see org.jts.eclipse.cjsidl.state#getDefaultState()
   * @see #getstate()
   * @generated
   */
  EReference getstate_DefaultState();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.state#getSubState <em>Sub State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Sub State</em>'.
   * @see org.jts.eclipse.cjsidl.state#getSubState()
   * @see #getstate()
   * @generated
   */
  EReference getstate_SubState();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.defaultState <em>default State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>default State</em>'.
   * @see org.jts.eclipse.cjsidl.defaultState
   * @generated
   */
  EClass getdefaultState();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.defaultState#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.defaultState#getComment()
   * @see #getdefaultState()
   * @generated
   */
  EAttribute getdefaultState_Comment();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.defaultState#getTransition <em>Transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Transition</em>'.
   * @see org.jts.eclipse.cjsidl.defaultState#getTransition()
   * @see #getdefaultState()
   * @generated
   */
  EReference getdefaultState_Transition();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.defaultState#getDefaultTransition <em>Default Transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Default Transition</em>'.
   * @see org.jts.eclipse.cjsidl.defaultState#getDefaultTransition()
   * @see #getdefaultState()
   * @generated
   */
  EReference getdefaultState_DefaultTransition();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.entry <em>entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>entry</em>'.
   * @see org.jts.eclipse.cjsidl.entry
   * @generated
   */
  EClass getentry();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.entry#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.entry#getComment()
   * @see #getentry()
   * @generated
   */
  EAttribute getentry_Comment();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.entry#getActions <em>Actions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Actions</em>'.
   * @see org.jts.eclipse.cjsidl.entry#getActions()
   * @see #getentry()
   * @generated
   */
  EReference getentry_Actions();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.entry#getSendActions <em>Send Actions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Send Actions</em>'.
   * @see org.jts.eclipse.cjsidl.entry#getSendActions()
   * @see #getentry()
   * @generated
   */
  EReference getentry_SendActions();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.exit <em>exit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>exit</em>'.
   * @see org.jts.eclipse.cjsidl.exit
   * @generated
   */
  EClass getexit();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.exit#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.exit#getComment()
   * @see #getexit()
   * @generated
   */
  EAttribute getexit_Comment();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.exit#getActions <em>Actions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Actions</em>'.
   * @see org.jts.eclipse.cjsidl.exit#getActions()
   * @see #getexit()
   * @generated
   */
  EReference getexit_Actions();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.exit#getSendActions <em>Send Actions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Send Actions</em>'.
   * @see org.jts.eclipse.cjsidl.exit#getSendActions()
   * @see #getexit()
   * @generated
   */
  EReference getexit_SendActions();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.transParams <em>trans Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>trans Params</em>'.
   * @see org.jts.eclipse.cjsidl.transParams
   * @generated
   */
  EClass gettransParams();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.transParams#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.jts.eclipse.cjsidl.transParams#getParams()
   * @see #gettransParams()
   * @generated
   */
  EReference gettransParams_Params();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.transParam <em>trans Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>trans Param</em>'.
   * @see org.jts.eclipse.cjsidl.transParam
   * @generated
   */
  EClass gettransParam();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.transParam#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.transParam#getComment()
   * @see #gettransParam()
   * @generated
   */
  EAttribute gettransParam_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.transParam#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.transParam#getType()
   * @see #gettransParam()
   * @generated
   */
  EReference gettransParam_Type();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.transParam#getScopedEventType <em>Scoped Event Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scoped Event Type</em>'.
   * @see org.jts.eclipse.cjsidl.transParam#getScopedEventType()
   * @see #gettransParam()
   * @generated
   */
  EReference gettransParam_ScopedEventType();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.transParam#getUnsignedType <em>Unsigned Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unsigned Type</em>'.
   * @see org.jts.eclipse.cjsidl.transParam#getUnsignedType()
   * @see #gettransParam()
   * @generated
   */
  EAttribute gettransParam_UnsignedType();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.transParam#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.transParam#getName()
   * @see #gettransParam()
   * @generated
   */
  EAttribute gettransParam_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.transition <em>transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>transition</em>'.
   * @see org.jts.eclipse.cjsidl.transition
   * @generated
   */
  EClass gettransition();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.transition#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.transition#getComment()
   * @see #gettransition()
   * @generated
   */
  EAttribute gettransition_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.transition#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.transition#getType()
   * @see #gettransition()
   * @generated
   */
  EAttribute gettransition_Type();

  /**
   * Returns the meta object for the reference list '{@link org.jts.eclipse.cjsidl.transition#getScoped <em>Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.transition#getScoped()
   * @see #gettransition()
   * @generated
   */
  EReference gettransition_Scoped();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.transition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.transition#getName()
   * @see #gettransition()
   * @generated
   */
  EAttribute gettransition_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.transition#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Params</em>'.
   * @see org.jts.eclipse.cjsidl.transition#getParams()
   * @see #gettransition()
   * @generated
   */
  EReference gettransition_Params();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.transition#getTransGuard <em>Trans Guard</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Trans Guard</em>'.
   * @see org.jts.eclipse.cjsidl.transition#getTransGuard()
   * @see #gettransition()
   * @generated
   */
  EReference gettransition_TransGuard();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.transition#getActions <em>Actions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Actions</em>'.
   * @see org.jts.eclipse.cjsidl.transition#getActions()
   * @see #gettransition()
   * @generated
   */
  EReference gettransition_Actions();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.transition#getSendActions <em>Send Actions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Send Actions</em>'.
   * @see org.jts.eclipse.cjsidl.transition#getSendActions()
   * @see #gettransition()
   * @generated
   */
  EReference gettransition_SendActions();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.transition#getDestination <em>Destination</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Destination</em>'.
   * @see org.jts.eclipse.cjsidl.transition#getDestination()
   * @see #gettransition()
   * @generated
   */
  EReference gettransition_Destination();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.defaultTransition <em>default Transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>default Transition</em>'.
   * @see org.jts.eclipse.cjsidl.defaultTransition
   * @generated
   */
  EClass getdefaultTransition();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.defaultTransition#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.defaultTransition#getComment()
   * @see #getdefaultTransition()
   * @generated
   */
  EAttribute getdefaultTransition_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.defaultTransition#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.defaultTransition#getType()
   * @see #getdefaultTransition()
   * @generated
   */
  EAttribute getdefaultTransition_Type();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.defaultTransition#getTransGuard <em>Trans Guard</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Trans Guard</em>'.
   * @see org.jts.eclipse.cjsidl.defaultTransition#getTransGuard()
   * @see #getdefaultTransition()
   * @generated
   */
  EReference getdefaultTransition_TransGuard();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.defaultTransition#getActions <em>Actions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Actions</em>'.
   * @see org.jts.eclipse.cjsidl.defaultTransition#getActions()
   * @see #getdefaultTransition()
   * @generated
   */
  EReference getdefaultTransition_Actions();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.defaultTransition#getSendActions <em>Send Actions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Send Actions</em>'.
   * @see org.jts.eclipse.cjsidl.defaultTransition#getSendActions()
   * @see #getdefaultTransition()
   * @generated
   */
  EReference getdefaultTransition_SendActions();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.defaultTransition#getDestination <em>Destination</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Destination</em>'.
   * @see org.jts.eclipse.cjsidl.defaultTransition#getDestination()
   * @see #getdefaultTransition()
   * @generated
   */
  EReference getdefaultTransition_Destination();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.internalTransition <em>internal Transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>internal Transition</em>'.
   * @see org.jts.eclipse.cjsidl.internalTransition
   * @generated
   */
  EClass getinternalTransition();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.internalTransition#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.internalTransition#getComment()
   * @see #getinternalTransition()
   * @generated
   */
  EAttribute getinternalTransition_Comment();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.simpleTransition <em>simple Transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>simple Transition</em>'.
   * @see org.jts.eclipse.cjsidl.simpleTransition
   * @generated
   */
  EClass getsimpleTransition();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.simpleTransition#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.simpleTransition#getComment()
   * @see #getsimpleTransition()
   * @generated
   */
  EAttribute getsimpleTransition_Comment();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.simpleTransition#getNextState <em>Next State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Next State</em>'.
   * @see org.jts.eclipse.cjsidl.simpleTransition#getNextState()
   * @see #getsimpleTransition()
   * @generated
   */
  EReference getsimpleTransition_NextState();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.pushTransition <em>push Transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>push Transition</em>'.
   * @see org.jts.eclipse.cjsidl.pushTransition
   * @generated
   */
  EClass getpushTransition();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.pushTransition#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.pushTransition#getComment()
   * @see #getpushTransition()
   * @generated
   */
  EAttribute getpushTransition_Comment();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.pushTransition#getNextState <em>Next State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Next State</em>'.
   * @see org.jts.eclipse.cjsidl.pushTransition#getNextState()
   * @see #getpushTransition()
   * @generated
   */
  EReference getpushTransition_NextState();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.pushTransition#getSimpleTransition <em>Simple Transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Simple Transition</em>'.
   * @see org.jts.eclipse.cjsidl.pushTransition#getSimpleTransition()
   * @see #getpushTransition()
   * @generated
   */
  EReference getpushTransition_SimpleTransition();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.popTransition <em>pop Transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>pop Transition</em>'.
   * @see org.jts.eclipse.cjsidl.popTransition
   * @generated
   */
  EClass getpopTransition();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.popTransition#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.popTransition#getComment()
   * @see #getpopTransition()
   * @generated
   */
  EAttribute getpopTransition_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.popTransition#getSecondaryTransition <em>Secondary Transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Secondary Transition</em>'.
   * @see org.jts.eclipse.cjsidl.popTransition#getSecondaryTransition()
   * @see #getpopTransition()
   * @generated
   */
  EReference getpopTransition_SecondaryTransition();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.popTransition#getParam <em>Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Param</em>'.
   * @see org.jts.eclipse.cjsidl.popTransition#getParam()
   * @see #getpopTransition()
   * @generated
   */
  EReference getpopTransition_Param();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.nextState <em>next State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>next State</em>'.
   * @see org.jts.eclipse.cjsidl.nextState
   * @generated
   */
  EClass getnextState();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.nextState#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.nextState#getComment()
   * @see #getnextState()
   * @generated
   */
  EAttribute getnextState_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.nextState#getFirstState <em>First State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>First State</em>'.
   * @see org.jts.eclipse.cjsidl.nextState#getFirstState()
   * @see #getnextState()
   * @generated
   */
  EReference getnextState_FirstState();

  /**
   * Returns the meta object for the reference list '{@link org.jts.eclipse.cjsidl.nextState#getScoped <em>Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.nextState#getScoped()
   * @see #getnextState()
   * @generated
   */
  EReference getnextState_Scoped();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.nextState#getNextState <em>Next State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Next State</em>'.
   * @see org.jts.eclipse.cjsidl.nextState#getNextState()
   * @see #getnextState()
   * @generated
   */
  EReference getnextState_NextState();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.guard <em>guard</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>guard</em>'.
   * @see org.jts.eclipse.cjsidl.guard
   * @generated
   */
  EClass getguard();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.guard#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.guard#getComment()
   * @see #getguard()
   * @generated
   */
  EAttribute getguard_Comment();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.guard#getGuardAction <em>Guard Action</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Guard Action</em>'.
   * @see org.jts.eclipse.cjsidl.guard#getGuardAction()
   * @see #getguard()
   * @generated
   */
  EReference getguard_GuardAction();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.guard#getEquiv <em>Equiv</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Equiv</em>'.
   * @see org.jts.eclipse.cjsidl.guard#getEquiv()
   * @see #getguard()
   * @generated
   */
  EAttribute getguard_Equiv();

  /**
   * Returns the meta object for the attribute list '{@link org.jts.eclipse.cjsidl.guard#getLogicalOperator <em>Logical Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Logical Operator</em>'.
   * @see org.jts.eclipse.cjsidl.guard#getLogicalOperator()
   * @see #getguard()
   * @generated
   */
  EAttribute getguard_LogicalOperator();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.guardAction <em>guard Action</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>guard Action</em>'.
   * @see org.jts.eclipse.cjsidl.guardAction
   * @generated
   */
  EClass getguardAction();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.guardAction#getNot <em>Not</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Not</em>'.
   * @see org.jts.eclipse.cjsidl.guardAction#getNot()
   * @see #getguardAction()
   * @generated
   */
  EAttribute getguardAction_Not();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.guardAction#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.guardAction#getName()
   * @see #getguardAction()
   * @generated
   */
  EAttribute getguardAction_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.guardAction#getParam <em>Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Param</em>'.
   * @see org.jts.eclipse.cjsidl.guardAction#getParam()
   * @see #getguardAction()
   * @generated
   */
  EReference getguardAction_Param();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.guardParam <em>guard Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>guard Param</em>'.
   * @see org.jts.eclipse.cjsidl.guardParam
   * @generated
   */
  EClass getguardParam();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.guardParam#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Parameter</em>'.
   * @see org.jts.eclipse.cjsidl.guardParam#getParameter()
   * @see #getguardParam()
   * @generated
   */
  EReference getguardParam_Parameter();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.guardParam#getGuardConst <em>Guard Const</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Guard Const</em>'.
   * @see org.jts.eclipse.cjsidl.guardParam#getGuardConst()
   * @see #getguardParam()
   * @generated
   */
  EAttribute getguardParam_GuardConst();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.actionList <em>action List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>action List</em>'.
   * @see org.jts.eclipse.cjsidl.actionList
   * @generated
   */
  EClass getactionList();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.actionList#getActions <em>Actions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Actions</em>'.
   * @see org.jts.eclipse.cjsidl.actionList#getActions()
   * @see #getactionList()
   * @generated
   */
  EReference getactionList_Actions();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.sendActionList <em>send Action List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>send Action List</em>'.
   * @see org.jts.eclipse.cjsidl.sendActionList
   * @generated
   */
  EClass getsendActionList();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.sendActionList#getActions <em>Actions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Actions</em>'.
   * @see org.jts.eclipse.cjsidl.sendActionList#getActions()
   * @see #getsendActionList()
   * @generated
   */
  EReference getsendActionList_Actions();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.action <em>action</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>action</em>'.
   * @see org.jts.eclipse.cjsidl.action
   * @generated
   */
  EClass getaction();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.action#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.action#getComment()
   * @see #getaction()
   * @generated
   */
  EAttribute getaction_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.action#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.action#getName()
   * @see #getaction()
   * @generated
   */
  EAttribute getaction_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.action#getParam <em>Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Param</em>'.
   * @see org.jts.eclipse.cjsidl.action#getParam()
   * @see #getaction()
   * @generated
   */
  EReference getaction_Param();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.constDef <em>const Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>const Def</em>'.
   * @see org.jts.eclipse.cjsidl.constDef
   * @generated
   */
  EClass getconstDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.constDef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.constDef#getComment()
   * @see #getconstDef()
   * @generated
   */
  EAttribute getconstDef_Comment();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.constDef#getConstType <em>Const Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Const Type</em>'.
   * @see org.jts.eclipse.cjsidl.constDef#getConstType()
   * @see #getconstDef()
   * @generated
   */
  EReference getconstDef_ConstType();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.constDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.constDef#getName()
   * @see #getconstDef()
   * @generated
   */
  EAttribute getconstDef_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.constDef#getConstValue <em>Const Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Const Value</em>'.
   * @see org.jts.eclipse.cjsidl.constDef#getConstValue()
   * @see #getconstDef()
   * @generated
   */
  EAttribute getconstDef_ConstValue();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.constDef#getFieldUnits <em>Field Units</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Field Units</em>'.
   * @see org.jts.eclipse.cjsidl.constDef#getFieldUnits()
   * @see #getconstDef()
   * @generated
   */
  EAttribute getconstDef_FieldUnits();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.declaredTypeSetRef <em>declared Type Set Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>declared Type Set Ref</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSetRef
   * @generated
   */
  EClass getdeclaredTypeSetRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredTypeSetRef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSetRef#getComment()
   * @see #getdeclaredTypeSetRef()
   * @generated
   */
  EAttribute getdeclaredTypeSetRef_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.declaredTypeSetRef#getImportedNamespace <em>Imported Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Imported Namespace</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSetRef#getImportedNamespace()
   * @see #getdeclaredTypeSetRef()
   * @generated
   */
  EReference getdeclaredTypeSetRef_ImportedNamespace();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredTypeSetRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.declaredTypeSetRef#getName()
   * @see #getdeclaredTypeSetRef()
   * @generated
   */
  EAttribute getdeclaredTypeSetRef_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.typeDef <em>type Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>type Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef
   * @generated
   */
  EClass gettypeDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getMessageDef <em>Message Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Message Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getMessageDef()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_MessageDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getArrayDef <em>Array Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getArrayDef()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_ArrayDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getRecordDef <em>Record Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Record Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getRecordDef()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_RecordDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getListDef <em>List Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>List Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getListDef()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_ListDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getVariantDef <em>Variant Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variant Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getVariantDef()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_VariantDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getSequenceDef <em>Sequence Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sequence Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getSequenceDef()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_SequenceDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getFixedFieldDef <em>Fixed Field Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fixed Field Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getFixedFieldDef()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_FixedFieldDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getVarField <em>Var Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var Field</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getVarField()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_VarField();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getBitfieldDef <em>Bitfield Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bitfield Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getBitfieldDef()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_BitfieldDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getFixedLenString <em>Fixed Len String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fixed Len String</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getFixedLenString()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_FixedLenString();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getVarLenString <em>Var Len String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var Len String</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getVarLenString()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_VarLenString();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getVarLenField <em>Var Len Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var Len Field</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getVarLenField()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_VarLenField();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getVarFormatField <em>Var Format Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var Format Field</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getVarFormatField()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_VarFormatField();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getHeaderDef <em>Header Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Header Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getHeaderDef()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_HeaderDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getBodyDef <em>Body Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getBodyDef()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_BodyDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.typeDef#getFooterDef <em>Footer Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Footer Def</em>'.
   * @see org.jts.eclipse.cjsidl.typeDef#getFooterDef()
   * @see #gettypeDef()
   * @generated
   */
  EReference gettypeDef_FooterDef();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.messageDef <em>message Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>message Def</em>'.
   * @see org.jts.eclipse.cjsidl.messageDef
   * @generated
   */
  EClass getmessageDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.messageDef#getCommand <em>Command</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Command</em>'.
   * @see org.jts.eclipse.cjsidl.messageDef#getCommand()
   * @see #getmessageDef()
   * @generated
   */
  EAttribute getmessageDef_Command();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.messageDef#getMessageID <em>Message ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Message ID</em>'.
   * @see org.jts.eclipse.cjsidl.messageDef#getMessageID()
   * @see #getmessageDef()
   * @generated
   */
  EAttribute getmessageDef_MessageID();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.messageDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.messageDef#getName()
   * @see #getmessageDef()
   * @generated
   */
  EAttribute getmessageDef_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.messageDef#getDescr <em>Descr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Descr</em>'.
   * @see org.jts.eclipse.cjsidl.messageDef#getDescr()
   * @see #getmessageDef()
   * @generated
   */
  EReference getmessageDef_Descr();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.messageDef#getHeader <em>Header</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Header</em>'.
   * @see org.jts.eclipse.cjsidl.messageDef#getHeader()
   * @see #getmessageDef()
   * @generated
   */
  EReference getmessageDef_Header();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.messageDef#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.jts.eclipse.cjsidl.messageDef#getBody()
   * @see #getmessageDef()
   * @generated
   */
  EReference getmessageDef_Body();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.messageDef#getFooter <em>Footer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Footer</em>'.
   * @see org.jts.eclipse.cjsidl.messageDef#getFooter()
   * @see #getmessageDef()
   * @generated
   */
  EReference getmessageDef_Footer();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.headerDef <em>header Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>header Def</em>'.
   * @see org.jts.eclipse.cjsidl.headerDef
   * @generated
   */
  EClass getheaderDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.headerDef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.headerDef#getComment()
   * @see #getheaderDef()
   * @generated
   */
  EAttribute getheaderDef_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.headerDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.headerDef#getName()
   * @see #getheaderDef()
   * @generated
   */
  EAttribute getheaderDef_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.headerDef#getRecordListSequenceVariant <em>Record List Sequence Variant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Record List Sequence Variant</em>'.
   * @see org.jts.eclipse.cjsidl.headerDef#getRecordListSequenceVariant()
   * @see #getheaderDef()
   * @generated
   */
  EReference getheaderDef_RecordListSequenceVariant();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.bodyDef <em>body Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>body Def</em>'.
   * @see org.jts.eclipse.cjsidl.bodyDef
   * @generated
   */
  EClass getbodyDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.bodyDef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.bodyDef#getComment()
   * @see #getbodyDef()
   * @generated
   */
  EAttribute getbodyDef_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.bodyDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.bodyDef#getName()
   * @see #getbodyDef()
   * @generated
   */
  EAttribute getbodyDef_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.bodyDef#getRecordListSequenceVariant <em>Record List Sequence Variant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Record List Sequence Variant</em>'.
   * @see org.jts.eclipse.cjsidl.bodyDef#getRecordListSequenceVariant()
   * @see #getbodyDef()
   * @generated
   */
  EReference getbodyDef_RecordListSequenceVariant();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.footerDef <em>footer Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>footer Def</em>'.
   * @see org.jts.eclipse.cjsidl.footerDef
   * @generated
   */
  EClass getfooterDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.footerDef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.footerDef#getComment()
   * @see #getfooterDef()
   * @generated
   */
  EAttribute getfooterDef_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.footerDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.footerDef#getName()
   * @see #getfooterDef()
   * @generated
   */
  EAttribute getfooterDef_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.footerDef#getRecordListSequenceVariant <em>Record List Sequence Variant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Record List Sequence Variant</em>'.
   * @see org.jts.eclipse.cjsidl.footerDef#getRecordListSequenceVariant()
   * @see #getfooterDef()
   * @generated
   */
  EReference getfooterDef_RecordListSequenceVariant();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.headerRef <em>header Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>header Ref</em>'.
   * @see org.jts.eclipse.cjsidl.headerRef
   * @generated
   */
  EClass getheaderRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.headerRef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.headerRef#getComment()
   * @see #getheaderRef()
   * @generated
   */
  EAttribute getheaderRef_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.headerRef#getTypeRef <em>Type Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type Ref</em>'.
   * @see org.jts.eclipse.cjsidl.headerRef#getTypeRef()
   * @see #getheaderRef()
   * @generated
   */
  EReference getheaderRef_TypeRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.headerRef#getScopedRef <em>Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scoped Ref</em>'.
   * @see org.jts.eclipse.cjsidl.headerRef#getScopedRef()
   * @see #getheaderRef()
   * @generated
   */
  EReference getheaderRef_ScopedRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.headerRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.headerRef#getName()
   * @see #getheaderRef()
   * @generated
   */
  EAttribute getheaderRef_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.bodyRef <em>body Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>body Ref</em>'.
   * @see org.jts.eclipse.cjsidl.bodyRef
   * @generated
   */
  EClass getbodyRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.bodyRef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.bodyRef#getComment()
   * @see #getbodyRef()
   * @generated
   */
  EAttribute getbodyRef_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.bodyRef#getTypeRef <em>Type Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type Ref</em>'.
   * @see org.jts.eclipse.cjsidl.bodyRef#getTypeRef()
   * @see #getbodyRef()
   * @generated
   */
  EReference getbodyRef_TypeRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.bodyRef#getScopedRef <em>Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scoped Ref</em>'.
   * @see org.jts.eclipse.cjsidl.bodyRef#getScopedRef()
   * @see #getbodyRef()
   * @generated
   */
  EReference getbodyRef_ScopedRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.bodyRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.bodyRef#getName()
   * @see #getbodyRef()
   * @generated
   */
  EAttribute getbodyRef_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.footerRef <em>footer Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>footer Ref</em>'.
   * @see org.jts.eclipse.cjsidl.footerRef
   * @generated
   */
  EClass getfooterRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.footerRef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.footerRef#getComment()
   * @see #getfooterRef()
   * @generated
   */
  EAttribute getfooterRef_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.footerRef#getTypeRef <em>Type Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type Ref</em>'.
   * @see org.jts.eclipse.cjsidl.footerRef#getTypeRef()
   * @see #getfooterRef()
   * @generated
   */
  EReference getfooterRef_TypeRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.footerRef#getScopedRef <em>Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scoped Ref</em>'.
   * @see org.jts.eclipse.cjsidl.footerRef#getScopedRef()
   * @see #getfooterRef()
   * @generated
   */
  EReference getfooterRef_ScopedRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.footerRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.footerRef#getName()
   * @see #getfooterRef()
   * @generated
   */
  EAttribute getfooterRef_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.headerScopedRef <em>header Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>header Scoped Ref</em>'.
   * @see org.jts.eclipse.cjsidl.headerScopedRef
   * @generated
   */
  EClass getheaderScopedRef();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.headerScopedRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.headerScopedRef#getName()
   * @see #getheaderScopedRef()
   * @generated
   */
  EReference getheaderScopedRef_Name();

  /**
   * Returns the meta object for the reference list '{@link org.jts.eclipse.cjsidl.headerScopedRef#getNames <em>Names</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Names</em>'.
   * @see org.jts.eclipse.cjsidl.headerScopedRef#getNames()
   * @see #getheaderScopedRef()
   * @generated
   */
  EReference getheaderScopedRef_Names();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.headerScopedRef#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.headerScopedRef#getType()
   * @see #getheaderScopedRef()
   * @generated
   */
  EReference getheaderScopedRef_Type();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.bodyScopedRef <em>body Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>body Scoped Ref</em>'.
   * @see org.jts.eclipse.cjsidl.bodyScopedRef
   * @generated
   */
  EClass getbodyScopedRef();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.bodyScopedRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.bodyScopedRef#getName()
   * @see #getbodyScopedRef()
   * @generated
   */
  EReference getbodyScopedRef_Name();

  /**
   * Returns the meta object for the reference list '{@link org.jts.eclipse.cjsidl.bodyScopedRef#getNames <em>Names</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Names</em>'.
   * @see org.jts.eclipse.cjsidl.bodyScopedRef#getNames()
   * @see #getbodyScopedRef()
   * @generated
   */
  EReference getbodyScopedRef_Names();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.bodyScopedRef#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.bodyScopedRef#getType()
   * @see #getbodyScopedRef()
   * @generated
   */
  EReference getbodyScopedRef_Type();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.footerScopedRef <em>footer Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>footer Scoped Ref</em>'.
   * @see org.jts.eclipse.cjsidl.footerScopedRef
   * @generated
   */
  EClass getfooterScopedRef();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.footerScopedRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.footerScopedRef#getName()
   * @see #getfooterScopedRef()
   * @generated
   */
  EReference getfooterScopedRef_Name();

  /**
   * Returns the meta object for the reference list '{@link org.jts.eclipse.cjsidl.footerScopedRef#getNames <em>Names</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Names</em>'.
   * @see org.jts.eclipse.cjsidl.footerScopedRef#getNames()
   * @see #getfooterScopedRef()
   * @generated
   */
  EReference getfooterScopedRef_Names();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.footerScopedRef#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.footerScopedRef#getType()
   * @see #getfooterScopedRef()
   * @generated
   */
  EReference getfooterScopedRef_Type();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.containerDef <em>container Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>container Def</em>'.
   * @see org.jts.eclipse.cjsidl.containerDef
   * @generated
   */
  EClass getcontainerDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.containerDef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.containerDef#getComment()
   * @see #getcontainerDef()
   * @generated
   */
  EAttribute getcontainerDef_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.containerDef#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.containerDef#getOptional()
   * @see #getcontainerDef()
   * @generated
   */
  EAttribute getcontainerDef_Optional();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.containerDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.containerDef#getName()
   * @see #getcontainerDef()
   * @generated
   */
  EAttribute getcontainerDef_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.containerRef <em>container Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>container Ref</em>'.
   * @see org.jts.eclipse.cjsidl.containerRef
   * @generated
   */
  EClass getcontainerRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.containerRef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.containerRef#getComment()
   * @see #getcontainerRef()
   * @generated
   */
  EAttribute getcontainerRef_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.containerRef#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.containerRef#getOptional()
   * @see #getcontainerRef()
   * @generated
   */
  EAttribute getcontainerRef_Optional();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.containerRef#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.containerRef#getType()
   * @see #getcontainerRef()
   * @generated
   */
  EReference getcontainerRef_Type();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.containerRef#getTypeScoped <em>Type Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.containerRef#getTypeScoped()
   * @see #getcontainerRef()
   * @generated
   */
  EReference getcontainerRef_TypeScoped();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.containerRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.containerRef#getName()
   * @see #getcontainerRef()
   * @generated
   */
  EAttribute getcontainerRef_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.declaredEventDef <em>declared Event Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>declared Event Def</em>'.
   * @see org.jts.eclipse.cjsidl.declaredEventDef
   * @generated
   */
  EClass getdeclaredEventDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredEventDef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.declaredEventDef#getComment()
   * @see #getdeclaredEventDef()
   * @generated
   */
  EAttribute getdeclaredEventDef_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.declaredEventDef#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.declaredEventDef#getType()
   * @see #getdeclaredEventDef()
   * @generated
   */
  EReference getdeclaredEventDef_Type();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.declaredEventDef#getScopedEventType <em>Scoped Event Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scoped Event Type</em>'.
   * @see org.jts.eclipse.cjsidl.declaredEventDef#getScopedEventType()
   * @see #getdeclaredEventDef()
   * @generated
   */
  EReference getdeclaredEventDef_ScopedEventType();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.declaredEventDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.declaredEventDef#getName()
   * @see #getdeclaredEventDef()
   * @generated
   */
  EAttribute getdeclaredEventDef_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.simpleNumericType <em>simple Numeric Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>simple Numeric Type</em>'.
   * @see org.jts.eclipse.cjsidl.simpleNumericType
   * @generated
   */
  EClass getsimpleNumericType();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.simpleNumericType#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.simpleNumericType#getType()
   * @see #getsimpleNumericType()
   * @generated
   */
  EAttribute getsimpleNumericType_Type();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.fixedLenString <em>fixed Len String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>fixed Len String</em>'.
   * @see org.jts.eclipse.cjsidl.fixedLenString
   * @generated
   */
  EClass getfixedLenString();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.fixedLenString#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.fixedLenString#getComment()
   * @see #getfixedLenString()
   * @generated
   */
  EAttribute getfixedLenString_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.fixedLenString#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.fixedLenString#getOptional()
   * @see #getfixedLenString()
   * @generated
   */
  EAttribute getfixedLenString_Optional();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.fixedLenString#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.fixedLenString#getName()
   * @see #getfixedLenString()
   * @generated
   */
  EAttribute getfixedLenString_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.fixedLenString#getUpperLim <em>Upper Lim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Upper Lim</em>'.
   * @see org.jts.eclipse.cjsidl.fixedLenString#getUpperLim()
   * @see #getfixedLenString()
   * @generated
   */
  EAttribute getfixedLenString_UpperLim();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.fixedLenString#getUpperLimRef <em>Upper Lim Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Lim Ref</em>'.
   * @see org.jts.eclipse.cjsidl.fixedLenString#getUpperLimRef()
   * @see #getfixedLenString()
   * @generated
   */
  EReference getfixedLenString_UpperLimRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.fixedLenString#getUpperLimScoped <em>Upper Lim Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Lim Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.fixedLenString#getUpperLimScoped()
   * @see #getfixedLenString()
   * @generated
   */
  EReference getfixedLenString_UpperLimScoped();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.varLenString <em>var Len String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>var Len String</em>'.
   * @see org.jts.eclipse.cjsidl.varLenString
   * @generated
   */
  EClass getvarLenString();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenString#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.varLenString#getComment()
   * @see #getvarLenString()
   * @generated
   */
  EAttribute getvarLenString_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenString#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.varLenString#getOptional()
   * @see #getvarLenString()
   * @generated
   */
  EAttribute getvarLenString_Optional();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenString#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.varLenString#getName()
   * @see #getvarLenString()
   * @generated
   */
  EAttribute getvarLenString_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenString#getLowerLim <em>Lower Lim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Lower Lim</em>'.
   * @see org.jts.eclipse.cjsidl.varLenString#getLowerLim()
   * @see #getvarLenString()
   * @generated
   */
  EAttribute getvarLenString_LowerLim();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.varLenString#getLowerLimRef <em>Lower Lim Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lower Lim Ref</em>'.
   * @see org.jts.eclipse.cjsidl.varLenString#getLowerLimRef()
   * @see #getvarLenString()
   * @generated
   */
  EReference getvarLenString_LowerLimRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.varLenString#getLowerLimScoped <em>Lower Lim Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lower Lim Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.varLenString#getLowerLimScoped()
   * @see #getvarLenString()
   * @generated
   */
  EReference getvarLenString_LowerLimScoped();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenString#getUpperLim <em>Upper Lim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Upper Lim</em>'.
   * @see org.jts.eclipse.cjsidl.varLenString#getUpperLim()
   * @see #getvarLenString()
   * @generated
   */
  EAttribute getvarLenString_UpperLim();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.varLenString#getUpperLimRef <em>Upper Lim Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Lim Ref</em>'.
   * @see org.jts.eclipse.cjsidl.varLenString#getUpperLimRef()
   * @see #getvarLenString()
   * @generated
   */
  EReference getvarLenString_UpperLimRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.varLenString#getUpperLimScoped <em>Upper Lim Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Lim Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.varLenString#getUpperLimScoped()
   * @see #getvarLenString()
   * @generated
   */
  EReference getvarLenString_UpperLimScoped();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.fixedFieldDef <em>fixed Field Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>fixed Field Def</em>'.
   * @see org.jts.eclipse.cjsidl.fixedFieldDef
   * @generated
   */
  EClass getfixedFieldDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.fixedFieldDef#getComment()
   * @see #getfixedFieldDef()
   * @generated
   */
  EAttribute getfixedFieldDef_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.fixedFieldDef#getOptional()
   * @see #getfixedFieldDef()
   * @generated
   */
  EAttribute getfixedFieldDef_Optional();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.fixedFieldDef#getType()
   * @see #getfixedFieldDef()
   * @generated
   */
  EReference getfixedFieldDef_Type();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.fixedFieldDef#getName()
   * @see #getfixedFieldDef()
   * @generated
   */
  EAttribute getfixedFieldDef_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getFieldUnit <em>Field Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Field Unit</em>'.
   * @see org.jts.eclipse.cjsidl.fixedFieldDef#getFieldUnit()
   * @see #getfixedFieldDef()
   * @generated
   */
  EAttribute getfixedFieldDef_FieldUnit();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getValueRange <em>Value Range</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value Range</em>'.
   * @see org.jts.eclipse.cjsidl.fixedFieldDef#getValueRange()
   * @see #getfixedFieldDef()
   * @generated
   */
  EReference getfixedFieldDef_ValueRange();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.varField <em>var Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>var Field</em>'.
   * @see org.jts.eclipse.cjsidl.varField
   * @generated
   */
  EClass getvarField();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varField#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.varField#getComment()
   * @see #getvarField()
   * @generated
   */
  EAttribute getvarField_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varField#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.varField#getOptional()
   * @see #getvarField()
   * @generated
   */
  EAttribute getvarField_Optional();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varField#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.varField#getName()
   * @see #getvarField()
   * @generated
   */
  EAttribute getvarField_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.varField#getVtagField <em>Vtag Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Vtag Field</em>'.
   * @see org.jts.eclipse.cjsidl.varField#getVtagField()
   * @see #getvarField()
   * @generated
   */
  EReference getvarField_VtagField();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.varLenField <em>var Len Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>var Len Field</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField
   * @generated
   */
  EClass getvarLenField();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenField#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField#getComment()
   * @see #getvarLenField()
   * @generated
   */
  EAttribute getvarLenField_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenField#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField#getOptional()
   * @see #getvarLenField()
   * @generated
   */
  EAttribute getvarLenField_Optional();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenField#getFieldFormat <em>Field Format</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Field Format</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField#getFieldFormat()
   * @see #getvarLenField()
   * @generated
   */
  EAttribute getvarLenField_FieldFormat();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenField#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField#getName()
   * @see #getvarLenField()
   * @generated
   */
  EAttribute getvarLenField_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenField#getCountComment <em>Count Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Count Comment</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField#getCountComment()
   * @see #getvarLenField()
   * @generated
   */
  EAttribute getvarLenField_CountComment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenField#getLowerLim <em>Lower Lim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Lower Lim</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField#getLowerLim()
   * @see #getvarLenField()
   * @generated
   */
  EAttribute getvarLenField_LowerLim();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.varLenField#getLowerLimRef <em>Lower Lim Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lower Lim Ref</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField#getLowerLimRef()
   * @see #getvarLenField()
   * @generated
   */
  EReference getvarLenField_LowerLimRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.varLenField#getLowerLimScoped <em>Lower Lim Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lower Lim Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField#getLowerLimScoped()
   * @see #getvarLenField()
   * @generated
   */
  EReference getvarLenField_LowerLimScoped();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varLenField#getUpperLim <em>Upper Lim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Upper Lim</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField#getUpperLim()
   * @see #getvarLenField()
   * @generated
   */
  EAttribute getvarLenField_UpperLim();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.varLenField#getUpperLimRef <em>Upper Lim Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Lim Ref</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField#getUpperLimRef()
   * @see #getvarLenField()
   * @generated
   */
  EReference getvarLenField_UpperLimRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.varLenField#getUpperLimScoped <em>Upper Lim Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Lim Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.varLenField#getUpperLimScoped()
   * @see #getvarLenField()
   * @generated
   */
  EReference getvarLenField_UpperLimScoped();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum <em>tagged Units Enum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>tagged Units Enum</em>'.
   * @see org.jts.eclipse.cjsidl.taggedUnitsEnum
   * @generated
   */
  EClass gettaggedUnitsEnum();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getConst_tag <em>Const tag</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Const tag</em>'.
   * @see org.jts.eclipse.cjsidl.taggedUnitsEnum#getConst_tag()
   * @see #gettaggedUnitsEnum()
   * @generated
   */
  EAttribute gettaggedUnitsEnum_Const_tag();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getTag <em>Tag</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Tag</em>'.
   * @see org.jts.eclipse.cjsidl.taggedUnitsEnum#getTag()
   * @see #gettaggedUnitsEnum()
   * @generated
   */
  EReference gettaggedUnitsEnum_Tag();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getScopedTag <em>Scoped Tag</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scoped Tag</em>'.
   * @see org.jts.eclipse.cjsidl.taggedUnitsEnum#getScopedTag()
   * @see #gettaggedUnitsEnum()
   * @generated
   */
  EReference gettaggedUnitsEnum_ScopedTag();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.taggedUnitsEnum#getName()
   * @see #gettaggedUnitsEnum()
   * @generated
   */
  EAttribute gettaggedUnitsEnum_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.taggedUnitsEnum#getType()
   * @see #gettaggedUnitsEnum()
   * @generated
   */
  EReference gettaggedUnitsEnum_Type();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getFieldUnit <em>Field Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Field Unit</em>'.
   * @see org.jts.eclipse.cjsidl.taggedUnitsEnum#getFieldUnit()
   * @see #gettaggedUnitsEnum()
   * @generated
   */
  EAttribute gettaggedUnitsEnum_FieldUnit();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getValueSetDef <em>Value Set Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value Set Def</em>'.
   * @see org.jts.eclipse.cjsidl.taggedUnitsEnum#getValueSetDef()
   * @see #gettaggedUnitsEnum()
   * @generated
   */
  EReference gettaggedUnitsEnum_ValueSetDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getScaledRangeDef <em>Scaled Range Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scaled Range Def</em>'.
   * @see org.jts.eclipse.cjsidl.taggedUnitsEnum#getScaledRangeDef()
   * @see #gettaggedUnitsEnum()
   * @generated
   */
  EReference gettaggedUnitsEnum_ScaledRangeDef();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.varFormatField <em>var Format Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>var Format Field</em>'.
   * @see org.jts.eclipse.cjsidl.varFormatField
   * @generated
   */
  EClass getvarFormatField();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varFormatField#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.varFormatField#getComment()
   * @see #getvarFormatField()
   * @generated
   */
  EAttribute getvarFormatField_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varFormatField#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.varFormatField#getOptional()
   * @see #getvarFormatField()
   * @generated
   */
  EAttribute getvarFormatField_Optional();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varFormatField#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.varFormatField#getName()
   * @see #getvarFormatField()
   * @generated
   */
  EAttribute getvarFormatField_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varFormatField#getCountComment <em>Count Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Count Comment</em>'.
   * @see org.jts.eclipse.cjsidl.varFormatField#getCountComment()
   * @see #getvarFormatField()
   * @generated
   */
  EAttribute getvarFormatField_CountComment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.varFormatField#getUnits <em>Units</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Units</em>'.
   * @see org.jts.eclipse.cjsidl.varFormatField#getUnits()
   * @see #getvarFormatField()
   * @generated
   */
  EAttribute getvarFormatField_Units();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.varFormatField#getCountRange <em>Count Range</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Count Range</em>'.
   * @see org.jts.eclipse.cjsidl.varFormatField#getCountRange()
   * @see #getvarFormatField()
   * @generated
   */
  EReference getvarFormatField_CountRange();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.varFormatField#getFormatField <em>Format Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Format Field</em>'.
   * @see org.jts.eclipse.cjsidl.varFormatField#getFormatField()
   * @see #getvarFormatField()
   * @generated
   */
  EReference getvarFormatField_FormatField();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.formatEnumDef <em>format Enum Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>format Enum Def</em>'.
   * @see org.jts.eclipse.cjsidl.formatEnumDef
   * @generated
   */
  EClass getformatEnumDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.formatEnumDef#getIndex <em>Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Index</em>'.
   * @see org.jts.eclipse.cjsidl.formatEnumDef#getIndex()
   * @see #getformatEnumDef()
   * @generated
   */
  EAttribute getformatEnumDef_Index();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.formatEnumDef#getConstRef <em>Const Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Const Ref</em>'.
   * @see org.jts.eclipse.cjsidl.formatEnumDef#getConstRef()
   * @see #getformatEnumDef()
   * @generated
   */
  EReference getformatEnumDef_ConstRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.formatEnumDef#getConstScopedRef <em>Const Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Const Scoped Ref</em>'.
   * @see org.jts.eclipse.cjsidl.formatEnumDef#getConstScopedRef()
   * @see #getformatEnumDef()
   * @generated
   */
  EReference getformatEnumDef_ConstScopedRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.formatEnumDef#getFieldFormat <em>Field Format</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Field Format</em>'.
   * @see org.jts.eclipse.cjsidl.formatEnumDef#getFieldFormat()
   * @see #getformatEnumDef()
   * @generated
   */
  EAttribute getformatEnumDef_FieldFormat();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.formatEnumDef#getFieldFormatStr <em>Field Format Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Field Format Str</em>'.
   * @see org.jts.eclipse.cjsidl.formatEnumDef#getFieldFormatStr()
   * @see #getformatEnumDef()
   * @generated
   */
  EAttribute getformatEnumDef_FieldFormatStr();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.valueSetDef <em>value Set Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>value Set Def</em>'.
   * @see org.jts.eclipse.cjsidl.valueSetDef
   * @generated
   */
  EClass getvalueSetDef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.valueSetDef#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Value</em>'.
   * @see org.jts.eclipse.cjsidl.valueSetDef#getValue()
   * @see #getvalueSetDef()
   * @generated
   */
  EReference getvalueSetDef_Value();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.valueSetDef#getOffset <em>Offset</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Offset</em>'.
   * @see org.jts.eclipse.cjsidl.valueSetDef#getOffset()
   * @see #getvalueSetDef()
   * @generated
   */
  EAttribute getvalueSetDef_Offset();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.bitfieldDef <em>bitfield Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>bitfield Def</em>'.
   * @see org.jts.eclipse.cjsidl.bitfieldDef
   * @generated
   */
  EClass getbitfieldDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.bitfieldDef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.bitfieldDef#getComment()
   * @see #getbitfieldDef()
   * @generated
   */
  EAttribute getbitfieldDef_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.bitfieldDef#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.bitfieldDef#getOptional()
   * @see #getbitfieldDef()
   * @generated
   */
  EAttribute getbitfieldDef_Optional();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.bitfieldDef#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.bitfieldDef#getType()
   * @see #getbitfieldDef()
   * @generated
   */
  EAttribute getbitfieldDef_Type();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.bitfieldDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.bitfieldDef#getName()
   * @see #getbitfieldDef()
   * @generated
   */
  EAttribute getbitfieldDef_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.bitfieldDef#getSubField <em>Sub Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Sub Field</em>'.
   * @see org.jts.eclipse.cjsidl.bitfieldDef#getSubField()
   * @see #getbitfieldDef()
   * @generated
   */
  EReference getbitfieldDef_SubField();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.valueRange <em>value Range</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>value Range</em>'.
   * @see org.jts.eclipse.cjsidl.valueRange
   * @generated
   */
  EClass getvalueRange();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.valueRange#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.valueRange#getComment()
   * @see #getvalueRange()
   * @generated
   */
  EAttribute getvalueRange_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.valueRange#getLowerLimit_type <em>Lower Limit type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Lower Limit type</em>'.
   * @see org.jts.eclipse.cjsidl.valueRange#getLowerLimit_type()
   * @see #getvalueRange()
   * @generated
   */
  EAttribute getvalueRange_LowerLimit_type();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.valueRange#getLowerLim <em>Lower Lim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Lower Lim</em>'.
   * @see org.jts.eclipse.cjsidl.valueRange#getLowerLim()
   * @see #getvalueRange()
   * @generated
   */
  EAttribute getvalueRange_LowerLim();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.valueRange#getLowerLimRef <em>Lower Lim Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lower Lim Ref</em>'.
   * @see org.jts.eclipse.cjsidl.valueRange#getLowerLimRef()
   * @see #getvalueRange()
   * @generated
   */
  EReference getvalueRange_LowerLimRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.valueRange#getLowerLimScoped <em>Lower Lim Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lower Lim Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.valueRange#getLowerLimScoped()
   * @see #getvalueRange()
   * @generated
   */
  EReference getvalueRange_LowerLimScoped();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.valueRange#getUpperLim <em>Upper Lim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Upper Lim</em>'.
   * @see org.jts.eclipse.cjsidl.valueRange#getUpperLim()
   * @see #getvalueRange()
   * @generated
   */
  EAttribute getvalueRange_UpperLim();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.valueRange#getUpperLimRef <em>Upper Lim Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Lim Ref</em>'.
   * @see org.jts.eclipse.cjsidl.valueRange#getUpperLimRef()
   * @see #getvalueRange()
   * @generated
   */
  EReference getvalueRange_UpperLimRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.valueRange#getUpperLimScoped <em>Upper Lim Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Lim Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.valueRange#getUpperLimScoped()
   * @see #getvalueRange()
   * @generated
   */
  EReference getvalueRange_UpperLimScoped();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.valueRange#getUpperLimit_type <em>Upper Limit type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Upper Limit type</em>'.
   * @see org.jts.eclipse.cjsidl.valueRange#getUpperLimit_type()
   * @see #getvalueRange()
   * @generated
   */
  EAttribute getvalueRange_UpperLimit_type();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.valueSpec <em>value Spec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>value Spec</em>'.
   * @see org.jts.eclipse.cjsidl.valueSpec
   * @generated
   */
  EClass getvalueSpec();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.valueSpec#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.valueSpec#getComment()
   * @see #getvalueSpec()
   * @generated
   */
  EAttribute getvalueSpec_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.valueSpec#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.valueSpec#getName()
   * @see #getvalueSpec()
   * @generated
   */
  EAttribute getvalueSpec_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.valueSpec#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.jts.eclipse.cjsidl.valueSpec#getValue()
   * @see #getvalueSpec()
   * @generated
   */
  EAttribute getvalueSpec_Value();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.scaledRangeDef <em>scaled Range Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>scaled Range Def</em>'.
   * @see org.jts.eclipse.cjsidl.scaledRangeDef
   * @generated
   */
  EClass getscaledRangeDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getInterp <em>Interp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Interp</em>'.
   * @see org.jts.eclipse.cjsidl.scaledRangeDef#getInterp()
   * @see #getscaledRangeDef()
   * @generated
   */
  EAttribute getscaledRangeDef_Interp();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLim <em>Lower Lim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Lower Lim</em>'.
   * @see org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLim()
   * @see #getscaledRangeDef()
   * @generated
   */
  EAttribute getscaledRangeDef_LowerLim();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLimRef <em>Lower Lim Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lower Lim Ref</em>'.
   * @see org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLimRef()
   * @see #getscaledRangeDef()
   * @generated
   */
  EReference getscaledRangeDef_LowerLimRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLimScoped <em>Lower Lim Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lower Lim Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLimScoped()
   * @see #getscaledRangeDef()
   * @generated
   */
  EReference getscaledRangeDef_LowerLimScoped();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLim <em>Upper Lim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Upper Lim</em>'.
   * @see org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLim()
   * @see #getscaledRangeDef()
   * @generated
   */
  EAttribute getscaledRangeDef_UpperLim();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLimRef <em>Upper Lim Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Lim Ref</em>'.
   * @see org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLimRef()
   * @see #getscaledRangeDef()
   * @generated
   */
  EReference getscaledRangeDef_UpperLimRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLimScoped <em>Upper Lim Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Lim Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLimScoped()
   * @see #getscaledRangeDef()
   * @generated
   */
  EReference getscaledRangeDef_UpperLimScoped();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getFunction <em>Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Function</em>'.
   * @see org.jts.eclipse.cjsidl.scaledRangeDef#getFunction()
   * @see #getscaledRangeDef()
   * @generated
   */
  EAttribute getscaledRangeDef_Function();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.subField <em>sub Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>sub Field</em>'.
   * @see org.jts.eclipse.cjsidl.subField
   * @generated
   */
  EClass getsubField();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.subField#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.subField#getComment()
   * @see #getsubField()
   * @generated
   */
  EAttribute getsubField_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.subField#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.subField#getName()
   * @see #getsubField()
   * @generated
   */
  EAttribute getsubField_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.subField#getFromIndex <em>From Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>From Index</em>'.
   * @see org.jts.eclipse.cjsidl.subField#getFromIndex()
   * @see #getsubField()
   * @generated
   */
  EAttribute getsubField_FromIndex();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.subField#getToIndex <em>To Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>To Index</em>'.
   * @see org.jts.eclipse.cjsidl.subField#getToIndex()
   * @see #getsubField()
   * @generated
   */
  EAttribute getsubField_ToIndex();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.subField#getValueSet <em>Value Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value Set</em>'.
   * @see org.jts.eclipse.cjsidl.subField#getValueSet()
   * @see #getsubField()
   * @generated
   */
  EReference getsubField_ValueSet();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.listDef <em>list Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>list Def</em>'.
   * @see org.jts.eclipse.cjsidl.listDef
   * @generated
   */
  EClass getlistDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.listDef#getCountComment <em>Count Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Count Comment</em>'.
   * @see org.jts.eclipse.cjsidl.listDef#getCountComment()
   * @see #getlistDef()
   * @generated
   */
  EAttribute getlistDef_CountComment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.listDef#getMinCount <em>Min Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min Count</em>'.
   * @see org.jts.eclipse.cjsidl.listDef#getMinCount()
   * @see #getlistDef()
   * @generated
   */
  EAttribute getlistDef_MinCount();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.listDef#getMinCountRef <em>Min Count Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Min Count Ref</em>'.
   * @see org.jts.eclipse.cjsidl.listDef#getMinCountRef()
   * @see #getlistDef()
   * @generated
   */
  EReference getlistDef_MinCountRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.listDef#getMinCountScoped <em>Min Count Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Min Count Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.listDef#getMinCountScoped()
   * @see #getlistDef()
   * @generated
   */
  EReference getlistDef_MinCountScoped();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.listDef#getMaxCount <em>Max Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max Count</em>'.
   * @see org.jts.eclipse.cjsidl.listDef#getMaxCount()
   * @see #getlistDef()
   * @generated
   */
  EAttribute getlistDef_MaxCount();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.listDef#getMaxCountRef <em>Max Count Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Max Count Ref</em>'.
   * @see org.jts.eclipse.cjsidl.listDef#getMaxCountRef()
   * @see #getlistDef()
   * @generated
   */
  EReference getlistDef_MaxCountRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.listDef#getMaxCountScoped <em>Max Count Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Max Count Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.listDef#getMaxCountScoped()
   * @see #getlistDef()
   * @generated
   */
  EReference getlistDef_MaxCountScoped();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.listDef#getContainerRef <em>Container Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Container Ref</em>'.
   * @see org.jts.eclipse.cjsidl.listDef#getContainerRef()
   * @see #getlistDef()
   * @generated
   */
  EReference getlistDef_ContainerRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.listDef#getContainerDef <em>Container Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Container Def</em>'.
   * @see org.jts.eclipse.cjsidl.listDef#getContainerDef()
   * @see #getlistDef()
   * @generated
   */
  EReference getlistDef_ContainerDef();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.variantDef <em>variant Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>variant Def</em>'.
   * @see org.jts.eclipse.cjsidl.variantDef
   * @generated
   */
  EClass getvariantDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.variantDef#getVtagComment <em>Vtag Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Vtag Comment</em>'.
   * @see org.jts.eclipse.cjsidl.variantDef#getVtagComment()
   * @see #getvariantDef()
   * @generated
   */
  EAttribute getvariantDef_VtagComment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.variantDef#getMinCount <em>Min Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min Count</em>'.
   * @see org.jts.eclipse.cjsidl.variantDef#getMinCount()
   * @see #getvariantDef()
   * @generated
   */
  EAttribute getvariantDef_MinCount();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.variantDef#getMinCountRef <em>Min Count Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Min Count Ref</em>'.
   * @see org.jts.eclipse.cjsidl.variantDef#getMinCountRef()
   * @see #getvariantDef()
   * @generated
   */
  EReference getvariantDef_MinCountRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.variantDef#getMinCountScoped <em>Min Count Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Min Count Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.variantDef#getMinCountScoped()
   * @see #getvariantDef()
   * @generated
   */
  EReference getvariantDef_MinCountScoped();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.variantDef#getMaxCount <em>Max Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max Count</em>'.
   * @see org.jts.eclipse.cjsidl.variantDef#getMaxCount()
   * @see #getvariantDef()
   * @generated
   */
  EAttribute getvariantDef_MaxCount();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.variantDef#getMaxCountRef <em>Max Count Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Max Count Ref</em>'.
   * @see org.jts.eclipse.cjsidl.variantDef#getMaxCountRef()
   * @see #getvariantDef()
   * @generated
   */
  EReference getvariantDef_MaxCountRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.variantDef#getMaxCountScoped <em>Max Count Scoped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Max Count Scoped</em>'.
   * @see org.jts.eclipse.cjsidl.variantDef#getMaxCountScoped()
   * @see #getvariantDef()
   * @generated
   */
  EReference getvariantDef_MaxCountScoped();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.variantDef#getItemList <em>Item List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Item List</em>'.
   * @see org.jts.eclipse.cjsidl.variantDef#getItemList()
   * @see #getvariantDef()
   * @generated
   */
  EReference getvariantDef_ItemList();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.taggedItemDef <em>tagged Item Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>tagged Item Def</em>'.
   * @see org.jts.eclipse.cjsidl.taggedItemDef
   * @generated
   */
  EClass gettaggedItemDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.taggedItemDef#getContainerDef <em>Container Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Container Def</em>'.
   * @see org.jts.eclipse.cjsidl.taggedItemDef#getContainerDef()
   * @see #gettaggedItemDef()
   * @generated
   */
  EReference gettaggedItemDef_ContainerDef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.taggedItemDef#getContainerRef <em>Container Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Container Ref</em>'.
   * @see org.jts.eclipse.cjsidl.taggedItemDef#getContainerRef()
   * @see #gettaggedItemDef()
   * @generated
   */
  EReference gettaggedItemDef_ContainerRef();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.sequenceDef <em>sequence Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>sequence Def</em>'.
   * @see org.jts.eclipse.cjsidl.sequenceDef
   * @generated
   */
  EClass getsequenceDef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.sequenceDef#getContainerTypeList <em>Container Type List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Container Type List</em>'.
   * @see org.jts.eclipse.cjsidl.sequenceDef#getContainerTypeList()
   * @see #getsequenceDef()
   * @generated
   */
  EReference getsequenceDef_ContainerTypeList();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.recordDef <em>record Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>record Def</em>'.
   * @see org.jts.eclipse.cjsidl.recordDef
   * @generated
   */
  EClass getrecordDef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.recordDef#getArrayDef <em>Array Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Array Def</em>'.
   * @see org.jts.eclipse.cjsidl.recordDef#getArrayDef()
   * @see #getrecordDef()
   * @generated
   */
  EReference getrecordDef_ArrayDef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.recordDef#getFieldDef <em>Field Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Field Def</em>'.
   * @see org.jts.eclipse.cjsidl.recordDef#getFieldDef()
   * @see #getrecordDef()
   * @generated
   */
  EReference getrecordDef_FieldDef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.recordDef#getVariableFieldDef <em>Variable Field Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable Field Def</em>'.
   * @see org.jts.eclipse.cjsidl.recordDef#getVariableFieldDef()
   * @see #getrecordDef()
   * @generated
   */
  EReference getrecordDef_VariableFieldDef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.recordDef#getBitfieldDef <em>Bitfield Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Bitfield Def</em>'.
   * @see org.jts.eclipse.cjsidl.recordDef#getBitfieldDef()
   * @see #getrecordDef()
   * @generated
   */
  EReference getrecordDef_BitfieldDef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.recordDef#getFixedLengthStringDef <em>Fixed Length String Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Fixed Length String Def</em>'.
   * @see org.jts.eclipse.cjsidl.recordDef#getFixedLengthStringDef()
   * @see #getrecordDef()
   * @generated
   */
  EReference getrecordDef_FixedLengthStringDef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.recordDef#getVariableLengthStringDef <em>Variable Length String Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable Length String Def</em>'.
   * @see org.jts.eclipse.cjsidl.recordDef#getVariableLengthStringDef()
   * @see #getrecordDef()
   * @generated
   */
  EReference getrecordDef_VariableLengthStringDef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.recordDef#getVariableLengthFieldDef <em>Variable Length Field Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable Length Field Def</em>'.
   * @see org.jts.eclipse.cjsidl.recordDef#getVariableLengthFieldDef()
   * @see #getrecordDef()
   * @generated
   */
  EReference getrecordDef_VariableLengthFieldDef();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.recordDef#getVarFormatField <em>Var Format Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Var Format Field</em>'.
   * @see org.jts.eclipse.cjsidl.recordDef#getVarFormatField()
   * @see #getrecordDef()
   * @generated
   */
  EReference getrecordDef_VarFormatField();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.recordDef#getTypeReference <em>Type Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Type Reference</em>'.
   * @see org.jts.eclipse.cjsidl.recordDef#getTypeReference()
   * @see #getrecordDef()
   * @generated
   */
  EReference getrecordDef_TypeReference();

  /**
   * Returns the meta object for the containment reference list '{@link org.jts.eclipse.cjsidl.recordDef#getScopedRef <em>Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Scoped Ref</em>'.
   * @see org.jts.eclipse.cjsidl.recordDef#getScopedRef()
   * @see #getrecordDef()
   * @generated
   */
  EReference getrecordDef_ScopedRef();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.constReference <em>const Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>const Reference</em>'.
   * @see org.jts.eclipse.cjsidl.constReference
   * @generated
   */
  EClass getconstReference();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.constReference#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.constReference#getComment()
   * @see #getconstReference()
   * @generated
   */
  EAttribute getconstReference_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.constReference#getConstVal <em>Const Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Const Val</em>'.
   * @see org.jts.eclipse.cjsidl.constReference#getConstVal()
   * @see #getconstReference()
   * @generated
   */
  EReference getconstReference_ConstVal();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.typeReference <em>type Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>type Reference</em>'.
   * @see org.jts.eclipse.cjsidl.typeReference
   * @generated
   */
  EClass gettypeReference();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.typeReference#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.typeReference#getComment()
   * @see #gettypeReference()
   * @generated
   */
  EAttribute gettypeReference_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.typeReference#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.typeReference#getOptional()
   * @see #gettypeReference()
   * @generated
   */
  EAttribute gettypeReference_Optional();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.typeReference#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.typeReference#getType()
   * @see #gettypeReference()
   * @generated
   */
  EReference gettypeReference_Type();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.typeReference#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.typeReference#getName()
   * @see #gettypeReference()
   * @generated
   */
  EAttribute gettypeReference_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.arrayDef <em>array Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>array Def</em>'.
   * @see org.jts.eclipse.cjsidl.arrayDef
   * @generated
   */
  EClass getarrayDef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.arrayDef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.arrayDef#getComment()
   * @see #getarrayDef()
   * @generated
   */
  EAttribute getarrayDef_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.arrayDef#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.arrayDef#getOptional()
   * @see #getarrayDef()
   * @generated
   */
  EAttribute getarrayDef_Optional();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.arrayDef#getTypeRef <em>Type Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type Ref</em>'.
   * @see org.jts.eclipse.cjsidl.arrayDef#getTypeRef()
   * @see #getarrayDef()
   * @generated
   */
  EReference getarrayDef_TypeRef();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.arrayDef#getScopedType <em>Scoped Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scoped Type</em>'.
   * @see org.jts.eclipse.cjsidl.arrayDef#getScopedType()
   * @see #getarrayDef()
   * @generated
   */
  EReference getarrayDef_ScopedType();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.arrayDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.arrayDef#getName()
   * @see #getarrayDef()
   * @generated
   */
  EAttribute getarrayDef_Name();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.arrayDef#getArraySize <em>Array Size</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Array Size</em>'.
   * @see org.jts.eclipse.cjsidl.arrayDef#getArraySize()
   * @see #getarrayDef()
   * @generated
   */
  EAttribute getarrayDef_ArraySize();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.messageScopedRef <em>message Scoped Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>message Scoped Ref</em>'.
   * @see org.jts.eclipse.cjsidl.messageScopedRef
   * @generated
   */
  EClass getmessageScopedRef();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.messageScopedRef#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.messageScopedRef#getComment()
   * @see #getmessageScopedRef()
   * @generated
   */
  EAttribute getmessageScopedRef_Comment();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.messageScopedRef#getScope <em>Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Scope</em>'.
   * @see org.jts.eclipse.cjsidl.messageScopedRef#getScope()
   * @see #getmessageScopedRef()
   * @generated
   */
  EReference getmessageScopedRef_Scope();

  /**
   * Returns the meta object for the reference list '{@link org.jts.eclipse.cjsidl.messageScopedRef#getScopes <em>Scopes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Scopes</em>'.
   * @see org.jts.eclipse.cjsidl.messageScopedRef#getScopes()
   * @see #getmessageScopedRef()
   * @generated
   */
  EReference getmessageScopedRef_Scopes();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.messageScopedRef#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ref</em>'.
   * @see org.jts.eclipse.cjsidl.messageScopedRef#getRef()
   * @see #getmessageScopedRef()
   * @generated
   */
  EReference getmessageScopedRef_Ref();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.messageScopedRef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.messageScopedRef#getName()
   * @see #getmessageScopedRef()
   * @generated
   */
  EAttribute getmessageScopedRef_Name();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.scopedType <em>scoped Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>scoped Type</em>'.
   * @see org.jts.eclipse.cjsidl.scopedType
   * @generated
   */
  EClass getscopedType();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.scopedType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.scopedType#getName()
   * @see #getscopedType()
   * @generated
   */
  EReference getscopedType_Name();

  /**
   * Returns the meta object for the reference list '{@link org.jts.eclipse.cjsidl.scopedType#getNames <em>Names</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Names</em>'.
   * @see org.jts.eclipse.cjsidl.scopedType#getNames()
   * @see #getscopedType()
   * @generated
   */
  EReference getscopedType_Names();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.scopedType#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.scopedType#getType()
   * @see #getscopedType()
   * @generated
   */
  EReference getscopedType_Type();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.scopedEventType <em>scoped Event Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>scoped Event Type</em>'.
   * @see org.jts.eclipse.cjsidl.scopedEventType
   * @generated
   */
  EClass getscopedEventType();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.scopedEventType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.scopedEventType#getName()
   * @see #getscopedEventType()
   * @generated
   */
  EReference getscopedEventType_Name();

  /**
   * Returns the meta object for the reference list '{@link org.jts.eclipse.cjsidl.scopedEventType#getNames <em>Names</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Names</em>'.
   * @see org.jts.eclipse.cjsidl.scopedEventType#getNames()
   * @see #getscopedEventType()
   * @generated
   */
  EReference getscopedEventType_Names();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.scopedEventType#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.scopedEventType#getType()
   * @see #getscopedEventType()
   * @generated
   */
  EReference getscopedEventType_Type();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.scopedTypeId <em>scoped Type Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>scoped Type Id</em>'.
   * @see org.jts.eclipse.cjsidl.scopedTypeId
   * @generated
   */
  EClass getscopedTypeId();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.scopedTypeId#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.jts.eclipse.cjsidl.scopedTypeId#getComment()
   * @see #getscopedTypeId()
   * @generated
   */
  EAttribute getscopedTypeId_Comment();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.scopedTypeId#getOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see org.jts.eclipse.cjsidl.scopedTypeId#getOptional()
   * @see #getscopedTypeId()
   * @generated
   */
  EAttribute getscopedTypeId_Optional();

  /**
   * Returns the meta object for the containment reference '{@link org.jts.eclipse.cjsidl.scopedTypeId#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ref</em>'.
   * @see org.jts.eclipse.cjsidl.scopedTypeId#getRef()
   * @see #getscopedTypeId()
   * @generated
   */
  EReference getscopedTypeId_Ref();

  /**
   * Returns the meta object for the attribute '{@link org.jts.eclipse.cjsidl.scopedTypeId#getScopedName <em>Scoped Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Scoped Name</em>'.
   * @see org.jts.eclipse.cjsidl.scopedTypeId#getScopedName()
   * @see #getscopedTypeId()
   * @generated
   */
  EAttribute getscopedTypeId_ScopedName();

  /**
   * Returns the meta object for class '{@link org.jts.eclipse.cjsidl.scopedConstId <em>scoped Const Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>scoped Const Id</em>'.
   * @see org.jts.eclipse.cjsidl.scopedConstId
   * @generated
   */
  EClass getscopedConstId();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.scopedConstId#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Name</em>'.
   * @see org.jts.eclipse.cjsidl.scopedConstId#getName()
   * @see #getscopedConstId()
   * @generated
   */
  EReference getscopedConstId_Name();

  /**
   * Returns the meta object for the reference list '{@link org.jts.eclipse.cjsidl.scopedConstId#getNames <em>Names</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Names</em>'.
   * @see org.jts.eclipse.cjsidl.scopedConstId#getNames()
   * @see #getscopedConstId()
   * @generated
   */
  EReference getscopedConstId_Names();

  /**
   * Returns the meta object for the reference '{@link org.jts.eclipse.cjsidl.scopedConstId#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.jts.eclipse.cjsidl.scopedConstId#getType()
   * @see #getscopedConstId()
   * @generated
   */
  EReference getscopedConstId_Type();

  /**
   * Returns the meta object for enum '{@link org.jts.eclipse.cjsidl.FIELD_FORMAT <em>FIELD FORMAT</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>FIELD FORMAT</em>'.
   * @see org.jts.eclipse.cjsidl.FIELD_FORMAT
   * @generated
   */
  EEnum getFIELD_FORMAT();

  /**
   * Returns the meta object for enum '{@link org.jts.eclipse.cjsidl.UNIT <em>UNIT</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>UNIT</em>'.
   * @see org.jts.eclipse.cjsidl.UNIT
   * @generated
   */
  EEnum getUNIT();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  CjsidlFactory getCjsidlFactory();

} //CjsidlPackage
