/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>record Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.recordDef#getArrayDef <em>Array Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.recordDef#getFieldDef <em>Field Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.recordDef#getVariableFieldDef <em>Variable Field Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.recordDef#getBitfieldDef <em>Bitfield Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.recordDef#getFixedLengthStringDef <em>Fixed Length String Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.recordDef#getVariableLengthStringDef <em>Variable Length String Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.recordDef#getVariableLengthFieldDef <em>Variable Length Field Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.recordDef#getVarFormatField <em>Var Format Field</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.recordDef#getTypeReference <em>Type Reference</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.recordDef#getScopedRef <em>Scoped Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getrecordDef()
 * @model
 * @generated
 */
public interface recordDef extends containerDef
{
  /**
   * Returns the value of the '<em><b>Array Def</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.arrayDef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array Def</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array Def</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getrecordDef_ArrayDef()
   * @model containment="true"
   * @generated
   */
  EList<arrayDef> getArrayDef();

  /**
   * Returns the value of the '<em><b>Field Def</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.fixedFieldDef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Field Def</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Field Def</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getrecordDef_FieldDef()
   * @model containment="true"
   * @generated
   */
  EList<fixedFieldDef> getFieldDef();

  /**
   * Returns the value of the '<em><b>Variable Field Def</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.varField}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable Field Def</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable Field Def</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getrecordDef_VariableFieldDef()
   * @model containment="true"
   * @generated
   */
  EList<varField> getVariableFieldDef();

  /**
   * Returns the value of the '<em><b>Bitfield Def</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.bitfieldDef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bitfield Def</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bitfield Def</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getrecordDef_BitfieldDef()
   * @model containment="true"
   * @generated
   */
  EList<bitfieldDef> getBitfieldDef();

  /**
   * Returns the value of the '<em><b>Fixed Length String Def</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.fixedLenString}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fixed Length String Def</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fixed Length String Def</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getrecordDef_FixedLengthStringDef()
   * @model containment="true"
   * @generated
   */
  EList<fixedLenString> getFixedLengthStringDef();

  /**
   * Returns the value of the '<em><b>Variable Length String Def</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.varLenString}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable Length String Def</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable Length String Def</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getrecordDef_VariableLengthStringDef()
   * @model containment="true"
   * @generated
   */
  EList<varLenString> getVariableLengthStringDef();

  /**
   * Returns the value of the '<em><b>Variable Length Field Def</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.varLenField}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable Length Field Def</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable Length Field Def</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getrecordDef_VariableLengthFieldDef()
   * @model containment="true"
   * @generated
   */
  EList<varLenField> getVariableLengthFieldDef();

  /**
   * Returns the value of the '<em><b>Var Format Field</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.varFormatField}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var Format Field</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var Format Field</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getrecordDef_VarFormatField()
   * @model containment="true"
   * @generated
   */
  EList<varFormatField> getVarFormatField();

  /**
   * Returns the value of the '<em><b>Type Reference</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.typeReference}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Reference</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Reference</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getrecordDef_TypeReference()
   * @model containment="true"
   * @generated
   */
  EList<typeReference> getTypeReference();

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getrecordDef_ScopedRef()
   * @model containment="true"
   * @generated
   */
  EList<scopedTypeId> getScopedRef();

} // recordDef
