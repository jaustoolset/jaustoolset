/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>trans Param</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.transParam#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transParam#getType <em>Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transParam#getScopedEventType <em>Scoped Event Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transParam#getUnsignedType <em>Unsigned Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.transParam#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransParam()
 * @model
 * @generated
 */
public interface transParam extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransParam_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transParam#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(EObject)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransParam_Type()
   * @model
   * @generated
   */
  EObject getType();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transParam#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(EObject value);

  /**
   * Returns the value of the '<em><b>Scoped Event Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scoped Event Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scoped Event Type</em>' containment reference.
   * @see #setScopedEventType(scopedEventType)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransParam_ScopedEventType()
   * @model containment="true"
   * @generated
   */
  scopedEventType getScopedEventType();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transParam#getScopedEventType <em>Scoped Event Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scoped Event Type</em>' containment reference.
   * @see #getScopedEventType()
   * @generated
   */
  void setScopedEventType(scopedEventType value);

  /**
   * Returns the value of the '<em><b>Unsigned Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unsigned Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unsigned Type</em>' attribute.
   * @see #setUnsignedType(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransParam_UnsignedType()
   * @model
   * @generated
   */
  String getUnsignedType();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transParam#getUnsignedType <em>Unsigned Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unsigned Type</em>' attribute.
   * @see #getUnsignedType()
   * @generated
   */
  void setUnsignedType(String value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettransParam_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.transParam#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // transParam
