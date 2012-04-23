/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>variant Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.variantDef#getVtagComment <em>Vtag Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.variantDef#getMinCount <em>Min Count</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.variantDef#getMinCountRef <em>Min Count Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.variantDef#getMinCountScoped <em>Min Count Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.variantDef#getMaxCount <em>Max Count</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.variantDef#getMaxCountRef <em>Max Count Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.variantDef#getMaxCountScoped <em>Max Count Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.variantDef#getItemList <em>Item List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvariantDef()
 * @model
 * @generated
 */
public interface variantDef extends containerDef
{
  /**
   * Returns the value of the '<em><b>Vtag Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Vtag Comment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Vtag Comment</em>' attribute.
   * @see #setVtagComment(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvariantDef_VtagComment()
   * @model
   * @generated
   */
  String getVtagComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.variantDef#getVtagComment <em>Vtag Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Vtag Comment</em>' attribute.
   * @see #getVtagComment()
   * @generated
   */
  void setVtagComment(String value);

  /**
   * Returns the value of the '<em><b>Min Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Count</em>' attribute.
   * @see #setMinCount(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvariantDef_MinCount()
   * @model
   * @generated
   */
  String getMinCount();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.variantDef#getMinCount <em>Min Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min Count</em>' attribute.
   * @see #getMinCount()
   * @generated
   */
  void setMinCount(String value);

  /**
   * Returns the value of the '<em><b>Min Count Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min Count Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Count Ref</em>' containment reference.
   * @see #setMinCountRef(constReference)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvariantDef_MinCountRef()
   * @model containment="true"
   * @generated
   */
  constReference getMinCountRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.variantDef#getMinCountRef <em>Min Count Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min Count Ref</em>' containment reference.
   * @see #getMinCountRef()
   * @generated
   */
  void setMinCountRef(constReference value);

  /**
   * Returns the value of the '<em><b>Min Count Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min Count Scoped</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Count Scoped</em>' containment reference.
   * @see #setMinCountScoped(scopedConstId)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvariantDef_MinCountScoped()
   * @model containment="true"
   * @generated
   */
  scopedConstId getMinCountScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.variantDef#getMinCountScoped <em>Min Count Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min Count Scoped</em>' containment reference.
   * @see #getMinCountScoped()
   * @generated
   */
  void setMinCountScoped(scopedConstId value);

  /**
   * Returns the value of the '<em><b>Max Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Count</em>' attribute.
   * @see #setMaxCount(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvariantDef_MaxCount()
   * @model
   * @generated
   */
  String getMaxCount();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.variantDef#getMaxCount <em>Max Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max Count</em>' attribute.
   * @see #getMaxCount()
   * @generated
   */
  void setMaxCount(String value);

  /**
   * Returns the value of the '<em><b>Max Count Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max Count Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Count Ref</em>' containment reference.
   * @see #setMaxCountRef(constReference)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvariantDef_MaxCountRef()
   * @model containment="true"
   * @generated
   */
  constReference getMaxCountRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.variantDef#getMaxCountRef <em>Max Count Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max Count Ref</em>' containment reference.
   * @see #getMaxCountRef()
   * @generated
   */
  void setMaxCountRef(constReference value);

  /**
   * Returns the value of the '<em><b>Max Count Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max Count Scoped</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Count Scoped</em>' containment reference.
   * @see #setMaxCountScoped(scopedConstId)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvariantDef_MaxCountScoped()
   * @model containment="true"
   * @generated
   */
  scopedConstId getMaxCountScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.variantDef#getMaxCountScoped <em>Max Count Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max Count Scoped</em>' containment reference.
   * @see #getMaxCountScoped()
   * @generated
   */
  void setMaxCountScoped(scopedConstId value);

  /**
   * Returns the value of the '<em><b>Item List</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.taggedItemDef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Item List</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Item List</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvariantDef_ItemList()
   * @model containment="true"
   * @generated
   */
  EList<taggedItemDef> getItemList();

} // variantDef
