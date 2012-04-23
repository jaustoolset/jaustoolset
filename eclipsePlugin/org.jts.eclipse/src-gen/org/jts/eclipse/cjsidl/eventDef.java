/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>event Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.eventDef#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.eventDef#getDescr <em>Descr</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.eventDef#getHeader <em>Header</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.eventDef#getBody <em>Body</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.eventDef#getFooter <em>Footer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#geteventDef()
 * @model
 * @generated
 */
public interface eventDef extends EObject
{
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#geteventDef_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.eventDef#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Descr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Descr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Descr</em>' containment reference.
   * @see #setDescr(description)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#geteventDef_Descr()
   * @model containment="true"
   * @generated
   */
  description getDescr();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.eventDef#getDescr <em>Descr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Descr</em>' containment reference.
   * @see #getDescr()
   * @generated
   */
  void setDescr(description value);

  /**
   * Returns the value of the '<em><b>Header</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Header</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Header</em>' containment reference.
   * @see #setHeader(EObject)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#geteventDef_Header()
   * @model containment="true"
   * @generated
   */
  EObject getHeader();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.eventDef#getHeader <em>Header</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Header</em>' containment reference.
   * @see #getHeader()
   * @generated
   */
  void setHeader(EObject value);

  /**
   * Returns the value of the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body</em>' containment reference.
   * @see #setBody(EObject)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#geteventDef_Body()
   * @model containment="true"
   * @generated
   */
  EObject getBody();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.eventDef#getBody <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body</em>' containment reference.
   * @see #getBody()
   * @generated
   */
  void setBody(EObject value);

  /**
   * Returns the value of the '<em><b>Footer</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Footer</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Footer</em>' containment reference.
   * @see #setFooter(EObject)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#geteventDef_Footer()
   * @model containment="true"
   * @generated
   */
  EObject getFooter();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.eventDef#getFooter <em>Footer</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Footer</em>' containment reference.
   * @see #getFooter()
   * @generated
   */
  void setFooter(EObject value);

} // eventDef
