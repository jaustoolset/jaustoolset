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
import org.jts.eclipse.cjsidl.messageSet;
import org.jts.eclipse.cjsidl.messages;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>message Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.messageSetImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.messageSetImpl#getInputComment <em>Input Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.messageSetImpl#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.messageSetImpl#getOutputComment <em>Output Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.messageSetImpl#getOutputSet <em>Output Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class messageSetImpl extends MinimalEObjectImpl.Container implements messageSet
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
   * The default value of the '{@link #getInputComment() <em>Input Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInputComment()
   * @generated
   * @ordered
   */
  protected static final String INPUT_COMMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getInputComment() <em>Input Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInputComment()
   * @generated
   * @ordered
   */
  protected String inputComment = INPUT_COMMENT_EDEFAULT;

  /**
   * The cached value of the '{@link #getInputSet() <em>Input Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInputSet()
   * @generated
   * @ordered
   */
  protected messages inputSet;

  /**
   * The default value of the '{@link #getOutputComment() <em>Output Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutputComment()
   * @generated
   * @ordered
   */
  protected static final String OUTPUT_COMMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOutputComment() <em>Output Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutputComment()
   * @generated
   * @ordered
   */
  protected String outputComment = OUTPUT_COMMENT_EDEFAULT;

  /**
   * The cached value of the '{@link #getOutputSet() <em>Output Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutputSet()
   * @generated
   * @ordered
   */
  protected messages outputSet;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected messageSetImpl()
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
    return CjsidlPackage.eINSTANCE.getmessageSet();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.MESSAGE_SET__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getInputComment()
  {
    return inputComment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInputComment(String newInputComment)
  {
    String oldInputComment = inputComment;
    inputComment = newInputComment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.MESSAGE_SET__INPUT_COMMENT, oldInputComment, inputComment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public messages getInputSet()
  {
    return inputSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInputSet(messages newInputSet, NotificationChain msgs)
  {
    messages oldInputSet = inputSet;
    inputSet = newInputSet;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.MESSAGE_SET__INPUT_SET, oldInputSet, newInputSet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInputSet(messages newInputSet)
  {
    if (newInputSet != inputSet)
    {
      NotificationChain msgs = null;
      if (inputSet != null)
        msgs = ((InternalEObject)inputSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.MESSAGE_SET__INPUT_SET, null, msgs);
      if (newInputSet != null)
        msgs = ((InternalEObject)newInputSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.MESSAGE_SET__INPUT_SET, null, msgs);
      msgs = basicSetInputSet(newInputSet, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.MESSAGE_SET__INPUT_SET, newInputSet, newInputSet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOutputComment()
  {
    return outputComment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOutputComment(String newOutputComment)
  {
    String oldOutputComment = outputComment;
    outputComment = newOutputComment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.MESSAGE_SET__OUTPUT_COMMENT, oldOutputComment, outputComment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public messages getOutputSet()
  {
    return outputSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOutputSet(messages newOutputSet, NotificationChain msgs)
  {
    messages oldOutputSet = outputSet;
    outputSet = newOutputSet;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.MESSAGE_SET__OUTPUT_SET, oldOutputSet, newOutputSet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOutputSet(messages newOutputSet)
  {
    if (newOutputSet != outputSet)
    {
      NotificationChain msgs = null;
      if (outputSet != null)
        msgs = ((InternalEObject)outputSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.MESSAGE_SET__OUTPUT_SET, null, msgs);
      if (newOutputSet != null)
        msgs = ((InternalEObject)newOutputSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.MESSAGE_SET__OUTPUT_SET, null, msgs);
      msgs = basicSetOutputSet(newOutputSet, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.MESSAGE_SET__OUTPUT_SET, newOutputSet, newOutputSet));
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
      case CjsidlPackage.MESSAGE_SET__INPUT_SET:
        return basicSetInputSet(null, msgs);
      case CjsidlPackage.MESSAGE_SET__OUTPUT_SET:
        return basicSetOutputSet(null, msgs);
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
      case CjsidlPackage.MESSAGE_SET__COMMENT:
        return getComment();
      case CjsidlPackage.MESSAGE_SET__INPUT_COMMENT:
        return getInputComment();
      case CjsidlPackage.MESSAGE_SET__INPUT_SET:
        return getInputSet();
      case CjsidlPackage.MESSAGE_SET__OUTPUT_COMMENT:
        return getOutputComment();
      case CjsidlPackage.MESSAGE_SET__OUTPUT_SET:
        return getOutputSet();
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
      case CjsidlPackage.MESSAGE_SET__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.MESSAGE_SET__INPUT_COMMENT:
        setInputComment((String)newValue);
        return;
      case CjsidlPackage.MESSAGE_SET__INPUT_SET:
        setInputSet((messages)newValue);
        return;
      case CjsidlPackage.MESSAGE_SET__OUTPUT_COMMENT:
        setOutputComment((String)newValue);
        return;
      case CjsidlPackage.MESSAGE_SET__OUTPUT_SET:
        setOutputSet((messages)newValue);
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
      case CjsidlPackage.MESSAGE_SET__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.MESSAGE_SET__INPUT_COMMENT:
        setInputComment(INPUT_COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.MESSAGE_SET__INPUT_SET:
        setInputSet((messages)null);
        return;
      case CjsidlPackage.MESSAGE_SET__OUTPUT_COMMENT:
        setOutputComment(OUTPUT_COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.MESSAGE_SET__OUTPUT_SET:
        setOutputSet((messages)null);
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
      case CjsidlPackage.MESSAGE_SET__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.MESSAGE_SET__INPUT_COMMENT:
        return INPUT_COMMENT_EDEFAULT == null ? inputComment != null : !INPUT_COMMENT_EDEFAULT.equals(inputComment);
      case CjsidlPackage.MESSAGE_SET__INPUT_SET:
        return inputSet != null;
      case CjsidlPackage.MESSAGE_SET__OUTPUT_COMMENT:
        return OUTPUT_COMMENT_EDEFAULT == null ? outputComment != null : !OUTPUT_COMMENT_EDEFAULT.equals(outputComment);
      case CjsidlPackage.MESSAGE_SET__OUTPUT_SET:
        return outputSet != null;
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
    result.append(", inputComment: ");
    result.append(inputComment);
    result.append(", outputComment: ");
    result.append(outputComment);
    result.append(')');
    return result.toString();
  }

} //messageSetImpl
