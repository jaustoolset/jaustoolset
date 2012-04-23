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
 * A representation of the model object '<em><b>messages</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.messages#getMessageDefs <em>Message Defs</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.messages#getTypeRefs <em>Type Refs</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.messages#getScopedRefs <em>Scoped Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getmessages()
 * @model
 * @generated
 */
public interface messages extends EObject
{
  /**
   * Returns the value of the '<em><b>Message Defs</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.messageDef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message Defs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message Defs</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getmessages_MessageDefs()
   * @model containment="true"
   * @generated
   */
  EList<messageDef> getMessageDefs();

  /**
   * Returns the value of the '<em><b>Type Refs</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.messageRef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Refs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Refs</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getmessages_TypeRefs()
   * @model containment="true"
   * @generated
   */
  EList<messageRef> getTypeRefs();

  /**
   * Returns the value of the '<em><b>Scoped Refs</b></em>' containment reference list.
   * The list contents are of type {@link org.jts.eclipse.cjsidl.messageScopedRef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scoped Refs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scoped Refs</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getmessages_ScopedRefs()
   * @model containment="true"
   * @generated
   */
  EList<messageScopedRef> getScopedRefs();

} // messages
