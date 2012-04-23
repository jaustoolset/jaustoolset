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
import org.jts.eclipse.cjsidl.subField;
import org.jts.eclipse.cjsidl.valueSetDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>sub Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.subFieldImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.subFieldImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.subFieldImpl#getFromIndex <em>From Index</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.subFieldImpl#getToIndex <em>To Index</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.subFieldImpl#getValueSet <em>Value Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class subFieldImpl extends MinimalEObjectImpl.Container implements subField
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
   * The default value of the '{@link #getFromIndex() <em>From Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFromIndex()
   * @generated
   * @ordered
   */
  protected static final String FROM_INDEX_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFromIndex() <em>From Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFromIndex()
   * @generated
   * @ordered
   */
  protected String fromIndex = FROM_INDEX_EDEFAULT;

  /**
   * The default value of the '{@link #getToIndex() <em>To Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getToIndex()
   * @generated
   * @ordered
   */
  protected static final String TO_INDEX_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getToIndex() <em>To Index</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getToIndex()
   * @generated
   * @ordered
   */
  protected String toIndex = TO_INDEX_EDEFAULT;

  /**
   * The cached value of the '{@link #getValueSet() <em>Value Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValueSet()
   * @generated
   * @ordered
   */
  protected valueSetDef valueSet;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected subFieldImpl()
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
    return CjsidlPackage.eINSTANCE.getsubField();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SUB_FIELD__COMMENT, oldComment, comment));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SUB_FIELD__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFromIndex()
  {
    return fromIndex;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFromIndex(String newFromIndex)
  {
    String oldFromIndex = fromIndex;
    fromIndex = newFromIndex;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SUB_FIELD__FROM_INDEX, oldFromIndex, fromIndex));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getToIndex()
  {
    return toIndex;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setToIndex(String newToIndex)
  {
    String oldToIndex = toIndex;
    toIndex = newToIndex;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SUB_FIELD__TO_INDEX, oldToIndex, toIndex));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public valueSetDef getValueSet()
  {
    return valueSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetValueSet(valueSetDef newValueSet, NotificationChain msgs)
  {
    valueSetDef oldValueSet = valueSet;
    valueSet = newValueSet;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.SUB_FIELD__VALUE_SET, oldValueSet, newValueSet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValueSet(valueSetDef newValueSet)
  {
    if (newValueSet != valueSet)
    {
      NotificationChain msgs = null;
      if (valueSet != null)
        msgs = ((InternalEObject)valueSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SUB_FIELD__VALUE_SET, null, msgs);
      if (newValueSet != null)
        msgs = ((InternalEObject)newValueSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SUB_FIELD__VALUE_SET, null, msgs);
      msgs = basicSetValueSet(newValueSet, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SUB_FIELD__VALUE_SET, newValueSet, newValueSet));
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
      case CjsidlPackage.SUB_FIELD__VALUE_SET:
        return basicSetValueSet(null, msgs);
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
      case CjsidlPackage.SUB_FIELD__COMMENT:
        return getComment();
      case CjsidlPackage.SUB_FIELD__NAME:
        return getName();
      case CjsidlPackage.SUB_FIELD__FROM_INDEX:
        return getFromIndex();
      case CjsidlPackage.SUB_FIELD__TO_INDEX:
        return getToIndex();
      case CjsidlPackage.SUB_FIELD__VALUE_SET:
        return getValueSet();
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
      case CjsidlPackage.SUB_FIELD__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.SUB_FIELD__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.SUB_FIELD__FROM_INDEX:
        setFromIndex((String)newValue);
        return;
      case CjsidlPackage.SUB_FIELD__TO_INDEX:
        setToIndex((String)newValue);
        return;
      case CjsidlPackage.SUB_FIELD__VALUE_SET:
        setValueSet((valueSetDef)newValue);
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
      case CjsidlPackage.SUB_FIELD__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.SUB_FIELD__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.SUB_FIELD__FROM_INDEX:
        setFromIndex(FROM_INDEX_EDEFAULT);
        return;
      case CjsidlPackage.SUB_FIELD__TO_INDEX:
        setToIndex(TO_INDEX_EDEFAULT);
        return;
      case CjsidlPackage.SUB_FIELD__VALUE_SET:
        setValueSet((valueSetDef)null);
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
      case CjsidlPackage.SUB_FIELD__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.SUB_FIELD__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.SUB_FIELD__FROM_INDEX:
        return FROM_INDEX_EDEFAULT == null ? fromIndex != null : !FROM_INDEX_EDEFAULT.equals(fromIndex);
      case CjsidlPackage.SUB_FIELD__TO_INDEX:
        return TO_INDEX_EDEFAULT == null ? toIndex != null : !TO_INDEX_EDEFAULT.equals(toIndex);
      case CjsidlPackage.SUB_FIELD__VALUE_SET:
        return valueSet != null;
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
    result.append(", fromIndex: ");
    result.append(fromIndex);
    result.append(", toIndex: ");
    result.append(toIndex);
    result.append(')');
    return result.toString();
  }

} //subFieldImpl
