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
 * A representation of the model object '<em><b>protocol Behavior</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.protocolBehavior#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.protocolBehavior#getStateless <em>Stateless</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.protocolBehavior#getStateMachine <em>State Machine</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getprotocolBehavior()
 * @model
 * @generated
 */
public interface protocolBehavior extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getprotocolBehavior_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.protocolBehavior#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Stateless</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Stateless</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Stateless</em>' attribute.
   * @see #setStateless(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getprotocolBehavior_Stateless()
   * @model
   * @generated
   */
  String getStateless();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.protocolBehavior#getStateless <em>Stateless</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Stateless</em>' attribute.
   * @see #getStateless()
   * @generated
   */
  void setStateless(String value);

  /**
   * Returns the value of the '<em><b>State Machine</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.stateMachine}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>State Machine</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>State Machine</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getprotocolBehavior_StateMachine()
   * @model containment="true"
   * @generated
   */
  EList<stateMachine> getStateMachine();

} // protocolBehavior
