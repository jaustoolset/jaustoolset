/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>push Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.pushTransition#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.pushTransition#getNextState <em>Next State</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.pushTransition#getSimpleTransition <em>Simple Transition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getpushTransition()
 * @model
 * @generated
 */
public interface pushTransition extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getpushTransition_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.pushTransition#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Next State</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Next State</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Next State</em>' containment reference.
   * @see #setNextState(nextState)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getpushTransition_NextState()
   * @model containment="true"
   * @generated
   */
  nextState getNextState();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.pushTransition#getNextState <em>Next State</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Next State</em>' containment reference.
   * @see #getNextState()
   * @generated
   */
  void setNextState(nextState value);

  /**
   * Returns the value of the '<em><b>Simple Transition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Simple Transition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Simple Transition</em>' containment reference.
   * @see #setSimpleTransition(simpleTransition)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getpushTransition_SimpleTransition()
   * @model containment="true"
   * @generated
   */
  simpleTransition getSimpleTransition();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.pushTransition#getSimpleTransition <em>Simple Transition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Simple Transition</em>' containment reference.
   * @see #getSimpleTransition()
   * @generated
   */
  void setSimpleTransition(simpleTransition value);

} // pushTransition
