/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.nextState;
import org.jts.eclipse.cjsidl.state;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>next State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.nextStateImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.nextStateImpl#getFirstState <em>First State</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.nextStateImpl#getScoped <em>Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.nextStateImpl#getNextState <em>Next State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class nextStateImpl extends MinimalEObjectImpl.Container implements nextState
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
   * The cached value of the '{@link #getFirstState() <em>First State</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFirstState()
   * @generated
   * @ordered
   */
  protected state firstState;

  /**
   * The cached value of the '{@link #getScoped() <em>Scoped</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScoped()
   * @generated
   * @ordered
   */
  protected EList<state> scoped;

  /**
   * The cached value of the '{@link #getNextState() <em>Next State</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNextState()
   * @generated
   * @ordered
   */
  protected state nextState;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected nextStateImpl()
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
    return CjsidlPackage.eINSTANCE.getnextState();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.NEXT_STATE__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public state getFirstState()
  {
    if (firstState != null && firstState.eIsProxy())
    {
      InternalEObject oldFirstState = (InternalEObject)firstState;
      firstState = (state)eResolveProxy(oldFirstState);
      if (firstState != oldFirstState)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CjsidlPackage.NEXT_STATE__FIRST_STATE, oldFirstState, firstState));
      }
    }
    return firstState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public state basicGetFirstState()
  {
    return firstState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFirstState(state newFirstState)
  {
    state oldFirstState = firstState;
    firstState = newFirstState;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.NEXT_STATE__FIRST_STATE, oldFirstState, firstState));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<state> getScoped()
  {
    if (scoped == null)
    {
      scoped = new EObjectResolvingEList<state>(state.class, this, CjsidlPackage.NEXT_STATE__SCOPED);
    }
    return scoped;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public state getNextState()
  {
    if (nextState != null && nextState.eIsProxy())
    {
      InternalEObject oldNextState = (InternalEObject)nextState;
      nextState = (state)eResolveProxy(oldNextState);
      if (nextState != oldNextState)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CjsidlPackage.NEXT_STATE__NEXT_STATE, oldNextState, nextState));
      }
    }
    return nextState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public state basicGetNextState()
  {
    return nextState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNextState(state newNextState)
  {
    state oldNextState = nextState;
    nextState = newNextState;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.NEXT_STATE__NEXT_STATE, oldNextState, nextState));
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
      case CjsidlPackage.NEXT_STATE__COMMENT:
        return getComment();
      case CjsidlPackage.NEXT_STATE__FIRST_STATE:
        if (resolve) return getFirstState();
        return basicGetFirstState();
      case CjsidlPackage.NEXT_STATE__SCOPED:
        return getScoped();
      case CjsidlPackage.NEXT_STATE__NEXT_STATE:
        if (resolve) return getNextState();
        return basicGetNextState();
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
      case CjsidlPackage.NEXT_STATE__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.NEXT_STATE__FIRST_STATE:
        setFirstState((state)newValue);
        return;
      case CjsidlPackage.NEXT_STATE__SCOPED:
        getScoped().clear();
        getScoped().addAll((Collection<? extends state>)newValue);
        return;
      case CjsidlPackage.NEXT_STATE__NEXT_STATE:
        setNextState((state)newValue);
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
      case CjsidlPackage.NEXT_STATE__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.NEXT_STATE__FIRST_STATE:
        setFirstState((state)null);
        return;
      case CjsidlPackage.NEXT_STATE__SCOPED:
        getScoped().clear();
        return;
      case CjsidlPackage.NEXT_STATE__NEXT_STATE:
        setNextState((state)null);
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
      case CjsidlPackage.NEXT_STATE__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.NEXT_STATE__FIRST_STATE:
        return firstState != null;
      case CjsidlPackage.NEXT_STATE__SCOPED:
        return scoped != null && !scoped.isEmpty();
      case CjsidlPackage.NEXT_STATE__NEXT_STATE:
        return nextState != null;
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

} //nextStateImpl
