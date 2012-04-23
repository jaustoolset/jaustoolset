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
 * A representation of the model object '<em><b>declared Type Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredTypeSet#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredTypeSet#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredTypeSet#getVersion <em>Version</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredTypeSet#getDeclaredConstSetRef <em>Declared Const Set Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredTypeSet#getDeclaredTypeSetRef <em>Declared Type Set Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredTypeSet#getTypeDef <em>Type Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredTypeSet#getTypeRef <em>Type Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredTypeSet#getScopedRef <em>Scoped Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredTypeSet()
 * @model
 * @generated
 */
public interface declaredTypeSet extends EObject
{
  /**
   * Returns the value of the '<em><b>Type Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Name</em>' attribute.
   * @see #setTypeName(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredTypeSet_TypeName()
   * @model
   * @generated
   */
  String getTypeName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.declaredTypeSet#getTypeName <em>Type Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Name</em>' attribute.
   * @see #getTypeName()
   * @generated
   */
  void setTypeName(String value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredTypeSet_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.declaredTypeSet#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Version</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Version</em>' attribute.
   * @see #setVersion(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredTypeSet_Version()
   * @model
   * @generated
   */
  String getVersion();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.declaredTypeSet#getVersion <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Version</em>' attribute.
   * @see #getVersion()
   * @generated
   */
  void setVersion(String value);

  /**
   * Returns the value of the '<em><b>Declared Const Set Ref</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.declaredConstSetRef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Declared Const Set Ref</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Declared Const Set Ref</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredTypeSet_DeclaredConstSetRef()
   * @model containment="true"
   * @generated
   */
  EList<declaredConstSetRef> getDeclaredConstSetRef();

  /**
   * Returns the value of the '<em><b>Declared Type Set Ref</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.declaredTypeSetRef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Declared Type Set Ref</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Declared Type Set Ref</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredTypeSet_DeclaredTypeSetRef()
   * @model containment="true"
   * @generated
   */
  EList<declaredTypeSetRef> getDeclaredTypeSetRef();

  /**
   * Returns the value of the '<em><b>Type Def</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.typeDef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Def</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Def</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredTypeSet_TypeDef()
   * @model containment="true"
   * @generated
   */
  EList<typeDef> getTypeDef();

  /**
   * Returns the value of the '<em><b>Type Ref</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.typeReference}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Ref</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Ref</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredTypeSet_TypeRef()
   * @model containment="true"
   * @generated
   */
  EList<typeReference> getTypeRef();

  /**
   * Returns the value of the '<em><b>Scoped Ref</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.scopedTypeId}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scoped Ref</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scoped Ref</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredTypeSet_ScopedRef()
   * @model containment="true"
   * @generated
   */
  EList<scopedTypeId> getScopedRef();

} // declaredTypeSet
