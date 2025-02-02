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
 * A representation of the model object '<em><b>body Scoped Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.bodyScopedRef#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.bodyScopedRef#getNames <em>Names</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.bodyScopedRef#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getbodyScopedRef()
 * @model
 * @generated
 */
public interface bodyScopedRef extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' reference.
   * @see #setName(EObject)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getbodyScopedRef_Name()
   * @model
   * @generated
   */
  EObject getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.bodyScopedRef#getName <em>Name</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' reference.
   * @see #getName()
   * @generated
   */
  void setName(EObject value);

  /**
   * Returns the value of the '<em><b>Names</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Names</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Names</em>' reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getbodyScopedRef_Names()
   * @model
   * @generated
   */
  EList<EObject> getNames();

  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(bodyDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getbodyScopedRef_Type()
   * @model
   * @generated
   */
  bodyDef getType();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.bodyScopedRef#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(bodyDef value);

} // bodyScopedRef
