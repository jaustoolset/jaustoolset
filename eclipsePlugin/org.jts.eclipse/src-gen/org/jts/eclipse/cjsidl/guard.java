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
 * A representation of the model object '<em><b>guard</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.guard#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.guard#getGuardAction <em>Guard Action</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.guard#getEquiv <em>Equiv</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.guard#getLogicalOperator <em>Logical Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getguard()
 * @model
 * @generated
 */
public interface guard extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getguard_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.guard#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Guard Action</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.guardAction}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Guard Action</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Guard Action</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getguard_GuardAction()
   * @model containment="true"
   * @generated
   */
  EList<guardAction> getGuardAction();

  /**
   * Returns the value of the '<em><b>Equiv</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Equiv</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Equiv</em>' attribute.
   * @see #setEquiv(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getguard_Equiv()
   * @model
   * @generated
   */
  String getEquiv();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.guard#getEquiv <em>Equiv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Equiv</em>' attribute.
   * @see #getEquiv()
   * @generated
   */
  void setEquiv(String value);

  /**
   * Returns the value of the '<em><b>Logical Operator</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Logical Operator</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Logical Operator</em>' attribute list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getguard_LogicalOperator()
   * @model unique="false"
   * @generated
   */
  EList<String> getLogicalOperator();

} // guard
