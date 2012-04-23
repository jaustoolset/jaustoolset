/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>container Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.containerRef#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.containerRef#getOptional <em>Optional</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.containerRef#getType <em>Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.containerRef#getTypeScoped <em>Type Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.containerRef#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getcontainerRef()
 * @model
 * @generated
 */
public interface containerRef extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getcontainerRef_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.containerRef#getComment <em>Comment</em>}' attribute.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getcontainerRef_Optional()
   * @model
   * @generated
   */
  String getOptional();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.containerRef#getOptional <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Optional</em>' attribute.
   * @see #getOptional()
   * @generated
   */
  void setOptional(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(containerDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getcontainerRef_Type()
   * @model
   * @generated
   */
  containerDef getType();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.containerRef#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(containerDef value);

  /**
   * Returns the value of the '<em><b>Type Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Scoped</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Scoped</em>' containment reference.
   * @see #setTypeScoped(scopedType)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getcontainerRef_TypeScoped()
   * @model containment="true"
   * @generated
   */
  scopedType getTypeScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.containerRef#getTypeScoped <em>Type Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Scoped</em>' containment reference.
   * @see #getTypeScoped()
   * @generated
   */
  void setTypeScoped(scopedType value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getcontainerRef_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.containerRef#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // containerRef
