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
import org.jts.eclipse.cjsidl.scopedEventType;
import org.jts.eclipse.cjsidl.transParam;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>trans Param</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transParamImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transParamImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transParamImpl#getScopedEventType <em>Scoped Event Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transParamImpl#getUnsignedType <em>Unsigned Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transParamImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class transParamImpl extends MinimalEObjectImpl.Container implements transParam
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
   * The cached value of the '{@link #getType() <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected EObject type;

  /**
   * The cached value of the '{@link #getScopedEventType() <em>Scoped Event Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScopedEventType()
   * @generated
   * @ordered
   */
  protected scopedEventType scopedEventType;

  /**
   * The default value of the '{@link #getUnsignedType() <em>Unsigned Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnsignedType()
   * @generated
   * @ordered
   */
  protected static final String UNSIGNED_TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUnsignedType() <em>Unsigned Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnsignedType()
   * @generated
   * @ordered
   */
  protected String unsignedType = UNSIGNED_TYPE_EDEFAULT;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected transParamImpl()
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
    return CjsidlPackage.eINSTANCE.gettransParam();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANS_PARAM__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getType()
  {
    if (type != null && type.eIsProxy())
    {
      InternalEObject oldType = (InternalEObject)type;
      type = eResolveProxy(oldType);
      if (type != oldType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CjsidlPackage.TRANS_PARAM__TYPE, oldType, type));
      }
    }
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject basicGetType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(EObject newType)
  {
    EObject oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANS_PARAM__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scopedEventType getScopedEventType()
  {
    return scopedEventType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetScopedEventType(scopedEventType newScopedEventType, NotificationChain msgs)
  {
    scopedEventType oldScopedEventType = scopedEventType;
    scopedEventType = newScopedEventType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANS_PARAM__SCOPED_EVENT_TYPE, oldScopedEventType, newScopedEventType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScopedEventType(scopedEventType newScopedEventType)
  {
    if (newScopedEventType != scopedEventType)
    {
      NotificationChain msgs = null;
      if (scopedEventType != null)
        msgs = ((InternalEObject)scopedEventType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANS_PARAM__SCOPED_EVENT_TYPE, null, msgs);
      if (newScopedEventType != null)
        msgs = ((InternalEObject)newScopedEventType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANS_PARAM__SCOPED_EVENT_TYPE, null, msgs);
      msgs = basicSetScopedEventType(newScopedEventType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANS_PARAM__SCOPED_EVENT_TYPE, newScopedEventType, newScopedEventType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUnsignedType()
  {
    return unsignedType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnsignedType(String newUnsignedType)
  {
    String oldUnsignedType = unsignedType;
    unsignedType = newUnsignedType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANS_PARAM__UNSIGNED_TYPE, oldUnsignedType, unsignedType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANS_PARAM__NAME, oldName, name));
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
      case CjsidlPackage.TRANS_PARAM__SCOPED_EVENT_TYPE:
        return basicSetScopedEventType(null, msgs);
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
      case CjsidlPackage.TRANS_PARAM__COMMENT:
        return getComment();
      case CjsidlPackage.TRANS_PARAM__TYPE:
        if (resolve) return getType();
        return basicGetType();
      case CjsidlPackage.TRANS_PARAM__SCOPED_EVENT_TYPE:
        return getScopedEventType();
      case CjsidlPackage.TRANS_PARAM__UNSIGNED_TYPE:
        return getUnsignedType();
      case CjsidlPackage.TRANS_PARAM__NAME:
        return getName();
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
      case CjsidlPackage.TRANS_PARAM__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.TRANS_PARAM__TYPE:
        setType((EObject)newValue);
        return;
      case CjsidlPackage.TRANS_PARAM__SCOPED_EVENT_TYPE:
        setScopedEventType((scopedEventType)newValue);
        return;
      case CjsidlPackage.TRANS_PARAM__UNSIGNED_TYPE:
        setUnsignedType((String)newValue);
        return;
      case CjsidlPackage.TRANS_PARAM__NAME:
        setName((String)newValue);
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
      case CjsidlPackage.TRANS_PARAM__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.TRANS_PARAM__TYPE:
        setType((EObject)null);
        return;
      case CjsidlPackage.TRANS_PARAM__SCOPED_EVENT_TYPE:
        setScopedEventType((scopedEventType)null);
        return;
      case CjsidlPackage.TRANS_PARAM__UNSIGNED_TYPE:
        setUnsignedType(UNSIGNED_TYPE_EDEFAULT);
        return;
      case CjsidlPackage.TRANS_PARAM__NAME:
        setName(NAME_EDEFAULT);
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
      case CjsidlPackage.TRANS_PARAM__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.TRANS_PARAM__TYPE:
        return type != null;
      case CjsidlPackage.TRANS_PARAM__SCOPED_EVENT_TYPE:
        return scopedEventType != null;
      case CjsidlPackage.TRANS_PARAM__UNSIGNED_TYPE:
        return UNSIGNED_TYPE_EDEFAULT == null ? unsignedType != null : !UNSIGNED_TYPE_EDEFAULT.equals(unsignedType);
      case CjsidlPackage.TRANS_PARAM__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
    result.append(", unsignedType: ");
    result.append(unsignedType);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //transParamImpl
