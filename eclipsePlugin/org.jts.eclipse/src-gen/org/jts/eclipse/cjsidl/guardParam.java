/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>guard Param</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.guardParam#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.guardParam#getGuardConst <em>Guard Const</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getguardParam()
 * @model
 * @generated
 */
public interface guardParam extends EObject
{
  /**
   * Returns the value of the '<em><b>Parameter</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter</em>' reference.
   * @see #setParameter(transParam)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getguardParam_Parameter()
   * @model
   * @generated
   */
  transParam getParameter();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.guardParam#getParameter <em>Parameter</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter</em>' reference.
   * @see #getParameter()
   * @generated
   */
  void setParameter(transParam value);

  /**
   * Returns the value of the '<em><b>Guard Const</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Guard Const</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Guard Const</em>' attribute.
   * @see #setGuardConst(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getguardParam_GuardConst()
   * @model
   * @generated
   */
  String getGuardConst();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.guardParam#getGuardConst <em>Guard Const</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Guard Const</em>' attribute.
   * @see #getGuardConst()
   * @generated
   */
  void setGuardConst(String value);

} // guardParam
