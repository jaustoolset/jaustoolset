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
import org.jts.eclipse.cjsidl.FIELD_FORMAT;
import org.jts.eclipse.cjsidl.constReference;
import org.jts.eclipse.cjsidl.formatEnumDef;
import org.jts.eclipse.cjsidl.scopedConstId;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>format Enum Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.formatEnumDefImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.formatEnumDefImpl#getConstRef <em>Const Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.formatEnumDefImpl#getConstScopedRef <em>Const Scoped Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.formatEnumDefImpl#getFieldFormat <em>Field Format</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.formatEnumDefImpl#getFieldFormatStr <em>Field Format Str</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class formatEnumDefImpl extends MinimalEObjectImpl.Container implements formatEnumDef
{
  /**
   * The default value of the '{@link #getIndex() <em>Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndex()
   * @generated
   * @ordered
   */
  protected static final String INDEX_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIndex() <em>Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndex()
   * @generated
   * @ordered
   */
  protected String index = INDEX_EDEFAULT;

  /**
   * The cached value of the '{@link #getConstRef() <em>Const Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstRef()
   * @generated
   * @ordered
   */
  protected constReference constRef;

  /**
   * The cached value of the '{@link #getConstScopedRef() <em>Const Scoped Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstScopedRef()
   * @generated
   * @ordered
   */
  protected scopedConstId constScopedRef;

  /**
   * The default value of the '{@link #getFieldFormat() <em>Field Format</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFieldFormat()
   * @generated
   * @ordered
   */
  protected static final FIELD_FORMAT FIELD_FORMAT_EDEFAULT = FIELD_FORMAT.AU;

  /**
   * The cached value of the '{@link #getFieldFormat() <em>Field Format</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFieldFormat()
   * @generated
   * @ordered
   */
  protected FIELD_FORMAT fieldFormat = FIELD_FORMAT_EDEFAULT;

  /**
   * The default value of the '{@link #getFieldFormatStr() <em>Field Format Str</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFieldFormatStr()
   * @generated
   * @ordered
   */
  protected static final String FIELD_FORMAT_STR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFieldFormatStr() <em>Field Format Str</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFieldFormatStr()
   * @generated
   * @ordered
   */
  protected String fieldFormatStr = FIELD_FORMAT_STR_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected formatEnumDefImpl()
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
    return CjsidlPackage.eINSTANCE.getformatEnumDef();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIndex()
  {
    return index;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIndex(String newIndex)
  {
    String oldIndex = index;
    index = newIndex;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FORMAT_ENUM_DEF__INDEX, oldIndex, index));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public constReference getConstRef()
  {
    return constRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstRef(constReference newConstRef, NotificationChain msgs)
  {
    constReference oldConstRef = constRef;
    constRef = newConstRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.FORMAT_ENUM_DEF__CONST_REF, oldConstRef, newConstRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstRef(constReference newConstRef)
  {
    if (newConstRef != constRef)
    {
      NotificationChain msgs = null;
      if (constRef != null)
        msgs = ((InternalEObject)constRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FORMAT_ENUM_DEF__CONST_REF, null, msgs);
      if (newConstRef != null)
        msgs = ((InternalEObject)newConstRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FORMAT_ENUM_DEF__CONST_REF, null, msgs);
      msgs = basicSetConstRef(newConstRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FORMAT_ENUM_DEF__CONST_REF, newConstRef, newConstRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scopedConstId getConstScopedRef()
  {
    return constScopedRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstScopedRef(scopedConstId newConstScopedRef, NotificationChain msgs)
  {
    scopedConstId oldConstScopedRef = constScopedRef;
    constScopedRef = newConstScopedRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.FORMAT_ENUM_DEF__CONST_SCOPED_REF, oldConstScopedRef, newConstScopedRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstScopedRef(scopedConstId newConstScopedRef)
  {
    if (newConstScopedRef != constScopedRef)
    {
      NotificationChain msgs = null;
      if (constScopedRef != null)
        msgs = ((InternalEObject)constScopedRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FORMAT_ENUM_DEF__CONST_SCOPED_REF, null, msgs);
      if (newConstScopedRef != null)
        msgs = ((InternalEObject)newConstScopedRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FORMAT_ENUM_DEF__CONST_SCOPED_REF, null, msgs);
      msgs = basicSetConstScopedRef(newConstScopedRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FORMAT_ENUM_DEF__CONST_SCOPED_REF, newConstScopedRef, newConstScopedRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FIELD_FORMAT getFieldFormat()
  {
    return fieldFormat;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFieldFormat(FIELD_FORMAT newFieldFormat)
  {
    FIELD_FORMAT oldFieldFormat = fieldFormat;
    fieldFormat = newFieldFormat == null ? FIELD_FORMAT_EDEFAULT : newFieldFormat;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FORMAT_ENUM_DEF__FIELD_FORMAT, oldFieldFormat, fieldFormat));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFieldFormatStr()
  {
    return fieldFormatStr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFieldFormatStr(String newFieldFormatStr)
  {
    String oldFieldFormatStr = fieldFormatStr;
    fieldFormatStr = newFieldFormatStr;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FORMAT_ENUM_DEF__FIELD_FORMAT_STR, oldFieldFormatStr, fieldFormatStr));
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
      case CjsidlPackage.FORMAT_ENUM_DEF__CONST_REF:
        return basicSetConstRef(null, msgs);
      case CjsidlPackage.FORMAT_ENUM_DEF__CONST_SCOPED_REF:
        return basicSetConstScopedRef(null, msgs);
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
      case CjsidlPackage.FORMAT_ENUM_DEF__INDEX:
        return getIndex();
      case CjsidlPackage.FORMAT_ENUM_DEF__CONST_REF:
        return getConstRef();
      case CjsidlPackage.FORMAT_ENUM_DEF__CONST_SCOPED_REF:
        return getConstScopedRef();
      case CjsidlPackage.FORMAT_ENUM_DEF__FIELD_FORMAT:
        return getFieldFormat();
      case CjsidlPackage.FORMAT_ENUM_DEF__FIELD_FORMAT_STR:
        return getFieldFormatStr();
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
      case CjsidlPackage.FORMAT_ENUM_DEF__INDEX:
        setIndex((String)newValue);
        return;
      case CjsidlPackage.FORMAT_ENUM_DEF__CONST_REF:
        setConstRef((constReference)newValue);
        return;
      case CjsidlPackage.FORMAT_ENUM_DEF__CONST_SCOPED_REF:
        setConstScopedRef((scopedConstId)newValue);
        return;
      case CjsidlPackage.FORMAT_ENUM_DEF__FIELD_FORMAT:
        setFieldFormat((FIELD_FORMAT)newValue);
        return;
      case CjsidlPackage.FORMAT_ENUM_DEF__FIELD_FORMAT_STR:
        setFieldFormatStr((String)newValue);
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
      case CjsidlPackage.FORMAT_ENUM_DEF__INDEX:
        setIndex(INDEX_EDEFAULT);
        return;
      case CjsidlPackage.FORMAT_ENUM_DEF__CONST_REF:
        setConstRef((constReference)null);
        return;
      case CjsidlPackage.FORMAT_ENUM_DEF__CONST_SCOPED_REF:
        setConstScopedRef((scopedConstId)null);
        return;
      case CjsidlPackage.FORMAT_ENUM_DEF__FIELD_FORMAT:
        setFieldFormat(FIELD_FORMAT_EDEFAULT);
        return;
      case CjsidlPackage.FORMAT_ENUM_DEF__FIELD_FORMAT_STR:
        setFieldFormatStr(FIELD_FORMAT_STR_EDEFAULT);
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
      case CjsidlPackage.FORMAT_ENUM_DEF__INDEX:
        return INDEX_EDEFAULT == null ? index != null : !INDEX_EDEFAULT.equals(index);
      case CjsidlPackage.FORMAT_ENUM_DEF__CONST_REF:
        return constRef != null;
      case CjsidlPackage.FORMAT_ENUM_DEF__CONST_SCOPED_REF:
        return constScopedRef != null;
      case CjsidlPackage.FORMAT_ENUM_DEF__FIELD_FORMAT:
        return fieldFormat != FIELD_FORMAT_EDEFAULT;
      case CjsidlPackage.FORMAT_ENUM_DEF__FIELD_FORMAT_STR:
        return FIELD_FORMAT_STR_EDEFAULT == null ? fieldFormatStr != null : !FIELD_FORMAT_STR_EDEFAULT.equals(fieldFormatStr);
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
    result.append(" (index: ");
    result.append(index);
    result.append(", fieldFormat: ");
    result.append(fieldFormat);
    result.append(", fieldFormatStr: ");
    result.append(fieldFormatStr);
    result.append(')');
    return result.toString();
  }

} //formatEnumDefImpl
