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
import org.jts.eclipse.cjsidl.constReference;
import org.jts.eclipse.cjsidl.fixedLenString;
import org.jts.eclipse.cjsidl.scopedConstId;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>fixed Len String</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedLenStringImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedLenStringImpl#getOptional <em>Optional</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedLenStringImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedLenStringImpl#getUpperLim <em>Upper Lim</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedLenStringImpl#getUpperLimRef <em>Upper Lim Ref</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.fixedLenStringImpl#getUpperLimScoped <em>Upper Lim Scoped</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class fixedLenStringImpl extends MinimalEObjectImpl.Container implements fixedLenString
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
   * The default value of the '{@link #getUpperLim() <em>Upper Lim</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpperLim()
   * @generated
   * @ordered
   */
  protected static final String UPPER_LIM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUpperLim() <em>Upper Lim</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpperLim()
   * @generated
   * @ordered
   */
  protected String upperLim = UPPER_LIM_EDEFAULT;

  /**
   * The cached value of the '{@link #getUpperLimRef() <em>Upper Lim Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpperLimRef()
   * @generated
   * @ordered
   */
  protected constReference upperLimRef;

  /**
   * The cached value of the '{@link #getUpperLimScoped() <em>Upper Lim Scoped</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpperLimScoped()
   * @generated
   * @ordered
   */
  protected scopedConstId upperLimScoped;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected fixedLenStringImpl()
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
    return CjsidlPackage.eINSTANCE.getfixedLenString();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_LEN_STRING__COMMENT, oldComment, comment));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_LEN_STRING__OPTIONAL, oldOptional, optional));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_LEN_STRING__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUpperLim()
  {
    return upperLim;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUpperLim(String newUpperLim)
  {
    String oldUpperLim = upperLim;
    upperLim = newUpperLim;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM, oldUpperLim, upperLim));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public constReference getUpperLimRef()
  {
    return upperLimRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUpperLimRef(constReference newUpperLimRef, NotificationChain msgs)
  {
    constReference oldUpperLimRef = upperLimRef;
    upperLimRef = newUpperLimRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_REF, oldUpperLimRef, newUpperLimRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUpperLimRef(constReference newUpperLimRef)
  {
    if (newUpperLimRef != upperLimRef)
    {
      NotificationChain msgs = null;
      if (upperLimRef != null)
        msgs = ((InternalEObject)upperLimRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_REF, null, msgs);
      if (newUpperLimRef != null)
        msgs = ((InternalEObject)newUpperLimRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_REF, null, msgs);
      msgs = basicSetUpperLimRef(newUpperLimRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_REF, newUpperLimRef, newUpperLimRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public scopedConstId getUpperLimScoped()
  {
    return upperLimScoped;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUpperLimScoped(scopedConstId newUpperLimScoped, NotificationChain msgs)
  {
    scopedConstId oldUpperLimScoped = upperLimScoped;
    upperLimScoped = newUpperLimScoped;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_SCOPED, oldUpperLimScoped, newUpperLimScoped);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUpperLimScoped(scopedConstId newUpperLimScoped)
  {
    if (newUpperLimScoped != upperLimScoped)
    {
      NotificationChain msgs = null;
      if (upperLimScoped != null)
        msgs = ((InternalEObject)upperLimScoped).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_SCOPED, null, msgs);
      if (newUpperLimScoped != null)
        msgs = ((InternalEObject)newUpperLimScoped).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_SCOPED, null, msgs);
      msgs = basicSetUpperLimScoped(newUpperLimScoped, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_SCOPED, newUpperLimScoped, newUpperLimScoped));
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
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_REF:
        return basicSetUpperLimRef(null, msgs);
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_SCOPED:
        return basicSetUpperLimScoped(null, msgs);
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
      case CjsidlPackage.FIXED_LEN_STRING__COMMENT:
        return getComment();
      case CjsidlPackage.FIXED_LEN_STRING__OPTIONAL:
        return getOptional();
      case CjsidlPackage.FIXED_LEN_STRING__NAME:
        return getName();
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM:
        return getUpperLim();
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_REF:
        return getUpperLimRef();
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_SCOPED:
        return getUpperLimScoped();
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
      case CjsidlPackage.FIXED_LEN_STRING__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.FIXED_LEN_STRING__OPTIONAL:
        setOptional((String)newValue);
        return;
      case CjsidlPackage.FIXED_LEN_STRING__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM:
        setUpperLim((String)newValue);
        return;
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_REF:
        setUpperLimRef((constReference)newValue);
        return;
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_SCOPED:
        setUpperLimScoped((scopedConstId)newValue);
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
      case CjsidlPackage.FIXED_LEN_STRING__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.FIXED_LEN_STRING__OPTIONAL:
        setOptional(OPTIONAL_EDEFAULT);
        return;
      case CjsidlPackage.FIXED_LEN_STRING__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM:
        setUpperLim(UPPER_LIM_EDEFAULT);
        return;
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_REF:
        setUpperLimRef((constReference)null);
        return;
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_SCOPED:
        setUpperLimScoped((scopedConstId)null);
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
      case CjsidlPackage.FIXED_LEN_STRING__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.FIXED_LEN_STRING__OPTIONAL:
        return OPTIONAL_EDEFAULT == null ? optional != null : !OPTIONAL_EDEFAULT.equals(optional);
      case CjsidlPackage.FIXED_LEN_STRING__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM:
        return UPPER_LIM_EDEFAULT == null ? upperLim != null : !UPPER_LIM_EDEFAULT.equals(upperLim);
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_REF:
        return upperLimRef != null;
      case CjsidlPackage.FIXED_LEN_STRING__UPPER_LIM_SCOPED:
        return upperLimScoped != null;
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
    result.append(", upperLim: ");
    result.append(upperLim);
    result.append(')');
    return result.toString();
  }

} //fixedLenStringImpl
