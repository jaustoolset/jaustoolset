/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>fixed Len String</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedLenString#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedLenString#getOptional <em>Optional</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedLenString#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedLenString#getUpperLim <em>Upper Lim</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedLenString#getUpperLimRef <em>Upper Lim Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.fixedLenString#getUpperLimScoped <em>Upper Lim Scoped</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedLenString()
 * @model
 * @generated
 */
public interface fixedLenString extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedLenString_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedLenString#getComment <em>Comment</em>}' attribute.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedLenString_Optional()
   * @model
   * @generated
   */
  String getOptional();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedLenString#getOptional <em>Optional</em>}' attribute.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedLenString_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedLenString#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedLenString_UpperLim()
   * @model
   * @generated
   */
  String getUpperLim();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedLenString#getUpperLim <em>Upper Lim</em>}' attribute.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedLenString_UpperLimRef()
   * @model containment="true"
   * @generated
   */
  constReference getUpperLimRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedLenString#getUpperLimRef <em>Upper Lim Ref</em>}' containment reference.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getfixedLenString_UpperLimScoped()
   * @model containment="true"
   * @generated
   */
  scopedConstId getUpperLimScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.fixedLenString#getUpperLimScoped <em>Upper Lim Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Lim Scoped</em>' containment reference.
   * @see #getUpperLimScoped()
   * @generated
   */
  void setUpperLimScoped(scopedConstId value);

} // fixedLenString
