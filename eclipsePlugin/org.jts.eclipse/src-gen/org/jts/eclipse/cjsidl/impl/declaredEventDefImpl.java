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
import org.jts.eclipse.cjsidl.declaredEventDef;
import org.jts.eclipse.cjsidl.eventDef;
import org.jts.eclipse.cjsidl.scopedEventType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>declared Event Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredEventDefImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredEventDefImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredEventDefImpl#getScopedEventType <em>Scoped Event Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.declaredEventDefImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class declaredEventDefImpl extends MinimalEObjectImpl.Container implements declaredEventDef
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
  protected eventDef type;

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
  protected declaredEventDefImpl()
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
    return CjsidlPackage.eINSTANCE.getdeclaredEventDef();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.DECLARED_EVENT_DEF__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public eventDef getType()
  {
    if (type != null && type.eIsProxy())
    {
      InternalEObject oldType = (InternalEObject)type;
      type = (eventDef)eResolveProxy(oldType);
      if (type != oldType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CjsidlPackage.DECLARED_EVENT_DEF__TYPE, oldType, type));
      }
    }
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public eventDef basicGetType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(eventDef newType)
  {
    eventDef oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.DECLARED_EVENT_DEF__TYPE, oldType, type));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.DECLARED_EVENT_DEF__SCOPED_EVENT_TYPE, oldScopedEventType, newScopedEventType);
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
        msgs = ((InternalEObject)scopedEventType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.DECLARED_EVENT_DEF__SCOPED_EVENT_TYPE, null, msgs);
      if (newScopedEventType != null)
        msgs = ((InternalEObject)newScopedEventType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.DECLARED_EVENT_DEF__SCOPED_EVENT_TYPE, null, msgs);
      msgs = basicSetScopedEventType(newScopedEventType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.DECLARED_EVENT_DEF__SCOPED_EVENT_TYPE, newScopedEventType, newScopedEventType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.DECLARED_EVENT_DEF__NAME, oldName, name));
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
      case CjsidlPackage.DECLARED_EVENT_DEF__SCOPED_EVENT_TYPE:
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
      case CjsidlPackage.DECLARED_EVENT_DEF__COMMENT:
        return getComment();
      case CjsidlPackage.DECLARED_EVENT_DEF__TYPE:
        if (resolve) return getType();
        return basicGetType();
      case CjsidlPackage.DECLARED_EVENT_DEF__SCOPED_EVENT_TYPE:
        return getScopedEventType();
      case CjsidlPackage.DECLARED_EVENT_DEF__NAME:
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
      case CjsidlPackage.DECLARED_EVENT_DEF__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.DECLARED_EVENT_DEF__TYPE:
        setType((eventDef)newValue);
        return;
      case CjsidlPackage.DECLARED_EVENT_DEF__SCOPED_EVENT_TYPE:
        setScopedEventType((scopedEventType)newValue);
        return;
      case CjsidlPackage.DECLARED_EVENT_DEF__NAME:
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
      case CjsidlPackage.DECLARED_EVENT_DEF__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.DECLARED_EVENT_DEF__TYPE:
        setType((eventDef)null);
        return;
      case CjsidlPackage.DECLARED_EVENT_DEF__SCOPED_EVENT_TYPE:
        setScopedEventType((scopedEventType)null);
        return;
      case CjsidlPackage.DECLARED_EVENT_DEF__NAME:
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
      case CjsidlPackage.DECLARED_EVENT_DEF__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.DECLARED_EVENT_DEF__TYPE:
        return type != null;
      case CjsidlPackage.DECLARED_EVENT_DEF__SCOPED_EVENT_TYPE:
        return scopedEventType != null;
      case CjsidlPackage.DECLARED_EVENT_DEF__NAME:
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
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //declaredEventDefImpl
