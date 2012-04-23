/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>tagged Item Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.taggedItemDef#getContainerDef <em>Container Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.taggedItemDef#getContainerRef <em>Container Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedItemDef()
 * @model
 * @generated
 */
public interface taggedItemDef extends EObject
{
  /**
   * Returns the value of the '<em><b>Container Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Container Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Container Def</em>' containment reference.
   * @see #setContainerDef(containerDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedItemDef_ContainerDef()
   * @model containment="true"
   * @generated
   */
  containerDef getContainerDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.taggedItemDef#getContainerDef <em>Container Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Container Def</em>' containment reference.
   * @see #getContainerDef()
   * @generated
   */
  void setContainerDef(containerDef value);

  /**
   * Returns the value of the '<em><b>Container Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Container Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Container Ref</em>' containment reference.
   * @see #setContainerRef(containerRef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedItemDef_ContainerRef()
   * @model containment="true"
   * @generated
   */
  containerRef getContainerRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.taggedItemDef#getContainerRef <em>Container Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Container Ref</em>' containment reference.
   * @see #getContainerRef()
   * @generated
   */
  void setContainerRef(containerRef value);

} // taggedItemDef
