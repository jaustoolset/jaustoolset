/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>scaled Range Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.scaledRangeDef#getInterp <em>Interp</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLim <em>Lower Lim</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLimRef <em>Lower Lim Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLimScoped <em>Lower Lim Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLim <em>Upper Lim</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLimRef <em>Upper Lim Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLimScoped <em>Upper Lim Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.scaledRangeDef#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#getscaledRangeDef()
 * @model
 * @generated
 */
public interface scaledRangeDef extends EObject
{
  /**
   * Returns the value of the '<em><b>Interp</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Interp</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Interp</em>' attribute.
   * @see #setInterp(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getscaledRangeDef_Interp()
   * @model
   * @generated
   */
  String getInterp();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getInterp <em>Interp</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interp</em>' attribute.
   * @see #getInterp()
   * @generated
   */
  void setInterp(String value);

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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getscaledRangeDef_LowerLim()
   * @model
   * @generated
   */
  String getLowerLim();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLim <em>Lower Lim</em>}' attribute.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getscaledRangeDef_LowerLimRef()
   * @model containment="true"
   * @generated
   */
  constReference getLowerLimRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLimRef <em>Lower Lim Ref</em>}' containment reference.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getscaledRangeDef_LowerLimScoped()
   * @model containment="true"
   * @generated
   */
  scopedConstId getLowerLimScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getLowerLimScoped <em>Lower Lim Scoped</em>}' containment reference.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getscaledRangeDef_UpperLim()
   * @model
   * @generated
   */
  String getUpperLim();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLim <em>Upper Lim</em>}' attribute.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getscaledRangeDef_UpperLimRef()
   * @model containment="true"
   * @generated
   */
  constReference getUpperLimRef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLimRef <em>Upper Lim Ref</em>}' containment reference.
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
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getscaledRangeDef_UpperLimScoped()
   * @model containment="true"
   * @generated
   */
  scopedConstId getUpperLimScoped();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getUpperLimScoped <em>Upper Lim Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Lim Scoped</em>' containment reference.
   * @see #getUpperLimScoped()
   * @generated
   */
  void setUpperLimScoped(scopedConstId value);

  /**
   * Returns the value of the '<em><b>Function</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function</em>' attribute.
   * @see #setFunction(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#getscaledRangeDef_Function()
   * @model
   * @generated
   */
  String getFunction();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.scaledRangeDef#getFunction <em>Function</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function</em>' attribute.
   * @see #getFunction()
   * @generated
   */
  void setFunction(String value);

} // scaledRangeDef
