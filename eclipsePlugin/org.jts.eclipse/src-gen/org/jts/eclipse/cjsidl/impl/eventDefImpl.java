/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.description;
import org.jts.eclipse.cjsidl.eventDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>event Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.eventDefImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.eventDefImpl#getDescr <em>Descr</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.eventDefImpl#getHeader <em>Header</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.eventDefImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.eventDefImpl#getFooter <em>Footer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class eventDefImpl extends MinimalEObjectImpl.Container implements eventDef
{
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
   * The cached value of the '{@link #getDescr() <em>Descr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescr()
   * @generated
   * @ordered
   */
  protected description descr;

  /**
   * The cached value of the '{@link #getHeader() <em>Header</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHeader()
   * @generated
   * @ordered
   */
  protected EObject header;

  /**
   * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBody()
   * @generated
   * @ordered
   */
  protected EObject body;

  /**
   * The cached value of the '{@link #getFooter() <em>Footer</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFooter()
   * @generated
   * @ordered
   */
  protected EObject footer;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected eventDefImpl()
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
    return CjsidlPackage.eINSTANCE.geteventDef();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.EVENT_DEF__NAME, oldName, name));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.EVENT_DEF__DESCR, oldDescr, newDescr);
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
        msgs = ((InternalEObject)descr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.EVENT_DEF__DESCR, null, msgs);
      if (newDescr != null)
        msgs = ((InternalEObject)newDescr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.EVENT_DEF__DESCR, null, msgs);
      msgs = basicSetDescr(newDescr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.EVENT_DEF__DESCR, newDescr, newDescr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getHeader()
  {
    return header;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHeader(EObject newHeader, NotificationChain msgs)
  {
    EObject oldHeader = header;
    header = newHeader;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.EVENT_DEF__HEADER, oldHeader, newHeader);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHeader(EObject newHeader)
  {
    if (newHeader != header)
    {
      NotificationChain msgs = null;
      if (header != null)
        msgs = ((InternalEObject)header).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.EVENT_DEF__HEADER, null, msgs);
      if (newHeader != null)
        msgs = ((InternalEObject)newHeader).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.EVENT_DEF__HEADER, null, msgs);
      msgs = basicSetHeader(newHeader, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.EVENT_DEF__HEADER, newHeader, newHeader));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getBody()
  {
    return body;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBody(EObject newBody, NotificationChain msgs)
  {
    EObject oldBody = body;
    body = newBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.EVENT_DEF__BODY, oldBody, newBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBody(EObject newBody)
  {
    if (newBody != body)
    {
      NotificationChain msgs = null;
      if (body != null)
        msgs = ((InternalEObject)body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.EVENT_DEF__BODY, null, msgs);
      if (newBody != null)
        msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.EVENT_DEF__BODY, null, msgs);
      msgs = basicSetBody(newBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.EVENT_DEF__BODY, newBody, newBody));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getFooter()
  {
    return footer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFooter(EObject newFooter, NotificationChain msgs)
  {
    EObject oldFooter = footer;
    footer = newFooter;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.EVENT_DEF__FOOTER, oldFooter, newFooter);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFooter(EObject newFooter)
  {
    if (newFooter != footer)
    {
      NotificationChain msgs = null;
      if (footer != null)
        msgs = ((InternalEObject)footer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.EVENT_DEF__FOOTER, null, msgs);
      if (newFooter != null)
        msgs = ((InternalEObject)newFooter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.EVENT_DEF__FOOTER, null, msgs);
      msgs = basicSetFooter(newFooter, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.EVENT_DEF__FOOTER, newFooter, newFooter));
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
      case CjsidlPackage.EVENT_DEF__DESCR:
        return basicSetDescr(null, msgs);
      case CjsidlPackage.EVENT_DEF__HEADER:
        return basicSetHeader(null, msgs);
      case CjsidlPackage.EVENT_DEF__BODY:
        return basicSetBody(null, msgs);
      case CjsidlPackage.EVENT_DEF__FOOTER:
        return basicSetFooter(null, msgs);
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
      case CjsidlPackage.EVENT_DEF__NAME:
        return getName();
      case CjsidlPackage.EVENT_DEF__DESCR:
        return getDescr();
      case CjsidlPackage.EVENT_DEF__HEADER:
        return getHeader();
      case CjsidlPackage.EVENT_DEF__BODY:
        return getBody();
      case CjsidlPackage.EVENT_DEF__FOOTER:
        return getFooter();
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
      case CjsidlPackage.EVENT_DEF__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.EVENT_DEF__DESCR:
        setDescr((description)newValue);
        return;
      case CjsidlPackage.EVENT_DEF__HEADER:
        setHeader((EObject)newValue);
        return;
      case CjsidlPackage.EVENT_DEF__BODY:
        setBody((EObject)newValue);
        return;
      case CjsidlPackage.EVENT_DEF__FOOTER:
        setFooter((EObject)newValue);
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
      case CjsidlPackage.EVENT_DEF__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.EVENT_DEF__DESCR:
        setDescr((description)null);
        return;
      case CjsidlPackage.EVENT_DEF__HEADER:
        setHeader((EObject)null);
        return;
      case CjsidlPackage.EVENT_DEF__BODY:
        setBody((EObject)null);
        return;
      case CjsidlPackage.EVENT_DEF__FOOTER:
        setFooter((EObject)null);
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
      case CjsidlPackage.EVENT_DEF__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.EVENT_DEF__DESCR:
        return descr != null;
      case CjsidlPackage.EVENT_DEF__HEADER:
        return header != null;
      case CjsidlPackage.EVENT_DEF__BODY:
        return body != null;
      case CjsidlPackage.EVENT_DEF__FOOTER:
        return footer != null;
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //eventDefImpl
