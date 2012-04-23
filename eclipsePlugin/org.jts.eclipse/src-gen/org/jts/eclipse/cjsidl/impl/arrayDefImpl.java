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
import org.jts.eclipse.cjsidl.arrayDef;
import org.jts.eclipse.cjsidl.scopedType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>array Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.arrayDefImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.arrayDefImpl#getOptional <em>Optional</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.arrayDefImpl#getTypeRef <em>Type Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.arrayDefImpl#getScopedType <em>Scoped Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.arrayDefImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.arrayDefImpl#getArraySize <em>Array Size</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class arrayDefImpl extends MinimalEObjectImpl.Container implements arrayDef
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
   * The default value of the '{@link #getOptional() <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOptional()
   * @generated
   * @ordered
   */
  protected static final String OPTIONAL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOptional() <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOptional()
   * @generated
   * @ordered
   */
  protected String optional = OPTIONAL_EDEFAULT;

  /**
   * The cached value of the '{@link #getTypeRef() <em>Type Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeRef()
   * @generated
   * @ordered
   */
  protected EObject typeRef;

  /**
   * The cached value of the '{@link #getScopedType() <em>Scoped Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScopedType()
   * @generated
   * @ordered
   */
  protected scopedType scopedType;

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
   * The default value of the '{@link #getArraySize() <em>Array Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArraySize()
   * @generated
   * @ordered
   */
  protected static final String ARRAY_SIZE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getArraySize() <em>Array Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArraySize()
   * @generated
   * @ordered
   */
  protected String arraySize = ARRAY_SIZE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected arrayDefImpl()
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
    return CjsidlPackage.eINSTANCE.getarrayDef();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.ARRAY_DEF__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOptional()
  {
    return optional;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOptional(String newOptional)
  {
    String oldOptional = optional;
    optional = newOptional;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.ARRAY_DEF__OPTIONAL, oldOptional, optional));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getTypeRef()
  {
    if (typeRef != null && typeRef.eIsProxy())
    {
      InternalEObject oldTypeRef = (InternalEObject)typeRef;
      typeRef = eResolveProxy(oldTypeRef);
      if (typeRef != oldTypeRef)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CjsidlPackage.ARRAY_DEF__TYPE_REF, oldTypeRef, typeRef));
      }
    }
    return typeRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject basicGetTypeRef()
  {
    return typeRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypeRef(EObject newTypeRef)
  {
    EObject oldTypeRef = typeRef;
    typeRef = newTypeRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.ARRAY_DEF__TYPE_REF, oldTypeRef, typeRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scopedType getScopedType()
  {
    return scopedType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetScopedType(scopedType newScopedType, NotificationChain msgs)
  {
    scopedType oldScopedType = scopedType;
    scopedType = newScopedType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.ARRAY_DEF__SCOPED_TYPE, oldScopedType, newScopedType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScopedType(scopedType newScopedType)
  {
    if (newScopedType != scopedType)
    {
      NotificationChain msgs = null;
      if (scopedType != null)
        msgs = ((InternalEObject)scopedType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.ARRAY_DEF__SCOPED_TYPE, null, msgs);
      if (newScopedType != null)
        msgs = ((InternalEObject)newScopedType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.ARRAY_DEF__SCOPED_TYPE, null, msgs);
      msgs = basicSetScopedType(newScopedType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.ARRAY_DEF__SCOPED_TYPE, newScopedType, newScopedType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.ARRAY_DEF__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getArraySize()
  {
    return arraySize;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArraySize(String newArraySize)
  {
    String oldArraySize = arraySize;
    arraySize = newArraySize;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.ARRAY_DEF__ARRAY_SIZE, oldArraySize, arraySize));
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
      case CjsidlPackage.ARRAY_DEF__SCOPED_TYPE:
        return basicSetScopedType(null, msgs);
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
      case CjsidlPackage.ARRAY_DEF__COMMENT:
        return getComment();
      case CjsidlPackage.ARRAY_DEF__OPTIONAL:
        return getOptional();
      case CjsidlPackage.ARRAY_DEF__TYPE_REF:
        if (resolve) return getTypeRef();
        return basicGetTypeRef();
      case CjsidlPackage.ARRAY_DEF__SCOPED_TYPE:
        return getScopedType();
      case CjsidlPackage.ARRAY_DEF__NAME:
        return getName();
      case CjsidlPackage.ARRAY_DEF__ARRAY_SIZE:
        return getArraySize();
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
      case CjsidlPackage.ARRAY_DEF__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.ARRAY_DEF__OPTIONAL:
        setOptional((String)newValue);
        return;
      case CjsidlPackage.ARRAY_DEF__TYPE_REF:
        setTypeRef((EObject)newValue);
        return;
      case CjsidlPackage.ARRAY_DEF__SCOPED_TYPE:
        setScopedType((scopedType)newValue);
        return;
      case CjsidlPackage.ARRAY_DEF__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.ARRAY_DEF__ARRAY_SIZE:
        setArraySize((String)newValue);
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
      case CjsidlPackage.ARRAY_DEF__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.ARRAY_DEF__OPTIONAL:
        setOptional(OPTIONAL_EDEFAULT);
        return;
      case CjsidlPackage.ARRAY_DEF__TYPE_REF:
        setTypeRef((EObject)null);
        return;
      case CjsidlPackage.ARRAY_DEF__SCOPED_TYPE:
        setScopedType((scopedType)null);
        return;
      case CjsidlPackage.ARRAY_DEF__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.ARRAY_DEF__ARRAY_SIZE:
        setArraySize(ARRAY_SIZE_EDEFAULT);
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
      case CjsidlPackage.ARRAY_DEF__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.ARRAY_DEF__OPTIONAL:
        return OPTIONAL_EDEFAULT == null ? optional != null : !OPTIONAL_EDEFAULT.equals(optional);
      case CjsidlPackage.ARRAY_DEF__TYPE_REF:
        return typeRef != null;
      case CjsidlPackage.ARRAY_DEF__SCOPED_TYPE:
        return scopedType != null;
      case CjsidlPackage.ARRAY_DEF__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.ARRAY_DEF__ARRAY_SIZE:
        return ARRAY_SIZE_EDEFAULT == null ? arraySize != null : !ARRAY_SIZE_EDEFAULT.equals(arraySize);
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
    result.append(", optional: ");
    result.append(optional);
    result.append(", name: ");
    result.append(name);
    result.append(", arraySize: ");
    result.append(arraySize);
    result.append(')');
    return result.toString();
  }

} //arrayDefImpl
