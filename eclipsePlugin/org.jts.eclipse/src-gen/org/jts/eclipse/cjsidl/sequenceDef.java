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
 * A representation of the model object '<em><b>sequence Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.sequenceDef#getContainerTypeList <em>Container Type List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getsequenceDef()
 * @model
 * @generated
 */
public interface sequenceDef extends containerDef
{
  /**
   * Returns the value of the '<em><b>Container Type List</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Container Type List</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Container Type List</em>' containment reference list.
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getsequenceDef_ContainerTypeList()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getContainerTypeList();

} // sequenceDef
