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
import org.jts.eclipse.cjsidl.nextState;
import org.jts.eclipse.cjsidl.pushTransition;
import org.jts.eclipse.cjsidl.simpleTransition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>push Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.pushTransitionImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.pushTransitionImpl#getNextState <em>Next State</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.pushTransitionImpl#getSimpleTransition <em>Simple Transition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class pushTransitionImpl extends MinimalEObjectImpl.Container implements pushTransition
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
   * The cached value of the '{@link #getNextState() <em>Next State</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNextState()
   * @generated
   * @ordered
   */
  protected nextState nextState;

  /**
   * The cached value of the '{@link #getSimpleTransition() <em>Simple Transition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSimpleTransition()
   * @generated
   * @ordered
   */
  protected simpleTransition simpleTransition;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected pushTransitionImpl()
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
    return CjsidlPackage.eINSTANCE.getpushTransition();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.PUSH_TRANSITION__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public nextState getNextState()
  {
    return nextState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNextState(nextState newNextState, NotificationChain msgs)
  {
    nextState oldNextState = nextState;
    nextState = newNextState;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.PUSH_TRANSITION__NEXT_STATE, oldNextState, newNextState);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNextState(nextState newNextState)
  {
    if (newNextState != nextState)
    {
      NotificationChain msgs = null;
      if (nextState != null)
        msgs = ((InternalEObject)nextState).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.PUSH_TRANSITION__NEXT_STATE, null, msgs);
      if (newNextState != null)
        msgs = ((InternalEObject)newNextState).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.PUSH_TRANSITION__NEXT_STATE, null, msgs);
      msgs = basicSetNextState(newNextState, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.PUSH_TRANSITION__NEXT_STATE, newNextState, newNextState));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public simpleTransition getSimpleTransition()
  {
    return simpleTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSimpleTransition(simpleTransition newSimpleTransition, NotificationChain msgs)
  {
    simpleTransition oldSimpleTransition = simpleTransition;
    simpleTransition = newSimpleTransition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.PUSH_TRANSITION__SIMPLE_TRANSITION, oldSimpleTransition, newSimpleTransition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSimpleTransition(simpleTransition newSimpleTransition)
  {
    if (newSimpleTransition != simpleTransition)
    {
      NotificationChain msgs = null;
      if (simpleTransition != null)
        msgs = ((InternalEObject)simpleTransition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.PUSH_TRANSITION__SIMPLE_TRANSITION, null, msgs);
      if (newSimpleTransition != null)
        msgs = ((InternalEObject)newSimpleTransition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.PUSH_TRANSITION__SIMPLE_TRANSITION, null, msgs);
      msgs = basicSetSimpleTransition(newSimpleTransition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.PUSH_TRANSITION__SIMPLE_TRANSITION, newSimpleTransition, newSimpleTransition));
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
      case CjsidlPackage.PUSH_TRANSITION__NEXT_STATE:
        return basicSetNextState(null, msgs);
      case CjsidlPackage.PUSH_TRANSITION__SIMPLE_TRANSITION:
        return basicSetSimpleTransition(null, msgs);
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
      case CjsidlPackage.PUSH_TRANSITION__COMMENT:
        return getComment();
      case CjsidlPackage.PUSH_TRANSITION__NEXT_STATE:
        return getNextState();
      case CjsidlPackage.PUSH_TRANSITION__SIMPLE_TRANSITION:
        return getSimpleTransition();
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
      case CjsidlPackage.PUSH_TRANSITION__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.PUSH_TRANSITION__NEXT_STATE:
        setNextState((nextState)newValue);
        return;
      case CjsidlPackage.PUSH_TRANSITION__SIMPLE_TRANSITION:
        setSimpleTransition((simpleTransition)newValue);
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
      case CjsidlPackage.PUSH_TRANSITION__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.PUSH_TRANSITION__NEXT_STATE:
        setNextState((nextState)null);
        return;
      case CjsidlPackage.PUSH_TRANSITION__SIMPLE_TRANSITION:
        setSimpleTransition((simpleTransition)null);
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
      case CjsidlPackage.PUSH_TRANSITION__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.PUSH_TRANSITION__NEXT_STATE:
        return nextState != null;
      case CjsidlPackage.PUSH_TRANSITION__SIMPLE_TRANSITION:
        return simpleTransition != null;
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

} //pushTransitionImpl
