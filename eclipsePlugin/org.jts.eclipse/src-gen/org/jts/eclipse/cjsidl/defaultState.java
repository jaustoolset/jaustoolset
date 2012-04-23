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
 * A representation of the model object '<em><b>default State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.defaultState#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.defaultState#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.defaultState#getDefaultTransition <em>Default Transition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdefaultState()
 * @model
 * @generated
 */
public interface defaultState extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdefaultState_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.defaultState#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Transition</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.transition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Transition</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transition</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdefaultState_Transition()
   * @model containment="true"
   * @generated
   */
  EList<transition> getTransition();

  /**
   * Returns the value of the '<em><b>Default Transition</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.defaultTransition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default Transition</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default Transition</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdefaultState_DefaultTransition()
   * @model containment="true"
   * @generated
   */
  EList<defaultTransition> getDefaultTransition();

} // defaultState
