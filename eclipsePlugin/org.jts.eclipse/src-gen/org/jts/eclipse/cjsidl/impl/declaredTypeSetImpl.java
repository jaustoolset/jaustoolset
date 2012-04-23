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
import org.jts.eclipse.cjsidl.declaredConstSetRef;
import org.jts.eclipse.cjsidl.declaredTypeSet;
import org.jts.eclipse.cjsidl.declaredTypeSetRef;
import org.jts.eclipse.cjsidl.scopedTypeId;
import org.jts.eclipse.cjsidl.typeDef;
import org.jts.eclipse.cjsidl.typeReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>declared Type Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredTypeSetImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredTypeSetImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredTypeSetImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredTypeSetImpl#getDeclaredConstSetRef <em>Declared Const Set Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredTypeSetImpl#getDeclaredTypeSetRef <em>Declared Type Set Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredTypeSetImpl#getTypeDef <em>Type Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredTypeSetImpl#getTypeRef <em>Type Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredTypeSetImpl#getScopedRef <em>Scoped Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class declaredTypeSetImpl extends MinimalEObjectImpl.Container implements declaredTypeSet
{
  /**
   * The default value of the '{@link #getTypeName() <em>Type Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeName()
   * @generated
   * @ordered
   */
  protected static final String TYPE_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeName()
   * @generated
   * @ordered
   */
  protected String typeName = TYPE_NAME_EDEFAULT;

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
   * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
  protected static final String VERSION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
  protected String version = VERSION_EDEFAULT;

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
   * The cached value of the '{@link #getDeclaredTypeSetRef() <em>Declared Type Set Ref</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDeclaredTypeSetRef()
   * @generated
   * @ordered
   */
  protected EList<declaredTypeSetRef> declaredTypeSetRef;

  /**
   * The cached value of the '{@link #getTypeDef() <em>Type Def</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeDef()
   * @generated
   * @ordered
   */
  protected EList<typeDef> typeDef;

  /**
   * The cached value of the '{@link #getTypeRef() <em>Type Ref</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeRef()
   * @generated
   * @ordered
   */
  protected EList<typeReference> typeRef;

  /**
   * The cached value of the '{@link #getScopedRef() <em>Scoped Ref</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScopedRef()
   * @generated
   * @ordered
   */
  protected EList<scopedTypeId> scopedRef;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected declaredTypeSetImpl()
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
    return CjsidlPackage.eINSTANCE.getdeclaredTypeSet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTypeName()
  {
    return typeName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypeName(String newTypeName)
  {
    String oldTypeName = typeName;
    typeName = newTypeName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.DECLARED_TYPE_SET__TYPE_NAME, oldTypeName, typeName));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.DECLARED_TYPE_SET__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getVersion()
  {
    return version;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVersion(String newVersion)
  {
    String oldVersion = version;
    version = newVersion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.DECLARED_TYPE_SET__VERSION, oldVersion, version));
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
      declaredConstSetRef = new EObjectContainmentEList<declaredConstSetRef>(declaredConstSetRef.class, this, CjsidlPackage.DECLARED_TYPE_SET__DECLARED_CONST_SET_REF);
    }
    return declaredConstSetRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<declaredTypeSetRef> getDeclaredTypeSetRef()
  {
    if (declaredTypeSetRef == null)
    {
      declaredTypeSetRef = new EObjectContainmentEList<declaredTypeSetRef>(declaredTypeSetRef.class, this, CjsidlPackage.DECLARED_TYPE_SET__DECLARED_TYPE_SET_REF);
    }
    return declaredTypeSetRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<typeDef> getTypeDef()
  {
    if (typeDef == null)
    {
      typeDef = new EObjectContainmentEList<typeDef>(typeDef.class, this, CjsidlPackage.DECLARED_TYPE_SET__TYPE_DEF);
    }
    return typeDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<typeReference> getTypeRef()
  {
    if (typeRef == null)
    {
      typeRef = new EObjectContainmentEList<typeReference>(typeReference.class, this, CjsidlPackage.DECLARED_TYPE_SET__TYPE_REF);
    }
    return typeRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<scopedTypeId> getScopedRef()
  {
    if (scopedRef == null)
    {
      scopedRef = new EObjectContainmentEList<scopedTypeId>(scopedTypeId.class, this, CjsidlPackage.DECLARED_TYPE_SET__SCOPED_REF);
    }
    return scopedRef;
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
      case CjsidlPackage.DECLARED_TYPE_SET__DECLARED_CONST_SET_REF:
        return ((InternalEList<?>)getDeclaredConstSetRef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.DECLARED_TYPE_SET__DECLARED_TYPE_SET_REF:
        return ((InternalEList<?>)getDeclaredTypeSetRef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_DEF:
        return ((InternalEList<?>)getTypeDef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_REF:
        return ((InternalEList<?>)getTypeRef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.DECLARED_TYPE_SET__SCOPED_REF:
        return ((InternalEList<?>)getScopedRef()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_NAME:
        return getTypeName();
      case CjsidlPackage.DECLARED_TYPE_SET__NAME:
        return getName();
      case CjsidlPackage.DECLARED_TYPE_SET__VERSION:
        return getVersion();
      case CjsidlPackage.DECLARED_TYPE_SET__DECLARED_CONST_SET_REF:
        return getDeclaredConstSetRef();
      case CjsidlPackage.DECLARED_TYPE_SET__DECLARED_TYPE_SET_REF:
        return getDeclaredTypeSetRef();
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_DEF:
        return getTypeDef();
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_REF:
        return getTypeRef();
      case CjsidlPackage.DECLARED_TYPE_SET__SCOPED_REF:
        return getScopedRef();
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
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_NAME:
        setTypeName((String)newValue);
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__VERSION:
        setVersion((String)newValue);
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__DECLARED_CONST_SET_REF:
        getDeclaredConstSetRef().clear();
        getDeclaredConstSetRef().addAll((Collection<? extends declaredConstSetRef>)newValue);
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__DECLARED_TYPE_SET_REF:
        getDeclaredTypeSetRef().clear();
        getDeclaredTypeSetRef().addAll((Collection<? extends declaredTypeSetRef>)newValue);
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_DEF:
        getTypeDef().clear();
        getTypeDef().addAll((Collection<? extends typeDef>)newValue);
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_REF:
        getTypeRef().clear();
        getTypeRef().addAll((Collection<? extends typeReference>)newValue);
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__SCOPED_REF:
        getScopedRef().clear();
        getScopedRef().addAll((Collection<? extends scopedTypeId>)newValue);
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
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_NAME:
        setTypeName(TYPE_NAME_EDEFAULT);
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__VERSION:
        setVersion(VERSION_EDEFAULT);
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__DECLARED_CONST_SET_REF:
        getDeclaredConstSetRef().clear();
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__DECLARED_TYPE_SET_REF:
        getDeclaredTypeSetRef().clear();
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_DEF:
        getTypeDef().clear();
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_REF:
        getTypeRef().clear();
        return;
      case CjsidlPackage.DECLARED_TYPE_SET__SCOPED_REF:
        getScopedRef().clear();
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
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_NAME:
        return TYPE_NAME_EDEFAULT == null ? typeName != null : !TYPE_NAME_EDEFAULT.equals(typeName);
      case CjsidlPackage.DECLARED_TYPE_SET__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.DECLARED_TYPE_SET__VERSION:
        return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
      case CjsidlPackage.DECLARED_TYPE_SET__DECLARED_CONST_SET_REF:
        return declaredConstSetRef != null && !declaredConstSetRef.isEmpty();
      case CjsidlPackage.DECLARED_TYPE_SET__DECLARED_TYPE_SET_REF:
        return declaredTypeSetRef != null && !declaredTypeSetRef.isEmpty();
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_DEF:
        return typeDef != null && !typeDef.isEmpty();
      case CjsidlPackage.DECLARED_TYPE_SET__TYPE_REF:
        return typeRef != null && !typeRef.isEmpty();
      case CjsidlPackage.DECLARED_TYPE_SET__SCOPED_REF:
        return scopedRef != null && !scopedRef.isEmpty();
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
    result.append(" (typeName: ");
    result.append(typeName);
    result.append(", name: ");
    result.append(name);
    result.append(", version: ");
    result.append(version);
    result.append(')');
    return result.toString();
  }

} //declaredTypeSetImpl
