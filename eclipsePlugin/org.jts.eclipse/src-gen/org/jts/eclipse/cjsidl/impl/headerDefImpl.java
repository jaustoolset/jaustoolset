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
import org.jts.eclipse.cjsidl.headerDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>header Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.headerDefImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.headerDefImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.headerDefImpl#getRecordListSequenceVariant <em>Record List Sequence Variant</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class headerDefImpl extends MinimalEObjectImpl.Container implements headerDef
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
   * The cached value of the '{@link #getRecordListSequenceVariant() <em>Record List Sequence Variant</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRecordListSequenceVariant()
   * @generated
   * @ordered
   */
  protected EObject recordListSequenceVariant;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected headerDefImpl()
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
    return CjsidlPackage.eINSTANCE.getheaderDef();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.HEADER_DEF__COMMENT, oldComment, comment));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.HEADER_DEF__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getRecordListSequenceVariant()
  {
    return recordListSequenceVariant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRecordListSequenceVariant(EObject newRecordListSequenceVariant, NotificationChain msgs)
  {
    EObject oldRecordListSequenceVariant = recordListSequenceVariant;
    recordListSequenceVariant = newRecordListSequenceVariant;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.HEADER_DEF__RECORD_LIST_SEQUENCE_VARIANT, oldRecordListSequenceVariant, newRecordListSequenceVariant);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRecordListSequenceVariant(EObject newRecordListSequenceVariant)
  {
    if (newRecordListSequenceVariant != recordListSequenceVariant)
    {
      NotificationChain msgs = null;
      if (recordListSequenceVariant != null)
        msgs = ((InternalEObject)recordListSequenceVariant).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.HEADER_DEF__RECORD_LIST_SEQUENCE_VARIANT, null, msgs);
      if (newRecordListSequenceVariant != null)
        msgs = ((InternalEObject)newRecordListSequenceVariant).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.HEADER_DEF__RECORD_LIST_SEQUENCE_VARIANT, null, msgs);
      msgs = basicSetRecordListSequenceVariant(newRecordListSequenceVariant, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.HEADER_DEF__RECORD_LIST_SEQUENCE_VARIANT, newRecordListSequenceVariant, newRecordListSequenceVariant));
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
      case CjsidlPackage.HEADER_DEF__RECORD_LIST_SEQUENCE_VARIANT:
        return basicSetRecordListSequenceVariant(null, msgs);
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
      case CjsidlPackage.HEADER_DEF__COMMENT:
        return getComment();
      case CjsidlPackage.HEADER_DEF__NAME:
        return getName();
      case CjsidlPackage.HEADER_DEF__RECORD_LIST_SEQUENCE_VARIANT:
        return getRecordListSequenceVariant();
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
      case CjsidlPackage.HEADER_DEF__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.HEADER_DEF__NAME:
        setName((String)newValue);
        return;
      case CjsidlPackage.HEADER_DEF__RECORD_LIST_SEQUENCE_VARIANT:
        setRecordListSequenceVariant((EObject)newValue);
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
      case CjsidlPackage.HEADER_DEF__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.HEADER_DEF__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CjsidlPackage.HEADER_DEF__RECORD_LIST_SEQUENCE_VARIANT:
        setRecordListSequenceVariant((EObject)null);
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
      case CjsidlPackage.HEADER_DEF__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.HEADER_DEF__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CjsidlPackage.HEADER_DEF__RECORD_LIST_SEQUENCE_VARIANT:
        return recordListSequenceVariant != null;
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

} //headerDefImpl
