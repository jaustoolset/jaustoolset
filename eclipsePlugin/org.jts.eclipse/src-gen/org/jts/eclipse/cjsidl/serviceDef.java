/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>service Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.serviceDef#getServiceName <em>Service Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.serviceDef#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.serviceDef#getServiceVersion <em>Service Version</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.serviceDef#getDescr <em>Descr</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.serviceDef#getAssumpt <em>Assumpt</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.serviceDef#getRefs <em>Refs</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.serviceDef#getConstSet <em>Const Set</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.serviceDef#getTypeSet <em>Type Set</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.serviceDef#getMessageSet <em>Message Set</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.serviceDef#getInternalEventSet <em>Internal Event Set</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.serviceDef#getProtocolBehavior <em>Protocol Behavior</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef()
 * @model
 * @generated
 */
public interface serviceDef extends EObject
{
  /**
   * Returns the value of the '<em><b>Service Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Service Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Service Name</em>' attribute.
   * @see #setServiceName(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef_ServiceName()
   * @model
   * @generated
   */
  String getServiceName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.serviceDef#getServiceName <em>Service Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Service Name</em>' attribute.
   * @see #getServiceName()
   * @generated
   */
  void setServiceName(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.serviceDef#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Service Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Service Version</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Service Version</em>' attribute.
   * @see #setServiceVersion(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef_ServiceVersion()
   * @model
   * @generated
   */
  String getServiceVersion();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.serviceDef#getServiceVersion <em>Service Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Service Version</em>' attribute.
   * @see #getServiceVersion()
   * @generated
   */
  void setServiceVersion(String value);

  /**
   * Returns the value of the '<em><b>Descr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Descr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Descr</em>' containment reference.
   * @see #setDescr(description)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef_Descr()
   * @model containment="true"
   * @generated
   */
  description getDescr();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.serviceDef#getDescr <em>Descr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Descr</em>' containment reference.
   * @see #getDescr()
   * @generated
   */
  void setDescr(description value);

  /**
   * Returns the value of the '<em><b>Assumpt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Assumpt</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Assumpt</em>' attribute.
   * @see #setAssumpt(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef_Assumpt()
   * @model
   * @generated
   */
  String getAssumpt();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.serviceDef#getAssumpt <em>Assumpt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Assumpt</em>' attribute.
   * @see #getAssumpt()
   * @generated
   */
  void setAssumpt(String value);

  /**
   * Returns the value of the '<em><b>Refs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Refs</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Refs</em>' containment reference.
   * @see #setRefs(references)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef_Refs()
   * @model containment="true"
   * @generated
   */
  references getRefs();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.serviceDef#getRefs <em>Refs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Refs</em>' containment reference.
   * @see #getRefs()
   * @generated
   */
  void setRefs(references value);

  /**
   * Returns the value of the '<em><b>Const Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Const Set</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Const Set</em>' containment reference.
   * @see #setConstSet(declaredConstSet)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef_ConstSet()
   * @model containment="true"
   * @generated
   */
  declaredConstSet getConstSet();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.serviceDef#getConstSet <em>Const Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Const Set</em>' containment reference.
   * @see #getConstSet()
   * @generated
   */
  void setConstSet(declaredConstSet value);

  /**
   * Returns the value of the '<em><b>Type Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Set</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Set</em>' containment reference.
   * @see #setTypeSet(declaredTypeSet)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef_TypeSet()
   * @model containment="true"
   * @generated
   */
  declaredTypeSet getTypeSet();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.serviceDef#getTypeSet <em>Type Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Set</em>' containment reference.
   * @see #getTypeSet()
   * @generated
   */
  void setTypeSet(declaredTypeSet value);

  /**
   * Returns the value of the '<em><b>Message Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message Set</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message Set</em>' containment reference.
   * @see #setMessageSet(messageSet)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef_MessageSet()
   * @model containment="true"
   * @generated
   */
  messageSet getMessageSet();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.serviceDef#getMessageSet <em>Message Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message Set</em>' containment reference.
   * @see #getMessageSet()
   * @generated
   */
  void setMessageSet(messageSet value);

  /**
   * Returns the value of the '<em><b>Internal Event Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Internal Event Set</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Internal Event Set</em>' containment reference.
   * @see #setInternalEventSet(internalEventSet)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef_InternalEventSet()
   * @model containment="true"
   * @generated
   */
  internalEventSet getInternalEventSet();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.serviceDef#getInternalEventSet <em>Internal Event Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Internal Event Set</em>' containment reference.
   * @see #getInternalEventSet()
   * @generated
   */
  void setInternalEventSet(internalEventSet value);

  /**
   * Returns the value of the '<em><b>Protocol Behavior</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Protocol Behavior</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Protocol Behavior</em>' containment reference.
   * @see #setProtocolBehavior(protocolBehavior)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getserviceDef_ProtocolBehavior()
   * @model containment="true"
   * @generated
   */
  protocolBehavior getProtocolBehavior();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.serviceDef#getProtocolBehavior <em>Protocol Behavior</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Protocol Behavior</em>' containment reference.
   * @see #getProtocolBehavior()
   * @generated
   */
  void setProtocolBehavior(protocolBehavior value);

} // serviceDef
