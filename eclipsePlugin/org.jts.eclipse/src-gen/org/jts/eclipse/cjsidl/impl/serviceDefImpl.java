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
import org.jts.eclipse.cjsidl.declaredConstSet;
import org.jts.eclipse.cjsidl.declaredTypeSet;
import org.jts.eclipse.cjsidl.description;
import org.jts.eclipse.cjsidl.internalEventSet;
import org.jts.eclipse.cjsidl.messageSet;
import org.jts.eclipse.cjsidl.protocolBehavior;
import org.jts.eclipse.cjsidl.references;
import org.jts.eclipse.cjsidl.serviceDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>service Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl#getServiceName <em>Service Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl#getServiceVersion <em>Service Version</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl#getDescr <em>Descr</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl#getAssumpt <em>Assumpt</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl#getRefs <em>Refs</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl#getConstSet <em>Const Set</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl#getTypeSet <em>Type Set</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl#getMessageSet <em>Message Set</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl#getInternalEventSet <em>Internal Event Set</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.serviceDefImpl#getProtocolBehavior <em>Protocol Behavior</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class serviceDefImpl extends MinimalEObjectImpl.Container implements serviceDef
{
  /**
   * The default value of the '{@link #getServiceName() <em>Service Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getServiceName()
   * @generated
   * @ordered
   */
  protected static final String SERVICE_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getServiceName() <em>Service Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getServiceName()
   * @generated
   * @ordered
   */
  protected String serviceName = SERVICE_NAME_EDEFAULT;

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
   * The default value of the '{@link #getServiceVersion() <em>Service Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getServiceVersion()
   * @generated
   * @ordered
   */
  protected static final String SERVICE_VERSION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getServiceVersion() <em>Service Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getServiceVersion()
   * @generated
   * @ordered
   */
  protected String serviceVersion = SERVICE_VERSION_EDEFAULT;

  /**
   * The cached value of the '{@link #getDescr() <em>Descr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescr()
   * @generated
   * @ordered
   */
  protected description descr;

  /**
   * The default value of the '{@link #getAssumpt() <em>Assumpt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAssumpt()
   * @generated
   * @ordered
   */
  protected static final String ASSUMPT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAssumpt() <em>Assumpt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAssumpt()
   * @generated
   * @ordered
   */
  protected String assumpt = ASSUMPT_EDEFAULT;

  /**
   * The cached value of the '{@link #getRefs() <em>Refs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRefs()
   * @generated
   * @ordered
   */
  protected references refs;

  /**
   * The cached value of the '{@link #getConstSet() <em>Const Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstSet()
   * @generated
   * @ordered
   */
  protected declaredConstSet constSet;

  /**
   * The cached value of the '{@link #getTypeSet() <em>Type Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeSet()
   * @generated
   * @ordered
   */
  protected declaredTypeSet typeSet;

  /**
   * The cached value of the '{@link #getMessageSet() <em>Message Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessageSet()
   * @generated
   * @ordered
   */
  protected messageSet messageSet;

  /**
   * The cached value of the '{@link #getInternalEventSet() <em>Internal Event Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInternalEventSet()
   * @generated
   * @ordered
   */
  protected internalEventSet internalEventSet;

  /**
   * The cached value of the '{@link #getProtocolBehavior() <em>Protocol Behavior</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProtocolBehavior()
   * @generated
   * @ordered
   */
  protected protocolBehavior protocolBehavior;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected serviceDefImpl()
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
    return CjsidlPackage.eINSTANCE.getserviceDef();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getServiceName()
  {
    return serviceName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setServiceName(String newServiceName)
  {
    String oldServiceName = serviceName;
    serviceName = newServiceName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__SERVICE_NAME, oldServiceName, serviceName));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getServiceVersion()
  {
    return serviceVersion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setServiceVersion(String newServiceVersion)
  {
    String oldServiceVersion = serviceVersion;
    serviceVersion = newServiceVersion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__SERVICE_VERSION, oldServiceVersion, serviceVersion));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public description getDescr()
  {
    return descr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDescr(description newDescr, NotificationChain msgs)
  {
    description oldDescr = descr;
    descr = newDescr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__DESCR, oldDescr, newDescr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDescr(description newDescr)
  {
    if (newDescr != descr)
    {
      NotificationChain msgs = null;
      if (descr != null)
        msgs = ((InternalEObject)descr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__DESCR, null, msgs);
      if (newDescr != null)
        msgs = ((InternalEObject)newDescr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__DESCR, null, msgs);
      msgs = basicSetDescr(newDescr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__DESCR, newDescr, newDescr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAssumpt()
  {
    return assumpt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAssumpt(String newAssumpt)
  {
    String oldAssumpt = assumpt;
    assumpt = newAssumpt;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__ASSUMPT, oldAssumpt, assumpt));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public references getRefs()
  {
    return refs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRefs(references newRefs, NotificationChain msgs)
  {
    references oldRefs = refs;
    refs = newRefs;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__REFS, oldRefs, newRefs);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRefs(references newRefs)
  {
    if (newRefs != refs)
    {
      NotificationChain msgs = null;
      if (refs != null)
        msgs = ((InternalEObject)refs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__REFS, null, msgs);
      if (newRefs != null)
        msgs = ((InternalEObject)newRefs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__REFS, null, msgs);
      msgs = basicSetRefs(newRefs, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__REFS, newRefs, newRefs));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public declaredConstSet getConstSet()
  {
    return constSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstSet(declaredConstSet newConstSet, NotificationChain msgs)
  {
    declaredConstSet oldConstSet = constSet;
    constSet = newConstSet;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__CONST_SET, oldConstSet, newConstSet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstSet(declaredConstSet newConstSet)
  {
    if (newConstSet != constSet)
    {
      NotificationChain msgs = null;
      if (constSet != null)
        msgs = ((InternalEObject)constSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__CONST_SET, null, msgs);
      if (newConstSet != null)
        msgs = ((InternalEObject)newConstSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__CONST_SET, null, msgs);
      msgs = basicSetConstSet(newConstSet, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__CONST_SET, newConstSet, newConstSet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public declaredTypeSet getTypeSet()
  {
    return typeSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTypeSet(declaredTypeSet newTypeSet, NotificationChain msgs)
  {
    declaredTypeSet oldTypeSet = typeSet;
    typeSet = newTypeSet;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__TYPE_SET, oldTypeSet, newTypeSet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypeSet(declaredTypeSet newTypeSet)
  {
    if (newTypeSet != typeSet)
    {
      NotificationChain msgs = null;
      if (typeSet != null)
        msgs = ((InternalEObject)typeSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__TYPE_SET, null, msgs);
      if (newTypeSet != null)
        msgs = ((InternalEObject)newTypeSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__TYPE_SET, null, msgs);
      msgs = basicSetTypeSet(newTypeSet, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__TYPE_SET, newTypeSet, newTypeSet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public messageSet getMessageSet()
  {
    return messageSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMessageSet(messageSet newMessageSet, NotificationChain msgs)
  {
    messageSet oldMessageSet = messageSet;
    messageSet = newMessageSet;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__MESSAGE_SET, oldMessageSet, newMessageSet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMessageSet(messageSet newMessageSet)
  {
    if (newMessageSet != messageSet)
    {
      NotificationChain msgs = null;
      if (messageSet != null)
        msgs = ((InternalEObject)messageSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__MESSAGE_SET, null, msgs);
      if (newMessageSet != null)
        msgs = ((InternalEObject)newMessageSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__MESSAGE_SET, null, msgs);
      msgs = basicSetMessageSet(newMessageSet, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__MESSAGE_SET, newMessageSet, newMessageSet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public internalEventSet getInternalEventSet()
  {
    return internalEventSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInternalEventSet(internalEventSet newInternalEventSet, NotificationChain msgs)
  {
    internalEventSet oldInternalEventSet = internalEventSet;
    internalEventSet = newInternalEventSet;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__INTERNAL_EVENT_SET, oldInternalEventSet, newInternalEventSet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInternalEventSet(internalEventSet newInternalEventSet)
  {
    if (newInternalEventSet != internalEventSet)
    {
      NotificationChain msgs = null;
      if (internalEventSet != null)
        msgs = ((InternalEObject)internalEventSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__INTERNAL_EVENT_SET, null, msgs);
      if (newInternalEventSet != null)
        msgs = ((InternalEObject)newInternalEventSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__INTERNAL_EVENT_SET, null, msgs);
      msgs = basicSetInternalEventSet(newInternalEventSet, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__INTERNAL_EVENT_SET, newInternalEventSet, newInternalEventSet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public protocolBehavior getProtocolBehavior()
  {
    return protocolBehavior;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetProtocolBehavior(protocolBehavior newProtocolBehavior, NotificationChain msgs)
  {
    protocolBehavior oldProtocolBehavior = protocolBehavior;
    protocolBehavior = newProtocolBehavior;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__PROTOCOL_BEHAVIOR, oldProtocolBehavior, newProtocolBehavior);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProtocolBehavior(protocolBehavior newProtocolBehavior)
  {
    if (newProtocolBehavior != protocolBehavior)
    {
      NotificationChain msgs = null;
      if (protocolBehavior != null)
        msgs = ((InternalEObject)protocolBehavior).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__PROTOCOL_BEHAVIOR, null, msgs);
      if (newProtocolBehavior != null)
        msgs = ((InternalEObject)newProtocolBehavior).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.SERVICE_DEF__PROTOCOL_BEHAVIOR, null, msgs);
      msgs = basicSetProtocolBehavior(newProtocolBehavior, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.SERVICE_DEF__PROTOCOL_BEHAVIOR, newProtocolBehavior, newProtocolBehavior));
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
      case CjsidlPackage.SERVICE_DEF__DESCR:
        return basicSetDescr(null, msgs);
      case CjsidlPackage.SERVICE_DEF__REFS:
        return basicSetRefs(null, msgs);
      case CjsidlPackage.SERVICE_DEF__CONST_SET:
        return basicSetConstSet(null, msgs);
      case CjsidlPackage.SERVICE_DEF__TYPE_SET:
        return basicSetTypeSet(null, msgs);
      case CjsidlPackage.SERVICE_DEF__MESSAGE_SET:
        return basicSetMessageSet(null, msgs);
      case CjsidlPackage.SERVICE_DEF__INTERNAL_EVENT_SET:
        return basicSetInternalEventSet(null, msgs);
      case CjsidlPackage.SERVICE_DEF__PROTOCOL_BEHAVIOR:
        return basicSetProtocolBehavior(null, msgs);
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
      case CjsidlPackage.SERVICE_DEF__SERVICE_NAME:
        return getServiceName();
      case CjsidlPackage.SERVICE_DEF__NAME:
        return getName();
      case CjsidlPackage.SERVICE_DEF__SERVICE_VERSION:
        return getServiceVersion();
      case CjsidlPackage.SERVICE_DEF__DESCR:
        return getDescr();
      case CjsidlPackage.SERVICE_DEF__ASSUMPT:
        return getAssumpt();
      case CjsidlPackage.SERVICE_DEF__REFS:
        return getRefs();
      case CjsidlPackage.SERVICE_DEF__CONST_SET:
        return getConstSet();
      case CjsidlPackage.SERVICE_DEF__TYPE_SET:
        return getTypeSet();
      case CjsidlPackage.SERVICE_DEF__MESSAGE_SET:
        return getMessageSet();
      case CjsidlPackage.SERVICE_DEF__INTERNAL_EVENT_SET:
        return getInternalEventSet();
      case CjsidlPackage.SERVICE_DEF__PROTOCOL_BEHAVIOR:
        return getProtocolBehavior();
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
      case CjsidlPackage.SERVICE_DEF__SERVICE_NAME:
        setServiceName((String)newValue);
        return;
      case CjsidlPackage.SERVICE_DEF__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.SERVICE_DEF__SERVICE_VERSION:
        setServiceVersion((String)newValue);
        return;
      case CjsidlPackage.SERVICE_DEF__DESCR:
        setDescr((description)newValue);
        return;
      case CjsidlPackage.SERVICE_DEF__ASSUMPT:
        setAssumpt((String)newValue);
        return;
      case CjsidlPackage.SERVICE_DEF__REFS:
        setRefs((references)newValue);
        return;
      case CjsidlPackage.SERVICE_DEF__CONST_SET:
        setConstSet((declaredConstSet)newValue);
        return;
      case CjsidlPackage.SERVICE_DEF__TYPE_SET:
        setTypeSet((declaredTypeSet)newValue);
        return;
      case CjsidlPackage.SERVICE_DEF__MESSAGE_SET:
        setMessageSet((messageSet)newValue);
        return;
      case CjsidlPackage.SERVICE_DEF__INTERNAL_EVENT_SET:
        setInternalEventSet((internalEventSet)newValue);
        return;
      case CjsidlPackage.SERVICE_DEF__PROTOCOL_BEHAVIOR:
        setProtocolBehavior((protocolBehavior)newValue);
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
      case CjsidlPackage.SERVICE_DEF__SERVICE_NAME:
        setServiceName(SERVICE_NAME_EDEFAULT);
        return;
      case CjsidlPackage.SERVICE_DEF__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.SERVICE_DEF__SERVICE_VERSION:
        setServiceVersion(SERVICE_VERSION_EDEFAULT);
        return;
      case CjsidlPackage.SERVICE_DEF__DESCR:
        setDescr((description)null);
        return;
      case CjsidlPackage.SERVICE_DEF__ASSUMPT:
        setAssumpt(ASSUMPT_EDEFAULT);
        return;
      case CjsidlPackage.SERVICE_DEF__REFS:
        setRefs((references)null);
        return;
      case CjsidlPackage.SERVICE_DEF__CONST_SET:
        setConstSet((declaredConstSet)null);
        return;
      case CjsidlPackage.SERVICE_DEF__TYPE_SET:
        setTypeSet((declaredTypeSet)null);
        return;
      case CjsidlPackage.SERVICE_DEF__MESSAGE_SET:
        setMessageSet((messageSet)null);
        return;
      case CjsidlPackage.SERVICE_DEF__INTERNAL_EVENT_SET:
        setInternalEventSet((internalEventSet)null);
        return;
      case CjsidlPackage.SERVICE_DEF__PROTOCOL_BEHAVIOR:
        setProtocolBehavior((protocolBehavior)null);
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
      case CjsidlPackage.SERVICE_DEF__SERVICE_NAME:
        return SERVICE_NAME_EDEFAULT == null ? serviceName != null : !SERVICE_NAME_EDEFAULT.equals(serviceName);
      case CjsidlPackage.SERVICE_DEF__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.SERVICE_DEF__SERVICE_VERSION:
        return SERVICE_VERSION_EDEFAULT == null ? serviceVersion != null : !SERVICE_VERSION_EDEFAULT.equals(serviceVersion);
      case CjsidlPackage.SERVICE_DEF__DESCR:
        return descr != null;
      case CjsidlPackage.SERVICE_DEF__ASSUMPT:
        return ASSUMPT_EDEFAULT == null ? assumpt != null : !ASSUMPT_EDEFAULT.equals(assumpt);
      case CjsidlPackage.SERVICE_DEF__REFS:
        return refs != null;
      case CjsidlPackage.SERVICE_DEF__CONST_SET:
        return constSet != null;
      case CjsidlPackage.SERVICE_DEF__TYPE_SET:
        return typeSet != null;
      case CjsidlPackage.SERVICE_DEF__MESSAGE_SET:
        return messageSet != null;
      case CjsidlPackage.SERVICE_DEF__INTERNAL_EVENT_SET:
        return internalEventSet != null;
      case CjsidlPackage.SERVICE_DEF__PROTOCOL_BEHAVIOR:
        return protocolBehavior != null;
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
    result.append(" (serviceName: ");
    result.append(serviceName);
    result.append(", name: ");
    result.append(name);
    result.append(", serviceVersion: ");
    result.append(serviceVersion);
    result.append(", assumpt: ");
    result.append(assumpt);
    result.append(')');
    return result.toString();
  }

} //serviceDefImpl
