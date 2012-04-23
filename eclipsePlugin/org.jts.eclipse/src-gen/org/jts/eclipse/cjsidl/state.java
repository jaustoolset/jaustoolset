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
 * A representation of the model object '<em><b>state</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.state#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.state#getInitial <em>Initial</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.state#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.state#getEntryAction <em>Entry Action</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.state#getExitAction <em>Exit Action</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.state#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.state#getDefaultTransition <em>Default Transition</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.state#getDefaultState <em>Default State</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.state#getSubState <em>Sub State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstate()
 * @model
 * @generated
 */
public interface state extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstate_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.state#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Initial</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initial</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initial</em>' attribute.
   * @see #setInitial(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstate_Initial()
   * @model
   * @generated
   */
  String getInitial();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.state#getInitial <em>Initial</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Initial</em>' attribute.
   * @see #getInitial()
   * @generated
   */
  void setInitial(String value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstate_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.state#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Entry Action</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entry Action</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entry Action</em>' containment reference.
   * @see #setEntryAction(entry)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstate_EntryAction()
   * @model containment="true"
   * @generated
   */
  entry getEntryAction();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.state#getEntryAction <em>Entry Action</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Entry Action</em>' containment reference.
   * @see #getEntryAction()
   * @generated
   */
  void setEntryAction(entry value);

  /**
   * Returns the value of the '<em><b>Exit Action</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exit Action</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exit Action</em>' containment reference.
   * @see #setExitAction(exit)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstate_ExitAction()
   * @model containment="true"
   * @generated
   */
  exit getExitAction();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.state#getExitAction <em>Exit Action</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Exit Action</em>' containment reference.
   * @see #getExitAction()
   * @generated
   */
  void setExitAction(exit value);

  /**
   * Returns the value of the '<em><b>Transitions</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.transition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Transitions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transitions</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstate_Transitions()
   * @model containment="true"
   * @generated
   */
  EList<transition> getTransitions();

  /**
   * Returns the value of the '<em><b>Default Transition</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.defaultTransition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default Transition</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default Transition</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstate_DefaultTransition()
   * @model containment="true"
   * @generated
   */
  EList<defaultTransition> getDefaultTransition();

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstate_DefaultState()
   * @model containment="true"
   * @generated
   */
  defaultState getDefaultState();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.state#getDefaultState <em>Default State</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Default State</em>' containment reference.
   * @see #getDefaultState()
   * @generated
   */
  void setDefaultState(defaultState value);

  /**
   * Returns the value of the '<em><b>Sub State</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.state}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sub State</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sub State</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getstate_SubState()
   * @model containment="true"
   * @generated
   */
  EList<state> getSubState();

} // state
