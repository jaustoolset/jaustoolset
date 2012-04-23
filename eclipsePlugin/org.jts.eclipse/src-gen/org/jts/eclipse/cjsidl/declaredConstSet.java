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
 * A representation of the model object '<em><b>declared Const Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredConstSet#getConstName <em>Const Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredConstSet#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredConstSet#getConstSetVersion <em>Const Set Version</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredConstSet#getDeclaredConstSetRef <em>Declared Const Set Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.declaredConstSet#getConstDef <em>Const Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredConstSet()
 * @model
 * @generated
 */
public interface declaredConstSet extends EObject
{
  /**
   * Returns the value of the '<em><b>Const Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Const Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Const Name</em>' attribute.
   * @see #setConstName(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredConstSet_ConstName()
   * @model
   * @generated
   */
  String getConstName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.declaredConstSet#getConstName <em>Const Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Const Name</em>' attribute.
   * @see #getConstName()
   * @generated
   */
  void setConstName(String value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredConstSet_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.declaredConstSet#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Const Set Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Const Set Version</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Const Set Version</em>' attribute.
   * @see #setConstSetVersion(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredConstSet_ConstSetVersion()
   * @model
   * @generated
   */
  String getConstSetVersion();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.declaredConstSet#getConstSetVersion <em>Const Set Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Const Set Version</em>' attribute.
   * @see #getConstSetVersion()
   * @generated
   */
  void setConstSetVersion(String value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredConstSet_DeclaredConstSetRef()
   * @model containment="true"
   * @generated
   */
  EList<declaredConstSetRef> getDeclaredConstSetRef();

  /**
   * Returns the value of the '<em><b>Const Def</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.constDef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Const Def</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Const Def</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getdeclaredConstSet_ConstDef()
   * @model containment="true"
   * @generated
   */
  EList<constDef> getConstDef();

} // declaredConstSet
