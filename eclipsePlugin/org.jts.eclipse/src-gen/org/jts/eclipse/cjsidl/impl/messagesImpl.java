/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.messageDef;
import org.jts.eclipse.cjsidl.messageRef;
import org.jts.eclipse.cjsidl.messageScopedRef;
import org.jts.eclipse.cjsidl.messages;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>messages</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.messagesImpl#getMessageDefs <em>Message Defs</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.messagesImpl#getTypeRefs <em>Type Refs</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.messagesImpl#getScopedRefs <em>Scoped Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class messagesImpl extends MinimalEObjectImpl.Container implements messages
{
  /**
   * The cached value of the '{@link #getMessageDefs() <em>Message Defs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessageDefs()
   * @generated
   * @ordered
   */
  protected EList<messageDef> messageDefs;

  /**
   * The cached value of the '{@link #getTypeRefs() <em>Type Refs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeRefs()
   * @generated
   * @ordered
   */
  protected EList<messageRef> typeRefs;

  /**
   * The cached value of the '{@link #getScopedRefs() <em>Scoped Refs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScopedRefs()
   * @generated
   * @ordered
   */
  protected EList<messageScopedRef> scopedRefs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected messagesImpl()
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
    return CjsidlPackage.eINSTANCE.getmessages();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<messageDef> getMessageDefs()
  {
    if (messageDefs == null)
    {
      messageDefs = new EObjectContainmentEList<messageDef>(messageDef.class, this, CjsidlPackage.MESSAGES__MESSAGE_DEFS);
    }
    return messageDefs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<messageRef> getTypeRefs()
  {
    if (typeRefs == null)
    {
      typeRefs = new EObjectContainmentEList<messageRef>(messageRef.class, this, CjsidlPackage.MESSAGES__TYPE_REFS);
    }
    return typeRefs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<messageScopedRef> getScopedRefs()
  {
    if (scopedRefs == null)
    {
      scopedRefs = new EObjectContainmentEList<messageScopedRef>(messageScopedRef.class, this, CjsidlPackage.MESSAGES__SCOPED_REFS);
    }
    return scopedRefs;
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
      case CjsidlPackage.MESSAGES__MESSAGE_DEFS:
        return ((InternalEList<?>)getMessageDefs()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.MESSAGES__TYPE_REFS:
        return ((InternalEList<?>)getTypeRefs()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.MESSAGES__SCOPED_REFS:
        return ((InternalEList<?>)getScopedRefs()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.MESSAGES__MESSAGE_DEFS:
        return getMessageDefs();
      case CjsidlPackage.MESSAGES__TYPE_REFS:
        return getTypeRefs();
      case CjsidlPackage.MESSAGES__SCOPED_REFS:
        return getScopedRefs();
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
      case CjsidlPackage.MESSAGES__MESSAGE_DEFS:
        getMessageDefs().clear();
        getMessageDefs().addAll((Collection<? extends messageDef>)newValue);
        return;
      case CjsidlPackage.MESSAGES__TYPE_REFS:
        getTypeRefs().clear();
        getTypeRefs().addAll((Collection<? extends messageRef>)newValue);
        return;
      case CjsidlPackage.MESSAGES__SCOPED_REFS:
        getScopedRefs().clear();
        getScopedRefs().addAll((Collection<? extends messageScopedRef>)newValue);
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
      case CjsidlPackage.MESSAGES__MESSAGE_DEFS:
        getMessageDefs().clear();
        return;
      case CjsidlPackage.MESSAGES__TYPE_REFS:
        getTypeRefs().clear();
        return;
      case CjsidlPackage.MESSAGES__SCOPED_REFS:
        getScopedRefs().clear();
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
      case CjsidlPackage.MESSAGES__MESSAGE_DEFS:
        return messageDefs != null && !messageDefs.isEmpty();
      case CjsidlPackage.MESSAGES__TYPE_REFS:
        return typeRefs != null && !typeRefs.isEmpty();
      case CjsidlPackage.MESSAGES__SCOPED_REFS:
        return scopedRefs != null && !scopedRefs.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //messagesImpl
