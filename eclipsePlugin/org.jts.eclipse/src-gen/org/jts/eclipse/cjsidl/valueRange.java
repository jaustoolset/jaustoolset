/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>value Range</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.valueRange#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.valueRange#getLowerLimit_type <em>Lower Limit type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.valueRange#getLowerLim <em>Lower Lim</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.valueRange#getLowerLimRef <em>Lower Lim Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.valueRange#getLowerLimScoped <em>Lower Lim Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.valueRange#getUpperLim <em>Upper Lim</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.valueRange#getUpperLimRef <em>Upper Lim Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.valueRange#getUpperLimScoped <em>Upper Lim Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.valueRange#getUpperLimit_type <em>Upper Limit type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvalueRange()
 * @model
 * @generated
 */
public interface valueRange extends EObject
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvalueRange_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.valueRange#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Lower Limit type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lower Limit type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lower Limit type</em>' attribute.
   * @see #setLowerLimit_type(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvalueRange_LowerLimit_type()
   * @model
   * @generated
   */
  String getLowerLimit_type();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.valueRange#getLowerLimit_type <em>Lower Limit type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lower Limit type</em>' attribute.
   * @see #getLowerLimit_type()
   * @generated
   */
  void setLowerLimit_type(String value);

  /**
   * Returns the value of the '<em><b>Lower Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lower Lim</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lower Lim</em>' attribute.
   * @see #setLowerLim(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvalueRange_LowerLim()
   * @model
   * @generated
   */
  String getLowerLim();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.valueRange#getLowerLim <em>Lower Lim</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lower Lim</em>' attribute.
   * @see #getLowerLim()
   * @generated
   */
  void setLowerLim(String value);

  /**
   * Returns the value of the '<em><b>Lower Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lower Lim Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lower Lim Ref</em>' containment reference.
   * @see #setLowerLimRef(constReference)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvalueRange_LowerLimRef()
   * @model containment="true"
   * @generated
   */
  constReference getLowerLimRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.valueRange#getLowerLimRef <em>Lower Lim Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lower Lim Ref</em>' containment reference.
   * @see #getLowerLimRef()
   * @generated
   */
  void setLowerLimRef(constReference value);

  /**
   * Returns the value of the '<em><b>Lower Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lower Lim Scoped</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lower Lim Scoped</em>' containment reference.
   * @see #setLowerLimScoped(scopedConstId)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvalueRange_LowerLimScoped()
   * @model containment="true"
   * @generated
   */
  scopedConstId getLowerLimScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.valueRange#getLowerLimScoped <em>Lower Lim Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lower Lim Scoped</em>' containment reference.
   * @see #getLowerLimScoped()
   * @generated
   */
  void setLowerLimScoped(scopedConstId value);

  /**
   * Returns the value of the '<em><b>Upper Lim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Upper Lim</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Upper Lim</em>' attribute.
   * @see #setUpperLim(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvalueRange_UpperLim()
   * @model
   * @generated
   */
  String getUpperLim();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.valueRange#getUpperLim <em>Upper Lim</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Lim</em>' attribute.
   * @see #getUpperLim()
   * @generated
   */
  void setUpperLim(String value);

  /**
   * Returns the value of the '<em><b>Upper Lim Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Upper Lim Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Upper Lim Ref</em>' containment reference.
   * @see #setUpperLimRef(constReference)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvalueRange_UpperLimRef()
   * @model containment="true"
   * @generated
   */
  constReference getUpperLimRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.valueRange#getUpperLimRef <em>Upper Lim Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Lim Ref</em>' containment reference.
   * @see #getUpperLimRef()
   * @generated
   */
  void setUpperLimRef(constReference value);

  /**
   * Returns the value of the '<em><b>Upper Lim Scoped</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Upper Lim Scoped</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Upper Lim Scoped</em>' containment reference.
   * @see #setUpperLimScoped(scopedConstId)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvalueRange_UpperLimScoped()
   * @model containment="true"
   * @generated
   */
  scopedConstId getUpperLimScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.valueRange#getUpperLimScoped <em>Upper Lim Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Lim Scoped</em>' containment reference.
   * @see #getUpperLimScoped()
   * @generated
   */
  void setUpperLimScoped(scopedConstId value);

  /**
   * Returns the value of the '<em><b>Upper Limit type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Upper Limit type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Upper Limit type</em>' attribute.
   * @see #setUpperLimit_type(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getvalueRange_UpperLimit_type()
   * @model
   * @generated
   */
  String getUpperLimit_type();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.valueRange#getUpperLimit_type <em>Upper Limit type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Limit type</em>' attribute.
   * @see #getUpperLimit_type()
   * @generated
   */
  void setUpperLimit_type(String value);

} // valueRange
