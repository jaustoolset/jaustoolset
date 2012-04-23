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

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.guard;
import org.jts.eclipse.cjsidl.guardAction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>guard</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.guardImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.guardImpl#getGuardAction <em>Guard Action</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.guardImpl#getEquiv <em>Equiv</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.guardImpl#getLogicalOperator <em>Logical Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class guardImpl extends MinimalEObjectImpl.Container implements guard
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
   * The cached value of the '{@link #getGuardAction() <em>Guard Action</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGuardAction()
   * @generated
   * @ordered
   */
  protected EList<guardAction> guardAction;

  /**
   * The default value of the '{@link #getEquiv() <em>Equiv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEquiv()
   * @generated
   * @ordered
   */
  protected static final String EQUIV_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEquiv() <em>Equiv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEquiv()
   * @generated
   * @ordered
   */
  protected String equiv = EQUIV_EDEFAULT;

  /**
   * The cached value of the '{@link #getLogicalOperator() <em>Logical Operator</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLogicalOperator()
   * @generated
   * @ordered
   */
  protected EList<String> logicalOperator;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected guardImpl()
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
    return CjsidlPackage.eINSTANCE.getguard();
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
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.GUARD__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<guardAction> getGuardAction()
  {
    if (guardAction == null)
    {
      guardAction = new EObjectContainmentEList<guardAction>(guardAction.class, this, CjsidlPackage.GUARD__GUARD_ACTION);
    }
    return guardAction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEquiv()
  {
    return equiv;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEquiv(String newEquiv)
  {
    String oldEquiv = equiv;
    equiv = newEquiv;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.GUARD__EQUIV, oldEquiv, equiv));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getLogicalOperator()
  {
    if (logicalOperator == null)
    {
      logicalOperator = new EDataTypeEList<String>(String.class, this, CjsidlPackage.GUARD__LOGICAL_OPERATOR);
    }
    return logicalOperator;
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
      case CjsidlPackage.GUARD__GUARD_ACTION:
        return ((InternalEList<?>)getGuardAction()).basicRemove(otherEnd, msgs);
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
      case CjsidlPackage.GUARD__COMMENT:
        return getComment();
      case CjsidlPackage.GUARD__GUARD_ACTION:
        return getGuardAction();
      case CjsidlPackage.GUARD__EQUIV:
        return getEquiv();
      case CjsidlPackage.GUARD__LOGICAL_OPERATOR:
        return getLogicalOperator();
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
      case CjsidlPackage.GUARD__COMMENT:
        setComment((String)newValue);
        return;
      case CjsidlPackage.GUARD__GUARD_ACTION:
        getGuardAction().clear();
        getGuardAction().addAll((Collection<? extends guardAction>)newValue);
        return;
      case CjsidlPackage.GUARD__EQUIV:
        setEquiv((String)newValue);
        return;
      case CjsidlPackage.GUARD__LOGICAL_OPERATOR:
        getLogicalOperator().clear();
        getLogicalOperator().addAll((Collection<? extends String>)newValue);
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
      case CjsidlPackage.GUARD__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case CjsidlPackage.GUARD__GUARD_ACTION:
        getGuardAction().clear();
        return;
      case CjsidlPackage.GUARD__EQUIV:
        setEquiv(EQUIV_EDEFAULT);
        return;
      case CjsidlPackage.GUARD__LOGICAL_OPERATOR:
        getLogicalOperator().clear();
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
      case CjsidlPackage.GUARD__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case CjsidlPackage.GUARD__GUARD_ACTION:
        return guardAction != null && !guardAction.isEmpty();
      case CjsidlPackage.GUARD__EQUIV:
        return EQUIV_EDEFAULT == null ? equiv != null : !EQUIV_EDEFAULT.equals(equiv);
      case CjsidlPackage.GUARD__LOGICAL_OPERATOR:
        return logicalOperator != null && !logicalOperator.isEmpty();
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
    result.append(", equiv: ");
    result.append(equiv);
    result.append(", logicalOperator: ");
    result.append(logicalOperator);
    result.append(')');
    return result.toString();
  }

} //guardImpl
