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

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.constReference;
import org.jts.eclipse.cjsidl.containerDef;
import org.jts.eclipse.cjsidl.containerRef;
import org.jts.eclipse.cjsidl.listDef;
import org.jts.eclipse.cjsidl.scopedConstId;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>list Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.listDefImpl#getCountComment <em>Count Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.listDefImpl#getMinCount <em>Min Count</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.listDefImpl#getMinCountRef <em>Min Count Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.listDefImpl#getMinCountScoped <em>Min Count Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.listDefImpl#getMaxCount <em>Max Count</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.listDefImpl#getMaxCountRef <em>Max Count Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.listDefImpl#getMaxCountScoped <em>Max Count Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.listDefImpl#getContainerRef <em>Container Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.listDefImpl#getContainerDef <em>Container Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class listDefImpl extends containerDefImpl implements listDef
{
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
   * The default value of the '{@link #getMinCount() <em>Min Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinCount()
   * @generated
   * @ordered
   */
  protected static final String MIN_COUNT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMinCount() <em>Min Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinCount()
   * @generated
   * @ordered
   */
  protected String minCount = MIN_COUNT_EDEFAULT;

  /**
   * The cached value of the '{@link #getMinCountRef() <em>Min Count Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinCountRef()
   * @generated
   * @ordered
   */
  protected constReference minCountRef;

  /**
   * The cached value of the '{@link #getMinCountScoped() <em>Min Count Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinCountScoped()
   * @generated
   * @ordered
   */
  protected scopedConstId minCountScoped;

  /**
   * The default value of the '{@link #getMaxCount() <em>Max Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMaxCount()
   * @generated
   * @ordered
   */
  protected static final String MAX_COUNT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMaxCount() <em>Max Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMaxCount()
   * @generated
   * @ordered
   */
  protected String maxCount = MAX_COUNT_EDEFAULT;

  /**
   * The cached value of the '{@link #getMaxCountRef() <em>Max Count Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMaxCountRef()
   * @generated
   * @ordered
   */
  protected constReference maxCountRef;

  /**
   * The cached value of the '{@link #getMaxCountScoped() <em>Max Count Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMaxCountScoped()
   * @generated
   * @ordered
   */
  protected scopedConstId maxCountScoped;

  /**
   * The cached value of the '{@link #getContainerRef() <em>Container Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContainerRef()
   * @generated
   * @ordered
   */
  protected containerRef containerRef;

  /**
   * The cached value of the '{@link #getContainerDef() <em>Container Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContainerDef()
   * @generated
   * @ordered
   */
  protected containerDef containerDef;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected listDefImpl()
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
    return CjsidlPackage.eINSTANCE.getlistDef();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__COUNT_COMMENT, oldCountComment, countComment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMinCount()
  {
    return minCount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMinCount(String newMinCount)
  {
    String oldMinCount = minCount;
    minCount = newMinCount;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__MIN_COUNT, oldMinCount, minCount));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public constReference getMinCountRef()
  {
    return minCountRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMinCountRef(constReference newMinCountRef, NotificationChain msgs)
  {
    constReference oldMinCountRef = minCountRef;
    minCountRef = newMinCountRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__MIN_COUNT_REF, oldMinCountRef, newMinCountRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMinCountRef(constReference newMinCountRef)
  {
    if (newMinCountRef != minCountRef)
    {
      NotificationChain msgs = null;
      if (minCountRef != null)
        msgs = ((InternalEObject)minCountRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__MIN_COUNT_REF, null, msgs);
      if (newMinCountRef != null)
        msgs = ((InternalEObject)newMinCountRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__MIN_COUNT_REF, null, msgs);
      msgs = basicSetMinCountRef(newMinCountRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__MIN_COUNT_REF, newMinCountRef, newMinCountRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scopedConstId getMinCountScoped()
  {
    return minCountScoped;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMinCountScoped(scopedConstId newMinCountScoped, NotificationChain msgs)
  {
    scopedConstId oldMinCountScoped = minCountScoped;
    minCountScoped = newMinCountScoped;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__MIN_COUNT_SCOPED, oldMinCountScoped, newMinCountScoped);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMinCountScoped(scopedConstId newMinCountScoped)
  {
    if (newMinCountScoped != minCountScoped)
    {
      NotificationChain msgs = null;
      if (minCountScoped != null)
        msgs = ((InternalEObject)minCountScoped).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__MIN_COUNT_SCOPED, null, msgs);
      if (newMinCountScoped != null)
        msgs = ((InternalEObject)newMinCountScoped).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__MIN_COUNT_SCOPED, null, msgs);
      msgs = basicSetMinCountScoped(newMinCountScoped, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__MIN_COUNT_SCOPED, newMinCountScoped, newMinCountScoped));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMaxCount()
  {
    return maxCount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMaxCount(String newMaxCount)
  {
    String oldMaxCount = maxCount;
    maxCount = newMaxCount;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__MAX_COUNT, oldMaxCount, maxCount));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public constReference getMaxCountRef()
  {
    return maxCountRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMaxCountRef(constReference newMaxCountRef, NotificationChain msgs)
  {
    constReference oldMaxCountRef = maxCountRef;
    maxCountRef = newMaxCountRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__MAX_COUNT_REF, oldMaxCountRef, newMaxCountRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMaxCountRef(constReference newMaxCountRef)
  {
    if (newMaxCountRef != maxCountRef)
    {
      NotificationChain msgs = null;
      if (maxCountRef != null)
        msgs = ((InternalEObject)maxCountRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__MAX_COUNT_REF, null, msgs);
      if (newMaxCountRef != null)
        msgs = ((InternalEObject)newMaxCountRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__MAX_COUNT_REF, null, msgs);
      msgs = basicSetMaxCountRef(newMaxCountRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__MAX_COUNT_REF, newMaxCountRef, newMaxCountRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scopedConstId getMaxCountScoped()
  {
    return maxCountScoped;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMaxCountScoped(scopedConstId newMaxCountScoped, NotificationChain msgs)
  {
    scopedConstId oldMaxCountScoped = maxCountScoped;
    maxCountScoped = newMaxCountScoped;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__MAX_COUNT_SCOPED, oldMaxCountScoped, newMaxCountScoped);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMaxCountScoped(scopedConstId newMaxCountScoped)
  {
    if (newMaxCountScoped != maxCountScoped)
    {
      NotificationChain msgs = null;
      if (maxCountScoped != null)
        msgs = ((InternalEObject)maxCountScoped).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__MAX_COUNT_SCOPED, null, msgs);
      if (newMaxCountScoped != null)
        msgs = ((InternalEObject)newMaxCountScoped).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__MAX_COUNT_SCOPED, null, msgs);
      msgs = basicSetMaxCountScoped(newMaxCountScoped, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__MAX_COUNT_SCOPED, newMaxCountScoped, newMaxCountScoped));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public containerRef getContainerRef()
  {
    return containerRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContainerRef(containerRef newContainerRef, NotificationChain msgs)
  {
    containerRef oldContainerRef = containerRef;
    containerRef = newContainerRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__CONTAINER_REF, oldContainerRef, newContainerRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContainerRef(containerRef newContainerRef)
  {
    if (newContainerRef != containerRef)
    {
      NotificationChain msgs = null;
      if (containerRef != null)
        msgs = ((InternalEObject)containerRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__CONTAINER_REF, null, msgs);
      if (newContainerRef != null)
        msgs = ((InternalEObject)newContainerRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__CONTAINER_REF, null, msgs);
      msgs = basicSetContainerRef(newContainerRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__CONTAINER_REF, newContainerRef, newContainerRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public containerDef getContainerDef()
  {
    return containerDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContainerDef(containerDef newContainerDef, NotificationChain msgs)
  {
    containerDef oldContainerDef = containerDef;
    containerDef = newContainerDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__CONTAINER_DEF, oldContainerDef, newContainerDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContainerDef(containerDef newContainerDef)
  {
    if (newContainerDef != containerDef)
    {
      NotificationChain msgs = null;
      if (containerDef != null)
        msgs = ((InternalEObject)containerDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__CONTAINER_DEF, null, msgs);
      if (newContainerDef != null)
        msgs = ((InternalEObject)newContainerDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.LIST_DEF__CONTAINER_DEF, null, msgs);
      msgs = basicSetContainerDef(newContainerDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.LIST_DEF__CONTAINER_DEF, newContainerDef, newContainerDef));
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
      case CjsidlPackage.LIST_DEF__MIN_COUNT_REF:
        return basicSetMinCountRef(null, msgs);
      case CjsidlPackage.LIST_DEF__MIN_COUNT_SCOPED:
        return basicSetMinCountScoped(null, msgs);
      case CjsidlPackage.LIST_DEF__MAX_COUNT_REF:
        return basicSetMaxCountRef(null, msgs);
      case CjsidlPackage.LIST_DEF__MAX_COUNT_SCOPED:
        return basicSetMaxCountScoped(null, msgs);
      case CjsidlPackage.LIST_DEF__CONTAINER_REF:
        return basicSetContainerRef(null, msgs);
      case CjsidlPackage.LIST_DEF__CONTAINER_DEF:
        return basicSetContainerDef(null, msgs);
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
      case CjsidlPackage.LIST_DEF__COUNT_COMMENT:
        return getCountComment();
      case CjsidlPackage.LIST_DEF__MIN_COUNT:
        return getMinCount();
      case CjsidlPackage.LIST_DEF__MIN_COUNT_REF:
        return getMinCountRef();
      case CjsidlPackage.LIST_DEF__MIN_COUNT_SCOPED:
        return getMinCountScoped();
      case CjsidlPackage.LIST_DEF__MAX_COUNT:
        return getMaxCount();
      case CjsidlPackage.LIST_DEF__MAX_COUNT_REF:
        return getMaxCountRef();
      case CjsidlPackage.LIST_DEF__MAX_COUNT_SCOPED:
        return getMaxCountScoped();
      case CjsidlPackage.LIST_DEF__CONTAINER_REF:
        return getContainerRef();
      case CjsidlPackage.LIST_DEF__CONTAINER_DEF:
        return getContainerDef();
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
      case CjsidlPackage.LIST_DEF__COUNT_COMMENT:
        setCountComment((String)newValue);
        return;
      case CjsidlPackage.LIST_DEF__MIN_COUNT:
        setMinCount((String)newValue);
        return;
      case CjsidlPackage.LIST_DEF__MIN_COUNT_REF:
        setMinCountRef((constReference)newValue);
        return;
      case CjsidlPackage.LIST_DEF__MIN_COUNT_SCOPED:
        setMinCountScoped((scopedConstId)newValue);
        return;
      case CjsidlPackage.LIST_DEF__MAX_COUNT:
        setMaxCount((String)newValue);
        return;
      case CjsidlPackage.LIST_DEF__MAX_COUNT_REF:
        setMaxCountRef((constReference)newValue);
        return;
      case CjsidlPackage.LIST_DEF__MAX_COUNT_SCOPED:
        setMaxCountScoped((scopedConstId)newValue);
        return;
      case CjsidlPackage.LIST_DEF__CONTAINER_REF:
        setContainerRef((containerRef)newValue);
        return;
      case CjsidlPackage.LIST_DEF__CONTAINER_DEF:
        setContainerDef((containerDef)newValue);
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
      case CjsidlPackage.LIST_DEF__COUNT_COMMENT:
        setCountComment(COUNT_COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.LIST_DEF__MIN_COUNT:
        setMinCount(MIN_COUNT_EDEFAULT);
        return;
      case CjsidlPackage.LIST_DEF__MIN_COUNT_REF:
        setMinCountRef((constReference)null);
        return;
      case CjsidlPackage.LIST_DEF__MIN_COUNT_SCOPED:
        setMinCountScoped((scopedConstId)null);
        return;
      case CjsidlPackage.LIST_DEF__MAX_COUNT:
        setMaxCount(MAX_COUNT_EDEFAULT);
        return;
      case CjsidlPackage.LIST_DEF__MAX_COUNT_REF:
        setMaxCountRef((constReference)null);
        return;
      case CjsidlPackage.LIST_DEF__MAX_COUNT_SCOPED:
        setMaxCountScoped((scopedConstId)null);
        return;
      case CjsidlPackage.LIST_DEF__CONTAINER_REF:
        setContainerRef((containerRef)null);
        return;
      case CjsidlPackage.LIST_DEF__CONTAINER_DEF:
        setContainerDef((containerDef)null);
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
      case CjsidlPackage.LIST_DEF__COUNT_COMMENT:
        return COUNT_COMMENT_EDEFAULT == null ? countComment != null : !COUNT_COMMENT_EDEFAULT.equals(countComment);
      case CjsidlPackage.LIST_DEF__MIN_COUNT:
        return MIN_COUNT_EDEFAULT == null ? minCount != null : !MIN_COUNT_EDEFAULT.equals(minCount);
      case CjsidlPackage.LIST_DEF__MIN_COUNT_REF:
        return minCountRef != null;
      case CjsidlPackage.LIST_DEF__MIN_COUNT_SCOPED:
        return minCountScoped != null;
      case CjsidlPackage.LIST_DEF__MAX_COUNT:
        return MAX_COUNT_EDEFAULT == null ? maxCount != null : !MAX_COUNT_EDEFAULT.equals(maxCount);
      case CjsidlPackage.LIST_DEF__MAX_COUNT_REF:
        return maxCountRef != null;
      case CjsidlPackage.LIST_DEF__MAX_COUNT_SCOPED:
        return maxCountScoped != null;
      case CjsidlPackage.LIST_DEF__CONTAINER_REF:
        return containerRef != null;
      case CjsidlPackage.LIST_DEF__CONTAINER_DEF:
        return containerDef != null;
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
    result.append(" (countComment: ");
    result.append(countComment);
    result.append(", minCount: ");
    result.append(minCount);
    result.append(", maxCount: ");
    result.append(maxCount);
    result.append(')');
    return result.toString();
  }

} //listDefImpl
