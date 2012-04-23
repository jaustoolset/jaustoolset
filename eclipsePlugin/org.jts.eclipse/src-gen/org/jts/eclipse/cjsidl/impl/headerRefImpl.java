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
import org.jts.eclipse.cjsidl.headerDef;
import org.jts.eclipse.cjsidl.headerRef;
import org.jts.eclipse.cjsidl.headerScopedRef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>header Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.headerRefImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.headerRefImpl#getTypeRef <em>Type Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.headerRefImpl#getScopedRef <em>Scoped Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.headerRefImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class headerRefImpl extends MinimalEObjectImpl.Container implements headerRef
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
   * The cached value of the '{@link #getTypeRef() <em>Type Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeRef()
   * @generated
   * @ordered
   */
  protected headerDef typeRef;

  /**
   * The cached value of the '{@link #getScopedRef() <em>Scoped Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScopedRef()
   * @generated
   * @ordered
   */
  protected headerScopedRef scopedRef;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected headerRefImpl()
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
    return CjsidlPackage.eINSTANCE.getheaderRef();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.HEADER_REF__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public headerDef getTypeRef()
  {
    if (typeRef != null && typeRef.eIsProxy())
    {
      InternalEObject oldTypeRef = (InternalEObject)typeRef;
      typeRef = (headerDef)eResolveProxy(oldTypeRef);
      if (typeRef != oldTypeRef)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CjsidlPackage.HEADER_REF__TYPE_REF, oldTypeRef, typeRef));
      }
    }
    return typeRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public headerDef basicGetTypeRef()
  {
    return typeRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypeRef(headerDef newTypeRef)
  {
    headerDef oldTypeRef = typeRef;
    typeRef = newTypeRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.HEADER_REF__TYPE_REF, oldTypeRef, typeRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public headerScopedRef getScopedRef()
  {
    return scopedRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetScopedRef(headerScopedRef newScopedRef, NotificationChain msgs)
  {
    headerScopedRef oldScopedRef = scopedRef;
    scopedRef = newScopedRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.HEADER_REF__SCOPED_REF, oldScopedRef, newScopedRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScopedRef(headerScopedRef newScopedRef)
  {
    if (newScopedRef != scopedRef)
    {
      NotificationChain msgs = null;
      if (scopedRef != null)
        msgs = ((InternalEObject)scopedRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.HEADER_REF__SCOPED_REF, null, msgs);
      if (newScopedRef != null)
        msgs = ((InternalEObject)newScopedRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.HEADER_REF__SCOPED_REF, null, msgs);
      msgs = basicSetScopedRef(newScopedRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.HEADER_REF__SCOPED_REF, newScopedRef, newScopedRef));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.HEADER_REF__NAME, oldName, name));
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
      case CjsidlPackage.HEADER_REF__SCOPED_REF:
        return basicSetScopedRef(null, msgs);
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
      case CjsidlPackage.HEADER_REF__COMMENT:
        return getComment();
      case CjsidlPackage.HEADER_REF__TYPE_REF:
        if (resolve) return getTypeRef();
        return basicGetTypeRef();
      case CjsidlPackage.HEADER_REF__SCOPED_REF:
        return getScopedRef();
      case CjsidlPackage.HEADER_REF__NAME:
        return getName();
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
      case CjsidlPackage.HEADER_REF__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.HEADER_REF__TYPE_REF:
        setTypeRef((headerDef)newValue);
        return;
      case CjsidlPackage.HEADER_REF__SCOPED_REF:
        setScopedRef((headerScopedRef)newValue);
        return;
      case CjsidlPackage.HEADER_REF__NAME:
        setName((String)newValue);
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
      case CjsidlPackage.HEADER_REF__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.HEADER_REF__TYPE_REF:
        setTypeRef((headerDef)null);
        return;
      case CjsidlPackage.HEADER_REF__SCOPED_REF:
        setScopedRef((headerScopedRef)null);
        return;
      case CjsidlPackage.HEADER_REF__NAME:
        setName(NAME_EDEFAULT);
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
      case CjsidlPackage.HEADER_REF__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.HEADER_REF__TYPE_REF:
        return typeRef != null;
      case CjsidlPackage.HEADER_REF__SCOPED_REF:
        return scopedRef != null;
      case CjsidlPackage.HEADER_REF__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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

} //headerRefImpl
