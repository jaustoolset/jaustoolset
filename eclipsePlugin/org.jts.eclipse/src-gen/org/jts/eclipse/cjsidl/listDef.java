/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>list Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.listDef#getCountComment <em>Count Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.listDef#getMinCount <em>Min Count</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.listDef#getMinCountRef <em>Min Count Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.listDef#getMinCountScoped <em>Min Count Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.listDef#getMaxCount <em>Max Count</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.listDef#getMaxCountRef <em>Max Count Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.listDef#getMaxCountScoped <em>Max Count Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.listDef#getContainerRef <em>Container Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.listDef#getContainerDef <em>Container Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getlistDef()
 * @model
 * @generated
 */
public interface listDef extends containerDef
{
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getlistDef_CountComment()
   * @model
   * @generated
   */
  String getCountComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.listDef#getCountComment <em>Count Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Count Comment</em>' attribute.
   * @see #getCountComment()
   * @generated
   */
  void setCountComment(String value);

  /**
   * Returns the value of the '<em><b>Min Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Count</em>' attribute.
   * @see #setMinCount(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getlistDef_MinCount()
   * @model
   * @generated
   */
  String getMinCount();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.listDef#getMinCount <em>Min Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min Count</em>' attribute.
   * @see #getMinCount()
   * @generated
   */
  void setMinCount(String value);

  /**
   * Returns the value of the '<em><b>Min Count Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min Count Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Count Ref</em>' containment reference.
   * @see #setMinCountRef(constReference)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getlistDef_MinCountRef()
   * @model containment="true"
   * @generated
   */
  constReference getMinCountRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.listDef#getMinCountRef <em>Min Count Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min Count Ref</em>' containment reference.
   * @see #getMinCountRef()
   * @generated
   */
  void setMinCountRef(constReference value);

  /**
   * Returns the value of the '<em><b>Min Count Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min Count Scoped</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Count Scoped</em>' containment reference.
   * @see #setMinCountScoped(scopedConstId)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getlistDef_MinCountScoped()
   * @model containment="true"
   * @generated
   */
  scopedConstId getMinCountScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.listDef#getMinCountScoped <em>Min Count Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min Count Scoped</em>' containment reference.
   * @see #getMinCountScoped()
   * @generated
   */
  void setMinCountScoped(scopedConstId value);

  /**
   * Returns the value of the '<em><b>Max Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Count</em>' attribute.
   * @see #setMaxCount(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getlistDef_MaxCount()
   * @model
   * @generated
   */
  String getMaxCount();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.listDef#getMaxCount <em>Max Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max Count</em>' attribute.
   * @see #getMaxCount()
   * @generated
   */
  void setMaxCount(String value);

  /**
   * Returns the value of the '<em><b>Max Count Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max Count Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Count Ref</em>' containment reference.
   * @see #setMaxCountRef(constReference)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getlistDef_MaxCountRef()
   * @model containment="true"
   * @generated
   */
  constReference getMaxCountRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.listDef#getMaxCountRef <em>Max Count Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max Count Ref</em>' containment reference.
   * @see #getMaxCountRef()
   * @generated
   */
  void setMaxCountRef(constReference value);

  /**
   * Returns the value of the '<em><b>Max Count Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max Count Scoped</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Count Scoped</em>' containment reference.
   * @see #setMaxCountScoped(scopedConstId)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getlistDef_MaxCountScoped()
   * @model containment="true"
   * @generated
   */
  scopedConstId getMaxCountScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.listDef#getMaxCountScoped <em>Max Count Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max Count Scoped</em>' containment reference.
   * @see #getMaxCountScoped()
   * @generated
   */
  void setMaxCountScoped(scopedConstId value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getlistDef_ContainerRef()
   * @model containment="true"
   * @generated
   */
  containerRef getContainerRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.listDef#getContainerRef <em>Container Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Container Ref</em>' containment reference.
   * @see #getContainerRef()
   * @generated
   */
  void setContainerRef(containerRef value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getlistDef_ContainerDef()
   * @model containment="true"
   * @generated
   */
  containerDef getContainerDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.listDef#getContainerDef <em>Container Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Container Def</em>' containment reference.
   * @see #getContainerDef()
   * @generated
   */
  void setContainerDef(containerDef value);

} // listDef
