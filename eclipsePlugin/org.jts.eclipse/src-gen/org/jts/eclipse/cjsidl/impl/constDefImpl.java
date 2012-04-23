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
import org.jts.eclipse.cjsidl.constDef;
import org.jts.eclipse.cjsidl.simpleNumericType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>const Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.constDefImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.constDefImpl#getConstType <em>Const Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.constDefImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.constDefImpl#getConstValue <em>Const Value</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.constDefImpl#getFieldUnits <em>Field Units</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class constDefImpl extends MinimalEObjectImpl.Container implements constDef
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
   * The cached value of the '{@link #getConstType() <em>Const Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstType()
   * @generated
   * @ordered
   */
  protected simpleNumericType constType;

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
   * The default value of the '{@link #getConstValue() <em>Const Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstValue()
   * @generated
   * @ordered
   */
  protected static final String CONST_VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getConstValue() <em>Const Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstValue()
   * @generated
   * @ordered
   */
  protected String constValue = CONST_VALUE_EDEFAULT;

  /**
   * The default value of the '{@link #getFieldUnits() <em>Field Units</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFieldUnits()
   * @generated
   * @ordered
   */
  protected static final UNIT FIELD_UNITS_EDEFAULT = UNIT.METER;

  /**
   * The cached value of the '{@link #getFieldUnits() <em>Field Units</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFieldUnits()
   * @generated
   * @ordered
   */
  protected UNIT fieldUnits = FIELD_UNITS_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected constDefImpl()
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
    return CjsidlPackage.eINSTANCE.getconstDef();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.CONST_DEF__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public simpleNumericType getConstType()
  {
    return constType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstType(simpleNumericType newConstType, NotificationChain msgs)
  {
    simpleNumericType oldConstType = constType;
    constType = newConstType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.CONST_DEF__CONST_TYPE, oldConstType, newConstType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstType(simpleNumericType newConstType)
  {
    if (newConstType != constType)
    {
      NotificationChain msgs = null;
      if (constType != null)
        msgs = ((InternalEObject)constType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.CONST_DEF__CONST_TYPE, null, msgs);
      if (newConstType != null)
        msgs = ((InternalEObject)newConstType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.CONST_DEF__CONST_TYPE, null, msgs);
      msgs = basicSetConstType(newConstType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.CONST_DEF__CONST_TYPE, newConstType, newConstType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.CONST_DEF__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getConstValue()
  {
    return constValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstValue(String newConstValue)
  {
    String oldConstValue = constValue;
    constValue = newConstValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.CONST_DEF__CONST_VALUE, oldConstValue, constValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UNIT getFieldUnits()
  {
    return fieldUnits;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFieldUnits(UNIT newFieldUnits)
  {
    UNIT oldFieldUnits = fieldUnits;
    fieldUnits = newFieldUnits == null ? FIELD_UNITS_EDEFAULT : newFieldUnits;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.CONST_DEF__FIELD_UNITS, oldFieldUnits, fieldUnits));
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
      case CjsidlPackage.CONST_DEF__CONST_TYPE:
        return basicSetConstType(null, msgs);
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
      case CjsidlPackage.CONST_DEF__COMMENT:
        return getComment();
      case CjsidlPackage.CONST_DEF__CONST_TYPE:
        return getConstType();
      case CjsidlPackage.CONST_DEF__NAME:
        return getName();
      case CjsidlPackage.CONST_DEF__CONST_VALUE:
        return getConstValue();
      case CjsidlPackage.CONST_DEF__FIELD_UNITS:
        return getFieldUnits();
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
      case CjsidlPackage.CONST_DEF__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.CONST_DEF__CONST_TYPE:
        setConstType((simpleNumericType)newValue);
        return;
      case CjsidlPackage.CONST_DEF__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.CONST_DEF__CONST_VALUE:
        setConstValue((String)newValue);
        return;
      case CjsidlPackage.CONST_DEF__FIELD_UNITS:
        setFieldUnits((UNIT)newValue);
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
      case CjsidlPackage.CONST_DEF__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.CONST_DEF__CONST_TYPE:
        setConstType((simpleNumericType)null);
        return;
      case CjsidlPackage.CONST_DEF__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.CONST_DEF__CONST_VALUE:
        setConstValue(CONST_VALUE_EDEFAULT);
        return;
      case CjsidlPackage.CONST_DEF__FIELD_UNITS:
        setFieldUnits(FIELD_UNITS_EDEFAULT);
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
      case CjsidlPackage.CONST_DEF__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.CONST_DEF__CONST_TYPE:
        return constType != null;
      case CjsidlPackage.CONST_DEF__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.CONST_DEF__CONST_VALUE:
        return CONST_VALUE_EDEFAULT == null ? constValue != null : !CONST_VALUE_EDEFAULT.equals(constValue);
      case CjsidlPackage.CONST_DEF__FIELD_UNITS:
        return fieldUnits != FIELD_UNITS_EDEFAULT;
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
    result.append(", name: ");
    result.append(name);
    result.append(", constValue: ");
    result.append(constValue);
    result.append(", fieldUnits: ");
    result.append(fieldUnits);
    result.append(')');
    return result.toString();
  }

} //constDefImpl
