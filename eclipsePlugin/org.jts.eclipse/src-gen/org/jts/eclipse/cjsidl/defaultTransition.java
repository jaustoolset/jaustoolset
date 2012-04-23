/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>default Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.defaultTransition#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.defaultTransition#getType <em>Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.defaultTransition#getTransGuard <em>Trans Guard</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.defaultTransition#getActions <em>Actions</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.defaultTransition#getSendActions <em>Send Actions</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.defaultTransition#getDestination <em>Destination</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdefaultTransition()
 * @model
 * @generated
 */
public interface defaultTransition extends EObject
{
  /**
   * Returns the value of the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comment</em>' attribute.
   * @see #setComment(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdefaultTransition_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.defaultTransition#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see #setType(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdefaultTransition_Type()
   * @model
   * @generated
   */
  String getType();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.defaultTransition#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see #getType()
   * @generated
   */
  void setType(String value);

  /**
   * Returns the value of the '<em><b>Trans Guard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Trans Guard</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Trans Guard</em>' containment reference.
   * @see #setTransGuard(guard)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdefaultTransition_TransGuard()
   * @model containment="true"
   * @generated
   */
  guard getTransGuard();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.defaultTransition#getTransGuard <em>Trans Guard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Trans Guard</em>' containment reference.
   * @see #getTransGuard()
   * @generated
   */
  void setTransGuard(guard value);

  /**
   * Returns the value of the '<em><b>Actions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Actions</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Actions</em>' containment reference.
   * @see #setActions(actionList)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdefaultTransition_Actions()
   * @model containment="true"
   * @generated
   */
  actionList getActions();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.defaultTransition#getActions <em>Actions</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Actions</em>' containment reference.
   * @see #getActions()
   * @generated
   */
  void setActions(actionList value);

  /**
   * Returns the value of the '<em><b>Send Actions</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Send Actions</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Send Actions</em>' containment reference.
   * @see #setSendActions(sendActionList)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdefaultTransition_SendActions()
   * @model containment="true"
   * @generated
   */
  sendActionList getSendActions();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.defaultTransition#getSendActions <em>Send Actions</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Send Actions</em>' containment reference.
   * @see #getSendActions()
   * @generated
   */
  void setSendActions(sendActionList value);

  /**
   * Returns the value of the '<em><b>Destination</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Destination</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Destination</em>' containment reference.
   * @see #setDestination(EObject)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdefaultTransition_Destination()
   * @model containment="true"
   * @generated
   */
  EObject getDestination();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.defaultTransition#getDestination <em>Destination</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Destination</em>' containment reference.
   * @see #getDestination()
   * @generated
   */
  void setDestination(EObject value);

} // defaultTransition
