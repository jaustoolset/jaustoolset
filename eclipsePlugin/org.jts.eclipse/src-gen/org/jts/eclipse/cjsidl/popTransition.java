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
 * A representation of the model object '<em><b>pop Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.popTransition#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.popTransition#getSecondaryTransition <em>Secondary Transition</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.popTransition#getParam <em>Param</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getpopTransition()
 * @model
 * @generated
 */
public interface popTransition extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getpopTransition_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.popTransition#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Secondary Transition</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Secondary Transition</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Secondary Transition</em>' reference.
   * @see #setSecondaryTransition(transition)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getpopTransition_SecondaryTransition()
   * @model
   * @generated
   */
  transition getSecondaryTransition();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.popTransition#getSecondaryTransition <em>Secondary Transition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Secondary Transition</em>' reference.
   * @see #getSecondaryTransition()
   * @generated
   */
  void setSecondaryTransition(transition value);

  /**
   * Returns the value of the '<em><b>Param</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.guardParam}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Param</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Param</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getpopTransition_Param()
   * @model containment="true"
   * @generated
   */
  EList<guardParam> getParam();

} // popTransition
