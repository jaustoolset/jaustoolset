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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.sequenceDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>sequence Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.sequenceDefImpl#getContainerTypeList <em>Container Type List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class sequenceDefImpl extends containerDefImpl implements sequenceDef
{
  /**
   * The cached value of the '{@link #getContainerTypeList() <em>Container Type List</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContainerTypeList()
   * @generated
   * @ordered
   */
  protected EList<EObject> containerTypeList;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected sequenceDefImpl()
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
    return CjsidlPackage.eINSTANCE.getsequenceDef();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getContainerTypeList()
  {
    if (containerTypeList == null)
    {
      containerTypeList = new EObjectContainmentEList<EObject>(EObject.class, this, CjsidlPackage.SEQUENCE_DEF__CONTAINER_TYPE_LIST);
    }
    return containerTypeList;
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
      case CjsidlPackage.SEQUENCE_DEF__CONTAINER_TYPE_LIST:
        return ((InternalEList<?>)getContainerTypeList()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.SEQUENCE_DEF__CONTAINER_TYPE_LIST:
        return getContainerTypeList();
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
      case CjsidlPackage.SEQUENCE_DEF__CONTAINER_TYPE_LIST:
        getContainerTypeList().clear();
        getContainerTypeList().addAll((Collection<? extends EObject>)newValue);
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
      case CjsidlPackage.SEQUENCE_DEF__CONTAINER_TYPE_LIST:
        getContainerTypeList().clear();
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
      case CjsidlPackage.SEQUENCE_DEF__CONTAINER_TYPE_LIST:
        return containerTypeList != null && !containerTypeList.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //sequenceDefImpl
