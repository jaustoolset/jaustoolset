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
import org.jts.eclipse.cjsidl.containerDef;
import org.jts.eclipse.cjsidl.containerRef;
import org.jts.eclipse.cjsidl.taggedItemDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>tagged Item Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.taggedItemDefImpl#getContainerDef <em>Container Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.taggedItemDefImpl#getContainerRef <em>Container Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class taggedItemDefImpl extends MinimalEObjectImpl.Container implements taggedItemDef
{
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
   * The cached value of the '{@link #getContainerRef() <em>Container Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContainerRef()
   * @generated
   * @ordered
   */
  protected containerRef containerRef;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected taggedItemDefImpl()
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
    return CjsidlPackage.eINSTANCE.gettaggedItemDef();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_DEF, oldContainerDef, newContainerDef);
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
        msgs = ((InternalEObject)containerDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_DEF, null, msgs);
      if (newContainerDef != null)
        msgs = ((InternalEObject)newContainerDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_DEF, null, msgs);
      msgs = basicSetContainerDef(newContainerDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_DEF, newContainerDef, newContainerDef));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_REF, oldContainerRef, newContainerRef);
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
        msgs = ((InternalEObject)containerRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_REF, null, msgs);
      if (newContainerRef != null)
        msgs = ((InternalEObject)newContainerRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_REF, null, msgs);
      msgs = basicSetContainerRef(newContainerRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_REF, newContainerRef, newContainerRef));
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
      case CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_DEF:
        return basicSetContainerDef(null, msgs);
      case CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_REF:
        return basicSetContainerRef(null, msgs);
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
      case CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_DEF:
        return getContainerDef();
      case CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_REF:
        return getContainerRef();
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
      case CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_DEF:
        setContainerDef((containerDef)newValue);
        return;
      case CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_REF:
        setContainerRef((containerRef)newValue);
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
      case CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_DEF:
        setContainerDef((containerDef)null);
        return;
      case CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_REF:
        setContainerRef((containerRef)null);
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
      case CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_DEF:
        return containerDef != null;
      case CjsidlPackage.TAGGED_ITEM_DEF__CONTAINER_REF:
        return containerRef != null;
    }
    return super.eIsSet(featureID);
  }

} //taggedItemDefImpl
