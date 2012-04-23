/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>fixed Field Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedFieldDef#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedFieldDef#getOptional <em>Optional</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedFieldDef#getType <em>Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedFieldDef#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedFieldDef#getFieldUnit <em>Field Unit</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedFieldDef#getValueRange <em>Value Range</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedFieldDef()
 * @model
 * @generated
 */
public interface fixedFieldDef extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedFieldDef_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getComment <em>Comment</em>}' attribute.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedFieldDef_Optional()
   * @model
   * @generated
   */
  String getOptional();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getOptional <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Optional</em>' attribute.
   * @see #getOptional()
   * @generated
   */
  void setOptional(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(simpleNumericType)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedFieldDef_Type()
   * @model containment="true"
   * @generated
   */
  simpleNumericType getType();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(simpleNumericType value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedFieldDef_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Field Unit</b></em>' attribute.
   * The literals are from the enumeration {@link org.jts.eclipse.cjsidl.UNIT}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Field Unit</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Field Unit</em>' attribute.
   * @see org.jts.eclipse.cjsidl.UNIT
   * @see #setFieldUnit(UNIT)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedFieldDef_FieldUnit()
   * @model
   * @generated
   */
  UNIT getFieldUnit();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getFieldUnit <em>Field Unit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Field Unit</em>' attribute.
   * @see org.jts.eclipse.cjsidl.UNIT
   * @see #getFieldUnit()
   * @generated
   */
  void setFieldUnit(UNIT value);

  /**
   * Returns the value of the '<em><b>Value Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value Range</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value Range</em>' containment reference.
   * @see #setValueRange(EObject)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedFieldDef_ValueRange()
   * @model containment="true"
   * @generated
   */
  EObject getValueRange();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedFieldDef#getValueRange <em>Value Range</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value Range</em>' containment reference.
   * @see #getValueRange()
   * @generated
   */
  void setValueRange(EObject value);

} // fixedFieldDef
