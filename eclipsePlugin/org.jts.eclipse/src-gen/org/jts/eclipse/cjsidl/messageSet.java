/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>message Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.messageSet#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.messageSet#getInputComment <em>Input Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.messageSet#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.messageSet#getOutputComment <em>Output Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.messageSet#getOutputSet <em>Output Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getmessageSet()
 * @model
 * @generated
 */
public interface messageSet extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getmessageSet_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.messageSet#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Input Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Input Comment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Input Comment</em>' attribute.
   * @see #setInputComment(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getmessageSet_InputComment()
   * @model
   * @generated
   */
  String getInputComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.messageSet#getInputComment <em>Input Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Input Comment</em>' attribute.
   * @see #getInputComment()
   * @generated
   */
  void setInputComment(String value);

  /**
   * Returns the value of the '<em><b>Input Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Input Set</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Input Set</em>' containment reference.
   * @see #setInputSet(messages)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getmessageSet_InputSet()
   * @model containment="true"
   * @generated
   */
  messages getInputSet();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.messageSet#getInputSet <em>Input Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Input Set</em>' containment reference.
   * @see #getInputSet()
   * @generated
   */
  void setInputSet(messages value);

  /**
   * Returns the value of the '<em><b>Output Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output Comment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output Comment</em>' attribute.
   * @see #setOutputComment(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getmessageSet_OutputComment()
   * @model
   * @generated
   */
  String getOutputComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.messageSet#getOutputComment <em>Output Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output Comment</em>' attribute.
   * @see #getOutputComment()
   * @generated
   */
  void setOutputComment(String value);

  /**
   * Returns the value of the '<em><b>Output Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output Set</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output Set</em>' containment reference.
   * @see #setOutputSet(messages)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getmessageSet_OutputSet()
   * @model containment="true"
   * @generated
   */
  messages getOutputSet();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.messageSet#getOutputSet <em>Output Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output Set</em>' containment reference.
   * @see #getOutputSet()
   * @generated
   */
  void setOutputSet(messages value);

} // messageSet
