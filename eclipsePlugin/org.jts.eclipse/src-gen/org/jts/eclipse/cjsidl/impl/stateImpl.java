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
import org.jts.eclipse.cjsidl.entry;
import org.jts.eclipse.cjsidl.exit;
import org.jts.eclipse.cjsidl.state;
import org.jts.eclipse.cjsidl.transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>state</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateImpl#getInitial <em>Initial</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateImpl#getEntryAction <em>Entry Action</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateImpl#getExitAction <em>Exit Action</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateImpl#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateImpl#getDefaultTransition <em>Default Transition</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateImpl#getDefaultState <em>Default State</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.stateImpl#getSubState <em>Sub State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class stateImpl extends MinimalEObjectImpl.Container implements state
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
   * The default value of the '{@link #getInitial() <em>Initial</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitial()
   * @generated
   * @ordered
   */
  protected static final String INITIAL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getInitial() <em>Initial</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitial()
   * @generated
   * @ordered
   */
  protected String initial = INITIAL_EDEFAULT;

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
   * The cached value of the '{@link #getEntryAction() <em>Entry Action</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntryAction()
   * @generated
   * @ordered
   */
  protected entry entryAction;

  /**
   * The cached value of the '{@link #getExitAction() <em>Exit Action</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExitAction()
   * @generated
   * @ordered
   */
  protected exit exitAction;

  /**
   * The cached value of the '{@link #getTransitions() <em>Transitions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTransitions()
   * @generated
   * @ordered
   */
  protected EList<transition> transitions;

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
   * The cached value of the '{@link #getDefaultState() <em>Default State</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaultState()
   * @generated
   * @ordered
   */
  protected defaultState defaultState;

  /**
   * The cached value of the '{@link #getSubState() <em>Sub State</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubState()
   * @generated
   * @ordered
   */
  protected EList<state> subState;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected stateImpl()
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
    return CjsidlPackage.eINSTANCE.getstate();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getInitial()
  {
    return initial;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInitial(String newInitial)
  {
    String oldInitial = initial;
    initial = newInitial;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE__INITIAL, oldInitial, initial));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public entry getEntryAction()
  {
    return entryAction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEntryAction(entry newEntryAction, NotificationChain msgs)
  {
    entry oldEntryAction = entryAction;
    entryAction = newEntryAction;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE__ENTRY_ACTION, oldEntryAction, newEntryAction);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEntryAction(entry newEntryAction)
  {
    if (newEntryAction != entryAction)
    {
      NotificationChain msgs = null;
      if (entryAction != null)
        msgs = ((InternalEObject)entryAction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.STATE__ENTRY_ACTION, null, msgs);
      if (newEntryAction != null)
        msgs = ((InternalEObject)newEntryAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.STATE__ENTRY_ACTION, null, msgs);
      msgs = basicSetEntryAction(newEntryAction, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE__ENTRY_ACTION, newEntryAction, newEntryAction));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public exit getExitAction()
  {
    return exitAction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExitAction(exit newExitAction, NotificationChain msgs)
  {
    exit oldExitAction = exitAction;
    exitAction = newExitAction;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE__EXIT_ACTION, oldExitAction, newExitAction);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExitAction(exit newExitAction)
  {
    if (newExitAction != exitAction)
    {
      NotificationChain msgs = null;
      if (exitAction != null)
        msgs = ((InternalEObject)exitAction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.STATE__EXIT_ACTION, null, msgs);
      if (newExitAction != null)
        msgs = ((InternalEObject)newExitAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.STATE__EXIT_ACTION, null, msgs);
      msgs = basicSetExitAction(newExitAction, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE__EXIT_ACTION, newExitAction, newExitAction));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<transition> getTransitions()
  {
    if (transitions == null)
    {
      transitions = new EObjectContainmentEList<transition>(transition.class, this, CjsidlPackage.STATE__TRANSITIONS);
    }
    return transitions;
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
      defaultTransition = new EObjectContainmentEList<defaultTransition>(defaultTransition.class, this, CjsidlPackage.STATE__DEFAULT_TRANSITION);
    }
    return defaultTransition;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE__DEFAULT_STATE, oldDefaultState, newDefaultState);
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
        msgs = ((InternalEObject)defaultState).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.STATE__DEFAULT_STATE, null, msgs);
      if (newDefaultState != null)
        msgs = ((InternalEObject)newDefaultState).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.STATE__DEFAULT_STATE, null, msgs);
      msgs = basicSetDefaultState(newDefaultState, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.STATE__DEFAULT_STATE, newDefaultState, newDefaultState));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<state> getSubState()
  {
    if (subState == null)
    {
      subState = new EObjectContainmentEList<state>(state.class, this, CjsidlPackage.STATE__SUB_STATE);
    }
    return subState;
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
      case CjsidlPackage.STATE__ENTRY_ACTION:
        return basicSetEntryAction(null, msgs);
      case CjsidlPackage.STATE__EXIT_ACTION:
        return basicSetExitAction(null, msgs);
      case CjsidlPackage.STATE__TRANSITIONS:
        return ((InternalEList<?>)getTransitions()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.STATE__DEFAULT_TRANSITION:
        return ((InternalEList<?>)getDefaultTransition()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.STATE__DEFAULT_STATE:
        return basicSetDefaultState(null, msgs);
      case CjsidlPackage.STATE__SUB_STATE:
        return ((InternalEList<?>)getSubState()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.STATE__COMMENT:
        return getComment();
      case CjsidlPackage.STATE__INITIAL:
        return getInitial();
      case CjsidlPackage.STATE__NAME:
        return getName();
      case CjsidlPackage.STATE__ENTRY_ACTION:
        return getEntryAction();
      case CjsidlPackage.STATE__EXIT_ACTION:
        return getExitAction();
      case CjsidlPackage.STATE__TRANSITIONS:
        return getTransitions();
      case CjsidlPackage.STATE__DEFAULT_TRANSITION:
        return getDefaultTransition();
      case CjsidlPackage.STATE__DEFAULT_STATE:
        return getDefaultState();
      case CjsidlPackage.STATE__SUB_STATE:
        return getSubState();
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
      case CjsidlPackage.STATE__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.STATE__INITIAL:
        setInitial((String)newValue);
        return;
      case CjsidlPackage.STATE__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.STATE__ENTRY_ACTION:
        setEntryAction((entry)newValue);
        return;
      case CjsidlPackage.STATE__EXIT_ACTION:
        setExitAction((exit)newValue);
        return;
      case CjsidlPackage.STATE__TRANSITIONS:
        getTransitions().clear();
        getTransitions().addAll((Collection<? extends transition>)newValue);
        return;
      case CjsidlPackage.STATE__DEFAULT_TRANSITION:
        getDefaultTransition().clear();
        getDefaultTransition().addAll((Collection<? extends defaultTransition>)newValue);
        return;
      case CjsidlPackage.STATE__DEFAULT_STATE:
        setDefaultState((defaultState)newValue);
        return;
      case CjsidlPackage.STATE__SUB_STATE:
        getSubState().clear();
        getSubState().addAll((Collection<? extends state>)newValue);
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
      case CjsidlPackage.STATE__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.STATE__INITIAL:
        setInitial(INITIAL_EDEFAULT);
        return;
      case CjsidlPackage.STATE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.STATE__ENTRY_ACTION:
        setEntryAction((entry)null);
        return;
      case CjsidlPackage.STATE__EXIT_ACTION:
        setExitAction((exit)null);
        return;
      case CjsidlPackage.STATE__TRANSITIONS:
        getTransitions().clear();
        return;
      case CjsidlPackage.STATE__DEFAULT_TRANSITION:
        getDefaultTransition().clear();
        return;
      case CjsidlPackage.STATE__DEFAULT_STATE:
        setDefaultState((defaultState)null);
        return;
      case CjsidlPackage.STATE__SUB_STATE:
        getSubState().clear();
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
      case CjsidlPackage.STATE__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.STATE__INITIAL:
        return INITIAL_EDEFAULT == null ? initial != null : !INITIAL_EDEFAULT.equals(initial);
      case CjsidlPackage.STATE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.STATE__ENTRY_ACTION:
        return entryAction != null;
      case CjsidlPackage.STATE__EXIT_ACTION:
        return exitAction != null;
      case CjsidlPackage.STATE__TRANSITIONS:
        return transitions != null && !transitions.isEmpty();
      case CjsidlPackage.STATE__DEFAULT_TRANSITION:
        return defaultTransition != null && !defaultTransition.isEmpty();
      case CjsidlPackage.STATE__DEFAULT_STATE:
        return defaultState != null;
      case CjsidlPackage.STATE__SUB_STATE:
        return subState != null && !subState.isEmpty();
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
    result.append(", initial: ");
    result.append(initial);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //stateImpl
