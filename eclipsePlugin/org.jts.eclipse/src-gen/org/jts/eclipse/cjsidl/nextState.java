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
 * A representation of the model object '<em><b>next State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.nextState#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.nextState#getFirstState <em>First State</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.nextState#getScoped <em>Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.nextState#getNextState <em>Next State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getnextState()
 * @model
 * @generated
 */
public interface nextState extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getnextState_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.nextState#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>First State</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>First State</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>First State</em>' reference.
   * @see #setFirstState(state)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getnextState_FirstState()
   * @model
   * @generated
   */
  state getFirstState();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.nextState#getFirstState <em>First State</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>First State</em>' reference.
   * @see #getFirstState()
   * @generated
   */
  void setFirstState(state value);

  /**
   * Returns the value of the '<em><b>Scoped</b></em>' reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.state}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scoped</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scoped</em>' reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getnextState_Scoped()
   * @model
   * @generated
   */
  EList<state> getScoped();

  /**
   * Returns the value of the '<em><b>Next State</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Next State</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Next State</em>' reference.
   * @see #setNextState(state)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getnextState_NextState()
   * @model
   * @generated
   */
  state getNextState();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.nextState#getNextState <em>Next State</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Next State</em>' reference.
   * @see #getNextState()
   * @generated
   */
  void setNextState(state value);

} // nextState
