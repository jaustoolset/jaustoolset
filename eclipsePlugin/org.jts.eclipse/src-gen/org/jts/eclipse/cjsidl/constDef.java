/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>const Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.constDef#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.constDef#getConstType <em>Const Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.constDef#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.constDef#getConstValue <em>Const Value</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.constDef#getFieldUnits <em>Field Units</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getconstDef()
 * @model
 * @generated
 */
public interface constDef extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getconstDef_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.constDef#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Const Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Const Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Const Type</em>' containment reference.
   * @see #setConstType(simpleNumericType)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getconstDef_ConstType()
   * @model containment="true"
   * @generated
   */
  simpleNumericType getConstType();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.constDef#getConstType <em>Const Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Const Type</em>' containment reference.
   * @see #getConstType()
   * @generated
   */
  void setConstType(simpleNumericType value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getconstDef_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.constDef#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Const Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Const Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Const Value</em>' attribute.
   * @see #setConstValue(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getconstDef_ConstValue()
   * @model
   * @generated
   */
  String getConstValue();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.constDef#getConstValue <em>Const Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Const Value</em>' attribute.
   * @see #getConstValue()
   * @generated
   */
  void setConstValue(String value);

  /**
   * Returns the value of the '<em><b>Field Units</b></em>' attribute.
   * The literals are from the enumeration {@link org.jts.eclipse.cjsidl.UNIT}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Field Units</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Field Units</em>' attribute.
   * @see org.jts.eclipse.cjsidl.UNIT
   * @see #setFieldUnits(UNIT)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getconstDef_FieldUnits()
   * @model
   * @generated
   */
  UNIT getFieldUnits();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.constDef#getFieldUnits <em>Field Units</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Field Units</em>' attribute.
   * @see org.jts.eclipse.cjsidl.UNIT
   * @see #getFieldUnits()
   * @generated
   */
  void setFieldUnits(UNIT value);

} // constDef
