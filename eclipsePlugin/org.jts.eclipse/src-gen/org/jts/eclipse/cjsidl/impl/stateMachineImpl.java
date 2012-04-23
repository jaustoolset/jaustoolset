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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.defaultState;
import org.jts.eclipse.cjsidl.refAttr;
import org.jts.eclipse.cjsidl.startState;
import org.jts.eclipse.cjsidl.state;
import org.jts.eclipse.cjsidl.stateMachine;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>state Machine</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateMachineImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateMachineImpl#getScoped <em>Scoped</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateMachineImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateMachineImpl#getStartState <em>Start State</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateMachineImpl#getDefaultState <em>Default State</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateMachineImpl#getStates <em>States</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class stateMachineImpl extends MinimalEObjectImpl.Container implements stateMachine
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
   * The cached value of the '{@link #getStartState() <em>Start State</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStartState()
   * @generated
   * @ordered
   */
  protected startState startState;

  /**
   * The cached value of the '{@link #getDefaultState() <em>Default State</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaultState()
   * @generated
   * @ordered
   */
  protected defaultState defaultState;

  /**
   * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStates()
   * @generated
   * @ordered
   */
  protected EList<state> states;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected stateMachineImpl()
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
    return CjsidlPackage.eINSTANCE.getstateMachine();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE_MACHINE__COMMENT, oldComment, comment));
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
      scoped = new EObjectResolvingEList<refAttr>(refAttr.class, this, CjsidlPackage.STATE_MACHINE__SCOPED);
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE_MACHINE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public startState getStartState()
  {
    return startState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStartState(startState newStartState, NotificationChain msgs)
  {
    startState oldStartState = startState;
    startState = newStartState;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE_MACHINE__START_STATE, oldStartState, newStartState);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStartState(startState newStartState)
  {
    if (newStartState != startState)
    {
      NotificationChain msgs = null;
      if (startState != null)
        msgs = ((InternalEObject)startState).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.STATE_MACHINE__START_STATE, null, msgs);
      if (newStartState != null)
        msgs = ((InternalEObject)newStartState).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.STATE_MACHINE__START_STATE, null, msgs);
      msgs = basicSetStartState(newStartState, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE_MACHINE__START_STATE, newStartState, newStartState));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public defaultState getDefaultState()
  {
    return defaultState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDefaultState(defaultState newDefaultState, NotificationChain msgs)
  {
    defaultState oldDefaultState = defaultState;
    defaultState = newDefaultState;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE_MACHINE__DEFAULT_STATE, oldDefaultState, newDefaultState);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDefaultState(defaultState newDefaultState)
  {
    if (newDefaultState != defaultState)
    {
      NotificationChain msgs = null;
      if (defaultState != null)
        msgs = ((InternalEObject)defaultState).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.STATE_MACHINE__DEFAULT_STATE, null, msgs);
      if (newDefaultState != null)
        msgs = ((InternalEObject)newDefaultState).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.STATE_MACHINE__DEFAULT_STATE, null, msgs);
      msgs = basicSetDefaultState(newDefaultState, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE_MACHINE__DEFAULT_STATE, newDefaultState, newDefaultState));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<state> getStates()
  {
    if (states == null)
    {
      states = new EObjectContainmentEList<state>(state.class, this, CjsidlPackage.STATE_MACHINE__STATES);
    }
    return states;
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
      case CjsidlPackage.STATE_MACHINE__START_STATE:
        return basicSetStartState(null, msgs);
      case CjsidlPackage.STATE_MACHINE__DEFAULT_STATE:
        return basicSetDefaultState(null, msgs);
      case CjsidlPackage.STATE_MACHINE__STATES:
        return ((InternalEList<?>)getStates()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.STATE_MACHINE__COMMENT:
        return getComment();
      case CjsidlPackage.STATE_MACHINE__SCOPED:
        return getScoped();
      case CjsidlPackage.STATE_MACHINE__NAME:
        return getName();
      case CjsidlPackage.STATE_MACHINE__START_STATE:
        return getStartState();
      case CjsidlPackage.STATE_MACHINE__DEFAULT_STATE:
        return getDefaultState();
      case CjsidlPackage.STATE_MACHINE__STATES:
        return getStates();
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
      case CjsidlPackage.STATE_MACHINE__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.STATE_MACHINE__SCOPED:
        getScoped().clear();
        getScoped().addAll((Collection<? extends refAttr>)newValue);
        return;
      case CjsidlPackage.STATE_MACHINE__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.STATE_MACHINE__START_STATE:
        setStartState((startState)newValue);
        return;
      case CjsidlPackage.STATE_MACHINE__DEFAULT_STATE:
        setDefaultState((defaultState)newValue);
        return;
      case CjsidlPackage.STATE_MACHINE__STATES:
        getStates().clear();
        getStates().addAll((Collection<? extends state>)newValue);
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
      case CjsidlPackage.STATE_MACHINE__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.STATE_MACHINE__SCOPED:
        getScoped().clear();
        return;
      case CjsidlPackage.STATE_MACHINE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.STATE_MACHINE__START_STATE:
        setStartState((startState)null);
        return;
      case CjsidlPackage.STATE_MACHINE__DEFAULT_STATE:
        setDefaultState((defaultState)null);
        return;
      case CjsidlPackage.STATE_MACHINE__STATES:
        getStates().clear();
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
      case CjsidlPackage.STATE_MACHINE__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.STATE_MACHINE__SCOPED:
        return scoped != null && !scoped.isEmpty();
      case CjsidlPackage.STATE_MACHINE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.STATE_MACHINE__START_STATE:
        return startState != null;
      case CjsidlPackage.STATE_MACHINE__DEFAULT_STATE:
        return defaultState != null;
      case CjsidlPackage.STATE_MACHINE__STATES:
        return states != null && !states.isEmpty();
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
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //stateMachineImpl
