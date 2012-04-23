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
import org.jts.eclipse.cjsidl.defaultState;
import org.jts.eclipse.cjsidl.defaultTransition;
import org.jts.eclipse.cjsidl.transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>default State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.defaultStateImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.defaultStateImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.defaultStateImpl#getDefaultTransition <em>Default Transition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class defaultStateImpl extends MinimalEObjectImpl.Container implements defaultState
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
   * The cached value of the '{@link #getTransition() <em>Transition</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTransition()
   * @generated
   * @ordered
   */
  protected EList<transition> transition;

  /**
   * The cached value of the '{@link #getDefaultTransition() <em>Default Transition</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaultTransition()
   * @generated
   * @ordered
   */
  protected EList<defaultTransition> defaultTransition;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected defaultStateImpl()
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
    return CjsidlPackage.eINSTANCE.getdefaultState();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.DEFAULT_STATE__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<transition> getTransition()
  {
    if (transition == null)
    {
      transition = new EObjectContainmentEList<transition>(transition.class, this, CjsidlPackage.DEFAULT_STATE__TRANSITION);
    }
    return transition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<defaultTransition> getDefaultTransition()
  {
    if (defaultTransition == null)
    {
      defaultTransition = new EObjectContainmentEList<defaultTransition>(defaultTransition.class, this, CjsidlPackage.DEFAULT_STATE__DEFAULT_TRANSITION);
    }
    return defaultTransition;
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
      case CjsidlPackage.DEFAULT_STATE__TRANSITION:
        return ((InternalEList<?>)getTransition()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.DEFAULT_STATE__DEFAULT_TRANSITION:
        return ((InternalEList<?>)getDefaultTransition()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.DEFAULT_STATE__COMMENT:
        return getComment();
      case CjsidlPackage.DEFAULT_STATE__TRANSITION:
        return getTransition();
      case CjsidlPackage.DEFAULT_STATE__DEFAULT_TRANSITION:
        return getDefaultTransition();
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
      case CjsidlPackage.DEFAULT_STATE__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.DEFAULT_STATE__TRANSITION:
        getTransition().clear();
        getTransition().addAll((Collection<? extends transition>)newValue);
        return;
      case CjsidlPackage.DEFAULT_STATE__DEFAULT_TRANSITION:
        getDefaultTransition().clear();
        getDefaultTransition().addAll((Collection<? extends defaultTransition>)newValue);
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
      case CjsidlPackage.DEFAULT_STATE__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.DEFAULT_STATE__TRANSITION:
        getTransition().clear();
        return;
      case CjsidlPackage.DEFAULT_STATE__DEFAULT_TRANSITION:
        getDefaultTransition().clear();
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
      case CjsidlPackage.DEFAULT_STATE__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.DEFAULT_STATE__TRANSITION:
        return transition != null && !transition.isEmpty();
      case CjsidlPackage.DEFAULT_STATE__DEFAULT_TRANSITION:
        return defaultTransition != null && !defaultTransition.isEmpty();
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

} //defaultStateImpl
