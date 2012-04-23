/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>var Len Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.varLenField#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varLenField#getOptional <em>Optional</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varLenField#getFieldFormat <em>Field Format</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varLenField#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varLenField#getCountComment <em>Count Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varLenField#getLowerLim <em>Lower Lim</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varLenField#getLowerLimRef <em>Lower Lim Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varLenField#getLowerLimScoped <em>Lower Lim Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varLenField#getUpperLim <em>Upper Lim</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varLenField#getUpperLimRef <em>Upper Lim Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.varLenField#getUpperLimScoped <em>Upper Lim Scoped</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField()
 * @model
 * @generated
 */
public interface varLenField extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varLenField#getComment <em>Comment</em>}' attribute.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField_Optional()
   * @model
   * @generated
   */
  String getOptional();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varLenField#getOptional <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Optional</em>' attribute.
   * @see #getOptional()
   * @generated
   */
  void setOptional(String value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField_FieldFormat()
   * @model
   * @generated
   */
  FIELD_FORMAT getFieldFormat();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varLenField#getFieldFormat <em>Field Format</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Field Format</em>' attribute.
   * @see org.jts.eclipse.cjsidl.FIELD_FORMAT
   * @see #getFieldFormat()
   * @generated
   */
  void setFieldFormat(FIELD_FORMAT value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varLenField#getName <em>Name</em>}' attribute.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField_CountComment()
   * @model
   * @generated
   */
  String getCountComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varLenField#getCountComment <em>Count Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Count Comment</em>' attribute.
   * @see #getCountComment()
   * @generated
   */
  void setCountComment(String value);

  /**
   * Returns the value of the '<em><b>Lower Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lower Lim</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lower Lim</em>' attribute.
   * @see #setLowerLim(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField_LowerLim()
   * @model
   * @generated
   */
  String getLowerLim();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varLenField#getLowerLim <em>Lower Lim</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lower Lim</em>' attribute.
   * @see #getLowerLim()
   * @generated
   */
  void setLowerLim(String value);

  /**
   * Returns the value of the '<em><b>Lower Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lower Lim Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lower Lim Ref</em>' containment reference.
   * @see #setLowerLimRef(constReference)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField_LowerLimRef()
   * @model containment="true"
   * @generated
   */
  constReference getLowerLimRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varLenField#getLowerLimRef <em>Lower Lim Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lower Lim Ref</em>' containment reference.
   * @see #getLowerLimRef()
   * @generated
   */
  void setLowerLimRef(constReference value);

  /**
   * Returns the value of the '<em><b>Lower Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lower Lim Scoped</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lower Lim Scoped</em>' containment reference.
   * @see #setLowerLimScoped(scopedConstId)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField_LowerLimScoped()
   * @model containment="true"
   * @generated
   */
  scopedConstId getLowerLimScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varLenField#getLowerLimScoped <em>Lower Lim Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lower Lim Scoped</em>' containment reference.
   * @see #getLowerLimScoped()
   * @generated
   */
  void setLowerLimScoped(scopedConstId value);

  /**
   * Returns the value of the '<em><b>Upper Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Upper Lim</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Upper Lim</em>' attribute.
   * @see #setUpperLim(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField_UpperLim()
   * @model
   * @generated
   */
  String getUpperLim();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varLenField#getUpperLim <em>Upper Lim</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Lim</em>' attribute.
   * @see #getUpperLim()
   * @generated
   */
  void setUpperLim(String value);

  /**
   * Returns the value of the '<em><b>Upper Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Upper Lim Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Upper Lim Ref</em>' containment reference.
   * @see #setUpperLimRef(constReference)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField_UpperLimRef()
   * @model containment="true"
   * @generated
   */
  constReference getUpperLimRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varLenField#getUpperLimRef <em>Upper Lim Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Lim Ref</em>' containment reference.
   * @see #getUpperLimRef()
   * @generated
   */
  void setUpperLimRef(constReference value);

  /**
   * Returns the value of the '<em><b>Upper Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Upper Lim Scoped</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Upper Lim Scoped</em>' containment reference.
   * @see #setUpperLimScoped(scopedConstId)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvarLenField_UpperLimScoped()
   * @model containment="true"
   * @generated
   */
  scopedConstId getUpperLimScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.varLenField#getUpperLimScoped <em>Upper Lim Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Lim Scoped</em>' containment reference.
   * @see #getUpperLimScoped()
   * @generated
   */
  void setUpperLimScoped(scopedConstId value);

} // varLenField
