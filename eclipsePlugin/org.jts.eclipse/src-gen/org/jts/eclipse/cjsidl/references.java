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
 * A representation of the model object '<em><b>references</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.references#getRefInherit <em>Ref Inherit</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.references#getRefClient <em>Ref Client</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getreferences()
 * @model
 * @generated
 */
public interface references extends EObject
{
  /**
   * Returns the value of the '<em><b>Ref Inherit</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ref Inherit</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref Inherit</em>' containment reference.
   * @see #setRefInherit(refAttr)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getreferences_RefInherit()
   * @model containment="true"
   * @generated
   */
  refAttr getRefInherit();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.references#getRefInherit <em>Ref Inherit</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ref Inherit</em>' containment reference.
   * @see #getRefInherit()
   * @generated
   */
  void setRefInherit(refAttr value);

  /**
   * Returns the value of the '<em><b>Ref Client</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.refAttr}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ref Client</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref Client</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getreferences_RefClient()
   * @model containment="true"
   * @generated
   */
  EList<refAttr> getRefClient();

} // references
