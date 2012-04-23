/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>sub Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.subField#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.subField#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.subField#getFromIndex <em>From Index</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.subField#getToIndex <em>To Index</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.subField#getValueSet <em>Value Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getsubField()
 * @model
 * @generated
 */
public interface subField extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getsubField_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.subField#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getsubField_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.subField#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>From Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>From Index</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>From Index</em>' attribute.
   * @see #setFromIndex(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getsubField_FromIndex()
   * @model
   * @generated
   */
  String getFromIndex();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.subField#getFromIndex <em>From Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>From Index</em>' attribute.
   * @see #getFromIndex()
   * @generated
   */
  void setFromIndex(String value);

  /**
   * Returns the value of the '<em><b>To Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>To Index</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>To Index</em>' attribute.
   * @see #setToIndex(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getsubField_ToIndex()
   * @model
   * @generated
   */
  String getToIndex();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.subField#getToIndex <em>To Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>To Index</em>' attribute.
   * @see #getToIndex()
   * @generated
   */
  void setToIndex(String value);

  /**
   * Returns the value of the '<em><b>Value Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value Set</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value Set</em>' containment reference.
   * @see #setValueSet(valueSetDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getsubField_ValueSet()
   * @model containment="true"
   * @generated
   */
  valueSetDef getValueSet();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.subField#getValueSet <em>Value Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value Set</em>' containment reference.
   * @see #getValueSet()
   * @generated
   */
  void setValueSet(valueSetDef value);

} // subField
