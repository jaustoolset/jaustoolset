/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>format Enum Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.formatEnumDef#getIndex <em>Index</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.formatEnumDef#getConstRef <em>Const Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.formatEnumDef#getConstScopedRef <em>Const Scoped Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.formatEnumDef#getFieldFormat <em>Field Format</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.formatEnumDef#getFieldFormatStr <em>Field Format Str</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getformatEnumDef()
 * @model
 * @generated
 */
public interface formatEnumDef extends EObject
{
  /**
   * Returns the value of the '<em><b>Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Index</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Index</em>' attribute.
   * @see #setIndex(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getformatEnumDef_Index()
   * @model
   * @generated
   */
  String getIndex();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.formatEnumDef#getIndex <em>Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Index</em>' attribute.
   * @see #getIndex()
   * @generated
   */
  void setIndex(String value);

  /**
   * Returns the value of the '<em><b>Const Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Const Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Const Ref</em>' containment reference.
   * @see #setConstRef(constReference)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getformatEnumDef_ConstRef()
   * @model containment="true"
   * @generated
   */
  constReference getConstRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.formatEnumDef#getConstRef <em>Const Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Const Ref</em>' containment reference.
   * @see #getConstRef()
   * @generated
   */
  void setConstRef(constReference value);

  /**
   * Returns the value of the '<em><b>Const Scoped Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Const Scoped Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Const Scoped Ref</em>' containment reference.
   * @see #setConstScopedRef(scopedConstId)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getformatEnumDef_ConstScopedRef()
   * @model containment="true"
   * @generated
   */
  scopedConstId getConstScopedRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.formatEnumDef#getConstScopedRef <em>Const Scoped Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Const Scoped Ref</em>' containment reference.
   * @see #getConstScopedRef()
   * @generated
   */
  void setConstScopedRef(scopedConstId value);

  /**
   * Returns the value of the '<em><b>Field Format</b></em>' attribute.
   * The literals are from the enumeration {@link org.jts.eclipse.cjsidl.FIELD_FORMAT}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Field Format</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Field Format</em>' attribute.
   * @see org.jts.eclipse.cjsidl.FIELD_FORMAT
   * @see #setFieldFormat(FIELD_FORMAT)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getformatEnumDef_FieldFormat()
   * @model
   * @generated
   */
  FIELD_FORMAT getFieldFormat();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.formatEnumDef#getFieldFormat <em>Field Format</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Field Format</em>' attribute.
   * @see org.jts.eclipse.cjsidl.FIELD_FORMAT
   * @see #getFieldFormat()
   * @generated
   */
  void setFieldFormat(FIELD_FORMAT value);

  /**
   * Returns the value of the '<em><b>Field Format Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Field Format Str</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Field Format Str</em>' attribute.
   * @see #setFieldFormatStr(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getformatEnumDef_FieldFormatStr()
   * @model
   * @generated
   */
  String getFieldFormatStr();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.formatEnumDef#getFieldFormatStr <em>Field Format Str</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Field Format Str</em>' attribute.
   * @see #getFieldFormatStr()
   * @generated
   */
  void setFieldFormatStr(String value);

} // formatEnumDef
