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
 * A representation of the model object '<em><b>state Machine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.stateMachine#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.stateMachine#getScoped <em>Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.stateMachine#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.stateMachine#getStartState <em>Start State</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.stateMachine#getDefaultState <em>Default State</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.stateMachine#getStates <em>States</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstateMachine()
 * @model
 * @generated
 */
public interface stateMachine extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstateMachine_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.stateMachine#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Scoped</b></em>' reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.refAttr}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scoped</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scoped</em>' reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstateMachine_Scoped()
   * @model
   * @generated
   */
  EList<refAttr> getScoped();

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstateMachine_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.stateMachine#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Start State</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start State</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start State</em>' containment reference.
   * @see #setStartState(startState)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstateMachine_StartState()
   * @model containment="true"
   * @generated
   */
  startState getStartState();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.stateMachine#getStartState <em>Start State</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start State</em>' containment reference.
   * @see #getStartState()
   * @generated
   */
  void setStartState(startState value);

  /**
   * Returns the value of the '<em><b>Default State</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default State</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default State</em>' containment reference.
   * @see #setDefaultState(defaultState)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstateMachine_DefaultState()
   * @model containment="true"
   * @generated
   */
  defaultState getDefaultState();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.stateMachine#getDefaultState <em>Default State</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Default State</em>' containment reference.
   * @see #getDefaultState()
   * @generated
   */
  void setDefaultState(defaultState value);

  /**
   * Returns the value of the '<em><b>States</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.state}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>States</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>States</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstateMachine_States()
   * @model containment="true"
   * @generated
   */
  EList<state> getStates();

} // stateMachine
