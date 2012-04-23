/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>exit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.exit#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.exit#getActions <em>Actions</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.exit#getSendActions <em>Send Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getexit()
 * @model
 * @generated
 */
public interface exit extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getexit_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.exit#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getexit_Actions()
   * @model containment="true"
   * @generated
   */
  actionList getActions();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.exit#getActions <em>Actions</em>}' containment reference.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getexit_SendActions()
   * @model containment="true"
   * @generated
   */
  sendActionList getSendActions();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.exit#getSendActions <em>Send Actions</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Send Actions</em>' containment reference.
   * @see #getSendActions()
   * @generated
   */
  void setSendActions(sendActionList value);

} // exit
