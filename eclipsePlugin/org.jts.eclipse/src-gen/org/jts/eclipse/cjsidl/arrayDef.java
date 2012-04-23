/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>array Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.arrayDef#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.arrayDef#getOptional <em>Optional</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.arrayDef#getTypeRef <em>Type Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.arrayDef#getScopedType <em>Scoped Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.arrayDef#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.arrayDef#getArraySize <em>Array Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getarrayDef()
 * @model
 * @generated
 */
public interface arrayDef extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getarrayDef_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.arrayDef#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Optional</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Optional</em>' attribute.
   * @see #setOptional(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getarrayDef_Optional()
   * @model
   * @generated
   */
  String getOptional();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.arrayDef#getOptional <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Optional</em>' attribute.
   * @see #getOptional()
   * @generated
   */
  void setOptional(String value);

  /**
   * Returns the value of the '<em><b>Type Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Ref</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Ref</em>' reference.
   * @see #setTypeRef(EObject)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getarrayDef_TypeRef()
   * @model
   * @generated
   */
  EObject getTypeRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.arrayDef#getTypeRef <em>Type Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Ref</em>' reference.
   * @see #getTypeRef()
   * @generated
   */
  void setTypeRef(EObject value);

  /**
   * Returns the value of the '<em><b>Scoped Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scoped Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scoped Type</em>' containment reference.
   * @see #setScopedType(scopedType)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getarrayDef_ScopedType()
   * @model containment="true"
   * @generated
   */
  scopedType getScopedType();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.arrayDef#getScopedType <em>Scoped Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scoped Type</em>' containment reference.
   * @see #getScopedType()
   * @generated
   */
  void setScopedType(scopedType value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getarrayDef_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.arrayDef#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Array Size</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array Size</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array Size</em>' attribute.
   * @see #setArraySize(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getarrayDef_ArraySize()
   * @model
   * @generated
   */
  String getArraySize();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.arrayDef#getArraySize <em>Array Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array Size</em>' attribute.
   * @see #getArraySize()
   * @generated
   */
  void setArraySize(String value);

} // arrayDef
