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
 * A representation of the model object '<em><b>var Format Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.varFormatField#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varFormatField#getOptional <em>Optional</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varFormatField#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varFormatField#getCountComment <em>Count Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varFormatField#getUnits <em>Units</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varFormatField#getCountRange <em>Count Range</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varFormatField#getFormatField <em>Format Field</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarFormatField()
 * @model
 * @generated
 */
public interface varFormatField extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarFormatField_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varFormatField#getComment <em>Comment</em>}' attribute.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarFormatField_Optional()
   * @model
   * @generated
   */
  String getOptional();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varFormatField#getOptional <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Optional</em>' attribute.
   * @see #getOptional()
   * @generated
   */
  void setOptional(String value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarFormatField_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varFormatField#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Count Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Count Comment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Count Comment</em>' attribute.
   * @see #setCountComment(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarFormatField_CountComment()
   * @model
   * @generated
   */
  String getCountComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varFormatField#getCountComment <em>Count Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Count Comment</em>' attribute.
   * @see #getCountComment()
   * @generated
   */
  void setCountComment(String value);

  /**
   * Returns the value of the '<em><b>Units</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Units</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Units</em>' attribute.
   * @see #setUnits(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarFormatField_Units()
   * @model
   * @generated
   */
  String getUnits();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varFormatField#getUnits <em>Units</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Units</em>' attribute.
   * @see #getUnits()
   * @generated
   */
  void setUnits(String value);

  /**
   * Returns the value of the '<em><b>Count Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Count Range</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Count Range</em>' containment reference.
   * @see #setCountRange(valueRange)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarFormatField_CountRange()
   * @model containment="true"
   * @generated
   */
  valueRange getCountRange();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varFormatField#getCountRange <em>Count Range</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Count Range</em>' containment reference.
   * @see #getCountRange()
   * @generated
   */
  void setCountRange(valueRange value);

  /**
   * Returns the value of the '<em><b>Format Field</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.formatEnumDef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Format Field</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Format Field</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarFormatField_FormatField()
   * @model containment="true"
   * @generated
   */
  EList<formatEnumDef> getFormatField();

} // varFormatField
