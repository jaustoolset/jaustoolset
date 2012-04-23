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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.actionList;
import org.jts.eclipse.cjsidl.guard;
import org.jts.eclipse.cjsidl.refAttr;
import org.jts.eclipse.cjsidl.sendActionList;
import org.jts.eclipse.cjsidl.transParams;
import org.jts.eclipse.cjsidl.transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transitionImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transitionImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transitionImpl#getScoped <em>Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transitionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transitionImpl#getParams <em>Params</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transitionImpl#getTransGuard <em>Trans Guard</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transitionImpl#getActions <em>Actions</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transitionImpl#getSendActions <em>Send Actions</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.transitionImpl#getDestination <em>Destination</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class transitionImpl extends MinimalEObjectImpl.Container implements transition
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
   * The default value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected static final String TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected String type = TYPE_EDEFAULT;

  /**
   * The cached value of the '{@link #getScoped() <em>Scoped</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScoped()
   * @generated
   * @ordered
   */
  protected EList<refAttr> scoped;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getParams() <em>Params</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParams()
   * @generated
   * @ordered
   */
  protected transParams params;

  /**
   * The cached value of the '{@link #getTransGuard() <em>Trans Guard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTransGuard()
   * @generated
   * @ordered
   */
  protected guard transGuard;

  /**
   * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActions()
   * @generated
   * @ordered
   */
  protected actionList actions;

  /**
   * The cached value of the '{@link #getSendActions() <em>Send Actions</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSendActions()
   * @generated
   * @ordered
   */
  protected sendActionList sendActions;

  /**
   * The cached value of the '{@link #getDestination() <em>Destination</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDestination()
   * @generated
   * @ordered
   */
  protected EObject destination;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected transitionImpl()
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
    return CjsidlPackage.eINSTANCE.gettransition();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(String newType)
  {
    String oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<refAttr> getScoped()
  {
    if (scoped == null)
    {
      scoped = new EObjectResolvingEList<refAttr>(refAttr.class, this, CjsidlPackage.TRANSITION__SCOPED);
    }
    return scoped;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public transParams getParams()
  {
    return params;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParams(transParams newParams, NotificationChain msgs)
  {
    transParams oldParams = params;
    params = newParams;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__PARAMS, oldParams, newParams);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParams(transParams newParams)
  {
    if (newParams != params)
    {
      NotificationChain msgs = null;
      if (params != null)
        msgs = ((InternalEObject)params).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANSITION__PARAMS, null, msgs);
      if (newParams != null)
        msgs = ((InternalEObject)newParams).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANSITION__PARAMS, null, msgs);
      msgs = basicSetParams(newParams, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__PARAMS, newParams, newParams));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public guard getTransGuard()
  {
    return transGuard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTransGuard(guard newTransGuard, NotificationChain msgs)
  {
    guard oldTransGuard = transGuard;
    transGuard = newTransGuard;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__TRANS_GUARD, oldTransGuard, newTransGuard);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTransGuard(guard newTransGuard)
  {
    if (newTransGuard != transGuard)
    {
      NotificationChain msgs = null;
      if (transGuard != null)
        msgs = ((InternalEObject)transGuard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANSITION__TRANS_GUARD, null, msgs);
      if (newTransGuard != null)
        msgs = ((InternalEObject)newTransGuard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANSITION__TRANS_GUARD, null, msgs);
      msgs = basicSetTransGuard(newTransGuard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__TRANS_GUARD, newTransGuard, newTransGuard));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public actionList getActions()
  {
    return actions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetActions(actionList newActions, NotificationChain msgs)
  {
    actionList oldActions = actions;
    actions = newActions;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__ACTIONS, oldActions, newActions);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setActions(actionList newActions)
  {
    if (newActions != actions)
    {
      NotificationChain msgs = null;
      if (actions != null)
        msgs = ((InternalEObject)actions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANSITION__ACTIONS, null, msgs);
      if (newActions != null)
        msgs = ((InternalEObject)newActions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANSITION__ACTIONS, null, msgs);
      msgs = basicSetActions(newActions, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__ACTIONS, newActions, newActions));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public sendActionList getSendActions()
  {
    return sendActions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSendActions(sendActionList newSendActions, NotificationChain msgs)
  {
    sendActionList oldSendActions = sendActions;
    sendActions = newSendActions;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__SEND_ACTIONS, oldSendActions, newSendActions);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSendActions(sendActionList newSendActions)
  {
    if (newSendActions != sendActions)
    {
      NotificationChain msgs = null;
      if (sendActions != null)
        msgs = ((InternalEObject)sendActions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANSITION__SEND_ACTIONS, null, msgs);
      if (newSendActions != null)
        msgs = ((InternalEObject)newSendActions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANSITION__SEND_ACTIONS, null, msgs);
      msgs = basicSetSendActions(newSendActions, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__SEND_ACTIONS, newSendActions, newSendActions));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getDestination()
  {
    return destination;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDestination(EObject newDestination, NotificationChain msgs)
  {
    EObject oldDestination = destination;
    destination = newDestination;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__DESTINATION, oldDestination, newDestination);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDestination(EObject newDestination)
  {
    if (newDestination != destination)
    {
      NotificationChain msgs = null;
      if (destination != null)
        msgs = ((InternalEObject)destination).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANSITION__DESTINATION, null, msgs);
      if (newDestination != null)
        msgs = ((InternalEObject)newDestination).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TRANSITION__DESTINATION, null, msgs);
      msgs = basicSetDestination(newDestination, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TRANSITION__DESTINATION, newDestination, newDestination));
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
      case CjsidlPackage.TRANSITION__PARAMS:
        return basicSetParams(null, msgs);
      case CjsidlPackage.TRANSITION__TRANS_GUARD:
        return basicSetTransGuard(null, msgs);
      case CjsidlPackage.TRANSITION__ACTIONS:
        return basicSetActions(null, msgs);
      case CjsidlPackage.TRANSITION__SEND_ACTIONS:
        return basicSetSendActions(null, msgs);
      case CjsidlPackage.TRANSITION__DESTINATION:
        return basicSetDestination(null, msgs);
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
      case CjsidlPackage.TRANSITION__COMMENT:
        return getComment();
      case CjsidlPackage.TRANSITION__TYPE:
        return getType();
      case CjsidlPackage.TRANSITION__SCOPED:
        return getScoped();
      case CjsidlPackage.TRANSITION__NAME:
        return getName();
      case CjsidlPackage.TRANSITION__PARAMS:
        return getParams();
      case CjsidlPackage.TRANSITION__TRANS_GUARD:
        return getTransGuard();
      case CjsidlPackage.TRANSITION__ACTIONS:
        return getActions();
      case CjsidlPackage.TRANSITION__SEND_ACTIONS:
        return getSendActions();
      case CjsidlPackage.TRANSITION__DESTINATION:
        return getDestination();
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
      case CjsidlPackage.TRANSITION__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.TRANSITION__TYPE:
        setType((String)newValue);
        return;
      case CjsidlPackage.TRANSITION__SCOPED:
        getScoped().clear();
        getScoped().addAll((Collection<? extends refAttr>)newValue);
        return;
      case CjsidlPackage.TRANSITION__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.TRANSITION__PARAMS:
        setParams((transParams)newValue);
        return;
      case CjsidlPackage.TRANSITION__TRANS_GUARD:
        setTransGuard((guard)newValue);
        return;
      case CjsidlPackage.TRANSITION__ACTIONS:
        setActions((actionList)newValue);
        return;
      case CjsidlPackage.TRANSITION__SEND_ACTIONS:
        setSendActions((sendActionList)newValue);
        return;
      case CjsidlPackage.TRANSITION__DESTINATION:
        setDestination((EObject)newValue);
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
      case CjsidlPackage.TRANSITION__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.TRANSITION__TYPE:
        setType(TYPE_EDEFAULT);
        return;
      case CjsidlPackage.TRANSITION__SCOPED:
        getScoped().clear();
        return;
      case CjsidlPackage.TRANSITION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.TRANSITION__PARAMS:
        setParams((transParams)null);
        return;
      case CjsidlPackage.TRANSITION__TRANS_GUARD:
        setTransGuard((guard)null);
        return;
      case CjsidlPackage.TRANSITION__ACTIONS:
        setActions((actionList)null);
        return;
      case CjsidlPackage.TRANSITION__SEND_ACTIONS:
        setSendActions((sendActionList)null);
        return;
      case CjsidlPackage.TRANSITION__DESTINATION:
        setDestination((EObject)null);
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
      case CjsidlPackage.TRANSITION__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.TRANSITION__TYPE:
        return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
      case CjsidlPackage.TRANSITION__SCOPED:
        return scoped != null && !scoped.isEmpty();
      case CjsidlPackage.TRANSITION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.TRANSITION__PARAMS:
        return params != null;
      case CjsidlPackage.TRANSITION__TRANS_GUARD:
        return transGuard != null;
      case CjsidlPackage.TRANSITION__ACTIONS:
        return actions != null;
      case CjsidlPackage.TRANSITION__SEND_ACTIONS:
        return sendActions != null;
      case CjsidlPackage.TRANSITION__DESTINATION:
        return destination != null;
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
    result.append(", type: ");
    result.append(type);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //transitionImpl
