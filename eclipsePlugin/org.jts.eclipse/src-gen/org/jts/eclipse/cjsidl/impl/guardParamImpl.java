/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.guardParam;
import org.jts.eclipse.cjsidl.transParam;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>guard Param</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.guardParamImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.guardParamImpl#getGuardConst <em>Guard Const</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class guardParamImpl extends MinimalEObjectImpl.Container implements guardParam
{
  /**
   * The cached value of the '{@link #getParameter() <em>Parameter</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameter()
   * @generated
   * @ordered
   */
  protected transParam parameter;

  /**
   * The default value of the '{@link #getGuardConst() <em>Guard Const</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGuardConst()
   * @generated
   * @ordered
   */
  protected static final String GUARD_CONST_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getGuardConst() <em>Guard Const</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGuardConst()
   * @generated
   * @ordered
   */
  protected String guardConst = GUARD_CONST_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected guardParamImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return CjsidlPackage.eINSTANCE.getguardParam();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public transParam getParameter()
  {
    if (parameter != null && parameter.eIsProxy())
    {
      InternalEObject oldParameter = (InternalEObject)parameter;
      parameter = (transParam)eResolveProxy(oldParameter);
      if (parameter != oldParameter)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CjsidlPackage.GUARD_PARAM__PARAMETER, oldParameter, parameter));
      }
    }
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public transParam basicGetParameter()
  {
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParameter(transParam newParameter)
  {
    transParam oldParameter = parameter;
    parameter = newParameter;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.GUARD_PARAM__PARAMETER, oldParameter, parameter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getGuardConst()
  {
    return guardConst;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGuardConst(String newGuardConst)
  {
    String oldGuardConst = guardConst;
    guardConst = newGuardConst;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.GUARD_PARAM__GUARD_CONST, oldGuardConst, guardConst));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case CjsidlPackage.GUARD_PARAM__PARAMETER:
        if (resolve) return getParameter();
        return basicGetParameter();
      case CjsidlPackage.GUARD_PARAM__GUARD_CONST:
        return getGuardConst();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case CjsidlPackage.GUARD_PARAM__PARAMETER:
        setParameter((transParam)newValue);
        return;
      case CjsidlPackage.GUARD_PARAM__GUARD_CONST:
        setGuardConst((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case CjsidlPackage.GUARD_PARAM__PARAMETER:
        setParameter((transParam)null);
        return;
      case CjsidlPackage.GUARD_PARAM__GUARD_CONST:
        setGuardConst(GUARD_CONST_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case CjsidlPackage.GUARD_PARAM__PARAMETER:
        return parameter != null;
      case CjsidlPackage.GUARD_PARAM__GUARD_CONST:
        return GUARD_CONST_EDEFAULT == null ? guardConst != null : !GUARD_CONST_EDEFAULT.equals(guardConst);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (guardConst: ");
    result.append(guardConst);
    result.append(')');
    return result.toString();
  }

} //guardParamImpl
