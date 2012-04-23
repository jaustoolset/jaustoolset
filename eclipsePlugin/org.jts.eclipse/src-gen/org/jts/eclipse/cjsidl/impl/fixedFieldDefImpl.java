/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.UNIT;
import org.jts.eclipse.cjsidl.fixedFieldDef;
import org.jts.eclipse.cjsidl.simpleNumericType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>fixed Field Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedFieldDefImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedFieldDefImpl#getOptional <em>Optional</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedFieldDefImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedFieldDefImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedFieldDefImpl#getFieldUnit <em>Field Unit</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedFieldDefImpl#getValueRange <em>Value Range</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class fixedFieldDefImpl extends MinimalEObjectImpl.Container implements fixedFieldDef
{
  /**
   * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected static final String COMMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected String comment = COMMENT_EDEFAULT;

  /**
   * The default value of the '{@link #getOptional() <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOptional()
   * @generated
   * @ordered
   */
  protected static final String OPTIONAL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOptional() <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOptional()
   * @generated
   * @ordered
   */
  protected String optional = OPTIONAL_EDEFAULT;

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
   * The cached value of the '{@link #getValueRange() <em>Value Range</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValueRange()
   * @generated
   * @ordered
   */
  protected EObject valueRange;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected fixedFieldDefImpl()
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
    return CjsidlPackage.eINSTANCE.getfixedFieldDef();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    String oldComment = comment;
    comment = newComment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_FIELD_DEF__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOptional()
  {
    return optional;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOptional(String newOptional)
  {
    String oldOptional = optional;
    optional = newOptional;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_FIELD_DEF__OPTIONAL, oldOptional, optional));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_FIELD_DEF__TYPE, oldType, newType);
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
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FIXED_FIELD_DEF__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FIXED_FIELD_DEF__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_FIELD_DEF__TYPE, newType, newType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_FIELD_DEF__NAME, oldName, name));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_FIELD_DEF__FIELD_UNIT, oldFieldUnit, fieldUnit));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getValueRange()
  {
    return valueRange;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetValueRange(EObject newValueRange, NotificationChain msgs)
  {
    EObject oldValueRange = valueRange;
    valueRange = newValueRange;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_FIELD_DEF__VALUE_RANGE, oldValueRange, newValueRange);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValueRange(EObject newValueRange)
  {
    if (newValueRange != valueRange)
    {
      NotificationChain msgs = null;
      if (valueRange != null)
        msgs = ((InternalEObject)valueRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FIXED_FIELD_DEF__VALUE_RANGE, null, msgs);
      if (newValueRange != null)
        msgs = ((InternalEObject)newValueRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FIXED_FIELD_DEF__VALUE_RANGE, null, msgs);
      msgs = basicSetValueRange(newValueRange, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_FIELD_DEF__VALUE_RANGE, newValueRange, newValueRange));
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
      case CjsidlPackage.FIXED_FIELD_DEF__TYPE:
        return basicSetType(null, msgs);
      case CjsidlPackage.FIXED_FIELD_DEF__VALUE_RANGE:
        return basicSetValueRange(null, msgs);
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
      case CjsidlPackage.FIXED_FIELD_DEF__COMMENT:
        return getComment();
      case CjsidlPackage.FIXED_FIELD_DEF__OPTIONAL:
        return getOptional();
      case CjsidlPackage.FIXED_FIELD_DEF__TYPE:
        return getType();
      case CjsidlPackage.FIXED_FIELD_DEF__NAME:
        return getName();
      case CjsidlPackage.FIXED_FIELD_DEF__FIELD_UNIT:
        return getFieldUnit();
      case CjsidlPackage.FIXED_FIELD_DEF__VALUE_RANGE:
        return getValueRange();
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
      case CjsidlPackage.FIXED_FIELD_DEF__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.FIXED_FIELD_DEF__OPTIONAL:
        setOptional((String)newValue);
        return;
      case CjsidlPackage.FIXED_FIELD_DEF__TYPE:
        setType((simpleNumericType)newValue);
        return;
      case CjsidlPackage.FIXED_FIELD_DEF__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.FIXED_FIELD_DEF__FIELD_UNIT:
        setFieldUnit((UNIT)newValue);
        return;
      case CjsidlPackage.FIXED_FIELD_DEF__VALUE_RANGE:
        setValueRange((EObject)newValue);
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
      case CjsidlPackage.FIXED_FIELD_DEF__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.FIXED_FIELD_DEF__OPTIONAL:
        setOptional(OPTIONAL_EDEFAULT);
        return;
      case CjsidlPackage.FIXED_FIELD_DEF__TYPE:
        setType((simpleNumericType)null);
        return;
      case CjsidlPackage.FIXED_FIELD_DEF__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.FIXED_FIELD_DEF__FIELD_UNIT:
        setFieldUnit(FIELD_UNIT_EDEFAULT);
        return;
      case CjsidlPackage.FIXED_FIELD_DEF__VALUE_RANGE:
        setValueRange((EObject)null);
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
      case CjsidlPackage.FIXED_FIELD_DEF__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.FIXED_FIELD_DEF__OPTIONAL:
        return OPTIONAL_EDEFAULT == null ? optional != null : !OPTIONAL_EDEFAULT.equals(optional);
      case CjsidlPackage.FIXED_FIELD_DEF__TYPE:
        return type != null;
      case CjsidlPackage.FIXED_FIELD_DEF__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.FIXED_FIELD_DEF__FIELD_UNIT:
        return fieldUnit != FIELD_UNIT_EDEFAULT;
      case CjsidlPackage.FIXED_FIELD_DEF__VALUE_RANGE:
        return valueRange != null;
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
    result.append(" (comment: ");
    result.append(comment);
    result.append(", optional: ");
    result.append(optional);
    result.append(", name: ");
    result.append(name);
    result.append(", fieldUnit: ");
    result.append(fieldUnit);
    result.append(')');
    return result.toString();
  }

} //fixedFieldDefImpl
