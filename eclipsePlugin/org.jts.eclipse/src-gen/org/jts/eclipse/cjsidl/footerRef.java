/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>footer Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.footerRef#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.footerRef#getTypeRef <em>Type Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.footerRef#getScopedRef <em>Scoped Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.footerRef#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfooterRef()
 * @model
 * @generated
 */
public interface footerRef extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfooterRef_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.footerRef#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Type Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Ref</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Ref</em>' reference.
   * @see #setTypeRef(footerDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfooterRef_TypeRef()
   * @model
   * @generated
   */
  footerDef getTypeRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.footerRef#getTypeRef <em>Type Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Ref</em>' reference.
   * @see #getTypeRef()
   * @generated
   */
  void setTypeRef(footerDef value);

  /**
   * Returns the value of the '<em><b>Scoped Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scoped Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scoped Ref</em>' containment reference.
   * @see #setScopedRef(footerScopedRef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfooterRef_ScopedRef()
   * @model containment="true"
   * @generated
   */
  footerScopedRef getScopedRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.footerRef#getScopedRef <em>Scoped Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scoped Ref</em>' containment reference.
   * @see #getScopedRef()
   * @generated
   */
  void setScopedRef(footerScopedRef value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfooterRef_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.footerRef#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // footerRef
