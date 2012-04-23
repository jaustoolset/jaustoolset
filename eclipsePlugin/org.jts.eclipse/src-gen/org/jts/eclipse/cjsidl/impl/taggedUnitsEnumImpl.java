/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.UNIT;
import org.jts.eclipse.cjsidl.constReference;
import org.jts.eclipse.cjsidl.scaledRangeDef;
import org.jts.eclipse.cjsidl.scopedConstId;
import org.jts.eclipse.cjsidl.simpleNumericType;
import org.jts.eclipse.cjsidl.taggedUnitsEnum;
import org.jts.eclipse.cjsidl.valueSetDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>tagged Units Enum</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.taggedUnitsEnumImpl#getConst_tag <em>Const tag</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.taggedUnitsEnumImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.taggedUnitsEnumImpl#getScopedTag <em>Scoped Tag</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.taggedUnitsEnumImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.taggedUnitsEnumImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.taggedUnitsEnumImpl#getFieldUnit <em>Field Unit</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.taggedUnitsEnumImpl#getValueSetDef <em>Value Set Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.taggedUnitsEnumImpl#getScaledRangeDef <em>Scaled Range Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class taggedUnitsEnumImpl extends MinimalEObjectImpl.Container implements taggedUnitsEnum
{
  /**
   * The default value of the '{@link #getConst_tag() <em>Const tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConst_tag()
   * @generated
   * @ordered
   */
  protected static final String CONST_TAG_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getConst_tag() <em>Const tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConst_tag()
   * @generated
   * @ordered
   */
  protected String const_tag = CONST_TAG_EDEFAULT;

  /**
   * The cached value of the '{@link #getTag() <em>Tag</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTag()
   * @generated
   * @ordered
   */
  protected constReference tag;

  /**
   * The cached value of the '{@link #getScopedTag() <em>Scoped Tag</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScopedTag()
   * @generated
   * @ordered
   */
  protected scopedConstId scopedTag;

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
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected simpleNumericType type;

  /**
   * The default value of the '{@link #getFieldUnit() <em>Field Unit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFieldUnit()
   * @generated
   * @ordered
   */
  protected static final UNIT FIELD_UNIT_EDEFAULT = UNIT.METER;

  /**
   * The cached value of the '{@link #getFieldUnit() <em>Field Unit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFieldUnit()
   * @generated
   * @ordered
   */
  protected UNIT fieldUnit = FIELD_UNIT_EDEFAULT;

  /**
   * The cached value of the '{@link #getValueSetDef() <em>Value Set Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValueSetDef()
   * @generated
   * @ordered
   */
  protected valueSetDef valueSetDef;

  /**
   * The cached value of the '{@link #getScaledRangeDef() <em>Scaled Range Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScaledRangeDef()
   * @generated
   * @ordered
   */
  protected scaledRangeDef scaledRangeDef;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected taggedUnitsEnumImpl()
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
    return CjsidlPackage.eINSTANCE.gettaggedUnitsEnum();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getConst_tag()
  {
    return const_tag;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConst_tag(String newConst_tag)
  {
    String oldConst_tag = const_tag;
    const_tag = newConst_tag;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__CONST_TAG, oldConst_tag, const_tag));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public constReference getTag()
  {
    return tag;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTag(constReference newTag, NotificationChain msgs)
  {
    constReference oldTag = tag;
    tag = newTag;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__TAG, oldTag, newTag);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTag(constReference newTag)
  {
    if (newTag != tag)
    {
      NotificationChain msgs = null;
      if (tag != null)
        msgs = ((InternalEObject)tag).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_UNITS_ENUM__TAG, null, msgs);
      if (newTag != null)
        msgs = ((InternalEObject)newTag).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_UNITS_ENUM__TAG, null, msgs);
      msgs = basicSetTag(newTag, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__TAG, newTag, newTag));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scopedConstId getScopedTag()
  {
    return scopedTag;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetScopedTag(scopedConstId newScopedTag, NotificationChain msgs)
  {
    scopedConstId oldScopedTag = scopedTag;
    scopedTag = newScopedTag;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__SCOPED_TAG, oldScopedTag, newScopedTag);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScopedTag(scopedConstId newScopedTag)
  {
    if (newScopedTag != scopedTag)
    {
      NotificationChain msgs = null;
      if (scopedTag != null)
        msgs = ((InternalEObject)scopedTag).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_UNITS_ENUM__SCOPED_TAG, null, msgs);
      if (newScopedTag != null)
        msgs = ((InternalEObject)newScopedTag).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_UNITS_ENUM__SCOPED_TAG, null, msgs);
      msgs = basicSetScopedTag(newScopedTag, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__SCOPED_TAG, newScopedTag, newScopedTag));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public simpleNumericType getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(simpleNumericType newType, NotificationChain msgs)
  {
    simpleNumericType oldType = type;
    type = newType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__TYPE, oldType, newType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(simpleNumericType newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_UNITS_ENUM__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_UNITS_ENUM__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__TYPE, newType, newType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UNIT getFieldUnit()
  {
    return fieldUnit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFieldUnit(UNIT newFieldUnit)
  {
    UNIT oldFieldUnit = fieldUnit;
    fieldUnit = newFieldUnit == null ? FIELD_UNIT_EDEFAULT : newFieldUnit;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__FIELD_UNIT, oldFieldUnit, fieldUnit));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public valueSetDef getValueSetDef()
  {
    return valueSetDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetValueSetDef(valueSetDef newValueSetDef, NotificationChain msgs)
  {
    valueSetDef oldValueSetDef = valueSetDef;
    valueSetDef = newValueSetDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__VALUE_SET_DEF, oldValueSetDef, newValueSetDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValueSetDef(valueSetDef newValueSetDef)
  {
    if (newValueSetDef != valueSetDef)
    {
      NotificationChain msgs = null;
      if (valueSetDef != null)
        msgs = ((InternalEObject)valueSetDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_UNITS_ENUM__VALUE_SET_DEF, null, msgs);
      if (newValueSetDef != null)
        msgs = ((InternalEObject)newValueSetDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_UNITS_ENUM__VALUE_SET_DEF, null, msgs);
      msgs = basicSetValueSetDef(newValueSetDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__VALUE_SET_DEF, newValueSetDef, newValueSetDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scaledRangeDef getScaledRangeDef()
  {
    return scaledRangeDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetScaledRangeDef(scaledRangeDef newScaledRangeDef, NotificationChain msgs)
  {
    scaledRangeDef oldScaledRangeDef = scaledRangeDef;
    scaledRangeDef = newScaledRangeDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__SCALED_RANGE_DEF, oldScaledRangeDef, newScaledRangeDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScaledRangeDef(scaledRangeDef newScaledRangeDef)
  {
    if (newScaledRangeDef != scaledRangeDef)
    {
      NotificationChain msgs = null;
      if (scaledRangeDef != null)
        msgs = ((InternalEObject)scaledRangeDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_UNITS_ENUM__SCALED_RANGE_DEF, null, msgs);
      if (newScaledRangeDef != null)
        msgs = ((InternalEObject)newScaledRangeDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_UNITS_ENUM__SCALED_RANGE_DEF, null, msgs);
      msgs = basicSetScaledRangeDef(newScaledRangeDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_UNITS_ENUM__SCALED_RANGE_DEF, newScaledRangeDef, newScaledRangeDef));
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
      case CjsidlPackage.TAGGED_UNITS_ENUM__TAG:
        return basicSetTag(null, msgs);
      case CjsidlPackage.TAGGED_UNITS_ENUM__SCOPED_TAG:
        return basicSetScopedTag(null, msgs);
      case CjsidlPackage.TAGGED_UNITS_ENUM__TYPE:
        return basicSetType(null, msgs);
      case CjsidlPackage.TAGGED_UNITS_ENUM__VALUE_SET_DEF:
        return basicSetValueSetDef(null, msgs);
      case CjsidlPackage.TAGGED_UNITS_ENUM__SCALED_RANGE_DEF:
        return basicSetScaledRangeDef(null, msgs);
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
      case CjsidlPackage.TAGGED_UNITS_ENUM__CONST_TAG:
        return getConst_tag();
      case CjsidlPackage.TAGGED_UNITS_ENUM__TAG:
        return getTag();
      case CjsidlPackage.TAGGED_UNITS_ENUM__SCOPED_TAG:
        return getScopedTag();
      case CjsidlPackage.TAGGED_UNITS_ENUM__NAME:
        return getName();
      case CjsidlPackage.TAGGED_UNITS_ENUM__TYPE:
        return getType();
      case CjsidlPackage.TAGGED_UNITS_ENUM__FIELD_UNIT:
        return getFieldUnit();
      case CjsidlPackage.TAGGED_UNITS_ENUM__VALUE_SET_DEF:
        return getValueSetDef();
      case CjsidlPackage.TAGGED_UNITS_ENUM__SCALED_RANGE_DEF:
        return getScaledRangeDef();
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
      case CjsidlPackage.TAGGED_UNITS_ENUM__CONST_TAG:
        setConst_tag((String)newValue);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__TAG:
        setTag((constReference)newValue);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__SCOPED_TAG:
        setScopedTag((scopedConstId)newValue);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__TYPE:
        setType((simpleNumericType)newValue);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__FIELD_UNIT:
        setFieldUnit((UNIT)newValue);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__VALUE_SET_DEF:
        setValueSetDef((valueSetDef)newValue);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__SCALED_RANGE_DEF:
        setScaledRangeDef((scaledRangeDef)newValue);
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
      case CjsidlPackage.TAGGED_UNITS_ENUM__CONST_TAG:
        setConst_tag(CONST_TAG_EDEFAULT);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__TAG:
        setTag((constReference)null);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__SCOPED_TAG:
        setScopedTag((scopedConstId)null);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__TYPE:
        setType((simpleNumericType)null);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__FIELD_UNIT:
        setFieldUnit(FIELD_UNIT_EDEFAULT);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__VALUE_SET_DEF:
        setValueSetDef((valueSetDef)null);
        return;
      case CjsidlPackage.TAGGED_UNITS_ENUM__SCALED_RANGE_DEF:
        setScaledRangeDef((scaledRangeDef)null);
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
      case CjsidlPackage.TAGGED_UNITS_ENUM__CONST_TAG:
        return CONST_TAG_EDEFAULT == null ? const_tag != null : !CONST_TAG_EDEFAULT.equals(const_tag);
      case CjsidlPackage.TAGGED_UNITS_ENUM__TAG:
        return tag != null;
      case CjsidlPackage.TAGGED_UNITS_ENUM__SCOPED_TAG:
        return scopedTag != null;
      case CjsidlPackage.TAGGED_UNITS_ENUM__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.TAGGED_UNITS_ENUM__TYPE:
        return type != null;
      case CjsidlPackage.TAGGED_UNITS_ENUM__FIELD_UNIT:
        return fieldUnit != FIELD_UNIT_EDEFAULT;
      case CjsidlPackage.TAGGED_UNITS_ENUM__VALUE_SET_DEF:
        return valueSetDef != null;
      case CjsidlPackage.TAGGED_UNITS_ENUM__SCALED_RANGE_DEF:
        return scaledRangeDef != null;
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
    result.append(" (const_tag: ");
    result.append(const_tag);
    result.append(", name: ");
    result.append(name);
    result.append(", fieldUnit: ");
    result.append(fieldUnit);
    result.append(')');
    return result.toString();
  }

} //taggedUnitsEnumImpl
