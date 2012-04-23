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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.constReference;
import org.jts.eclipse.cjsidl.scopedConstId;
import org.jts.eclipse.cjsidl.taggedItemDef;
import org.jts.eclipse.cjsidl.variantDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>variant Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.variantDefImpl#getVtagComment <em>Vtag Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.variantDefImpl#getMinCount <em>Min Count</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.variantDefImpl#getMinCountRef <em>Min Count Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.variantDefImpl#getMinCountScoped <em>Min Count Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.variantDefImpl#getMaxCount <em>Max Count</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.variantDefImpl#getMaxCountRef <em>Max Count Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.variantDefImpl#getMaxCountScoped <em>Max Count Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.variantDefImpl#getItemList <em>Item List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class variantDefImpl extends containerDefImpl implements variantDef
{
  /**
   * The default value of the '{@link #getVtagComment() <em>Vtag Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVtagComment()
   * @generated
   * @ordered
   */
  protected static final String VTAG_COMMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getVtagComment() <em>Vtag Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVtagComment()
   * @generated
   * @ordered
   */
  protected String vtagComment = VTAG_COMMENT_EDEFAULT;

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
   * The cached value of the '{@link #getItemList() <em>Item List</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItemList()
   * @generated
   * @ordered
   */
  protected EList<taggedItemDef> itemList;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected variantDefImpl()
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
    return CjsidlPackage.eINSTANCE.getvariantDef();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getVtagComment()
  {
    return vtagComment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVtagComment(String newVtagComment)
  {
    String oldVtagComment = vtagComment;
    vtagComment = newVtagComment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VARIANT_DEF__VTAG_COMMENT, oldVtagComment, vtagComment));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VARIANT_DEF__MIN_COUNT, oldMinCount, minCount));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.VARIANT_DEF__MIN_COUNT_REF, oldMinCountRef, newMinCountRef);
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
        msgs = ((InternalEObject)minCountRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.VARIANT_DEF__MIN_COUNT_REF, null, msgs);
      if (newMinCountRef != null)
        msgs = ((InternalEObject)newMinCountRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.VARIANT_DEF__MIN_COUNT_REF, null, msgs);
      msgs = basicSetMinCountRef(newMinCountRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VARIANT_DEF__MIN_COUNT_REF, newMinCountRef, newMinCountRef));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.VARIANT_DEF__MIN_COUNT_SCOPED, oldMinCountScoped, newMinCountScoped);
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
        msgs = ((InternalEObject)minCountScoped).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.VARIANT_DEF__MIN_COUNT_SCOPED, null, msgs);
      if (newMinCountScoped != null)
        msgs = ((InternalEObject)newMinCountScoped).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.VARIANT_DEF__MIN_COUNT_SCOPED, null, msgs);
      msgs = basicSetMinCountScoped(newMinCountScoped, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VARIANT_DEF__MIN_COUNT_SCOPED, newMinCountScoped, newMinCountScoped));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VARIANT_DEF__MAX_COUNT, oldMaxCount, maxCount));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.VARIANT_DEF__MAX_COUNT_REF, oldMaxCountRef, newMaxCountRef);
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
        msgs = ((InternalEObject)maxCountRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.VARIANT_DEF__MAX_COUNT_REF, null, msgs);
      if (newMaxCountRef != null)
        msgs = ((InternalEObject)newMaxCountRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.VARIANT_DEF__MAX_COUNT_REF, null, msgs);
      msgs = basicSetMaxCountRef(newMaxCountRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VARIANT_DEF__MAX_COUNT_REF, newMaxCountRef, newMaxCountRef));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.VARIANT_DEF__MAX_COUNT_SCOPED, oldMaxCountScoped, newMaxCountScoped);
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
        msgs = ((InternalEObject)maxCountScoped).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.VARIANT_DEF__MAX_COUNT_SCOPED, null, msgs);
      if (newMaxCountScoped != null)
        msgs = ((InternalEObject)newMaxCountScoped).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.VARIANT_DEF__MAX_COUNT_SCOPED, null, msgs);
      msgs = basicSetMaxCountScoped(newMaxCountScoped, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.VARIANT_DEF__MAX_COUNT_SCOPED, newMaxCountScoped, newMaxCountScoped));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<taggedItemDef> getItemList()
  {
    if (itemList == null)
    {
      itemList = new EObjectContainmentEList<taggedItemDef>(taggedItemDef.class, this, CjsidlPackage.VARIANT_DEF__ITEM_LIST);
    }
    return itemList;
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
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT_REF:
        return basicSetMinCountRef(null, msgs);
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT_SCOPED:
        return basicSetMinCountScoped(null, msgs);
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT_REF:
        return basicSetMaxCountRef(null, msgs);
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT_SCOPED:
        return basicSetMaxCountScoped(null, msgs);
      case CjsidlPackage.VARIANT_DEF__ITEM_LIST:
        return ((InternalEList<?>)getItemList()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.VARIANT_DEF__VTAG_COMMENT:
        return getVtagComment();
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT:
        return getMinCount();
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT_REF:
        return getMinCountRef();
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT_SCOPED:
        return getMinCountScoped();
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT:
        return getMaxCount();
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT_REF:
        return getMaxCountRef();
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT_SCOPED:
        return getMaxCountScoped();
      case CjsidlPackage.VARIANT_DEF__ITEM_LIST:
        return getItemList();
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
      case CjsidlPackage.VARIANT_DEF__VTAG_COMMENT:
        setVtagComment((String)newValue);
        return;
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT:
        setMinCount((String)newValue);
        return;
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT_REF:
        setMinCountRef((constReference)newValue);
        return;
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT_SCOPED:
        setMinCountScoped((scopedConstId)newValue);
        return;
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT:
        setMaxCount((String)newValue);
        return;
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT_REF:
        setMaxCountRef((constReference)newValue);
        return;
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT_SCOPED:
        setMaxCountScoped((scopedConstId)newValue);
        return;
      case CjsidlPackage.VARIANT_DEF__ITEM_LIST:
        getItemList().clear();
        getItemList().addAll((Collection<? extends taggedItemDef>)newValue);
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
      case CjsidlPackage.VARIANT_DEF__VTAG_COMMENT:
        setVtagComment(VTAG_COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT:
        setMinCount(MIN_COUNT_EDEFAULT);
        return;
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT_REF:
        setMinCountRef((constReference)null);
        return;
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT_SCOPED:
        setMinCountScoped((scopedConstId)null);
        return;
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT:
        setMaxCount(MAX_COUNT_EDEFAULT);
        return;
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT_REF:
        setMaxCountRef((constReference)null);
        return;
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT_SCOPED:
        setMaxCountScoped((scopedConstId)null);
        return;
      case CjsidlPackage.VARIANT_DEF__ITEM_LIST:
        getItemList().clear();
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
      case CjsidlPackage.VARIANT_DEF__VTAG_COMMENT:
        return VTAG_COMMENT_EDEFAULT == null ? vtagComment != null : !VTAG_COMMENT_EDEFAULT.equals(vtagComment);
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT:
        return MIN_COUNT_EDEFAULT == null ? minCount != null : !MIN_COUNT_EDEFAULT.equals(minCount);
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT_REF:
        return minCountRef != null;
      case CjsidlPackage.VARIANT_DEF__MIN_COUNT_SCOPED:
        return minCountScoped != null;
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT:
        return MAX_COUNT_EDEFAULT == null ? maxCount != null : !MAX_COUNT_EDEFAULT.equals(maxCount);
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT_REF:
        return maxCountRef != null;
      case CjsidlPackage.VARIANT_DEF__MAX_COUNT_SCOPED:
        return maxCountScoped != null;
      case CjsidlPackage.VARIANT_DEF__ITEM_LIST:
        return itemList != null && !itemList.isEmpty();
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
    result.append(" (vtagComment: ");
    result.append(vtagComment);
    result.append(", minCount: ");
    result.append(minCount);
    result.append(", maxCount: ");
    result.append(maxCount);
    result.append(')');
    return result.toString();
  }

} //variantDefImpl
