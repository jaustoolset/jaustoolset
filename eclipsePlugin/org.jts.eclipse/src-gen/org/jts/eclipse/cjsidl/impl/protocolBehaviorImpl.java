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
import org.jts.eclipse.cjsidl.protocolBehavior;
import org.jts.eclipse.cjsidl.stateMachine;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>protocol Behavior</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.protocolBehaviorImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.protocolBehaviorImpl#getStateless <em>Stateless</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.protocolBehaviorImpl#getStateMachine <em>State Machine</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class protocolBehaviorImpl extends MinimalEObjectImpl.Container implements protocolBehavior
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
   * The default value of the '{@link #getStateless() <em>Stateless</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStateless()
   * @generated
   * @ordered
   */
  protected static final String STATELESS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStateless() <em>Stateless</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStateless()
   * @generated
   * @ordered
   */
  protected String stateless = STATELESS_EDEFAULT;

  /**
   * The cached value of the '{@link #getStateMachine() <em>State Machine</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStateMachine()
   * @generated
   * @ordered
   */
  protected EList<stateMachine> stateMachine;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected protocolBehaviorImpl()
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
    return CjsidlPackage.eINSTANCE.getprotocolBehavior();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.PROTOCOL_BEHAVIOR__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getStateless()
  {
    return stateless;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStateless(String newStateless)
  {
    String oldStateless = stateless;
    stateless = newStateless;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.PROTOCOL_BEHAVIOR__STATELESS, oldStateless, stateless));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<stateMachine> getStateMachine()
  {
    if (stateMachine == null)
    {
      stateMachine = new EObjectContainmentEList<stateMachine>(stateMachine.class, this, CjsidlPackage.PROTOCOL_BEHAVIOR__STATE_MACHINE);
    }
    return stateMachine;
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
      case CjsidlPackage.PROTOCOL_BEHAVIOR__STATE_MACHINE:
        return ((InternalEList<?>)getStateMachine()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.PROTOCOL_BEHAVIOR__COMMENT:
        return getComment();
      case CjsidlPackage.PROTOCOL_BEHAVIOR__STATELESS:
        return getStateless();
      case CjsidlPackage.PROTOCOL_BEHAVIOR__STATE_MACHINE:
        return getStateMachine();
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
      case CjsidlPackage.PROTOCOL_BEHAVIOR__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.PROTOCOL_BEHAVIOR__STATELESS:
        setStateless((String)newValue);
        return;
      case CjsidlPackage.PROTOCOL_BEHAVIOR__STATE_MACHINE:
        getStateMachine().clear();
        getStateMachine().addAll((Collection<? extends stateMachine>)newValue);
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
      case CjsidlPackage.PROTOCOL_BEHAVIOR__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.PROTOCOL_BEHAVIOR__STATELESS:
        setStateless(STATELESS_EDEFAULT);
        return;
      case CjsidlPackage.PROTOCOL_BEHAVIOR__STATE_MACHINE:
        getStateMachine().clear();
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
      case CjsidlPackage.PROTOCOL_BEHAVIOR__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.PROTOCOL_BEHAVIOR__STATELESS:
        return STATELESS_EDEFAULT == null ? stateless != null : !STATELESS_EDEFAULT.equals(stateless);
      case CjsidlPackage.PROTOCOL_BEHAVIOR__STATE_MACHINE:
        return stateMachine != null && !stateMachine.isEmpty();
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
    result.append(", stateless: ");
    result.append(stateless);
    result.append(')');
    return result.toString();
  }

} //protocolBehaviorImpl
