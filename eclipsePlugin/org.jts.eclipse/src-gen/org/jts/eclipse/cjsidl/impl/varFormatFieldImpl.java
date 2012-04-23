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
import org.jts.eclipse.cjsidl.formatEnumDef;
import org.jts.eclipse.cjsidl.valueRange;
import org.jts.eclipse.cjsidl.varFormatField;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>var Format Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.varFormatFieldImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.varFormatFieldImpl#getOptional <em>Optional</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.varFormatFieldImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.varFormatFieldImpl#getCountComment <em>Count Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.varFormatFieldImpl#getUnits <em>Units</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.varFormatFieldImpl#getCountRange <em>Count Range</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.varFormatFieldImpl#getFormatField <em>Format Field</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class varFormatFieldImpl extends MinimalEObjectImpl.Container implements varFormatField
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
   * The default value of the '{@link #getCountComment() <em>Count Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCountComment()
   * @generated
   * @ordered
   */
  protected static final String COUNT_COMMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCountComment() <em>Count Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCountComment()
   * @generated
   * @ordered
   */
  protected String countComment = COUNT_COMMENT_EDEFAULT;

  /**
   * The default value of the '{@link #getUnits() <em>Units</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnits()
   * @generated
   * @ordered
   */
  protected static final String UNITS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUnits() <em>Units</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnits()
   * @generated
   * @ordered
   */
  protected String units = UNITS_EDEFAULT;

  /**
   * The cached value of the '{@link #getCountRange() <em>Count Range</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCountRange()
   * @generated
   * @ordered
   */
  protected valueRange countRange;

  /**
   * The cached value of the '{@link #getFormatField() <em>Format Field</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFormatField()
   * @generated
   * @ordered
   */
  protected EList<formatEnumDef> formatField;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected varFormatFieldImpl()
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
    return CjsidlPackage.eINSTANCE.getvarFormatField();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VAR_FORMAT_FIELD__COMMENT, oldComment, comment));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VAR_FORMAT_FIELD__OPTIONAL, oldOptional, optional));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VAR_FORMAT_FIELD__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCountComment()
  {
    return countComment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCountComment(String newCountComment)
  {
    String oldCountComment = countComment;
    countComment = newCountComment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VAR_FORMAT_FIELD__COUNT_COMMENT, oldCountComment, countComment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUnits()
  {
    return units;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnits(String newUnits)
  {
    String oldUnits = units;
    units = newUnits;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VAR_FORMAT_FIELD__UNITS, oldUnits, units));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public valueRange getCountRange()
  {
    return countRange;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCountRange(valueRange newCountRange, NotificationChain msgs)
  {
    valueRange oldCountRange = countRange;
    countRange = newCountRange;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.VAR_FORMAT_FIELD__COUNT_RANGE, oldCountRange, newCountRange);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCountRange(valueRange newCountRange)
  {
    if (newCountRange != countRange)
    {
      NotificationChain msgs = null;
      if (countRange != null)
        msgs = ((InternalEObject)countRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.VAR_FORMAT_FIELD__COUNT_RANGE, null, msgs);
      if (newCountRange != null)
        msgs = ((InternalEObject)newCountRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.VAR_FORMAT_FIELD__COUNT_RANGE, null, msgs);
      msgs = basicSetCountRange(newCountRange, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VAR_FORMAT_FIELD__COUNT_RANGE, newCountRange, newCountRange));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<formatEnumDef> getFormatField()
  {
    if (formatField == null)
    {
      formatField = new EObjectContainmentEList<formatEnumDef>(formatEnumDef.class, this, CjsidlPackage.VAR_FORMAT_FIELD__FORMAT_FIELD);
    }
    return formatField;
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
      case CjsidlPackage.VAR_FORMAT_FIELD__COUNT_RANGE:
        return basicSetCountRange(null, msgs);
      case CjsidlPackage.VAR_FORMAT_FIELD__FORMAT_FIELD:
        return ((InternalEList<?>)getFormatField()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.VAR_FORMAT_FIELD__COMMENT:
        return getComment();
      case CjsidlPackage.VAR_FORMAT_FIELD__OPTIONAL:
        return getOptional();
      case CjsidlPackage.VAR_FORMAT_FIELD__NAME:
        return getName();
      case CjsidlPackage.VAR_FORMAT_FIELD__COUNT_COMMENT:
        return getCountComment();
      case CjsidlPackage.VAR_FORMAT_FIELD__UNITS:
        return getUnits();
      case CjsidlPackage.VAR_FORMAT_FIELD__COUNT_RANGE:
        return getCountRange();
      case CjsidlPackage.VAR_FORMAT_FIELD__FORMAT_FIELD:
        return getFormatField();
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
      case CjsidlPackage.VAR_FORMAT_FIELD__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__OPTIONAL:
        setOptional((String)newValue);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__COUNT_COMMENT:
        setCountComment((String)newValue);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__UNITS:
        setUnits((String)newValue);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__COUNT_RANGE:
        setCountRange((valueRange)newValue);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__FORMAT_FIELD:
        getFormatField().clear();
        getFormatField().addAll((Collection<? extends formatEnumDef>)newValue);
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
      case CjsidlPackage.VAR_FORMAT_FIELD__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__OPTIONAL:
        setOptional(OPTIONAL_EDEFAULT);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__COUNT_COMMENT:
        setCountComment(COUNT_COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__UNITS:
        setUnits(UNITS_EDEFAULT);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__COUNT_RANGE:
        setCountRange((valueRange)null);
        return;
      case CjsidlPackage.VAR_FORMAT_FIELD__FORMAT_FIELD:
        getFormatField().clear();
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
      case CjsidlPackage.VAR_FORMAT_FIELD__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.VAR_FORMAT_FIELD__OPTIONAL:
        return OPTIONAL_EDEFAULT == null ? optional != null : !OPTIONAL_EDEFAULT.equals(optional);
      case CjsidlPackage.VAR_FORMAT_FIELD__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.VAR_FORMAT_FIELD__COUNT_COMMENT:
        return COUNT_COMMENT_EDEFAULT == null ? countComment != null : !COUNT_COMMENT_EDEFAULT.equals(countComment);
      case CjsidlPackage.VAR_FORMAT_FIELD__UNITS:
        return UNITS_EDEFAULT == null ? units != null : !UNITS_EDEFAULT.equals(units);
      case CjsidlPackage.VAR_FORMAT_FIELD__COUNT_RANGE:
        return countRange != null;
      case CjsidlPackage.VAR_FORMAT_FIELD__FORMAT_FIELD:
        return formatField != null && !formatField.isEmpty();
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
    result.append(", countComment: ");
    result.append(countComment);
    result.append(", units: ");
    result.append(units);
    result.append(')');
    return result.toString();
  }

} //varFormatFieldImpl
