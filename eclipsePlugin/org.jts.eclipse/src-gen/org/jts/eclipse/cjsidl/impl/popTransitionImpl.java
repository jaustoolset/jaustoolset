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
import org.jts.eclipse.cjsidl.guardParam;
import org.jts.eclipse.cjsidl.popTransition;
import org.jts.eclipse.cjsidl.transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>pop Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.popTransitionImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.popTransitionImpl#getSecondaryTransition <em>Secondary Transition</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.popTransitionImpl#getParam <em>Param</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class popTransitionImpl extends MinimalEObjectImpl.Container implements popTransition
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
   * The cached value of the '{@link #getSecondaryTransition() <em>Secondary Transition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSecondaryTransition()
   * @generated
   * @ordered
   */
  protected transition secondaryTransition;

  /**
   * The cached value of the '{@link #getParam() <em>Param</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParam()
   * @generated
   * @ordered
   */
  protected EList<guardParam> param;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected popTransitionImpl()
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
    return CjsidlPackage.eINSTANCE.getpopTransition();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.POP_TRANSITION__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public transition getSecondaryTransition()
  {
    if (secondaryTransition != null && secondaryTransition.eIsProxy())
    {
      InternalEObject oldSecondaryTransition = (InternalEObject)secondaryTransition;
      secondaryTransition = (transition)eResolveProxy(oldSecondaryTransition);
      if (secondaryTransition != oldSecondaryTransition)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CjsidlPackage.POP_TRANSITION__SECONDARY_TRANSITION, oldSecondaryTransition, secondaryTransition));
      }
    }
    return secondaryTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public transition basicGetSecondaryTransition()
  {
    return secondaryTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSecondaryTransition(transition newSecondaryTransition)
  {
    transition oldSecondaryTransition = secondaryTransition;
    secondaryTransition = newSecondaryTransition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.POP_TRANSITION__SECONDARY_TRANSITION, oldSecondaryTransition, secondaryTransition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<guardParam> getParam()
  {
    if (param == null)
    {
      param = new EObjectContainmentEList<guardParam>(guardParam.class, this, CjsidlPackage.POP_TRANSITION__PARAM);
    }
    return param;
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
      case CjsidlPackage.POP_TRANSITION__PARAM:
        return ((InternalEList<?>)getParam()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.POP_TRANSITION__COMMENT:
        return getComment();
      case CjsidlPackage.POP_TRANSITION__SECONDARY_TRANSITION:
        if (resolve) return getSecondaryTransition();
        return basicGetSecondaryTransition();
      case CjsidlPackage.POP_TRANSITION__PARAM:
        return getParam();
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
      case CjsidlPackage.POP_TRANSITION__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.POP_TRANSITION__SECONDARY_TRANSITION:
        setSecondaryTransition((transition)newValue);
        return;
      case CjsidlPackage.POP_TRANSITION__PARAM:
        getParam().clear();
        getParam().addAll((Collection<? extends guardParam>)newValue);
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
      case CjsidlPackage.POP_TRANSITION__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.POP_TRANSITION__SECONDARY_TRANSITION:
        setSecondaryTransition((transition)null);
        return;
      case CjsidlPackage.POP_TRANSITION__PARAM:
        getParam().clear();
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
      case CjsidlPackage.POP_TRANSITION__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.POP_TRANSITION__SECONDARY_TRANSITION:
        return secondaryTransition != null;
      case CjsidlPackage.POP_TRANSITION__PARAM:
        return param != null && !param.isEmpty();
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
    result.append(')');
    return result.toString();
  }

} //popTransitionImpl
