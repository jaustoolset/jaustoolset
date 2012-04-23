/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.transition#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transition#getType <em>Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transition#getScoped <em>Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transition#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transition#getParams <em>Params</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transition#getTransGuard <em>Trans Guard</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transition#getActions <em>Actions</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transition#getSendActions <em>Send Actions</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transition#getDestination <em>Destination</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransition()
 * @model
 * @generated
 */
public interface transition extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransition_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transition#getComment <em>Comment</em>}' attribute.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransition_Type()
   * @model
   * @generated
   */
  String getType();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transition#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see #getType()
   * @generated
   */
  void setType(String value);

  /**
   * Returns the value of the '<em><b>Scoped</b></em>' reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.refAttr}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scoped</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scoped</em>' reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransition_Scoped()
   * @model
   * @generated
   */
  EList<refAttr> getScoped();

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransition_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transition#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Params</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Params</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params</em>' containment reference.
   * @see #setParams(transParams)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransition_Params()
   * @model containment="true"
   * @generated
   */
  transParams getParams();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transition#getParams <em>Params</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Params</em>' containment reference.
   * @see #getParams()
   * @generated
   */
  void setParams(transParams value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransition_TransGuard()
   * @model containment="true"
   * @generated
   */
  guard getTransGuard();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transition#getTransGuard <em>Trans Guard</em>}' containment reference.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransition_Actions()
   * @model containment="true"
   * @generated
   */
  actionList getActions();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transition#getActions <em>Actions</em>}' containment reference.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransition_SendActions()
   * @model containment="true"
   * @generated
   */
  sendActionList getSendActions();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transition#getSendActions <em>Send Actions</em>}' containment reference.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransition_Destination()
   * @model containment="true"
   * @generated
   */
  EObject getDestination();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transition#getDestination <em>Destination</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Destination</em>' containment reference.
   * @see #getDestination()
   * @generated
   */
  void setDestination(EObject value);

} // transition
