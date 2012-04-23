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
import org.jts.eclipse.cjsidl.refAttr;
import org.jts.eclipse.cjsidl.references;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>references</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.referencesImpl#getRefInherit <em>Ref Inherit</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.referencesImpl#getRefClient <em>Ref Client</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class referencesImpl extends MinimalEObjectImpl.Container implements references
{
  /**
   * The cached value of the '{@link #getRefInherit() <em>Ref Inherit</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRefInherit()
   * @generated
   * @ordered
   */
  protected refAttr refInherit;

  /**
   * The cached value of the '{@link #getRefClient() <em>Ref Client</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRefClient()
   * @generated
   * @ordered
   */
  protected EList<refAttr> refClient;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected referencesImpl()
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
    return CjsidlPackage.eINSTANCE.getreferences();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public refAttr getRefInherit()
  {
    return refInherit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRefInherit(refAttr newRefInherit, NotificationChain msgs)
  {
    refAttr oldRefInherit = refInherit;
    refInherit = newRefInherit;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.REFERENCES__REF_INHERIT, oldRefInherit, newRefInherit);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRefInherit(refAttr newRefInherit)
  {
    if (newRefInherit != refInherit)
    {
      NotificationChain msgs = null;
      if (refInherit != null)
        msgs = ((InternalEObject)refInherit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.REFERENCES__REF_INHERIT, null, msgs);
      if (newRefInherit != null)
        msgs = ((InternalEObject)newRefInherit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.REFERENCES__REF_INHERIT, null, msgs);
      msgs = basicSetRefInherit(newRefInherit, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.REFERENCES__REF_INHERIT, newRefInherit, newRefInherit));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<refAttr> getRefClient()
  {
    if (refClient == null)
    {
      refClient = new EObjectContainmentEList<refAttr>(refAttr.class, this, CjsidlPackage.REFERENCES__REF_CLIENT);
    }
    return refClient;
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
      case CjsidlPackage.REFERENCES__REF_INHERIT:
        return basicSetRefInherit(null, msgs);
      case CjsidlPackage.REFERENCES__REF_CLIENT:
        return ((InternalEList<?>)getRefClient()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.REFERENCES__REF_INHERIT:
        return getRefInherit();
      case CjsidlPackage.REFERENCES__REF_CLIENT:
        return getRefClient();
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
      case CjsidlPackage.REFERENCES__REF_INHERIT:
        setRefInherit((refAttr)newValue);
        return;
      case CjsidlPackage.REFERENCES__REF_CLIENT:
        getRefClient().clear();
        getRefClient().addAll((Collection<? extends refAttr>)newValue);
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
      case CjsidlPackage.REFERENCES__REF_INHERIT:
        setRefInherit((refAttr)null);
        return;
      case CjsidlPackage.REFERENCES__REF_CLIENT:
        getRefClient().clear();
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
      case CjsidlPackage.REFERENCES__REF_INHERIT:
        return refInherit != null;
      case CjsidlPackage.REFERENCES__REF_CLIENT:
        return refClient != null && !refClient.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //referencesImpl
