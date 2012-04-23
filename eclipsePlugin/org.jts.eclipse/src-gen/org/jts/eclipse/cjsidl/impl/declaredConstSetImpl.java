/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.constDef;
import org.jts.eclipse.cjsidl.declaredConstSet;
import org.jts.eclipse.cjsidl.declaredConstSetRef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>declared Const Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredConstSetImpl#getConstName <em>Const Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredConstSetImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredConstSetImpl#getConstSetVersion <em>Const Set Version</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredConstSetImpl#getDeclaredConstSetRef <em>Declared Const Set Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredConstSetImpl#getConstDef <em>Const Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class declaredConstSetImpl extends MinimalEObjectImpl.Container implements declaredConstSet
{
  /**
   * The default value of the '{@link #getConstName() <em>Const Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstName()
   * @generated
   * @ordered
   */
  protected static final String CONST_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getConstName() <em>Const Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstName()
   * @generated
   * @ordered
   */
  protected String constName = CONST_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getConstSetVersion() <em>Const Set Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstSetVersion()
   * @generated
   * @ordered
   */
  protected static final String CONST_SET_VERSION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getConstSetVersion() <em>Const Set Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstSetVersion()
   * @generated
   * @ordered
   */
  protected String constSetVersion = CONST_SET_VERSION_EDEFAULT;

  /**
   * The cached value of the '{@link #getDeclaredConstSetRef() <em>Declared Const Set Ref</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDeclaredConstSetRef()
   * @generated
   * @ordered
   */
  protected EList<declaredConstSetRef> declaredConstSetRef;

  /**
   * The cached value of the '{@link #getConstDef() <em>Const Def</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstDef()
   * @generated
   * @ordered
   */
  protected EList<constDef> constDef;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected declaredConstSetImpl()
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
    return CjsidlPackage.eINSTANCE.getdeclaredConstSet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getConstName()
  {
    return constName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstName(String newConstName)
  {
    String oldConstName = constName;
    constName = newConstName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.DECLARED_CONST_SET__CONST_NAME, oldConstName, constName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.DECLARED_CONST_SET__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getConstSetVersion()
  {
    return constSetVersion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstSetVersion(String newConstSetVersion)
  {
    String oldConstSetVersion = constSetVersion;
    constSetVersion = newConstSetVersion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.DECLARED_CONST_SET__CONST_SET_VERSION, oldConstSetVersion, constSetVersion));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<declaredConstSetRef> getDeclaredConstSetRef()
  {
    if (declaredConstSetRef == null)
    {
      declaredConstSetRef = new EObjectContainmentEList<declaredConstSetRef>(declaredConstSetRef.class, this, CjsidlPackage.DECLARED_CONST_SET__DECLARED_CONST_SET_REF);
    }
    return declaredConstSetRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<constDef> getConstDef()
  {
    if (constDef == null)
    {
      constDef = new EObjectContainmentEList<constDef>(constDef.class, this, CjsidlPackage.DECLARED_CONST_SET__CONST_DEF);
    }
    return constDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case CjsidlPackage.DECLARED_CONST_SET__DECLARED_CONST_SET_REF:
        return ((InternalEList<?>)getDeclaredConstSetRef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.DECLARED_CONST_SET__CONST_DEF:
        return ((InternalEList<?>)getConstDef()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case CjsidlPackage.DECLARED_CONST_SET__CONST_NAME:
        return getConstName();
      case CjsidlPackage.DECLARED_CONST_SET__NAME:
        return getName();
      case CjsidlPackage.DECLARED_CONST_SET__CONST_SET_VERSION:
        return getConstSetVersion();
      case CjsidlPackage.DECLARED_CONST_SET__DECLARED_CONST_SET_REF:
        return getDeclaredConstSetRef();
      case CjsidlPackage.DECLARED_CONST_SET__CONST_DEF:
        return getConstDef();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case CjsidlPackage.DECLARED_CONST_SET__CONST_NAME:
        setConstName((String)newValue);
        return;
      case CjsidlPackage.DECLARED_CONST_SET__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.DECLARED_CONST_SET__CONST_SET_VERSION:
        setConstSetVersion((String)newValue);
        return;
      case CjsidlPackage.DECLARED_CONST_SET__DECLARED_CONST_SET_REF:
        getDeclaredConstSetRef().clear();
        getDeclaredConstSetRef().addAll((Collection<? extends declaredConstSetRef>)newValue);
        return;
      case CjsidlPackage.DECLARED_CONST_SET__CONST_DEF:
        getConstDef().clear();
        getConstDef().addAll((Collection<? extends constDef>)newValue);
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
      case CjsidlPackage.DECLARED_CONST_SET__CONST_NAME:
        setConstName(CONST_NAME_EDEFAULT);
        return;
      case CjsidlPackage.DECLARED_CONST_SET__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.DECLARED_CONST_SET__CONST_SET_VERSION:
        setConstSetVersion(CONST_SET_VERSION_EDEFAULT);
        return;
      case CjsidlPackage.DECLARED_CONST_SET__DECLARED_CONST_SET_REF:
        getDeclaredConstSetRef().clear();
        return;
      case CjsidlPackage.DECLARED_CONST_SET__CONST_DEF:
        getConstDef().clear();
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
      case CjsidlPackage.DECLARED_CONST_SET__CONST_NAME:
        return CONST_NAME_EDEFAULT == null ? constName != null : !CONST_NAME_EDEFAULT.equals(constName);
      case CjsidlPackage.DECLARED_CONST_SET__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.DECLARED_CONST_SET__CONST_SET_VERSION:
        return CONST_SET_VERSION_EDEFAULT == null ? constSetVersion != null : !CONST_SET_VERSION_EDEFAULT.equals(constSetVersion);
      case CjsidlPackage.DECLARED_CONST_SET__DECLARED_CONST_SET_REF:
        return declaredConstSetRef != null && !declaredConstSetRef.isEmpty();
      case CjsidlPackage.DECLARED_CONST_SET__CONST_DEF:
        return constDef != null && !constDef.isEmpty();
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
    result.append(" (constName: ");
    result.append(constName);
    result.append(", name: ");
    result.append(name);
    result.append(", constSetVersion: ");
    result.append(constSetVersion);
    result.append(')');
    return result.toString();
  }

} //declaredConstSetImpl
